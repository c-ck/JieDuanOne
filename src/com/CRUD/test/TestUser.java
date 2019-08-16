package com.CRUD.test;

import com.CRUD.dao.UserDao;
import com.CRUD.entity.User;
import org.junit.Test;

import java.util.List;

public class TestUser {

        UserDao userDao = new UserDao();

   @Test
    public void testExcuteQ(){
       User user = new User("admin","123456");
       List<User> list = userDao.excuteQuery(user);
       System.out.println(list.get(0));
       System.out.println(list);
   }
   @Test
   public void testExcuteQ2(){
       User user = new User("admin");
       List<User> list = userDao.excuteQuery(user);
       System.out.println(list.get(0));
       System.out.println(list);
   }
    @Test
    public void testadd(){//添加
        User user = new User("张三","123456","男","看电视","15477777778","admin@qq.com","上海");
        int rows = userDao.insertUser(user);
        System.out.println(rows);
    }
}
