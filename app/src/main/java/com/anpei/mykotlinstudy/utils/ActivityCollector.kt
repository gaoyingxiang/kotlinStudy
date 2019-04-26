package com.anpei.mykotlinstudy.utils

import android.app.Activity

class ActivityCollector {
    companion object {
        var activities = ArrayList<Activity>()
        fun addActivity(activity: Activity){
            activities.add(activity)
        }

        fun removeActivity(activity: Activity){
            activities.remove(activity)
            if (!activity.isFinishing) activity.finish()
        }
        /**
         * 结束指定类名的Activity
         */
        fun finishActivityclass(cls: Class<*>) {
            activities!!.let {
                for (activity in it) {
                    if (activity.javaClass == cls) {
                        removeActivity(activity)
                        break
                    }
                }
            }
        }

        fun finishAll(){
            for (activity in activities){
                if (!activity.isFinishing) activity.finish()
            }
            activities.clear()
        }
    }
}