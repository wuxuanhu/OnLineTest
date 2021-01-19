package controller;

import com.alibaba.fastjson.JSON;
import dao.MyDao;
import pojo.Admin;
import service.MyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(value = "/Servlet")
public class Servlet extends HttpServlet {
    MyService ms = new MyService();
    MyDao md = new MyDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = null;
//      输入流与输出流的格式
        request.setCharacterEncoding("utf8");
        response.setCharacterEncoding("utf8");

        String paramString = ms.readJSONString(request);//将接收到的json数据转换成字符串
        System.out.println(paramString);
        Map map = JSON.parseObject(paramString,HashMap.class);//将上面转好的字符串转成对象的形式

        switch (map.get("mark").toString()){
            case  "login"://登录时请求的数据
                Admin admin = JSON.parseObject(map.get("data").toString(),Admin.class);
                result = ms.checkingAdmin(admin);//验证管理员身份
                break;
//            case "index"://首页请求的数据
//                result = JSON.toJSONString(md.getScoreData());
//                break;
            case "accChecking"://注册时请求验证账号是否存在
                Admin admin1 = JSON.parseObject(map.get("data").toString(),Admin.class);
                result = ms.checkingAdminAcc(admin1);//验证账号是否存在
                break;
            case "getRegister"://完整的注册数据
                Admin admin2 = JSON.parseObject(map.get("data").toString(),Admin.class);
                result = md.registerAdmin(admin2);
                break;
//            case "delete"://删除
//                result = md.deleScore(Integer.parseInt(map.get("data").toString()));
//                break;
        }

        response.setContentType("application/json");//返回前端的数据格式   json
        PrintWriter pw =  response.getWriter();//通过输出流的方式将数据传回
        pw.write(result);
        pw.flush();
        pw.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

}
