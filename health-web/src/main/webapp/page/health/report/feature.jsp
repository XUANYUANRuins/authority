<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="row">
	<div class="col-sm-6">
		<div class="row">
			<div
				class="col-xs-11 label label-lg label-warning arrowed-in arrowed-right">
				<b>左眼特征</b>
			</div>
		</div>

		<div>
			<ul class="list-unstyled spaced">
				<li style="display: none;"><i class="ace-icon fa fa-caret-right blue"></i>
					<label>体重指数：${tbHealthInfo.bmi}</label>
				</li>
			<c:forEach items="${feature}" var ="item" varStatus="status">
				<li><i class="ace-icon fa fa-caret-right blue"></i> 
					<label>${item.name }：</label>
					<input name="leftfeature" type="checkbox" <c:if test='${fn:contains(tbReport.leftfeature,item.code)}'>checked</c:if> value="${item.code}" />
				</li>
			</c:forEach>
			</ul>
		</div>
	</div>
	<!-- /.col -->

	<div class="col-sm-6">
		<div class="row">
			<div
				class="col-xs-11 label label-lg label-warning arrowed-in arrowed-right">
				<b>右眼特征</b>
			</div>
		</div>

		<div>
			<ul class="list-unstyled spaced">
				<li style="display: none;"><i class="ace-icon fa fa-caret-right blue"></i>
					<label>体重指数：${tbHealthInfo.bmi}</label>
				</li>
			<c:forEach items="${feature}" var ="item" varStatus="status">
				<li><i class="ace-icon fa fa-caret-right blue"></i> 
					<label>${item.name }：</label>
					<input name="rightfeature" type="checkbox" <c:if test='${fn:contains(tbReport.rightfeature,item.code)}'>checked</c:if> value="${item.code}" />
				</li>
			</c:forEach>
			</ul>
		</div>
	</div>
	<!-- /.col -->
</div>
<!-- /.row -->