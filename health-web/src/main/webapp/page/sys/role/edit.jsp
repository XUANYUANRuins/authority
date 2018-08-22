<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 添加和编辑 -->
<div class="modal fade in" id="editModal" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form id="editfm">
				<!-- 内容动态填充 -->
				<div class="modal-body">
					<div class="form-group">
						<label>角色名称</label> 
						<input class="form-control" name="name" type="text" maxlength="20" />
					</div>
					<div class="form-group">
						<label>角色编号</label> 
						<input class="form-control" name="roleCode" type="text" maxlength="20" />
					</div>
					<div class="form-group">
						<label data-localize="common.status">状态</label>
						<select class="form-control" name="status">
							<option value="0" data-localize="common.on">启用</option>
							<option value="1" data-localize="common.off">停用</option>
						</select>
					</div>
					<div class="form-group">
						<label>角色描述</label> 
						<input class="form-control" name="description" type="text" maxlength="50" />
					</div>
					<div class="form-group">
			            <label>拥有权限</label>
			            <div id="treeview"></div>
			        </div>
			        <input type="hidden" name="menuids" id="menuids" />
					<input type="hidden" name="id" />
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

