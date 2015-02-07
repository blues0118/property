<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	
	function save() {
		var equipmentcontent = {};
		// 维护内容
		equipmentcontent.eqcontent = $("#eqcontent").val();
		// 金额
		equipmentcontent.eqsum = $("#eqsum").val();
		// 日期
		equipmentcontent.eqdate = $("#eqdate").val();
		// 设备id
		equipmentcontent.equipmentid = document.all("equipmentid").value;
		// 总台账id
		equipmentcontent.termid = $("#termid").val();
		
		var data = JSON.stringify(equipmentcontent);
		// 添加画面
	    $.ajax({
	        async : true,
	        url : '${pageContext.request.contextPath}/pay/saveequipPay.do',
	        type : 'post',
	        data:staffcontent,
	        dataType : 'text',
	        success : function(data) {
	        	sessionOut(data);
	            if (data == "success") {
	            	alert("更新完毕。");
	            } else {
	            	alert("可能因为您长时间没有操作，或读取数据时出错，请关闭浏览器，重新登录尝试或与管理员联系!！");
	            }
	        }
	    });
	}
</script>
<title>添加设备支出</title>
</head>
<body>
	<table width="400" cellspacing="0" cellpadding="8" align="center" style="margin-top:20px">
		<tbody>
			<tr>
                <td colspan="2" align="center">添加设备支出</td>
            </tr>
			<tr>
				<td>总台账账期:</td>
				<td>
					<select id="termid">
					    <option value=""></option>
						<c:forEach items="${termList}" var="item">
							<option value="${item.id}">${item.termcode}</option>
					    </c:forEach>
					</select>
                </td>
			</tr>
			<tr>
				<td>维护内容:</td>
				<td><input type="text" id="eqcontent" name="eqcontent" ></td>
			</tr>
			<tr>
				<td>金额:</td>
				<td><input type="text" id="eqsum" name="eqsum" ></td>
			</tr>
			<tr>
				<td>日期:</td>
				<td><input type="text" id="eqdate" name="eqdate" ></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="button" onclick="save()">保存</button>
					<button type="button" onclick="closepage()">关闭</button>
				</td>
			</tr>
		</tbody>
	</table>
	<div>
		<input type="hidden" name="equipmentid"value="${equipmentid}"/>
	</div>
</body>
</html>