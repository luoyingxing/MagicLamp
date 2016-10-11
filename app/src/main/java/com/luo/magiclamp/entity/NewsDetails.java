package com.luo.magiclamp.entity;

import java.io.Serializable;

/**
 * NewsDetails
 * <p/>
 * Created by Administrator on 2016/10/12.
 */
public class NewsDetails implements Serializable {
    private int news_id; //	新闻news_id
    private String title; //标题
    private String top_image; //头部图片
    private String text_image0; //内容中的图片（可能为空）
    private String text_image1; //	内容中的图片（可能为空）
    private String source; //	新闻来源
    private String content; //	新闻体
    private String digest; //	概要
    private int reply_count; //	回复数
    private int edit_time; //	发布时间

    public int getNewsId() {
        return news_id;
    }

    public void setNewsId(int newsId) {
        this.news_id = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopImage() {
        return top_image;
    }

    public void setTopImage(String topImage) {
        this.top_image = topImage;
    }

    public String getTextImage0() {
        return text_image0;
    }

    public void setTextImage0(String textImage0) {
        this.text_image0 = textImage0;
    }

    public String getTextImage1() {
        return text_image1;
    }

    public void setTextImage1(String textImage1) {
        this.text_image1 = textImage1;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public int getReplyCount() {
        return reply_count;
    }

    public void setReplyCount(int replyCount) {
        this.reply_count = replyCount;
    }

    public int getEditTime() {
        return edit_time;
    }

    public void setEditTime(int editTime) {
        this.edit_time = editTime;
    }
}
