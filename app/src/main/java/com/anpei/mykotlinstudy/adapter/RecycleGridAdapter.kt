package com.anpei.mykotlinstudy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.anpei.mykotlinstudy.AppApplication.Companion.context
import com.anpei.mykotlinstudy.R
import com.bumptech.glide.Glide


class RecycleGridAdapter (ctx: Context, list:List<String>): BaseAdapter() {
    var dataList = ArrayList<String>()
    var mContext:Context? = null
    init {
        mContext = ctx
        dataList.addAll(list)
    }

    override fun getView(position: Int, convertView: View?, p2: ViewGroup?): View {
        var holder: ViewHolder? = null
        var view: View
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_pic, p2, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }
        Glide.with(context)
            .load(dataList[position])
            .into(holder.ivPic)
        return view
    }

    override fun getItem(p0: Int): Any  = dataList[p0]

    override fun getItemId(p0: Int): Long  = 0

    override fun getCount(): Int = dataList.size

    class ViewHolder(view: View) {
        var ivPic = view.findViewById<ImageView>(R.id.iv_pic)!!
    }


}