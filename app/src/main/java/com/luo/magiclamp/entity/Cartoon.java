package com.luo.magiclamp.entity;

import java.io.Serializable;

/**
 * Cartoon
 * <p>
 * Created by Administrator on 2017/2/26.
 */

public class Cartoon implements Serializable {
    private String id;
    private String title;
    private String link;

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Cartoon{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}