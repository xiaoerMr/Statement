package com.app.lib_comment.update

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File

class UpdateManager(private val activity: AppCompatActivity) {

    private val action = "android.intent.action.VIEW"
    private val type = "application/vnd.android.package-archive"
    private val category = "android.intent.category.DEFAULT"
    private val fileProvider by lazy { "${activity.applicationContext.packageName}.fileprovider" }

    /**
     * 检查是否需要升级
     */
    fun checkVersion() {

    }

    /**
     * 更新提示框
     */
    fun showUpdateDialog() {

    }

    /**
     * 检查写权限
     */
    private fun checkPermission(){

    }

    /**
     * 下载新版本
     */
    fun downNewVersion() {

    }

    /**
     * 下载进度框
     */
    private fun downDialog() {

    }

    /**
     * 安装新版本 安装
     */
    fun installApk(apk: File) {
        val intent = Intent(action)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        //对Android N(24) 及以上的版本做判断
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val apkUriN = FileProvider.getUriForFile(activity, fileProvider, apk)
            intent.addCategory(category)
            //权限
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.setDataAndType(apkUriN, type)
        } else {
            val apkUri = Uri.fromFile(apk)
            intent.setDataAndType(apkUri, type)
        }
        activity.startActivity(intent)
    }
}