$(function(){
	$("[id*=editBtn-]").each(function(){
		var deptno = this.id.split("-")[1] ;
		$(this).on("click",function() {
			var maxnum = $("#maxnum-" + deptno).val();
			if (maxnum > 0){
				if (window.confirm("您确定要修改该部门的人数上限吗？")) {
					$.post("pages/back/dept/DeptServlet/editMaxnum", {
						deptno: deptno,
						maxnum: maxnum
					}, function (data) {
						operateAlert(data.trim() == "true", "部门人数上限修改成功！", "部门人数上限修改失败！");
					}, "text");

				}
			}else{
				operateAlert(false, "", "部门人数上限修改失败！调整的部门最大人数要大于部门当前人数");
			}
		}) ;
	});
	$("[id*='showBtn-']").each(function(){	// 取得显示按钮
		var deptno = this.id.split("-")[1];	// 分离出id信息
		$(this).on("click",function(){
			$(deptTitleSpan).text($("#dname-" + deptno).text()) ;
			// 编写Ajax异步更新操作，读取所有的权限信息
			$("#empInfo").modal("toggle") ;
		}) ;
	}) ;
});