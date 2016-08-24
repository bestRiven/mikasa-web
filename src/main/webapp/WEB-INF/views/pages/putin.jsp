<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="style" value="http://p.missfresh.cn/meituanHtml/" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">  
		<title>美团外卖管理</title>
		<link rel="stylesheet" href="${ctx}/static/assets/css/bootstrap.min.css"/>
		<link rel="stylesheet" href="${ctx}/static/assets/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${ctx}/static/assets/css/jquery-ui-1.10.3.full.min.css"/>
		<link rel="stylesheet" href="${ctx}/static/assets/css/datepicker.css" />
		<link rel="stylesheet" href="${ctx}/static/assets/css/ui.jqgrid.css" />
		<link rel="stylesheet" href="${ctx}/static/assets/css/ace.min.css" />
		<%-- <link rel="stylesheet" href="${style}/static/assets/css/bootstrap.min.css"/>
		<link rel="stylesheet" href="${style}/static/assets/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${style}/static/assets/css/jquery-ui-1.10.3.full.min.css"/>
		<link rel="stylesheet" href="${style}/static/assets/css/datepicker.css" />
		<link rel="stylesheet" href="${style}/static/assets/css/ui.jqgrid.css" />
		<link rel="stylesheet" href="${style}/static/assets/css/ace.min.css" /> --%>
	</head>

	<body>
	<!-- Modal data-backdrop="static"必须点击关闭按钮关闭-->
