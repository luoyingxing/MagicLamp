package com.luo.magiclamp.entity;

import java.io.Serializable;

/**
 * ChatPack
 * <p>
 * Created by Administrator on 2017/2/15.
 */

public class ChatPack implements Serializable {
    private String reason;
    private Chat result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Chat getResult() {
        return result;
    }

    public void setResult(Chat result) {
        this.result = result;
    }

    public int getErrorCode() {
        return error_code;
    }

    public void setErrorCode(int errorCode) {
        this.error_code = errorCode;
    }
}
