package com.example.sh.drawerlayoutdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.example.sh.drawerlayoutdemo.R;
import com.example.sh.drawerlayoutdemo.pager.AttentionPager;
import com.example.sh.drawerlayoutdemo.pager.HomePager;
import com.example.sh.drawerlayoutdemo.pager.PersonalCenterPager;
import com.example.sh.drawerlayoutdemo.pager.VideoPager;
import com.example.sh.drawerlayoutdemo.view.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主页面
 * DrawerLayout：http://blog.csdn.net/lmj623565791/article/details/41531475
 * SlidingMenu: http://blog.csdn.net/lmj623565791/article/details/36677279
 * 对比：http://blog.csdn.net/sdksdk0/article/details/50152793
 */
public class MainActivity extends FragmentActivity {

    @BindView(R.id.rl_main)
    FrameLayout rlMain;
    @BindView(R.id.rg_button)
    RadioGroup rgButton;

    //页面集合
    private ArrayList<BaseActivity> baseActivities;

    //代表选择的页面
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initPager();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    /**
     * 初始化各个页面
     */
    private void initPager() {
        baseActivities = new ArrayList<>();
        baseActivities.add(new HomePager(this));//首页
        baseActivities.add(new VideoPager(this));//视频
        baseActivities.add(new AttentionPager(this));//关注
        baseActivities.add(new PersonalCenterPager(this));//个人中心

        //设置RadioGroup的监听
        rgButton.setOnCheckedChangeListener(new MainOnCheckedChangeListener());


        //设置默认选择首页
        rgButton.check(R.id.rb_home);
    }

    class MainOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
            switch (checkedId) {
                default:
                    position = 0;//首页
                    break;
                case R.id.rb_video://视频
                    position = 1;
                    break;
                case R.id.rb_attention://关注
                    position = 2;
                    break;
                case R.id.rb_personal_center://个人中心
                    position = 3;
                    break;
            }
            setFragment();
        }
    }

    /**
     * 根据位置，把Fragment的视图切换成对应位置的视图
     */
    private void setFragment() {
        //1.得到FragmentManager
        FragmentManager fm = getSupportFragmentManager();
        //2.开启事务
        FragmentTransaction ft = fm.beginTransaction();
        //3.替换视图
        ft.replace(R.id.rl_main, new Fragment() {
            @Nullable
            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

                BaseActivity baseActivity = getBaseActivity();
                if (baseActivity != null) {
                    return baseActivity.rootView;//不同孩子页面的视图
                }
                return null;
            }
        });
        //4.提交
        ft.commit();
    }

    private BaseActivity getBaseActivity() {
        BaseActivity basePager = baseActivities.get(position);//取就是父类 的应用指向孩子的实例
        System.out.println("basePager==" + basePager);
        if (basePager != null && !basePager.isInit) {
            basePager.isInit = true;
            basePager.initData();//初始化数据
        }
        return basePager;
    }
}
