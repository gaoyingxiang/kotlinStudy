package com.anpei.mykotlinstudy.adapter

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.anpei.mykotlinstudy.AppApplication.Companion.context
import com.anpei.mykotlinstudy.R
import com.anpei.mykotlinstudy.http.entity.ServiceListResp

class ServiceListAdapter(context: Context, serviceList: List<ServiceListResp.ContentBean.ServiceListBean>) :
    BaseAdapter() {
    var serviceListData = ArrayList<ServiceListResp.ContentBean.ServiceListBean>()
    var mContext: Context? = null

    init {
        serviceListData.addAll(serviceList)
        mContext = context
    }

    override fun getView(position: Int, convertView: View?, p2: ViewGroup?): View {
        var holder: ViewHolder? = null
        var view: View
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_service_list, p2, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }
        when {
            serviceListData[position].status == "1" -> //待服务
                holder.tvType.text = "待服务"
            serviceListData[position].status == "2" -> //服务中
                holder.tvType.text = "服务中"
            serviceListData[position].status == "3" -> //已完成
                holder.tvType.text = "已完成"
        }
        holder.tvName.text = serviceListData[position].username
        holder.tvSex.text = serviceListData[position].sex
        holder.tvAge.text = serviceListData[position].age
        holder.tvServiceType.text =
            Html.fromHtml("预约服务类型：" + "<font color='#666666'>" + serviceListData[position].servename + "</font>")
        holder.tvServiceTime.text =
            Html.fromHtml("预约服务时间：" + "<font color='#666666'>" + serviceListData[position].booktime + "</font>")
        return view
    }

    override fun getItem(p0: Int): Any  = serviceListData[p0]

    override fun getItemId(p0: Int): Long  = 0

    override fun getCount(): Int  =  serviceListData.size

    class ViewHolder(view: View) {
        var tvName =view.findViewById<TextView>(R.id.tv_name)
        var tvSex =view.findViewById<TextView>(R.id.tv_sex)
        var tvAge =view.findViewById<TextView>(R.id.tv_age)
        var tvType =view.findViewById<TextView>(R.id.tv_type)
        var tvServiceType =view.findViewById<TextView>(R.id.tv_service_type)
        var tvServiceTime =view.findViewById<TextView>(R.id.tv_service_time)
    }
}