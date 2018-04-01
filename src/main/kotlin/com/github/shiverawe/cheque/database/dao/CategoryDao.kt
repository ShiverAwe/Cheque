package com.github.shiverawe.cheque.database.dao

interface CategoryDao {
    /**
     * Returns path from root category to current from category tree
     */
    fun getCategoryLine(name: String): List<String>

    fun getCategoryList(): List<String>

    fun getLeafCategories(): List<String>

    fun createCategory(name: String, parent: String)

}