$(document).ready(function(){
	var k=null;
	var useflg=null;
	var roleRow=null;
	

	
	
//企业端的企业产品表获取
	
	var columnClass = [
		                  { "data": "roleID" },
		                  { "data": "roleCode" },
		      	          { "data": "roleName" },
		      	          { "data": "dataAccess",
		      	        	  "render":function(data,type,full,meta){
		      	        		  if(data=='1') {
		      	        			 return '本部门';
		      	        			  
		      	        			  }
		      	        		 if(data=='2') {
		      	        			 return '本机构';
		      	        			  
		      	        			  }
		      	        		 if(data=='3') {
		      	        			 return '全局';
		      	        			  
		      	        			  }
		      	        		  
		      	        	  }},
		      	          { "data": "operateLevel" }
		    
		      	         ];
	var roleTable = $('#roleTb').DataTable( {
			  "deferRender": true,
			  "processing": true,
			  "serverSide": true,
			  "paging" : false,
			  "info":false,
			  "ajax":{
				  url : "InspOperatorAction_GetRoleDefine",
				  type : "GET",
			  },
		      "columns": columnClass,
		      "columnDefs": [
		                     {
		                         "targets": [ 0 ],
		                         "visible": false,
		                     }
		                 ]
		  } );
	
	$('#roleTb').on( 'draw.dt', function () {
		
		if(roleTable.rows().data().length > 0){
		var dtRow = null;
		var node = null;
		if(roleRow != null){
		dtRow = roleTable.row($(roleRow).context._DT_RowIndex);
		node = dtRow.node();
		if(node != null){
		$(node).click();
		return;
		}
		}
		dtRow = roleTable.row(0);
		node = dtRow.node();
		$(node).click();
		}else{
			roleRow = null;
		}
		});
	
	$('#roleTb tbody').on("click", "tr", clickentcheckRow );	
	function clickentcheckRow(){  
		  var row = this;
		  if ( $(row).hasClass('active') ) {  
			     roleRow = null;
	            $(row).removeClass('active');
	        }
	        else {
	        	roleRow = row;
	        	 roleTable.$('tr.active').removeClass('active');
	            $(row).addClass('active');
	            
	          
	        }  
	  }	
	
	$("#setsleft").on("click",function(){
		//alert("4sem"+k);
		  for ( var i = 0; i <k; i++) {
			  
			 
			  
			$("input[name='name"+i+"']").each(function(index){
				
				if($(this).prop("checked")){   //jquery1.6改进了$(this).attr("checked")
				 
				  $("input[name='subname"+i+"']").prop("checked",true);
				  $("input[name='name"+i+"']").prop("checked",false);
				}
			});
			}
		
		
	});
	
	//角色新增按钮
	$("#addrole").click(function() {
		
		useflg=1;
		getoperatelimit('角色管理', useflg);
		
			
			
	});
	//角色修改按钮
	$("#editrole").click(function() {
		
		useflg=2;
		getoperatelimit('角色管理', useflg);
		
			
			
	});
	//取消按钮
	$("#closeDialogBtn").click(function() {
		
			 $(".overlay").hide();
			 $("#addRoleDialog").hide();
			 $("#setsleftlistall tbody").empty(); 
			 clearAlertDiv("addRoleDialog");
			 $("#radept").prop("checked",false);
			 $("#raorg").prop("checked",false);
			 $("#ratotel").prop("checked",false);
	});
	
//角色删除按钮
	$("#deleterole").click(function() {
		
		useflg=3;
		getoperatelimit('角色管理', useflg);
		
			
			
	});
	
	//保存
	$("#saverole").click(function() {
		
			
			
			var roleCode=$("#arolecode").val().trim(); 
			var roleName=$("#arolename").val().trim(); 
			var operateLevel=$("#aoperatLevel").val().trim(); 
			if(isNaN(operateLevel)){alert("操作等级必须是整数");return;}
			var dataAccess =null;
			if($("#radept").prop("checked")){
				dataAccess=1;
			}
			if($("#raorg").prop("checked")){
				dataAccess=2;
			}
			if($("#ratotel").prop("checked")){
				dataAccess=3;
			}
			var str='';
			  for ( var i = 0; i <k; i++) {
					
						var menuname=$("input[name='name"+i+"']").parents().next().children().html();
						//alert(menuname);  
						var count=0;
						var setsarray = new Array();
						$("input[name='subname"+i+"']").each(function(index){
						    
							if($(this).prop("checked")){   //jquery1.6改进了$(this).attr("checked")
								    var temp=$(this).parents().next().html();
									temp=temp+" "+'1';
									setsarray[count]=temp;
									count++;
								}
							else{
								var temp=$(this).parents().next().html();
								temp=temp+" "+'0';
								setsarray[count]=temp;
								count++;
							}
							});
						 
					str=str+menuname+"/"+setsarray+"@";
					}
			 
			//alert(str);
			if(useflg==1){
				SaveRole('0', roleCode, roleName, dataAccess, operateLevel,str);
			}else if(useflg==2){
				var roleid=roleTable.row(roleRow).data().roleID;
				SaveRole(roleid, roleCode, roleName, dataAccess, operateLevel,str);
			}
			
			
			
	});
	
	
	
	function GetMenuDefine(){
		
		 
		$.post("RoleManageAction_GetMenuDefine?&ts="   
				+ new Date().getTime(), 
				function(rdata) {
					
					

					/*alert(rdata.data[0].one.length);
					alert(rdata.data[0].two.length);
					alert(rdata.data[0].one[0].menuName);
					alert(rdata.data[0].two[0].menuName);*/
					$showlist=$($("#setsleftlistall")); 
					var ctbody=$("<tbody></tbody>").appendTo($showlist);
					k=rdata.data[0].one.length;
					for(var i=0;i<rdata.data[0].one.length;i++){
					    var ctr=$("<tr></tr>").appendTo($(ctbody));
					    var ctd=$('<td >全选<input type="checkbox" name=name'+i+'></input></td> ').appendTo($(ctr));
					   // var ctd=$('<td width="25%">'+rdata.data.one[i].menuName+'</td>').appendTo($(ctr));
					    var ctd=$('<td> <lable style="margin-left:-11%">'+rdata.data[0].one[i].menuName+'</lable></td>').appendTo($(ctr));
						/*$(ctr).mouseover(function(){
							$(this).toggleClass('oddrow');
						});
						$(ctr).mouseout(function(){
							$(this).toggleClass("oddrow");     
						});*/
					    for ( var j = 0; j < rdata.data[0].two.length; j++) {
					    	if(rdata.data[0].one[i].menuName==rdata.data[0].two[j].menuName){
					    		//alert("here");
					    		var ctr=$("<tr></tr>").appendTo($(ctbody));
							    var ctd=$('<td width="20%"><input style="margin-left:67%" type="checkbox" name=subname'+i+'></input></td>').appendTo($(ctr));
							   // var ctd=$('<td width="25%">'+rdata.data.one[i].menuName+'</td>').appendTo($(ctr));
							    var ctd=$('<td width="55%">'+rdata.data[0].two[j].submenuName+'</td>').appendTo($(ctr));
								
					    	}
					    	  
							
						}
					}
				
					
					
					
				}, 'json');
	}
	function GetSubMenuDefine(roleCode,menuName){
		data={ 
				roleCode:roleCode,
				menuName:menuName
				
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("RoleManageAction_GetSubMenuDefine?&ts="   
				+ new Date().getTime(), {
					 data : jsonstr
				}, function(rdata) {
					if(rdata.data=="true"){
						alert("评定撤销操作成功");
						
						 
					}else{
						alert(rdata.data);
					}
					
				}, 'json');
	}
	function SaveRole(roleID,roleCode,roleName,dataAccess,operateLevel,str){
		data={ 
				roleID:roleID,
				roleCode : roleCode,
				roleName : roleName,
				dataAccess:dataAccess,
				operateLevel :operateLevel,
				str:str
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("RoleManageAction_SaveRole?&ts="   
				+ new Date().getTime(), {
					 data : jsonstr
				}, function(rdata) {
					if(rdata.data=="true"){
						
						//SaveRoleSubmenu(str);
						alert("保存成功");
						$("#closeDialogBtn").click();
						
						roleTable.draw();
					}else{
						alert(rdata.data);
					}
					
				}, 'json');
	}
	function SaveRoleSubmenu(str){
		data={ 
				str:str
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("RoleManageAction_SaveRoleSubmenu?&ts="   
				+ new Date().getTime(), {
					 data : jsonstr
				}, function(rdata) {
					if(rdata.data=="true"){
						alert("评定撤销操作成功");
						
						 
					}else{
						alert(rdata.data);
					}
					
				}, 'json');
	}
	function GetRoleMenuLimit(roleCode){
		data={ 
				roleCode:roleCode
				
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("RoleManageAction_GetRoleMenuLimit?&ts="   
				+ new Date().getTime(), {
					 data : jsonstr
				}, function(rdata) {
					
					

					/*alert(rdata.data[0].one.length);
					alert(rdata.data[0].two.length);
					alert(rdata.data[0].one[0].menuName);
					alert(rdata.data[0].two[0].menuName);*/
					$showlist=$($("#setsleftlistall")); 
					var ctbody=$("<tbody></tbody>").appendTo($showlist);
					k=rdata.data[0].one.length;
					//alert(k);
					for(var i=0;i<rdata.data[0].one.length;i++){
					    var ctr=$("<tr></tr>").appendTo($(ctbody));
					    var ctd=$('<td >全选<input type="checkbox" name=name'+i+'></input></td> ').appendTo($(ctr));
					   // var ctd=$('<td width="25%">'+rdata.data.one[i].menuName+'</td>').appendTo($(ctr));
					    var ctd=$('<td> <lable style="margin-left:-11%">'+rdata.data[0].one[i].menuName+'</lable></td>').appendTo($(ctr));
						/*$(ctr).mouseover(function(){
							$(this).toggleClass('oddrow');
						});
						$(ctr).mouseout(function(){
							$(this).toggleClass("oddrow");     
						});*/
					    for ( var j = 0; j < rdata.data[0].two.length; j++) {         
					    	if(rdata.data[0].one[i].menuName==rdata.data[0].two[j].menuName){
					    		//alert("here");
					    		var ctr=$("<tr></tr>").appendTo($(ctbody));
					    		if(rdata.data[0].two[j].operateLimit=='1'){
					    			var ctd=$('<td width="20%"><input style="margin-left:67%" type="checkbox" checked name=subname'+i+'></input></td>').appendTo($(ctr));
					    			 var ctd=$('<td width="55%">'+rdata.data[0].two[j].submenuName+'</td>').appendTo($(ctr));
										  
					    		}else{
					    			  var ctd=$('<td width="20%"><input style="margin-left:67%" type="checkbox" name=subname'+i+'></input></td>').appendTo($(ctr));
					    			  var ctd=$('<td width="55%">'+rdata.data[0].two[j].submenuName+'</td>').appendTo($(ctr));
										
					    		}
							   // var ctd=$('<td width="25%">'+rdata.data.one[i].menuName+'</td>').appendTo($(ctr));
							   
					    	}
					    	  
							
						}
					}
				
					
					
					
				}, 'json');
	}
	function GetRoleSubmenuLimit(){
		data={ 
				declNo:declNo,
				processName :processName,
				processOrgCode : processOrgCode,
				processDeptCode : processDeptCode,
				processOperatorCode:processOperatorCode
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("RoleManageAction_GetRoleSubmenuLimit?&ts="   
				+ new Date().getTime(), {
					 data : jsonstr
				}, function(rdata) {
					if(rdata.data=="true"){
						alert("评定撤销操作成功");
						
						 
					}else{
						alert(rdata.data);
					}
					
				}, 'json');
	}
	function DelRole(roleCode){
		data={ 
				roleCode:roleCode
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("RoleManageAction_DelRole?&ts="   
				+ new Date().getTime(), {
					 data : jsonstr
				}, function(rdata) {
					if(rdata.data=="true"){
						alert("删除成功");
						roleTable.draw();
						 
					}else{
						alert(rdata.data);
					}
					
				}, 'json');
	}
	
	
	 function getoperatelimit(menuname,useflg){
			
			data={
					
					menuName : menuname
			};
			
			var jsonstr=JSON.stringify(data);
			 $.post("EntAction_getOperateLimit?&ts="
						+ new Date().getTime(), {
							data:jsonstr
				}, function(rdata) {
					if(rdata.data == "true"){
						
					 if(useflg==1){
						 
						 $(".overlay").show();
					     $("#addRoleDialog").show();
					     GetMenuDefine();
					 
					 }else if(useflg==2){
					 if(roleRow==null){
							alert("请选择修改项");
						}else{
							
							 $(".overlay").show();    
							 $("#addRoleDialog").show(); 
							
							var arolecode=roleTable.row(roleRow).data().roleCode;
							var arolename=roleTable.row(roleRow).data().roleName;
							var aoperatLevel=roleTable.row(roleRow).data().operateLevel;
							var dataAccess=roleTable.row(roleRow).data().dataAccess;
						     
							$("#arolecode").val(arolecode);
							$("#arolename").val(arolename);
							$("#aoperatLevel").val(aoperatLevel);    
							if(dataAccess=='1'){
								$("#radept").prop("checked",true);
							}
							if(dataAccess=='2'){
								$("#raorg").prop("checked",true);
							}
							if(dataAccess=='3'){
								$("#ratotel").prop("checked",true);
							}
							
							GetRoleMenuLimit(arolecode);
						}
					 }else if(useflg==3){
						 
						 if(roleRow==null){
								alert("请选择删除项");
							}else{
								var arolecode=roleTable.row(roleRow).data().roleCode;
								if(confirm("确定要删除吗?")){
									 DelRole(arolecode);
								}
								
							}
						 
						 
						 
					 }
						
					}else{
						alert(rdata.data);
					}
				}, 'json');
			
		}	 
	 
});