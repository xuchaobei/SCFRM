$(document).ready(function(){
	
	reLogin = false;
	
	initInspOrgSelect("orgCode","orgCode-select","deptCode","deptCode-select");
	
	$("#signup").click(login);
	
	function login(){
		var orgCode = getSelectValue("orgCode");
		if(orgCode == ""){
			alert("机构不能为空！");
			return;
		}
		var deptCode = getSelectValue("deptCode");
		if(deptCode == ""){
			alert("科室不能为空！");
			return;
		}
		var operatorCode = $("#operatorCode").val().trim();
		if(operatorCode == null || operatorCode == ""){
			alert("用户名不能为空！");
			return;
		}
		var password = $("#password").val().trim();
		if(password == null || password == ""){
			alert("密码不能为空！");
			return;
		}
		var requestParams = {
			orgCode : orgCode,
			deptCode : deptCode,
			operatorCode : operatorCode,
			password : password
		};  
		if(checkJsonParam(requestParams)){
			 $.post("LoginAction_login?&ts="
						+ new Date().getTime(), 
						requestParams,		
			     function(rdata) {
					if(rdata.data == "true"){
						document.location.href="NewFile.jsp";
					}else{
						alert(rdata.data);
					}
				}, 'json');
		}
	}
	
});