<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>管理员查询</title>
</head>
<body>
	<center>
		<h1>管理员查询</h1>
		<div class="msg">${msg}</div>
		<hr>
		<table cellspacing="0px" cellpadding="0px" border="1px" width="600px">
			<thead>
				<tr>
					<th>用户名</th>
					<th>密码</th>
					<th>类型</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${allUsers}" var="user">
					<tr>
						<td>${user.name}</td>
						<td>${user.password}</td>
						<td>${user.type}</td>
						<c:choose>
							<c:when test="${user.name=='imooc'}">
								<td>-</td>
							</c:when>
							<c:otherwise>
								<td><a href="<%=basePath%>/DeleteUser?username=${user.name}">删除</a></td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</center>
</body>
</html>