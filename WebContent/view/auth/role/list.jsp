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
		    var result = '<div style="float:left;cursor:pointer;"><a title="设置角色功能访问权限" href="javascript:;" onclick="auth(\''+rowdata.id+'\')"><span class="ui-icon ui-icon-gear"></span></a></div>';
		    result += '<div style="float:left;cursor:pointer;"><a title="修改角色信息" href="javascript:;" onclick="edit(\''+rowdata.id+'\')"><span class="ui-icon ui-icon-pencil"></span></a></div>';
		    result += '<div style="float:left;cursor:pointer;"><a title="查看拥有该角色的帐户" href="javascript:;" onclick="searchAccount(\''+rowdata.id+'\')"><span class="ui-icon ui-icon-person"></span></a></div>';
		    return result;
        }
    });
	
	function loadData() {
		var title = "角色管理";
		var pageer = "#pager";
		var colNames;
		var colModel;
		var datatype = "json";
		var page = 50;
		var size;
		var url = "${pageContext.request.contextPath}/role/list.do";
		
		colNames = ['操作','角色名称','描述'];
		colModel = [ 
		           {name:'fun',index:'fun', width:60,fixed:true,resizable:false,align:"center",frozen:true,formatter:"funFormatter"}, 
		           {name:'rolecode',index:'rolecode', width:100,align:"center"}, 
		           {name:'rolememo',index:'rolememo', width:100,align:"center"}
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
		var url = "add.do?time="+Date.parse(new Date());
		var whObj = { width: 340, height: 300 };
		var result = openShowModalDialog(url,window,whObj);
		
		refresh();
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
			alert("没有获得要删除的角色，请重新尝试，或与管理员联系。");
			return;
		}
		
		if (confirm("确定要删除选择的角色吗？删除该角色，拥有该角色的帐户组、帐户将失去角色。")) {
		    $.ajax({
		        async : true,
		        url : "${pageContext.request.contextPath}/role/delete.do",
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
		
		if (id == "") {
			alert("请先选择要修改的数据。");
			return;
		}
		
		if (id == "1") {
			alert("超级帐户不能修改。");
			return;
		}
		
		var url = "edit.do?id="+id + "&time="+Date.parse(new Date());
		var whObj = { width: 340, height: 300 };
		var result = openShowModalDialog(url,window,whObj);
		
		refresh();
		//window.location.reload(true); // 刷新窗体
	}
	
	function auth(id) {
		var url = "auth.do?id="+id + "&time="+Date.parse(new Date());
		var whObj = { width: 440, height: 500 };
		var result = openShowModalDialog(url,window,whObj);
	}
	
	function searchAccount(id) {
		var url = "searchAccount.do?id="+id + "&time="+Date.parse(new Date());
		var whObj = { width: 740, height: 500 };
		var result = openShowModalDialog(url,window,whObj);
	}

//-->
</script>


<!--内容部分开始-->

		<div class="top_dd" style="margin-bottom: 10px;position:relative;z-index:999; ">
			<div class="dqwz_l">当前位置：系统维护-角色管理</div>
			<div  class="caozuoan">
				[ <a href="#" onclick="add()">添加角色</a> ]
				[ <a href="#" onclick="del()">删除角色</a> ]
				[ <a href="#" onclick="refresh()">刷新列表</a> ]
	        </div>
	        <div style="clear:both"></div>
	    </div>
		<div class="scrollTable" align="left" style="padding-left:5px;  padding-right: 8px;">
			<table id="dataGrid"></table>
			<div id="pager"></div>
		</div>