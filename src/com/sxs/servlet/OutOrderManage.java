package com.sxs.servlet;

import com.sxs.pojo.OutOrderInfo;
import com.sxs.serviceImpl.OutOrderServiceImpl;
import com.sxs.serviceInf.OutOrderServiceInf;
import com.sxs.util.ParseUri;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("all")
@WebServlet(urlPatterns = {"/outorderdetail", "/outorderdetail/sel", "/outorderdetail/upd"})
public class OutOrderManage extends HttpServlet {

    OutOrderServiceInf service = null;

    @Override
    public void init() throws ServletException {
        service = new OutOrderServiceImpl();
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

        String option = ParseUri.parseOption(req.getRequestURI());

        if (null != option) {
            if ("sel".equals(option)) {
                req.setAttribute("selOutOrder", service.selOutOrderById(this.selSetAttribute(req)));
                try {
                    req.getRequestDispatcher("/outorderInfo.jsp").forward(req, resp);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }else if ("upd".equals(option)) {
                service.updOutOrder(this.updSetAttribute(req));
            }
        }

        // 默认情况下查询所有的出库单详情
//        req.setAttribute("outOrders", service.selAllOutOrder());

        int pageIndex = 1;
        if (null != req.getParameter("pageIndex")) {
            pageIndex = (int)Double.parseDouble(req.getParameter("pageIndex"));
        }
        req.setAttribute("pageInfo", service.selOutOrderWithPageIndex(pageIndex));

        try {
            req.getRequestDispatcher("/outorderInfo.jsp").forward(req, resp);
            return;
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 在查找前需要执行的操作
     * @param req
     * @return
     */
    private OutOrderInfo selSetAttribute(HttpServletRequest req){
        OutOrderInfo order = new OutOrderInfo();

        if (null != req.getParameter("id") && !"".equals(req.getParameter("id"))) {
            order.setId(Integer.parseInt(req.getParameter("id")));
            return order;
        }
        return null;
    }

    private OutOrderInfo updSetAttribute(HttpServletRequest req){
        DateFormat format = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
        OutOrderInfo order = new OutOrderInfo();

        int id = Integer.parseInt(req.getParameter("id"));
        int goodsNumid = Integer.parseInt(req.getParameter("upd-goods-id"));
        int count = Integer.parseInt(req.getParameter("upd-in-count"));
        double price = Double.parseDouble(req.getParameter("upd-goods-price"));
        double taotalPrice = count * price;
        String buy = req.getParameter("upd-goods-buy");
        String sale = req.getParameter("upd-goods-sale");
        String goodsName = req.getParameter("upd-goods-name");

        order.setId(id);
        order.setGoodsNumId(goodsNumid);
        order.setCount(count);
        order.setPrice(price);
        order.setTotalPrice(taotalPrice);
        order.setBuy(buy);
        order.setSale(sale);
        order.setTime(format.format(new Date()));
        order.setGoodsName(goodsName);

        return order;

    }

}
