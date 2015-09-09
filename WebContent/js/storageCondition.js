$(document).ready(function(){
	
	
	
	 var curRow = null; //局部变量
	 var productClassCode=null;
	 var subcurRow = null;
	 var useflg=null;
	 var menunam='储存条件设置';
	  
//页面进入加载产品大类
	var columnproClass = [
	                  { "data": "storageConditionID" },
	                  { "data": "conditionCode" },
	      	          { "data": "conditionName" },
	                 ];
	var table = $('#storageConditionTb').DataTable( {
		  "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "search":true,
		  "ajax":{
			  url : "StorageConditionAction_getStorageCondition",
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
	//初始化第一行默认选中
	$('#storageConditionTb').on( 'draw.dt', function () {
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
	
	
	$('#storageConditionTb tbody').on("click", "tr", clickRow );
	
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
				     conditionName : "",
				  
				     };
		  var jsonstr = JSON.stringify(data);   
		  
		  return jsonstr;
	  }
	  
	//add productClass dialog show and hide
		 $("#addstorageCondition").click(function(){
			  
			 useflg=1;
			 getoperatelimit(menunam, useflg);
		  });
		 $("#closeaddstorageConditionDialogBtn").click(function(){
			  $(".overlay").hide();
		       $("#addstorageConditionDialog").hide(); 
		        $("#storageConditionCode").val("");
				$("#storageConditionName").val(""); 
		  });
		 // update productClass dialog show and hide
		 $("#editstorageCondition").click(function(){
			 if(curRow==null){alert("选择要修改的储存条件");return;}
			 useflg=2;
			 getoperatelimit(menunam, useflg);
			
			 
			 });
		 
		 //保存功能
		 $("#savestorageConditionBtn").click(function(){
			 if(useflg==1){
				 saveProClass('0',"#storageConditionCode","#storageConditionName");   
			 }else if(useflg==2){
				 var storageConditionID=table.row(curRow).data().storageConditionID;
					
				 saveProClass(storageConditionID,"#storageConditionCode","#storageConditionName"); 
			 }
			 		  
		  });
		
		 
		 //保存产品大类函数
		  function saveProClass(boole,classcodeID,classNameID){
			 var conditionCode = $(classcodeID).val().trim();
			  var conditionName = $(classNameID).val().trim();
			 
			  if(conditionCode==null || conditionCode=="" ||conditionName==null || conditionName=="" )
			   {alert("信息填写完整");}
			  else if(conditionCode.length>2){
				  alert("储存条件编号字符串长度必须应小于等于2");
			  }else{
				  data={  
						   storageConditionID:boole,
							conditionCode : conditionCode,
							conditionName : conditionName
			    	};   
				  var jsonstr = JSON.stringify(data);
			  $.post("StorageConditionAction_SaveStorageCondition?&ts="   
						+ new Date().getTime(), {
							 data : jsonstr
						}, function(rdata) {
					if(rdata.data == "true"){ 
						if(useflg==1){
							curRow=null;
							table.draw();
						}else if(useflg==2){
						
							table.draw(false);
						}
						
						$("#closeaddstorageConditionDialogBtn").click();
						
				    }else{
						alert(rdata.data);
						//$("#closeaddstorageConditionDialogBtn").click();
					}
				}, 'json');
		  }	
		  }
		  //删除
		  $("#deletestorageCondition").click(function(){
			  
			     useflg=3;
				 getoperatelimit(menunam, useflg);
			  	  
		  });
		  function delproClass(){
			  if(curRow == null){
				  alert("请先选择一条要删除的记录！");
				  return;
			  }
			  if(confirm("确认要删除该条记录?")){
				  var storageConditionID=table.row(curRow).data().storageConditionID;
				    
				  $.post("StorageConditionAction_DelStorageCondition?&ts="
							+ new Date().getTime(), {
								data: JSON.stringify({storageConditionID : storageConditionID})
							
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
						    $("#addstorageConditionDialog").show();
						 }else if(useflg==2){
						 $(".overlay").show();
						 $("#addstorageConditionDialog").show(); 
						 $("#storageConditionCode").val(table.row(curRow).data().conditionCode);
						 $("#storageConditionName").val(table.row(curRow).data().conditionName);
						
						 }else if(useflg==3){
							 delproClass();
							 
						 }
							
						}else{
							alert(rdata.data);
						}
					}, 'json');
				
			}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	function DelStorageCondition(storageConditionID){
		data={ 
				storageConditionID:storageConditionID
				
				
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("StorageConditionAction_DelStorageCondition?&ts="   
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
	function SaveStorageCondition(storageConditionID,conditionCode,conditionName){
		data={ 
				storageConditionID:storageConditionID,
				conditionCode : conditionCode,
				conditionName : conditionName
				
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("StorageConditionAction_SaveStorageCondition?&ts="   
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
	function getStorageCondition(){
		data={ 
				
				
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("StorageConditionAction_getStorageCondition?&ts="   
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
		 window.location.href="StorageConditionExportAction";
	}
});