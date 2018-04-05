package com.github.shiverawe.cheque.database.dao

import com.github.shiverawe.cheque.entities.ChequeItem
import org.h2.jdbc.JdbcSQLException

class H2ChequeItemDao : H2Dao(), ChequeItemDao {

    val TABLE_NAME: String = config["h2.tables.cheque_items"]

    override fun createTable() {
        try {
            withConnection {
                withStatement {
                    executeUpdate(
                            "CREATE TABLE ${TABLE_NAME} (id INTEGER PRIMARY KEY AUTO_INCREMENT, chequeId INTEGER, originName VARCHAR(50), name VARCHAR(50), cost FLOAT);"
                    )
                }
            }
        } catch (e: JdbcSQLException) {
            System.err.println(e.message)
        }
    }

    override fun getChequeItems(chequeId: Int): List<ChequeItem> {
        return withConnection {
            withStatement {
                executeQuery("SELECT * FROM ${TABLE_NAME};").collect {
                    ChequeItem(
                            id = resultSet.getInt("id"),
                            chequeId = resultSet.getInt("chequeId"),
                            originName = resultSet.getString("originName"),
                            name = resultSet.getString("name"),
                            cost = resultSet.getFloat("cost")
                    )
                }
            }
        }
    }

    override fun create(item: ChequeItem) {
        val fields = "chequeId, originName, name, cost"
        val values = "'${item.chequeId}', '${item.originName}', '${item.name}', '${item.cost}'"
        withConnection {
            withStatement {
                executeUpdate("INSERT INTO ${TABLE_NAME} ($fields) VALUES ($values);")
            }
        }
    }

    override fun delete(id: Int) {
        withConnection {
            withStatement {
                executeUpdate("DELETE FROM ${TABLE_NAME} WHERE id='${id}'")
            }
        }
    }

}
