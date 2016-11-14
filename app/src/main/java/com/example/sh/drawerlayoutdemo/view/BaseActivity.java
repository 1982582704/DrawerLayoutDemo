package com.example.sh.drawerlayoutdemo.view;

import android.app.Activity;
import android.view.View;

/**
 * @author Sh
 * @Time 2016/10/21 17:32
 * 作用：基类
 */
public abstract class BaseActivity {

    //上下文
    public final Activity mActivity;

    //根视图 代表孩子的视图
    public View rootView;

    //标识数据是否被初始化，initData是否被调用
    public boolean isInit;

    public BaseActivity(Activity mActivity) {
        this.mActivity = mActivity;
        rootView = initView();
    }


    /**
     * 初始化视图
     * 由孩子强制实现该方法，实现特定的效果
     *
     * @return
     */
    public abstract View initView();

    /**
     * 返回键
     */
    public abstract void back();


    /**
     * 当孩子需要请求网络数据，或者数据初始化的时候，重新该方法
     */
    public void initData() {

    }
}
