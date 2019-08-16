package com.CRUD.servlet;

import com.CRUD.dao.UserDao;
import com.CRUD.entity.User;
import com.CRUD.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String rePassword = req.getParameter("rePassword");
        String sex = req.getParameter("sex");
        String hobbys = req.getParameter("hobbys");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String addrs = req.getParameter("addrs");
        String errMsg = "";
        int rows = 0;

        try {
            if (StringUtils.isNull(username)){
                throw new RuntimeException("账号不能为空！");
            }
            UserDao userDao = new UserDao();
            User user = new User(username);
            List<User> list = userDao.excuteQuery(user);
            if (list.size()>0){
                throw new RuntimeException("账号已存在！");
            }
            if (StringUtils.isNull(password) || StringUtils.isNull(rePassword)){
                throw new RuntimeException("密码不能为空！");
            }
            if (!password.equals(rePassword)){
                throw new RuntimeException("密码与确认密码不一致！");
            }

            user = new User(username, password, sex, hobbys, phone, email, addrs);
            System.out.println(user);
            rows = userDao.insertUser(user);
        } catch (RuntimeException e) {
            e.printStackTrace();
            errMsg = e.getMessage();
        }
        PrintWriter out = resp.getWriter();
        if (rows>0){
            out.println("<script type='text/javascript'>alert('注册成功');location.href='login.jsp';</script>");
        }else {
            out.println("<script type='text/javascript'>alert('注册失败："+errMsg+"');history.back();</script>");
        }
    }
}
