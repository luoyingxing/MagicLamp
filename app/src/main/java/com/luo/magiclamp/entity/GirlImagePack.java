package com.luo.magiclamp.entity;

import java.io.Serializable;
import java.util.List;

/**
 * GirlImagePack
 * <p>
 * Created by Administrator on 2017/3/2.
 */

public class GirlImagePack implements Serializable {

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"newslist":[{"title":"哈尼宝宝 [TuiGirl推女郎]第76期 身高185cm超级长腿车模腿控秒阵亡","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2017/03/m.xxxiao.com_f1712d95bb12286dc54343ba97406bed-683x1024.jpg","description":"美女写真","ctime":"2017-03-01 20:00","url":"http://m.xxxiao.com/123872"},{"title":"美腿秀519（Olivia 2017.03.01)","picUrl":"http://image.hnol.net/c/2017-03/01/15/201703011531479491-2285289.jpg","description":"华声美女","ctime":"2017-03-01 16:00","url":"http://bbs.voc.com.cn/mm/meinv-7674836-0-1.html"},{"title":"美女车模","picUrl":"http://image.hnol.net/c/2017-03/01/09/201703010942213951-5472387.jpg","description":"华声美女","ctime":"2017-03-01 11:00","url":"http://bbs.voc.com.cn/mm/meinv-7674044-0-1.html"},{"title":"腹黑宝儿宝哥Divena 2016ChinaJoy斗鱼直播Showgirl性感可人","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2017/03/m.xxxiao.com_7e64f7b5d229a40003630c24b20878aa-770x1024.jpg","description":"美女写真","ctime":"2017-03-01 08:00","url":"http://m.xxxiao.com/123856"},{"title":"[灌水]深蓝色的温度[贴图]","picUrl":"http://image.hnol.net/c/2017-02/28/20/201702282022272381-4659347.jpg","description":"华声美女","ctime":"2017-03-01 00:00","url":"http://bbs.voc.com.cn/mm/meinv-7673579-0-1.html"},{"title":"[原创]龙萧萧写真曝光 清纯唯美夺人心扉","picUrl":"http://image.hnol.net/c/2017-02/28/17/201702281750245811-5472461.jpg","description":"华声美女","ctime":"2017-02-28 23:00","url":"http://bbs.voc.com.cn/mm/meinv-7673400-0-1.html"},{"title":"[贴图][分享]美模写真","picUrl":"http://image.hnol.net/c/2017-02/28/20/201702282035159211-5273753.jpg","description":"华声美女","ctime":"2017-02-28 21:00","url":"http://bbs.voc.com.cn/mm/meinv-7673587-0-1.html"},{"title":"明星毛晓彤","picUrl":"http://image.hnol.net/c/2017-02/28/20/201702282000095331-5058976.jpg","description":"华声美女","ctime":"2017-02-28 21:00","url":"http://bbs.voc.com.cn/mm/meinv-7673548-0-1.html"},{"title":"姹紫嫣红","picUrl":"http://image.hnol.net/c/2017-02/28/12/20170228123821351-239867.jpg","description":"华声美女","ctime":"2017-02-28 17:00","url":"http://bbs.voc.com.cn/mm/meinv-7672690-0-1.html"},{"title":"美腿秀518（Lucy 2017.02.28)","picUrl":"http://image.hnol.net/c/2017-02/28/16/201702281634513861-2285289.jpg","description":"华声美女","ctime":"2017-02-28 17:00","url":"http://bbs.voc.com.cn/mm/meinv-7673249-0-1.html"}],"code":200,"msg":"success"}
     */

    private int showapi_res_code;
    private String showapi_res_error;
    /**
     * newslist : [{"title":"哈尼宝宝 [TuiGirl推女郎]第76期 身高185cm超级长腿车模腿控秒阵亡","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2017/03/m.xxxiao.com_f1712d95bb12286dc54343ba97406bed-683x1024.jpg","description":"美女写真","ctime":"2017-03-01 20:00","url":"http://m.xxxiao.com/123872"},{"title":"美腿秀519（Olivia 2017.03.01)","picUrl":"http://image.hnol.net/c/2017-03/01/15/201703011531479491-2285289.jpg","description":"华声美女","ctime":"2017-03-01 16:00","url":"http://bbs.voc.com.cn/mm/meinv-7674836-0-1.html"},{"title":"美女车模","picUrl":"http://image.hnol.net/c/2017-03/01/09/201703010942213951-5472387.jpg","description":"华声美女","ctime":"2017-03-01 11:00","url":"http://bbs.voc.com.cn/mm/meinv-7674044-0-1.html"},{"title":"腹黑宝儿宝哥Divena 2016ChinaJoy斗鱼直播Showgirl性感可人","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2017/03/m.xxxiao.com_7e64f7b5d229a40003630c24b20878aa-770x1024.jpg","description":"美女写真","ctime":"2017-03-01 08:00","url":"http://m.xxxiao.com/123856"},{"title":"[灌水]深蓝色的温度[贴图]","picUrl":"http://image.hnol.net/c/2017-02/28/20/201702282022272381-4659347.jpg","description":"华声美女","ctime":"2017-03-01 00:00","url":"http://bbs.voc.com.cn/mm/meinv-7673579-0-1.html"},{"title":"[原创]龙萧萧写真曝光 清纯唯美夺人心扉","picUrl":"http://image.hnol.net/c/2017-02/28/17/201702281750245811-5472461.jpg","description":"华声美女","ctime":"2017-02-28 23:00","url":"http://bbs.voc.com.cn/mm/meinv-7673400-0-1.html"},{"title":"[贴图][分享]美模写真","picUrl":"http://image.hnol.net/c/2017-02/28/20/201702282035159211-5273753.jpg","description":"华声美女","ctime":"2017-02-28 21:00","url":"http://bbs.voc.com.cn/mm/meinv-7673587-0-1.html"},{"title":"明星毛晓彤","picUrl":"http://image.hnol.net/c/2017-02/28/20/201702282000095331-5058976.jpg","description":"华声美女","ctime":"2017-02-28 21:00","url":"http://bbs.voc.com.cn/mm/meinv-7673548-0-1.html"},{"title":"姹紫嫣红","picUrl":"http://image.hnol.net/c/2017-02/28/12/20170228123821351-239867.jpg","description":"华声美女","ctime":"2017-02-28 17:00","url":"http://bbs.voc.com.cn/mm/meinv-7672690-0-1.html"},{"title":"美腿秀518（Lucy 2017.02.28)","picUrl":"http://image.hnol.net/c/2017-02/28/16/201702281634513861-2285289.jpg","description":"华声美女","ctime":"2017-02-28 17:00","url":"http://bbs.voc.com.cn/mm/meinv-7673249-0-1.html"}]
     * code : 200
     * msg : success
     */

    private ShowapiResBodyBean showapi_res_body;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean {
        private int code;
        private String msg;
        /**
         * title : 哈尼宝宝 [TuiGirl推女郎]第76期 身高185cm超级长腿车模腿控秒阵亡
         * picUrl : http://m.xxxiao.com/wp-content/uploads/sites/3/2017/03/m.xxxiao.com_f1712d95bb12286dc54343ba97406bed-683x1024.jpg
         * description : 美女写真
         * ctime : 2017-03-01 20:00
         * url : http://m.xxxiao.com/123872
         */

        private List<NewslistBean> newslist;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public List<NewslistBean> getNewslist() {
            return newslist;
        }

        public void setNewslist(List<NewslistBean> newslist) {
            this.newslist = newslist;
        }

        public static class NewslistBean {
            private String title;
            private String picUrl;
            private String description;
            private String ctime;
            private String url;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
