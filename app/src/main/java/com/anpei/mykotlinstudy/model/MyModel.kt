package com.anpei.mykotlinstudy.model

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.anpei.mykotlinstudy.AppApplication
import com.anpei.mykotlinstudy.Base.BaseModel
import com.anpei.mykotlinstudy.http.HttpManager
import com.anpei.mykotlinstudy.http.entity.UserInfoResp
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MyModel(mContext:Context):BaseModel(mContext) {
    var userInfoInterface:UserInfoInterface?=null
    fun setInterface(userInfoInterface:UserInfoInterface){
        this.userInfoInterface = userInfoInterface
    }

    fun userCenter(){
        HttpManager.service.userCenter() .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ success ->
                Log.e("个人中心----->",success.msg)
                if (success.ret==200){
                    userInfoInterface!!.info(success)
                }else{
                    Toast.makeText(AppApplication.context, success.msg, Toast.LENGTH_SHORT).show()
                }
            }, { error ->

            })
    }

    fun exitLogin(){
        httpLoadingDialog!!.show()
        HttpManager.service.exitLogin() .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ success ->
                Log.e("退出登录----->",success.msg)
                if (success.ret==200){
                    httpLoadingDialog!!.dismiss()
                    userInfoInterface!!.exit()
                    Toast.makeText(AppApplication.context, success.msg, Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(AppApplication.context, success.msg, Toast.LENGTH_SHORT).show()
                }
            }, { error ->
                httpLoadingDialog!!.dismiss()
            })
    }

    interface UserInfoInterface{
        fun info(userInfoData:UserInfoResp)
        fun exit()
    }
}