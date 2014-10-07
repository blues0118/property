<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/util.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ba-resize.min.js"></script>


<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table_main.css" type="text/css">


<style>
<!--
body {
	position: relative;
	font:12px/19px Arial, Helvetica, sans-serif; color:#666;
}

.bs-glyphicons {
	margin: 0 -10px 20px;
	overflow: hidden
}

.bs-glyphicons-list {
	padding-left: 0;
	list-style: none
}

.bs-glyphicons li {
	float: left;
	width: 90px;
	height: 90px;
	padding: 10px;
	line-height: 1.4;
	text-align: center;
	background-color: #f9f9f9;
	border: 1px solid #fff
}

.bs-glyphicons .glyphicon {
	margin-top: 5px;
	margin-bottom: 10px;
	font-size: 24px
}

.bs-glyphicons .glyphicon-class {
	display: block;
	text-align: center;
	word-wrap: break-word
}

.bs-glyphicons li:hover {
	color: #fff;
	background-color: #563d7c
}


.bs-customizer .toggle {
	float: right;
	margin-top: 25px
}

.bs-customizer label {
	margin-top: 10px;
	font-weight: 500;
	color: #555
}
-->
</style>

<script type="text/javascript">
	
	var rowNum = '';
	
	var unitmap = ${UnitMap};
	
	$(function(){
		init();
	});
	
	function callback(){
		
	}
	
	function init(){
		for(var i=0;i<20;i++){
			$('#unitData').append(buildRow(i+1));
		}
		$('#unitData li').live('click',click);
	}
	
	function click(){
		$('li[rownum='+rowNum+']').css("background-color","#f9f9f9");
		if($(this).attr('isdata') != '1'){
			rowNum = $(this).attr('rownum');
			$(this).css("background-color","#563d7c");
		}else{
			rowNum = '';
		}
	}
		
	function buildRow(rNum){
		
		var row = '';
		var tmp_rowNum = '';
		for(var i=0;i<20;i++){
			tmp_rowNum = rNum + '-' + (i+1);
			
			if(unitmap[tmp_rowNum]){
				row += '<li isdata="1" rownum="'+tmp_rowNum+'" style="background-color:'+unitmap[tmp_rowNum]['unitcolor']+'">';
				row += '<span>' + unitmap[tmp_rowNum]['unitcode'] + '</span><br/>';
				row += '<span>' + unitmap[tmp_rowNum]['using_area'] + '㎡</span>';
			}else{
				row += '<li rownum="'+tmp_rowNum+'">';
			}
			row += '</li>';
		}
		return row;
	}
	function add(){
		if(!rowNum){
			alert('请选择要添加单元的位置！');
			return;
		}
		var url = "add.do?rowNum="+rowNum+"&projeuctid=${projeuctid}&time="+Date.parse(new Date());
		var whObj = { width: 764, height: 400 };
		openShowModalDialog(url,window,whObj);
		window.location.reload();
	}
	

//-->
</script>

<body>
<!--内容部分开始-->

	
		<div class="top_dd" style="margin-bottom: 10px;position:relative;z-index:999; ">
			
			<div  class="caozuoan">
				[ <a id="addUnit" href="javascript:add();" title="添加单元信息" >增加</a> ]
				[ <a id="addProject" href="#" title="打印" onclick="return false;">打印</a> ]
	         </div>
	         <div style="clear:both"></div>
		</div>
		
		<div class="bs-glyphicons">
			<ul class="bs-glyphicons-list" id="unitData">
				
			</ul>
		</div>
		
<!--内容部分结束-->
</body>
