package com.CRUD.servlet;

import com.CRUD.dao.GoodsInfoDao;
import com.CRUD.entity.GoodsInfo;
import com.CRUD.utils.StringUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;

public class GoodServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String opr = req.getParameter("opr");
        if ("add".equals(opr)){
            add(req, resp);
        }else if ("loadData".equals(opr)){
            loadData(req, resp);
        }
        else if ("update".equals(opr)){
            update(req, resp);
        }
        else if ("delete".equals(opr)){
            delete(req, resp);
        }
        else if ("view".equals(opr)){
            view(req, resp);
        }
        else {
            list(req,resp);
        }
    }
    //修改
    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String errMsg = "";
        int rows = 0;
        String id = req.getParameter("id");
        String name = req.getParameter("name");

        try {
            GoodsInfo goods = this.uploadFile(req);
            goods.setId(Integer.parseInt(id));
            GoodsInfoDao goodsInfoDao = new GoodsInfoDao();
            if ("update".equals(name)){
                rows = goodsInfoDao.updateGoodsD(goods);
            }else {
                rows = goodsInfoDao.updateGoods(goods);
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
            errMsg = e.getMessage();
        }
        PrintWriter out = resp.getWriter();
        if (rows>0){
            if ("update".equals(name)){
                out.println("<script type='text/javascript'>alert('保存成功');location.href='goodServlet';</script>");
            }else if ("informationUpdate".equals(name)){
                out.println("<script type='text/javascript'>alert('保存成功');location.href='goodServlet?opr=loadData&id="+id+"&name=information';</script>");
            }
        }else {
            out.println("<script type='text/javascript'>alert('保存失败："+errMsg+"');history.back();</script>");
        }
    }
    //商品数据加载
    private void loadData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String errMsg = "";
        try {
            int id = StringUtils.strInt(req.getParameter("id"),0);
            req.setAttribute("id",id);
            if (id == 0){
                throw new RuntimeException("商品编号不能为空");
            }
            GoodsInfoDao goodsInfoDao = new GoodsInfoDao();
            GoodsInfo good = new GoodsInfo();
            good.setId(id);
            List<GoodsInfo> list = goodsInfoDao.findByGoodsInfo(good);
            if (list==null && list.size()<1){
                throw new RuntimeException("该商品编号找不到");
            }
            req.setAttribute("gs",list.get(0));
        } catch (RuntimeException e) {
            e.printStackTrace();
            errMsg=e.getMessage();
        }
        if ("".equals(errMsg)){
            String name = req.getParameter("name");
            req.setAttribute("name",name);
            if ("update".equals(name)){
                req.getRequestDispatcher("good_update.jsp").forward(req,resp);
            }else if ("information".equals(name)){
                req.getRequestDispatcher("good_view.jsp").forward(req,resp);
            }else if ("informationUpdate".equals(name)){
                req.getRequestDispatcher("good_update.jsp").forward(req,resp);
            }
        }else {
            PrintWriter out = resp.getWriter();
            out.println("<script type='text/javascript'>alert('数据加载失败：'\"+errMsg+\");history.back();</script>");
        }
    }
    //商品详情
    private void view(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GoodsInfoDao goDao = new GoodsInfoDao();
        String id = req.getParameter("id");

        GoodsInfo byId = goDao.findById(Integer.parseInt(id));
        req.setAttribute("gs",byId);
        req.getRequestDispatcher("good_view.jsp").forward(req,resp);
    }
    //删除
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String errMsg = "";
        int rows = 0;
        try {
            int id = StringUtils.strInt(req.getParameter("id"),0);
            if (id == 0){
                throw new RuntimeException("学号不能为空");
            }
            GoodsInfoDao goodDao = new GoodsInfoDao();
            rows = goodDao.deleteGoods(id);
        } catch (RuntimeException e) {
            e.printStackTrace();
            errMsg=e.getMessage();
        }
        //判断受影响的行数
        PrintWriter out = resp.getWriter();
        if(rows>0){
            // 表示修改成功
            out.println("<script type='text/javascript'>alert('删除成功');location.href='goodServlet';</script>");
        }else{
            // 修改失败
            out.println("<script type='text/javascript'>alert('删除失败："+errMsg+"');history.back();</script>");
        }
    }
    //添加
    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String errMsg = "";
        int rows = 0;
        try {
            GoodsInfo goods = this.uploadFile(req);
            GoodsInfoDao goDao = new GoodsInfoDao();
            
            rows = goDao.addGoods(goods);
        } catch (Exception e) {
            e.printStackTrace();
            errMsg = e.getMessage();
        }
        //判断受影响行数
        PrintWriter out = resp.getWriter();
        if (rows>0){
            // 表示修改成功
            out.println("<script type='text/javascript'>alert('保存成功');location.href='goodServlet';</script>");
        }
        else {
            // 修改失败
            out.println("<script type='text/javascript'>alert('保存失败："+errMsg+"');history.back();</script>");
        }
    }

    //上传文件表单
    public GoodsInfo uploadFile(HttpServletRequest req) throws FileUploadException, IOException {
        
        GoodsInfo goods = new GoodsInfo();
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> fileItemList = upload.parseRequest(req);
        if (fileItemList!=null && fileItemList.size()>0){
            GoodsInfoDao goodsInfoDao = new GoodsInfoDao();
            for (FileItem fileItem : fileItemList){
                if (fileItem.isFormField()){
                    if ("goodsInfoName".equals(fileItem.getFieldName())){
                        String goodsInfoName = fileItem.getString("utf-8");
                        if (!StringUtils.isNotNull(goodsInfoName)){
                            throw new RuntimeException("商品名称不能为空");
                        }
                        goods.setGoodsInfoName(goodsInfoName);
                    }else if ("goodsInfoPic".equals(fileItem.getString("utf-8"))){
                        goods.setGoodsInfoPic(fileItem.getString("utf-8"));
                    }else if ("goodsInfoPrice".equals(fileItem.getFieldName())){
                        String goodsInfoPrice = fileItem.getString();
                        if (!StringUtils.isNotNull(goodsInfoPrice)){
                            throw new RuntimeException("商品价格不能为空");
                        }
                        goods.setGoodsInfoPrice(Integer.parseInt(goodsInfoPrice));
                    }else if ("goodsInfoDescription".equals(fileItem.getFieldName())){
                        goods.setGoodsInfoDescription(fileItem.getString("utf-8"));
                    }else if ("goodsStock".equals(fileItem.getFieldName())){
                        String goodsStock =fileItem.getString();
                        if (!StringUtils.isNotNull(goodsStock)){
                            goods.setGoodsStock(0);
                        }else {
                            goods.setGoodsStock(Integer.parseInt(goodsStock));
                        }
                    }else if ("flag".equals(fileItem.getFieldName())){
                        goods.setFlag(fileItem.getString("utf-8"));
                    }else if ("created".equals(fileItem.getFieldName())){
                        goods.setCreated(fileItem.getString("utf-8"));
                    }else if ("createdDate".equals(fileItem.getFieldName())){
                        Date date = new Date();

                        goods.setCreatedDate(date);
                    }
                }else {

                    String fileName = fileItem.getName();
                    if (StringUtils.isNotNull(fileName)){
                        String parentPath = req.getServletContext().getRealPath("/upload");
                        //判断/upload文件夹是否存在，不存在则创建
                        File parentFile = new File(parentPath);
                        if (!parentFile.exists()){//exists存在
                            parentFile.mkdirs();//mkdirs建立一个新的子目录（DOS命令）
                        }
                        File newFile = new File(parentFile, fileName);
                        InputStream is = fileItem.getInputStream();
                        //创建一个本地字节输出流FileOutputStream对象，构造方法中绑定要输出的目的地
                        FileOutputStream os = new FileOutputStream(newFile);
                        IOUtils.copy(is,os);
                        os.close();
                        is.close();
                        goods.setGoodsInfoPic(fileName);
                    }
                }
            }
        }else {
            throw new RuntimeException("数据加载失败");
        }
        return goods;
    }

    //查看商品表
    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        GoodsInfoDao goodsDao = new GoodsInfoDao();
        List<GoodsInfo> list = goodsDao.findByGoodsInfo(null);
        req.setAttribute("list",list);
        req.getRequestDispatcher("good_list.jsp").forward(req,resp);
    }


}
