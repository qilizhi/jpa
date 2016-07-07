<%@page import="com.qlz.constant.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	session.setAttribute(Constants.SESSION_USERNAME, "qilizhi2");
%>
<html>
<title>
socketTest
</title>
<script src="http://cdn.bootcss.com/sockjs-client/1.1.1/sockjs.min.js"></script>
<script src="${ctx}/static/assets/global/plugins/jquery.min.js"
	type="text/javascript"></script>
<script>
	var websocket;
	if ('WebSocket' in window) {
		websocket = new WebSocket("ws://localhost:8080/demo/webSocketServer");
	} else if ('MozWebSocket' in window) {
		websocket = new MozWebSocket("ws://localhost:8080/demo/webSocketServer");
	} else {
		websocket = new SockJS(
				"http://localhost:8080/demo/sockjs/webSocketServer");
	}
	websocket.onopen = function(evnt) {
		console.log(evnt);
	};
	websocket.onmessage = function(evnt) {
		$("#msgcount").html("(<font color='red'>" + evnt.data + "</font>)")
	};
	websocket.onerror = function(evnt) {
		console.log(evnt);
	};
	websocket.onclose = function(evnt) {
		console.log(evnt);
	}
</script>
</head>
<body>
	<h2>Hello World!</h2>
	<span id="msgcount">0</span>
</body>
</html>
