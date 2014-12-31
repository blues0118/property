<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/easyvalidator/css/validate.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyvalidator/js/easy_validator.pack.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyvalidator/js/jquery.bgiframe.min.js"></script>
<base target="_self">

<script>
	function closepage() {
		//获取当前窗口索引
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}
	$(function() {
		//获取传来的值
		var result = '${result }';
		//如果返回值不为空，说明保存了，弹出提示，刷新父页面
		if (result != "") {
			closepage();
			alert(result);
		}
		
	})
</script>
<title>修改单元台帐</title>
</head>
<body>
	<form name="form1" action="save.do" method="post">
		<input type="hidden" id="_id" name="id" value="${chargeitem.id }" />
		<input type="hidden" id="_itemcontent" name="itemcontent" value="" />
		<table width="90%" cellspacing="0" cellpadding="8" align="center" style="margin-top:20px">
			<tbody>
				<tr >
	                <td colspan="4" align="center">修改单元台帐信息</td>
	            </tr>
				<tr>
					<td>收费项目名称：</td>
					<td><input name="itemcode" type="text" value="${chargeitem.itemsort }" /></td>
					<td>是否按表收费：</td>
					<td><select id="iswatch" name="iswatch">
						<option <c:if test="${book.iswatch == 0 }">selected="selected"</c:if> value="0">否</option>
						<option <c:if test="${book.iswatch == 1 }">selected="selected"</c:if> value="1">是</option>
					</select></td>
				</tr>
				<tr>
					<td>上期读数 :</td>
					<td><input type="text" name="lastnumber" value="${book.lastnumber }" tip="上期读数[必须填写]"></td>
					<td>本次读数 :</td>
					<td><input type="text" name="newnumber" value="${book.newnumber }" tip="本次读数[必须填写]"></td>
				</tr>
				<tr>
					<td>收费金额 :</td>
					<td><input type="text" name="chargesum" value="${book.chargesum }" tip="收费金额[必须填写]"></td>
					<td>备注 :</td>
					<td><input type="text" name="bookmemo" value="${book.bookmemo }"></td>
				</tr>
				
				<tr >
					<td colspan="4" align="center">
						<button type="button" onclick="act()"><span class="ok">保存</span></button>
						<button type="button" onclick="closepage()">关闭</button>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	
	<script type="text/javascript">

		$(function() {
		
		});

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