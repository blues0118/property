<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/util.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ba-resize.min.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/js/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/zTree/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript">

	var setting = {
		data: {
			key: {
				name:"projectname"
			},
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "parentid"
			}
		},
		check: {
			enable: true
		},
		view:{
			dblClickExpand:false,
			showLine: true,
			selectedMulti: false
		},
		callback: {
			onClick: onClick
		}
	};
	
	function onClick(event, treeId,nodes) {
	
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		if (nodes.projecttype == 'L') {
			//$("#fra").attr("src","${pageContext.request.contextPath}/property/list.do?projeuctid=" + nodes.id);
			return ;
		}else{
			treeObj.expandNode(nodes);
		}
	}
	function callback(){
		
	}
	
	
	var nodes = ${projectList};
	
	for (var i=0;i<nodes.length;i++) {
		
		if (nodes[i].parentid == '0') {
			nodes[i].isParent = true;
			nodes[i].open = true;
			//nodes[i].icon = "${pageContext.request.contextPath}/images/20140926124231782_easyicon_net_16.png";
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
	
//-->
</script>

<!--内容部分开始-->

<div id="bodyer">
	<div class="scrollTable" align="left" style="padding-left:5px; padding-right: 8px;">
		<dd>
			<ul id="treeDemo" class="ztree"></ul>
		</dd>
	</div>
</div>
<!--内容部分结束-->

