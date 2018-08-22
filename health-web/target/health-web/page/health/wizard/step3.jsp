<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="step-pane" data-step="3" style="min-height:480px;">

	<div class="col-sm-11 col-md-offset-2">
		<div class="row" >
			<div>
				<form id="uploadform" action="${ctx}/consultation/upload" method="post" enctype="multipart/form-data">
					<div class="img-box col-sm-4">
						<section class="img-section">
							<p class="up-p">请上传左眼图像</p>
							<div class="z_photo upimg-div clear" >
					               	 <section class="z_file fl">
					               	 	<img src="${impUpPath}/img/a11.png" class="add-img" id="m1">
					               	 	<input type="file" name="leftImg" id="file1" class="file" value="" accept="image/jpg,image/jpeg,image/png,image/bmp" />
					               	 </section>
					         </div>
						 </section>
					</div>
					
					<div class="img-box col-sm-4">
						<section class="img-section">
							<p class="up-p">请上传右眼图像</p>
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
					
					<!-- 隐藏控件 -->
					<!-- 基本信息 -->
					<input type="hidden" name="idcard" />
					<input type="hidden" name="name" />
					<input type="hidden" name="sex" />
					<input type="hidden" name="birthday" />
					<input type="hidden" name="tel" />
					<input type="hidden" name="address" />
					<input type="hidden" name="guardian" />
					<input type="hidden" name="guardianphone" />
					<input type="hidden" name="hospital" />
					<input type="hidden" name="doctor" />
					<input type="hidden" name="remark" />
					<!-- 健康表 -->
					<input type="hidden" name="height" />
					<input type="hidden" name="weight" />
					<input type="hidden" name="bmi" />
					<input type="hidden" name="waist" />
					<input type="hidden" name="menopause" />
					<input type="hidden" name="bloodpressure" />
					<input type="hidden" name="pressure" />
					<input type="hidden" name="state" />
					<input type="hidden" name="labour" />
					<input type="hidden" name="fruit" />
					<input type="hidden" name="drink" />
					<input type="hidden" name="diabetes" />
<!-- 					<input type="hidden" name="diagnose1" /> -->
					<input type="hidden" name="relatives" />
					<input type="hidden" name="glaucoma" />
<!-- 					<input type="hidden" name="diagnose2" /> -->
					<input type="hidden" name="hypertension" />
<!-- 					<input type="hidden" name="diagnose3" /> -->
					<input type="hidden" name="cholesterol" />
					<input type="hidden" name="medicine" />
					<input type="hidden" name="symptom" />
					<input type="hidden" name="leftvision" />
					<input type="hidden" name="rightvision" />
					<input type="hidden" id="isNone" name="isNone" value="0"/>
					<input type="hidden" name="createid" value="${sessionScope.currentUser.id}" />
					
				</form>
			</div>
		</div>
		
	</div>
	
</div>


<!-- loading -->
<div class="modal fade" id="loading" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop='static'>
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


