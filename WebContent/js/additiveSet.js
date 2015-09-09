$(document).ready(function(){
	
	
	
	 var curRow = null; //局部变量
	 var productClassCode=null;
	 var subcurRow = null;
	 var useflg=null;
	 var menunam='添加剂设置';
	 var additivename='';
	  
//页面进入加载产品大类
	var columnproClass = [
	                  { "data": "additiveID" },
	                  { "data": "additiveName" },
	      	        
	                 ];
	var table = $('#additiveSetTb').DataTable( {
		  "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "search":true,
		  "ajax":{
			  url : "AdditiveSetAction_getAdditive",
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
	$('#additiveSetTb').on( 'draw.dt', function () {
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
	
	
	$('#additiveSetTb tbody').on("click", "tr", clickRow );
	
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
				     additiveName : additivename
				  
				     };
		  var jsonstr = JSON.stringify(data);   
		  
		  return jsonstr;
	  }
	  //查询
	  $("#queryadditiveSet").click(function(){
		  
		    $(".overlay").show();
	        $("#queryaddadditiveSetDialog").show(); 
	       
		  });
	  $("#querysaveadditiveSetBtn").click(function(){
		  
		    additivename=$("#queryadditiveSetName").val().trim();
		    if(additivename==''){alert("请输入添加剂名称字段");return;}
	        table.draw();
	        $("#querycloseaddadditiveSetDialogBtn").click();
		  });
	  //查询取消
	  $("#querycloseaddadditiveSetDialogBtn").click(function(){
		    $(".overlay").hide();
	        $("#queryaddadditiveSetDialog").hide(); 
	      
			$("#queryadditiveSetName").val(""); 
	  });
	//add productClass dialog show and hide
		 $("#addadditiveSet").click(function(){
			  
			 useflg=1;
			 getoperatelimit(menunam, useflg);
		  });
		 $("#closeaddadditiveSetDialogBtn").click(function(){
			    $(".overlay").hide();
		        $("#addadditiveSetDialog").hide(); 
		      
				$("#additiveSetName").val(""); 
		  });
		 // update productClass dialog show and hide
		 $("#editadditiveSet").click(function(){
			 if(curRow==null){alert("选择要修改的添加剂名称");return;}
			 useflg=2;
			 getoperatelimit(menunam, useflg);
			
			 
			 });
		 
		 //保存功能
		 $("#saveadditiveSetBtn").click(function(){
			 if(useflg==1){
				 saveProClass('0',"#additiveSetName");   
			 }else if(useflg==2){
				 var additiveID=table.row(curRow).data().additiveID;
					
				 saveProClass(additiveID,"#additiveSetName"); 
			 }
			 		  
		  });
		
		 
		 //保存产品大类函数
		  function saveProClass(boole,classNameID){
			
			  var additiveName = $(classNameID).val().trim();
			 
			  if(additiveName==null || additiveName=="" )
			   {alert("信息填写完整");}
			  else{
				  data={  
						   additiveID:boole,
						   additiveName : additiveName
			    	};   
				  var jsonstr = JSON.stringify(data);
			  $.post("AdditiveSetAction_SaveAdditive?&ts="   
						+ new Date().getTime(), {
							 data : jsonstr
						}, function(rdata) {
					if(rdata.data == "true"){ 
						if(useflg==1){
							curRow=null;
							additivename='';
							table.draw();
						}else if(useflg==2){
							
							table.draw(false);
						}
						
						$("#closeaddadditiveSetDialogBtn").click();
						
				    }else{
						alert(rdata.data);
						//$("#closeaddstorageConditionDialogBtn").click();
					}
				}, 'json');
		  }	
		  }
		  //删除
		  $("#deleteadditiveSet").click(function(){
			  
			     useflg=3;
				 getoperatelimit(menunam, useflg);
			  	  
		  });
		  function delproClass(){
			  if(curRow == null){
				  alert("请先选择一条要删除的记录！");
				  return;
			  }
			  if(confirm("确认要删除该条记录?")){
				  var additiveID=table.row(curRow).data().additiveID;
				    
				  $.post("AdditiveSetAction_DelAdditive?&ts="
							+ new Date().getTime(), {
								data: JSON.stringify({additiveID : additiveID})
							
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
						    $("#addadditiveSetDialog").show();
						 }else if(useflg==2){
						 $(".overlay").show();
						 $("#addadditiveSetDialog").show(); 
						 $("#additiveSetName").val(table.row(curRow).data().additiveName);
						
						 }else if(useflg==3){
							 delproClass();
							 
						 }
							
						}else{
							alert(rdata.data);
						}
					}, 'json');
				
			}	
	
	
	
	  
	
	
	
	
	
	
	
	function SaveAdditive(additiveID,additiveName){
		data={ 
				additiveID:additiveID,
				additiveName :additiveName
			
				
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("AdditiveSetAction_SaveAdditive?&ts="   
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
	function DelAdditive(additiveID){
		data={ 
				additiveID:additiveID
			
				
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("AdditiveSetAction_DelAdditive?&ts="   
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
	function getAdditive(additiveName){
		data={ 
				additiveName:additiveName
			
				
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("AdditiveSetAction_getAdditive?&ts="   
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
		 window.location.href="AdditiveExportAction";
	}
});