<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>修改角色</title>
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
			alert(result);
		}
	})
</script>
</head>
<body>
	<form action="update.do" method="post">
		<table width="90%" cellspacing="0" cellpadding="8" align="center" style="margin-top:20px">
			<tbody>
				<tr >
	                <td colspan="2" align="center">修改角色</td>
	                <input name="id" type="hidden" id="" value="${role.id }"  />
	            </tr>
				<tr>
					<td>角色名称 :</td>
					<td><input type="text" name="rolecode" value="${role.rolecode }" reg="^.+$" tip="角色名称[必须填写]"></td>
				</tr>
				<tr >
					<td>角色描述 :</td>
					<td>
						<input name="rolememo" type="text" id="rolememo" value="${role.rolememo }" tip="角色描述[不必须填写] " />
					</td>
				</tr>
				<tr >
					<td colspan="2" align="center">
						<input type="submit" value="保存" class="save" />
						<input type="button" value="关闭" class="close" onclick="closepage()">
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>