<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>修改密码</title>
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
				    <li class="active" style="color:#00c9ef">修改密码</li>
				</ul>
				<!-- /.breadcrumb -->
            </div>
            
			<div class="page-content">
				<!-- row -->
				<div class="row">
					<div class="col-xs-12">
						<!-- PAGE CONTENT BEGINS -->
						<form class="form-horizontal" role="form" id="editfm" method="post">
						
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">旧密码</label>

								<div class="col-sm-9">
									<input type="password" name="password" class="col-xs-10 col-sm-5" style="margin-top: 7px;margin-left: 20px;"/>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">新密码</label>

								<div class="col-sm-9">
									<input type="password" name="newPassword" class="col-xs-10 col-sm-5" style="margin-top: 7px;margin-left: 20px;"/>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">确认新密码</label>

								<div class="col-sm-9">
									<input type="password" name="newPasswordRepeat" class="col-xs-10 col-sm-5" style="margin-top: 7px;margin-left: 20px;"/>
								</div>
							</div>
							
							<div class="clearfix form-actions">
								<div class="col-md-offset-4 col-md-9">
									<button class="btn btn-sm btn-info" type="button" onclick="updatePassword()">
										<i class="ace-icon fa fa-check bigger-110"></i>
										确定
									</button>
									&nbsp; &nbsp; &nbsp;
									<button class="btn btn-sm " type="reset">
										<i class="ace-icon fa fa-undo bigger-110"></i>
										重置
									</button>
								</div>
							</div>
							
						</form>
						<!-- PAGE CONTENT END -->
					</div>
				</div>
				<!-- /.row -->
				
			</div><!-- /.page-content -->
		</div>
	</div><!-- /.main-content -->
	
	
	<%@ include file="../../common/footer.jsp"%>
	
	</div><!-- /.main-container -->


	<script type="text/javascript" src="js/modifyPW.js"></script>
	
</body>
</html>