package com.jwenfeng.library.pulltorefresh;

/**
 * 当前类注释:
 * 作者：yxgao on 2017/12/18
 * 邮箱：381690272@qq.com
 * © 版权所有，未经允许不得传播
 */

public interface BaseRefreshListener {


    /**
     * 刷新
     */
    void refresh();

    /**
     * 加载更多
     */
    void loadMore();
}
