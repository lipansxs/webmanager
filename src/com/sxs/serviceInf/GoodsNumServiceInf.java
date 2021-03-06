package com.sxs.serviceInf;

import com.sxs.pojo.GoodsNum;
import com.sxs.pojo.PageInfo;

import java.util.List;

public interface GoodsNumServiceInf {

    /**
     * 增加一个货号
     */
    public int addGoodsNum(String goodsNum);

    /**
     * 删除一个货号
     */
    public int delGoodsNum(int id);

    /**
     * 修改一个货号
     */
    public int updGoodsNum(int id, String goodsNum);

    /**
     * 通过货号来查询货号数据
     */
    public GoodsNum selGoodsNumByGoodsNum(String goodsNum);

    /**
     * 通过id来查询货号数据
     */
    public GoodsNum selGoodsNumById(int id);

    /**
     * 查询所有货号
     */
    public List<GoodsNum> selAllGoodsNum();

    /**
     * 通过页面当前页面数来查询
     */
    public PageInfo<GoodsNum> selWithPageIndex(int pageIndex);
}
