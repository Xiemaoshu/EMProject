<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String loginUrl = basePath + "MemberLoginServlet/login" ;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<jsp:include page="/pages/plugins/include_javascript.jsp" />
<link rel="stylesheet" type="text/css" href="css/login.css" />
<script type="text/javascript" src="jquery/jquery.backstretch.min.js"></script>
<script type="text/javascript" src="js/login.js"></script>
</head>
<body>
	<div class="top-content">
		<div class="inner-bgx">
			<div class="container">
				<div class="row">
					<div class="col-sm-6 col-sm-offset-3 form-box">
						<div class="form-top">
							<div class="form-top-left">
								<h3>企业人事管理系统</h3>
							</div>
							<div class="form-top-right">
								<i class="fa fa-lock"><img src="images/login-lock.png"></i>
							</div>
						</div>
						<div class="form-bottom">
							<form role="form" action="<%=loginUrl%>" method="post" class="form-horizontal" id="myform">
								<div class="form-group" id="member.midDiv">
									<div class="col-md-12">
										<!-- 定义表单输入组件 -->
										<input type="text" id="member.mid" name="member.mid" class="form-control"
											placeholder="请输入登录帐号">
									</div>
								</div>
								<div class="form-group" id="member.passwordDiv">
									<div class="col-md-12">
										<!-- 定义表单输入组件 -->
										<input type="password" id="member.password" name="member.password" class="form-control"
											placeholder="请输入登录密码...">
									</div>
								</div>
								<div class="form-group" id="codeDiv">
									<div class="col-md-6">
										<!-- 定义表单输入组件 -->
										<input type="text" id="code" name="code" class="form-control"
											placeholder="验证码..." size="4" maxlength="4" style="width:410px;">
									</div>
									<div class="col-md-1 col-md-offset-3"><img id="imageCode" src="captcha.jpg"></div>
									<div class="col-md-2 col-md-pull-1">
									</div>
								</div>
								<button type="submit" class="btn">登录系统</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
