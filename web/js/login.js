// 数据交互
$("#log").click(function () {
    var accVal = $("input:first").val();
    var pwdVal = $("input:eq(1)").val();
    var jsonData = {
        mark:"login",
        data:{
            acc:accVal,
            pwd:pwdVal
        }
    }
    $.ajax({
        url:"http://localhost:8080/OnLineTest/Servlet",
        type:"post",
        data:JSON.stringify(jsonData),//将json格式转换为字符串
        contentType:"application/json",
        dataType:"json",
        success:function (result) {
            //result 接收后端返回的数据  只是变量，名字任意
            if (result.mark){
                window.location.href = "webService/index.html";
            }else {
                alert("账号或密码输入错误！！！")
            }
        }
    })
})

//注册账号时   失去焦点进行验证
$("input:eq(2)").blur(function () {
    var accVal = $(this).val();
    var jsonData = {//传输至后端的数据   json格式
        mark:"accChecking",//账号验证  标识符 用于同一个servlet判断不同的ajax传入的数据
        data:{              //真正传入的数据
            acc:accVal
        }
    }
    $.ajax({  //jq下的ajax用法
        url:"http://localhost:8080/OnLineTest/Servlet",//地址
        type:"post",//传输方式
        data:JSON.stringify(jsonData),//将json格式转换为字符串并进行传送
        contentType:"application/json",//接收后端传回的数据格式是json
        dataType:"json",//传入后端的数据格式也是json
        success:function (result) {//后端返回的数据   在result中
            //result 接收后端返回的数据  只是变量，名字任意
            if (result.mark){
                //已注册
                $("#acchave").show();
            }else {
                //可注册
                $("#acchave").hide();
                //点击注册按钮 将数据传入后台 并存入数据库
                $("#zhuce").click(function () {
                    console.log("进入按钮");
                    var accVal = $("input:eq(2)").val();
                    var pwdVal = $("input:eq(3)").val();
                    var emaVal = $("input:eq(4)").val();
                    var pheVal = $("input:eq(5)").val();
                    var jsonData = {//传输至后端的数据   json格式
                        mark: "getRegister",//账号验证  标识符 用于同一个servlet判断不同的ajax传入的数据
                        data: {              //真正传入的数据
                            acc: accVal,
                            pwd: pwdVal,
                            email: emaVal,
                            phone: pheVal
                        }
                    }
                    $.ajax({  //jq下的ajax用法
                        url: "http://localhost:8080/OnLineTest/Servlet",//地址
                        type: "post",//传输方式
                        data: JSON.stringify(jsonData),//将json格式转换为字符串并进行传送
                        contentType: "application/json",//接收后端传回的数据格式是json
                        dataType: "json",//传入后端的数据格式也是json
                        success: function (result) {//后端返回的数据   在result中
                            //result 接收后端返回的数据  只是变量，名字任意
                            if (result.mark) {
                                //注册成功
                                alert("注册成功！")
                            } else {
                                //注册失败
                                alert("注册失败！ ")
                            }
                        }

                    })
                })
            }
        }
    })
})
