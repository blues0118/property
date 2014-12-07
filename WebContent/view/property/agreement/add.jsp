<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/util.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/util.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css" type="text/css" />
<base target="_self">




<script>
$(function(){
	$("#unitid").val("${unitId}");
	$("#leaseid").val("${leaseid}");
	console.info($("#unitid").val());
	console.info($("#leaseid").val());
});
function save() {
		
    $.ajax({
        async : true,
        url : "${pageContext.request.contextPath}/agreement/save.do",
        type : 'post',
        data:$('#inputForm').serialize(),
        dataType : 'text',
        success : function(data) {
        	sessionOut(data);
            if (data == "success") {
            	alert("保存完毕，可继续录入");
            }else {
            	alert("可能因为您长时间没有操作，或读取数据时出错，请关闭浏览器，重新登录尝试或与管理员联系！");
            }
        },
        error:function(data){
        	alert("保存失败");
        }
    });
}
function closepage() {
		//获取当前窗口索引
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}

</script>
<title>添加租赁合同</title>
</head>
<body>
		<div class="tab_box">
			 <div>
			 		<form id="inputForm" action="${pageContext.request.contextPath}/property/save.do" method="post" >
			 		<input type="hidden" name="unitid" id="unitid">
			 		<input type="hidden" name="leaseid" id="leaseid">
					<table width="800" cellspacing="0" cellpadding="8" align="center" style="margin-top:20px">
						<tbody>
							<tr>
				                <td colspan="6" align="center">添加租赁合同</td>
				            </tr>
							<tr>
								<td>合同编号</td>
								<td><input type="text" id="leasecode" name="leasecode" size="10" ></td>
								<td>开始日期</td>
								<td><input type="text" id="beingdate" name="beingdate" size="10" ></td>
								<td>结束日期</td>
								<td><input type="text" id="enddate" name="enddate" size="10" ></td>
							</tr>
							<tr>
								<td colspan="6" align="center">
									<button type="button" onclick="save()">保存</button>
									<button type="button" onclick="closepage()">关闭</button>
								</td>
							</tr>
						</tbody>
					</table>
					</form>
			 </div>
			
		</div>
	</div>
	
</body>
</html>