package com.example.sh.drawerlayoutdemo.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sh.drawerlayoutdemo.R;

/**
 * @author Sh
 * @Time 2016/10/22
 * 作用：右边菜单
 */
public class MenuRightFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_menu_right, container, false);
    }
}
