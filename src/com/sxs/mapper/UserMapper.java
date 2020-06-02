package com.sxs.mapper;

import com.sxs.constant.Constant;
import com.sxs.pojo.GoodsNum;
import com.sxs.pojo.PageInfo;
import com.sxs.pojo.User;
import com.sxs.util.JDBCUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserMapper {


    // 打印日志对象
    private Logger logger = Logger.getLogger(UserMapper.class);

    /**
     * 增加用户,只需要提供用户名和密码
     * @param user 需要增加的用户对象
     * @return 插入数据库中，影响的数据条数
     */
    public int add(User user){

        int result = 0;
        Connection conn = JDBCUtil.getConn();

        String sql = "";

        // 如果这个用户名和已经被删除的用户的用户名一样，就执行更新操作
        boolean isDel = this.isDel(user);
        if (isDel){
            sql = "update set pwd = ?, isdel = ? where name = ?";
        }else if (selByName(user.getName()) != null) {
            return Constant.USER_ALREADY_EXISTENCE;
        }else {
            sql = "insert into user_tb values(default, ?, ?, ?)";
        }

        PreparedStatement ps = null;


        try {
            // 获取PreparedStatement对象
            ps = conn.prepareStatement(sql);

            // 设置sql语句的参数值
            if (isDel) {
                ps.setString(1, user.getPwd());
                ps.setInt(2, 0);
                ps.setString(3, user.getName());
            }else{
                ps.setString(1, user.getName());
                ps.setString(2, user.getPwd());
                ps.setInt(3, 0);
            }
            logger.info("正在增加用户：" + user);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(user + "增加失败！原因：" + e.getMessage());
            return 0;
        }finally{

            // 关闭数据库连接相关对象
            JDBCUtil.close(conn, ps);
        }
        logger.info(user + "增加成功！");
        return result;
    }

    /**
     * 通过id来删除用户
     * @param id
     * @return
     */
    public int delById(int id){

        int result = 0;
        Connection conn = JDBCUtil.getConn();
        String sql = "update user_tb set isdel = ? where id = ?";

        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql);

            ps.setInt(1, 1);
            ps.setInt(2, id);

            logger.info("正在删除id = " + id + "的用户！");
            // 执行sql
            result = ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("id = " + id + "的用户删除失败,原因：" + e.getMessage());
        }finally{
            JDBCUtil.close(conn, ps);
        }
        logger.info("id = " + id + "的用户删除成功");
        return result;
    }

    /**
     * 通过用户名来删除用户
     * @param
     * @return
     */
    public int delByName(String name){

        int result = 0;
        Connection conn = JDBCUtil.getConn();
        String sql = "update user_tb set isdel = ? where username = ?";

        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql);

            ps.setInt(1, 1);
            ps.setString(2, name);

            logger.info("正在删除用户名为：" + name + "的用户");
            // 执行sql
            result = ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("用户名为：" + name + "的用户删除失败,原因：" + e.getMessage());
        }finally{
            JDBCUtil.close(conn, ps);
        }
        logger.info("用户名为：" + name + "的用户删除成功！");
        return result;
    }

    /**
     * 通过用户的id来修改用户的其他信息
     * @param user
     * @return
     */
    public int update(User user){

        // 如果是被删除了就更新
        // 在更新的时候，如果该用户名已经存在，就把原来的那条数据更新
        String sql = "";
        boolean isDel = this.isDel(user);
        if (isDel){
            sql = "update user_tb set pwd=?, isdel=? where username = ?";
        }else{
            sql = "update user_tb set username = ?, pwd = ? where id = ?";
        }

        // 最终修改的结果
        int result = 0;
        Connection conn = JDBCUtil.getConn();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql);


            if (isDel) {
                ps.setString(1, user.getPwd());
                ps.setInt(2, 0);
                ps.setString(3, user.getName());

                // 如果修改后的新用户名是一个已经被删除的用户，在修改完之后，要把旧用户给删除
                this.delById(user.getId());

            }else{
                ps.setString(1, user.getName());
                ps.setString(2, user.getPwd());
                ps.setInt(3, user.getId());
            }


            logger.info("正在更新id为：" + user.getId() + "的用户为：" + user);
            // 执行sql
            result = ps.executeUpdate();



        } catch (SQLException e) {
            logger.error("id为:" + user.getId() + "更新失败！conn，原因是：" + e.getMessage());
        }finally{
            JDBCUtil.close(conn, ps);
        }
        logger.info("id为:" + user.getId() + "更新成功！");
        return result;
    }

    /**
     * 通过id来查找用户
     * @param id
     * @return
     */
    public User selById(int id){

        User result = null;

        // 如果被删除了，就返回
        if (checkIsDel(id)) {
            return result;
        }

        Connection conn = JDBCUtil.getConn();
        String sql = "select id, username, pwd from user_tb where id = ?";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            // 获取查询结果
            rs = ps.executeQuery();

            // 使游标移动到第一行
            rs.next();

            logger.info("正在查询id为：" + id + "的用户！");
            result = new User(rs.getInt("id"), rs.getString("username"), rs.getString("pwd"));

        } catch (SQLException e) {
            logger.error("查询id为：" + id + "的用户失败，因为该用户不存在");
        }finally{
            JDBCUtil.close(rs);
            JDBCUtil.close(conn, ps);
        }
        logger.info("id为：" + id + "的用户查询成功");
        return result;
    }

    /**
     * 通过用户名来查找用户
     */
    public User selByName(String name){
        User user = null;

        // 判断是否别删除
        if (checkIsDel(name)) {
            return user;
        }

        Connection conn = JDBCUtil.getConn();
        String sql = "select id, username, pwd from user_tb where username=?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1, name);

            logger.info("正在查询用户名为：" + name + "的用户");
            rs = ps.executeQuery();
            // 使游标移动到第一行
            rs.next();

            user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("pwd"));

        } catch (SQLException e) {
            logger.error("用户名为：" + name + "的用户查询失败！因为用户不存在");
        }finally{
            JDBCUtil.close(rs);
            JDBCUtil.close(conn, ps);

        }
        logger.info("用户名为：" + name + "的用户查询成功！");
        return user;
    }

    /**
     * 用来判断用户是否被删除(通过用户名),如果已经被删除就返回true 如果没有被删除就返回false
     */
    public boolean isDel(User user){
        boolean result = false;
        Connection conn = JDBCUtil.getConn();
        String sql = "";

        if (null != user.getName()) {
            sql = "select isdel from user_tb where username = ?";
        }else if (user.getId() > 0) {
            sql = "select isdel from user_tb where id = ?";
        }

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);

            if (null != user.getName()) {
                ps.setString(1, user.getName());
            }else if (user.getId() > 0) {
                ps.setInt(1, user.getId());
            }


            logger.info("查询用户：" + user + "是否已经删除！");
            rs = ps.executeQuery();

            rs.next();

            // 如果为0就表示没有被删除，否则表示已经被删除
            if (rs.getInt("isdel") == 0){
                result = false;
            }else {
                result = true;
            }

        } catch (SQLException e) {
            logger.error("查询用户：" + user.getName() + "是否被删除失败，因为该用户不存在");
        }finally{
            JDBCUtil.close(rs);
            JDBCUtil.close(conn, ps);
        }
        logger.info("查询用户：" + user.getName() + "是否被删除成功！");
        return result;
    }

    /**
     * 查询所有用户
     */
    public List<User> selAllUser(){

        List<User> users = new ArrayList<>();
        String sql = "select * from user_tb where isdel = 0";

        // 通过数据库连接池，获得数据库连接对象
        Connection conn = JDBCUtil.getConn();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("pwd"));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(rs);
            JDBCUtil.close(conn, ps);
        }
        return users;
    }

    /**
     * 通过id检查是否被删除
     */
    public boolean checkIsDel(int id){
        User user = new User();

        user.setId(id);
        return this.isDel(user);
    }

    /**
     * 通过用户名判断是否被删除
     * @param name
     * @return
     */
    public boolean checkIsDel(String name){
        User user = new User();
        user.setName(name);
        return this.isDel(user);
    }

    /**
     * 通过分页查询数据,默认每页显示5条数据
     */
    public PageInfo<User> selWithPageIndex(int pageIndex){
        //
        PageInfo<User> pageInfo = new PageInfo<>();
        pageInfo.setPageIndex(pageIndex);

        // 设置页面信息的总的页数
        // 如果总的页面数是0，就查询数据库
        if (pageInfo.getTotalPageCount() == 0) {
            pageInfo.setTotalPageCount(SQLOption.selOption("select count(*) from user_tb where isdel = 0", null, null, (rowSet)->{
                double dataCount = 0;
                try {
                    dataCount = rowSet.getInt(1);
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    JDBCUtil.close(rowSet);
                }
                // 防止小数
                return Math.ceil(dataCount/pageInfo.getPageSize());
            }));
        }

        return SQLOption.selOption("select * from user_tb where isdel = 0 limit ?, ?", pageInfo, (ps, page)->{

            try {
                ps.setInt(1, page.getPageBegin());
                ps.setInt(2, page.getPageSize());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }, (rowSet)->{
            try {
                rowSet.beforeFirst();

                while (rowSet.next()) {
                    User user = new User();
                    user.setId(rowSet.getInt("id"));
                    user.setName(rowSet.getString("username"));
                    user.setPwd(rowSet.getString("pwd"));
                    pageInfo.addPageConent(user);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                JDBCUtil.close(rowSet);
            }
            return pageInfo;
        });
    }
}
