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
	function loadComplete() {}
	function gridComplete() {}
	function gridSel() {}
	function gridState() {}
	function callback() {}
	function refresh() {
		//loadEquipData();
	}
	jQuery.extend($.fn.fmatter, {
        funEquipFormatter: function (cellvalue, options, rowdata) {
		    var result = '<button title="详细" type="button" onclick="loadEquipData(\''+rowdata.id+'\')">详细</button>';
		    result += '<button title="修改" type="button" onclick="editEquip(\''+rowdata.id+'\')">修改</button>';
		    result += '<button title="删除" type="button" onclick="delEquip(\''+rowdata.id+'\')">删除</button>';
		    return result;
	    },
	    funStaffFormatter: function (cellvalue, options, rowdata) {
		    var result = '<button title="工资详细" type="button" onclick="loadstaffPayData(\''+rowdata.id+'\')">详细</button>';
		    result += '<button title="修改" type="button" onclick="editStaff(\''+rowdata.id+'\')">修改</button>';
		    result += '<button title="删除" type="button" onclick="delStaff(\''+rowdata.id+'\')">删除</button>';
		    result += '<button title="添加支出" type="button" onclick="addstaffPay(\''+rowdata.id+'\')">添加支出</button>';
		    return result;
	    },
	    funOtherFormatter: function (cellvalue, options, rowdata) {
		    var result = '<button title="详细" type="button" onclick="loadOtherData(\''+rowdata.id+'\')">详细</button>';
		    result += '<button title="修改" type="button" onclick="editOther(\''+rowdata.id+'\')">修改</button>';
		    result += '<button title="删除" type="button" onclick="delOther(\''+rowdata.id+'\')">删除</button>';
		    return result;
	    },
	    funStaffContentFormatter: function (cellvalue, options, rowdata) {
		    var result = '<button title="修改" type="button" onclick="editStaffcontent(\''+rowdata.id+'\')">修改</button>';
		    result += '<button title="删除" type="button" onclick="delStaffcontent(\''+rowdata.id+'\')">删除</button>';
		    return result;
	    }
    });

	function addequip(){
		if (document.all("projectid").value == "projectid") {
			alert("请先选择要右侧的单元节点。");
			return;
		}
		var url = "${pageContext.request.contextPath}/pay/addequip.do?projectid="+document.all("projectid").value;
		parent.$.layer({
	        type: 2,
	        title: '添加设备',
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
	
	function loadstaffPayData(id) {
		$("#eqlist").hide();
		$("#stlist").hide();
		$("#otlist").hide();
		$("#sclist").show();
		
		var title = "员工工资管理";
		var pageer = "#pager_sc";
		var colNames;
		var colModel;
		var datatype = "json";
		var page = 17;
		var size;
		var url = "${pageContext.request.contextPath}/pay/staffContentList.do?staffid="+id;
		
		colNames = ['总台账id','工资','保险','操作'];
		colModel = [
                   {name:'termid',index:'termid', align:"center"},
		           {name:'wage',index:'wage', align:"center"}, 
		           {name:'safe',index:'safe', align:"center"}, 
                   {name:'fun',index:'fun', fixed:true,resizable:false,align:"center",frozen:true,formatter:"funStaffContentFormatter"}
		];
		
		var searchTxt = $("#searchTxt").val();
		size = $(window).height()-120;
		var postData={searchTxt:searchTxt};
		
		var _option = {
				gridObject:"dataGrid_sc",
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
	
	function loadEquipData() {
		$("#eqlist").show();
		$("#stlist").hide();
		$("#otlist").hide();
		$("#sclist").hide();
		
		var title = "设备管理";
		var pageer = "#pager_eq";
		var colNames;
		var colModel;
		var datatype = "json";
		var page = 17;
		var size;
		var url = "${pageContext.request.contextPath}/pay/equiplist.do?projectid="+document.all("projectid").value;
		
		colNames = ['设备名称','设备描述','操作'];
		colModel = [
                   {name:'code',index:'code', align:"center"},
		           {name:'memo',index:'memo', align:"center"}, 
                   {name:'fun',index:'fun', fixed:true,resizable:false,align:"center",frozen:true,formatter:"funEquipFormatter"}
		];
		
		var searchTxt = $("#searchTxt").val();
		size = $(window).height()-120;
		var postData={searchTxt:searchTxt};
		
		var _option = {
				gridObject:"dataGrid_eq",
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
	
	function editEquip(id){
		if (id == "") {
			alert("请先选择要修改的数据。");
			return;
		}
		
		var url = "${pageContext.request.contextPath}/pay/editEquip.do?id="+id + "&time="+Date.parse(new Date());
		
		parent.$.layer({
	        type: 2,
	        title: '修改设备信息',
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
	
	function delEquip(id) {
		
		//var ids = getGridSelectids();
		var ids = id;
		
		if (ids == "") {
			alert("请先选择要删除的数据。");
			return;
		}
		if (confirm("确定要删除选择的设备吗？删除该设备，将同时删除该设备的一切附属信息。请谨慎操作。")) {
		    $.ajax({
		        async : true,
		        url : "${pageContext.request.contextPath}/pay/deleteEquip.do",
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

	function addstaff(){
		if (document.all("projectid").value == "projectid") {
			alert("请先选择要右侧的单元节点。");
			return;
		}
		var url = "${pageContext.request.contextPath}/pay/addStaff.do?projectid="+document.all("projectid").value;
		parent.$.layer({
	        type: 2,
	        title: '添加员工',
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


	function addstaffPay(id){
		
		var url = "${pageContext.request.contextPath}/pay/addStaffPay.do?staffId="+id;
		parent.$.layer({
	        type: 2,
	        title: '添加员工支出',
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
	
	function loadStaffData() {
		$("#eqlist").hide();
		$("#stlist").show();
		$("#sclist").hide();
		$("#otlist").hide();
		
		var title = "员工管理";
		var pageer = "#pager_st";
		var colNames;
		var colModel;
		var datatype = "json";
		var page = 17;
		var size;
		var url = "${pageContext.request.contextPath}/pay/staffList.do?projectid="+document.all("projectid").value;
		
		colNames = ['员工名称','备注','操作'];
		colModel = [
                   {name:'staffcode',index:'staffcode', width:40, align:"center"},
		           {name:'staffmemo',index:'staffmemo', width:40, align:"center"}, 
                   {name:'fun',index:'fun', width:180, fixed:true,resizable:false,align:"center",frozen:true,formatter:"funStaffFormatter"}
		];
		
		var searchTxt = $("#searchTxt").val();
		size = $(window).height()-120;
		var postData={searchTxt:searchTxt};
		
		var _option = {
				gridObject:"dataGrid_st",
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
	
	function editStaff(id){
		if (id == "") {
			alert("请先选择要修改的数据。");
			return;
		}
		
		var url = "${pageContext.request.contextPath}/pay/editStaff.do?id="+id + "&time="+Date.parse(new Date());
		
		parent.$.layer({
	        type: 2,
	        title: '修改员工信息',
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
	
	function delStaff(id) {
		
		//var ids = getGridSelectids();
		var ids = id;
		
		if (ids == "") {
			alert("请先选择要删除的数据。");
			return;
		}
		if (confirm("确定要删除选择的员工吗？删除该员工，将同时删除该员工的一切附属信息。请谨慎操作。")) {
		    $.ajax({
		        async : true,
		        url : "${pageContext.request.contextPath}/pay/deleteStaff.do",
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

	function addother(){
		if (document.all("projectid").value == "projectid") {
			alert("请先选择要右侧的单元节点。");
			return;
		}
		var url = "${pageContext.request.contextPath}/pay/addOther.do?projectid="+document.all("projectid").value;
		parent.$.layer({
	        type: 2,
	        title: '添加其他支出',
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
	
	function loadOtherData() {
		$("#eqlist").hide();
		$("#stlist").hide();
		$("#sclist").hide();
		$("#otlist").show();
		
		var title = "其他支出管理";
		var pageer = "#pager_ot";
		var colNames;
		var colModel;
		var datatype = "json";
		var page = 17;
		var size;
		var url = "${pageContext.request.contextPath}/pay/otherList.do?projectid="+document.all("projectid").value;
		
		colNames = ['支出日期','支出描述','支出描述','总台账账期','操作'];
		colModel = [
                   {name:'paydate',index:'paydate', align:"center"},
		           {name:'paymemo',index:'paymemo', align:"center"},
		           {name:'paysum',index:'paysum', align:"center"}, 
		           {name:'termid',index:'termid', align:"center"},  
                   {name:'fun',index:'fun', fixed:true,resizable:false,align:"center",frozen:true,formatter:"funOtherFormatter"}
		];
		
		var searchTxt = $("#searchTxt").val();
		size = $(window).height()-120;
		var postData={searchTxt:searchTxt};
		
		var _option = {
				gridObject:"dataGrid_ot",
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
	
	function editOther(id){
		if (id == "") {
			alert("请先选择要修改的数据。");
			return;
		}
		
		var url = "${pageContext.request.contextPath}/pay/editOther.do?id="+id + "&time="+Date.parse(new Date());
		
		parent.$.layer({
	        type: 2,
	        title: '修改其他支出信息',
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
	
	function delOther(id) {
		
		//var ids = getGridSelectids();
		var ids = id;
		
		if (ids == "") {
			alert("请先选择要删除的数据。");
			return;
		}
		if (confirm("确定要删除选择的其他支出吗？请谨慎操作。")) {
		    $.ajax({
		        async : true,
		        url : "${pageContext.request.contextPath}/pay/deleteOther.do",
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
	
	function editStaffcontent(id){
		if (id == "") {
			alert("请先选择要修改的数据。");
			return;
		}
		
		var url = "${pageContext.request.contextPath}/pay/editStaffcontent.do?id="+id + "&time="+Date.parse(new Date());
		
		parent.$.layer({
	        type: 2,
	        title: '修改员工工资信息',
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

	function delStaffcontent(id) {
		
		//var ids = getGridSelectids();
		var ids = id;
		
		if (ids == "") {
			alert("请先选择要删除的数据。");
			return;
		}
		if (confirm("确定要删除选择的员工工资吗？请谨慎操作。")) {
		    $.ajax({
		        async : true,
		        url : "${pageContext.request.contextPath}/pay/deleteStaffcontent.do",
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

</script>


<!--内容部分开始-->

		<div class="top_dd" style="margin-bottom: 10px;position:relative;z-index:5555;">
			<div class="dqwz_l">当前位置：支出管理</div>
			<div class="caozuoan">
				[ <a href="#" onclick="addequip()">添加设备</a> ]
				[ <a href="#" onclick="addstaff()">添加员工</a> ]
				[ <a href="#" onclick="addother()">添加其他</a> ]
				<button type="button" onclick="loadEquipData()">设备管理</button>
				<button type="button" onclick="loadStaffData()">员工管理</button>
				<button type="button" onclick="loadOtherData()">其他管理</button>
	         </div>
	         <div style="clear:both"></div>
	    </div>
	    
		<div id="eqlist" class="scrollTable" align="left" style="padding-left:5px; padding-right: 8px;" >
			<table id="dataGrid_eq"></table>
			<div id="pager_eq"></div>
		</div>
	    
		<div id="stlist" class="scrollTable" align="left" style="padding-left:5px; padding-right: 8px;" >
			<table id="dataGrid_st"></table>
			<div id="pager_st"></div>
		</div>
		
		<div id="sclist" class="scrollTable" align="left" style="padding-left:5px; padding-right: 8px;" >
			<table id="dataGrid_sc"></table>
			<div id="pager_sc"></div>
		</div>
	    
		<div id="otlist" class="scrollTable" align="left" style="padding-left:5px; padding-right: 8px;" >
			<table id="dataGrid_ot"></table>
			<div id="pager_ot"></div>
		</div>
		
		<div>
			<input type="hidden" name="projectid" value="${projectid}"/>
		</div>
