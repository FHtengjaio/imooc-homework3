$(document).ready(function () {

    var unNode = $("input[name='username']");
    var passwdNode = $("input[name='password']");

    $("#code").click(function () {
        $("#code").attr("src", "http://localhost:8080/VerifyCode?time="+new Date().getTime() );
    });

    $("#login").on("click", checkcode);

    function checkcode() {
        var vCode = $("#inputCode").val();
        var param = {code:vCode};
        var isnameok = checkelement({node:unNode, regex:/^[a-zA-Z0-9_]{3,12}$/});
        var ispasswdok = checkelement({node:passwdNode, regex:/^[a-zA-Z0-9_]{5,12}$/});
        if(!(isnameok && ispasswdok)){
           return
        }
        $.post("http://localhost:8080/CheckCode", param, function (data) {
            if(data === "success"){
                $("#loginForm").submit();
            }
            else {
                alert("验证码出错");
                $("#code").click();
                $("#inputCode").focus();
            }
        })
    }

    unNode.on("blur", {node:unNode, regex:/^[a-zA-Z0-9_]{3,12}$/}, checkelement);
    unNode.on("focus", function () { $(this).removeClass("regexerror"); });

    passwdNode.on("blur", {node:passwdNode, regex:/^[a-zA-Z0-9_]{5,12}$/}, checkelement);
    passwdNode.on("focus", function () { $(this).removeClass("regexerror"); });


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
            oNode.addClass("regexerror");
            oNode.next().show();
            return false;
        } else {
            oNode.next().hide();
            return true;
        }
    }
});
