<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="style" value="http://p.missfresh.cn/meituanHtml/" />
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>Mikasa</title>
		<!-- basic styles -->
		<%-- <link href="${ctx}/static/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="${ctx}/static/assets/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${ctx}/static/assets/css/ace.min.css" />
		<link rel="stylesheet" href="${ctx}/static/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="${ctx}/static/assets/css/ace-skins.min.css" />
		<script src="${ctx}/static/assets/js/ace-extra.min.js"></script> --%>
		<link href="${style}/static/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="${style}/static/assets/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${style}/static/assets/css/ace.min.css" />
		<link rel="stylesheet" href="${style}/static/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="${style}/static/assets/css/ace-skins.min.css" />
		<script src="${style}/static/assets/js/ace-extra.min.js"></script>
	</head>

	<body>
		
		<div class="main-container" id="main-container">

					<div class="page-content">
						

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<div class="error-container">
									<div class="well">
										<h1 class="grey lighter smaller">
											<span class="blue bigger-125">
												<i class="icon-lock"></i>
												403
											</span>
											您没有访问权限.
										</h1>
									</div>
								</div><!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->
</body>
</html>