package com.app.lib_comment.update

import com.app.lib_comment.ext.e
import com.app.lib_comment.net.api.Api
import com.app.lib_comment.net.api.ApiDownFile
import com.app.lib_comment.util.FileUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class DownWorker {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Api.baseDown)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    suspend fun statDown(downUrl: String ,process:(process:Int)->Unit,success:(file:File)->Unit) {
        val api = retrofit.create(ApiDownFile::class.java)
        flow {
            val localFilePath = FileUtils().createLocalFile("xx.apk")
            val downloadFile = File(localFilePath)
            FileUtils().writeCache(api.downApk(downUrl), downloadFile){
                process.invoke(it)
            }
            emit(downloadFile)
        }.flowOn(Dispatchers.IO)
            .onStart{
                e("---->","start")
            }.catch {
                e("---->","catch:${it.message}")
            }.collect {
                success.invoke(it)
        }
    }
}