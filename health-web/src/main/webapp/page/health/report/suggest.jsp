<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!--
	作者：修改
	时间：2018-03-21
	描述：
-->
<!--<div class="row">
	<div
		class="col-xs-12 label label-lg label-info arrowed-in arrowed-right">
		<b>医生建议</b>
	</div>
</div>-->
<div>
	<ul id="suggest_ul" class="list-unstyled  spaced">
       <li class="divider"></li>
       <c:forEach items="${suggest}" var ="item" varStatus="status">
		<c:if test='${fn:contains(tbReport.doctorresultopt,item.code)}'>
		<li>
			<label>${item.name }</label>
		</li>
		</c:if>
		</c:forEach>
		<c:if test="${empty tbReport.doctorresultopt && empty tbReport.doctorresult}">
		<li>
			
			医生诊断正在进行中，请稍候。
		</li>
		</c:if>
		<c:if test="${!empty tbReport.doctorresult}">
        <li>
            
            ${tbReport.doctorresult}
        </li>
       </c:if>
   </ul>
   <!--<button type="button" class="btn btn-warning btn-block" data-toggle="modal" data-target="#mySuggest">
       		<i class="ace-icon fa fa-pencil-square-o bigger-110"></i>
       		编辑医生建议
   </button>-->
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="mySuggest" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">医生建议</h4>
            </div>
            <div class="modal-body">
            
            	<ul id="suggest_edit" class="list-unstyled  spaced">
					 <c:forEach items="${suggest}" var ="item" varStatus="status">
						<li><i class="ace-icon fa fa-caret-right blue"></i>
							<input name="doctorresultopt" type="checkbox" <c:if test='${fn:contains(tbReport.doctorresultopt,item.code)}'>checked</c:if> value="${item.code }"/>
							<label>${item.name }</label>
						</li>
					 </c:forEach>
					<li>
						<textarea class="form-control" name="doctorresult" maxlength="200">${tbReport.doctorresult}</textarea>
					</li>
				</ul>
				
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-info" onclick="doctorEdit();" data-dismiss="modal">提交更改</button>
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
