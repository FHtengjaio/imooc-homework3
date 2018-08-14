$(document).ready(function () {

    var idNode = $("input[name='courseId']");
    var timeNode = $("input[name='courseTime']");
    var nameNode = $("input[name='courseName']");
    var descNode = $("textarea");

    $("#code").click(function () {
        $("#code").attr("src", "http://localhost:8080/VerifyCode?time="+new Date().getTime() );
    });

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

    $(".btn_area button:first-child").on("click", checkcode);

    function checkcode() {
        var vCode = $("#inputCode").val();
        var param = {code:vCode};
        var isidok = checkelement({node:idNode, regex:/^\d{3,}$/});
        var istimeok = checkelement({node:timeNode, regex:/^\d+\.\d{2}$/});
        var isnameok = checkelement({node:nameNode, regex:/./});
        var isdescok = checkelement({node:descNode, regex:/./});
        if(!(isidok && istimeok && isnameok && isdescok)){
            return;
        }
        $.post("http://localhost:8080/CheckCode", param, function (data) {
            if(data === "success"){
                $(".form_box").submit();
            }
            else {
                alert("验证码出错");
                $("#code").click();
                $("#inputCode").focus();
            }
        })
    }

    idNode.on("blur", {node:idNode, regex:/^\d{3,}$/}, checkelement);
    idNode.on("focus", function () { $(this).parent().removeClass("regexError");$(this).next().hide(); });
    nameNode.on("blur", {node:nameNode, regex:/./}, checkelement);
    nameNode.on("focus", function () { $(this).parent().removeClass("regexError");$(this).next().hide(); });
    descNode.on("blur", {node:descNode, regex:/./}, checkelement);
    descNode.on("focus", function () { $(this).parent().removeClass("regexError");$(this).next().hide(); });
    timeNode.on("blur", {node:timeNode, regex:/\d+\.\d{2}$/}, checkelement);
    timeNode.on("focus", function () { $(this).parent().removeClass("regexError");$(this).next().hide();});


    function checkelement(param) {
        var oNode;
        var regex;
        if ($(this).prop("nodeName") === "INPUT" || $(this).prop("nodeName") === "TEXTAREA") {
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
