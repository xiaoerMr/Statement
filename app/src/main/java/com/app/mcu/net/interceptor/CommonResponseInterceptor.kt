package com.app.mcu.net.interceptor

import com.app.mcu.ext.d
import com.app.mcu.net.BaseNetwork
import okhttp3.Interceptor
import okhttp3.Response

class CommonResponseInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val startTime = System.currentTimeMillis()
        val request = chain.request()
        val response = chain.proceed(request)

        d(BaseNetwork.TAG, "url=${request.url}, requestTime=${System.currentTimeMillis() - startTime}ms")

        return response
    }
}