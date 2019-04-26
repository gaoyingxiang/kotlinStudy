package com.anpei.mykotlinstudy.view

import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.widget.Switch
import android.widget.Toast
import com.anpei.mykotlinstudy.AppApplication.Companion.context
import com.anpei.mykotlinstudy.Base.CommonActivity
import com.anpei.mykotlinstudy.R
import com.anpei.mykotlinstudy.content.Constant
import com.anpei.mykotlinstudy.http.entity.ServiceInfoResp
import com.anpei.mykotlinstudy.model.ServiceModel
import com.anpei.mykotlinstudy.utils.AppUtils
import com.anpei.mykotlinstudy.utils.rbar.RxBarTool
import com.uuzuche.lib_zxing.activity.CodeUtils

import kotlinx.android.synthetic.main.activity_wait_service.*
import kotlinx.android.synthetic.main.item_service_list.*
import kotlinx.android.synthetic.main.layout_activity_title.*
import java.util.jar.Manifest

class WaitServiceActivity : CommonActivity(){


    val serviceModel by lazy { ServiceModel(this) }
    var serviceId:String? = null
    var mobile:String? = null
    var guardianMobile:String? = null

    override fun layoutId(): Int = R.layout.activity_wait_service

    override fun initData() {
        serviceModel.setWaitInterface(object :ServiceModel.WaitServiceInterface{
            override fun info(dataInfo: ServiceInfoResp) {

                tv_wait_name.text = dataInfo.content.username
                tv_wait_sex.text = dataInfo.content.sex
                tv_wait_age.text = dataInfo.content.age
                tv_phone_number.text = dataInfo.content.mobile
                tv_guardian_mobile.text = dataInfo.content.guardianMobile
                tv_address.text = dataInfo.content.mobile
                tv_wait_type.text = dataInfo.content.servename
                tv_time.text = dataInfo.content.booktime

                mobile = dataInfo.content.mobile
                guardianMobile = dataInfo.content.guardianMobile
            }
        })
        serviceModel.serviceInfo(serviceId!!)
    }

    override fun initView() {

    }

    override fun initBoot() {
        RxBarTool.setStatusBarColor(activity, R.color.color_68c946)
        serviceId  = this.intent.extras[Constant.SERVICE_ID] as String
    }

    override fun start() {
        //返回
        ly_title_back.setOnClickListener {
            finish()
        }
        //扫描
        tv_scan.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.CAMERA),
                    Constant.REQ_PERM_CAMERA
                )
            }else{
                var intent = Intent(this,ScanActivity::class.java)
                startActivityForResult(intent,Constant.REQUEST_CODE)
            }
        }
        //个人手机号
        iv_phone.setOnClickListener {
            AppUtils.callPhone(this,mobile!!)
        }

        //监护人手机号
        iv_guardian_mobile.setOnClickListener {
            AppUtils.callPhone(this,guardianMobile!!)
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){
            Constant.REQ_PERM_CAMERA-> //处理扫描结果（在界面上显示）
                // 摄像头权限申请
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    val intent = Intent(activity, ScanActivity::class.java)
                    startActivityForResult(intent, Constant.REQUEST_CODE)
                } else {
                    // 被禁止授权
                    Toast.makeText(context,"请至权限中心打开本应用的相机访问权限",Toast.LENGTH_SHORT)
                }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            Constant.REQUEST_CODE->
                //处理扫描结果（在界面上显示）
                if (null != data) {
                    val bundle = data.extras ?: return
                    if (bundle[CodeUtils.RESULT_TYPE] as Int == CodeUtils.RESULT_SUCCESS) {
                        val result = bundle[CodeUtils.RESULT_STRING] as String
                        Log.e("扫码返回------>", result)
                        val bundle1 = Bundle()
                        bundle1.putString(Constant.SERVIEC_ID, serviceId)
                        bundle1.putString(Constant.U_ID, result)
                        startActivity(ScanResultActivity::class.java, bundle1)
                    } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                        Toast.makeText(context,"解析二维码失败",Toast.LENGTH_SHORT)
                    }
                }
        }
    }

}
