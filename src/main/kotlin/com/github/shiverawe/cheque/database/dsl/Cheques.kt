package com.github.shiverawe.cheque.database.dsl

import org.jetbrains.exposed.dao.IntIdTable

object Cheques : IntIdTable() {
    val inn = varchar("inn", 50)
    val kkt = varchar("kkt", 50)
    val ofd = varchar("ofd", length = 50)
    val fn = varchar("fn", length = 50)
    val fd = varchar("fd", length = 50)
    val fp = varchar("fp", length = 50)
    val shiftNumber = varchar("shiftNumber", 50)
    val shiftDocNumber = varchar("shiftDocNumber", 50)
    val date = varchar("date", length = 50)
    val summary = integer("summary")
}

