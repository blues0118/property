<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/public.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/util.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.ba-resize.min.js"></script>


<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/system.css">



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
			$('#con_right').append(buildRow(i+1));
		}
	}
	
		
	function buildRow(rNum){
		
		var row = '';
		var tmp_rowNum = '';
		var flag = false;
		var firstFlag = true;
		for(var i=0;i<20;i++){
			tmp_rowNum = rNum + '-' + (i+1);
			//add by wangzibo 每一行的第一个元素为一个复选框
			if(unitmap[tmp_rowNum]){
				if(firstFlag){
					flag = true;
					firstFlag = false;
        			row += '<div>';
					row += '<ul>';
					row += '<li  isdata="1" class="num">'+rNum+'<input type="checkbox" id = "'+tmp_rowNum+'" value="" onchange="chgCheckboxStatus(\''+tmp_rowNum+'\')" class="btn0"></li>';
					row += '<li isdata="1" rownum="'+tmp_rowNum+'" class="info c1" style="background-color:'+unitmap[tmp_rowNum]['unitcolor']+'">';
					row += 		'<div>';
					row += 			'<p><span class="mianji">NT12-11    '+unitmap[tmp_rowNum]['using_area']+'m<sup>2</sup> </span></p>';
					row += 			'<p>'+unitmap[tmp_rowNum]['unitcode']+'</p>';
					row += 		'</div>';
					row += 		'<div class="btns">';
					row += 			'<input type="checkbox" value="'+unitmap[tmp_rowNum]['id']+'" class="btn2">';
					row += 			'<input type="button" onclick="edit(\''+unitmap[tmp_rowNum]['id']+'\')"  class="xinxi">';
					row += 			'<input type="button" class="dayin">';
					row += 			'<input type="button" class="time">';
					row += 		'</div>';
					row += 		'<div class="tixing">';
					row += 			'<img src="${pageContext.request.contextPath}/images/r_07.png" width="27" height="24">';
					row += 		'</div>';
					row += '</li>';
				}else{
					row += '<li isdata="1" rownum="'+tmp_rowNum+'" class="info c1" style="background-color:'+unitmap[tmp_rowNum]['unitcolor']+'">';
					row += 		'<div>';
					row += 			'<p><span class="mianji">NT12-11    '+unitmap[tmp_rowNum]['using_area']+'m<sup>2</sup> </span></p>';
					row += 			'<p>'+unitmap[tmp_rowNum]['unitcode']+'</p>';
					row += 		'</div>';
					row += 		'<div class="btns">';
					row += 			'<input type="checkbox" value="'+unitmap[tmp_rowNum]['id']+'" class="btn2">';
					row += 			'<input type="button" onclick="edit(\''+unitmap[tmp_rowNum]['id']+'\')"  class="xinxi">';
					row += 			'<input type="button" class="dayin">';
					row += 			'<input type="button" class="time">';
					row += 		'</div>';
					row += 		'<div class="tixing">';
					row += 			'<img src="${pageContext.request.contextPath}/images/r_07.png" width="27" height="24">';
					row += 		'</div>';
					row += '</li>';
				}
				//add by wangzibo 若有数据则显示，若没有则显示一个空的标签
				
			}
		}
		if(flag){
			row += '<div style=" clear:both"></div>';
			row += '</ul>';
			row += '</div>';
		}
		return row;
	}
	function chgCheckboxStatus(id){
		if($("#"+id).attr('checked')=="checked"){
			$("#"+id).parent().parent().children().children(".btns").children("input[type=checkbox]").attr("checked","true");
		}else{
			$("#"+id).parent().parent().children().children(".btns").children("input[type=checkbox]").removeAttr("checked");
		}
	}
	function add(){
		var projectid = "${projeuctid}";
		var url = "../property/add.do?projeuctid="+projectid+"&time="+Date.parse(new Date());
		parent.$.layer({
	        type: 2,
	        title: '添加单元',
	        maxmin: false,
	        shadeClose: true, //开启点击遮罩关闭层
	        area : ['800px' , '460px'],
	        offset : ['', ''],
	        iframe: {src: url},
	        end:function() {
	        	//关闭弹出框1时，刷新frame内容
	        	$('#f').val("第一个弹出框，刷新后");
	        }
	    });
	}
	
	function addBatchLease(){
		var ids = getCheckboxIds();
		if (ids == "" ||ids == undefined) {
			alert("请选择需要添加业主的单元，请重新尝试，或与管理员联系。");
			return;
		}
		console.log("ids========"+ids);
		var projectid = "${projeuctid}";
		var url = "../lease/add.do?unitid="+ids+"&time="+Date.parse(new Date());
		//调用parent.util.js里的添加帐户方法。
		parent.$.layer({
	        type: 2,
	        title: '添加租赁合同',
	        maxmin: false,
	        shadeClose: true, //开启点击遮罩关闭层
	        area : ['850px' , '400px'],
	        offset : ['', ''],
	        iframe: {src: url},
	        end:function() {
	        	//关闭弹出框1时，刷新frame内容
	        	$('#f').val("第一个弹出框，刷新后");
	        }
	    });
	}
	
	function getCheckboxIds() {
		var ids;
		var num = 0;
		$("#con_right input[type='checkbox']").each(function(){
        if($(this).is(":checked")){
              if($(this).attr("value")!=''){
              	  if(num==0){
              	  	  ids = $(this).attr("value");
              	  }else{
              	  	  ids +=","+$(this).attr("value");
              	  }
              	  num++;
              }
        }
      })
      return ids;
	}
	function del() {
		var ids = getCheckboxIds();
		if (ids == "") {
			alert("没有获得要删除的单元，请重新尝试，或与管理员联系。");
			return;
		}
		if (confirm("确定要删除选择的单元吗？")) {
		    $.ajax({
		        async : true,
		        url : "${pageContext.request.contextPath}/property/delete.do",
		        type : 'post',
		        data: {ids:ids.toString()},
		        dataType : 'text',
		        success : function(data) {
		        	sessionOut(data);
		            if (data == "success") {
		            	alert("删除完毕。");
		            	window.location.reload();
		            } else {
		            	alert("可能因为您长时间没有操作，或读取数据时出错，请关闭浏览器，重新登录尝试或与管理员联系!！");
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
	
	
	var url = "../property/edit.do?id="+id + "&time="+Date.parse(new Date());
	
	parent.$.layer({
        type: 2,
        title: '查看或修改帐户信息',
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['1300px' , '500px'],
        offset : ['', ''],
        iframe: {src: url},
        end: function(){
        	//调用iframe子页面的刷新方法
        	tt.window.refresh();
        }
    });
}
	function refresh() {
		console.log("listlistlistlistlistlistlistlistlistlistlistlistlistlistlist");
		window.location.reload();
	}
</script>

<body>
	<!--内容部分开始-->

	<div id="top2">
		<div class="top2_left">
			您的当前位置：首页／物业管理／小区管理
		</div>
		<div class="top2_right">
			<div class="buttons">
				<a href="javascript:addBatchLease();" class="jia">&nbsp;</a><!-- 批量增加业主信息 -->
				<a href="javascript:add();" class="jia" alt="批量添加业主信息">&nbsp;</a>
				<a href="javascript:del();" class="jian" alt="新增单元">&nbsp;</a>
				<a href="#" class="money">&nbsp;</a>
				<a href="#" class="dayin1">&nbsp;</a>
				<div style="clear: both"></div>
			</div>
			<div id="seach" class="seach">
				<input type="text" class="txtbox">
				<input type="button" class="btn1" value="搜索">
			</div>

		</div>
		<div style="clear: both"></div>


	</div>
	<div id="con">
			<div id="con_right">
	
			</div>
	</div>



	<!--内容部分结束-->
</body>
