package com.sxs.pojo;

public class BaseOutOrder {

    /**
     * 出库单id
     */
    private int id;

    /**
     * 商品名
     */
    private String goodsName;

    /**
     * 出库数量
     */
    private int count;

    /**
     * 单价
     */
    private double price;

    /**
     * 总价
     */
    private double totalPrice;

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BaseOutOrder{" +
                "id=" + id +
                ", goodsName='" + goodsName + '\'' +
                ", count=" + count +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
