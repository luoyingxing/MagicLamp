package com.luo.magiclamp.entity;

import java.io.Serializable;

/**
 * Chat
 * <p/>
 * Created by luoyingxing on 2016/10/15.
 * updated by luoyingxing on 2017/2/15
 */
public class Chat implements Serializable {
    private int code;
    private String text;
    private int type; // 0默认为机器，1为用户发出的消息体

    public Chat(String text, int type) {
        this.text = text;
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
