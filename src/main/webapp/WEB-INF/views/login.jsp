<%@ page language="java" pageEncoding="UTF-8" %> 
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="style" value="http://p.missfresh.cn/meituanHtml" />
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1"> 
		<title>Mikasa</title>
		<link rel="stylesheet" href="${ctx}/static/assets/css/bootstrap.min.css"/>
		<link rel="stylesheet" href="${ctx}/static/assets/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${ctx}/static/assets/css/ace.min.css" />
		<link rel="stylesheet" href="${ctx}/static/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="${ctx}/static/assets/css/ace-skins.min.css" />
		<%-- <link rel="stylesheet" href="${style}/static/assets/css/bootstrap.min.css"/>
		<link rel="stylesheet" href="${style}/static/assets/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${style}/static/assets/css/ace.min.css" />
		<link rel="stylesheet" href="${style}/static/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="${style}/static/assets/css/ace-skins.min.css" /> --%>
	</head>

	<body class="login-layout">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<div class="center">
								<h1>
									<i class="icon-leaf green"></i>
									<span class="red">商城</span>
									<span class="white">后台管理系统</span>
								</h1>
								<h4 class="blue">&copy; Mikasa</h4>
							</div>

							<div class="space-6"></div>

							<div class="position-relative">
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header blue lighter bigger">
												<i class="icon-coffee green"></i>
												账户 登录
											</h4>

											<div class="space-6"></div>

											<form id="loginForm" role="form" action="${ctx}/login/index.do" method="post">
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" name="userName" placeholder="用户名" />
															<i class="icon-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" name="password" placeholder="密码" />
															<i class="icon-lock"></i>
														</span>
													</label>
													
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input class="form-control" placeholder="验证码(不区分大小写)" type="text" name="verifyCode"/>
															<i class="icon-picture"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<img id="verifyCodeImage" src="${ctx}/login/getVerifyCodeImage.do"/>
												         	&nbsp;&nbsp; 
												         	<a href="" onclick="reloadVerifyCode()">看不清楚? 换一张</a>
														</span>
													</label>

													<div class="space"></div>

													<div class="clearfix">
														<label class="inline">
															<input type="checkbox" class="ace" />
															<span class="lbl"> 记住密码</span>
														</label>

														<button type="submit" class="width-35 pull-right btn btn-sm btn-primary">
															<i class="icon-key"></i>
															登录
														</button>
													</div>

													<div class="space-4"></div>
												</fieldset>
												<br>
					                            <%--用于输入后台返回的验证错误信息 --%>  
					    						<P style="color:red"><c:out value="${message}" /></P>
											</form>

										</div><!-- /widget-main -->

									</div><!-- /widget-body -->
								</div><!-- /login-box -->

							</div><!-- /position-relative -->
						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div>
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<script type="text/javascript" >
			window.jQuery || document.write("<script src='${ctx}/static/assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>
		<script src="${ctx}/static/assets/js/bootstrap.min.js"></script>
		<script src="${ctx}/static/assets/js/ace-elements.min.js"></script>
		<script src="${ctx}/static/assets/js/ace.min.js"></script>
		<script src="${ctx}/static/assets/js/ace-extra.min.js"></script>
		<%-- <script src="${style}/static/assets/js/bootstrap.min.js"></script>
		<script src="${style}/static/assets/js/ace-elements.min.js"></script>
		<script src="${style}/static/assets/js/ace.min.js"></script>
		<script src="${style}/static/assets/js/ace-extra.min.js"></script> --%>
		
		<script type="text/javascript">  
		function reloadVerifyCode(){  
			document.getElementById('verifyCodeImage').setAttribute('src', '${ctx}/login/getVerifyCodeImage.do');  
		}  
		</script>
		
</body>
</html>