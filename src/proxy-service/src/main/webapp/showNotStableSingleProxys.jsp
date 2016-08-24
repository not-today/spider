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
<body>pvs
	<table class="pure-table pure-table-horizontal">
		<tr><td>省份</td><td>操作</td></tr>
		<c:forEach var="pvn" items="${pvs}">
		<tr>
			<td>${pvn.name}</td>
			<td><a href="${contextname }/ShowNotStableSiglePvnProxy?code=${pvn.code}">查看</a></td>
		</tr>
		</c:forEach>
	</table>
	<table class="pure-table pure-table-horizontal">
		<tr><th colspan="3">${title}</th></tr>
		<tr><td>id</td><td>ip</td><td>端口</td><td>状态</td><td>操作</td></tr>
		<c:forEach var="proxy" items="${notstableProxys}">
		<tr>
			<td>${proxy.uuid}</td>
			<td>${proxy.ip}</td>
			<td>${proxy.port}</td>
			<td>${proxy.status.index==0?"不可用":"可用"}</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>