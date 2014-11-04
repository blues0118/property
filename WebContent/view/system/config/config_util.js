/*
 * 系统配置功能的js类。主要解决，因为编辑时弹出框，会仅在iframe里弹出。所以创建js文件，在主页面导入js，iframe里调用，传参
 */

//创建config.util命名空间
if(typeof config == "undefined"){
 var config = {};
}

config.util = {};

//系统配置的修改，弹出框
config.util.edit = function(id) {
	var url = "../config/edit.do?id="+id + "&time="+Date.parse(new Date());
	
	$.layer({
        type: 2,
        title: '详细信息',
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
