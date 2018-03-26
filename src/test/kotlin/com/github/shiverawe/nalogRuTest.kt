package com.github.shiverawe

import org.junit.Test

class nalogRuTest {

    @Test
    fun checkNalogRuReturnsDataForAnyCheck(){
        NalogRu.url(ChequeSamples.sample1)
    }
}