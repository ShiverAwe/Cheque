package com.github.shiverawe.cheque.nalogru

import com.github.shiverawe.cheque.lib.FileConfiguration

object Credentials{
    val config = FileConfiguration("secret.properties")
    val username = config["nalogru.username"]
    val pass = config["nalogru.password"]
}