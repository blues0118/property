<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/json2.js"></script>
<!-- //打印的js、css引入 start -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/view/property/unit/property_print_util.js"></script>
<!-- //打印的js、css引入 end -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/util.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cxcolor/js/jquery.cxcolor.min.js"></script>
<script src="${pageContext.request.contextPath}/js/layer/layer.min.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/js/jqgrid/css/ui.jqgrid.css"/>
<script src="${pageContext.request.contextPath}/js/jqgrid/js/i18n/grid.locale-cn.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jqgrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>
<base target="_self">

<link rel="stylesheet" href="${pageContext.request.contextPath}/js/jquery.cxcolor/css/jquery.cxcolor.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css" type="text/css" />
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
<style>
    .print_font {
        font-size: 10px;
        margin:0px 0px 10px 0px;
    }
    .tr_height {
        height: 20px;
    }
    .foorer_font {
        font-size: 12px;
        font-weight:blod;
    }

    .content_tr {}

    .content_tr td {
        height: 10px;
        overflow: hidden;
        font-size: 12px;
    }
</style>

<script>

//-------------增加租赁合同弹出页--------start
//创建property.util命名空间
if(typeof leaseAgreement == "undefined"){
 var leaseAgreement = {};
}

leaseAgreement.util = {};
//添加，弹出框
leaseAgreement.util.addAgreement = function(url) {
	
	$.layer({
        type: 2,
        title: '添加租赁合同',
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['850px' , '400px'],
        offset : ['150px', ''],
        iframe: {src: url},
        end: function(){
        	//调用iframe子页面的刷新方法
        	//tt.window.refresh();
        	window.location.reload();
        }
    });
}
//--------------增加租赁合同弹出页---------- end

//var unitmap = ${UnitAll};
$(function(){
	initStyle();//初始化样式
    //initData();//初始化 单元信息、住户资料、租赁合同
    
});
function initStyle(){
	var $div_li =$("div[id='tabAll'] > .tab_menu > ul > li");
	var $div_li_chargeItem =$("div[id='chargeItem'] > .tab_menu > ul > li");
    $div_li.click(function(){
		$(this).addClass("selected") //当前<li>元素高亮
			   .siblings().removeClass("selected");  //去掉其它同辈<li>元素的高亮
        var index =  $div_li.index(this);  // 获取当前点击的<li>元素 在 全部li元素中的索引。
        console.log("div_li"+index);
		$("div[id='tabAll'] > .tab_box > div") //选取子节点。不选取子节点的话，会引起错误。如果里面还有div
				.eq(index).show()   //显示 <li>元素对应的<div>元素
				.siblings().hide(); //隐藏其它几个同辈的<div>元素
	}).hover(function(){
		$(this).addClass("hover");
	},function(){
		$(this).removeClass("hover");
	});
	//收费项目
	$div_li_chargeItem.click(function(){
		$(this).addClass("selected") //当前<li>元素高亮
			   .siblings().removeClass("selected");  //去掉其它同辈<li>元素的高亮
        var index =  $div_li_chargeItem.index(this);  // 获取当前点击的<li>元素 在 全部li元素中的索引。
        console.log("div_li_chargeItem"+index);
		$("div[id='chargeItem'] > .tab_box > div") //选取子节点。不选取子节点的话，会引起错误。如果里面还有div
				.eq(index).show()   //显示 <li>元素对应的<div>元素
				.siblings().hide(); //隐藏其它几个同辈的<div>元素
	}).hover(function(){
		$(this).addClass("hover");
	},function(){
		$(this).removeClass("hover");
	});
    $('#unitcolor').cxColor();
}
//加载 单元信息、住户资料、租赁合同。抄表记录

