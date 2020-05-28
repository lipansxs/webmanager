package com.sxs.test.mappertest;

import com.sxs.constant.Constant;
import com.sxs.mapper.OutOrderMapper;
import com.sxs.pojo.BaseOutOrder;
import com.sxs.pojo.OutOrderInfo;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class OutOrderMapperTest {

    private OutOrderMapper mapper = null;

    @Before
    public void setUp() throws Exception {
        mapper = new OutOrderMapper();
    }

    @Test
    public void addOutOrder() {

        DateFormat format = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");

        OutOrderInfo outOrder = new OutOrderInfo();
        outOrder.setId(1);
        outOrder.setGoodsNumId(1);
        outOrder.setCount(10000);
        outOrder.setPrice(10);
        outOrder.setTotalPrice(100000);
        outOrder.setSale("数达集团");
        outOrder.setBuy("阿里巴巴集团");
        outOrder.setTime(format.format(new Date()));
        outOrder.setGoodsName("短裤");

        assertEquals(Constant.DB_UPDATE_SUCCESS, mapper.addOutOrder(outOrder));

    }

    @Test
    public void delOutOrder(){
        OutOrderInfo outOrder = new OutOrderInfo();
        outOrder.setId(1);
        assertEquals(Constant.DB_UPDATE_SUCCESS, mapper.delOutOrder(outOrder));
    }

    @Test
    public void updOutOrder(){

        DateFormat format = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");

        OutOrderInfo outOrder = new OutOrderInfo();
        outOrder.setId(1);
        outOrder.setGoodsNumId(1);
        outOrder.setCount(10000);
        outOrder.setPrice(10);
        outOrder.setTotalPrice(100000);
        outOrder.setSale("舒达集团");
        outOrder.setBuy("腾讯集团");
        outOrder.setTime(format.format(new Date()));
        outOrder.setGoodsName("短袖");

        assertEquals(Constant.DB_UPDATE_SUCCESS, mapper.updOutOrder(outOrder));
    }

    @Test
    public void selAllOutOrder(){
        System.out.println(mapper.selAllOutOrder());
        assertNotNull(mapper.selAllOutOrder());
    }

    @Test
    public void selOutOrderById(){
        OutOrderInfo order = new OutOrderInfo();
        order.setId(1);

        System.out.println(mapper.selOutOrderById(order));
        assertNotNull(mapper.selOutOrderById(order));

    }

    @Test
    public void updBaseOutOrder(){
        BaseOutOrder order = new BaseOutOrder();

        order.setId(1);
        order.setGoodsName("拉布拉达");
        order.setCount(1000);
        order.setTotalPrice(100000);

        mapper.updBaseOutOrder(order);
    }

    @Test
    public void selAllBaseOutOrder(){
        System.out.println(mapper.selAllBaseOutOrder());
        assertNotNull(mapper.selAllBaseOutOrder());
    }

    @Test
    public void selBaseOutOrderById(){
        BaseOutOrder order = new BaseOutOrder();
        order.setId(1);
        System.out.println(mapper.selBaseOutOrderById(order));
        assertNotNull(mapper.selBaseOutOrderById(order));
    }

    @Test
    public void isDel(){
        OutOrderInfo order = new OutOrderInfo();
        order.setId(1);
        assertEquals(true, mapper.isDel(order));
    }
}