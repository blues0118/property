<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	
	function update() {
		var id = '${bookterm.id }';
		if (id == "") {
			alert("没有获取足够数据，请退出后，重新尝试，或与管理员联系。");
			return;
		}
		
		var bookterm = {};
		bookterm.id = id;
		bookterm.termcode = $("#termcode").val();
		bookterm.termmemo = $("#termmemo").val();
		
	    $.ajax({
	        async : true,
	        url : "${pageContext.request.contextPath}/bookterm/update.do",
	        type : 'post',
	        data:bookterm,
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
<title>修改总台账帐期</title>
</head>
<body>
	<table width="400" cellspacing="0" cellpadding="8" align="center" style="margin-top:20px">
		<tbody>
			<tr>
                <td colspan="2" align="center">修改总台账帐期</td>
            </tr>
            <tr>
				<td>总账期名称 :</td>
				<td><input type="text" id="termcode" name="termcode" value="${bookterm.termcode }"></td>
			</tr>
			<tr>
				<td>备注 :</td>
				<td><input type="text" id="termmemo" name="termmemo" value="${bookterm.termmemo }"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="button" onclick="update()">保存</button>
					<button type="button" onclick="closepage()">关闭</button>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>