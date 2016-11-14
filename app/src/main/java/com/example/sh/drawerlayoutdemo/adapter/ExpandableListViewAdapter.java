package com.example.sh.drawerlayoutdemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sh.drawerlayoutdemo.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 方法 http://blog.sina.com.cn/s/blog_b37dcd9701017s61.html
 */
public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    public String[] group = {"开发", "常用", "搞笑", "原创", "资讯", "体育", "游戏", "汽车", "娱乐"};
    public String[][] gridViewChild = {{"Android", "IOS", "JAVA", "PHP", "HTML", "C++", "JavaScript", "MySQL"},
            {"明星八卦", "搞笑综艺", "雷人囧事", "网络红人", "科技资讯", "美女花边", "游戏达人", "美女车模", "疯狂恶搞"},
            {"疯狂恶搞", "搞笑综艺", "原创搞笑", "爆笑宠物", "雷人囧事"}, {"原创热点", "原创影视", "音乐动画", "火星搞笑", "校园作品", "网络红人", "拍客"},
            {"社会资讯", "国内资讯", "国际资讯", "财富资讯", "科技资讯"}, {"篮球天地", "足球世界", "综合体育", "极限运动", "武术摔角", "美女花边"},
            {"网络游戏", "电子竞技", "单机电玩", "游戏达人", "工会战队"}, {"新车速递", "车型推荐", "改装酷玩", "汽车广告", "评测报告", "美女车模"}, {"明星八卦", "影视资讯"}};
    String[][] child = {{""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}};
    LayoutInflater mInflater;
    Context context;

    public ExpandableListViewAdapter(Context context) {
        // TODO Auto-generated constructor stub
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    /**
     * 　获取指定组中的指定子元素数据
     *
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return child[groupPosition][childPosition];
    }

    /**
     * 获取指定组中的指定子元素ID
     *
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return childPosition;
    }

    /**
     * 返回的View对象将作为特定组、特定位置的子列表项
     *
     * @param groupPosition
     * @param childPosition
     * @param isLastChild
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if (convertView == null) {
            mViewChild = new ViewChild();
            convertView = mInflater.inflate(R.layout.channel_expandablelistview_item, null);
            mViewChild.gridView = (GridView) convertView.findViewById(R.id.channel_item_child_gridView);
            convertView.setTag(mViewChild);
        } else {
            mViewChild = (ViewChild) convertView.getTag();
        }

        SimpleAdapter mSimpleAdapter = new SimpleAdapter(context, setGridViewData(gridViewChild[groupPosition]), R.layout.channel_gridview_item,
                new String[]{"channel_gridview_item"}, new int[]{R.id.channel_gridview_item});
        mViewChild.gridView.setAdapter(mSimpleAdapter);
        setGridViewListener(mViewChild.gridView);
        mViewChild.gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));

//        hashMap.put(groupPosition + "", mViewChild.gridView);
        return convertView;
    }

    /**
     * 设置gridview点击事件监听
     *
     * @param gridView
     */
    private void setGridViewListener(final GridView gridView) {
        gridView.setOnItemClickListener(new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                if (view instanceof TextView) {
                    //如果想要获取到哪一行，则自定义gridview的adapter，item设置2个textview一个隐藏设置id，显示哪一行
                    TextView tv = (TextView) view;
                    Toast.makeText(context, "position == " + position + " ---> " + tv.getText(), Toast.LENGTH_SHORT).show();
                    Log.e("TAG", " position ==" + position + " ||text=" + tv.getText());
                }
            }
        });
    }


    /**
     * 设置gridview数据
     *
     * @param data
     * @return
     */
    private ArrayList<HashMap<String, Object>> setGridViewData(String[] data) {
        ArrayList<HashMap<String, Object>> gridItem = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < data.length; i++) {
            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("channel_gridview_item", data[i]);
            gridItem.add(hashMap);
        }
        return gridItem;
    }

    /**
     * 获取指定组中的子元素个数
     *
     * @param groupPosition
     * @return
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        // TODO Auto-generated method stub
        return child[groupPosition].length;
    }

    /**
     * 获取指定组中的数据
     *
     * @param groupPosition 组位置
     * @return
     */
    @Override
    public Object getGroup(int groupPosition) {
        // TODO Auto-generated method stub
        return group[groupPosition];
    }

    /**
     * 返回包含的组列表项的数量
     *
     * @return
     */
    @Override
    public int getGroupCount() {
        // TODO Auto-generated method stub
        Log.i("TAG", "group.length ==" + group.length + " -XXXXXX");
        return group.length;
    }

    @Override
    public long getGroupId(int groupPosition) {
        // TODO Auto-generated method stub
        return groupPosition;
    }

    /**
     * 将返回的View对象将作为组列表项
     *
     * @param groupPosition
     * @param isExpanded
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if (convertView == null) {
            mViewChild = new ViewChild();
            convertView = mInflater.inflate(R.layout.channel_expandablelistview, null);
            mViewChild.textView = (TextView) convertView.findViewById(R.id.channel_group_name);
            mViewChild.imageView = (ImageView) convertView.findViewById(R.id.channel_imageview_orientation);
            convertView.setTag(mViewChild);
        } else {
            mViewChild = (ViewChild) convertView.getTag();
        }

        if (isExpanded) {
            mViewChild.imageView.setImageResource(R.drawable.channel_expandablelistview_top_icon);
        } else {
            mViewChild.imageView.setImageResource(R.drawable.channel_expandablelistview_bottom_icon);
        }
        mViewChild.textView.setText(getGroup(groupPosition).toString());
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return true;
    }

    /**
     * 返回true时，可以对子列表项设置监听
     *
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return true;
    }

    ViewChild mViewChild;

    static class ViewChild {
        ImageView imageView;
        TextView textView;
        GridView gridView;
    }
}
