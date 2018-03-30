package com.github.shiverawe.cheque.lib

/**
 * This class provides easy way to load config from file.
 */
open class PropertiesInstrumented(val filename: String) {

    private val properties = java.util.Properties()

    init {
        this.javaClass.getResourceAsStream("/$filename").use {
            properties.load(it)
        }
    }

    protected fun property(name: String): String {
        if (!properties.contains(name)) throw kotlin.NoSuchElementException("File '$filename' does not contain property named '$name'")
        return properties.getProperty(name)
    }

}
