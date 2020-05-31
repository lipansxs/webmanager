package com.sxs.serviceInf;

import com.sxs.pojo.GoodsNum;
import com.sxs.pojo.PageInfo;
import com.sxs.pojo.User;

import java.util.List;

public interface UserServiceInf {
    /**
     * 登入功能
     */
    public int login(User user);

    /**
     * 增加用户
     */
    public int add(User user);

    /**
     * 删除用户（优先使用id）
     */
    public int del(User user);

    /**
     * 修改用户(优先使用id )
     */
    public int update(User user);

    /**
     * 查询用户
     */
    public User sel(User user);

    /**
     * 查询所有用户
     */
    public List<User> selAll();

    /**
     * 通过页面当前页面数来查询
     */
    public PageInfo<User> selWithPageIndex(int pageIndex);

}
