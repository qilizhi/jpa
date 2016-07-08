/**
 * 
 */


var _SysUser=function(){
	
	//临时存放用户变量。
	
	var _userNo;
	/*对话框展开*/
	var authShow=function(userNo) {

		$("#modalForm").each(function() {
			this.reset();
		});
		loadRoleTreeByUserNo(userNo);
		$("#responsive").modal('show');
		$("#ajax").hide();
		//去除绑定update事件
		$("#modalForm button[type='submit']").unbind();
		//$("#modalForm button[type='submit']").click(add);
	}
	
	/* 关闭表单 */
	var  modalHide=function() {
		$("#pswResponsive,#responsive").modal('hide');
		$("div.modal-backdrop").remove();
		//重设表单 
		$("#modalForm").each(function() {
			this.reset();
		});
	}
	
	//获取对信息
	var get=function(id) {
		var url = mlx.ctx + "/admin/sysUser/get/" + id;
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
	
	var formValidate=function(){
		var form1 = $('#pswResponsive form');
		var error1 = $('.alert-danger', form1);
		var success1 = $('.alert-success', form1);

		form1.validate({
			errorElement : 'span', //default input error message container
			errorClass : 'help-block help-block-error', // default input error message class
			focusInvalid : false, // do not focus the last invalid input
			ignore : "", // validate all fields including form hidden input
			messages : {
				password : {
					minlength : "密码长度至少要5个字符！",
					required : "密码不能为空！"
				},
				confirm_password: {
					minlength : "密码长度不对",
					required : "密码不能为空！",
					equalTo:"两次输入的密码不正确！"
				}
			},
			rules : {
				password : {
					minlength : 5,
					//maxlength:25,
					required : true
				},
				confirm_password : {
					required : true,
					//maxlength:25,
					minlength:5,
					equalTo:'#password'
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
	
/*	更改密码对话框*/
	var changPswShow=function(id) {
		
		$("#pswResponsive #modalForm").each(function() {
			this.reset();
		});
		
		//获取对象
		var obj = get(id);
		//将对象属性 填充表单
		for ( var name in obj) {
			if(name=="id"){
				$("#pswResponsive #modalForm input[name=" + name + "]").val(obj[name]);
			}else{
			$("#pswResponsive #modalForm span[name=" + name + "]").html(obj[name]);
			}
		}
		
		//阻止表单事件
		$("form").submit(function(e) {
			e.preventDefault();
		});
		
		$("#pswResponsive").modal('show');
		$("#pswResponsive #ajax").hide();
		//去除绑定update事件
		$("#pswResponsive #modalForm button[type='submit']").unbind();
		$("#pswResponsive #modalForm button[type='submit']").click(changePsw);
		//$("#modalForm button[type='submit']").click(add);
	}
	
	//重设密码
	var changePsw=function(){
		var submitData = $("#pswResponsive #modalForm").serialize();
		var url=mlx.ctx+"/admin/sysUser/resetPassword";
		$("#ajax").show();
		$.ajax({
			url : url,
			type : 'post',
			dataType:"json",
			data : submitData,
			success : function(result) {
				if (result.code == "200") {
					modalHide();
					//location.reload();
					comm.showMsg('success', '消息提示', '密码重设成功！');
				} else {
					$("#ajax").hide();
					comm.showMsg('error', '消息提示', '密码重设失败！');
				}
			},
			error : function(e) {
				$("#message").html("请求出错！");
				comm.showMsg('error', '消息提示', '请求出错！');
				$("#ajax").hide();
			}
		});
		
	}
	

	// 根据RoleId加载权限树
	var loadRoleTreeByUserNo = function(userNo) {
		var roleUrl = mlx.ctx + "/admin/sysUser/roleTree";
		_userNo=userNo;		
		$.ajax({
			url : roleUrl,
			type : 'get',
			dataType:"json",
			data : {
				'userNo' : userNo
			},
			success : function(result) {
				var roleData = result.result;
				// $('#tree_2').removeData();
				$('#tree_2').jstree('destroy');
				//console.log(roleData);
				// $(document).off('.jstree');
				$("userNo").val(userNo);
				$('#tree_2').jstree({
					'plugins' : [ "checkbox", "types" ],
					'core' : {
						"themes" : {
							"responsive" : false
						},
						'data' : roleData
					},
					"types" : {
						"default" : {
							"icon" : "fa fa-folder icon-state-warning icon-lg"
						},
						"file" : {
							"icon" : "fa fa-file icon-state-warning icon-lg"
						}
					},
					"checkbox" : {
						whole_node : false,
						tie_selection : false
					}
				});

				// 绑定check and uncheck事件
				bind_check_un();

			}

		});

	}

	// 跟据authorityId roleId 删除,

	var insertUser_Role = function(userNo, roleIds) {
		var url = mlx.ctx + '/admin/sysUser/insertByUserNoAndRoleIds';
		$.ajax({
			url : url,
			type : 'post',
			dataType:"json",
			data : {
				'roleIds' : roleIds,
				'userNo' : userNo
			},
			success : function(result) {

				if (result.code == "200") {
					//comm.showMsg('success', '提示', '选择成功！');
				} else {
					comm.showMsg('warning', '提示', '选择失败!');
					// 刷新树
					loadRoleTreeByUserNo(userNo);
				}

			},
			error : function(e) {
				// 取消选择
				comm.showMsg('warning', '提示', '请求失败!');
				// //刷新树
				//$.jstree.reference('#tree_2').uncheck_node(authorityIds);
				loadRoleTreeByUserNo(userNo);
			}

		})

	}

	// 跟据authorityId roleId 添加,

	var deleteUser_Role = function(userNo,roleIds) {
		var url = mlx.ctx + '/admin/sysUser/deleteByUserNoAndRoleIds';
		$.ajax({
			url : url,
			type : 'post',
			dataType:"json",
			data : {
				'roleIds' : roleIds,
				'userNo' : userNo
			},
			success : function(result) {
				if (result.code == "200") {
					//comm.showMsg('success', '提示', '取消成功!');
				} else {
					// //刷新树
					comm.showMsg('warning', '提示', '取消失败!');
					//$.jstree.reference('#tree_2').check_node(authorityIds);
					loadRoleTreeByUserNo(userNo);
				}

			},
			error : function(e) {
				comm.showMsg('warning', '提示', '请求失败!');
				// //刷新树
				//$.jstree.reference('#tree_2').check_node(authorityIds);
				loadRoleTreeByUserNo(userNo);
			}
		})
	}
	
	var bind_check_un = function() {
		// 绑定uncheck check 事件
		$('#tree_2').on(
				'check_node.jstree',
				function(e, data) {
					// 给隐藏域set値
					// $("#authorityId").val(data.node.id);
					var roleIds = "";
					if (data.node.children_d.length > 0) {
						roleIds += data.node.id + ","
								+ data.node.children_d.toString();
					} else {

						roleIds = data.node.id;
					}
					insertUser_Role(_userNo,roleIds);

				});
		$('#tree_2').on(
				'uncheck_node.jstree',
				function(e, data) {
					// 给隐藏域set値
					// $("#authorityId").val(data.node.id);
					var roleIds = "";
					if (data.node.children_d.length > 0) {
						roleIds += data.node.id + ","
								+ data.node.children_d.toString();
					} else {

						roleIds = data.node.id;
					}
					deleteUser_Role(_userNo, roleIds);

				});

	}

	
	
	//_sysUser 暴露的方法
	return {
		
		authShow:function(userNo){
			authShow(userNo);
			
		},
		changePassword:function(userNo){
			changPswShow(userNo);
		},
		formValidate:function(){
			formValidate();
		}
	
		
	}
	
}()


jQuery(document).ready(function(){
	
	//初始化较验
	_SysUser.formValidate();
})

