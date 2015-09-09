$().ready(function(){
	var entcheckRow=null;
	var materialRow = null;
	var accessoryRow=null;   
	var additiveRow=null;
	var productRow=null;
	var entCode='';
	var entName ='';
	var entProductName ='';
	var status ='待审核';//待审核
	var inspOrgCode ='';//操作员所在机构从cookie中获取
	var inspDeptCode='';//操作员所在部门代码
	var roleCode='';//操作员角色代码 从cookie中获取
	var inspOperateorCode='';//操作人代码
	var rejectflg=null;
	var entcheckflg=null;
	initentstatusSelect();
//企业端的企业产品表获取
	
	var columnClass = [
		                  { "data": "entProductInputID" },
		                  { "data": "entCode" },
		      	          { "data": "entName" },
		      	          { "data": "entProductName" },
		      	          { "data": "entProductCode" },
		                  { "data": "status" },
		      	          { "data": "appDatetime",
		      	        	"render": function ( data, type, full, meta ) {
	                    	      return data.substring(0,10);
	                    	    }},
		                  { "data": "remarks" },
		      	          { "data": "productCode" },
		      	         ];
	var entchecktable = $('#entproductCheckTb').DataTable( {
			  "deferRender": true,
			  "processing": true,
			  "serverSide": true,
			  "ajax":{
				  url : "EntProductCheckAction_getEntProductInput",
				  type : "GET",
				  data : function(d){
					//  d.data = getClassReqParam();
					  d.data=inintparm(entCode, entName, entProductName, status);
					  d.inspOrgCode=inspOrgCode;
				 }
			  },
		      "columns": columnClass,
		      "columnDefs": [
		                     {
		                         "targets": [ 0 ],
		                         "visible": false,
		                     }
		                 ]
		  } );
	
	$('#entproductCheckTb').on( 'draw.dt', function () {
		curItemCtrlRow = null;
		if(entchecktable.rows().data().length > 0){
		var dtRow = null;
		var node = null;
		if(entcheckRow != null){
		dtRow = entchecktable.row($(entcheckRow).context._DT_RowIndex);
		node = dtRow.node();
		if(node != null){
		$(node).click();
		return;
		}
		}
		dtRow = entchecktable.row(0);
		node = dtRow.node();
		$(node).click();
		}else{
			entcheckRow = null;
		}
		});
	
	//动态更新获取参数			  
	 function inintparm(entCode,entName,entProductName,status,inspOrgCode){
						data={
								entCode:entCode,
								entName :entName,
								entProductName :entProductName,
								status :status,
								inspOrgCode :inspOrgCode
								
								
						};
						var jsonstr=JSON.stringify(data);
						return jsonstr;
						
					}	
/////--------------------------------------------------------------------主表完毕--------------	

	    var materialtable = $('#newmaterisourceTb').DataTable({"paging" : false,
			"info":false});
		var accessorytable = $('#newacessorysTb').DataTable({"paging" : false,
			"info":false});
		var additivetable = $('#newaddtivetionTb').DataTable({"paging" : false,
			"info":false});
		var producttable = $('#continuecheckproductTb').DataTable({"paging" : false,
			"info":false});
		var comparleftmatable = $('#comparedetaildataTb').DataTable({"paging" : false,
			"info":false});
		var comparrightable = $('#combecheckedTb').DataTable({"paging" : false,
			"info":false});
///-------------------------------------------------------------------------三个表	

	function initentstatusSelect(){
		  $.get("EntProductCheckAction_getEntProductStatus?&ts="
					+ new Date().getTime(), 
		    function(rdata) {
				var source = new Array();
				$.each(rdata.data, function(index, value){
					source[index] = value.status;
				});	
				cus_autocomplete(source, "entproductCheckstaus", "sentproductstaus-select", null, null);		
			}, 'json');
	  }
//------------------------------------------------------------------状态初始化完毕	

    $('#entproductCheckTb tbody').on("click", "tr", clickentcheckRow );	
    $('#continuecheckproductTb tbody').on("click", "tr", clickproductRow );	
//选中企业基本数据表中的一行
	function clickentcheckRow(){  
		  var row = this;
		  if ( $(row).hasClass('active') ) {  
			    entcheckRow = null;
	            $(row).removeClass('active');
	        }
	        else {
	        	entcheckRow = row;
	        	entchecktable.$('tr.active').removeClass('active');
	            $(row).addClass('active');
	            
	          
	        }  
	  }	
	function clickproductRow(){  
		  var row = this;
		  if ( $(row).hasClass('active') ) {  
			   productRow = null;
	            $(row).removeClass('active');
	        }
	        else {
	        	productRow = row;
	        	producttable.$('tr.active').removeClass('active');
	            $(row).addClass('active');
	            
	          
	        }  
	  }	
//--------------------------------------------------------四个表的中某一行的点击功能
//主界面查询按钮
	  $("#queryentproductCheck").click(function(){
		 $(".overlay").show();
		 $("#queryentproductCheckDialog").show();   
	  });
//查询对话框中取消按钮
		 $("#closequeryentproductCheckDialogBtn").click(function(){
			 $(".overlay").hide();
			 $("#queryentproductCheckDialog").hide(); 
			// clearAlertDiv("queryentproductCheckDialog");暂时不要
			    // entName ='';
				// entProductName ='';
				// status ='待审核';
		  });	
//查询功能
	$('#queryentproductCheckBtn').click(function(){
		     
		         entName=$("#entproductCheckentName").val().trim();
				 entProductName=$("#entproductCheckentproductName").val().trim();
				 status=$("#entproductCheckstaus").val().trim();
				 entchecktable.draw();
				 $("#closequeryentproductCheckDialogBtn").click();
				 
	});
//---------------------------------------------------------------------------查询完毕-------------------------
//详细数据按钮
	 $("#getentproductCheck").click(function(){
		 if(entcheckRow == null){
			  alert("请先选择一条记录！");
			  return;
		  }
		 $(".overlay").show();
		 $("#entproductInputDetailDataDialog").show();  
		 var entCod=entchecktable.row(entcheckRow).data().entCode;
		 var entProductCode=entchecktable.row(entcheckRow).data().entProductCode;
		 getEntProductDetailByID(entCod, entProductCode);
	  });
	//企业详细数据函数
	function getEntProductDetailByID(entCode,entProductCode){
		
		data={
				entCode:entCode,
				entProductCode :entProductCode
		};
		 var jsonstr =JSON.stringify(data);
		$.post("EntProductCheckAction_getEntProductDetailByID?&ts="   
				+ new Date().getTime(), {
					 data : jsonstr
				}, function(rdata) {
					//先放基本数据
					
					if(rdata.data[0].one.length>0){
						
					
					$("#entproInputid").val(rdata.data[0].one[0].entProductInputID);
					$("#entproInputentcode").val(rdata.data[0].one[0].entCode);
					$("#entproInputentname").val(rdata.data[0].one[0].entName);
					$("#entproInputentproname").val(rdata.data[0].one[0].entProductName);
					$("#entproInputentprocode").val(rdata.data[0].one[0].entProductCode);
					$("#entproInputentremarks").val(rdata.data[0].one[0].remarks);
					$("#entproInputproclass").val(rdata.data[0].one[0].productClassCode+" "+rdata.data[0].one[0].className);
					$("#entproInputprosubclass").val(rdata.data[0].one[0].productSubclassCode+" "+rdata.data[0].one[0].subclassName);
					$("#entproInputprocemethod").val(rdata.data[0].one[0].processingMethodCode+" "+rdata.data[0].one[0].methodName);
					$("#entproInputpacktype").val(rdata.data[0].one[0].packageTypeCode+" "+rdata.data[0].one[0].typeName);
					$("#entproInputintenduse").val(rdata.data[0].one[0].intentedUseCode+" "+rdata.data[0].one[0].useName);
					$("#entproInputprodegree").val(rdata.data[0].one[0].processingDegreeCode+" "+rdata.data[0].one[0].degreeName);
					$("#entproInputstoragecondi").val(rdata.data[0].one[0].storageConditionCode+" "+rdata.data[0].one[0].conditionName);
					$("#entproInputentstatus").val(rdata.data[0].one[0].status);
					$("#entproInputappdatetime").val(rdata.data[0].one[0].appDatetime.substring(0,10));
					$("#entproInputproductcode").val(rdata.data[0].one[0].productCode);
					$("#entproInputproductname").val(rdata.data[0].one[0].productName);
					$("#entproInputrefusereason").val(rdata.data[0].one[0].refuseReason);
					$("#entproInputcheckorg").val(rdata.data[0].one[0].checkDeptName);
					$("#entproInputcheckdate").val(rdata.data[0].one[0].checkDatetime);
					}
					
					if(rdata.data[0].two.length>0){
						
					
					//原料数据
					for ( var i = 0; i < rdata.data[0].two.length; i++) {
						var ifmain=rdata.data[0].two[i].ifMainMaterial;
						if(ifmain=='0'){
							ifmain='否';
						}else{
							ifmain='是';
						}
						materialtable.row.add([
						    		           rdata.data[0].two[i].className,
						    		           rdata.data[0].two[i].subclassName,rdata.data[0].two[i].materialName,
						    		           rdata.data[0].two[i].sourceName,ifmain
						    		           ]);
						
					  }
					materialtable.draw();
					}
					if(rdata.data[0].three.length>0){
						
					
					//辅料数据
					for ( var i = 0; i < rdata.data[0].three.length; i++) {
						accessorytable.row.add([
						                        rdata.data[0].three[i].accessoryName,rdata.data[0].three[i].accessoryUse,rdata.data[0].three[i].usedProcess
						                        ]);
					}
					accessorytable.draw();
					}
					if(rdata.data[0].four.length>0){
						
					
					//添加剂数据
					for ( var i = 0; i <rdata.data[0].four.length; i++) {
						additivetable.row.add([
						                       rdata.data[0].four[i].additiveName,rdata.data[0].four[i].purpose,
						                       rdata.data[0].four[i].useProcess,rdata.data[0].four[i].useQuantity
						                        ]);
					}
					additivetable.draw();  
					}
					}, 'json');
	}
//------------------------------------------------------------------------详细数据功能完毕--------	
//驳回按钮
	 $("#regtentproductInputDetailDataBtn").click(function(){
		 
		 rejectflg=1;
		 entcheckflg=0;
		 getoperatelimit(roleCode, "企业产品审核");
		
	  });	
//驳回对话框中确认按钮
	 $("#registerentproductCheckBtn").click(function(){
		 
		
		 rejectEntProduct();
		  
	  });
	 
//驳回对话框中取消按钮
	 $("#closeregisterentproductCheckDialogBtn").click(function(){
		 rejectflg=0;
		 $("#registerentproductCheckDialog").hide(); 
		 $("#entproductInputDetailDataDialog").show();
		 $("#rejectreasonbad").val('');
		
		 
	  });	
	
	
	//驳回产品函数
	function rejectEntProduct(checkOrgCode,checkDeptCode,checkOperatorCode){
		var  entCod=$("#entproInputentcode").val().trim();
		var  entProductCode=$("#entproInputentprocode").val().trim();
		var  refuseReason=$("#rejectreasonbad").val().trim();
		if(refuseReason==''){alert("请输入驳回理由");return;}
		data={ 
				  entCode:entCod,
				  entProductCode:entProductCode,
				  refuseReason :refuseReason,
				  checkOrgCode : inspOrgCode,
				  checkDeptCode : inspDeptCode,
				  checkOperatorCode : inspOperateorCode
				 
			};   
		  var jsonstr = JSON.stringify(data);
		  $.post("EntProductCheckAction_rejectEntProduct?&ts="   
					+ new Date().getTime(), {
						 data : jsonstr
					}, function(rdata) {
						$("#closeregisterentproductCheckDialogBtn").click();
						 $("#entproductInputDetailDataDialog").hide();
						 /*$(entcheckRow).addClass('active');*/
						 $(".overlay").hide();
						 entchecktable.draw();
					}, 'json');
		
	}
//-------------------------------------------------------------------------驳回功能完毕-----------
//审核按钮
	 $("#checkentproductInputDetailDataBtn").click(function(){
		 entcheckflg=1;
		 rejectflg=0;
		 getoperatelimit(roleCode, "企业产品审核");
		 
	  });
	
//审核一种确认按钮
	 $("#checkprogressingBtn").click(function(){
		 saveProduct();
		
	  });
//审核一种取消按钮
	 $("#closecheckprogressingDialogBtn").click(function(){
		 
		 $(".overlay").show();
		 $("#checkprogressingDialog").hide(); 
		 $("#entproductInputDetailDataDialog").show();
	  });
//审核二中新产品按钮
	 $("#surenewproduct").click(function(){
		 
		 $(".overlay").show();
		 $("#entproductInputDetailDataDialog").hide(); 
		 $("#checkprogressingDialog").show();
		 $("#continuecheckproductDialog").hide(); 
		 //没做完？？
	  });	
//审核二中已有产品按钮
	 $("#hadoweproduct").click(function(){
		 if(productRow==null){alert("请选中产品");return;}
		 var entCod=$("#entproInputentcode").val().trim();
		 var productcode=producttable.row(productRow).data()[1];
		 var entproductcode=$("#entproInputentprocode").val().trim();
	
		 checkMaterialForProductCode(productcode, entCod, entproductcode);
		 
		
		
	  });
//审核二中取消按钮
	 $("#closecontinuecheckproDialog").click(function(){
		 
		 $(".overlay").show();
		 $("#continuecheckproductDialog").hide(); 
		 $("#entproductInputDetailDataDialog").show();
		 producttable.row().remove().draw();
	  });
	 
 //审核二中产品数据比对按钮
	 $("#compareproducts").click(function(){
		 
		 if(productRow==null){alert("请选中产品");return;}
		 comparleftmatable.row().remove().draw();
		 comparrightable.row().remove().draw();
		 $(".overlay").show();
		 $("#continuecheckproductDialog").hide(); 
		 $("#compareproductDataDialog").show();
		 var entCod=$("#entproInputentcode").val().trim();
		 var productcode=producttable.row(productRow).data()[1];
		 var entproductcode=$("#entproInputentprocode").val().trim();
	
		 GetProductInfoCompared(entCod, entproductcode, productcode);
	  });
 //产品数据比对对话框中返回按钮
	 $("#backompareproductDataaBtn").click(function(){
		 
		 $(".overlay").show();
		 $("#compareproductDataDialog").hide(); 
		 $("#continuecheckproductDialog").show();
	  });	 
	//产品审核，获得产品集合通过产品编号
	function getProductListByProductNo15(){
		var  entCod=$("#entproInputentcode").val().trim();
		var  entProductCode=$("#entproInputentprocode").val().trim();
		data={ 
				  entCode:entCod,
				  entProductCode:entProductCode
			};   
		  var jsonstr = JSON.stringify(data);
		  $.post("EntProductCheckAction_getProductListByProductNo15?&ts="   
					+ new Date().getTime(), {
						 data : jsonstr
					}, function(rdata) {
						
						
						 var code=rdata.data[0].one+rdata.data[0].two;
						  $("#newgetproductcode").val(code);
						  $("#checkprogressingproname").val($("#entproInputentproname").val());
					
						if(parseInt(rdata.data[0].three, 10)==0){//对话框一
						
							  $(".overlay").show();
							  $("#entproductInputDetailDataDialog").hide(); 
							  $("#checkprogressingDialog").show();
							  }else{//对话框二
							 producttable.row().remove().draw();
							 productRow=null;
							 $(".overlay").show();
							 $("#entproductInputDetailDataDialog").hide(); 
							 $("#continuecheckproductDialog").show();
							 
							 for ( var i = 0; i < rdata.data[0].four.length; i++) {
								 producttable.row.add([
									           rdata.data[0].four[i].productID,rdata.data[0].four[i].productCode,rdata.data[0].four[i].productName
									          ]);
								}
							 producttable.draw();
							 
						}  
						//$("#closeregisterentproductCheckDialogBtn").click();
						// $("#entproductInputDetailDataDialog").hide();
						//数据源的问题？？？？四个集合？处理数据？？？？？？？？？？？？？？？？？？？？？？？
					}, 'json');
		
	}
	
	//审核一确认函数
	function saveProduct(){
		var  entCod=$("#entproInputentcode").val().trim();
		var  entProductCode=$("#entproInputentprocode").val().trim();
		var  productCode=$("#newgetproductcode").val().trim();
		var  productName=$("#checkprogressingproname").val().trim();
		data={ 
				  entCode:entCod,
				  entProductCode:entProductCode,
				  productCode : productCode,
				  productName : productName,
				  checkOrgCode : inspOrgCode,
				  checkDeptCode : inspDeptCode,
				  checkOperatorCode : inspOperateorCode
				  
			};   
		  var jsonstr = JSON.stringify(data);
		  $.post("EntProductCheckAction_saveProduct?&ts="   
					+ new Date().getTime(), {
						 data : jsonstr
					}, function(rdata) {
						//$("#closeregisterentproductCheckDialogBtn").click();
						// $("#entproductInputDetailDataDialog").hide();
						
					    if(rdata.data=='true'){
					    	alert("审核成功");
					    	 $("#entproductInputDetailDataDialog").hide(); 
					    	 $(".overlay").hide();
							 $("#checkprogressingDialog").hide(); 
							 entcheckflg=0;
							 entchecktable.draw();
					    	
					    	/*getEntProductInput("entproInputentname", "entproInputentproname", "entproInputentstatus", "org");*/
					    }else{
					    	alert(rdata.data);
					    	$("#closecheckprogressingDialogBtn").click();
					    	
					    }
					}, 'json');
		
	}
	//检测是否为已有产品函数
	function checkMaterialForProductCode(productCode,entCode,entProductCode){
		data={    
				  productCode : productCode,
				  entCode:entCode,
				  entProductCode:entProductCode
				  
		};   
		  var jsonstr = JSON.stringify(data);
		  $.post("EntProductCheckAction_checkMaterialForProduct?&ts="   
					+ new Date().getTime(), {
						 data : jsonstr
					}, function(rdata) {
						if(rdata.data=='true'){
					    	 var entCod=$("#entproInputentcode").val().trim();
							 var productcode=producttable.row(productRow).data()[1];
							 var entproductcode=$("#entproInputentprocode").val().trim();
					
					    	updateEntProduct(entCod, entproductcode, productcode);
					    	
					    	
					    	
					    }else{
					    	alert(rdata.data);
					    }
					}, 'json');
		
	}
	//更新企业产品
	function updateEntProduct(entCode,entProductCode,productCode){
		data={ 
				  entCode:entCode,
				  entProductCode:entProductCode,
				  productCode : productCode,
				  checkOrgCode : inspOrgCode,
				  checkDeptCode : inspDeptCode,
				  checkOperatorCode : inspOperateorCode
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("EntProductCheckAction_updateEntProduct?&ts="   
				+ new Date().getTime(), {
					 data : jsonstr
				}, function(rdata) {
					
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
	//产品比对函数
	function GetProductInfoCompared(entCode,entProductCode,productCode){
		data={ 
				  entCode:entCode,
				  entProductCode:entProductCode,
				  productCode : productCode
				 
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("EntProductCheckAction_getProductInfoCompared?&ts="   
				+ new Date().getTime(), {
					 data : jsonstr
				}, function(rdata) {
					//已有产品的产品数据
					$("#comparedialogprocode").val(rdata.data[0].one[0].productCode);
					$("#comparedialogproname").val(rdata.data[0].one[0].productName);
					$("#comparedialogprobigclass").val(rdata.data[0].one[0].className);
					$("#comparedialogprosmalclass").val(rdata.data[0].one[0].subclassName);
					$("#comparedialogprogway").val(rdata.data[0].one[0].methodName);
					$("#comparedialogpropackway").val(rdata.data[0].one[0].typeName);
					$("#comparedialogintenduse").val(rdata.data[0].one[0].useName);
					$("#comparedialogcheckdates").val(rdata.data[0].one[0].checkDatetime.substring(0,10));
					$("#comparedialogcheckdeptment").val(rdata.data[0].one[0].checkDeptName);
					//被审产品的产品数据
					$("#copdiaentselfcode").val(rdata.data[0].three[0].entProductCode);
					$("#copdiaproname").val(rdata.data[0].three[0].entProductName);
					$("#copdiaproclas").val(rdata.data[0].three[0].className);
					$("#copdiaprosubcls").val(rdata.data[0].three[0].subclassName);
					$("#copdiaprgway").val(rdata.data[0].three[0].methodName);
					$("#copdiapackway").val(rdata.data[0].three[0].typeName);
					$("#copdiaintenuse").val(rdata.data[0].three[0].useName);
					$("#copdiaforchecktime").val(rdata.data[0].three[0].checkDatetime.substring(0,10));
					
					//已有产品原料数据
					for ( var i = 0; i < rdata.data[0].two.length; i++) {
						var ifmain=rdata.data[0].two[i].ifMainMaterial;
						if(ifmain=='0'){
							ifmain='否';
						}else{
							ifmain='是';
						}
						comparleftmatable.row.add([
						    		           rdata.data[0].two[i].className,
						    		           rdata.data[0].two[i].subclassName,rdata.data[0].two[i].materialName,
						    		           rdata.data[0].two[i].sourceName,ifmain
						    		           ]);
						comparleftmatable.draw();
						   
					  }
				
					//被审核原料数据
					for ( var i = 0; i < rdata.data[0].four.length; i++) {
						var ifmain=rdata.data[0].four[i].ifMainMaterial;
						if(ifmain=='0'){
							ifmain='否';
						}else{
							ifmain='是';
						}
						comparrightable.row.add([
						    		           rdata.data[0].four[i].className,
						    		           rdata.data[0].four[i].subclassName,rdata.data[0].four[i].materialName,
						    		           rdata.data[0].four[i].sourceName,ifmain
						    		           ]);
						comparrightable.draw();
					  }
		
				
				}, 'json');
	}
	
//操作的权限函数
	 function getoperatelimit(rolecode,menuname){
			
			data={
					roleCode :rolecode,
					menuName : menuname
			};
			
			var jsonstr=JSON.stringify(data);
			 $.post("EntAction_getOperateLimit?&ts="
						+ new Date().getTime(), {
							data:jsonstr
				}, function(rdata) {
					if(rdata.data == "true"){
					
					 if(rejectflg==1){
						 var statu=$("#entproInputentstatus").val().trim();
						
						 /*if(statu != '待审核'){alert("企业产品不是待审核状态，不能驳回");
						 }else */ /*暂时不要*/
							 
							 if(confirm("确定要驳回吗?")){
							    $("#entproductInputDetailDataDialog").hide(); 
				                $("#registerentproductCheckDialog").show(); 
				                $(".overlay").show();
				                
						 }
						
					 }if(entcheckflg==1){
					 		var statu=$("#entproInputentstatus").val().trim();
					 		
					 		if(statu != '待审核'){alert("企业产品不是待审核状态，不能审核");
					 		}else{
					 			getProductListByProductNo15();
					 		}
						  
					 }/*if(seFlg==1){
						 $(".overlay").show();
						 $("#addentbasicDataDialog").show(); 
						 $("#logininformation").hide();
						 
					 }*/
						
					}else{
						alert(rdata.data);
					}
				}, 'json');
			
		}	
	 
	 
	
	
	 
	
	 
	
	 
	
	
	//企业详细数据对话框中返回 按钮
	 $("#backentproductInputDetailDataBtn").click(function(){
		
		 
		 materialtable.row().remove().draw();
		 accessorytable.row().remove().draw();
		 additivetable.row().remove().draw();
		
		 $(".overlay").hide();
		 $("#entproductInputDetailDataDialog").hide();  
	  });
	
});