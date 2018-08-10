$(document).ready(function () {
    $("#code").click(function () {
        $("#code").attr("src", "http://localhost:8080/VerifyCode?time="+new Date().getTime() );
    });
    $("#login").on("click", checkcode);
    function checkcode() {
        var vCode = $("#inputCode").val();
        var param = {code:vCode};
        $.post("http://localhost:8080/CheckCode", param, function (data) {
            if(data === "success")
                $("#loginForm").submit();
            else {
                alert("验证码出错");
                $("#code").click();
                $("#inputCode").focus();
            }
        })
    }
});
