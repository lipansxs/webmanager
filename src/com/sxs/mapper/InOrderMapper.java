package com.sxs.mapper;

import com.sxs.pojo.BaseInOrder;
import com.sxs.pojo.GoodsNum;
import com.sxs.pojo.InOrderInfo;
import com.sxs.pojo.PageInfo;
import com.sxs.util.JDBCUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InOrderMapper {
    /**
     * 实现基本信息的查询、修改
     */

    /**
     * 通过id来更新基本数据
     * @param baseInOrder
     * @return
     */
    public int updBaseInOrderById(BaseInOrder baseInOrder){

        InOrderInfo order = new InOrderInfo();
        order.setId(baseInOrder.getId());
        order = this.selById(order);

        int subCount = order.getInCount() - baseInOrder.getTrueCount();

        return SQLOption.updOption("update inorder_tb set goodsname = ?, " +
                "trueincount = ?," +
                "totalprice = ?, " +
                "price = ?," +
                "subcount = ? " +
                "where id = ?", baseInOrder, (ps, pojo)->{
            try {
                ps.setString(1, pojo.getGoodsName());
                ps.setInt(2, pojo.getTrueCount());
                ps.setDouble(3, pojo.getTotalPrice());
                ps.setDouble(4, pojo.getPrice());
                ps.setInt(5, subCount);
                ps.setInt(6, pojo.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 查询所有未删除入库单的基本信息
     * @return
     */
    public List<BaseInOrder> selAllBaseInOrder(){
        return SQLOption.selOption("select id, goodsname, trueincount, totalprice from inorder_tb where isdel = 0", null, null, (rowSet)->{
            List<BaseInOrder> baseInOrders = new ArrayList<>();

            try {
                rowSet.beforeFirst();
                while (rowSet.next()) {
                    BaseInOrder baseInOrder = new BaseInOrder();

                    baseInOrder.setId(rowSet.getInt("id"));
                    baseInOrder.setGoodsName(rowSet.getString("goodsname"));
                    baseInOrder.setTrueCount(rowSet.getInt("trueincount"));
                    baseInOrder.setTotalPrice(rowSet.getDouble("totalprice"));

                    baseInOrders.add(baseInOrder);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                JDBCUtil.close(rowSet);
            }
            return baseInOrders;
        });
    }

    /**
     * 分页查询所有基本入库数据
     */
    public PageInfo<BaseInOrder> selBaseInOrderWithPageIndex(int pageIndex){
        PageInfo<BaseInOrder> pageInfo = new PageInfo<>();
        pageInfo.setPageIndex(pageIndex);

        // 设置页面信息的总的页数
        // 如果总的页面数是0，就查询数据库
        if (pageInfo.getTotalPageCount() == 0) {
            // 设置总的页数
            pageInfo.setTotalPageCount(SQLOption.selOption("select count(*) from inorder_tb where isdel = 0", null, null, (rowSet)->{
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

        return SQLOption.selOption("select * from inorder_tb where isdel = 0 limit ?, ?", pageInfo, (ps, page)->{

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
                    BaseInOrder baseInOrder = new BaseInOrder();

                    baseInOrder.setId(rowSet.getInt("id"));
                    baseInOrder.setGoodsName(rowSet.getString("goodsname"));
                    baseInOrder.setTrueCount(rowSet.getInt("trueincount"));
                    baseInOrder.setTotalPrice(rowSet.getDouble("totalprice"));

                    pageInfo.addPageConent(baseInOrder);
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
     * 通过id来查询入库单基本信息
     * @param order
     * @return
     */
    public BaseInOrder selBaseInOrderById(BaseInOrder order){
        return SQLOption.selOption("select id, goodsname, trueincount, totalprice from inorder_tb where id = ? and isdel = 0", order, (ps, pojo)->{
            try {
                ps.setInt(1, pojo.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }, (rowSet)->{
            BaseInOrder baseInOrder = null;

            if (rowSet.size() != 0) {
                try {
                    baseInOrder = new BaseInOrder();
                    baseInOrder.setId(rowSet.getInt("id"));
                    baseInOrder.setGoodsName(rowSet.getString("goodsname"));
                    baseInOrder.setTrueCount(rowSet.getInt("trueincount"));
                    baseInOrder.setTotalPrice(rowSet.getDouble("totalprice"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return baseInOrder;
        });
    }

    /**
     * 实现详细信息的增加、删除、修改、查询
     */

    /**
     * 增加一条入库单数据
     * @param inorder
     * @return
     */
    public int addInOrder(InOrderInfo inorder){

        return SQLOption.updOption("insert into inorder_tb values(default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", inorder, (ps, pojo)->{
            try {
                ps.setString(1, pojo.getGoodsName());
                ps.setInt(2, pojo.getGoodsNumId());
                ps.setInt(3, pojo.getInCount());
                ps.setInt(4, pojo.getTrueCount());
                ps.setInt(5, pojo.getSubCount());
                ps.setDouble(6, pojo.getPrice());
                ps.setDouble(7, pojo.getTotalPrice());
                ps.setString(8, pojo.getBuy());
                ps.setString(9, pojo.getSale());
                ps.setInt(10, 0);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });
    }

    /**
     * 通过id来删除入库单
     * @param inorder
     * @return
     */
    public int delInOrder(InOrderInfo inorder){
        return SQLOption.updOption("update inorder_tb set isdel = 1 where id = ?", inorder, (ps, pojo)->{
            try {
                ps.setInt(1, pojo.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 通过id来更新数据
     * @param inorder
     * @return
     */
    public int updInOrder(InOrderInfo inorder){
        return SQLOption.updOption("update inorder_tb set goodsname = ?, " +
                "goodsnumid = ?, " +
                "incount = ?, " +
                "trueincount = ?, " +
                "subcount = ?, " +
                "price = ?, " +
                "totalprice = ?, " +
                "buy = ?," +
                "sale = ? where id = ?", inorder, (ps, pojo)->{
            try {
                ps.setString(1, pojo.getGoodsName());
                ps.setInt(2, pojo.getGoodsNumId());
                ps.setInt(3, pojo.getInCount());
                ps.setInt(4, pojo.getTrueCount());
                ps.setInt(5, pojo.getSubCount());
                ps.setDouble(6, pojo.getPrice());
                ps.setDouble(7, pojo.getTotalPrice());
                ps.setString(8, pojo.getBuy());
                ps.setString(9, pojo.getSale());
                ps.setInt(10, pojo.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });
    }

    /**
     * 查询所有入库单
     * @return
     */
    public List<InOrderInfo> selAll(){
        return SQLOption.selOption("select * from inorder_tb where isdel = 0", null, null, (rowSet)->{
            List<InOrderInfo> orders = new ArrayList<>();

            try {
                rowSet.beforeFirst();
                while (rowSet.next()) {
                    InOrderInfo order = new InOrderInfo();

                    order.setId(rowSet.getInt("id"));
                    order.setGoodsName(rowSet.getString("goodsname"));
                    order.setGoodsNumId(rowSet.getInt("goodsnumid"));
                    order.setInCount(rowSet.getInt("incount"));
                    order.setTrueCount(rowSet.getInt("trueincount"));
                    order.setSubCount(rowSet.getInt("subcount"));
                    order.setPrice(rowSet.getDouble("price"));
                    order.setTotalPrice(rowSet.getDouble("totalprice"));
                    order.setBuy(rowSet.getString("buy"));
                    order.setSale(rowSet.getString("sale"));

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
     * 分页查询所有基本入库数据
     */
    public PageInfo<InOrderInfo> selInOrderWithPageIndex(int pageIndex){
        PageInfo<InOrderInfo> pageInfo = new PageInfo<>();
        pageInfo.setPageIndex(pageIndex);

        // 设置页面信息的总的页数
        // 如果总的页面数是0，就查询数据库
        if (pageInfo.getTotalPageCount() == 0) {
            // 设置总的页数
            pageInfo.setTotalPageCount(SQLOption.selOption("select count(*) from inorder_tb where isdel = 0", null, null, (rowSet)->{
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

        return SQLOption.selOption("select * from inorder_tb where isdel = 0 limit ?, ?", pageInfo, (ps, page)->{

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
                    InOrderInfo order = new InOrderInfo();

                    order.setId(rowSet.getInt("id"));
                    order.setGoodsName(rowSet.getString("goodsname"));
                    order.setGoodsNumId(rowSet.getInt("goodsnumid"));
                    order.setInCount(rowSet.getInt("incount"));
                    order.setTrueCount(rowSet.getInt("trueincount"));
                    order.setSubCount(rowSet.getInt("subcount"));
                    order.setPrice(rowSet.getDouble("price"));
                    order.setTotalPrice(rowSet.getDouble("totalprice"));
                    order.setBuy(rowSet.getString("buy"));
                    order.setSale(rowSet.getString("sale"));

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
     * 通过id来查询入库单
     * @param order
     * @return
     */
    public InOrderInfo selById(InOrderInfo order){
        return SQLOption.selOption("select * from inorder_tb where id = ? and isdel = 0", order, (ps, pojo)->{
            try {
                ps.setInt(1, pojo.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }, (rowSet)->{
            InOrderInfo orderInfo = null;

            try {
                orderInfo = new InOrderInfo();
                orderInfo.setId(rowSet.getInt("id"));
                orderInfo.setGoodsName(rowSet.getString("goodsname"));
                orderInfo.setGoodsNumId(rowSet.getInt("goodsnumid"));
                orderInfo.setInCount(rowSet.getInt("incount"));
                orderInfo.setTrueCount(rowSet.getInt("trueincount"));
                orderInfo.setSubCount(rowSet.getInt("subcount"));
                orderInfo.setPrice(rowSet.getDouble("price"));
                orderInfo.setTotalPrice(rowSet.getDouble("totalprice"));
                orderInfo.setBuy(rowSet.getString("buy"));
                orderInfo.setSale(rowSet.getString("sale"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return orderInfo;
        });
    }

    /**
     * 判断入库单是否被删除了
     * @param order
     * @return
     */
    public boolean isDel(InOrderInfo order){
        return SQLOption.selOption("select isdel from inorder_tb where id = ?", order, (ps, pojo)->{
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
