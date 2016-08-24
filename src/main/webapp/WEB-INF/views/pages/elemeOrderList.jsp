<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="style" value="http://p.missfresh.cn/meituanHtml/" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">  
		<title>饿了么管理</title>
		<link rel="stylesheet" href="${ctx}/static/assets/css/bootstrap.min.css"/>
		<link rel="stylesheet" href="${ctx}/static/assets/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${ctx}/static/assets/css/jquery-ui-1.10.3.full.min.css"/>
		<link rel="stylesheet" href="${ctx}/static/assets/css/datepicker.css" />
		<link rel="stylesheet" href="${ctx}/static/assets/css/ui.jqgrid.css" />
		<link rel="stylesheet" href="${ctx}/static/assets/css/ace.min.css" />
		<link rel="stylesheet" href="${ctx}/static/assets/json/jquery.jsonview.css" />
		<%-- <link rel="stylesheet" href="${style}/static/assets/css/bootstrap.min.css"/>
		<link rel="stylesheet" href="${style}/static/assets/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${style}/static/assets/css/jquery-ui-1.10.3.full.min.css"/>
		<link rel="stylesheet" href="${style}/static/assets/css/datepicker.css" />
		<link rel="stylesheet" href="${style}/static/assets/css/ui.jqgrid.css" />
		<link rel="stylesheet" href="${style}/static/assets/css/ace.min.css" /> --%>
	</head>

	<body>
	<!-- Modal data-backdrop="static"必须点击关闭按钮关闭-->
<div class="modal fade" id="msgModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel" style="color:#307ecc;">原始订单数据</h4>
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
								饿了么管理
								<small>
									<i class="icon-double-angle-right"></i>
									订单查询列表
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row" >
							
								<!-- PAGE CONTENT BEGINS -->

								<div class="alert alert-info" >
									<div class="input-group">
									订单号:
									<input id="order_id" type="text" class="" placeholder="请输入订单号" />
										&nbsp;&nbsp;&nbsp;&nbsp;
									门店ID:
									<input id="app_poi_code" type="text" class="" placeholder="请输入供门店ID" />
										&nbsp;&nbsp;&nbsp;&nbsp;
									收货人:
									<input id=consignee type="text" class="" placeholder="请输入收货人" />
										&nbsp;&nbsp;&nbsp;&nbsp;
									无坐标:
									<input id="have_no_coordinate" type="checkbox" class="" />
										&nbsp;&nbsp;&nbsp;&nbsp;
											<button id="find_btn" type="button" class="btn btn-purple">
												查找
												<i class="icon-search icon-on-right bigger-110"></i>
											</button>
										<!-- &nbsp;&nbsp;&nbsp;&nbsp;
											<button id="export" type="button" class="btn btn-inverse">
												导出
												<i class="icon-download-alt icon-on-right bigger-110"></i>
											</button> -->
											
									</div>
								</div>

								<table id="grid-table"></table>
							
								<div id="grid-pager"></div>
			
								<!-- PAGE CONTENT ENDS -->
							<div id="json"></div>
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
		<script src="${ctx}/static/assets/json/jquery.jsonview.js"></script>
	<%-- 	<script src="${style}/static/assets/js/jquery-2.0.3.min.js"></script>
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
					url: "${ctx}/eleme/order/getList.do",
					datatype: "json",
					mtype: "GET",
					height: 500,
					colNames:['订单号','下单时间','金额','门店ID','门店名称','收货人','收货人电话','收货地址'],
					colModel:[
						{name:'order_id',index:'order_id',search:false,sortable:false,width:180,
							formatter:customFmatter},
						{name:'created_at',index:'created_at',search:false,sortable:false,width:180,
							formatter:'date',
							formatoptions:{srcformat: 'Y-m-d H:i:s', newformat: 'Y-m-d H:i:s'}},
						{name:'total_price',index:'total_price',search:false,sortable:false},
						{name:'restaurant_id',index:'restaurant_id',search:false,sortable:false},
						{name:'restaurant_name',index:'restaurant_name',search:false,sortable:false},
						{name:'consignee',index:'consignee',search:false,sortable:false},
						{name:'phone',index:'phone',search:false,sortable:false},
						{name:'address',index:'address',search:false,sortable:false}
					], 
			
					viewrecords : true,
					rowNum: 15,
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
							updatePagerIcons(table);
							//enableTooltips(table);
						}, 0);
					},
			
					caption: "订单查询列表",
					autowidth: true
			
				});
				
				$("#find_btn").click(function(){ 
			        var order_id = $("#order_id").val(); 
			        var app_poi_code = $("#app_poi_code").val();
			        var consignee = $("#consignee").val();
			        var have_no_coordinate=$("#have_no_coordinate").get(0).checked;
			        jQuery(grid_selector).jqGrid('setGridParam',{
			            url:"${ctx}/eleme/order/getList.do", 
			            postData:{  'order_id':order_id,
					            	'app_poi_code':app_poi_code,
					            	'consignee':consignee,
					            	'have_no_coordinate':have_no_coordinate} //发送数据 
			        }).trigger("reloadGrid"); //重新载入 
			    });
				
				//navButtons
				jQuery(grid_selector).jqGrid('navGrid',pager_selector,
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
				
				
				function customFmatter(cellvalue, options, rowObject) {
					//console.log(cellvalue);
					//alert(cellvalue);
					//console.log(options);
					//console.log(rowObject);
					var order_id = cellvalue;
					return "<a onclick=\"viewInfo(\'"+order_id+"\')\">"+cellvalue+"</a>";

				}; 
				
			});
			
			function viewInfo(order_id){
				$.ajax({
	                cache: false,
	                type: "POST",
	                url: "${ctx}/eleme/order/show.do",
	                data:{'order_id':order_id},
	                error: function(request) {
	                	$("#msg")[0].innerHTML = "获取原始订单失败!";
						$('#msgModal').modal();
	                },
	                success: function(data) {
	                	$("#msg").JSONView(data);
	    				$('#msgModal').modal();
	                }
	            });
			};
		</script>
</body>
</html>