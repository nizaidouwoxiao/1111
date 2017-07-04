package com.example.administrator.myapplication.dao;

import android.content.Intent;
import android.util.Log;

import com.example.administrator.myapplication.ExamApplication;
import com.example.administrator.myapplication.bean.Exam;
import com.example.administrator.myapplication.bean.ExamInfo;
import com.example.administrator.myapplication.bean.Result;
import com.example.administrator.myapplication.bean.utils.OkHttpUtils;
import com.example.administrator.myapplication.bean.utils.ResultUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/7/3 0003.
 */

public class ExamDao implements IExam{
    @Override
    public void loadExamInfo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpUtils<ExamInfo>utils=new OkHttpUtils<>(ExamApplication.getInstance());
                String url="http://101.251.196.90:8080/JztkServer/examInfo";
                utils.url(url).targetClass(ExamInfo.class)
                        .execute(new OkHttpUtils.OnCompleteListener<ExamInfo>(){
                            @Override
                            public void onSuccess(ExamInfo result) {

                                Log.e("main","result="+result);
                                ExamApplication.getInstance().setExamInfo(result);
                                ExamApplication.getInstance()
                                        .sendBroadcast(new Intent(ExamApplication.LOAD_EXAM_INFO)
                                        .putExtra(ExamApplication.LOAD_DATA_SUCCESS,true));
                            }

                            @Override
                            public void onError(String error) {
                                Log.e("main","error="+error);
                                ExamApplication.getInstance()
                                        .sendBroadcast(new Intent(ExamApplication.LOAD_EXAM_INFO)
                                                .putExtra(ExamApplication.LOAD_DATA_SUCCESS,false));
                            }
                        });
                OkHttpUtils<String> utils1 =new OkHttpUtils<String>(ExamApplication.getInstance());
                String url2="http://101.251.196.90:8080/JztkServer/getQuestions?Type=rand";
                utils1.url(url2)
                        .targetClass(String.class)
                        .execute(new OkHttpUtils.OnCompleteListener<String>(){
                            @Override
                            public void onSuccess(String jsonStr) {

                                Result result =ResultUtils.getListResultFromJson(jsonStr);
                                if(result!=null&& result.getError_code()==0){
                                    List<Exam> list = result.getResult();
                                    if(list != null && list.size()>0){
                                       ExamApplication.getInstance().setmExamList(list);
                                        ExamApplication.getInstance()
                                                .sendBroadcast(new Intent(ExamApplication.LOAD_EXAM_QUESTION)
                                                        .putExtra(ExamApplication.LOAD_DATA_SUCCESS,true));
                                    }
                                }
                            }

                            @Override
                            public void onError(String error) {
                                Log.e("main","error+"+error);
                                ExamApplication.getInstance()
                                        .sendBroadcast(new Intent(ExamApplication.LOAD_EXAM_QUESTION)
                                                .putExtra(ExamApplication.LOAD_DATA_SUCCESS,false));
                            }
                        });
            }
        }).start();

    }

    @Override
    public void loadQuestionlists() {

    }
}
