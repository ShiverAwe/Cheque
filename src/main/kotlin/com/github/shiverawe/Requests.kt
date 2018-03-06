package com.github.shiverawe

object Requests{
    fun taxcom(cc: ChequeCredentials): String {
        return """https://receipt.taxcom.ru/v01/show?fp=${cc.fp}&s=${cc.summary}"""
    }

    fun platformaOfd(cc: ChequeCredentials): String {
        return """https://lk.platformaofd.ru/web/noauth/cheque?fn=${cc.fn}&fp=${cc.fp}"""
    }

    fun ofdRu(cc: ChequeCredentials):String {
        return """"https://ofd.ru/api/rawdoc/RecipeInfo?Fn=${cc.fn}&Kkt=${cc.kkt}&Inn=${cc.inn}&Num=${cc.shiftNumber}&Sign=${cc.shiftDocNumber}"""
    }



}