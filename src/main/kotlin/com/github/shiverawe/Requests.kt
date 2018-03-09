package com.github.shiverawe

import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

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
            return """https://ofd.ru/api/rawdoc/RecipeInfo?Fn=${cc.fn}&Kkt=${cc.kkt}&Inn=${cc.inn}&Num=${cc.fd}&Sign=${cc.fp}"""
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

    fun executePost(url: String, body: String = ""): String {
        val obj = URL(url)
        val conn = obj.openConnection() as HttpURLConnection
        conn.doOutput = true

        conn.outputStream.use {
            OutputStreamWriter(it).use {
                it.write(body)
            }
        }

        val response = conn.inputStream.use{
            it. bufferedReader().use {
                it.lines().reduce(String::plus)
            }
        }.orElse("")

        return response
    }
}