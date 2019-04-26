package com.anpei.mykotlinstudy.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.anpei.mykotlinstudy.R
import com.anpei.mykotlinstudy.content.Constant

class TopInflterAdapter(context: Context, list: List<String>) : RecyclerView.Adapter<TopInflterAdapter.ViewHolder>() {
    var dataList = ArrayList<String>()
    var mContext: Context = context
    var clickItemPosition : Int = 0
    var itemClickInterface: ItemClickInterface? = null
    fun set(itemClickInterface: ItemClickInterface) {
        this.itemClickInterface = itemClickInterface
    }

    init {
        dataList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var view: View = LayoutInflater.from(mContext).inflate(R.layout.item_top_inflter, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList!!.count() //dataList 非空下执行    ?标识当前对象是否为空     ！！标识当前对象不为空的情况下执行
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.tvItemTitle.text = dataList[position]
        if (clickItemPosition == position) {
            holder!!.tvItemTitle.setTextColor(Color.parseColor("#68C946"))
            holder!!.vLine.visibility = View.VISIBLE
        } else {
            holder!!.tvItemTitle.setTextColor(Color.parseColor("#1A1A1A"))
            holder!!.vLine.visibility = View.GONE
        }
        //返回健
        holder!!.lyItem.setOnClickListener {
            clickItemPosition = position
            holder!!.tvItemTitle.setTextColor(Color.parseColor("#68C946"))
            holder!!. vLine.visibility = View.VISIBLE
            itemClickInterface!!.click(position)
            notifyDataSetChanged()
        }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvItemTitle = view.findViewById<TextView>(R.id.tv_item_title)
        var lyItem = view.findViewById<LinearLayout>(R.id.ly_item)
        var vLine = view.findViewById<View>(R.id.v_line)
    }

    interface ItemClickInterface {
        fun click(position: Int)
    }
}