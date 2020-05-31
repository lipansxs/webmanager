package com.sxs.serviceImpl;

import com.sxs.mapper.GoodsNumMapper;
import com.sxs.pojo.GoodsNum;
import com.sxs.pojo.PageInfo;
import com.sxs.serviceInf.GoodsNumServiceInf;

import java.util.List;

public class GoodsNumServiceImpl implements GoodsNumServiceInf {

    private final GoodsNumMapper mapper = new GoodsNumMapper();

    @Override
    public int addGoodsNum(String goodsNum) {
        GoodsNum goodsNum_tmp = new GoodsNum(goodsNum);
        return mapper.add(goodsNum_tmp);
    }

    @Override
    public int delGoodsNum(int id) {
        GoodsNum goodsNum_tmp = new GoodsNum();
        goodsNum_tmp.setId(id);

        return mapper.del(goodsNum_tmp);
    }

    @Override
    public int updGoodsNum(int id, String goodsNum) {
        GoodsNum goodsNum_tmp = new GoodsNum(id, goodsNum);
        return mapper.upd(goodsNum_tmp);
    }

    @Override
    public GoodsNum selGoodsNumByGoodsNum(String goodsNum) {
        return mapper.selByGoodsNum(goodsNum);
    }

    @Override
    public GoodsNum selGoodsNumById(int id) {
        return mapper.selById(id);
    }

    @Override
    public List<GoodsNum> selAllGoodsNum() {
        return mapper.selAll();
    }

    @Override
    public PageInfo<GoodsNum> selWithPageIndex(int pageIndex) {
        return mapper.selWithPageIndex(pageIndex);
    }
}
