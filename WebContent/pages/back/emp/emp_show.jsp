<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/pages/plugins/include_static_head.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<jsp:include page="/pages/plugins/include_javascript_head.jsp" />
<script type="text/javascript" src="<%=basePath%>/js/pages/back/emp/emp_show.js"></script>
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
									<strong><span class="glyphicon glyphicon-user"></span>&nbsp;雇员详细信息</strong>
								</div>
								<div class="panel-body" style="height : 95%;">
									<div class="row">
										<div class="col-md-2">
											<img src="upload/emp/${emp.photo}" class="img-responsive img-bordered-sm" alt="">
										</div>
										<div class="col-md-5">
											<div class="row">
												<div class="col-md-2 text-left">雇员编号</div>
												<div class="col-md-5 ">${emp.empno}</div>
											</div>
											<div class="row">
												<div class="col-md-2 text-left">雇员姓名</div>
												<div class="col-md-5 ">${emp.ename}</div>
											</div>
											<div class="row">
												<div class="col-md-2 text-left">部门编号</div>
												<div class="col-md-5 ">${emp.deptno}</div>
											</div>
											<div class="row">
												<div class="col-md-2 text-left">操作员</div>
												<div class="col-md-5 ">${emp.mid}</div>
											</div>
											<div class="row">
												<div class="col-md-2 text-left">工资等级</div>
												<div class="col-md-5 ">${level.title}</div>
											</div>
											<div class="row">
												<div class="col-md-2 text-left">职业</div>
												<div class="col-md-5 ">${emp.job}</div>
											</div>
											<div class="row">
												<div class="col-md-2 text-left">基本工资</div>
												<div class="col-md-5 ">${emp.sal}</div>
											</div>
											<div class="row">
												<div class="col-md-2 text-left">销售佣金</div>
												<div class="col-md-5 ">${emp.comm}</div>
											</div>
											<div class="row">
												<div class="col-md-2 text-left">入职日期</div>
												<div class="col-md-5 ">${emp.hiredate}</div>
											</div>
											<div class="row">
												<div class="col-md-2 text-left">在职状态</div>
												<c:if test="${emp.flag==0}">
													<div class="col-md-5  text-danger">离职</div>
												</c:if>
												<c:if test="${emp.flag==1}">
													<div class="col-md-5  text-success">在职</div>
												</c:if>

											</div>
										</div>

									</div>
									<h5 class="divider"></h5>
									<div class="row">
										<div class="panel panel-success">
											<div class="panel-heading">
												<strong><span class="glyphicon glyphicon-user"></span>&nbsp;雇员所在部门详情信息</strong>
											</div>
											<div class="panel-body">
												<div class="col-md-7">
													<div class="row">
														<div class="col-md-2 text-left">部门编号</div>
														<div class="col-md-5 ">${dept.deptno}</div>
													</div>
													<div class="row">
														<div class="col-md-2 text-left">部门名称</div>
														<div class="col-md-5 ">${dept.dname}</div>
													</div>
													<div class="row">
														<div class="col-md-2 text-left">部门最大人数</div>
														<div class="col-md-5 ">${dept.maxnum}</div>
													</div>
													<div class="row">
														<div class="col-md-2 text-left">部门当前人数</div>
														<div class="col-md-5 ">${dept.currnum}</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="panel panel-warning">
											<div class="panel-heading">
												<strong><span class="glyphicon glyphicon-user"></span>&nbsp;雇员所处的工资等级详情</strong>
											</div>
											<div class="panel-body">
												<table class="table table-hover">
													<tr>
														<th>工资等级</th>
														<th>等级名称</th>
														<th>最低工资</th>
														<th>最高工资</th>

													</tr>
														<tr>
															<td>${level.flag}</td>
															<td>${level.title}</td>
															<td>${level.losal}</td>
															<td>${level.hisal}</td>
														</tr>
												</table>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="panel panel-warning">
											<div class="panel-heading">
												<strong><span class="glyphicon glyphicon-user"></span>&nbsp;雇员所有日志详情信息</strong>
											</div>
											<div class="panel-body">
												<table class="table table-hover">
													<tr>
														<th>操作员编号</th>
														<th>职位变化</th>
														<th>所在部门变化</th>
														<th>工资调整</th>
														<th>佣金变化</th>
														<th>在职状态</th>
														<th>修改说明</th>
													</tr>
													<c:forEach items="${allElogs}" var="elog">
														<tr>
														<td>${elog.mid}</td>
														<td>${elog.job}</td>
														<td>${elog.deptno}</td>
														<td>
																${elog.sal}
																<c:if test="${elog.sflag == 0}">
																	<span class="glyphicon glyphicon-check"></span>
																</c:if>
																<c:if test="${elog.sflag == 1}">
																	<span class="text-success"><span class="glyphicon glyphicon-plus-sign"></span></span>
																</c:if>
																<c:if test="${elog.sflag == 2}">
																	<span class="text-danger"><span class="glyphicon glyphicon-minus-sign"></span></span>
																</c:if>
																<c:if test="${elog.sflag == 3}">
																	<span class="text-warning"><span class="glyphicon glyphicon-info-sign"></span></span>
																</c:if>
														</td>
														<td>${elog.comm}</td>
														<td>${elog.flag}</td>
														<td>${elog.note}</td>
														</tr>
													</c:forEach>
												</table>
											</div>
										</div>
									</div>

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
