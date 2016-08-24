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
		<div class="container">
		   <div class="row" >
		      <div class="col-xs-6 col-sm-3" 
		         style="background-color: #dedef8;
		         box-shadow: inset 1px -1px 1px #444, inset -1px 1px 1px #444;">
		         <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
		      </div>
		      <div class="col-xs-6 col-sm-3" 
		         style="background-color: #dedef8;box-shadow: 
		         inset 1px -1px 1px #444, inset -1px 1px 1px #444;">
		         <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do 
		            eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut 
		            enim ad minim veniam, quis nostrud exercitation ullamco laboris 
		            nisi ut aliquip ex ea commodo consequat.
		         </p>
		         <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do 
		            eiusmod tempor incididunt ut. 
		         </p>
		      </div>
		
		      <div class="clearfix visible-xs"></div>
		
		      <div class="col-xs-6 col-sm-3" 
		         style="background-color: #dedef8;
		         box-shadow:inset 1px -1px 1px #444, inset -1px 1px 1px #444;">
		         <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco 
		            laboris nisi ut aliquip ex ea commodo consequat. 
		         </p>
		      </div>
		      <div class="col-xs-6 col-sm-3" 
		         style="background-color: #dedef8;box-shadow: 
		         inset 1px -1px 1px #444, inset -1px 1px 1px #444;">
		         <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do 
		            eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut 
		            enim ad minim 
		         </p>
		      </div>
		   </div>
		</div>
		</div>
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
</body>
</html>