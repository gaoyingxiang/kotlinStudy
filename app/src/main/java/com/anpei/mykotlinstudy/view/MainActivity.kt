package com.anpei.mykotlinstudy.view

import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.AdapterView
import com.anpei.mykotlinstudy.AppApplication.Companion.context
import com.anpei.mykotlinstudy.Base.CommonActivity
import com.anpei.mykotlinstudy.R
import com.anpei.mykotlinstudy.adapter.ServiceListAdapter
import com.anpei.mykotlinstudy.adapter.TopInflterAdapter
import com.anpei.mykotlinstudy.content.Constant
import com.anpei.mykotlinstudy.http.entity.ServiceListResp
import com.anpei.mykotlinstudy.model.MainModel
import com.anpei.mykotlinstudy.utils.rbar.RxBarTool
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener
import com.jwenfeng.library.pulltorefresh.ViewStatus
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_activity_title_no_back.*

class MainActivity : CommonActivity() {
    var topInflterAdapter: TopInflterAdapter? = null
    var topList = ArrayList<String>()
    val mainModel by lazy { MainModel(this) }
    var serviceListAdapter: ServiceListAdapter? = null
    var gridLayoutManager: GridLayoutManager? = null
    var status: Int = 1   //状态
    var page: Int = 1     //页数
    var serviceDataList = ArrayList<ServiceListResp.ContentBean.ServiceListBean>()
    var bundle:Bundle?= null

    override fun layoutId(): Int = R.layout.activity_main

    override fun initData() {
        RxBarTool.setSYsTemBarColor(activity, v_bar)

        topList.add("待服务")
        topList.add("服务中")
        topList.add("已完成")
        topInflterAdapter = TopInflterAdapter(this, topList)
        topInflterAdapter.apply {
            this!!.set(object : TopInflterAdapter.ItemClickInterface {
                override fun click(position: Int) {
                    //点击了不同的选择
                    status = position + 1
                    pull_to_layout.showView(ViewStatus.LOADING_STATUS)
                    mainModel.serviceList(status, page)
                }
            })
        }
        gridLayoutManager = GridLayoutManager(this, 3)
        this.rl_top_type.layoutManager = gridLayoutManager
        this.rl_top_type.adapter = topInflterAdapter

        mainModel.apply {
            setInterface(object : MainModel.ServiceListInterface {
                override fun start() {
//                    if (page == 1)
                }

                override fun dataList(serviceListResp: ServiceListResp) {
                    pull_to_layout.showView(ViewStatus.CONTENT_STATUS)
                    if (page == 1) {
                        serviceDataList.clear()
                    }
                    serviceDataList.addAll(serviceListResp.content.serveList)
                    serviceListAdapter = ServiceListAdapter(context, serviceDataList)
                    lv_service.adapter = serviceListAdapter
                }

                override fun error() {
                    if (page == 1) pull_to_layout.showView(ViewStatus.EMPTY_STATUS)
                }
            })
        }
    }

    override fun initView() {
        tv_title.text = "服务中心"
        iv_more.setImageResource(R.mipmap.icon_my)
    }

    override fun initBoot() {
    }

    override fun start() {
        pull_to_layout.showView(ViewStatus.LOADING_STATUS)
        mainModel.serviceList(status, page)

        //个人中心
        iv_more.setOnClickListener {
            startActivity(MyActivity::class.java)
        }

        //上拉加载下拉刷新
        this.pull_to_layout.setRefreshListener(object : BaseRefreshListener {
            override fun refresh() {
                page = 1
                mainModel.serviceList(status, page)
                Handler().postDelayed({
                    // 结束加载更多
                    pull_to_layout.finishRefresh()
                }, 200)
            }

            override fun loadMore() {
                page += 1
                mainModel.serviceList(status, page)
                Handler().postDelayed({
                    pull_to_layout.finishLoadMore()
                }, 200)
            }
        })

        //点击事件
        lv_service.onItemClickListener = AdapterView.OnItemClickListener { p0, p1, p2, p3 ->
            bundle = Bundle()
            bundle!!.putString(Constant.SERVICE_ID,serviceDataList[p2].id)
           when(status){
               1-> startActivity(WaitServiceActivity::class.java, bundle!!)//待服务
               2-> startActivity(DoServiceActivity::class.java, bundle!!)//服务中
               3-> startActivity(FinishServiceActivity::class.java, bundle!!)//已完成
           }
        }
    }

}