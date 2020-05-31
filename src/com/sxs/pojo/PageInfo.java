package com.sxs.pojo;

import com.sxs.constant.Constant;

import java.util.ArrayList;
import java.util.List;

public  class  PageInfo <R>{

    /**
     * 每一页数据的开始位置
     */
    private int pageBegin;

    /**
     * 总共页面的数目
     */
    private double totalPageCount;

    /**
     * 每一页显示的数据的数目
     */
    private int pageSize = Constant.PAGE_SIZE;

    /**
     * 页面当前位置
     */
    private int pageIndex;

    /**
     * 每一个页面中显示的数据
     */
    private List<R> pageList = new ArrayList<>();


    /**
     * 获取页面数据开始的位置
     * @return
     */
    public int getPageBegin(){
        this.pageBegin = (this.pageIndex-1) * this.pageSize;
        return this.pageBegin;
    }


    public double getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(double totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void addPageConent(R content){
        this.pageList.add(content);
    }

    public List<R> getPageList(){
        return this.pageList;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "pageBegin=" + pageBegin +
                ", totalPageCount=" + totalPageCount +
                ", pageSize=" + pageSize +
                ", pageIndex=" + pageIndex +
                ", pageList=" + pageList +
                '}';
    }
}
