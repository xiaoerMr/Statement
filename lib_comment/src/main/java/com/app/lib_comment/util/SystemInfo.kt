package com.app.lib_comment.util

import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import com.app.lib_comment.BuildConfig

/**
 * 系统信息
 */
class SystemInfo {

    fun getVersionName(context:AppCompatActivity) :String = context.packageManager.getPackageInfo(
        context.packageName,  PackageManager.GET_CONFIGURATIONS).versionName

    fun getVersionCode(context:AppCompatActivity) :Int = context.packageManager.getPackageInfo(
        context.packageName,  PackageManager.GET_CONFIGURATIONS).versionCode

    fun getSDK() :Int = Build.VERSION.SDK_INT

    fun getDebug() :Boolean = BuildConfig.DEBUG
}