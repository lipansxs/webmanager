package com.sxs.serviceImpl;

import com.sxs.mapper.OutOrderMapper;
import com.sxs.pojo.BaseOutOrder;
import com.sxs.pojo.OutOrderInfo;
import com.sxs.serviceInf.OutOrderServiceInf;

import java.util.List;

public class OutOrderServiceImpl implements OutOrderServiceInf {

    private final OutOrderMapper mapper = new OutOrderMapper();

    @Override
    public int addOutOrder(OutOrderInfo order) {
        return mapper.addOutOrder(order);
    }

    @Override
    public int delOutOrder(OutOrderInfo order) {
        return mapper.delOutOrder(order);
    }

    @Override
    public int updOutOrder(OutOrderInfo order) {
        return mapper.updOutOrder(order);
    }

    @Override
    public List<OutOrderInfo> selAllOutOrder() {
        return mapper.selAllOutOrder();
    }

    @Override
    public OutOrderInfo selOutOrderById(OutOrderInfo order) {
        return mapper.selOutOrderById(order);
    }

    @Override
    public int updBaseOutOrder(BaseOutOrder baseOutOrder) {
        return mapper.updBaseOutOrder(baseOutOrder);
    }

    @Override
    public List<BaseOutOrder> selAllBaseOutOrder() {
        return mapper.selAllBaseOutOrder();
    }

    @Override
    public BaseOutOrder selBaseOutOrderById(BaseOutOrder baseOutOrder) {
        return mapper.selBaseOutOrderById(baseOutOrder);
    }
}
