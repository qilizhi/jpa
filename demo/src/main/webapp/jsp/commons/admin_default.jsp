<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"	prefix="decorator"%>
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
<title><decorator:title default="约车后台" /></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<script type="text/javascript">
	window.mlx = {
		ctx : "${ctx}"
	};
</script>
<%@ include file="header.jsp"%>
<decorator:head />
</head>
<!-- END HEAD -->

<body class="page-header-fixed page-sidebar-closed-hide-logo">
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
						<form class="search" action="extra_search.html" method="GET">
							<input type="name" class="form-control" name="query"
								placeholder="搜索..."> <a href="javascript:;"
								class="btn submit"> <i class="fa fa-search"></i>
							</a>
						</form>
						<!-- END SEARCH -->
						<!-- BEGIN TOPBAR ACTIONS -->
						<div class="topbar-actions">
							<!-- BEGIN GROUP NOTIFICATION -->
							<div class="btn-group-notification btn-group"
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
											<li><a href="javascript:;"> <span class="details">
														<span class="label label-sm label-icon label-danger">
															<i class="fa fa-bolt"></i>
													</span> 消息通知内容描述 68%.
												</span> <span class="time">2 天</span>
											</a></li>
											<li><a href="javascript:;"> <span class="details">
														<span class="label label-sm label-icon label-danger">
															<i class="fa fa-bolt"></i>
													</span> 消息通知内容描述.
												</span> <span class="time">3 天</span>
											</a></li>
											<li><a href="javascript:;"> <span class="details">
														<span class="label label-sm label-icon label-warning">
															<i class="fa fa-bell-o"></i>
													</span> 消息通知内容描述.
												</span> <span class="time">4 天</span>
											</a></li>
											<li><a href="javascript:;"> <span class="details">
														<span class="label label-sm label-icon label-info">
															<i class="fa fa-bullhorn"></i>
													</span> 消息通知内容描述.
												</span> <span class="time">5 天</span>
											</a></li>
											<li><a href="javascript:;"> <span class="details">
														<span class="label label-sm label-icon label-danger">
															<i class="fa fa-bolt"></i>
													</span> 消息通知内容描述.
												</span> <span class="time">9 天</span>
											</a></li>
										</ul>
									</li>
								</ul>
							</div>
							<!-- END GROUP NOTIFICATION -->
							<!-- BEGIN GROUP INFORMATION -->
							<div class="btn-group-red btn-group">
								<button type="button" class="btn btn-sm dropdown-toggle"
									data-toggle="dropdown" data-hover="dropdown"
									data-close-others="true">
									<i class="fa fa-plus"></i>
								</button>
								<ul class="dropdown-menu-v2" role="menu">
									<li class="active"><a href="#">线路订单</a></li>
									<li><a href="#">用户反馈</a></li>
									<li><a href="#">攻略列表</a></li>
									<li class="divider"></li>
									<li><a href="#">最新攻略 <span class="badge badge-success">4</span>
									</a></li>
									<li><a href="#">最新线路 <span class="badge badge-danger">2</span>
									</a></li>
								</ul>
							</div>
							<!-- END GROUP INFORMATION -->
							<!-- BEGIN USER PROFILE -->
							<div class="btn-group-img btn-group">
								<button type="button" class="btn btn-sm dropdown-toggle"
									data-toggle="dropdown" data-hover="dropdown"
									data-close-others="true">
									<span>欢迎您,<shiro:principal />管理员
									</span> <img
										src="${ctx}/static/assets/layouts/layout5/img/avatar1.jpg"
										alt="">
								</button>
								<ul class="dropdown-menu-v2" role="menu">
									<li><a href="page_user_profile_1.html"> <i
											class="icon-user"></i> 我的设置 <span class="badge badge-danger">1</span>
									</a></li>
									<li><a href="app_calendar.html"> <i
											class="icon-calendar"></i> 我的资料
									</a></li>
									<li><a href="app_todo_2.html"> <i class="icon-rocket"></i>
											我的消息 <span class="badge badge-success"> 7 </span>
									</a></li>
									<li class="divider"></li>
									<!-- <li><a href="page_user_lock_1.html"> <i
											class="icon-lock"></i> 我的权限
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
							<li class="dropdown dropdown-fw ${homeclass}"><a
								href="javascript:;" id="menu-home" class="text-uppercase"> <i
									class="icon-home"></i> 主页
							</a>
								<ul class="dropdown-menu dropdown-menu-fw">
									<li class="${home_indexclass}"><a href="${ctx}/admin">
											<i class="icon-home"></i> 首页
									</a></li>
									<%-- <li class="${home_reviewclass}"><a
										href="${ctx}/admin/review"> <i class="icon-eye"></i> 预览
									</a></li> --%>
									<li class="${home_tuanclass}"><a
										href="${ctx}/admin/guideTuan"> <i class="icon-eye"></i> 出团列表
									</a></li>

								</ul></li>

							<li class="dropdown dropdown-fw ${productclass}"><a
								href="javascript:;" class="text-uppercase"> <i
									class="icon-basket"></i>产品
							</a>
								<ul class="dropdown-menu dropdown-menu-fw">
									<li class="${product_lineclass}"><a
										href="${ctx}/admin/guideLine"> <i class="icon-basket"></i>
											线路列表
									</a></li>
									<li class="${product_guideServiceclass}"><a
										href="${ctx}/admin/guideService"> <i class="icon-basket"></i>导服列表
									</a></li>
									<li class="${product_virtualGoodsclass}"><a
										href="${ctx}/admin/virtualGoods"> <i class="icon-basket"></i>
											虚拟产品
									</a></li>
								</ul></li>
							<li class="dropdown dropdown-fw ${contentclass}"><a
								href="javascript:;" class="text-uppercase"> <i
									class="icon-basket"></i>内容
							</a>
								<ul class="dropdown-menu dropdown-menu-fw">
									<li class="${content_strategyclass}"><a
										href="${ctx}/admin/guideStrategy"> <i class="icon-basket"></i>
											攻略列表
									</a></li>
									<li class="${content_imagesclass}"><a
										href="${ctx}/admin/imageInfo"> <i class="icon-basket"></i>
											图库管理
									</a></li>
									<li class="${content_advInfoclass}"><a
										href="${ctx}/admin/advInfo"> <i class="icon-basket"></i>
											广告管理
									</a></li>
									<li class="${content_groupclass}"><a
										href="${ctx}/admin/group"> <i class="icon-eye"></i> 万人群
									</a></li>
								</ul></li>

							<li class="dropdown dropdown-fw ${orderclass}"><a
								href="javascript:;" class="text-uppercase"> <i
									class="icon-basket"></i> 订单
							</a>
								<ul class="dropdown-menu dropdown-menu-fw">
									<li class="${order_lineclass}"><a
										href="${ctx}/admin/orderLineInfo"> <i class="icon-basket"></i>
											订单列表
									</a></li>
									<li class="${order_refundclass}"><a
										href="${ctx}/admin/orderRefund"> <i class="icon-basket"></i>
											退款列表
									</a></li>
								</ul></li>

							<li class="dropdown dropdown-fw  ${memberclass}"><a
								href="javascript:;" class="text-uppercase"> <i
									class="icon-puzzle"></i>会员
							</a>
								<ul class="dropdown-menu dropdown-menu-fw">
									<li class="${member_listclass}"><a
										href="${ctx}/admin/member/list"><i class="icon-diamond"></i>
											会员列表 </a></li>
								</ul></li>
							<li class="dropdown dropdown-fw  ${guideclass}"><a
								href="javascript:;" class="text-uppercase"> <i
									class="icon-puzzle"></i> 导游
							</a>
								<ul class="dropdown-menu dropdown-menu-fw">
									<li class="${guide_auditclass}"><a
										href="${ctx}/admin/guideUserInfo/list?isAuditStatus=true"><i
											class="icon-diamond"></i> 导游申请列表 </a></li>
									<li class="${guide_listclass}"><a
										href="${ctx}/admin/guideUserInfo/list"><i
											class="icon-diamond"></i> 导游列表 </a></li>
									<li class="${guide_intro_listclass}"><a
										href="${ctx}/admin/guideIntro/list"><i class="icon-diamond"></i>
											导游故事列表 </a></li>

								</ul></li>

							<li class="dropdown dropdown-fw  ${weixinclass}"><a
								href="javascript:;" class="text-uppercase"> <i
									class="icon-layers"></i>微信
							</a>
								<ul class="dropdown-menu dropdown-menu-fw">
									<li class="${weixin_publicmgrclass}"><a
										href="${ctx}/admin/wxPublic"> <i class="icon-basket"></i>
											公众号管理
									</a></li>
									<li class="${weixin_menumgrclass}"><a
										href="${ctx}/admin/wxMenu"> <i class="icon-basket"></i>
											微信菜单管理
									</a></li>
									<%-- <li class="dropdown more-dropdown-sub ${weixin_actInfo} ">
										<a href="javascript:;"> <i class="icon-docs"></i> 活动管理
									</a>
										<ul class="dropdown-menu">
											<li class="${weixin_redpacketmgrclass}"><a
												href="${ctx}/admin/actInfo"> <i class="icon-clock"></i>
													红包活动
											</a></li>
											<li class="${weixin_redpacketclass}"><a
												href="${ctx}/admin/actRedPacket"> <i class="icon-check"></i>
													红包记录
											</a></li>
											<li class="${weixin_activechartclass}"><a
												href="${ctx}/admin/actInfo/statistics"> <i
													class="icon-bar-chart"></i> 活动统计
											</a></li>
										</ul>
									</li>
									<li class="dropdown more-dropdown-sub ${weixin_userInfo}">
										<a href="javascript:;"> <i class="icon-user"></i> 微信用户
									</a>
										<ul class="dropdown-menu">
											<li class="${weixin_userclass}"><a
												href="${ctx}/admin/userInfo/generalUserInfo"><i
													class="icon-envelope"></i> 普通用户 </a></li>
											<li class="${weixin_guideuserclass}"><a
												href="${ctx}/admin/userInfo/guideUserInfo"><i
													class="icon-envelope"></i> 导游用户 </a></li>
											<li class="${weixin_userchartclass}"><a
												href="${ctx}/admin/userInfo/chart"><i
													class="icon-envelope"></i> 用户统计 </a></li>
										</ul>
									</li> --%>
								</ul></li>
							<li class="dropdown dropdown-fw ${systemclass}"><a
								href="javascript:;" class="text-uppercase"><i
									class="icon-settings"></i> 系统 </a>
								<ul class="dropdown-menu dropdown-menu-fw">
									<li class="dropdown more-dropdown-sub ${system_authority}">
										<a href="javascript:;"> <i class="icon-user"></i>权限管理
									</a>
										<ul class="dropdown-menu">
										<li class="${system_sysUserclass}"><a
												href="${ctx}/admin/sysUser/list"><i class="icon-docs"></i>用户管理
											</a></li>
										<li class="${system_roleclass}"><a
												href="${ctx}/admin/role/list"><i class="icon-docs"></i>角色管理
											</a></li>
											<li class="${system_perimclass}"><a
												href="${ctx}/admin/authority/list"><i class="icon-docs"></i>授权管理 </a></li>
											<li class="${system_resourceclass}"><a
												href="${ctx}/admin/resource/list"><i class="icon-docs"></i>资源列表
											</a></li>
											
										</ul>
									</li>
									<%--       <li class="${system_roleuserclass}">
                                            <a href="#"><i class="icon-docs"></i>用户权限分配 </a>
                                        </li> --%>
									<%-- <li class="${system_userclass}"><a
										href="${ctx}/admin/sysUser/list"><i class="icon-docs"></i>
											系统通知 </a></li> --%>
									<li class="${system_noticeclass}"><a
										href="${ctx}/admin/sysNotice"><i class="icon-docs"></i>
											平台公告 </a></li>
									<li class="${system_logclass}"><a
										href="${ctx}/admin/sysBizLog"><i class="icon-docs"></i>
											系统日志 </a></li>
								</ul></li>
						</ul>
					</div>
					<!-- END HEADER MENU -->
				</div>
				<!--/container-->
			</nav>
		</header>
		<!-- END HEADER -->
		<div class="container-fluid">

			<!-- BEGIN PAGE BASE CONTENT -->
			<div id="main-content" class="page-content">
				<decorator:body />
			</div>
			<!-- END PAGE BASE CONTENT -->

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
	<script src="${ctx}/static/assets/global/scripts/app.min.js"
		type="text/javascript"></script>
	<!-- END THEME GLOBAL SCRIPTS -->
	<!-- BEGIN THEME LAYOUT SCRIPTS -->
	<script
		src="${ctx}/static/assets/layouts/layout5/scripts/layout.min.js"
		type="text/javascript"></script>
	<!-- END THEME LAYOUT SCRIPTS -->
	<script
		src="${ctx}/static/assets/global/plugins/jquery-validation/js/jquery.validate.min.js"
		type="text/javascript"></script>
	<%-- 	<script
		src="${ctx}/static/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"
		type="text/javascript"></script>
	<script
		src="${ctx}/static/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js"
		type="text/javascript"></script> --%>
	<%-- 	<script
		src="${ctx}/static/assets/global/plugins/bootbox/bootbox.min.js"
		type="text/javascript"></script>--%>
	<script
		src="${ctx}/static/assets/global/plugins/bootstrap-toastr/toastr.min.js"
		type="text/javascript"></script>
	<%-- 	<script
		src="${ctx}/static/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"
		type="text/javascript"></script>
	<script
		src="${ctx}/static/assets/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"
		type="text/javascript"></script>
		<script type="text/javascript"
		src="${ctx}/static/assets/global/plugins/jquery-file-upload/js/vendor/jquery.ui.widget.js"></script>
	<script type="text/javascript"
		src="${ctx}/static/assets/global/plugins/jquery-file-upload/js/jquery.fileupload.js"></script> --%>
	<script type="text/javascript" src="${ctx}/static/js/common.js"></script>
</body>

</html>