package com.anpei.mykotlinstudy

import android.app.Application
import android.content.Context
import com.anpei.mykotlinstudy.content.Constant
import com.anpei.mykotlinstudy.utils.PreferenceHelper
import kotlin.properties.Delegates

class AppApplication : Application() {
    var preferenceHelper : PreferenceHelper? = null
    companion object {
        var context: Context by Delegates.notNull()
            private set
        var preferenceHelper:PreferenceHelper?=null

        fun getMyPreferenceHelper(): PreferenceHelper? {
            synchronized(AppApplication::class.java) {
                if (preferenceHelper == null) {
                    PreferenceHelper.PREFERENCE_NAME = Constant.SHARED_PREFERENCE_NAME
                    preferenceHelper  = PreferenceHelper(context)
                }
            }
            return preferenceHelper
        }
    }


    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }


}