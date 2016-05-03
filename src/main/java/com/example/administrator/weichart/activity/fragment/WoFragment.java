package com.example.administrator.weichart.activity.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.weichart.R;

/**
 * Created by Ethan.
 * tiefeng0606@live.cn
 */
public class WoFragment extends Fragment {
    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_wo,null);
        return mView;

    }
}
