<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/pages/plugins/include_static_head.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<%
	String addEmpUrl = basePath + "pages/back/emp/emp_add.jsp" ;
	String editEmpUrl = basePath + "pages/back/emp/emp_edit.jsp" ;
%>
<jsp:include page="/pages/plugins/include_javascript_head.jsp" />
<script type="text/javascript" src="js/pages/back/emp/emp_list.js"></script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- 导入头部标题栏内容 -->
		<jsp:include page="/pages/plugins/include_title_head.jsp" />
		<!-- 导入左边菜单项 -->
		<jsp:include page="/pages/plugins/include_menu_item.jsp">
			<jsp:param name="role" value="emp"/>
			<jsp:param name="action" value="emp:list"/>
		</jsp:include>
		<div class="content-wrapper">
			<!-- 此处编写需要显示的页面 -->
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<!-- /.box-header -->
						<div class="box-body table-responsive no-padding">
							<div class="panel panel-info">
								<div class="panel-heading">
									<strong><span class="glyphicon glyphicon-user"></span>&nbsp;雇员信息列表</strong>
								</div>
								<div class="panel-body" style="height : 95%;">
									<jsp:include page="/pages/plugins/include_splitpage_search.jsp"/>
									<table class="table table-hover">
										<tr>
											<th width="5%" class="text-center"><input type="checkbox" id="selall"></th>
											<th width="15%" class="text-center">姓名</th> 
											<th width="15%" class="text-center">级别</th>
											<th width="15%" class="text-center">职位</th>
											<th width="10%" class="text-center">部门</th>
											<th width="10%" class="text-center">基本工资</th>
											<th width="10%" class="text-center">佣金</th>
											<th width="15%" class="text-center">雇佣日期</th>
											<th width="10%" class="text-center">操作</th>
										</tr>
										<tr>
											<td class="text-center"><input type="checkbox" id="empno" name="empno" value="1"></td>
											<td class="text-center">SMITH</td>
											<td class="text-center">实习生（T1）</td>
											<td class="text-center">网络管理</td>
											<td class="text-center">开发部</td>
											<td class="text-center">￥1700/月</td>
											<td class="text-center">￥0/月</td>
											<td class="text-center">2019-10-10</td>
											<td class="text-center"><a href="<%=editEmpUrl%>" class="btn btn-xs btn-primary"><span class="glyphicon glyphicon-edit"></span>&nbsp;编辑</a></td>
										</tr>
										<tr>
											<td class="text-center"><input type="checkbox" id="empno" name="empno" value="1"></td>
											<td class="text-center">SMITH</td>
											<td class="text-center">实习生（T1）</td>
											<td class="text-center">网络管理</td>
											<td class="text-center">开发部</td>
											<td class="text-center">￥1700/月</td>
											<td class="text-center">￥0/月</td>
											<td class="text-center">2019-10-10</td>
											<td class="text-center"><a href="<%=editEmpUrl%>" class="btn btn-xs btn-primary"><span class="glyphicon glyphicon-edit"></span>&nbsp;编辑</a></td>
										</tr>
									</table>
									<a href="<%=addEmpUrl%>" id="inBtn" class="btn btn-lg btn-primary"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;雇员入职</a>
									<button id="outBtn" class="btn btn-lg btn-danger"><span class="glyphicon glyphicon-remove"></span>&nbsp;雇员离职</button>
									<jsp:include page="/pages/plugins/include_splitpage_bar.jsp"/>
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
