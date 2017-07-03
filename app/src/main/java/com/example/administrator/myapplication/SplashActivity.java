package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2017/6/28 0028.
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        zCountDownTimer.start();
    }
    CountDownTimer zCountDownTimer =new CountDownTimer(2000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            Intent intent =new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);
            SplashActivity.this.finish();
        }
    };
}
