<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title data-localize="dict.title">数据字典</title>
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
				    <li class="active" style="color:#00c9ef">数据字典</li>
				</ul>
				<!-- /.breadcrumb -->
            </div>
            
			
			<div class="page-content">
				<div class="row">
				
					<div class="col-lg-3">
						<div id="treeview"></div>
					</div>
					
					<div class="col-lg-9">
						<div class="panel panel-default">
							<div class="panel-heading" data-localize="common.query_criteria">查询条件</div>
							<div class="panel-body">
								<form id="formSearch" class="form-horizontal">
									<div class="form-group">
									
										<!-- 搜索条件 -->
										<input type="hidden" id="search_parentid" />
										<label class="control-label col-sm-1" data-localize="dict.name">名称</label>
										<div class="col-sm-2">
											<input type="text" class="form-control" id="search_name" maxlength="20">
										</div>
										<label class="control-label col-sm-1" data-localize="dict.code">代码</label>
										<div class="col-sm-2">
											<input type="text" class="form-control" id="search_code" maxlength="20">
										</div>
										<label class="control-label col-sm-1" data-localize="common.status">状态</label>
										<div class="col-sm-2">
											<select class="form-control" id="search_status">
												<option value="" data-localize="common.select">请选择</option>
												<option value="0" data-localize="common.on">启用</option>
												<option value="1" data-localize="common.off">停用</option>
											</select>
										</div>
										
										<div class="col-sm-1">
											<button type="button" class="btn btn-sm btn-primary"
												onclick="search();"><i class="fa fa-search"></i> 
												<span data-localize="button.search">查询</span>
											</button>
										</div>
										
									</div>
								</form>
							</div>
						</div>
	
						<div id="toolbar" class="btn-group">
							<p>
								<button type="button" class="btn btn-sm btn-success" 
									data-toggle="modal" data-target="#editModal" onclick="add();">
									<i class="fa fa-plus"></i> <span data-localize="button.add_this">在该节点中添加</span>
								</button>
							</p>
						</div>
						<!-- 加载列表 -->
						<table id="listDataTable" class="table table-striped table-bordered table-hover"></table>
						
					</div>
					<!-- /.col-lg-9  -->
			</div>
				
				
			</div><!-- /.page-content -->
		</div>
	</div><!-- /.main-content -->
	
	
	<%@ include file="../../common/footer.jsp"%>
	
	</div><!-- /.main-container -->
	
	
	<!-- edit 模态框 -->
	<%@ include file="edit.jsp"%>
			
	<script type="text/javascript" src="js/dict.js"></script>

</body>
</html>