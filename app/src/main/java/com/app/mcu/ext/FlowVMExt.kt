package com.app.mcu.ext

import com.app.mcu.Constants
import com.app.mcu.base.BaseViewModel
import com.app.mcu.net.ApiException
import com.app.mcu.net.BaseResponse
import com.google.gson.JsonParseException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

/**
 * 请求结果预处理
 */
suspend fun <T> BaseViewModel.launchFlow(
    showLoading: Boolean = false,
    request: suspend () -> BaseResponse<T>
): Flow<T> {
    if (showLoading) {

    }

    return flow {
                //执行请求
                val response = request.invoke()
                // 是否成功 200
                when (response.code) {
                    Constants.RESPONSE_CODE_SUCCESS -> emit(response.data) // 成功
                    Constants.RESPONSE_CODE_TOKEN_INVALID -> goLoginPage() // token 失效
                    else -> throw ApiException(response.msg)
                }
            }
            .flowOn(Dispatchers.IO)
            .onCompletion { throwable ->
                if (showLoading) {

                }
                throwable?.let {
                    throw ApiException(getExceptionMessage(throwable))
                }
            }
}

// 跳转到登录页面
private fun goLoginPage() {
e("---","----》 跳转到 登陆 页面")
}

private fun getExceptionMessage(throwable: Throwable): String {
    return when (throwable) {
        is ApiException -> throwable.message!!
        is ConnectException,
        is HttpException,
        is UnknownHostException,
        is SocketTimeoutException -> "网络连接异常"
        is JsonParseException,
        is JSONException,
        is ParseException -> "数据解析异常"
        is IllegalArgumentException -> "参数错误"
        else -> "未知错误"
    }
}
