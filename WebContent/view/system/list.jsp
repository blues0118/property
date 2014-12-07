<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/view/common/header.jsp"%>
<%@ include file="/view/common/top_menu.jsp"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table_main.css" type="text/css">

<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/js/jqgrid/jquery-ui/redmond/jquery-ui-1.8.2.custom.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ba-resize.min.js"></script>

<!-- 弹出框插件 -->
<script src="${pageContext.request.contextPath}/js/layer/layer.min.js"></script>

<!-- 系统配置，功能弹出框 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/view/system/config/config_util.js"></script>
<!-- 帐户，功能弹出框 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/view/auth/account/account_util.js"></script>
<!-- 角色，功能弹出框 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/view/auth/role/role_util.js"></script>
<!-- 收费项目，功能弹出框 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/view/system/charge/charge_util.js"></script>
<!-- 代码管理，功能弹出框 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/view/system/code/code_util.js"></script>


<script type="text/javascript">

	$(function(){
		
	});
	
	function callback() {
		
	}
	
	function onClickItem(page,item) {
		$("#fra").attr("src",page);
		
		$("#item_ul a").removeClass().addClass("txt2");
		
		$("#"+item).addClass("on");
	}
	
//-->
</script>

<!--内容部分开始-->

<div id="bodyer">
	<div id="bodyer_left">
		<dl>
			<dt>
				<a href="#" class="blue"><img
					src="${pageContext.request.contextPath}/images/i1_03.png"
					width="29" height="22" class="tubiao" /><span>系统维护</span></a>
			</dt>
			<dd>
				<ul id="item_ul">
					<li><a id="config_a" href="javascript:;" onclick="onClickItem('${pageContext.request.contextPath}/config/index.do','config_a')" class="txt2 on"><img
							src="${pageContext.request.contextPath}/images/i_07.png"
							width="18" height="15" class="tubiao1" /><span>参数设置</span></a></li>
					<li><a id="account_a" href="javascript:;" onclick="onClickItem('${pageContext.request.contextPath}/account/index.do','account_a')" class="txt2"><img
							src="${pageContext.request.contextPath}/images/i_12.png"
							width="18" height="13" class="tubiao1" /><span>帐户管理</span></a></li>
					<li><a id="role_a" href="javascript:;" onclick="onClickItem('${pageContext.request.contextPath}/role/index.do','role_a')" class="txt2"><img
							src="${pageContext.request.contextPath}/images/i_10.png"
							width="18" height="13" class="tubiao1" /><span>角色管理</span></a></li>
					<li><a id="project_a" href="javascript:;" onclick="onClickItem('${pageContext.request.contextPath}/project/index.do','project_a')" class="txt2"><img
							src="${pageContext.request.contextPath}/images/i_14.png"
							width="18" height="13" class="tubiao1" /><span>物业项目</span></a></li>
					<li><a id="charge_a" href="javascript:;" onclick="onClickItem('${pageContext.request.contextPath}/charge/index.do','charge_a')" class="txt2"><img
							src="${pageContext.request.contextPath}/images/i_14.png"
							width="18" height="13" class="tubiao1" /><span>收费管理</span></a></li>
					<li><a href="javascript:;" onclick="onClickItem('${pageContext.request.contextPath}/code/index.do','code_a')" class="txt2"><img
							src="${pageContext.request.contextPath}/images/i_14.png"
							width="18" height="13" class="tubiao1" /><span>代码管理</span></a></li>
				</ul>
			</dd>
		</dl>
	</div>
	<div id="bodyer_right">
		<Iframe id="fra" name="tt" src="${pageContext.request.contextPath}/config/index.do" width="100%" height="100%" scrolling="no" frameborder="0"></iframe>
	</div>
	<div style="clear: both"></div>
</div>
<!--内容部分结束-->

<%@ include file="/view/common/footer.jsp"%>