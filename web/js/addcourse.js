$(document).ready(function () {

    //获取JQ需要操作的对象
    var idNode = $("input[name='courseId']");           //courseId
    var timeNode = $("input[name='courseTime']");       //courseTime
    var nameNode = $("input[name='courseName']");       //courseName
    var descNode = $("textarea");                       //description

    //点击验证码图片，更新验证码
    $("#code").click(function () {
        $("#code").attr("src", "http://localhost:8080/VerifyCode?time="+new Date().getTime() );
    });

    //取消按钮实现reset功能
    $(".btn_area button:last-child").on("click", function () {
        var inputs = $("input:not('#operator')").val("");
        descNode.val("");
        inputs.val("");
        descNode.parent().removeClass("regexError");
        inputs.parent().removeClass("regexError");
        descNode.next().hide();
        inputs.next().hide();
        $("select").val($("option:first").val());
    });
    
    //点击添加按钮，执行js代码
    $(".btn_area button:first-child").on("click", checkregex);
    
    //检查表单数据是否符合要求
    function checkregex() {
        var isidok = checkelement({node:idNode, regex:/^\d{3,}$/});
        var istimeok = checkelement({node:timeNode, regex:/^\d+\.\d{2}$/});
        var isnameok = checkelement({node:nameNode, regex:/./});
        var isdescok = checkelement({node:descNode, regex:/./});
        if(!(isidok && istimeok && isnameok && isdescok)){
            return;
        }
        //如果检查通过，执行验证码验证操作
        checkcode();
    }

    //验证码操作，后台验证
    function checkcode() {
        var vCode = $("#inputCode").val();
        var param = {code:vCode};
        $.post("http://localhost:8080/CheckCode", param, function (data) {
            if(data === "success"){
                submitform();   //验证码验证通过，发起表单提交，尝试添加课程
            }
            else {
                alert("验证码出错");
                $("#code").click();
                $("#inputCode").focus();
            }
        })
    }
    
    //表单提交，尝试添加课程
    function submitform(){
        $.getJSON(
            "http://localhost:8080/AddCourse.do",
            {
                courseId: idNode.val(),
                courseName: nameNode.val(),
                courseType: $("select").val(),
                description: descNode.val(),
                courseTime: timeNode.val()
            },
            function (res) {
                //添加成功后页面跳转，页面样式修改
                if (res.result === "success") {
                    location.assign("http://localhost:8080/GetCourse.do");
                    $(window.parent.document).find("a:eq(2)").removeClass("on");
                    $(window.parent.document).find("a:eq(4)").addClass("on");
                }else{
                    //添加失败，显示错误信息
                    $(".error_msg").html(res.msg);
                    $("#code").click();
                }
            }
        );
    }

    //courseId，courseTime，courseName，description 添加blur，focus事件，正则匹配
    idNode.on("blur", {node:idNode, regex:/^\d{3,}$/}, checkelement);
    idNode.on("focus", function () { $(this).parent().removeClass("regexError");$(this).next().hide(); });
    nameNode.on("blur", {node:nameNode, regex:/./}, checkelement);
    nameNode.on("focus", function () { $(this).parent().removeClass("regexError");$(this).next().hide(); });
    descNode.on("blur", {node:descNode, regex:/./}, checkelement);
    descNode.on("focus", function () { $(this).parent().removeClass("regexError");$(this).next().hide(); });
    timeNode.on("blur", {node:timeNode, regex:/\d+\.\d{2}$/}, checkelement);
    timeNode.on("focus", function () { $(this).parent().removeClass("regexError");$(this).next().hide();});

    //正则匹配
    function checkelement(param) {
        var oNode;
        var regex;
        //判断调用checkelement函数的来源，属于blur事件还是button点击事件
        if ($(this).prop("nodeName") === "INPUT" || $(this).prop("nodeName") === "TEXTAREA") {
            oNode = param.data.node;
            regex = param.data.regex;
        } else {
            oNode = param.node;
            regex = param.regex;
        }

        //开始匹配
        if (!regex.test(oNode.val())) {
            oNode.parent().addClass("regexError");
            oNode.next().show();
            return false;
        } else {
            return true;
        }
    }

    //在textarea中敲击回车，阻止keydown事件冒泡，进而影响表单提交
    descNode.on("keydown", function (event) {
        event.stopPropagation();
    })
});
