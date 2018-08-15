//敲击回车，提交调单
$(document).ready(function() {
    $("body").keydown(function () {
        if (event.keyCode == "13") {//keyCode=13是回车键
            $('.btn_area button:first').click();
        }
    })
});