package com.github.shiverawe.cheque.database.dao

import java.util.*

interface TagDao {

    /**
     * Returns `true` if tag has been created.
     * Returns `false` if tag already exists
     */
    fun createTag(tagName: String): Boolean
    fun getTagId(tagName: String): Optional<Int>
    fun getItemsForTag(tagId: Int): List<Int>
    fun getTags(): List<String>

    /**
     * Returns `true` if added.
     * Returns `false` if such binding already exists.
     */
    fun addItemForTag(itemId: Int, tagId: Int): Boolean
    fun existsItemForTag(itemId: Int, tagId: Int): Boolean
}