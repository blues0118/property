$(function(){
	console.log();
	initStyle();//初始化样式
    //initData();//初始化 单元信息、住户资料、租赁合同
    
});
var roles = "";

function loadComplete() {
	
}
function gridComplete() {
	
}

function gridSel() {
	
}
function gridState() {
	
}
function callback() {
	loadAgreementData();
	loadMeterItemData();
	loadChargeItemData();
	loadMeterchargeItemData();
	loadStandingbookData();
}


function reloadGrid() {
	jQuery("#agreementDataGrid").trigger("reloadGrid");
	jQuery("#meterItemDataGrid").trigger("reloadGrid");
	jQuery("#chargeItemDataGrid").trigger("reloadGrid");
	jQuery("#meterchargeItemDataGrid").trigger("reloadGrid");
	jQuery("#standingbookDataGrid").trigger("reloadGrid");
}
function refresh() {
	reloadGrid();
}
var selectedIndex;
function initStyle(){
	var $div_li =$("div[id='tabAll'] > .tab_menu > ul > li");
	var $div_li_chargeItem =$("div[id='chargeItem'] > .tab_menu > ul > li");
    $div_li.click(function(){
		$(this).addClass("selected") //当前<li>元素高亮
			   .siblings().removeClass("selected");  //去掉其它同辈<li>元素的高亮
        var index =  $div_li.index(this);  // 获取当前点击的<li>元素 在 全部li元素中的索引。
        selectedIndex = index;
		$("div[id='tabAll'] > .tab_box > div") //选取子节点。不选取子节点的话，会引起错误。如果里面还有div
				.eq(index).show()   //显示 <li>元素对应的<div>元素
				.siblings().hide(); //隐藏其它几个同辈的<div>元素
	}).hover(function(){
		$(this).addClass("hover");
	},function(){
		$(this).removeClass("hover");
	});
	//收费项目
	$div_li_chargeItem.click(function(){
		$(this).addClass("selected") //当前<li>元素高亮
			   .siblings().removeClass("selected");  //去掉其它同辈<li>元素的高亮
        var index =  $div_li_chargeItem.index(this);  // 获取当前点击的<li>元素 在 全部li元素中的索引。
        console.log("div_li_chargeItem"+index);
		$("div[id='chargeItem'] > .tab_box > div") //选取子节点。不选取子节点的话，会引起错误。如果里面还有div
				.eq(index).show()   //显示 <li>元素对应的<div>元素
				.siblings().hide(); //隐藏其它几个同辈的<div>元素
	}).hover(function(){
		$(this).addClass("hover");
	},function(){
		$(this).removeClass("hover");
	});
    $('#unitcolor').cxColor();
}
//加载 单元信息、住户资料、租赁合同。抄表记录

