package com.sxs.test.mappertest;

import com.sxs.constant.Constant;
import com.sxs.mapper.GoodsNumMapper;
import com.sxs.pojo.GoodsNum;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GoodsNumMapperTest {

    private GoodsNumMapper mapper = null;

    @Before
    public void setUp() throws Exception {
        mapper = new GoodsNumMapper();
    }

    @Test
    public void add() {
        assertEquals(Constant.DB_UPDATE_SUCCESS, mapper.add(new GoodsNum("479839")));
    }

    @Test
    public void del() {
        assertEquals(Constant.DB_UPDATE_SUCCESS, mapper.del(new GoodsNum(3, "")));
    }

    @Test
    public void upd() {
        assertEquals(Constant.DB_UPDATE_SUCCESS, mapper.upd(new GoodsNum(1, "424288")));
    }

    @Test
    public void selAll() {
        System.out.println("selAll:" + mapper.selAll());
        assertNotNull(mapper.selAll());
    }

    @Test
    public void selByGoodsNum() {
        System.out.println("selByNum:" + mapper.selByGoodsNum("424288"));
        assertNotNull(mapper.selByGoodsNum("424288"));
    }

    @Test
    public void selById() {
        // selById: com.sxs.pojo.GoodsNum@52102734
        System.out.println("selById: " + mapper.selById(2));
        assertNotNull(mapper.selById(2));
    }

    @Test
    public void isDel(){
        assertEquals(false, mapper.isDel(new GoodsNum(1)));
        assertEquals(true, mapper.isDel(new GoodsNum(3)));
    }

    @Test
    public void selWithPageIndex(){
        System.out.println(mapper.selWithPageIndex(2));
    }
}