package com.app.mcu.util

import android.content.Context
import android.os.Build
import android.os.Environment
import androidx.core.content.ContextCompat
import java.io.File

class FileUtils {


    private val fileName = "Mcu"

    /**
     *  私有目录（包名）
     *  storage/emulated/0/Android/data/com.app.application/files/Mcu/
     */
    fun getRootFilePath(context: Context): String? {
        val externalFile = context.getExternalFilesDir("")
        externalFile?.let {
            val file = File(it.absolutePath, fileName)
            if (!file.exists()) {
                file.mkdirs()
            }
            return file.absolutePath + File.separator
        }
        return null
    }

    /**
     * documents 公共目录下的
     * /storage/emulated/0/Documents/Mcu/
     */
    fun getRootFilePathDocuments(): String{
        val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).path +  "${File.separator}$fileName${File.separator}"
        val file = File(path)
        if (!file.exists()) {
            file.mkdirs()
        }
        return  path
    }

    fun getRootPath(context: Context): String {
        // 10版本 Q 以上
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ContextCompat.getExternalFilesDirs(context, null)[0].absolutePath
        } else {
            Environment.getExternalStorageDirectory().absolutePath
        }
    }
}