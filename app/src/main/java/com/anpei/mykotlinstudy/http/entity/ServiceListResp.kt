package com.anpei.mykotlinstudy.http.entity

data class ServiceListResp(val msg: String, val ret: Int, val content: ContentBean) {
    data class ContentBean(val serveList: List<ServiceListBean>) {
        data class ServiceListBean(
            val id: String,
            val username: String,
            val sex: String,
            val age: String,
            val servename: String,
            val booktime: String,
            val status: String
        ) {

        }
    }
}