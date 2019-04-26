package com.anpei.mykotlinstudy.http.entity

import com.anpei.mykotlinstudy.Base.CommonResp

data class LoginResp (val msg:String, val ret:Int,val content:Content){
    data class Content(val token:String){
    }
}