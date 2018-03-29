package com.github.shiverawe.cheque.database.dao

import com.github.shiverawe.cheque.lib.FileConfiguration
import com.mchange.v2.c3p0.ComboPooledDataSource
import java.sql.Connection

abstract class AbstractH2Dao {

    private val config = FileConfiguration("database.properties")

    private val jdbsUrl = "jdbc:h2:./src/main/resources/h2/${config["h2.database_name"]}"

    private val h2: ComboPooledDataSource by lazy {
        val ds = ComboPooledDataSource()
        ds.driverClass = "org.h2.Driver"
        ds.jdbcUrl = jdbsUrl
        ds.user = config["h2.username"]
        ds.password = config["h2.password"]
        ds
    }

    val connection: Connection
        get() = h2.connection
}
