package com.app.lib_comment

object  Constants {

    // ---------》 网络请求相关
    /**
     * 请求成功码
     */
    const val RESPONSE_CODE_SUCCESS = 10200

    /**
     * token 失效码
     */
    const val RESPONSE_CODE_TOKEN_INVALID = 401


    // ---------》 分页请求相关
    /**
     * 分页请求，每页数据量（20条）
     */
    const val LIST_PAGE_SIZE = 20

    /**
     * 分页请求 ，首页页码（默认为 1）
     */
    const val LIST_PAGE_FIRST_INDEX = 1
}