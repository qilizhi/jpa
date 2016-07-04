<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
</head>
<body>

	<div class="row">
		<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
			<div class="dashboard-stat blue">
				<div class="visual">
					<i class="fa fa-comments"></i>
				</div>
				<div class="details">
					<div class="number">
						<span data-counter="counterup" data-value="${monthSales}"></span>元
					</div>
					<div class="desc">本月销售额</div>
				</div>
				<a class="more" href="${ctx}/admin/orderLineInfo"> 更多 <i
					class="m-icon-swapright m-icon-white"></i>
				</a>
			</div>
		</div>
		<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
			<div class="dashboard-stat blue">
				<div class="visual">
					<i class="fa fa-bar-chart-o"></i>
				</div>
				<div class="details">
					<div class="number">
						<span data-counter="counterup" data-value="${monthServices}"></span>
					</div>
					<div class="desc">本月导服数</div>
				</div>
				<a class="more" href="${ctx}/admin/guideService"> 更多 <i
					class="m-icon-swapright m-icon-white"></i>
				</a>
			</div>
		</div>
		<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
			<div class="dashboard-stat yellow">
				<div class="visual">
					<i class="fa fa-shopping-cart"></i>
				</div>
				<div class="details">
					<div class="number">
						<span data-counter="counterup" data-value="${monthTuans}"></span>
					</div>
					<div class="desc">本月出团数</div>
				</div>
				<a class="more" href="${ctx}/admin/guideTuan"> 更多 <i
					class="m-icon-swapright m-icon-white"></i>
				</a>
			</div>
		</div>
		<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
			<div class="dashboard-stat purple">
				<div class="visual">
					<i class="fa fa-globe"></i>
				</div>
				<div class="details">
					<div class="number">
						<span data-counter="counterup" data-value="${monthNewGuides }"></span>

					</div>
					<div class="desc">本月新增导游数</div>
				</div>
				<a class="more" href="${ctx}/admin/guideUserInfo/list""> 更多 <i
					class="m-icon-swapright m-icon-white"></i>
				</a>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
			<div class="dashboard-stat red">
				<div class="visual">
					<i class="fa fa-comments"></i>
				</div>
				<div class="details">
					<div class="number">
						<span data-counter="counterup" data-value="${monthOrders }">0</span>
					</div>
					<div class="desc">本月订单数</div>
				</div>
				<a class="more" href="${ctx}/admin/orderLineInfo"> 更多 <i
					class="m-icon-swapright m-icon-white"></i>
				</a>
			</div>
		</div>
		<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
			<div class="dashboard-stat green">
				<div class="visual">
					<i class="fa fa-bar-chart-o"></i>
				</div>
				<div class="details">
					<div class="number">
						<span data-counter="counterup" data-value="${totalGuides }"></span>人
					</div>
					<div class="desc">导游总人数</div>
				</div>
				<a class="more" href="${ctx}/admin/orderLineInfo"> 更多 <i
					class="m-icon-swapright m-icon-white"></i>
				</a>
			</div>
		</div>
		<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
			<div class="dashboard-stat green">
				<div class="visual">
					<i class="fa fa-shopping-cart"></i>
				</div>
				<div class="details">
					<div class="number">
						<span data-counter="counterup" data-value="${totalMembers }">0</span>
					</div>
					<div class="desc">用户总数量</div>
				</div>
				<a class="more" href="javascript:;"> 更多 <i
					class="m-icon-swapright m-icon-white"></i>
				</a>
			</div>
		</div>
		<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
			<div class="dashboard-stat grey">
				<div class="visual">
					<i class="fa fa-globe"></i>
				</div>
				<div class="details">
					<div class="number">
						<span data-counter="counterup" data-value="${totalLines }"></span>
					</div>
					<div class="desc">线路数量</div>
				</div>
				<a class="more" href="javascript:;"> 更多 <i
					class="m-icon-swapright m-icon-white"></i>
				</a>
			</div>
		</div>
	</div>
	<div class="clearfix"></div>
	<!-- END DASHBOARD STATS 1-->
	<%--                     <div class="row">
                        <div class="col-md-6 col-sm-6">
                            <!-- BEGIN PORTLET-->
                            <div class="portlet light bordered">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class="icon-bar-chart font-green"></i>
                                        <span class="caption-subject font-green bold uppercase">浏览量</span>
                                        <span class="caption-helper">本周...</span>
                                    </div>
                                    <div class="actions">
                                        <div class="btn-group btn-group-devided" data-toggle="buttons">
                                            <label class="btn red btn-outline btn-circle btn-sm active">
                                                <input type="radio" name="options" class="toggle" id="option1">最新</label>
                                            <label class="btn red btn-outline btn-circle btn-sm">
                                                <input type="radio" name="options" class="toggle" id="option2">上周</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="portlet-body">
                                    <div id="site_statistics_loading">
                                        <img src="${ctx}/static/assets/global/img/loading.gif" alt="loading" /> </div>
                                    <div id="site_statistics_content" class="display-none">
                                        <div id="site_statistics" class="chart"> </div>
                                    </div>
                                </div>
                            </div>
                            <!-- END PORTLET-->
                        </div>
                        <div class="col-md-6 col-sm-6">
                            <!-- BEGIN PORTLET-->
                            <div class="portlet light bordered">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class="icon-share font-red-sunglo hide"></i>
                                        <span class="caption-subject font-red-sunglo bold uppercase">年度销售额</span>
                                        <span class="caption-helper">每月销售...</span>
                                    </div>
                                    <div class="actions">
                                        <div class="btn-group">
                                            <a href="" class="btn dark btn-outline btn-circle btn-sm dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true"> 筛选范围
                                                <span class="fa fa-angle-down"> </span>
                                            </a>
                                            <ul class="dropdown-menu pull-right">
                                                <li>
                                                    <a href="javascript:;"> Q1 2016
                                                        <span class="label label-sm label-default"> 一季度 </span>
                                                    </a>
                                                </li>
                                                <li>
                                                    <a href="javascript:;"> Q2 2016
                                                        <span class="label label-sm label-default"> 二季度 </span>
                                                    </a>
                                                </li>
                                                <li class="active">
                                                    <a href="javascript:;"> Q3 2016
                                                        <span class="label label-sm label-success"> 三季度 </span>
                                                    </a>
                                                </li>
                                                <li>
                                                    <a href="javascript:;"> Q4 2016
                                                        <span class="label label-sm label-warning"> 四季度 </span>
                                                    </a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="portlet-body">
                                    <div id="site_activities_loading">
                                        <img src="${ctx}/static/assets/global/img/loading.gif" alt="loading" /> </div>
                                    <div id="site_activities_content" class="display-none">
                                        <div id="site_activities" style="height: 228px;"> </div>
                                    </div>
                                    <div style="margin: 20px 0 10px 30px">
                                        <div class="row">
                                            <div class="col-md-3 col-sm-3 col-xs-6 text-stat">
                                                <span class="label label-sm label-success"> 最高值: </span>
                                                <h3>$13,234</h3>
                                            </div>
                                            <div class="col-md-3 col-sm-3 col-xs-6 text-stat">
                                                <span class="label label-sm label-info"> 总金额: </span>
                                                <h3>$134,900</h3>
                                            </div>
                                            <div class="col-md-3 col-sm-3 col-xs-6 text-stat">
                                                <span class="label label-sm label-danger"> 最低值: </span>
                                                <h3>$1,134</h3>
                                            </div>
                                            <div class="col-md-3 col-sm-3 col-xs-6 text-stat">
                                                <span class="label label-sm label-warning"> 订单数: </span>
                                                <h3>235090</h3>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- END PORTLET-->
                        </div>
                    </div> --%>
	<div class="row">
		<div class="col-md-6 col-sm-6">
			<div class="portlet light bordered">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-share font-blue"></i> <span
							class="caption-subject font-blue bold uppercase">待处理导游</span>
					</div>
					<div class="actions">
						<!-- <div class="btn-group">
							<a class="btn btn-sm blue btn-outline btn-circle"
								href="javascript:;"> <i class="fa fa-refresh"></i>
							</a>
						</div> -->
					</div>
				</div>
				<div class="portlet-body">
					<div class="scroller" style="height: 300px;"
						data-always-visible="1" data-rail-visible="0">
						<ul class="feeds">
							<c:forEach items="${guidesInfos}" var="guide">
								<li>
									<div class="col1">
										<div class="cont">
											<div class="cont-col1">
												<div class="label label-sm label-info">
													<i class="fa fa-user"></i>
												</div>
											</div>
											<div class="cont-col2">
												<div class="desc">
													${guide.realName } <span
														class="label label-sm label-success">${EAuditStatus[guide.auditStatus]}
													</span>
												</div>
											</div>
										</div>
									</div>
									<div class="col2">
										<div class="date">
											<fmt:formatDate value="${guide.createTime }"
												pattern="yyy/MM/dd" />
										</div>
									</div>
								</li>
							</c:forEach>

						</ul>
					</div>
					<div class="scroller-footer">
						<div class="btn-arrow-link pull-right">
							<a href="${ctx}/admin/guideUserInfo/list?isAuditStatus=true"">查看更多记录</a>
							<i class="icon-arrow-right"></i>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-6 col-sm-6">
			<div class="portlet light bordered">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-share font-blue"></i> <span
							class="caption-subject font-blue bold uppercase">待处理订单</span>
					</div>
					<div class="actions">
						<!-- <div class="btn-group">
							<a class="btn btn-sm blue btn-outline btn-circle"
								href="javascript:;"> <i class="fa fa-refresh"></i>
							</a>
						</div> -->
					</div>
				</div>
				<div class="portlet-body">
					<div class="scroller" style="height: 300px;"
						data-always-visible="1" data-rail-visible="0">
						<ul class="feeds">
							<c:forEach items="${w_orders}" var="order">
								<li>
									<div class="col1">
										<div class="cont">
											<div class="cont-col1">
												<div class="label label-sm label-info">
													<i class="fa fa-shopping-cart"></i>
												</div>
											</div>
											<div class="cont-col2">
												<div class="desc">
													未支付订单 <span class="label label-sm label-success">订单编号:
														${order.orderId} </span>
												</div>
											</div>
										</div>
									</div>
									<div class="col2">
										<div class="date">
											<fmt:formatDate value="${order.createTime}"
												pattern="yyy/MM/dd" />
										</div>
									</div>
								</li>
							</c:forEach>
							<c:forEach items="${rc_orders}" var="order">
								<li>
									<div class="col1">
										<div class="cont">
											<div class="cont-col1">
												<div class="label label-sm label-warning">
													<i class="fa fa-share"></i>
												</div>
											</div>
											<div class="cont-col2">
												<div class="desc">
													退款审核中订单 <span class="label label-sm label-success">订单编号:
														${order.orderId} </span>
												</div>
											</div>
										</div>
									</div>
									<div class="col2">
										<div class="date">
											<fmt:formatDate value="${order.createTime}"
												pattern="yyy/MM/dd" />
										</div>
									</div>
								</li>
							</c:forEach>
							<c:forEach items="${rw_orders}" var="order">
								<li>
									<div class="col1">
										<div class="cont">
											<div class="cont-col1">
												<div class="label label-sm label-danger">
													<i class="fa fa-briefcase"></i>
												</div>
											</div>
											<div class="cont-col2">
												<div class="desc">
													等待退款订单 <span class="label label-sm label-success">订单编号:
														${order.orderId} </span>
												</div>
											</div>
										</div>
									</div>
									<div class="col2">
										<div class="date">
											<fmt:formatDate value="${order.createTime }"
												pattern="yyy/MM/dd" />
										</div>
									</div>
								</li>
							</c:forEach>
						</ul>
					</div>
					<div class="scroller-footer">
						<div class="btn-arrow-link pull-right">
							<a href="${ctx}/admin/orderLineInfo">查看更多记录</a> <i
								class="icon-arrow-right"></i>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

	<!-- END PAGE BASE CONTENT -->
</body>
</html>