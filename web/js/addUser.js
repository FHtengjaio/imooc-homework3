$(document).ready(function () {
    $("button:first").on("click", function () {
        $(window.parent.document).find("a:first").removeClass("on");
        $(window.parent.document).find("a:eq(1)").addClass("on");
    });
    $.getJSON(
            "AddCourse.do",
            {
                username:$("input[name='username']"),
                password:$("input[name='password']"),
                operator:$("input[name='operator']")
            },
            function (data) {
                $(".error_msg").html(data.msg);
                if (data.result === "success") {
                    location.assign("SelectUser.do");
                }
        });
});