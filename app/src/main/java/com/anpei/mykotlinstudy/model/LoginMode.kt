package com.anpei.mykotlinstudy.model

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.anpei.mykotlinstudy.AppApplication.Companion.context

import com.anpei.mykotlinstudy.Base.BaseModel
import com.anpei.mykotlinstudy.http.HttpManager
import com.anpei.mykotlinstudy.utils.SpHelper

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginMode(context: Context) : BaseModel(context) {
    private var loginInterface:LoginInterface?=null
    fun setLoginInterface(loginInterface: LoginInterface){
        this.loginInterface = loginInterface
    }
    fun login(mobile: String, password: String) {
        httpLoadingDialog?.let { it.show() }
        HttpManager.service.login(mobile, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ success ->
                httpLoadingDialog?.let { it.dismiss() }
                Log.e("这里是正确的", "-------" + success.msg)
                //读字段
                if (success.ret==200){
                    Log.e("这里是返回的token", success.content.token)
                    SpHelper.saveToken(success.content.token)
                    loginInterface?.loginData()
                }else{
                    Toast.makeText(context, success.msg, Toast.LENGTH_SHORT).show()
                }
            }, { throwable ->
                httpLoadingDialog?.let { it.dismiss() }
                Log.e("这里是错误的", "---------------")
            })
    }
    interface LoginInterface {
        fun loginData()
    }
}

