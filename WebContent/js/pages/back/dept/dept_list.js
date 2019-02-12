var cp = 1;//默认当前所在页为1
var lineSize = 5;//默认每页显示数据个数为5
var allRecorders = 0; //保存部门雇员总个数
var pageSize = 0;//保存分页总页数
$(function(){
	$("[id*=editBtn-]").each(function(){
		var deptno = this.id.split("-")[1] ;
		$(this).on("click",function() {
			var maxnum = $("#maxnum-" + deptno).val();
			if (maxnum > 0){
				if (window.confirm("您确定要修改该部门的人数上限吗？")) {
					$.post("pages/back/dept/DeptServletBack/editMaxnum", {
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
			//每次打开模态窗口默认当前所在页为1
			cp = 1;
			//加载部门的雇员分页数据
			loadData(deptno);
			$("#empInfo").modal("toggle") ;//显示模态窗口
			//为上一页和下一页按钮绑定点击事件
			$("#previousBut").on("click",function(){
				if(cp > 1){
					//清除按钮样式
					$("#previousButLi").attr("class","");
					cp --;
				}else if(cp ==1 ){
					//禁用上一页按钮
					$("#previousButLi").attr("class","disabled");
				}else{
					//重新赋值,防止出现负数的情况
					cp=1;
				}
			});
			$("#nextBut").on("click",function(){
				if(cp < pageSize){
					$("#nextButLi").attr("class","");
					cp ++;
				}else if(cp == pageSize){
					$("#nextButLi").attr("class","disabled");
				}else{
					cp = pageSize;//防止出现cp > pageSize 的情况
				}
			});

		}) ;
	}) ;

	//设置模态窗口隐藏后,清楚上一页和下一页的绑定事件
	$("#empBody").on("hidden.bs.modal",function(){
		$("#previousBut").unbind("click");
		$("#nextBut").unbind("click");
	})

});

/**
 * 进行部门雇员分页ajax处理,调用DeptServletBack/list函数
 * <p>将分页查询出来的数据以json的数据格式返回
 * <p>将返回的数据,填写到 #empBody 元素中
 * @param deptno
 */
function loadData(deptno){
	$.post("pages/back/dept/DeptServletBack/listEmp",{cp:cp,lineSize:lineSize,deptno:deptno},function(data){
		if(data.flag==true){
			//取出数据总量
			allRecorders = data.allRecorders;
			//计算出分页总页数
			pageSize = (allRecorders +lineSize-1)/lineSize;
			//进行数据填写时,先清空表格中原有的内容,放置表格重复添加数据
			$("#empBody tr").remove();
			//进行数据填写
			for (var i = 0; i < data.allEmps.length; i++) {
				var empTrObj = "<tr> " +
					" <td class='text-center'><img src='upload/emp/"+data.allEmps[i].photo+"'style='width:30px;'></td> " +
					" <td class='text-center'>"+data.allEmps[i].ename+"</td>  " +
					" <td class='text-center'>"+data.allEmps[i].job+"</td> " +
					" <td class='text-center'>"+data.allEmps[i].lid+"</td> " +
					" <td class='text-center'>￥"+data.allEmps[i].sal+"/月</td> " +
					" <td class='text-center'>￥"+data.allEmps[i].comm+"/月</td> " +
					" <td class='text-center'>"+new Date(data.allEmps[i].hiredate.time).format("yyyy-MM-dd")+"</td> " +
					" </tr>"
				$("#empBody").append(empTrObj);
			}
		}
	},"json");

}
