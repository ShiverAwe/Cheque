package com.github.shiverawe.ofdru

import com.github.shiverawe.ChequeCredentials
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

object OfdRu{
    val host = "https://ofd.ru"
    val login = "vs3x3@mail.ru"
    val password = "ykkyzt4Vm5rY"
}

fun main(args: Array<String>) {
    val url = ofdRuIntegrationUrl(ChequeCredentials())
    val obj = URL(url)
    val conn = obj.openConnection() as HttpURLConnection
}

/**
 * This method returns URL, which returns direct link to cheque data
 * Формирование прямой ссылки на электронный чек
 */
private fun ofdRuRecUrl(cc: ChequeCredentials): String {
    return """"${OfdRu.host}rec/${cc.inn}/${cc.kkt}/${cc.fn}/${cc.shiftDocNumber}/${cc.fp}"""
}

private fun ofdRuIntegrationUrl(cc: ChequeCredentials): String{
    return """${OfdRu.host}api/integration/v1/inn/${cc.inn}/kkt/${cc.kkt}/receipt/${cc.fd}"""
}

private fun ofdRuIntegrationUrl1(cc: ChequeCredentials): String{
    return """${OfdRu.host}api/integration/v1/inn/${cc.inn}/kkt/${cc.kkt}/zreport/${cc.shiftNumber}/receipt/${cc.shiftDocNumber}"""
}

/**
 * This method allows to get access token for private organization requests
 * Авторизация через AuthToken
 */
private fun ofdRuAuthUrl(){
    val url = """${OfdRu.host}/api/Authorization/CreateAuthToken"""
    val obj = URL(url)
    val conn = obj.openConnection() as HttpURLConnection
    conn.setRequestProperty("login", OfdRu.login)
    conn.setRequestProperty("password", OfdRu.password)

    val data = """{"Login": "${OfdRu.login}","Password": "${OfdRu.password}"}"""

    val out = OutputStreamWriter(conn.outputStream)
    out.write(data)
    out.close()

    val inp = conn.inputStream.bufferedReader().lines()
    print(inp)
}
