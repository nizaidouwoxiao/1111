package com.example.administrator.myapplication.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/28 0028.
 */

public class asdff {

    /**
     * error_code : 0
     * reason : ok
     * result : []
     */

    private int error_code;
    private String reason;
    private List<Queation> queations;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<Queation> getQueations() {
        return queations;
    }

    public void setQueations(List<Queation> queations) {
        this.queations = queations;
    }
}
