package com.anpei.mykotlinstudy

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.anpei.mykotlinstudy.Base.CommonActivity
import com.anpei.mykotlinstudy.content.Constant
import com.anpei.mykotlinstudy.utils.SpHelper
import com.anpei.mykotlinstudy.utils.SpUtils
import com.anpei.mykotlinstudy.view.LoginActivity
import com.anpei.mykotlinstudy.view.MainActivity

class WelcomeActivity : CommonActivity() {
    private var handler: Handler? = Handler()
    var bundle: Bundle = Bundle()
    override fun layoutId(): Int = R.layout.activity_welcome

    override fun initData() {
        handler?.let {
            it.postDelayed({
                if (SpHelper.getToken().equals("")) {
                    this.bundle.putBoolean(Constant.ISFIRST, true)
                    startActivity(LoginActivity::class.java,this.bundle)
                } else {
                    startActivity(MainActivity::class.java)
                }
                finish()
            }, 2000)
        }
    }

    override fun initView() {

    }

    override fun initBoot() {

    }

    override fun start() {

    }

}
