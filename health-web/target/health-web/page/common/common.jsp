<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="curl" value="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${ctx}" />

<meta name="renderer" content="webkit"> 
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="${ctx}/static/assets/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctx}/static/assets/font-awesome/4.5.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="${ctx}/page/health/report/css/bootstrap-grid.min.css">

<!-- page specific plugin styles -->

<!-- text fonts -->
<link rel="stylesheet" href="${ctx}/static/assets/css/fonts.googleapis.com.css" />

<!-- ace styles -->
<link rel="stylesheet" href="${ctx}/static/assets/css/ace.min.css" />

<!--[if lte IE 9]>
	<link rel="stylesheet" href="${ctx}/static/assets/css/ace-part2.min.css" />
<![endif]-->
<link rel="stylesheet" href="${ctx}/static/assets/css/ace-skins.min.css" />
<link rel="stylesheet" href="${ctx}/static/assets/css/ace-rtl.min.css" />

<!--[if lte IE 9]>
  <link rel="stylesheet" href="${ctx}/static/assets/css/ace-ie.min.css" />
<![endif]-->

<!-- jquery-confirm css -->
<link rel="stylesheet" href="${ctx}/static/assets/css/jquery-confirm.min.css" />

<!-- validator css -->
<link rel="stylesheet" href="${ctx}/static/assets/css/bootstrap-validator.min.css" />

<!-- CSS -->
<link rel="stylesheet" href="${ctx}/static/css/style.css" />

<!-- icon CSS -->
<link rel="stylesheet" href="${ctx}/static/icomoon/icon.css" />

<!-- web CSS -->
<link rel="stylesheet" href="${ctx}/static/icomoon/icon-files/icon-files.css" />

<!-- inline styles related to this page -->

<!-- ace settings handler -->
<script src="${ctx}/static/assets/js/ace-extra.min.js"></script>

<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

<!--[if lte IE 8]>
<script src="${ctx}/static/assets/js/html5shiv.min.js"></script>
<script src="${ctx}/static/assets/js/respond.min.js"></script>
<![endif]-->

<!-- basic scripts -->

<!--[if !IE]> -->
<script src="${ctx}/static/assets/js/jquery-2.1.4.min.js"></script>

<!-- <![endif]-->

<!--[if IE]>
<script src="${ctx}/static/assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
<!--  
<script type="text/javascript">
	if('ontouchstart' in document.documentElement) document.write("<script src='${ctx}/static/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>
-->
<script src="${ctx}/static/assets/js/bootstrap.min.js"></script>

<script src="${ctx}/static/assets/js/bootstrap-validator.min.js"></script>
<script src="${ctx}/static/assets/js/bootstrap-validator_zh_CN.js"></script>

<!-- ace scripts -->
<script src="${ctx}/static/assets/js/ace-elements.min.js"></script>
<script src="${ctx}/static/assets/js/ace.min.js"></script>

<!-- i18n -->
<script src="${ctx}/static/plugins/i18n/jquery.i18n.properties.min.js"></script>

<!-- Bootbox -->
<script src="${ctx}/static/assets/js/bootbox.js"></script>
<!-- alert message -->
<script src="${ctx}/static/js/alert.js"></script>

<!-- web -->
<script src="${ctx}/static/icomoon/icon-files/icon-files.js"></script>

<script type="text/javascript">

	var ctx = '${ctx}'; /* js全局url */
	
	// 国际化
	$(document).ready(function(){
	    loadProperties('message', ctx+'/static/plugins/i18n/message/');
	});
	
	function loadProperties(name, path, lang){
	    //var lang = lang || navigator.language;	//根据浏览器获取语言
	    var lang =  lang || "zh_CN";
	    jQuery.i18n.properties({
	        name:name, 
	        path:path,
	        mode:'map',
	        language: lang,
	        callback: function() {
	            $("[data-localize]").each(function() {
	                var elem = $(this),
	                    localizedValue = jQuery.i18n.map[elem.data("localize")];
	                
	                if (elem.is("input[type=text]") || elem.is("input[type=password]") || elem.is("input[type=email]")) {
	                    elem.attr("placeholder", localizedValue);
	                } else if (elem.is("input[type=button]") || elem.is("input[type=submit]")) {
	                    elem.attr("value", localizedValue);
	                } else {
	                    elem.text(localizedValue);
	                }
	            });
	        }
	    });
	}
	
	
</script>


