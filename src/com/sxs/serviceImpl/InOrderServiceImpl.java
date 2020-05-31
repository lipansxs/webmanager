package com.sxs.serviceImpl;

import com.sxs.mapper.InOrderMapper;
import com.sxs.pojo.BaseInOrder;
import com.sxs.pojo.InOrderInfo;
import com.sxs.pojo.PageInfo;
import com.sxs.serviceInf.InOrderServiceInf;

import java.util.List;

public class InOrderServiceImpl implements InOrderServiceInf {

    private final InOrderMapper mapper = new InOrderMapper();

    @Override
    public int addInOrder(InOrderInfo order) {
        return mapper.addInOrder(order);
    }

    @Override
    public int delInOrder(InOrderInfo order) {
        return mapper.delInOrder(order);
    }

    @Override
    public int updInOrder(InOrderInfo order) {
        return mapper.updInOrder(order);
    }

    @Override
    public List<InOrderInfo> selAllInOrder() {
        return mapper.selAll();
    }

    @Override
    public InOrderInfo selInOrderById(InOrderInfo order) {
        return mapper.selById(order);
    }

    @Override
    public int updBaseInOrderById(BaseInOrder baseInOrder) {
        return mapper.updBaseInOrderById(baseInOrder);
    }

    @Override
    public List<BaseInOrder> selAllBaseInOrder() {
        return mapper.selAllBaseInOrder();
    }

    @Override
    public PageInfo<BaseInOrder> selBaseInOrderWithPageIndex(int pageIndex) {
        return mapper.selBaseInOrderWithPageIndex(pageIndex);
    }

    @Override
    public PageInfo<InOrderInfo> selInOrderWithPageIndex(int pageIndex) {
        return mapper.selInOrderWithPageIndex(pageIndex);
    }

    @Override
    public BaseInOrder selBaseInOrderById(BaseInOrder baseInOrder) {
        return mapper.selBaseInOrderById(baseInOrder);
    }
}
