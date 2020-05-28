package com.sxs.servlet;

import com.sun.jdi.IntegerType;
import com.sxs.pojo.BaseInOrder;
import com.sxs.pojo.InOrderInfo;
import com.sxs.serviceImpl.InOrderServiceImpl;
import com.sxs.serviceInf.InOrderServiceInf;
import com.sxs.util.ParseUri;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/baseinorder", "/baseinorder/add", "/baseinorder/del", "/baseinorder/upd", "/baseinorder/sel"})
public class BaseInOrderManage extends HttpServlet {

    private InOrderServiceInf service = null;

    @Override
    public void init() throws ServletException {
        service = new InOrderServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp){

        // 获取请求uri
        String option = ParseUri.parseOption(req.getRequestURI());

        if (null != option) {
            if ("sel".equals(option)) { // 查询
                if (null != req.getParameter("id")) {
                    // 将查询结果设置为请求的属性
                    req.setAttribute("selInOrderBase", service.selBaseInOrderById(new BaseInOrder(Integer.parseInt(req.getParameter("id")))));
                    // 将请求转发
                    try {
                        req.getRequestDispatcher("/inorderbase.jsp").forward(req, resp);
                        return;
                    } catch (ServletException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }else if ("add".equals(option)) { // 添加入库单数据
                service.addInOrder(addSetAttribute(req, resp));
            }else if ("upd".equals(option)) { // 更新入库单基本数据
                service.updBaseInOrderById(updSetAttribute(req, resp));
            }else if ("del".equals(option)) {
                service.delInOrder(delSetAttribute(req));
            }
        }

        // 默认情况下查询所有基本信息
        req.setAttribute("inorderBases", service.selAllBaseInOrder());


        try {
            req.getRequestDispatcher("/inorderbase.jsp").forward(req, resp);
            return;
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置对象属性
     * @param req
     * @param resp
     * @return
     */
    private InOrderInfo addSetAttribute(HttpServletRequest req, HttpServletResponse resp){
        InOrderInfo order = new InOrderInfo();

        String goodsName = null;
        if (null != req.getParameter("goods-name")) { // 商品名
            goodsName = req.getParameter("goods-name");
        }

        int goodsNumId = 0;
        if (null != req.getParameter("goods-id")) { // 货号id
            goodsNumId = Integer.parseInt(req.getParameter("goods-id"));
        }

        int inCount = 0;
        if (null != req.getParameter("in-count")) { //需要进货量
            inCount = Integer.parseInt(req.getParameter("in-count"));
        }

        int trueInCount = 0;
        if (null != req.getParameter("true-in-count")) { // 实际进货量
            trueInCount = Integer.parseInt(req.getParameter("true-in-count"));
        }

        int subCount = inCount - trueInCount; // 进货差量

        double price = 0;
        if (null != req.getParameter("goods-price")) { // 商品单价
            price = Double.parseDouble(req.getParameter("goods-price"));
        }

        double totalPrice = price * trueInCount; // 总价

        String buy = "";
        if (null != req.getParameter("goods-buy")) {
            buy = req.getParameter("goods-buy");
        }

        String sale = "";
        if (null != req.getParameter("goods-sale")) {
            sale = req.getParameter("goods-sale");
        }

        order.setGoodsName(goodsName); // 商品名
        order.setGoodsNumId(goodsNumId);  // 货号id
        order.setInCount(inCount);  // 需要进货量
        order.setTrueCount(trueInCount); // 实际进货量
        order.setSubCount(subCount);    // 进货差量
        order.setPrice(price); // 单价
        order.setTotalPrice(totalPrice); // 总价
        order.setBuy(buy); // 买方
        order.setSale(sale); // 卖方

        return order;
    }

    /**
     * 在更新的时候设置属性
     * @param req
     * @param resp
     * @return
     */
    private BaseInOrder updSetAttribute(HttpServletRequest req, HttpServletResponse resp){
        BaseInOrder order = new BaseInOrder();

        int id = 0;
        if (null != req.getParameter("id")) {
            id = Integer.parseInt(req.getParameter("id"));
        }

        String goodsName = "";
        if (null != req.getParameter("goods-name")) {
            goodsName = req.getParameter("goods-name");
        }

        int trueInCount = 0;
        if (null != req.getParameter("upd-true-in-count")) {
            trueInCount = Integer.parseInt(req.getParameter("upd-true-in-count"));
        }

        double totalPrice = 0;
        if (null != req.getParameter("upd-total-price")) {
            totalPrice = Double.parseDouble(req.getParameter("upd-total-price"));
        }

        order.setId(id);
        order.setGoodsName(goodsName);
        order.setTrueCount(trueInCount);
        order.setTotalPrice(totalPrice);

        return order;
    }

    private InOrderInfo delSetAttribute(HttpServletRequest req){
        InOrderInfo order = new InOrderInfo();

        int id = 0;
        if (null != req.getParameter("id")) {
            id = Integer.parseInt(req.getParameter("id"));
        }
        order.setId(id);
        return order;
    }
}
