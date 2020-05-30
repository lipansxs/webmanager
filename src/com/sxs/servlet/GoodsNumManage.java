package com.sxs.servlet;

import com.sxs.mapper.GoodsNumMapper;
import com.sxs.pojo.GoodsNum;
import com.sxs.serviceImpl.GoodsNumServiceImpl;
import com.sxs.util.ParseUri;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;

@WebServlet(urlPatterns = {"/goodsnum", "/goodsnum/sel", "/goodsnum/add", "/goodsnum/upd", "/goodsnum/del"})
public class GoodsNumManage extends HttpServlet {

    private GoodsNumServiceImpl service = null;

    @Override
    public void init() throws ServletException {
        service = new GoodsNumServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }


    private void processRequest(HttpServletRequest req, HttpServletResponse resp){

        // 获取请求uri，并且解析请求uri的操作
        String option = ParseUri.parseOption(req.getRequestURI());

        // 如果是查询
        if (null != option && "sel".equals(option)) {

            try {
                // 查询分为id查询和货号查询

                // 如果货号不为null就把货号作为查询条件
                String goodsNum = null;
                if (null != req.getParameter("goods-num")) {
                    goodsNum = req.getParameter("goods-num");
                    req.setAttribute("selGoodsNum", service.selGoodsNumByGoodsNum(goodsNum));
                }

                int id = 0;
                if (null != req.getParameter("id")){
                    id = Integer.parseInt(req.getParameter("id"));
                    req.setAttribute("selGoodsNum", service.selGoodsNumById(id));
                }

                req.getRequestDispatcher("/goodsnummanage.jsp").forward(req, resp);
                return;
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if (null != option && "add".equals(option)) { // 增加货号
            if (null != req.getParameter("goods-num")) {
                service.addGoodsNum(req.getParameter("goods-num"));
            }
        }else if (null != option && "del".equals(option)) { // 删除
            if (null != req.getParameter("id")) {
                service.delGoodsNum(Integer.parseInt(req.getParameter("id")));
            }
        }else if (null != option && "upd".equals(option)) {
            if (null != req.getParameter("goods-num") && null != req.getParameter("id")){
                service.updGoodsNum(Integer.parseInt(req.getParameter("id")), req.getParameter("goods-num"));
            }
        }

        // 默认情况下查询所有的货号数据
        req.setAttribute("goodsnums", service.selAllGoodsNum());
        try {
            req.getRequestDispatcher("/goodsnummanage.jsp").forward(req, resp);
            return;
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
