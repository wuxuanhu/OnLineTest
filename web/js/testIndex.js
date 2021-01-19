
$(function () {
    //页面加载时 动态创建select中的option
    getOption();

    //页面加载时，动态创建表格
    getQuestion();
})
function getOption() {
    var jsonData = {
        mark: "getInfo",
    }
    $.ajax({
        url: "http://localhost:8080/OnLineTest/ClassifyServlet",//地址
        type: "post",//传输方式
        async: false,//false同步  true异步
        data: JSON.stringify(jsonData),//将json格式转换为字符串并进行传送
        contentType: "application/json",//接收后端传回的数据格式是json
        dataType: "json",//传入后端的数据格式也是json
        success: function (result) {
            $("#select").children().remove();
            for (var i=0;i<result.length;i++){
                $("#select").append("<option>" + result[i]+ "</option>");
            }
        }
    })
}

var hideId = null;//存放当前编辑试题的id
function getQuestion() {
    $("#test_table").children().remove();
    var trTemp = $("<tr></tr>");
    //往行里面追加 td单元格
    trTemp.append("<th class=\"qian3\"><input type=\"checkbox\"></th>");
    trTemp.append("<th class=\"qian3\">题目类型</th>");
    trTemp.append("<th class=\"qian3\">试题分类</th>");
    trTemp.append("<th class=\"content\">试题内容</th>");
    trTemp.append("<th class=\"hou2\">增加时间</th>");
    trTemp.append("<th class=\"hou2\">管理</th>");
    $("#test_table").append(trTemp);

    var jsonData = {
        mark: "getQues",
    }
    $.ajax({
        url: "http://localhost:8080/OnLineTest/ClassifyServlet",
        type: "post",
        data: JSON.stringify(jsonData),//将json格式转换为字符串并进行传送
        contentType: "application/json",//接收后端传回的数据格式是json
        async: false,//false同步  true异步
        dataType: "json",//传入后端的数据格式也是json
        success: function (result) {
            console.log(result);
            for (var i=0;i<result.length;i++){
                var trTemp = $("<tr></tr>");

                //往行里面追加 td单元格
                trTemp.append("<td class=\"qian3\"><input type=\"checkbox\"></td>");
                if (result[i].type==0){
                    trTemp.append("<td class=\"qian3\">单项选择题</td>");
                } else{
                    trTemp.append("<td class=\"qian3\">简答题</td>");
                }
                trTemp.append("<td class=\"qian3\">"+result[i].classify+"</td>");
                trTemp.append("<td class=\"content\">"+result[i].topic+"</td>");
                trTemp.append("<td class=\"hou2\">"+result[i].timeStr+"</td>");
                trTemp.append("<td class=\"hou2\"><button class='edit'>编辑</button><span class='hide_id'>"+result[i].id+"</span><button class='dele'>删除</button></td>");
                $("#test_table").append(trTemp);
            }
            //编辑试题按钮
            $(".edit").click(function () {
                flag = 1;
                $("#inner").css("height","900px");
                $("#inner").load("addTest.html");
                hideId = $(this).next().text();
            })
            //删除试题按钮
            $(".dele").click(function () {
                var jsonData = {
                    mark:"deleteTest",
                    data:$(this).prev().text()
                }
                $.ajax({
                    url:"ClassifyServlet",
                    type:"post",
                    data:JSON.stringify(jsonData),//将json格式转换为字符串 传数据
                    contentType:"application/json",
                    dataType:"json",
                    success:function (result) {
                        if (result.mark) {
                            //删除成功
                            alert("删除成功！")
                        } else {
                            //删除失败
                            alert("删除失败！ ")
                        }
                    }
                })
                $(this).parent().parent().remove();
            })
        }
    })
}
