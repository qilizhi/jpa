var comm = {
			getSysMsg:function(uid){
				//获取系统的消息
			},
			getGuideSysMsg:function(uid){
				//获取导游系统的消息
			},	
			initCheck:function(){
				//初始化全选或者反选
				$("input.check-all").on("click",function(e){
					var _b = $(this).parent().hasClass("checked");
					var $chks = $("input.check-all-item").parent();
					if(!_b){
						//$chks.addClass("checked");
					}else{
						$chks.removeClass("checked");
					}
				});
			},
			initTableDetail:function(){
				$(".table-scrollable table.table>tbody>tr.expand>td:first-child").on("click",function(e){
					$(this).parent().toggleClass("parent").next("tr.child").toggle();
				});
			},
			initSort:function(){
				//初始化序号修改
				$("td.ext-sort").on("click",function(e){
					var $this = $(this);
					var _url = $this.data("href");
					if($this.find("input").length > 0){
						return;
					}
					if(_url == undefined || !_url){
						return;
					}
					var _content = $this.text();
					$this.html('<input type="text" value="'+_content+'" style="width:80px;" />');
					$this.find("input").on("blur",function(e){
						console.log($(this).val());
						//异步修改
						$.post(_url,{sort:$(this).val()},function(result){
							console.log(result);
						});
					});
				});
			},
			initValidateForm:function(){
				if($.validate){
					$('form.mlx-form').validate({
						submitHandler:function(form) {
							$(form).ajaxSubmit();
						}
					});
				}
			},
			initAjaxForm:function(){
				//初始化表单异步提交
				var $this = this;
				if($.validate){
					$('form.mlx-form').validate({
						submitHandler: function (form) {
				            var $form = $(form),
				            _data = $form.serializeArray();//序列化表单数据
							var $btn = $("button[type=submit]",$form).button('loading');
							$.post($form.attr("action"),_data,function(result){
								if(result.code === 200){
									$this.successMsg("操作成功");
									setTimeout(function(){
										window.location.href = window.location.href;
									},2000);
								}else if(result.code === -1){
									$this.errorMsg("抱歉,操作失败,请重试!");
									$btn.button('reset');
								}
							});
							
						}
					});
				}

			},
			initDel:function(){
				var $this = this;
				//删除
				$("a[target=delete]").on("click",function(e){
					e.preventDefault();
					var url = $(this).attr("href");	
					$this.confirm("提示", "此操作不可逆,您确定要继续进行删除操作吗？", function(){
						$.get(url,function(result){
							if(result.code === 200){
								$this.successMsg("删除操作成功");
								setTimeout(function(){
									window.location.href = window.location.href;
								},2000);
							}else if(result.code === -1){
								$this.errorMsg("抱歉,删除操作失败,请重试!");
							}						
						});
					});								
				});
				
				//批量删除
				$("a[target=deletes]").on("click",function(e){
					e.preventDefault();
					var url = $(this).attr("href");	
					//获取
					var _params = "";
					$("td>div.checker>span.checked>input").each(function(i,item){
						_params += "," + $(item).val();
					});
					_params = _params.replace(",", "");
					if(_params == "" || _params.length <= 0){
						$this.infoMsg("请选择需要删除的数据!");
						return;
					}
					$this.confirm("提示", "此操作不可逆,您确定要继续进行删除操作吗？", function(){
						$.post(url,{ids:_params},function(result){
							if(result.code === 200){
								$this.successMsg("删除操作成功");
								setTimeout(function(){
									window.location.href = window.location.href;
								},2000);
							}else if(result.code === -1){
								$this.errorMsg("抱歉,删除操作失败,请重试!");
							}						
						});
					});								
				});
				
			},
			confirm:function(title,content,func){
				var $modal = $("#basic-modals");
				func = func || function(e){$modal.modal("hide");};
				$modal.modal("show");
				$modal.find("h4.modal-title").html(title || "提示");
				$modal.find(".modal-body").html(content || "是否继续此操作?");
				$modal.find("button.green").one("click",function(e){
					$modal.modal("hide");
					func();
				});
			},
			showMsg:function (type, title, msg) {
				//消息提示type :success,info,warning,error
				toastr.clear();
				toastr.options.positionClass = "toast-top-center";
				toastr[type](title, msg);
			},
			successMsg:function (msg) {
				//消息提示type :success,info,warning,error
				toastr.clear();
				toastr.options.positionClass = "toast-top-center";
				toastr["success"]("成功", msg);
			},
			infoMsg:function (msg) {
				//消息提示type :success,info,warning,error
				toastr.clear();
				toastr.options.positionClass = "toast-top-center";
				toastr["info"]("提示", msg);
			},
			warningMsg:function (msg) {
				//消息提示type :success,info,warning,error
				toastr.clear();
				toastr.options.positionClass = "toast-top-center";
				toastr["warning"]("警告", msg);
			},
			errorMsg:function (msg) {
				//消息提示type :success,info,warning,error
				toastr.clear();
				toastr.options.positionClass = "toast-top-center";
				toastr["error"]("错误", msg);
			},
			init:function(){
				this.initTableDetail();
				this.initDel();
				this.initCheck();
				this.initSort();
				this.initAjaxForm();
			}
	};

//设置jQuery Ajax全局的参数  
$.ajaxSetup({
	error : function(jqXHR, textStatus, errorThrown) {
		switch (jqXHR.status) {
		case (500):
			comm.errorMsg("服务器系统内部错误");
			break;
		case (401):
			comm.errorMsg("未登录");
			break;
		case (403):
			comm.errorMsg("无权限执行此操作");
			break;
		case (408):
			comm.errorMsg("请求超时");
			break;
		default:
			comm.errorMsg("未知错误");
		}
	}
});

comm.init();

//图片放大预览,使用方式,在img标签上面,添加 data-preview='true'
$(function(){
	
/*	//修改TAB为hover切换 begin
	$("ul.navbar-nav>li").off("click");
	$("ul.navbar-nav >li.dropdown-fw:not(.open)").hover(function(){
		$("ul.navbar-nav >li.open>ul").hide();
		$(this).addClass("open").children("a").css({"background":"#009dc7","color":"white"});
	},function(){
		$("ul.navbar-nav >li.open>ul").show();
		$(this).removeClass("open").children("a").removeAttr("style");
	});
	//修改TAB为hover切换 end
*/	
   var $img_list=$("img[data-preview]");
	$img_list.css("cursor", "pointer")
  $img_list.on("click",function(){
  $(".js-prewimg").remove();
   var img=document.createElement("img");
   img.className="img-thumbnail js-prewimg";
   img.src=this.src;
   var cssJson={"position":"fixed","top":"40%","left":"50%","width":"400px","zIndex":"999","marginLeft":"-200px", "boxShadow":"0 2px 10px rgba(0, 0, 0, .5), 0 2px 3px rgba(0, 0, 0, .5)"}
   var $img=$(img);
    $img.css(cssJson);
    document.body.appendChild(img);    
})
document.onclick=function(e){
  if(!(e.target.className=='img-thumbnail js-prewimg'||e.target.getAttribute("data-preview")!=null)){
	  $(".js-prewimg").remove();
  };
}
});