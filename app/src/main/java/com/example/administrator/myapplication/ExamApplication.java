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
    public static String LOAD_EXAM_INFO="load_exam_info";
    public static String LOAD_EXAM_QUESTION="load_exam_qusetion";
    public static String LOAD_DATA_SUCCESS="load_data_success";
    ExamInfo mExamInfo;
    List<Exam> mExamList;
    private static ExamApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;

    }
    public static  ExamApplication getInstance(){
        return  instance;
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
