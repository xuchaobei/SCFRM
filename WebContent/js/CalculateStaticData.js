//@ sourceURL=CalculateStaticData.js 
$(document).ready(function(){
	$("#calculate").click(function(){
		var now = new Date();
		var hour = now.getHours();
		if(hour < 8 || hour > 17){
			var staticDate = $("#staticDate").val().trim()+"-1";
			 $.post(
				      'CalculateStaticDataAction_checkPermission',
				      {},
				      function (data) 
				      {
				    	  if(data.data){
				    		  alert(data.data);
				    	  }else{
				    		  calculateData(staticDate);	  
				    	  }
				      },"json");
		}else{
			alert("统计计算只限于18点到8点执行，当前时间不能操作");
		} 
	});
	
	function calculateData(staticDate){
		 $.post(
			      'CalculateStaticDataAction_calculateMonthlyStaticData',
			      {data : staticDate},
			      function (data) 
			      {
			      },"json");
	}
})