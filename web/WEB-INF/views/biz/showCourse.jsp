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
		<form action="<%=basePath%>/GetCourse.do" method="post" id="search">
			<div style="float:left" id="count">
				显示
				<select name="recordCount">
					<option value="5" selected="selected">5</option>
					<option value="10">10</option>
				</select>
			</div>
			<div style="float:right; ">搜索<input type="search" name="title" id="searchbox" value="${title}"></div>
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
			<div style="float:left">查询到${searchedCount}条记录 (全部记录数${totalCount}条)</div>
			<div style="float:right">
				<a href="javascript:setPage(1)" id="1">第一页</a>
				<a href="javascript:setPage(2)" id="2">上一页</a>
				<input type="text" value="${currentPage}" name="page" style="width: 20px" id="page" readonly="readonly">
				<a href="javascript:setPage(3)" id="3">下一页</a>
				<a href="javascript:setPage(4)" id="4">最后一页</a>
			</div>
			<div>${msg}</div>
		</form>
	</center>
</body>
<script>
    function setPage(id) {
        var oPageNode = $("#page");
        if (id === 1) {
            oPageNode.val(1);
        }else if (id === 2) {
            if (oPageNode.val() > 1) {
                oPageNode.val(oPageNode.val()-1);
            }
        }else if (id === 3) {
            if (oPageNode.val() < ${totalPage}) {
                oPageNode.val(parseInt(oPageNode.val())+1);
            }
        }else if (id === 4) {
            oPageNode.val(${totalPage});
        }
        $("#search").submit();
    }

    var flag = false;
    $('#searchbox').on('compositionstart',function(){
        flag = true;
    });

    $('#searchbox').on('compositionend',function(){
        flag = false;
        if(!flag){
            $("#search").submit();
		}
    });

    $('#searchbox').on('input propertychange', function() {
        console.log(flag);
        if (flag) {
            return;
        }
        $("#search").submit();
    });

    $(document).ready(function () {
        var val = $('#searchbox').val();
        $('#searchbox').val("").focus().val(val);
        $("select>option[value='${count}']").attr("selected","selected");
    });

    $("#count").on("change", function () {
        $("#page").val("1");
        $("#search").submit();
    });
</script>

</html>