$(document).ready(function(){
	
	
	
	
	
	//密码修改函数
$('#sureresetpswBtn').click(function(){                       
		var oldpsw=$("#oldpsw").val().trim();
		var newPas=$("#newpsw").val().trim();
		var newPasswords=$("#againnewpsw").val().trim();
		if(oldpsw=='' || newPas =='' || newPasswords=='' ){alert("信息填写完整");return;}
		if(newPas !=newPasswords){alert("两次输入不一致");return;}
		ChangeOperatorPassword(oldpsw, newPas);
	});	
	
	
	
	
	function ChangeOperatorPassword(oldPasswords,newPasswords){
		
		data={ 
				oldPasswords :oldPasswords,
				newPasswords :newPasswords
				
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("ResetPSWAction_ChangeOperatorPassword?&ts="   
				+ new Date().getTime(), {
					 data : jsonstr
				}, function(rdata) {
					if(rdata.data=="true"){
						alert("密码修改成功");
						
						 
					}else{
						alert(rdata.data);
					}
					
				}, 'json');
}	
	
	
	
	
});