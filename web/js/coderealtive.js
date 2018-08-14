$(document).ready(function () {

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

    // 点击登录
    $(".btn_area button:first-child").on("click", checkregex);

    // 登录前检查username，password是否格式正确
    function checkregex() {
        var isnameok = checkelement({node:unNode, regex:/^[a-zA-Z0-9_]{3,12}$/});
        var ispasswdok = checkelement({node:passwdNode, regex:/^[a-zA-Z0-9_]{5,12}$/});
        if (!(isnameok && ispasswdok)) {
            return
        }
        //检查通过后，发起验证码验证
        checkcode();
    }

    function checkcode() {
        var vCode = $("#inputCode").val();
        var param = {code:vCode};
        $.post("http://localhost:8080/CheckCode", param, function (data) {
            if(data === "success"){
                //验证码通过后，发起添加user请求
                sendrequest();
            }
            else {
                alert("验证码出错");
                $("#code").click();
                $("#inputCode").focus();
            }
        })
    }

    function sendrequest(){
        $.getJSON(
            "http://localhost:8080/AddUser.do",
            {
                username:$("input[name='username']").val(),
                password:$("input[name='password']").val(),
                operator:$("input[name='operator']").val()
            },
            function (res) {
                $(".error_msg").html(res.msg);
                if (res.result === "success") {
                    location.assign("http://localhost:8080/SelectUser.do");
                    $(window.parent.document).find("a:first").removeClass("on");
                    $(window.parent.document).find("a:eq(1)").addClass("on");
                } else {
                    $("#code").click();
                }
            });
    }

    unNode.on("blur", {node:unNode, regex:/^[a-zA-Z0-9_]{3,12}$/}, checkelement);
    unNode.on("focus", function () { $(this).parent().removeClass("regexError");$(this).next().hide(); });

    passwdNode.on("blur", {node:passwdNode, regex:/^[a-zA-Z0-9_]{5,12}$/}, checkelement);
    passwdNode.on("focus", function () { $(this).parent().removeClass("regexError");$(this).next().hide();});


    function checkelement(param) {
        var oNode;
        var regex;
        if ($(this).prop("nodeName") === "INPUT") {
            oNode = param.data.node;
            regex = param.data.regex;
        } else {
            oNode = param.node;
            regex = param.regex;
        }

        if (!regex.test(oNode.val())) {
            oNode.parent().addClass("regexError");
            oNode.next().show();
            return false;
        } else {
            return true;
        }
    }
});
