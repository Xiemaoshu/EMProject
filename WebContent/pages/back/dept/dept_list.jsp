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
			<jsp:param name="role" value="dept"/>
			<jsp:param name="action" value="dept:list"/>
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
									<strong><span class="glyphicon glyphicon-th-list"></span>&nbsp;部门信息列表</strong>
								</div>
								<div class="panel-body" style="height : 95%;">
									<table class="table table-hover">
										<tr>
											<th width="60%">部门名称</th>
											<th width="20%">部门最大人数</th>
											<th width="20%">部门当前人数</th>
										</tr>
										<tr>
											<td><span id="dname-1">财务部</span></td>
											<td>
												<input type="text" class="input-sm" name="maxnum-1" id="maxnum-1" value="3" style="width:20%">
												<button class="btn btn-info" id="editBtn-1"><span class="fa fa-pencil-square"></span>&nbsp;调整人数上限</button>
											</td>
											<td><a id="showBtn-1" class="btn btn-warning" title="查看本部门雇员信息">1位雇员</a></td>
										</tr>
										<tr> 
											<td><span id="dname-2">销售部</span></td>
											<td>
												<input type="text" class="input-sm" name="maxnum-2" id="maxnum-2" value="10" style="width:20%">
												<button class="btn btn-info" id="editBtn-2"><span class="fa fa-pencil-square"></span>&nbsp;调整人数上限</button>
											</td>
											<td><a id="showBtn-2" class="btn btn-warning">2位雇员</a></td>
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
	<div class="modal fade" id="empInfo"  tabindex="-1" aria-labelledby="modalTitle" aria-hidden="true" data-keyboard="true">
		<div class="modal-dialog" style="width: 1200px">
			<div class="modal-content">
				<div class="modal-header">
					<button class="close" type="button" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h3 class="modal-title">
						<span class="glyphicon glyphicon-folder-open"></span>
						<strong>查看“<span id="deptTitleSpan"></span>”信息</strong></h3>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<table id="actionsTab" class="table table-bordered table-hover table-condensed table-responsive">
								<thead>
									<tr>
										<td style="width:10%;" class="text-center"><strong>照片</strong></td> 
										<td style="width:10%;" class="text-center"><strong>姓名</strong></td> 
										<td style="width:20%;" class="text-center"><strong>职位</strong></td> 
										<td style="width:15%;" class="text-center"><strong>级别</strong></td> 
										<td style="width:15%;" class="text-center"><strong>工资</strong></td>
										<td style="width:15%;" class="text-center"><strong>佣金</strong></td>
										<td style="width:15%;" class="text-center"><strong>雇佣日期</strong></td>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td class="text-center"><img src="upload/emp/nophoto.png" style="width:30px;"></td> 
										<td class="text-center">张三</td>
										<td class="text-center">工程师</td>
										<td class="text-center">实习生（T1）</td>
										<td class="text-center">￥1700/月</td>
										<td class="text-center">￥0/月</td>
										<td class="text-center">2019-10-10</td>
									</tr>
									<tr>
										<td class="text-center"><img src="upload/emp/nophoto.png" style="width:30px;"></td> 
										<td class="text-center">张三</td>
										<td class="text-center">工程师</td>
										<td class="text-center">实习生（T1）</td>
										<td class="text-center">￥1700/月</td>
										<td class="text-center">￥0/月</td>
										<td class="text-center">2019-10-10</td>
									</tr>
									<tr>
										<td class="text-center"><img src="upload/emp/nophoto.png" style="width:30px;"></td> 
										<td class="text-center">张三</td>
										<td class="text-center">工程师</td>
										<td class="text-center">实习生（T1）</td>
										<td class="text-center">￥1700/月</td>
										<td class="text-center">￥0/月</td>
										<td class="text-center">2019-10-10</td>
									</tr>
									<tr>
										<td class="text-center"><img src="upload/emp/nophoto.png" style="width:30px;"></td> 
										<td class="text-center">张三</td>
										<td class="text-center">工程师</td>
										<td class="text-center">实习生（T1）</td>
										<td class="text-center">￥1700/月</td>
										<td class="text-center">￥0/月</td>
										<td class="text-center">2019-10-10</td>
									</tr>
									<tr>
										<td class="text-center"><img src="upload/emp/nophoto.png" style="width:30px;"></td> 
										<td class="text-center">张三</td>
										<td class="text-center">工程师</td>
										<td class="text-center">实习生（T1）</td>
										<td class="text-center">￥1700/月</td>
										<td class="text-center">￥0/月</td>
										<td class="text-center">2019-10-10</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4 col-md-offset-8">
							<ul class="pager">
								<li class="disabled"><span>上一页</span></li>
								<li><span>下一页</span></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭窗口</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
