<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript">
	window.onload = function(){
		var left_textarea_value = $("#left_edit textarea").val();
		left_textarea_value = left_textarea_value.replace(/<br\w*\/*>/g,"\n");
		$("#left_edit textarea").val(left_textarea_value);
		
		var right_textarea_value = $("#right_edit textarea").val();
		right_textarea_value = right_textarea_value.replace(/<br\w*\/*>/g,"\n");
		$("#right_edit textarea").val(right_textarea_value);
	}
</script>
<div class="row">
	<div class="col-sm-6">
		<div class="row">
			<div
				class="col-xs-11 label label-lg label-success arrowed-in arrowed-right">
				<b>左眼诊断结果</b>
			</div>
		</div>

		<div>
			<ul id="left_ul" class="list-unstyled spaced">
				 <c:forEach items="${result}" var ="item" varStatus="status">
					<c:if test='${tbReport.leftresultopt eq item.code}'>
					<li><i class="ace-icon fa fa-caret-right blue"></i>
						<label>${item.name }</label>
					</li>
					</c:if>
				 </c:forEach>
			 	<c:if test="${empty tbReport.leftresultopt && empty tbReport.leftresult}">
					<li>
						<i class="ace-icon fa fa-caret-right red"></i>
						系统诊断正在进行中，请稍候。
					</li>
			 	</c:if>
			 	<c:if test="${!empty tbReport.leftresult}"> 
					<li>
	                	${tbReport.leftresult }
	                </li>
	             </c:if>
			</ul>
		</div>
	</div>
	<!-- /.col -->

	<div class="col-sm-6">
		<div class="row">
			<div
				class="col-xs-11 label label-lg label-success arrowed-in arrowed-right">
				<b>右眼诊断结果</b>
			</div>
		</div>

		<div>
			<ul id="right_ul" class="list-unstyled spaced">
				<c:forEach items="${result}" var ="item" varStatus="status">
				<c:if test='${tbReport.rightresultopt eq item.code}'>
				<li><i class="ace-icon fa fa-caret-right blue"></i>
					<label>${item.name }</label>
				</li>
				</c:if>
				</c:forEach>
				<c:if test="${empty tbReport.rightresultopt && empty tbReport.rightresult}">
				<li>
					<i class="ace-icon fa fa-caret-right red"></i>
					系统诊断正在进行中，请稍候。
				</li>
				</c:if>
				<c:if test="${!empty tbReport.rightresult}"> 
				<li>
	            	${tbReport.rightresult }
	            </li>
	           </c:if>
			</ul>
		</div>
	</div>
	<!-- /.col -->
	<button type="button" class="btn btn-warning btn-block" data-toggle="modal" data-target="#myInitResult">
       		<i class="ace-icon fa fa-pencil-square-o bigger-110"></i>
       		编辑左右眼诊断结果
   </button>
</div>
<!-- /.row -->

<!-- 模态框（Modal） -->
<div class="modal fade" id="myInitResult" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">
    <div class="modal-dialog" style="width:900px">
        <div class="modal-content">
            <div class="modal-body">
            	<div class="col-sm-6">
					<div class="row">
						<div
							class="col-xs-11 label label-lg label-success arrowed-in arrowed-right">
							<b>左眼诊断结果</b>
						</div>
					</div>
			
					<div>
						<ul id="left_edit" class="list-unstyled spaced">
						 <c:forEach items="${result}" var ="item" varStatus="status">
							<li><i class="ace-icon fa fa-caret-right blue"></i>
								<input name="leftresultopt" type="radio" <c:if test='${tbReport.leftresultopt eq item.code}'>checked</c:if>  value="${item.code }"/>
								<label>${item.name }</label>
							</li>
						 </c:forEach>
							<li>
			                	<textarea class="form-control" name="leftresult" maxlength="200">${tbReport.leftresult }</textarea>
			                </li>
						</ul>
					</div>
				</div>
				<!-- /.col -->
			
				<div class="col-sm-6">
					<div class="row">
						<div
							class="col-xs-11 label label-lg label-success arrowed-in arrowed-right">
							<b>右眼诊断结果</b>
						</div>
					</div>
			
					<div>
						<ul id="right_edit" class="list-unstyled spaced">
						 <c:forEach items="${result}" var ="item" varStatus="status">
							<li><i class="ace-icon fa fa-caret-right blue"></i>
								<input name="rightresultopt" type="radio" <c:if test='${tbReport.rightresultopt eq item.code}'>checked</c:if>  value="${item.code }"/>
								<label>${item.name }</label>
							</li>
						 </c:forEach>
							<li>
			                	<textarea class="form-control" name="rightresult" maxlength="200">${tbReport.rightresult }</textarea>
			                </li>
						</ul>
					</div>
				</div>
				
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-info" onclick="initResultEdit();" data-dismiss="modal">提交更改</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>