<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/util.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ba-resize.min.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table_main.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/json2.js"></script>

<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/js/jqgrid/jquery-ui/redmond/jquery-ui-1.8.2.custom.css"/>
<script src="${pageContext.request.contextPath}/js/jqgrid/jquery-ui/jquery-ui-1.8.2.custom.min.js" type="text/javascript"></script>
<!-- ztree的css和js引入 -->
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/js/zTree/css/zTreeStyle/zTreeStyle.css"/>
<script src="${pageContext.request.contextPath}/js/zTree/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>


<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/js/jqgrid/css/ui.jqgrid.css"/>
<script src="${pageContext.request.contextPath}/js/jqgrid/js/i18n/grid.locale-cn.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jqgrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jqgrid/js/jquery.common.archiveGrid.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jqgrid/plugins/jquery.tablednd.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jqgrid/plugins/jquery.contextmenu.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jqgrid/plugins/ui.multiselect.js" type="text/javascript"></script>


<!-- 打印的js、css引入  -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/view/property/unit/property_print_util.js"></script>
<!-- 单元的操作js -->
<script src="${pageContext.request.contextPath}/view/property/unit/property_edit.js" type="text/javascript"></script>
<!-- 颜色拾取的js、css引入 end -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cxcolor/js/jquery.cxcolor.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/jquery.cxcolor/css/jquery.cxcolor.css" type="text/css">
<!-- 布局插件 -->
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/js/jquery.layout/layout-default-latest.css"/>
<script src="${pageContext.request.contextPath}/js/jquery.layout/jquery.layout-latest.js" type="text/javascript"></script>
<base target="_self">
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
        overflow: no;
        font-size: 12px;
    }
    .tableStyle{border:1px solid #08408b;border-collapse:collapse;font-size:12px;}
	.tableStyle th{border:1px solid #08408b;border-collapse:collapse;font-size:12px;}
	.tableStyle td{border:1px solid #08408b;border-collapse:collapse;font-size:12px;}
    
</style>

<script>

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
				<li>抄表记录</li>
				<li>固定收费项目</li>
				<li>抄表收费项目</li>
				<li>单元台帐</li>
				<li>收款单</li>
			</ul>
		</div>
		<div class="tab_box">
			 <div align="center" width="100%">
			 		<input type="hidden" name="projeuctid" value="${projeuctid}">
			 		<input type="hidden" name="unitid" id="unitid" value="${unit.id}">
			 		<input type="hidden" name="unittermid" id="unittermid">
			 		<input type="hidden" name="leaseid" id="leaseid" value="${lease.id }">
			 		<input type="hidden" name="rowNum" value="${rowNum}">
					<table cellspacing="10" cellpadding="0" class="tableStyle" align="center" width="100%">
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
									<button type="button" onclick="saveUnit()">保存</button>
									<button type="button" onclick="closepage()">关闭</button>
								</td>
							</tr>
						</tbody>
					</table>
			 </div>
			 <div align="center" class="hide">
			 	<table cellspacing="10" cellpadding="0" class="tableStyle" align="center" width="100%">
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
								<td align="center" colspan="4">
									<button type="button" onclick="saveLease()">保存</button>
									<button type="button" onclick="closepage()">关闭</button>
								</td>
							</tr>
						</tbody>
					</table>
			 </div>
			 <div align="center" class="hide" id="agreement_div">
			 	<table id="agreementDataGrid"></table>
				<div id="agreementPager"></div>
			 </div>
			 <div align="center" class="hide" id="meterItem_div">
			 	<table id="meterItemDataGrid"></table>
				<div id="meterItemPager"></div>
			 </div>
			 <div align="center" class = "hide" id = "chargeItem_div">
				<table id="chargeItemDataGrid"></table>
				<div id="chargeItemPager"></div>
			</div>
			 <div align="center" class="hide" id = "meterchargeItem_div">
				<table id="meterchargeItemDataGrid"></table>
				<div id="meterchargeItemPager"></div>
			</div>
			 <div align="center" class="hide" id = "standingbook_div">
			 	<div style="width:15%;float:left;width:20px">
					<ul id="treeDemo" class="ztree"></ul>
				</div>
			 	<div style="width:85%;float:right">
				 	<table id="standingbookDataGrid"></table>
					<div id="standingbookPager"></div>
				</div>
				<div style="clear:both"></div>
			 </div>
			 <div align="center" class="hide" id = "chargenote_div">
			 	<table id="chargenoteDataGrid"></table>
				<div id="chargenotePager"></div>
			 </div>
		</div>
	</div>
	<div id="print_div" style="height:0px;width:0px;overflow:hidden"></div>
	<div class="contextMenu" id="myMenu_agreement" style="display: none;">
	    <ul class="ui-corner-all">
	   	<span class="ui-widget-header ui-corner-top menu_title ">合同操作快捷方式</span>
	      <li id="add">添加</li>
	      <li id="del">删除</li>
	      <!-- <span class="menu_span"></span>
	      <li id="doc">挂接电子文件</li> -->
	    </ul>
	</div>
	<div class="contextMenu" id="myMenu_chargeitem" style="display: none;">
	    <ul class="ui-corner-all">
	   	<span class="ui-widget-header ui-corner-top menu_title ">收费项目操作快捷方式</span>
	      <li id="add">添加</li>
	      <li id="del">删除</li>
	      <!-- <span class="menu_span"></span>
	      <li id="doc">挂接电子文件</li> -->
	    </ul>
	</div>
	<div class="contextMenu" id="myMenu_standingbook" style="display: none;">
	    <ul class="ui-corner-all">
	   	<span class="ui-widget-header ui-corner-top menu_title ">单元台帐操作快捷方式</span>
	      <li id="add">添加</li>
	      <li id="del">删除</li>
	      <!-- <span class="menu_span"></span>
	      <li id="doc">挂接电子文件</li> -->
	    </ul>
	</div>
</form>
</body>
</html>