<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<title>课程批量导入(Excel)</title>
	<link rel="stylesheet" href="../../../css/common.css" type="text/css">
	<link rel="stylesheet" href="../../../css/form_common.css" type="text/css">
	<link rel="stylesheet" href="../../../css/importCourse_special.css" type="text/css">
	<script src="../../../js/jquery-3.3.1.js"></script>
	<script src="../../../js/common.js"></script>
	<script src="../../../js/importexcel.js"></script>
</head>
<body>
<div class="header">
	<span>课程管理</span>>><span>课程批量导入(Excel)</span>>>
</div>
<div class="main_box">
	<div class="title">课程批量导入</div>
	<div class="error_msg">${msg}</div>
	<div class="form_box">
		<input type="file" style="display: none" id="excel" name="excel">
		<div class="file_btn">
			<button type="button"></button>
		</div>
	</div>
</div>
</body>
</html>