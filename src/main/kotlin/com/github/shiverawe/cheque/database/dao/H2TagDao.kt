package com.github.shiverawe.cheque.database.dao

import java.util.*

class H2TagDao : H2Dao(), TagDao {
    override fun addItemForTag(itemId: Int, tagId: Int): Boolean {
        if (existsItemForTag(itemId, tagId)) return false
        withConnection {
            withStatement {
                executeUpdate("INSERT INTO ${TAGS_ITEMS_TABLE} (tagId, itemId) VALUES ('${tagId})','${itemId}'")
            }
        }
        return true
    }

    override fun existsItemForTag(itemId: Int, tagId: Int): Boolean {
        return withConnection {
            withStatement {
                executeQuery(
                        "SELECT id FROM ${TAGS_ITEMS_TABLE} WHERE tagId='${tagId}' AND itemId='${itemId}'"
                ).next() // has element
            }
        }
    }

    val TAGS_TABLE = config["h2.tables.tags"]
    val TAGS_ITEMS_TABLE = config["h2.tables.tags_items"]

    override fun createTag(tagName: String): Boolean {
        if (getTagId(tagName).isPresent) return false
        withConnection {
            withStatement {
                executeUpdate("INSERT INTO ${TAGS_TABLE} (tagName) VALUES '${tagName}'")
            }
        }
        return true
    }

    override fun getItemsForTag(tagId: Int): List<Int> {
        return withConnection {
            withStatement {
                executeQuery(
                        "SELECT itemId FROM ${TAGS_ITEMS_TABLE} WHERE id = '${tagId}'"
                ).collect {
                    getInt("itemId")
                }
            }
        }
    }

    override fun getTagId(tagName: String): Optional<Int> {
        val selectQuery = "SELECT id FROM ${TAGS_TABLE} WHERE tagName=${tagName}"
        val results = withConnection {
            withStatement {
                executeQuery(selectQuery).collect {
                    getInt("id")
                }
            }
        }
        if (results.isEmpty()) return Optional.empty()
        if (results.size == 1) return Optional.of(results[0])
        throw IllegalStateException("There is more than one tag named ${tagName}")
    }

    override fun getTags(): List<String> {
        return withConnection {
            withStatement {
                executeQuery("SELECT tagName FRON ${TAGS_TABLE}").collect {
                    getString("tagName")
                }
            }
        }
    }
}