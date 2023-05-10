@file:JvmName("Logger")

package com.app.lib_comment.ext

import android.util.Log
import com.app.lib_comment.BuildConfig
import com.app.lib_comment.util.SystemInfo
import java.util.*

private val systemInfo by lazy { SystemInfo() }

fun i(tag: String, msg: String) {
    if (systemInfo.getDebug()) {
        Log.i(tag,msg)
    }
}

fun d(tag: String, msg: String) {
    if (systemInfo.getDebug()) {
        Log.d(tag,msg)
    }
}

fun w(tag: String, msg: String) {
    if (systemInfo.getDebug()) {
        Log.w(tag,msg)
    }
}

fun e(tag: String, msg: String) {
    if (systemInfo.getDebug()) {
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