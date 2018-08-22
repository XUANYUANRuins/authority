<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">

	<head>
		<title>登录——看健康</title>
		<%@ include file="page/common/common.jsp"%>

		<!--<script type="text/javascript" src="${ctx}/static/js/login.js"></script>-->

		<script>
			function GetRequest() {
				var url = location.search; //获取url中"?"符后的字串   
				var theRequest = new Object();
				if(url.indexOf("?") != -1) {
					var str = url.substr(1);
					strs = str.split("&");
					for(var i = 0; i < strs.length; i++) {
						theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
					}
				}
				if(theRequest.kickout == 1) {
					alert("当前用户应在别的地方登录");
				}

				return theRequest;
			}

			$(function() {
				GetRequest(); //判断是否被踢出

				//判断是否宽屏
				var winWide = window.screen.width;
				var wideScreen = false;
				var html = "";
				if(winWide < 768) { //768及以下分辨率
					html += "<div class=\"col-sm-12\">";
					html += "<div class=\"col-sm-12\">";
					html += "<div class=\"login-container\">";
					html += "<div class=\"space-32\">";
					html += "</div>";
					html += "<div class=\"center\">";
					html += "    <img src=\"${ctx}/static/images/login_logo_mb.png\"/>";
					html += "     <span class=\"white\" id=\"id-text2\"></span>";
					html += "</div>";
					html += "<div class=\"position-relative\">";

					html += "<div id=\"login-box\" class=\"login-box visible widget-box no-border\" style=\"background:rgb(137,203,255);\">";
					html += "<div class=\"widget-body\">";
					html += "<div class=\"widget-main\">";
					html += "	<h4 class=\"header blue lighter bigger\">";
					html += "		<i class=\"ace-icon fa fa-coffee green\"></i>";
					html += "		登录";
					html += "	</h4>";

					html += "	<div class=\"space-6\"></div>";

					html += "	<form id=\"formId\" action=\"${ctx}/login\" method=\"POST\">";
					html += "		<fieldset>";
					html += "			<label class=\"block clearfix\">";
					html += "				<span class=\"block input-icon input-icon-left form-group\">";
					html += "					<input type=\"text\" style=\"border-radius: 6px !important; background: #fff !important; padding-left: 34px; margin-left: 0px;\" class=\"form-control\"  name=\"loginname\" value=\"${user.loginname }\" placeholder=\"用户名\" autofocus />";
					html += "					<i class=\"ace-icon fa icon-user\"></i>";
					html += "				</span>";
					html += "			</label>";

					html += "			<label class=\"block clearfix\">";
					html += "				<span class=\"block input-icon input-icon-left form-group\">";
					html += "					<input type=\"password\" style=\"border-radius: 6px !important; background: #fff !important; padding-left: 34px; margin-left: 0px;\" class=\"form-control\" name=\"password\" value=\"${user.password }\" placeholder=\"密码\" />";
					html += "					<i class=\"ace-icon fa icon-lock\"></i>";
					html += "				</span>";
					html += "			</label>";

					html += "			<label class=\"block clearfix\">";
					html += "				<span class=\"inline width-55 form-group\">";
					html += "					<input type=\"text\" style=\"border-radius: 6px !important; background: #fff !important; padding-left: 34px; margin-left: 0px;\" class=\"form-control\" id=\"verificationcode\" name=\"verificationcode\" placeholder=\"验证码\">";
					html += "				</span>";
					html += "				<img src=\"${ctx}/code/captcha-image\" id=\"kaptchaImage\" style=\"float:right\" />";
					html += "			</label>";

					html += "			<div class=\"block clearfix\" style=\"text-align: center;\">";
					html += "				<button type=\"submit\" class=\"btn btn-primary\" style=\"border-radius: 4px;padding:6px 8px;margin-right:20px\">";
					html += "					<i class=\"ace-icon fa fa-key\"></i>";
					html += "					<span data-localize=\"common.login\">登录</span>";
					html += "				</button>";

					html += "				<button type=\"reset\" class=\"btn btn-primary\" style=\"border-radius: 4px;padding:6px 8px;\">";
					html += "					<i class=\"ace-icon fa fa-repeat\"></i>";
					html += "					<span>重置</span>";
					html += "				</button>";
					html += "			</div>";

					html += "			<input type=\"hidden\" value=\"${errorInfo }\" id=\"error\" name=\"error\"></input>";

					html += "			<div class=\"space-4\"></div>";
					html += "		</fieldset>";
					html += "	</form>";
					html += "</div>";
					html += "</div>";
					html += "</div>";
					html += "</div>";
					html += "</div>";
					html += "</div>";
					html += "</div>";
					$("#logo_div").hide();
					$("body").css("background", "linear-gradient(0deg,#38987c,#57c598) !important;");
				} else if(winWide < 1680) {
					html += "<div class=\"col-sm-12\">";
					html += "<div class=\"col-sm-4 col-sm-offset-1\">";
					html += "	<div class=\"space-24\"></div>";
					html += "    <img src=\"${ctx}/static/images/health.png\" style=\"height:400px;width:400px;\"/>";
					html += "</div>";

					html += "<div class=\"col-sm-4\" >";
					html += "	<div class=\"login-container\" style=\"margin-left:40%\">";
					html += "		<div class=\"space-32\">";

					html += "		</div>";

					html += "		<div class=\"center\">";
					html += "			<h1>";
					html += "				<i class=\"ace-icon fa fa-eye white\"></i>";
					html += "				<span class=\"white\">看健康</span>";
					html += "				<span class=\"white\" id=\"id-text2\"></span>";
					html += "			</h1>";

					html += "		</div>";
					html += "		<div class=\"position-relative\">";
					html += "			<div id=\"login-box\" class=\"login-box visible widget-box no-border\" style=\"background:rgb(137,203,255);\">";
					html += "				<div class=\"widget-body\">";
					html += "					<div class=\"widget-main\">";
					html += "						<h4 class=\"header blue lighter bigger\">";
					html += "							<i class=\"ace-icon fa fa-coffee green\"></i>";
					html += "							登录";
					html += "						</h4>";

					html += "						<div class=\"space-6\"></div>";

					html += "						<form id=\"formId\" action=\"${ctx}/login\" method=\"POST\">";
					html += "							<fieldset>";
					html += "								<label class=\"block clearfix\">";
					html += "									<span class=\"block input-icon input-icon-left form-group\">";
					html += "										<input type=\"text\" style=\"border-radius: 6px !important; background: #fff !important; padding-left: 34px; margin-left: 0px;\" class=\"form-control\"  name=\"loginname\" value=\"${user.loginname }\" placeholder=\"用户名\" autofocus />";
					html += "										<i class=\"ace-icon fa icon-user\"></i>";
					html += "									</span>";
					html += "								</label>";

					html += "								<label class=\"block clearfix\">";
					html += "									<span class=\"block input-icon input-icon-left form-group\">";
					html += "										<input type=\"password\" style=\"border-radius: 6px !important; background: #fff !important; padding-left: 34px; margin-left: 0px;\" class=\"form-control\" name=\"password\" value=\"${user.password }\" placeholder=\"密码\" />";
					html += "										<i class=\"ace-icon fa icon-lock\"></i>";
					html += "									</span>";
					html += "								</label>";

					html += "								<label class=\"block clearfix\">";
					html += "									<span class=\"inline width-55 form-group\">";
					html += "										<input type=\"text\" style=\"border-radius: 6px !important; background: #fff !important; padding-left: 34px; margin-left: 0px;\" class=\"form-control\" id=\"verificationcode\" name=\"verificationcode\" placeholder=\"验证码\">";
					html += "									</span>";
					html += "									<img src=\"${ctx}/code/captcha-image\" id=\"kaptchaImage\" style=\"float:right\" />";
					html += "								</label>";

					html += "								<div class=\"block clearfix\" style=\"text-align: center;\">";
					html += "									<button type=\"submit\" class=\"btn btn-primary\" style=\"border-radius: 4px;padding:6px 8px;margin-right:20px\">";
					html += "										<i class=\"ace-icon fa fa-key\"></i>";
					html += "										<span data-localize=\"common.login\">登录</span>";
					html += "									</button>";

					html += "									<button type=\"reset\" class=\"btn btn-primary\" style=\"border-radius: 4px;padding:6px 8px;\">";
					html += "										<i class=\"ace-icon fa fa-repeat\"></i>";
					html += "										<span>重置</span>";
					html += "									</button>";
					html += "								</div>";

					html += "								<input type=\"hidden\" value=\"${errorInfo }\" id=\"error\" name=\"error\"></input>";

					html += "								<div class=\"space-4\"></div>";
					html += "							</fieldset>";
					html += "						</form>";
					html += "					</div>";
					html += "				</div>";
					html += "			</div>";
					html += "		</div>";
					html += "	</div>";
					html += "</div>";
					html += "</div>";
					$("#logo_div").show();
					wideScreen = true; //是宽屏
				} else {
					html += "<div class=\"col-sm-12\">";
					html += "<div class=\"col-sm-4 col-sm-offset-2\">";
					html += "	<div class=\"space-24\"></div>";
					html += "    <img src=\"${ctx}/static/images/health.png\" style=\"height:400px;width:400px;\"/>";
					html += "</div>";

					html += "<div class=\"col-sm-4\" >";
					html += "	<div class=\"login-container\" style=\"margin-left:1%\">";
					html += "		<div class=\"space-32\">";

					html += "		</div>";

					html += "		<div class=\"center\">";
					html += "			<h1>";
					html += "				<i class=\"ace-icon fa fa-eye white\"></i>";
					html += "				<span class=\"white\">看健康</span>";
					html += "				<span class=\"white\" id=\"id-text2\"></span>";
					html += "			</h1>";

					html += "		</div>";
					html += "		<div class=\"position-relative\">";
					html += "			<div id=\"login-box\" class=\"login-box visible widget-box no-border\" style=\"background:rgb(137,203,255);\">";
					html += "				<div class=\"widget-body\">";
					html += "					<div class=\"widget-main\">";
					html += "						<h4 class=\"header blue lighter bigger\">";
					html += "							<i class=\"ace-icon fa fa-coffee green\"></i>";
					html += "							登录";
					html += "						</h4>";

					html += "						<div class=\"space-6\"></div>";

					html += "						<form id=\"formId\" action=\"${ctx}/login\" method=\"POST\">";
					html += "							<fieldset>";
					html += "								<label class=\"block clearfix\">";
					html += "									<span class=\"block input-icon input-icon-left form-group\">";
					html += "										<input type=\"text\" style=\"border-radius: 6px !important; background: #fff !important; padding-left: 34px; margin-left: 0px;\" class=\"form-control\"  name=\"loginname\" value=\"${user.loginname }\" placeholder=\"用户名\" autofocus />";
					html += "										<i class=\"ace-icon fa icon-user\"></i>";
					html += "									</span>";
					html += "								</label>";

					html += "								<label class=\"block clearfix\">";
					html += "									<span class=\"block input-icon input-icon-left form-group\">";
					html += "										<input type=\"password\" style=\"border-radius: 6px !important; background: #fff !important; padding-left: 34px; margin-left: 0px;\" class=\"form-control\" name=\"password\" value=\"${user.password }\" placeholder=\"密码\" />";
					html += "										<i class=\"ace-icon fa icon-lock\"></i>";
					html += "									</span>";
					html += "								</label>";

					html += "								<label class=\"block clearfix\">";
					html += "									<span class=\"inline width-55 form-group\">";
					html += "										<input type=\"text\" style=\"border-radius: 6px !important; background: #fff !important; padding-left: 34px; margin-left: 0px;\" class=\"form-control\" id=\"verificationcode\" name=\"verificationcode\" placeholder=\"验证码\">";
					html += "									</span>";
					html += "									<img src=\"${ctx}/code/captcha-image\" id=\"kaptchaImage\" style=\"float:right\" />";
					html += "								</label>";

					html += "								<div class=\"block clearfix\" style=\"text-align: center;\">";
					html += "									<button type=\"submit\" class=\"btn btn-primary\" style=\"border-radius: 4px;padding:6px 8px;margin-right:20px\">";
					html += "										<i class=\"ace-icon fa fa-key\"></i>";
					html += "										<span data-localize=\"common.login\">登录</span>";
					html += "									</button>";

					html += "									<button type=\"reset\" class=\"btn btn-primary\" style=\"border-radius: 4px;padding:6px 8px;\">";
					html += "										<i class=\"ace-icon fa fa-repeat\"></i>";
					html += "										<span>重置</span>";
					html += "									</button>";
					html += "								</div>";

					html += "								<input type=\"hidden\" value=\"${errorInfo }\" id=\"error\" name=\"error\"></input>";

					html += "								<div class=\"space-4\"></div>";
					html += "							</fieldset>";
					html += "						</form>";
					html += "					</div>";
					html += "				</div>";
					html += "			</div>";
					html += "		</div>";
					html += "	</div>";
					html += "</div>";
					html += "</div>";
					$("#logo_div").show();
					wideScreen = true; //是宽屏
				}
				$("body .main-container .main-content").empty();
				$("body .main-container .main-content").append(html);

				$('#kaptchaImage').click(function() { /*点击图片刷新验证码*/
					$(this).hide().attr('src', ctx + '/code/captcha-image').fadeIn();
				});

				//验证
				validate();
				geterror();
			})

			//获取后台返回的错误信息
			function geterror() {
				var errorInfo = $("#error").val();
				if(!(errorInfo == null || errorInfo == '' || errorInfo == '验证码错误！')) {
					alertMsg(errorInfo);
				}
			}

			//表单验证
			function validate() {
				$('#formId').bootstrapValidator({
					fields: { /*验证*/
						loginname: { /*键名和input name值对应*/
							validators: {
								notEmpty: { /*非空提示*/
									message: $.i18n.prop("message.notempty")
								},
								regexp: {
									regexp: /^[a-zA-Z0-9_]+$/,
									message: $.i18n.prop("message.letterNumber")
								}
							}
						},
						password: { /*键名和input name值对应*/
							validators: {
								notEmpty: { /*非空提示*/
									message: $.i18n.prop("message.notempty")
								},
								stringLength: {
									min: 6,
									max: 30,
									message: $.i18n.prop("message.length", 6, 30)
								}
							}
						}

					}
				});
			}

			//设置cookie
			function setCookie(cname, cvalue, exdays) {
				var d = new Date();
				d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
				var expires = "expires=" + d.toUTCString();
				document.cookie = cname + "=" + cvalue + "; " + expires;
			}
			//获取cookie
			function getCookie(cname) {
				var name = cname + "=";
				var ca = document.cookie.split(';');
				for(var i = 0; i < ca.length; i++) {
					var c = ca[i];
					while(c.charAt(0) == ' ') c = c.substring(1);
					if(c.indexOf(name) != -1) return c.substring(name.length, c.length);
				}
				return "";
			}
		</script>

	</head>

	<body class="login-layout" style="position: private;">
		<div id="logo_div">
			<div style="height:80px;width:100%;background:#fff">
				<img src="${ctx}/static/images/health_logo.png" style="height:80px;width:390px" />
			</div>
		</div>
		<div class="main-container">
			<div class="main-content">
			</div>
		</div>

		<div id="foot_div" style="height:60px;width:100%;background:#fff;text-align: center;position: absolute;bottom:0px;">
			<p>福州依影健康科技有限公司V1.2</br>福建老年眼病与视觉工程研究中心眼底影像研究室 Since 2012</p>
		</div>
	</body>

</html>