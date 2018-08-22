<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="sidebar" class="sidebar responsive ace-save-state">
	<script type="text/javascript">
		try{ace.settings.loadState('sidebar')}catch(e){}
	</script>


	<ul class="nav nav-list" id="menu">
		<li class="" id="status0">
			<a href="${ctx}/page/main.jsp">
				<i class="menu-icon fa icon-home" style="font-size: 24px !important;"></i>
				<span class="menu-text"> 首页 </span>
			</a>

			<b class="arrow"></b>
		</li>
		
		<!-- 菜单 -->
		<c:forEach var="tree" items="${sessionScope.menus}" varStatus="status">
		<li class="" id="status${status.index+1 }">
			<a href="${ctx}${tree.url}" class="dropdown-toggle">
				<i class="menu-icon fa  icon-doctoractive"></i>
				<span class="menu-text"> ${tree.text} </span>
				<b class="arrow fa fa-angle-down"></b>
			</a>
			<b class="arrow"></b>

			<ul class="submenu">
				<c:forEach var="child" items="${tree.nodes}">
				<li class="" data-status="${status.index+1 }">
					<a href="${ctx}${child.url}">
						<i class="menu-icon fa fa-caret-right"></i>
						${child.text}
					</a>
					<b class="arrow"></b>
				</li>
				</c:forEach>
			</ul>
		</li>
		</c:forEach>
		
		<script type="text/javascript">
		
			var urlstr = location.href;
			// 特殊处理一些链接---会诊
			if(urlstr.indexOf("consultation") > -1) {
				urlstr = ctx+"/page/health/homepage/index.jsp";
			}
			
			var index=0;
			$("#menu li").each(function () {
				var href = $(this).find("a").attr('href');
				if (urlstr.indexOf(href) > -1 && href!='') {
					$(this).addClass('active');
					index = $(this).attr('data-status');
			    } else {
					$(this).removeClass('active');
				}
			});
	
			if (index!=0) {
				$("#status"+index).addClass('active open');
			}
		
		</script>
		
	</ul><!-- /.nav-list -->

	<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
		<i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
	</div>
</div>


