$(document).ready(function(){
	
	var productCode='';
	var productName='';
	var productRow=null;
	//表数据的获取
	var columnClass = [
		                  { "data": "productCode" },
		                  { "data": "productName" },
		                  { "data": "className" },
		                  { "data": "subclassName" },
		      	          { "data": "methodName" },
		      	          { "data": "typeName" },         
		      	          { "data": "useName" }
		                 ];
	var productTable = $('#productTb').DataTable( {
			  "deferRender": true,
			  "processing": true,
			  "serverSide": true,
			  "ajax":{
				  url : "ProductStanderAction_getProductByQuery",
				  type : "GET",
				  data : function(d){
					//  d.data = getClassReqParam();
					  d.data=inintparm();
					 
				  }
			  },
		      "columns": columnClass,
		    "columnDefs": [
		                     /*{
		                         "targets": [ 0,1,3,7 ],
		                         "visible": false,
		                     }*/
		                 ]        
		  } );
	
	 function inintparm(){
		 data={ 
				 productCode : productCode,
				 productName :productName
					
					
			}; 
			 var jsonstr = JSON.stringify(data);
			return jsonstr;
						
					}
	
	 //draw事件
		$('#productTb').on( 'draw.dt', function () {
			
			if(productTable.rows().data().length > 0){
			var dtRow = null;
			var node = null;
			if(productRow != null){
			dtRow = productTable.row($(productRow).context._DT_RowIndex);
			node = dtRow.node();
			if(node != null){           
			$(node).click();
			return;
			}
			}
			dtRow = productTable.row(0);
			node = dtRow.node();
			$(node).click();
			}else{
				productRow = null;
			}
			});	
	//表中产品表某一行的点击功能
	    $('#productTb tbody').on("click", "tr", clickentRow );	
	    	function clickentRow(){  
			  var row = this;
			  if ( $(row).hasClass('active') ) {  
				  	productRow = null;
		            $(row).removeClass('active');
		        }
		        else {
		        	productRow = row;
		        	productTable.$('tr.active').removeClass('active');
		            $(row).addClass('active');
		            
		          
		        }  
		  }
	    	
	   var detailTable = $('#dmaterialTb').DataTable( {
	    		"info" : false,
	    		"paging" : false,
	    		"lengthChange" : false,
	    		"columns": [{"data":"className"},
	    		            { "data": "subclassName" },
	    		            {"data":"materialName"},
	    		            { "data": "sourceName" },
	    		            {"data":"ifMainMaterial",
	    		             "render": function ( data, type, full, meta ) {
	                    	 return data === '1'  ? '是' : '否';
	                     	}}
	    		           
	    		],
	    		"columnDefs": [
	    		/*{
	    		"targets": [ 0 ],
	    		"visible": false,
	    		}*/
	    		]
	    		} );  	
	   //查询按钮
			$("#queryproduct").click(function() {
				
					 $(".overlay").show();
					 $("#queryproDialog").show();
					
					
			});    	
	
	//查询中取消
			$("#closeDialogBtn").click(function() {            
				
				 $(".overlay").hide();          
				 $("#queryproDialog").hide(); 
				 clearAlertDiv("queryproDialog");
				
		});
	//查询功能
			$("#queryproBtn").click(function() {
				
				productCode=$("#procode").val().trim();
				productName=$("#proname").val().trim();
				productTable.draw();
				$("#closeDialogBtn").click();
				
		});	
			
		//导出
			$("#export").click(exportData);
			function exportData(){
				 window.location.href="ProductStandExportAction?data="+inintparm();
			}	
		//数据规范按钮
			$("#productStand").click(function() {
				
				 
				 if(productRow==null){
					 alert("请先选中一行");
					 return;
				 }
				 detailTable.clear().draw();
				 $(".overlay").show();
				 $("#detailDia").show();
				 var code=productTable.row(productRow).data().productCode;
				 getProductByCode(code);
				
				
		}); 
			//数据规范中规范功能
			$("#sureStand").click(function() {
				var code=$("#dprocode").val().trim();
				var name=$("#dproname").val().trim();
				normalizeProductName(code, name);
		}); 
			function normalizeProductName(productCode,productName){
				data={ 
						productCode :productCode,
						productName:productName
						
				}; 
				 var jsonstr = JSON.stringify(data);
				$.post("ProductStanderAction_normalizeProductName?&ts="   
						+ new Date().getTime(), {
							 data : jsonstr
						}, function(rdata) {
							if(rdata.data=="true"){
								alert("数据规范成功");    
								$("#closeDetialDia").click();
								productTable.draw();
							}else{
								alert(rdata.data);
							}
							
						}, 'json');
		}
		//数据规范中取消
			$("#closeDetialDia").click(function() {            
				
				 $(".overlay").hide();          
				 $("#detailDia").hide(); 
				 detailTable.clear().draw();
				 clearAlertDiv("detailDia");
				
		});
			function getProductByCode(productCode){
				data={ 
						productCode : productCode
						
						
				}; 
				 var jsonstr = JSON.stringify(data);
				$.post("ProductStanderAction_getProductByCode?&ts="   
						+ new Date().getTime(), {
							 data : jsonstr
						}, function(rdata) {
							
							var material=rdata.data[0].two;  
							var detailData=rdata.data[0].one[0];
							var productCode=detailData.productCode;
							var productName=detailData.productName;
							var className=detailData.className;
							var subclassName=detailData.subclassName;
							var methodName=detailData.methodName;
							var typeName=detailData.typeName;
							var useName=detailData.useName;
							var checkDeptName=detailData.checkDeptName;
							var checkDatetime=detailData.checkDatetime.substring(0,10);
							$("#dprocode").val(productCode);
							$("#dproname").val(productName);              
							$("#dproclass").val(className);             
							$("#dprosubclass").val(subclassName);
							$("#dpromethod").val(methodName);
							$("#dpropackage").val(typeName);              
							$("#dprousetend").val(useName);             
							$("#dprodept").val(checkDeptName);
							$("#dprotime").val(checkDatetime);
							if(material!=undefined){
								detailTable.rows.add(material).draw();
							}
							
							
						}, 'json');                              
			}
			
			
			
});