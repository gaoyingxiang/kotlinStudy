package com.anpei.mykotlinstudy.model

import android.content.Context
import android.widget.Toast
import com.anpei.mykotlinstudy.AppApplication
import com.anpei.mykotlinstudy.Base.BaseModel
import com.anpei.mykotlinstudy.http.HttpManager
import com.anpei.mykotlinstudy.http.entity.ServiceInfoResp
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ServiceModel(context: Context) : BaseModel(context) {
    var waitServiceInterface: WaitServiceInterface? = null
    var finishServiceInterface: FinishServiceInterface? = null
    fun setWaitInterface(waitServiceInterface: WaitServiceInterface) {
        this.waitServiceInterface = waitServiceInterface
    }

    fun setFinishInterface(finishServiceInterface: FinishServiceInterface) {
        this.finishServiceInterface = finishServiceInterface
    }

    fun serviceInfo(id: String) {
        httpLoadingDialog!!.show()
        HttpManager.service.serveInfo(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ success ->
                when (success.ret) {
                    200 -> waitServiceInterface!!.info(success)
                    else -> Toast.makeText(AppApplication.context, success.msg, Toast.LENGTH_SHORT).show()
                }
                httpLoadingDialog!!.dismiss()
            }, { error ->
                httpLoadingDialog!!.dismiss()

            })
    }

    /**
     * 结束服务
     */
    fun finishService(serviceId: String) {
        httpLoadingDialog!!.show()
        HttpManager.service.finishService(serviceId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ success ->
                if (success.ret == 200) {
                    finishServiceInterface!!.finishService()
                    Toast.makeText(AppApplication.context, success.msg, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(AppApplication.context, success.msg, Toast.LENGTH_SHORT).show()
                }
                httpLoadingDialog!!.dismiss()
            }, { error ->
                httpLoadingDialog!!.dismiss()

            })
    }

    interface WaitServiceInterface {
        fun info(dataInfo: ServiceInfoResp)
    }

    interface FinishServiceInterface {
        fun finishService()
    }
}