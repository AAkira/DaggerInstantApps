package com.github.aakira.daggerinstantapps.ui

open class CommonStore(private val dispatcher: CommonDispatcher) {

    fun errors() = dispatcher.errors()
}