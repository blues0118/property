<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/view/common/header.jsp"%>
<%@ include file="/view/common/top_menu.jsp"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table_main.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/zTree/js/jquery.ztree.all-3.5.min.js"></script>

<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/js/jqgrid/jquery-ui/redmond/jquery-ui-1.8.2.custom.css"/>
<script src="${pageContext.request.contextPath}/js/jqgrid/jquery-ui/jquery-ui-1.8.2.custom.min.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/js/jqgrid/css/ui.jqgrid.css"/>
<script src="${pageContext.request.contextPath}/js/jqgrid/js/i18n/grid.locale-cn.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jqgrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jqgrid/js/jquery.common.archiveGrid.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jqgrid/plugins/jquery.tablednd.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jqgrid/plugins/jquery.contextmenu.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jqgrid/plugins/ui.multiselect.js" type="text/javascript"></script>


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
	
	//获取角色
	function readRole() {
		 $.ajax({
		        async : true,
		        url : "${pageContext.request.contextPath}/role/getRoles.do",
		        type : 'post',
		        dataType : 'text',
		        success : function(data) {
		        	sessionOut(data);
		            roles = JSON.parse(data);
		        }
		 });
	}
	
	
	$(function(){
		readRole();
	});
	
	function callback() {
		loadData();
	}
	
	jQuery.extend($.fn.fmatter, {
		accountstateFormatter: function (cellvalue, options, rowdata) {
			if (cellvalue != "0")
		    	return '<a title="帐户状态" href="javascript:;" onclick="updatestate(\''+rowdata.id+'\',0)"><font color=blue>启用</font></a>';
		    else 
		        return '<a title="帐户状态" href="javascript:;" onclick="updatestate(\''+rowdata.id+'\',1)"><font color=red>禁用</font></a>';
        },
        funFormatter: function (cellvalue, options, rowdata) {
		    var result = '<div style="float:left;cursor:pointer;"><a title="设置物业项目访问权限" href="javascript:;" onclick="show(\''+rowdata.id+'\')"><span class="ui-icon ui-icon-gear"></span></a></div>';
		    result += '<div style="float:left;cursor:pointer;"><a title="修改帐户信息" href="javascript:;" onclick="edit(\''+rowdata.id+'\')"><span class="ui-icon ui-icon-pencil"></span></a></div>';
		    result += '<div style="float:left;cursor:pointer;"><a title="修改帐户密码" href="javascript:;" onclick="update_password(\''+rowdata.id+'\')"><span class="ui-icon ui-icon-locked"></span></a></div>';
		    result += '<div style="float:left;cursor:pointer;"><a title="设置帐户角色" href="javascript:;" onclick="setrole(\''+rowdata.id+'\')"><span class="ui-icon ui-icon-alert"></span></a></div>';
		    return result;
	    },
	    roleFormatter:function (cellvalue, options, rowdata) {
	    	if (typeof(cellvalue) != "undefined" && cellvalue != "") {
	    		for (var i=0;i<roles.length;i++) {
	    			if (roles[i].id == cellvalue) {
	    				return roles[i].rolecode;
	    				break;
	    			}
	    		}
	    	}
	    	else {
	    		return "";
	    	}
	    }
    });
	
	function loadData() {
		var title = "帐户管理";
		var pageer = "#pager";
		var colNames;
		var colModel;
		var datatype = "json";
		var page = 50;
		var size;
		var url = "${pageContext.request.contextPath}/account/list.do";
		
		colNames = ['操作','帐号','姓名', '帐户描述', '状态','角色'];
		colModel = [ 
		           {name:'fun',index:'fun', width:80,fixed:true,resizable:false,align:"center",frozen:true,formatter:"funFormatter"}, 
		           {name:'accountcode',index:'accountcode', width:100,align:"center"}, 
		           {name:'accountname',index:'accountname', width:100,align:"center"}, 
		           {name:'accountmemo',index:'accountmemo', width:100,align:"center"},
		           {name:'accountstate',index:'accountstate', width:100,formatter:"accountstateFormatter",align:"center"}, 
		           {name:'roleid',index:'roleid', width:80, align:"center",formatter:"roleFormatter"}
		];
		size = $(".scrollTable").height()-75;
		
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
	
	function reloadGrid() {
		jQuery("#dataGrid").trigger("reloadGrid");
	}
	
	function getGridSelectids() {
		var rownumbers = "";
		rownumbers = $("#dataGrid").jqGrid('getGridParam','selarrrow');
		return rownumbers;
	}
	
	function add(){
		var url = "add.do?time="+Date.parse(new Date());
		var whObj = { width: 540, height: 400 };
		var result = openShowModalDialog(url,window,whObj);
		refresh();
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
		var whObj = { width: 540, height: 400 };
		var result = openShowModalDialog(url,window,whObj);
		
		refresh();
	}
	
	function update_password(id) {
		if (id == "") {
			alert("请先选择要修改密码的帐户。");
			return;
		}
		
		var url = "updatepass.do?id="+id + "&time="+Date.parse(new Date());
		var whObj = { width: 540, height: 400 };
		var result = openShowModalDialog(url,window,whObj);
		
		//refresh();
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
	
	function setrole(id) {
		
		if (id == "1") {
			alert("超级帐户不能修改角色。");
			return;
		}
		
		var url = "setrole.do?id="+id + "&time="+Date.parse(new Date());
		var whObj = { width: 740, height: 500 };
		var result = openShowModalDialog(url,window,whObj);
		
		refresh();
	}
	
	function setauth(id) {
		var url = "setauth.do?id="+id + "&time="+Date.parse(new Date());
		var whObj = { width: 800, height: 500 };
		var result = openShowModalDialog(url,window,whObj);
	}


</script>


<!--内容部分开始-->

<div id="bodyer">
	<div id="bodyer_left">
		<dl>
			<dt>
				<a href="#" class="blue"><img src="${pageContext.request.contextPath}/images/i1_03.png" width="29" height="22" class="tubiao" />
					<span>
						帐户管理
					</span>
				</a>
			</dt>
			<dd>
				<ul id="treeDemo" class="ztree"></ul>
			</dd>
		</dl>
	</div>
	<div id="bodyer_right">
		<div class="top_dd" style="margin-bottom: 10px;position:relative;z-index:5555;">
			<div class="dqwz_l">当前位置：权限管理－帐户管理</div>
			<div  class="caozuoan">
	            <div style="float: right;margin-top: 3px;margin-left: 5px">
	            	<a href="javascript:;" onclick="add()"><img style="margin-bottom:-3px" src="${pageContext.request.contextPath}/images/icons/user_add.png"  />
		                    添加帐户</a>
		            <a href="javascript:;" onclick="del()"><img style="margin-bottom:-3px" src="${pageContext.request.contextPath}/images/icons/user_go.png"  />
		                    删除帐户</a>
		            <a href="javascript:;" onclick="refresh()"><img style="margin-bottom:-3px" src="${pageContext.request.contextPath}/images/icons/arrow_refresh.png"  />
		                    刷新列表</a>
					
				</div>
	         </div>
	         <div style="clear:both"></div>
	    </div>
		<div class="scrollTable" align="left" style="padding-left:5px; padding-right: 8px;" >
			<table id="dataGrid"></table>
			<div id="pager"></div>
		</div>
	</div>
	<div style="clear: both"></div>
</div>
<!--内容部分结束-->

<%@ include file="/view/common/footer.jsp"%>