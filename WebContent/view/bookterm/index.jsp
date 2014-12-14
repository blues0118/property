<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/view/common/header.jsp"%>
<%@ include file="/view/common/top_menu.jsp"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table_main.css" type="text/css">

<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/js/jqgrid/jquery-ui/redmond/jquery-ui-1.8.2.custom.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ba-resize.min.js"></script>
<!-- 弹出框插件 -->
<script src="${pageContext.request.contextPath}/js/layer/layer.min.js"></script>
<style>
<!--
body{ height:100%; margin:0; font-size:12px; font-family:"微软雅黑";  }
a{ text-decoration:none;  font-size:12px; color:#1874CD;}
-->
</style>


<link rel="stylesheet" href="${pageContext.request.contextPath}/js/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/zTree/js/jquery.ztree.all-3.5.min.js"></script>

<script type="text/javascript">

	var setting = {
			edit: {
				enable: true,
				showRemoveBtn: false,
				showRenameBtn: false
			},
			data: {
				keep: {
					parent: true,
					leaf:true
				},
				key:{
					name:"projectname"
				},
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "parentid"
				}
			},
			callback: {
				onClick: onClick
			}
	};
	
	function onClick(event, treeId,nodes) {
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		// L：项目下节点的情况（P：项目  F：项目夹）
		if (nodes.projecttype == 'L') {
			$("#fra").attr("src","${pageContext.request.contextPath}/bookterm/relist.do?projectid=" + nodes.id);
			return ;
		}else{
			treeObj.expandNode(nodes);
		}
	}
	
	function callback() {
		$("#fra").attr("src","${pageContext.request.contextPath}/bookterm/relist.do?projectid=projectid");
	}
	
	var nodes = ${projectList};
	
	for (var i=0;i<nodes.length;i++) {
		if (nodes[i].parentid == '0') {
			nodes[i].isParent = true;
			nodes[i].open = true;

		}
		else if (nodes[i].projecttype == "P") {
			nodes[i].isParent = true;
			nodes[i].icon = "${pageContext.request.contextPath}/images/20140926124231782_easyicon_net_16.png";
		}
		else if (nodes[i].projecttype == "F") {
			nodes[i].isParent = true;
			nodes[i].iconOpen = "${pageContext.request.contextPath}/images/folder-open.gif";
			nodes[i].iconClose = "${pageContext.request.contextPath}/images/folder.gif";
		}
		else {
			nodes[i].isParent = false;
			nodes[i].icon = "${pageContext.request.contextPath}/images/tree-leaf.gif";
		}
		
	}
	
	$(function(){
		$.fn.zTree.init($("#treeDemo"), setting, nodes);
	});
	
	function onClickItem(page,item) {
		$("#fra").attr("src",page);
		
		$("#item_ul a").removeClass().addClass("txt2");
		$("#"+item).addClass("on");
	}
</script>

<!--内容部分开始-->

<div id="bodyer">
	<div id="bodyer_left">
		<div class="scrollTable" align="left" style="padding-left:5px; padding-right: 8px;">
			<dd>
				<ul id="treeDemo" class="ztree"></ul>
			</dd>
		</div>
	</div>
	<div id="bodyer_right">
	    <iframe id="fra" name="standingbook" width="100%" height="100%" scrolling="no" frameborder="0"></iframe>
	</div>
	<div style="clear: both"></div>
</div>
<!--内容部分结束-->

<%@ include file="/view/common/footer.jsp"%>