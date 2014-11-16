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
<title>添加收付费项</title>
</head>
<body>
	<form action="save.do" method="post">
		<table width="90%" cellspacing="0" cellpadding="8" align="center" style="margin-top:20px">
			<tbody>
				<tr >
	                <td colspan="2" align="center">添加收付费项</td>
	            </tr>
				<tr>
					<td>收费项名称 :</td>
					<td><input type="text" name="itemcode" value="${role.rolecode }" tip="收费项名称[必须填写]"></td>
				</tr>
				<tr >
					<td>收费备注:</td>
					<td>
						<input name="rolememo" type="text" id="chargeremar" value="${role.rolememo }" />
					</td>
				</tr>
				<tr>
					<td>是否按表计费：</td>
					<td><input name="" type="text" value="" /></td>
				</tr>
				<tr>
					<td>按表计费类型：</td>
					<td><input name="" type="text" value="" /></td>
				</tr>
				<tr>
					<td>收费项目排序：</td>
					<td><input name="" type="text" value="" /></td>
				</tr>
				<h3>收费标准内容</h3>
				<tr>
					<td>费用类型:</td>
					<td><select name="itemtype">
						<option value="1">收入</option>
						<option value="2">支出</option>
					</select></td>
				</tr>
				<tr>
					<td>费用类别：</td>
					<td><select name="itemcatagory">
						<option value="1">正常</option>
						<option value="2">押金</option>
						<option value="2">预收款</option>
					</select></td>
				</tr>
				<tr>
					<td>计算方式：</td>
					<td><select name="itemmode">
						<option value="1">使用面积</option>
						<option value="2">个数</option>
						<option value="3">建筑面积</option>
					</select></td>
				</tr>
				<tr>
					<td>计算单位：</td>
					<td><select name="itemunit">
						<option value="1">按次收费</option>
						<option value="2">按天收费</option>
						<option value="3">按月收费</option>
						<option value="4">按年收费</option>
					</select></td>
				</tr>
				<tr>
					<td>收费方式：</td>
					<td><input name="chargecatagory" type="text" value="" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input name="" type="text" value="" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input name="" type="text" value="" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input name="" type="text" value="" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input name="" type="text" value="" /></td>
				</tr>
				<tr >
					<td colspan="2" align="center">
						<button type="submit"><span class="ok">保存</span></button>
						<button type="button" onclick="closepage()">关闭</button>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>