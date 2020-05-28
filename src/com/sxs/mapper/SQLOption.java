package com.sxs.mapper;

import com.mysql.cj.protocol.Resultset;
import com.sxs.constant.Constant;
import com.sxs.util.JDBCUtil;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class SQLOption {
    /**
     * 更新数据库操作方法
     * R 代表需要操作的实体类对象
     * consumer 用来给ps对象赋值参数
     *
     */
    public static <R> int updOption(String sql, R pojo, BiConsumer<PreparedStatement, R> consumer){

        return (int) option(sql, pojo, (ps, obj)->{

            int result = 0;

            try {
                // 设置sql语句的参数
                consumer.accept(ps, obj);

                // 执行sql语句
                if (ps.executeUpdate() > 0){
                    result = Constant.DB_UPDATE_SUCCESS;
                }else{
                    result = Constant.DB_UPDATE_FAILED;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return result;

        });

    }


    /**
     * 根据某一列来查询数据，并且把数据封装成对象，并且把对象返回，其中SQL参数的赋值和对象的返回逻辑不同的类不同
     * @param sql
     * @param selColumnVal 需要设计的参数的值
     * @param function 通过获取rowSet来
     * @param <T> 实体类对象的类型
     * @param <R>
     * @return
     */
    public static <T, R> T selOption(String sql, R selColumnVal, BiConsumer<PreparedStatement, R> consumer , Function<CachedRowSet, T> function){
        // 获取rowSet对象
        CachedRowSet rowSet = selOption(sql, selColumnVal, consumer); // 这个consumer是用来设置sql中参数的值的

        try {
            // 把游标置为第一行数据
            rowSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 获得rowSet对象后，返回对象（不同的实体类有不同的逻辑）
        return function.apply(rowSet);

    }


    /**
     * 查询数据库,如果查询所有数据就把需要传递的参数传null和lambda表达式传null
     * @param sql sql语句
     * @param selColumnVal 需要传递个sql语句的参数的值
     * @param consumer 为ps设置参数，执行sql
     * @return
     */
    private static <T, R> CachedRowSet selOption(String sql, R selColumnVal, BiConsumer<PreparedStatement, R> consumer){

        return option(sql, selColumnVal, (ps, obj)->{

            CachedRowSet rowSet = null;
            ResultSet rs = null;

            try {

                // 创建离线访问数据库对象
                rowSet = RowSetProvider.newFactory().createCachedRowSet();

                // 如果查询字段为null（就是不查询任何字段）,就查询所有数据
                // 如果需要查询的列名不为null，就把需要查询的字段设置到sql语句中
                if (null != obj && consumer != null) {

                    // 设置sql的参数，根据不同的lambda表达式设置不同的参数
                    consumer.accept(ps, obj);
                }

                // 执行sql语句
                rs = ps.executeQuery();

                // 把查询出来的数据填充离线访问对象
                rowSet.populate(rs);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return rowSet;
        });
    }


    /**
     *
     * @param sql 需要执行的sql语句
     * @param obj 设置的参数值
     * @param function 执行sql语句
     * @param <R> 需要设置的值的类型
     * @param <T> 需要返回值的类型
     * @return
     */
    private static <R, T> T option(String sql, R obj, BiFunction<PreparedStatement, R, T> function){

        T result = null;

        // 获取数据库连接对象
        Connection conn = JDBCUtil.getConn();

        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql);

            // 设置ps的参数和获得返回值
            result = function.apply(ps, obj);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JDBCUtil.close(conn, ps);
        }
        return result;

    }



}
