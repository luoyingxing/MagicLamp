package com.luo.magiclamp.entity;

import java.io.Serializable;

/**
 * JokeList
 * <p/>
 * Created by luoyingxing on 16/10/14.
 */
public class JokeList implements Serializable {
    private String id;
    private String title;
    private String text;
    private String img;
    private int type;
    private String ct;

    public JokeList() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }
}
