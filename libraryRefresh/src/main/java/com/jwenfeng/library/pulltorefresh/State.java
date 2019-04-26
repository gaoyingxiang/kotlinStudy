package com.jwenfeng.library.pulltorefresh;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 当前类注释:
 * 作者：yxgao on 2017/12/18
 * 邮箱：381690272@qq.com
 * © 版权所有，未经允许不得传播
 */

public class State {


    @IntDef({REFRESH, LOADMORE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface REFRESH_STATE {

    }

    public static final int REFRESH = 10;
    public static final int LOADMORE = 11;
}
