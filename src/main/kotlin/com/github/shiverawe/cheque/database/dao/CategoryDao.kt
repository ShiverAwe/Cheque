package com.github.shiverawe.cheque.database.dao

interface CategoryDao {
    /**
     * Returns path from root category to current from category tree
     */
    fun getCategoryHierarchy(name: String): List<String>

    fun createCategory(name: String, parent: String)

}