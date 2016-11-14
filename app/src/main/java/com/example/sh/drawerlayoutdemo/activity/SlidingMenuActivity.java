package com.example.sh.drawerlayoutdemo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.sh.drawerlayoutdemo.R;
import com.example.sh.drawerlayoutdemo.utils.DensityUtil;
import com.example.sh.drawerlayoutdemo.view.MenuLeftFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 地址 http://blog.csdn.net/lmj623565791/article/details/36677279
 * SlidingMenu 滑动菜单
 */
public class SlidingMenuActivity extends SlidingFragmentActivity {

    public static final String LEFTMENU_TAG = "leftmenu_tag";
    public static final String MAIN_TAG = "main_tag";
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.rl_sliding_menu)
    RelativeLayout rlSlidingMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置没有标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //1.设置页面
        setContentView(R.layout.activity_sliding_menu);
        ButterKnife.bind(this);

        //2.设置左侧菜单的页面
        setBehindContentView(R.layout.layout_menu);

        //3.设置右侧菜单
        SlidingMenu slidingMenu = getSlidingMenu();
//        slidingMenu.setSecondaryMenu(R.layout.rightmenu);

        //4.设置模式：左侧+主页面；左侧+主页+右侧；主页+右侧
        slidingMenu.setMode(SlidingMenu.LEFT);

        //5.设置滑动区域：全屏滑动，边缘滑动，不可以滑动
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

        //6.设置主页面占200dp位置
        slidingMenu.setBehindOffset(DensityUtil.dip2px(this, 200));

        initFragment();

    }

    /**
     * 初始化Fragment并且加载到Activity中来
     */
    private void initFragment() {

        //1.FragmentManger
        FragmentManager fm = getSupportFragmentManager();
        //2.开启事务
        FragmentTransaction ft = fm.beginTransaction();
        //3.替换Fragment
        ft.replace(R.id.rl_leftmenu, new MenuLeftFragment(), LEFTMENU_TAG);
//        ft.replace(R.id.rl_sliding_menu, new ContentFragment(), MAIN_TAG);

        //4.事务提交
        ft.commit();

    }

    /**
     * 得到左侧菜单
     *
     * @return
     */
    public MenuLeftFragment getLeftMenuFragment() {
        return (MenuLeftFragment) getSupportFragmentManager().findFragmentByTag(LEFTMENU_TAG);
    }

    @OnClick(R.id.btn)
    public void onClick() {
        Toast.makeText(SlidingMenuActivity.this, "dsdsds", Toast.LENGTH_SHORT).show();
    }

    /**
     * 得到ContentFragment
     *
     * @return
     */
//    public ContentFragment getContentFragment() {
//        return (ContentFragment) getSupportFragmentManager().findFragmentByTag(MAIN_TAG);
//    }
}
