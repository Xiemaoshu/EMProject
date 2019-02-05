<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/pages/plugins/include_static_head.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<%
	String editEmpUrl = basePath + "pages/back/emp/EmpServlet/edit" ;
%>
<jsp:include page="/pages/plugins/include_javascript_head.jsp" />
<script type="text/javascript" src="js/pages/back/emp/emp_add.js"></script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- 导入头部标题栏内容 -->
		<jsp:include page="/pages/plugins/include_title_head.jsp" />
		<!-- 导入左边菜单项 -->
		<jsp:include page="/pages/plugins/include_menu_item.jsp">
			<jsp:param name="role" value="emp"/>
			<jsp:param name="action" value="emp:add"/>
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
									<strong><span class="glyphicon glyphicon-user"></span>&nbsp;编辑雇员信息</strong>
								</div>
								<div class="panel-body" style="height : 95%;">
									<form action="<%=editEmpUrl%>" id="myform" method="post" class="form-horizontal">
										<div class="form-group" id="emp.nameDiv">
											<label class="col-md-2 control-label" for="emp.name">雇员姓名：</label>
											<div class="col-md-5">
												<input type="text" name="emp.name" id="emp.name" class="form-control input-sm" placeholder="请输入雇员真实姓名">
											</div>
											<div class="col-md-4" id="emp.nameMsg">*</div>
										</div>
										<div class="form-group" id="emp.deptnoDiv">
											<label class="col-md-2 control-label" for="emp.deptno">所在部门：</label>
											<div class="col-md-5">
												<select id="emp.deptno" name="emp.deptno" class="form-control">
													<option value="1">销售部</option>
													<option value="2">研发部</option>
													<option value="3">后勤部</option>
												</select>
											</div> 
											<div class="col-md-4" id="emp.deptnoMsg">*</div>
										</div>
										<div class="form-group" id="emp.jobDiv">
											<label class="col-md-2 control-label" for="emp.job">雇员职位：</label>
											<div class="col-md-5">
												<input type="text" name="emp.job" id="emp.job" class="form-control input-sm" placeholder="请输入雇员职位">
											</div>
											<div class="col-md-4" id="emp.jobMsg">*</div>
										</div>
										<div class="form-group" id="emp.lidDiv">
											<label class="col-md-2 control-label" for="emp.lid">员工等级：</label>
											<div class="col-md-5">
												<select id="emp.lid" name="emp.lid" class="form-control">
													<option value="1">实习生</option>
													<option value="2">普通员工</option>
													<option value="3">小组组长</option>
													<option value="4">部门经理</option>
												</select>
											</div> 
											<div class="col-md-4" id="emp.lidMsg">*</div>
										</div>
										<div class="form-group" id="emp.salDiv">
											<label class="col-md-2 control-label" for="emp.sal">基本工资：</label>
											<div class="col-md-5">
												<input type="text" name="emp.sal" id="emp.sal" class="form-control input-sm" placeholder="请输入雇员基本工资，工资要与员工等级匹配">
											</div>
											<div class="col-md-4" id="emp.salMsg">*</div>
										</div>
										<div class="form-group" id="emp.commDiv">
											<label class="col-md-2 control-label" for="emp.comm">销售佣金：</label>
											<div class="col-md-5">
												<input type="text" name="emp.comm" id="emp.comm" class="form-control input-sm" placeholder="如果为销售人员，设置每月佣金">
											</div>
											<div class="col-md-4" id="emp.commMsg"></div>
										</div>
										<div class="form-group" id="emp.photoDiv">
											<label class="col-md-2 control-label" for="emp.comm">员工照片：</label>
											<div class="col-md-5">
												<input type="file" name="emp.photo" id="emp.photo" class="form-control input-sm" placeholder="请上传员工照片">
											</div>
											<div class="col-md-4" id="emp.photoMsg"></div>
										</div>
										<div class="form-group" id="member.noteDiv"> 
											<label class="col-md-2 control-label" for="note">员工简介：</label>
											<div class="col-md-5">
												<textarea rows="6" name="note" id="note" class="form-control input-sm" placeholder="将员工基本信息进行简要描述"></textarea>
											</div>
											<div class="col-md-4" id="noteMsg">*</div>
										</div>  
										<div class="form-group"> 
											<div class="col-md-offset-2 col-md-5">
												<input type="hidden" name="emp.empno" value="10" class="btn btn btn-primary">
												<input type="submit" value="修改" class="btn btn btn-primary">
												<input type="reset" value="重置" class="btn btn btn-warning">
											</div>
										</div>
									</form>
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
