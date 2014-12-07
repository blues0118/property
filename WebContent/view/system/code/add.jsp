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
<base target="_self">

<script>
	
	function closepage() {
		//获取当前窗口索引
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}

</script>
<title>添加系统代码</title>
</head>
<body>
	<form name="form1" action="save.do" method="post">
		<div>
			<input type="hidden" id="_id" name="id" value="${syscode.id }" />
		</div>
		<table width="400" cellspacing="0" cellpadding="8" align="center" style="margin-top:20px">
			<tbody>
				<tr>
	                <th colspan="2" align="center">添加系统代码</th>
	            </tr>
				<tr>
					<td>字段名称:</td>
					<td><input type="text" id="fieldcode" name="fieldcode" value="${syscode.fieldcode }" ></td>
				</tr>
				<tr>
					<td>字段中文名称 :</td>
					<td><input type="text" id="fieldcncode" name="fieldcncode" value="${syscode.fieldcncode }" ></td>
				</tr>
				<tr>
					<td>代码key:</td>
					<td><input type="text" id="fieldkey" name="fieldkey" value="${syscode.fieldkey }" ></td>
				</tr>
				<tr>
					<td>代码value :</td>
					<td><input type="text" id="fieldvalue" name="fieldvalue" value="${syscode.fieldvalue }"></td>
				</tr>
				<tr>
					<td>是否可编辑 :</td>
					<td><select name="isedit">
						<option <c:if test="${syscode.isedit == 0 }">selected="selected"</c:if> value="0">否</option>
						<option <c:if test="${syscode.isedit == 1 }">selected="selected"</c:if> value="1">是</option>
					</select></td>
				</tr>
				<tr>
					<td>排序 :</td>
					<td><input type="text" id="sort" name="sort" value="${syscode.sort }"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="button" onclick="act()">保存</button>
						<button type="button" onclick="closepage()">关闭</button>
					</td>
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