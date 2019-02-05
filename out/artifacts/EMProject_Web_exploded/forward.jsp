<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<jsp:include page="/pages/plugins/include_javascript.jsp" /> 
<link rel="stylesheet" type="text/css" href="css/login.css" />
<script type="text/javascript" src="jquery/jquery.backstretch.min.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<script type="text/javascript" src="js/forward.js"></script>
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
						<div class="form-bottom" style="background: white;">
							<div>
								<div><%=request.getAttribute("msg")%></div>
								<div><span id="mytime">5</span>秒后跳转到<a id="goUrl" href="<%=basePath+request.getAttribute("url")%>">首页</a>！</div>
							</div>
							<script type="text/javascript">
								var goUrl = "<%=basePath+request.getAttribute("url")%>" ;

							</script>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