function del(ids) {
		if (confirm("确定要删除选择的合同吗？")) {
		    $.ajax({
		        async : true,
		        url : "$('#contextPath')/agreement/delete.do",
		        type : 'post',
		        data: {ids:ids.toString()},
		        dataType : 'text',
		        success : function(data) {
		        	sessionOut(data);
		            if (data == "success") {
		            	alert("删除完毕。");
		            	window.location.reload();
		            } else {
		            	alert("可能因为您长时间没有操作，或读取数据时出错，请关闭浏览器，重新登录尝试或与管理员联系！");
		            }
		        }
		    });
		}
		
}
function delChargeitem(ids) {


		if (confirm("确定要删除选择的收费项目吗？")) {
		    $.ajax({
		        async : true,
		        url : "${pageContext.request.contextPath}/chargeitem/delete.do",
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
function save() {
    $.ajax({
        async : true,
        url : "${pageContext.request.contextPath}/property/save.do",
        type : 'post',
        data:$('#inputForm').serialize(),
        dataType : 'text',
        success : function(data) {
        	sessionOut(data);
            if (data == "success") {
            	alert("更新完毕。");
            } else {
            	alert("可能因为您长时间没有操作，或读取数据时出错，请关闭浏览器，重新登录尝试或与管理员联系!！");
            }
        }
    });
}
function closepage() {
	window.returnValue="ok";
	window.close();
}

function printTZD() {
	var unitId = $("#unitid").val();
    $.ajax({
        async : true,
        url : "../meterItem/listMeteritem.do",
        type : 'post',
        data:{unitId:unitId.toString()},
        dataType : 'text',
        success : function(data) {
        	sessionOut(data);
        	create_print_data(data);
        }
    });
}
//单元台帐右键菜单
function standingbookMenu() {
	$("#gview_standingbookDataGrid").contextMenu('myMenu_standingbook', {
    	menuStyle: {
    		backgroundColor: '#fcfdfd',
            border: '2px solid #a6c9e2',
            //maxWidth: '600px',
            width: '170px', // to have good width of the menu
            padding:'1px 1px 5px 1px'
	    },
        itemStyle : {
          //fontFamily: 'verdana',
          //backgroundColor: '#666',
          //color: '#1d5987',
          //border: 'none',
          //padding: '1px'
        },
        shadow:false,
        itemHoverStyle: {
        	border: '1px solid #79b7e7',
	        color: '#1d5987',
	        backgroundColor: '#d0e5f5',
	        cursor:'pointer'
        },
        bindings: {
          'add': function(t) {
        	addChargeitemforunit();
          },
          'del': function(t) {
        	delChargeitemforunit();
          }
        }
    });
}
//租赁合同右键菜单
function agreementMenu() {
	$("#gview_agreementDataGrid").contextMenu('myMenu_agreement', {
    	menuStyle: {
    		backgroundColor: '#fcfdfd',
            border: '2px solid #a6c9e2',
            //maxWidth: '600px',
            width: '170px', // to have good width of the menu
            padding:'1px 1px 5px 1px'
	    },
        itemStyle : {
          //fontFamily: 'verdana',
          //backgroundColor: '#666',
          //color: '#1d5987',
          //border: 'none',
          //padding: '1px'
        },
        shadow:false,
        itemHoverStyle: {
        	border: '1px solid #79b7e7',
	        color: '#1d5987',
	        backgroundColor: '#d0e5f5',
	        cursor:'pointer'
        },
        bindings: {
          'add': function(t) {
            addAgreement();
          },
          'del': function(t) {
            delAgreement();
          }
        }
    });
}
//收费项目右键菜单
function chargeitemMenu() {
	$("#gview_chargeItemDataGrid").contextMenu('myMenu_chargeitem', {
    	menuStyle: {
    		backgroundColor: '#fcfdfd',
            border: '2px solid #a6c9e2',
            //maxWidth: '600px',
            width: '170px', // to have good width of the menu
            padding:'1px 1px 5px 1px'
	    },
        itemStyle : {
          //fontFamily: 'verdana',
          //backgroundColor: '#666',
          //color: '#1d5987',
          //border: 'none',
          //padding: '1px'
        },
        shadow:false,
        itemHoverStyle: {
        	border: '1px solid #79b7e7',
	        color: '#1d5987',
	        backgroundColor: '#d0e5f5',
	        cursor:'pointer'
        },
        bindings: {
          'add': function(t) {
            addChargeitem();
          },
          'del': function(t) {
            delChargeitem();
          }
        }
    });
}
//收费项目右键菜单
function meterchargeitemMenu() {
	$("#gview_meterchargeItemDataGrid").contextMenu('myMenu_chargeitem', {
    	menuStyle: {
    		backgroundColor: '#fcfdfd',
            border: '2px solid #a6c9e2',
            //maxWidth: '600px',
            width: '170px', // to have good width of the menu
            padding:'1px 1px 5px 1px'
	    },
        itemStyle : {
          //fontFamily: 'verdana',
          //backgroundColor: '#666',
          //color: '#1d5987',
          //border: 'none',
          //padding: '1px'
        },
        shadow:false,
        itemHoverStyle: {
        	border: '1px solid #79b7e7',
	        color: '#1d5987',
	        backgroundColor: '#d0e5f5',
	        cursor:'pointer'
        },
        bindings: {
          'add': function(t) {
            addChargeitem();
          },
          'del': function(t) {
            delChargeitem();
          }
        }
    });
}
function getGridSelectids(gridObject) {
	var rownumbers = "";
	rownumbers = $("#"+gridObject).jqGrid('getGridParam','selarrrow');
	var str = getSelectid(gridObject,rownumbers);
	return str;
}

function getSelectid(gridObject,rows) {
	var result = new Array();
	for (var i=0;i<rows.length;i++) {
		var rowData = $("#"+gridObject).jqGrid('getRowData',rows[i]);
		result[i] = rowData["id"] ;
	}
	
	return result.toString();
}
function upload(id){
	if (id == "") {
		alert("请先选择要修改的数据。");
		return;
	}
	
	
	var url = "../agreement/upload.do?id="+id + "&time="+Date.parse(new Date());
	
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
