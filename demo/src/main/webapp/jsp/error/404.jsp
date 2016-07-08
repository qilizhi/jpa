<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>404错误页面</title>
<link href="${ctx}/static/css/error.min.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="row">
		<div class="col-md-12 page-404">
			<div class="number font-green">404</div>
			<div class="details">
				<h3>Oops! You're lost.</h3>
				<p>
					We can not find the page you're looking for. <br> <a
						href="#"> Return home </a> or try the search bar below.
				</p>
			<!-- 	<form action="#">
					<div class="input-group input-medium">
						<input type="text" class="form-control" placeholder="keyword...">
						<span class="input-group-btn">
							<button type="submit" class="btn green">
								<i class="fa fa-search"></i>
							</button>
						</span>
					</div>
					/input-group
				</form> -->
				
			</div>
		</div>
	</div>
</body>
</html>