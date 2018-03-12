package com.github.shiverawe

import org.junit.Test

class TaxcomTest {

    @Test
    fun checkThatTaxcomApiReturnsCheque(){
        val ofd: OfdReceiver = Requests.taxcom
        println(ofd.receive(ChequeSamples.taxcomSample))
    }
}