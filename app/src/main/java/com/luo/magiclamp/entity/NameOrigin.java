package com.luo.magiclamp.entity;

import java.io.Serializable;

/**
 * NameOrigin
 * <p/>
 * Created by Administrator on 2016/10/18.
 */
public class NameOrigin implements Serializable {
    private Result result;
    private int error_code;
    private String reason;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
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

    public static class Result {
        private String xing;
        private String intro;

        public String getXing() {
            return xing;
        }

        public void setXing(String xing) {
            this.xing = xing;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }
    }

}
