<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="navbar" class="navbar navbar-default          ace-save-state">
	<div class="navbar-container ace-save-state" id="navbar-container">
		<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
			<span class="sr-only">Toggle sidebar</span>
			
			<span class="icon-bar"></span>
			
			<span class="icon-bar"></span>
			
			<span class="icon-bar"></span>
		</button>

		<div class="navbar-header pull-left">
			<a href="${ctx}/page/main.jsp" class="navbar-brand">
				<small>
					<i class="fa fa-eye"></i>
					<span>看健康</span>
				</small>
			</a>
		</div>

		<div class="navbar-buttons navbar-header pull-right" role="navigation">
			<ul class="nav ace-nav">

				<li class="light-blue dropdown-modal">
					<a data-toggle="dropdown" href="#" class="dropdown-toggle">
						<span>
							<!-- <small>Welcome,</small> -->
							${sessionScope.currentUser.realname}
						</span>

						<i class="ace-icon fa fa-caret-down"></i>
					</a>

					<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-blue dropdown-caret dropdown-close">
						 <li>
							<a href="${ctx}/page/sys/user/modifyPW.jsp">
								<i class="ace-icon fa fa-cog"></i>
								<!-- Settings -->
								<span data-localize="common.modifyPW">修改密码 </span>
							</a>
						</li>

						<!-- <li>
							<a href="profile.html">
								<i class="ace-icon fa fa-user"></i>
								Profile
							</a>
						</li> -->

						<li class="divider"></li>

						<li>
							<a href="${ctx}/logout">
								<i class="ace-icon fa fa-power-off"></i>
								<!-- Logout -->
								<span data-localize="common.logout">退出</span>
							</a>
						</li>
					</ul>
				</li>
			</ul>
		</div>
	</div><!-- /.navbar-container -->
</div>
	