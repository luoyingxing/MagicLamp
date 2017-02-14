package com.luo.magiclamp.entity;

import java.io.Serializable;

/**
 * Joke
 * <p/>
 * Created by luoyingxing on 16/10/14.
 */
public class Joke implements Serializable {
    private String content;
    private String hashId;
    private String unixtime;
    private String updatetime;
    private String url;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    public String getUnixTime() {
        return unixtime;
    }

    public void setUnixTime(String unixTime) {
        this.unixtime = unixTime;
    }

    public String getUpdateTime() {
        return updatetime;
    }

    public void setUpdateTime(String updateTime) {
        this.updatetime = updateTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
