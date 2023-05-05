package com.app.mcu.ui.vm

import androidx.lifecycle.viewModelScope
import com.app.lib_comment.Constants
import com.app.lib_comment.base.BaseViewModel
import com.app.lib_comment.ext.launchFlow
import com.app.mcu.net.api.ApiService
import com.app.lib_comment.net.Network
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MainViewModel: BaseViewModel() {

    private var currentPage = 1

    private val request by lazy {
        Network().getApiService(ApiService::class.java)
    }

    fun getNewList(){
        viewModelScope.launch {
            launchFlow{
                request.newList(currentPage, Constants.LIST_PAGE_SIZE)
            }.catch {

            }.collect{

            }
        }
    }
}