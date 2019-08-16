package com.CRUD.dao;

import com.CRUD.entity.GoodsInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 多条件查询
 */
public class GoodsInfoDao extends UtilDao {

    public List<GoodsInfo> findByGoodsInfo(GoodsInfo goods){
        Connection conn = null;
        PreparedStatement prs = null;
        ResultSet rs = null;
        List<GoodsInfo> list = new ArrayList<>();

        try {
            conn = this.getConnection();
            StringBuffer sf = new StringBuffer();

            sf.append(" SELECT * FROM goodsinfo WHERE 1=1 ");

            List<Object> parList = new ArrayList<>();
            if (goods!=null){
                //编号查询
                if (goods.getId()>0){
                    sf.append(" and id=? ");
                    parList.add(goods.getId());
                }
                //名字查询
                if (goods.getGoodsInfoName()!=null){
                    sf.append(" and goodsInfoName like ? ");
                    parList.add(goods.getGoodsInfoName());
                }
                //价格查询
                if (goods.getGoodsInfoPrice()>0){
                    sf.append(" and goodsInfoPrice=? ");
                    parList.add(goods.getGoodsInfoPrice());
                }
                //库存查询
                if (goods.getGoodsStock()>0){
                    sf.append(" and goodsStock=? ");
                    parList.add(goods.getGoodsStock());
                }
            }

            //获取预编译语句集
            prs = conn.prepareStatement(sf.toString());

            //设置占位符的值
            if (parList!=null && parList.size()>0){
                for (int i = 0; i < parList.size(); i++) {
                    prs.setObject(i+1,parList.get(i));
                }
            }

            //执行sql语句
            rs = prs.executeQuery();
            //遍历结果集
            while (rs.next()){
                GoodsInfo entity = new GoodsInfo();
                entity.setId(rs.getInt("id"));
                entity.setGoodsInfoName(rs.getString("goodsInfoName"));
                entity.setGoodsInfoPic(rs.getString("goodsInfoPic"));
                entity.setGoodsInfoPrice(rs.getInt("goodsInfoPrice"));
                entity.setGoodsInfoDescription(rs.getString("goodsInfoDescription"));
                entity.setGoodsStock(rs.getInt("goodsStock"));
                entity.setFlag(rs.getString("flag"));
                entity.setCreated(rs.getString("created"));
                entity.setCreatedDate(rs.getTimestamp("createdDate"));
                list.add(entity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeAll(conn,prs,rs);
        }
        return list;
    }

    /**
     * 添加
     * @param goods
     * @return
     */
    public int addGoods(GoodsInfo goods){
        String sql = " INSERT INTO  goodsInfo(goodsInfoName,goodsInfoPic,goodsInfoPrice,goodsInfoDescription,goodsStock,flag,created,createdDate) VALUES(?,?,?,?,?,?,?,?) ";
        List<Object> par = new ArrayList<>();
        par.add(goods.getGoodsInfoName());
        par.add(goods.getGoodsInfoPic());
        par.add(goods.getGoodsInfoPrice());
        par.add(goods.getGoodsInfoDescription());
        par.add(goods.getGoodsStock());
        par.add(goods.getFlag());
        par.add(goods.getCreated());
        par.add(goods.getCreatedDate());
        return this.executeUpdate(sql,par);

    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int deleteGoods(int id){
        String sql = " DELETE FROM goodsInfo WHERE id=? ";
        List<Object> par = Arrays.asList(id);
        return this.executeUpdate(sql,par);
    }

    /**
     * 查询单个信息
     * @param id
     * @return
     */
    public GoodsInfo findById(int id){

        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pra = null;

        try {
            // 1、获得连接对象
            conn = this.getConnection();
            // 2、获得预编译语句集并执行SQL语句
            String sql = " select * from goodsInfo where id = ? ";
            // 2.1 获得预编译语句集
            pra = conn.prepareStatement(sql);
            // 2.2 设置占位符的值
            pra.setInt(1, id);
            // 2.3 执行sql语句
            rs = pra.executeQuery();//不要传入sql
            // 2.4 遍历结果集
            // 根据主键查询，所以只会有0或1条记录
            while (rs.next()){
                GoodsInfo info = new GoodsInfo();
                info.setId(rs.getInt("id"));
                info.setGoodsInfoName(rs.getString("goodsInfoName"));
                info.setGoodsInfoPic(rs.getString("goodsInfoPic"));
                info.setGoodsInfoPrice(rs.getInt("goodsInfoPrice"));
                info.setGoodsInfoDescription(rs.getString("goodsInfoDescription"));
                info.setGoodsStock(rs.getInt("goodsStock"));
                info.setFlag(rs.getString("flag"));
                info.setCreated(rs.getString("created"));
                info.setCreatedDate(rs.getTimestamp("createdDate"));
                return info;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 3、关闭资源
            this.closeAll(conn, pra, rs);
        }
        return null;
    }
    /**
     * 商品详情页修改
     * @param goods
     * @return
     */
    public int updateGoods(GoodsInfo goods){
        StringBuffer sf = new StringBuffer();
        sf.append(" UPDATE goodsInfo SET ");
        sf.append(" goodsInfoName=? ");//名称
        sf.append(" ,goodsInfoPic=? ");//图片
        sf.append(" ,goodsInfoPrice=? ");//价格
        sf.append(" ,goodsInfoDescription=? ");//描述
        sf.append(" ,goodsStock=? ");//库存
        sf.append(" ,flag=? ");//状态值
        sf.append(" ,created=? ");//创建人
        sf.append(" WHERE id=? ");//编号

        ArrayList<Object> par = new ArrayList<>();

        par.add(goods.getGoodsInfoName());
        par.add(goods.getGoodsInfoPic());
        par.add(goods.getGoodsInfoPrice());
        par.add(goods.getGoodsInfoDescription());
        par.add(goods.getGoodsStock());
        par.add(goods.getFlag());
        par.add(goods.getCreated());
        par.add(goods.getId());
        return this.executeUpdate(sf.toString(),par);
    }

    /**
     * 商品修改
     * @param goods
     * @return
     */
    public int updateGoodsD(GoodsInfo goods){
        StringBuffer sf = new StringBuffer();
        sf.append(" UPDATE goodsInfo SET ");
        sf.append(" goodsInfoName=? ");//名称
        sf.append(" ,goodsInfoPic=? ");//图片
        sf.append(" ,goodsInfoPrice=? ");//价格
        sf.append(" WHERE id=? ");//编号

        ArrayList<Object> par = new ArrayList<>();

        par.add(goods.getGoodsInfoName());
        par.add(goods.getGoodsInfoPic());
        par.add(goods.getGoodsInfoPrice());
        par.add(goods.getId());
        return this.executeUpdate(sf.toString(),par);
    }


}
