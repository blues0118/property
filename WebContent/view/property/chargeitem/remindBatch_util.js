//定义job类
var Remind = function(){};
//职位的操作
Remind.prototype = {
	initData:function(data){
		var url = "../charge/listForRemind.do";
		console.log("requestData="+data);
		return httpPostReqeust(url, data);
	},
	deleteRemind:function(data){
		var url = "../charge/listForRemind.do";
		console.log("requestData="+data);
		return httpPostReqeust(url, data);
	}
};
/**
 * url 请求接口地址
 * data 请求接口参数
 * async ajax是否异步，默认同步
 * HeadMap 设置header
 */
function httpPostReqeust(url, data, _async, headerUserId){
//	console.log('参数输出:');
//	console.log(data);	//调试用
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