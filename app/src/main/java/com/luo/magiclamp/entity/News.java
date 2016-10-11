package com.luo.magiclamp.entity;

import java.io.Serializable;
import java.util.List;

/**
 * News
 * <p/>
 * Created by Administrator on 2016/10/12.
 */
public class News implements Serializable {
    private int status; //200,status code
    private String error; //message for error
    private int count; //the count for data
    private List<NewsDetails> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<NewsDetails> getData() {
        return data;
    }

    public void setData(List<NewsDetails> data) {
        this.data = data;
    }
}
