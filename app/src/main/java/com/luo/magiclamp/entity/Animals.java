package com.luo.magiclamp.entity;

import java.io.Serializable;

/**
 * Animals
 * <p/>
 * Created by Administrator on 2016/10/17.
 */
public class Animals implements Serializable {
    private Result result;
    private int error_code;
    private String reason;

    public Animals() {
    }

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
        private String shengxiao1;
        private String shengxiao2;
        private String title;
        private String content1;
        private String content2;

        public String getShengxiao1() {
            return shengxiao1;
        }

        public void setShengxiao1(String shengxiao1) {
            this.shengxiao1 = shengxiao1;
        }

        public String getShengxiao2() {
            return shengxiao2;
        }

        public void setShengxiao2(String shengxiao2) {
            this.shengxiao2 = shengxiao2;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent1() {
            return content1;
        }

        public void setContent1(String content1) {
            this.content1 = content1;
        }

        public String getContent2() {
            return content2;
        }

        public void setContent2(String content2) {
            this.content2 = content2;
        }
    }
}
