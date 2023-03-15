package com.app.mcu.net

data class BaseResponse<T>(
    var code :Int =0,
    val msg: String,
    val data: T ,
)