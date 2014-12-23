<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/util.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ba-resize.min.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table_main.css" type="text/css">

<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/js/jqgrid/jquery-ui/redmond/jquery-ui-1.8.2.custom.css"/>
<script src="${pageContext.request.contextPath}/js/jqgrid/jquery-ui/jquery-ui-1.8.2.custom.min.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/js/jqgrid/css/ui.jqgrid.css"/>
<script src="${pageContext.request.contextPath}/js/jqgrid/js/i18n/grid.locale-cn.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jqgrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jqgrid/js/jquery.common.archiveGrid.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jqgrid/plugins/jquery.tablednd.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jqgrid/plugins/jquery.contextmenu.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jqgrid/plugins/ui.multiselect.js" type="text/javascript"></script>

<style>
<!--
body{ height:100%; margin:0; font-size:12px; font-family:"微软雅黑";  }
a{ text-decoration:none;  font-size:12px; color:#1874CD;}
-->
</style>

<script>

	var roles = "";

	function loadComplete() {
		
	}
	function gridComplete() {
		
	}
	
	function gridSel() {
		
	}
	function gridState() {
		
	}
	
	
	$(function(){
		
	});
	
	function callback() {
		loadData();
	}
	
	jQuery.extend($.fn.fmatter, {
		accountstateFormatter: function (cellvalue, options, rowdata) {
			if (cellvalue != "0")
		    	return '<a title="帐户状态" href="javascript:;" onclick="updatestate(\''+rowdata.id+'\',0)"><font color=blue>启用</font></a>';
		    else 
		        return '<a title="帐户状态" href="javascript:;" onclick="updatestate(\''+rowdata.id+'\',1)"><font color=red>禁用</font></a>';
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
	   }
    });
	
function loadData() {
	var title = "收费项目管理";
	var pageer = "#pager";
	var colNames;
	var colModel;
	var datatype = "json";
	var page = 50;
	var size;
	var url = "${pageContext.request.contextPath}/charge/list.do";
	
	colNames = ['id','名称','费用类型', '计算方式', '计算单位','收费方式','收费单价','收费周期','按表计费','计费类型','排序','最后读数','备注'];
	colModel = [ 
			   {name:'id',index:'id',hidden:true, width:100,align:"center"},
	           {name:'itemcode',index:'itemcode', width:100,align:"center"},
	           {name:'itemcontent',index:'itemtype', width:100,align:"center",formatter:"itemcontentFormatter"},
	           {name:'itemcontent',index:'itemcatagory', width:100,align:"center",formatter:"itemcontentFormatter"},
	           {name:'itemcontent',index:'itemunit', width:100,align:"center",formatter:"itemcontentFormatter"},
	           {name:'itemcontent',index:'itemmode', width:100,align:"center",formatter:"itemcontentFormatter"},
	           {name:'itemcontent',index:'chargeprice', width:100,align:"center",formatter:"itemcontentFormatter"},
	           {name:'itemcontent',index:'chargeperiod', width:100,align:"center",formatter:"itemcontentFormatter"},
	           {name:'iswatch',index:'iswatch', width:100,align:"center"},
	           {name:'watchtype',index:'watchtype', width:100,align:"center"},
	           {name:'itemsort',index:'itemsort', width:100,align:"center"},
	           {name:'watchnumber',index:'watchnumber', width:100,align:"center"},
	           {name:'chargeremark',index:'chargeremark', width:100,align:"center"}
	];
	
	var iswatch = $("#iswatch").val();
	size = $(window).height()-120;
	var postData={iswatch:iswatch};
	
	var _option = {
			gridObject:"dataGrid",
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
}
	
	function reloadGrid() {
		jQuery("#dataGrid").trigger("reloadGrid");
	}
	
	function getGridSelectids() {
		var rownumbers = "";
		rownumbers = $("#dataGrid").jqGrid('getGridParam','selarrrow');
		return rownumbers;
	}
	
	function add(){
		//调用account.util.js里的添加帐户方法。
		parent.account.util.add();
	}
	function refresh() {
		reloadGrid();
	}


</script>


<!--内容部分开始-->

		<div class="top_dd" style="margin-bottom: 10px;position:relative;z-index:5555;">
			<input type="hidden" id="unitid" value="${chargeitem.unitid }">
			<input type="hidden" id="iswatch" value="${chargeitem.iswatch }">
			<div class="dqwz_l">当前位置：物业管理－收费项目管理</div>
			<div  class="caozuoan">
				[ <a href="#" onclick="add()">确定添加</a> ]
				[ <a href="#" onclick="refresh()">刷新列表</a> ]
	         </div>
	         <div style="clear:both"></div>
	    </div>
		<div class="scrollTable" align="left" style="padding-left:5px; padding-right: 8px;" >
			<table id="dataGrid"></table>
			<div id="pager"></div>
		</div>
