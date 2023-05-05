package com.app.lib_comment.net

import com.app.lib_comment.net.api.Api
import com.app.lib_comment.util.SystemInfo
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class Network {
    companion object{
        const val TAG = "BaseNetworkApi"
        const val TIME_OUT = 10L
    }

    fun <T> getApiService(clazz :Class<T>): T = retrofit.create(clazz)

    private val retrofit  by lazy{
         Retrofit.Builder()
            .baseUrl(Api.base)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getOkHttpClient(): OkHttpClient {
//        val okHttpClient = getCustomOkHttpClient()
//        if (null != okHttpClient) {
//            return okHttpClient
//        }
        return defaultOkHttpClient
    }

    private val defaultOkHttpClient by lazy {
        val builder = OkHttpClient.Builder()
            .callTimeout(TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)

        // 公共请求头
//        builder.addInterceptor(CommonRequestInterceptor())
        // 请求耗时
//        builder.addInterceptor(CommonResponseInterceptor())
        if (SystemInfo.getDebug()) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(loggingInterceptor)
        }

        builder.build()
    }
}