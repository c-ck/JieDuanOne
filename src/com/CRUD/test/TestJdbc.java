package com.CRUD.test;

import com.CRUD.dao.GoodsInfoDao;
import com.CRUD.entity.GoodsInfo;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class TestJdbc {
    private GoodsInfoDao goDao;

    @Before
    public void before(){
        goDao = new GoodsInfoDao();
    }

    @Test
    public void test1(){//编号查询
        GoodsInfo goods = new GoodsInfo();
        goods.setId(3);
        List<GoodsInfo> list = goDao.findByGoodsInfo(goods);
        System.out.println(list);

    }
    @Test
    public void test2(){//添加
        GoodsInfo goods = new GoodsInfo();
        Date currentDate = new Date();
        goods.setGoodsInfoName("荣耀9X");
        goods.setGoodsInfoPic("aa.jsp");
        goods.setGoodsInfoPrice(2069);
        goods.setGoodsInfoDescription("4000mAh超强续航 4800万超清夜拍");
        goods.setGoodsStock(3000);
        goods.setFlag("激活");
        goods.setCreated("张三");
        goods.setCreatedDate(currentDate);
        int rows = goDao.addGoods(goods);
        System.out.println(rows);
    }
    @Test
    public void test3(){//删除
        int rows = goDao.deleteGoods(8);
        System.out.println("受影响行数："+rows);
    }
    @Test
    public void test4(){//查询
        GoodsInfo byId = goDao.findById(4);
        System.out.println(byId);
    }
    @Test
    public void test5(){//修改
        GoodsInfo goods = new GoodsInfo();
//        Date currentDate = new Date();
        goods.setGoodsInfoName("荣耀9X");
        goods.setGoodsInfoPic("aa.jsp");
        goods.setGoodsInfoPrice(2069);
        goods.setGoodsInfoDescription("4000mAh超强续航 4800万超清夜拍");
        goods.setGoodsStock(3000);
        goods.setFlag("激活");
        goods.setCreated("张三");
        goods.setId(15);
//        goods.setCreatedDate(currentDate);
        int rows = goDao.updateGoods(goods);
        System.out.println(rows);
    }
}
