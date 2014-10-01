// JavaScript 公共工具类


/**
 * 弹出模式页面。
 * @param url		
 * @param param
 * @param whparam		width和height   { width: 500, height: 500 }
 * @returns
 */
function openShowModalDialog(url,param,whparam){  
    
	 // 传递至子窗口的参数
	 var paramObj = param || { };
	 // 模态窗口高度和宽度
	 var whparamObj = whparam || { width: 500, height: 500 };   
	 // 居中位置
	 var top = (window.screen.availHeight - 20 - whparamObj.height) / 2;
	 var left = (window.screen.availWidth - 10 - whparamObj.width) / 2;    
	 // 参数  
	 var p = "help:no;status:no;center:yes;toolbars:0;location:no;resizable:no;scrollbars:0;";  
	     p += 'dialogWidth:'+(whparamObj.width)+'px;';  
	     p += 'dialogHeight:'+(whparamObj.height)+'px;';  
	     p += 'dialogLeft:' + left + 'px;';  
	     p += 'dialogTop:' + top + 'px;';        
	  return showModalDialog(url,paramObj,p);  
}

//获取滚动条位置，存入cookie
function jscroll(classname,name) {
	var jscroll = $('.'+classname).scrollTop();
	if (name == null) {
		setCookie('jscroll',jscroll);
	}
	else {
		setCookie(name,jscroll);
	}
}

//获取滚动条位置，存入cookie
function setCroll(object,name) {
	var jscroll = $(object).scrollTop();
	if (name == null) {
		setCookie('jscroll',jscroll);
	}
	else {
		setCookie(name,jscroll);
	}
}


//设置cookie
function setCookie(name,value){
    var Days = 30;
    var exp = new Date();
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}

//读取cookies
function getCookie(name){
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
        return (arr[2]);
    else
        return null;
}
//删除cookies
function delCookie(name){
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getCookie(name);
    if(cval!=null)
        document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}

function getRootPath_web() {
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht = curWwwPath.substring(0, pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return (localhostPaht + projectName);
}

function sessionOut(data) {
	if(data.indexOf("页面过期") > 0 ) {
		alert("您已经太长时间没有操作,请重新登录。");
		window.top.location.href=getRootPath_web() + "/login.do";
	}
	return false;
}

