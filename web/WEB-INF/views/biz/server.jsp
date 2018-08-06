<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>课程后台管理系统</title>
</head>
	<frameset rows="20%,*">
		<frame src="<%=basePath%>/TopInit"></frame>
		<frameset cols="15%,*">
			<frame src="<%=basePath%>/LeftInit"></frame>
			<frame name="main"></frame>
		</frameset>
	</frameset>
</html>