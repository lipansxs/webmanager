package com.sxs.serviceInf;

import com.sxs.pojo.BaseOutOrder;
import com.sxs.pojo.OutOrderInfo;
import com.sxs.pojo.PageInfo;

import java.util.List;

public interface OutOrderServiceInf {

    /**
     * 增加出库单数据
     *
     */
    public int addOutOrder(OutOrderInfo order);

    /**
     * 删除出库单信息
     * @param order
     * @return
     */
    public int delOutOrder(OutOrderInfo order);

    /**
     * 更新出库单信息
     * @param order
     * @return
     */
    public int updOutOrder(OutOrderInfo order);

    /**
     * 查询所有的出库单详情数据
     * @return
     */
    public List<OutOrderInfo> selAllOutOrder();

    /**
     * 通过id查询出库单详情
     * @param order
     * @return
     */
    public OutOrderInfo selOutOrderById(OutOrderInfo order);

    /**
     * 更新基本出库单基本信息
     * @param order
     * @return
     */
    public int updBaseOutOrder(BaseOutOrder baseOutOrder);

    /**
     * 查询所有出库单基本信息
     * @return
     */
    public List<BaseOutOrder> selAllBaseOutOrder();

    /**
     * 通过id查询入库单基本信息
     */
    public BaseOutOrder selBaseOutOrderById(BaseOutOrder baseOutOrder);

    /**
     * 分页查询出库单基本信息
     */
    public PageInfo<BaseOutOrder> selBaseOutOrderWithPageIndex(int pageIndex);

    /**
     * 分页查询出库单详情
     */
    public PageInfo<OutOrderInfo> selOutOrderWithPageIndex(int pageIndex);
}
