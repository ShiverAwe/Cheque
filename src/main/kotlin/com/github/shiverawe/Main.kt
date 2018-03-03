package com.github.shiverawe

import org.apache.http.client.methods.*;
import org.apache.commons.io.*;

import org.apache.http.impl.client.HttpClients
import org.apache.http.client.methods.HttpGet
import java.net.HttpURLConnection
import java.net.URL
import javax.xml.bind.DatatypeConverter
import java.io.OutputStreamWriter


private val DEFAULT_USER_AGENT = "" +
        "Mozilla/5.0 (X11; Linux x86_64) " +
        "AppleWebKit/537.36 (KHTML, like Gecko) " +
        "Chrome/33.0.1750.152 " +
        "Safari/537.36"

fun main(args: Array<String>) {
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

private fun nalogruUrl(fn: String, fd: String, fs: String): String {
    val api = "https://proverkacheka.nalog.ru:9999"
    val url = """${api}/v1/inns/*/kkts/*/fss/${fn}/tickets/${fd}?fiscalSign=${fs}&sendToEmail=no"""
    val obj = URL(url)
    val conn = obj.openConnection() as HttpURLConnection
    conn.setRequestProperty("Content-Type", "application/json")
    conn.doOutput = true
    conn.requestMethod = "POST"

    val username = "+79992002118"
    val pass = "926046"
    val credentials = "${username}:${pass}"

    val authtoken = "Basic " + DatatypeConverter
            .printBase64Binary(credentials.toByteArray())
    println(authtoken)

    conn.setRequestProperty("Device-Id", "")
    conn.setRequestProperty("Accept-Encoding", "gzip")
    conn.setRequestProperty("Connection", "Keep-Alive")
    conn.setRequestProperty("Device-OS", "Android 5.1")
    conn.setRequestProperty("Version", "2")
    conn.setRequestProperty("ClientVersion", "1.4.4.1")
    conn.setRequestProperty("UserAgent", "okhttp/3.0.1")
    conn.setRequestProperty("Authorization", authtoken)

    val data = """{"format":"json","pattern":"#"}"""


    val out = OutputStreamWriter(conn.outputStream)
    out.write(data)
    out.close()

    val inp = conn.inputStream.bufferedReader().lines()
    print(inp)

    return ""
}