<div class="modal fade" id="stopModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
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
									红包投放设置
								</small>
							</h1>
						</div><!-- /.page-header -->

			<div class="row">
				<div class="">
					<div class="tabbable">
						<ul class="nav nav-tabs padding-20 tab-color-blue background-blue" id="myTab">
							<li class="active">
								<a data-toggle="tab" href="#huabei">华北</a>
							</li>

							<li>
								<a data-toggle="tab" href="#huanan">华南</a>
							</li>

							<li>
								<a data-toggle="tab" href="#huadong">华东</a>
							</li>
						</ul>

						<div class="tab-content">
							<div id="huabei" class="tab-pane fade in active" style="height: 295px;">
								<p>Raw denim you probably haven't heard of them jean shorts Austin.</p>
								<p>Raw denim you probably haven't heard of them jean shorts Austin.</p>
								<p>Raw denim you probably haven't heard of them jean shorts Austin.</p>
								<p>Raw denim you probably haven't heard of them jean shorts Austin.</p>
								<p>Raw denim you probably haven't heard of them jean shorts Austin.</p>
								<p>Raw denim you probably haven't heard of them jean shorts Austin.</p>
								<p>Raw denim you probably haven't heard of them jean shorts Austin.</p>
								<p>Raw denim you probably haven't heard of them jean shorts Austin.</p>
								<p>Raw denim you probably haven't heard of them jean shorts Austin.</p>
								<p>Raw denim you probably haven't heard of them jean shorts Austin.</p>
							</div>

							<div id="huanan" class="tab-pane fade" style="height: 295px;">
								<table id="grid-table2"></table>
								<div id="grid-pager2"></div>
							</div>

							<div id="huadong" class="tab-pane fade" style="height: 295px;">
								<p>Etsy mixtape wayfarers, ethical wes anderson tofu before they sold out mcsweeney's organic lomo retro fanny pack lo-fi farm-to-table readymade.</p>
							</div>
						</div>
					</div>
				</div><!-- /span -->
			</div>
			<!-- /row -->
		</div><!-- /.page-content -->
				</div><!-- /.main-content -->

		<!-- basic scripts -->
		<script src="${ctx}/static/assets/js/jquery-2.0.3.min.js"></script>
		<script src="${ctx}/static/assets/js/bootstrap.min.js"></script>
		<script src="${ctx}/static/assets/js/jqGrid/jquery.jqGrid.min.js"></script>
		<script src="${ctx}/static/assets/js/jqGrid/i18n/grid.locale-ch.js"></script>
		<script src="${ctx}/static/assets/js/ace-elements.min.js"></script>
		<script src="${ctx}/static/assets/js/ace.min.js"></script>
		<script src="${ctx}/static/assets/js/ace-extra.min.js"></script>
		<!-- inline scripts related to this page -->
		<script type="text/javascript">
		/* $(function () {
			// 通过名称选取标签页
			$('#myTab a[href="#huanan"]').tab('show')
			 
			// 选取第一个标签页
			$('#myTab a:first').tab('show') 

			// 选取最后一个标签页
			$('#myTab a:last').tab('show') 

			// 选取第三个标签页（从 0 开始索引）
			$('#myTab li:eq(2) a').tab('show')
		}) */
		
		jQuery(function($) {
			var grid_selector2 = "#grid-table2";
			var pager_selector2 = "#grid-pager2";
		
			jQuery(grid_selector2).jqGrid({
				url: "${ctx}/RedPackage/getTemplate.do",
				datatype: "json",
				mtype: "GET",
				//height: 500,
				colNames:['ID','广告图','模版描述','模版类型','操作'],
				colModel:[
					{name:'id',index:'id',search:false,sortable:false},
					{name:'img_url',index:'img_url',search:false,sortable:false,editable: true},
					{name:'template_des',index:'template_des',search:false,sortable:false,editable: true},
					{name:'coupon_name',index:'coupon_name',search:false,sortable:false,editable: true},
					{name:'myac',index:'', width:80, fixed:true, sortable:false, resize:false,
						formatter:'actions', 
						formatoptions:{ 
							keys:true
							,delOptions:{recreateForm: true, beforeShowForm:beforeDeleteCallback},
							//editformbutton:true, editOptions:{recreateForm: true, beforeShowForm:beforeEditCallback}
						}
					}
				], 
		
				viewrecords : true,
				rowNum:5,
				rowList:[5,10,20],
				pager : pager_selector2,
				altRows: true,
				//toppager: true,
				//sortable:true,
				//multiselect: true,
				//multikey: "ctrlKey",
		        //multiboxonly: true,
		
		        jsonReader: {
		        	root: "list",
		        	records: "count",
		        	total:"total",
		        	repeatitems: false
		        	},
		        	
				loadComplete : function() {
					var table = this;
					setTimeout(function(){
						//styleCheckbox(table);
						//updateActionIcons(table);
						updatePagerIcons(table);
						//enableTooltips(table);
					}, 0);
				},
		
				editurl: "${ctx}/RedPackage/updateTemplate.do",
				caption: "红包投放设置",
				autowidth: true
		
			});
			
			//navButtons
			jQuery(grid_selector2).jqGrid('navGrid',pager_selector2,
				{ 	//navbar options
					edit: false,
					editicon : 'icon-pencil blue',
					add: false,
					addicon : 'icon-plus-sign purple',
					del: false,
					delicon : 'icon-trash red',
					search: false,
					searchicon : 'icon-search orange',
					refresh: true,
					refreshicon : 'icon-refresh green',
					view: true,
					viewicon : 'icon-zoom-in grey'
				},
				{
					//delete record form
					recreateForm: true,
					beforeShowForm : function(e) {
						var form = $(e[0]);
						if(form.data('styled')) return false;
						
						form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
						style_delete_form(form);
						
						form.data('styled', true);
					},
					onClick : function(e) {
						alert(1);
					}
				},
				{
					//view record form
					recreateForm: true,
					beforeShowForm: function(e){
						var form = $(e[0]);
						form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />');
					}
				}
			);
		
			function updatePagerIcons(table) {
				var replacement = 
				{
					'ui-icon-seek-first' : 'icon-double-angle-left bigger-140',
					'ui-icon-seek-prev' : 'icon-angle-left bigger-140',
					'ui-icon-seek-next' : 'icon-angle-right bigger-140',
					'ui-icon-seek-end' : 'icon-double-angle-right bigger-140'
				};
				$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
					var icon = $(this);
					var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
					
					if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
				});
			}
			function beforeDeleteCallback(e) {
				var form = $(e[0]);
				if(form.data('styled')) return false;
				
				form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
				style_delete_form(form);
				
				form.data('styled', true);
			}
			
			function style_delete_form(form) {
				var buttons = form.next().find('.EditButton .fm-button');
				buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();//ui-icon, s-icon
				buttons.eq(0).addClass('btn-danger').prepend('<i class="icon-trash"></i>');
				buttons.eq(1).prepend('<i class="icon-remove"></i>')
			}
		});
			
		</script>
</body>
</html>