package com.anpei.mykotlinstudy.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.anpei.mykotlinstudy.Base.CommonActivity
import com.anpei.mykotlinstudy.R
import com.anpei.mykotlinstudy.content.Constant
import com.anpei.mykotlinstudy.model.LoginMode
import com.anpei.mykotlinstudy.utils.SpHelper
import com.anpei.mykotlinstudy.utils.SpUtils
import com.anpei.mykotlinstudy.utils.rbar.RxBarTool
import kotlinx.android.synthetic.main.activity_login.*
import kotlin.reflect.KProperty

class LoginActivity : CommonActivity(){

    val loginMode by lazy { LoginMode(this)}
    var isFirst:Boolean = false
    override fun layoutId(): Int = R.layout.activity_login

    override fun initView() {
        activity?.let { RxBarTool.highApiEffects(it) }
    }

    override fun initBoot() {
        //回调
        loginMode.apply {
            setLoginInterface(object:LoginMode.LoginInterface{
                override fun loginData() {
                    SpHelper.saveMobile(et_phone.text.toString())
                    SpHelper.savePsd(et_psd.text.toString())
                    if (isFirst) {
                        startActivity(MainActivity::class.java)
                        finish()
                    } else {
                        finish()
                    }

                }
            })
        }
    }

    override fun initData() {
        if (this.intent.extras!=null){
            isFirst = activity?.intent?.extras!![Constant.ISFIRST] as Boolean
        }
    }

    override fun start() {
        this.tv_login.setOnClickListener {
            if (et_phone.text.isEmpty()){
                Toast.makeText(applicationContext, "请输入手机号", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (et_psd.text.isEmpty()){
                Toast.makeText(applicationContext, "请输入密码", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            loginMode?.let {
                it.login(et_phone.text.toString(),et_psd.text.toString())
            }
        }
    }

}
