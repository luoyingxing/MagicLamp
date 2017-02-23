package com.luo.magiclamp.entity;

import java.io.Serializable;

/**
 * Joke
 * <p/>
 * Created by luoyingxing on 16/10/14.
 */
public class Joke implements Serializable {
    /**
     * id : 585e403c6e36392559c741ef
     * title : 姿势我已经很老练了，就差个女友了！
     * img : http://www.zbjuran.com/uploads/allimg/161224/10-1612241H504D9.gif
     * type : 3
     * ct : 2016-12-24 17:30:36.985
     */
    private String id;
    private String title;
    private String text;
    private String img;
    private int type;
    private String ct;

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
