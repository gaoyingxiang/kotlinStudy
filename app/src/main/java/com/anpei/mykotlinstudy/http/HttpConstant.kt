package com.anpei.mykotlinstudy.http

object HttpConstant {
    const val BASE_URL = "http://youpin.ahaiba.com"
    //用户登录
    const val USER_LOGIN = "/index.php/login/login"
    //服务中心
    const val SERVE_LIST = "/index.php/base/serveList"
    //个人中心
    const val USER_CENTER = "/index.php/base/userCenter"
    //退出登录
    const val EXIT_LOGIN = "/index.php/base/exitLogin"
    //版本控制
    const val VERSION = "/index.php/base/version"
    //服务详情
    const val SERVE_INFO = "/index.php/base/serveInfo"
    //扫码结束后点击服务
    const val CLICK_SERVICE = "/index.php/base/clickServe"
    //结束服务
    const val FINISH_SERVE = "/index.php/base/finishServe"
    //提交图片
    const val UP_LOAD_IMGS = "/index.php/base/uploadImgs"
}