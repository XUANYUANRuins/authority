<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 添加和编辑 -->
<div class="modal fade in" id="editModal" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form id="editfm">
				<!-- 内容动态填充 -->
				<div class="modal-body">
					<div class="form-group">
						<label data-localize="terminal.cameraBrand">相机品牌<font color="red">*</font></label> 
						<input id="cameraBrand" class="form-control" name="cameraBrand" type="text" maxlength="20"/>
					</div>
					
					<div class="form-group">
						<label data-localize="terminal.cameraModel">相机型号<font color="red">*</font></label> 
						<input id="cameraModel" class="form-control" name="cameraModel" type="text" maxlength="20"/>
					</div>
					
					<div class="form-group">
						<label data-localize="terminal.trmlId">设备编号<font color="red">*</font></label> 
						<input id="trmlId" class="form-control" name="trmlId" type="text" maxlength="20"/>
					</div>
					
					<!--<div class="form-group">
						<label data-localize="terminal.status">状态</label>
						<select class="form-control" name="status">
							<option value="1" data-localize="terminal.on">有效</option>
							<option value="2" data-localize="terminal.off">无效</option>
						</select>
					</div>-->
					
					<!--<div class="form-group">
						<label data-localize="terminal.orgId">所属机构</label> 
						<input id="orgId" class="form-control" name="orgId" type="text" maxlength="20"/>
					</div>-->
					
					<div class="form-group">
						<label data-localize="terminal.orgId">所属机构<font color="red">*</font></label> 
						<select class="form-control" name="orgId" id="orgId"></select>
					</div>
					
					<div class="form-group">
						<label data-localize="terminal.cameraProducer">生产地</label> 
						<input id="cameraProducer" class="form-control" name="cameraProducer" type="text" maxlength="20"/>
					</div>
					
					<div class="form-group">
						<label data-localize="terminal.cameraProduceTime">生产时间</label> 
						<div class="input-group input-group-sm">
							<input type="text" id="cameraProduceTime" name="cameraProduceTime" class="form-control form_datetime" />
							<!-- 图标 -->
							<span class="input-group-addon">
								<i class="ace-icon fa fa-calendar"></i>
							</span>
						</div>
					</div>
					
					<div class="form-group">
						<label data-localize="terminal.cameraBuyTime">购置时间</label> 
						<div class="input-group input-group-sm">
							<input type="text" id="cameraBuyTime" name="cameraBuyTime" class="form-control form_datetime" />
							<!-- 图标 -->
							<span class="input-group-addon">
								<i class="ace-icon fa fa-calendar"></i>
							</span>
						</div>
					</div>
					
					<div class="form-group">
						<label data-localize="terminal.centralResolution">中心分辨率<font color="red">*</font></label> 
						<input id="centralResolution" class="form-control" name="centralResolution" type="text" maxlength="20"/>
					</div>
					
					<div class="form-group">
						<label data-localize="terminal.cameraAngle">视角</label> 
						<input id="cameraAngle" class="form-control" name="cameraAngle" type="text" maxlength="3"/>
					</div>
					
					<div class="form-group">
						<label data-localize="terminal.ccdResolution">CCD分辨率<font color="red">*</font></label> 
						<input id="ccdResolution" class="form-control" name="ccdResolution" type="text" maxlength="20"/>
					</div>
					
					<div class="form-group">
						<label data-localize="terminal.trmlIp">终端IP</label> 
						<input id="trmlIp" class="form-control" name="trmlIp" type="text" maxlength="20"/>
					</div>
					
					<div class="form-group">
						<label data-localize="terminal.trmlPort">终端端口</label> 
						<input id="trmlPort" class="form-control" name="trmlPort" type="text" maxlength="20"/>
					</div>
					<input type="hidden" name="id" id="trml_id" />
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" onclick="update()" data-localize="button.save" id="saveBtn">保存</button>
					<button class="btn btn-default" data-dismiss="modal" data-localize="button.cancel" id="cancleBtn">取消</button>
					<button class="btn btn-default" data-dismiss="modal" id="returnBtn" style="display: none;">返回</button>
				</div>
			</form>

		</div>
	</div>
</div>