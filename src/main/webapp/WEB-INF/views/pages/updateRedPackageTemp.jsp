<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set value="${requestScope.template}" var="template"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">  
		<title>优惠券管理</title>
		<!-- basic styles -->
		<link rel="stylesheet" href="${ctx}/static/assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${ctx}/static/assets/css/font-awesome.min.css" />
		<!-- page specific plugin styles -->
		<link rel="stylesheet" href="${ctx}/static/assets/css/jquery-ui-1.10.3.full.min.css"/>
		<link rel="stylesheet" href="${ctx}/static/assets/css/ace.min.css"/>
		<link rel="stylesheet" href="${ctx}/static/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="${ctx}/static/assets/css/ace-skins.min.css" />
		<link rel="stylesheet" href="${ctx}/static/assets/css/colorpicker.css" />
		<link rel="stylesheet" href="${ctx}/static/assets/css/chosen.css" />
		<link rel="stylesheet" href="${ctx}/static/assets/css/datepicker.css" />
		<link rel="stylesheet" href="${ctx}/static/assets/css/bootstrapValidator.css"/>
	</head>

	<body>
	<!-- Modal -->
<div class="modal fade" id="msgModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel" style="color:#307ecc;">提示信息</h4>
      </div>
      <div class="modal-body">
       	<span id='msg'></span>
      </div>
    </div>
  </div>
