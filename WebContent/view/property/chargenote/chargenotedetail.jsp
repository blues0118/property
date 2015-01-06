<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		funFormatter: function (cellvalue, options, rowdata) {
		    var result = '<button type="button" onclick="chargenoteDetail(\''+rowdata.unittermid+'\')">确认收款</button>';
		    return result;
    	}
    });
	
function loadData() {
	var title = "单元台帐详细信息";
	var pageer = "#pager";
	var colNames;
	var colModel;
	var datatype = "json";
	var page = 50;
	var size;
	var url = "${pageContext.request.contextPath}/bookterm/listStandingbook.do";
	
	colNames = ['id','项目名称','起始', '终止', '数量','单价','金额','备注','操作'];
	colModel = [ 
			   {name:'id',index:'id',hidden:true, width:100,align:"center"},
	           {name:'itemcode',index:'itemcode', width:100,align:"center"},
	           {name:'lastnumber',index:'lastnumber', width:100,align:"center"},
	           {name:'newnumber',index:'newnumber', width:100,align:"center"},
	           {name:'number',index:'number', width:100,align:"center"},
	           {name:'chargeprice',index:'chargeprice', width:100,align:"center"},
	           {name:'chargesum',index:'chargesum', width:100,align:"center"},
	           {name:'bookmemo',index:'bookmemo', width:100,align:"center"},
	           {name:'fun',index:'fun', width:80,fixed:true,resizable:false,align:"center",frozen:true,formatter:"funFormatter"}
	];
	
	var unittermid = $("#unittermid").val();
	size = $(window).height()-120;
	var postData={unittermid:unittermid};
	
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
				});
		
		//单元台帐右键
		chargenoteMenu();
}
	
	function reloadGrid() {
		jQuery("#dataGrid").trigger("reloadGrid");
	}
	
	function getGridSelectids() {
		var rownumbers = "";
		rownumbers = $("#dataGrid").jqGrid('getGridParam','selarrrow');
		return rownumbers;
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

//单元台帐右键菜单
function chargenoteMenu() {
	$("#gview_dataGrid").contextMenu('myMenu_chargenote', {
    	menuStyle: {
    		backgroundColor: '#fcfdfd',
            border: '2px solid #a6c9e2',
            //maxWidth: '600px',
            width: '170px', // to have good width of the menu
            padding:'1px 1px 5px 1px'
	    },
        itemStyle : {
          //fontFamily: 'verdana',
          //backgroundColor: '#666',
          //color: '#1d5987',
          //border: 'none',
          //padding: '1px'
        },
        shadow:false,
        itemHoverStyle: {
        	border: '1px solid #79b7e7',
	        color: '#1d5987',
	        backgroundColor: '#d0e5f5',
	        cursor:'pointer'
        },
        bindings: {
          'add': function(t) {
        	addChargeitem();
          }
        }
    });
}
//增加收费项目记录
function addChargeitem(){
	var unitid = $("#unitid").val();
	var url = "${pageContext.request.contextPath}/charge/addforchargenote.do?unitid="+unitid+"&time="+Date.parse(new Date());
	var loadi = parent.layer.load(0);
	parent.$.layer({
        type: 2,
        title: '为台帐添加收费项目',
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
</script>


<!--内容部分开始-->

		<div class="top_dd" style="margin-bottom: 10px;position:relative;z-index:5555;">
			<input type="hidden" id="unittermid" value="${chargenote.unittermid }">
			<input type="hidden" id="unitid" value="${chargenote.unitid }">
			<div class="dqwz_l">当前位置：物业管理－单元台帐</div>
			<div  class="caozuoan">
				发票号码：<input type="text" name="invoiceNum"/>
				[ <a href="#" onclick="refresh()">通知单打印</a> ]
				[ <a href="#" onclick="refresh()">收款单打印</a> ]
				[ <a href="#" onclick="refresh()">刷新列表</a> ]
	         </div>
	         <div style="clear:both"></div>
	    </div>
	    
		<div class="scrollTable" align="left" style="padding-left:5px; padding-right: 8px;" >
			<table id="dataGrid"></table>
			<div id="pager"></div>
		</div>
		<div class="contextMenu" id="myMenu_chargenote" style="display: none;">
			    <ul class="ui-corner-all">
			   	<span class="ui-widget-header ui-corner-top menu_title ">单元台帐操作</span>
			      <li id="add">添加</li>
			      <!-- <span class="menu_span"></span>
			      <li id="doc">挂接电子文件</li> -->
			    </ul>
			</div>