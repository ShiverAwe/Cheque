package com.github.shiverawe.cheque.database

import com.github.shiverawe.cheque.ChequeSamples
import com.github.shiverawe.cheque.database.dao.ChequeDao

object DbInit {
    @JvmStatic
    fun main(args: Array<String>) {
        val dao = ChequeDao()
        dao.createTable()
        dao.add(ChequeSamples.sample1)
        dao.add(ChequeSamples.sample2)
        dao.add(ChequeSamples.sample3)
        dao.add(ChequeSamples.sample4)
        dao.add(ChequeSamples.sample5)
        dao.add(ChequeSamples.sample6)
        dao.add(ChequeSamples.sample7)
        dao.add(ChequeSamples.sample8)
        dao.add(ChequeSamples.taxcomSample)
        println(dao.getAll().size)
    }
}
