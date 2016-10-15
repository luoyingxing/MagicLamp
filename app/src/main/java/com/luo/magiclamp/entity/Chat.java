package com.luo.magiclamp.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Chat
 * <p/>
 * Created by Administrator on 2016/10/15.
 */
public class Chat implements Serializable {
    private String text; //我可以在会议中给你帮助的机器人
    private int code; //100000
    private int emojid; //0
    private String app_id; //40000
    private String user_reqid; //0000000000147653974553569987396-123
    private int faqAnswerId; //0
    private String tableName; // ""
    private int task_pos; //1
    private String text_after; // ""
    private String showtext; //我可以在会议中给你帮助的机器人
    private String show_text_after; //""
    private List<ChatList> text_array;
    private int type;

    public Chat() {
    }

    public Chat(String text, int type) {
        this.text = text;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getEmojid() {
        return emojid;
    }

    public void setEmojid(int emojid) {
        this.emojid = emojid;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getUser_reqid() {
        return user_reqid;
    }

    public void setUser_reqid(String user_reqid) {
        this.user_reqid = user_reqid;
    }

    public int getFaqAnswerId() {
        return faqAnswerId;
    }

    public void setFaqAnswerId(int faqAnswerId) {
        this.faqAnswerId = faqAnswerId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getTask_pos() {
        return task_pos;
    }

    public void setTask_pos(int task_pos) {
        this.task_pos = task_pos;
    }

    public String getText_after() {
        return text_after;
    }

    public void setText_after(String text_after) {
        this.text_after = text_after;
    }

    public String getShowtext() {
        return showtext;
    }

    public void setShowtext(String showtext) {
        this.showtext = showtext;
    }

    public String getShow_text_after() {
        return show_text_after;
    }

    public void setShow_text_after(String show_text_after) {
        this.show_text_after = show_text_after;
    }

    public List<ChatList> getText_array() {
        return text_array;
    }

    public void setText_array(List<ChatList> text_array) {
        this.text_array = text_array;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
