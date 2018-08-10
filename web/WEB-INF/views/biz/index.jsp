<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../../../css/style.css" type="text/css" rel="stylesheet"/>
<title>登录页面</title>
<style type="text/css">
    .regexerror{
        border-color: red;
        color: red;
    }
</style>
<script src="../../../js/jquery-3.3.1.js"></script>
<script src="../../../js/coderealtive.js"></script>
</head>
<body >
	<center>
		<h1>用户登录</h1>
		<div class="msg">${msg}</div>
		<form action="<%=basePath%>/Login" method="post" id="loginForm">
			<table width="300px" cellspacing="0px" cellpadding="0px" border="0px">
				<tr>
					<td>用户名</td>
					<td colspan="2">
                        <input type="text" name="username" placeholder="用户名为3-12位字母数字或下划线组合" value="as">
                        <label id="user-name-label" style="display:none; color: red; font-size: 8px">格式错误</label>
                    </td>
				</tr>
				<tr>
					<td>密&nbsp;码</td>
					<td  colspan="2">
                        <input type="password" name="password" placeholder="长度为5-12位字母数字或下划线组合" value="aw">
                        <label id="password-label" style="display:none; color: red; font-size: 8px">格式错误</label>
                    </td>
				</tr>
				<tr>
					<td>验证码</td>
					<td style="border-right-style:none;">
						<input type="text" name="checkCode" placeholder="请输入4位验证码" id="inputCode" maxlength="4">
					</td>
					<td style="border-left-style:none;"><img class="code" src="<%=basePath%>/VerifyCode" alt="验证码" id="code"></td>
				</tr>
				<tr>
					<td colspan="3" style="text-align:center">
						<input type="button" value="登录" id="login">
						<input type="reset" value="取消" >
					</td>
				</tr>
			</table>
		</form>
	</center>
</body>
<script>
</script>
</html>