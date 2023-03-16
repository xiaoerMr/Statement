package com.app.mcu.net

data class BaseResponse<T>(
    var code :Int =0,
    val msg: String,
    val data: T ,
)


data class BasePage<T>(
    val records:MutableList<T>,
    val total:Int,
    val size:Int,
    var current:Int,
    val orders:Any,
    val hitCount:Boolean,
    val searchCount:Boolean,
    var pages:Int
)