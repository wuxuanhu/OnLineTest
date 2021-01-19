package service;

import com.alibaba.fastjson.JSON;
import dao.ClassifyDao;
import dao.MyDao;
import pojo.Admin;
import pojo.ChangeClassify;
import pojo.Question;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MyService {

    MyDao myDao = new MyDao();
    ClassifyDao ciDao = new ClassifyDao();

    //json转字符串
    public String readJSONString(HttpServletRequest request){
        StringBuffer json = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null){
                json.append(line);//拼接字符串
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

//登录时验证账号密码
    public String checkingAdmin(Admin admin){
        Map<String,Object> params = new HashMap<String,Object>();
        for (int i = 0;i < myDao.getAdminData().size();i++){
            if (admin.getAcc().equals(myDao.getAdminData().get(i).getAcc()) && admin.getPwd().equals(myDao.getAdminData().get(i).getPwd())){
                params.put("mark",true);
                break;
            }else {
                params.put("mark",false);
            }
        }
        //返回数据   将集合转换成json格式的字符串
        return JSON.toJSONString(params);   
    }
//注册时验证账号是否已经存在
    public String checkingAdminAcc(Admin admin){
        Map<String,Object> params = new HashMap<String,Object>();
        for (int i = 0;i < myDao.getAdminData().size();i++){
            if (admin.getAcc().equals(myDao.getAdminData().get(i).getAcc())){
                params.put("mark",true);
            }else {
                params.put("mark",false);
            }
        }
        //返回数据   将集合转换成json格式的字符串
        return JSON.toJSONString(params);
    }
    //验证分类名称是否重复 重复则返回false 不重复则存入表中
    public boolean addClassify(String name){
        Map<String,Object> params = new HashMap<String,Object>();
        boolean flag = false;
        for (int i = 0;i<ciDao.getClassify().size();i++){
            if (name.equals(ciDao.getClassify().get(i))){
                return false;
            }else{
                flag = true;
            }
        }
        if(flag){
            int mark = ciDao.addClassify(name);
            if(mark>0){
                return true;
            }else {
                return false;
            }
        }
        return flag;
    }
    //拿到分类表中的数据
    public ArrayList<String> getClassifyIfro(){
        return ciDao.getClassify();
    }
    //修改分类表中的数据
    public String changeClassifyInfo(ChangeClassify ccy){
        return ciDao.changeClassify(ccy);
    }
    //删除分类表中的数据
    public String deleClassifyInfo(String name){
        return ciDao.deleClassify(name);
    }
    //添加单选题
    public String addSingle(Question question){
        Map<String,Object> params = new HashMap<String,Object>();
        String uuid = String.valueOf(UUID.randomUUID());
        int mark = ciDao.addQuestion(question,uuid);
        int mark2 = ciDao.addOption(question,uuid);

        if(mark>0 && mark2>0){
            params.put("mark",true);
        }else {
            params.put("mark",false);
        }

        return JSON.toJSONString(params);
    }
    //添加单选题
    public String addShort(Question question){
        Map<String,Object> params = new HashMap<String,Object>();
        String uuid = String.valueOf(UUID.randomUUID());
        int mark = ciDao.addQuestion(question,uuid);

        if(mark>0){
            params.put("mark",true);
        }else {
            params.put("mark",false);
        }

        return JSON.toJSONString(params);
    }
    //获取试题信息
    public String getQuestionInfo(){
        return JSON.toJSONString(ciDao.getQuestion());
    }
    //编辑试题时 先拿到数据库中的对应id数据
    public String getEditTestInfo(String id){
        return JSON.toJSONString(ciDao.getEditTest(id));
    }
    //修改单选题信息
    public String changeSingleInfo(Question question){
        Map<String,Object> params = new HashMap<String,Object>();

        if (ciDao.changeTest(question)>0 && ciDao.changeOption(question)>0){
            params.put("mark",true);
        }else {
            params.put("mark",false);
        }

        return JSON.toJSONString(params);
    }
    //修改简答题信息
    public String changeShortInfo(Question question){
        Map<String,Object> params = new HashMap<String,Object>();

        if (ciDao.changeTest(question)>0){
            params.put("mark",true);
        }else {
            params.put("mark",false);
        }

        return JSON.toJSONString(params);
    }
    //删除试题表中的数据
    public String deleTestInfo(String id){

        return ciDao.deleTest(id);
    }
}
