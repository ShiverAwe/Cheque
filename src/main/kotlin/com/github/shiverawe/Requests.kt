package com.github.shiverawe

object Requests{
    fun taxcom(cc: ChequeCredentials): String {
        return """https://receipt.taxcom.ru/v01/show?fp=${cc.fp}&s=${cc.summary}"""
    }

    fun platformaOfd(cc: ChequeCredentials): String {
        return """https://lk.platformaofd.ru/web/noauth/cheque?fn=${cc.fn}&fp=${cc.fp}"""
    }

    fun ofdRu(cc: ChequeCredentials):String {
        return """https://ofd.ru/api/rawdoc/RecipeInfo?Fn=${cc.fn}&Kkt=${cc.kkt}&Inn=${cc.inn}&Num=${cc.fd}&Sign=${cc.fp}"""
    }

    fun _1_ofdRu(cc: ChequeCredentials): String {
        return ""
        // TODO VShefer 07 03 : Investigate how to use it
        val url_first_get = "https://consumer.1-ofd.ru/#/landing"
        val url_receipt_get = "https://consumer.1-ofd.ru/api/tickets/ticket/{}"
        val url_receipt_find = "https://consumer.1-ofd.ru/api/tickets/find-ticket"
    }


    fun ofdya(cc: ChequeCredentials): String {
        return """https://ofd-ya.ru/getFiscalDoc?kktRegId=${cc.kkt}&fiscalSign=${cc.fp}&json=true"""
    }
}