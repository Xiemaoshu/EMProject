<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/pages/plugins/include_static_head.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<jsp:include page="/pages/plugins/include_javascript_head.jsp" />
<script type="text/javascript" src="js/pages/back/dept/dept_list.js"></script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- 导入头部标题栏内容 -->
		<jsp:include page="/pages/plugins/include_title_head.jsp" />
		<!-- 导入左边菜单项 -->
		<jsp:include page="/pages/plugins/include_menu_item.jsp">
			<jsp:param name="role" value="member"/>
			<jsp:param name="action" value="member:list"/>
		</jsp:include>
		<div class="content-wrapper">
			<!-- 此处编写需要显示的页面 -->
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<!-- /.box-header -->
						<div class="box-body table-responsive no-padding">
							<div class="panel panel-danger">
								<div class="panel-heading">
									<strong><span class="glyphicon glyphicon-th-list"></span>&nbsp;管理员列表</strong>
								</div>
								<div class="panel-body" style="height : 95%;">
									<table class="table table-hover">
										<tr>
											<th width="60%">登录帐号</th>
											<th width="20%">姓名</th>
										</tr>
										<tr>
											<td>admin</td>
											<td>老张</td>
										</tr>
										<tr>
											<td>mermaid</td>
											<td>老李</td>
										</tr>
									</table>
									<jsp:include page="/pages/plugins/include_alert.jsp"/>
								</div> 
							</div>
						</div>
						<!-- /.box-body -->
					</div>
					<!-- /.box -->
				</div>
			</div>
		</div>
		<!-- 导入公司尾部认证信息 -->
		<jsp:include page="/pages/plugins/include_title_foot.jsp" />
		<!-- 导入右边工具设置栏 -->
		<jsp:include page="/pages/plugins/include_menu_sidebar.jsp" />
		<div class="control-sidebar-bg"></div>
	</div>
	<jsp:include page="/pages/plugins/include_javascript_foot.jsp" />
</body>
</html>
