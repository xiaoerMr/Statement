package com.app.lib_comment.update

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.lifecycleScope
import com.app.lib_comment.down.DownFile
import com.app.lib_comment.ext.e
import com.app.lib_comment.util.FileUtils
import com.permissionx.guolindev.PermissionX
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File

/**
 * 版本升级
 */
class UpdateManager(private val activity: AppCompatActivity) {

    private val action = "android.intent.action.VIEW"
    private val type = "application/vnd.android.package-archive"
    private val category = "android.intent.category.DEFAULT"
    private val fileProvider by lazy { "${activity.applicationContext.packageName}.fileProvider" }
    private val dialog by lazy { UpdateVersionDialog() }
    private val downFileManager by lazy { DownFile() }

    /**
     * 检查是否需要升级
     */
    fun checkVersion() {
//        val versionName = SystemInfo().getVersionName(activity)
        // 请求接口获取最新版本号；
        downFileManager.checkVersion { downUrl, forceUpdate, buildVersion ->
            showUpdateDialog(downUrl, forceUpdate, buildVersion)
        }
    }

    /**
     * 更新提示框
     */
    private fun showUpdateDialog(downUrl: String, forceUpdate: Boolean, buildVersion: String) {
        dialog.showUpdateDialog(
            updateListener = { checkPermission(downUrl) },
            closeListener = {}
        )
    }

    /**
     * 检查写权限
     */
    private fun checkPermission(downUrl: String) {
        PermissionX.init(activity)
            .permissions(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
            )
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {
                    downNewVersion(downUrl)
                }
            }
    }

    /**
     * 下载新版本
     */
    private fun downNewVersion(downUrl: String) {
        activity.lifecycleScope.launch {
            DownWorker().statDown(downUrl,
                process = {
                    downProgressDialog(it)
                },
                success = {
                    installApk(it)
                }
            )
        }
    }

    /**
     * 下载进度框
     */
    private fun downProgressDialog(progress: Int) {
        dialog.showProgressDialog(progress)
    }

    /**
     * 安装新版本 安装
     */
    private fun installApk(apk: File) {
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