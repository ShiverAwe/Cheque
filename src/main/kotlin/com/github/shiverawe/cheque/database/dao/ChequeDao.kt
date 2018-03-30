package com.github.shiverawe.cheque.database.dao

import com.github.shiverawe.cheque.ChequeCredentials

interface ChequeDao {

    fun getAll(): List<ChequeCredentials>

    fun add(cheque: ChequeCredentials)

    fun createTable()

}