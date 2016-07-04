(function($){
	
	//图片和文件上传
	var fileUploadUtil = {
			
			init:function(options){
				options = $.extend({ 
			         selector :"#fileupload",
			         limit:function(data){return false;},//判断是否阻止上传,如果返回值是true,则阻止
			         submit:function(data){},//提交文件时触发
			         resultBefore:function(data){},
			         result:function(index,file,count){},//上传完成回调
			         startFun:function(e){},//上传完成回调
			         progress:function(progress){},//总上传进度
			         progressSigle:function(progress){},//单文件上传进度
			         cancel:"#cancel",
			         sequentialUploads: true,/*顺序上传*/
			         singleFileUploads: false,/*单个单个上传*/
			         maxNumberOfFiles:150,//限制最大上传文件数量
			         maxFileSize:1048576,//限制最大上传文件大小只允许单文件最大默认1MB。
			         limitedFileType:0,//0表示图片和文件都可以上传,1表示只能上传图片,2表示只能上传非图片的符合条件的文件
			         auto:true,start:"#start"},options);
		if(options.limitedFileType == 1){
			options.maxFileSize = 1048576;//限制最大上传图片文件大小只允许单文件最大默认1MB。
		}
	    var $pattern;
	    if(options.limitedFileType === 0){
	    	$pattern = /(\.|\/)(gif|jpe?g|png|zip|rar|pdf|tar.gz|xls|xlsx|doc|docx)$/i;
	    }else if(options.limitedFileType === 1){
	    	$pattern = /(\.|\/)(gif|jpe?g|png)$/i;
	    }else if(options.limitedFileType === 2){
	    	$pattern = /(\.|\/)(zip|rar|pdf|tar.gz|xls|xlsx|doc|docx)$/i;
	    }else if(options.limitedFileType === 3){
	    	$pattern = /(\.|\/)(mp4|flv|ogg|f4v|webm)$/i;
	    }else if(options.limitedFileType === 4){
	    	$pattern = /(\.|\/)(jpe?g|png)$/i;
	    }else{
	    	$pattern = /(\.|\/)(gif|jpe?g|png|zip|rar|pdf|tar.gz|xls|xlsx|doc|docx)$/i;
	    }
	    var $url = $(options.selector).attr("data-url");
	    if($.trim($url).length <= 0){
	    	$url = window.g_basePath + '/upload';
	    }
		var jqXHR = $(options.selector).fileupload({
			        dataType: 'json',
			        url:$url,
		            // Enable image resizing, except for Android and Opera,
		            // which actually support image resizing, but fail to
		            // send Blob objects via XHR requests:
		            disableImageResize: /Android(?!.*Chrome)|Opera/.test(window.navigator.userAgent),
		            maxNumberOfFiles:options.maxNumberOfFiles,
		            maxFileSize: options.maxFileSize,
			        sequentialUploads: options.sequentialUploads,/*顺序上传*/
			        singleFileUploads: options.singleFileUploads,/*单个上传*/
		            acceptFileTypes: $pattern,
			        add: function (e, data) {
			        	if(options.limit(data)){
			        		return;
			        	}
			        	if(options.auto){
			        		//自动上传
			        		data.submit().fail(function () {
		                        $('<div class="alert alert-danger"/>')
		                        .text("抱歉当前服务器无法响应")
		                        .appendTo(options.selector);
		                    });
			        	}else{
			        		//手动上传
			        		data.context = $(options.start).click(function () {
			                    data.context = $('<p/>').text('正在上传中...').replaceAll($(this));
			                    data.submit().fail(function () {
			                        $('<div class="alert alert-danger"/>')
			                        .text("抱歉当前服务器无法响应")
			                        .appendTo(options.selector);
			                    });
			                });
			        	}
			        },
			        submit:function(e, data){
			        	options.submit.call($(e.target),data);
			        },
			        progressall: function (e, data) {
			        	//进度
			            var progress = parseInt(data.loaded / data.total * 100, 10);
			            options.progress.call($(e.target),progress);
			            //$('#progress .bar').css('width',progress + '%');
			        },
			        progress:function(e, data){
			        	var progress = parseInt(data.loaded / data.total * 100, 10);
			            options.progressSigle.call($(e.target),progress);
			        },
			        done: function (e, data) {
			        	console.log(data);
			        	var $target = $(e.target);
			        	options.resultBefore.call($target,data);
			        	//{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"}
			            //上传完成
			            $.each(data.result.result, function (index, file) {
			            	options.result.call($target,index,file,data.result.result.length);
			            }); 
			        },
			        drop: function (e, data) {
			        	//拖放文件
			            $.each(data.files, function (index, file) {
			                alert('Dropped file: ' + file.name);
			            });
			        },
			        change: function (e, data) {
			  
			        	var $OK = true;
			        	
			        	if(options.limit.call($(e.target),data)){
			        		return false;
			        	}
			        	//判断文件数量是否超出最大限制数量
			        	if(data.files.length > options.maxNumberOfFiles){
			        		comm.infoMsg('抱歉,选择的文件数量超出最大限制数'+options.maxNumberOfFiles+'个!');
			        		return false;
			        	}
			        	
			        	//改变选择的文件
			            $.each(data.files, function (index, file) {
			            	
			            	///文件大小判断
			            	if(file.size > options.maxFileSize){
								comm.infoMsg("您上传文件超过"+Math.ceil((options.maxFileSize/(1024*1024)))+"M!","");
			            		//alert("您上传文件超过"+Math.ceil((options.maxFileSize/(1024*1024)))+"M!","");
			            		$OK = false;
								return;
							}
			            	if(!$pattern.test(file.name.toString())){
			            		$OK = false;
			            		comm.infoMsg('抱歉,选择的文件类型不支持!');
			            		//alert('抱歉,选择的文件类型不支持!');
			            		return;
			            	}
			            });
			            
			            options.startFun.call(e.target,e);
			            
			            return $OK;
			        }
			    }).error(function (jqXHR, textStatus, errorThrown) {
			        if (errorThrown === 'abort') {
			        	comm.infoMsg('文件上传已经本取消');
			        	//alert('文件上传已经本取消');
			        }
			    });
				
				$(options.cancel).on("click",function(e){
					jqXHR.abort();
					e.preventDefault();
				});
			}
	};
	
	
	$.fn.customUpload = function(options){
		this.each(function(i,el){
			options.selector = el;
			fileUploadUtil.init(options);
		})
	};	
	
})(jQuery || $);