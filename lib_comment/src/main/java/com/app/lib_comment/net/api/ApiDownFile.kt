package com.app.lib_comment.net.api

import com.app.lib_comment.net.BaseResponse
import com.app.lib_comment.net.interceptor.ResPgyerCheckVersion
import okhttp3.ResponseBody
import retrofit2.http.*

interface ApiDownFile {

    @FormUrlEncoded
    @POST(Api.pgyerCheckVersion)
    suspend fun checkVersion(
        @Field("_api_key") _api_key: String,
        @Field("appKey") appKey: String,
//        @Header("buildVersion") buildVersion: String,
    ): BaseResponse<ResPgyerCheckVersion>



    @Streaming
    @GET
    suspend fun downApk(@Url url: String): ResponseBody

}