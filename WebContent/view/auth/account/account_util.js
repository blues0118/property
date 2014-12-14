/*
 * 帐户功能的js类。主要解决，因为添加、编辑时弹出框，会仅在iframe里弹出。所以创建js文件，在主页面导入js，iframe里调用，传参
 * 如果不是iframe的弹出框，可以将调用弹出框写在iframe的父页面里，不用单独创建js。
 */

//创建account.util命名空间
if(typeof account == "undefined"){
 var account = {};
}

account.util = {};


//添加，弹出框
account.util.add = function() {
	var url = "../account/add.do?time="+Date.parse(new Date());
	
	$.layer({
        type: 2,
        title: '添加帐户',
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['600px' , '300px'],
        offset : ['150px', ''],
        iframe: {src: url},
        end: function(){
        	//调用iframe子页面的刷新方法
        	tt.window.refresh();
        }
    });
	
	
}


//修改，弹出框
account.util.edit = function(id) {
	if (id == "") {
		alert("请先选择要修改的数据。");
		return;
	}
	
	if (id == "1") {
		alert("超级帐户不能修改。");
		return;
	}
	
	
	var url = "../account/edit.do?id="+id + "&time="+Date.parse(new Date());
	
	$.layer({
        type: 2,
        title: '修改帐户信息',
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['600px' , '300px'],
        offset : ['150px', ''],
        iframe: {src: url},
        end: function(){
        	//调用iframe子页面的刷新方法
        	tt.window.refresh();
        }
    });
	
	
}
//修改密码，弹出框
account.util.update_password = function(id) {
	if (id == "") {
		alert("请先选择要修改密码的帐户。");
		return;
	}
	
	var url = "../account/updatepass.do?id="+id + "&time="+Date.parse(new Date());
	
	$.layer({
		type: 2,
		title: '修改帐户密码',
		maxmin: false,
		shadeClose: true, //开启点击遮罩关闭层
		area : ['600px' , '300px'],
		offset : ['150px', ''],
		iframe: {src: url}
		
	});
	
	
}
//设置帐户角色，弹出框
account.util.setrole = function(id) {
	if (id == "1") {
		alert("超级帐户不能修改角色。");
		return;
	}
	
	var url = "../account/setrole.do?id="+id + "&time="+Date.parse(new Date());
	
	$.layer({
		type: 2,
		title: '设置帐户角色',
		maxmin: false,
		shadeClose: true, //开启点击遮罩关闭层
		area : ['800px' , '500px'],
		offset : ['50px', ''],
		iframe: {src: url},
		end: function(){
        	//调用iframe子页面的刷新方法
        	tt.window.refresh();
        }
	});
	
	
}
//设置账户操作权限，弹出框
account.util.setAuthority = function(id) {
    if (id == "") {
        alert("请先选择要设置的帐户。");
        return;
    }
    
    var url = "../account/setAuthority.do?id="+id + "&time="+Date.parse(new Date());
    
    $.layer({
        type: 2,
        title: '设置物业项目访问权限',
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['600px' , '300px'],
        offset : ['150px', ''],
        iframe: {src: url}
        
    });
    
    
}
