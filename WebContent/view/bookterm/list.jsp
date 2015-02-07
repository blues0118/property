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
<!-- 打印的js、css引入  
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css" type="text/css" />-->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>


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
		    result += '<button title="打印" type="button" onclick="printData(\''+rowdata.termcode+'\',\''+rowdata.id+'\',\''+rowdata.tremstatus+'\')">打印</button>';
		    result += '<button title="删除" type="button" onclick="del(\''+rowdata.id+'\',\''+rowdata.tremstatus+'\')">删除</button>';
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
		var page = 30;
		var size;
		var url = "${pageContext.request.contextPath}/bookterm/list.do?projectid="+document.all("projectid").value;
		
		colNames = ['总账期名称','备注', '创建时间', '创建人','总账期状态','操作'];
		colModel = [
                   {name:'termcode',index:'termcode',width:10, align:"center"},
		           {name:'termmemo',index:'termmemo',width:10, align:"center"},
		           {name:'createtime',index:'createtime',width:10, align:"center"},
		           {name:'createman',index:'createman',width:10, align:"center"}, 
		           {name:'tremstatus',index:'tremstatus',width:10, align:"center",formatter:"tremstatusFormatter"}, 
                   {name:'fun',index:'fun', fixed:true,resizable:false,width:180,align:"center",frozen:true,formatter:"funFormatter"}
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
		var page = 30;
		var size;
		var url = "${pageContext.request.contextPath}/bookterm/detaillist.do";
		
		colNames = ['单元名称','单元账期状态','操作'];
		colModel = [
                   {name:'unittermcode',index:'unittermcode', width:100,align:"center"},
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
		
		loadBookData("","","","");
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
		var page = 30;
		var size;
		var url = "${pageContext.request.contextPath}/bookterm/detailbooklist.do?";
		
		colNames = ['计费时间','收费时间', '收费项目名称','是否按表计费','上期读数','本期读数','台账状态','收费金额'];
		colModel = [
                   {name:'chargetime',index:'chargetime', width:100,align:"center"},
		           {name:'chargeovertime',index:'chargeovertime', width:100,align:"center"},
		           {name:'itemcode',index:'itemcode', width:100,align:"center"},
		           {name:'iswatch',index:'iswatch', width:100,align:"center"},
		           {name:'lastnumber',index:'lastnumber', width:100,align:"center"},
		           {name:'newnumber',index:'newnumber', width:100,align:"center"},
		           {name:'chargestatus',index:'chargestatus', width:100,align:"center",formatter:"tremstatusFormatter"},
		           {name:'chargesum',index:'chargesum', width:100,align:"center"}
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
	
	function del(id) {
		
		var accountcode = document.all("accountcode").value;
		
		if (accountcode != 'admin') {
			alert("本账户没有删除权限，如果要删除总账期，请和系统管理员联系。");
			return;
		}
		var ids = id;
		if (ids == "") {
			alert("请先选择要删除的数据。");
			return;
		}
    	if (confirm("确定要删除总账期吗？删除该总账期，将同时删除该总账期的一切附属信息。请谨慎操作。")) {
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
	
	function printData(termcode, id, status) {
		var myDate = new Date();
        var mytime=myDate.toLocaleTimeString();
        //alert(document.all("projectname").value);
        //alert(myDate.toLocaleString());
		var unitId = "1212";
		
	    $.ajax({
	        async : true,
	        url : "${pageContext.request.contextPath}/bookterm/booklistprint.do",
	        type : 'post',
	        data:{id:id.toString()},
	        dataType : 'text',
	        success : function(data) {
	        	sessionOut(data);
	        	create_print_data(data,termcode, id, status);
	        }
	    });
	}
	
	function ajml_html(field,data,termcode, id, status) {
		//？？将打印数据，转成对象
		var data_json = JSON.parse(data);
		var html = "";
		//?? Math.ceil 是script函数，四舍五入的，用来判断是否达到一页，如果是一页，就给div加上page-break-after: always，强制换页
		for (var i=0;i<Math.ceil(data_json.length/6);i++) {
			var index = 0;
			if (i == (Math.ceil(data_json.length/6) -1)) {
				html += "<div class=\"my_show\">";
			}
			else {
				html += "<div class=\"my_show\" style=\"page-break-after: always;\">";
			}
			html += "<table  cellspacing=\"0\" style=\"border-collapse: collapse; border-spacing: 0;background-color: transparent;max-width: 100%\" cellpadding=\"0\" width=\"100%\" class=\"print_font\">";
	        html += "<thead>";
	        html += "<tr>";
	        html += "<th colspan=\"7\">";
	        html += "<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\">";
	        html += "<tr align=\"center\">";
	        html += "<td  colspan=\"9\" style=\"font-size: 26px;padding-bottom: 5px;padding-top: 0px\">【"+termcode+"】报表</td>";
	        html += "</tr>";
	        html += "<tr align=\"left\">";
	        html += "<td  colspan=\"3\" align=\"left\" style=\"font-size: 12px;padding-bottom: 5px;padding-top: 0px\">"+document.all("projectname").value+"</td>";
	        if (status == 0) {
	        	html += "<td  colspan=\"3\" align=\"right\" style=\"font-size: 12px;padding-bottom: 5px;padding-top: 0px\">未结束</td>";
	        } else {
	        	html += "<td  colspan=\"3\" style=\"font-size: 12px;padding-bottom: 5px;padding-top: 0px\">已结束</td>";
	        }
	        var myDate = new Date();
	        html += "<td  colspan=\"9\" align=\"right\" style=\"font-size: 12px;padding-bottom: 5px;padding-top: 0px\">"+myDate.toLocaleString()+"</td>";
	        html += "</tr>";
	        html += "</table>";
			html += "</th>";
			html += "</thead>";
			html += "<tbody >";
			
			for (var j=i*6;j<i*6+6;j++) {
				if (j<data_json.length) {
					var rowspan = data_json[j][field.unittermid];
					rowspan = Number(rowspan) +2;
					html += "<tr style=\"font-size:12px;height:30px;border:1px #000 solid;text-align: center\">";
						html += "<td rowspan="+rowspan.toString()+" style=\"border:1px #000 solid;\">"+ (field.chargetime == 'NOTHING' ? '': data_json[j][field.chargetime]) +"</td>";
						html += "<td style=\"border:1px #000 solid;\">收款单状态:</td>";
						if (data_json[j][field.chargestatus]==0) {
							html += "<td style=\"border:1px #000 solid;\">未结束</td>";
						} else {
							html += "<td style=\"border:1px #000 solid;\">已结束</td>";
						}
						
						html += "<td style=\"border:1px #000 solid;\">发票号:</td>";
						html += "<td style=\"border:1px #000 solid;\">"+ (field.projectid == 'NOTHING' ? '' : data_json[j][field.projectid]) +"</td>";
						html += "<td style=\"border:1px #000 solid;\">经办人:</td>";
						html += "<td style=\"border:1px #000 solid;\">"+ (field.itemid == 'NOTHING' ? '' : data_json[j][field.itemid]) +"</td>";
						html += "<td style=\"border:1px #000 solid;\">收款日期:</td>";
						html += "<td style=\"border:1px #000 solid;\">"+ (field.chargeovertime == 'NOTHING' ? '' : data_json[j][field.chargeovertime]) +"</td>";
						html += "</tr>";
						html += "<tr style=\"font-size:12px;height:30px;border:1px #000 solid;text-align: center\">";

						html += "<th style=\"border:1px #000 solid;\">序号</td>";
						html += "<th style=\"border:1px #000 solid;\">项目名称</td>";
						html += "<th style=\"border:1px #000 solid;\">起始</td>";
						html += "<th style=\"border:1px #000 solid;\">终止</td>";
						html += "<th style=\"border:1px #000 solid;\">数量</td>";
						html += "<th style=\"border:1px #000 solid;\">金额</td>";
						html += "<th colspan=\"2\" style=\"border:1px #000 solid;\">备注</td>";
						html += "</tr>";
						html += "<tr style=\"font-size:12px;height:30px;border:1px #000 solid;text-align: center\">";

						html += "<td style=\"border:1px #000 solid;\">1</td>";
						html += "<td style=\"border:1px #000 solid;\">"+ (field.itemcode == 'NOTHING' ? '' : data_json[j][field.itemcode]) +"</td>";
						html += "<td style=\"border:1px #000 solid;\">"+ (field.lastnumber == 'NOTHING' ? '' : data_json[j][field.lastnumber]) +"</td>";
						html += "<td style=\"border:1px #000 solid;\">"+ (field.newnumber == 'NOTHING' ? '' : data_json[j][field.newnumber]) +"</td>";
						html += "<td style=\"border:1px #000 solid;\">"+ (field.newnumber == 'NOTHING' ? '' : data_json[j][field.newnumber]-data_json[j][field.lastnumber]) +"</td>";
						html += "<td style=\"border:1px #000 solid;\">"+ (field.chargesum == 'NOTHING' ? '' : data_json[j][field.chargesum]) +"</td>";
						html += "<td colspan=\"2\" style=\"border:1px #000 solid;\">"+ (field.bookmemo == 'NOTHING' ? '' : data_json[j][field.bookmemo]) +"</td>";
						html += "</tr>";
					
					if (rowspan > 3) {
						for(var m=0;m<rowspan-3;m++) {
							j++;
							var rowxuh=2+m;
							//alert(rowxuh);
							html += "<tr style=\"font-size:12px;height:30px;border:1px #000 solid;text-align: center\">";

							html += "<td style=\"border:1px #000 solid;\">"+rowxuh.toString()+"</td>";
							html += "<td style=\"border:1px #000 solid;\">"+ (field.itemcode == 'NOTHING' ? '' : data_json[j][field.itemcode]) +"</td>";
							html += "<td style=\"border:1px #000 solid;\">"+ (field.lastnumber == 'NOTHING' ? '' : data_json[j][field.lastnumber]) +"</td>";
							html += "<td style=\"border:1px #000 solid;\">"+ (field.newnumber == 'NOTHING' ? '' : data_json[j][field.newnumber]) +"</td>";
							html += "<td style=\"border:1px #000 solid;\">"+ (field.newnumber == 'NOTHING' ? '' : data_json[j][field.newnumber]-data_json[j][field.lastnumber]) +"</td>";
							html += "<td style=\"border:1px #000 solid;\">"+ (field.chargesum == 'NOTHING' ? '' : data_json[j][field.chargesum]) +"</td>";
							html += "<td colspan=\"2\" style=\"border:1px #000 solid;\">"+ (field.bookmemo == 'NOTHING' ? '' : data_json[j][field.bookmemo]) +"</td>";
							html += "</tr>";
						}
					}
					
				}else {	//这里判断在全部打印完后，填充满空白格
					html += "<tr class=\"content_tr\" style=\"height:50px;text-align: center\">";
					html += "<td></td>";
					html += "<td></td>";
					html += "<td></td>";
					html += "<td></td>";
					html += "<td></td>";
					html += "<td></td>";
					html += "<td></td>";
					html += "</tr>";
				}
			}
			html += "</tbody>";
			html += "</table>";
	        html += "</div>";
		}
		console.log("html============"+html);
		//alert(html);
		
		return html;
	}
	//?? 这个就是打印了，实际就是将数据创建一个动态的iframe，调用浏览器打印，参数这样写就行。
	function onPrint() {
	    $(".my_show").jqprint({
	        importCSS:false,
	        debug:false
	    });
	}

	function create_print_data(data,termcode, id, status) {
		var data_json = JSON.parse(data);
		if (data_json.length == 0) {
			alert ("打印对象没有，请重新选择。");
			return;
		}
		//获取打印字段对应
		var field = {};
		field.unitid = "unitid";
		field.noteid = "noteid";
		field.itemcode = "itemcode";
		field.lastnumber = "lastnumber";
		field.newnumber = "newnumber";
		field.chargesum = "chargesum";
		field.bookmemo = "bookmemo";
		field.unittermid = "unittermid";
		field.chargeovertime = "chargeovertime";
		field.chargestatus = "chargestatus";
		field.projectid = "projectid";
		field.itemid = "itemid";
		field.chargetime = "chargetime";
		var html = ajml_html(field,data,termcode, id, status);
		$("#print_div").html(html);
		onPrint();
	}
	
	function changeStatus() {
		var url = "${pageContext.request.contextPath}/bookterm/changestatus.do";
		parent.$.layer({
	        type: 2,
	        title: '扫条形码',
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
</script>


<!--内容部分开始-->

		<div class="top_dd" style="margin-bottom: 10px;position:relative;z-index:5555;">
			<div id="booktermenu" class="dqwz_l">当前位置：台账管理－总台账账期</div>
			<div id="uinttermenu" class="dqwz_l">当前位置：台账管理－总台账账期－单元账期</div>
			<div id="booktermbt" class="caozuoan">
				[ <a href="#" onclick="add()">添加总账期</a> ]
				<input type="text" id="searchTxt" name="searchTxt" />
				<button type="button" onclick="loadData()">查询</button>
				<button type="button" onclick="changeStatus()">扫码</button>
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
		<div id="print_div" style="height:0px;width:0px;overflow:hidden"></div>
		<div>
			<input type="hidden" name="projectid" value="${projectid}"/>
			<input type="hidden" name="projectname" value="${projectname}"/>
			<input type="hidden" name="accountcode" value="${accountcode}"/>
		</div>
