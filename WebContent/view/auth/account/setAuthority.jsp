<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/js/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/zTree/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/json2.js"></script>
<base target="_self">

<script>
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
		var authProArray = new Array();
		authProArray = ${proList};
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			for (var i=0;i<authProArray.length;i++) {
				var node = treeObj.getNodeByParam("id", authProArray[i], null);
				if (node != null) {
					treeObj.checkNode(node, true, false);
				}
			}
		
	});
	
	
	function closepage() {
		//获取当前窗口索引
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}
	
	function setchecknodes() {
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = treeObj.getCheckedNodes(true);
		
		var arrayObj = new Array();
		for(var i=0;i<nodes.length;i++) {
			arrayObj.push(nodes[i].id);
		}
		
		var b = JSON.stringify(arrayObj);
	    $.ajax({
	        async : true,
	        url : "${pageContext.request.contextPath}/account/tosetAuthority.do",
	        type : 'post',
	        data:{proListString:b,id:"${account_id}"},
	        dataType : 'text',
	        success : function(data) {
	            if (data == "success") {
	            	alert("授权完毕。");
	            	
	            } else {
	            	alert("可能因为您长时间没有操作，或读取数据时出错，请关闭浏览器，重新登录尝试或与管理员联系!！");
	            }
	        }
	    });
	}
	
</script>
<title>设置物业项目访问权限</title>
</head>
<body>
	<table id="data_table"   class="data_table table-Kang" aline="left" width="98%" 
				 border=0 cellspacing="1" cellpadding="4" >
		<tbody>
			<tr >
                <td colspan="2" align="center">为账户设置物业项目访问权限</td>
            </tr>
			<tr>
				<td align="center" colspan="2"><ul id="treeDemo" class="ztree"></ul></td>
			</tr>
			<tr >
				<td align="center" colspan="2">
					<button type="button" onclick="setchecknodes()">授权</button>
					<button type="button" onclick="closepage()">关闭</button>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>