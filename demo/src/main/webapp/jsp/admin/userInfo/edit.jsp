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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>导游编辑页面</title>
<style type="text/css">
.c-left {
	padding-top: 7px;
	margin-top: 1px;
	margin-bottom: 0;
	text-align: left;
}

.c-right {
	padding-top: 7px;
	margin-top: 1px;
	margin-bottom: 0;
	text-align: right;
}

.line {
	border-bottom: 1px solid #eef1f5;
}

form img {
	width: 200px;
	height: 150px;
}

.progress {
	border: 0;
	background-image: none;
	filter: none;
	/*  -webkit-box-shadow: none; */
	-moz-box-shadow: none;
	box-shadow: none;
	height: 20px;
	margin-bottom: 20px;
	background-color: #f5f5f5;
	border-radius: 4px;
	-webkit-box-shadow: inset 0 1px 2px rgba(0, 0, 0, .1);
	box-shadow: inset 0 1px 2px rgba(0, 0, 0, .1);
}

.btn>input {
	position: absolute;
	top: 0;
	right: 0;
	width: 100%;
	height: 100%;
	margin: 0;
	font-size: 23px;
	cursor: pointer;
	filter: alpha(opacity = 0);
	opacity: 0;
	direction: ltr;
}
</style>
<link
	href="${ctx}/static/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css">
