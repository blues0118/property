<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	
	function saveequip() {
		var equipment = {};
		// 设备名称
		equipment.code = $("#code").val();
		// 设备描述
		equipment.memo = $("#memo").val();
		// 项目id
		equipment.projectid = document.all("projectid").value;
		
		var data = JSON.stringify(equipment);
		// 添加画面
	    $.ajax({
	        async : true,
	        url : '${pageContext.request.contextPath}/pay/saveequip.do',
	        type : 'post',
	        data:equipment,
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
<title>添加设备</title>
</head>
<body>
	<table width="400" cellspacing="0" cellpadding="8" align="center" style="margin-top:20px">
		<tbody>
			<tr>
                <td colspan="2" align="center">添加设备</td>
            </tr>
			<tr>
				<td>设备名称:</td>
				<td><input type="text" id="code" name="code" ></td>
			</tr>
			<tr>
				<td>设备描述:</td>
				<td><input type="text" id="memo" name="memo" ></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="button" onclick="saveequip()">保存</button>
					<button type="button" onclick="closepage()">关闭</button>
				</td>
			</tr>
		</tbody>
	</table>
	<div>
		<input type="hidden" name="projectid"value="${projectid}"/>
	</div>
</body>
</html>