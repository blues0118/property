<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/util.js"></script>
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

<script type="text/javascript">
<!--

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
		    var result = '';//'<div style="float:left;cursor:pointer;"><a title="设置角色功能访问权限" href="javascript:;" onclick="auth(\''+rowdata.id+'\')"><span class="ui-icon ui-icon-gear"></span></a></div>';
		    result += '<div style="float:left;cursor:pointer;"><a title="修改收费项" href="javascript:;" onclick="edit(\''+rowdata.id+'\')"><span class="ui-icon ui-icon-pencil"></span></a></div>';
		    //result += '<div style="float:left;cursor:pointer;"><a title="查看拥有该角色的帐户" href="javascript:;" onclick="searchAccount(\''+rowdata.id+'\')"><span class="ui-icon ui-icon-person"></span></a></div>';
		    return result;
        },
        funIsEdit:function(cellvalue, options, rowdata){
            var result = "";
			if(rowdata.isedit == 0){
				result = "否";
			}else{
				result = "是";
			}
			return result;
        }
    });
	
	function loadData() {
		var title = "系统代码管理";
		var pageer = "#pager";
		var colNames;
		var colModel;
		var datatype = "json";
		var page = 50;
		var size;
		var url = "${pageContext.request.contextPath}/code/list.do";
		
		colNames = ['操作','字段名称','字段中文名称','代码key','代码value','状态'];
		colModel = [ 
		           {name:'fun',index:'fun', width:60,fixed:true,resizable:false,align:"center",frozen:true,formatter:"funFormatter"}, 
		           {name:'fieldcode',index:'fieldcode', width:100,align:"center"}, 
		           {name:'fieldcncode',index:'fieldcncode', width:100,align:"center"},
		           {name:'fieldkey',index:'fieldkey', width:100,align:"center"},
		           {name:'fieldvalue',index:'fieldvalue', width:100,align:"center"},
		           {name:'isedit',index:'isedit', width:100,align:"center",fixed:true,resizable:false,frozen:true,formatter:"funIsEdit"}
		];
		//size = $(".scrollTable").height() - 45;
		size = $(window).height()-120;
		
		var searchTxt = $("#searchTxt").val();
		var postData={searchTxt:searchTxt};
		
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
	
	function add(){  
		//调用code.util.js里的添加角色方法。
		parent.code.util.add();
	}
	
	function refresh() {
		reloadGrid();
	}
	
	function reloadGrid() {
		jQuery("#dataGrid").trigger("reloadGrid");
	}

	function getGridSelectids() {
		var rownumbers = "";
		rownumbers = $("#dataGrid").jqGrid('getGridParam','selarrrow');
		return rownumbers;
	}
	
	function del() {
		
		var ids = getGridSelectids();
		if (ids == "") {
			alert("请选择要删除的系统代码。");
			return;
		}
		
		if (confirm("确定要删除选择的项吗？")) {
		    $.ajax({
		        async : true,
		        url : "${pageContext.request.contextPath}/code/delete.do",
		        type : 'post',
		        data: {ids:ids.toString()},
		        dataType : 'text',
		        success : function(data) {
		        	sessionOut(data);
		            if (data == "success") {
		            	alert("删除完毕。");
		            	refresh();
		            } else {
		            	alert("可能因为您长时间没有操作，或读取数据时出错，请关闭浏览器，重新登录尝试或与管理员联系!！");
		            }
		        }
		    });
		}
	}
	
	function edit(id){
		//调用code.util.js里的修改收费项方法。
		parent.code.util.edit(id);
	}
	
//-->
</script>


<!--内容部分开始-->

		<div class="top_dd" style="margin-bottom: 10px;position:relative;z-index:999; ">
			<div class="dqwz_l">当前位置：系统维护-系统代码管理</div>
			<div  class="caozuoan">
				[ <a href="#" onclick="add()">添加</a> ]
				[ <a href="#" onclick="del()">删除</a> ]
				[ <a href="#" onclick="refresh()">刷新列表</a> ]
	        </div>
	        <div style="clear:both"></div>
	    </div>
		<div class="scrollTable" align="left" style="padding-left:5px;  padding-right: 8px;">
			<table id="dataGrid"></table>
			<div id="pager"></div>
		</div>