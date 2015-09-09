$(document).ready(function(){
	
	
	
	 var curRow = null; //局部变量
	 var productClassCode=null;
	 var subcurRow = null;
	 var useflg=null;
	 var menunam='辅料设置';
	 var accessoryname='';
	  
//页面进入加载产品大类
	var columnproClass = [
	                  { "data": "accessoryID" },
	                  { "data": "accessoryName" },
	      	        
	                 ];
	var table = $('#accessorySetTb').DataTable( {
		  "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "search":true,
		  "ajax":{
			  url : "AccessorySetAction_getAccessory",
			  type : "GET",
			  data : function(d){
				  d.data = getRequestParam();
				 
			  }
		  },
	      "columns": columnproClass,
	     /* "columnDefs": [
	                     {
	                         "targets": [ 0 ],
	                         "visible": false,
	                     }
	                 ]*/
	  } );
	//初始化第一行默认选中
	$('#accessorySetTb').on( 'draw.dt', function () {
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
		 
	  } );
	
	
	$('#accessorySetTb tbody').on("click", "tr", clickRow );
	
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
		 
	  }
	 
	//获取请求参数
	  function getRequestParam(){
		  var data = {
				     accessoryName : accessoryname
				  
				     };
		  var jsonstr = JSON.stringify(data);   
		  
		  return jsonstr;
	  }
	  //查询
	  $("#queryaccessorySet").click(function(){
		  
		    $(".overlay").show();
	        $("#queryaddaccessorySetDialog").show(); 
	       
		  });
	  $("#querysaveaccessorySetBtn").click(function(){
		  
		    accessoryname=$("#queryaccessorySetName").val().trim();
		    if(accessoryname==''){alert("请输入辅料名称字段");return;}
	        table.draw();
	        $("#querycloseaddaccessorySetDialogBtn").click();
		  });
	  //查询取消
	  $("#querycloseaddaccessorySetDialogBtn").click(function(){
		    $(".overlay").hide();
	        $("#queryaddaccessorySetDialog").hide(); 
	      
			$("#queryaccessorySetName").val(""); 
	  });
	//add productClass dialog show and hide
		 $("#addaccessorySet").click(function(){
			  
			 useflg=1;
			 getoperatelimit(menunam, useflg);
		  });
		 $("#closeaddaccessorySetDialogBtn").click(function(){
			    $(".overlay").hide();
		        $("#addaccessorySetDialog").hide(); 
		      
				$("#accessorySetName").val(""); 
		  });
		 // update productClass dialog show and hide
		 $("#editaccessorySet").click(function(){
			 if(curRow==null){alert("选择要修改的辅料名称");return;}
			 useflg=2;
			 getoperatelimit(menunam, useflg);
			
			 
			 });
		 
		 //保存功能
		 $("#saveaccessorySetBtn").click(function(){
			 if(useflg==1){
				 saveProClass('0',"#accessorySetName");   
			 }else if(useflg==2){
				 var accessoryID=table.row(curRow).data().accessoryID;
					
				 saveProClass(accessoryID,"#accessorySetName"); 
			 }
			 		  
		  });
		
		 
		 //保存产品大类函数
		  function saveProClass(boole,classNameID){
			
			  var accessoryName = $(classNameID).val().trim();
			 
			  if(accessoryName==null || accessoryName=="" )
			   {alert("信息填写完整");}
			  else{
				  data={  
						   accessoryID:boole,
						   accessoryName : accessoryName
			    	};   
				  var jsonstr = JSON.stringify(data);
			  $.post("AccessorySetAction_SaveAccessory?&ts="   
						+ new Date().getTime(), {
							 data : jsonstr
						}, function(rdata) {
					if(rdata.data == "true"){ 
						if(useflg==1){
							curRow=null;
							accessoryname='';
							table.draw();
						}else if(useflg==2){
							
							table.draw(false);
						}
						
						$("#closeaddaccessorySetDialogBtn").click();
						
				    }else{
						alert(rdata.data);
						//$("#closeaddstorageConditionDialogBtn").click();
					}
				}, 'json');
		  }	
		  }
		  //删除
		  $("#deleteaccessorySet").click(function(){
			  
			     useflg=3;
				 getoperatelimit(menunam, useflg);
			  	  
		  });
		  function delproClass(){
			  if(curRow == null){
				  alert("请先选择一条要删除的记录！");
				  return;
			  }
			  if(confirm("确认要删除该条记录?")){
				  var accessoryID=table.row(curRow).data().accessoryID;
				    
				  $.post("AccessorySetAction_DelAccessory?&ts="
							+ new Date().getTime(), {
								data: JSON.stringify({accessoryID : accessoryID})
							
					}, function(rdata) {   
						if(rdata.data == "true"){
							alert("删除成功！");
							table.row(curRow).remove().draw(false);
						}else{
							alert("删除失败！");
						}
					}, 'json');
			  }

		  }
		  
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
						    $("#addaccessorySetDialog").show();
						 }else if(useflg==2){
						 $(".overlay").show();
						 $("#addaccessorySetDialog").show(); 
						 $("#accessorySetName").val(table.row(curRow).data().accessoryName);
						
						 }else if(useflg==3){
							 delproClass();
							 
						 }
							
						}else{
							alert(rdata.data);
						}
					}, 'json');
				
			}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
function SaveAccessory(AccessoryID,AccessoryName){
	data={ 
			accessoryID:accessoryID,
			accessoryName : accessoryName
			
			
	}; 
	 var jsonstr = JSON.stringify(data);
	$.post("AccessorySetAction_SaveAccessory?&ts="   
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
function DelAccessory(accessoryID){
	data={ 
			accessoryID:accessoryID
			
			
	}; 
	 var jsonstr = JSON.stringify(data);
	$.post("AccessorySetAction_DelAccessory?&ts="   
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
function getAccessory(accessoryName){
	data={ 
			accessoryName:accessoryName
		
			
	}; 
	 var jsonstr = JSON.stringify(data);
	$.post("AccessorySetAction_getAccessory?&ts="   
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
$("#export").click(exportData);
function exportData(){
	 window.location.href="AccessoryExportAction?data="+getRequestParam();
}


});