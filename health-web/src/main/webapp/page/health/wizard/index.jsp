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
<link href="${impUpPath}/css/index.css" rel="stylesheet"/>
<script src="${impUpPath}/js/imgUp.js"></script>
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
				        <a href="${ctx}/page/health/homepage/index.jsp" style="color:#00c9ef;text-decoration: none;">会诊中心</a>
				    </li>
				    <li>
				        <a href="${ctx}/page/health/homepage/index.jsp" style="color:#00c9ef;text-decoration: none;">远程判读</a>
				    </li>
				    <li class="active" style="color:#00c9ef">新增诊断</li>
				</ul>
				<!-- /.breadcrumb -->
            </div>
            
			<div class="page-content">
	 
				<div class="row">
					<div class="col-xs-12">
						<!-- PAGE CONTENT BEGINS -->
						<div class="widget-box">
							<div class="widget-header widget-header-blue widget-header-flat" style="display: none;">
								<h4 class="widget-title lighter">新增问诊向导</h4>
							</div>

							<div class="widget-body">
								<div class="widget-main">
									<div id="fuelux-wizard-container">
										<div>
											<ul class="steps">
												<li data-step="1" class="active">
													<span class="step">1</span>
													<span class="title">基本信息</span>
												</li>

												<li data-step="2">
													<span class="step">2</span>
													<span class="title">问诊资料</span>
												</li>

												<li data-step="3">
													<span class="step">3</span>
													<span class="title">图像上传</span>
												</li>
												
											</ul>
										</div>

										<hr />

										<div class="step-content pos-rel">
										
											<%@ include file="step1.jsp"%>
											<%@ include file="step2.jsp"%>
											<%@ include file="step3.jsp"%>
											
										</div>
									</div>

									<hr />
									<div class="wizard-actions">
										<button class="btn btn-prev">
											<i class="ace-icon fa fa-arrow-left"></i>
											上一步
										</button>

										<button class="btn btn-success btn-next" data-last="完成">
											下一步
											<i class="ace-icon fa fa-arrow-right" style="padding-left: 5px;"></i>
										</button>
									</div>
								</div><!-- /.widget-main -->
							</div><!-- /.widget-body -->
						</div>
						<!-- PAGE CONTENT ENDS -->
					</div><!-- /.col -->
				</div><!-- /.row -->
				
			</div><!-- /.page-content -->
		</div>
	</div><!-- /.main-content -->
	
	<!-- footer -->
	<%@ include file="../../common/footer.jsp"%>
	
	</div><!-- /.main-container -->

<!-- 模态框（Modal） -->
<div class="modal fade" id="noneModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop='static'>
  <div class="modal-dialog" role="document">
    <div class="modal-content" style="background-color: #ffffff;">
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
	<script type="text/javascript" src="js/wizard.js"></script>
	<script>
		
	</script>

</body>
</html>