package com.dayuanit.emall.util;

import java.util.List;

public class PageUtils<T> {

    /**
     * 每页显示两条信息
     */
    public static final int PRE_PAGE_NUM = 2;

    private int currentPageNum;
    private int totalPageNum;
    private List<T> data;

    public static int getPrePageNum() {
        return PRE_PAGE_NUM;
    }

    public int getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(int currentPageNum) {
        this.currentPageNum = currentPageNum;
    }

    public int getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(int totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public PageUtils(int currentPageNum, int totalDataNum) {
        this.currentPageNum = currentPageNum;
        this.totalPageNum = getTotalPageNum(totalDataNum);
    }

    public static int getTotalPageNum(int totalDataNum) {
        return (totalDataNum % PRE_PAGE_NUM) == 0 ? totalDataNum/PRE_PAGE_NUM : (totalDataNum/PRE_PAGE_NUM) + 1;
    }

    public int getOffset() {
        return (currentPageNum - 1)*PRE_PAGE_NUM;
    }
}
