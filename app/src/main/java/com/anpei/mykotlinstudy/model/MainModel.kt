package com.anpei.mykotlinstudy.model

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.anpei.mykotlinstudy.AppApplication
import com.anpei.mykotlinstudy.Base.BaseModel
import com.anpei.mykotlinstudy.http.HttpManager
import com.anpei.mykotlinstudy.http.entity.ServiceListResp
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainModel(ctx: Context) : BaseModel(ctx) {
    var serviceListInterface:ServiceListInterface?=null
    fun setInterface(serviceListInterface:ServiceListInterface){
        this.serviceListInterface = serviceListInterface
    }
    fun serviceList(status: Int, page: Int) {
        //开始
        serviceListInterface!!.start()
        HttpManager.service.serveList(status, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ success ->
                Log.e("请求服务列表----->",success.msg)
                if (success.ret==200){
                    serviceListInterface!!.dataList(success)
                }else{
                    Toast.makeText(AppApplication.context, success.msg, Toast.LENGTH_SHORT).show()
                }
            }, { error ->
                serviceListInterface!!.error()
            })
    }

    interface ServiceListInterface{
        fun start()
        fun dataList(serviceListResp: ServiceListResp)
        fun error()
    }
}