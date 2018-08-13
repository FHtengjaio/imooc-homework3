<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>课程批量导入(Excel)</title>
	<link rel="stylesheet" href="../../../css/common.css" type="text/css">
	<link rel="stylesheet" href="../../../css/form_common.css" type="text/css">
	<link rel="stylesheet" href="../../../css/importCourse_special.css" type="text/css">
	<script src="../../../js/jquery-3.3.1.js"></script>
	<script>
        $(document).ready(function () {
            $("button").on("click",function () {
                $("#excel").trigger("click");
            });
            $("#excel").on("change", function () {
                // alert("execute onchange")
                if(confirm("确定上传"+$("#excel").val()+"吗？")){
                    $("#excel_form").submit();
                }
                else {
                    $("#excel").val("");
                }
            });
        });
	</script>
</head>
<body>
<div class="header">
	<span>课程管理</span>>><span>课程批量导入(Excel)</span>>>
</div>
<div class="main_box">
	<div class="title">课程批量导入</div>
	<div class="error_msg">${msg}</div>
	<form class="form_box" action="" method="post" enctype="multipart/form-data" id="excel_form">
		<input type="file" style="display: none" id="excel" name="excel">
		<div class="file_btn">
			<button type="button"></button>
		</div>
	</form>
</div>
</body>
</html>