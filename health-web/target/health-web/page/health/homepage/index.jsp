<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>看健康--远程眼底图像判读与会诊中心</title>

<!-- 导入共通样式页面 -->
<%@ include file="../../common/common.jsp"%>
<!-- 插件 -->
<%@ include file="../../common/lib.jsp"%>

<!-- 列表格式化 -->
<script type="text/javascript" src="${ctx}/static/js/fmt.js"></script>

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
				    <li class="active" style="color:#00c9ef">远程判读</li>
				</ul>
				<!-- /.breadcrumb -->
            </div><!-- /.page-header -->
			<div class="page-content">
				<div class="row">
					<div class="col-sm-12" style="padding:15px">
						<form id="formSearch" class="form-horizontal">
							<label class="control-label col-sm-1" style=" width: auto; float: left;">姓名</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" id="search_name" maxlength="20">
							</div>
							
							<label class="control-label col-sm-1" style=" width: auto; float: left;">糖尿病史</label>
							<div class="  col-sm-2">
								<select class="form-control" id="search_diabetes">
									<option value="">请选择</option>
									<option value="0">无</option>
									<option value="1">有</option>
								</select>
							</div>
							
							<label class="control-label col-sm-1" style="width: auto;">诊断状态</label>
							<div class="col-sm-2">
								<select class="form-control" id="search_status">
									<option value="">请选择</option>
									<option value="-2">报告被退回</option>
									<option value="-1">远程会诊中</option>
									<option value="0">机器自动诊断中</option>
									<option value="1">待远程医生确认</option>
									<option value="2">诊断完成</option>
								</select>
							</div>
							
							<div class="col-sm-3">
								<button type="button" class="btn btn-sm btn-primary"
									onclick="search();" style="border-radius: 4px;padding:6px 8px;"><i class="fa fa-search"></i> 
									<span>查询</span>
								</button>

								<button type="reset" class="btn btn-sm btn-default" style="border-radius: 4px;padding:6px 8px;">
									<i class="fa fa-repeat"></i> 
									<span>重置</span>
								</button>
								
								<button type="button" class="butnewly btn btn-sm btn-primary" id="butnewly"
									onclick="newWizard();" ><i class="glyphicon glyphicon-plus"></i> 
									<span>新建诊断</span>
								</button>						
							</div>
						</form>
					</div>
					<div class="col-lg-12">
						<!-- 加载列表 -->
						<table id="listDataTable" class="table table-striped table-bordered table-hover" style="width: 100%;"></table>
					</div>
					<!-- /.col-lg-12 -->
				</div>				
			</div><!-- /.page-content -->
		</div>
	</div><!-- /.main-content -->
	
	<!-- footer -->
	<%@ include file="../../common/footer.jsp"%>
	
	</div><!-- /.main-container -->
	<!-- 这里用来判断按钮权限 -->
	<input type="hidden" name="position" id="position" value="${sessionScope.currentUser.position}" >
	<input type="hidden" name="userid" id="userid" value="${sessionScope.currentUser.id}" >
	<!-- 自定义 js -->
	<script type="text/javascript" src="js/index.js"></script>

</body>
</html>