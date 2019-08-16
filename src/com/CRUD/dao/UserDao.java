package com.CRUD.dao;

import com.CRUD.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends UtilDao {
    //多条件查询
    public List<User> excuteQuery(User user){
        Connection conn = null;
        PreparedStatement prs = null;
        ResultSet rs = null;
        ArrayList<User> list = new ArrayList<>();

        try {
            conn = this.getConnection();
            StringBuffer sf = new StringBuffer();
            ArrayList<Object> paramList = new ArrayList<>();
            sf.append(" SELECT * FROM user WHERE 1=1 ");
            if (user!=null){
                if(user.getId()!=0){
                    sf.append(" and id = ? ");
                    paramList.add(user.getId());
                }
                if (user.getUsername()!=null){
                    sf.append(" and username=? ");
                    paramList.add(user.getUsername());
                }
                if (user.getPassword()!=null){
                    sf.append(" and password=? ");
                    paramList.add(user.getPassword());
                }
                if(user.getSex()!=null){
                    sf.append(" and sex = ? ");
                    paramList.add(user.getSex());
                }
                if(user.getHobbys()!=null){
                    sf.append(" and hobbys = ? ");
                    paramList.add(user.getHobbys());
                }
                if(user.getPhone()!=null){
                    sf.append(" and phone = ? ");
                    paramList.add(user.getPhone());
                }
                if(user.getEmail()!=null){
                    sf.append(" and email = ? ");
                    paramList.add(user.getEmail());
                }
                if(user.getAddrs()!=null){
                    sf.append(" and addrs = ? ");
                    paramList.add(user.getAddrs());
                }
                if(user.getFlag()!=null){
                    sf.append(" and flag = ? ");
                    paramList.add(user.getFlag());
                }
            }
            String sql = sf.toString();
            prs = conn.prepareStatement(sql);
            if (paramList!=null && paramList.size()>0){
                for (int i = 0; i < paramList.size(); i++) {
                    prs.setObject(i+1,paramList.get(i));
                }
            }
            rs = prs.executeQuery();
            while (rs.next()){
                User entity = new User();
                entity.setId(rs.getInt("id"));
                entity.setUsername(rs.getString("username"));
                entity.setPassword(rs.getString("password"));
                entity.setSex(rs.getString("sex"));
                entity.setHobbys(rs.getString("hobbys"));
                entity.setPhone(rs.getString("phone"));
                entity.setEmail(rs.getString("email"));
                entity.setAddrs(rs.getString("addrs"));
                entity.setFlag(rs.getString("flag"));
                list.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll(conn,prs,rs);
        }
        return list;
    }
    //添加
    public int insertUser(User user){
        String sql = " INSERT INTO  user(username,password,sex,hobbys,phone,email,addrs) VALUES(?,?,?,?,?,?,?) ";
        ArrayList<Object> paramList = new ArrayList<>();
        paramList.add(user.getUsername());
        paramList.add(user.getPassword());
        paramList.add(user.getSex());
        paramList.add(user.getHobbys());
        paramList.add(user.getPhone());
        paramList.add(user.getEmail());
        paramList.add(user.getAddrs());
        return this.executeUpdate(sql,paramList);
    }

}
