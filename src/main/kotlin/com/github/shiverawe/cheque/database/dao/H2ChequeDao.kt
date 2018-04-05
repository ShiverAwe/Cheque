package com.github.shiverawe.cheque.database.dao

import com.github.shiverawe.cheque.entities.ChequeCredentials
import com.github.shiverawe.cheque.lib.FileConfiguration
import org.h2.jdbc.JdbcSQLException
import java.util.*

class H2ChequeDao : H2Dao(), ChequeDao {

    val TABLE_NAME: String = config["h2.tables.cheque_info"]

    override fun createTable() {
        try {
            withConnection {
                withStatement {
                    executeUpdate("CREATE TABLE cheques (" +
                            "id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                            "inn VARCHAR(50), " +
                            "kkt VARCHAR(50), " +
                            "ofd VARCHAR(50), " +
                            "fn VARCHAR(50), " +
                            "fd VARCHAR(50), " +
                            "fp VARCHAR(50), " +
                            "shiftNumber VARCHAR(50), " +
                            "shiftDocNumber VARCHAR(50), " +
                            "summary VARCHAR(50), " +
                            "date VARCHAR(50))")
                }
            }
        } catch (e: JdbcSQLException) {
            System.err.println(e.message)
        }
    }

    override fun getAll(): List<ChequeCredentials> {
        return withConnection {
            withStatement {
                select("SELECT * FROM ${TABLE_NAME}") {
                    ChequeCredentials(
                            inn = getString("inn"),
                            kkt = getString("kkt"),
                            ofd = getString("ofd"),
                            fn = getString("fn"),
                            fd = getString("fd"),
                            fp = getString("fp"),
                            shiftNumber = getString("shiftNumber"),
                            shiftDocNumber = getString("shiftDocNumber"),
                            summary = getInt("summary"),
                            date = getString("date")
                    )
                }
            }
        }
    }

    override fun add(cheque: ChequeCredentials) {
        val fields = "inn, kkt, ofd, fn, fd, fp, shiftNumber, shiftDocNumber, summary, date"
        val values = "'${cheque.inn}', '${cheque.kkt}', '${cheque.ofd}', '${cheque.fn}', '${cheque.fd}', '${cheque.fp}', '${cheque.shiftNumber}', '${cheque.shiftDocNumber}', '${cheque.summary}', '${cheque.date}'"
        withConnection {
            withStatement {
                executeUpdate("INSERT INTO ${TABLE_NAME} ($fields) VALUES ($values);")
            }
        }
    }


}