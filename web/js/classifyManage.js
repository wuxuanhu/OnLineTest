$("#middle_btn").click(function () {
    var jsonData = {
        classificationName:$("#middle_ipt").val()
    }
    $.ajax({
        url:globalUrl+"addClassify",//地址
        type:"post",//传输方式
        data:jsonData,//将json格式转换为字符串并进行传送
        contentType:"application/json",//接收后端传回的数据格式是json
        dataType:"json",//传入后端的数据格式也是json
        success:function (result){
            if (result.mark) {
                alert("添加成功！")
                // creatTable();
            } else {
                alert("添加失败！ ")
            }
        }
    })
})
// //刷新页面直接更新
// $(function () {
//     creatTable();
// })
// //分类管理下主区域
// function creatTable() {
//     $("#middle_table").children().remove();
//     var trTemp = $("<tr></tr>");
//     //往行里面追加 td单元格
//     trTemp.append("<td class=\"td1\"><input type=\"checkbox\"></td>");
//     trTemp.append("<td class=\"td2\">分类名称</td>");
//     trTemp.append("<td class=\"td3\">管理</td>");
//     trTemp.append("<td class=\"td4\"></td>");
//     $("#middle_table").append(trTemp);
//
//     var jsonData = {
//         mark:"getInfo",
//     }
//     $.ajax({
//         url:"http://localhost:8080/OnLineTest/ClassifyServlet",//地址
//         type:"post",//传输方式
//         async:false,//false同步  true异步
//         data:JSON.stringify(jsonData),//将json格式转换为字符串并进行传送
//         contentType:"application/json",//接收后端传回的数据格式是json
//         dataType:"json",//传入后端的数据格式也是json
//         success:function (result){
//             console.log(result)
//             //动态创建表格
//             for(var i=0 ;i<result.length;i++){
//                 var trTemp = $("<tr></tr>");
//
//                 //往行里面追加 td单元格
//                 trTemp.append("<td class=\"td1\"><input type=\"checkbox\"></td>");
//                 trTemp.append("<td class=\"td2\">"+ result[i] +"</td>");
//                 trTemp.append("<td class=\"td3\"><button class='edit'>编辑</button><button class='dele'>删除</button></td>");
//                 trTemp.append("<td></td>");
//                 $("#middle_table").append(trTemp);
//             }
//             //删除按钮
//             $(".dele").click(function(){
//                 var jsonData = {
//                     mark:"delete",
//                     data:$(this).parent().prev().text()
//                 }
//                 $.ajax({
//                     url:"ClassifyServlet",
//                     type:"post",
//                     data:JSON.stringify(jsonData),//将json格式转换为字符串 传数据
//                     contentType:"application/json",
//                     dataType:"json",
//                     success:function (result) {
//                         if (result.mark) {
//                             //删除成功
//                             alert("删除成功！")
//                         } else {
//                             //删除失败
//                             alert("删除失败！ ")
//                         }
//                     }
//                 })
//                 $(this).parent().parent().remove();
//             })
//
//             //编辑按钮
//             $(".edit").click(function () {
//                 $(this).parent().next().append("<input type=\"text\" placeholder=\"请输入新的分类名称\"><button class='change'>修改</button><button class='cancel'>取消</button>")
//                 //取消按钮
//                 $(".cancel").click(function () {
//                     $(this).parent().text("");
//                 })
//                 //修改按钮
//                 $(".change").click(function () {
//                     console.log("1111")
//                     var jsonData = {
//                         mark:"change",
//                         data:{
//                             oldname:$(this).parent().prev().prev().text(),
//                             newname:$(this).prev().val()
//                         }
//                     }
//                     $.ajax({
//                         url:"http://localhost:8080/OnLineTest/ClassifyServlet",//地址
//                         type:"post",//传输方式
//                         data:JSON.stringify(jsonData),//将json格式转换为字符串并进行传送
//                         contentType:"application/json",//接收后端传回的数据格式是json
//                         dataType:"json",//传入后端的数据格式也是json
//                         success:function (result){
//                             if (result.mark) {
//                                 //注册成功
//                                 alert("修改成功！")
//                                 creatTable();
//                             } else {
//                                 //注册失败
//                                 alert("修改失败！ ")
//                             }
//                         }
//                     })
//                 })
//             })
//         }
//     })
// }