package com.example.sh.drawerlayoutdemo.pager;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.sh.drawerlayoutdemo.R;
import com.example.sh.drawerlayoutdemo.activity.DrawerLayoutActivity;
import com.example.sh.drawerlayoutdemo.activity.ExpandableListViewActivity;
import com.example.sh.drawerlayoutdemo.activity.SecondListActivity;
import com.example.sh.drawerlayoutdemo.activity.SlidingMenuActivity;
import com.example.sh.drawerlayoutdemo.view.BaseActivity;

/**
 * @author Sh
 * @Time 2016/10/22
 * 作用：首页
 */
public class HomePager extends BaseActivity implements View.OnClickListener {

    private Button btn;
    private Button bt;
    private Button bt_list;
    private Button btn_list;
    private Button btn_animation;

    public HomePager(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.activity_home_pager, null);
        btn = (Button) view.findViewById(R.id.btn);
        bt = (Button) view.findViewById(R.id.bt);
        bt_list = (Button) view.findViewById(R.id.bt_list);
        btn_list = (Button) view.findViewById(R.id.btn_list);
        btn_animation = (Button) view.findViewById(R.id.btn_animation);

        btn.setOnClickListener(this);
        bt.setOnClickListener(this);
        bt_list.setOnClickListener(this);
        btn_list.setOnClickListener(this);
        btn_animation.setOnClickListener(this);
        return view;
    }

    @Override
    public void back() {

    }

    private Intent intent;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn://抽屉式：侧滑菜单
                intent = new Intent(mActivity, DrawerLayoutActivity.class);
                mActivity.startActivity(intent);
                break;
            case R.id.bt://滑动菜单
                intent = new Intent(mActivity, SlidingMenuActivity.class);
                mActivity.startActivity(intent);
                break;
            case R.id.bt_list://二级列表
                intent = new Intent(mActivity, SecondListActivity.class);
                mActivity.startActivity(intent);
                break;
            case R.id.btn_list://多级列表
                intent = new Intent(mActivity, ExpandableListViewActivity.class);
                mActivity.startActivity(intent);
                break;
            case R.id.btn_animation://转场动画
//                intent = new Intent(mActivity, AnimationActivity.class);
//                mActivity.startActivity(intent);
                break;
        }
    }
}
