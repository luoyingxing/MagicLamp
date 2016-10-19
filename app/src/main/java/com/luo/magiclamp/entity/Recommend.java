package com.luo.magiclamp.entity;

import java.io.Serializable;

/**
 * Recommend
 * <p/>
 * Created by Administrator on 2016/10/19.
 */
public class Recommend implements Serializable {
    private String reason;
    private int error_code;
    private RecommendResult result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getErrorCode() {
        return error_code;
    }

    public void setErrorCode(int errorCode) {
        this.error_code = errorCode;
    }

    public RecommendResult getResult() {
        return result;
    }

    public void setResult(RecommendResult result) {
        this.result = result;
    }
}