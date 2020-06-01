package com.sxs.mapper;

import com.sxs.pojo.BaseOutOrder;
import com.sxs.pojo.InOrderInfo;
import com.sxs.pojo.OutOrderInfo;
import com.sxs.pojo.PageInfo;
import com.sxs.util.JDBCUtil;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OutOrderMapper {


    /**
     * 通过id来更新基本出库单数据
     * @param baseOutOrder
     * @return
     */
    public int updBaseOutOrder(BaseOutOrder baseOutOrder){
        return SQLOption.updOption("update outorder_tb set goodsname = ?, count = ?, totalprice = ?, price = ? where id = ?", baseOutOrder, (ps, pojo)->{
            try {
                ps.setString(1, pojo.getGoodsName());
                ps.setInt(2, pojo.getCount());
                ps.setDouble(3, pojo.getTotalPrice());
                ps.setDouble(4, pojo.getPrice());
                ps.setInt(5, pojo.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 查询所有的入库单的基本信息
     * @return
     */
    public List<BaseOutOrder> selAllBaseOutOrder(){
        return SQLOption.selOption("select id, goodsname, count, totalprice from outorder_tb where isdel = 0", null, null, (rowSet)->{
            List<BaseOutOrder> orders = new ArrayList<>();

            try {
                rowSet.beforeFirst();

                while (rowSet.next()) {
                    BaseOutOrder order = new BaseOutOrder();
                    order.setId(rowSet.getInt("id"));
                    order.setCount(rowSet.getInt("count"));
                    order.setTotalPrice(rowSet.getDouble("totalprice"));
                    order.setGoodsName(rowSet.getString("goodsname"));

                    orders.add(order);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                JDBCUtil.close(rowSet);
            }
            return orders;
        });
    }

    /**
     * 分页查询
     */
    public PageInfo<BaseOutOrder> selBaseOutOrderWithPageIndex(int pageIndex){
        PageInfo<BaseOutOrder> pageInfo = new PageInfo<>();
        pageInfo.setPageIndex(pageIndex);

        // 设置页面信息的总的页数
        // 如果总的页面数是0，就查询数据库
        if (pageInfo.getTotalPageCount() == 0) {
            // 设置总的页数
            pageInfo.setTotalPageCount(SQLOption.selOption("select count(*) from outorder_tb", null, null, (rowSet)->{
                double dataCount = 0;
                try {
                    dataCount = rowSet.getInt(1);
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    JDBCUtil.close(rowSet);
                }
                return Math.ceil(dataCount/pageInfo.getPageSize());
            }));
        }

        return SQLOption.selOption("select * from outorder_tb limit ?, ?", pageInfo, (ps, page)->{

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
                    BaseOutOrder order = new BaseOutOrder();
                    order.setId(rowSet.getInt("id"));
                    order.setCount(rowSet.getInt("count"));
                    order.setTotalPrice(rowSet.getDouble("totalprice"));
                    order.setGoodsName(rowSet.getString("goodsname"));

                    pageInfo.addPageConent(order);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                JDBCUtil.close(rowSet);
            }
            return pageInfo;
        });
    }



    /**
     * 通过id查询基本出库单
     * @param baseOutOrder
     * @return
     */
    public BaseOutOrder selBaseOutOrderById(BaseOutOrder baseOutOrder){
        return SQLOption.selOption("select id, goodsname, count, totalprice from outorder_tb where id = ? and isdel = 0", baseOutOrder, (ps, pojo)->{
            try {
                ps.setInt(1, pojo.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }, (rowSet)->{

            BaseOutOrder order = new BaseOutOrder();

            try {
                rowSet.beforeFirst();
                while(rowSet.next()){
                    order.setId(rowSet.getInt("id"));
                    order.setCount(rowSet.getInt("count"));
                    order.setTotalPrice(rowSet.getDouble("totalprice"));
                    order.setGoodsName(rowSet.getString("goodsname"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                JDBCUtil.close(rowSet);
            }
            return order;
        });
    }

    /**
     * 增加出库单数据
     * @param outorder
     * @return
     */
    public int addOutOrder(OutOrderInfo outorder){
        return SQLOption.updOption("insert into outorder_tb values(default, ?, ?, ?, ?, ?, ?, ?, ?, 0)", outorder, (ps, pojo)->{
            try {
                ps.setInt(1, pojo.getGoodsNumId());
                ps.setInt(2, pojo.getCount());
                ps.setDouble(3, pojo.getPrice());
                ps.setDouble(4, pojo.getTotalPrice());
                ps.setString(5, pojo.getSale());
                ps.setString(6, pojo.getBuy());
                ps.setString(7, pojo.getTime());
                ps.setString(8, pojo.getGoodsName());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 通过id来删除出库单
     * @param outOrder
     * @return
     */
    public int delOutOrder(OutOrderInfo outOrder){
        return SQLOption.updOption("update outorder_tb set isdel = 1 where id = ?", outOrder, (ps, pojo)->{
            try {
                ps.setInt(1, pojo.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 通过id更新数据
     * @param outOrder
     * @return
     */
    public int updOutOrder(OutOrderInfo outOrder){
        return SQLOption.updOption("update outorder_tb set goodsnumid = ?," +
                "count = ?," +
                "price = ?," +
                "totalprice = ?," +
                "sale = ?," +
                "buy = ?," +
                "time = ?," +
                "goodsname = ? where id = ?", outOrder, (ps, pojo)->{
            try {
                ps.setInt(1, pojo.getGoodsNumId());
                ps.setInt(2, pojo.getCount());
                ps.setDouble(3, pojo.getPrice());
                ps.setDouble(4, pojo.getTotalPrice());
                ps.setString(5, pojo.getSale());
                ps.setString(6, pojo.getBuy());
                ps.setString(7, pojo.getTime());
                ps.setString(8, pojo.getGoodsName());
                ps.setInt(9, pojo.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 查询所有出库单信息
     * @return
     */
    public List<OutOrderInfo> selAllOutOrder(){
        return SQLOption.selOption("select * from outorder_tb where isdel = 0", null, null, (rowSet)->{

            List<OutOrderInfo> orders = new ArrayList<>();

            try {
                // 把游标移动到第一行数据前面
                rowSet.beforeFirst();

                while (rowSet.next()) {
                    OutOrderInfo order = new OutOrderInfo();

                    order.setId(rowSet.getInt("id"));
                    order.setGoodsNumId(rowSet.getInt("goodsnumid"));
                    order.setCount(rowSet.getInt("count"));
                    order.setPrice(rowSet.getDouble("price"));
                    order.setTotalPrice(rowSet.getDouble("totalprice"));
                    order.setSale(rowSet.getString("sale"));
                    order.setBuy(rowSet.getString("buy"));
                    order.setTime(rowSet.getString("time"));
                    order.setGoodsName(rowSet.getString("goodsname"));

                    orders.add(order);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
                JDBCUtil.close(rowSet);
            }
            return orders;
        });
    }

    /**
     * 分页查询出库单详情
     */
    public PageInfo<OutOrderInfo> selOutOrderWithPageIndex(int pageIndex){
        PageInfo<OutOrderInfo> pageInfo = new PageInfo<>();
        pageInfo.setPageIndex(pageIndex);

        // 设置页面信息的总的页数
        // 如果总的页面数是0，就查询数据库
        if (pageInfo.getTotalPageCount() == 0) {
            // 设置总的页数
            pageInfo.setTotalPageCount(SQLOption.selOption("select count(*) from outorder_tb", null, null, (rowSet)->{
                double dataCount = 0;
                try {
                    dataCount = rowSet.getInt(1);
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    JDBCUtil.close(rowSet);
                }
                return Math.ceil(dataCount/pageInfo.getPageSize());
            }));
        }

        return SQLOption.selOption("select * from outorder_tb limit ?, ?", pageInfo, (ps, page)->{

            try {
                ps.setInt(1, page.getPageBegin());
                ps.setInt(2, page.getPageSize());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }, (rowSet)->{
            try {
                // 把游标移动到第一行数据前面
                rowSet.beforeFirst();

                while (rowSet.next()) {
                    OutOrderInfo order = new OutOrderInfo();

                    order.setId(rowSet.getInt("id"));
                    order.setGoodsNumId(rowSet.getInt("goodsnumid"));
                    order.setCount(rowSet.getInt("count"));
                    order.setPrice(rowSet.getDouble("price"));
                    order.setTotalPrice(rowSet.getDouble("totalprice"));
                    order.setSale(rowSet.getString("sale"));
                    order.setBuy(rowSet.getString("buy"));
                    order.setTime(rowSet.getString("time"));
                    order.setGoodsName(rowSet.getString("goodsname"));

                    pageInfo.addPageConent(order);

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                JDBCUtil.close(rowSet);
            }
            return pageInfo;
        });
    }

    /**
     * 通过id来查询出库单信息
     * @param outOrder
     * @return
     */
    public OutOrderInfo selOutOrderById(OutOrderInfo outOrder){
        return SQLOption.selOption("select * from outorder_tb where id = ? and isdel = 0", outOrder, (ps, pojo)->{
            try {
                ps.setInt(1, pojo.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }, (rowSet)->{
            OutOrderInfo order = new OutOrderInfo();

            try {
                rowSet.beforeFirst();

                while (rowSet.next()) {

                    order.setId(rowSet.getInt("id"));
                    order.setGoodsNumId(rowSet.getInt("goodsnumid"));
                    order.setCount(rowSet.getInt("count"));
                    order.setPrice(rowSet.getDouble("price"));
                    order.setTotalPrice(rowSet.getDouble("totalprice"));
                    order.setSale(rowSet.getString("sale"));
                    order.setBuy(rowSet.getString("buy"));
                    order.setTime(rowSet.getString("time"));
                    order.setGoodsName(rowSet.getString("goodsname"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return order;
        });
    }

    public boolean isDel(OutOrderInfo order){
        return SQLOption.selOption("select isdel from outorder_tb where id = ?", order, (ps, pojo)->{
            try {
                ps.setInt(1, pojo.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }, (rowSet)->{
            boolean result = false;
            try {
                if (rowSet.getInt("isdel") != 0) {
                    result = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                JDBCUtil.close(rowSet);
            }

            return result;
        });
    }

}
