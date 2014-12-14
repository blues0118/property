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
		typeFormatter: function (cellvalue, options, rowdata) {
		    if(cellvalue!=undefined && cellvalue!=null){
		    	if(cellvalue=='0'){
		    		return '未结束';
		    	}else if(cellvalue=='1'){
		    		return '已结束';
		    	}
		    }else{
		    	return '';
		    }
		    return result;
	    },
        funFormatter: function (cellvalue, options, rowdata) {
		    var result = '<button type="button" onclick="setMeteritem(\''+rowdata.id+'\')">详细</button>';
		    result += '<button type="button" onclick="saveChargeitem(saveChargeitem(\''+rowdata.id+'\')">修改</button>';
		    result += '<button type="button" onclick="delChargeitem(\''+rowdata.id+'\')">删除</button>';
		    return result;
	    }
    });
	
	function loadData() {
		var title = "抄表管理";
		var pageer = "#pager";
		var colNames;
		var colModel;
		var datatype = "json";
		var page = 50;
		var size;
		var url = "${pageContext.request.contextPath}/meter/meterList.do";
		
		colNames = ['抄表账期', '创建人', '创建时间','账期状态','备注','操作'];
		colModel = [ 
		           {name:'termcode',index:'termcode', width:80,align:"center"},
		           {name:'createman',index:'createman', width:80,align:"center"},
		           {name:'createtime',index:'createtime', width:80,align:"center"},
		           {name:'tremstatus',index:'tremstatus', width:100,align:"center",formatter:"typeFormatter"}, 
		           {name:'termmemo',index:'termmemo', width:100,align:"center"}, 
		           {name:'fun',index:'fun', width:140,fixed:true,resizable:false,align:"center",frozen:true,formatter:"funFormatter"}, 
		];
		
		var searchTxt = $("#searchTxt").val();
		size = $(window).height()-120;
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
	
	function reloadGrid() {
		jQuery("#dataGrid").trigger("reloadGrid");
	}
	
	function getGridSelectids() {
		var rownumbers = "";
		rownumbers = $("#dataGrid").jqGrid('getGridParam','selarrrow');
		return rownumbers;
	}
	
	function add(){
		//调用meteritem.util.js里的添加帐户方法。
		parent.meteritem.util.add();
	}
	
	function del() {
		
		var ids = getGridSelectids();
		
		if (ids == "") {
			alert("请先选择要删除的数据。");
			return;
		}
		if (confirm("确定要删除选择的帐户吗？删除该帐户，将同时删除该帐户的一切附属信息。请谨慎操作。")) {
		    $.ajax({
		        async : true,
		        url : "${pageContext.request.contextPath}/account/delete.do",
		        type : 'post',
		        data: {ids:ids.toString()},
		        dataType : 'text',
		        success : function(data) {
		        	sessionOut(data);
		            if (data == "success") {
		            	alert("删除完毕。");
		            	refresh();
		            }
		        }
		    });
		}
	}
	
	
	function setMeteritem(id){
		//调用meteritem.util.js里的单元抄表记录方法。
		parent.meteritem.util.setMeteritem(id);
	}
	
	function refresh() {
		reloadGrid();
	}
	
	function updatestate(id,state) {
		if (id == "") {
			alert("没有获得要更改状态的帐户，请重新尝试，或与管理员联系。");
			return;
		}
		var str = "确定要将状态更改为［禁用］吗？禁用后该帐户将不能登录本系统。";
		if (state == 1) {
			str = "确定要将状态更改为［启用］吗？";
		}
		
		if (confirm(str)) {
		    $.ajax({
		        async : true,
		        url : "${pageContext.request.contextPath}/account/updatestate.do",
		        type : 'post',
		        data: {id:id,state:state},
		        dataType : 'text',
		        success : function(data) {
		        	sessionOut(data);
		            if (data == "success") {
		            	alert("更改完毕。");
		            	
		            } else {
		            	alert("可能因为您长时间没有操作，或读取数据时出错，请关闭浏览器，重新登录尝试或与管理员联系!！");
		            }
		            refresh()
		        }
		    });
		}
	}


</script>


<!--内容部分开始-->

		<div class="top_dd" style="margin-bottom: 10px;position:relative;z-index:5555;">
			<div class="dqwz_l">当前位置：权限管理－帐户管理</div>
			<div  class="caozuoan">
				[ <a href="#" onclick="add()">添加帐户</a> ]
				[ <a href="#" onclick="del()">删除帐户</a> ]
				[ <a href="#" onclick="refresh()">刷新列表</a> ]
	         </div>
	         <div style="clear:both"></div>
	    </div>
		<div class="scrollTable" align="left" style="padding-left:5px; padding-right: 8px;" >
			<table id="dataGrid"></table>
			<div id="pager"></div>
		</div>
