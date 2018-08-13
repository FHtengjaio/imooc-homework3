<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>课程添加</title>
	<link rel="stylesheet" href="../../../css/common.css" type="text/css">
	<link rel="stylesheet" href="../../../css/form_common.css" type="text/css">
	<link rel="stylesheet" href="../../../css/addCourse_special.css" type="text/css">
	<script src="../../../js/jquery-3.3.1.js"></script>
	<script src="../../../js/adduser.js"></script>
</head>
<body>
<div class="header">
	<span>课程管理</span>>><span>课程添加</span>>>
</div>
<div class="main_box new">
	<div class="title title_new">课程添加</div>
	<div class="error_msg error_msg_new">${msg}</div>
	<form class="form_box form_box_new" action="<%=basePath%>/AddCourse.do" method="post">
		<div class="info_row">
			<div class="info">
				<div>课程ID</div>
				<input type="text" name="courseId" placeholder="请输入数字">
				<div class="regexMsg">格式错误</div>
			</div>
		</div>
		<div class="info_row">
			<div class="info">
				<div style="font-size: 12px">课程名称</div>
				<input type="text" name="courseName" placeholder="请输入名称">
				<div class="regexMsg">不能为空</div>
			</div>
		</div>
		<div class="info_row">
			<div class="info">
				<div>方&nbsp;&nbsp;&nbsp;向</div>
				<select name="courseType">
					<option value="Java">Java</option>
					<option value="Linux">Linux</option>
					<option value="前端">前端</option>
				</select>
			</div>
		</div>
		<div class="info_row">
			<div class="info textarea">
				<div id="desc_div">描&nbsp;&nbsp;&nbsp;述</div>
				<textarea name="description"></textarea>
				<div class="regexMsg">不能为空</div>
			</div>
		</div>
		<div class="info_row">
			<div class="info">
				<div>时&nbsp;&nbsp;&nbsp;长</div>
				<input type="text" name="courseTime" id="time" placeholder="请输入仅两位小数的数字,如：2.98">
				<div class="regexMsg">格式错误</div>
			</div>
		</div>
		<div class="info_row">
			<div class="info">
				<div>操作人</div>
				<input type="text" name="operator" id="operator" value="${sessionScope.LoginUser}" readonly>
			</div>
		</div>
		<div class="info_row">
			<div class="info code">
				<div>验证码</div>
				<input type="text" name="checkCode" placeholder="请输入4位验证码" id="inputCode" maxlength="4">
			</div>
			<div class="img_contain">
				<img id="code" src="<%=basePath%>/VerifyCode">
			</div>
		</div>
		<div class="btn_area btn_area_new">
			<button type="button">添加</button>
			<button type="button">取消</button>
		</div>
	</form>
</div>

</body>
</html>