package com.anpei.mykotlinstudy.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anpei.mykotlinstudy.Base.CommonActivity
import com.anpei.mykotlinstudy.R
import com.anpei.mykotlinstudy.content.Constant

class ScanResultActivity : CommonActivity() {
    var serviceId = this.intent.extras[Constant.SERVICE_ID] as String
    var uId  = this.intent.extras[Constant.U_ID] as String
    override fun initData() {
    }

    override fun initBoot() {
    }

    override fun start() {
    }

    override fun initView() {
    }

    override fun layoutId(): Int  = R.layout.activity_scan_result
}
