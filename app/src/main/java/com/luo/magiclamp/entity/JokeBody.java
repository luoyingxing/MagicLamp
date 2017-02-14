package com.luo.magiclamp.entity;

import java.io.Serializable;
import java.util.List;

/**
 * JokeBody
 * <p/>
 * Created by luoyingxing on 16/10/14.
 * update on 2017/2/15 , change the api which use aFanDa(阿凡达数据)
 */
public class JokeBody implements Serializable {
    private int error_code;
    private String reason;
    private List<Joke> result;

    public int getErrorCode() {
        return error_code;
    }

    public void setErrorCode(int errorCode) {
        this.error_code = errorCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<Joke> getResult() {
        return result;
    }

    public void setResult(List<Joke> result) {
        this.result = result;
    }
}
