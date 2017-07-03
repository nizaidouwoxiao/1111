package com.example.administrator.myapplication.biz;

/**
 * Created by Administrator on 2017/7/3 0003.
 */

public interface IExamBiz {
    void  beginExam();
    void  nextQuestion();
    void preQuestion();
    void commitExam();
}
