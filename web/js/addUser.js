$(document).ready(function () {

    //获取username，password对象
    var unNode = $("input[name='username']");
    var passwdNode = $("input[name='password']");

    // 点击图片更新验证码
    $("#code").click(function () {
        $("#code").attr("src", "http://localhost:8080/VerifyCode?time="+new Date().getTime() );
    });

    // 实现reset功能
    $(".btn_area button:last-child").on("click", function () {
        unNode.val("");
        passwdNode.val("");
        $("#inputCode").val("");
        unNode.parent().removeClass("regexError");
        passwdNode.parent().removeClass("regexError");
        unNode.next().hide();
        passwdNode.next().hide();
    });

    // 点击添加按钮
    $(".btn_area button:first-child").on("click", checkregex);

    // 表单提交前检查username，password是否格式正确
    function checkregex() {
        var isnameok = checkelement({node:unNode, regex:/^[a-zA-Z0-9_]{3,12}$/});
        var ispasswdok = checkelement({node:passwdNode, regex:/^[a-zA-Z0-9_]{5,12}$/});
        if (!(isnameok && ispasswdok)) {
            return
        }
        //检查通过后，发起验证码验证
        checkcode();
    }

    //验证码操作，后台验证
    function checkcode() {
        var vCode = $("#inputCode").val();
        var param = {code:vCode};
        $.post("http://localhost:8080/CheckCode", param, function (data) {
            if(data === "success"){
                //验证码通过后，发起添加user请求
                submitform();
            }
            else {
                alert("验证码出错");
                $("#code").click();
                $("#inputCode").focus();
            }
        })
    }

    //表单提交，尝试添加用户
    function submitform(){
        $.getJSON(
            "http://localhost:8080/AddUser.do",
            {
                username:$("input[name='username']").val(),
                password:$("input[name='password']").val(),
                operator:$("input[name='operator']").val()
            },
            function (res) {
                //添加成功后页面跳转，页面样式修改
                if (res.result === "success") {
                    location.assign("http://localhost:8080/SelectUser.do");
                    $(window.parent.document).find("a:first").removeClass("on");
                    $(window.parent.document).find("a:eq(1)").addClass("on");
                } else {
                    //添加失败，显示错误信息
                    $(".error_msg").html(res.msg);
                    $("#code").click();
                }
            });
    }

    //username，password 添加blur，focus事件，正则匹配
    unNode.on("blur", {node:unNode, regex:/^[a-zA-Z0-9_]{3,12}$/}, checkelement);
    unNode.on("focus", function () { $(this).parent().removeClass("regexError");$(this).next().hide(); });
    passwdNode.on("blur", {node:passwdNode, regex:/^[a-zA-Z0-9_]{5,12}$/}, checkelement);
    passwdNode.on("focus", function () { $(this).parent().removeClass("regexError");$(this).next().hide();});

    //正则匹配
    function checkelement(param) {
        var oNode;
        var regex;
        //判断调用checkelement函数的来源，属于blur事件还是button点击事件
        if ($(this).prop("nodeName") === "INPUT") {
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
});
