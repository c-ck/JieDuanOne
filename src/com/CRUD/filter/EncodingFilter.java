package com.CRUD.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private String econding;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        econding = filterConfig.getInitParameter("en");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(econding);
        servletResponse.setCharacterEncoding(econding);
        servletResponse.setContentType("text/html;charset="+econding);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
