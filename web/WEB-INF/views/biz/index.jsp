<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../../css/style.css" type="text/css" rel="stylesheet"/>
<title>登录页面</title>
<style type="text/css">
	.code 
	{
	 border:0;
	 padding:2px 3px;
	 float:left;
	 cursor:pointer;
	 width:40px;
	 height:20px;
	 line-height:20px;
	 text-align:center;
	 vertical-align:middle;
	}
	a 
	{
	 text-decoration:none;
	 font-size:12px;
	 color:#288bc4;
	}
	a:hover 
	{
	 text-decoration:underline;
	}
</style>
<script src="../../../js/jquery-3.3.1.js"></script>

</head>
<body >
	<center>
		<h1>用户登录</h1>
		<div class="msg">${msg}</div>
		<form action="<%=basePath%>/Login" method="post" onsubmit="return validateCode()">
			<table width="300px" cellspacing="0px" cellpadding="0px" border="1px">
				<tr>
					<td>用户名</td>
					<td colspan="2"><input type="text" name="username" placeholder="用户名为3-12位字母数字或下划线组合"></td>
				</tr>
				<tr>
					<td>密&nbsp;码</td>
					<td  colspan="2"><input type="password" name="password" placeholder="长度为5-12位字母数字或下划线组合" ></td>
				</tr>
				<tr>
					<td>验证码</td>
					<td style="border-right-style:none;">
						<input type="text" name="checkCode" placeholder="请输入验证码" id="inputCode">
					</td>
					<td style="border-left-style:none;"><img class="code" src="/VerifyCode" alt="验证码" id="code"></td>
				</tr>
				<tr>
					<td colspan="3" style="text-align:center">
						<input type="submit" value="登录">
						<input type="reset" value="取消">
					</td>
				</tr>
			</table>
		</form>
	</center>
</body>
<script>
    $(document).ready(function () {
		$("#code").click(function () {
			$(this).attr("src","<%=basePath%>/VerifyCode?time="+new Date().getTime() );
        });
    });
</script>
</html>