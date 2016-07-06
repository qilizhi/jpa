<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>导游用户</title>
<script>
	window.mlx = {
		ctx : "${ctx}"
	};
</script>
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
			<!-- BEGIN EXAMPLE TABLE PORTLET   portlet box purple -->
			<div class="portlet light portlet-fit bordered">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs"></i><span
							class="caption-subject font-dark bold uppercase">公众号列表</span>
					</div>
					<div class="actions">
						<a  class="btn btn-sm green btn-outline unforbidden">解禁</a> <a
							 class="btn btn-sm dark btn-outline audit">批量审核</a> <a
							 class="btn btn-sm red btn-outline forbidden">禁止</a>
					</div>
				</div>

				<div class="portlet-body">
					<form id="searchForm"
						action="
						<c:choose>				
						<c:when test="${userInfo.auditStatus==1}">							
								${ctx}/admin/userInfo/guideUserInfo?audit_status=1								
						</c:when>
						<c:otherwise>							
								${ctx}/admin/userInfo/guideUserInfo	
							 </c:otherwise> 				
					</c:choose> "
						method="get">


						<div class="row">
							<input type="hidden" name="pageNo" value="1">
							<div class="col-md-6">
								<select name="pageSize"
									class="form-control input-sm input-xsmall input-inline">
									<option value="5" <c:if test="${pageSize == 5}">selected</c:if>>5</option>
									<option value="10"
										<c:if test="${pageSize == 10}">selected</c:if>>10</option>
									<option value="20"
										<c:if test="${pageSize == 20}">selected</c:if>>20</option>
									<option value="50"
										<c:if test="${pageSize == 50}">selected</c:if>>50</option>
									<option value="100"
										<c:if test="${pageSize == 100}">selected</c:if>>100</option>
								</select>
							</div>
							<!-- 				<div class="col-md-4"></div> -->
							<div class="col-md-6" style="text-align: right;">
								<input type="text" class="form-filter input-sm" placeholder="昵称"
									name="nickName" value="${userInfo.nickName}"> <input
									type="text" class="form-filter input-sm" placeholder="penID"
									name="openId" value="${userInfo.openId}"> <input
									type="text" class="form-filter input-sm" placeholder="真实姓名"
									name="realName" value="${userInfo.realName}">
								<button type="submit"
									class="btn btn-sm green btn-outline filter-submit margin-bottom">
									<i class="fa fa-search"></i> 查询
								</button>

							</div>
						</div>
					</form>
					<div class="table-scrollable">
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th scope="col"><input type="checkbox" class="icheck"></th>
									<!-- <th scope="col">编号</th> -->
									<th scope="col">微信OpenID</th>
									<!-- <th scope="col">昵称</th> -->
									<th scope="col">真实姓名</th>
									<!-- 	<th scope="col">导游级别</th> -->
									<th scope="col">性别</th>
									<!-- 	<th scope="col">城市</th> -->
									<th scope="col">头像</th>
									<th scope="col">手机号码</th>
									<th scope="col">审核状态</th>
									<th scope="col">状态</th>
									<th scope="col">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="item">
									<tr>
										<td><input type="checkbox" class="icheck" id="${item.id}"></td>
										<td>${item.openId}</td>
										<td>${item.realName}</td>
										<td>${sexMap[item.sex]}</td>
										<td><img src="${item.headImgUrl}"
											title="${item.nickName}" height="50px" width="50px" /></td>
										<td>${item.mobile }</td>
										<td>${auditMap[item.auditStatus]}</td>
										<td>${userStatus[item.status]}</td>
										<td><a class="btn btn-sm yellow btn-outline"
											href="${ctx}/admin/userInfo/detail/${item.id}"
											target="_blank">详情</a> <a class="btn btn-sm blue btn-outline"
											href="${ctx}/admin/userInfo/edit/${item.id}" target="_blank">编辑</a>
											<a class="btn btn-sm red btn-outline auditShow"
											id="${item.id}">审核</a> <c:if test="${item.status==1}">
												<a class="btn btn-sm red btn-outline commForbidden"
													id="${item.id}">禁用</a>
											</c:if> <c:if test="${item.status==2}">
												<a class="btn btn-sm green btn-outline commUnForbidden"
													id="${item.id}">解禁</a>
											</c:if></td>
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
	<!-- 
	modal view -->

	<!-- /.modal -->
	<div id="responsive" class="modal fade draggable-modal ui-draggable"
		tabindex="-1" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form id="modalForm" class="form-horizontal">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true"></button>
					</div>
					<div class="modal-header">
						<div class="portlet-body">

							<!-- BEGIN FORM-->

							<div class="form-body">
								<div class="alert alert-danger display-hide">
									<button class="close" data-close="alert"></button>
									You have some form errors. Please check below.
								</div>
								<div class="form-group">
									<label class="control-label col-md-6">审核<span
										class="required"> * </span> <input type="radio" value="3"
										name="auditStatus" />不通过 <input type="radio" value="2"
										name="auditStatus" checked />通过
									</label>
								</div>
							</div>
							<!-- END FORM-->
						</div>
						<!-- END VALIDATION STATES-->
					</div>
					<div class="modal-footer">
						<div style="float: left; line-height: 30px;" id="ajax">
							<img
								src="${ctx }/static/assets/global/img/loading-spinner-grey.gif"
								alt="" class="loading"> <span id="message">
								&nbsp;&nbsp;请稍等... </span>
						</div>
						<button type="button" data-dismiss="modal"
							class="btn dark btn-outline">关闭</button>
						<button type="submit" class="btn green">提交</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${ctx}/static/js/guidelist.js"></script>
	<tg:pagination searchFormId="searchForm" paginator="${paginator}"></tg:pagination>

</body>
</html>