</div>
		
		<div class="main-container" id="main-container">

					<div class="page-content">
					<div class="page-header">
						<h1>
							优惠券管理
							<small>
								<i class="icon-double-angle-right"></i>
								编辑红包模版
							</small>
						</h1>
					</div><!-- /.page-header -->
						<form  id="myform"  class="form-horizontal" role="form">
						<div class="alert alert-info">
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="id"> 模版ID:</label>

								<div class="col-lg-2">
									<input type="text" id="id" name="id" readonly="readonly" class="form-control" value="${template.id}"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="template_des"> 模版描述:</label>

								<div class="col-lg-2">
									<input type="text" id="template_des" name="template_des" class="form-control" value="${template.template_des}"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="template_url"> 模版链接:</label>

								<div class="col-lg-2">
									<input type="text" id="template_url" name="template_url" class="form-control" value="${template.template_url}"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="img_url"> 广告图片:</label>
								<div class="col-lg-2">
									<input multiple="" type="file" id="id-input-file-3" name="img_url1" />
									<label>
										<input type="checkbox" name="file-format" id="id-file-format" class="ace" />
									</label>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="advertisement_url"> 广告链接:</label>

								<div class="col-lg-2">
									<input type="text" id="advertisement_url" name="advertisement_url" class="form-control" value="${template.advertisement_url}"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="coupon_code"> 优惠券:</label>

								<div class="col-lg-2">
									<input type="text" id="coupon_code" name="coupon_code" class="form-control" value="${template.coupon_code}"/>
								</div>
							</div>
						</div>

							<div class="clearfix form-actions">
								<div class="col-md-offset-3 col-md-9">
									<button id="submitForm" class="btn btn-info" type="button">
										<i class="icon-ok bigger-110"></i>
										提交
									</button>

									&nbsp; &nbsp; &nbsp;
									<button id="resetForm" class="btn" type="reset">
										<i class="icon-undo bigger-110"></i>
										重置
									</button>
								</div>
							</div>
						</form>
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->

		<!-- basic scripts -->
		<script src="${ctx}/static/assets/js/jquery-2.0.3.min.js"></script>
		<script src="${ctx}/static/assets/js/bootstrap.min.js"></script>
		<script src="${ctx}/static/assets/js/typeahead-bs2.min.js"></script>
		<script src="${ctx}/static/assets/js/ace-elements.min.js"></script>
		<script src="${ctx}/static/assets/js/ace.min.js"></script>
		<script src="${ctx}/static/assets/js/form/bootstrapValidator.js"></script>
		<script src="${ctx}/static/assets/js/modal.js"></script>
		<script src="${ctx}/static/assets/js/ace-extra.min.js"></script>
		<!-- inline scripts related to this page -->
		<script>
		jQuery(function($) {
			
			$('#myform').bootstrapValidator({
	            message: 'This value is not valid',
	            feedbackIcons: {
	                valid: 'glyphicon glyphicon-ok',
	                invalid: 'glyphicon glyphicon-remove',
	                validating: 'glyphicon glyphicon-refresh'
	            },
	            fields: {
	                'template_des': {
	                	validators: {
	                        notEmpty: {
	                            message: '模版描述不能为空.'
	                        }
	                    }
	                },
	                'template_url': {
	                	validators: {
	                        notEmpty: {
	                            message: '模版链接不能为空.'
	                        }
	                    }
	                },
	                'img_url': {
	                    validators: {
	                        notEmpty: {
	                            message: '广告图片不能为空.'
	                        }
	                    }
	                },
	                'advertisement_url': {
	                	validators: {
	                        notEmpty: {
	                            message: '广告链接不能为空.'
	                        }
	                    }
	                },
	                'coupon_code': {
	                	validators: {
	                        notEmpty: {
	                            message: '优惠券不能为空.'
	                        }
	                    }
	                }
	            }
	        })
	        .on('success.form.bv', function(e) {
            	$.ajax({
	                cache: false,
	                type: "POST",
	                url: "${ctx}/RedPackage/update.do",
	                data:$('#myform').serialize(),// 你的formid
	                async: false,
	                error: function(request) {
	                	$("#msg")[0].innerHTML = "保存失败!";
						$('#msgModal').modal();
	                },
	                success: function(data) {
	                	$("#msg")[0].innerHTML = data.successMsg;
						$('#msgModal').modal();
						if(data.operationResult=="success"){
	                		setTimeout('window.close()',3000);
	                	}
	                }
	            });
            });
			
			$("#submitForm").click(function(){ 
				$('#myform').bootstrapValidator('validate');
		    });
			$('#resetForm').click(function() {
		        $('#myform').data('bootstrapValidator').resetForm(true);
		    });
			
			$('#id-input-file-3').ace_file_input({
				style:'well',
				btn_choose:'请选择广告图片',
				btn_change:null,
				no_icon:'icon-cloud-upload',
				droppable:true,
				thumbnail:'small'//large | fit
				//,icon_remove:null//set null, to hide remove/reset button
				/**,before_change:function(files, dropped) {
					//Check an example below
					//or examples/file-upload.html
					return true;
				}*/
				/**,before_remove : function() {
					return true;
				}*/
				,
				preview_error : function(filename, error_code) {
					//name of the file that failed
					//error_code values
					//1 = 'FILE_LOAD_FAILED',
					//2 = 'IMAGE_LOAD_FAILED',
					//3 = 'THUMBNAIL_FAILED'
					//alert(error_code);
				}
		
			}).on('change', function(){
				//console.log($(this).data('ace_input_files'));
				//console.log($(this).data('ace_input_method'));
			});
			
		
			//dynamically change allowed formats by changing before_change callback function
			$('#id-file-format').removeAttr('checked').on('change', function() {
				var before_change
				var btn_choose
				var no_icon
				if(this.checked) {
					btn_choose = "Drop images here or click to choose";
					no_icon = "icon-picture";
					before_change = function(files, dropped) {
						var allowed_files = [];
						for(var i = 0 ; i < files.length; i++) {
							var file = files[i];
							if(typeof file === "string") {
								//IE8 and browsers that don't support File Object
								if(! (/\.(jpe?g|png|gif|bmp)$/i).test(file) ) return false;
							}
							else {
								var type = $.trim(file.type);
								if( ( type.length > 0 && ! (/^image\/(jpe?g|png|gif|bmp)$/i).test(type) )
										|| ( type.length == 0 && ! (/\.(jpe?g|png|gif|bmp)$/i).test(file.name) )//for android's default browser which gives an empty string for file.type
									) continue;//not an image so don't keep this file
							}
							
							allowed_files.push(file);
						}
						if(allowed_files.length == 0) return false;
		
						return allowed_files;
					}
				}
				else {
					btn_choose = "Drop files here or click to choose";
					no_icon = "icon-cloud-upload";
					before_change = function(files, dropped) {
						return files;
					}
				}
				var file_input = $('#id-input-file-3');
				file_input.ace_file_input('update_settings', {'before_change':before_change, 'btn_choose': btn_choose, 'no_icon':no_icon})
				file_input.ace_file_input('reset_input');
			});
			
		});
		
		</script>
		
</body>
</html>