package com.github.shiverawe.cheque.database.dsl

import org.jetbrains.exposed.sql.Table

object ChequeItems : Table() {
    val id = integer("id").primaryKey().autoIncrement()
    val chequeId = (integer("city_id") references Cheques.id).nullable() // Column<Int?>
    val name = varchar("name", length = 50)
    val cost = integer("cost")
}