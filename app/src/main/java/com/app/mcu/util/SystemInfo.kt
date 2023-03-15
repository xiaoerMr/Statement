package com.app.mcu.util

import android.os.Build

/**
 * 系统信息
 */
class SystemInfo {

    fun getSDK() :Int = Build.VERSION.SDK_INT

}