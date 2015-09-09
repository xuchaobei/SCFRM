$(document).ready(function(){
	var entRow=null;
	var seFlg=null;
	var delflg=null;
	var editFlg=null;
	var resetflg=null;
	var entName='';//企业名称
	var entCode='';//企业代码
	var mngOrgCode='';//企业管辖机构代码
	var inspOrgCode='';//操作员所属机构代码  从cookie中获取
	var inspDeptCode='';//操作员所在部门代码
	var inspOperateorCode='';//操作人代码
	var roleCode='';//操作员角色代码 从cookie中获取
	initInspOrgSelect();
	initspecialmanreqSelect();
	initriskcontrolSelect();
	initinspOrg();
	initchengxinlevel();
//获得所属机构	
	function initInspOrgSelect(){
		  $.get("SelectDataAction_getInspOrg?&ts="
					+ new Date().getTime(), 
		    function(rdata) {
				var source = new Array();
				$.each(rdata.data, function(index, value){
					source[index] = value.orgCode+" "+value.orgName;
				});	
				cus_autocomplete(source, "adddailoginsporg", "scomeinspOrg-select", null, null);		
			}, 'json');
	  }	
	//特殊监管要求初始化
	function initspecialmanreqSelect(){
	
		  $.get("EntAction_getEvlLevel?&ts="
					+ new Date().getTime(), 
			{data:JSON.stringify({levelType:"特殊监管要求"})},
		    function(rdata) {
				var source = new Array();
				$.each(rdata.data, function(index, value){
					source[index] = value.levelDesc;
				});	
				
				cus_autocomplete(source, "adddailogspmanrequire", "sspemanagerequire-select", null, null);		
			}, 'json');
		  
	  }
	//诚信等级初始化
	function initchengxinlevel(){
	
		  $.get("EntAction_getEvlLevel?&ts="
					+ new Date().getTime(), 
			{data:JSON.stringify({levelType:"企业诚信等级"})},
		    function(rdata) {
				var source = new Array();
				$.each(rdata.data, function(index, value){
					source[index] = value.levelDesc;
				});	
				
				cus_autocomplete(source, "adddailogcxscore", "sadddailogcxscoree-select", null, null);		
			}, 'json');
		  
	  }
	//风险控制初始化
	function initriskcontrolSelect(){
		  $.get("EntAction_getEvlLevel?&ts="
					+ new Date().getTime(), 
			{data:JSON.stringify({levelType:"风险控制措施"})},
			 function(rdata) {
				var source = new Array();
				$.each(rdata.data, function(index, value){
					source[index] = value.levelDesc;
				});	
				cus_autocomplete(source, "adddailogriskcontrolmeur", "sriskcontrolMeasu-select", null, null);		
			}, 'json');
	  }
	//检验机构初始化
	  function initinspOrg(){
	  $.get("SelectDataAction_getInspOrg?&ts="
				+ new Date().getTime(), 
		 function(rdata) {
			var source = new Array();
			$.each(rdata.data, function(index, value){
				source[index] = value.orgCode+" "+value.orgName;
			});	
			cus_autocomplete(source, "entbasicDatainspOrgCodeName", "sinspOrgCodeName-select", null, null);		
		}, 'json');
}
	//--------------------------------------------------------------------------------------------------四个初始化完成--------------------
//表数据的获取
	var columnClass = [
		                  { "data": "entID" },
		                  { "data": "entCode" },
		      	          { "data": "entName" },
		      	          { "data": "orgName" },
		      	          { "data": "address" },
		                  { "data": "specialMonitorReq" },
		      	          { "data": "riskCtrl" },
		      	          { "data": "qualitySafe" },
		                  { "data": "regScore" },
		      	          { "data": "creditLevel" },
		      	          { "data": "sourceMngScore" },
		                 
		      	         ];
	var entbasictable = $('#entbasicDataTb').DataTable( {
			  "deferRender": true,
			  "processing": true,
			  "serverSide": true,
			  "ajax":{
				  url : "EntAction_getEnt",
				  type : "GET",
				  data : function(d){
					//  d.data = getClassReqParam();
					  d.data=inintparm(entCode, entName, mngOrgCode);
					  d.inspOrgCode=inspOrgCode;
					  d.roleCode=roleCode;
				  }
			  },
		      "columns": columnClass,
		    "columnDefs": [
		                     {
		                         "targets": [ 0 ],
		                         "visible": false,
		                     }
		                 ]
		  } );
	
	$('#entbasicDataTb').on( 'draw.dt', function () {
		curItemCtrlRow = null;
		if(entbasictable.rows().data().length > 0){
		var dtRow = null;
		var node = null;
		if(entRow != null){
		dtRow = entbasictable.row($(entRow).context._DT_RowIndex);
		node = dtRow.node();
		if(node != null){
		$(node).click();
		return;
		}
		}
		dtRow = entbasictable.row(0);
		node = dtRow.node();
		$(node).click();
		}else{
			entRow = null;
		}
		});
	
	
//动态更新获取参数			  
	 function inintparm(entCode,entName,mngOrgCode){
						data={
								entCode:entCode,
								entName :entName,
								mngOrgCode :mngOrgCode
								
								
						};
						var jsonstr=JSON.stringify(data);
						return jsonstr;
						
					}
//基本数据表中某一行的点击功能
    $('#entbasicDataTb tbody').on("click", "tr", clickentRow );	
 //选中企业基本数据表中的一行
	function clickentRow(){  
		  var row = this;
		  if ( $(row).hasClass('active') ) {  
			    entRow = null;
	            $(row).removeClass('active');
	        }
	        else {
	        	entRow = row;
	        	entbasictable.$('tr.active').removeClass('active');
	            $(row).addClass('active');
	            
	          
	        }  
	  }	
//企业查询按钮
	$("#queryentbasicData").click(function() {
		
			 $(".overlay").show();
			 $("#queryentbasicDataDialog").show();
			
			
	});
//查询中取消
	$("#closequeryentbasicDataDialogBtn").click(function() {
		/*entCode='';
		entName='';
		mngOrgCode='';*/
		 $(".overlay").hide();
		 $("#queryentbasicDataDialog").hide(); 
		
});
//查询功能
	$("#queryentbasicDataBtn").click(function() {
		    entCode=$("#entbasicDataentCode").val().trim();
			entName=$("#entbasicDataentName").val().trim();
			mngOrgCode=getSelectValue("entbasicDatainspOrgCodeName");
			entbasictable.draw();
			$("#closequeryentbasicDataDialogBtn").click();
		
});
//企业基本数据新增/修改按钮
	$("#addentbasicData").click(function() {
		
		    seFlg=1;
		    getoperatelimit(roleCode, "企业基本数据管理");
			 
	});
	$("#editentbasicData").click(function() {
		editFlg=2;
	    getoperatelimit(roleCode, "企业基本数据管理");
		
	});	
	//企业具体信息
	function getentdetail(){
		
		   var entCod=entbasictable.row(entRow).data().entCode;
		   $.post("EntAction_getEntDetail?&ts="
					+ new Date().getTime(), {
						data:JSON.stringify({entCode : entCod})
			}, function(rdata) {
				
				if(rdata.data.length >0){
				
				$("#adddialogentcode").val(rdata.data[0].entCode);
				$("#adddialogentname").val(rdata.data[0].entName);
				$("#adddailogentaddress").val(rdata.data[0].address);
				$("#adddailogspmanrequire").val(rdata.data[0].specialMonitorReq);
				$("#adddailogriskcontrolmeur").val(rdata.data[0].riskCtrl);
				$("#adddailogremarks").val(rdata.data[0].remarks);
				$("#adddailoglevelogfunscore").val(rdata.data[0].regScore);
				$("#adddailogcxscore").val(rdata.data[0].creditLevel);
				$("#adddailogbasicmangscore").val(rdata.data[0].sourceMngScore);
				/*$("#adddailogprocessingmanscore").val(rdata.data[0].processMonitorScore);*/
				$("#adddailoginsporg").val(rdata.data[0].mngOrgCode+" "+rdata.data[0].orgName);
				$("#adddailogrecodorg").val(rdata.data[0].orgName);
				$("#adddailogrecorddept").val(rdata.data[0].deptName);
				$("#adddailogrecordman").val(rdata.data[0].operatorName);
				$("#adddailogrecordtime").val(rdata.data[0].regDatetime.substring(0,10));
				}
			}, 'json');
		
	}
//新增/修改取消按钮
	$("#closeaddentbasicDataDialogBtn").click(function(){
		  $(".overlay").hide();
	    $("#addentbasicDataDialog").hide();
	    clearAlertDiv("addentbasicDataDialog");
	    seFlg=0;
	});	
//新增/修改保存功能
	$("#saveentbasicDataBtn").bind('click', function(){ 
		
			if(seFlg==1){
				
				updateentbasicData('0');
				
			  }
			  if(seFlg==2){
				
				var entID=entbasictable.row(entRow).data().entID;
				updateentbasicData(entID);
				
			  }
		 
	});	
//企业基本信息保存函数
	function updateentbasicData(clCode){
		var  entCod=$("#adddialogentcode").val().trim();
		var  entName=$("#adddialogentname").val().trim();
		var  address=$("#adddailogentaddress").val().trim();
		var  specialMonitorReq=$("#adddailogspmanrequire").val().trim();
		var  riskCtrl=$("#adddailogriskcontrolmeur").val().trim();
		var  regScore=$("#adddailoglevelogfunscore").val().trim();
		var  creditLevel=$("#adddailogcxscore").val().trim();
		var  sourceMngScore=$("#adddailogbasicmangscore").val().trim();
		var  mngOrgCode=getSelectValue("adddailoginsporg");
		var  remarks=$("#adddailogremarks").val().trim();
		if(isNaN(regScore) || parseInt(regScore, 10)>10){alert("备案登记基础情况分值必须为小于10的整数");return;}
		if(isNaN(sourceMngScore) || parseInt(sourceMngScore, 10)>10){alert("源头管理情况分值必须为小于10的整数");return;}
		if(entCod=='' || entName=="" ||specialMonitorReq=='' || riskCtrl==""  ||regScore=='' || creditLevel=='' || 
	       sourceMngScore=='' || mngOrgCode==''  )
		   {alert("除地址备注外均为必填项");}
		  else{
			  data={ 
					  entID :clCode,
					  entCode:entCod,
					  entName:entName,
					  address:address,
					  specialMonitorReq : specialMonitorReq,
					  riskCtrl:riskCtrl ,
					  regScore :regScore ,
					  creditLevel :creditLevel ,
					  sourceMngScore: sourceMngScore,
					  mngOrgCode :mngOrgCode,
					  remarks: remarks,
					  regOrgCode:inspOrgCode,//操作人机构代码
					  regDeptCode :inspDeptCode ,//操作人部门代码
					  regOperatorCode:inspOperateorCode ,//操作人代码
					  
		    	};   
			  if(!checkJsonParam(data)){
					return ;
					}
			  var jsonstr = JSON.stringify(data);
		  $.post("EntAction_saveEnt?&ts="   
					+ new Date().getTime(), {
						 data : jsonstr,roleCode :roleCode
					}, function(rdata) {
				if(rdata.data == "true"){
					if(seFlg==1){
						entRow=null;
						entbasictable.draw();
						
					}else if(seFlg==2){
						entbasictable.draw(false);
					}
					
					$("#closeaddentbasicDataDialogBtn").click();
					
				}else{
					alert(rdata.data);
					$("#closeaddentbasicDataDialogBtn").click();
					
				}
			}, 'json');
	  }	
	
		
	}
//删除函数
	$('#deleteentbasicData').click(function(){
		delflg=1;
		getoperatelimit(roleCode, "企业基本数据管理");
		
	});
	
	function deleteentbasic(){
		  if(entRow == null){
			  alert("请先选择一条要删除的记录！");
			  return;
		  }
		  if(confirm("确定要删除吗?")){  
			   var entCode=entbasictable.row(entRow).data().entCode;
			  
			 
			  $.post("EntAction_delEnt?&ts="
						+ new Date().getTime(), {
							data:JSON.stringify({entCode : entCode}),inspOrgCode:inspOrgCode,roleCode:roleCode  
				}, function(rdata) {
					if(rdata.data == "true"){
						alert("删除成功！");
						
						entbasictable.row(entRow).remove().draw(false);//只有一行有个错误
					}
				}, 'json');
		  }
		}	
//重置企业密码
	$('#resetPSWentbasicData').click(function(){
		resetflg=1;
		getoperatelimit(roleCode, "企业基本数据管理");
	});
	function resetpsword(){
		  if(entRow == null){
			  alert("请先选择重置企业！");
			  return;
		  }
		  if(confirm("确定要重置吗?")){  
			   var entCode=entbasictable.row(entRow).data().entCode;
			  
			  $.post("EntAction_resetEntPasswords?&ts="
						+ new Date().getTime(), {
							data:JSON.stringify({entCode : entCode}),inspOrgCode:inspOrgCode,roleCode:roleCode  
				}, function(rdata) {
					if(rdata.data == "true"){
						alert("重置成功,重置密码为12345678");
					}
				}, 'json');
		  }
		}	
	function getoperatelimit(rolecode,menuname){
		
		data={
				roleCode :rolecode,
				menuName : menuname
		};
		
		var jsonstr=JSON.stringify(data);
		 $.post("EntAction_getOperateLimit?&ts="
					+ new Date().getTime(), {
						data:jsonstr
			}, function(rdata) {
				if(rdata.data == "true"){
					
				 if(delflg==1){
				    delflg=null;
					deleteentbasic();
				   
				 }if(resetflg==1){
				    resetflg=null;
					 resetpsword();
				    
				 }if(seFlg==1){
					 $(".overlay").show();
					 $("#addentbasicDataDialog").show(); 
					 $("#logininformation").hide();
					 
				 }if(editFlg==2){
					 editFlg=0;
					 if(entRow==null){
							alert("请选择修改项");
						}else{
							seFlg=2;
							 $(".overlay").show();
						     $("#addentbasicDataDialog").show(); 
						     $("#logininformation").show();
						     getentdetail();
						       /* var entCode=entbasictable.row(entRow).data().entCode;
								var entName=entbasictable.row(entRow).data().entName;
								var address=entbasictable.row(entRow).data().address;
								var specialMonitorReq=entbasictable.row(entRow).data().specialMonitorReq;
								var riskCtrl=entbasictable.row(entRow).data().riskCtrl;
								var qualitySafe=entbasictable.row(entRow).data().qualitySafe;
								var regScore=entbasictable.row(entRow).data().regScore;
								var creditScore=entbasictable.row(entRow).data().creditScore;
								var sourceMngScore=entbasictable.row(entRow).data().sourceMngScore;
								var processMonitorScore=entbasictable.row(entRow).data().processMonitorScore;
								var mngOrgCode=entbasictable.row(entRow).data().mngOrgCode;
								$("#adddialogentcode").val(entCode);
								$("#adddialogentname").val(entName);
								$("#adddailogentaddress").val(address);
								$("#adddailogspmanrequire").val(specialMonitorReq);
								$("#adddailogriskcontrolmeur").val(riskCtrl);
								$("#className").val(qualitySafe);
								$("#adddailoglevelogfunscore").val(regScore);
								$("#adddailogcxscore").val(creditScore);
								$("#adddailogbasicmangscore").val(sourceMngScore);
								$("#adddailogprocessingmanscore").val(processMonitorScore);
								$("#adddailoginsporg").val(mngOrgCode);*/
						}
					 
				 }
					
				}else{
					alert(rdata.data);
				}
			}, 'json');
		
	}
});