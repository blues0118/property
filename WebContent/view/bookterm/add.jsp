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
	
	function save() {
		var standingbook = {};
		// 总账期名称
		standingbook.termcode = $("#termcode").val();
		// 备注
		standingbook.termmemo = $("#termmemo").val();
		// 项目id
		standingbook.projectid = document.all("projectid").value;
		
		var data = JSON.stringify(standingbook);
		// 添加画面
	    $.ajax({
	        async : true,
	        url : '${pageContext.request.contextPath}/bookterm/save.do',
	        type : 'post',
	        data:standingbook,
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
<title>添加总台账</title>
</head>
<body>
	<table width="400" cellspacing="0" cellpadding="8" align="center" style="margin-top:20px">
		<tbody>
			<tr>
                <td colspan="2" align="center">添加总台账</td>
            </tr>
			<tr>
				<td>总账期名称:</td>
				<td><input type="text" id="termcode" name="termcode" ></td>
			</tr>
			<tr>
				<td>备注:</td>
				<td><input type="text" id="termmemo" name="termmemo" ></td>
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
		<input type="hidden" name="projectid"value="${projectid}"/>
	</div>
</body>
</html>