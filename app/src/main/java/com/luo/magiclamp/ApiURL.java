package com.luo.magiclamp;

/**
 * 接口访问
 * Created by luoyingxing on 16/10/09.
 */
public class ApiURL {
    public static String SERVER_HOST = "";

    public static final String API_NEWS_GET_NEWS = "http://api.dagoogle.cn/news/get-news"; //新闻

    public static final String API_RECREATION_JOKE_TEXT = "http://apis.baidu.com/showapi_open_bus/showapi_joke/joke_text"; //文本笑话
    public static final String API_RECREATION_JOKE_IMG = "http://apis.baidu.com/showapi_open_bus/showapi_joke/joke_pic"; //图片笑话

    public static final String API_CHAT_TURING = "http://apis.baidu.com/turing/turing/turing"; //图灵机器人
    public static final String API_CHAT_CONSTELLATION_CHART = "http://api.avatardata.cn/Constellation/Query"; //星座运势
    public static final String API_CHAT_CONSTELLATION_CONJUGATE = "http://api.avatardata.cn/XingZuoPeiDui/Lookup"; //星座配对
    public static final String API_CHAT_ANIMALS = "http://api.avatardata.cn/ShengXiaoPeiDui/Lookup"; //生肖配对
    public static final String API_NAME_ORIGIN = "http://api.avatardata.cn/XingShiQiYuan/LookUp"; //姓氏起源

    public static final String API_HEALTH_CLASSIFY = "http://apis.baidu.com/tngou/lore/classify"; //健康知识分类
    public static final String API_HEALTH_LIST = "http://apis.baidu.com/tngou/lore/list"; //健康知识列表
    public static final String API_HEALTH_DETAILS = "http://apis.baidu.com/tngou/lore/show"; //健康知识详情

    public static final String API_HEALTH_LIST_IMAGE = "http://tnfs.tngou.net/image"; //健康知识列表图片的前缀

    public static final String API_FOCUS_CLASSIFY = "http://apis.baidu.com/tngou/top/classify"; //天狗热点分类
    public static final String API_FOCUS_LIST = "http://apis.baidu.com/tngou/top/list"; //天狗热点列表
    public static final String API_FOCUS_DETAILS = "http://apis.baidu.com/tngou/top/show"; //天狗热点详情
}