<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title data-localize="terminal.title">设备管理</title>
<!-- 导入共通样式页面 -->
<%@ include file="../../common/common.jsp"%>
<!-- 插件 -->
<%@ include file="../../common/lib.jsp"%>

</head>
<body class="no-skin">
	
	<%@ include file="../../common/navbar.jsp"%>

	<div class="main-container ace-save-state" id="main-container">
		<script type="text/javascript">
			try{ace.settings.loadState('main-container')}catch(e){}
		</script>
	
	<%@ include file="../../common/sidebar.jsp"%>
	
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
				    <li>
				        <i class="ace-icon fa fa-home home-icon"></i>
				        <a href="${ctx}/page/main.jsp" style="color:#00c9ef;text-decoration: none;">首页</a>
				    </li>
				    <li>
				        <a href="${ctx}/page/main.jsp" style="color:#00c9ef;text-decoration: none;">系统管理</a>
				    </li>
				    <li class="active" style="color:#00c9ef">设备管理</li>
				</ul>
				<!-- /.breadcrumb -->
            </div>
			<div class="page-content">
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading" data-localize="common.query_criteria">查询条件</div>
							<div class="panel-body">
								<form id="formSearch" class="form-horizontal">
									<div class="form-group">
										<!-- 搜索条件 -->
										<label class="control-label col-sm-1" data-localize="terminal.cameraBrand">相机品牌</label>
										<div class="col-sm-2">
											<input type="text" class="form-control" id="search_cameraBrand" maxlength="20">
										</div>
										
										<label class="control-label col-sm-2" data-localize="terminal.cameraModel">相机型号</label>
										<div class="col-sm-2">
											<input type="text" class="form-control" id="search_cameraModel" maxlength="20">
										</div>
										
										<label class="control-label col-sm-2" data-localize="terminal.cameraProducer">生产地</label>
										<div class="col-sm-2">
											<input type="text" class="form-control" id="search_cameraProducer" maxlength="20">
										</div>
									</div>
									
									<div class="form-group">
										<label class="control-label col-sm-1" data-localize="terminal.orgId">所属机构</label>
										<div class="col-sm-2">
											<select class="form-control" name="search_orgId" id="search_orgId"></select>
										</div>
										
										<label class="control-label col-sm-2" data-localize="terminal.centralResolution">中心分辨率</label>
										<div class="col-sm-2">
											<input type="text" class="form-control" id="search_centralResolution" maxlength="20">
										</div>
										
										<label class="control-label col-sm-2" data-localize="terminal.ccdResolution">CCD分辨率</label>
										<div class="col-sm-2">
											<input type="text" class="form-control" id="search_ccdResolution" maxlength="20">
										</div>
									</div>
									
									<div class="form-group">
										<label class="control-label col-sm-1">生产时间</label>
										<div class="col-sm-2">
											<div class="input-group input-group-sm">
												<input type="text" id="search_produce_start" class="form-control form_datetime" />
												<!-- 图标 -->
												<span class="input-group-addon">
													<i class="ace-icon fa fa-calendar"></i>
												</span>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="input-group input-group-sm">
												<input type="text" id="search_produce_end" class="form-control form_datetime" />
												<!-- 图标 -->
												<span class="input-group-addon">
													<i class="ace-icon fa fa-calendar"></i>
												</span>
											</div>
										</div>
										
										<label class="control-label col-sm-1">购置时间</label>
										<div class="col-sm-2">
											<div class="input-group input-group-sm">
												<input type="text" id="search_buy_start" class="form-control form_datetime" />
												<!-- 图标 -->
												<span class="input-group-addon">
													<i class="ace-icon fa fa-calendar"></i>
												</span>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="input-group input-group-sm">
												<input type="text" id="search_buy_end" class="form-control form_datetime" />
												<!-- 图标 -->
												<span class="input-group-addon">
													<i class="ace-icon fa fa-calendar"></i>
												</span>
											</div>
										</div>
										
										<div class="col-sm-2">
											<button type="button" class="btn btn-sm btn-primary"
												onclick="search();"><i class="fa fa-search"></i> 
												<span data-localize="button.search">查询</span>
											</button>
											<button type="reset" class="btn btn-sm btn-default">
												<i class="fa fa-repeat"></i> 
												<span data-localize="button.reset">重置</span>
											</button>
										</div>
										
									</div>
								</form>
							</div>
						</div>
	
						<div id="toolbar" class="btn-group">
							<p>
								<button type="button" class="btn btn-sm btn-success" 
									data-toggle="modal" data-target="#editModal" onclick="edit(null);">
									<i class="fa fa-plus"></i> <span data-localize="button.new">添加</span>
								</button>
								&nbsp;&nbsp;
								<button type="button" class="btn btn-sm" onclick="batchDelete();">
									<i class="fa fa-trash-o"></i> <span>批量删除</span>
								</button>
							</p>
						</div>
						<!-- 加载列表 -->
						<table id="listDataTable" class="table table-striped table-bordered table-hover"></table>
	
					</div>
					<!-- /.col-lg-12 -->
				</div>
				
			</div><!-- /.page-content -->
		</div>
	</div><!-- /.main-content -->
	
	
	<%@ include file="../../common/footer.jsp"%>
	
	</div><!-- /.main-container -->


	<!-- edit 模态框 -->
	<%@ include file="edit.jsp"%>
	<script type="text/javascript" src="js/terminal.js"></script>

</body>
</html>