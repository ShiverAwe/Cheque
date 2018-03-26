package com.github.shiverawe.cheque.nalogru

import com.github.shiverawe.cheque.lib.PropertiesInstrumented

object Credentials : PropertiesInstrumented("secret.properties"){
    val username = property("nalogru.username")
    val pass = property("nalogru.password")
}