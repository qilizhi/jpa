/**
 * author qilizhi
 * 
 */

/** **权限树的处理************* */
var UITree = function() {

	var contextualMenuSample = function(url) {
		var data;
		$.ajax({
			url : url,
			type : 'get',
			dataType:"json",
			async : false,
			success : function(result) {
				data = result.result;
			}

		});
		$("#tree_3").jstree({
			"core" : {
				"themes" : {
					"responsive" : true
				},
				// so that create works
				"check_callback" : true,
				'data' : data
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

	var add=function(){
		var submitData = $("#addAuthorityResponsive form").serialize();
		$("#ajax").show();
		$.ajax({
			url : mlx.ctx + "/admin/authority/create",
			type : 'post',
			dataType:"json",
			data : submitData,
			success : function(result) {
				
				if (result.code == "200") {
					$("#addAuthorityResponsive").modal('hide');
					$("div.modal-backdrop").remove();
					//重设表单 
					$("#modalForm").each(function() {
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


	var searchFormSubmit = function() {
		$(document).on("click", "#searchFormbotton", function(e){
			
			
		});
		$(document).on("change", "form select[name=pageS]",function(e){
			
			var authorityId =UImultiSelect.getSelectedAuthorityId();	
						reloadTable(authorityId,"","");
		});

	}

	
	//刷新表单
	var reloadTable = function(authorityId,pageNo,pageSize) {
		var url = mlx.ctx + "/admin/authority/loadResource";
		//var pageSize=$("#pageSize").val();
		$.ajax({
			url : url,
			type : "get",
			data : {
				'authorityId':authorityId,
				'pageSize':pageSize,
				'pageNo':pageNo
			},
			success : function(data) {
				$("#mainTable").empty();
				$("#mainTable").append(data);

			},
			error : function(e) {
				comm.showMsg('error', '消息提示', '加载失败！');
			}

		});

	}

	var click = function(url) {
		$('#tree_3').on('activate_node.jstree', function(e, data) {
			/* 给隐藏域set値 */
			//$("#authorityId").val(data.node.id);
			reloadTable(data.node.id,"","");
		});

	}
	var addAuthorityShow=function() {

		$("#addAuthorityResponsive #modalForm").each(function() {
			this.reset();
		});
		$("#addAuthorityResponsive").modal('show');
		$("#addAuthorityResponsive #ajax").hide();
		//去除绑定update事件
		$("#addAuthorityResponsive #modalForm button[type='submit']").unbind();
		$("#addAuthorityResponsive #modalForm button[type='submit']").click(add);
	}

	return {
		// main function to initiate the module
		init : function(url) {
			contextualMenuSample(url);
			$("#add").on("click",function(e){
				addAuthorityShow();
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
		clicked : function(url) {
			click(url);
		},
		searchFormSubmit : function() {

			searchFormSubmit();
		},reloadTable:function(authorityId,pageNo,pageSize){
			
			reloadTable(authorityId,pageNo,pageSize);
		}

	};

}();

/** **权限树的处理*** end********** */











/** *******选择资源的处理 start*********************** */
var UImultiSelect = function() {

/*	var addUrl = mlx.ctx + "/admin/actInfo/create";
	var updateUrl = mlx.ctx + "/admin/actInfo/update";
	var delUrl = mlx.ctx + "/admin/actInfo/delete";
	var batdelUrl = mlx.ctx + "/admin/actInfo/deletes";
	var getUrl = mlx.ctx + "/admin/actInfo";*/

	var addShow = function() {
		var authorityId = getSelectedAuthorityId();
		if (authorityId == "" || authorityId == undefined) {
			comm.showMsg('warning', '提示', '请选择权限树！');
			return;
		}
		// 打开select 框
		handleMultiSelect();
		$("#modalForm").each(function() {
			this.reset();
		});
		$("#responsive").modal('show');
		$("#ajax").hide();
		// 阻止表单事件
		$("form").submit(function(e) {
			e.preventDefault();
		});
		// 去除绑定update事件
		$("#modalForm button[type='submit']").unbind();
		$("#modalForm button[type='submit']").click(updateSelected);
		$("#modalForm button[type='submit']").click(getSelected);
	}
	/** 编辑* */
	var editShow = function(id) {
		/*
		 * $("#modalForm").each(function() { this.reset(); });
		 */

		// 获取对象
		var obj = get(id);
		$("#ajax").hide();
		// 将对象属性 填充表单
		for ( var name in obj) {
			$("#modalForm input[name=" + name + "]").val(obj[name]);
		}
		// 阻止表单事件
		$("form").submit(function(e) {
			e.preventDefault();
		});
		// 去除绑定update事件
		$("#modalForm button[type='submit']").unbind();
		// $("#modalForm button[type='submit']").on('click', update);
		$("#responsive").modal('show');

	}

	/* 隐藏 */
	var modalHide = function() {
		$("#responsive").modal('hide');
		$("div.modal-backdrop").remove();
		$('.ms-container').remove();
		$('#my_multi_select1').empty();
		// 重设表单
		$("#modalForm").each(function() {
			this.reset();
		});
	}

	// 获取对信息
	var get = function(id) {
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
	// 获取选中的selected 返回字符串 5，2，3
	var getSelected = function() {

		var resourceLi = $(".ms-selection .ms-selected");
		var ids = "";
		$.each(resourceLi, function(index, resource) {

			if (resourceLi.length == index + 1) {
				ids += $(resource).attr("resourceid");
			} else {
				ids += $(resource).attr("resourceid") + ",";
			}
		});
		return ids;

	}

	var searchFormSubmit = function() {
		var url = mlx.ctx + "/admin/authority/loadResource";
		$("#searchFormbotton").on("click", function(e, data) {
			var data = $("#searchForm").serialize();
			$.ajax({
				url : url,
				type : "get",
				data : data,
				success : function(data) {
					$("#mainTable").empty();
					$("#mainTable").append(data);
				},
				error : function(e) {
					comm.showMsg('error', '消息提示', '加载失败！');
				}
			});

		})

	}

	//提交修改
	var updateSelected = function() {
		var url = mlx.ctx + "/admin/authority/setResource"
		var resourceIds = getSelected();
		var authorityId = getSelectedAuthorityId();
		$.ajax({
			url : url,
			type : 'post',
			dataType:"json",
			data : {
				'resourceIds' : resourceIds,
				'authorityId' : authorityId
			},
			success : function(r) {
				if (r.code == "200") {
					modalHide();
					//刷新表单
					UITree.reloadTable(authorityId,"","");
					comm.showMsg('success', '消息提示', '更新成功！');
				} else {
					modalHide();
					comm.showMsg('error', '消息提示', '更新失败！');
				}
			},
			error : function(e) {
				modalHide();
				comm.showMsg('error', '消息提示', '网络出错');
			}

		});

	}

	// 全选函数
	var selectAll = function() {

		$("thead tr th input[type='checkbox']").on("click", function(event) {
			// console.log(event);
			//console.log(event.currentTarget.checked);
			if (event.currentTarget.checked == true) {
				$("tbody tr input[type='checkbox']").prop("checked", true);
			}

			if (event.currentTarget.checked == false) {
				$("tbody tr input[type='checkbox']").prop("checked", false);
			}
		});
	}

	// 获取选 中的权限树Id
	var getSelectedAuthorityId = function() {
		nodeArray = $('#tree_3').jstree('get_selected')[0];
		//console.log(nodeArray);
		return nodeArray;

	}

	//多选刷新
	var multi_refresh = function() {
		$(".fa-refresh").click(function() {

			$('#my_multi_select1').multiSelect('refresh');
			return false;
		});
	}
	/*//多选全选*/
	var multi_selectAll = function() {
		$('.fa-indent').click(function() {
			$('#my_multi_select1').multiSelect('select_all');
			return false;
		});
	}
	/*取消全选*/
	var multi_deselectAll = function() {
		$('.fa-outdent').click(function() {
			$('#my_multi_select1').multiSelect('deselect_all');
			return false;
		});
	}

	/*多选加载数据*/
	var multi_loadData = function() {
		var url = mlx.ctx + "/admin/resource/all";
		/* 选择已选的树 */
		var authorityId = getSelectedAuthorityId();

		$.ajax({
			url : url,
			type : 'get',
			dataType:"json",
			data : {
				'authorityId' : authorityId
			},
			async : false,
			success : function(result) {
				//console.log(result.result);
				if (result.code == "200") {
					data = result.result;
					$('#my_multi_select1').empty();
					var options = "";
					// 拼接option
					$.each(data, function(index, option) {
						options += "<option ";
						if (option.flag == 1) {
							options += "selected ";
						}
						options += " resourceId='" + option.id + "'>"
								+ option.name + "</option>";

					})

					$('#my_multi_select1').append(options);
				} else {
					return false;
				}
			},
			error : function(e) {
				$("#message").html("请求出错！");
			}
		});

	}
	/*多选生成*/
	var handleMultiSelect = function() {
		/** 加载数据资源* */
		multi_loadData();
		/* 宣染树 */
		$('#my_multi_select1').multiSelect('destroy');
		$('#my_multi_select1')
				.multiSelect(
						{
							selectableFooter : "<div class='custom-header'>未选资源</div>",
							selectionFooter : "<div class='custom-header'>已选资源</div>",
							selectableHeader : "<input type='text' class='search-input ' autocomplete='off' placeholder='try \"搜索名称\"'>",
							selectionHeader : "<input type='text' class='search-input ' autocomplete='off' placeholder='try \"搜索名称\"'>",
							afterInit : function(ms) {
								var that = this, $selectableSearch = that.$selectableUl
										.prev(), $selectionSearch = that.$selectionUl
										.prev(), selectableSearchString = '#'
										+ that.$container.attr('id')
										+ ' .ms-elem-selectable:not(.ms-selected)', selectionSearchString = '#'
										+ that.$container.attr('id')
										+ ' .ms-elem-selection.ms-selected';

								that.qs1 = $selectableSearch.quicksearch(
										selectableSearchString).on('keydown',
										function(e) {
											if (e.which === 40) {
												that.$selectableUl.focus();
												return false;
											}
										});

								that.qs2 = $selectionSearch.quicksearch(
										selectionSearchString).on('keydown',
										function(e) {
											if (e.which == 40) {
												that.$selectionUl.focus();
												return false;
											}
										});
							},
							afterSelect : function() {
								this.qs1.cache();
								this.qs2.cache();
							},
							afterDeselect : function() {
								this.qs1.cache();
								this.qs2.cache();
							}

						});

	}
	
	return {
		selectAll : function() {
			selectAll();
		},
		
		addShow : function() {
			addShow();

		},
		searchFormSubmit : function() {
			searchFormSubmit();

		},
		getSelectedAuthorityId:function(){
			
			return getSelectedAuthorityId();
		},
		init : function() {
			$(".addShow").on("click", function(event) {
				addShow();
			});
			selectAll();
		},

		multi_init : function() {
			multi_refresh();
			multi_selectAll();
			multi_deselectAll();
		}
	};

}();

jQuery(document).ready(function() {
	var renameUrl = mlx.ctx + "/admin/authority/update";
	var createUrl = mlx.ctx + "/admin/authority/create";
	var deleteUrl = mlx.ctx + "/admin/authority/delete";
	var moveUrl = mlx.ctx + "/admin/authority/update";
	var copyUrl = mlx.ctx + "/admin/authority/copy";
	var treeUrl = mlx.ctx + "/admin/authority/Alltree";

	// 绑定按钮
	UITree.copyNode(copyUrl);
	UITree.moveNode(moveUrl);
	UITree.reNameNode(renameUrl);
	UITree.createNode(createUrl);
	UITree.deleteNode(deleteUrl);
	UITree.init(treeUrl);
	UITree.clicked();
	UITree.searchFormSubmit();
	UImultiSelect.init();
	UImultiSelect.multi_init();
	/*
	 * //选中树 var nodeId=$("#authorityId").val(); console.log(nodeId);
	 * $.jstree.reference('#tree_3').select_node(nodeId);
	 */

});
