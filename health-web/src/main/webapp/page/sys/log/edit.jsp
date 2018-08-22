<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 添加和编辑 -->
<div class="modal fade in" id="editModal" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form id="editfm">
				<!-- 内容动态填充 -->
				<div class="modal-body">
					<div class="form-group">
						<label data-localize="log.userName">登录名</label> 
						<input id="userName" class="form-control" name="userName" type="text" maxlength="20"/>
					</div>
					
					<div class="form-group">
						<label data-localize="log.ip">登录IP</label> 
						<input id="ip" class="form-control" name="ip" type="text" maxlength="20"/>
					</div>
					
					<div class="form-group">
						<label data-localize="log.realName">真实姓名</label> 
						<input id="realName" class="form-control" name="realName" type="text" maxlength="20"/>
					</div>
					
					<div class="form-group" style="display: none;">
						<label data-localize="log.loginTime">登录时间</label> 
						<div class="input-group input-group-sm">
							<input type="text" id="loginTime" name="loginTime" class="form-control form_datetime" />
							<!-- 图标 -->
							<span class="input-group-addon">
								<i class="ace-icon fa fa-calendar"></i>
							</span>
						</div>
					</div>
					
					<div class="form-group">
						<label data-localize="log.operateContent">操作内容</label> 
						<input id="operateContent" class="form-control" name="operateContent" type="text" maxlength="20"/>
					</div>
					
					<div class="form-group">
						<label data-localize="log.operateTime">操作时间</label> 
						<div class="input-group input-group-sm">
							<input type="text" id="operateTime" name="operateTime" class="form-control form_datetime" />
							<!-- 图标 -->
							<span class="input-group-addon">
								<i class="ace-icon fa fa-calendar"></i>
							</span>
						</div>
					</div>
					<input type="hidden" name="logId" id="log_id" />
				</div>
				<div class="modal-footer">
					<button class="btn btn-default" data-dismiss="modal">返回</button>
				</div>
			</form>
		</div>
	</div>
</div>