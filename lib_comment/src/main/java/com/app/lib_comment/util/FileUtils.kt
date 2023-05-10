package com.app.lib_comment.util

import android.content.Context
import android.os.Build
import android.os.Environment
import androidx.core.content.ContextCompat
import com.app.lib_comment.ext.e
import okhttp3.ResponseBody
import java.io.File
import java.io.IOException
import java.io.RandomAccessFile
import java.nio.channels.FileChannel

class FileUtils {


    private val fileName = "Mcu"
    private val fileDownName = "down"

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
            file.mkdir()
        }
        return  path
    }

    /**
     * documents 公共目录下的 /storage/emulated/0/Documents/
     */
    fun getRootPublicDocuments(): String{
        val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).path +  File.separator
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

    fun getFileType(fileName:String):String{
        val index = fileName.lastIndexOf(".")
        return if (index <= -1)  ""
        else fileName.substring(index +1)
    }

    /**
     * 生成文件的地址
     * @param downloadUrl String
     * @return String
     */
    fun createLocalFile(fileName: String): String {
        val cashDir = getRootFilePathDocuments()
        return "$cashDir$fileDownName${File.separator}$fileName"
    }

    /**
     * 将下载的数据写入本地缓存
     * @param responseBody ResponseBody 下载的文件数据
     * @param downloadFile File 写入后的文件
     * @return File 下载的文件
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writeCache(responseBody: ResponseBody, downloadFile: File, process:(process:Int)->Unit) {
        e("launchFlowDownFile","下载文件:写入文件")
        if (!downloadFile.parentFile.exists()) {
            downloadFile.parentFile.mkdirs()
        }
        val size = responseBody.contentLength()
        if (downloadFile.exists()) {
            if (downloadFile.length() == size) {
                process.invoke(100)
                return
            } else {
                downloadFile.delete()
            }
        }

        var currentProcess = 0
        val randomAccessFile = RandomAccessFile(downloadFile, "rwd")
        val fileChannel = randomAccessFile.channel
        val mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, responseBody.contentLength())
        val buffer = ByteArray(1024 * 1024)
        var len: Int
        while (true) {
            len = responseBody.byteStream().read(buffer)
            if (len == -1) {
                break
            }
            e("launchFlowDownFile","下载文件: $currentProcess / $size = ${(currentProcess / size.toFloat() *100).toInt()}%")
            mappedByteBuffer.put(buffer, 0, len)
            currentProcess += len
            process.invoke((currentProcess / size.toFloat() *100).toInt())
        }
        process.invoke(100)
        responseBody.byteStream().close()
        fileChannel?.close()
        randomAccessFile.close()
    }
}