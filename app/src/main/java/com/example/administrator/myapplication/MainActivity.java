package com.example.administrator.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.administrator.myapplication.bean.qq;
import com.example.administrator.myapplication.bean.utils.OkHttpUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test(View view) {
        OkHttpUtils<qq>utils=new OkHttpUtils<>(getApplicationContext());
        String url="http://101.251.196.90:8888/JztkServer/examInfo";
        utils.url(url).targetClass(qq.class)
                .execute(new OkHttpUtils.OnCompleteListener<qq>(){
                    @Override
                    public void onSuccess(qq result) {
                        Log.e("main","result="+result);
                    }

                    @Override
                    public void onError(String error) {
                        Log.e("main","error="+error);

                    }
                });
        startActivity(new Intent(MainActivity.this,Eaxm.class));
    }

    public void exit(View view) {
        finish();
    }
}
