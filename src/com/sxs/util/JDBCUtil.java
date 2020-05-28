package com.sxs.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUtil {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/shixun?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf-8&autoReconnect=true";
    private static final String USER = "root";
    private static final String PASSWORD = "lipanre@8879179.root";
    private static List<Connection> connList = new ArrayList<>();

    static{
        // 加载类
        try {
            Class.forName(DRIVER);

            // 创建5个连接数据库对象
            for (int i = 0; i < 5; i++) {
                connList.add(DriverManager.getConnection(URL, USER, PASSWORD));
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }

    /**
     * 获取数据库连接对象
     * @return
     */
    public static Connection getConn(){
        Connection resultConn = null;
        // 如果List中还有连接对象，就取出第一个，如果没有就返回null
        if (connList.size() > 0) {
            // 获取完连接对象，就移除
            resultConn = connList.get(0);
            connList.remove(resultConn);
        }
        return resultConn;

    }

    public static void close(Connection conn, PreparedStatement ps){
        try {
            if (null != ps) {
                ps.close();
            }
        } catch (SQLException e) {

        }

        connList.add(conn);
    }

    public static void close(ResultSet rs){
        try {
            if (null != rs) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(getConn());

    }
}
