package com.luo.magiclamp.entity;

import java.io.Serializable;
import java.util.List;

/**
 * CartoonPack
 * <p>
 * Created by Administrator on 2017/2/26.
 */

public class CartoonPack implements Serializable {
    private int showapi_res_code;
    private String showapi_res_error;
    private ResBody showapi_res_body;

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

    public ResBody getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ResBody showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public class ResBody {
        private int ret_code;
        private Page pagebean;
        private String currentPage;

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public Page getPagebean() {
            return pagebean;
        }

        public void setPagebean(Page pagebean) {
            this.pagebean = pagebean;
        }

        public String getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(String currentPage) {
            this.currentPage = currentPage;
        }

    }

    public class Page {
        private String allPages;
        private String maxResult;
        private List<Cartoon> contentlist;

        public String getAllPages() {
            return allPages;
        }

        public void setAllPages(String allPages) {
            this.allPages = allPages;
        }

        public String getMaxResult() {
            return maxResult;
        }

        public void setMaxResult(String maxResult) {
            this.maxResult = maxResult;
        }

        public List<Cartoon> getContentlist() {
            return contentlist;
        }

        public void setContentlist(List<Cartoon> contentlist) {
            this.contentlist = contentlist;
        }

    }

}