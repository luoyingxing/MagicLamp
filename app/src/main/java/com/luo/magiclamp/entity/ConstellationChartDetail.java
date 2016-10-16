package com.luo.magiclamp.entity;

import java.io.Serializable;

/**
 * ConstellationChartDetail
 * <p/>
 * Created by Administrator on 2016/10/17.
 */
public class ConstellationChartDetail implements Serializable {
    private String date; //0151214, /*日期数值*/
    private String name; //双子座", /*星座名称*/
    private String QFriend; //摩羯座", /*速配星座*/
    private String all; //"40%", /*综合指数*/
    private String color; //"黄色", /*幸运色*/
    private String datetime; //"2015年12月14日", /*日期*/
    private String health; //"55%", /*健康指数*/
    private String love; //"40%", /*爱情指数*/
    private String money; //"40%", /*财运指数*/
    private String number; //4, /*幸运数字*/
    private String summary;//"依旧是家庭和工作蜡烛二头烧的你，体力真的不堪负荷，超级无敌爆累，已经濒临零界点，一触即发，要好好耐住性子沟通，变得好困难，最后还是吵架了，但也许吵架了吵出彼此的苦，才会换来体谅吧，最终还是家人啊！", /*总结*/
    private String work; //"40%", /*工作指数*/
    private String resultcode; //:"200", /*返回状态码 200为成功*/
    private String error_code; //:0 /*返回错误码 0为没有错误*/

    public ConstellationChartDetail() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQFriend() {
        return QFriend;
    }

    public void setQFriend(String QFriend) {
        this.QFriend = QFriend;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getLove() {
        return love;
    }

    public void setLove(String love) {
        this.love = love;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }
}
