package com.example.administrator.weichart.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.administrator.weichart.R;

/**
 * Created by Administrator on 2016/4/27 0027.
 */
public class SplashActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
       new  Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               startActivity(new Intent(SplashActivity.this,HomeActivity.class));
               finish();
           }
       },3000);
    }
}
