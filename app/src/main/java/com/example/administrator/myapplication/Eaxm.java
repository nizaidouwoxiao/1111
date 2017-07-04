package com.example.administrator.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.myapplication.bean.Exam;
import com.example.administrator.myapplication.bean.ExamInfo;
import com.example.administrator.myapplication.bean.Result;
import com.example.administrator.myapplication.biz.ExamBiz;
import com.example.administrator.myapplication.biz.IExamBiz;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2017/6/29.
 */

public class Eaxm extends AppCompatActivity {
    TextView tvExamInfo,tvExamTitle,tvOp1,tvOp2,tvOp3,tvOp4,tvload,tvNo;
    CheckBox cb01,cb02,cb03,cb04;
    CheckBox[] cbs=new CheckBox[4];
    LinearLayout layoutLoading,layout03,layout04;
    ImageView mImageView;
    IExamBiz biz;
    ProgressBar dialog;
    boolean isLoadExamInfo=false;
    boolean isLoadQuestions=false;
    boolean isLoadExamInfoReceiver=false;
    boolean isLoadQuestionsReceiver=false;

    LoadExamBroadcast mLoadExamBroadcast;
    LoadQuestionBroadcast mLoadQuestionBroadcast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eaxm);
        mLoadExamBroadcast=new LoadExamBroadcast();
        mLoadQuestionBroadcast=new LoadQuestionBroadcast();
        setListener();
        initView();
        biz=new ExamBiz();
        loadData();
    }

    private void setListener() {
        registerReceiver(mLoadExamBroadcast,new IntentFilter(ExamApplication.LOAD_EXAM_INFO));
        registerReceiver(mLoadQuestionBroadcast,new IntentFilter(ExamApplication.LOAD_EXAM_QUESTION));
    }

    private void loadData() {
        layoutLoading.setEnabled(false);
        dialog.setVisibility(View.VISIBLE);
        tvload.setText("数据下载中...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                biz.beginExam();
            }
        }).start();
    }

    private void initView() {
        layoutLoading=(LinearLayout)findViewById(R.id.layout_loadding);
        layout03=(LinearLayout)findViewById(R.id.layout_03);
        layout04=(LinearLayout)findViewById(R.id.layout_04);
        dialog=(ProgressBar) findViewById(R.id.load_dialog);
        tvExamInfo=(TextView) findViewById(R.id.tv_examinfo);
        tvExamTitle=(TextView) findViewById(R.id.tv_exam_title);
        tvOp1=(TextView) findViewById(R.id.tv_Op1);
        tvOp2=(TextView) findViewById(R.id.tv_Op2);
        tvOp3=(TextView) findViewById(R.id.tv_Op3);
        tvOp4=(TextView) findViewById(R.id.tv_Op4);
        tvload=(TextView)findViewById(R.id.tv_load);
        tvNo=(TextView)findViewById(R.id.tv_exam_no);
        cb01=(CheckBox) findViewById(R.id.cb_01);
        cb02=(CheckBox) findViewById(R.id.cb_02);
        cb03=(CheckBox) findViewById(R.id.cb_03);
        cb04=(CheckBox) findViewById(R.id.cb_04);
        cbs[0]=cb01;
        cbs[1]=cb02;
        cbs[2]=cb03;
        cbs[3]=cb04;
        mImageView=(ImageView)findViewById(R.id.in_exam_image);
        layoutLoading.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
        cb01.setOnCheckedChangeListener(listener);
        cb02.setOnCheckedChangeListener(listener);
        cb03.setOnCheckedChangeListener(listener);
        cb04.setOnCheckedChangeListener(listener);
    }
    CompoundButton.OnCheckedChangeListener listener =new CompoundButton.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                int userAnswer = 0;
                switch (buttonView.getId()) {
                    case R.id.cb_01:
                        userAnswer = 1;
                        break;
                    case R.id.cb_02:
                        userAnswer = 2;
                        break;
                    case R.id.cb_03:
                        userAnswer = 3;
                        break;
                    case R.id.cb_04:
                        userAnswer = 4;
                        break;
                }
                Log.e("checkedChanged", "usera=" + userAnswer + "isChecked=" + isChecked);
                if (userAnswer > 0) {
                    for (CheckBox cb : cbs) {
                        cb.setChecked(false);
                    }
                    cbs[userAnswer - 1].setChecked(true);
                }
            }
        }
    };

    private void initData() {
        if (isLoadExamInfoReceiver&&isLoadQuestionsReceiver){
            if(isLoadExamInfo &&isLoadQuestions){
                layoutLoading.setVisibility(View.GONE);
                ExamInfo examInfo = ExamApplication.getInstance().getmExamInfo();
                if(examInfo!=null){
                    showData(examInfo);
                }
                showExam( biz.getExam());
            }else {
                layoutLoading.setEnabled(true);
                dialog.setVisibility(View.GONE);
                tvload.setText("下载失败,点击重新下载");
            }
        }
    }

    private void showExam(Exam exam) {
        Log.e("showExam","showExam.exam"+exam);
        if(exam!=null){
            tvNo.setText(biz.getExamIndex());
            tvExamTitle.setText(exam.getQuestion());
            tvOp1.setText(exam.getItem1());
            tvOp2.setText(exam.getItem2());
            tvOp3.setText(exam.getItem3());
            tvOp4.setText(exam.getItem4());

            tvOp3.setVisibility(exam.getItem3().equals("")?View.GONE:View.VISIBLE);
            cb03.setVisibility(exam.getItem3().equals("")?View.GONE:View.VISIBLE);
            tvOp4.setVisibility(exam.getItem4().equals("")?View.GONE:View.VISIBLE);
            cb04.setVisibility(exam.getItem4().equals("")?View.GONE:View.VISIBLE);

            if(exam.getUrl()!=null&& exam.getUrl().equals("")){
            Picasso.with(Eaxm.this)
                    .load(exam.getUrl())
                    .into(mImageView);
            }else {
                mImageView.setVisibility(View.GONE);
            }
        }

    }

    private void showData(ExamInfo examInfo) {
        tvExamInfo.setText(examInfo.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mLoadExamBroadcast!=null){
            unregisterReceiver(mLoadExamBroadcast);
        }
        if (mLoadQuestionBroadcast!=null){
            unregisterReceiver(mLoadQuestionBroadcast);
        }
    }



    public void nextExam(View view) {
        showExam(biz.nextQuestion());
    }

    public void proExam(View view) {
        showExam(biz.preQuestion());
    }

    class LoadExamBroadcast extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean isSuccess =intent.getBooleanExtra(ExamApplication.LOAD_DATA_SUCCESS,false);
            Log.e("LoadExamBroadcast","LoadExamBroadcast,isSuccess="+isSuccess);
            if (isSuccess){
                isLoadExamInfo=true;
            }
            isLoadExamInfoReceiver=true;
            initData();
        }
    }
    class LoadQuestionBroadcast extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean isSuccess =intent.getBooleanExtra(ExamApplication.LOAD_DATA_SUCCESS,false);
            Log.e("LoadQuestionBroadcast","LoadQuestionBroadcast,isSuccess="+isSuccess);
            if (isSuccess){
                isLoadQuestions=true;
            }
            isLoadQuestionsReceiver=true;
            initData();
        }
    }
}
