package com.CRUD.servlet;

import com.CRUD.dao.UserDao;
import com.CRUD.entity.User;
import com.CRUD.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String opr = req.getParameter("opr");
        if ("in".equals(opr)){
            inLogin(req, resp);
        }else if ("out".equals(opr)){
            outLogin(req, resp);
        }
    }
    //退出登录
    private void outLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        if (user!=null){
            session.removeAttribute("user");
            PrintWriter out = resp.getWriter();
            out.println("<script type='text/javascript'>alert('退出成功');location.href='login.jsp';</script>");
        }
    }
    //判断用户是否存在
    private void inLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String errMsg = "";
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User(username, password);
        List<User> list = new ArrayList<>();

        try {
            if (StringUtils.isNull(username) || StringUtils.isNull(password)){
              throw new RuntimeException("账号或密码不能为空！");
            }
            UserDao userDao = new UserDao();
            //通过username和password查询账号和密码是否存在
            list = userDao.excuteQuery(user);
            if (list == null || list.size()<1){//true=false , true
                throw new RuntimeException("账号或密码错误！");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            errMsg = e.getMessage();
        }
        PrintWriter out = resp.getWriter();
        if ("".equals(errMsg)){
            HttpSession session = req.getSession();
            session.setAttribute("user",list.get(0));
            out.println("<script type='text/javascript'>alert('登录成功');location.href='goodServlet';</script>");
        }else {
            out.println("<script type='text/javascript'>alert('登录失败:"+errMsg+"');location.href='login.jsp';</script>");
        }
    }
}
