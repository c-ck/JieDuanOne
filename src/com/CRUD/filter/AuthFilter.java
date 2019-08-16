package com.CRUD.filter;

import com.CRUD.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * //过滤器：使用过滤器防止未登录用户访问商品展示页面和商品详情页
 */
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //0.强制转换
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //1.获取资源请求路径
        String requestURI = req.getRequestURI();
        //2.判断是否包含登录相关资源路径,要注意排除掉 css/js/图片/验证码等资源
        boolean flag = true;
        flag = flag && !requestURI.startsWith("/login.jsp");//检测字符串以什么开头
        flag = flag && !requestURI.startsWith("/loginServlet");
        flag = flag && !requestURI.startsWith("/register.jsp");
        flag = flag && !requestURI.startsWith("/registerServlet");
        String errMsg = "";
        if(flag) {
            HttpSession session = req.getSession();
            try {
                //不包含，需要验证用户是否登录
                //3.从获取session中获取user
                User user = (User) session.getAttribute("user");
                if (user == null) {
                    throw new RuntimeException("您尚未登陆，请登录！");
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
                errMsg = e.getMessage();
            }
        }
        if("".equals(errMsg)) {
            //包含，用户就是想登录。放行
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            //没有登录。跳转登录页面
            PrintWriter out = resp.getWriter();
            out.println("<script type='text/javascript'>alert('"+errMsg+"');location.href='login.jsp';</script>");
            out.close();
        }
    }

    @Override
    public void destroy(){

    }
}
