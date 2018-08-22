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
				        <a href="${ctx}/page/health/homepage/index.jsp">会诊中心</a>
				    </li>
				    <li class="active">报告页面</li>
				</ul>
				<!-- /.breadcrumb -->
            </div>
		
			<div class="page-content">
	 
				<div class="row">
                        <div class="col-xs-12">
                            <!-- PAGE CONTENT BEGINS -->
                            <div class="space-6"></div>

                            <div class="row">
                                <div class="col-sm-10 col-sm-offset-1">
                                    <div class="widget-box transparent">
                                        <div class="widget-header widget-header-large">
                                            <h3 class="widget-title grey lighter">
                                                <i class="ace-icon fa fa-leaf green"></i> 眼底图像筛查结果
                                            </h3>

                                        </div>

                                        <div class="widget-body">
                                            <div class="widget-main padding-24">

                                                <div class="row">
                                                    
                                                    <div class="col-xs-12 col-sm-12">
                                                    
                                                    	<div class="row">
                                                            <div class="col-xs-12 label label-lg label-info arrowed-in arrowed-right">
                                                                <b>基本信息</b>
                                                            </div>
                                                        </div>
                                                        <div>
                                                            <ul class="list-unstyled spaced">
                                                                <li>
                                                                    <i class="ace-icon fa fa-caret-right blue"></i>
                                                                    <label>姓名：</label> ${tbBaseInfo.name }
                                                                </li>

                                                                <li>
                                                                    <i class="ace-icon fa fa-caret-right blue"></i>
                                                                    <label>性别：</label> 
                                                                    <c:if test="${tbBaseInfo.sex == 0}">
                                                                    	男
                                                                    </c:if>
                                                                    <c:if test="${tbBaseInfo.sex == 1}">
                                                                    	女
                                                                    </c:if>
                                                                </li>

                                                                <li>
                                                                    <i class="ace-icon fa fa-caret-right blue"></i>
                                                                    <label>出生年月：</label><fmt:formatDate value="${tbBaseInfo.birthday}"  pattern="yyyy-MM-dd"/> 
                                                                </li>

                                                            </ul>
                                                        </div>
                                                        
                                                        <hr />
	                                                    <!-- 左右眼图片 -->
	                                                    <div class="row">
		                                                    <div class="col-xs-12 col-sm-5 center">
		                                                        <span class="profile-picture">
		                                                                <img class="editable img-responsive" alt="左眼图片" id="avatar2" 
		                                                                src="${ctx}${tbReport.leftsignpic}" />
		                                                        </span>
		                                                    </div>
		                                                    
		                                                    <div class="col-xs-12 col-sm-5 col-sm-offset-1 center">
		                                                    	<span class="profile-picture">
		                                                                <img class="editable img-responsive" alt="右眼图片" id="avatar2" 
		                                                                src="${ctx}${tbReport.rightsignpic}" />
		                                                        </span>
		                                                    </div>
	                                                    </div>
	                                                    <hr />
	                                                    <form id="editfm" action="${ctx}/consultation/update" method="post" enctype="multipart/form-data">
	                                                    <div class="row">
                                                        	<div class="img-box col-sm-5">
																<section class="img-section">
																	<p class="up-p">替换左眼图片</p>
																	<div class="z_photo upimg-div clear" >
															               	 <section class="z_file fl">
															               	 	<img src="${impUpPath}/img/a11.png" class="add-img">
															               	 	<input type="file" name="leftImg" id="file1" class="file" value="" accept="image/jpg,image/jpeg,image/png,image/bmp" />
															               	 </section>
															         </div>
																 </section>
															</div>
															<div class="img-box col-sm-5">
																<section class="img-section">
																	<p class="up-p">替换右眼图片</p>
																	<div class="z_photo upimg-div clear" >
															               	 <section class="z_file fl">
															               	 	<img src="${impUpPath}/img/a11.png" class="add-img">
															               	 	<input type="file" name="rightImg" id="file2" class="file" value="" accept="image/jpg,image/jpeg,image/png,image/bmp" />
															               	 </section>
															         </div>
																 </section>
															</div>
													        <aside class="mask works-mask">
																<div class="mask-content">
																	<p class="del-p">您确定要删除该图片吗？</p>
																	<p class="check-p"><span class="del-com wsdel-ok">确定</span><span class="wsdel-no">取消</span></p>
																</div>
															</aside>
                                                        </div>
                                                        <hr />
														
                                                        <div class="row">
                                                            <div class="col-xs-12 label label-lg label-success arrowed-in arrowed-right">
                                                                <b>初步诊断结果</b>
                                                            </div>
                                                        </div>
                                                        <div>
                                                            <ul class="list-unstyled  spaced">
                                                                <li class="divider"></li>
                                                                
                                                                <li>
                                                                    <i class="ace-icon fa fa-caret-right red"></i>
                                                                    <b>左眼：</b>
                                                                </li>
                                                                <li>
                                                                	<textarea class="form-control" name="leftresult" 
                                                                		<c:if test="${tbReport.initstatus == 0}">readonly</c:if> >${tbReport.leftresult}</textarea>
                                                                </li>

                                                                <li>
                                                                    <i class="ace-icon fa fa-caret-right red"></i>
                                                                    <b>右眼：</b>
                                                                </li>
                                                                <li>
                                                                	<textarea class="form-control" name="rightresult" 
                                                                		<c:if test="${tbReport.initstatus == 0}">readonly</c:if> >${tbReport.rightresult}</textarea>
                                                                </li>

                                                            </ul>
                                                        </div>
                                                        
                                                        <div class="row">
                                                            <div class="col-xs-12 label label-lg label-info arrowed-in arrowed-right">
                                                                <b>医生建议</b>
                                                            </div>
                                                        </div>
                                                        <div>
                                                            <ul class="list-unstyled  spaced">
                                                                <li class="divider"></li>
                                                                
                                                                <li>
                                                                    <textarea class="form-control" name="doctorresult" >${tbReport.doctorresult}</textarea>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                        
                                                        <input type="hidden" name="id" value="${tbReport.id}" />
                                                        </form>
                                                        
                                                        <hr />
                                                        
                                                        <div class="clearfix form-actions">
															<div class="col-md-offset-4 col-md-8">
																<button class="btn btn-info" type="button" onclick="updateReport();">
																	<i class="ace-icon fa fa-check bigger-110"></i>
																	保存
																</button>
																&nbsp; &nbsp; &nbsp;
																<button class="btn" type="button" onclick="backHome();">
																	<i class="ace-icon fa fa-undo bigger-110"></i>
																	返回
																</button>
															</div>
														</div>

                                                    </div>
                                                    <!-- /.col -->
                                                    
                                                </div>
                                                <!-- /.row -->

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
				
			</div><!-- /.page-content -->
		</div>
	</div><!-- /.main-content -->
	
	<!-- footer -->
	<%@ include file="../../common/footer.jsp"%>
	
	</div><!-- /.main-container -->


	<!-- 自定义 js -->
	<script type="text/javascript" src="${ctx}/page/health/report/js/report.js"></script>

</body>
</html>