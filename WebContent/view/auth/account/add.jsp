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
		
		var accountcode = $("#accountcode").val();
		var accountname = $("#accountname").val();
		var accountmemo = $("#accountmemo").val();
		var accountstate = 0;
		if ($("#accountstate").is(":checked")) {
			accountstate = 1;
		}
		
		var account = {};
		account.accountcode = accountcode;
		account.accountname = accountname;
		account.accountmemo = accountmemo;
		account.accountstate = accountstate;
		
		var data = JSON.stringify(account);
		
	    $.ajax({
	        async : true,
	        url : "${pageContext.request.contextPath}/account/save.do",
	        type : 'post',
	        data:account,
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
<title>添加帐户</title>
</head>
<body>
	<table width="400" cellspacing="0" cellpadding="8" align="center" style="margin-top:20px">
		<tbody>
			<tr>
                <td colspan="2" align="center">添加帐户</td>
            </tr>
			<tr>
				<td>帐号 :</td>
				<td><input type="text" id="accountcode" name="accountcode" ></td>
			</tr>
			<tr>
				<td>帐户姓名 :</td>
				<td><input type="text" id="accountname" name="accountname" ></td>
			</tr>
			<tr>
				<td>帐户状态 :</td>
				<td><input type="checkbox" id="accountstate" name="accountstate" >启用</td>
			</tr>
			<tr>
				<td>帐户描述 :</td>
				<td><input type="text" id="accountmemo" name="accountmemo"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="button" onclick="save()">保存</button>
					<button type="button" onclick="closepage()">关闭</button>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>