package com.app.lib_comment.util

import android.os.Build

/**
 * 系统信息
 */
class SystemInfo {

    fun getSDK() :Int = Build.VERSION.SDK_INT

}