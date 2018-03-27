package com.github.shiverawe.cheque.categories

import com.github.shiverawe.cheque.categories.Parser
import org.junit.Test

class ParserTest {

    /**
     * Etot test ostavlyaet v input tolko bukvy (vrode kak)
     */
    @Test
    fun main() {
        val input = "kat123ya56hello";
        val parser = Parser();
        val output = parser.parseItem(input)
        println(output);
        assert(output == "katyahello")
    }

    @Test
    fun checkThatItemIsFound(){

    }

}