package com.anpei.mykotlinstudy.Base

import android.content.Context
import com.mirror.common.commondialog.httploadingdialog.HttpLoadingDialog

open class BaseModel(ctx: Context) {
    var mContext: Context? = null
    var httpLoadingDialog:HttpLoadingDialog?=null
    init {
        this.mContext = ctx
        httpLoadingDialog = HttpLoadingDialog(ctx)
        httpLoadingDialog!!.dialogMessage="加载中"//！！ 标识不为空的情况下执行
        //or
       // httpLoadingDialog?.let { it.dialogMessage== "加载中"}
    }
}