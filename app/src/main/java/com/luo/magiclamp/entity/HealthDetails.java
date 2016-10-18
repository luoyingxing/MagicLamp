package com.luo.magiclamp.entity;

import java.io.Serializable;

/**
 * HealthDetails
 * <p/>
 * Created by Administrator on 2016/10/18.
 */
public class HealthDetails implements Serializable {
    private String description; // "减肥瘦身,美丽一生,减肥资讯 瘦身资讯 ,减肥瘦身健康知识,减肥瘦身信息专题"
    private int id; //11
    private String keywords; //减肥瘦身
    private String name; //减肥瘦身
    private int seq; // 1
    private String title; //减肥瘦身
    private int count; //638
    private int fcount; // 0
    private String img; ///lore/161012/958383e81740a4164baa67413c6cc34d.jpg，相对路径
    private int loreclass; //11
    private int rcount; // 0
    private long time; //1476281594000
    private String message;
    private boolean status;
    private String url;

    public HealthDetails() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getFcount() {
        return fcount;
    }

    public void setFcount(int fcount) {
        this.fcount = fcount;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getLoreclass() {
        return loreclass;
    }

    public void setLoreclass(int loreclass) {
        this.loreclass = loreclass;
    }

    public int getRcount() {
        return rcount;
    }

    public void setRcount(int rcount) {
        this.rcount = rcount;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
