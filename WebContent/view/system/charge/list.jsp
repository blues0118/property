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
<style>
<!--
 *{ margin:0; padding:0;}
 body { font:12px/19px Arial, Helvetica, sans-serif; color:#666;}
 .tab { margin:10px;}
 .tab_menu { clear:both;}
 .tab_menu li { float:left; text-align:center; cursor:pointer; list-style:none; padding:1px 6px; margin-right:4px; background:#F1F1F1; border:1px solid #898989; border-bottom:none;}
 .tab_menu li.hover { background:#DFDFDF;}
 .tab_menu li.selected { color:#FFF; background:#6D84B4;}
 .tab_box { clear:both; border:1px solid #898989; padding:10px; }
-->
</style>
<script type="text/javascript">
<!--
	var iswatch = 0;
	function loadComplete() {
		
	}
	function gridComplete() {
		
	}
	
	function gridSel() {
		
	}
	function gridState() {
		
	}
	
	$(function(){
		//收费项目
		var $div_li_chargeItem =$("div[id='chargeItem'] > .tab_menu > ul > li");
		$div_li_chargeItem.click(function(){
			$(this).addClass("selected") //当前<li>元素高亮
				   .siblings().removeClass("selected");  //去掉其它同辈<li>元素的高亮
	        var index =  $div_li_chargeItem.index(this);  // 获取当前点击的<li>元素 在 全部li元素中的索引。
	        console.log("div_li_chargeItem"+index);
			$("div[id='chargeItem'] > .tab_box > div") //选取子节点。不选取子节点的话，会引起错误。如果里面还有div
					.eq(index).show()   //显示 <li>元素对应的<div>元素
					.siblings().hide(); //隐藏其它几个同辈的<div>元素

			iswatch = $(this).attr('id');
			loadData(iswatch);
			
		}).hover(function(){
			$(this).addClass("hover");
		},function(){
			$(this).removeClass("hover");
		});
	});
	
	function callback() {
		loadData(iswatch);
	}
	
	jQuery.extend($.fn.fmatter, {
        funFormatter: function (cellvalue, options, rowdata) {
		    var result = '';//'<div style="float:left;cursor:pointer;"><a title="设置角色功能访问权限" href="javascript:;" onclick="auth(\''+rowdata.id+'\')"><span class="ui-icon ui-icon-gear"></span></a></div>';
		    result += '<div style="float:left;cursor:pointer;"><a title="修改收费项" href="javascript:;" onclick="edit(\''+rowdata.id+'\')"><span class="ui-icon ui-icon-pencil"></span></a></div>';
		    //result += '<div style="float:left;cursor:pointer;"><a title="查看拥有该角色的帐户" href="javascript:;" onclick="searchAccount(\''+rowdata.id+'\')"><span class="ui-icon ui-icon-person"></span></a></div>';
		    return result;
        }
    });
	
	function loadData(v) {
		var title = "收费项管理";
		var pageer = "#pager";
		var colNames;
		var colModel;
		var datatype = "json";
		var page = 50;
		var size;
		var url = "${pageContext.request.contextPath}/charge/list.do";
		if(v == 0){
			colNames = ['操作','费用项目名称','描述'];
			colModel = [ 
	           {name:'fun',index:'fun', width:60,fixed:true,resizable:false,align:"center",frozen:true,formatter:"funFormatter"}, 
	           {name:'itemcode',index:'itemcode', width:100,align:"center"}, 
	           {name:'chargeremark',index:'chargeremark', width:100,align:"center"}
			];
		}else{
			colNames = ['操作','费用项目名称','单价','描述'];
			colModel = [ 
	           {name:'fun',index:'fun', width:60,fixed:true,resizable:false,align:"center",frozen:true,formatter:"funFormatter"}, 
	           {name:'itemcode',index:'itemcode', width:100,align:"center"}, 
	           {name:'watch_price',index:'watch_price', width:100,align:"center"},
	           {name:'chargeremark',index:'chargeremark', width:100,align:"center"}
			];
		}
		
		//size = $(".scrollTable").height() - 45;
		size = $(window).height()-120;
		
		var searchTxt = $("#searchTxt").val();
		var postData={searchTxt:searchTxt,iswatch:v};
		
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
		//调用charge.util.js里的添加角色方法。
		parent.charge.util.add();
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
			alert("请选择要删除的收费项。");
			return;
		}
		
		if (confirm("确定要删除选择的收费项吗？")) {
		    $.ajax({
		        async : true,
		        url : "${pageContext.request.contextPath}/charge/delete.do",
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
		//调用charge.util.js里的修改收费项方法。
		parent.charge.util.edit(id);
	}
	
//-->
</script>


<!--内容部分开始-->

		<div class="top_dd" style="margin-bottom: 10px;position:relative;z-index:999; ">
			<div class="dqwz_l">当前位置：系统维护-收费管理</div>
			<div  class="caozuoan">
				[ <a href="#" onclick="add()">添加</a> ]
				[ <a href="#" onclick="del()">删除</a> ]
				[ <a href="#" onclick="refresh()">刷新列表</a> ]
	        </div>
	        <div style="clear:both"></div>
	    </div>
		<div class="hide" align="center" id="chargeItem">
			<div class="tab_menu">
				<ul>
					<li class="selected" id="0">收费项目</li>
					<li id="1">抄表收费项目</li>
				</ul>
			</div>
			<div class="tab_box">
				<div class = "hide" id = "chargeItem_div">
					<table id="dataGrid"></table>
					<div id="pager"></div>
				</div>
 			</div>
		</div>
