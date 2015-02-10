<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/json2.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/util.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/view/property/chargeitem/remindBatch_util.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/view/property/chargeitem/remindBatch.js"></script>
<base target="_self">
<script>
	
	function closepage() {
		//获取当前窗口索引
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}
</script>
<title>收款单打印编辑</title>
</head>
<body>
	<form name="form1" action="save.do" method="post">
		<div>
			<input type="hidden" id="projeuctId" value="${projectid }">
			<input type="hidden" id="unitid" value="${unitid }">
			<input type="hidden" id="iswatch" value="">
		</div>
		<table width="800" cellspacing="0" cellpadding="8" text-align="center" style="margin-top:20px">
			<tbody id="dataTableTbody">
				<tr>
					<th colspan="8">收款单编辑</th>
				</tr>
				<tr>
					<th>单元编号</th>
					<th>费用名称</th>
					<th>起~止</th>
					<th>数量</th>
					<th>单价</th>
					<th>金额</th>
					<th>备注</th>
					<th>操作</th>
				</tr>
			</tbody>
		</table>
	</form>
	
	<script type="text/javascript">
		//提交表单
		function act(){
			var id = $("#_id").val();
			if("" == id)
				form1.action = "save.do";
			else
				form1.action = "update.do";
			
			form1.submit();
		}
	</script>
</body>
</html>