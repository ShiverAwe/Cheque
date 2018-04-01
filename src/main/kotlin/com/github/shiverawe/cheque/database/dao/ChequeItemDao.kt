package com.github.shiverawe.cheque.database.dao

import com.github.shiverawe.cheque.entities.ChequeItem


interface ChequeItemDao {

    /**
     * Returns list of cheque items ids, which belobg to cheque with specified chequeId,
     * or empty list, if cheque id does not exist
     */
    fun getChequeItems(chequeId: Int): List<ChequeItem>

    /**
     * Creates a new cheque item
     * Id, specified in ChequeItem could be ignored and selected by database
     */
    fun create(item: ChequeItem)

    /**
     * Deletes a cheque item by id
     */
    fun delete(id: Int)
}