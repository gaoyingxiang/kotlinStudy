package com.anpei.mykotlinstudy.adapter

import android.content.Context
import android.content.pm.ServiceInfo
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.anpei.mykotlinstudy.R
import com.anpei.mykotlinstudy.http.entity.ServiceInfoResp
import com.anpei.mykotlinstudy.widget.RecycleGridView

class RecyclePicListAdapter(ctx:Context,list: List<ServiceInfoResp.Content.Photo>): RecyclerView.Adapter<RecyclePicListAdapter.ViewHolder>() {
    var recycleGridView:RecycleGridAdapter?=null
    var mContext:Context? = null
    var dataList = ArrayList<ServiceInfoResp.Content.Photo>()

    init {
        mContext = ctx
        dataList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var view: View = LayoutInflater.from(mContext).inflate(R.layout.item_pic_grid_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int  = dataList.size

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.tvDesc.text = dataList[position].desc
        recycleGridView = RecycleGridAdapter(mContext!!,dataList[position].imgs)
        holder.rvPic.adapter = recycleGridView
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var rvPic = view.findViewById<RecycleGridView>(R.id.rv_pic)
        var tvDesc = view.findViewById<TextView>(R.id.tv_desc)
    }
}