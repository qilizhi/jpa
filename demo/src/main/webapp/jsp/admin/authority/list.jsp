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
<title>授权管理</title>
<script type="text/javascript">
	window.mlx = {
		ctx : "${ctx}"
	};
</script>

<link
	href="${ctx}/static/assets/global/plugins/jstree/dist/themes/default/style.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${ctx}/static/assets/global/plugins/jquery-multi-select/css/multi-select.css"
	rel="stylesheet" type="text/css" />
<%-- <link
	href="${ctx}/static/assets/global/plugins/jquery-multi-select/css/application.css.css"
	rel="stylesheet" type="text/css" /> --%>
</head>
<body>

	<div class="row">
		<div class="col-md-3">
			<div class="portlet yellow-lemon box">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs"></i>权限树
					</div>
					<div class="tools">
						<a class="collapse"> </a> 
						<a 	title="新增" id="add"
							style="font-size: 24px; color: #fff; margin-top: -39px;cursor: pointer;">+</a>
						<!--  <label
							class="btn red btn-outline btn-circle btn-sm active"> New
						</label> -->
						<!--   <a href="#portlet-config" data-toggle="modal" class="config"> </a>
                                        <a href="javascript:;" class="remove"> </a> -->
					</div>
				</div>

				<div class="portlet-body">
					<div id="tree_3" class="tree-demo"></div>

				</div>
			</div>
		</div>
		<div class="col-md-9">

			<div class="col-md-12">
				<!-- BEGIN EXAMPLE TABLE PORTLET   portlet box purple -->
				<div class="portlet light portlet-fit bordered">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-cogs"></i><span
								class="caption-subject font-dark bold uppercase">资源列表</span>
						</div>
						<div class="actions">
							<a class="btn btn-sm green btn-outline addShow">编辑权限</a>
							<!-- 	<a	href="javascript:;" class="btn btn-sm dark btn-outline">审核</a>  -->
							<!-- <a class="btn btn-sm red btn-outline batDel">批量删除</a> -->
						</div>
					</div>
					<!-- right -->
					<div class="portlet-body" id="mainTable"></div>
				</div>
			</div>
		</div>
		<!-- /.modal 权限选 择 -->
		<div id="responsive" class="modal fade draggable-modal ui-draggable"
			tabindex="-1" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<form id="modalForm" class="form-horizontal">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true"></button>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="portlet">
									<div class="portlet-title">
										<div class="caption">
											<i class="icon-social-dribbble font-green"></i> <span
												class="caption-subject font-green bold uppercase">权限选择</span>
										</div>
										<div class="actions">
											<!-- 	<a class="reload"
												href="javascript:;">
											</a>
											 -->

											<div class="fa-item">
												<i class="fa fa-indent"></i>
											</div>
											<div class="fa-item">
												<i class="fa fa-outdent"></i>
											</div>
											<!-- <div class="fa-item">
												<i class="fa fa-refresh"></i>
											</div> -->
											<!--  <a class="btn btn-circle btn-icon-only btn-default"
												href="javascript:;"> <i class="icon-wrench"></i>
											</a> <a class="btn btn-circle btn-icon-only btn-default"
												href="javascript:;"> <i class="icon-trash"></i>
											</a> -->
										</div>
									</div>
									<div class="portlet-body">
										<!-- BEGIN FORM-->
									
										
												
												<div class="col-md-12">
													<select multiple="multiple" class="multi-select"
														id="my_multi_select1" name="my_multi_select1[]">
													</select>
										</div>

										<!-- END FORM-->
									</div>
								</div>
								<!-- END PORTLET-->
							</div>
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
	<!-- /.modal -->
	<div id="addAuthorityResponsive" class="modal fade draggable-modal ui-draggable"
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
								<input type="hidden" class="form-control" name="id" />
								<div class="form-group  margin-top-20">
									<label class="control-label col-md-3">权限根名称 <span
										class="required"> * </span>
									</label>
									<div class="col-md-8">
										<div class="input-icon right">
											<i class="fa"></i> <input type="text" class="form-control"
												name="name"  />
										</div>
									</div>
								</div>
								<!-- <div class="form-group">
									<label class="control-label col-md-3">资源路径 <span
										class="required"> * </span>
									</label>
									<div class="col-md-8">
										<div class="input-icon right">
											<i class="fa"></i> <input type="text" class="form-control"
												name="path" placeholder="例：/admin/authority/create" />  
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">资源描述 <span
										class="required"> * </span>
									</label>
									<div class="col-md-8">
										<div class="input-icon right">
											<i class="fa"></i> <input type="text" class="form-control"
												name="description" />
										</div>
									</div>
								</div> -->
															

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

		<script
			src="${ctx }/static/assets/global/plugins/jquery-multi-select/js/jquery.quicksearch.js"
			type="text/javascript"></script>
		<script
			src="${ctx }/static/assets/global/plugins/jquery-multi-select/js/jquery.multi-select.js"
			type="text/javascript"></script>
		<script
			src="${ctx }/static/assets/global/plugins/jstree/dist/jstree.min.js"
			type="text/javascript"></script>
		<script src="${ctx }/static/js/authority.js" type="text/javascript"></script>
		<script type="text/javascript">
		var authorityId='${authorityId}';
		var pageNo='${pageNo}'
		var pageSize='${pageSize}';
		if(authorityId!=null&&authorityId!=undefined){
			UITree.reloadTable(authorityId,pageNo,pageSize);
		}
		</script>
</body>
</html>