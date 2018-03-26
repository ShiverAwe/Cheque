package com.github.shiverawe.cheque

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

    val sample2 = ChequeCredentials(
            inn = "7728594673",
            kkt = "0000077369008344",
            fn = "8710000101726647",
            fd = "108885", // 598
            fp = "3641221202",
            shiftNumber = "17",
            shiftDocNumber = "28",
            summary = 59800,
            date = "2018-02-24T15:19:00"
    )

    val sample3 = ChequeCredentials(
            inn = "7728029110",
            kkt = "0001252596043709",
            fn = "8710000101336577",
            fd = "56004",
            fp = "266920135",
            shiftNumber = "179",
            shiftDocNumber = "242",
            summary = 43827,
            date = "2018-02-26T19:33:00"
    )

    val sample4 = ChequeCredentials(
            inn = "7825706086",
            kkt = "0000054967036943",
            fn = "8710000101860388",
            fd = "6699",
            fp = "2364073400",
            shiftNumber = "23",
            shiftDocNumber = "98",
            summary = 7890,
            date = "2018-03-09T15:32:00"
    )

    val sample5 = ChequeCredentials(
            inn = "4707028657",
            kkt = "0000529830027784",
            fn = "8710000100693679",
            fd = "64665",
            fp = "491494742",
            shiftNumber = "275",
            shiftDocNumber = "112",
            summary = 60000,
            date = "2018-01-19T14:50:00"
    )

    val sample6 = ChequeCredentials(
            inn = "2310031475",
            kkt = "0000478464000357",
            fn = "8710000100305430",
            fd = "69255",
            fp = "3722131219",
            shiftNumber = "283",
            shiftDocNumber = "90",
            summary = 31800,
            date = "2018-03-07T12:23:00"
    )

    val sample7 = ChequeCredentials(
            inn = "7728551528",
            kkt = "0000285000019018",
            fn = "8710000100350674",
            fd = "43301",
            fp = "3505652326",
            shiftNumber = "330",
            shiftDocNumber = "126",
            summary = 43400,
            date = "2018-03-07T17:40:00"
    )

    val sample8 = ChequeCredentials(
            inn = "4707028657",
            kkt = "0000528000039896",
            fn = "8710000100693132",
            fd = "61462",
            fp = "1083622415",
            shiftNumber = "349",
            shiftDocNumber = "35",
            summary = 57500,
            date = "2018-01-17T00:23:00"
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

