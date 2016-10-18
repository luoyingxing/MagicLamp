package com.luo.magiclamp.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Health
 * <p/>
 * Created by Administrator on 2016/10/18.
 */
public class Health implements Serializable {
    private boolean status; //true
    private List<HealthDetails> tngou;

    public Health() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<HealthDetails> getHealthDetails() {
        return tngou;
    }

    public void setHealthDetails(List<HealthDetails> tngou) {
        this.tngou = tngou;
    }

}