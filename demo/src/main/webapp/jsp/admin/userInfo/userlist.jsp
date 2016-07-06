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
<title>普通用户</title>
<script type="text/javascript">
window.mlx = {
		ctx : "${ctx}"
	};
</script>
<style type="text/css">
form img {
	width: 100px;
	height: 80px;
}
</style>
</head>
<body>
	<!-- 	<div id="toast-container" class="toast-top-center" aria-live="polite"
		role="alert">
		<div class="toast toast-success">
			<button c lass="toast-close-button" role="button">×</button>
			<div class="toast-title">Toastr Notifications</div>
			<div class="toast-message">Gnome &amp; Growl type non-blocking
				notifications</div>
		</div>
	</div>  -->
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
				<!-- 
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs"></i>
					</div>
					<div class="tools">
						<a href="javascript:;" class="reload" data-original-title=""
							title="刷新"> </a>
					</div>
				</div> -->

				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs"></i><span
							class="caption-subject font-dark bold uppercase">公众号列表</span>
					</div>
					<div class="actions">
						<!-- <a href="javascript:addShow()"
							class="btn btn-sm green btn-outline">解禁</a> 
							<a	href="javascript:;" class="btn btn-sm dark btn-outline">审核</a> 
							<a
							href="javascript:batDel();" class="btn btn-sm red btn-outline">禁止</a> -->
					</div>
				</div>

				<div class="portlet-body">

					<form id="searchForm"
						action="${ctx}/admin/userInfo/list" method="get">
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
									name="openId" value="${userInfo.openId}"> 
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
									<!-- <th scope="col"><input type="checkbox" class="check-all"></th> -->
									<th scope="col">编号</th>
									<th scope="col">微信OpenID</th>
									<th scope="col">昵称</th>
									<th scope="col">真实姓名</th>
									<th scope="col">性别</th>
									<th scope="col">城市</th>
									<th scope="col">头像</th>
								<!-- 	<th scope="col">创建时间</th> -->
									<th scope="col">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="item">
									<tr>
										<%-- <td><input type="checkbox" class="check-all-item" value="${item.id}"></td> --%>
										<td>${item.userNo}</td>
										<td>${item.openId}</td>
										<td>${item.nickName}</td>
										<td>${sexMap[item.sex]}</td>
										<td>${item.city}</td>
										<td><img src="${item.headImgUrl}"
											title="${item.nickName}" height="50px" width="50px" /></td>
										<td><fmt:formatDate value="${item.createTime}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<%--  --%>
										<td><a class="btn btn-sm yellow btn-outline"
											href="javascript:detailShow(${item.id});" target="_blank">详情</a>
											<!-- <a class="btn btn-sm blue btn-outline" href="javascript:;"
											target="_blank">编辑</a> --></td>
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


	<!-- //提交加载中  -->

	<div id="detailResponsive"
		class="modal fade draggable-modal ui-draggable" tabindex="-1"
		aria-hidden="true">
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

								<!-- <div class="alert alert-success display-hide">
									<button class="close" data-close="alert"></button>
									Your form validation is successful!
								</div> -->


								<input type="hidden" class="form-control" name="id" />

								<div class="form-group  margin-top-20">
									<label class="control-label col-md-3">openId: </label>
									<div class="col-md-3">
										<label class="control-label"><span id="openId">
										</span> </label>

									</div>

								</div>
								<div class="form-group  margin-top-20">

									<label class="control-label col-md-3">用户编号: </label>
									<div class="col-md-3">
										<label class="control-label"><span id="userNo">
										</span> </label>

									</div>
									<label class="control-label col-md-3">会员级别: </label>
									<div class="col-md-3">
										<label class="control-label"><span id="level">
										</span> </label>

									</div>


								</div>
								<div class="form-group  margin-top-20">
									<label class="control-label col-md-3">昵称: </label>
									<div class="col-md-3">
										<label class="control-label"><span id="nickName">
										</span> </label>

									</div>

									<label class="control-label col-md-3">真实姓名: </label>
									<div class="col-md-3">
										<label class="control-label"><span id="realName">
										</span> </label>

									</div>
								</div>
								<div class="form-group  margin-top-20">
									<label class="control-label col-md-3">性别: </label>
									<div class="col-md-3">
										<label class="control-label"><span id="sex"> </span> </label>

									</div>

									<label class="control-label col-md-3">国家: </label>
									<div class="col-md-3">
										<label class="control-label"><span id="country">
										</span> </label>

									</div>
								</div>
								<div class="form-group  margin-top-20">
									<label class="control-label col-md-3">省: </label>
									<div class="col-md-3">
										<label class="control-label"><span id="province">
										</span> </label>

									</div>

									<label class="control-label col-md-3">城市: </label>
									<div class="col-md-3">
										<label class="control-label"><span id="city"> </span>
										</label>

									</div>
								</div>
								<div class="form-group  margin-top-20">
									<label class="control-label col-md-3">头像url: </label>
									<div class="col-md-3">
										<label class="control-label">
										
										<a id="headImgUrl" href=""><img	src="" alt="点击图片查看大图" /></a>
									
										</label>

									</div>

									<label class="control-label col-md-3">创建时间: </label>
									<div class="col-md-3">
										<label class="control-label"><span id="createDate">
										</span> </label>

									</div>
								</div>
								<div class="form-group  margin-top-20">
									<label class="control-label col-md-3">更新时间: </label>
									<div class="col-md-3">
										<label class="control-label"><span id="updateDate">
										</span> </label>

									</div>

									<label class="control-label col-md-3">语种: </label>
									<div class="col-md-3">
										<label class="control-label"><span id="language">
										</span> </label>

									</div>
								</div>
								<div class="form-group  margin-top-20">
									<label class="control-label col-md-3">用户特权信息: </label>
									<div class="col-md-3">
										<label class="control-label"><span id="privilege">
										</span> </label>

									</div>

									<label class="control-label col-md-3">微信用户维一Id: </label>
									<div class="col-md-3">
										<label class="control-label"><span id="unionid">
										</span> </label>

									</div>
								</div>


							</div>
							<!-- END FORM-->
						</div>
						<!-- END VALIDATION STATES-->
					</div>
					<div class="modal-footer">
						<%-- <div style="float: left; line-height: 30px;" id="ajax">
							<img
								src="${ctx }/static/assets/global/img/loading-spinner-grey.gif"
								alt="" class="loading"> <span id="message">
								&nbsp;&nbsp;请稍等... </span>
						</div> --%>
						<button type="button" data-dismiss="modal"
							class="btn dark btn-outline">关闭</button>
						<!-- 	<button type="submit" class="btn green">提交</button> -->
					</div>
				</form>
			</div>
		</div>
	</div>

	<script>
		var getUrl = mlx.ctx + "/admin/userInfo";
		function detailShow(id) {
			$("#detailResponsive").modal('show');
			var obj = get(id);
			//将对象属性 填充表单
			for ( var name in obj) {
				if (name == "headImgUrl") {
					$("#headImgUrl").attr("href",obj[name]);
					$("#headImgUrl img").attr("src",obj[name]);
				}
				if (name == "sex") {
					if (obj[name] == 1) {
						$("#modalForm span[id=" + name + "]").html("男");
					} else if (obj[name] == 2) {
						$("#modalForm span[id=" + name + "]").html("女");
					}
					continue;
				}
				$("#modalForm span[id=" + name + "]").html(obj[name]);
			}
		}
		/* 隐藏 */
		function modalHide() {
			$("#responsive").modal('hide');
			$("div.modal-backdrop").remove();
			//重设表单 
			$("#modalForm").each(function() {
				this.reset();
			});
		}
		//获取对信息
		function get(id) {
			var url = getUrl + "/" + id;
			var data;
			$.ajax({
				url : url,
				type : 'get',
				async : false,
				timeout : 400,
				success : function(result) {
					if (result.code == "200") {
						data = result.result;
					} else {
						return false;
					}
				},
				error : function(e) {
					$("#message").html("请求出错！");
				}
			});

			return data;
		}
	</script>
	<tg:pagination searchFormId="searchForm" paginator="${paginator}"></tg:pagination>

</body>
</html>