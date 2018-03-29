package com.github.shiverawe.cheque.lib

class FileConfiguration(filename: String) : PropertiesInstrumented(filename) {
    operator fun get(property: String): String{
        return property(property)
    }
}