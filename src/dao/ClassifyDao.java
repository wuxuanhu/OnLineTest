package dao;

import com.alibaba.fastjson.JSON;
import pojo.Admin;
import pojo.ChangeClassify;
import pojo.Question;
import wxh.jdbcutil.JDBCUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ClassifyDao {

    //添加分类名称
    public int addClassify(String name){

        int mark = 0;
        JDBCUtil.getConnection();//建立连接
        String sql = "insert into classify values(?,?,?,now())";
        PreparedStatement pstmt = JDBCUtil.getPreparedStatement(sql);//预处理

        try {
            pstmt.setString(1, String.valueOf(UUID.randomUUID()));//添加随机id
            pstmt.setString(2,name);
            pstmt.setInt(3,1);//初次创建的分类都表示存在

            mark = pstmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        JDBCUtil.close();

        return mark;
    }
    //获取分类表所有数据
    public ArrayList<String> getClassify(){
        ArrayList<String> adminList = new ArrayList<String>();

        JDBCUtil.getConnection();//建立连接
        String sql = "select * from classify where exist = 1 order by time desc";//查询存在分类
        PreparedStatement pstmt = JDBCUtil.getPreparedStatement(sql);//预处理
        try {
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                adminList.add(rs.getString(2));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        JDBCUtil.close();

        return adminList;
    }
    //修改分类的数据
    public String changeClassify(ChangeClassify ccy){
        Map<String,Object> params = new HashMap<String,Object>();

        JDBCUtil.getConnection();//建立连接
        String sql = "update classify set name = ? where name = ? and exist = 1";//修改住客状态信息
        PreparedStatement pstmt = JDBCUtil.getPreparedStatement(sql);//预处理
        try {
            System.out.println(ccy.getNewname());
            System.out.println(ccy.getOldname());
            pstmt.setString(1,ccy.getNewname());
            pstmt.setString(2,ccy.getOldname());

            int mark = pstmt.executeUpdate();
            System.out.println(mark);
            if(mark>0){
                params.put("mark",true);
            }else {
                params.put("mark",false);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        JDBCUtil.close();
        System.out.println("b"+JSON.toJSONString(params));
        return JSON.toJSONString(params);
    }
    //删除分类
    public String deleClassify(String dele){
        Map<String,Object> params = new HashMap<String,Object>();

        JDBCUtil.getConnection();//建立连接
        String sql = "update classify set exist = '0' where name = ?";//修改分类exist为0  代表删除
        PreparedStatement pstmt = JDBCUtil.getPreparedStatement(sql);//预处理
        try {
            pstmt.setString(1,dele);
            int mark = pstmt.executeUpdate();
            System.out.println(mark);
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
    //添加试题信息
    public int addQuestion(Question question,String uuid){
        int mark = 0;
        JDBCUtil.getConnection();//建立连接
        String sql = "insert into question values(?,?,?,?,?,?,now(),1)";
        PreparedStatement pstmt = JDBCUtil.getPreparedStatement(sql);//预处理

        try {
            pstmt.setString(1,uuid);
            pstmt.setInt(2,question.getType());
            pstmt.setString(3,question.getTopic());
            pstmt.setString(4,question.getAnswer());
            pstmt.setInt(5,question.getScore());
            pstmt.setString(6,question.getClassify());

            mark = pstmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        JDBCUtil.close();

        return mark;
    }
    //添加option信息
    public int  addOption(Question question,String uuid){
        int mark = 0;
        JDBCUtil.getConnection();//建立连接
        String sql= "insert into options values(?,?,?,?,?)";
        PreparedStatement pstmt = JDBCUtil.getPreparedStatement(sql);//预处理
        try {
            pstmt.setString(1,uuid);
            pstmt.setString(2,question.getOptiona());
            pstmt.setString(3,question.getOptionb());
            pstmt.setString(4,question.getOptionc());
            pstmt.setString(5,question.getOptiond());

            mark = pstmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        JDBCUtil.close();

        return mark;
    }
    //查询试题信息
    public ArrayList<Question> getQuestion(){
        ArrayList<Question> adminList = new ArrayList<Question>();

        JDBCUtil.getConnection();//建立连接
        String sql = "select * from question where exist = 1 order by createtime desc";//查询存在分类
        PreparedStatement pstmt = JDBCUtil.getPreparedStatement(sql);//预处理
        try {
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                adminList.add(new Question(rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(6),rs.getString(7)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        JDBCUtil.close();
        return adminList;
    }
    //编辑试题时 先拿到数据库中的对应id数据
    public ArrayList<Question> getEditTest(String id){
        ArrayList<Question> adminList = new ArrayList<Question>();

        JDBCUtil.getConnection();//建立连接
        String sql = "select * from question left join options on question.id = options.id where question.id = ?";//查询存在分类
        PreparedStatement pstmt = JDBCUtil.getPreparedStatement(sql);//预处理
        try {
            pstmt.setString(1,id);

            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                adminList.add(new Question(rs.getInt(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        JDBCUtil.close();
        return adminList;
    }
    //修改试题信息
    public int changeTest(Question question){
        int mark = 0;
        JDBCUtil.getConnection();//建立连接
        String sql = "update question set topic = ?,answer = ?,score = ?,classify = ? where id = ? and exist = 1";//修改试题信息
        PreparedStatement pstmt = JDBCUtil.getPreparedStatement(sql);//预处理
        try {

            pstmt.setString(1,question.getTopic());
            pstmt.setString(2,question.getAnswer());
            pstmt.setInt(3,question.getScore());
            pstmt.setString(4,question.getClassify());
            pstmt.setString(5,question.getId());

            mark = pstmt.executeUpdate();
            System.out.println(mark);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        JDBCUtil.close();

        return mark;
    }
    //修改option信息
    public int changeOption(Question question){
        int mark = 0;
        JDBCUtil.getConnection();//建立连接
        String sql = "update options set a = ?,b = ?,c = ?,d = ? where id = ?";//修改试题信息
        PreparedStatement pstmt = JDBCUtil.getPreparedStatement(sql);//预处理
        try {

            pstmt.setString(1,question.getOptiona());
            pstmt.setString(2,question.getOptionb());
            pstmt.setString(3,question.getOptionc());
            pstmt.setString(4,question.getOptiond());
            pstmt.setString(5,question.getId());

            mark = pstmt.executeUpdate();
            System.out.println(mark);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        JDBCUtil.close();

        return mark;
    }
    //删除试题
    public String deleTest(String dele){
        Map<String,Object> params = new HashMap<String,Object>();

        JDBCUtil.getConnection();//建立连接
        String sql = "update question set exist = 0 where id = ?";//修改分类exist为0  代表删除
        PreparedStatement pstmt = JDBCUtil.getPreparedStatement(sql);//预处理
        try {
            pstmt.setString(1,dele);

            int mark = pstmt.executeUpdate();
            System.out.println(mark);
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
}
