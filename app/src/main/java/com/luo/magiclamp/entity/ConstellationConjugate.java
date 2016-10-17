package com.luo.magiclamp.entity;

import java.io.Serializable;

/**
 * ConstellationConjugate
 * <p/>
 * Created by luoyingxing on 16/10/17.
 */
public class ConstellationConjugate implements Serializable {
    /**
     * xingzuo1 : 天蝎座
     * xingzuo2 : 天秤座
     * title : 天蝎座：天秤座
     * content1 : 友情：★★
     * 爱情：★★★
     * 婚姻：★★
     * 亲情：★★★
     * content2 : 透不到气来的组合。
     * 　　天秤和天蝎一个冷，一个热，天秤外表热情，内心总有点保留，天蝎外表冷漠，但一着火，热情膨湃。两人真的很不同呀！
     * 　　初相识时，天秤座会被天蝎座这种激情和神秘吸引，而且沉迷在他那股强烈的占有欲里，好象世界应该由他为你主管，但一旦久了，你会被天蝎座那种寸草不留的激情弄得透不到气，越想走他逼得你越紧，相反，天蝎座也对天秤座这种四处留情，“什么朋友都是朋友”的交友态度看不顺眼，造成好多不必要麻烦，虽然天秤座会避重就轻去瞒骗天蝎座，但是一旦被他发觉？看来会没完没了了~
     * 　　在性生活方面，应该是你俩最协调的一环，大家对性都很敏锐，知道对方什么时候需要然后加以配合，有时一些小小的事情也会将你们的性欲燃起。
     */

    private Result result;

    private int error_code;
    private String reason;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public static class Result {
        private String xingzuo1;
        private String xingzuo2;
        private String title;
        private String content1;
        private String content2;

        public String getXingzuo1() {
            return xingzuo1;
        }

        public void setXingzuo1(String xingzuo1) {
            this.xingzuo1 = xingzuo1;
        }

        public String getXingzuo2() {
            return xingzuo2;
        }

        public void setXingzuo2(String xingzuo2) {
            this.xingzuo2 = xingzuo2;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent1() {
            return content1;
        }

        public void setContent1(String content1) {
            this.content1 = content1;
        }

        public String getContent2() {
            return content2;
        }

        public void setContent2(String content2) {
            this.content2 = content2;
        }
    }
}
