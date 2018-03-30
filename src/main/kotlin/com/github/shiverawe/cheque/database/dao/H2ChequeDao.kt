package com.github.shiverawe.cheque.database.dao

import com.github.shiverawe.cheque.ChequeCredentials
import com.github.shiverawe.cheque.lib.FileConfiguration
import java.util.*

class H2ChequeDao : H2Dao(), ChequeDao {

    override fun createTable() {
        connection.use { connection ->
            connection.createStatement().use { statement ->
                statement.executeUpdate(
                        "CREATE TABLE cheques (id INTEGER PRIMARY KEY AUTO_INCREMENT, inn VARCHAR(50), kkt VARCHAR(50), ofd VARCHAR(50), fn VARCHAR(50), fd VARCHAR(50), fp VARCHAR(50), shiftNumber VARCHAR(50), shiftDocNumber VARCHAR(50), summary VARCHAR(50), date VARCHAR(50))")
            }
        }
    }

    override fun getAll(): List<ChequeCredentials> {
        val cheques = ArrayList<ChequeCredentials>()
        connection.use { connection ->
            connection.createStatement().use { statement ->
                statement.executeQuery("SELECT * FROM ${TABLE_NAME}").use { resultSet ->
                    while (resultSet.next()) {
                        cheques.add(
                                ChequeCredentials(
                                        inn = resultSet.getString("inn"),
                                        kkt = resultSet.getString("kkt"),
                                        ofd = resultSet.getString("ofd"),
                                        fn = resultSet.getString("fn"),
                                        fd = resultSet.getString("fd"),
                                        fp = resultSet.getString("fp"),
                                        shiftNumber = resultSet.getString("shiftNumber"),
                                        shiftDocNumber = resultSet.getString("shiftDocNumber"),
                                        summary = resultSet.getInt("summary"),
                                        date = resultSet.getString("date")
                                )
                        )
                    }
                }
            }
        }
        return cheques
    }

    override fun add(cheque: ChequeCredentials) {
        val fields = "inn, kkt, ofd, fn, fd, fp, shiftNumber, shiftDocNumber, summary, date"
        val values = "'${cheque.inn}', '${cheque.kkt}', '${cheque.ofd}', '${cheque.fn}', '${cheque.fd}', '${cheque.fp}', '${cheque.shiftNumber}', '${cheque.shiftDocNumber}', '${cheque.summary}', '${cheque.date}'"
        connection.use { connection ->
            connection.createStatement().use { statement ->
                statement.executeUpdate("INSERT INTO ${TABLE_NAME} ($fields) VALUES ($values);")
            }
        }
    }

    companion object {
        val config = FileConfiguration("database.properties")
        private val TABLE_NAME: String = config["h2.tables.cheque_info"]
    }

}