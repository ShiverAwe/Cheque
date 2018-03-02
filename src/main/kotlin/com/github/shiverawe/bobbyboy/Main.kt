package com.github.shiverawe.bobbyboy

import com.github.shiverawe.bobbyboy.*

// создаём необходимые директории если отсутствуют
fun init() {
    if (!os.path.exists(config.receipt_dir))
        os.makedirs(config.receipt_dir)
    if (!os.path.exists(config.report_dir))
        os.makedirs(config.report_dir)
}

//resend - разрешение повторно передавать данные по чеку, который уже сохранен
fun recognize(resend, receipt_text) {

    var ofd_receipt = OFDProvider(resend).detect(receipt_text)

    if (!ofd_receipt is Boolean) {
        var items = ofd_receipt.get_items()
        if (items != null) {
            return ofd_receipt
        } else if (ofd_receipt) {
            val kkt = raw_input("Enter `PH KKT`: ")
            val inn = raw_input("Enter `INN`: ")
            ofd_receipt = OFDProvider(resend).detect(receipt_text, kkt, inn)
        }
        if (!ofd_receipt is Boolean) {
            items = ofd_receipt.get_items()
            if (items) {
                return ofd_receipt
            }
        }
        return false
    }
}

fun main(args: Array<String>) {
    var parser
    parser = argparse.ArgumentParser(description = 'Import receipts data from OFD to Drebedengi')
    parser.add_argument('--text', help = 'take receipt data from string')
    parser.add_argument('--noediting', action = 'store_false', help = 'disable manual report editing')
    var args = parser.parse_args()

    init()

    var receipt
    if (args.text != null) {
        // распознаём из введённого текста
        receipt = recognize(config.already_recognized_send, args.text)
    } else {
        receipt = recognize(config.already_recognized_send, qr.get_content_with_gui())
    }

    if (receipt != null) {
        val report_name = receipt.get_csv_file_name()
        val dreb_session = Drebedengi(config.username, config.password)
        if (!dreb_session.logged_in()) {
            throw RuntimeException("Auth is not successful!")
        }

        val categories = dreb_session.get_categories()

        var ms_saved_receipt = dreb_session.search(receipt.dreb_time, receipt.raw_sum)

        if (sms_saved_receipt) {
            receipt.payment_method = sms_saved_receipt['payment_method']
        }

        report.make(receipt.items,
                categories,
                report_name,
                receipt.dreb_time,
                receipt.raw_sum,
                receipt.total_sum,
                receipt.payment_method)

        if (args.noediting) {
            report.edit(report_name)
        }

        println("Press Enter to export report to Drebedengi...")

        report.clear(report_name)

        import_result = dreb_session.send_csv(report_name)

        if (import_result && sms_saved_receipt) {
            dreb_session.delete_item(sms_saved_receipt['id'])
        }
    } else {
        print("Receipt search failed!")
    }

}