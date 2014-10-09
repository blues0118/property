<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/json2.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/util.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cxcolor/js/jquery.cxcolor.min.js"></script>
<base target="_self">

<link rel="stylesheet" href="${pageContext.request.contextPath}/js/jquery.cxcolor/css/jquery.cxcolor.css" type="text/css">
<style>
<!--
 *{ margin:0; padding:0;}
 body { font:12px/19px Arial, Helvetica, sans-serif; color:#666;}
 .tab { margin:10px;}
 .tab_menu { clear:both;}
 .tab_menu li { float:left; text-align:center; cursor:pointer; list-style:none; padding:1px 6px; margin-right:4px; background:#F1F1F1; border:1px solid #898989; border-bottom:none;}
 .tab_menu li.hover { background:#DFDFDF;}
 .tab_menu li.selected { color:#FFF; background:#6D84B4;}
 .tab_box { clear:both; border:1px solid #898989; padding:10px; }
 .hide{display:none}
-->
</style>

<script>
$(function(){
    var $div_li =$("div.tab_menu ul li");
    $div_li.click(function(){
		$(this).addClass("selected") //当前<li>元素高亮
			   .siblings().removeClass("selected");  //去掉其它同辈<li>元素的高亮
        var index =  $div_li.index(this);  // 获取当前点击的<li>元素 在 全部li元素中的索引。
		$("div.tab_box > div") //选取子节点。不选取子节点的话，会引起错误。如果里面还有div
				.eq(index).show()   //显示 <li>元素对应的<div>元素
				.siblings().hide(); //隐藏其它几个同辈的<div>元素
	}).hover(function(){
		$(this).addClass("hover");
	},function(){
		$(this).removeClass("hover");
	});
    $('#unitcolor').cxColor();
});

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
            	alert("更新完毕。");
            } else {
            	alert("可能因为您长时间没有操作，或读取数据时出错，请关闭浏览器，重新登录尝试或与管理员联系!！");
            }
        }
    });
}
function closepage() {
	window.returnValue="ok";
	window.close();
}

</script>
<title>添加单元信息</title>
</head>
<body>
	 <div class="tab">
		<div class="tab_menu">
			<ul>
				<li class="selected">单元信息</li>
				<li>住户资料</li>
				<li>业主资料</li>
				<li>合同协议图片</li>
				<li>备注信息</li>
				<li>押金记录</li>
				<li>预收款记录</li>
				<li>抄表记录</li>
				<li>信息变更记录</li>
				<li>结算历史记录</li>
			</ul>
		</div>
		<div class="tab_box">
			 <div>
			 		<form id="inputForm" action="${pageContext.request.contextPath}/property/save.do" method="post" >
			 		<input type="hidden" name="projeuctid" value="${projeuctid}">
			 		<input type="hidden" name="rowNum" value="${rowNum}">
					<table cellspacing="10" cellpadding="0" align="center" >
						<tbody>
							<tr>
								<td width="50">单元编号</td>
								<td><input type="text" id="unitcode" name="unitcode" size="10" ></td>
								<td width="50" align="right">状态</td>
								<td>
									<select name="unitstate">  
										<option>空</option>
										<option selected="selected" >已入住</option>
										<option>损坏</option>
										<option>其他</option>
									</select>
								</td>
								<td width="50">单元类型</td>
								<td>
									<select name="unittype">  
										<option selected="selected">租用</option>
										<option>自用</option>
									</select>
								</td>
								<td width="50">单元用途</td>
								<td>
									<select>  
										<option selected="selected">营业</option>
										<option>住宿</option>
										<option>仓库</option>
										<option>公司</option>
									</select>
								</td>
							</tr>
							<tr>
								<td>单元名称</td>
								<td colspan="3"><input type="text" id="" name="" size="42" ></td>
								<td>合同开始</td>
								<td ><input type="text" id="" name="" size="10" ></td>
								<td>合同到期</td>
								<td ><input type="text" id="" name="" size="10" ></td>
							</tr>
							<tr>
								<td>套内面积</td>
								<td ><input type="text" id="using_area" name="using_area" size="10" ></td>
								<td>建筑面积</td>
								<td ><input type="text" id="building_area" name="building_area" size="10" ></td>
								<td>单元位置</td>
								<td ><input type="text" id="" name="" value="${rowNum}" readonly="readonly" size="10" ></td>
								<td>合同编号</td>
								<td ><input type="text" id="" name="" size="10" ></td>
							</tr>
							<tr>
								<td>单元颜色</td>
								<td><input type="text" id="unitcolor" name="unitcolor" size="10" ></td>
								<td colspan="2" align="left"><input type="checkbox" style="margin:5px;" name="ischarge">不进行收费</td>
								<td colspan="4" align="center">
									<button type="button" onclick="save()">保存</button>
									<button type="button" onclick="closepage()">关闭</button>
								</td>
							</tr>
						</tbody>
					</table>
					</form>
			 </div>
			 <div class="hide">2</div>
			 <div class="hide">3</div>
			 <div class="hide">4</div>
			 <div class="hide">5</div>
			 <div class="hide">6</div>
			 <div class="hide">7</div>
			 <div class="hide">8</div>
			 <div class="hide">9</div>
		</div>
	</div>
	
</body>
</html>