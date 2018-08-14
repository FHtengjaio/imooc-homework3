<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<title>课程查询</title>
	<link rel="stylesheet" href="../../../css/common.css" type="text/css">
	<link rel="stylesheet" href="../../../css/table_common.css" type="text/css">
	<link rel="stylesheet" href="../../../css/showCourse_special.css" type="text/css">
	<script src="../../../js/jquery-3.3.1.js"></script>
	<script src="../../../js/showcourses.js"></script>
</head>
<body>
<div class="header">
	<span>课程管理</span>>><span>课程查询</span>>>
</div>
<div class="main_box main_box_new">
	<div class="title">课程查询</div>
	<div class="error_msg">
		<div class="message">${msg}</div>
		<div class="export_excel">
			<a href="<%=basePath%>/CourseExport.do?title=" style="display: none" id="a_exp_excel"></a>
			<button class="button_new">课程导出</button>
		</div>
	</div>
	<form action="<%=basePath%>/GetCourse.do" method="post" id="searchForm">
		<div class="tool_bar">
			<div class="size">
				<select name="size" id="size">
					<option value="5">5</option>
					<option value="10">10</option>
					<option value="15">15</option>
				</select>
			</div>
			<div class="search_box">
				搜索<input type="search" name="title" id="searchbox">
			</div>
		</div>
		<div class="tb">
			<div class="tb_head">
				<div class="head_content">课程ID</div>
				<div class="head_content">课程名称</div>
				<div class="head_content">方向</div>
				<div class="head_content desc">描述</div>
				<div class="head_content">时长</div>
				<div class="head_content last">操作人</div>
			</div>
			<div class="tb_bd">
			<c:forEach items="${allCourses}" var="course">
				<ul>
					<li>
						<div class="bd_content">${course.id}</div>
						<div class="bd_content">${course.name}</div>
						<div class="bd_content">${course.direction}</div>
						<div class="bd_content desc">${course.des}</div>
						<div class="bd_content">${course.time}</div>
						<div class="bd_content last">${course.operator}</div>
					</li>
				</ul>
			</c:forEach>
			</div>
		</div>
		<div class="tb_foot">
			<div class="search_note">
				搜索到<span id="searchedCount">${searchedCount}</span>条数据,
				共有<span id="totalCount">${totalCount}</span>条数据
			</div>
			<div class="page_link">
				<a href="javascript:void(0);" data-id="1">首页</a>
				<a href="javascript:void(0);" data-id="">上一页</a>
				<input type="text" name="page" readonly id="page" value="1">
				<a href="javascript:void(0);" data-id="">下一页</a>
				<a href="javascript:void(0);" data-id="${totalPage}" id="last_a">尾页</a>
			</div>
		</div>
	</form>
</div>
</body>
</html>