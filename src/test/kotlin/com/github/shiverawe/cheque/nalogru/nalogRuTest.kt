package com.github.shiverawe.cheque.nalogru

import com.github.shiverawe.cheque.ChequeSamples
import org.junit.Test

class nalogRuTest {

    @Test
    fun checkNalogRuReturnsDataForAnyCheck(){
        NalogRu.url(ChequeSamples.sample1)
    }
}