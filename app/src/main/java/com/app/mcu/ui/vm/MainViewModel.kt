package com.app.mcu.ui.vm

import androidx.lifecycle.viewModelScope
import com.app.mcu.api.ApiService
import com.app.mcu.base.BaseViewModel
import com.app.mcu.ext.launchFlow
import com.app.mcu.net.BaseNetwork
import kotlinx.coroutines.launch

class MainViewModel:BaseViewModel() {

    private var currentPage = 1
    private val pageSize = 20

    private val request by lazy {
        BaseNetwork().getApiService(ApiService::class.java)
    }

    fun getNewList(){
        viewModelScope.launch {
            launchFlow{
                request.newList(currentPage, pageSize)
            }
        }
    }
}