<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>查询管理员</title>
	<link rel="stylesheet" href="../../../css/common.css" type="text/css">
	<link rel="stylesheet" href="../../../css/table_common.css" type="text/css">
	<link rel="stylesheet" href="../../../css/selectUser_special.css" type="text/css">
	<script src="../../../js/jquery-3.3.1.js"></script>
	<script>
        $(document).ready(function () {
            $("li:odd>div").addClass("odd");
            $("li>div").on("mouseenter mouseleave", function () {
                $(this).parent().children("div").toggleClass("on");
            })
        });
	</script>
</head>
<body>
<div class="header">
	<span>用户管理</span>>><span>查询管理员</span>>>
</div>
<div class="main_box main_box_new">
	<div class="title">查询管理员</div>
	<div class="error_msg">${msg}</div>
	<div class="tb">
		<div class="tb_head">
			<div class="head_content">用户名</div>
			<div class="head_content">密码</div>
			<div class="head_content">类型</div>
			<div class="head_content last">操作</div>
		</div>
		<div class="tb_bd">
			<c:forEach items="${allUsers}" var="user">
				<ul>
					<li>
						<div class="bd_content">${user.name}</div>
						<div class="bd_content">${user.password}</div>
						<div class="bd_content">${user.type}</div>
						<c:choose>
							<c:when test="${user.name!='imooc'}">
								<div class="bd_content last"><a href="<%=basePath%>/DeleteUser.do?username=${user.name}">删除</a></div>
							</c:when>
							<c:otherwise>
								<div class="bd_content last">-</div>
							</c:otherwise>
						</c:choose>
					</li>
				</ul>
			</c:forEach>
		</div>
	</div>
</div>
</body>
</html>