/*
 * 帐户功能的js类。主要解决，因为添加、编辑时弹出框，会仅在iframe里弹出。所以创建js文件，在主页面导入js，iframe里调用，传参
 * 如果不是iframe的弹出框，可以将调用弹出框写在iframe的父页面里，不用单独创建js。
 */

//创建property.util命名空间
if(typeof property == "undefined"){
 var property = {};
}

property.util = {};


//添加，弹出框
property.util.add = function(url1) {
	var url = url1;
	$.layer({
        type: 2,
        title: '添加单元',
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['850px' , '400px'],
        offset : ['', ''],
        iframe: {src: url},
        end: function(){
        	tt.window.refresh();//调用iframe子页面的刷新方法
        }
    });
}
//添加，弹出框
property.util.addAgreement = function(url) {
	
	$.layer({
        type: 2,
        title: '添加租赁合同',
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['850px' , '400px'],
        offset : ['150px', ''],
        iframe: {src: url},
        end: function(){
        	//调用iframe子页面的刷新方法
        	//tt.window.refresh();
        	window.location.reload();
        }
    });
}
//添加，弹出框
property.util.addLease = function(url) {
	
	$.layer({
        type: 2,
        title: '添加租赁合同',
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['850px' , '400px'],
        offset : ['150px', ''],
        iframe: {src: url},
        end: function(){
        	//调用iframe子页面的刷新方法
        	tt.window.refresh();
        }
    });
}

//修改，弹出框
property.util.edit = function(id) {
	if (id == "") {
		alert("请先选择要修改的数据。");
		return;
	}
	
	if (id == "1") {
		alert("超级帐户不能修改。");
		return;
	}
	
	
	var url = "../property/edit.do?id="+id + "&time="+Date.parse(new Date());
	
	$.layer({
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
