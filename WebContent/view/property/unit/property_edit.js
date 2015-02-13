$(function(){
	console.log();
	initStyle();//初始化样式
    //initData();//初始化 单元信息、住户资料、租赁合同
	$.fn.zTree.init($("#treeDemo"), setting);
    
});
//start
var unittermid='';
var setting = {
		async: {
			enable: true,
			url:"../bookterm/listBookterm.do",
			autoParam:["id", "name=name", "level=lv"],
			otherParam:{"otherParam":"zTreeAsyncTest"},
			dataFilter: filter
		},
		callback:{
			onClick:refreshBookterm
		}
	};

	function filter(treeId, parentNode, childNodes) {
		if (!childNodes) return null;
		for (var i=0, l=childNodes.length; i<l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		}
		return childNodes;
	}
	function refreshBookterm(event, treeId, treeNode) {
	    //alert(treeNode.id + ", " + treeNode.name);
	    unittermid = treeNode.id;
	    $("#unittermid").val(unittermid);
	    loadStandingbookData();
	};

//end
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
	loadChargenoteData();
}


function reloadGrid() {
	jQuery("#agreementDataGrid").trigger("reloadGrid");
	jQuery("#meterItemDataGrid").trigger("reloadGrid");
	jQuery("#chargeItemDataGrid").trigger("reloadGrid");
	jQuery("#meterchargeItemDataGrid").trigger("reloadGrid");
	jQuery("#standingbookDataGrid").trigger("reloadGrid");
	jQuery("#chargenoteDataGrid").trigger("reloadGrid");
}
function refresh() {
	reloadGrid();
}
var selectedIndex;
function initStyle(){
	var $div_li =$("div[id='tabAll'] > .tab_menu > ul > li");
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
	
    $('#unitcolor').cxColor();
}
//加载 单元信息、住户资料、租赁合同。抄表记录

