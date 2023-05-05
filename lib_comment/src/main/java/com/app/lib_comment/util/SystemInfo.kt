package com.app.lib_comment.util

import android.os.Build
import com.app.lib_comment.BuildConfig

/**
 * 系统信息
 */
object SystemInfo {

    fun getSDK() :Int = Build.VERSION.SDK_INT

    fun getDebug() :Boolean = BuildConfig.DEBUG
}