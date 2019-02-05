<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String logOutUrl = "MemberLoginServlet/logout";
%>
<header class="main-header">

	<!-- Logo -->
	<a href="pages/index.jsp" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
		<span class="logo-mini"><b>H</b>R</span> <!-- logo for regular state and mobile devices -->
		<span class="logo-lg"><b>Human</b>Resource</span>
	</a>

	<!-- Header Navbar: style can be found in header.less -->
	<nav class="navbar navbar-static-top">
		<!-- Sidebar toggle button-->
		<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
			role="button"> <span class="sr-only">Toggle navigation</span>
		</a>
		<!-- Navbar Right Menu -->
		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
				<li class="dropdown user user-menu"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"> <img
						src="upload/emp/nophoto.png" class="user-image" alt="User Image">
						<span class="hidden-xs">${name}</span>
				</a>
					<ul class="dropdown-menu"> 
						<!-- User image -->
						<li class="user-header"><img src="upload/emp/nophoto.png"
							class="img-circle" alt="User Image">
							</li>
						<!-- Menu Body -->
						<li>
							<div class="text-center">

							</div>
						</li>
						<!-- Menu Footer-->
						<li class="user-footer">
							<div class="text-center">
								<a href="<%=logOutUrl%>" class="btn btn-default btn-flat">系统注销</a>
							</div>
						</li>
					</ul></li>
				<!-- Control Sidebar Toggle Button -->
				<li><a href="#" data-toggle="control-sidebar"><i
						class="fa fa-gears"></i></a></li>
			</ul>
		</div>

	</nav>
</header>