<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>看健康--后台管理</title>

<!-- 导入共通样式页面 修改 -->
<%@ include file="common/common.jsp"%>

</head>

<body class="no-skin">
	
	<%@ include file="common/navbar.jsp"%>

	<div class="main-container ace-save-state" id="main-container">
		<script type="text/javascript">
			try{ace.settings.loadState('main-container')}catch(e){}
		</script>
	
	<%@ include file="common/sidebar.jsp"%>
	
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
				    <li>
				        <i class="ace-icon fa fa-home home-icon"></i>
				        <a href="${ctx}/page/main.jsp" style="color:#00c9ef;text-decoration: none;">首页</a>
				    </li>
				    <li class="active" style="color:#00c9ef">系统管理</li>
				</ul>
				<!-- /.breadcrumb -->
            </div>
            
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<!-- PAGE CONTENT BEGINS -->
							
						<div class="widget-box" id="widget-box-4">
							<div class="widget-header widget-header-large">
								<h4 class="widget-title"><i class="fa fa-bar-chart"></i>诊断统计</h4>
							</div>
							
							<div class="widget-body">
								<div class="widget-main">
								<div class="row">	
									<div class="col-sm-3">
										<div class="col-sm-6">
											<div class="fs1" style="text-align:center;margin-left: 0px;border-right: 1px solid darkgray;">
								                <span class="icon-totalnumber">							                
								                </span>
								               <div style="font-size: 14px;margin-top: -10px;">	
								               	诊断总人数
								                </div>
									         </div>
							        	</div>
										
										<div class="col-sm-1">									  		
									  		<div id="allNum" style="text-align:left;margin-top:10px;color:#00c9ef;font-size:40px;font-weight:bold;">0</div>
										</div>
										<div class="col-sm-3">				  		
									  		<div style="text-align:left;margin-top:40px;padding-left: 20px;">人</div>
										</div>
									</div>
									<div class="col-sm-3">
										<div class="col-sm-6">
											<div class="fs1" style="text-align:center;margin-left: 0px;border-right: 1px solid darkgray;">
								                <span class="icon-compeletd001">							                
								                </span>
								               <div style="font-size: 14px;margin-top: -10px;">	
								               	已完成诊断
								                </div>
									         </div>
							        	</div>
										
										<div class="col-sm-1">									  		
									  		<div id="completed" style="text-align:left;margin-top:10px;color:#5ab548;font-weight:bold;font-size:40px;">0</div>
										</div>
										<div class="col-sm-3">				  		
									  		<div style="text-align:left;margin-top:40px;color:#5ab548;">人</div>
										</div>
									</div>
									<div class="col-sm-3">
										<div class="col-sm-6">
											<div class="fs1" style="text-align:center;margin-left: 0px;border-right: 1px solid darkgray;">
								                <span class="icon-treated">							                
								                </span>
								               <div style="font-size: 14px;margin-top: -10px;">		
								               	待处理诊断
								                </div>
									         </div>
							        	</div>
										
										<div class="col-sm-1">									  		
									  		<div id="treated" style="text-align:left;margin-top:10px;color:#db335f;font-size:40px;font-weight:bold;">0</div>
										</div>
										<div class="col-sm-3">				  		
									  		<div style="text-align:left;margin-top:40px;color:#db335f;">人</div>
										</div>
									</div>
									<div class="col-sm-3">									
										<div class="col-sm-6">
											<div class="fs1" style="text-align:center;margin-left: 0px;border-right: 1px solid darkgray;">
								                <span class="icon-visit" >							                
								                </span>
								               <div style="font-size: 14px;margin-top: -10px;">	
								               	复诊人数
								                </div>
									         </div>
							        	</div>
										
										<div class="col-sm-1">									  		
									  		<div id="noall" style="text-align:right;margin-top:10px;color:#fe6820;font-weight:bold;font-size:40px;">0</div>
										</div>
										<div class="col-sm-3">				  		
									  		<div style="text-align:left;margin-top:40px;color:#fe6820;">人</div>
										</div>
									</div>

								</div>	
								</div>
							</div>
						</div>
						<!-- PAGE CONTENT ENDS -->
					</div><!-- /.col -->
				</div><!-- /.row -->
				
				
			</div><!-- /.page-content -->
		</div>
	</div><!-- /.main-content -->
	
	
	<%@ include file="common/footer.jsp"%>
	
	</div><!-- /.main-container -->

	<script type="text/javascript" src="main-js/main.js"></script>
</body>
</html>