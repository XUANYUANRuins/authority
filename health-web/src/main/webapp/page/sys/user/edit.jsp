<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 添加和编辑 -->
<div class="modal fade in" id="editModal" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form id="editfm">
				<!-- 内容动态填充 -->
				<div class="modal-body">
					<div class="form-group">
						<label data-localize="user.loginname">用户名</label> 
						<input id="loginname" class="form-control" name="loginname" type="text" maxlength="20"/>
					</div>
					<div class="form-group">
						<label data-localize="user.password">密码</label>
						<div style="float:left;width: 100%;">
							<input class="form-control" name="password" type="password" readonly style="float:left;width: 80%;" maxlength="50"/>
							<button class="btn btn-sm btn-primary" name="button" type="button" style="float:right;" onclick="resetPassword()" id="resetPwd">
							<i class="icon-save"></i>
								重置密码
							</button>
						</div>		
					</div>
					<div class="form-group">
						<label>真实姓名</label> 
						<input class="form-control" name="realname" type="text" maxlength="20"/>
					</div>
					<!--<div class="form-group">
						<label data-localize="user.position">职位</label> 
						<select class="form-control" name="position" id="position"></select>
					</div>-->
					<div class="form-group">
						<label data-localize="common.status">状态</label>
						<select class="form-control" name="status">
							<option value="0" data-localize="common.on">启用</option>
							<option value="1" data-localize="common.off">停用</option>
						</select>
					</div>
					<div class="form-group">
						<label data-localize="user.phone">手机号</label> 
						<input id="phone" class="form-control" name="phone" type="text" maxlength="11"/>
					</div>
					<div class="form-group">
						<label data-localize="user.email">邮箱</label> 
						<input id="email" class="form-control" name="email" type="text" maxlength="20"/>
					</div>
					
					<div class="form-group">
						<label data-localize="user.orgId">所属机构<font color="red">*</font></label> 
						<select class="form-control" name="orgId" id="orgId" onchange="refleshTerminalList();"></select>
					</div>
					
					<div class="form-group">
			            <label data-localize="user.role">所属角色</label>
			            <div id="treeview"></div>
			        </div>
			        
			        <div class="form-group">
			            <label>操作设备</label>
			            <div id="treeview1"></div>
			        </div>
			        <input type="hidden" name="roleids" id="roleids" />
			        <input type="hidden" name="terminalids" id="terminalids" />
					<input type="hidden" name="id" id="userid" />
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