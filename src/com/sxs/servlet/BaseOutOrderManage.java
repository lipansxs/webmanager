package com.sxs.servlet;

import com.sxs.pojo.BaseOutOrder;
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

@WebServlet(urlPatterns = {"/baseoutorder", "/baseoutorder/add", "/baseoutorder/del", "/baseoutorder/upd", "/baseoutorder/sel"})
public class BaseOutOrderManage extends HttpServlet {

    private OutOrderServiceInf service = null;

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

        // 获取请求uri的操作
        String option = ParseUri.parseOption(req.getRequestURI());

        if (null != option) {
            // 查询出库单基本信息
            if ("sel".equals(option)) {
                try {
                    req.setAttribute("selOutOrderBase", selOrder(req));
                    req.getRequestDispatcher("/outorderbase.jsp").forward(req, resp);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }else if ("add".equals(option)) {
                service.addOutOrder(this.addSetAttribute(req));
            }else if ("del".equals(option)) {
                service.delOutOrder(this.delSetAttribute(req));
            }else if ("upd".equals(option)) {
                service.updBaseOutOrder(this.updSetAttribute(req));
            }
        }

        // 默认情况下查询所有出库单基本信息
        req.setAttribute("outOrderBases", service.selAllBaseOutOrder());


        try {
            req.getRequestDispatcher("/outorderbase.jsp").forward(req, resp);
            return;
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询出库单基本信息时的操作
     * @param req
     * @return
     */
    private BaseOutOrder selOrder(HttpServletRequest req){
        BaseOutOrder order = new BaseOutOrder();
        if (null != req.getParameter("id") && !"".equals(req.getParameter("id"))) {
            order.setId(Integer.parseInt(req.getParameter("id")));
            return service.selBaseOutOrderById(order);
        }
        return null;
    }

    /**
     *
     * @param req
     * @return
     */
    private OutOrderInfo addSetAttribute(HttpServletRequest req){
        DateFormat format = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
        OutOrderInfo order = new OutOrderInfo();

        int goodsNumid = Integer.parseInt(req.getParameter("goods-id"));
        int count = Integer.parseInt(req.getParameter("in-count"));
        double price = Double.parseDouble(req.getParameter("goods-price"));
        double taotalPrice = count * price;
        String buy = req.getParameter("goods-buy");
        String sale = req.getParameter("goods-sale");
        String goodsName = req.getParameter("goods-name");

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

    /**
     * 删除出库单之前需要执行的操作
     * @param req
     * @return
     */
    private OutOrderInfo delSetAttribute(HttpServletRequest req){
        OutOrderInfo order = new OutOrderInfo();
        if (null != req.getParameter("id") && !"".equals(req.getParameter("id"))) {
            order.setId(Integer.parseInt(req.getParameter("id")));
            return order;
        }
        return null;
    }

    private BaseOutOrder updSetAttribute(HttpServletRequest req){
        BaseOutOrder order = new BaseOutOrder();

        order.setId(Integer.parseInt(req.getParameter("id")));
        order.setGoodsName(req.getParameter("upd-goods-name"));
        order.setCount(Integer.parseInt(req.getParameter("upd-out-count")));
        order.setTotalPrice(Double.parseDouble(req.getParameter("upd-total-price")));

        return order;
    }
}
