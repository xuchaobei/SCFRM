$(document).ready(function(){
	
	
	
	 var curRow = null; //局部变量
	 var productClassCode=null;
	 var subcurRow = null;
	 var useflg=null;
	 var menunam='加工程度设置';
	  
//页面进入加载产品大类
	var columnproClass = [
	                  { "data": "processingDegreeID" },
	                  { "data": "degreeCode" },
	      	          { "data": "degreeName" },
	                 ];
	var table = $('#processingDegreeTb').DataTable( {
		  "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "search":true,
		  "ajax":{
			  url : "ProcessingDegreeAction_getProcessingDegree",
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
	$('#processingDegreeTb').on( 'draw.dt', function () {
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
	
	
	$('#processingDegreeTb tbody').on("click", "tr", clickRow );
	
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
				     degreeName : "",
				  
				     };
		  var jsonstr = JSON.stringify(data);   
		  
		  return jsonstr;
	  }
	  
	//add productClass dialog show and hide
		 $("#addprocessingDegree").click(function(){
			  
			 useflg=1;
			 getoperatelimit(menunam, useflg);
		  });
		 $("#closeaddprocessingDegreeDialogBtn").click(function(){
			  $(".overlay").hide();
		       $("#addprocessingDegreeDialog").hide(); 
		        $("#processingDegreeCode").val("");
				$("#processingDegreeName").val(""); 
		  });
		 // update productClass dialog show and hide
		 $("#editprocessingDegree").click(function(){
			 if(curRow==null){alert("选择要修改的加工程度");return;}
			 useflg=2;
			 getoperatelimit(menunam, useflg);
			
			 
			 });
		 
		 //保存功能
		 $("#saveprocessingDegreeBtn").click(function(){
			 if(useflg==1){
				 saveProClass('0',"#processingDegreeCode","#processingDegreeName");   
			 }else if(useflg==2){
				 var processingDegreeID=table.row(curRow).data().processingDegreeID;
					
				 saveProClass(processingDegreeID,"#processingDegreeCode","#processingDegreeName"); 
			 }
			 		  
		  });
		
		 
		 //保存产品大类函数
		  function saveProClass(boole,classcodeID,classNameID){
			 var degreeCode = $(classcodeID).val().trim();
			  var degreeName = $(classNameID).val().trim();
			 
			  if(degreeCode==null || degreeCode=="" ||degreeName==null || degreeName=="" )
			   {alert("信息填写完整");}
			  else if(degreeCode.length>2){
				  alert("加工程度编号字符串长度必须应小于等于2");
			  }else{
				  data={  
						   processingDegreeID:boole,
							degreeCode : degreeCode,
							degreeName : degreeName
			    	};   
				  var jsonstr = JSON.stringify(data);
			  $.post("ProcessingDegreeAction_SaveProcessingDegree?&ts="   
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
						
						$("#closeaddprocessingDegreeDialogBtn").click();
						
				    }else{
						alert(rdata.data);
						//$("#closeaddproductClassDialogBtn").click();
					}
				}, 'json');
		  }	
		  }
		  //删除
		  $("#deleteprocessingDegree").click(function(){
			  
			     useflg=3;
				 getoperatelimit(menunam, useflg);
			  	  
		  });
		  function delproClass(){
			  if(curRow == null){
				  alert("请先选择一条要删除的记录！");
				  return;
			  }
			  if(confirm("确认要删除该条记录?")){
				  var processingDegreeID=table.row(curRow).data().processingDegreeID;
				    
				  $.post("ProcessingDegreeAction_DelProcessingDegree?&ts="
							+ new Date().getTime(), {
								data: JSON.stringify({processingDegreeID : processingDegreeID})
							
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
						    $("#addprocessingDegreeDialog").show();
						 }else if(useflg==2){
						 $(".overlay").show();
						 $("#addprocessingDegreeDialog").show(); 
						 $("#processingDegreeCode").val(table.row(curRow).data().degreeCode);
						 $("#processingDegreeName").val(table.row(curRow).data().degreeName);
						
						 }else if(useflg==3){
							 delproClass();
							 
						 }
							
						}else{
							alert(rdata.data);
						}
					}, 'json');
				
			}	
	
	
	
	
	
function SaveProcessingDegree(processingDegreeID,degreeCode,degreeName){
	data={ 
			processingDegreeID:processingDegreeID,
			degreeCode : degreeCode,
			degreeName : degreeName    
			
	}; 
	 var jsonstr = JSON.stringify(data);
	$.post("ProcessingDegreeAction_SaveProcessingDegree?&ts="   
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
function DelProcessingDegree(processingDegreeID){
	data={ 
			processingDegreeID:processingDegreeID
			
	}; 
	 var jsonstr = JSON.stringify(data);
	$.post("ProcessingDegreeAction_DelProcessingDegree?&ts="   
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
function getProcessingDegree(){
	data={ 
			
	}; 
	 var jsonstr = JSON.stringify(data);
	$.post("ProcessingDegreeAction_getProcessingDegree?&ts="   
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
	 window.location.href="ProcessDegreeExportAction";
}
});