function del(ids) {
		if (confirm("确定要删除选择的合同吗？")) {
			var loadi = parent.layer.load(0);
			setTimeout(function () {
				$.ajax({
			        async : false,
			        url : '../agreement/delete.do',
			        type : 'post',
			        data: {
						ids:ids.toString()
					},
			        dataType : 'text',
			        success : function(data) {
			        	sessionOut(data);
			            if (data == "success") {
			            	parent.layer.close(loadi);
			            } else {
			            	alert("可能因为您长时间没有操作，或读取数据时出错，请关闭浏览器，重新登录尝试或与管理员联系!！");
			            }
			        }
			    });
				reloadGrid();
			},200);  
		}
		
}
function delChargeitem(ids) {
		//得到选中行的id
		var gridObject;
		gridObject = "chargeItemDataGrid";
		/*
		var str = "";
		var rownumbers = "";
		rownumbers = $("#"+gridObject).jqGrid('getGridParam','selarrrow');
		str = getSelectid(gridObject,rownumbers);
		//得到选中行的id
		
		if (str == "") {
			alert("请先选择要删除的数据。");
			return;
		}
		*/
		if (confirm("确定要删除选择的收费项目吗？")) {
			var loadi = parent.layer.load(0);
			setTimeout(function () {
				$.ajax({
			        async : false,
			        url : '../charge/delete.do',
			        type : 'post',
			        data: {
						ids:ids.toString()
					},
			        dataType : 'text',
			        success : function(data) {
			        	parent.layer.close(loadi);
			        }
			    });
				reloadGrid();
			},200); 
			
		}
		
}
function save() {
    $.ajax({
        async : true,
        url : "../property/save.do",
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
function saveUnit() {
	var projectid = $("#projeuctid").val();
	var url = "../property/save.do";
    $.ajax({
        async : true,
        url : url,
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
function saveLease() {
    $.ajax({
        async : true,
        url : "../lease/save.do",
        type : 'post',
        data:$('#inputForm').serialize(),
        dataType : 'text',
        success : function(data) {
        	sessionOut(data);
        	var result = $.parseJSON(data);
            if (result.status == "success") {
            	$("#leaseid").val(result.leaseid);
            	alert("保存成功。");
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
        	addMeterChargeitem();
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
	var loadi = parent.layer.load(0);
	parent.$.layer({
	    type: 2,
	    title: '查看或修改帐户信息',
	    maxmin: false,
	    shadeClose: true, //开启点击遮罩关闭层
	    area : ['1000px' , '500px'],
	    offset : ['', ''],
	    iframe: {src: url},
	    end: function(){
	    	parent.layer.close(loadi);
        	reloadGrid();
	    }
	});
}
//-----------------------------------------------------
var termid_ini;
var unittermid_ini;
jQuery.extend($.fn.fmatter, {
    funFormatter: function (cellvalue, options, rowdata) {
	    var result = '<button type="button" onclick="upload(\''+rowdata.id+'\')">上传</button>';
	    result += '<button type="button" onclick="delAgreement()">删除</button>';
	    return result;
    },
    itemcontentFormatter: function (cellvalue, options, rowdata) {
    	if(options.colModel.index =='itemtype'){
    		if(cellvalue =='1'){
    			return "收入";
    		}else if(cellvalue =='2'){
    			return "支出";
    		}else{
    			return '';
    		}
    	}else if(options.colModel.index =='itemcatagory'){
    		if(cellvalue =='1'){
    			return "正常";
    		}else if(cellvalue =='2'){
    			return "押金";
    		}else if(cellvalue =='3'){
    			return "预收款";
    		}else{
    			return '';
    		}
    	}else if(options.colModel.index =='itemmode'){
    		if(cellvalue =='1'){
    			return "使用面积";
    		}else if(cellvalue =='2'){
    			return "个数";
    		}else if(cellvalue =='3'){
    			return "建筑面积";
    		}
    	}else if(options.colModel.index =='itemunit'){
    		if(cellvalue =='1'){
    			return "按次收费";
    		}else if(cellvalue =='2'){
    			return "按天收费";
    		}else if(cellvalue =='3'){
    			return "按月收费";
    		}else if(cellvalue =='4'){
    			return "按年收费";
    		}else{
    			return '';
    		}
    	}else if(options.colModel.index =='chargecatagory'){
    		if(cellvalue =='1'){
    			return "周期性";
    		}else if(cellvalue =='2'){
    			return "一次性";
    		}else if(cellvalue =='3'){
    			return "临时性";
    		}else if(cellvalue =='4'){
    			return "季节性";
    		}else{
    			return '';
    		}
    	}else if(options.colModel.index =='chargeprice'){
    		if(cellvalue!=null && cellvalue!=undefined){
    			return cellvalue;//cellvalueJson[0].chargeprice+"/"+cellvalueJson[0].chargepriceunit;
    		}else{
    			return '';
    		}
    	}else if(options.colModel.index =='chargeperiod'){
    		if(cellvalue!=null && cellvalue!=undefined){
    			return cellvalue;//cellvalueJson[0].chargeperiod+"/"+cellvalueJson[0].chargeperiodunit;
    		}else{
    			return '';
    		}
    	}else if(options.colModel.index =='watch_price'){
    		if(cellvalue!=null && cellvalue!=undefined){
    			return cellvalue;//cellvalueJson[0].watch_price;
    		}else{
    			return '';
    		}
    	}else if(options.colModel.index =='iswatch'){
    		if(cellvalue =='0'){
    			return "否";
    		}else if(cellvalue =='1'){
    			return "是";
    		}else{
    			return '';
    		}
    	}else if(options.colModel.index =='watchtype'){
    		if(cellvalue =='0'){
    			return "水费";
    		}else if(cellvalue =='1'){
    			return "电费";
    		}else if(cellvalue =='2'){
    			return "燃气费";
    		}else{
    			return '';
    		}
    	}
	    return "";
    },
    watchtypeFormatter:function (cellvalue, options, rowdata) {
    	if(cellvalue =='0'){
			return "水费";
		}else if(cellvalue =='1'){
			return "电费";
		}else if(cellvalue =='2'){
			return "燃气费";
		}else{
			return '';
		}
    },
    chargeitemFunFormatter: function (cellvalue, options, rowdata) {
	    var result = '<button type="button" onclick="updateChargeitem(\''+rowdata.id+'\')">修改</button>';
	    result += '<button type="button" onclick="delChargeitem(\''+rowdata.id+'\')">删除</button>';
	    return result;
    },
    meterchargeitemFunFormatter: function (cellvalue, options, rowdata) {
	    var result = '<button type="button" onclick="updateChargeitem(\''+rowdata.id+'\')">修改</button>';
	    result += '<button type="button" onclick="delChargeitem(\''+rowdata.id+'\')">删除</button>';
	    return result;
    },
    standingbookFunFormatter: function (cellvalue, options, rowdata) {
    	var result;
    	if(rowdata.chargestatus=='0'){
		    result = '<button type="button" onclick="updateOneChargeitemforunit(\''+rowdata.id+'\')">修改</button>';
		    result += '<button type="button" onclick="delOneChargeitemforunit(\''+rowdata.id+'\')">删除</button>';
    	}else if(rowdata.chargestatus=='1'){
		   result="已结束";
    	}
	    return result;
    },
    chargenoteFunFormatter: function (cellvalue, options, rowdata) {
    	var result;
    	if(rowdata.chargestatus=='0'){
    		result = '<button type="button" onclick="chargenoteDetail(\''+rowdata.unittermid+'\')">详细</button>';
	    	result += '<button type="button" onclick="confirmReceived(\''+rowdata.unittermid+'\')">确认收款</button>';
    	}else if(rowdata.chargestatus=='1'){
		   result="已结束";
    	}
	    return result;
    },
    initDataFormatter: function (cellvalue, options, rowdata) {
	    if(options.colModel.index =='termid' && cellvalue!=''){
	    	termid_ini = cellvalue;
	    }else if(options.colModel.index =='unittermid' && cellvalue!=''){
	    	unittermid_ini = cellvalue;
	    }
	    console.log("termid_ini="+termid_ini+"==unittermid=="+unittermid_ini);
	    return cellvalue;
    },
    chargeStatusFormatter:function (cellvalue, options, rowdata) {
	    if(cellvalue =='0'){
	    	return "未结束";
	    }else if(cellvalue =='1'){
	    	return "已结束";
	    }
	    return "";
    },
    chargenotestatusFormatter:function (cellvalue, options, rowdata) {
	    if(cellvalue =='0'){
	    	return "未收款";
	    }else if(cellvalue =='1'){
	    	return "已收款";
	    }
	    return "";
    }
});
function confirmReceived(id){
	if(!confirm("确定要进行收款吗？")){
		return;
	}
	var url = "../book/confirmReceived.do";
	data={
		unittermid:id,
		id:$("#unitid").val()
	}
	console.log("requestData="+data);
	var result = httpPostReqeust(url, data);
	console.log(result);
	if(result!=null && result.status == true){
		reloadGrid();
	}else{
		alert("收款失败");
	}
}

function httpPostReqeust(url, data, _async, headerUserId){
//console.log('参数输出:');
//console.log(data);	//调试用
var async = false;// 是否异步 默认同步
if(_async){
	async = _async;
}
var _result = $.ajax({
	  url: url,
	  dataType: "json",
	  async: async,
	  cache: false,
	  data: data,
	  type: "POST"
}).responseText;
eval("var result="+_result);
return result;
}
function loadAgreementData() {
	var title = "租赁合同管理";
	var pageer = "#agreementPager";
	var colNames;
	var colModel;
	var datatype = "json";
	var page = 50;
	var size;
	var url = "../agreement/list.do";
	
	colNames = ['id','合同编号','开始日期', '结束日期', '住户姓名','证件类型','证件号码','电话号码','手机号码','操作'];
	colModel = [ 
			   {name:'id',index:'id',hidden:true, width:130,align:"center"},
	           {name:'leasecode',index:'leasecode', width:130,align:"center"},
	           {name:'beingdate',index:'beingdate', width:130,align:"center"},
	           {name:'enddate',index:'enddate', width:130,align:"center"},
	           {name:'leasename',index:'leasename', width:130,align:"center"},
	           {name:'leasetype',index:'leasetype', width:130,align:"center"},
	           {name:'leasenumber',index:'leasenumber', width:130,align:"center"},
	           {name:'phonenumber',index:'phonenumber', width:130,align:"center"},
	           {name:'mobilephone',index:'mobilephone', width:130,align:"center"}, 
	           {name:'fun',index:'fun', width:80,fixed:true,resizable:false,align:"center",frozen:true,formatter:"funFormatter"}
	];
	
	var searchTxt = $("#unitid").val();
	size = $(window).height()-120;
	var postData={searchTxt:searchTxt};
	
	var _option = {
			gridObject:"agreementDataGrid",
			url:url,
			datatype:"json",
			colNames:colNames,
			colModel:colModel,
			postData:postData,
			pageer:pageer,
			page:page,
			title:title,
			size:size,
			autowidth:true
	};
	
	//创建grid
	$.loadGridData(_option);
	jQuery("#"+_option.gridObject).jqGrid(
				'navGrid',_option.pageer,
				{
					edit:false,
					add:false,
					del:false,
					search:false,
					refreshstate:'current'
				}).navButtonAdd(_option.pageer,{  
					   caption:"",   
					   buttonicon:"ui-icon-plus",
					   onClickButton: function(){   
						   addAgreement();
					   },
					   title:"添加",
					   position:"last"
				}).navButtonAdd(_option.pageer,{  
					   caption:"",   
					   buttonicon:"ui-icon-trash",
					   onClickButton: function(){   
						  delAgreement();
					   },
					   title:"删除",
					   position:"last"
				});
		
		//租赁合同右键
		agreementMenu();
		$("#agreementDataGrid").setGridWidth($("#agreement_div").width() -10);
		$('#agreement_div').css('overflow', 'auto');
}
function loadMeterItemData() {
	var title = "抄表记录管理";
	var pageer = "#meterItemPager";
	var colNames;
	var colModel;
	var datatype = "json";
	var page = 50;
	var size;
	var url = "../meterItem/list.do";
	
	colNames = ['id','抄表日期','项目名称', '上期度数', '本次度数','抄表人'];
	colModel = [ 
			   {name:'id',index:'id',hidden:true, align:"center"},
	           {name:'createtime',index:'createtime',align:"center"},
	           {name:'watchcode',index:'watchcode', align:"center"},
	           {name:'lastnumber',index:'lastnumber',align:"center"},
	           {name:'newnumber',index:'newnumber', align:"center"},
	           {name:'meterman',index:'meterman', align:"center"}
	];
	
	var searchTxt = $("#unitid").val();
	size = $(window).height()-120;
	var postData={searchTxt:searchTxt};
	
	var _option = {
			gridObject:"meterItemDataGrid",
			url:url,
			datatype:"json",
			colNames:colNames,
			colModel:colModel,
			postData:postData,
			pageer:pageer,
			page:page,
			title:title,
			size:size,
			autowidth:true
	};
	//创建grid
	$.loadGridData(_option);
	$("#meterItemDataGrid").setGridWidth($("#meterItem_div").width() -10);
	$('#meterItem_div').css('overflow', 'auto');
	
}
function loadChargeItemData() {
	var title = "固定收费项目管理";
	var pageer = "#chargeItemPager";
	var colNames;
	var colModel;
	var datatype = "json";
	var page = 50;
	var size;
	var url = "../charge/list.do";
	
	colNames = ['id','名称','费用类型', '计算方式', '计算单位','收费方式','收费单价','收费周期','排序','备注','操作'];
	colModel = [ 
			   {name:'id',index:'id',hidden:true, width:110,align:"center"},
	           {name:'itemcode',index:'itemcode', width:110,align:"center"},
	           {name:'itemtype',index:'itemtype', width:110,align:"center",formatter:"itemcontentFormatter"},
	           {name:'itemmode',index:'itemmode', width:110,align:"center",formatter:"itemcontentFormatter"},
	           {name:'itemunit',index:'itemunit', width:110,align:"center",formatter:"itemcontentFormatter"},
	           {name:'chargecatagory',index:'chargecatagory', width:110,align:"center",formatter:"itemcontentFormatter"},
	           {name:'chargeprice',index:'chargeprice', width:110,align:"center",formatter:"itemcontentFormatter"},
	           {name:'chargeperiod',index:'chargeperiod', width:110,align:"center",formatter:"itemcontentFormatter"},
	           {name:'itemsort',index:'itemsort', width:110,align:"center",formatter:"itemcontentFormatter"},
	           {name:'chargeremark',index:'chargeremark', width:110,align:"center"},
	           {name:'meterman',index:'meterman', width:120,align:"center",formatter:"chargeitemFunFormatter"}
	];
	
	var unitid = $("#unitid").val();
	size = $(window).height()-120;
	var postData={unitid:unitid,iswatch:0};
	
	var _option = {
			gridObject:"chargeItemDataGrid",
			url:url,
			datatype:"json",
			colNames:colNames,
			colModel:colModel,
			postData:postData,
			pageer:pageer,
			page:page,
			title:title,
			size:size,
			autowidth:true
	};
	//创建grid
	$.loadGridData(_option);
	jQuery("#"+_option.gridObject).jqGrid(
				'navGrid',_option.pageer,
				{
					edit:false,
					add:false,
					del:false,
					search:false,
					refreshstate:'current'
				}).navButtonAdd(_option.pageer,{  
					   caption:"",   
					   buttonicon:"ui-icon-plus",
					   onClickButton: function(){   
						   addChargeitem();
					   },
					   title:"添加",
					   position:"last"
				}).navButtonAdd(_option.pageer,{  
					   caption:"",   
					   buttonicon:"ui-icon-trash",
					   onClickButton: function(){   
						  delChargeitem();
					   },
					   title:"删除",
					   position:"last"
				});
		
	//固定收费项目右键
	chargeitemMenu();
	$("#chargeItemDataGrid").setGridWidth($("#chargeItem_div").width() -10);
	$('#chargeItem_div').css('overflow', 'auto');
	
}
//抄表收费项目管理
function loadMeterchargeItemData() {
	var title = "抄表收费项目管理";
	var pageer = "#meterchargeItemPager";
	var colNames;
	var colModel;
	var datatype = "json";
	var page = 50;
	var size;
	var url = "../charge/list.do";
	
	colNames = ['id','收费项名称','按表计费类型', '单价', '收费备注','操作'];
	colModel = [ 
			   {name:'id',index:'id',hidden:true,align:"center"},
	           {name:'itemcode',index:'itemcode',align:"center"},
	           {name:'watchtype',index:'watchtype',align:"center",formatter:"watchtypeFormatter"},
	           {name:'watch_price',index:'watch_price',align:"center",formatter:"itemcontentFormatter"},
	           {name:'chargeremark',index:'chargeremark',align:"center"},
	           {name:'meterman',index:'meterman',align:"center",formatter:"meterchargeitemFunFormatter"}
	];
	
	var unitid = $("#unitid").val();
	size = $(window).height()-120;
	var postData={unitid:unitid,iswatch:1};
	
	var _option = {
			gridObject:"meterchargeItemDataGrid",
			url:url,
			datatype:"json",
			colNames:colNames,
			colModel:colModel,
			postData:postData,
			pageer:pageer,
			page:page,
			title:title,
			size:size,
			autowidth:true
	};
	//创建grid
	$.loadGridData(_option);
	jQuery("#"+_option.gridObject).jqGrid(
				'navGrid',_option.pageer,
				{
					edit:false,
					add:false,
					del:false,
					search:false,
					refreshstate:'current'
				}).navButtonAdd(_option.pageer,{  
					   caption:"",   
					   buttonicon:"ui-icon-plus",
					   onClickButton: function(){   
						   addMeterChargeitem();
					   },
					   title:"添加",
					   position:"last"
				}).navButtonAdd(_option.pageer,{  
					   caption:"",   
					   buttonicon:"ui-icon-trash",
					   onClickButton: function(){   
						  delChargeitem();
					   },
					   title:"删除",
					   position:"last"
				});
		
	//抄表收费项目右键
	meterchargeitemMenu();
	$("#meterchargeItemDataGrid").setGridWidth($("#meterchargeItem_div").width() -10);
	$('#meterchargeItem_div').css('overflow', 'auto');
	
}
function loadStandingbookData() {
	var title = "单元收费台帐";
	var pageer = "#standingbookPager";
	var colNames;
	var colModel;
	var datatype = "json";
	var page = 50;
	var size;
	var url = "../bookterm/listStandingbook.do";
	
	colNames = ['id','项目名称','计费时间', '收费时间', '上期度数','本次度数','单价（元）','金额（元）','状态','备注','总账期id','单元账期id','操作'];
	colModel = [ 
			   {name:'id',index:'id',hidden:true, width:100,align:"center"},
	           {name:'itemcode',index:'itemcode', width:100,align:"center"},
	           {name:'chargetime',index:'chargetime', width:100,align:"center"},
	           {name:'chargeovertime',index:'chargeovertime', width:100,align:"center"},
	           {name:'lastnumber',index:'lastnumber', width:100,align:"center"},
	           {name:'newnumber',index:'newnumber', width:100,align:"center"},
	           {name:'chargeprice',index:'chargeprice', width:100,align:"center"},
	           {name:'chargesum',index:'chargesum', width:100,align:"center"},
	           {name:'chargestatus',index:'chargestatus', width:100,align:"center", formatter:"chargeStatusFormatter"},
	           {name:'bookmemo',index:'bookmemo', width:100,align:"center"},
	           {name:'termid',index:'termid',hidden:true, width:100,align:"center", formatter:"initDataFormatter"},
	           {name:'unittermid',index:'unittermid',hidden:true, width:100,align:"center", formatter:"initDataFormatter"},
	           {name:'fun',index:'fun', width:100,align:"center",formatter:"standingbookFunFormatter"}
	];
	
	var searchTxt = $("#unitid").val();
	size = $(window).height()-120;
	var postData={unitid:searchTxt,unittermid:unittermid};
	
	var _option = {
			gridObject:"standingbookDataGrid",
			url:url,
			datatype:"json",
			colNames:colNames,
			colModel:colModel,
			postData:postData,
			pageer:pageer,
			page:page,
			title:title,
			size:size,
			autowidth:true
	};
	//创建grid
	$.loadGridData(_option);
	jQuery("#"+_option.gridObject).jqGrid(
				'navGrid',_option.pageer,
				{
					edit:false,
					add:false,
					del:false,
					search:false,
					refreshstate:'current'
				}).navButtonAdd(_option.pageer,{  
					   caption:"",   
					   buttonicon:"ui-icon-plus",
					   onClickButton: function(){   
						   addChargeitemforunit();
					   },
					   title:"添加",
					   position:"last"
				}).navButtonAdd(_option.pageer,{  
					   caption:"",   
					   buttonicon:"ui-icon-trash",
					   onClickButton: function(){   
						  delChargeitemforunit();
					   },
					   title:"删除",
					   position:"last"
				});
	//单元台帐右键
	standingbookMenu();
	$("#standingbookDataGrid").setGridWidth($("#standingbook_div").width() -150);
	$('#standingbook_div').css('overflow', 'auto');
	
}

function loadChargenoteData() {
	var title = "收款单";
	var pageer = "#chargenotePager";
	var colNames;
	var colModel;
	var datatype = "json";
	var page = 50;
	var size;
	var url = "../chargenote/list.do";
	
	colNames = ['id','单元台帐id','收款日期','收款单状态', '发票号', '经办人','操作'];
	colModel = [ 
			   {name:'id',index:'id',hidden:true, align:"center"},
			   {name:'unittermid',index:'unittermid',hidden:true, align:"center"},
	           {name:'chargedate',index:'chargedate', align:"center"},
	           {name:'chargestatus',index:'chargestatus',align:"center",formatter:"chargenotestatusFormatter"},
	           {name:'invoicenumber',index:'invoicenumber',align:"center"},
	           {name:'jbr',index:'jbr', align:"center"},
	           {name:'fun',index:'fun', align:"center",formatter:"chargenoteFunFormatter"}
	];
	
	var searchTxt = $("#unitid").val();
	size = $(window).height()-120;
	var postData={searchTxt:searchTxt};
	
	var _option = {
			gridObject:"chargenoteDataGrid",
			url:url,
			datatype:"json",
			colNames:colNames,
			colModel:colModel,
			postData:postData,
			pageer:pageer,
			page:page,
			title:title,
			size:size,
			autowidth:true
	};
	//创建grid
	$.loadGridData(_option);
	$("#chargenoteDataGrid").setGridWidth($("#chargenote_div").width() -10);
	$('#chargenote_div').css('overflow', 'auto');
	
}
//增加合同记录
function addAgreement(){
	var unitid = $("#unitid").val();
	var leaseid = $("#leaseid").val();
	var url = "../agreement/add.do?unitid="+unitid+"&leaseid="+leaseid+"&time="+Date.parse(new Date());
	var loadi = parent.layer.load(0);
	parent.$.layer({
        type: 2,
        title: '添加租赁合同',
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['850px' , '400px'],
        offset : ['150px', ''],
        iframe: {src: url},
        end: function(){
        	parent.layer.close(loadi);
        	reloadGrid();
        }
    });
}
//增加单元台帐收费项目
function addChargeitemforunit(){
	var projeuctid = $("#projeuctid").val();
	var unitid = $("#unitid").val();
	if(unittermid ==null || unittermid ==undefined || unittermid ==''){
		alert("请先选择相应的账期");
		return;
	}
	var url = "../bookterm/addChargeitemforunit.do?termid="+termid_ini+"&unittermid="+$("#unittermid").val()+"&unitid="+unitid+"&projeuctid="+projeuctid+"&time="+Date.parse(new Date());
	var loadi = parent.layer.load(0);
	parent.$.layer({
        type: 2,
        title: '添加单元台帐收费项目',
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['850px' , '400px'],
        offset : ['150px', ''],
        iframe: {src: url},
        end: function(){
        	parent.layer.close(loadi);
        	reloadGrid();
        }
    });
}
//修改收费项目
function updateChargeitem(id){
	var url = "../charge/editforunit.do?id="+id+"&time="+Date.parse(new Date());
	var loadi = parent.layer.load(0);
	parent.$.layer({
        type: 2,
        title: '修改收费项目',
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['850px' , '400px'],
        offset : ['150px', ''],
        iframe: {src: url},
        end: function(){
        	parent.layer.close(loadi);
        	reloadGrid();
        }
    });
}
//收款单详细信息显示 
function chargenoteDetail(id){
	var url = "../chargenote/chargenoteDetail.do?id="+id+"&time="+Date.parse(new Date());
	var loadi = parent.layer.load(0);
	parent.$.layer({
        type: 2,
        title: '收款单详细信息',
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['850px' , '400px'],
        offset : ['150px', ''],
        iframe: {src: url},
        end: function(){
        	parent.layer.close(loadi);
        	reloadGrid();
        }
    });
}
//修改单元台帐
function updateOneChargeitemforunit(id){
	var url = "../bookterm/editforunit.do?id="+id+"&time="+Date.parse(new Date());
	var loadi = parent.layer.load(0);
	parent.$.layer({
        type: 2,
        title: '修改单元台帐',
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['850px' , '400px'],
        offset : ['150px', ''],
        iframe: {src: url},
        end: function(){
        	parent.layer.close(loadi);
        	reloadGrid();
        }
    });
}
//增加固定收费项目记录
function addChargeitem(){
	var unitid = $("#unitid").val();
	var leaseid = $("#leaseid").val();
	var url = "../charge/addforunit.do?unitid="+unitid+"&iswatch=0&time="+Date.parse(new Date());
	var loadi = parent.layer.load(0);
	parent.$.layer({
        type: 2,
        title: '添加固定收费项目',
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['850px' , '400px'],
        offset : ['150px', ''],
        iframe: {src: url},
        end: function(){
        	parent.layer.close(loadi);
        	reloadGrid();
        }
    });
}
//增加抄表收费项目记录
function addMeterChargeitem(){
	var unitid = $("#unitid").val();
	var leaseid = $("#leaseid").val();
	var url = "../charge/addforunit.do?unitid="+unitid+"&iswatch=1&time="+Date.parse(new Date());
	var loadi = parent.layer.load(0);
	parent.$.layer({
        type: 2,
        title: '添加抄表收费项目',
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['850px' , '400px'],
        offset : ['150px', ''],
        iframe: {src: url},
        end: function(){
        	parent.layer.close(loadi);
        	reloadGrid();
        }
    });
}
//删除单元台帐
function delChargeitemforunit(){
	var gridObject;
	//设置滚动条
	setCroll('#standingbookDataGrid .ui-jqgrid-bdiv','jqgrid-div');
	gridObject = "standingbookDataGrid";
	
	var str = "";
	
	var rownumbers = "";
	rownumbers = $("#"+gridObject).jqGrid('getGridParam','selarrrow');
	str = getSelectid(gridObject,rownumbers);
	
	if (str == "") {
		alert("请先选择要删除的数据。");
		return;
	}
	console.log($("#contextPath"));
	if (confirm("确定要删除选中的单元台帐记录吗？请谨慎操作。")) {
		var loadi = parent.layer.load(0);
		setTimeout(function () {
			$.ajax({
		        async : false,
		        url : '../bookterm/deleteunitbook.do',
		        type : 'post',
		        data: {
					ids:str.toString()
				},
		        dataType : 'text',
		        success : function(data) {
		        	parent.layer.close(loadi);
		        }
		    });
			reloadGrid();
		},200);  
	};
}
//删除一条单元台帐记录
function delOneChargeitemforunit(id){
	
	if (confirm("确定要删除选中的单元台帐记录吗？请谨慎操作。")) {
		var loadi = parent.layer.load(0);
		setTimeout(function () {
			$.ajax({
		        async : false,
		        url : '../bookterm/deleteunitbook.do',
		        type : 'post',
		        data: {
					ids:id.toString()
				},
		        dataType : 'text',
		        success : function(data) {
		        	parent.layer.close(loadi);
		        }
		    });
			reloadGrid();
		},200);  
	};
}
//删除合同记录
function delAgreement(){
	//得到选中行的id
	var gridObject;
	setCroll('#agreementDataGrid .ui-jqgrid-bdiv','jqgrid-div');
	gridObject = "agreementDataGrid";
	var str = "";
	var rownumbers = "";
	rownumbers = $("#"+gridObject).jqGrid('getGridParam','selarrrow');
	str = getSelectid(gridObject,rownumbers);
	//得到选中行的id
	
	if (str == "") {
		alert("请先选择要删除的数据。");
		return;
	}
	console.log($("#contextPath"));
	if (confirm("确定要删除选中的合同吗？请谨慎操作。")) {
		var loadi = parent.layer.load(0);
		setTimeout(function () {
			$.ajax({
		        async : false,
		        url : '../agreement/delete.do',
		        type : 'post',
		        data: {
					ids:str.toString()
				},
		        dataType : 'text',
		        success : function(data) {
		        	parent.layer.close(loadi);
		        }
		    });
			reloadGrid();
		},200);  
	};
}
