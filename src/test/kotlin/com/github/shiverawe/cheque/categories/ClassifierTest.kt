package com.github.shiverawe.cheque.categories

import org.junit.Test

class ClassifierTest {

    /**
     * Checks that that Parser.getCategoryFor correctly classifies items
     */
    @Test
    fun main() {
        val parser = Parser()

        // СЛАДОСТИ
        assert(parser.getCategoryFor("Сникерс") == "sweets")

        // ОВОЩИ
        assert(parser.getCategoryFor("Картошка") == "vegetables")

        // ФРУКТЫ
        assert(parser.getCategoryFor("Яблоко") == "fruits")

        // И Т.Д...
    }
}