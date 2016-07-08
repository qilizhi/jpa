/**
 * 活动
 */

var _ActInfo=function(){
	
	var addUrl = mlx.ctx + "/admin/resource/create";
	var updateUrl = mlx.ctx + "/admin/resource/update";
	var delUrl = mlx.ctx + "/admin/resource/delete";
	var batdelUrl = mlx.ctx + "/admin/resource/deletes";
	var getUrl = mlx.ctx + "/admin/resource";

	var  initDateTimePicker=function() {
		$(".form_datetime").datetimepicker({
			autoclose : true,
			isRTL : App.isRTL(),
			language : "zh-CN",
			format : "yyyy-mm-dd hh:ii:ss",
			pickerPosition : (App.isRTL() ? "bottom-right" : "bottom-left")
		});
	}

	/**获取表格上选 中的ids**/
	 var getCheckIds=function() {
		//获取删 除的ids
		var ids = "";
		var obj = $("tbody tr td input[type='checkbox']:checked");
		$.each(obj, function(index, data) {
			if (obj.length == index + 1) {
				ids += $(data).attr("id");
			} else {
				ids += $(data).attr("id") + ",";
			}
		});
		return ids;
	}

	/**  批量删除 **/
	var  batDel=function() {
		var ids = getCheckIds();
		if (ids == "") {
			comm.showMsg('warning', '消息提示', '没有选 中，请选择！');
			return;
		}
		var url = batdelUrl + "/" + ids;
		/* 设置按钮的语言 */
		//bootbox.setLocale("zh_CN");
		comm.confirm("提示","你确定要删除这条记录吗?", function() {
			//console.log("OK"+result);
			//if (result) {

				$.ajax({
					url : url,
					type : 'post',
					dataType:"json",
					success : function(result) {
						if (result.code == "200") {
							//addHide();
							comm.showMsg('success', '消息提示', '删除成功！');
							location.reload();
						} else {
							comm.showMsg('warning', '消息提示', '删除失败！');

						}
					},
					error : function(e) {
						comm.showMsg('error', '消息提示', '删除出错，网络出问题了！');
					}

				});

			//}
		});

	}

	/**详情**/
	var detailShow=function(id) {
		$("#detailResponsive").modal('show');
		var obj = get(id);
		//将对象属性 填充表单
		for ( var name in obj) {
			$("#modalForm span[id=" + name + "]").html(obj[name]);
		}
		/* $("#ajax").hide();
		$("#modalForm button[type='submit']").one("click", add); */
	}
	var addShow=function() {

		$("#modalForm").each(function() {
			this.reset();
		});
		$("#responsive").modal('show');
		$("#ajax").hide();
		//去除绑定update事件
		$("#modalForm button[type='submit']").unbind();
		$("#modalForm button[type='submit']").click(add);
	}
	/** 编辑**/
	var editShow=function(id) {

		/* 	$("#modalForm").each(function() {
				this.reset();
			}); */

		//获取对象
		var obj = get(id);
		$("#ajax").hide();
		//将对象属性 填充表单
		for ( var name in obj) {
			$("#modalForm input[name=" + name + "]").val(obj[name]);
		}
		//阻止表单事件
		$("form").submit(function(e) {
			e.preventDefault();
		});
		//去除绑定update事件
		$("#modalForm button[type='submit']").unbind();
		$("#modalForm button[type='submit']").on('click', update);
		$("#responsive").modal('show');

	}

	/* 隐藏 */
	var  modalHide=function() {
		$("#responsive").modal('hide');
		$("div.modal-backdrop").remove();
		//重设表单 
		$("#modalForm").each(function() {
			this.reset();
		});
	}

	//获取对信息
	var get=function(id) {
		var url = getUrl + "/" + id;
		var data;
		$.ajax({
			url : url,
			type : 'get',
			dataType:"json",
			async : false,
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

	//新增
	var  add=function() {
		var submitData = $("#responsive form").serialize();
		$("#ajax").show();
		$.ajax({
			url : addUrl,
			type : 'post',
			dataType:"json",
			data : submitData,
			success : function(result) {
				if (result.code == "200") {
					modalHide();
					location.reload();

				} else {
					$("#message").html("创建失败！");
					comm.showMsg('error', '消息提示', '创建失败！');
					$("#ajax").hide();
				}
			},
			error : function(e) {
				$("#message").html("请求出错！");
				comm.showMsg('error', '消息提示', '请求出错！');
				$("#ajax").hide();
			}
		});

	}

	//提 交更新数据
	var update=function() {
		var submitData = $("#modalForm").serialize();
		$("#ajax").show();
		$.ajax({
			url : updateUrl,
			type : 'post',
			dataType:"json",
			data : submitData,
			success : function(result) {
				if (result.code == "200") {
					modalHide();
					location.reload();

				} else {
					$("#ajax").hide();
					comm.showMsg('error', '消息提示', '更新失败！');
				}
			},
			error : function(e) {
				$("#message").html("请求出错！");
				comm.showMsg('error', '消息提示', '请求出错！');
				$("#ajax").hide();
			}
		});
	}

	/* 删除 */
	var del=function(id) {
		var url = delUrl + "/" + id;
		/* 设置按钮的语言 */
		//bootbox.setLocale("zh_CN");
		comm.confirm("提示","你确定要删除这条记录吗?", function() {
			//console.log(result);
			//if (result) {

				$.ajax({
					url : url,
					type : 'post',
					dataType:"json",
					success : function(result) {
						if (result.code == "200") {
							//addHide();
							comm.showMsg('success', '消息提示', '删除成功！');
							location.reload();
						} else {
							comm.showMsg('warning', '消息提示', '删除失败！');

						}
					},
					error : function(e) {
						comm.showMsg('error', '消息提示', '删除出错，网络出问题了！');
					}

				});

			//}
		});
	}

	//查看红包记录
	var showRecord=function(id) {
		var url = mlx.ctx + "/admin/actRedPacket?id=" + id;
		window.location.href = url;

	}
	
	var selectAll=function (){
	
	$("thead tr th input[type='checkbox']").on(
			"click",
			function(event) {
			//	console.log(event);
				//console.log(event.currentTarget.checked);
				if (event.currentTarget.checked==true) {
					$("tbody tr input[type='checkbox']").prop(
							"checked", true);
				} 
				
				if(event.currentTarget.checked==false) {
					$("tbody tr input[type='checkbox']").prop(
							"checked",false);
				}
			});
	}
	
	var formValidate=function(){
		var form1 = $('#responsive form');
		var error1 = $('.alert-danger', form1);
		var success1 = $('.alert-success', form1);

		form1.validate({
			errorElement : 'span', //default input error message container
			errorClass : 'help-block help-block-error', // default input error message class
			focusInvalid : false, // do not focus the last invalid input
			ignore : "", // validate all fields including form hidden input
			messages : {

				name : {
					minlength : "至少要一个字符！",
					required : "不能为空！"
				},

				path : {
					minlength : 2,
					required : "不能为空！"
				},
				description:{
					required:"请写描述！"
				}
			},
			rules : {
				name : {
					minlength : 1,
					required : true
				},
				path : {
					required : true,
					minlength:1
				},
				description:{
					
					required:true
				}
				

			},

			invalidHandler : function(event, validator) { //display error alert on form submit              
				success1.hide();
				error1.show();
				App.scrollTo(error1, -200);
			},

			highlight : function(element) { // hightlight error inputs
				$(element).closest('.form-group').addClass('has-error'); // set error class to the control group
			},

			unhighlight : function(element) { // revert the change done by hightlight
				$(element).closest('.form-group').removeClass('has-error'); // set error class to the control group
			},

			success : function(label) {
				label.closest('.form-group').removeClass('has-error'); // set success class to the control group
			},

			submitHandler : function(form) {
				success1.show();
				error1.hide();
			}
		});
		
	}

	
	
	return {
		selectAll:function(){
			selectAll();
					
		},
	    formValidate:function(){
	    	formValidate();
	    },showRecord:function(id){
	    	showRecord(id);
	    },del:function(id){
	    	del(id);
	    },detailShow:function(id){
	    	
	    	detailShow(id);
	    },editShow:function(id){
	    		
	    		editShow(id);
	    },batDel:function(){
	    	batDel();
	    },addShow:function(){
	    	addShow();
	    	
	    },init:function(){
	    	initDateTimePicker();
	    	selectAll();
	    	formValidate();
	    }
	}
	
}()

jQuery(document).ready(function(){
	_ActInfo.init();
	//绑定按钮
	$(".addShow").on("click",function(event){
		_ActInfo.addShow();        	
     });			
		
     $(".batDel").on("click",function(event){
    	 _ActInfo.batDel();        	
     });			
     $(".detailShow").on("click",function(event){
    	 _ActInfo.detailShow(event.target.id);        	
     });			
     $(".editShow").on("click",function(event){
    	 _ActInfo.editShow(event.target.id);        	
     });			
     $(".del").on("click",function(event){
    	 _ActInfo.del(event.target.id);        	
     });			
     $(".showRecord").on("click",function(event){
    	 _ActInfo.showRecord(event.target.id);        	
     });			
	
})