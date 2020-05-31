package com.sxs.serviceImpl;

import com.sxs.constant.Constant;
import com.sxs.mapper.UserMapper;
import com.sxs.pojo.PageInfo;
import com.sxs.pojo.User;
import com.sxs.serviceInf.UserServiceInf;

import java.util.List;

public class UserServiceImpl implements UserServiceInf {

    private UserMapper userMapper = new UserMapper();

    @Override
    public int login(User user) {

        // 如果用户被删除了，就不存在了
        if (this.userMapper.isDel(user)) {
            return Constant.USER_NOT_EXISTENCE;
        }

        User user_tmp = userMapper.selByName(user.getName());

        if (user_tmp != null) {
            // 用户存在的条件下，比较密码
            if (user_tmp.getPwd().equals(user.getPwd())) {
                // 如果密码正确就登入成功
                return Constant.LOGIN_SUCCESS;
            }else {
                // 密码不正确
                return Constant.PASSWORD_NOT_RIGHT;
            }
        }else{
            // 用户不存在
            return Constant.USER_NOT_EXISTENCE;
        }

    }

    @Override
    public int add(User user) {

        // 增加用户之前判断用户是否已经存在
        if (null == userMapper.selByName(user.getName())){
            return userMapper.add(user);
        }
        // 用户已存在
        return Constant.USER_ALREADY_EXISTENCE;

    }

    @Override
    public int del(User user) {

        int result = 0;

        if (user.getId() > 0) {
            result = userMapper.delById(user.getId());
        }else if (user.getName() != null) {

            if (this.userMapper.isDel(user)) {
                return Constant.USER_NOT_EXISTENCE;
            }

            result = userMapper.delByName(user.getName());
        }
        return result;
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public User sel(User user) {
        if (user.getId() > 0) {
            return userMapper.selById(user.getId());
        }
        return userMapper.selByName(user.getName());
    }

    @Override
    public List<User> selAll() {
        return userMapper.selAllUser();
    }

    @Override
    public PageInfo<User> selWithPageIndex(int pageIndex) {
        return userMapper.selWithPageIndex(pageIndex);
    }

}
