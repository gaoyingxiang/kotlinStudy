package com.anpei.mykotlinstudy.view

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import com.anpei.mykotlinstudy.Base.CommonActivity
import com.anpei.mykotlinstudy.R
import com.anpei.mykotlinstudy.utils.rbar.RxBarTool
import com.uuzuche.lib_zxing.activity.CaptureFragment
import com.uuzuche.lib_zxing.activity.CodeUtils
import kotlinx.android.synthetic.main.activity_scan.*

class ScanActivity : CommonActivity() {
    var captureFragment:CaptureFragment?=null
    override fun layoutId(): Int  = R.layout.activity_scan

    override fun initData() {
        RxBarTool.transparencyBar(activity)
        tv_title.text = "扫描二维码"
    }

    override fun initView() {

    }

    override fun initBoot() {
        captureFragment = CaptureFragment()
        CodeUtils.setFragmentArgs(captureFragment,R.layout.my_camera)
        captureFragment!!.analyzeCallback = myAnalyzeCallback
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_my_container,captureFragment).commit()
    }


    override fun start() {
        ly_title_back.setOnClickListener {
            finish()
        }

    }

    /**
     * 二维码解析回调函数
     */
    private var myAnalyzeCallback: CodeUtils.AnalyzeCallback = object : CodeUtils.AnalyzeCallback {
        override fun onAnalyzeSuccess(mBitmap: Bitmap, result: String) {
            val resultIntent = Intent()
            val bundle = Bundle()
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS)
            bundle.putString(CodeUtils.RESULT_STRING, result)
            resultIntent.putExtras(bundle)
            activity!!.setResult(RESULT_OK, resultIntent)
            activity!!.finish()
        }

        override fun onAnalyzeFailed() {
            val resultIntent = Intent()
            val bundle = Bundle()
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED)
            bundle.putString(CodeUtils.RESULT_STRING, "")
            resultIntent.putExtras(bundle)
            activity!!.setResult(RESULT_OK, resultIntent)
            activity!!.finish()
        }
    }
}
