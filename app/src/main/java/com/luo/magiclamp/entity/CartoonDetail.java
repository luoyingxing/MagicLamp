package com.luo.magiclamp.entity;

import java.io.Serializable;

/**
 * CartoonDetail
 * <p>
 * Created by Administrator on 2017/2/27.
 */

public class CartoonDetail implements Serializable {
    private int showapi_res_code;
    private String showapi_res_error;
    private Detail showapi_res_body;

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

    public Detail getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(Detail showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class Detail {
        private String title;
        private int ret_code;
        private String img;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
