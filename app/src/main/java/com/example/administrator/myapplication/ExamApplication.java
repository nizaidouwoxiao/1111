package com.example.administrator.myapplication;

import android.app.Application;
import android.util.Log;

import com.example.administrator.myapplication.bean.Exam;
import com.example.administrator.myapplication.bean.ExamInfo;
import com.example.administrator.myapplication.bean.Result;
import com.example.administrator.myapplication.bean.utils.OkHttpUtils;
import com.example.administrator.myapplication.bean.utils.ResultUtils;
import com.example.administrator.myapplication.biz.ExamBiz;
import com.example.administrator.myapplication.biz.IExamBiz;

import java.util.List;

import static android.R.id.list;

/**
 * Created by Administrator on 2017/7/3.
 */

public class ExamApplication extends Application {
    ExamInfo mExamInfo;
    List<Exam> mExamList;
    private static ExamApplication instance;
    IExamBiz biz;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        biz=new ExamBiz();
        initData();
    }
    public static  ExamApplication getInstance(){
        return  instance;
    }

    private void initData() {
      new Thread(new Runnable() {
          @Override
          public void run() {
              biz.beginExam();
          }
      }).start();
    }
    public ExamInfo getmExamInfo(){
        return mExamInfo;
    }
    public void setExamInfo(ExamInfo examInfo){
        mExamInfo=examInfo;
    }




    public List<Exam> getmExamList() {
        return mExamList;
    }

    public void setmExamList(List<Exam> mExamList) {
        this.mExamList = mExamList;
    }
}
