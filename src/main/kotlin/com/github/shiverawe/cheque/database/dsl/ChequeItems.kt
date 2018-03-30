package com.github.shiverawe.cheque.database.dsl

import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Table

object ChequeItems : IntIdTable() {
    val chequeId = (integer("city_id").entityId() references Cheques.id).nullable()
    val name = varchar("name", length = 50)
    val cost = integer("cost")
}