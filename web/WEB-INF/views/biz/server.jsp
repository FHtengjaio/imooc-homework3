<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<title>课程后台管理系统</title>
	<link rel="stylesheet" href="../../../css/common.css" type="text/css">
	<link rel="stylesheet" href="../../../css/server.css" type="text/css">
	<script src="../../../js/jquery-3.3.1.js"></script>
	<script src="../../../js/server.js"></script>
</head>
<body>
<div class="top">
	<div class="server_title">
		<h1>课程后台管理系统</h1>
	</div>
	<div class="login_user">
		<div class="show_info">
			你好，<span>${sessionScope.LoginUser}</span>
		</div>
	</div>
</div>
<div class="bd">
	<div class="selection">
		<div class="header">
			<span>操作框</span>
		</div>
		<div class="control_box">
			<div class="control_content">
				<span>用户管理</span>
				<c:if test="${LoginUser=='imooc'}">
				<ul>
					<li><a href="<%=basePath%>/AddUserInit.do" target="main">添加管理员</a></li>
					<li><a href="<%=basePath%>/SelectUser.do" target="main">查询管理员</a></li>
				</ul>
				</c:if>
			</div>
			<div class="control_content">
				<span>课程管理</span>
				<ul>
					<li><a href="<%=basePath%>/AddCourseInit.do" target="main">课程添加</a></li>
					<li><a href="<%=basePath%>/ImportCourseInit.do" target="main">课程批量导入(Excel)</a></li>
					<li><a href="<%=basePath%>/GetCourse.do" target="main">课程查询</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="content">
		<iframe id="link_content" name="main" frameborder="0"></iframe>
	</div>
</div>
</body>
</html>