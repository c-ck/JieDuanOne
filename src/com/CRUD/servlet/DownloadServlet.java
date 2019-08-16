package com.CRUD.servlet;


import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author Ivy Li
 * @create 2019-07-12
 */
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、获得下载文件名
        String fileName = req.getParameter("fileName");
        // 2、创建文件输入流
        // 获得文件根目录
        String rootPath = req.getServletContext().getRealPath("/upload");
        File downloadFile = new File(rootPath, fileName);
        if (downloadFile.exists()) {//如果文件存在就下载
            // 2、设置下载文件的打开方式
//            resp.setHeader("content-disposition", "attachment;filename="+fileName);
            fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
            resp.setHeader("content-disposition", "attachment;filename=" + fileName);

            InputStream is = new FileInputStream(downloadFile);
            OutputStream os = resp.getOutputStream();

            IOUtils.copy(is, os);
            os.close();
            is.close();
        } else {
            PrintWriter out = resp.getWriter();
            out.println("<script type='text/javascript'>alert('文件不存在');history.back;</script>");
        }
    }
}
