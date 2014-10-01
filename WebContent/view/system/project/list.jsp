<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/util.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ba-resize.min.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table_main.css" type="text/css">

<link rel="stylesheet" href="${pageContext.request.contextPath}/js/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/zTree/js/jquery.ztree.all-3.5.min.js"></script>



<style>
<!--
body{ height:100%; margin:0; font-size:12px; font-family:"微软雅黑";  }
a{ text-decoration:none;  font-size:12px; color:#1874CD;}
-->
</style>


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
				beforeRename: beforeRename,
				onRename: onRename,
				onRemove: onRemove,
				onClick: onClick
			}
	};
	
	function onClick(event, treeId, nodes) {
		if (nodes.treetype != 'L') {
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			treeObj.expandNode(nodes);
		}
	};
	
	function edit() {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getSelectedNodes(),
		treeNode = nodes[0];
		if (nodes.length == 0) {
			alert("请先选择一个节点");
			return;
		}
		zTree.editName(treeNode);
	};
	
	function remove(e) {
		if (confirm("确定要删除选择的项目吗？将同时删除该项目下包含的其他附属数据。请谨慎操作。")) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getSelectedNodes(),
			treeNode = nodes[0];
			if (nodes.length == 0) {
				alert("请先选择一个节点");
				return;
			}
			zTree.removeNode(treeNode, true);
		}
	};
	
	function onRemove(e, treeId, treeNode) {
		$.ajax({
	        async : false,
	        url : "${pageContext.request.contextPath}/project/delete.do",
	        type : 'post',
	        data : {
				'id' : treeNode.id
			},
	        dataType : 'text',
	        success : function(data) {
	        	sessionOut(data);
	        }
	    });
	}
	
	function beforeRename(treeId, treeNode, newName, isCancel) {
		if (newName.length == 0) {
			alert("节点名称不能为空.");
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			setTimeout(function(){zTree.editName(treeNode)}, 10);
			return false;
		}
		return true;
	}
	
	function onRename(event, treeId, treeNode, isCancel) {
		var newNode = {};
		newNode.id = treeNode.id;
		newNode.projectname = treeNode.projectname;
		
	    $.ajax({
	        async : false,
	        url : "${pageContext.request.contextPath}/project/update.do",
	        type : 'post',
	        data:newNode,
	        dataType : 'text',
	        success : function(data) {
	        	sessionOut(data);
	        	
	        }
	    });
		
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
		
		$("#addProject").bind("click", {projectType:'P'}, add);
		$("#addFolder").bind("click", {projectType:'F'}, add);
		$("#addLeaf").bind("click", {projectType:'L'}, add);
		$("#edit").bind("click", edit);
		$("#remove").bind("click", remove);
	});
	
	function callback() {
	}
	
	var newCount = 1;
	function add(e) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		
		projecttype = e.data.projectType;
		
		nodes = zTree.getSelectedNodes();
		treeNode = nodes[0];
		
		if (typeof(treeNode) == "undefined") {
			alert("请选中左侧物业项目管理树，再尝试创建。");
			return;
		}
		else if (treeNode.projecttype == "L") {
			alert("楼作为物业管理单元的基本单位，不能再向下创建。");
			return;
		}
		
		var newId = "";
		var projectname = "";
		
		if (projecttype == "P") {
			projectname = "新物业项目" + newCount;
		}
		else if (projecttype == "F") {
			projectname = "新物业项目分类" + newCount;
		}
		else {
			projectname = "新物业楼" + newCount;
		}
		
		var newNode = {};
		newNode.projectname = projectname;
		newNode.projecttype = projecttype;
		newNode.parentid = treeNode.id;
		
		//var data = JSON.stringify(newNode);
		
	    $.ajax({
	        async : false,
	        url : "${pageContext.request.contextPath}/project/save.do",
	        type : 'post',
	        data:newNode,
	        dataType : 'text',
	        success : function(data) {
	        	sessionOut(data);
	        	newId = data;
	        }
	    });
		
	    newNode.id = newId;
		if (projecttype == "P") {
			newNode.isParent = true;
			newNode.icon = "${pageContext.request.contextPath}/images/20140926124231782_easyicon_net_16.png";
		}
		else if (projecttype == "F") {
			newNode.isParent = true;
			newNode.iconOpen = "${pageContext.request.contextPath}/images/folder-open.gif";
			newNode.iconClose = "${pageContext.request.contextPath}/images/folder.gif";
		}
		else {
			newNode.isParent = false;
			newNode.icon = "${pageContext.request.contextPath}/images/tree-leaf.gif";
		}
		
		if (treeNode) {
			treeNode = zTree.addNodes(treeNode, newNode);
		}
		
		if (treeNode) {
			zTree.editName(treeNode[0]);
		} else {
			alert("叶子节点被锁定，无法增加子节点");
		}
	};
	
//-->
</script>


<!--内容部分开始-->

	
		<div class="top_dd" style="margin-bottom: 10px;position:relative;z-index:999; ">
			<div class="dqwz_l">当前位置：系统维护-项目管理</div>
			<div  class="caozuoan">
				[ <a id="addProject" href="#" title="添加物业项目" onclick="return false;">添加物业项目</a> ]
				[ <a id="addFolder" href="#" title="添加项目分类" onclick="return false;">添加项目分类</a> ]
	            [ <a id="addLeaf" href="#" title="添加楼" onclick="return false;">添加楼</a> ]
	            [ <a id="edit" href="#" title="编辑名称" onclick="return false;">编辑名称</a> ]
	            [ <a id="remove" href="#" title="删除" onclick="return false;">删除</a> ]
	         </div>
	         <div style="clear:both"></div>
		</div>
		<div class="scrollTable" align="left" style="padding-left:5px; padding-right: 8px;">
			<dd>
				<ul id="treeDemo" class="ztree"></ul>
			</dd>
		</div>
<!--内容部分结束-->

