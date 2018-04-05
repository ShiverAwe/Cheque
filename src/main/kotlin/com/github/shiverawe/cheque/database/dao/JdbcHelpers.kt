package com.github.shiverawe.cheque.database.dao

import java.sql.Connection
import java.sql.ResultSet
import java.sql.Statement
import java.util.*

interface JdbcHelpers {
    val connection: Connection


    fun <T> withConnection(action: Connection.() -> T): T {
        return connection.use { it.action() }
    }

    fun <T> Connection.withStatement(action: Statement.() -> T): T {
        return withConnection {
            createStatement().use {
                it.action()
            }
        }
    }

    /**
     * This method collects values from ResultSet rows.
     * ResultSet will be closed automatically.
     */
    fun <T> ResultSet.collect(action: ResultSet.() -> T): List<T> {
        val items = ArrayList<T>()
        use {
            while (next()) {
                items.add(action())
            }
        }
        return items
    }

    /**
     * This methos allows to execute SELECT query and collect results.
     * All exceptions will not be caught.
     * All produced resources will be closed automatically.
     * Connection will not be closed.
     */
    fun <T> select(selectQuery: String, action: ResultSet.() -> T): List<T> {
        return withConnection {
            withStatement {
                executeQuery(selectQuery).collect(action)
            }
        }
    }
}

/**
 * This class allows to give
 */
private class ResultSetWrapper(rs: ResultSet) : ResultSet by rs {
    override fun next(): Boolean = true
    override fun close() {}
}