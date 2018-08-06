<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>课程查询</title>
<!-- 分页查看 -->
<script src="../../../js/jquery-3.3.1.js"></script>
</head>
<body>
	<center>
		<h1>课程查询</h1>
		<hr>
		<form>
			<div style="float:left">
				显示
				<select name="recordCount">
				<option value="5">5</option>
				<option value="10">10</option>
			</select>
			</div>
			<div style="float:right; ">搜索<input type="search" name="title"></div>
			<input type="hidden" value="">
		<table cellspacing="0px" cellpadding="0px" border="1px" width="100%" class="tablelist" id="example">
			<thead>
				<tr>
					<th>课程ID</th>
					<th>课程名</th>
					<th>方向</th>
					<th>描述</th>
					<th>时长(小时)</th>
					<th>操作人</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${allCourses}" var="course">
					<tr>
						<td>${course.id}</td>
						<td>${course.name}</td>
						<td>${course.direction}</td>
						<td>${course.des}</td>
						<td>${course.time}</td>
						<td>${course.operator}</td>
					</tr>
			</c:forEach>
			</tbody>
		</table>
			<div style="float:left">总记录数为${searchedCount}条 (全部记录数${totalCount}条)</div>
			<div style="float:right">
				<a href="javascript:setPage()" id="1">第一页</a>
				<a href="javascript:setPage()" id="2">上一页</a>
				<input type="text" value="${currentPage}" name="page" style="width: 20px" id="page">
				<a href="javascript:setPage()" id="3">下一页</a>
				<a href="javascript:setPage()" id="4">最后一页</a>
			</div>
		</form>
	</center>
	
</body>
<script>
    function setPage(id) {
        var oPageNode = $("page");
        if (id === 1) {
            oPageNode.val(1);
        }else if (id === 2) {
            if (oPageNode.val() > 1) {
                oPageNode.val(oPageNode.val()-1);
            }
        }else if (id === 3) {
            if (oPageNode.val() < ${totalPage}) {
                oPageNode.val(oPageNode.val()+1);
            }
        }else if (id === 4) {
            oPageNode.val(${totalPage});
        }
    }
</script>
</html>