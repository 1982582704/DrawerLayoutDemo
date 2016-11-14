package com.example.sh.drawerlayoutdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.sh.drawerlayoutdemo.R;
import com.example.sh.drawerlayoutdemo.adapter.MyAdapter;
import com.example.sh.drawerlayoutdemo.adapter.SubAdapter;
import com.example.sh.drawerlayoutdemo.view.MyListView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 二级列表
 * bug： 二维数组[X][Y] Y的数据不能完全显示
 */
public class SecondListActivity extends Activity {

    @BindView(R.id.listView)
    MyListView listView;
    @BindView(R.id.subListView)
    MyListView subListView;

    private MyAdapter myAdapter;
    private SubAdapter subAdapter;

    String cities[][] = new String[][]{
            new String[]{"全部美食", "本帮江浙菜", "川菜", "粤菜", "湘菜", "东北菜", "台湾菜", "新疆/清真", "素菜", "火锅", "其他"},
            new String[]{"运动户外", "咖啡厅", "酒吧", "茶馆", "KTV", "电影院", "游乐游艺", "公园", "景点/郊游", "洗浴", "足浴按摩", "文化艺术",
                    "DIY手工坊", "桌球馆", "桌面游戏", "更多休闲娱乐"},
            new String[]{"全部购物", "综合商场", "服饰鞋包", "运动户外", "珠宝饰品", "化妆品", "数码家电", "亲子购物", "家居建材"
                    , "书店", "书店", "眼镜店", "特色集市", "更多购物场所", "食品茶酒", "超市/便利店", "药店"},
            new String[]{"更多购物场所", "咖啡厅", "酒吧", "茶馆", "KTV", "电影院", "游乐游艺", "公园", "景点/郊游", "洗浴", "足浴按摩", "文化艺术",
                    "DIY手工坊", "桌球馆", "桌面游戏", "更多休闲娱乐"},
            new String[]{"电影院", "咖啡厅", "酒吧", "茶馆", "KTV", "游乐游艺", "公园", "景点/郊游", "洗浴", "足浴按摩", "文化艺术",
                    "桌球馆", "桌面游戏", "更多休闲娱乐"},
            new String[]{"全部", "咖啡厅", "酒吧", "茶馆", "电影院", "游乐游艺", "公园", "景点/郊游", "洗浴", "足浴按摩", "文化艺术",
                    "DIY手工坊", "桌球馆", "桌面游戏", "更多休闲娱乐"},
            new String[]{"更多休闲娱乐", "咖啡厅", "酒吧", "茶馆", "KTV", "电影院", "游乐游艺", "公园", "景点/郊游", "洗浴", "足浴按摩", "文化艺术",
                    "DIY手工坊", "桌球馆", "桌面游戏", "更多休闲娱乐"},
            new String[]{"桌面游戏", "咖啡厅", "酒吧", "茶馆", "KTV", "电影院", "游乐游艺", "公园", "景点/郊游", "洗浴", "足浴按摩", "文化艺术",
                    "DIY手工坊", "桌球馆", "桌面游戏", "更多休闲娱乐"},
            new String[]{"咖啡厅", "酒吧", "茶馆", "KTV", "电影院", "游乐游艺", "公园", "景点/郊游", "洗浴", "足浴按摩", "文化艺术",
                    "DIY手工坊", "桌球馆", "桌面游戏"},
            new String[]{"全部休闲娱乐", "咖啡厅", "酒吧", "茶馆", "KTV", "电影院", "游乐游艺", "公园", "景点/郊游", "洗浴", "足浴按摩", "文化艺术",
                    "DIY手工坊", "桌球馆", "桌面游戏", "更多休闲娱乐"},
            new String[]{"全部休闲aaa", "咖啡厅", "酒吧", "茶馆", "KTV", "电影院", "游乐游艺", "公园", "景点/郊游", "洗浴", "足浴按摩", "文化艺术",
                    "DIY手工坊", "桌球馆", "桌面游戏"},
    };
    String foods[] = new String[]{"全部频道", "美食", "休闲娱乐", "购物", "酒店", "丽人", "运动健身", "结婚", "亲子", "爱车", "生活服务"};
//    int images[] = new int[]{R.drawable.ic_category_0, R.drawable.ic_category_10, R.drawable.ic_category_30, R.drawable.ic_category_20
//            , R.drawable.ic_category_60, R.drawable.ic_category_50, R.drawable.ic_category_45, R.drawable.ic_category_50, R.drawable.ic_category_70,
//            R.drawable.ic_category_65, R.drawable.ic_category_80};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_list);
        ButterKnife.bind(this);
//        myAdapter = new MyAdapter(getApplicationContext(), foods, images);
        myAdapter = new MyAdapter(getApplicationContext(), foods);
        listView.setAdapter(myAdapter);

        //默认选择
        selectDefult();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                // TODO Auto-generated method stub
                final int location = position;
                myAdapter.setSelectedPosition(position);//设置选中的位置
                myAdapter.notifyDataSetInvalidated();//每当发现数据集监控有改变的情况，就会调用此方法.会重绘控件（还原到初始状态）
                //myAdapter.notifyDataSetChanged ();//每当发现数据集有改变的情况，或者读取到数据的新状态时，就会调用此方法.重绘当前可见区域
                subAdapter = new SubAdapter(getApplicationContext(), cities, position);
                subListView.setAdapter(subAdapter);
                subListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1,
                                            int position, long arg3) {
                        // TODO Auto-generated method stub
                        Toast.makeText(getApplicationContext(), cities[location][position], Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    /**
     * 默认选择
     */
    private void selectDefult() {
        final int location = 0;
        myAdapter.setSelectedPosition(0);
        myAdapter.notifyDataSetInvalidated();
        subAdapter = new SubAdapter(getApplicationContext(), cities, 0);
        subListView.setAdapter(subAdapter);
        subListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), cities[location][position], Toast.LENGTH_SHORT).show();
            }
        });
    }
}
