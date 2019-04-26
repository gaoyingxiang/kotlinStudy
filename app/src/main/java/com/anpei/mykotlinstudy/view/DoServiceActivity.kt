package com.anpei.mykotlinstudy.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anpei.mykotlinstudy.Base.CommonActivity
import com.anpei.mykotlinstudy.R
import com.anpei.mykotlinstudy.content.Constant
import com.anpei.mykotlinstudy.http.entity.ServiceInfoResp
import com.anpei.mykotlinstudy.model.ServiceModel
import com.anpei.mykotlinstudy.utils.AppUtils
import com.anpei.mykotlinstudy.utils.rbar.RxBarTool
import kotlinx.android.synthetic.main.activity_do_service.*
import kotlinx.android.synthetic.main.activity_wait_service.*
import kotlinx.android.synthetic.main.layout_activity_title.*

class DoServiceActivity : CommonActivity() {
    val serviceModel by lazy { ServiceModel(this) }
    var serviceId: String? = null
    var mobile: String? = null
    var guardianMobile: String? = null
    override fun layoutId(): Int = R.layout.activity_do_service

    override fun initData() {
        serviceModel.setWaitInterface(object : ServiceModel.WaitServiceInterface {
            override fun info(dataInfo: ServiceInfoResp) {

                tv_do_name.text = dataInfo.content.username
                tv_do_sex.text = dataInfo.content.sex
                tv_do_age.text = dataInfo.content.age
                tv_do_phone_number.text = dataInfo.content.mobile
                tv_do_guardian_mobile.text = dataInfo.content.guardianMobile
                tv_do_address.text = dataInfo.content.mobile
                tv_do_type.text = dataInfo.content.servename
                tv_do_time.text = dataInfo.content.booktime
                tv_do_start_time.text = dataInfo.content.time

                mobile = dataInfo.content.mobile
                guardianMobile = dataInfo.content.guardianMobile
            }
        })
        serviceModel.serviceInfo(serviceId!!)


        serviceModel.setFinishInterface(object:ServiceModel.FinishServiceInterface{
            override fun finishService() {
                finish()
            }
        })
    }

    override fun initView() {
    }

    override fun initBoot() {
        RxBarTool.setStatusBarColor(activity, R.color.color_68c946)
        serviceId = this.intent.extras[Constant.SERVICE_ID] as String
    }

    override fun start() {
        iv_do_phone.setOnClickListener {
            AppUtils.callPhone(this, mobile!!)
        }
        iv_do_guardian_mobile.setOnClickListener {
            AppUtils.callPhone(this, guardianMobile!!)
        }
        ly_title_back.setOnClickListener {
            finish()
        }
        //上传图片
        tv_up_load_pic.setOnClickListener {
            var bundle = Bundle()
            bundle.putString(Constant.SERVICE_ID, serviceId!!)
            startActivity(UpLoadPicActivity::class.java, bundle)
        }
        //结束
        tv_end.setOnClickListener {
            serviceModel.finishService(serviceId!!)
        }
    }
}
