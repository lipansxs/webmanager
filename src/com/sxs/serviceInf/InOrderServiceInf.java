package com.sxs.serviceInf;

import com.sxs.pojo.BaseInOrder;
import com.sxs.pojo.InOrderInfo;

import java.util.List;

public interface InOrderServiceInf {

    /**
     * 增加入库单数据
     * @param order
     * @return
     */
    public int addInOrder(InOrderInfo order);

    /**
     * 通过id删除入库单信息
     * @param order
     * @return
     */
    public int delInOrder(InOrderInfo order);

    /**
     * 通过id来更新入库单信息
     * @param order
     * @return
     */
    public int updInOrder(InOrderInfo order);

    /**
     * 查询所有入库单信息
     * @return
     */
    public List<InOrderInfo> selAllInOrder();

    /**
     * 通过id来查询入库单信息
     * @param order
     * @return
     */
    public InOrderInfo selInOrderById(InOrderInfo order);

    /**
     * 利用id来更新入库单基本信息
     * @return
     */
    public int updBaseInOrderById(BaseInOrder baseInOrder);

    /**
     * 查询所有入库单的基本信息
     * @return
     */
    public List<BaseInOrder> selAllBaseInOrder();

    /**
     *
     * @param baseInOrder
     * @return
     */
    public BaseInOrder selBaseInOrderById(BaseInOrder baseInOrder);
}
