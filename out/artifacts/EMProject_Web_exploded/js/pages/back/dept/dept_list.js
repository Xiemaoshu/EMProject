$(function(){
	$("[id*=editBtn-]").each(function(){
		var deptno = this.id.split("-")[1] ;
		$(this).on("click",function(){
			var maxnum = $("#maxnum-" + deptno).val() ;
			console.log("***** deptno = " + deptno + "，人数上限：" + maxnum) ;
			if (window.confirm("您确定要修改该部门的人数上限吗？")) {
				operateAlert(true , "部门人数上限修改成功！","部门人数上限修改失败！") ;
			}
		}) ;
	})
	$("[id*='showBtn-']").each(function(){	// 取得显示按钮
		var deptno = this.id.split("-")[1];	// 分离出id信息
		$(this).on("click",function(){
			console.log("deptno = " + deptno) ;
			$(deptTitleSpan).text($("#dname-" + deptno).text()) ;
			// 编写Ajax异步更新操作，读取所有的权限信息
			$("#empInfo").modal("toggle") ;
		}) ;
	}) ;
})