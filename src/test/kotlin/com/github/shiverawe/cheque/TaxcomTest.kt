package com.github.shiverawe.cheque

import org.junit.Test

class TaxcomTest {

    @Test
    fun checkThatTaxcomApiReturnsCheque(){
        val ofd: OfdReceiver = Requests.taxcom
        println(ofd.receive(ChequeSamples.sample9))
    }
}