package com.sxs.test.mappertest;

import com.sxs.mapper.UserMapper;
import com.sxs.pojo.User;
import com.sxs.util.JDBCUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserMapperTest {

    private UserMapper mapper = new UserMapper();


    @Test
    public void add() {
        assertNotEquals("插入失败！", 0, mapper.add(new User("张三", "123456")));
    }

    @Test
    public void delById() {
        assertNotEquals("通过id删除失败", 0, mapper.delById(6));
    }

    @Test
    public void delByName() {
        assertNotEquals("通过name删除失败", 0, mapper.delByName("张三"));
    }

    @Test
    public void update() {
        assertNotEquals("修改失败", 0, mapper.update(new User(6, "赵六", "456356734")));
    }

    @Test
    public void selById(){
        assertNotNull(mapper.selById(6));
    }

    @Test
    public void selByName(){
        assertNotNull(mapper.selByName("赵六"));
    }

    @Test
    public void isDel(){
        assertTrue(mapper.isDel(new User("赵六", "123456")));
    }
}