package com.app.lib_comment.down

import androidx.lifecycle.viewModelScope
import com.app.lib_comment.Constants
import com.app.lib_comment.base.BaseViewModel
import com.app.lib_comment.ext.launchFlow
import com.app.lib_comment.net.Network
import com.app.lib_comment.net.api.ApiDownFile
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.io.File

class DownFile : BaseViewModel() {

    private val request by lazy {
        Network().getApiService(ApiDownFile::class.java)
    }

    //    suspend fun checkVersion(versionName: String){
    fun checkVersion(newVersion:(downUrl:String, forceUpdate:Boolean, buildVersion:String)->Unit) {

        viewModelScope.launch {
            launchFlow {
                request.checkVersion(Constants.PGYER_API_KEY, Constants.PGYER_APP_KEY)
            }.catch {

            }.collect {
//                if (it.buildHaveNewVersion) {
                    newVersion.invoke(it.downloadURL,it.needForceUpdate,it.buildVersion)
//                }
            }
        }
    }

    fun downApk(downUrl:String,
                progress :(progress:Int)->Unit,
                success :(apk: File)->Unit,
                fail :()->Unit
    ) {
        viewModelScope.launch {

        }
    }


}