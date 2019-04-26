package com.anpei.mykotlinstudy.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.anpei.mykotlinstudy.AppApplication
import java.io.*
import kotlin.reflect.KProperty

/**
 * 委托类
 * desc:kotlin委托属性+SharedPreference实例
 */
class SpUtils<T>(val key:String,private val default:T) {
    //类似于static
    companion object {
        //const 只允许在top-level级别和object中声明
        //const val 可见性为public final static，可以直接访问。
        //val 可见性为private final static，并且val 会生成方法getNormalObject() ，通过方法调用访问。
        private const val file_name="kotlin_file"
        //注意var不能用lazy 延时属性，第一次加载的时候用，后面会记住这个属性
        private val prefs:SharedPreferences by lazy {
            AppApplication.context.getSharedPreferences(file_name,Context.MODE_PRIVATE)
        }
        /**
         * 删除全部数据
         */
        fun clearPreference(){
            prefs.edit().clear().apply()
        }
        /**
         * 根据key删除存储数据
         */
        fun clearPreferece(key:String){
            prefs.edit().remove(key).apply()
        }
    }


    operator fun getValue(thisRef:Any?,property:KProperty<*>):T{
        return getSharePreferences(key,default)
    }
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putSharedPreferences(key, value)
    }
    fun setValue(thisRef: Any?, value: T) {
        putSharedPreferences(key, value)
    }

    fun getValue(thisRef:Any?):T{
        return getSharePreferences(key,default)
    }

    @SuppressLint("CommitPrefEdits")
    private fun putSharedPreferences(name: String, value: T) = with(prefs.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> putString(name,serialize(value))
        }.apply()
    }
    @Suppress("UNCHECKED_CAST")
    private fun getSharePreferences(key:String,default: T):T = with(prefs){
        val res:Any = when(default){
            is Long -> getLong(key,default)
            is String ->getString(key,default)
            is Int -> getInt(key, default)
            is Boolean -> getBoolean(key, default)
            is Float -> getFloat(key, default)
            else ->  deSerialization(getString(key,serialize(default)))
        }
        return res as T
    }

    /**
     * 序列化对象

     * @param person
     * *
     * @return
     * *
     * @throws IOException
     */
    @Throws(IOException::class)
    private fun<A> serialize(obj: A): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        val objectOutputStream = ObjectOutputStream(
            byteArrayOutputStream)
        objectOutputStream.writeObject(obj)
        var serStr = byteArrayOutputStream.toString("ISO-8859-1")
        serStr = java.net.URLEncoder.encode(serStr, "UTF-8")
        objectOutputStream.close()
        byteArrayOutputStream.close()
        return serStr
    }

    /**
     * 反序列化对象

     * @param str
     * *
     * @return
     * *
     * @throws IOException
     * *
     * @throws ClassNotFoundException
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IOException::class, ClassNotFoundException::class)
    private fun<A> deSerialization(str: String): A {
        val redStr = java.net.URLDecoder.decode(str, "UTF-8")
        val byteArrayInputStream = ByteArrayInputStream(
            redStr.toByteArray(charset("ISO-8859-1")))
        val objectInputStream = ObjectInputStream(
            byteArrayInputStream)
        val obj = objectInputStream.readObject() as A
        objectInputStream.close()
        byteArrayInputStream.close()
        return obj
    }
}