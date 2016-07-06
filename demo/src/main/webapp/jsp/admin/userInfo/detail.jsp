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
<title>详情页面</title>
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
.line{
border-bottom: 1px solid #eef1f5;
}

form img {
/* width:20px;
height:20px; */
    width: 20%;
}
</style>
</head>
<body>


	<div class="portlet light bordered">
		<div class="portlet-title">
			<div class="caption">
				<i class="icon-social-dribbble font-green"></i> <span
					class="caption-subject font-green bold uppercase">导游详情</span>
			</div>
		<!-- 	<div class="actions">
				<a class="btn btn-circle btn-icon-only btn-default"
					href="javascript:;"> <i class="icon-cloud-upload"></i>
				</a> <a class="btn btn-circle btn-icon-only btn-default"
					href="javascript:;"> <i class="icon-wrench"></i>
				</a> <a class="btn btn-circle btn-icon-only btn-default"
					href="javascript:;"> <i class="icon-trash"></i>
				</a>
			</div> -->
		</div>
		<div class="portlet-body">
			<!-- <div id="bootstrap_alerts_demo"></div> -->
				<div class="line"> <h4>会员基本信息</h4 ></div>
			<form class="form-horizontal">
				<div class="form-group">
					<label class="col-md-2 control-label" for="title">用户编号:</label> <span
						class="col-md-4  c-left" for="title">${userInfo.userNo}</span> <label
						class="col-md-2 control-label" for="title">微信openId:</label> <span
						class="col-md-4  c-left" for="title">${userInfo.openId}</span>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="title">昵称:</label> <span
						class="col-md-4  c-left" for="title">${userInfo.nickName}</span> <label
						class="col-md-2 control-label" for="title">性别:</label> <span
						class="col-md-4  c-left" for="title">${sexMap[userInfo.sex]}</span>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="title">省:</label> <span
						class="col-md-4  c-left" for="title">${userInfo.province}</span> <label
						class="col-md-2 control-label" for="title">市:</label> <span
						class="col-md-4  c-left" for="title">${userInfo.city}</span>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="title">国家:</label> <span
						class="col-md-4  c-left" for="title">${userInfo.country}</span> <label
						class="col-md-2 control-label" for="title">用户头像:</label> <span
						class="col-md-4  c-left" for="title"><a href="${userInfo.headImgUrl}"><img src="${userInfo.headImgUrl}" alt="点击图片查看大图"/></a></span>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="title">特权:</label> <span
						class="col-md-4  c-left" for="title">${userInfo.privilege}</span>
					<label class="col-md-2 control-label" for="title">unionid:</label>
					<span class="col-md-4  c-left" for="title">${userInfo.unionid}</span>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="title">创建时间:</label> <span
						class="col-md-4  c-left" for="title">${userInfo.createDate}</span>
					<label class="col-md-2 control-label" for="title">更新时间:</label> <span
						class="col-md-4  c-left" for="title">${userInfo.updateDate}</span>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="title">语言:</label> <span
						class="col-md-4  c-left" for="title">${userInfo.language}</span> <label
						class="col-md-2 control-label" for="title">公众号:</label> <span
						class="col-md-4  c-left" for="title">${userInfo.weixinPublicId}</span>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="title">真实名字:</label> <span
						class="col-md-4  c-left" for="title">${userInfo.realName}</span> <label
						class="col-md-2 control-label" for="title">会员等级:</label> <span
						class="col-md-4  c-left" for="title">${userInfo.level}</span>
				</div>
				<div class="line"> <h4>导游基本信息</h4 ></div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="title">导游等级:</label> <span
						class="col-md-4  c-left" for="title">${userInfo.guideLevel}</span>
					<label class="col-md-2 control-label" for="title">背景图url:</label> <span
						class="col-md-4  c-left" for="title"><a href="${imgPrex}/${userInfo.bgImgUrl}"><img src="${imgPrex}/${userInfo.bgImgUrl}" alt="点击图片查看大图"/></a></span>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="title">年齡:</label> <span
						class="col-md-4  c-left" for="title">${userInfo.age}</span> <label
						class="col-md-2 control-label" for="title">导游签名:</label> <span
						class="col-md-4  c-left" for="title">${userInfo.sign}</span>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="title">标签 :</label> <span
						class="col-md-4  c-left" for="title">${userInfo.tag}</span> <label
						class="col-md-2 control-label" for="title">工作年限:</label> <span
						class="col-md-4  c-left" for="title">${userInfo.workYear}</span>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="title">介绍:</label> <span
						class="col-md-4  c-left" for="title">${userInfo.intro}</span> <label
						class="col-md-2 control-label" for="title">导游证号:</label> <span
						class="col-md-4  c-left" for="title">${userInfo.guideCardNo}</span>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="title">正面图:</label> <span
						class="col-md-4  c-left" for="title"><a href="${imgPrex}/${userInfo.idCardFrontPic}"><img src="${imgPrex}/${userInfo.idCardFrontPic}" alt="点击图片查看大图"/></a></span>
					<label class="col-md-2 control-label" for="title">背面图:</label> <span
						class="col-md-4  c-left" for="title"><a href="${imgPrex}/${userInfo.idCardSidePic}"><img src="${imgPrex}/${userInfo.idCardSidePic}" alt="点击图片查看大图"/></a></span>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="title">身份证号:</label> <span
						class="col-md-4  c-left" for="title">${userInfo.idCard}</span> <label
						class="col-md-2 control-label" for="title">审核状态:</label> <span
						class="col-md-4  c-left" for="title">${auditMap[userInfo.auditStatus]}</span>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="title">音频:</label> <span
						class="col-md-4  c-left" for="title">${userInfo.audioUrl}</span> <label
						class="col-md-2 control-label" for="title">状态:</label> <span
						class="col-md-4  c-left" for="title">${userStatus[userInfo.status]}</span>
				</div>

			</form>
		</div>
	</div>
	<script>
		window.mlx = {
			ctx : "${ctx}"
		};
	</script>
</body>
</html>