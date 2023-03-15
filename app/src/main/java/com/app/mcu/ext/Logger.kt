@file:JvmName("Logger")

package com.app.mcu.ext

import android.util.Log
import com.app.mcu.BuildConfig
import java.util.*

private fun isLoggable(): Boolean {
    return BuildConfig.DEBUG
}

fun i(tag: String, msg: String) {
    if (isLoggable()) {
        Log.i(tag,msg)
    }
}

fun d(tag: String, msg: String) {
    if (isLoggable()) {
        Log.d(tag,msg)
    }
}

fun w(tag: String, msg: String) {
    if (isLoggable()) {
        Log.w(tag,msg)
    }
}

fun e(tag: String, msg: String) {
    if (isLoggable()) {
        Log.e(tag,msg)
    }
}


private fun format(fmt: String, vararg args: Any): String {
    return if (args.isEmpty()) {
        fmt
    } else {
        String.format(Locale.getDefault(), fmt, *args)
    }
}