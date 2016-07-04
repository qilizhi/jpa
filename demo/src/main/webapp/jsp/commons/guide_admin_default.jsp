<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8" />
<title><decorator:title default="美丽玩家后台" /></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<link rel="shortcut icon"
	href="http://www.mlxing.com/static/images/favicon.ico" />
<%@ include file="header.jsp"%>

<decorator:head />
</head>
<!-- END HEAD -->

<body class="page-header-fixed page-sidebar-closed-hide-logo login">
	<decorator:getProperty property="body.id" writeEntireProperty="true" />
	<decorator:getProperty property="body.class" writeEntireProperty="true" />
	<!-- BEGIN CONTAINER -->
	<div class="wrapper">
		<!-- BEGIN HEADER -->
		<header class="page-header">
			<nav class="navbar mega-menu" role="navigation">
				<div class="container-fluid">
					<div class="clearfix navbar-fixed-top">
						<!-- Brand and toggle get grouped for better mobile display -->
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target=".navbar-responsive-collapse">
							<span class="sr-only">Toggle navigation</span> <span
								class="toggle-icon"> <span class="icon-bar"></span> <span
								class="icon-bar"></span> <span class="icon-bar"></span>
							</span>
						</button>
						<!-- End Toggle Button -->
						<!-- BEGIN LOGO -->
						<a id="index" class="page-logo" href="index.html"> <img
							src="${ctx}/static/img/logo.png" alt="Logo">
						</a>
						<!-- END LOGO -->
						<!-- BEGIN SEARCH -->
						<!-- <form class="search" action="extra_search.html" method="GET">
							<input type="name" class="form-control" name="query"
								placeholder="搜索..."> <a href="javascript:;"
								class="btn submit"> <i class="fa fa-search"></i>
							</a>
						</form> -->
						<!-- END SEARCH -->
						<!-- BEGIN TOPBAR ACTIONS -->
						<div class="topbar-actions">
							<!-- BEGIN GROUP NOTIFICATION -->
							<!-- <div class="btn-group-notification btn-group"
								id="header_notification_bar">
								<button type="button" class="btn btn-sm dropdown-toggle"
									data-toggle="dropdown" data-hover="dropdown"
									data-close-others="true">
									<i class="icon-bell"></i> <span class="badge">7</span>
								</button>
								<ul class="dropdown-menu-v2">
									<li class="external">
										<h3>
											<span class="bold">12 条</span> 消息
										</h3> <a href="#">显示全部</a>
									</li>
									<li>
										<ul class="dropdown-menu-list scroller"
											style="height: 250px; padding: 0;"
											data-handle-color="#637283">
											<li><a href="javascript:;"> <span class="details">
														<span class="label label-sm label-icon label-success">
															<i class="fa fa-plus"></i>
													</span> 新用户注册
												</span> <span class="time">现在</span>
											</a></li>
											<li><a href="javascript:;"> <span class="details">
														<span class="label label-sm label-icon label-danger">
															<i class="fa fa-bolt"></i>
													</span> 消息通知内容描述.
												</span> <span class="time">3 分钟</span>
											</a></li>
											<li><a href="javascript:;"> <span class="details">
														<span class="label label-sm label-icon label-warning">
															<i class="fa fa-bell-o"></i>
													</span> 消息通知内容描述.
												</span> <span class="time">10 分钟</span>
											</a></li>
											<li><a href="javascript:;"> <span class="details">
														<span class="label label-sm label-icon label-info">
															<i class="fa fa-bullhorn"></i>
													</span> 消息通知内容描述.
												</span> <span class="time">14 分钟</span>
											</a></li>
										</ul>
									</li>
								</ul>
							</div> -->
							<!-- END GROUP NOTIFICATION -->
							<!-- BEGIN GROUP INFORMATION -->
							<div class="btn-group-red btn-group">
								<button type="button" class="btn btn-sm dropdown-toggle"
									data-toggle="dropdown" data-hover="dropdown"
									data-close-others="true">
									<i class="fa fa-plus"></i>
								</button>
								<ul class="dropdown-menu-v2" role="menu">
									<li class="active"><a href="${ctx }/guideAdmin/guideOrder">线路订单</a></li>
									<li><a href="${ctx }/guideAdmin/strategy/list">攻略列表</a></li>
									<li class="divider"></li>
									<li><a href="${ctx}/guideAdmin/line/create">新线路</a></li>
									<li><a href="${ctx}/guideAdmin/strategy/create">写攻略</a></li>
								</ul>
							</div>
							<!-- END GROUP INFORMATION -->
							<!-- BEGIN USER PROFILE -->
							<div class="btn-group-img btn-group">
								<button type="button" class="btn btn-sm dropdown-toggle"
									data-toggle="dropdown" data-hover="dropdown"
									data-close-others="true">
									<span>欢迎您, <shiro:principal /></span> <img
										src="${ctx}/static/assets/layouts/layout5/img/avatar1.jpg"
										alt="">
								</button>
								<ul class="dropdown-menu-v2" role="menu">
									<li><a href="${ctx}/guideAdmin/tuan"> <i
											class="icon-user"></i> 出团列表<!--  <span class="badge badge-danger">1</span> -->
									</a></li>
									<li><a href="${ctx}/guideAdmin/aboutMe"> <i
											class="icon-calendar"></i> 我的资料
									</a></li>
									<li><a href="${ctx}/guideAdmin/myStory"> <i
											class="icon-rocket"></i> 我的故事 <!-- <span class="badge badge-success"> 7 </span> -->
									</a></li>
									<li class="divider"></li>

									<!-- <li><a href="page_user_lock_1.html"> <i
											class="icon-lock"></i> 我的收益
									</a></li> -->

									<li><a href="${ctx}/logout"> <i class="icon-key"></i>
											注销
									</a></li>
								</ul>
							</div>
							<!-- END USER PROFILE -->

						</div>
						<!-- END TOPBAR ACTIONS -->
					</div>
					<!-- BEGIN HEADER MENU -->
					<div
						class="nav-collapse collapse navbar-collapse navbar-responsive-collapse">
						<ul class="nav navbar-nav">
							<li class="dropdown dropdown-fw  active selected ${homeclass}"><a
								href="${ctx}/guideAdmin" class="text-uppercase"> <i
									class="icon-home"></i> 主页
							</a>
								<ul class="dropdown-menu dropdown-menu-fw">
									<li class="${home_indexclass}"><a href="${ctx}/guideAdmin">首页
									</a></li>
									<li class="${myStory_class}"><a
										href="${ctx}/guideAdmin/myStory">我的故事 </a></li>
									<li class="${aboutMe_class} "><a
										href="${ctx}/guideAdmin/aboutMe">基本资料 </a></li>
									<li class="${tuan_class}"><a href="${ctx}/guideAdmin/tuan">出团列表
									</a></li>
									<li class="${pre_class}"><a
										href="${ctx}/guideAdmin/preView">预览 </a></li>
								</ul></li>
							<li class="dropdown dropdown-fw ${guide_orderclass}"><a
								href="${ctx }/guideAdmin/guideOrder" class="text-uppercase">
									<i class="icon-basket"></i> 订单
							</a></li>

							<li class="dropdown dropdown-fw ${guide_lineclass}"><a
								href="${ctx }/guideAdmin/line/list" class="text-uppercase">
									<i class="icon-puzzle"></i> 线路
							</a></li>

							<li class="dropdown dropdown-fw ${guide_strategyclass}"><a
								href="${ctx }/guideAdmin/strategy/list" class="text-uppercase">
									<i class="icon-paper-plane"></i> 攻略
							</a></li>

							<li class="dropdown dropdown-fw ${guideService_localclass}"><a
								href="${ctx }/guideAdmin/guideService" class="text-uppercase">
									<i class="icon-pointer"></i> 导服
							</a></li>

							<li class="dropdown dropdown-fw ${guide_imgclass}"><a
								href="${ctx }/guideAdmin/imgStorage" class="text-uppercase">
									<i class="icon-wallet"></i> 图库
							</a></li>
							<%-- <li class="dropdown dropdown-fw ${form_wizardclass}"><a href="${ctx}/guideAdmin/linePrice/form"
								class="text-uppercase"> <i class="icon-wallet"></i> 线路
							</a></li> --%>

							<%-- <li class="dropdown more-dropdown"><a href="javascript:;"
								class="text-uppercase"> 更多 </a>
								<ul class="dropdown-menu">
									<li><a href="${ctx }/guideAdmin/actRedPacket/list">红包记录</a></li>
									<li><a href="${ctx }/guideAdmin/sysLoginLog/list">登录日志</a></li>
									<li><a href="${ctx }/guideAdmin/guideFans/list">我的粉丝</a></li> 
									<li><a href="${ctx }/guideAdmin/imgStorage">我的图库</a></li>	
								</ul></li> --%>
						</ul>
					</div>
					<!-- END HEADER MENU -->
				</div>
				<!--/container-->
			</nav>
		</header>
		<!-- END HEADER -->
		<div class="container-fluid">
			<div class="page-content">
				<!-- BEGIN BREADCRUMBS -->
				<!-- END BREADCRUMBS -->
				<!-- BEGIN PAGE BASE CONTENT -->
				<decorator:body />
				<!-- END PAGE BASE CONTENT -->
			</div>
			<!-- BEGIN FOOTER -->
			<%@ include file="footer.jsp"%>
			<!-- END FOOTER -->
		</div>
	</div>
	<!-- END CONTAINER -->

	<div class="modal fade" id="basic-modals" tabindex="-1" role="basic"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">提示</h4>
				</div>
				<div class="modal-body">是否继续进行此操作?</div>
				<div class="modal-footer">
					<button type="button" class="btn dark btn-outline"
						data-dismiss="modal">关闭</button>
					<button type="button" class="btn green">继续</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>

	<!-- BEGIN THEME GLOBAL SCRIPTS -->
	<!-- BEGIN THEME GLOBAL SCRIPTS -->
	<script src="${ctx}/static/assets/global/scripts/app.min.js"
		type="text/javascript"></script>
	<!-- END THEME GLOBAL SCRIPTS -->
	<!-- BEGIN THEME LAYOUT SCRIPTS -->
	<script
		src="${ctx}/static/assets/layouts/layout5/scripts/layout.min.js"
		type="text/javascript"></script>
	<!-- END THEME LAYOUT SCRIPTS -->
	<%-- <script src="${ctx}/static/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script> --%>
	<script
		src="${ctx}/static/assets/global/plugins/bootbox/bootbox.min.js"
		type="text/javascript"></script>
	<script
		src="${ctx}/static/assets/global/plugins/bootstrap-toastr/toastr.min.js"
		type="text/javascript"></script>
	<%-- <script src="${ctx}/static/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script> --%>
	<script
		src="${ctx}/static/assets/global/plugins/bootstrap-toastr/toastr.min.js"
		type="text/javascript"></script>
	<script type="text/javascript" src="${ctx}/static/js/page/common.js"></script>

</body>

</html>