<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图表</title>
</head>
<body>
	<div class="row">
		<div class="col-md-12">
			<!-- BEGIN CHART PORTLET-->
			<div class="row">
				<div class="col-md-6">
					<!-- BEGIN CHART PORTLET-->
					<div class="portlet light bordered">
						<div class="portlet-title">
							<div class="caption">
								<i class="icon-bar-chart font-green-haze"></i> <span
									class="caption-subject bold uppercase font-green-haze">会员统计</span>
								<span class="caption-helper">当月每天新增会员数量（个）</span>
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"> </a> <a
									href="#portlet-config" data-toggle="modal" class="config">
								</a>
								<!--  <a href="javascript:void();" class="reload"> </a> -->
								<a href="javascript:;" class="fullscreen"> </a> <a
									href="javascript:;" class="remove"> </a>
							</div>
						</div>
						<div class="portlet-body">
							<div id="chart_1" class="chart" style="height: 500px;"></div>
						</div>
					</div>
					<!-- END CHART PORTLET-->
				</div>

				<div class="col-md-6">
					<!-- BEGIN CHART PORTLET-->
					<div class="portlet light bordered">
						<div class="portlet-title">
							<div class="caption">
								<i class="icon-bar-chart font-green-haze"></i> <span
									class="caption-subject bold uppercase font-green-haze">导游统计</span>
								<span class="caption-helper">当月每天注册导游数量（个）</span>
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"> </a> <a
									href="#portlet-config" data-toggle="modal" class="config">
								</a>
								<!--  <a href="javascript:;" class="reload"> </a> -->
								<a href="javascript:;" class="fullscreen"> </a> <a
									href="javascript:;" class="remove"> </a>
							</div>
						</div>
						<div class="portlet-body">
							<div id="chart_2" class="chart" style="height: 500px;"></div>
						</div>
					</div>
					<!-- END CHART PORTLET-->
				</div>
			</div>
			<!-- END CHART PORTLET-->
		</div>
	</div>
	<script type="text/javascript">
		window.mlx = {
			ctx : "${ctx}"
		};
	</script>
	<script
		src="${ctx}/static/assets/global/plugins/amcharts/amcharts/amcharts.js"
		type="text/javascript"></script>
	<script
		src="${ctx}/static/assets/global/plugins/amcharts/amcharts/serial.js"
		type="text/javascript"></script>
	<script
		src="${ctx}/static/assets/global/plugins/amcharts/amcharts/themes/light.js"
		type="text/javascript"></script>

	<script src="${ctx}/static/js/userChart.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function() {
			var userUrl = mlx.ctx + "/admin/userInfo/statistics";
			var memberData = "";
			var guideData = "";
			//获取数据
			$.ajax({
				url : userUrl,
				data : {
					userType : 1
				},
				type : "get",
				async : false,
				success : function(result) {

					memberData = result.result;
				}

			});
			$.ajax({
				url : userUrl,
				data : {
					userType : 2
				},
				type : "get",
				async : false,
				success : function(result) {

					guideData = result.result;
				}

			});
			//画图表
			_userChart.initMemberChart(memberData);
			_userChart.initGuideChart(guideData);

		})
	</script>
</body>
</html>