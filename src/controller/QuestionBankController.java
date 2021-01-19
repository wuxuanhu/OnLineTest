package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.MyService;


public class QuestionBankController {
    MyService ms = new MyService();

    @RequestMapping("/addClassify")
    @ResponseBody
    public boolean addClassify(String classificationName){
        System.out.println(classificationName);
        return ms.addClassify(classificationName);
    }
}
