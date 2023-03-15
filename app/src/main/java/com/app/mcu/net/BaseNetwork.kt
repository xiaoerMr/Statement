package com.app.mcu.net

import com.app.mcu.BuildConfig
import com.app.mcu.Constants
import com.app.mcu.bean.Api
import com.app.mcu.net.interceptor.CommonRequestInterceptor
import com.app.mcu.net.interceptor.CommonResponseInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class BaseNetwork {
    companion object{
        const val TAG = "BaseNetworkApi"
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
            .callTimeout(10L, TimeUnit.SECONDS)
            .connectTimeout(10L, TimeUnit.SECONDS)
            .readTimeout(10L, TimeUnit.SECONDS)
            .writeTimeout(10L, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)

        builder.addInterceptor(CommonRequestInterceptor())
        builder.addInterceptor(CommonResponseInterceptor())
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(loggingInterceptor)
        }

        builder.build()
    }
}