package com.anpei.mykotlinstudy.view

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Handler
import android.support.v7.widget.GridLayoutManager
import com.anpei.mykotlinstudy.AppApplication.Companion.context
import com.anpei.mykotlinstudy.Base.CommonActivity
import com.anpei.mykotlinstudy.R
import com.anpei.mykotlinstudy.adapter.UpPicSelectAdapter
import com.anpei.mykotlinstudy.content.Constant
import com.anpei.mykotlinstudy.entity.PicInfo
import com.anpei.mykotlinstudy.model.UpLoadPicModel
import com.anpei.mykotlinstudy.utils.AppUtils
import com.anpei.mykotlinstudy.utils.rbar.RxBarTool
import com.wildma.pictureselector.PictureSelector
import kotlinx.android.synthetic.main.activity_up_load_pic.*
import kotlinx.android.synthetic.main.layout_activity_title.*
import java.io.File

class UpLoadPicActivity : CommonActivity() {
    var serviceId :String? = null
    var upPicSelectAdapter:UpPicSelectAdapter?=null
    val gridLayoutManager by lazy {
        GridLayoutManager(this,3)
    }
    var picInfos = ArrayList<PicInfo>()
    var files = ArrayList<File>()
    var picInfo:PicInfo? = null
    var bitmap:Bitmap? = null
    var picFile:File? =null
    var picSize  = 9
    var rid :String = ""

    val upLoadPicModel by lazy {
        UpLoadPicModel(this)
    }

    val heandler by lazy{
        Handler()
    }

    override fun layoutId(): Int  = R.layout.activity_up_load_pic

    override fun initData() {
        RxBarTool.setStatusBarColor(activity, R.color.color_68c946)
        serviceId =  this.intent.extras[Constant.SERVICE_ID] as String
        picInfo = PicInfo("","","1",null)
        picInfos.add(picInfo!!)
        upPicSelectAdapter = UpPicSelectAdapter(this,picInfos)
        upPicSelectAdapter!!.setChooseInfo(object :UpPicSelectAdapter.PicChooseInterface{
            override fun choosePic(position: Int) {
                if (picInfos[position].type=="1"){
                    PictureSelector.create(activity,PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(false,200,200,1,1)
                }
            }
        })
        rv_pic.layoutManager = gridLayoutManager
        rv_pic.adapter = upPicSelectAdapter
    }

    override fun initView() {
    }

    override fun initBoot() {
    }

    override fun start() {
        ly_title_back.setOnClickListener {
            finish()
        }

        //提交
        tv_commit.setOnClickListener {
            upLoadPicModel.commitPic(serviceId!!,et_desc.text.toString(),files)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==PictureSelector.SELECT_REQUEST_CODE){
            if (data!=null){
                var picturePath:String = data.getStringExtra(PictureSelector.PICTURE_PATH)
                var file:File = File(picturePath)
                bitmap = AppUtils.getBitmapFormUri(activity!!, Uri.fromFile(file))
                setPicData(bitmap!!, file)
            }
        }
    }

    /**
     * 图片集合
     *
     * @param bitmap
     * @param file
     */
     fun setPicData(bitmap: Bitmap, file: File) {
        picInfos.remove(picInfo)
        picInfos.add(PicInfo("", "", "3", bitmap))
        if (picInfos.size < picSize) {
            picInfos.add(picInfo!!)
        }
        files.add(file)
        upPicSelectAdapter = UpPicSelectAdapter(activity!!, picInfos)
        upPicSelectAdapter!!.setChooseInfo(object :UpPicSelectAdapter.PicChooseInterface{
            override fun choosePic(position: Int) {
                if (picInfos[position].type=="1"){
                    PictureSelector.create(activity,PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(false,200,200,1,1)
                }
            }
        })
        rv_pic.layoutManager = gridLayoutManager
        rv_pic.adapter = upPicSelectAdapter
    }
}