function del(ids) {


		if (confirm("确定要删除选择的合同吗？")) {
		    $.ajax({
		        async : true,
		        url : "${pageContext.request.contextPath}/agreement/delete.do",
		        type : 'post',
		        data: {ids:ids.toString()},
		        dataType : 'text',
		        success : function(data) {
		        	sessionOut(data);
		            if (data == "success") {
		            	alert("删除完毕。");
		            	window.location.reload();
		            } else {
		            	alert("可能因为您长时间没有操作，或读取数据时出错，请关闭浏览器，重新登录尝试或与管理员联系！");
		            }
		        }
		    });
		}
		
}
function delChargeitem(ids) {


		if (confirm("确定要删除选择的收费项目吗？")) {
		    $.ajax({
		        async : true,
		        url : "${pageContext.request.contextPath}/chargeitem/delete.do",
		        type : 'post',
		        data: {ids:ids.toString()},
		        dataType : 'text',
		        success : function(data) {
		        	sessionOut(data);
		            if (data == "success") {
		            	alert("删除完毕。");
		            	window.location.reload();
		            } else {
		            	alert("可能因为您长时间没有操作，或读取数据时出错，请关闭浏览器，重新登录尝试或与管理员联系!！");
		            }
		        }
		    });
		}
		
}
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
function addAgreement(){
	var unitid = $("#unitid").val();
	var leaseid = $("#leaseid").val();
	var url = "../agreement/add.do?unitid="+unitid+"&leaseid="+leaseid+"&time="+Date.parse(new Date());
	//调用parent.util.js里的添加方法。
	leaseAgreement.util.addAgreement(url);
}
function refresh() {
	console.log("======================================================");
}
function printTZD() {
	var unitId = $("#unitid").val();
    $.ajax({
        async : true,
        url : "${pageContext.request.contextPath}/meterItem/listMeteritem.do",
        type : 'post',
        data:{unitId:unitId.toString()},
        dataType : 'text',
        success : function(data) {
        	sessionOut(data);
        	create_print_data(data);
        }
    });
}
</script>
<title>添加单元信息</title>
</head>
<body>
<form id="inputForm" action="${pageContext.request.contextPath}/property/save.do" method="post" >
	 <div class="tab" id="tabAll">
		<div class="tab_menu">
			<ul>
				<li class="selected">单元信息</li>
				<li>住户资料</li>
				<li>租赁合同</li>
				<li>收费账期</li>
				<li>抄表记录</li>
				<li>收费项目</li>
				<li>单元台帐</li>
				<li>报表打印</li>
			</ul>
		</div>
		<div class="tab_box">
			 <div align="center" width="100%">
			 		<input type="hidden" name="projeuctid" value="${projeuctid}">
			 		<input type="hidden" name="unitid" id="unitid" value="${unit.id}">
			 		<input type="hidden" name="leaseid" id="leaseid" value="${lease.leasename }">
			 		<input type="hidden" name="rowNum" value="${rowNum}">
					<table cellspacing="10" cellpadding="0" align="center" >
						<tbody>
							<tr>
								<td>单元编号</td>
								<td><input type="text" id="unitcode" name="unitcode" value="${ unit.unitcode}" size="10" ></td>
								<td>建筑面积(㎡)</td>
								<td><input type="text" id="building_area" name="building_area" value="${ unit.building_area}" size="10" ></td>
								<td>使用面积(㎡)</td>
								<td><input type="text" id="using_area" name="using_area" value="${ unit.using_area}" size="10" ></td>
							</tr>
							<tr>
								<td>单元状态</td>
								<td>
									<select name="unitstate" id="unitstate" value="${ unit.unitstate}">  
										<option value="空">空</option>
										<option value="已入住" selected="selected" >已入住</option>
										<option value="损坏">损坏</option>
										<option value="其它">其它</option>
									</select>
								</td>
								<td>单元类型</td>
								<td>
									<select name="unittype" id="unittype"  value="${ unit.unittype}">  
										<option value="租用" selected="selected">租用</option>
										<option value="自用">自用</option>
									</select>
								</td>
								<td>是否收费</td>
								<td>
									<select name="ischarge" id="ischarge" value="${ unit.ischarge}">  
										<option value="1" selected="selected">收费</option>
										<option value="0">不收费</option>
									</select>
								</td>
							</tr>
							<tr>
								<td>单元行排序</td>
								<td ><input type="text" id="unitrowsort" name="unitrowsort" value="${ unit.unitrowsort}" size="10" ></td>
								<td>单元排序</td>
								<td ><input type="text" id="unitsort" name="unitsort" value="${ unit.unitsort}" size="10"></td>
								<td>背景颜色</td>
								<td >
									<input type="text" id="unitcolor" name="unitcolor" value="${ unit.unitcolor}" size="10" >
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
			 </div>
			 <div align="center" class="hide">
			 	<table cellspacing="10" cellpadding="0" align="center" >
						<tbody>
							<tr>
								<td width="100px">住户姓名</td>
								<td width="150px"><input type="text" id="leasename" name="leasename" value="${lease.leasename }" size="10" ></td>
								<td width="100px">住户单位</td>
								<td width="150px"><input type="text" id="leaseunit" name="leaseunit" value="${lease.leaseunit }" size="10" ></td>
							</tr>
							<tr>
								<td>证件类型</td>
								<td>
									<select name="leasetype"  id="leasetype" value="${lease.leasetype }">  
										<option value="居民身份证" selected="selected">居民身份证</option>
										<option value="护照">护照</option>
										<option value="警官证">警官证</option>
										<option value="学生证">学生证</option>
										<option value="驾驶证">驾驶证</option>
									</select>
								</td>
								<td>证件号码</td>
								<td>
									<input type="text" id="leasenumber" name="leasenumber" value="${lease.leasenumber }" size="10" >
								</td>
							</tr>
							<tr>
								<td>电话号码</td>
								<td ><input type="text" id="phonenumber" name="phonenumber" value="${lease.phonenumber }" size="10" ></td>
								<td>手机号码</td>
								<td ><input type="text" id="mobilephone" name="mobilephone" value="${lease.mobilephone }" size="10"></td>
							</tr>
							<tr>
								<td colspan="3"></td>
								<td align="center">
									<button type="button" onclick="save()">保存</button>
									<button type="button" onclick="closepage()">关闭</button>
								</td>
							</tr>
						</tbody>
					</table>
			 </div>
			 <div align="center" class="hide">
			 	<table cellspacing="10" cellpadding="0" align="center" >
			 			<thead>
			 				<tr>
								<td width="100px">序号</td>
								<td width="100px">合同编号</td>
								<td width="100px">开始日期</td>
								<td width="100px">结束日期</td>
								<td width="100px">住户姓名</td>
								<td width="100px">证件类型</td>
								<td width="100px">证件号码</td>
								<td width="100px">电话号码</td>
								<td width="100px">手机号码</td>
								<td width="100px">操作</td>
							</tr>
			 			</thead>
						<tbody id="agreements">
							<c:forEach var="agreement" items="${agreementList}" varStatus="s">
								<tr>
									<td width="100px"><c:out value="${s.index}" /></td>
									<td width="100px"><c:out value="${agreement.leasecode}" /></td>
									<td width="100px"><c:out value="${agreement.beingdate}" /></td>
									<td width="100px"><c:out value="${agreement.enddate}" /></td>
									<td width="100px"><c:out value="${lease.leasename}" /></td>
									<td width="100px"><c:out value="${lease.leasetype}" /></td>
									<td width="100px"><c:out value="${lease.leasenumber}" /></td>
									<td width="100px"><c:out value="${lease.phonenumber}" /></td>
									<td width="100px"><c:out value="${lease.mobilephone}" /></td>
									<td width="100px">
										<button type="button" onclick="save('${agreement.id}')">上传</button>
										<button type="button" onclick="del('${agreement.id}')">删除</button>
									</td>
								</tr>
							</c:forEach>
							
							<tr>
								<td colspan="10" align="right">
									<button type="button" onclick="addAgreement()">新增</button>
								</td>
							</tr>
						</tbody>
					</table>
			 </div>
			 <div class="hide">收费账期</div>
			 <div class="hide" align="center">
				<table cellspacing="10" cellpadding="0" align="center" >
			 			<thead>
			 				<tr>
								<td width="100px">序号</td>
								<td width="100px">抄表日期</td>
								<td width="100px">项目名称</td>
								<td width="100px">上期度数</td>
								<td width="100px">本次度数</td>
								<td width="100px">抄表人</td>
							</tr>
			 			</thead>
						<tbody id="meteritems">
							
						</tbody>
					</table>
			 </div>
			 <div class="hide" id="chargeItem">
			 	<div class="tab_menu">
					<ul>
						<li class="selected">收费项目</li>
						<li>抄表收费项目</li>
					</ul>
				</div>
				<div class="tab_box">
					<div>
						<table cellspacing="10" cellpadding="0" align="center" >
					 			<thead>
					 				<tr>
										<td width="100px">序号</td>
										<td width="100px">名称</td>
										<td width="100px">费用类型</td>
										<td width="100px">计算方式</td>
										<td width="100px">计算单位</td>
										<td width="100px">收费方式</td>
										<td width="100px">收费单价</td>
										<td width="100px">收费周期</td>
										<td width="100px">排序</td>
										<td width="100px">备注</td>
										<td width="100px">操作</td>
									</tr>
					 			</thead>
								<tbody id="chargeItems">
									<c:forEach var="chargeitem" items="${chargeItemList}" varStatus="s">
										<c:if test="${chargeitem.iswatch == '0'}">
											<tr>
												<td width="100px"><c:out value="${s.index}" /></td>
												<td width="100px"><c:out value="${chargeitem.itemcode}" /></td>
												<td width="100px"><c:out value="费用类型待定" /></td>
												<td width="100px"><c:out value="计算方式待定" /></td>
												<td width="100px"><c:out value="计算单位待定" /></td>
												<td width="100px"><c:out value="收费方式待定" /></td>
												<td width="100px"><c:out value="收费单价待定" /></td>
												<td width="100px"><c:out value="收费周期待定" /></td>
												<td width="100px"><c:out value="${chargeitem.itemsort}" /></td>
												<td width="100px"><c:out value="${chargeitem.chargeremark}" /></td>
												<td width="100px">
													<button type="button" onclick="saveChargeitem('${chargeitem.id}')">修改</button>
													<button type="button" onclick="delChargeitem('${chargeitem.id}')">删除</button>
												</td>
											</tr>
										</c:if>
									</c:forEach>
									<tr>
										<td colspan="11" align="right">
											<button type="button" onclick="addChargeItem()">增加</button>
										</td>
									</tr>
								</tbody>
							</table>
					</div>
					<div class="hide">
						<table cellspacing="10" cellpadding="0" align="center" >
					 			<thead>
					 				<tr>
										<td width="100px">序号</td>
										<td width="100px">名称</td>
										<td width="100px">费用类型</td>
										<td width="100px">费用方式</td>
										<td width="100px">计算单位</td>
										<td width="100px">收费方式</td>
										<td width="100px">计算单价</td>
										<td width="100px">收费周期</td>
										<td width="100px">按表计费</td>
										<td width="100px">计费类型</td>
										<td width="100px">排序</td>
										<td width="100px">最后度数</td>
										<td width="100px">备注</td>
										<td width="100px">操作</td>
									</tr>
					 			</thead>
								<tbody id="chargeItems1">
									<c:forEach var="chargeitem" items="${chargeItemList}" varStatus="s">
										<c:if test="${chargeitem.iswatch == '1'}">
											<tr>
												<td width="100px"><c:out value="${s.index}" /></td>
												<td width="100px"><c:out value="${chargeitem.itemcode}" /></td>
												<td width="100px"><c:out value="费用类型待定" /></td>
												<td width="100px"><c:out value="计算方式待定" /></td>
												<td width="100px"><c:out value="计算单位待定" /></td>
												<td width="100px"><c:out value="收费方式待定" /></td>
												<td width="100px"><c:out value="收费单价待定" /></td>
												<td width="100px"><c:out value="收费周期待定" /></td>
												<td width="100px"><c:out value="${chargeitem.iswatch}" /></td>
												<td width="100px"><c:out value="${chargeitem.watchtype}" /></td>
												<td width="100px"><c:out value="${chargeitem.itemsort}" /></td>
												<td width="100px"><c:out value="${chargeitem.watchnumber}" /></td>
												<td width="100px"><c:out value="${chargeitem.chargeremark}" /></td>
												<td width="100px">
													<button type="button" onclick="saveChargeitem('${chargeitem.id}')">修改</button>
													<button type="button" onclick="delChargeitem('${chargeitem.id}')">删除</button>
												</td>
											</tr>
										</c:if>
									</c:forEach>
									<tr>
										<td colspan="14" align="right">
											<button type="button" onclick="addChargeItem()">增加</button>
										</td>
									</tr>
								</tbody>
							</table>
					</div>
				 </div>
			 </div>
			 <div class="hide">
			 	<table cellspacing="10" cellpadding="0" align="center" >
			 		<thead>
					 	<tr>
							<th width="100px" colspan="12">单元收费台帐</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td rowspan="20" width="180px">
								<c:forEach var="unitterm" items="${unittermList}" varStatus="s">
									<a href="#"><c:out value="${unitterm.unittermcode}" /></a>
								</c:forEach>
							</td>
							<td width="150px">序号</td>
							<td width="100px">项目名称</td>
							<td width="150px">计费时间</td>
							<td width="150px">收费时间</td>
							<td width="150px">上期度数</td>
							<td width="150px">本次度数</td>
							<td width="150px">单价（元）</td>
							<td width="150px">金额（元）</td>
							<td width="150px">状态</td>
							<td width="150px">备注</td>
							<td width="150px">操作</td>
						</tr>
						<tr>
							<c:forEach var="standingbook" items="${standingbookList}" varStatus="s">
								<tr>
									<td width="100px"><c:out value="${s.index}" /></td>
									<td width="100px"><c:out value="${standingbook.chargetime}" /></td>
									<td width="100px"><c:out value="${standingbook.chargetime}" /></td>
									<td width="100px"><c:out value="${standingbook.chargeovertime}" /></td>
									<td width="100px"><c:out value="${standingbook.lastnumber}" /></td>
									<td width="100px"><c:out value="${standingbook.newnumber}" /></td>
									<td width="100px"><c:out value="${standingbook.chargesum}" /></td>
									<td width="100px"><c:out value="${standingbook.chargesum}" /></td>
									<td width="100px"><c:out value="${standingbook.chargestatus}" /></td>
									<td width="100px"><c:out value="${standingbook.bookmemo}" /></td>
									<td width="100px">
										<button type="button" onclick="saveChargeitem('${chargeitem.id}')">修改</button>
										<button type="button" onclick="delChargeitem('${chargeitem.id}')">删除</button>
									</td>
								</tr>
							</c:forEach>
						</tr>
						
					</tbody>
				</table>
			 </div>
			 <div class="hide">
			 	<table cellspacing="10" cellpadding="0" align="center" >
			 		<thead>
					 	<tr>
							<th width="100px" colspan="12">报表打印</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td rowspan="20" width="180px">
								<a href="#">20140101001</a>
								<a href="#">20140101001</a>
								<a href="#">20140101001</a>
								<a href="#">20140101001</a>
								<a href="#">20140101001</a>
								<a href="#">20140101001</a>
							</td>
							<td width="150px">序号</td>
							<td width="100px">项目名称</td>
							<td width="150px">计费时间</td>
							<td width="150px">收费时间</td>
							<td width="150px">上期度数</td>
							<td width="150px">本次度数</td>
							<td width="150px">单价（元）</td>
							<td width="150px">金额（元）</td>
							<td width="150px">状态</td>
							<td width="150px">备注</td>
							<td width="150px">操作</td>
						</tr>
						<tr>
							<td width="150px">1</td>
							<td width="100px">水表1</td>
							<td width="150px">2014-11-12</td>
							<td width="150px"></td>
							<td width="150px">1000</td>
							<td width="150px">1800</td>
							<td width="150px">1.6</td>
							<td width="150px">200</td>
							<td width="150px">未收</td>
							<td width="150px">备注哦</td>
							<td width="150px">
								<button type="button" onclick="printTZD()">通知单打印</button>
								<button type="button" onclick="printTZD()">收费单打印</button>
							</td>
						</tr>
						
					</tbody>
				</table>
			 </div>
		</div>
	</div>
	<div id="print_div" style="height:0px;width:0px;overflow:hidden"></div>
</form>
</body>
</html>