package com.sxs.test.serviceImplTest;

import com.sxs.constant.Constant;
import com.sxs.pojo.User;
import com.sxs.serviceImpl.UserServiceImpl;
import com.sxs.serviceInf.UserServiceInf;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceImplTest {

    private UserServiceInf service = new UserServiceImpl();

    @Test
    public void login() {
        User user = new User("lip", "123456");
        assertEquals(service.login(user), Constant.USER_NOT_EXISTENCE);
    }

    @Test
    public void add() {
        User user = new User("李四", "654321");
        assertNotEquals(0, service.add(user));
    }

    @Test
    public void del() {
        User user = new User("李四", "654321");
        assertNotEquals(10, service.del(user));
    }

    @Test
    public void update() {
        User user = new User(7, "张三", "lipanre");
        assertNotEquals(0, service.update(user));
    }

    @Test
    public void sel(){
        User user = new User("张三", "lipanre");
        assertNotNull(service.sel(user));
    }
}