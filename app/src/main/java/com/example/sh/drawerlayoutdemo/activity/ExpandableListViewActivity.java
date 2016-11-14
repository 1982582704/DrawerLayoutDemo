package com.example.sh.drawerlayoutdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.sh.drawerlayoutdemo.R;
import com.example.sh.drawerlayoutdemo.adapter.ExpandableListViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作用:多级列表
 * date: 2016/11/3
 */
public class ExpandableListViewActivity extends Activity {

    @BindView(R.id.expandableListView)
    ExpandableListView mExpandableListView;

    ExpandableListViewAdapter mExpandableListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view);
        ButterKnife.bind(this);
        mExpandableListViewAdapter = new ExpandableListViewAdapter(this);
        mExpandableListView.setAdapter(mExpandableListViewAdapter);
        mExpandableListView.expandGroup(0);//默认展开第几个
//        openAll() ;

        /**
         * 折叠和展开事件
         */
        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                // TODO Auto-generated method stub
                Log.e("TAG", "groupPosition--->==" + groupPosition);
                return false; //默认为false，设为true时，点击事件不会展开Group
            }
        });
        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Log.e("TAG", "groupPosition ==" + groupPosition + "||childPosition == " + childPosition);
                return false;
            }
        });

    }

    /**
     * 不需要点击右侧箭头符号即可看到所有列表已经展开
     */
    private void openAll() {
        int groupCount = mExpandableListView.getCount();
        for (int i = 0; i < groupCount; i++) {
            mExpandableListView.expandGroup(i);//展开关闭
        }
        ;
    }
}
