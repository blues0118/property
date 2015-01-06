<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	function loadComplete() {
		
	}
	function gridComplete() {
		
	}
	
	function gridSel() {
		
	}
	function gridState() {
		
	}
	
	function callback() {
		loadData();
	}
	jQuery.extend($.fn.fmatter, {
        tremstatusFormatter: function (cellvalue, options, rowdata) {
			if (cellvalue != 1)
		    	return '未结束';
		    else 
		        return '已结束';
        },
        funFormatter: function (cellvalue, options, rowdata) {
		    var result = '<button title="详细" type="button" onclick="loadDetailData(\''+rowdata.id+'\')">详细</button>';
		    result += '<button title="修改" type="button" onclick="edit(\''+rowdata.id+'\')">修改</button>';
		    result += '<button title="删除" type="button" onclick="del()">删除</button>';
		    return result;
	    },
	    funDetailFormatter: function (cellvalue, options, rowdata) {
		    var result = '<button title="详细" type="button" onclick="loadBookData(\''+rowdata.projectid+'\',\''+rowdata.unitid+'\',\''+rowdata.termid+'\',\''+rowdata.id+'\')">详细</button>';
		    return result;
	    }
    });
	
	function loadData() {

		
		$("#booktermenu").show();
		$("#booktermbt").show();
		$("#booktermlist").show();
		
		$("#uinttermenu").hide();
		$("#unittermbt").hide();
		$("#unittermlist").hide();
		
		var title = "总台账账期管理";
		var pageer = "#pager_bt";
		var colNames;
		var colModel;
		var datatype = "json";
		var page = 17;
		var size;
		var url = "${pageContext.request.contextPath}/bookterm/list.do?projectid="+document.all("projectid").value;
		
		colNames = ['总账期名称','备注', '创建时间', '创建人','总账期状态','操作'];
		colModel = [
                   {name:'termcode',index:'termcode', align:"center"},
		           {name:'termmemo',index:'termmemo', align:"center"},
		           {name:'createtime',index:'createtime', align:"center"},
		           {name:'createman',index:'createman', align:"center"}, 
		           {name:'tremstatus',index:'tremstatus', align:"center",formatter:"tremstatusFormatter"}, 
                   {name:'fun',index:'fun', fixed:true,resizable:false,align:"center",frozen:true,formatter:"funFormatter"}
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
	
	function loadDetailData(termid) {
		
		$("#booktermenu").hide();
		$("#booktermbt").hide();
		$("#booktermlist").hide();

		$("#uinttermenu").show();
		$("#unittermbt").show();
		$("#unittermlist").show();
		var title = "单元台账账期";
		var pageer = "#pager_ut";
		var colNames;
		var colModel;
		var datatype = "json";
		var page = 50;
		var size;
		var url = "${pageContext.request.contextPath}/bookterm/detaillist.do";
		
		colNames = ['账期名称','备注', '单元账期状态','操作'];
		colModel = [
                   {name:'unittermcode',index:'unittermcode', width:100,align:"center"},
		           {name:'unittermmemo',index:'unittermmemo', width:100,align:"center"},
		           {name:'unittermstatus',index:'unittermstatus', width:100,align:"center",formatter:"tremstatusFormatter"}, 
                   {name:'fun',index:'fun', width:80,fixed:true,resizable:false,align:"center",frozen:true,formatter:"funDetailFormatter"}
		];
		
		var searchTxt = termid;
		size = $(window).height()-335;
		var postData={searchTxt:searchTxt};
		
		var _option = {
				gridObject:"detailDataGrid",
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
	
	function loadBookData(projectid,unitid,termid,unittermid) {


		$("#uinttermenu").show();
		$("#unittermbt").show();
		$("#unittermlist").show();
		var title = "台账详细";
		var pageer = "#pager_b";
		var colNames;
		var colModel;
		var datatype = "json";
		var page = 50;
		var size;
		var url = "${pageContext.request.contextPath}/bookterm/detailbooklist.do?";
		
		colNames = ['计费时间','收费时间', '收费项目名称','是否按表计费','上期读数','本期读数','台账状态','收费金额','操作'];
		colModel = [
                   {name:'chargetime',index:'chargetime', width:100,align:"center"},
		           {name:'chargeovertime',index:'chargeovertime', width:100,align:"center"},
		           {name:'itemcode',index:'itemcode', width:100,align:"center"},
		           {name:'iswatch',index:'iswatch', width:100,align:"center"},
		           {name:'lastnumber',index:'lastnumber', width:100,align:"center"},
		           {name:'newnumber',index:'newnumber', width:100,align:"center"},
		           {name:'chargestatus',index:'chargestatus', width:100,align:"center",formatter:"tremstatusFormatter"},
		           {name:'chargesum',index:'chargesum', width:100,align:"center"}, 
                   {name:'fun',index:'fun', width:80,fixed:true,resizable:false,align:"center",frozen:true,formatter:"funDetailFormatter"}
		];

		size = $(window).height()-395;
		var book = {};
		
		book.projectid = projectid;
		book.unitid = unitid;
		book.termid = termid;
		book.unittermid = unittermid;
		var postData={projectid:projectid,unitid:unitid,termid:termid,unittermid:unittermid};
		var _option = {
				gridObject:"detailbookDataGrid",
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
		if (document.all("projectid").value == "projectid") {
			alert("请先选择要右侧的单元节点。");
			return;
		}
		var url = "${pageContext.request.contextPath}/bookterm/add.do?projectid="+document.all("projectid").value;
		parent.$.layer({
	        type: 2,
	        title: '添加总台账',
	        maxmin: false,
	        shadeClose: true, //开启点击遮罩关闭层
	        area : ['600px' , '300px'],
	        offset : ['', ''],
	        iframe: {src: url},
	        end: function(){
	        	// 数据重新读取方法
	        	refresh();
	        }
	    });
	}
	
	function del() {
		
		var ids = getGridSelectids();
		
		if (ids == "") {
			alert("请先选择要删除的数据。");
			return;
		}
		if (confirm("确定要删除选择的总账期吗？删除该总账期，将同时删除该总账期的一切附属信息。请谨慎操作。")) {
		    $.ajax({
		        async : true,
		        url : "${pageContext.request.contextPath}/bookterm/delete.do",
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
	
	function edit(id){
		if (id == "") {
			alert("请先选择要修改的数据。");
			return;
		}
		
		var url = "${pageContext.request.contextPath}/bookterm/edit.do?id="+id + "&time="+Date.parse(new Date());
		
		parent.$.layer({
	        type: 2,
	        title: '修改总账期信息',
	        maxmin: false,
	        shadeClose: true, //开启点击遮罩关闭层
	        area : ['600px' , '300px'],
	        offset : ['', ''],
	        iframe: {src: url},
	        end: function(){
	        	// 数据重新读取方法
	        	refresh();
	        }
	    });
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
			<div id="booktermenu" class="dqwz_l">当前位置：台账管理－总台账账期</div>
			<div id="uinttermenu" class="dqwz_l">当前位置：台账管理－总台账账期－单元账期</div>
			<div id="booktermbt" class="caozuoan">
				[ <a href="#" onclick="add()">添加总账期</a> ]
				<input type="text" id="searchTxt" name="searchTxt" />
				<button type="button" onclick="loadData()">查询</button>
	         </div>
			<div id="unittermbt" class="caozuoan">
				<button type="button" onclick="loadData()">返回</button>
	         </div>
	         <div style="clear:both"></div>

	    </div>
		<div id="booktermlist" class="scrollTable" align="left" style="padding-left:5px; padding-right: 8px;" >
			<table id="dataGrid"></table>
			<div id="pager_bt"></div>
		</div>
		<div id="unittermlist" class="scrollTable" align="left" style="padding-left:5px; padding-right: 8px;" >
		<div>
			<table id="detailDataGrid"></table>
			<div id="pager_ut"></div>
		</div>
		<div>
			<table id="detailbookDataGrid"></table>
			<div id="pager_b"></div>
		</div>
		</div>
		<div>
			<input type="hidden" name="projectid" value="${projectid}"/>
		</div>
