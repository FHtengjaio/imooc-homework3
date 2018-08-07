<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录页面</title>
<style type="text/css">
	.c1{
		width:300px;
		cursor:pointer;
	}
	.c2{
		width:300px;
		padding-left: 30px;
	}
</style>
<script src="../../../js/jquery-3.3.1.js"></script>
<script src="../../../js/left.js"></script>
</head>
<body>
	<c:choose>
		<c:when test="${sessionScope.LoginUser=='imooc' }">
			<div class="c1">
				<h3 id="controlUser">用户管理</h3>
				<div class="c2" id="menu1" style="display:none;">
					<p><a href="<%=basePath%>/AddUserInit" target="main">添加管理员</a></p>
					<p><a href="<%=basePath%>/SelectUser" target="main">查询管理员</a></p>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<h3>用户管理</h3>
		</c:otherwise>
	</c:choose>
	<div class="c1">
		<h3 id="controlCourse">课程管理</h3>
		<div class="c2" id="menu2" style="display:none;">
			<p><a href="<%=basePath%>/AddCourseInit" target="main">课程添加</a></p>
			<p><a href="<%=basePath%>/ImportCourseInit" target="main">课程批量导入(Excel)</a></p>
			<p><a href="<%=basePath%>/CourseExport" target="main">课程导出</a></p>
			<p><a href="<%=basePath%>/GetCourse" target="main">课程查询</a></p>
		</div>
	</div>

</body>
</html>