<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="../../../js/jquery-3.3.1.js"></script>
<script>
    $(document).ready(function () {
        $("#code").click(function () {
            $(this).attr("src","<%=basePath%>/VerifyCode?time="+new Date().getTime() );
        });
    });
</script>
<link href="../../css/style.css" type="text/css" rel="stylesheet"/>
<title>管理员添加</title>
</head>
<body>
	<center>
		<h1>添加管理员</h1>
		<div class="msg">${msg}</div>
		<hr>
		<form action="<%=basePath%>/AddUser" method="post" onsubmit="">
			<table width="300px" cellspacing="0px" cellpadding="0px" border="1px">
				<input type="hidden" name="operator" value="${sessionScope.LoginUser}">
				<tr>
					<td>用户名</td>
					<td><input type="text" name="username" placeholder="用户名为3-12位字母数字或下划线组合" ></td>
				</tr>
				<tr>
					<td>密&nbsp;码</td>
					<td><input type="password" name="password" placeholder="密码长度为5-12位字母数字或下划线组合" ></td>
				</tr>
				<tr>
					<td>验证码</td>
					<td>
						<input type="text" name="checkCode" placeholder="请输入验证码" >
						<img src="<%=basePath%>/VerifyCode" id="code">
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center">
						<input type="submit" value="添加">
						<input type="reset" value="取消">
					</td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>