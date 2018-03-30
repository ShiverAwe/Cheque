package com.github.shiverawe.cheque.database.dsl

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

object DbInit {
    @JvmStatic
    fun main(args: Array<String>) {
        Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")

        transaction {
            SchemaUtils.create(Cheques, ChequeItems)

            Cheques.insert {
                it[ofd] = "taxcom"
                it[fn] = "some fn"
                it[fd] = "some fd"
                it[fp] = "some fp"
            }


        }
    }
}