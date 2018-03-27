package com.github.shiverawe.cheque.database.dsl

import org.jetbrains.exposed.sql.Table

object Cheques : Table() {
    val id = integer("id").primaryKey().autoIncrement()
    val ofd = varchar("ofd", length = 50)
    val fn = varchar("fn", length = 50)
    val fd = varchar("fd", length = 50)
    val fp = varchar("fp", length = 50)
    val date = varchar("date", length = 50)
}
