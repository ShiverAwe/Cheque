package com.github.shiverawe

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