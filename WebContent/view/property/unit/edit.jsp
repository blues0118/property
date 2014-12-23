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

<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/js/jqgrid/css/ui.jqgrid.css"/>
<script src="${pageContext.request.contextPath}/js/jqgrid/js/i18n/grid.locale-cn.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jqgrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jqgrid/js/jquery.common.archiveGrid.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jqgrid/plugins/jquery.tablednd.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jqgrid/plugins/jquery.contextmenu.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jqgrid/plugins/ui.multiselect.js" type="text/javascript"></script>


<!-- 打印的js、css引入  -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css" type="text/css" />
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
        overflow: hidden;
        font-size: 12px;
    }
</style>

<script>
jQuery.extend($.fn.fmatter, {
    funFormatter: function (cellvalue, options, rowdata) {
	    var result = '<button type="button" onclick="upload(\''+rowdata.id+'\')">上传</button>';
	    result += '<button type="button" onclick="delAgreement()">删除</button>';
	    return result;
    },
    itemcontentFormatter: function (cellvalue, options, rowdata) {
    	console.log("cellvalue=="+cellvalue);
    	var cellvalueJson = $.parseJSON(cellvalue);
    	if(options.colModel.index =='itemtype'){
    		if(cellvalueJson[0].itemtype =='1'){
    			return "收入";
    		}else if(cellvalueJson[0].itemtype =='2'){
    			return "支出";
    		}
    	}else if(options.colModel.index =='itemcatagory'){
    		if(cellvalueJson[0].itemtype =='1'){
    			return "正常";
    		}else if(cellvalueJson[0].itemtype =='2'){
    			return "押金";
    		}else if(cellvalueJson[0].itemtype =='2'){
    			return "预收款";
    		}
    	}else if(options.colModel.index =='itemmode'){
    		if(cellvalueJson[0].itemtype =='1'){
    			return "使用面积";
    		}else if(cellvalueJson[0].itemtype =='2'){
    			return "个数";
    		}else if(cellvalueJson[0].itemtype =='2'){
    			return "建筑面积";
    		}
    	}else if(options.colModel.index =='itemunit'){
    		if(cellvalueJson[0].itemtype =='1'){
    			return "按次收费";
    		}else if(cellvalueJson[0].itemtype =='2'){
    			return "按天收费";
    		}else if(cellvalueJson[0].itemtype =='2'){
    			return "按月收费";
    		}else if(cellvalueJson[0].itemtype =='2'){
    			return "按年收费";
    		}
    	}else if(options.colModel.index =='chargecatagory'){
    		if(cellvalueJson[0].itemtype =='1'){
    			return "周期性";
    		}else if(cellvalueJson[0].itemtype =='2'){
    			return "一次性";
    		}else if(cellvalueJson[0].itemtype =='2'){
    			return "临时性";
    		}else if(cellvalueJson[0].itemtype =='2'){
    			return "季节性";
    		}
    	}else if(options.colModel.index =='chargeprice'){
    		return cellvalueJson[0].chargeprice+"/"+cellvalueJson[0].chargepriceunit;
    	}else if(options.colModel.index =='chargeperiod'){
    		return cellvalueJson[0].chargeperiod+"/"+cellvalueJson[0].chargeperiodunit;
    	}else if(options.colModel.index =='watch_price'){
    		return cellvalueJson[0].watch_price;
    	}
	    return "";
    },
    chargeitemFunFormatter: function (cellvalue, options, rowdata) {
	    var result = '<button type="button" onclick="updateChargeitem(\''+rowdata.id+'\')">修改</button>';
	    result += '<button type="button" onclick="delChargeitem(\''+rowdata.id+'\')">删除</button>';
	    return result;
    },
    meterchargeitemFunFormatter: function (cellvalue, options, rowdata) {
	    var result = '<button type="button" onclick="updateChargeitem(\''+rowdata.id+'\')">修改</button>';
	    result += '<button type="button" onclick="delChargeitem(\''+rowdata.id+'\')">删除</button>';
	    return result;
    },
    standingbookFunFormatter: function (cellvalue, options, rowdata) {
	    var result = '<button type="button" onclick="saveChargeitem(\''+rowdata.id+'\')">修改</button>';
	    result += '<button type="button" onclick="delChargeitem(\''+rowdata.id+'\')">删除</button>';
	    return result;
    }
});
function loadAgreementData() {
	var title = "租赁合同管理";
	var pageer = "#agreementPager";
	var colNames;
	var colModel;
	var datatype = "json";
	var page = 50;
	var size;
	var url = "${pageContext.request.contextPath}/agreement/list.do";
	
	colNames = ['id','合同编号','开始日期', '结束日期', '住户姓名','证件类型','证件号码','电话号码','手机号码','操作'];
	colModel = [ 
			   {name:'id',index:'id',hidden:true, width:130,align:"center"},
	           {name:'leasecode',index:'leasecode', width:130,align:"center"},
	           {name:'beingdate',index:'beingdate', width:130,align:"center"},
	           {name:'enddate',index:'enddate', width:130,align:"center"},
	           {name:'leasename',index:'leasename', width:130,align:"center"},
	           {name:'leasetype',index:'leasetype', width:130,align:"center"},
	           {name:'leasenumber',index:'leasenumber', width:130,align:"center"},
	           {name:'phonenumber',index:'phonenumber', width:130,align:"center"},
	           {name:'mobilephone',index:'mobilephone', width:130,align:"center"}, 
	           {name:'fun',index:'fun', width:80,fixed:true,resizable:false,align:"center",frozen:true,formatter:"funFormatter"}
	];
	
	var searchTxt = $("#unitid").val();
	size = $(window).height()-120;
	var postData={searchTxt:searchTxt};
	
	var _option = {
			gridObject:"agreementDataGrid",
			url:url,
			datatype:"json",
			colNames:colNames,
			colModel:colModel,
			postData:postData,
			pageer:pageer,
			page:page,
			title:title,
			size:size
	};
	
	//创建grid
	$.loadGridData(_option);
	jQuery("#"+_option.gridObject).jqGrid(
				'navGrid',_option.pageer,
				{
					edit:false,
					add:false,
					del:false,
					search:false,
					refreshstate:'current'
				}).navButtonAdd(_option.pageer,{  
					   caption:"",   
					   buttonicon:"ui-icon-plus",
					   onClickButton: function(){   
						   addAgreement();
					   },
					   title:"添加",
					   position:"last"
				}).navButtonAdd(_option.pageer,{  
					   caption:"",   
					   buttonicon:"ui-icon-trash",
					   onClickButton: function(){   
						  delAgreement();
					   },
					   title:"删除",
					   position:"last"
				});
		
		//租赁合同右键
		agreementMenu();
		$("#agreementDataGrid").setGridWidth($("#agreement_div").width() -10);
		$('#agreement_div').css('overflow', 'hidden');
}
function loadMeterItemData() {
	var title = "抄表记录管理";
	var pageer = "#meterItemPager";
	var colNames;
	var colModel;
	var datatype = "json";
	var page = 50;
	var size;
	var url = "${pageContext.request.contextPath}/meterItem/list.do";
	
	colNames = ['id','抄表日期','项目名称', '上期度数', '本次度数','抄表人'];
	colModel = [ 
			   {name:'id',index:'id',hidden:true, width:270,align:"center"},
	           {name:'createtime',index:'createtime', width:270,align:"center"},
	           {name:'watchcode',index:'watchcode', width:270,align:"center"},
	           {name:'lastnumber',index:'lastnumber', width:270,align:"center"},
	           {name:'newnumber',index:'newnumber', width:270,align:"center"},
	           {name:'meterman',index:'meterman', width:270,align:"center"}
	];
	
	var searchTxt = $("#unitid").val();
	size = $(window).height()-120;
	var postData={searchTxt:searchTxt};
	
	var _option = {
			gridObject:"meterItemDataGrid",
			url:url,
			datatype:"json",
			colNames:colNames,
			colModel:colModel,
			postData:postData,
			pageer:pageer,
			page:page,
			title:title,
			size:size
	};
	//创建grid
	$.loadGridData(_option);
	$("#meterItemDataGrid").setGridWidth($("#meterItem_div").width() -10);
	$('#meterItem_div').css('overflow', 'hidden');
	
}
function loadChargeItemData() {
	var title = "收费项目管理";
	var pageer = "#chargeItemPager";
	var colNames;
	var colModel;
	var datatype = "json";
	var page = 50;
	var size;
	var url = "${pageContext.request.contextPath}/charge/list.do";
	
	colNames = ['id','名称','费用类型', '计算方式', '计算单位','收费方式','收费单价','收费周期','排序','备注','操作'];
	colModel = [ 
			   {name:'id',index:'id',hidden:true, width:110,align:"center"},
	           {name:'itemcode',index:'itemcode', width:110,align:"center"},
	           {name:'itemcontent',index:'itemtype', width:110,align:"center",formatter:"itemcontentFormatter"},
	           {name:'itemcontent',index:'itemcatagory', width:110,align:"center",formatter:"itemcontentFormatter"},
	           {name:'itemcontent',index:'itemmode', width:110,align:"center",formatter:"itemcontentFormatter"},
	           {name:'itemcontent',index:'itemunit', width:110,align:"center",formatter:"itemcontentFormatter"},
	           {name:'itemcontent',index:'chargecatagory', width:110,align:"center",formatter:"itemcontentFormatter"},
	           {name:'itemcontent',index:'chargeprice', width:110,align:"center",formatter:"itemcontentFormatter"},
	           {name:'itemsort',index:'itemsort', width:110,align:"center"},
	           {name:'chargeremark',index:'chargeremark', width:110,align:"center"},
	           {name:'meterman',index:'meterman', width:120,align:"center",formatter:"chargeitemFunFormatter"}
	];
	
	var unitid = $("#unitid").val();
	size = $(window).height()-120;
	var postData={unitid:unitid,iswatch:0};
	
	var _option = {
			gridObject:"chargeItemDataGrid",
			url:url,
			datatype:"json",
			colNames:colNames,
			colModel:colModel,
			postData:postData,
			pageer:pageer,
			page:page,
			title:title,
			size:size
	};
	//创建grid
	$.loadGridData(_option);
	jQuery("#"+_option.gridObject).jqGrid(
				'navGrid',_option.pageer,
				{
					edit:false,
					add:false,
					del:false,
					search:false,
					refreshstate:'current'
				}).navButtonAdd(_option.pageer,{  
					   caption:"",   
					   buttonicon:"ui-icon-plus",
					   onClickButton: function(){   
						   addChargeitem();
					   },
					   title:"添加",
					   position:"last"
				}).navButtonAdd(_option.pageer,{  
					   caption:"",   
					   buttonicon:"ui-icon-trash",
					   onClickButton: function(){   
						  delChargeitem();
					   },
					   title:"删除",
					   position:"last"
				});
		
	//租赁合同右键
	chargeitemMenu();
	$("#chargeItemDataGrid").setGridWidth($("#chargeItem_div").width() -10);
	$('#chargeItem_div').css('overflow', 'hidden');
	
}
function loadMeterchargeItemData() {
	var title = "收费项目管理";
	var pageer = "#meterchargeItemPager";
	var colNames;
	var colModel;
	var datatype = "json";
	var page = 50;
	var size;
	var url = "${pageContext.request.contextPath}/charge/list.do";
	
	colNames = ['id','名称','费用类型', '计算方式', '计算单位','收费方式','收费单价','收费周期','按表计费','计费类型','排序','最后读数','备注','操作'];
	colModel = [ 
			   {name:'id',index:'id',hidden:true, width:100,align:"center"},
	           {name:'itemcode',index:'itemcode', width:100,align:"center"},
	           {name:'itemcontent',index:'itemtype', width:100,align:"center",formatter:"itemcontentFormatter"},
	           {name:'itemcontent',index:'itemcatagory', width:100,align:"center",formatter:"itemcontentFormatter"},
	           {name:'itemcontent',index:'itemunit', width:100,align:"center",formatter:"itemcontentFormatter"},
	           {name:'itemcontent',index:'itemmode', width:100,align:"center",formatter:"itemcontentFormatter"},
	           {name:'itemcontent',index:'chargeprice', width:100,align:"center",formatter:"itemcontentFormatter"},
	           {name:'itemcontent',index:'chargeperiod', width:100,align:"center",formatter:"itemcontentFormatter"},
	           {name:'iswatch',index:'iswatch', width:100,align:"center",formatter:"itemcontentFormatter"},
	           {name:'watchtype',index:'watchtype', width:100,align:"center",formatter:"itemcontentFormatter"},
	           {name:'itemsort',index:'itemsort', width:100,align:"center",formatter:"itemcontentFormatter"},
	           {name:'watchnumber',index:'watchnumber', width:100,align:"center"},
	           {name:'chargeremark',index:'chargeremark', width:100,align:"center"},
	           {name:'meterman',index:'meterman',align:"center",formatter:"meterchargeitemFunFormatter"}
	];
	
	var unitid = $("#unitid").val();
	size = $(window).height()-120;
	var postData={unitid:unitid,iswatch:1};
	
	var _option = {
			gridObject:"meterchargeItemDataGrid",
			url:url,
			datatype:"json",
			colNames:colNames,
			colModel:colModel,
			postData:postData,
			pageer:pageer,
			page:page,
			title:title,
			size:size
	};
	//创建grid
	$.loadGridData(_option);
	jQuery("#"+_option.gridObject).jqGrid(
				'navGrid',_option.pageer,
				{
					edit:false,
					add:false,
					del:false,
					search:false,
					refreshstate:'current'
				}).navButtonAdd(_option.pageer,{  
					   caption:"",   
					   buttonicon:"ui-icon-plus",
					   onClickButton: function(){   
						   addChargeitem();
					   },
					   title:"添加",
					   position:"last"
				}).navButtonAdd(_option.pageer,{  
					   caption:"",   
					   buttonicon:"ui-icon-trash",
					   onClickButton: function(){   
						  delChargeitem();
					   },
					   title:"删除",
					   position:"last"
				});
		
	//租赁合同右键
	meterchargeitemMenu();
	$("#meterchargeItemDataGrid").setGridWidth($("#meterchargeItem_div").width() -10);
	$('#meterchargeItem_div').css('overflow', 'hidden');
	
}
function loadStandingbookData() {
	var title = "单元收费台帐";
	var pageer = "#standingbookPager";
	var colNames;
	var colModel;
	var datatype = "json";
	var page = 50;
	var size;
	var url = "${pageContext.request.contextPath}/standingbook/listStandingbook.do";
	
	colNames = ['id','项目名称','计费时间', '收费时间', '上期度数','本次度数','单价（元）','金额（元）','状态','备注','操作'];
	colModel = [ 
			   {name:'id',index:'id',hidden:true, width:100,align:"center"},
	           {name:'itemcode',index:'itemcode', width:100,align:"center"},
	           {name:'chargetime',index:'chargetime', width:100,align:"center"},
	           {name:'chargeovertime',index:'chargeovertime', width:100,align:"center"},
	           {name:'lastnumber',index:'lastnumber', width:100,align:"center"},
	           {name:'newnumber',index:'newnumber', width:100,align:"center"},
	           {name:'chargeprice',index:'chargeprice', width:100,align:"center"},
	           {name:'chargesum',index:'chargesum', width:100,align:"center"},
	           {name:'chargestatus',index:'chargestatus', width:100,align:"center"},
	           {name:'bookmemo',index:'bookmemo', width:100,align:"center"},
	           {name:'fun',index:'fun', width:100,align:"center",formatter:"standingbookFunFormatter"}
	];
	
	var searchTxt = $("#unitid").val();
	size = $(window).height()-120;
	var postData={searchTxt:searchTxt};
	
	var _option = {
			gridObject:"standingbookDataGrid",
			url:url,
			datatype:"json",
			colNames:colNames,
			colModel:colModel,
			postData:postData,
			pageer:pageer,
			page:page,
			title:title,
			size:size
	};
	//创建grid
	$.loadGridData(_option);
	$("#standingbookDataGrid").setGridWidth($("#standingbook_div").width() -10);
	$('#standingbook_div').css('overflow', 'hidden');
	
}
//增加合同记录
function addAgreement(){
	var unitid = $("#unitid").val();
	var leaseid = $("#leaseid").val();
	var url = "${pageContext.request.contextPath}/agreement/add.do?unitid="+unitid+"&leaseid="+leaseid+"&time="+Date.parse(new Date());
	var loadi = parent.layer.load(0);
	parent.$.layer({
        type: 2,
        title: '添加租赁合同',
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['850px' , '400px'],
        offset : ['150px', ''],
        iframe: {src: url},
        end: function(){
        	parent.layer.close(loadi);
        	reloadGrid();
        }
    });
}
//修改收费项目
function updateChargeitem(id){
	var url = "${pageContext.request.contextPath}/charge/editforunit.do?id="+id+"&time="+Date.parse(new Date());
	var loadi = parent.layer.load(0);
	parent.$.layer({
        type: 2,
        title: '修改收费项目',
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['850px' , '400px'],
        offset : ['150px', ''],
        iframe: {src: url},
        end: function(){
        	parent.layer.close(loadi);
        	reloadGrid();
        }
    });
}
//增加收费项目记录
function addChargeitem(){
	var unitid = $("#unitid").val();
	var leaseid = $("#leaseid").val();
	var url = "${pageContext.request.contextPath}/charge/addforunit.do?unitid="+unitid+"&iswatch=0&time="+Date.parse(new Date());
	var loadi = parent.layer.load(0);
	parent.$.layer({
        type: 2,
        title: '添加收费项目',
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['850px' , '400px'],
        offset : ['150px', ''],
        iframe: {src: url},
        end: function(){
        	parent.layer.close(loadi);
        	reloadGrid();
        }
    });
	
}
//删除合同记录
function delAgreement(){
	var gridObject;
	//设置滚动条
	setCroll('#agreementDataGrid .ui-jqgrid-bdiv','jqgrid-div');
	gridObject = "agreementDataGrid";
	
	var str = "";
	
	var rownumbers = "";
	rownumbers = $("#"+gridObject).jqGrid('getGridParam','selarrrow');
	str = getSelectid(gridObject,rownumbers);
	
	if (str == "") {
		alert("请先选择要删除的数据。");
		return;
	}
	console.log($("#contextPath"));
	if (confirm("确定要删除选中的合同吗？请谨慎操作。")) {
		var loadi = parent.layer.load(0);
		setTimeout(function () {
			$.ajax({
		        async : false,
		        url : '${pageContext.request.contextPath}/agreement/delete.do',
		        type : 'post',
		        data: {
					ids:str.toString()
				},
		        dataType : 'text',
		        success : function(data) {
		        	parent.layer.close(loadi);
					alert(data);
		        }
		    });
			reloadGrid();
		},200);  
	};
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
				<li>抄表记录</li>
				<li>收费项目</li>
				<li>单元台帐</li>
				<li>收款单</li>
				<li>报表打印</li>
			</ul>
		</div>
		<div class="tab_box">
			 <div align="center" width="100%">
			 		<input type="hidden" name="projeuctid" value="${projeuctid}">
			 		<input type="hidden" name="unitid" id="unitid" value="${unit.id}">
			 		<input type="hidden" name="leaseid" id="leaseid" value="${lease.id }">
			 		<input type="hidden" name="rowNum" value="${rowNum}">
					<table cellspacing="10" cellpadding="0" align="center" width="100%">
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
			 	<table cellspacing="10" cellpadding="0" align="center" width="100%">
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
									<button type="button" onclick="save()">保存</button>
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
			 <div class="hide" align="center" id="meterItem_div">
			 	<table id="meterItemDataGrid"></table>
				<div id="meterItemPager"></div>
			 </div>
			 <div class="hide" id="chargeItem">
			 	<div class="tab_menu">
					<ul>
						<li class="selected">收费项目</li>
						<li>抄表收费项目</li>
					</ul>
				</div>
				<div class="tab_box">
					<div class = "hide" id = "chargeItem_div">
						<table id="chargeItemDataGrid"></table>
						<div id="chargeItemPager"></div>
					</div>
					<div class="hide" id = "meterchargeItem_div">
						<table id="meterchargeItemDataGrid"></table>
						<div id="meterchargeItemPager"></div>
					</div>
				 </div>
			 </div>
			 <div class="hide">
			 	<div style = " height: 500px;width: 80px;float: left;">
			 		<table>
			 			<tr>
			 				<th>单元收费台帐</th>
			 			</tr>
			 			<c:forEach var="unitterm" items="${unittermList}" varStatus="s">
			 				<tr>
			 					<td><a href="#"><c:out value="${unitterm.unittermcode}" /></a></td>
			 				</tr>
						</c:forEach>
			 			
			 		</table>
			 	</div>
			 	<div id = "standingbook_div">
				 	<table id="standingbookDataGrid"></table>
					<div id="standingbookPager"></div>
				</div>
			 </div>
			  <div class="hide">收费账期</div>
			 <div class="hide">
			 	<table cellspacing="10" cellpadding="0" align="center" width="100%">
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
</form>
</body>
</html>