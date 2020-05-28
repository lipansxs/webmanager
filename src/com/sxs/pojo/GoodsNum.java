package com.sxs.pojo;

public class GoodsNum {
    /**
     * 货号id
     */
    private int id;

    /**
     * 商品货号
     */
    private String goodsNum;

    public GoodsNum() {
    }

    public GoodsNum(int id) {
        this.id = id;
    }

    public GoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    public GoodsNum(int id, String goodsNum) {
        this.id = id;
        this.goodsNum = goodsNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    @Override
    public String toString() {
        return "GoodsNum{" +
                "id=" + id +
                ", goodsNum='" + goodsNum + '\'' +
                '}';
    }
}
