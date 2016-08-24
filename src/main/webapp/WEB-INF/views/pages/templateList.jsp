<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="style" value="http://p.missfresh.cn/meituanHtml" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">  
		<title>优惠券管理</title>
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
									红包模版列表
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<div class="alert alert-info" >
									<div class="input-group">
									模版ID:
									<input id="id" type="text" class="" placeholder="请输入模版ID" />
										&nbsp;&nbsp;&nbsp;&nbsp;
									模版描述:
									<input id="template_des" type="text" class="" placeholder="请输入模版描述" />
										&nbsp;&nbsp;&nbsp;&nbsp;
									模版类型:
									<input id="coupon_name" type="text" class="" placeholder="请输入模版类型" />
										&nbsp;&nbsp;&nbsp;&nbsp;
											<button id="find_btn" type="button" class="btn btn-purple">
												查找
												<i class="icon-search icon-on-right bigger-110"></i>
											</button>
									</div>
								</div>

								<table id="grid-table"></table>

								<div id="grid-pager"></div>

								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
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
		<%-- <script src="${style}/static/assets/js/jquery-2.0.3.min.js"></script>
		<script src="${style}/static/assets/js/bootstrap.min.js"></script>
		<script src="${style}/static/assets/js/jqGrid/jquery.jqGrid.min.js"></script>
		<script src="${style}/static/assets/js/jqGrid/i18n/grid.locale-ch.js"></script>
		<script src="${style}/static/assets/js/ace-elements.min.js"></script>
		<script src="${style}/static/assets/js/ace.min.js"></script>
		<script src="${style}/static/assets/js/ace-extra.min.js"></script> --%>
		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			jQuery(function($) {
				var grid_selector = "#grid-table";
				var pager_selector = "#grid-pager";
			
				jQuery(grid_selector).jqGrid({
					url: "${ctx}/RedPackage/getTemplate.do",
					datatype: "json",
					mtype: "GET",
					height: 500,
					colNames:['ID','广告图','模版描述','优惠券类型','操作'],
					colModel:[
						{name:'id',index:'id',search:false,sortable:false},
						{name:'img_url',index:'img_url',search:false,sortable:false,editable: true},
						{name:'template_des',index:'template_des',search:false,sortable:false,editable: true},
						{name:'coupon_name',index:'coupon_name',search:false,sortable:false,editable: true},
						{name:'Edit',index:'Edit',search:false,sortable:false}
						/* {name:'name',
							index:'name',
							formatter:"showlink", 
							formatoptions:{
								baseLinkUrl:"${ctx}/RedPackage/getTemplateById.do",
								idName:"id", addParam:"&name=123"
							}
						} */
						/* {name:'myac',index:'', width:80, fixed:true, sortable:false, resize:false,
							formatter:'actions', 
							formatoptions:{ 
								keys:true
								,delOptions:{recreateForm: true, beforeShowForm:beforeDeleteCallback},
								//editformbutton:true, editOptions:{recreateForm: true, beforeShowForm:beforeEditCallback}
							}
						} */
					], 
			
					viewrecords : true,
					rowNum:15,
					rowList:[15,20,30],
					pager : pager_selector,
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
							//updatePagerIcons(table);
							//enableTooltips(table);
						}, 0);
					},
					
					
					gridComplete : function() {
						var ids = jQuery("#grid-table").jqGrid('getDataIDs');
						for (var i = 0; i < ids.length; i++) {
							var id = ids[i];
							var rowData = $("#grid-table").getRowData(id);
							/* var editBtn = "<a href='#' style='color:#f60' onclick='edit(\""
									+ id + "\")' >编辑</a>";//此处会将点击行id传给_edit(id) js函数 */
							var editBtn = '<button type="button" onclick="updateById('
									+ id + ')" class="btn btn-info">编辑<i class="icon-pencil icon-on-right"></i></button>';		
							jQuery("#grid-table").jqGrid(
									'setRowData', ids[i], {
									Edit : editBtn
							});
						}
					},

									//editurl: "${ctx}/RedPackage/updateTemplate.do",
									caption : "红包模版列表",
									autowidth : true

								});

				$("#find_btn").click(function() {
					var id = $("#id").val();
					var template_des = $("#template_des").val();
					var coupon_name = $("#coupon_name").val();
					jQuery(grid_selector).jqGrid('setGridParam', {
						url : "${ctx}/RedPackage/getTemplate.do",
						postData : {
							'id' : id,
							'template_des' : template_des,
							'coupon_name' : coupon_name
						}
					//发送数据 
					}).trigger("reloadGrid"); //重新载入 
				});

				//navButtons
				jQuery(grid_selector).jqGrid(
						'navGrid',
						pager_selector,
						{ //navbar options
							edit : false,
							editicon : 'icon-pencil blue',
							add : false,
							addicon : 'icon-plus-sign purple',
							del : false,
							delicon : 'icon-trash red',
							search : false,
							searchicon : 'icon-search orange',
							refresh : true,
							refreshicon : 'icon-refresh green',
							view : true,
							viewicon : 'icon-zoom-in grey'
						},
						{
							//delete record form
							recreateForm : true,
							beforeShowForm : function(e) {
								var form = $(e[0]);
								if (form.data('styled'))
									return false;

								form.closest('.ui-jqdialog').find(
										'.ui-jqdialog-titlebar').wrapInner(
										'<div class="widget-header" />')
								style_delete_form(form);

								form.data('styled', true);
							},
							onClick : function(e) {
								alert(1);
							}
						},
						{
							//view record form
							recreateForm : true,
							beforeShowForm : function(e) {
								var form = $(e[0]);
								form.closest('.ui-jqdialog').find(
										'.ui-jqdialog-title').wrap(
										'<div class="widget-header" />');
							}
						});

			});

			function updateById(id) {
				window.open("${ctx}/RedPackage/getById.do?id="+id);
			};
		</script>
</body>
</html>