package com.anpei.mykotlinstudy.http.entity

data class ServiceInfoResp(
    val content: Content,
    val msg: String,
    val ret: Int
) {
    data class Content(
        val address: String,
        val age: String,
        val booktime: String,
        val endtime: String,
        val guardianMobile: String,
        val id: String,
        val mobile: String,
        val photo: List<Photo>,
        val servename: String,
        val sex: String,
        val status: String,
        val time: String,
        val username: String
    ) {
        data class Photo(
            val desc: String,
            val imgs: List<String>
        )
    }
}