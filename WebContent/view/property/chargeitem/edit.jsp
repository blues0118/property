<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
			closepage();
			alert(result);
		}
		
	})
</script>
<title>添加收付费项</title>
</head>
<body>
	<form name="form1" action="save.do" method="post">
		<input type="hidden" id="_id" name="id" value="${chargeitem.id }" />
		<input type="hidden" id="_itemcontent" name="itemcontent" value="" />
		<table width="90%" cellspacing="0" cellpadding="8" align="center" style="margin-top:20px">
			<tbody>
				<tr >
	                <td colspan="4" align="center">修改收付费项</td>
	            </tr>
				<tr>
					<td>收费项目排序：</td>
					<td><input name="itemsort" type="text" value="${chargeitem.itemsort }" /></td>
					<td>是否按表计费：</td>
					<td><select id="iswatch" name="iswatch" onchange="selIswatch(this.value)">
						<option <c:if test="${chargeitem.iswatch == 0 }">selected="selected"</c:if> value="0">否</option>
						<option <c:if test="${chargeitem.iswatch == 1 }">selected="selected"</c:if> value="1">是</option>
					</select></td>
				</tr>
				<tr>
					<td>收费项名称 :</td>
					<td colspan="3"><input type="text" name="itemcode" value="${chargeitem.itemcode }" tip="收费项名称[必须填写]"></td>
				</tr>
				
				<tr><td colspan="4">
					<table id="_iswatch_No" width="100%" cellspacing="0" cellpadding="8" align="center">
						<tr>
							<td>费用类型:</td>
							<td><select id="itemtype" name="itemtype">
								<option <c:if test="${itemcontent.itemtype == 1 }">selected="selected"</c:if> value="1">收入</option>
								<option <c:if test="${itemcontent.itemtype == 2 }">selected="selected"</c:if> value="2">支出</option>
							</select></td>
							
							<td>费用类别：</td>
							<td><select id="itemcatagory" name="itemcatagory">
								<option <c:if test="${itemcontent.itemcatagory == 1 }">selected="selected"</c:if> value="1">正常</option>
								<option <c:if test="${itemcontent.itemcatagory == 2 }">selected="selected"</c:if> value="2">押金</option>
								<option <c:if test="${itemcontent.itemcatagory == 3 }">selected="selected"</c:if> value="3">预收款</option>
							</select></td>
						</tr>
						<tr>
							<td>计算方式：</td>
							<td><select id="itemmode" name="itemmode">
								<option <c:if test="${itemcontent.itemmode == 1 }">selected="selected"</c:if> value="1">使用面积</option>
								<option <c:if test="${itemcontent.itemmode == 2 }">selected="selected"</c:if> value="2">个数</option>
								<option <c:if test="${itemcontent.itemmode == 3 }">selected="selected"</c:if> value="3">建筑面积</option>
							</select></td>
							<td>计算单位：</td>
							<td><select id="itemunit" name="itemunit">
								<option <c:if test="${itemcontent.itemunit == 1 }">selected="selected"</c:if> value="1">按次收费</option>
								<option <c:if test="${itemcontent.itemunit == 2 }">selected="selected"</c:if> value="2">按天收费</option>
								<option <c:if test="${itemcontent.itemunit == 3 }">selected="selected"</c:if> value="3">按月收费</option>
								<option <c:if test="${itemcontent.itemunit == 4 }">selected="selected"</c:if> value="4">按年收费</option>
							</select></td>
						</tr>
						<tr>
							<td>收费方式：</td>
							<td><select id="chargecatagory" name="chargecatagory">
								<option <c:if test="${itemcontent.chargecatagory == 1 }">selected="selected"</c:if> value="1">周期性</option>
								<option <c:if test="${itemcontent.chargecatagory == 2 }">selected="selected"</c:if> value="2">一次性</option>
								<option <c:if test="${itemcontent.chargecatagory == 3 }">selected="selected"</c:if> value="3">临时性</option>
								<option <c:if test="${itemcontent.chargecatagory == 4 }">selected="selected"</c:if> value="4">季节性</option>
							</select></td>
							<td>收费单价：</td>
							<td><input id="chargeprice" name="chargeprice" type="text" value="${itemcontent.chargeprice }" />
								<select id="chargepriceunit" name="chargepriceunit">
									<option <c:if test="${itemcontent.chargepriceunit == 1 }">selected="selected"</c:if> value="1">日</option>
									<option <c:if test="${itemcontent.chargepriceunit == 2 }">selected="selected"</c:if> value="2">月</option>
									<option <c:if test="${itemcontent.chargepriceunit == 3 }">selected="selected"</c:if> value="3">年</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>收费周期：</td>
							<td><input id="chargeperiod" name="chargeperiod" type="text" value="${itemcontent.chargeperiod }" />
								<select id="chargeperiodunit" name="chargeperiodunit">
									<option <c:if test="${itemcontent.chargeperiodunit == 1 }">selected="selected"</c:if> value="1">日</option>
									<option <c:if test="${itemcontent.chargeperiodunit == 2 }">selected="selected"</c:if> value="2">月</option>
									<option <c:if test="${itemcontent.chargeperiodunit == 3 }">selected="selected"</c:if> value="3">年</option>
								</select>
							</td>
						</tr>
					</table>
					<table id="_iswatch_Yes" width="100%" cellspacing="0" cellpadding="8" align="center">
						<tr>
							<td>按表计费类型：</td>
							<td><select name="watchtype" id="watchtype">
								<option <c:if test="${chargeitem.watchtype == 0 }">selected="selected"</c:if> value="0">水</option>
								<option <c:if test="${chargeitem.watchtype == 1 }">selected="selected"</c:if> value="1">电</option>
								<option <c:if test="${chargeitem.watchtype == 2 }">selected="selected"</c:if> value="2">燃气</option>
							</select></td>
							<td>单价：</td>
							<td><input id="watch_price" name="watch_price" type="text" value="${itemcontent.watch_price }" /></td>
						</tr>
					</table>
				</td></tr>
				<tr >
					<td>收费备注:</td>
					<td colspan="3">
						<input name="chargeremark" id="chargeremark" type="text" value="${chargeitem.chargeremark }" />
					</td>
				</tr>
				
				<tr >
					<td colspan="4" align="center">
						<button type="button" onclick="act()"><span class="ok">保存</span></button>
						<button type="button" onclick="closepage()">关闭</button>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	
	<script type="text/javascript">

		$(function() {
			var iswatch = '${chargeitem.iswatch}';
			///$('input').attr("readonly",true);
			//$("select").attr("disabled", "disabled");
			selIswatch(iswatch);
		});

		//是否按表收费
		function selIswatch(v){
			if(v == 0){
				$("#_iswatch_No").show();
				$("#_iswatch_Yes").hide();
				$("#chargeremark").removeAttr("readonly");
				$("#chargeprice").removeAttr("readonly");
			}else{
				$("#_iswatch_No").hide();
				$("#_iswatch_Yes").show();
				$("#chargeremark").removeAttr("readonly");
				$("#watch_price").removeAttr("readonly");
			}
			
			
		}

		//收费内容
		function itemCont(){
			var iswatch = $("#iswatch").val();
			var itemArr = new Array();
			if(iswatch == 0){
				var item = {};
				item.itemtype = $("#itemtype").val();
				item.itemcatagory = $("#itemcatagory").val();
				item.itemmode = $("#itemmode").val();
				item.itemunit = $("#itemunit").val();
				item.chargecatagory = $("#chargecatagory").val();
					
				item.chargeprice = $("#chargeprice").val();
				item.chargepriceunit = $("#chargepriceunit").val();
				
				item.chargeperiod = $("#chargeperiod").val();
				item.chargeperiodunit = $("#chargeperiodunit").val();
				
				itemArr.push(item);
				
			}else{
				var item = {};
				item.watch_price = $("#watch_price").val();
				
				itemArr.push(item);
			}
			var content = JSON.stringify(itemArr);
			$("#_itemcontent").val(content);
		}

		//提交表单
		function act(){
			itemCont();
			var id = $("#_id").val();
			if("" == id)
				form1.action = "save.do";
			else
				form1.action = "update.do";
			
			form1.submit();
		}
		
	</script>
</body>
</html>