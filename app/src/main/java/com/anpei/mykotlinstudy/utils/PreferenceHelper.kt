package com.anpei.mykotlinstudy.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.anpei.mykotlinstudy.AppApplication.Companion.context
import com.bumptech.glide.Glide.init

class PreferenceHelper(context:Context) {
    private var mPreference:SharedPreferences?=null
    private var mPreferenceEditor:SharedPreferences.Editor?=null
    companion object {
        var PREFERENCE_NAME:String = "AndroidSharePreferenceFile"
    }
     init {
        this.mPreference = context.getSharedPreferences(PREFERENCE_NAME,0)
        this.mPreferenceEditor = this.mPreference?.edit()
    }

    fun getInt(key: String, defaultValue: Int): Int? {
        return this.mPreference?.getInt(key, defaultValue)
    }

    fun putInt(key: String, value: Int): Boolean? {
        this.mPreferenceEditor?.putInt(key, value)
        return this.mPreferenceEditor?.commit()
    }

    fun getString(str: String, defaultValue: String): String? {
        return this.mPreference?.getString(str, defaultValue)
    }

    fun putString(key: String, value: String): Boolean? {
        this.mPreferenceEditor?.putString(key, value)
        return this.mPreferenceEditor?.commit()
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean? {
        return this.mPreference?.getBoolean(key, defaultValue)
    }

    fun putBoolean(key: String, value: Boolean): Boolean? {
        this.mPreferenceEditor?.putBoolean(key, value)
        return this.mPreferenceEditor?.commit()
    }

    fun getFloat(key: String, defaultValue: Float): Float? {
        return this.mPreference?.getFloat(key, defaultValue)
    }

    fun putFloat(key: String, value: Float): Boolean? {
        this.mPreferenceEditor?.putFloat(key, value)
        return this.mPreferenceEditor?.commit()
    }

    fun getLong(key: String, defaultValue: Long): Long? {
        return this.mPreference?.getLong(key, defaultValue)
    }

    fun putLong(key: String, value: Long): Boolean? {
        this.mPreferenceEditor?.putLong(key, value)
        return this.mPreferenceEditor?.commit()
    }
}