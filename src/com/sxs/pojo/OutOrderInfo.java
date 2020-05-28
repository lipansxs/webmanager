package com.sxs.pojo;

public class OutOrderInfo extends BaseOutOrder{

    /**
     * 货号id
     */
    private int goodsNumId;

    /**
     * 单价
     */
    private double price;

    /**
     * 卖方名
     */
    private String sale;

    /**
     * 买方名
     */
    private String buy;

    /**
     * 出货时间
     */
    private String time;


    public int getGoodsNumId() {
        return goodsNumId;
    }

    public void setGoodsNumId(int goodsNumId) {
        this.goodsNumId = goodsNumId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "OutOrderInfo{" +
                "goodsNumId=" + goodsNumId +
                ", price=" + price +
                ", sale='" + sale + '\'' +
                ", buy='" + buy + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
