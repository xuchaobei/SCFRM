$(document).ready(function(){
	
	
	 var curRow = null; //局部变量
	 var productClassCode=null;
	 var subcurRow = null;
	 var useflg=null;
	 var menunam='添加剂用途设置';
	 var purposename='';
	  
//页面进入加载产品大类
	var columnproClass = [
	                  { "data": "additivePurposeID" },
	                  { "data": "purpose" },
	      	        
	                 ];
	var table = $('#additivePurposeSetTb').DataTable( {
		  "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "search":true,
		  "ajax":{
			  url : "AdditivePurposeAction_getAdditivePurpose",
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
	$('#additivePurposeSetTb').on( 'draw.dt', function () {
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
	
	
	$('#additivePurposeSetTb tbody').on("click", "tr", clickRow );
	
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
				     purpose : purposename
				  
				     };
		  var jsonstr = JSON.stringify(data);   
		  
		  return jsonstr;
	  }
	  //查询按钮
	  $("#queryadditivePurposeSet").click(function(){
		  
		    $(".overlay").show();
	        $("#queryaddadditivePurposeSetDialog").show(); 
	       
		  });
	  //查询功能
	  $("#querysaveadditivePurposeSetBtn").click(function(){
		  
		    purposename=$("#queryadditivePurposeSetName").val().trim();
		    if(purposename==''){alert("请输入添加剂用途字段");return;}
	        table.draw();
	        $("#querycloseaddadditivePurposeSetDialogBtn").click();
		  });
	  //查询取消
	  $("#querycloseaddadditivePurposeSetDialogBtn").click(function(){
		    $(".overlay").hide();
	        $("#queryaddadditivePurposeSetDialog").hide(); 
	      
			$("#queryadditivePurposeSetName").val(""); 
	  });
	//add productClass dialog show and hide
		 $("#addadditivePurposeSet").click(function(){
			  
			 useflg=1;
			 getoperatelimit(menunam, useflg);
		  });
		 $("#closeaddadditivePurposeSetDialogBtn").click(function(){
			    $(".overlay").hide();
		        $("#addadditivePurposeSetDialog").hide(); 
		      
				$("#additivePurposeSetName").val(""); 
		  });
		 // update productClass dialog show and hide
		 $("#editadditivePurposeSet").click(function(){
			 if(curRow==null){alert("选择要修改的添加剂用途");return;}
			 useflg=2;
			 getoperatelimit(menunam, useflg);
			
			 
			 });
		 
		 //保存功能
		 $("#saveadditivePurposeSetBtn").click(function(){
			 if(useflg==1){
				 saveProClass('0',"#additivePurposeSetName");   
			 }else if(useflg==2){
				 var additivePurposeID=table.row(curRow).data().additivePurposeID;
					
				 saveProClass(additivePurposeID,"#additivePurposeSetName"); 
			 }
			 		  
		  });
		
		 
		 //保存产品大类函数
		  function saveProClass(boole,classNameID){
			
			  var purpose = $(classNameID).val().trim();
			 
			  if(purpose==null || purpose=="" )
			   {alert("信息填写完整");}
			  else{
				  data={  
						   additivePurposeID:boole,
						   purpose : purpose
			    	};   
				  var jsonstr = JSON.stringify(data);
			  $.post("AdditivePurposeAction_SaveAdditivePurpose?&ts="   
						+ new Date().getTime(), {
							 data : jsonstr
						}, function(rdata) {
					if(rdata.data == "true"){ 
						if(useflg==1){
							curRow=null;
							purposename='';
							table.draw();
						}else if(useflg==2){
							
							table.draw(false);
						}
						
						$("#closeaddadditivePurposeSetDialogBtn").click();
						
				    }else{
						alert(rdata.data);
						//$("#closeaddstorageConditionDialogBtn").click();
					}
				}, 'json');
		  }	
		  }
		  //删除
		  $("#deleteadditivePurposeSet").click(function(){
			  
			     useflg=3;
				 getoperatelimit(menunam, useflg);
			  	  
		  });
		  function delproClass(){
			  if(curRow == null){
				  alert("请先选择一条要删除的记录！");
				  return;
			  }
			  if(confirm("确认要删除该条记录?")){
				  var additivePurposeID=table.row(curRow).data().additivePurposeID;
				    
				  $.post("AdditivePurposeAction_DelAdditivePurpose?&ts="
							+ new Date().getTime(), {
								data: JSON.stringify({additivePurposeID : additivePurposeID})
							
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
						    $("#addadditivePurposeSetDialog").show();
						 }else if(useflg==2){
						 $(".overlay").show();
						 $("#addadditivePurposeSetDialog").show(); 
						 $("#additivePurposeSetName").val(table.row(curRow).data().purpose);
						
						 }else if(useflg==3){
							 delproClass();
							 
						 }
							
						}else{
							alert(rdata.data);
						}
					}, 'json');
				
			}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	   
	
	
	
	
	
	function SaveAdditivePurpose(additivePurposeID,purpose){
		data={ 
				additivePurposeID:additivePurposeID,
				purpose :purpose
			
				
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("AdditivePurposeAction_SaveAdditivePurpose?&ts="   
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
	function DelAdditivePurpose(additivePurposeID){
		data={ 
				additivePurposeID:additivePurposeID
			
				
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("AdditivePurposeAction_DelAdditivePurpose?&ts="   
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
	function getAdditivePurpose(purpose){
		data={ 
				purpose:purpose
			
				
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("AdditivePurposeAction_getAdditivePurpose?&ts="   
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
		 window.location.href="AddPurposeExportAction";
	}
});