package com.anpei.mykotlinstudy.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.*


class AppUtils private constructor(){
    init {
        throw Error("DO NOT NEED instantiate!")
    }

    //申请静态方法
    companion object {
        /**
         * 获取版本号
         */
        fun getVersionName(ctx: Context): String {
            var verName = ""
            val pm = ctx.packageManager//context为当前Activity上下文
            val pi: PackageInfo
            try {
                pi = pm.getPackageInfo(ctx.packageName, 0)
                verName = pi.versionName
            } catch (e: Exception) {
                e.printStackTrace();
            }

            return verName

        }

        /**
         * 获取版本号 versionCode
         */
        fun getVersionCode(ctx: Context): Int {

            val pm = ctx.packageManager
            val pi: PackageInfo
            pi = pm.getPackageInfo(ctx.packageName, 0)
            return pi.versionCode
        }

        /**
         * 拨打电话（跳转到拨号界面，用户手动点击拨打）
         *
         * @param phoneNum 电话号码
         */
        fun callPhone(context: Context, phoneNum: String) {
            val intent = Intent(Intent.ACTION_DIAL)
            val data = Uri.parse("tel:$phoneNum")
            intent.data = data
            context.startActivity(intent)
        }


         fun filesToMultipartBodyParts(files: List<File>): List<MultipartBody.Part> {
            val parts = ArrayList<MultipartBody.Part>()
            for (file in files) {
                val requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
                val part = MultipartBody.Part.createFormData("image", file.name, requestBody)
                parts.add(part)
            }
            return parts
        }
        /**
         * 通过uri获取图片并进行压缩
         *
         * @param uri
         */
        @Throws(FileNotFoundException::class, IOException::class)
        fun getBitmapFormUri(ac: Activity, uri: Uri): Bitmap? {
            var input = ac.contentResolver.openInputStream(uri)
            val onlyBoundsOptions = BitmapFactory.Options()
            onlyBoundsOptions.inJustDecodeBounds = true
            onlyBoundsOptions.inDither = true//optional
            onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888//optional
            BitmapFactory.decodeStream(input, null, onlyBoundsOptions)
            input!!.close()
            val originalWidth = onlyBoundsOptions.outWidth
            val originalHeight = onlyBoundsOptions.outHeight
            if (originalWidth == -1 || originalHeight == -1)
                return null
            //图片分辨率以480x800为标准
            val hh = 800f//这里设置高度为800f
            val ww = 480f//这里设置宽度为480f
            //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
            var be = 1//be=1表示不缩放
            if (originalWidth > originalHeight && originalWidth > ww) {//如果宽度大的话根据宽度固定大小缩放
                be = (originalWidth / ww).toInt()
            } else if (originalWidth < originalHeight && originalHeight > hh) {//如果高度高的话根据宽度固定大小缩放
                be = (originalHeight / hh).toInt()
            }
            if (be <= 0)
                be = 1
            //比例压缩
            val bitmapOptions = BitmapFactory.Options()
            bitmapOptions.inSampleSize = be//设置缩放比例
            bitmapOptions.inDither = true//optional
            bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888//optional
            input = ac.contentResolver.openInputStream(uri)
            val bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions)
            input!!.close()

            return compressImage(bitmap!!)//再进行质量压缩
        }

        /**
         * 质量压缩方法
         *
         * @param image
         * @return
         */
        fun compressImage(image: Bitmap): Bitmap? {

            val baos = ByteArrayOutputStream()
            image.compress(Bitmap.CompressFormat.JPEG, 100, baos)//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
            var options = 100
            while (baos.toByteArray().size / 1024 > 100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
                baos.reset()//重置baos即清空baos
                //第一个参数 ：图片格式 ，第二个参数： 图片质量，100为最高，0为最差  ，第三个参数：保存压缩后的数据的流
                image.compress(Bitmap.CompressFormat.JPEG, options, baos)//这里压缩options%，把压缩后的数据存放到baos中
                options -= 10//每次都减少10
                Log.e("图片压缩：", (baos.toByteArray().size / 1024).toString() + "KB")
            }
            val isBm = ByteArrayInputStream(baos.toByteArray())//把压缩后的数据baos存放到ByteArrayInputStream中
            return BitmapFactory.decodeStream(isBm, null, null)
        }
    }

}