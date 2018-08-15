<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<title>添加用户</title>
	<link rel="stylesheet" href="../../../css/common.css" type="text/css">
	<link rel="stylesheet" href="../../../css/form_common.css" type="text/css">
	<script src="../../../js/jquery-3.3.1.js"></script>
	<script src="../../../js/common.js"></script>
	<script src="../../../js/addUser.js"></script>
	<script src="../../../js/buttonevent.js"></script>
</head>
<body>
<div class="header">
	<span>用户管理</span>>><span>添加管理员</span>>>
</div>
<div class="main_box">
	<div class="title">添加管理员</div>
	<div class="error_msg">${msg}</div>
	<div class="form_box">
		<div class="info_row">
			<div class="info">
				<div>用户名</div>
				<input type="text" name="username" placeholder="长度为3-12位字母数字或下划线组合">
				<div class="regexMsg">格式错误</div>
			</div>
		</div>
		<div class="info_row">
			<div class="info">
				<div>密&nbsp;&nbsp;&nbsp;码</div>
				<input type="password" name="password" placeholder="长度为5-12位字母数字或下划线组合">
				<div class="regexMsg">格式错误</div>
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
		<div class="btn_area">
			<button type="button">添加</button>
			<button type="button">取消</button>
		</div>
	</div>
</div>
</body>
</html>