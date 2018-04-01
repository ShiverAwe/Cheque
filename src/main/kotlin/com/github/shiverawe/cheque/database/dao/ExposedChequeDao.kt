package com.github.shiverawe.cheque.database.dao

import com.github.shiverawe.cheque.entities.ChequeCredentials
import com.github.shiverawe.cheque.database.dsl.Cheque
import com.github.shiverawe.cheque.database.dsl.ChequeItems
import com.github.shiverawe.cheque.database.dsl.Cheques
import com.github.shiverawe.cheque.lib.FileConfiguration
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction


class ExposedChequeDao : ChequeDao {
    override fun createTable() {
        Database.connect(jdbcUrl, driver)
        transaction {
            SchemaUtils.create(Cheques, ChequeItems)
        }
    }

    private val config = FileConfiguration("database.properties")

    private val jdbcUrl = "jdbc:h2:./src/main/resources/h2/${config["h2.database_name"]}"
    private val driver = "org.h2.Driver"

    override fun getAll(): List<ChequeCredentials> {
        Database.connect(jdbcUrl, driver)
        return transaction {
            Cheque.all().map {
                ChequeCredentials(
                        inn = it.inn,
                        kkt = it.kkt,
                        ofd = it.ofd,
                        fn = it.fn,
                        fd = it.fd,
                        fp = it.fp,
                        shiftNumber = it.shiftNumber,
                        shiftDocNumber = it.shiftDocNumber,
                        summary = it.summary,
                        date = it.date
                )
            }
        }
    }

    override fun add(cheque: ChequeCredentials) {
        Database.connect(jdbcUrl, driver)
        transaction {
            Cheque.new {
                inn = cheque.inn
                kkt = cheque.kkt
                ofd = cheque.ofd
                fn = cheque.fn
                fd = cheque.fd
                fp = cheque.fp
                shiftNumber = cheque.shiftNumber
                shiftDocNumber = cheque.shiftDocNumber
                summary = cheque.summary
                date = cheque.date
            }
        }
    }
}