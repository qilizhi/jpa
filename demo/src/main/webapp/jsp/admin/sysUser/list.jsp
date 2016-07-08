<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%-- <%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%> --%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>普通用户列表</title>
<link
	href="${ctx}/static/assets/global/plugins/jstree/dist/themes/default/style.min.css"
	rel="stylesheet" type="text/css" />
</head>
<body>

	<div class="note note-success">
		<p>
			温馨提示<br />1.查询普通微信用户信息,管理平台微信用户信息。<br />2.查询导游用户请点击<a
				class="btn red btn-outline" href="${ctx}/admin/sysUser/guideUser/list"
				target="_blank">导游用户</a>
		</p>
	</div>

	<div class="row">
		<div class="col-md-12">
			<!-- BEGIN EXAMPLE TABLE PORTLET-->
			<div class="portlet light portlet-fit bordered">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs"></i><span
							class="caption-subject font-dark bold uppercase">平台用户列表</span>
					</div>
					<!-- 	<div class="actions">
					<a href="javascript:;"  class="btn btn-sm dark btn-outline" >解禁</a>
					<a href="javascript:;"  class="btn btn-sm red btn-outline" >禁止</a>	
					</div> -->
				</div>
				<div class="portlet-body">
					<form id="searchForm" action="${ctx}/admin/sysUser/list" method="get">
						<div class="row">
							<div class="col-md-4">
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
							<input type="hidden" name="pageNo" value="1">
							<div class="col-md-4"></div>
							<div class="col-md-4" style="text-align: right;">
								<input type="text" class="form-filter input-sm"
									placeholder="用户编号" name="userNo" value="${userInfo.userNo}">
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


									 <th scope="col">用户编号</th> 
									 <th scope="col">登录名</th> 
									 <th scope="col">真实姓名</th> 
									<!-- <th scope="col">性别</th> -->
									<th scope="col">手机号</th>
									<!-- <th scope="col">创建时间</th> -->
									<th scope="col">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="item">
									<tr>
														
										 <td>${item.userNo}</td> 
										<%--  <td>${guideInfoMap[item.userNo].realName}</td>  --%>
										  <td>${item.loginName}</td> 
										  <td>${item.name}</td> 
										<%-- <td>${sexMap[item.sex]}</td> --%>
										<td>${item.mobile}</td>
								<%-- 		<td><fmt:formatDate value="${item.createTime}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td> --%>
										<td>
										<%-- <a class="btn btn-sm yellow btn-outline" href="javascript:_SysUser.changePassword('${item.id}');" target="_blank">更改密码</a> --%> 
										<a class="btn btn-sm blue btn-outline"	href="javascript:_SysUser.authShow('${item.userNo}');"
											target="_blank">管理授权</a></td>
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
	<!-- /.modal -->
	<div id="responsive" class="modal fade draggable-modal ui-draggable"
		tabindex="-1" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form id="modalForm" class="form-horizontal">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true"></button>
						<h4 class="modal-title">角色树</h4>
					</div>
					<div class="modal-body">
						<div class="portlet-body">
							<!-- BEGIN FORM-->
							<!-- role树 tree -->
							<input type="hidden" id="User" value=""> 
							<div id="tree_2" class="tree-demo"></div>
							<!-- END FORM-->
						</div>
						<!-- END VALIDATION STATES-->
					</div>
					<div class="modal-footer">
						<button type="button" data-dismiss="modal"
							class="btn dark btn-outline">确定</button>
					
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<!-- /.modal -->
	<div id="pswResponsive" class="modal fade draggable-modal ui-draggable"
		tabindex="-1" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form id="modalForm" class="form-horizontal">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true"></button>
						<h4 class="modal-title">修改用户密码</h4>
					</div>
					<div class="modal-body">
						<div class="portlet-body">
							<!-- BEGIN FORM-->
						<div class="form-group form-md-line-input">
						<input type="hidden" name="id"/> 
									<label class="control-label col-md-3">userNo：
									</label>
									<div class="col-md-4">
									<!-- 	<div class="input-icon right">
											<i class="fa"></i> <span  
												name="userNo" > 122</span>
										</div> -->
										<label class="control-label"><span id="title" name="userNo"></span></label>
									</div>
								</div>
<!-- 
								<div class="form-group">
									<label class="control-label col-md-3"> 用户真名：
									</label>
									<div class="col-md-4">
										<div class="input-icon right">
											<i class="fa"></i> <span 
												name="realName" > fwh</span>
										</div>
									</div>
								</div> -->
								<div class="form-group  ">
									<label class="control-label col-md-3">输入新密码：<span
										class="required"> * </span>
									</label>
									<div class="col-md-4">
										<div class="input-icon control-label">
											<i class="fa"></i> <input type="password" name="password" id="password" />
										</div>
									</div>
								</div>
								<div class="form-group  ">
									<label class="control-label col-md-3">再次确认密码：<span
										class="required"> * </span>
									</label>
									<div class="col-md-4">
										<div class="input-icon control-label">
											<i class="fa"></i> <input type="password" name="confirm_password" />
										</div>
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

	<script>
		window.mlx = {
			ctx : "${ctx}"
		};
	</script>
	<script
		src="${ctx }/static/assets/global/plugins/jstree/dist/jstree.min.js"
		type="text/javascript"></script>
	<script type="text/javascript" src="${ctx}/static/js/sysUser.js"></script>
	<tg:pagination searchFormId="searchForm" paginator="${paginator}"></tg:pagination>

</body>
</html>