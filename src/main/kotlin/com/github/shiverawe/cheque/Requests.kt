package com.github.shiverawe.cheque

import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

object Requests {
    object taxcom : OfdReceiver {
        override fun url(cc: ChequeCredentials): String {
            return """https://receipt.taxcom.ru/v01/show?fp=${cc.fp}&s=${cc.summary}"""
        }
    }

    object platformaOfd : OfdReceiver {
        override fun url(cc: ChequeCredentials): String {
            return """https://lk.platformaofd.ru/web/noauth/cheque?fn=${cc.fn}&fp=${cc.fp}"""
        }
    }

    object ofdRu : OfdReceiver {
        override fun url(cc: ChequeCredentials): String {
            return """${OfdRu.host}/api/rawdoc/RecipeInfo?Fn=${cc.fn}&Kkt=${cc.kkt}&Inn=${cc.inn}&Num=${cc.fd}&Sign=${cc.fp}"""
        }

        object OfdRu {
            val host = "https://ofd.ru"
            val login = "vs3x3@mail.ru"
            val password = "ykkyzt4Vm5rY"
        }

        /**
         * This method returns URL, which returns direct link to cheque data
         * Формирование прямой ссылки на электронный чек
         */
        private fun ofdRuRecUrl(cc: ChequeCredentials): String {
            return """"${OfdRu.host}/rec/${cc.inn}/${cc.kkt}/${cc.fn}/${cc.shiftDocNumber}/${cc.fp}"""
        }

        private fun ofdRuIntegrationUrl(cc: ChequeCredentials): String {
            return """${OfdRu.host}/api/integration/v1/inn/${cc.inn}/kkt/${cc.kkt}/receipt/${cc.fd}"""
        }

        private fun ofdRuIntegrationUrl1(cc: ChequeCredentials): String {
            return """${OfdRu.host}/api/integration/v1/inn/${cc.inn}/kkt/${cc.kkt}/zreport/${cc.shiftNumber}/receipt/${cc.shiftDocNumber}"""
        }

    }

    object ofdYa : OfdReceiver {
        override fun url(cc: ChequeCredentials): String {
            return """https://ofd-ya.ru/getFiscalDoc?kktRegId=${cc.kkt}&fiscalSign=${cc.fp}&json=true"""
        }
    }

    fun _1_ofdRu(cc: ChequeCredentials): String {
        return ""
        // TODO VShefer 07 03 : Investigate how to use it
        val url_first_get = "https://consumer.1-ofd.ru/#/landing"
        val url_receipt_get = "https://consumer.1-ofd.ru/api/tickets/ticket/{}"
        val url_receipt_find = "https://consumer.1-ofd.ru/api/tickets/find-ticket"
    }
}

interface OfdReceiver {
    fun url(cc: ChequeCredentials): String

    fun receive(cc: ChequeCredentials): String {
        return executePost(url(cc))
    }

    private fun executePost(url: String, body: String = ""): String {
        val obj = URL(url)
        val conn = obj.openConnection() as HttpURLConnection
        conn.doOutput = true

        conn.outputStream.use {
            OutputStreamWriter(it).use {
                it.write(body)
            }
        }

        val response = conn.inputStream.use {
            it.bufferedReader().use {
                it.lines().reduce(String::plus)
            }
        }.orElse("")

        return response
    }
}