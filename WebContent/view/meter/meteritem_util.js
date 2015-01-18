/*
 * 角色功能的js类。主要解决，因为添加、编辑时弹出框，会仅在iframe里弹出。所以创建js文件，在主页面导入js，iframe里调用，传参
 * 如果不是iframe的弹出框，可以将调用弹出框写在iframe的父页面里，不用单独创建js。
 */

//创建meteritem.util命名空间
if(typeof meteritem == "undefined"){
 var meteritem = {};
}

meteritem.util = {};


//添加，弹出框
meteritem.util.add = function() {
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


//招标记录
meteritem.util.setMeteritem = function(id,projectid,meterstatus) {
	var url = "../meter/meteritem.do?meterid="+id + "&projectid=" + projectid + "&meterstatus=" + meterstatus + "&time="+Date.parse(new Date());
	
	$.layer({
        type: 2,
        title: '抄表记录',
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
	
