<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>登录——看健康</title>
<%@ include file="page/common/common.jsp"%>

<script type="text/javascript" src="${ctx}/static/js/login.js"></script>


</head>
<body class="login-layout" style="background-color: #42b198;">
	<div class="main-container">
		<div class="main-content">
			<div class="row">
				<div class="col-sm-10 col-sm-offset-1">
					<div class="login-container">
						<div class="space-32"></div>
						<div class="space-32"></div>
						<div class="space-32"></div>
						<div class="center">
							<h1>
								<i class="ace-icon fa fa-eye white"></i> <span class="white">看健康</span>
								<span class="white" id="id-text2"></span>
							</h1>
							<h4 class="white" id="id-company-text">远程眼底图像判读与会诊中心</h4>
						</div>

						<div class="space-6"></div>

						<div class="position-relative">
							<div id="login-box"
								class="login-box visible widget-box no-border"
								style="background-color: #e1e1e1; border-radius: 5px;">
								<div class="widget-body">
									<div class="widget-main">

										<div class="space-3"></div>

										<form id="formId" action="${ctx}/login" method="POST">
											<fieldset>
												<label class="block clearfix">
													<span class="block input-icon input-icon-left form-group">
														<input type="text" class="form-control" name="loginname" value="${user.loginname }" placeholder="用户名" autofocus />
														<i class="ace-icon fa fa-user"></i>
													</span>
												</label>

												<label class="block clearfix">
													<span class="block input-icon input-icon-left form-group">
														<input type="password" class="form-control" name="password" value="${user.password }" placeholder="密码" />
														<i class="ace-icon fa fa-lock"></i>
													</span>
												</label>
												
												<label class="block clearfix">
													<span class="inline width-55 form-group">
														<input type="text" class="form-control" id="verificationcode" name="verificationcode" placeholder="验证码">
													</span>
													<img src="${ctx}/code/captcha-image" id="kaptchaImage" style="float:right" />
												</label>
												
												<div class="block clearfix">
													<button type="submit" class="btn btn-primary btn-block">
														<i class="ace-icon fa fa-key"></i>
														<span data-localize="common.login">登录</span>
													</button>
												</div>
												
												<input type="hidden" value="${errorInfo }" id="error" name="error"></input>
			
												<div class="space-4"></div>
											</fieldset>
										</form>


									</div>
									<!-- /.widget-main -->


								</div>
								<!-- /.widget-body -->
							</div>
							<!-- /.login-box -->

						</div>
						<!-- /.position-relative -->


					</div>
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /.main-content -->
	</div>
	<!-- /.main-container -->

</body>
</html>