package com.example.administrator.weichart.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.example.administrator.weichart.R;
import com.example.administrator.weichart.activity.fragment.FaXianFragment;
import com.example.administrator.weichart.activity.fragment.TongXunLuFragment;
import com.example.administrator.weichart.activity.fragment.WeiXInFragment;
import com.example.administrator.weichart.activity.fragment.WoFragment;
import com.example.administrator.weichart.activity.utils.LogUtils;
import com.example.administrator.weichart.activity.view.MyView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/27 0027.
 */
public class HomeActivity extends FragmentActivity implements View.OnClickListener {
    private ArrayList<Fragment> mFragmentList;
    private ArrayList<MyView> mMyViewList;
    private MyView weiXinMyView, tongXunLuMyView, faXianMyView, woMyView;
    private ViewPager mViewPager;
    private Button mMoreButton;
    private RelativeLayout rl;
    private PopupWindow mPopupWindow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        initViews();
        initData();
        initEvent();
        mViewPager.setAdapter(new myViewPagerAdapter(getSupportFragmentManager()));
        mViewPager.addOnPageChangeListener(new MyPageChangeListener());

    }

    private void initEvent() {
        weiXinMyView.setAlpha(1f);
    }

    private void initData() {
        mFragmentList = new ArrayList<Fragment>();
        mFragmentList.add(new WeiXInFragment());
        mFragmentList.add(new TongXunLuFragment());
        mFragmentList.add(new FaXianFragment());
        mFragmentList.add(new WoFragment());


        mMyViewList = new ArrayList<MyView>();
        mMyViewList.add(weiXinMyView);
        mMyViewList.add(tongXunLuMyView);
        mMyViewList.add(faXianMyView);
        mMyViewList.add(woMyView);


    }

    private void initViews() {
        weiXinMyView = (MyView) findViewById(R.id.weixin);
        tongXunLuMyView = (MyView) findViewById(R.id.tongxunlu);
        faXianMyView = (MyView) findViewById(R.id.faxian);
        woMyView = (MyView) findViewById(R.id.wo);

        weiXinMyView.setOnClickListener(this);
        tongXunLuMyView.setOnClickListener(this);
        faXianMyView.setOnClickListener(this);
        woMyView.setOnClickListener(this);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);


        mMoreButton = (Button) findViewById(R.id.button_more);
        mMoreButton.setOnClickListener(this);
        rl = (RelativeLayout) findViewById(R.id.rl_title);

        View view = LayoutInflater.from(this).inflate(R.layout.more_list, null);
        mPopupWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

    }

    @Override
    public void onClick(View v) {
       switch (v.getId())
       {
           case R.id.weixin:
               mViewPager.setCurrentItem(0);
               break;
           case R.id.tongxunlu:
               mViewPager.setCurrentItem(1);
               break;
           case R.id.faxian:
               mViewPager.setCurrentItem(2);
               break;
           case R.id.wo:
               mViewPager.setCurrentItem(3);
               break;
       }

    }


    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (positionOffset > 0) {
                MyView leftView = mMyViewList.get(position);
                leftView.setAlpha(1 - positionOffset);
                LogUtils.E("positionOffset==" + (1 - positionOffset));


                MyView rightView = mMyViewList.get(position + 1);
                rightView.setAlpha(positionOffset);
                LogUtils.E("position==" + position);
            }


        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private class myViewPagerAdapter extends FragmentPagerAdapter {


        public myViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
    }
}
