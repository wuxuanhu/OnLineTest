package dao;

import com.alibaba.fastjson.JSON;
import pojo.Admin;
import pojo.Score;
import wxh.jdbcutil.JDBCUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyDao {

    //从数据库获得账号密码存入管理员对象
    public ArrayList<Admin> getAdminData(){
        ArrayList<Admin> adminList = new ArrayList<Admin>();

        JDBCUtil.getConnection();//建立连接
        String sql = "select * from admin";//查询登录账号密码
        PreparedStatement pstmt = JDBCUtil.getPreparedStatement(sql);//预处理
        try {
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                adminList.add(new Admin(rs.getString(2),rs.getString(3)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        JDBCUtil.close();

        return adminList;
    }
    //拿到学生表
    public ArrayList<Score> getScoreData(){
        ArrayList<Score> scoreList = new ArrayList<Score>();

        JDBCUtil.getConnection();//建立连接
        String sql = "select * from score where exist = 1";//查询登录账号密码
        PreparedStatement pstmt = JDBCUtil.getPreparedStatement(sql);//预处理

        try {
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                scoreList.add(new Score(rs.getInt(1),rs.getString(2),rs.getInt(3)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        JDBCUtil.close();

        return scoreList;
    }
//注册管理员
    public String registerAdmin(Admin admin){
        Map<String,Object> params = new HashMap<String,Object>();

        JDBCUtil.getConnection();//建立连接
        String sql = "insert into admin values(?,?,?,?,?,?)";
        PreparedStatement pstmt = JDBCUtil.getPreparedStatement(sql);//预处理

        try {
            pstmt.setInt(1,getAdminData().size()+1);//刷新从数据库获得的管理员的数据 同时根据集合的大小添加id
            pstmt.setString(2,admin.getAcc());
            pstmt.setString(3,admin.getPwd());
            pstmt.setString(4,admin.getEmail());
            pstmt.setString(5,admin.getPhone());
            pstmt.setInt(6,1);//初次注册的人都表示存在

            int mark = pstmt.executeUpdate();
            //数据增加成功后修改房间状态
            if(mark>0){
                params.put("mark",true);
            }else {
                params.put("mark",false);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        JDBCUtil.close();

        return JSON.toJSONString(params);
    }
    //添加学生分数
    public int addScore(int id,String name,int  score){
        int mark = 0;

        JDBCUtil.getConnection();//建立连接
        String sql = "insert into score values(?,?,?,1)";
        PreparedStatement pstmt = JDBCUtil.getPreparedStatement(sql);//预处理

        try {
            pstmt.setInt(1,id);//刷新从数据库获得的管理员的数据 同时根据集合的大小添加id
            pstmt.setString(2,name);
            pstmt.setInt(3,score);

            mark = pstmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        JDBCUtil.close();
        return mark;
    }

    //删除学生成绩
    public int deleScore(int dele){
        int mark = 0;

        JDBCUtil.getConnection();//建立连接
        String sql = "update score set exist = '0' where id = ?";//修改住客状态信息
        PreparedStatement pstmt = JDBCUtil.getPreparedStatement(sql);//预处理
        try {
            pstmt.setInt(1,dele);
            mark = pstmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        JDBCUtil.close();

        return mark;
    }
}
