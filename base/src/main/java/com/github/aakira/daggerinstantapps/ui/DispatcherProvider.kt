package com.github.aakira.daggerinstantapps.ui

import javax.inject.Inject

class DispatcherProvider @Inject constructor() {

    val errorDispatcher = ErrorDispatcher()

}