<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<title>看健康--远程眼底图像判读与会诊中心</title>

		<!-- 导入共通样式页面 -->
		<%@ include file="../../common/common.jsp"%>
		<!-- 插件 -->
		<%@ include file="../../common/lib.jsp"%>

		<!-- 图片上传插件 -->
		<c:set var="impUpPath" value="${ctx}/static/plugins/imgUp" />
		<script>
			var impUpPath = '${impUpPath}';
		</script>
		<link href="${impUpPath}/css/index.css" rel="stylesheet" />
		<script src="${impUpPath}/js/imgUp2.js"></script>
		<link rel="stylesheet" href="${ctx}/page/health/report/css/style.css">
		<link rel="stylesheet" href="${ctx}/page/health/report/dist/easyzoom.css">
		<link rel="stylesheet" href="${ctx}/page/health/report/css/bootstrap-grid.min.css">
	</head>

	<body class="no-skin">

		<!-- navbar -->
		<%@ include file="../../common/navbar.jsp"%>

		<div class="main-container ace-save-state" id="main-container">

			<!-- sidebar -->
			<%@ include file="../../common/sidebar.jsp"%>

			<div class="main-content">
				<div class="main-content-inner">

					<div class="breadcrumbs ace-save-state" id="breadcrumbs">
						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="${ctx}/page/health/homepage/index.jsp" style="color:#2da8c8;text-decoration: none;">会诊中心</a>
							</li>
							<li>
								<a href="${ctx}/page/health/homepage/index.jsp" style="color:#2da8c8;text-decoration: none;">远程判读</a>
							</li>
							<li class="active" style="color:#2da8c8">查看报告</li>
						</ul>
						<!-- /.breadcrumb -->
					</div>

					<div class="page-content">

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<div class="space-6"></div>

								<div class="row">
									<div class="col-sm-10 col-sm-offset-1 div_border">
										<div class="center">
											<h3 class="widget-title grey lighter">眼底照像筛查结果</h3>
										</div>
										<div class="row div_border2">
											<div class="col-xs-12 table_title">
												基本信息
											</div>
										</div>
										<div class="row_nav menu">
											<ul>
												<li>
													<label><strong>姓名：</strong></label> <label>${tbBaseInfo.name }</label>
												</li>

												<li>
													<label><strong>性别：</strong></label><label>
													<c:if test="${tbBaseInfo.sex == 0}">
														男
													</c:if>
													<c:if test="${tbBaseInfo.sex == 1}">
														女
													</c:if></label>
												</li>

												<li>
													<label><strong>出生年月：</strong></label><label><span id="birthday"><fmt:formatDate value="${tbBaseInfo.birthday}"  pattern="yyyy-MM-dd"/></span></label>
												</li>
												<li>
													<label><strong>年龄：</strong></label><label><span id="age"></span></label>
												</li>

												<li>
													<label><strong>电话：</strong></label><label>${tbBaseInfo.tel }</label>
												</li>

											</ul>
										</div>
										<form id="editfm" action="${ctx}/consultation/update" method="post" enctype="multipart/form-data">
											<div>
												<div style="width: 40%; float:left; " id="box1" class="div_border2">
													<div class="z_photo align-center box z_file fl" id="img1" style="background-color: #f7f7f7; width: 100%;">
														<div class="drawing">
														<c:if test="${!empty tbReport.leftsignpic}">
															<img src="${ctx}${tbReport.leftsignpic}" class="editable img-responsive" style="max-height: 500px;" id="avatar1">
														</c:if>
														<c:if test="${empty tbReport.leftsignpic}">
															<img src="${ctx}${tbHealthInfo.leftpic}" class="editable img-responsive" style="max-height: 500px;" id="avatar1">
														</c:if>
														</div>
														<div class="box-content" tabindex="5">

															<ul class="icon">
																<li>
																	<a href="javascript:void(0);" data-toggle="modal" data-target="#enlarge1" title="放大"><i class="fa fa-search"></i></a>
																</li>
																<c:if test="${tbReport.status=='-2'}">
																	<li>
																		<a href="javascript:void(0);" onclick="replaceAvatar1('avatar1');" title="替换图片"><i class="fa fa-picture-o"></i></a>
																	</li>
																</c:if>
																<c:if test="${tbReport.status=='-1'}">
																	<li>
																		<a href="javascript:void(0);" data-toggle="modal" data-target="#drawing1" title="画图"><i class="fa fa-paint-brush"></i></a>
																	</li>
																</c:if>
															</ul>
														</div>

														<input type="file" style="display: none;" name="leftImg" id="file1" class="file" value="" imgNum="1" accept="image/jpg,image/jpeg,image/png,image/bmp" />

													</div>
													<div class="img-box z_photo align-center box z_file fl" id="img2" style="background-color: #f7f7f7; width: 100%;">
														<div class="drawing">
														<c:if test="${!empty tbReport.rightsignpic}">
															<a href="${ctx}${tbReport.rightsignpic}" id="avatar2_a"></a>
															<img src="${ctx}${tbReport.rightsignpic}" class="editable img-responsive" style="max-height: 500px;" id="avatar2">
														</c:if>
														<c:if test="${empty tbReport.rightsignpic}">
															<a href="${ctx}${tbHealthInfo.rightpic}" id="avatar2_a"></a>
															<img src="${ctx}${tbHealthInfo.rightpic}" class="editable img-responsive" style="max-height: 500px;" id="avatar2">
														</c:if>
														</div>
														<div class="box-content" tabindex="5">
															<ul class="icon">
																<li>
																	<a href="javascript:void(0);" data-toggle="modal" data-target="#enlarge2" title="放大"><i class="fa fa-search"></i></a>
																</li>
																<c:if test="${tbReport.status=='-2'}">
																	<li>
																		<a href="javascript:void(0);" onclick="replaceAvatar2('avatar2');" title="替换图片"><i class="fa fa-picture-o"></i></a>
																	</li>
																</c:if>
																<c:if test="${tbReport.status=='-1'}">
																	<li>
																		<a href="javascript:void(0);" data-toggle="modal" data-target="#drawing2" title="画图"><i class="fa fa-paint-brush"></i></a>
																	</li>
																</c:if>
															</ul>
														</div>

														<input type="file" style="display: none;" name="rightImg" id="file2" class="file" value="" imgNum="1" accept="image/jpg,image/jpeg,image/png,image/bmp" />

													</div>
												</div>

												<div id="box2" class="div_border div_overflow">
													<table>
														<tr>
															<td>
																<div>
																	<div class="table_title">
																		<span class="icon-lefteye"></span>
																		<span class="mls">左眼特征</span>
																	</div>
																</div>
															</td>
														</tr>
														<tr>
															<td>
																<div class="menul-li">
																	<ul>
																		<li>
																			<label>体重指数：${tbHealthInfo.bmi}</label>
																		</li>
																		<c:forEach items="${feature}" var="item" varStatus="status">
																			<li>

																				<input name="leftfeature" type="checkbox" <c:if test='${fn:contains(tbReport.leftfeature,item.code)}'>checked</c:if> value="${item.code}" />
																				<label>${item.name }</label>
																			</li>
																		</c:forEach>
																	</ul>
																</div>
															</td>
														</tr>
														<tr>
															<td>
																<div>
																	<div class="table_nav">
																		<span>左眼诊断结果</span>
																	</div>
																	<div style="float: right; padding-right: 10px; padding-top: 2px;">
																		<button type="button" class="btn btn-sm btn-info" data-toggle="modal" data-target="#myInitResult-left">
																						       	<i class="ace-icon fa fa-pencil-square-o bigger-110"></i>
																						       	编辑
																						   </button>
																	</div>
																</div>
															</td>
														</tr>
														<tr>
															<td style="padding: 12px;">
																<!-- 左右眼初步诊断结果 -->
																<%@ include file="initResult-left.jsp"%>
															</td>
														</tr>
														<tr>
															<td>
																<div>
																	<div class="table_title">
																		<span class="icon-righteye"></span>
																		<span class="mls">右眼特征</span>
																	</div>
																</div>
															</td>
														</tr>
														<tr>
															<td>
																<div class="menul-li">
																	<ul>
																		<li>
																			<label>体重指数：${tbHealthInfo.bmi}</label>
																		</li>
																		<c:forEach items="${feature}" var="item" varStatus="status">
																			<li>

																				<input name="rightfeature" type="checkbox" <c:if test='${fn:contains(tbReport.rightfeature,item.code)}'>checked</c:if> value="${item.code}" />
																				<label>${item.name }</label>

																			</li>
																		</c:forEach>
																	</ul>
																</div>
															</td>
														</tr>
														<tr>
															<td>
																<div>
																	<div class="table_nav">
																		<span>右眼诊断结果</span>
																	</div>
																	<div style="float: right;padding-right: 10px; padding-top: 2px;">
																		<button type="button" class="btn btn-sm btn-info" data-toggle="modal" data-target="#myInitResult-right">
																						       		<i class="ace-icon fa fa-pencil-square-o bigger-110"></i>
																						       		编辑
																						   </button>
																	</div>
																</div>
															</td>
														</tr>
														<tr>
															<td style="padding: 12px;">
																<!-- 左右眼初步诊断结果 -->
																<%@ include file="initResult-right.jsp"%>
															</td>
														</tr>
														<tr>
															<td>
																<div>
																	<div class="table_title">
																		<span class="icon-doctoractive"></span>
																		<span class="mls">医生建议</span>
																	</div>
																</div>
																<div style="float: right;padding-right: 10px; padding-top: 2px;">
																	<button type="button" class="btn btn-sm btn-info" data-toggle="modal" data-target="#mySuggest">
																					       		<i class="ace-icon fa fa-pencil-square-o bigger-110"></i>
																					       		编辑
																					   	</button>
																</div>
															</td>
														</tr>
														<tr>
															<td style="padding: 12px;">
																<%@ include file="suggest.jsp"%>
															</td>
														</tr>
														<tr>
															<td>
																<div class="row" style="text-align: right; padding: 20px; margin-right: 20px;font-size: 10px;">
																	<font color="gray">
																		<strong>签名：</strong>
																	</font>
																	<font color="gray">${tbUser.realname}</font>
																	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	<font color="gray"><strong>日期：</strong></font>
																	<font color="gray">
																		<fmt:formatDate value="${tbReport.createtime}" pattern="yyyy-MM-dd HH:mm:ss" />
																	</font>
																</div>
															</td>
														</tr>
													</table>

												</div>
											</div>

											<input type="hidden" name="id" value="${tbReport.id}" />
											<input type="hidden" name="tbHealthInfoid" value="${tbHealthInfo.id}" />
											<input type="hidden" id="position" name="position" value="${sessionScope.currentUser.position}" />
											<input type="hidden" id="isSubmit" name="isSubmit" value="1" />
											<input type="hidden" id="status" name="status" value="${tbReport.status}" />
											<input type="hidden" id="centerX_left1" name="centerX_left1" value="${tbCircle.centerx_left1}" />
											<input type="hidden" id="centerY_left1" name="centerY_left1" value="${tbCircle.centery_left1}" />
											<input type="hidden" id="radius_left1" name="radius_left1" value="${tbCircle.radius_left1}" />
											<input type="hidden" id="centerX_right1" name="centerX_right1" value="${tbCircle.centerx_right1}" />
											<input type="hidden" id="centerY_right1" name="centerY_right1" value="${tbCircle.centery_right1}" />
											<input type="hidden" id="radius_right1" name="radius_right1" value="${tbCircle.radius_right1}" />
											<input type="hidden" id="centerX_left2" name="centerX_left2" value="${tbCircle.centerx_left2}" />
											<input type="hidden" id="centerY_left2" name="centerY_left2" value="${tbCircle.centery_left2}" />
											<input type="hidden" id="radius_left2" name="radius_left2" value="${tbCircle.radius_left2}" />
											<input type="hidden" id="centerX_right2" name="centerX_right2" value="${tbCircle.centerx_right2}" />
											<input type="hidden" id="centerY_right2" name="centerY_right2" value="${tbCircle.centery_right2}" />
											<input type="hidden" id="radius_right2" name="radius_right2" value="${tbCircle.radius_right2}" />
										</form>
										<div>
											<div class="clearfix form-actions" style="clear: both;">
												<div class="center">

													<!--角色为远程会诊中心-->
													<c:if test="${sessionScope.currentUser.position=='p06'}">
														<c:if test="${tbReport.status != '-1' && tbReport.status != '-2'}">
															<button class="btn btn-warning" id="save" type="button" onclick="updateReport(this);">
																<i class="icon-save"></i>
																	保存
															</button>
														</c:if>
														<c:if test="${tbReport.status == '-1'}">
															<button class="btn btn-warning" id="submit" type="button" onclick="updateReport(this);">
															<i class="icon-save"></i>
																提交
															</button> &nbsp; &nbsp; &nbsp;&nbsp;
															<button class="btn btn-warning" id="return" type="button" onclick="updateReport(this);">
																<i class="icon-save"></i>
																退回
															</button>
														</c:if>

													</c:if>
													<!--角色为基层-->
													<c:if test="${sessionScope.currentUser.position=='p05'}">
														<button class="btn btn-warning" id="submit" type="button" onclick="getUsersP06();">
																	<i class="icon-save"></i>
																	提交
																</button>
													</c:if>
													&nbsp; &nbsp; &nbsp;&nbsp;
													<button class="btn btn-success" type="button" onclick="backHome();">
																	<i class="ace-icon fa fa-arrow-left"></i>
																	返回
																</button>
												</div>
											</div>
										</div>
									</div>
								</div>

								<!-- PAGE CONTENT ENDS -->
							</div>
							<!-- /.col -->
						</div>
						<!-- /.row -->

					</div>
					<!-- /.page-content -->
				</div>
			</div>
			<!-- /.main-content -->

			<!-- footer -->
			<%@ include file="../../common/footer.jsp"%>

		</div>

		<!-- 模态框（Modal） -->
		<div class="modal fade" id="enlarge1" tabindex="-1" role="dialog" aria-hidden="true">
			<div class="modal-dialog modal-width">
				<div class="modal-content">
					<div class="modal-body align-center" style="background-color: #f7f7f7;">
						<div style="max-width: 500px;" class="easyzoom easyzoom--overlay" id="easyzoom1">
							<c:if test="${!empty tbReport.leftsignpic}">
								<a href="${ctx}${tbReport.leftsignpic}" id="avatar1_a"></a>
								<img src="${ctx}${tbReport.leftsignpic}" class="editable img-responsive" style="max-height: 500px;" id="avatar1_enlarge">
							</c:if>
							<c:if test="${empty tbReport.leftsignpic}">
								<a href="${ctx}${tbHealthInfo.leftpic}" id="avatar1_a"></a>
								<img src="${ctx}${tbHealthInfo.leftpic}" class="editable img-responsive" style="max-height: 500px;" id="avatar1_enlarge">
							</c:if>
						</div>
						<!-- /.col -->
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-sm btn-default circleimg" data-dismiss="modal">关闭</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>

		<div class="modal fade" id="enlarge2" tabindex="-2" role="dialog" aria-hidden="true">
			<div class="modal-dialog modal-width">
				<div class="modal-content">
					<div class="modal-body align-center" style="background-color: #f7f7f7;">
						<div style="max-width: 500px;" class="easyzoom easyzoom--overlay" id="easyzoom2">
							<c:if test="${!empty tbReport.rightsignpic}">
								<a href="${ctx}${tbReport.rightsignpic}" id="avatar2_a"></a>
								<img src="${ctx}${tbReport.rightsignpic}" class="editable img-responsive" style="max-height: 500px;" id="avatar2_enlarge">
							</c:if>
							<c:if test="${empty tbReport.rightsignpic}">
								<a href="${ctx}${tbHealthInfo.rightpic}" id="avatar2_a"></a>
								<img src="${ctx}${tbHealthInfo.rightpic}" class="editable img-responsive" style="max-height: 500px;" id="avatar2_enlarge">
							</c:if>
						</div>
						<!-- /.col -->
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-sm btn-default circleimg" data-dismiss="modal">关闭</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		<!-- /.main-container -->
		<!-- loading -->
		<div class="modal fade" id="myModal" data-keyboard="false" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myToggleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">提示</h4>
					</div>
					<div class="modal-body">
						正在上传中，请稍候。。。
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="drawing1" tabindex="-1" role="dialog" aria-hidden="true">
			<div class="modal-dialog modal-width">
				<div class="modal-content">
					<div class="modal-body align-center" style="background-color: #f7f7f7;">
						<div style="width: 500px;" class="drawing_left circle_sign" id="drawing_left">
							<c:if test="${!empty tbReport.leftsignpic}">
								<img src="${ctx}${tbReport.leftsignpic}" style="max-height: 500px;" />
							</c:if>
							<c:if test="${empty tbReport.leftsignpic}">
								<img src="${ctx}${tbHealthInfo.leftpic}" style="max-height: 500px;" />
							</c:if>
						</div>
						<!-- /.col -->
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-sm btn-info circleimg" onclick="removeCircle1()">清除</button>
						<button type="button" class="btn btn-sm btn-default circleimg" data-dismiss="modal">关闭</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>

		<div class="modal fade" id="drawing2" tabindex="-1" role="dialog" aria-hidden="true">
			<div class="modal-dialog modal-width">
				<div class="modal-content">
					<div class="modal-body align-center" style="background-color: #f7f7f7;">
						<div style="max-width: 500px;" class="drawing_right circle_sign" id="drawing_right">
							<c:if test="${!empty tbReport.rightsignpic}">
								<img src="${ctx}${tbReport.rightsignpic}" class="editable img-responsive" style="max-height: 500px;" />
							</c:if>
							<c:if test="${empty tbReport.rightsignpic}">
								<img src="${ctx}${tbHealthInfo.rightpic}" class="editable img-responsive" style="max-height: 500px;" />
							</c:if>
						</div>
						<!-- /.col -->
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-sm btn-info circleimg" onclick="removeCircle2()">清除</button>
						<button type="button" class="btn btn-sm btn-default circleimg" data-dismiss="modal">关闭</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		
		<!-- 模态框（Modal） -->
		<div class="modal fade" id="noneModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop='static'>
		  <div class="modal-dialog modal-width" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h4 class="modal-title" id="myModalLabel">当前远程会诊中心无人在线，是否继续提交</h4>
		      </div>
		      <div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
				<button type="button" class="btn btn-primary" id="continueSubmit" onclick="continueSubmit()">
					提交
				</button>
			</div>
		  </div>
		</div>

		<!-- 自定义 js -->
		<script type="text/javascript" src="${ctx}/page/health/report/js/report.js"></script>
		<script src="${ctx}/page/health/report/dist/easyzoom.js"></script>
		<script type="text/javascript">
			$(function() { // 初始化内容
				var position = $("input[id='position']")[0].value;
				if(position == "") {
					//返回主页
					location.href = ctx + "/page/health/login.jsp";
				}

				var status = $("input[name='status']").val();
				if(status != 1 && status != 2) {
					$("input[type='checkbox']").prop("disabled", true);
					$(".btn-sm").prop("disabled", true);
					$(".circleimg").prop("disabled", false);
				}

				//设置滚动条高度
				var box2_width = parseInt($('#box1').css('width')) * 2 + 12;
				$('#box2').css('height', box2_width);

				var img1_width = parseInt($('#img1').css('width'));
				$('#img1').css('height', img1_width);
				

				var img2_width = parseInt($('#img2').css('width'));
				$('#img2').css('height', img2_width);

				var $easyzoom = $('.easyzoom').easyZoom();
				var api = $easyzoom.data('easyZoom');

				$("#avatar1").load(function() {
					var avatar1_width = parseInt($('#img1').css('width'));
					$('#avatar1').css('max-height', avatar1_width);
					
				});

				$("#avatar2").load(function() {
					var avatar2_width = parseInt($('#img2').css('width'));
					$('#avatar2').css('max-height', avatar2_width);
				});
			});

			function replaceAvatar1(id) {
				$("#file1").attr("imgId", id);
				var num = parseInt($("#file1").attr("imgNum"));
				num = num + 1;
				$("#file1").attr("imgNum", num);
				$("#file1").click();
			}

			function replaceAvatar2(id) {
				$("#file2").attr("imgId", id);
				var num = parseInt($("#file2").attr("imgNum"));
				num = num + 1;
				$("#file2").attr("imgNum", num);
				$("#file2").click();
			}
		</script>
	</body>

</html>