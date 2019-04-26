package com.anpei.mykotlinstudy.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.anpei.mykotlinstudy.Base.CommonActivity
import com.anpei.mykotlinstudy.R
import com.anpei.mykotlinstudy.adapter.RecyclePicListAdapter
import com.anpei.mykotlinstudy.content.Constant
import com.anpei.mykotlinstudy.http.entity.ServiceInfoResp
import com.anpei.mykotlinstudy.model.ServiceModel
import com.anpei.mykotlinstudy.utils.rbar.RxBarTool
import kotlinx.android.synthetic.main.activity_do_service.*
import kotlinx.android.synthetic.main.activity_finish_service.*
import kotlinx.android.synthetic.main.layout_activity_title.*

class FinishServiceActivity : CommonActivity() {
    val serviceModel by lazy { ServiceModel(this) }
    var serviceId: String? = null
    var mobile: String? = null
    var guardianMobile: String? = null

    var recyclePlicListAdapter:RecyclePicListAdapter?=null
    var layoutManager:LinearLayoutManager? = null

    override fun layoutId(): Int = R.layout.activity_finish_service

    override fun initData() {
        serviceModel.setWaitInterface(object : ServiceModel.WaitServiceInterface {
            override fun info(dataInfo: ServiceInfoResp) {

                tv_finish_name.text = dataInfo.content.username
                tv_finish_sex.text = dataInfo.content.sex
                tv_finish_age.text = dataInfo.content.age
                tv_finish_phone_number.text = dataInfo.content.mobile
                tv_finish_guardian_mobile.text = dataInfo.content.guardianMobile
                tv_finish_address.text = dataInfo.content.mobile
                tv_finish_type.text = dataInfo.content.servename
                tv_finish_time.text = dataInfo.content.booktime
                tv_finish_start_time.text = dataInfo.content.time
                tv_finish_end_time.text = dataInfo.content.endtime

                mobile = dataInfo.content.mobile
                guardianMobile = dataInfo.content.guardianMobile
                recyclePlicListAdapter = RecyclePicListAdapter(activity!!,dataInfo.content.photo)
                lv_pic.setHasFixedSize(true)
                lv_pic.isNestedScrollingEnabled = false
                lv_pic.layoutManager = layoutManager
                lv_pic.adapter = recyclePlicListAdapter
            }
        })
        serviceModel.serviceInfo(serviceId!!)
    }

    override fun initView() {
    }

    override fun initBoot() {
        RxBarTool.setStatusBarColor(activity, R.color.color_68c946)
        serviceId = this.intent.extras[Constant.SERVICE_ID] as String
        layoutManager = LinearLayoutManager(this)
    }

    override fun start() {
        ly_title_back.setOnClickListener {
            finish()
        }
    }

}
