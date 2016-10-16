package com.luo.magiclamp.entity;

import java.io.Serializable;

/**
 * ConstellationChart
 * <p/>
 * Created by Administrator on 2016/10/17.
 */
public class ConstellationChart implements Serializable {
    private ConstellationChartDetail result1;
    private String result2; //null
    private int error_code; //0
    private String reason; //Succes

    public ConstellationChart() {
    }

    public ConstellationChartDetail getResult1() {
        return result1;
    }

    public void setResult1(ConstellationChartDetail result1) {
        this.result1 = result1;
    }

    public String getResult2() {
        return result2;
    }

    public void setResult2(String result2) {
        this.result2 = result2;
    }

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
}
