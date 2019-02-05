$(function() {
	$(selall).on("click",function(){
		checkboxSelectAll("empno",this.checked) ;
	}) ;
	$(outBtn).on("click",function(){
		operateChecked("empno","/pages/back/emp") ;
	}) ;
})