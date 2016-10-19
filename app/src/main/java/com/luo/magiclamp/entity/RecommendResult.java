package com.luo.magiclamp.entity;

import java.io.Serializable;
import java.util.List;

/**
 * RecommendResult
 * <p/>
 * Created by Administrator on 2016/10/19.
 */
public class RecommendResult implements Serializable {
    private List<RecommendDetails> list;
    private int totalPage; //25
    private int ps; //20
    private int pno; //1

    public List<RecommendDetails> getList() {
        return list;
    }

    public void setList(List<RecommendDetails> list) {
        this.list = list;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public int getPno() {
        return pno;
    }

    public void setPno(int pno) {
        this.pno = pno;
    }
}
