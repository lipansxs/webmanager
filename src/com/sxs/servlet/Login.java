package com.sxs.servlet;

import com.sxs.constant.Constant;
import com.sxs.pojo.User;
import com.sxs.serviceImpl.UserServiceImpl;
import com.sxs.serviceInf.UserServiceInf;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "Login", urlPatterns = "/login")
public class Login extends HttpServlet {

    /**
     *
     * 数据库访问服务对象
     */
    private UserServiceInf userService = null;

    private Logger logger = null;


    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
        logger = Logger.getLogger(Login.class);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = new User(request.getParameter("username"), request.getParameter("password"));

        logger.info(user.getName() + "正在登入");

        int loginResult = userService.login(user);

        if (loginResult == Constant.LOGIN_SUCCESS){
            logger.info(user.getName() + "登入成功！");

            request.getSession().setAttribute("logging", user.getName());
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        request.getRequestDispatcher("WEB-INF/error/LogginError.jsp?loginresult=" + loginResult).forward(request, response);
//        response.sendRedirect( "WEB-INF/error/LogginError.jsp?loginresult=" + loginResult);
    }

}
