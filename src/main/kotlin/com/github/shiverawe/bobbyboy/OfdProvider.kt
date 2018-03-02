//package com.github.shiverawe.bobbyboy
//
//import java.lang.System.err
//import java.util.*
//
//open class OFDProvider {
//    // заводской номер фискального накопителя
//    // fiscalDriveId
//    // fn
//    // ФН
//    val fiscal_drive_id = null
//    // номер фискального документа
//    // fiscalDocumentNumber
//    // i
//    // ФД
//    val fiscal_document_number = null
//    // номер ФПД
//    // фискальный признак документа (подпись)
//    // fp
//    // ФП
//    val fiscal_id = null
//    // регистрационный номер ККТ
//    val kkt = null
//    // инн
//    val inn = null
//    // время покупки
//    val time = null
//    // сумма чека из ОФД
//    val raw_sum = 0
//    // подсчитанная сумма чека
//    val total_sum = 0
//    // номер чека
//    val number = 0
//    // идентификатор чека на сервере
//    val receipt_id = ""
//    // данные чека
//    val receipt_data = null
//    // опция для возможности повторной отправки данных уже сохраненного чека
//    val resend = false
//    // место хранения и траты денег по умолчанию
//    val payment_method = config.payment_method.default
//    // время из чека
//    val raw_time = null
//
//    // регулярное выражение для проверки соответствия формату текста QR
//    val ofd_type1_match_regexp = """t=([\dT]+)&s=([\d\.]+)&fn=(\d+)&i=(\d+)&fp=(\d+)&n=(\d+)"""
//
//    fun __init__(self: Any, resend: Any) {
//        self.resend = resend
//    }
//
//    fun load(self: Any, data: Collection<Any>) {
//        for (key in data) {
//            setattr(self, key, data[key])
//        }
//    }
//
//    @staticmethod
//    fun parse_data(fields) {
//        time = datetime.datetime.strptime(fields[0], "%Y%m%dT%H%M%S")
//        drebtime = time.strftime("%Y-%m-%d %H:%M:%S")
//
//        return {
//            "raw_time" -> fields[0],
//            "time": time,
//            "dreb_time": drebtime,
//            "raw_sum": "{0:.2f}".format(float(fields[1])),
//            "fiscal_drive_id": fields[2],
//            "fiscal_document_number": fields[3],
//            "fiscal_id": fields[4],
//            "number": fields[5]
//        }
//    }
//
//    // определение ОФД по данным чека и запросами
//    fun detect(self, text, kkt = null, inn = null) {
//        var data
//
//        var ofd_type1_match = re.match(self.ofd_type1_match_regexp, text)
//        // проверка чека по обычному на данный момент содержанию QR
//
//        if (ofd_type1_match) {
//            // получаем данные чека
//            data = self.parse_data(ofd_type1_match.groups())
//
//
//            print("Ticket {3} at {0} with sum {1}, FPD {4}, fiscal drive {2} (n={5})".format(
//                    data["time"], data["raw_sum"], data["fiscal_drive_id"], data["fiscal_document_number"],
//                    data["fiscal_id"], data["number"]))
//
//            data["kkt"] = kkt
//            data["inn"] = inn
//
//            // для списка известных провайдеров
//            for provider in [PlatformaOFD, Taxcom, OFDRU, OFD1, OFDYA]:
//            // проверяем что данные удовлетворяют требованиям ОФД
//            if provider(self.resend).is_suitable(data):
//            // инициализируем и загружаем данные
//            ofd = provider(self.resend)
//            ofd.load(data)
//            // если поиск успешен, то возвращаем инстанс чека этого ОФД
//            try {
//                if (ofd.search())
//                    return ofd
//            } catch (e: Exception) {
//                err.print("Request error: ")
//                e.printStackTrace()
//            }
//            return true
//        } else if (text.startswith("http://check.egais.ru")) {
//            print("This is an EGAIS receipt without sum!")
//            return false
//        } else {
//            // добавить распознавание ЕГАИС
//            print("No match with known OFD in content!")
//            return false
//        }
//    }
//
//    // имя файла для сохранения контента чека из ОФД
//    fun get_receipt_file_name(self) {
//        filename = os.path.dirname(os.path.realpath(__file__)) +
//                self.raw_time + "_" + self.fiscal_id + \
//        "_" + self.fiscal_drive_id + ".txt"
//        return os.path.join(config.receipt_dir, filename)
//    }
//
//    // имя файла для сохранения файла загрузки в Дребеденьги
//    fun get_csv_file_name(self) {
//        val filename = self.raw_time + "_" + self.fiscal_id + "_" + self.fiscal_drive_id + ".csv"
//        return os.path.join(config.report_dir, filename)
//    }
//}
//
//class OFDRU : OFDProvider {
//    url_receipt_get = "https://ofd.ru/api/rawdoc/RecipeInfo?Fn={}&Kkt={}&Inn={}&Num={}&Sign={}"
//
//    @staticmethod
//    fun is_suitable(data) {
//        return ret(
//                data["fiscal_drive_id"],
//                data["fiscal_id"],
//                data["fiscal_document_number"],
//                data["kkt"] and data["inn"]
//        )
//    }
//
//    fun search(self) {
//        print("Search in OFD.RU...")
//        url = self.url_receipt_get.format(
//                self.fiscal_drive_id, self.kkt, self.inn, self.fiscal_document_number, self.fiscal_id)
//        request = requests.get(url)
//        if (request.status_code == 404) {
//            print("Not found!")
//            return false
//        } else {
//            self.receipt_data = request.content
//            filename = self.get_receipt_file_name()
//            if (!os.path.exists(filename)) {
//                val outfile = open(filename, 'w')
//                outfile.write(self.receipt_data)
//            }
//        }
//        return true
//    }
//
//    fun get_items(self) {
//        if (self.receipt_data) {
//            self.total_sum = 0
//            self.receipt_data = json.loads(self.receipt_data)
//            items_count = len(self.receipt_data["Document"]["Items"])
//            print("Found items: {}".format(items_count))
//
//            val items = []
//            for (item in self.receipt_data["Document"]["Items"]) {
//                val name = item["Name"].encode("utf8")
//                val summa = (item["Total"]) / 100.0
//                val price = (item["Price"]) / 100.0
//                val count = item["Quantity"]
//                self.total_sum += summa
//
//                if (count != 1) {
//                    items.append(
//                            ("{} ({} * {})".format(name, price, count),
//                            "-{0:.2f}".format(summa)))
//                } else {
//                    items.append((name, "-{0:.2f}".format(summa)))
//                }
//            }
//            print("Items total sum: {}".format(self.total_sum))
//            self.total_sum = "{0:.2f}".format(self.total_sum)
//            if (self.total_sum != self.raw_sum) {
//                print("WARNING! Manually calculated sum ${self.total_sum} is not equal to the receipt sum ${self.raw_sum}!")
//            }
//            self.items = items
//            return items
//        } else {
//            print("No receipt data!")
//            return false
//        }
//    }
//}