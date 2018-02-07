package com.github.aakira.daggerinstantapps.util.ext

import android.app.Activity
import com.github.aakira.daggerinstantapps.util.ToastUtil

fun Activity.showErrorToast(message: String = "Error") {
    ToastUtil.showToast(this, message)
}

fun Activity.showEmptywToast(message: String = "Item is empty") {
    ToastUtil.showToast(this, message)
}