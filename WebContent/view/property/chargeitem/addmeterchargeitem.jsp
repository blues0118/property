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
        itemcontentFormatter: function (cellvalue, options, rowdata) {
	    	if(options.colModel.index =='itemtype'){
	    		if(cellvalue =='1'){
	    			return "收入";
	    		}else if(cellvalue =='2'){
	    			return "支出";
	    		}else{
	    			return '';
	    		}
	    	}else if(options.colModel.index =='itemcatagory'){
	    		if(cellvalue =='1'){
	    			return "正常";
	    		}else if(cellvalue =='2'){
	    			return "押金";
	    		}else if(cellvalue =='2'){
	    			return "预收款";
	    		}else{
	    			return '';
	    		}
	    	}else if(options.colModel.index =='itemmode'){
	    		if(cellvalue =='1'){
	    			return "使用面积";
	    		}else if(cellvalue =='2'){
	    			return "个数";
	    		}else if(cellvalue =='2'){
	    			return "建筑面积";
	    		}else{
	    			return '';
	    		}
	    	}else if(options.colModel.index =='itemunit'){
	    		if(cellvalue =='1'){
	    			return "按次收费";
	    		}else if(cellvalue =='2'){
	    			return "按天收费";
	    		}else if(cellvalue =='2'){
	    			return "按月收费";
	    		}else if(cellvalue =='2'){
	    			return "按年收费";
	    		}else{
	    			return '';
	    		}
	    	}else if(options.colModel.index =='chargecatagory'){
	    		if(cellvalue =='1'){
	    			return "周期性";
	    		}else if(cellvalue =='2'){
	    			return "一次性";
	    		}else if(cellvalue =='2'){
	    			return "临时性";
	    		}else if(cellvalue =='2'){
	    			return "季节性";
	    		}else{
	    			return '';
	    		}
	    	}else if(options.colModel.index =='chargeprice'){
	    		if(cellvalue!=null && cellvalue!=undefined){
	    			return cellvalue;//cellvalueJson[0].chargeprice+"/"+cellvalueJson[0].chargepriceunit;
	    		}else{
	    			return '';
	    		}
	    	}else if(options.colModel.index =='chargeperiod'){
	    		if(cellvalue!=null && cellvalue!=undefined){
	    			return cellvalue;//cellvalueJson[0].chargeperiod+"/"+cellvalueJson[0].chargeperiodunit;
	    		}else{
	    			return '';
	    		}
	    	}else if(options.colModel.index =='watch_price'){
	    		if(cellvalue!=null && cellvalue!=undefined){
	    			return cellvalue;//cellvalueJson[0].watch_price;
	    		}else{
	    			return '';
	    		}
	    	}else if(options.colModel.index =='iswatch'){
	    		if(cellvalue =='0'){
	    			return "否";
	    		}else if(cellvalue =='1'){
	    			return "是";
	    		}else{
	    			return '';
	    		}
	    	}else if(options.colModel.index =='watchtype'){
	    		if(cellvalue =='0'){
	    			return "水费";
	    		}else if(cellvalue =='1'){
	    			return "电费";
	    		}else if(cellvalue =='2'){
	    			return "燃气费";
	    		}else{
	    			return '';
	    		}
	    	}
		    return '';
	   }
    });
	
function loadData() {
	var title = "抄表收费项目管理";
	var pageer = "#pager";
	var colNames;
	var colModel;
	var datatype = "json";
	var page = 50;
	var size;
	var url = "${pageContext.request.contextPath}/charge/list.do";
	
	colNames = ['id','收费项名称','按表计费类型', '单价', '收费备注'];
	colModel = [ 
			   {name:'id',index:'id',hidden:true,align:"center"},
	           {name:'itemcode',index:'itemcode',align:"center"},
	           {name:'watchtype',index:'watchtype',align:"center",formatter:"itemcontentFormatter"},
	           {name:'watch_price',index:'watch_price',align:"center"},
	           {name:'chargeremark',index:'chargeremark',align:"center"},
	];
	
	var iswatch = $("#iswatch").val();
	size = $(window).height()-120;
	var postData={iswatch:'1',unitid:'SYSTEM'};
	
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
		var gridObject;
		//设置滚动条
		setCroll('#dataGrid .ui-jqgrid-bdiv','jqgrid-div');
		gridObject = "dataGrid";
		
		var str = "";
		
		var rownumbers = "";
		rownumbers = $("#"+gridObject).jqGrid('getGridParam','selarrrow');
		str = getSelectid(gridObject,rownumbers);
		
		if (str == "") {
			alert("请先选择要添加的数据。");
			return;
		}
		if (confirm("确定要为单元添加选中的收费项目吗？请谨慎操作!")) {
			var loadi = parent.layer.load(0);
			$.ajax({
		        async : false,
		        url : '${pageContext.request.contextPath}/charge/savechargeitem.do',
		        type : 'post',
		        data: {
					ids:str.toString(),
					unitid:$("#unitid").val()
				},
		        dataType : 'text',
		        success : function(data) {
		        	parent.layer.close(loadi);
		        	closepage();
		        }
		    });
		};
	}
	function refresh() {
		reloadGrid();
	}
	function getSelectid(gridObject,rows) {
		var result = new Array();
		for (var i=0;i<rows.length;i++) {
			var rowData = $("#"+gridObject).jqGrid('getRowData',rows[i]);
			result[i] = rowData["id"] ;
		}
		
		return result.toString();
	}
function closepage() {
		//获取当前窗口索引
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
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
