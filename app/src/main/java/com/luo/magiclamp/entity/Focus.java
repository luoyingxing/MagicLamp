package com.luo.magiclamp.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Focus
 * <p/>
 * Created by luoyingxing on 16/10/19.
 */
public class Focus implements Serializable {
    private boolean status; //true
    private int total; //1724
    private List<FocusDetails> tngou;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<FocusDetails> getTngou() {
        return tngou;
    }

    public void setTngou(List<FocusDetails> tngou) {
        this.tngou = tngou;
    }
}
