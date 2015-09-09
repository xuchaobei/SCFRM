$(document).ready(function(){
			
	    var curRow = null; //局部变量
	    var productClassCode=null;
	    var subcurRow = null;
	    var useflg=null;
	    var menunam='认可检验机构';
			
	    initProductClassSelect("proclass","proclass-select","subproclass","subproclass-select");
	    searchDetectionItem("itemcheck","itemcheck-select");
	    $("#itemcheck-select").click(searchDetectionItem);
	var columnproClass = [
		                  { "data": "otherTestLabID" },
		                  { "data": "testLabCode" },
		      	          { "data": "testLabName" },
		      	          { "data": "address" },
		      	          { "data": "tel" },
		      	          { "data": "regDeptName"},
		      	          { "data": "regDatetime"},
		                 ];
		var table = $('#labTb').DataTable( {
			  "deferRender": true,
			  "processing": true,
			  "serverSide": true,
			  "search":true,
			  "ajax":{
				  url : "ThridTestLabAction_GetOtherTestLab",
				  type : "GET",
				  data : function(d){
					  d.data = getRequestParam();
					 
				  }
			  },
		      "columns": columnproClass,
		    "columnDefs": [
		                     {
		                         "targets": [ 0 ],
		                         "visible": false,
		                     }
		                 ]
		  } );
	
		//获取请求参数
		  function getRequestParam(){
			  var data = {
					     testLabName:$("#querylabname").val().trim()
					     };
			  var jsonstr = JSON.stringify(data);   
			  
			  return jsonstr;
		  }
	
		  var columnproSubClass = [
			                          { "data": "otherTestLabItemID" },
			                          { "data": "itemCode" },
					      	          { "data": "itemName" },
	   				      	          { "data": "productClassCode" },
	   				      	          { "data": "productClassName" },
	   				      	          { "data": "productSubclassCode" },
	   				      	          { "data": "productSubclassName" },
					                 ]; 
		  var subtables = $('#labItemTb').DataTable( {
				
			    "search":true,
				"deferRender": true,
				"processing": true,
				"serverSide": true,
				  
				   "ajax":{
					  url : "ThridTestLabAction_GetOtherTestLabItem",
					  type : "GET",
					  data :  function(d){
						  if(curRow == null){
							  d.data=JSON.stringify({otherTestLabID : 0});
							 // return {OtherTestLabID : 0};
						  }else{
							  //获得convCtrlID
							  d.data=JSON.stringify({otherTestLabID : table.row(curRow).data().otherTestLabID});
							  //return {OtherTestLabID : table.row(curRow).data().classCode};
						  }
					 }
			  },
			      "columns": columnproSubClass,
			     "columnDefs": [
			                     {
			                         "targets": [ 0 ],
			                         "visible": false,
			                     }
			                 ]
			      
		    });  
	
		  
//初始化第一行默认选中
			$('#labTb').on( 'draw.dt', function () {
				  if(table.rows().data().length > 0){
					  var dtRow = null;
					  var node = null;
					  if(curRow != null){
					  dtRow = table.row($(curRow).context._DT_RowIndex);
					  node = dtRow.node();
					  if(node != null){
					  $(node).click();
					  return;
					  }
					  }
					  dtRow = table.row(0);
					  node = dtRow.node();
					  $(node).click();
               }else{
              	 curRow=null;
               }
				  subcurRow=null;
				  
			  } );
//初始化第一行默认选中小类
			$('#labItemTb').on( 'draw.dt', function () {
				  if(subtables.rows().data().length > 0){
					  var dtRow = null;
					  var node = null;
					  if(subcurRow != null){
					  dtRow = subtables.row($(subcurRow).context._DT_RowIndex);
					  node = dtRow.node();
					  if(node != null){
					  $(node).click();
					  return;
					  }
					  }
					  dtRow = subtables.row(0);
					  node = dtRow.node();
					  $(node).click();  
				 }else{
              	 subcurRow=null;
               }
				 
			  } );  
		  
//点击选中某一行
			$('#labTb tbody').on("click", "tr", clickRow );
			$('#labItemTb tbody').on("click", "tr", clickRowSub );
			
//选中产品大类某一行
			 function clickRow(){  
				  var row = this;
				  if ( $(row).hasClass('active') ) {  
					    curRow = null;
			            $(row).removeClass('active');
			        }
			        else {
			        	curRow = row;
			            table.$('tr.active').removeClass('active');
			            $(row).addClass('active');
			            
			          
			        }
				  subtables.draw();
				  subcurRow = null;  
			  }
//选中产品小类某一行
			 function clickRowSub(){  
				  var row = this;
				  if ( $(row).hasClass('active') ) {  
					    subcurRow = null;
			            $(row).removeClass('active');
			        }
			        else {
			        	subcurRow = row;
			            subtables.$('tr.active').removeClass('active');
			            $(row).addClass('active');
			        }
			 }
		
			 
//新增
			 $("#addnew").click(function(){
				  
				 useflg=1;
				 getoperatelimit(menunam, useflg);
			  });
			 $("#closelabDialogBtn").click(function(){
				  $(".overlay").hide();
			      $("#addlabDialog").hide(); 
			      clearAlertDiv("addlabDialog");
			  });
			 $("#savelab").click(function(){
				 
				   // var otherTestLabID=$("#labcode").val().trim();
					var testLabCode=$("#labcode").val().trim();
					var testLabName =$("#labname").val().trim();
					if(testLabName==''){alert("请输入检测机构名称");return;}
					var address =$("#labaddress").val().trim();
					var tel=$("#labtel").val().trim();
					if(useflg==1){
						SaveOtherTestLab('0', testLabCode, testLabName, address, tel); 
					}
					if(useflg==2){
						var otherTestLabID=table.row(curRow).data().otherTestLabID;
						SaveOtherTestLab(otherTestLabID, testLabCode, testLabName, address, tel); 
					}
				 
				 		  
			  });
//修改
			 $("#edit").click(function(){
				  
				 useflg=2;
				 getoperatelimit(menunam, useflg);
			  });
			 
			 
//查询
			 $("#query").click(function(){
				  $(".overlay").show();
			      $("#querylab").show(); 
			  });
			 
			 $("#querylabBtn").click(function(){
				  table.draw();
				  $(".overlay").hide();
			      $("#querylab").hide(); 
			  });
			 $("#closequerylabDialogBtn").click(function(){
				  $(".overlay").hide();
			      $("#querylab").hide(); 
			      
			  });
			 
//删除
			 $("#delete").click(function(){
				  
				 useflg=3;
				 getoperatelimit(menunam, useflg);
			  });

			 
			 
//新增项目
			 $("#itemadd").click(function(){
				
				 useflg=4;
				 getoperatelimit(menunam, useflg);
				  
			  });
			  $("#closeitemDialogBtn").click(function(){
				  $(".overlay").hide();
			      $("#additemDialog").hide(); 
			      clearAlertDiv("additemDialog");
			  });
			  
			  $("#saveitemBtn").click(function(){
					 
				   // var otherTestLabID=$("#labcode").val().trim();
					var itemCode=getSelectValue("itemcheck");
					var productClassCode =getSelectValue("proclass");
					var productSubclassCode=getSelectValue("subproclass");
					
					if(itemCode==''){alert("请输入检测项目");return;}
					if(productClassCode==''){alert("请输入产品大类");return;}
					if(productSubclassCode==''){alert("请输入产品小类");return;}
					var otherTestLabID=table.row(curRow).data().otherTestLabID;
					if(useflg==4){
						
						SaveOtherTestLabItem('0', otherTestLabID, itemCode, productClassCode, productSubclassCode); 
					}
					if(useflg==5){
						var otherTestLabItemID=subtables.row(subcurRow).data().otherTestLabItemID;
						SaveOtherTestLabItem(otherTestLabItemID, otherTestLabID, itemCode, productClassCode, productSubclassCode); 
					}
				 
				 		  
			  });
//修改项目			  
			  $("#edititem").click(function(){
				  
					 useflg=5;
					 getoperatelimit(menunam, useflg);
				  });			  
			  
//删除项目		
			  $("#deleteitem").click(function(){
				  
					 useflg=6;
					 getoperatelimit(menunam, useflg);
				  });
			 
//权限	 
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
							    $("#addlabDialog").show();
							    $("#foredit").hide();
							 }else if(useflg==2){
							 $(".overlay").show();
							 $("#addlabDialog").show(); 
							 $("#foredit").show();
							 $("#labcode").val(table.row(curRow).data().testLabCode);
							 $("#labname").val(table.row(curRow).data().testLabName);
							 $("#labaddress").val(table.row(curRow).data().address);
							 $("#labtel").val(table.row(curRow).data().tel);
							 $("#operadept").val(table.row(curRow).data().regDeptName);
							 $("#operatime").val(table.row(curRow).data().regDatetime.substring(0,10));
							 }else if(useflg==3){

							    	if(curRow == null){
										  alert("请先选择一条要删除的记录！");
										  return;
									  }
							    	if(confirm("确定要删除吗?")){
							    		var otherTestLabID=table.row(curRow).data().otherTestLabID;
										DelOtherTestLab(otherTestLabID);
							    	}
							    	
								 
							 }else if(useflg==4){  
								 if(curRow == null){
									  alert("请先选择第三方检测机构！");
									  return;
								  }
								 $(".overlay").show();
								 $("#additemDialog").show();  
		      						 
							 }else if(useflg==5){
								 if(subcurRow == null){alert("选择要修改的项目");}
								 else{
								 $(".overlay").show();
								 $("#additemDialog").show(); 
								 $("#itemcheck").val(subtables.row(subcurRow).data().itemCode+" "+subtables.row(subcurRow).data().itemName);
							     $("#proclass").val(subtables.row(subcurRow).data().productClassCode+" "+subtables.row(subcurRow).data().productClassName);
								 $("#subproclass").val(subtables.row(subcurRow).data().productSubclassCode+" "+subtables.row(subcurRow).data().productSubclassName);
								 }
							 
							 }else if(useflg==6){
								 if(subcurRow == null){
									  alert("请先选择一条要删除的记录！");
									  return;
								  }
						    	if(confirm("确定要删除吗?")){
						    		var otherTestLabItemID=subtables.row(subcurRow).data().otherTestLabItemID;
									DelOtherTestLabItem(otherTestLabItemID);
						    	}
							 }
								
							}else{
								alert(rdata.data);
							}
						}, 'json');
					
				}		 
			 
			 
		  
	function GetOtherTestLab(testLabName){
		data={ 
				testLabName:testLabName
				
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("ThridTestLabAction_GetOtherTestLab?&ts="   
				+ new Date().getTime(), {
					 data : jsonstr
				}, function(rdata) {
					//$("#closeregisterentproductCheckDialogBtn").click();
					// $("#entproductInputDetailDataDialog").hide();
					
				    if(rdata.data=='true'){
				    	alert("审核成功");
				    	 $(".overlay").hide();
						 $("#entproductInputDetailDataDialog").hide(); 
						 $("#continuecheckproductDialog").hide();
						 producttable.row().remove().draw();
						 entcheckflg=0;
						 entchecktable.draw();
						 
				    	
				    }else{
				    	alert(rdata.data);
				    }
				}, 'json');
	}
	function GetOtherTestLabItem(otherTestLabID){
		data={ 
				otherTestLabID:otherTestLabID,
				
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("ThridTestLabAction_GetOtherTestLabItem?&ts="   
				+ new Date().getTime(), {
					 data : jsonstr
				}, function(rdata) {
					//$("#closeregisterentproductCheckDialogBtn").click();
					// $("#entproductInputDetailDataDialog").hide();
					
				    if(rdata.data=='true'){
				    	alert("审核成功");
				    	 $(".overlay").hide();
						 $("#entproductInputDetailDataDialog").hide(); 
						 $("#continuecheckproductDialog").hide();
						 producttable.row().remove().draw();
						 entcheckflg=0;
						 entchecktable.draw();
						 
				    	
				    }else{
				    	alert(rdata.data);
				    }
				}, 'json');
	}
	function SaveOtherTestLab(otherTestLabID,testLabCode,testLabName,address,tel){
		data={ 
				otherTestLabID:otherTestLabID,
				testLabCode:testLabCode,
				testLabName : testLabName,
				address : address,
				tel : tel
				
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("ThridTestLabAction_SaveOtherTestLab?&ts="   
				+ new Date().getTime(), {
					 data : jsonstr
				}, function(rdata) {
					//$("#closeregisterentproductCheckDialogBtn").click();
					// $("#entproductInputDetailDataDialog").hide();
					
				    if(rdata.data=='true'){
				    	
				    	alert("保存成功");
				    	table.draw();
				        $("#closelabDialogBtn").click();
				    	
				    }else{
				    	alert(rdata.data);
				    }
				}, 'json');
	}
	function SaveOtherTestLabItem(otherTestLabItemID,otherTestLabID,itemCode,productClassCode,productSubclassCode){
		data={ 
				otherTestLabItemID:otherTestLabItemID,
				otherTestLabID:otherTestLabID,
				itemCode : itemCode,
				productClassCode : productClassCode,
				productSubclassCode : productSubclassCode
				
		}; 
		if(!checkJsonParam(data)){
			return ;
			}
		 var jsonstr = JSON.stringify(data);
		$.post("ThridTestLabAction_SaveOtherTestLabItem?&ts="   
				+ new Date().getTime(), {
					 data : jsonstr
				}, function(rdata) {
					//$("#closeregisterentproductCheckDialogBtn").click();
					// $("#entproductInputDetailDataDialog").hide();
					
				    if(rdata.data=='true'){
				    	
				    	alert("保存成功");
				    	subtables.draw();
				        $("#closeitemDialogBtn").click();
				    	
				    }else{
				    	alert(rdata.data);
				    }
				}, 'json');
	}
	function DelOtherTestLab(otherTestLabID){
		data={ 
				otherTestLabID :otherTestLabID
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("ThridTestLabAction_DelOtherTestLab?&ts="   
				+ new Date().getTime(), {
					 data : jsonstr
				}, function(rdata) {
					//$("#closeregisterentproductCheckDialogBtn").click();
					// $("#entproductInputDetailDataDialog").hide();
					
					
				    if(rdata.data=='true'){
				    	
				    	alert("删除成功");
						table.draw();
				    }else{
				    	alert(rdata.data);
				    }
				}, 'json');
	}
		function DelOtherTestLabItem(otherTestLabItemID){
			data={ 
					otherTestLabItemID :otherTestLabItemID
			}; 
			 var jsonstr = JSON.stringify(data);
			$.post("ThridTestLabAction_DelOtherTestLabItem?&ts="   
					+ new Date().getTime(), {
						 data : jsonstr
					}, function(rdata) {
						//$("#closeregisterentproductCheckDialogBtn").click();
						// $("#entproductInputDetailDataDialog").hide();
						
						
					    if(rdata.data=='true'){
					    	alert("删除成功");
							subtables.draw();
					    	
					    }else{
					    	alert(rdata.data);
					    }
					}, 'json');
		}	
});