package com.app.lib_comment.net

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
    var pages:Int
)