/*
 * 角色功能的js类。主要解决，因为添加、编辑时弹出框，会仅在iframe里弹出。所以创建js文件，在主页面导入js，iframe里调用，传参
 * 如果不是iframe的弹出框，可以将调用弹出框写在iframe的父页面里，不用单独创建js。
 */

//创建charge.util命名空间
if(typeof charge == "undefined"){
 var charge = {};
}

charge.util = {};


//添加，弹出框
charge.util.add = function() {
	var url = "../charge/add.do?time="+Date.parse(new Date());
	
	$.layer({
        type: 2,
        title: '添加收费项',
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['900px' , '500px'],
        offset : ['', ''],
        iframe: {src: url},
        end: function(){
        	//调用iframe子页面的刷新方法
        	tt.window.refresh();
        }
    });
	
	
}


//修改，弹出框
charge.util.edit = function(id) {
	if (id == "") {
		alert("请先选择要修改的数据。");
		return;
	}
	
	var url = "../charge/edit.do?id="+id + "&time="+Date.parse(new Date());
	
	$.layer({
        type: 2,
        title: '修改收费项',
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['900px' , '500px'],
        offset : ['', ''],
        iframe: {src: url},
        end: function(){
        	//调用iframe子页面的刷新方法
        	tt.window.refresh();
        }
    });
}
	
