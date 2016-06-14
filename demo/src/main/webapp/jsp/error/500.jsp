<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page isErrorPage="true" %>
<%-- <%@ page import="java.io.*,java.util.Date"%>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	response.setStatus(200);
	Throwable ex = null;
	if (exception != null)
		ex = exception;
	if (request.getAttribute("javax.servlet.error.exception") != null)
		ex = (Throwable) request.getAttribute("javax.servlet.error.exception");
	
	//记录日志
	Logger logger = LoggerFactory.getLogger("500.jsp");
	logger.error(ex.getMessage(), ex);
	
	Integer _status = (Integer)request.getAttribute("javax.servlet.error.status_code");
	String _msg = (String)request.getAttribute("javax.servlet.error.message");
%>
<c:set var="ctx" value="${pageContext.request.contextPath}" /> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>500错误页面</title>
</head>
<body>
<%-- statusCode = <%= _status %><br/>
errorMessage = <%= _msg %> --%>
500
</body>
</html>