/**
 * 
 */

/**
 * author qilizhi
 * 
 */

/** **权限树的处理************* */
var UITree = function() {

	/* 初始化树 */
	var contextualMenuSample = function(url) {

		$.ajax({
			url : url,
			type : 'get',
			dataType:"json",
			success : function(result) {
				var roleData = result.result;
				$("#tree_3").jstree({
					"core" : {
						"themes" : {
							"responsive" : true
						},
						// so that create works
						"check_callback" : true,
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
					"state" : {
						"key" : "demo2"
					},
					"plugins" : [ "contextmenu", "dnd", "state", "types" ]
				});
			}
		});

	}

	var rename = function(url) {

		$('#tree_3').on(
				'rename_node.jstree',
				function(e, data) {
					$.ajax({
						url : url,
						type : 'post',
						dataType:"json",
						data : {
							"id" : data.node.id,
							"name" : data.text
						},
						success : function(result) {
							// 更新成功
							if (result.code == "200") {
								comm.showMsg('success', '消息提示', '重命名成功！');
							} else {
								comm.showMsg('error', '消息提示', '重命名失败！');

								$.jstree.reference('#tree_3').set_text(
										data.node, data.old);

							}

						}

					});

				})
	}

	//新增根Role
	var add=function(){
		var submitData = $("#addRoleResponsive form").serialize();
		$("#ajax").show();
		$.ajax({
			url : mlx.ctx + "/admin/role/create",
			type : 'post',
			dataType:"json",
			data : submitData,
			success : function(result) {
				
				if (result.code == "200") {
					$("#addRoleResponsive").modal('hide');
					$("div.modal-backdrop").remove();
					//重设表单 
					$("#addRoleResponsive #modalForm").each(function() {
						this.reset();
					});
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
	
	var addRoleShow=function() {

		$("#addRoleResponsive #modalForm").each(function() {
			this.reset();
		});
		$("#addRoleResponsive").modal('show');
		$("#addRoleResponsive #ajax").hide();
		//去除绑定update事件
		$("#addRoleResponsive #modalForm button[type='submit']").unbind();
		$("#addRoleResponsive #modalForm button[type='submit']").click(add);
	}
	var create = function(url) {

		$('#tree_3').on(
				'create_node.jstree',
				function(e, data) {

					$.ajax({
						url : url,
						type : 'post',
						dataType:"json",
						data : {
							"parentId" : data.parent,
							"name" : data.node.text
						},
						success : function(result) {
							// console.log(result);
							// 创建成功
							if (result.code == "200") {
								comm.showMsg('success', '消息提示', '创建成功！');
								$.jstree.reference('#tree_3').set_id(data.node,
										result.result.id);
							} else {
								comm.showMsg('error', '消息提示', '创建失败！');
								$('#tree_3').jstree('select_node', data.old);

							}

						}

					});

				})
	}

	var del = function(url) {
		$('#tree_3').on(
				'delete_node.jstree',
				function(e, data) {
					$.ajax({
						url : url,
						type : 'post',
						dataType:"json",
						data : {
							"id" : data.node.id,
							"name" : data.node.text
						},
						success : function(result) {

							// 删除成功
							if (result.code == "200") {
								comm.showMsg('success', '消息提示', '删除成功！');

								$.jstree.reference('#tree_3').set_id(data.node,
										result.result.id);
							} else {
								comm.showMsg('error', '消息提示', '删除失败！');
								$.jstree.reference('#tree_3').refresh();

							}

						}

					});

				})

	}

	var move = function(url) {

		$('#tree_3').on('move_node.jstree', function(e, data) {
			if (data.parent != data.old_parent) {
				$.ajax({
					url : url,
					type : 'post',
					dataType:"json",
					data : {
						"parentId" : data.parent,
						"id" : data.node.id
					},
					success : function(result) {
						// 删除成功
						if (result.code == "200") {
							comm.showMsg('success', '消息提示', '移动成功！');

							/*
							 * $.jstree.reference('#tree_3')
							 * .set_id(data.node,result.result.id);
							 */
						} else {
							comm.showMsg('error', '消息提示', '移动失败！');
							$.jstree.reference('#tree_3').refresh();

						}

					}

				});
			}

		})

	}

	var copy = function(url) {

		$('#tree_3').on('copy_node.jstree', function(e, data) {
			// console.log(data);

			/*
			 * $.ajax({ url : url, type : 'post', data : {
			 * "parentId":data.parent, "id" : data.node.id }, success :
			 * function(result) { // 删除成功 if (result.code == "200") {
			 * comm.showMsg('success', '消息提示', '移动成功！');
			 * 
			 * $.jstree.reference('#tree_3')
			 * .set_id(data.node,result.result.id); } else {
			 * comm.showMsg('error', '消息提示', '移动失败！');
			 * $.jstree.reference('#tree_3').refresh(); } }
			 * 
			 * });
			 */

		})

	}



	// 根据RoleId加载权限树
	var loadAuthorityTreeByRoleId = function(roleId) {
		var authorityUrl = mlx.ctx + "/admin/role/authorityTree"
		$.ajax({
			url : authorityUrl,
			type : 'get',
			dataType:"json",
			data : {
				'roleId' : roleId
			},
			success : function(result) {
				var authorityData = result.result;
				// $('#tree_2').removeData();
				$('#tree_2').jstree('destroy');
				// $(document).off('.jstree');
				//console.log(authorityData);
				$('#tree_2').jstree({
					'plugins' : [ "checkbox", "types" ],
					'core' : {
						"themes" : {
							"responsive" : false
						},
						'data' : authorityData
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

	var insertRole_Authority = function(roleId, authorityIds) {
		var url = mlx.ctx + '/admin/role/insertByRoleIdAndAuthIds';
		$.ajax({
			url : url,
			type : 'post',
			dataType:"json",
			data : {
				'roleId' : roleId,
				'authorityIds' : authorityIds
			},
			success : function(result) {

				if (result.code == "200") {
					//comm.showMsg('success', '提示', '选择成功！');
				} else {
					comm.showMsg('warning', '提示', '选择失败!');
					// 刷新树
					loadAuthorityTreeByRoleId(roleId);
				}

			},
			error : function(e) {
				// 取消选择
				comm.showMsg('warning', '提示', '请求失败!');
				// //刷新树
				//$.jstree.reference('#tree_2').uncheck_node(authorityIds);
				loadAuthorityTreeByRoleId(roleId);
			}

		})

	}

	// 跟据authorityId roleId 添加,

	var deleteRole_Authority = function(roleId, authorityIds) {
		var url = mlx.ctx + '/admin/role/deleteByRoleIdAndAuthIds';
		$.ajax({
			url : url,
			type : 'post',
			dataType:"json",
			data : {
				'roleId' : roleId,
				'authorityIds' : authorityIds
			},
			success : function(result) {
				if (result.code == "200") {
					//comm.showMsg('success', '提示', '取消成功!');
				} else {
					// //刷新树
					comm.showMsg('warning', '提示', '取消失败!');
					//$.jstree.reference('#tree_2').check_node(authorityIds);
					loadAuthorityTreeByRoleId(roleId);
				}

			},
			error : function(e) {
				comm.showMsg('warning', '提示', '请求失败!');
				// //刷新树
				//$.jstree.reference('#tree_2').check_node(authorityIds);
				loadAuthorityTreeByRoleId(roleId);
			}
		})
	}

	// 绑定点击事件
	var click = function() {
		$('#tree_3').on('activate_node.jstree', function(e, data) {
			/* 给隐藏域set値 */
			// $("#authorityId").val(data.node.id);
			loadAuthorityTreeByRoleId(data.node.id);
		});
	}

	var bind_check_un = function() {
		// 绑定uncheck check 事件
		$('#tree_2').on(
				'check_node.jstree',
				function(e, data) {
					// 给隐藏域set値
					// $("#authorityId").val(data.node.id);
					var authorityIds = "";
					if (data.node.children_d.length > 0) {
						authorityIds += data.node.id + ","
								+ data.node.children_d.toString();
					} else {

						authorityIds = data.node.id;
					}
					var roleId = UImultiSelect.getSelectedRoleId()
					insertRole_Authority(roleId, authorityIds);

				});
		$('#tree_2').on(
				'uncheck_node.jstree',
				function(e, data) {
					// 给隐藏域set値
					// $("#authorityId").val(data.node.id);
					var authorityIds = "";
					if (data.node.children_d.length > 0) {
						authorityIds += data.node.id + ","
								+ data.node.children_d.toString();
					} else {

						authorityIds = data.node.id;
					}
					var roleId = UImultiSelect.getSelectedRoleId();
					deleteRole_Authority(roleId, authorityIds);

				});

	}

	return {
		// main function to initiate the module
		init : function(url) {
			contextualMenuSample(url);
			$("#add").on("click",function(e){
				addRoleShow();
			})

		},
		reNameNode : function(url) {

			rename(url);
		},
		createNode : function(url) {
			create(url);
		},
		deleteNode : function(url) {

			del(url);
		},
		moveNode : function(url) {
			move(url);
		},
		copyNode : function(url) {
			copy(url);

		},
		clicked : function() {
			click();
		}
	};

}();

/** **权限树的处理*** end********** */

/** *******选择资源的处理 start*********************** */
var UImultiSelect = function() {

	// 获取选 中的权限树Id
	var getSelectedRoleId = function() {
		nodeArray = $('#tree_3').jstree('get_selected')[0];
		// console.log(nodeArray);
		return nodeArray;

	}

	return {
		getSelectedRoleId : function() {
			return getSelectedRoleId();
		}

	};

}();

jQuery(document).ready(function() {
	var renameUrl = mlx.ctx + "/admin/role/update";
	var createUrl = mlx.ctx + "/admin/role/create";
	var deleteUrl = mlx.ctx + "/admin/role/delete";
	var moveUrl = mlx.ctx + "/admin/role/update";
	var copyUrl = mlx.ctx + "/admin/role/copy";
	var treeUrl = mlx.ctx + "/admin/role/roleTree";

	// 绑定按钮
	UITree.copyNode(copyUrl);
	UITree.moveNode(moveUrl);
	UITree.reNameNode(renameUrl);
	UITree.createNode(createUrl);
	UITree.deleteNode(deleteUrl);
	UITree.init(treeUrl);
	UITree.clicked();

	/*
	 * //选中树 var nodeId=$("#authorityId").val(); console.log(nodeId);
	 * $.jstree.reference('#tree_3').select_node(nodeId);
	 */

});
