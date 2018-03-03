package com.github.shiverawe.ofdru

import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL


private fun ofdruUrl(inn: String, kkt: String, fn: String, docNumb: String, fpd: String): String {
    return "https://ofd.ru/rec/${inn}/${kkt}/${fn}/${docNumb}/${fpd}"
}

private fun ofdruAuthUrl(){
    val login = "vs3x3@mail.ru"
    val password = "ykkyzt4Vm5rY"
    val api = "https://ofd.ru"
    val url = "${api}/api/Authorization/CreateAuthToken"
    val obj = URL(url)
    val conn = obj.openConnection() as HttpURLConnection
    conn.setRequestProperty("login", login)
    conn.setRequestProperty("password", password)

    val data = """{"Login": "$login","Password": "$password"}"""

    val out = OutputStreamWriter(conn.outputStream)
    out.write(data)
    out.close()

    val inp = conn.inputStream.bufferedReader().lines()
    print(inp)
}
