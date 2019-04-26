package com.anpei.mykotlinstudy.model

import android.content.Context
import android.widget.Toast
import com.anpei.mykotlinstudy.AppApplication
import com.anpei.mykotlinstudy.Base.BaseModel
import com.anpei.mykotlinstudy.http.HttpManager
import com.anpei.mykotlinstudy.utils.AppUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.nio.file.Files

class UpLoadPicModel(mContext:Context):BaseModel(mContext) {
    fun commitPic(rid:String,desc:String,files: List<File>){
        httpLoadingDialog!!.show()
        HttpManager.service.commit(rid,desc,AppUtils.filesToMultipartBodyParts(files))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ success ->
                if (success.ret == 200) {
                    Toast.makeText(AppApplication.context, success.msg, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(AppApplication.context, success.msg, Toast.LENGTH_SHORT).show()
                }
                httpLoadingDialog!!.dismiss()
            }, { error ->
                httpLoadingDialog!!.dismiss()

            })
    }
}