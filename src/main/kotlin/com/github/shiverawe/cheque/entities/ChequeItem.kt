package com.github.shiverawe.cheque.entities

class ChequeItem(
        val id: Int = 0,
        /**
         * Id of cheque
         */
        val chequeId: Int,
        /**
         * Origin name from cheque
         */
        val originName: String,
        /**
         * Corresponding product name from our base
         */
        val name: String,
        /**
         * Cost of item
         */
        val cost: Float
){
        override fun toString(): String {
                return "ChequeItem(id=$id, chequeId=$chequeId, originName='$originName', name='$name', cost=$cost)"
        }
}