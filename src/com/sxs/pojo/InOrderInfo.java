package com.sxs.pojo;

public class InOrderInfo extends BaseInOrder {

    /**
     * 货号id
     */
    private int goodsNumId;

    /**
     * 进货量
     */
    private int inCount;

    /**
     * 进货差量
     */
    private int subCount;

    /**
     * 商品单价
     */
    private double price;

    /**
     * 买方名
     */
    private String buy;

    /**
     * 卖方名
     */
    private String sale;



    public int getGoodsNumId() {
        return goodsNumId;
    }

    public void setGoodsNumId(int goodsNumId) {
        this.goodsNumId = goodsNumId;
    }

    public int getInCount() {
        return inCount;
    }

    public void setInCount(int inCount) {
        this.inCount = inCount;
    }

    public int getSubCount() {
        return subCount;
    }

    public void setSubCount(int subCount) {
        this.subCount = subCount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }
}
