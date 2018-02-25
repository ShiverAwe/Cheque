package com.github.shiverawe

import org.apache.http.client.methods.*;
import org.apache.commons.io.*;

import org.apache.http.impl.client.HttpClients
import org.apache.http.client.methods.HttpGet
import java.net.HttpURLConnection
import java.net.URL
import javax.xml.bind.DatatypeConverter
import java.io.InputStreamReader
import com.sun.xml.internal.ws.streaming.XMLStreamReaderUtil.close
import com.sun.xml.internal.ws.streaming.XMLStreamWriterUtil.getOutputStream
import java.io.BufferedReader
import java.io.OutputStreamWriter


private val DEFAULT_USER_AGENT = "" +
        "Mozilla/5.0 (X11; Linux x86_64) " +
        "AppleWebKit/537.36 (KHTML, like Gecko) " +
        "Chrome/33.0.1750.152 " +
        "Safari/537.36"

fun main(args: Array<String>) {
    val INN = "7728029110"
    val KKT = "0000113865043432"
    val FN = "8710000100257633"
    val FD = "108885"
    val FP = "1924949121"
    val SMENA = "315"
    val docNumb = "411"


    nalogruUrl(FN, FD, FP)
    //print(ofdruUrl(INN, KKT, FN, docNumb, FP))
    //print(getUrl("https://ofd.ru/api/integration/v1/inn/7728029110/kkt/0000113865043432/zreport/315/receipt/411"))
}

private fun getUrl(uri: String): String {
    val req = HttpGet(uri)
    req.setHeader("User-Agent", DEFAULT_USER_AGENT)
    HttpClients.createDefault().use { client ->
        client.execute(req).use { response ->
            val inputStream = response.entity.content
            return IOUtils.toString(inputStream)
        }
    }
}

private fun getAuth(login: String, password: String): String {
    val req = HttpPost("https://ofd.ru/api/Authorization/CreateAuthToken")
    val body = """{"Login": "${login}","Password": "${password}"}"""
    HttpClients.createDefault().use { client ->
        client.execute(req).use { response ->
            val inputStream = response.entity.content
            return IOUtils.toString(inputStream)
        }
    }
}

private fun ofdruUrl(inn: String, kkt: String, fn: String, docNumb: String, fpd: String): String {
    return "https://ofd.ru/rec/${inn}/${kkt}/${fn}/${docNumb}/${fpd}"
}

private fun nalogruUrl(fn: String, fd: String, fs: String): String {

    val api = "https://proverkacheka.nalog.ru:9999"
    val url = """${api}/v1/inns/*/kkts/*/fss/${fn}/tickets/${fd}?fiscalSign=${fs}&sendToEmail=no"""
    val obj = URL(url)
    val conn = obj.openConnection() as HttpURLConnection
    conn.setRequestProperty("Content-Type", "application/json")
    conn.doOutput = true
    conn.requestMethod = "POST"

    val username = "89992002118"
    val pass = "926046"
    val credentials = "${username}:${pass}"
    val basicAuth = "Basic " + DatatypeConverter
            .printBase64Binary(credentials.toByteArray())
    conn.setRequestProperty("Authorization", basicAuth)

    val derviceId = ""
    val deviceOS = "Android 4.4.4"
    val protocol = "2"
    val clientVersion = "1.4.1.3"
    val userAgent = "okhttp/3.0.1"

    conn.setRequestProperty("Device-Id", derviceId)
    conn.setRequestProperty("Device-OS", deviceOS)
    conn.setRequestProperty("Version", protocol)
    conn.setRequestProperty("ClientVersion", clientVersion)
    conn.setRequestProperty("UserAgent", userAgent)

    val data = """{"format":"json","pattern":"#"}"""


    val out = OutputStreamWriter(conn.outputStream)
    out.write(data)
    out.close()

    val inp = BufferedReader(InputStreamReader(conn.inputStream))
    print(inp.lines())

    return ""
}