<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>500错误页面</title>
<link href="${ctx}/static/css/error.min.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<div class="row">
		<div class="col-md-12 page-500">
			<div class=" number font-red">500</div>
			<div class=" details">
				<h3>Oops! Something went wrong.</h3>
				<p>
					We are fixing it! Please come back in a while. <br>
				</p>
				<!-- <p>
					<a href="index.html" class="btn red btn-outline"> Return home </a>
					<br>
				</p> -->
			</div>
		</div>
	</div>
</body>
</html>