</head>
<body>


	<div class="portlet light bordered">
		<div class="portlet-title">
			<div class="caption">
				<i class="icon-social-dribbble font-green"></i> <span
					class="caption-subject font-green bold uppercase">导游信息编辑</span>
			</div>
		</div>
		<div class="portlet-body">
			<!-- <div id="bootstrap_alerts_demo"></div> -->
			<div class="line">
				<h4>会员基本信息</h4>
			</div>
			<form class="form-horizontal">
				<div class="form-group">
					<label class="col-md-2 control-label">用户编号:</label> <span
						class="col-md-4  c-left">${userInfo.userNo}</span> <label
						class="col-md-2 control-label">微信openId:</label> <span
						class="col-md-4  c-left">${userInfo.openId}</span>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">昵称:</label> <span
						class="col-md-4  c-left">${userInfo.nickName}</span> <label
						class="col-md-2 control-label">性别:</label> <span
						class="col-md-4  c-left">${sexMap[userInfo.sex]}</span>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">省:</label> <span
						class="col-md-4  c-left">${userInfo.province}</span> <label
						class="col-md-2 control-label">市:</label> <span
						class="col-md-4  c-left">${userInfo.city}</span>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">国家:</label> <span
						class="col-md-4  c-left">${userInfo.country}</span> <label
						class="col-md-2 control-label">用户头像:</label> <span
						class="col-md-4  c-left"><a href="${userInfo.headImgUrl}"><img
							src="${userInfo.headImgUrl}" alt="点击图片查看大图" /></a></span>

					<!-- <span
						class="btn green fileinput-button"> <i class="fa fa-plus"></i>
						<span> Add files... </span> <input type="file" name="files[]"
						multiple="">
					</span> -->
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">特权:</label> <span
						class="col-md-4  c-left">${userInfo.privilege}</span> <label
						class="col-md-2 control-label">unionid:</label> <span
						class="col-md-4  c-left">${userInfo.unionid}</span>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">创建时间:</label> <span
						class="col-md-4  c-left">${userInfo.createDate}</span> <label
						class="col-md-2 control-label">更新时间:</label> <span
						class="col-md-4  c-left">${userInfo.updateDate}</span>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">语言:</label> <span
						class="col-md-4  c-left">${userInfo.language}</span> <label
						class="col-md-2 control-label">公众号:</label> <span
						class="col-md-4  c-left">${userInfo.weixinPublicId}</span>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">真实名字:</label> <span
						class="col-md-4  c-left">${userInfo.realName}</span> <label
						class="col-md-2 control-label">会员等级:</label> <span
						class="col-md-4  c-left">${userInfo.level}</span>
				</div>
				<div class="line">
					<h4>导游基本信息</h4>
				</div>

				<div class="form-group">
					<label class="col-md-2 control-label">导游等级:</label>
					<%-- 	<div class="col-md-4 ">
						<input class="c-left " name="guideLevel"
							value="${userInfo.guideLevel}" />
					</div> --%>
					<span class="col-md-4  c-left">${userInfo.guideLevel}</span> <label
						class="col-md-2 control-label">背景图url:</label> <span
						class="col-md-3  c-left"><a
						href="${imgPrex}/${userInfo.bgImgUrl}"><img
							src="${imgPrex}/${userInfo.bgImgUrl}" alt="点击图片查看大图" /></a> </span>

					<!-- <div class="fa-item col-md-1 c-left">
						<i class="fa fa-cloud-upload"></i> 上传
					</div>
						<span class="btn blue fileinput-button">
                                            <i class="fa fa-plus"></i>
                                            <span> 选择头像 </span>
                                            <input type="file" name="files[]" multiple="">
                                            </span>
                       <button class="btn blue start">
						<i class="fa fa-plus"></i> <span>选择头像</span>
						<input type="file" name="files[]" multiple="">
						 
					</button> -->


				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">年齡:</label> <span
						class="col-md-4  c-left">${userInfo.age}</span> <label
						class="col-md-2 control-label">导游签名:</label> <span
						class="col-md-4  c-left">${userInfo.sign}</span>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">标签 :</label> <span
						class="col-md-4  c-left">${userInfo.tag}</span> <label
						class="col-md-2 control-label">工作年限:</label> <span
						class="col-md-4  c-left">${userInfo.workYear}</span>

				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">介绍:</label>
					<%-- 	<div class="col-md-4 ">
						<input class="c-left " name="intro" value="${userInfo.intro}" />
					</div> --%>
					<span class="col-md-4  c-left">${userInfo.intro}</span> <label
						class="col-md-2 control-label">导游证号:</label> <span
						class="col-md-4  c-left">${userInfo.guideCardNo}</span>

				</div>
				<div class="form-group">



					<label class="col-md-2 control-label">正面图:</label> <span
						class="col-md-4  c-left"><a
						href="${imgPrex}/${userInfo.idCardFrontPic}"><img
							src="${imgPrex}/${userInfo.idCardFrontPic}" alt="点击图片查看大图" /></a></span> <label
						class="col-md-2 control-label">背面图:</label> <span
						class="col-md-4  c-left"><a
						href="${imgPrex}/${userInfo.idCardSidePic}"><img
							src="${imgPrex}/${userInfo.idCardSidePic}" alt="点击图片查看大图" /></a></span>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">身份证号:</label>
					<%-- 	<div class="col-md-4 ">
						<input class="c-left " name="idCard" value="${userInfo.idCard}" />
					</div> --%>
					<span class="col-md-4  c-left">${userInfo.idCard}</span> <label
						class="col-md-2 control-label">审核状态:</label> <span
						class="col-md-4  c-left">${auditMap[userInfo.auditStatus]}</span>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">音频:</label> <span
						class="col-md-4  c-left">${userInfo.audioUrl}</span> <label
						class="col-md-2 control-label">状态:</label> <span
						class="col-md-4  c-left">${userStatus[userInfo.status]}</span>
				</div>

				<!-- 
				<div class="form-group">
					<div class="col-md-2">

						<div class="progress progress-striped active">
							<div class="progress-bar progress-bar-success"
								style="width: 20%;"></div>
						</div>

					</div>
					<p class="size col-md-2 ">9.29 KB</p>
					<button class="btn blue start">
						<i class="fa fa-upload"></i> <span>Start</span>
					</button>
					<button class="btn red cancel">
						<i class="fa fa-ban"></i> <span>Cancel</span>
					</button>
				</div>-->
				<div class="row">
					<div class="col-md-offset-5 col-md-9">
						<a href="javascript:auditShow(${userInfo.id});" class="btn green">
							<i class="fa fa-check"></i>审核
						</a>
						<!--  <a href="javascript:;" class="btn btn-outline grey-salsa">Cancel</a> -->
					</div>
				</div>
			</form>
		</div>
	</div>



	<!-- modal -->

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

								<!-- <div class="alert alert-success display-hide">
									<button class="close" data-close="alert"></button>
									Your form validation is successful!
								</div> -->


								<div class="form-group">
									<label class="control-label col-md-6">审核<span
										class="required"> * </span> <input type="radio" value="3"
										name="auditStatus" />不通过 <input type="radio" value="2"
										name="auditStatus" checked />通过
									</label>
								</div>
								<!-- <div class="form-group  margin-top-20">
									<label class="control-label col-md-3">审核备注<span
										class="required"> * </span>
									</label>
									<div class="col-md-4">
										<textarea class="form-control" id="message" rows="3" name=""
											placeholder="请输入审核备注..."></textarea>
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
	<script>
		window.mlx = {
			ctx : "${ctx}"
		};
	</script>
	<script type="text/javascript">
		var auditUrl = mlx.ctx + "/admin/userInfo/audit";
		function auditShow(ids) {

			$("#ajax").hide();
			//将对象属性 填充表单
			/* for ( var name in obj) {
				$("#modalForm input[name=" + name + "]").val(obj[name]);
			} */
			//去除绑定事件
			var data = {};
			data.ids = ids;
			$("#modalForm button[type='submit']").on("click", data, commAudit);
			$("#responsive").modal('show');

		}

		//审核
		function commAudit(pdata) {

			var url = auditUrl + "/" + pdata.data.ids;
			var submitData = $("#responsive form").serialize();
			$.ajax({
				url : url,
				type : 'post',
				data : submitData,
				success : function(result) {
					if (result.code == "200") {
						//addHide();
						comm.showMsg('success', '消息提示', '通过成功！');
						location.reload();
					} else {
						comm.showMsg('warning', '消息提示', '通过失败！');
					}
				},
				error : function(e) {
					comm.showMsg('error', '消息提示', '审核 出错，网络出问题了！');
				}

			});

		}
	</script>

</body>
</html>