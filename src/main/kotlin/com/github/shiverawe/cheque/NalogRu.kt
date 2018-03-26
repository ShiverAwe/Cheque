package com.github.shiverawe.cheque

import org.apache.commons.io.IOUtils
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import javax.xml.bind.DatatypeConverter

object NalogRu {

    val api = "https://proverkacheka.nalog.ru:9999"
    val username = "+79992002118"
    val pass = "926046"

    private val DEFAULT_USER_AGENT = "" +
            "Mozilla/5.0 (X11; Linux x86_64) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) " +
            "Chrome/33.0.1750.152 " +
            "Safari/537.36"

    private fun executeGet(uri: String): String {
        val req = HttpGet(uri)
        req.setHeader("User-Agent", DEFAULT_USER_AGENT)
        HttpClients.createDefault().use { client ->
            client.execute(req).use { response ->
                val inputStream = response.entity.content
                return IOUtils.toString(inputStream)
            }
        }
    }

    fun url(cc: ChequeCredentials): String {
        val url = """${api}/v1/inns/*/kkts/*/fss/${cc.fn}/tickets/${cc.fd}?fiscalSign=${cc.fp}&sendToEmail=no"""
        val obj = URL(url)
        val conn = obj.openConnection() as HttpURLConnection
        conn.doOutput = true
        conn.requestMethod = "POST"

        val credentials = "${username}:${pass}"

        val authtoken = "Basic " + DatatypeConverter
                .printBase64Binary(credentials.toByteArray())
        //println(authtoken)

        conn.setRequestProperty("Content-Type", "application/json")
        conn.setRequestProperty("Device-Id", "")
        conn.setRequestProperty("Accept-Encoding", "gzip")
        conn.setRequestProperty("Connection", "Keep-Alive")
        conn.setRequestProperty("Device-OS", "Android 5.1")
        conn.setRequestProperty("Version", "2")
        conn.setRequestProperty("ClientVersion", "1.4.4.1")
        conn.setRequestProperty("UserAgent", "okhttp/3.0.1")
        conn.setRequestProperty("Authorization", authtoken)

        val data = """{"format":"json","pattern":"#"}"""

        conn.outputStream.use {
            OutputStreamWriter(it).use {
                it.write(data)
            }
        }

        val response = conn.inputStream.use {
            it.bufferedReader().use {
                it.lines()
            }
        }

        print(response)

        return response.toString()
    }

}