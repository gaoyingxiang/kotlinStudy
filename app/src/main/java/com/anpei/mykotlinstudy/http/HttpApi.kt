package com.anpei.mykotlinstudy.http


import com.anpei.mykotlinstudy.http.entity.*
import com.anpei.mykotlinstudy.utils.SpHelper
import com.anpei.mykotlinstudy.utils.SpUtils
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*
import java.io.File
import java.nio.file.Files


interface HttpApi {
    /**
     * 登录
     */
    @FormUrlEncoded
    @POST(HttpConstant.USER_LOGIN)
    fun login(@Field("mobile") mobile:String, @Field("password") password:String): Observable<LoginResp>

    /**
     * 服务列表
     */
    @FormUrlEncoded
    @POST(HttpConstant.SERVE_LIST)
    fun serveList(@Field("status") status:Int, @Field("page") page:Int,@Field("token") token:String = SpHelper.getToken() as String): Observable<ServiceListResp>

    /**
     * 服务信息
     */
    @FormUrlEncoded
    @POST(HttpConstant.SERVE_INFO)
    fun serveInfo(@Field("id") id:String,@Field("token") token:String = SpHelper.getToken() as String): Observable<ServiceInfoResp>

    /**
     * 结束服务
     */
    @FormUrlEncoded
    @POST(HttpConstant.FINISH_SERVE)
    fun finishService(@Field("rid") rid:String,@Field("token") token:String = SpHelper.getToken() as String): Observable<BaseResp>

    /**
     * 用户信息
     */
    @FormUrlEncoded
    @POST(HttpConstant.USER_CENTER)
    fun userCenter(@Field("token") token: String = SpHelper.getToken() as String):Observable<UserInfoResp>

    /**
     * 退出登录
     */
    @FormUrlEncoded
    @POST(HttpConstant.EXIT_LOGIN)
    fun exitLogin(@Field("token") token: String = SpHelper.getToken() as String):Observable<BaseResp>

    /**
     * 图片上传
     */
    @Multipart
    @POST(HttpConstant.UP_LOAD_IMGS)
    fun commit (@Field("rid") rid:String,
                @Field("desc") desc:String,
                @Part("imgs[]")  parts: List<MultipartBody.Part>,
                @Field("token") token: String = SpHelper.getToken() as String):Observable<BaseResp>

}