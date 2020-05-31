package com.sxs.mapper;

import com.sxs.pojo.GoodsNum;
import com.sxs.pojo.PageInfo;
import com.sxs.util.JDBCUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsNumMapper {

    /**
     * 增删改查
     */

    /**
     * 增加货号
     * @param goodsNum
     * @return
     */
    public int add(GoodsNum goodsNum){
        return SQLOption.updOption("insert into goodsnum_tb values(default, ?, ?)", goodsNum, (ps, pojo)->{
            try {
                ps.setString(1, pojo.getGoodsNum());
                ps.setInt(2, 0);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });
    }

    /**
     * 通过id来删除货号
     * @param goodsNum
     * @return
     */
    public int del(GoodsNum goodsNum){
        return SQLOption.updOption("update goodsnum_tb set isdel = 1 where id = ?", goodsNum, (ps, pojo)->{
            try {
                ps.setInt(1, pojo.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 通过id来更新货号
     * @param goodsnum
     * @return
     */
    public int upd(GoodsNum goodsnum) {
        return SQLOption.updOption("update goodsnum_tb set goodsnum=? where id = ?", goodsnum, (ps, pojo)->{
            try {
                ps.setString(1, pojo.getGoodsNum());
                ps.setInt(2, pojo.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 查询所有货号
     * @return
     */
    public List<GoodsNum> selAll(){

        return SQLOption.selOption("select * from goodsnum_tb where isdel = 0", null, null,(rowSet)->{
            List<GoodsNum> goodsNums = new ArrayList<>();

            try {
                // 把光标移动到第一行之前
                rowSet.beforeFirst();

                // 循环获取全部的货号
                while (rowSet.next()) {
                    GoodsNum goodsNum = new GoodsNum();
                    goodsNum.setId(rowSet.getInt("id"));
                    goodsNum.setGoodsNum(rowSet.getString("goodsnum"));

                    goodsNums.add(goodsNum);
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }

            return goodsNums;
        });


    }

    /**
     * 通过货号查询
     * @param goodsNum
     * @return
     */
    public GoodsNum selByGoodsNum(String goodsNum){
        return SQLOption.selOption("select * from goodsnum_tb where goodsnum = ? and isdel = 0", goodsNum, (ps, obj)->{
            try {
                ps.setString(1, obj);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }, (rowSet)->{
            GoodsNum goodsNum_obj = null;
            if (rowSet.size() > 0){

               try {
                   goodsNum_obj = new GoodsNum(rowSet.getInt("id"), rowSet.getString("goodsnum"));
               } catch (SQLException e) {
                   e.printStackTrace();
               }finally {
                   JDBCUtil.close(rowSet);
               }
           }
            // 最后返回这个货号对象
            return goodsNum_obj;
        });
    }


    /**
     * 通过id来查询
     * @param id
     * @return
     */
    public GoodsNum selById(int id){
        return SQLOption.selOption("select * from goodsnum_tb where id = ? and isdel = 0", id, (ps, obj)->{
            try {
                ps.setObject(1, obj);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }, (rowSet)->{
            GoodsNum goodsNum = null;
            try {
                goodsNum = new GoodsNum(rowSet.getInt("id"), rowSet.getString("goodsnum"));
            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
                JDBCUtil.close(rowSet);
            }
            return goodsNum;
        });
    }


    /**
     * 通过id判断货号是否被删除了，已经被删除了返回true，没有被删除返回false
     * @param goodsNum
     * @return
     */
    public boolean isDel(GoodsNum goodsNum){

        return SQLOption.selOption("select isdel from goodsnum_tb where id = ?", goodsNum, (ps, goodsNum_tmp)->{
            try {
                // 设置sql的参数
                ps.setInt(1, goodsNum_tmp.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }, (rowSet)->{
            // 设置需要返回什么值
            boolean isDel = false;

            try {
                if (rowSet.getInt("isDel") != 0){
                    isDel = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                JDBCUtil.close(rowSet);
            }

            return isDel;
        });

    }

    /**
     * 通过分页查询数据,默认每页显示5条数据
     */
    public PageInfo<GoodsNum> selWithPageIndex(int pageIndex){
        PageInfo<GoodsNum> pageInfo = new PageInfo<>();
        pageInfo.setPageIndex(pageIndex);

        // 设置页面信息的总的页数
        // 如果总的页面数是0，就查询数据库
        if (pageInfo.getTotalPageCount() == 0) {
            pageInfo.setTotalPageCount(SQLOption.selOption("select count(*) from goodsnum_tb", null, null, (rowSet)->{
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

        return SQLOption.selOption("select * from goodsnum_tb limit ?, ?", pageInfo, (ps, page)->{

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
                     GoodsNum goodsNum = new GoodsNum();
                     goodsNum.setId(rowSet.getInt("id"));
                     goodsNum.setGoodsNum(rowSet.getString("goodsnum"));
                     pageInfo.addPageConent(goodsNum);
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
