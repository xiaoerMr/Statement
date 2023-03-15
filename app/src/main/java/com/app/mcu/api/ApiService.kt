package com.app.mcu.api

import com.app.mcu.bean.Api
import com.app.mcu.bean.LoginBean
import com.app.mcu.net.BaseResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @FormUrlEncoded
    @POST(Api.login)
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String,
    ): BaseResponse<LoginBean>

    @GET(Api.newList)
    suspend fun newList(
        @Query("currentPage") currentPage: Int,
        @Query("pageSize") pageSize: Int,
    ): BaseResponse<LoginBean>

    @GET(Api.messageList)
    suspend fun messageList(
        @Header("currentPage") token: String,
    ): BaseResponse<LoginBean>


}