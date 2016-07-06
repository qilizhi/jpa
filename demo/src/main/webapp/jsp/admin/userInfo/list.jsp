<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>普通用户列表</title>
</head>
<body>

	<div class="note note-success">
		<p>
			温馨提示<br />1.查询普通微信用户信息,管理平台微信用户信息。<br />2.查询导游用户请点击<a
				class="btn red btn-outline" href="${ctx}/admin/guideUserInfo"
				target="_blank">导游用户</a>
		</p>
	</div>

	<div class="row">
		<div class="col-md-12">
			<!-- BEGIN EXAMPLE TABLE PORTLET-->
			<div class="portlet light portlet-fit bordered">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs"></i><span class="caption-subject font-dark bold uppercase">普通微信用户列表</span>
					</div>
					<div class="actions">
					<a href="javascript:;"  class="btn btn-sm dark btn-outline" >解禁</a>
					<a href="javascript:;"  class="btn btn-sm red btn-outline" >禁止</a>	
					</div>
				</div>
				<div class="portlet-body">
				<form id="searchForm" action="${ctx}/admin/userInfo" method="get">
	<div class="row">
		<div class="col-md-4">
		<select name="pageSize" class="form-control input-sm input-xsmall input-inline">
		<option value="5" <c:if test="${pageSize == 5}">selected</c:if> >5</option>
		<option value="10" <c:if test="${pageSize == 10}">selected</c:if> >10</option>
		<option value="20" <c:if test="${pageSize == 20}">selected</c:if> >20</option>
		<option value="50" <c:if test="${pageSize == 50}">selected</c:if> >50</option>
		<option value="100" <c:if test="${pageSize == 100}">selected</c:if> >100</option>
		</select>
		</div>
		<input type="hidden" name="pageNo" value="1">
		<div class="col-md-4"></div>
		<div class="col-md-4" style="text-align: right;">
		<input type="text" class="form-filter input-sm" placeholder="昵称或者真实名称" name="searchStr" value="${searchStr}">		
		<button type="submit" class="btn btn-sm green btn-outline filter-submit margin-bottom">
		<i class="fa fa-search"></i> 查询</button>		
		</div>		
	</div>
	</form>				
					<div class="table-scrollable">
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
								    <th scope="col"><input type="checkbox" class="check-all"></th>
									<th scope="col">编号</th>
									<th scope="col">微信OpenID</th>
									<th scope="col">昵称</th>
									<th scope="col">真实姓名</th>
									<th scope="col">性别</th>
									<th scope="col">城市</th>
									<th scope="col">头像</th>
									<th scope="col">创建时间</th>
									<th scope="col">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="item">
									<tr>
									    <td><input type="checkbox" class="check-all-item" value="${item.id}"></td>
										<td>${item.userNo}</td>
										<td>${item.openId}</td>
										<td>${item.nickName}</td>
										<td>${item.realName}</td>
										<td>${sexMap[item.sex]}</td>
										<td>${item.city}</td>
										<td><img src="${item.headImgUrl}"
											title="${item.nickName}" height="50px" width="50px" /></td>
										<td><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td>										
											<a class="btn btn-sm yellow btn-outline" href="javascript:;"
											target="_blank">详情</a>
											<a class="btn btn-sm red btn-outline" href="javascript:;"
											target="_blank">禁止</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<!-- END EXAMPLE TABLE PORTLET-->

		</div>
	</div>
	<script>
		window.mlx = {
			ctx : "${ctx}"
		};
	</script>
	<tg:pagination searchFormId="searchForm" paginator="${paginator}"></tg:pagination>
<script type="text/javascript">
seajs.use("js/page/common",function(com){
	com.initCheck();
	com.initSort();
});
</script>
</body>
</html>