package com.jwenfeng.library.pulltorefresh.view;

import android.view.View;

/**
 * 当前类注释:
 * 作者：yxgao on 2017/12/18
 * 邮箱：381690272@qq.com
 * © 版权所有，未经允许不得传播
 */
public interface HeadView {

    /**
     * 开始下拉
     */
    void begin();

    /**
     * 回调的精度,单位为px
     *
     * @param progress 当前高度
     * @param all      总高度
     */
    void progress(float progress, float all);

    void finishing(float progress, float all);
    /**
     * 下拉完毕
     */
    void loading();

    /**
     * 看不见的状态
     */
    void normal();

    /**
     * 返回当前视图
     * */
    View getView();

}
