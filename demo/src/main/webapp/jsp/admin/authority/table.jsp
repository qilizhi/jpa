<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<form id="searchForm" action="${ctx}/admin/authority/list" method="get">
	<div class="row">
		<input type="hidden" name="pageNo" value="1">
		<!-- 与权限树选中的相对应 -->
		<input type="hidden" name="authorityId" id="authorityId"
			value="${authorityId}">
		<div class="col-md-4">

			<select id="pageSize" name="pageSize"
				class="form-control input-sm input-xsmall input-inline">
				<option value="5" <c:if test="${pageSize== 5}">selected</c:if>>5</option>
				<option value="10" <c:if test="${pageSize == 10}">selected</c:if>>10</option>
				<option value="20" <c:if test="${pageSize == 20}">selected</c:if>>20</option>
				<option value="50" <c:if test="${pageSize == 50}">selected</c:if>>50</option>
				<option value="100" <c:if test="${pageSize == 100}">selected</c:if>>100</option>
			</select>
		</div>
		<div class="col-md-4"></div>
		<div class="col-md-4" style="text-align: right;">
			 	<%-- <input type="text" class="form-filter input-sm"
									placeholder="资源名 " name="name" value="${actInfo.actName}">  --%>
			<!-- <button type="button" id="searchFormbotton"
				class="btn btn-sm green btn-outline filter-submit margin-bottom">
				<i class="fa fa-search"></i> 查询
			</button> -->
		</div>
	</div>
</form>
<div class="table-scrollable">
	<table class="table table-striped table-bordered table-hover">
		<thead>
			<tr>
				<!-- <th scope="col"><input type="checkbox" class="icheck" /></th> -->
				<th scope="col">资源名</th>
				<th scope="col">资源路径</th>
				<th scope="col">资源描述</th>
				<!-- <th scope="col">是否有效</th> -->
			</tr>
		</thead>
		<tbody>
			<!-- 异步加载表 -->
			<c:forEach items="${list}" var="item" varStatus="index">
				<tr role="row">
					<%-- <td><input type="checkbox" id="${item.id}" class="icheck" /></td> --%>
					<td>${item.name}</td>
					<td>${item.path}</td>
					<td>${item.description}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<tg:pagination searchFormId="searchForm" paginator="${paginator}"></tg:pagination>
