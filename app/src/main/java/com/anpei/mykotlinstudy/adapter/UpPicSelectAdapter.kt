package com.anpei.mykotlinstudy.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.anpei.mykotlinstudy.R
import com.anpei.mykotlinstudy.entity.PicInfo
import com.bumptech.glide.Glide

class UpPicSelectAdapter(context: Context, list: List<PicInfo>) :
    RecyclerView.Adapter<UpPicSelectAdapter.ViewHolder>() {
    var dataList = ArrayList<PicInfo>()
    var mContext: Context = context
    var picChooseInterface:PicChooseInterface?=null
    init {
        dataList.addAll(list)
    }
    fun setChooseInfo(picChooseInterface:PicChooseInterface){
        this.picChooseInterface = picChooseInterface
    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var view: View = LayoutInflater.from(mContext).inflate(R.layout.item_up_pic, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.count()

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        when {
            dataList[position].type == "1" -> holder!!.ivPic.setImageResource(R.mipmap.icon_fwd_add)
            dataList[position].type == "2" -> {
                //加载网络图片
                Glide.with(mContext)
                    .load(dataList[position].picPath)
                    .into(holder!!.ivPic)
            }
            dataList[position].type == "3" -> holder!!.ivPic.setImageBitmap(dataList[position].bitmap)
        }

        holder!!.ivPic.setOnClickListener {
            picChooseInterface!!.choosePic(position)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var ivPic = view.findViewById<ImageView>(R.id.iv_pic)!!
    }

    interface PicChooseInterface {
        fun choosePic(
            position: Int
        )
    }
}