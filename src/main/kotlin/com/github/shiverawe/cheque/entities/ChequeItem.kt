package com.github.shiverawe.cheque.entities

class ChequeItem(
        val id: Int,
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
        val cost: Int
)