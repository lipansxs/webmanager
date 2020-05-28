package com.sxs.test.mappertest;

import com.sxs.constant.Constant;
import com.sxs.mapper.InOrderMapper;
import com.sxs.pojo.BaseInOrder;
import com.sxs.pojo.InOrderInfo;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InOrderMapperTest {

    private InOrderMapper mapper = null;
    @Before
    public void setUp() throws Exception {
        mapper = new InOrderMapper();
    }

    @Test
    public void updBaseInOrderById(){
        BaseInOrder baseInOrder = new BaseInOrder();
        baseInOrder.setId(1);
        baseInOrder.setGoodsName("裤子");
        baseInOrder.setTotalPrice(100000);
        baseInOrder.setTrueCount(100);

        assertEquals(Constant.DB_UPDATE_SUCCESS, mapper.updBaseInOrderById(baseInOrder));
    }

    @Test
    public void selAllBaseInOrder() {
        System.out.println(mapper.selAllBaseInOrder());
        assertNotNull(mapper.selAllBaseInOrder());
    }

    @Test
    public void selBaseInOrderById(){
        BaseInOrder order = new BaseInOrder();
        order.setId(1);
        System.out.println(mapper.selBaseInOrderById(order));
        assertNotNull(mapper.selBaseInOrderById(order));
    }

    @Test
    public void addInOrder() {

        InOrderInfo inorder = new InOrderInfo();

        inorder.setId(1);
        inorder.setBuy("腾讯集团");
        inorder.setGoodsNumId(1);
        inorder.setInCount(1000);
        inorder.setPrice(10);
        inorder.setSale("华科集团");
        inorder.setSubCount(20);
        inorder.setGoodsName("衣服");
        inorder.setTotalPrice(9800);
        inorder.setTrueCount(980);

        assertEquals(Constant.DB_UPDATE_SUCCESS, mapper.addInOrder(inorder));
    }

    @Test
    public void delInOrder(){
        InOrderInfo inorder = new InOrderInfo();
        inorder.setId(1);
        assertEquals(Constant.DB_UPDATE_SUCCESS, mapper.delInOrder(inorder));
    }

    @Test
    public void updInOrder(){
        InOrderInfo inorder = new InOrderInfo();

        inorder.setId(1);
        inorder.setBuy("大树集团");
        inorder.setGoodsNumId(1);
        inorder.setInCount(1000);
        inorder.setPrice(10);
        inorder.setSale("阿里巴巴集团");
        inorder.setSubCount(20);
        inorder.setGoodsName("衣服");
        inorder.setTotalPrice(9800);
        inorder.setTrueCount(980);

        assertEquals(Constant.DB_UPDATE_SUCCESS, mapper.updInOrder(inorder));
    }

    @Test
    public void selAll(){
        System.out.println(mapper.selAll());
        assertNotNull(mapper.selAll());
    }

    @Test
    public void selById(){
        InOrderInfo order = new InOrderInfo();
        order.setId(1);

        System.out.println(mapper.selById(order));
        assertNotNull(mapper.selById(order));
    }

    @Test
    public void isDel(){
        InOrderInfo order = new InOrderInfo();
        order.setId(1);

        assertEquals(true, mapper.isDel(order));
    }


}