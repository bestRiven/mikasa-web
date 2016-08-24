<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="style" value="http://p.missfresh.cn/meituanHtml/" />
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>每日优鲜</title>
		<!-- basic styles -->
		<link href="${ctx}/static/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="${ctx}/static/assets/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${ctx}/static/assets/css/ace.min.css" />
		<link rel="stylesheet" href="${ctx}/static/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="${ctx}/static/assets/css/ace-skins.min.css" />
		<script src="${ctx}/static/assets/js/ace-extra.min.js"></script>
		<%-- <link href="${style}/static/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="${style}/static/assets/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${style}/static/assets/css/ace.min.css" />
		<link rel="stylesheet" href="${style}/static/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="${style}/static/assets/css/ace-skins.min.css" />
		<script src="${style}/static/assets/js/ace-extra.min.js"></script> --%>
	</head>

	<body>
		
		<div class="main-container" id="main-container">

					<div class="page-content">
						<div class="page-header">
							<h1>
								每日优鲜 商城管理平台
								<small>
									<i class="icon-double-angle-right"></i>
									welcome.
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<div class="alert alert-info">
									<i class="icon-hand-right"></i>

									Keep on going never give up.
									<button class="close" data-dismiss="alert">
										<i class="icon-remove"></i>
									</button>
								</div>

							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->
		<!-- basic scripts -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='${ctx}/static/assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>
		<script src="${ctx}/static/assets/js/bootstrap.min.js"></script>
		<script src="${ctx}/static/assets/js/typeahead-bs2.min.js"></script>
		<script src="${ctx}/static/assets/js/date-time/bootstrap-datepicker.min.js"></script>
		<script src="${ctx}/static/assets/js/ace-elements.min.js"></script>
		<script src="${ctx}/static/assets/js/ace.min.js"></script>
		<script type="text/javascript">
			window.jQuery || document.write("<script src='${style}/static/assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>
		<%-- <script src="${style}/static/assets/js/bootstrap.min.js"></script>
		<script src="${style}/static/assets/js/typeahead-bs2.min.js"></script>
		<script src="${style}/static/assets/js/date-time/bootstrap-datepicker.min.js"></script>
		<script src="${style}/static/assets/js/ace-elements.min.js"></script>
		<script src="${style}/static/assets/js/ace.min.js"></script> --%>
		<!-- inline scripts related to this page -->
</body>
</html>
