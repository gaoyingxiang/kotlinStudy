package com.anpei.mykotlinstudy.utils

import com.anpei.mykotlinstudy.AppApplication
import com.anpei.mykotlinstudy.content.Constant

class SpHelper private constructor(){
    companion object {
        fun clearSp(){
            AppApplication.getMyPreferenceHelper()?.putString(Constant.MOBILE,"")
            AppApplication.preferenceHelper?.putString(Constant.PASSWORD,"")
            AppApplication.getMyPreferenceHelper()?.putString(Constant.TOKEN,"")
        }
        fun saveMobile( mobile:String){
            AppApplication.getMyPreferenceHelper()?.putString(Constant.MOBILE,mobile)
        }
        fun getMobile():String?{
            return AppApplication.getMyPreferenceHelper()?.getString(Constant.MOBILE,"")
        }
        fun savePsd(password:String){
            AppApplication.preferenceHelper?.putString(Constant.PASSWORD,password)
        }
        fun getPsd():String?{
           return AppApplication.preferenceHelper?.getString(Constant.PASSWORD,"")
        }
        fun saveToken(token:String){
            AppApplication.getMyPreferenceHelper()?.putString(Constant.TOKEN,token)
        }
        fun getToken():String?{
            return AppApplication.getMyPreferenceHelper()?.getString(Constant.TOKEN,"")
        }
    }
}