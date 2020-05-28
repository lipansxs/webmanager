package com.sxs.pojo;

public class BaseInOrder {

    /**
     * 入库单id
     */
    private int id;

    /**
     * 商品名
     */
    private String goodsName;

    /**
     * 实际进货量
     */
    private int trueCount;

    /**
     * 总价
     */
    private double totalPrice;

    public BaseInOrder() {
    }

    public BaseInOrder(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getTrueCount() {
        return trueCount;
    }

    public void setTrueCount(int trueCount) {
        this.trueCount = trueCount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "BaseInOrder{" +
                "id=" + id +
                ", goodsName='" + goodsName + '\'' +
                ", trueCount=" + trueCount +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
