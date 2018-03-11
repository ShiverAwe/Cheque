package com.github.shiverawe

object ChequeSamples {
    val sample1 = ChequeCredentials(
            inn = "7728029110",
            kkt = "0000113865043432",
            fn = "8710000100257633",
            fd = "108885",
            fp = "1924949121",
            shiftNumber = "315",
            shiftDocNumber = "411",
            summary = 178156,
            date = "2018-02-23T23:45:00"
    )


    val taxcomSample = ChequeCredentials(
            inn = "???",
            kkt = "???",
            fn = "???",
            fd = "???",
            fp = "0808855891",
            shiftNumber = "???",
            shiftDocNumber = "???",
            summary = 61100 / 100, // Для такскома не нужны копейки
            date = "???"
    )

}
