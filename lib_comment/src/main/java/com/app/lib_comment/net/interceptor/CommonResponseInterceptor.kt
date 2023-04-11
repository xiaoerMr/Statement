package com.app.lib_comment.net.interceptor

import com.app.lib_comment.ext.d
import com.app.lib_comment.net.Network
import okhttp3.Interceptor
import okhttp3.Response

class CommonResponseInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val startTime = System.currentTimeMillis()
        val request = chain.request()
        val response = chain.proceed(request)

        d(Network.TAG, "url=${request.url}, requestTime=${System.currentTimeMillis() - startTime}ms")

        return response
    }
}