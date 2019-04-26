package com.anpei.mykotlinstudy.Base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.anpei.mykotlinstudy.utils.ActivityCollector

abstract class CommonActivity : AppCompatActivity() {
    var activity : Activity ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        activity = this;
        initView()
        initBoot()
        initData()
        start()
        ActivityCollector.addActivity(this)
    }

    /**
     * 加载布局
     */
    abstract fun layoutId(): Int

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 初始化View
     */
    abstract fun initView()

    /**
     * 初始化
     */
    abstract fun initBoot()

    /**
     * 开始请求
     */
    abstract fun start()

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }

    fun startActivity(clazz: Class<*>) {
        this.startActivity(clazz, null as Bundle?, 0)
    }

    fun startActivity(clazz: Class<*>, bundle: Bundle) {
        this.startActivity(clazz, bundle, 0)
    }

    fun startActivity(clazz: Class<*>, requestCode: Int) {
        this.startActivity(clazz, null as Bundle?, requestCode)
    }

    fun startActivity(clazz: Class<*>, bundle: Bundle?, requestCode: Int) {
        val intent = Intent(this, clazz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }

        if (requestCode == 0) {
            this.startActivity(intent)
        } else {
            this.startActivityForResult(intent, requestCode)
        }

    }

}