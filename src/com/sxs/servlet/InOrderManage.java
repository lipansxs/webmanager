package com.sxs.servlet;

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

@WebServlet(urlPatterns = {"/inorderdetail", "/inorderdetail/sel", "/inorderdetail/upd"})
public class InOrderManage extends HttpServlet {

    private InOrderServiceInf service = null;

    @Override
    public void init() throws ServletException {
        service = new InOrderServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp){

        // 获得uri
        String option = ParseUri.parseOption(req.getRequestURI());

        if (null != option) {
            if ("sel".equals(option)) {
                int id = 0;
                if (null != req.getParameter("id")) {
                    req.setAttribute("selInOrder", service.selInOrderById(selSetAttribute(req)));
                    try {
                        req.getRequestDispatcher("/inorderInfo.jsp").forward(req, resp);
                    } catch (ServletException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
            }else if ("upd".equals(option)) { // 更新操作
                service.updInOrder(updSetAttribute(req));
            }
        }


        req.setAttribute("inorders", service.selAllInOrder());
        try {
            req.getRequestDispatcher("/inorderInfo.jsp").forward(req, resp);
            return;
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 在查询用户时设置属性
     * @param req
     * @return
     */
    private InOrderInfo selSetAttribute(HttpServletRequest req){
        InOrderInfo order = new InOrderInfo();

        int id = 0;
        if (null != req.getParameter("id")) {
            id = Integer.parseInt(req.getParameter("id"));
        }
        order.setId(id);

        return order;
    }

    /**
     *
     * 更新入库单时设置属性
     * @param req
     * @return
     */
    private InOrderInfo updSetAttribute(HttpServletRequest req){
        InOrderInfo order = new InOrderInfo();

        int id = 0;
        if (null != req.getParameter("id") && !"".equals(req.getParameter("id"))) {
            id = Integer.parseInt(req.getParameter("id"));
        }

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

        order.setId(id);
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
}
