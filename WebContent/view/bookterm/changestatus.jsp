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
		var barcodes  = $("#termcode").val();
		// 添加画面
	    $.ajax({
	        async : true,
	        url : '${pageContext.request.contextPath}/bookterm/savestatus.do',
	        type : 'post',
	        data:{barcodes:barcodes.toString()},
	        dataType : 'text',
	        success : function(data) {
	        	sessionOut(data);
	            if (data == "success") {
	            	alert("状态更新完毕。");
	            	closepage();
	            } else {
	            	alert("可能因为您长时间没有操作，或读取数据时出错，请关闭浏览器，重新登录尝试或与管理员联系!！");
	            }
	        }
	    });
	}

	//声明起始数
	var optionsNum = 1;
	var i = optionsNum;
	
	//插入行
	function insert_row(){
		var itemTable = document.getElementById("optionsTab");
		i++;
		R = itemTable.insertRow()
		R.id = "optionsTr" + i;
		
		//序号
		C=R.insertCell()
		C.align = "center";
		C.innerHTML=i;
		//项目名称
		C=R.insertCell()
		C.innerHTML="<input class=\"text\" type=\"text\" id=\"itemOption"+i+"\" name=\"questItemVO.itemOptions\" size=\"40\" />";
	}
</script>
<title>扫码页面</title>
</head>
<body>
	<table width="400" cellspacing="0" cellpadding="8" align="center" style="margin-top:20px">
		<tbody>
			<tr>
                <td colspan="3" align="center">扫码功能</td>
            </tr>
			<tr>
				<td>收款单条形码:</td>
				<td><input type="text" id="termcode" name="termcode" ></td>
				<td><button type="button" onclick="insert_row()">添加</button></td>
			</tr>
			<tr>
				<td colspan="3" align="center">
					<button type="button" onclick="save()">保存</button>
					<button type="button" onclick="closepage()">关闭</button>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>