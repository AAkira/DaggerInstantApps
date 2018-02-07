package com.github.aakira.daggerinstantapps.util

import android.content.Context
import android.support.annotation.IntDef
import android.support.annotation.StringRes
import android.widget.Toast

object ToastUtil {

    @IntDef(Toast.LENGTH_SHORT.toLong(), Toast.LENGTH_LONG.toLong())
    annotation class Duration

    private var toast: Toast? = null

    fun showToast(context: Context, @StringRes stringId: Int,
                  @Duration length: Int = Toast.LENGTH_SHORT) {
        toast?.cancel()
        toast = Toast.makeText(context, context.getString(stringId), length)
        toast?.show()
    }

    fun showToast(context: Context, text: String,
                  @Duration length: Int = Toast.LENGTH_SHORT) {
        toast?.cancel()
        toast = Toast.makeText(context, text, length)
        toast?.show()
    }
}