package com.app.lib_comment.net.interceptor

data class ResPgyerCheckVersion(
    val buildHaveNewVersion:Boolean,
    val needForceUpdate:Boolean,
    val downloadURL:String,
    val buildVersion:String,


    //needForceUpdate	Boolean	是否强制更新
    //downloadURL	String	应用安装地址
    //buildHaveNewVersion	Boolean	是否有新版本
)
