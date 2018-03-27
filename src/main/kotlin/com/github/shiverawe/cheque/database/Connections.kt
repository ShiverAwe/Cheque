package com.github.shiverawe.cheque.database

import com.mchange.v2.c3p0.ComboPooledDataSource
import java.sql.Connection

class DataSource private constructor() {

    private val cpds: ComboPooledDataSource by lazy {
        ComboPooledDataSource()
    }

    val connection: Connection
        get() = this.cpds.connection

    companion object {

        private val jdbsUrlH2 = "jdbc:h2:./src/main/resources/h2/data"
        private val driverH2 = "org.h2.Driver"

        val instanceH2: DataSource by lazy {
            val ds = DataSource()
            ds.cpds.driverClass = driverH2 //loads the jdbc driver
            ds.cpds.jdbcUrl = jdbsUrlH2
            ds.cpds.user = "root"
            ds.cpds.password = "root"
            ds
        }
    }
}