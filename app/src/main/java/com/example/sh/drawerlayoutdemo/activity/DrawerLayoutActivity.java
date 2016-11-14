package com.example.sh.drawerlayoutdemo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.sh.drawerlayoutdemo.R;
import com.nineoldandroids.view.ViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 抽屉式：侧滑菜单
 */
public class DrawerLayoutActivity extends FragmentActivity {

    @BindView(R.id.rl_one)
    RelativeLayout rlOne;
    @BindView(R.id.rl_two)
    RelativeLayout rlTwo;
    @BindView(R.id.rl_three)
    RelativeLayout rlThree;
    @BindView(R.id.rl_four)
    RelativeLayout rlFour;
    @BindView(R.id.rl_five)
    RelativeLayout rlFive;

    @BindView(R.id.ll_scan)
    LinearLayout llScan;
    @BindView(R.id.ll_discussion_group)
    LinearLayout llDiscussionGroup;
    @BindView(R.id.ll_group_chat)
    LinearLayout llGroupChat;
    @BindView(R.id.ll_shake)
    LinearLayout llShake;

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_drawer_layout);
        ButterKnife.bind(this);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawerLayout);
        //右边菜单默认为关闭
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
                Gravity.RIGHT);

        initView();
        initEvents();
    }

    private void initView() {
        View view = View.inflate(this, R.layout.layout_menu, null);
        Toast.makeText(DrawerLayoutActivity.this, "左侧边缘滑动-DrawerLayout", Toast.LENGTH_SHORT).show();
        View view1 = View.inflate(this, R.layout.layout_menu_right, null);
    }

    /**
     * 打开右边菜单
     *
     * @param view
     */
    public void OpenRightMenu(View view) {
        mDrawerLayout.openDrawer(Gravity.RIGHT);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,
                Gravity.RIGHT);
    }


    private void initEvents() {
        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            /**
             * 抽屉状态的改变
             * @param newState
             */
            @Override
            public void onDrawerStateChanged(int newState) {
            }

            /**
             * 抽屉滑动
             * @param drawerView
             * @param slideOffset
             */
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                View mContent = mDrawerLayout.getChildAt(0);
                View mMenu = drawerView;
                float scale = 1 - slideOffset;
                float rightScale = 0.8f + scale * 0.2f;

                if (drawerView.getTag().equals("LEFT")) {//左边菜单

                    float leftScale = 1 - 0.3f * scale;

                    ViewHelper.setScaleX(mMenu, leftScale);
                    ViewHelper.setScaleY(mMenu, leftScale);
                    ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));
                    ViewHelper.setTranslationX(mContent,
                            mMenu.getMeasuredWidth() * (1 - scale));
                    ViewHelper.setPivotX(mContent, 0);
                    ViewHelper.setPivotY(mContent,
                            mContent.getMeasuredHeight() / 2);
                    mContent.invalidate();
                    ViewHelper.setScaleX(mContent, rightScale);
                    ViewHelper.setScaleY(mContent, rightScale);
                } else {//右边菜单
                    ViewHelper.setTranslationX(mContent,
                            -mMenu.getMeasuredWidth() * slideOffset);
                    ViewHelper.setPivotX(mContent, mContent.getMeasuredWidth());
                    ViewHelper.setPivotY(mContent,
                            mContent.getMeasuredHeight() / 2);
                    mContent.invalidate();
                    ViewHelper.setScaleX(mContent, rightScale);
                    ViewHelper.setScaleY(mContent, rightScale);
                }
            }

            /**
             * 打开
             * @param drawerView
             */
            @Override
            public void onDrawerOpened(View drawerView) {
            }

            /**
             * 关闭
             * @param drawerView
             */
            @Override
            public void onDrawerClosed(View drawerView) {
                mDrawerLayout.setDrawerLockMode(
                        DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
            }
        });
    }

    @OnClick({R.id.rl_one, R.id.rl_two, R.id.rl_three, R.id.rl_four, R.id.rl_five, R.id.ll_discussion_group, R.id.ll_group_chat, R.id.ll_scan, R.id.ll_shake})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_one://第一个Item
                Toast.makeText(DrawerLayoutActivity.this, "第一个Item", Toast.LENGTH_SHORT).show();

                break;
            case R.id.rl_two://第二个Item
                Toast.makeText(DrawerLayoutActivity.this, "第二个Item", Toast.LENGTH_SHORT).show();

                break;
            case R.id.rl_three://第三个Item
                Toast.makeText(DrawerLayoutActivity.this, "第三个Item", Toast.LENGTH_SHORT).show();

                break;
            case R.id.rl_four://第四个Item
                Toast.makeText(DrawerLayoutActivity.this, "第四个Item", Toast.LENGTH_SHORT).show();

                break;
            case R.id.rl_five://第五个Item
                Toast.makeText(DrawerLayoutActivity.this, "第五个Item", Toast.LENGTH_SHORT).show();

                break;
            case R.id.ll_scan://扫一扫
                Toast.makeText(DrawerLayoutActivity.this, "扫一扫", Toast.LENGTH_SHORT).show();

                break;
            case R.id.ll_discussion_group://讨论组
                Toast.makeText(DrawerLayoutActivity.this, "讨论组", Toast.LENGTH_SHORT).show();

                break;
            case R.id.ll_group_chat://群聊
                Toast.makeText(DrawerLayoutActivity.this, "群聊", Toast.LENGTH_SHORT).show();

                break;
            case R.id.ll_shake://摇一摇
                Toast.makeText(DrawerLayoutActivity.this, "摇一摇", Toast.LENGTH_SHORT).show();

                break;
        }
    }
}

