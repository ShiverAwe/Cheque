package com.github.shiverawe.cheque.categories

import java.util.*

class Parser {

    val legal = "abcdefghijklmnopqrstuvwxyz"

    fun parseItem(s: String): String {
        return s
                .toLowerCase()
                .filter {
                    legal.contains(it)
                }
    }

    val registeredItems = Arrays.asList(
           "Sneakers", "картошка"
    )

    /**
     * This meths search for occurrences in list `registeredItems` or returns "unknown"
     */
    fun findItem(s: String): String{
        if (registeredItems.contains(s)) return s;
        else return "unknown"
    }

}
