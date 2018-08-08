<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>课程添加</title>
<link href="../../../css/style.css" type="text/css" rel="stylesheet"/>
</head>
<body>
	<center>
		<h1>课程添加</h1>
		<div class="msg">${msg}</div>
		<hr>
		<form action="<%=basePath%>/AddCourse.do" method="post" >
			<table width="400px" cellspacing="0px" cellpadding="0px" border="1px">
				<tr>
					<td>课程ID</td>
					<td><input type="text" name="courseId" placeholder="请输入3-5位数字" pattern="\\d{3,5}" required="required"></td>
				</tr>
				<tr>
					<td>课程名</td>
					<td><input type="text" name="courseName" placeholder="请输入课程名"></td>
				</tr>
				<tr>
					<td>方向</td>
					<td>
						<select name="courseType">
							<option value="Java">Java</option>
							<option value="前端">前端</option>
							<option value="Linux">Linux</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>描述</td>
					<td>
						<textarea name="description"></textarea>
					</td>
				</tr>
				<tr>
					<td>时长</td>
					<td>
						<input name="courseTime" type="text" placeholder="请输入只有两位小数的数字" pattern="\d+\.\\d{2}" required="required">
					</td>
				</tr>
				<tr>
					<td>操作人</td>
					<td>
						<input name="operator" type="text" value="${sessionScope.LoginUser}" readonly="readonly"/>
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