package com.example.administrator.myapplication.biz;

import com.example.administrator.myapplication.dao.ExamDao;
import com.example.administrator.myapplication.dao.IExam;

/**
 * Created by Administrator on 2017/7/3 0003.
 */

public class ExamBiz  implements IExamBiz{
    IExam dao;
    public ExamBiz(){
        this.dao=new ExamDao();

    }
    @Override
    public void beginExam() {
        dao.loadExamInfo();
        dao.loadQuestionlists();
    }

    @Override
    public void nextQuestion() {

    }

    @Override
    public void preQuestion() {

    }

    @Override
    public void commitExam() {

    }
}
