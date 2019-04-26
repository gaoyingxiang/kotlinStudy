package com.anpei.mykotlinstudy.http.entity

data class UserInfoResp(
    val content: Content,
    val msg: String,
    val ret: Int
) {
    data class Content(
        val userinfo: Userinfo
    ) {
        data class Userinfo(
            val mobile: String,
            val username: String
        )
    }
}