$(document).ready(function(){
	
	var curLimitTableRow = null;
	
	initInspOrgSele("upinsporg", "upinsporg-select", "upinspdept", "upinspdept-select");
	initInspOrgSele("queryinsporg", "queryinsporg-select", "queryinspdept", "queryinspdept-select");
	
	/**
	 * 检验机构
	 * @param inputID
	 * @param btnID
	 */
	function initInspOrgSele(orgInputID, orgBtnID, deptInputID, deptBtnID){
		  $.get("InspOperatorAction_GetInspOrgPaging?&ts="
					+ new Date().getTime(), 
		    function(rdata) {
				var source = new Array();
				$.each(rdata.data, function(index, value){
					source[index] = value.orgCode+" "+value.orgName;
				});	
				cus_autocomplete(source, orgInputID, orgBtnID, null, 
					function(event, ui){
					    var orgCode = ui.item.value.split(" ")[0];
					    $("#"+deptInputID).val("");
					    return initInspDeptSele(orgCode, deptInputID, deptBtnID);
				});		
			}, 'json');
	}


	/**
	 * 检验部门
	 * @param orgCode
	 * @param inputID
	 * @param btnID
	 */
	function initInspDeptSele(orgCode, inputID, btnID){
		  $.get("InspOperatorAction_GetInspDeptPaging?&ts="
					+ new Date().getTime(), 
			{ data :  JSON.stringify({orgCode : orgCode})},
		    function(rdata) {
				var source = new Array();
				$.each(rdata.data, function(index, value){
					source[index] = value.deptCode+" "+value.deptName;
				});	
				cus_autocomplete(source, inputID, btnID, null, null);		
			}, 'json');
	}
/*	$("#uproleName-select").click(function(){
		initRoleName("uproleName");
		});
	function initRoleName(inputID){     
		$.get("InspOperatorAction_GetRoleDefine?&ts="
		+ new Date().getTime(),
		function(rdata) {
			var source = new Array();
			$.each(rdata.data, function(index, value){
				source[index] = value.roleCode+" "+value.roleName;
			});	
			if(source.length == 0){             
				alert("查询结果为空！");
				return;
			}
			cus_autocomplete(source, inputID, null, null, null);      
			$("#"+inputID).autocomplete( "search", "" );         
		}, 'json');
}*/
	
	
	var seFlg=null;
	var limitFlg=null;
	var orgCode='';
	var deptCode='';
	var operatorName='';
	var operatorRow=null;
//表数据的获取
	var columnClass = [
		                  { "data": "operatorID" },
		                  { "data": "orgCode" },
		                  { "data": "orgName" },
		                  { "data": "deptCode" },
		      	          { "data": "deptName" },
		      	          { "data": "operatorCode" },         
		      	          { "data": "operatorName" },
		                  { "data": "roleCode" },
		      	          
		                 
		      	         ];
	var operatortable = $('#inspOperatorTb').DataTable( {
			  "deferRender": true,
			  "processing": true,
			  "serverSide": true,
			  "ajax":{
				  url : "InspOperatorAction_GetInspOperatorByQuery",
				  type : "GET",
				  data : function(d){
					//  d.data = getClassReqParam();
					  d.data=inintparm(orgCode, deptCode, operatorName);
					 
				  }
			  },
		      "columns": columnClass,
		    "columnDefs": [
		                     {
		                         "targets": [ 0,1,3,7 ],
		                         "visible": false,
		                     }
		                 ]        
		  } );
	var limitTable = $('#aLimitTable').DataTable( {
		"info" : false,
		"paging" : false,
		"lengthChange" : false,
		"columns": [{"data":"roleCode"},
		            { "data": "roleName" },
		],
		"columnDefs": [
		/*{
		"targets": [ 0 ],
		"visible": false,
		}*/
		]
		} );
	//动态更新获取参数			  
	 function inintparm(orgCode,deptCode,operatorName){
		 data={ 
					orgCode : orgCode,
					deptCode :deptCode,
					operatorName :operatorName
					
			}; 
			 var jsonstr = JSON.stringify(data);
			return jsonstr;
						
					}
	 //draw事件
		$('#inspOperatorTb').on( 'draw.dt', function () {
			
			if(operatortable.rows().data().length > 0){
			var dtRow = null;
			var node = null;
			if(operatorRow != null){
			dtRow = operatortable.row($(operatorRow).context._DT_RowIndex);
			node = dtRow.node();
			if(node != null){           
			$(node).click();
			return;
			}
			}
			dtRow = operatortable.row(0);
			node = dtRow.node();
			$(node).click();
			}else{
				operatorRow = null;
			}
			});
	 
//人员管理表中某一行的点击功能
	    $('#inspOperatorTb tbody').on("click", "tr", clickentRow );	
	 //选中企业基本数据表中的一行
		function clickentRow(){  
			  var row = this;
			  if ( $(row).hasClass('active') ) {  
				  operatorRow = null;
		            $(row).removeClass('active');
		        }
		        else {
		        	operatorRow = row;
		        	operatortable.$('tr.active').removeClass('active');
		            $(row).addClass('active');
		            
		          
		        }  
		  }	
		$('#aLimitTable tbody').on("click", "tr", clickLimitTableRow );
		function clickLimitTableRow(){
			var row = this;
			if ( $(row).hasClass('active') ) {
			curLimitTableRow = null;
			$(row).removeClass('active');
			}
			else {
			curLimitTableRow = row;
			limitTable.$('tr.active').removeClass('active');
			$(row).addClass('active');
			}
			}
	 
//人员查询按钮
		$("#queryoperator").click(function() {
			
				 $(".overlay").show();
				 $("#queryoperDialog").show();
				
				
		});
	//查询中取消
		$("#closequeryoperDialogDialogBtn").click(function() {            
			
			 $(".overlay").hide();          
			 $("#queryoperDialog").hide(); 
			 clearAlertDiv("queryoperDialog");
			
	});
	//查询功能
		$("#queryoperBtn").click(function() {
			orgCode=getSelectValue("queryinsporg");
			deptCode=getSelectValue("queryinspdept");
			operatorName=$("#queryusername").val().trim();
				operatortable.draw();
				$("#closequeryoperDialogDialogBtn").click();
			
	});
	
//人员管理新增/修改按钮
	$("#addoperator").click(function() {
		 seFlg=1;
		 getoperatelimit("人员管理");
			 
	});
	$("#editoperator").click(function() {
		seFlg=2;
	    //getoperatelimit("人员管理");
	    

		 
		 if(operatorRow==null){
				alert("请选择修改项");
			}else{
				
				 $(".overlay").show();
			     $("#addoperatorDialog").show(); 
			     var operatorID=operatortable.row(operatorRow).data().operatorID;
			     var orgCode=operatortable.row(operatorRow).data().orgCode;
			     var deptCode=operatortable.row(operatorRow).data().deptCode;
			     var operatorCode=operatortable.row(operatorRow).data().operatorCode;
			     
			     GetInspOperatorByID(operatorID,orgCode,deptCode,operatorCode);
			     
			}
		 
	 
		
	});		
	
	
//新增/修改取消按钮
	$("#closeaddoperatorDialogBtn").click(function(){
		$(".overlay").hide();
	    $("#addoperatorDialog").hide();
	    clearAlertDiv("addoperatorDialog");         
	    limitTable.clear().draw();
	    seFlg=0;
	});	
//新增/修改保存功能
	$("#saveoperateBtn").bind('click', function(){ 
		         
		 limitFlg=1;
		 getoperatelimit("人员管理");
		 
	});		
	
//删除函数
	$('#deleteoperator').click(function(){
		seFlg=3;
		getoperatelimit("人员管理");
		
	});	
//密码重置
	$('#resetoperator').click(function(){
		seFlg=4;
		getoperatelimit("人员管理");
		
	});	
	
	
	function getoperatelimit(menuname){
		
		data={
				
				menuName : menuname
		};
		
		var jsonstr=JSON.stringify(data);
		 $.post("EntAction_getOperateLimit?&ts="
					+ new Date().getTime(), {
						data:jsonstr
			}, function(rdata) {
				if(rdata.data == "true"){
					
				 if(seFlg==1){
					 $(".overlay").show();
					 $("#addoperatorDialog").show(); 
					
					 
				 }if(limitFlg==1){
					   
						var orgcode=getSelectValue("upinsporg");
						var deptCode=getSelectValue("upinspdept");
						var operatorCode=$("#loginName").val().trim();
						var operatorName=$("#userName").val().trim();
						//var roleCode=getSelectValue("uproleName");
						if(orgcode==''|| deptCode==''||operatorCode==''||operatorName==''){alert("信息填写完整");return;}
						if(seFlg==1){
							
							SaveInspOperator('0', orgcode, deptCode, operatorCode, operatorName,'99');
							
						}
						  if(seFlg==2){
							  var operatorID=operatortable.row(operatorRow).data().operatorID;
							  SaveInspOperator(operatorID, orgcode, deptCode, operatorCode, operatorName,'99');
						 }
					 
				 }if(seFlg==3){
					 if(operatorRow==null){alert("请选择删除项");return;}
					 if(confirm("确认删除吗?")){
						 var operatorID=operatortable.row(operatorRow).data().operatorID;
						 DelInspOperator(operatorID);
						 
					 }
				 }if(seFlg==4){
					 if(operatorRow==null){alert("请选择一项");return;}
					 if(confirm("确认要重置密码吗?")){
						 var operatorID=operatortable.row(operatorRow).data().operatorID;
						 SetInspOperatorPassword(operatorID);
						 
					 }
				 }
					
				}else{
					limitFlg=0; 
					alert(rdata.data);
				}
			}, 'json');
		
	}
	
	
	
	
	function GetInspOperatorByQuery(orgCode,deptCode,operatorName){
		data={ 
				orgCode : orgCode,
				deptCode :deptCode,
				operatorName :operatorName
				
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("InspOperatorAction_GetInspOperatorByQuery?&ts="   
				+ new Date().getTime(), {
					 data : jsonstr
				}, function(rdata) {
					if(rdata.data=="true"){
						alert("退档成功");
						
						 
					}else{
						alert(rdata.data);
					}
					
				}, 'json');
}	
	
	
	function SaveInspOperator(operatorID,orgCode,deptCode,operatorCode,operatorName,roleCode){
		
		var itemLimitList = new Array();
		var len=limitTable.data().length;
		if(len==0){
			alert("请为人员至少添加一个角色");return;
		}
		for(var i = 0 ; i < limitTable.data().length; i++){
		var row = limitTable.data()[i];
		var itemLimit = {  roleCode : row.roleCode,
						   roleName : row.roleName,
						};
		itemLimitList[i] = itemLimit;
		}
		data={ 
				operatorID :operatorID,
				orgCode : orgCode,
				deptCode :deptCode,
				operatorCode :operatorCode,
				operatorName :operatorName,
				roleCode:roleCode,
				itemLimitList:itemLimitList      
				
		}; 
		
		if(!checkJsonParam(data)){
			return ;
			}
		 var jsonstr = JSON.stringify(data);
		$.post("InspOperatorAction_SaveInspOperator?&ts="   
				+ new Date().getTime(), {
					 data : jsonstr
				}, function(rdata) {
					if(rdata.data=="true"){
						alert("保存成功");
						operatortable.draw();
						$("#closeaddoperatorDialogBtn").click();
						limitFlg=0;
					}else{
						alert(rdata.data);
						limitFlg=0;
					}
					
				}, 'json');
}		
	function SetInspOperatorPassword(operatorID){
		data={ 
				operatorID :operatorID
				
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("InspOperatorAction_SetInspOperatorPassword?&ts="   
				+ new Date().getTime(), {
					 data : jsonstr
				}, function(rdata) {
					if(rdata.data=="true"){
						alert("密码重置成功");                
						
						 
					}else{
						alert(rdata.data);
					}
					
				}, 'json');
}		
	function GetInspOrgPaging(orgCode,deptCode,operatorName){
		/*data={ 
				orgCode : orgCode,
				deptCode :deptCode,
				operatorName :operatorName
				
		}; 
		 var jsonstr = JSON.stringify(data);*/
		$.post("InspOperatorAction_GetInspOrgPaging?&ts="   
				+ new Date().getTime(), /*{
					 data : jsonstr
				},*/ function(rdata) {
					if(rdata.data=="true"){
						alert("退档成功");
						
						 
					}else{
						alert(rdata.data);
					}
					
				}, 'json');
}		
	function GetInspDeptPaging(orgCode){
		data={ 
				orgCode : orgCode
				
				
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("InspOperatorAction_GetInspDeptPaging?&ts="   
				+ new Date().getTime(), {
					 data : jsonstr
				}, function(rdata) {
					if(rdata.data=="true"){
						alert("退档成功");
						
						 
					}else{
						alert(rdata.data);
					}
					
				}, 'json');
}		
	function GetRoleDefine(){
		/*data={ 
				orgCode : orgCode,
				deptCode :deptCode,
				operatorName :operatorName
				
		}; 
		 var jsonstr = JSON.stringify(data);*/
		$.post("InspOperatorAction_GetRoleDefine?&ts="   
				+ new Date().getTime(), /*{
					 data : jsonstr
				},*/ function(rdata) {
					if(rdata.data=="true"){
						alert("退档成功");
						
						 
					}else{
						alert(rdata.data);
					}
					
				}, 'json');
}		
	function GetInspOperatorByID(operatorID,orgCode,deptCode,operatorCode){
		data={ 
				operatorID :operatorID,
				orgCode:orgCode,
				deptCode:deptCode,
				operatorCode:operatorCode          
				
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("InspOperatorAction_GetInspOperatorByID?&ts="   
				+ new Date().getTime(), {           
					 data : jsonstr
				}, function(rdata) {
					
					var itemLimit=rdata.data[0].two;          
					var upinsporg=operatortable.row(operatorRow).data().orgCode+" "+operatortable.row(operatorRow).data().orgName;
					var upinspdept=operatortable.row(operatorRow).data().deptCode+" "+operatortable.row(operatorRow).data().deptName;
					var loginName=operatortable.row(operatorRow).data().operatorCode;
					var userName=operatortable.row(operatorRow).data().operatorName;
					//var uproleName=operatortable.row(operatorRow).data().roleName;
					//var uproleCode=operatortable.row(operatorRow).data().roleCode;
					$("#upinsporg").val(upinsporg);
					$("#upinspdept").val(upinspdept);              
					$("#loginName").val(loginName);             
					$("#userName").val(userName);
					//$("#uproleName").val(uproleCode+" "+uproleName);  
					if(itemLimit!=undefined){
						limitTable.rows.add(itemLimit).draw();
					}
					
					
				}, 'json');
}		
	function DelInspOperator(operatorID){         
		data={ 
				operatorID :operatorID
				
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("InspOperatorAction_DelInspOperator?&ts="   
				+ new Date().getTime(), {
					 data : jsonstr
				}, function(rdata) {
					if(rdata.data=="true"){
						alert("删除成功");
						operatortable.row(operatorRow).remove().draw(false);
					}else{
						alert(rdata.data);
					}
					
				}, 'json');
}	

	
	var matrclaRow=null;
	var matericlasalltable = $('#materialclsallTb').DataTable({"paging" : false,
		"info":false});
	//原料细类查询1
	$("#addLimitTable").click(function(){
		   matericlasalltable.row().remove().draw();
			$(".overlay").show();
			$("#addoperatorDialog").hide();
			$("#subsubclaqueydialog").show();    
			matrclaRow=null;
			getmaterialclassAll();
		  });
	
	
	$('#materialclsallTb tbody').on("click", "tr", clickmateriallRow );
	function clickmateriallRow(){
		  var row = this;       
		  if ( $(row).hasClass('active') ) {
			    matrclaRow = null;
	          $(row).removeClass('active');
	      }
	      else {           
	      	matrclaRow = row;
	      	matericlasalltable.$('tr.active').removeClass('active');
	      	$(row).addClass('active');
	      }
	}
//选中返回
	$("#confrimback").click(function(){
		if(matrclaRow==null){alert("请选中一行");return;}
		var code=matericlasalltable.row(matrclaRow).data()[0];
		var limitItem = {
				roleCode : code,
				roleName : matericlasalltable.row(matrclaRow).data()[1],
				};
		
		//var itemLimitList = new Array();
		for(var i = 0 ; i < limitTable.data().length; i++){
		var row = limitTable.data()[i];
		var roleCode=row.roleCode;
	
		if(roleCode==code){
			alert("数据重复");
			return;
			break;
			
		}
		}
		
		
		limitTable.row.add(limitItem).draw();
		$("#closesubsub").click();
		  });
	//取消
	$("#closesubsub").click(function(){
		$("#subsubclaqueydialog").hide();
		$("#addoperatorDialog").show();
		matrclaRow=null;
		  });
	
	function getmaterialclassAll(){
	    
		    var jsonstr = JSON.stringify(data);
			$.post("InspOperatorAction_GetRoleDefine?&ts="   
					+ new Date().getTime(), {
						 data : jsonstr 
					}, function(rdata) {
						
						if(rdata.data.length>0){
						for ( var i = 0; i <rdata.data.length; i++) {
							matericlasalltable.row.add([
							                       rdata.data[i].roleCode,rdata.data[i].roleName
							                      ]);
						}
						matericlasalltable.draw();   
						}
					}, 'json');
	}	              
	
	
	$("#editLimitTable").click(function(){
		deleteLimitTable();
		});
	function deleteLimitTable(){
		if(curLimitTableRow == null){
		alert("请先选择一条要删除的记录");
		return;
		}else{
		if(confirm("确认删除该条记录？")){
		limitTable.row(curLimitTableRow).remove().draw();            
		}
		}
		}
	
	
	
});