<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
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

<style>
<!--
body{ height:100%; margin:0; font-size:12px; font-family:"微软雅黑";  }
a{ text-decoration:none;  font-size:12px; color:#1874CD;}
-->
</style>
 
<style type="text/css" media="print"> 
#accordion h3, #vcol, div.loading, div.ui-tabs-hide,ul.ui-tabs-nav li, td.HeaderRight { display:none } 
.ui-jqgrid-titlebar, .ui-jqgrid-title{ display:none } 
.ui-jqgrid-bdiv_self{position: relative; margin: 0em; padding:0; text-align:left;} 
#pager{display:none; z-index:-1;} 
</style> 


<script>

	$(function(){
		reload();
	})
	//var colNames = ['序号','单元编号','抄表日期','上期表数','本期表数','上期表数','本期表数','上期表数','本期表数'];
	var colNames = ${colNames};
	var colModel = ${colModel};
	function reload() {
		var size = $(window).height()-120;
		var temp;
		$("#dataGrid").jqGrid({
			url:'${pageContext.request.contextPath}/meter/meteritemList.do?meterid=${meterid}&projectid=${projectid}',
			datatype:"json",
			colNames:colNames,
			colModel:colModel,
			//data:mydata,
			caption:"抄表记录",
			size:size,
			cellEdit: true,
			cellsubmit: 'clientArray', 
			afterEditCell: function (id,name,val,iRow,iCol){
				//alert("马上要编辑了。。id:"+id+" name:" + name + " val="+val +" iRow="+iRow + " iCol="+iCol);
				temp = val;
			},
			afterSaveCell : function(id,name,val,iRow,iCol) { 
				//alert("编辑完了，焦点移开了。。id:"+id+" name:" + name + " val="+val +" iRow="+iRow + " iCol="+iCol);
				var lastNumber = jQuery("#dataGrid").getCell(iRow, iCol-1);
				if(parseInt(val) < parseInt(lastNumber)){
					jQuery("#dataGrid").setCell(iRow,iCol,temp,'');  
					alert("本期表数小于上期表数，请重新输入！");
				}else{
					var meteritemId = jQuery("#dataGrid").getCell(iRow, iCol-2);
					modifyMeterItem(id,meteritemId,val);
				}
			}
		});
		
		jQuery("#dataGrid").jqGrid('setGroupHeaders', {
		  useColSpanStyle: false, 
		  groupHeaders:${groupHeaders}
		});
	}

	function modifyMeterItem(id,name,val){
		$.ajax({
	       async : true,
	       url : "${pageContext.request.contextPath}/meter/update.do",
	       type : 'post',
	       data: {unitid:id,meteritemid:name,newnumber:val},
	       dataType : 'text',
	       success : function(data) {
	           if (data == "success") {
	           	alert("修改完毕。");
	           	//refresh();
	           }
	       }
	   });
	}

	function book(){
		var meterid = '${meterid}';
		var projectid = '${projectid}';
		$.ajax({
	       async : true,
	       url : "${pageContext.request.contextPath}/meter/book.do",
	       type : 'post',
	       data: {projectid:projectid,meterid:meterid},
	       dataType : 'text',
	       success : function(data) {
	           if (data == "success") {
	           	alert("修改完毕。");
	           	//refresh();
	           }
	       }
	   });
	}

	 
	$("#btnPrint").live("click", function () {
		window.focus(); 
		window.print(); 
		return false; 
	}); 
	var GridHeight; 
	function window.onbeforeprint() { 
		//打印前事件 
		var jqgridObj=jQuery("#dataGrid"); 
		GridHeight = jqgridObj.jqGrid('getGridParam', 'height');//获取高度 
		jqgridObj.jqGrid('setGridHeight', '100%');//将其高度设置成100%,主要是为了jqgrid 中有Scroll条时 能把该scroll条内内容都打印出来 
		$("#gview_jqgridlist .ui-jqgrid-bdiv").removeClass().addClass("ui-jqgrid-bdiv_self");//去除掉overflow属性
	} 
	function window.onafterprint() {//打印后事件 //放开隐藏的元素 
		$("#gview_jqgridlist .ui-jqgrid-bdiv_self").removeClass().addClass("ui-jqgrid-bdiv");//恢复overflow属性，否则会导致jqgrid中scroll条消失 
		jQuery("#dataGrid").jqGrid('setGridHeight', GridHeight);//设置成打印前的高度
	} 
	
</script>


<!--内容部分开始-->

		<div class="top_dd" style="margin-bottom: 10px;position:relative;z-index:5555;">
			<div class="dqwz_l">当前位置：抄表管理－抄表记录</div>
			<div  class="caozuoan">
				[ <a href="#" onclick="book()">计入台账</a> ]
				[ <a href="javascript:;" id="btnPrint">打印</a> ]
	         </div>
	         <div style="clear:both"></div>
	    </div>
		<div class="scrollTable" align="left" style="padding-left:5px; padding-right: 8px;" >
			<table id="dataGrid"></table>
			<div id="pager"></div>
		</div>
