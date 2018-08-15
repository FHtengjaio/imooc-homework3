$(document).ready(function () {

    var fileNode = $("#excel");

    //点击按钮出发input的click事件
    $("button").on("click",function () {
        fileNode.trigger("click");
    });

    //当input内容改变时，弹出提示，用户点击确定后上传文件
    fileNode.on("change", function () {
        var filepath = $("#excel").val().split("\\");   //获取文件的名称
        var filename = filepath[filepath.length-1];
        if(confirm("确定上传"+ filename +"吗？")){
            $(".error_msg").html("");
            uploadexcel();
        }
        //点击取消的，清空input中的内容
        else {
            fileNode.val("");
        }
    });

    //ajax文件上传
    function uploadexcel() {
        //准备文件数据
        var excel = $("#excel").prop("files")[0];
        var formData = new FormData();
        formData.append("excel", excel);
        $.ajax({
            type: "post",
            url: "CourseImport.do",
            data: formData,
            dataType: 'json',
            processData: false,  //让jQuery不处理数据
            contentType: false,
            success: function (data) {
                console.log(data.result);
                if (data.result === "success") {
                    //数据导入后，由于页面跳转，将导入的提示语句保存在浏览器缓存中
                    //方便在showCourse.jsp页面中获取使用
                    localStorage.setItem("msg",data.msg);
                    location.href = "/GetCourse.do";
                    $(window.parent.document).find("a:eq(3)").removeClass("on");    //操作框样式改动
                    $(window.parent.document).find("a:eq(4)").addClass("on");
                } else {
                    //上传失败后的操作
                    $(".error_msg").html(data.msg);
                    $("#excel").val("");
                }
            }
        });
    }
});