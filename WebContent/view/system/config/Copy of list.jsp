<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/view/common/header.jsp"%>
<%@ include file="/view/common/top_menu.jsp"%>
<%-- <%@ include file="/view/common/top_second_menu.jsp"%> --%>

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

<script type="text/javascript">

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
		    var result = '<div style="float:left;cursor:pointer;"><a title="修改角色信息" href="javascript:;" onclick="edit(\''+rowdata.id+'\')"><span class="ui-icon ui-icon-pencil"></span></a></div>';
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
		var url = "${pageContext.request.contextPath}/config/list.do";
		
		colNames = ['操作','参数名称','参数值','参数描述'];
		colModel = [ 
		           {name:'fun',index:'fun', width:30,fixed:true,resizable:false,align:"center",frozen:true,formatter:"funFormatter"}, 
		           {name:'configname',index:'configname', width:100,align:"center"}, 
		           {name:'configvalue',index:'configvalue', width:100,align:"center"}, 
		           {name:'rolememo',index:'rolememo', width:100,align:"center"}
		];
		size = $(".scrollTable").height() - 45;
		
		var postData= "";
		
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
	
	function edit(id){  
		//var result = window.showModalDialog("edit.do?id="+id,"查看窗口","toolbars=0;location=no;status=no;resizable=no;dialogWidth=700px;dialogHeight=400px;scrollbars=0");
		var url = "edit.do?id="+id + "&time="+Date.parse(new Date());
		var whObj = { width: 600, height: 300 };
		var result = openShowModalDialog(url,null,whObj);
		
		refresh();
		//window.location.reload(true); // 刷新窗体
		
	}
	
	function refresh() {
		reloadGrid();
	}
	
	function reloadGrid() {
		jQuery("#dataGrid").trigger("reloadGrid");
	}


//-->
</script>


<!--内容部分开始-->

<div id="bodyer">
	<div id="bodyer_left">
		<dl>
			<dt>
				<a href="#" class="blue"><img
					src="${pageContext.request.contextPath}/images/i1_03.png"
					width="29" height="22" class="tubiao" /><span>系统配置</span></a>
			</dt>
			<dd>
				<ul>
					<li><a href="${pageContext.request.contextPath}/config/list.do" class="txt2 on"><img
							src="${pageContext.request.contextPath}/images/i_07.png"
							width="18" height="15" class="tubiao1" /><span>参数设置</span></a></li>
					<li><a href="" class="txt2"><img
							src="${pageContext.request.contextPath}/images/i_10.png"
							width="18" height="13" class="tubiao1" /><span>文件服务器</span></a></li>
					<li><a href="" class="txt2"><img
							src="${pageContext.request.contextPath}/images/i_10.png"
							width="18" height="13" class="tubiao1" /><span>电子文件权限</span></a></li>
					<li><a href="" class="txt2"><img
							src="${pageContext.request.contextPath}/images/i_14.png"
							width="18" height="13" class="tubiao1" /><span>索引维护</span></a></li>
				</ul>
			</dd>
		</dl>
	</div>
	<div id="bodyer_right">
		<div class="top_dd" style="margin-bottom: 10px;position:relative;z-index:999; ">
			<div class="dqwz">当前位置：系统配置-参数设置</div>
		</div>
		<div class="scrollTable" align="left" style="padding-left:5px; padding-right: 8px;">
			<table id="dataGrid"></table>
			<div id="pager"></div>
		</div>
	</div>
	<div style="clear: both"></div>
</div>
<!--内容部分结束-->

<%@ include file="/view/common/footer.jsp"%>