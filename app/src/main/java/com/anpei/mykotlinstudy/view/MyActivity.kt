package com.anpei.mykotlinstudy.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anpei.mykotlinstudy.AppApplication.Companion.context
import com.anpei.mykotlinstudy.Base.CommonActivity
import com.anpei.mykotlinstudy.R
import com.anpei.mykotlinstudy.content.Constant
import com.anpei.mykotlinstudy.http.entity.UserInfoResp
import com.anpei.mykotlinstudy.model.MyModel
import com.anpei.mykotlinstudy.utils.ActivityCollector
import com.anpei.mykotlinstudy.utils.AppUtils
import com.anpei.mykotlinstudy.utils.SpHelper
import com.anpei.mykotlinstudy.utils.rbar.RxBarTool
import kotlinx.android.synthetic.main.activity_my.*
import kotlinx.android.synthetic.main.activity_scan.*

class MyActivity : CommonActivity() {
    val myModel by lazy { MyModel(this) }
    var bundle: Bundle = Bundle()
    override fun initData() {
        RxBarTool.setStatusBarColor(activity, R.color.color_68c946)
        tv_version.text = "V${AppUtils.getVersionName(context)}"
        myModel.setInterface(object :MyModel.UserInfoInterface{
            override fun exit() {
                SpHelper.clearSp()
                ActivityCollector.finishAll()
                bundle.putBoolean(Constant.ISFIRST, true)
                startActivity(LoginActivity::class.java,bundle)
            }
            override fun info(userInfoData: UserInfoResp) {
                tv_name.text = userInfoData.content.userinfo.username
                tv_phone.text = userInfoData.content.userinfo.mobile
            }
        })
    }

    override fun initView() {

    }

    override fun initBoot() {
    }

    override fun start() {
        ly_title_back.setOnClickListener {
            finish()
        }

        tv_login_off.setOnClickListener {
            myModel.exitLogin()
        }
    }

    override fun layoutId(): Int  = R.layout.activity_my

    override fun onResume() {
        super.onResume()
        myModel.userCenter()
    }
}
