<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/util.js"></script>
<base target="_self">

<script>
	function closepage() {
		//获取当前窗口索引
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}
	
	function update() {
		var id = '${staffcontent.id }';
		if (id == "") {
			alert("没有获取足够数据，请退出后，重新尝试，或与管理员联系。");
			return;
		}
		var staffcontent = {};
		staffcontent.id = id;
		// 工资
		staffcontent.wage = $("#wage").val();
		// 保险
		staffcontent.safe = $("#safe").val();
		// 总台账id
		staffcontent.termid = $("#termid").val();
		
	    $.ajax({
	        async : true,
	        url : "${pageContext.request.contextPath}/pay/updateStaffcontent.do",
	        type : 'post',
	        data:staffcontent,
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
	
</script>
<title>修改员工工资</title>
</head>
<body>
	<table width="400" cellspacing="0" cellpadding="8" align="center" style="margin-top:20px">
		<tbody>
			<tr>
                <td colspan="2" align="center">修改员工工资信息</td>
            </tr>
			<tr>
				<td>总台账账期:</td>
				<td>
					<select id="termid">
					    <option value=""></option>
						<c:forEach items="${termList}" var="item">
							<c:if test="${staffcontent.termid == item.id}">
							<option value="${item.id}" selected="selected">${item.termcode}</option>
							</c:if>
							<c:if test="${staffcontent.termid != item.id}">
							<option value="${item.id}">${item.termcode}</option>
							</c:if>
					    </c:forEach>
					</select>
                </td>
			</tr>
			<tr>
				<td>工资:</td>
				<td><input type="text" id="wage" name="wage" value="${staffcontent.wage }"></td>
			</tr>
			<tr>
				<td>保险:</td>
				<td><input type="text" id="safe" name="safe" value="${staffcontent.safe }"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="button" onclick="update()">保存</button>
					<button type="button" onclick="closepage()">关闭</button>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>