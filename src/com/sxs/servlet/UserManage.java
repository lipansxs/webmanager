package com.sxs.servlet;

import com.sxs.pojo.User;
import com.sxs.serviceImpl.UserServiceImpl;
import com.sxs.serviceInf.UserServiceInf;
import com.sxs.util.ParseUri;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("all")
@WebServlet(name = "UserManage", urlPatterns = {"/usermanage", "/usermanage/sel", "/usermanage/add", "/usermanage/del", "/usermanage/upd"})
public class UserManage extends HttpServlet {

    private UserServiceInf userService = null;
    private Matcher m = null;
    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
        m = Pattern.compile("/([^/]*?)$").matcher("");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp){

        // 如果没有参数就查询所有用户
        // sel?key=name & name = XXXX 通过用户名查询
        // sel?key=id & id = XXX 通过id查询
        // add?name=XXXX&pwd=XXX 增加用户
        // del?id=? 通过id删除用户
        // upd?id=XXX&name=XXX&pwd=XXX

        // 获取请求的uri 如：usernamage/add

        // 获取请求uri
        // 解析请求uri
        String option = ParseUri.parseOption(req.getRequestURI());


        /**
         * 增加用户
         */
        if (null != option && "add".equals(option)) {

            // 添加用户
            String name = req.getParameter("name");
            String pwd = req.getParameter("pwd");
            this.addUser(name, pwd);

        }else if (null != option && "del".equals(option)) { // 删除

            // 删除用户
            this.delUser(Integer.parseInt(req.getParameter("id")));
        }else if (null != option && "upd".equals(option)) { // 更新
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String pwd = req.getParameter("pwd");

            this.updUser(id, name, pwd);

        }else if (null != option && "sel".equals(option)){ // 查找用户

            // 查找的用户名
            String name = req.getParameter("name");

            // id
            int id = 0;
            if (null != req.getParameter("id")) {
                id = Integer.parseInt(req.getParameter("id"));
            }

            // 设置属性
            req.setAttribute("selUser", this.selUser(name, id));
            try {
                req.getRequestDispatcher("/usermanage.jsp").forward(req, resp);
                return;
            } catch (IOException | ServletException e) {
                e.printStackTrace();
            }
        }

//        req.setAttribute("users", userService.selAll());
        // 默认显示第一页的数据
        int pageIndex = 1;
        if (null != req.getParameter("pageIndex")) {
            pageIndex = (int)Double.parseDouble(req.getParameter("pageIndex"));
        }
        req.setAttribute("pageInfo", userService.selWithPageIndex(pageIndex));

        try {
            req.getRequestDispatcher("/usermanage.jsp").forward(req, resp);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }

        return;
    }

    /**
     * 添加用户操作
     * @param user
     */
    private void addUser(String name, String pwd){
        userService.add(new User(name, pwd));
    }

    /**
     * 删除用户
     * @param id
     */
    public void delUser(int id){
        User user = new User();
        user.setId(id);
        userService.del(user);
    }

    /**
     * 查询某一个用户
     * @param name
     * @param id
     * @return
     */
    public User selUser(String name, int id){
        User user = new User();

        user.setName(name);
        user.setId(id);

        return userService.sel(user);
    }

    public void updUser(int id, String name, String pwd){
        User user = new User(id, name, pwd);
        userService.update(user);
    }
}
