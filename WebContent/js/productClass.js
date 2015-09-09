$(document).ready(
		function(){
			 var curRow = null; //局部变量
			 var productClassCode=null;
			 var subcurRow = null;
			 var useflg=null;
			var menunam='产品类别设置';
			  
	//页面进入加载产品大类
			var columnproClass = [
			                  { "data": "productClassID" },
			                  { "data": "classCode" },
			      	          { "data": "className" },
			                 ];
			var table = $('#productClassTb').DataTable( {
				  "deferRender": true,
				  "processing": true,
				  "serverSide": true,
				  "search":true,
				  "ajax":{
					  url : "ProductClassAction_getProductClass",
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
			$('#productClassTb').on( 'draw.dt', function () {
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
			$('#productSubClassTb').on( 'draw.dt', function () {
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
			//小类表
			var columnproSubClass = [
			                          { "data": "productSubclassID" },
			                          { "data": "classCode" },
					      	          { "data": "subclassCode" },
	   				      	          { "data": "subclassName" },
					                 ]; 
			var subtables = $('#productSubClassTb').DataTable( {
				"info" : false,
				"paging" : false,
				"lengthChange" : false,
				"deferRender": true,
				"processing": true,
				"serverSide": true,
				  
				   "ajax":{
					  url : "SelectDataAction_getProductSubclass",
					  type : "GET",
					  data :  function(d){
						  if(curRow == null){
							  return {productClassCode : 0};
						  }else{
							  //获得convCtrlID
							  return {productClassCode : table.row(curRow).data().classCode};
						  }
					 }
			  },
			      "columns": columnproSubClass,
			     "columnDefs": [
			                     {
			                         "targets": [ 0 ],
			                         "visible": false,
			                     },
			                     {
			                         "targets": [ 1 ],
			                         "visible": false,
			                     }
			                 ]
			      
		    });  
			
			//点击选中某一行
			$('#productClassTb tbody').on("click", "tr", clickRow );
			$('#productSubClassTb tbody').on("click", "tr", clickRowSub );
			
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
			  
			
							
				 
			
			
			//获取请求参数
			  function getRequestParam(){
				  var data = {
						    productClassCode : "",
						    productClassName : "",
						     };
				  var jsonstr = JSON.stringify(data);   
				  
				  return jsonstr;
			  }
			  //
			  function getRequestParam2(){
				  if(productClassCode == null){productClassCode = table.row('tr:first').data().classCode;}
				  else { productClassCode = table.row(curRow).data().classCode;}
				 
				  var data = {
						    productClassCode : productClassCode
						   
						     };
				  var jsonstr = JSON.stringify(data);
				  
				  return jsonstr;
			  }
		
			
			//add productClass dialog show and hide
			 $("#addproductClass").click(function(){
				  
				 useflg=1;
				 getoperatelimit(menunam, useflg);
			  });
			 $("#closeaddproductClassDialogBtn").click(function(){
				  $(".overlay").hide();
			      $("#addproductClassDialog").hide(); 
			      $("#productClassCode").val("");
					$("#productClassName").val(""); 
			  });
			 // update productClass dialog show and hide
			 $("#editproductClass").click(function(){
				 if(curRow==null){alert("选择要修改的产品大类");return;}
				 useflg=2;
				 getoperatelimit(menunam, useflg);
				
				 
				 });
			 $("#closeupdateproductClassDialogBtn").click(function(){
				  $(".overlay").hide();
			      $("#updateproductClassDialog").hide();   
			  });
			//add productSubClass dialog show and hide
			 $("#addproductSubClass").click(function(){
				 if(curRow == null){
					  alert("请先选择相应产品大类！");
					  return;
				  }
				 useflg=4;
				 getoperatelimit(menunam, useflg);
				  
			  });
			 $("#closeaddproductSubClassDialogBtn").click(function(){
				  $(".overlay").hide();
			      $("#addproductSubClassDialog").hide(); 
			      clearAlertDiv("addproductSubClassDialog");
			  });
			 // update productSubClass dialog show and hide
			 $("#editproductSubClass").click(function(){
				 useflg=5;
				 getoperatelimit(menunam, useflg);
				
			  });
			 $("#closeupdateproductSubClassDialogBtn").click(function(){  
				  $(".overlay").hide();
			      $("#updateproductSubClassDialog").hide();   
			  });
			 //保存产品大类
			 $("#saveproductClassBtn").click(function(){
				  saveProClass('0',"#productClassCode","#productClassName");   		  
			  });
			 //修改产品大类
			 $("#updateproductClassBtn").click(function(){
				 
				 var productClassID=table.row(curRow).data().productClassID;
				
				 saveProClass(productClassID,"#updateproductClassCode","#updateproductClassName");   		  
				 $("#updateproductClassDialog").hide();    
			 });
			 
			 //保存产品大类函数
			  function saveProClass(boole,classcodeID,classNameID){
				 var productClassCode = $(classcodeID).val().trim();
				  var productClassName = $(classNameID).val().trim();
				 
				  if(productClassCode==null || productClassCode=="" ||productClassName==null || productClassName=="" )
				   {alert("信息填写完整");}
				  else if(productClassCode.length!=2){
					  alert("产品大类编号字符串长度必须为2");
				  }else{
					  data={    productClassID:boole,
				    	         classCode:productClassCode,
				    	        className:productClassName
				    	};   
					  var jsonstr = JSON.stringify(data);
				  $.post("ProductClassAction_saveProClass?&ts="   
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
							
							$("#closeaddproductClassDialogBtn").click();
							$("#productClassCode").val("");
							$("#productClassName").val(""); 
					    }else{
							alert(rdata.data);
							$("#closeaddproductClassDialogBtn").click();
						}
					}, 'json');
			  }	
			  }
			  //删除产品大类
			  $("#deleteproductClass").click(function(){
				  
				  useflg=3;
					 getoperatelimit(menunam, useflg);
				  	  
			  });
			  function delproClass(){
				  if(curRow == null){
					  alert("请先选择一条要删除的记录！");
					  return;
				  }
				  if(confirm("确认要删除该条记录?")){
					  var productClassID=table.row(curRow).data().productClassID;
  				    
					  $.post("ProductClassAction_deleteProductClass?&ts="
								+ new Date().getTime(), {
									data: JSON.stringify({productClassID : productClassID})
								
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
			  //保存产品小类
			  $("#saveproductSubClassBtn").click(function(){
				 var classCode= table.row(curRow).data().classCode;
				 saveProSubClass(classCode, "#productSubClassCode", "#productSubClassName", '0');
			  });
			  function saveProSubClass(classCode,subclasscodeID,subclassNameID,boole){
					  var subclassCode = $(subclasscodeID).val().trim();
					  var subclassName = $(subclassNameID).val().trim();
					  if(subclassCode==null || subclassCode=="" ||subclassName==null || subclassName=="" )
					   {alert("信息填写完整");}
					  else if(subclassCode.length !=2){
						  alert("产品小类编号字符串长度必须为2");
					  }else{
						  data={    productSubclassID:boole,
								    classCode:classCode,
								    subclassCode:subclassCode,
					    	        subclassName:subclassName
					    	};   
						  var jsonstr = JSON.stringify(data);
					  $.post("ProductClassAction_saveSubProductClass?&ts="   
								+ new Date().getTime(), {
									 data : jsonstr
								}, function(rdata) {  
							if(rdata.data == "true"){
								if(useflg==4){
									subcurRow=null;
									subtables.draw();
								}else if(useflg==5){
									subtables.draw(false);
								}
								subtables.draw();       
								/*curRow=null;
								subcurRow=null;*/
								
								$("#closeaddproductSubClassDialogBtn").click();
								$("#productSubClassCode").val("");
								$("#productSubClassName").val(""); 
						    }else{
								alert(rdata.data);
							}
						}, 'json');
				  }	
				  }
			  //修改产品小类
			   
			  $("#updateproductSubClassBtn").click(function(){
				  var classCode= subtables.row(subcurRow).data().classCode; 
				  var productSubclassID= subtables.row(subcurRow).data().productSubclassID;
				  saveProSubClass(classCode, "#updateproductSubClassCode", "#updateproductSubClassName", productSubclassID);   		  
				  $("#updateproductSubClassDialog").hide();      
				 });
			  //删除产品小类  
			  $("#deleteproductSubClass").click(function(){
				    useflg=6;
					 getoperatelimit(menunam, useflg);
				   	  
			  });
			  function delproSubClass(){
				  if(subcurRow == null){
					  alert("请先选择一条要删除的记录！");
					  return;
				  }
				  if(confirm("确认要删除该条记录?")){
					  var productSubclassID=subtables.row(subcurRow).data().productSubclassID;
  				    
					  $.post("ProductClassAction_deleteSubProductClass?&ts="
								+ new Date().getTime(), {
									data: JSON.stringify({productSubclassID : productSubclassID})
								
						}, function(rdata) {   
							if(rdata.data == "true"){
								alert("删除成功！");
								subtables.row(subcurRow).remove().draw(false);
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
							    $("#addproductClassDialog").show();
							 }else if(useflg==2){
							 $(".overlay").show();
							 $("#updateproductClassDialog").show(); 
							 $("#updateproductClassCode").val(table.row(curRow).data().classCode);
							 $("#updateproductClassName").val(table.row(curRow).data().className);
							
							 }else if(useflg==3){
								 delproClass();
								 
							 }else if(useflg==4){  
								 $(".overlay").show();
								 $("#addproductSubClassDialog").show();  
		      						 
							 }else if(useflg==5){
								 if(subcurRow == null){alert("选择要修改的产品小类");}
								 else{
								 $(".overlay").show();
								 $("#updateproductSubClassDialog").show(); 
								 $("#updateproductSubClassCode").val(subtables.row(subcurRow).data().subclassCode);
								 $("#updateproductSubClassName").val(subtables.row(subcurRow).data().subclassName);
								 }
								 
							 }else if(useflg==6){
								 delproSubClass();
							 }
								
							}else{
								alert(rdata.data);
							}
						}, 'json');
					
				}	 
			 
			 
$("#export").click(exportData);
function exportData(){
	 window.location.href="ProductClassForExportAction";
}
	

	
	
});