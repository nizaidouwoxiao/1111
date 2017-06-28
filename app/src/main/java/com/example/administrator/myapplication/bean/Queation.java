package com.example.administrator.myapplication.bean;

/**
 * Created by Administrator on 2017/6/28 0028.
 */

public class Queation {

    /**
     * id : 2
     * question : 这个标志是何含义？
     * answer : 1
     * item1 : 线形诱导标志
     * item2 : 合流诱导标志
     * item3 : 分流诱导标志
     * item4 : 转弯诱导标志
     * explains : 线型诱导标线型诱导标志，用于引导车辆驾驶人改变行驶方向，促使安全运行。视需要设于易肇事之弯道路段，小半径匝道曲线或中央隔离设施及渠化设施的端部。线形诱导标的颜色规定为：指示性线形诱导标一般道路为蓝底白图案,高速公路为绿底白图案，用以提供一般性行驶指示;警告性线形诱导标为红底白图案，可使车辆驾驶人提高警觉，并准备防范应变之措施。
     * url : http://images.juheapi.com/jztk/c1c2subject1/2.jpg
     */

    private int id;
    private String question;
    private String answer;
    private String item1;
    private String item2;
    private String item3;
    private String item4;
    private String explains;
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getItem1() {
        return item1;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public String getItem2() {
        return item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public String getItem3() {
        return item3;
    }

    public void setItem3(String item3) {
        this.item3 = item3;
    }

    public String getItem4() {
        return item4;
    }

    public void setItem4(String item4) {
        this.item4 = item4;
    }

    public String getExplains() {
        return explains;
    }

    public void setExplains(String explains) {
        this.explains = explains;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
