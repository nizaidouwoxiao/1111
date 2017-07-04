package com.example.administrator.myapplication.biz;

import com.example.administrator.myapplication.bean.Exam;

/**
 * Created by Administrator on 2017/7/3 0003.
 */

public interface IExamBiz {
    void beginExam();
    Exam getExam();
    Exam nextQuestion();
    Exam preQuestion();
    int commitExam();
    String getExamIndex();
}
