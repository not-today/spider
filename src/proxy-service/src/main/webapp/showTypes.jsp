<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% request.setAttribute("contextname", request.getContextPath());%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>所有省份所使用的代理类型</title>
<link rel="stylesheet" href="${contextname}/Pure.css">
</head>
<body>
	<table class="pure-table pure-table-horizontal">
		<tr><td>省份</td><td>代理类型</td><td>操作</td></tr>
		<c:forEach var="kv" items="${types}">
		<tr>
			<form action="${contextname}/EditProxyType" method="get">
				<input type="hidden" name="pvn" value="${kv.value.code}" />
				<td>${kv.value.name}</td>
				<td>
					<select name="type">
						<c:forEach var="kv1" items="${typename}">
							<option value="${kv1.key}" ${kv.value.proxyType==kv1.key?"selected":""}>${kv1.value}</option>
						</c:forEach>
					</select>
				</td>
				<td>
					<input type="submit" value="修改" class="pure-button">
				</td>
			</form>
		</tr>
		</c:forEach>
	</table>
	<form action="xxx" method="get" id="editForm">
	</form>
	<script type="text/javascript">
		function edit(pvn,type){
			alert(pvn+type)
			var form = document.getElementById("editForm");
			var pvnele = document.createElement("input");
			pvnele.setAttribute("name","pvn");
			pvnele.setAttribute("value",pvn);
			form.appendChild(pvnele);
			var typeele = document.createElement("input");
			typeele.setAttribute("name","type");
			typeele.setAttribute("value",type);
			form.appendChild(typeele);
			form.submit();
		}
	</script>
</body>
</html>