package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication.bean.Exam;
import com.example.administrator.myapplication.bean.ExamInfo;
import com.example.administrator.myapplication.bean.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2017/6/29.
 */

public class Eaxm extends AppCompatActivity {
    TextView tvExamInfo,tvExamTitle,tvOp1,tvOp2,tvOp3,tvOp4;
    ImageView mImageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eaxm);
        initView();
        initData();
    }

    private void initView() {
        tvExamInfo=(TextView) findViewById(R.id.tv_examinfo);
        tvExamTitle=(TextView) findViewById(R.id.tv_exam_title);
        tvOp1=(TextView) findViewById(R.id.tv_Op1);
        tvOp2=(TextView) findViewById(R.id.tv_Op2);
        tvOp3=(TextView) findViewById(R.id.tv_Op3);
        tvOp4=(TextView) findViewById(R.id.tv_Op4);
        mImageView=(ImageView)findViewById(R.id.in_exam_image);
    }

    private void initData() {
        ExamInfo examInfo = ExamApplication.getInstance().getmExamInfo();
        if(examInfo!=null){
            showData(examInfo);
        }
        List<Exam> examList= ExamApplication.getInstance().getmExamList();
        if(examList!=null){
            showExam(examList);

        }
    }

    private void showExam(List<Exam> examList) {
        Exam exam=examList.get(0);
        if(exam!=null){
            tvExamTitle.setText(exam.getQuestion());
            tvOp1.setText(exam.getItem1());
            tvOp2.setText(exam.getItem2());
            tvOp3.setText(exam.getItem3());
            tvOp4.setText(exam.getItem4());
            Picasso.with(Eaxm.this)
                    .load(exam.getUrl())
                    .into(mImageView);
        }
    }

    private void showData(ExamInfo examInfo) {
        tvExamInfo.setText(examInfo.toString());
    }
}
