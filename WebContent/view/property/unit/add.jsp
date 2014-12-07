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

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cxcolor/js/jquery.cxcolor.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/jquery.cxcolor/css/jquery.cxcolor.css" type="text/css">
<base target="_self">




<script>
$(function(){
	 $('#unitcolor').cxColor();
})

function save() {
		
    $.ajax({
        async : true,
        url : "${pageContext.request.contextPath}/property/save.do",
        type : 'post',
        data:$('#inputForm').serialize(),
        dataType : 'text',
        success : function(data) {
        	sessionOut(data);
            if (data == "success") {
            	alert("更新完毕，可继续录入，请修改单元位置等信息后保存");
            }else if(data == "db_exist") {
            	alert("当前楼层当前位置已存在，请勿重复添加！");
            }else {
            	alert("可能因为您长时间没有操作，或读取数据时出错，请关闭浏览器，重新登录尝试或与管理员联系!！");
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
<title>添加单元信息</title>
</head>
<body>
		<div class="tab_box">
			 <div>
			 		<form id="inputForm" action="${pageContext.request.contextPath}/property/save.do" method="post" >
			 		<input type="hidden" name="projeuctid" value="${projeuctid}">
					<table width="800" cellspacing="0" cellpadding="8" align="center" style="margin-top:20px">
						<tbody>
							<tr>
				                <td colspan="6" align="center">添加单元</td>
				            </tr>
							<tr>
								<td>单元编号</td>
								<td><input type="text" id="unitcode" name="unitcode" size="10" ></td>
								<td>建筑面积(㎡)</td>
								<td><input type="text" id="building_area" name="building_area" size="10" ></td>
								<td>使用面积(㎡)</td>
								<td><input type="text" id="using_area" name="using_area" size="10" ></td>
							</tr>
							<tr>
								<td>单元状态</td>
								<td>
									<select name="unitstate">  
										<option value="空">空</option>
										<option value="已入住" selected="selected" >已入住</option>
										<option value="损坏">损坏</option>
										<option value="其它">其它</option>
									</select>
								</td>
								<td>单元类型</td>
								<td>
									<select name="unittype">  
										<option value="租用" selected="selected">租用</option>
										<option value="自用">自用</option>
									</select>
								</td>
								<td>是否收费</td>
								<td>
									<select name="ischarge">  
										<option value="1" selected="selected">收费</option>
										<option value="0">不收费</option>
									</select>
								</td>
							</tr>
							<tr>
								<td>单元行排序</td>
								<td ><input type="text" id="unitrowsort" name="unitrowsort" size="10" ></td>
								<td>单元排序</td>
								<td ><input type="text" id="unitsort" name="unitsort" size="10"></td>
								<td>背景颜色</td>
								<td >
									<input type="text" id="unitcolor" name="unitcolor" size="10" >
								</td>
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