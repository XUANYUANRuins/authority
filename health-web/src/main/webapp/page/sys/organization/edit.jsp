<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 添加和编辑 -->
<div class="modal fade in" id="editModal" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form id="editfm">
				<!-- 内容动态填充 -->
				<div class="modal-body">
					<div class="form-group">
						<label data-localize="organization.orgName">机构名称<font color="red">*</font></label> 
						<input id="orgName" class="form-control" name="orgName" type="text" maxlength="20"/>
					</div>
					
					<div class="form-group">
						<label data-localize="organization.orgId">机构编号<font color="red">*</font></label> 
						<input id="orgId" class="form-control" name="orgId" type="text" maxlength="20"/>
					</div>
					
					<div class="form-group">
						<label data-localize="organization.status">状态</label>
						<select class="form-control" name="status">
							<option value="1" data-localize="organization.on">有效</option>
							<option value="2" data-localize="organization.off">无效</option>
						</select>
					</div>
					
					<div class="form-group">
						<label data-localize="organization.fixedTelephone">固定电话</label> 
						<input id="fixedTelephone" class="form-control" name="fixedTelephone" type="text" maxlength="20"/>
					</div>
					
					<div class="form-group">
						<label data-localize="organization.orgPhone">机构电话<font color="red">*</font></label> 
						<input id="orgPhone" class="form-control" name="orgPhone" type="text" maxlength="20"/>
					</div>
					
					<div class="form-group">
						<label data-localize="organization.linkman">联系人</label> 
						<input id="linkman" class="form-control" name="linkman" type="text" maxlength="20"/>
					</div>
					<div class="form-group">
						<label data-localize="organization.orgAddress">机构详细地址</label> 
						<input id="orgAddress" class="form-control" name="orgAddress" type="text" maxlength="20"/>
					</div>
					<input type="hidden" name="id" id="org_id" />
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