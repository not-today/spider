<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<% request.setAttribute("contextname", request.getContextPath());%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>首页</title>
<link rel="stylesheet" href="${contextname}/Pure.css">
</head>
<body>
	<a href="${contextname}/ShowProxyType" class="pure-button pure-button-active">省份代理类型管理</a><br>
	<a href="${contextname}/ShowCharset" class="pure-button pure-button-active">省份字符编码管理</a><br>
	<a href="${contextname}/ShowNotStableProxy" class="pure-button pure-button-active">不稳定代理管理</a><br>
	<a href="${contextname}/ShowStableProxy" class="pure-button pure-button-active">稳定代理管理</a><br>
</body>
</html>