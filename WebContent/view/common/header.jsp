<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
<link type="text/css" href="${pageContext.request.contextPath}/css/template.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/util.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ba-resize.min.js"></script>

<script type="text/javascript">
function callback() {
}

function setcookie() {
	var jscroll = $('.body-wrapper').scrollTop();
	setCookie('jscroll',jscroll);
}
</script>

<title>${sysname }</title>
<!--[if IE 6]>
<script src="${pageContext.request.contextPath}/js/DD_belatedPNG.js"></script>
<script>
  DD_belatedPNG.fix('.login_bottom,.logo,.l_m_b, tubiao, tubiao1,');
</script>
<![endif]-->
</head>
<body>
