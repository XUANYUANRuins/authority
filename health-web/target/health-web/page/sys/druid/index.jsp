<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>SQL监控</title>

<!-- 导入共通样式页面 -->
<%@ include file="../../common/common.jsp"%>

<script>

$(function(){
	// 浏览器可见高度
    var mainheight = $(window).height()*0.9;
    $("#iframeId").height(mainheight);
});

</script>

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
			<div class="page-content">

				<div class="page-header">
					<h1>
						SQL监控
						<small>
							<i class="ace-icon fa fa-angle-double-right"></i>
							情况
						</small>
					</h1>
				</div><!-- /.page-header -->

				<div class="row">
	            	<div class="col-lg-12">
	            		<iframe src="${ctx}/druid/index.html" id="iframeId" frameborder="0" style="width:100%;"></iframe>
	            	</div>
	            </div>
	            <!-- /.row -->
				
				
			</div><!-- /.page-content -->
		</div>
	</div><!-- /.main-content -->
	
	
	</div><!-- /.main-container -->
	
</body>
</html>