package controller;


import com.alibaba.fastjson.JSON;
import pojo.ChangeClassify;
import pojo.Question;
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

@WebServlet(value = "/ClassifyServlet")
public class ClassifyServlet extends HttpServlet {
    MyService ms = new MyService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = null;
//      输入流与输出流的格式
        request.setCharacterEncoding("utf8");
        response.setCharacterEncoding("utf8");

        String paramString = ms.readJSONString(request);//将接收到的json数据转换成字符串
        System.out.println(paramString);
        Map map = JSON.parseObject(paramString, HashMap.class);//将上面转好的字符串转成对象的形式
        System.out.println(map.get("mark"));
        System.out.println(map.get("data"));

        switch (map.get("mark").toString()){
//            case "add":
//                result = ms.addClassify(map.get("data").toString());
//                break;
            case "getInfo":
                result = JSON.toJSONString(ms.getClassifyIfro());
                break;
            case "change":
                ChangeClassify ccy = JSON.parseObject(map.get("data").toString(), ChangeClassify.class);
                result = ms.changeClassifyInfo(ccy);
                break;
            case "delete":
                result = ms.deleClassifyInfo(map.get("data").toString());
                System.out.println(result);
                break;
            case  "addSingle":
                Question question1 = JSON.parseObject(map.get("data").toString(), Question.class);
                if (question1.getChangeTestFlag().equals("0")){
                    result = ms.addSingle(question1);
                }
                if (question1.getChangeTestFlag().equals("1")){
                    result = ms.changeSingleInfo(question1);
                }
                break;
            case "getQues":
                result = ms.getQuestionInfo();
                break;
            case "addShort":
                Question question2 = JSON.parseObject(map.get("data").toString(), Question.class);
                if (question2.getChangeTestFlag().equals("0")){
                    result = ms.addShort(question2);
                }
                if (question2.getChangeTestFlag().equals("1")){
                    result = ms.changeShortInfo(question2);
                }
                break;
            case "editTest":
                result = ms.getEditTestInfo(map.get("data").toString());
                break;
            case "deleteTest" :
                result = ms.deleTestInfo(map.get("data").toString());
                System.out.println(result);
                break;
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
