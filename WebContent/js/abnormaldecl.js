$(document).ready(function(){
	
	var goodsnum=0;
	var declNo='';
	var inspOrgCode='';//系统登录时检验机构代码
	var inspDeptCode ='';
	var inspOperatorCode='' ;
	var abnormalRow=null;
	var editdeclRow=null;
	var entproductRow=null;
	var setFlg=null;
	var goFlg=null;            
	$("#theoneisnotuse").hide();
	var columnClass = [
		                  { "data": "declNo"},
		                  { "data": "declDate" ,
		                	  "render": function (data, type, full, meta){
		                		  return data=data.substring(0,10);
		                	  }},
		                  { "data": "entCode" },
		      	          { "data": "entName" },
		      	          { "data": "countryCode" },
		      	          { "data": "countryName" },
		                  { "data": "productData" },
		                  { "data": "productDataFromEnt" },
		      	          { "data": "remarks"}
		      	         ];
	var abnormaltable = $('#annormaldataTb').DataTable( {
			  "deferRender": true,
			  "processing": true,
			  "serverSide": true,
			  "ajax":{
				  url : "UnUsualDeclAction_GetAbnormalDecl",
				  type : "GET",
				  data : function(d){
					//  d.data = getClassReqParam();
					  
					  d.data=inintparm(declNo,inspOrgCode,inspDeptCode);
					  
				 }
			  },
		      "columns": columnClass,   
		      "columnDefs": [
								{
								    "targets": [ 4 ],
								    "visible": false,
								}
		                 ]
		  } );
	
	//
	var editClass = [
		                  { "data": "declProductDetailID"   },
		                  { "data": "productCode" },
		                  { "data": "productName" },
		      	          { "data": "goodnn" },
		      	          { "data": "baseName" },
		      	          { "data": "samplingRatio" },    
		                  { "data": "ifSampling" ,
		                	  "render": function ( data, type, full, meta ) {
		                		 
		                		  if(data=='1'){      
		                			  return '是';
		                		  }else if(data=='0'){
		                			  return '否';
		                		  }else{
		                			  return '';
		                		  }
	                    	      // return data === '1'  ? '是' : '否';
	                    	    }},
		                  { "data": "weight" },
		      	          { "data": "values_USD"},
		                  { "data": "entProductCode"}
		      	         ];
	var editdecltable = $('#outproductdataTb').DataTable( {
			  "deferRender": true,
			  "processing": true,
			  "serverSide": true,
			  "paging" : false,
				"info":false,
			  "ajax":{
				  url : "ProcessReceiverAction_GetDeclProduct",
				  type : "GET",
				  data : function(d){
					//  d.data = getClassReqParam();
					  if(abnormalRow==null){
						  d.data=JSON.stringify({declNo :'0' });
						  
					  }else{
						   var decln = abnormaltable.row(abnormalRow).data().declNo;
						  d.data=JSON.stringify({declNo :decln });
					  }
					 
					  
				 }
			  },
		      "columns": editClass,     
		      "columnDefs": [
									{
									    "targets": [ 9 ],
									    "visible": false,
									}
		                 ]
		  } );
	
	/*var editdecltable=$('#outproductdataTb').DataTable({
		"paging" : false,
		"info":false
	});*/
	var entproducttable=$('#entproductqueryTb').DataTable({
		"paging" : false,
		"info":false
	});
	
	//初始化样品数据第一行默认选
	$('#annormaldataTb').on( 'draw.dt', function () {
		  if(abnormaltable.rows().data().length > 0){
			  var dtRow = null;
			  var node = null;
			  if(abnormalRow != null){
			  dtRow = abnormaltable.row($(abnormalRow).context._DT_RowIndex);
			  
			  node = dtRow.node();
			
			  if(node != null){
			  $(node).click();
			  return;
			  }
			  }
			  dtRow = abnormaltable.row(0);
			  node = dtRow.node();
			  $(node).click();
         }else{
        	 abnormalRow=null;
        	
         }
		  //subsubclassRow=null;
		       
	  } );	
	
	//初始化报检数据第一行默认选
	$('#outproductdataTb').on( 'draw.dt', function () {
		  if(editdecltable.rows().data().length > 0){
			  var dtRow = null;
			  var node = null;
			  
			  if(editdeclRow != null){
			  dtRow = editdecltable.row($(editdeclRow).context._DT_RowIndex);
			 
			  node = dtRow.node();
		
			  if(node != null){
				
			  $(node).click();
			  return;
			  }
			  }
			  dtRow = editdecltable.row(0);
			  node = dtRow.node();
			  $(node).click();    
         }
		  else{
        	 editdeclRow=null;
        	
         }
		  //subsubclassRow=null;
		       
	  } );
	
	
	$('#entproductqueryTb tbody').on("click", "tr", clickentproductqueryRow );
	$('#outproductdataTb tbody').on("click", "tr", clickentdeclRow );
	$('#annormaldataTb tbody').on("click", "tr", clickabnormalRow );
	//选中异常表中的一行
	function clickabnormalRow(){  
		  var row = this;
		  if ( $(row).hasClass('active') ) {  
			   abnormalRow = null;
	            $(row).removeClass('active');
	        }
	        else {
	        	abnormalRow = row;
	        	abnormaltable.$('tr.active').removeClass('active');
	            $(row).addClass('active');
	        }
   }
	//选中企业产品查询表中的一行
	function clickentproductqueryRow(){  
		  var row = this;
		  if ( $(row).hasClass('active') ) {  
			    entproductRow = null;
	            $(row).removeClass('active');
	        }
	        else {
	        	entproductRow = row;
	        	entproducttable.$('tr.active').removeClass('active');
	            $(row).addClass('active');
	        }
   }
	function clickentdeclRow(){  
		  var row = this;
		  if ( $(row).hasClass('active') ) {  
			  editdeclRow = null;
	            $(row).removeClass('active');
	        }
	        else {
	        	editdeclRow = row;
	        	editdecltable.$('tr.active').removeClass('active');
	            $(row).addClass('active');
	        }
 }
	
	 function inintparm(declNo,inspOrgCode,inspDeptCode){
		 data={ 
					declNo:declNo,
					inspOrgCode:inspOrgCode,
					inspDeptCode : inspDeptCode
					
			}; 
			 var jsonstr = JSON.stringify(data);
			return jsonstr;
			
		}
	
	
	
	
	
	
	
	
//主界面查询按钮
	  $("#querydecldata").click(function(){
		 $(".overlay").show();
		 $("#querydeclsinglepriveDialog").show();      
	  });
//查询对话框中取消按钮
		 $("#closedeclsinglepriveDialogBtn").click(function(){
			 $(".overlay").hide();
			 $("#querydeclsinglepriveDialog").hide(); 
			 clearAlertDiv("querydeclsinglepriveDialog");
		 });	
	
//查询功能
			$('#querydeclsinglepriveBtn').click(function(){
				           declNo=$("#declnodeclsingleprive").val().trim();
				           if(declNo==''||declNo==null){alert("请输入报检号");return;}
				           abnormaltable.draw();  
				           $("#closedeclsinglepriveDialogBtn").click();
						 
			});	
			
//-----------------------------------------------------------------------查询完毕-------			
	
//主界面退单按钮
			  $("#editoutproductdata").click(function(){
				 
				  if(abnormalRow == null){
					  alert("请先选择一条要退单的单证！");
					  return;
				  }
				  var remarks=abnormaltable.row(abnormalRow).data().remarks;
				  var decln = abnormaltable.row(abnormalRow).data().declNo;
				  if(remarks=='厂检单填写的出口产品不全'){
					  goFlg=1;
					 RecalculateDeclProductSampling(decln);
				 }else{
					 alert('其他异常，需要在修改报检单证上操作');
				 }
				 
				 /* if(confirm("确定要进行退档操作?")){
					  var decln = abnormaltable.row(abnormalRow).data().declNo;
					  ReturnDecl(decln, inspOrgCode, inspDeptCode, inspOperatorCode);
					  
				  }*/
			  });			
//主界面重读数据功能
			  $("#sueragreadBtn").click(function(){
				 
				   var decln=$("#agreaddecl").val().trim();
				   if(decln==''){alert("请输入报检号");return;}
				   CheckAbnormalDecl(decln);
				 });	
//主界面重读数据按钮
			  $("#deleteoutproductdata").click(function(){
				 
				    $(".overlay").show();
					$("#againreadDialog").show();  
				 });
//重读数据取消按钮
			  $("#closeagreadBtn").click(function(){
				 
				    $(".overlay").hide();
					 
					 $("#againreadDialog").hide();  
				 });			  
				
//主界面修改报检数据按钮
			  $("#baojiandanzforeditBtn").click(function(){
				  
				  getoperatelimit('异常单证',1);
				 
				 
			  });	
			  
//-----------------------------------------------------------------主界面后三个按钮-------------			  
			  
//保存报检数据按钮
			  $("#savethedeclsdatasBtn").click(function(){
				 
				  var entcode=getSelectValue("productcodeoutcheckproductabc");
				  var countrycode=getSelectValue("baseconaosfdrodef");
				  var decldeate=$("#jianyandateckpro input").val();
				  var delcn=$("#vfsdfdbaodeclpro").val().trim();
				  if( entcode==''|| entcode==null){alert("请填写出口企业");return;}
				 if( countrycode==''|| countrycode==null){alert("请填写出口国家");return;}
				 if( decldeate==''|| decldeate==null){alert("请填写报检日期");return;}
				 if(delcn ==''|| delcn==null){alert("请填写报检号");return;}
				SaveDeclInfoInput(delcn, decldeate, entcode, countrycode, inspOrgCode, inspDeptCode, inspOperatorCode);
			  });			  
//新增产品数据按钮
			  $("#foraddnewproductBtn").click(function(){
				  
				 $(".overlay").show();
				 $("#foreditingdecldataDialog").hide();
				 $("#addoutcheckproductDialog").show();  
				 $("#chongzhongtheprodiv").hide();
				 setFlg=1;
			  });
//修改产品数据按钮
			  $("#editthechanpforBtn").click(function(){
				  if(editdeclRow==null){alert("选中一条");return;}
				 $(".overlay").show();
				 $("#foreditingdecldataDialog").hide();    
				 $("#chongzhongtheprodiv").hide();
				 $("#addoutcheckproductDialog").show(); 
				 $("#productcodeoutcheckproduct").val(editdecltable.row(editdeclRow).data().productCode);
				 $("#baseconaoutcheckpro").val(editdecltable.row(editdeclRow).data().baseCode+" "+editdecltable.row(editdeclRow).data().baseName);
				 $("#bcheckgoodnanum").val(editdecltable.row(editdeclRow).data().goodnn);
				 $("#entprozijidecodema").val(editdecltable.row(editdeclRow).data().entProductCode);
				 setFlg=2;   
			  });			  
//新增产品对话框中保存功能	
				 $('#saveoutcheckproductBtn').click(function(){
					  var entzicode=$("#entprozijidecodema").val().trim();
					   var procode=$("#productcodeoutcheckproduct").val().trim();
					  var basecode= getSelectValue("baseconaoutcheckpro");
					  if(procode==''){alert("填写产品编号");return;}
					  if(entzicode==''){alert("企业自编码不能为空");return;}
					  var goodnn=$("#bcheckgoodnanum").val().trim();
					 
					  var goodsNo= getSelectValue("bcheckgoodnanum");
					 
					  var k=[];
					  k= goodnn.split(' ');     
					 
					  var goodsCName=k[1];
					 var declN=abnormaltable.row(abnormalRow).data().declNo;
					  if(setFlg==1){
						  saveDeclProductInput(0, declN, procode,entzicode, basecode, goodsNo, goodsCName, 0);
						 
					  }else if(setFlg==2){
						  var declprodetailid=editdecltable.row(editdeclRow).data().declProductDetailID;
						
						  saveDeclProductInput(declprodetailid, declN, procode,entzicode, basecode, goodsNo, goodsCName, 0);
						  
					  }
						 
				 	});	
				 
//新增产品对话框中取消按钮
				 $("#closeqoutcheckproductDialogBtn").click(function(){
					 $(".overlay").show();
					 $("#addoutcheckproductDialog").hide(); 
					 $("#foreditingdecldataDialog").show();
					 clearAlertDiv("addoutcheckproductDialog");
					 $("#samplingflgoutcheckpro").prop("checked",false);
				  });				 
				 
				 
				 function    saveDeclProductInput(declProductDetailID,declNo,productCode,entProductCode,baseCode,goodsNo,goodsCName,samplingFlg){
						data={ 
								declProductDetailID:declProductDetailID,
								declNo:declNo,
								productCode : productCode,
								entProductCode :entProductCode,
								baseCode : getSelectValue("baseconaoutcheckpro"),
								goodsNo : getSelectValue("bcheckgoodnanum"),
								goodsCName :goodsCName,
								samplingFlg :samplingFlg
						}; 
						if(!checkJsonParam(data)){
							return ;
							}
						 var jsonstr = JSON.stringify(data);
						$.post("ProcessReceiverAction_SaveDeclProductInput?&ts="   
								+ new Date().getTime(), {
									 data : jsonstr
								}, function(rdata) {
									if(rdata.data=="true"){
										alert("保存成功");
										 /*editdecltable.row().remove().draw();
										getDeclProduct(declNo);*/
										if(setFlg==1){
											editdeclRow=null;
											editdecltable.draw();
										}else if(setFlg==2){
											editdecltable.draw(false);
										}
										 $("#closeqoutcheckproductDialogBtn").click();
									}else{
										alert(rdata.data);
										$("#closeqoutcheckproductDialogBtn").click();
									}
									
								}, 'json');
					}  
	
//新增产品对话框中产品编号按钮--企业产品查询	
				$("#sproductoutcheckproduct").click(function(){
						 $(".overlay").show();
						 $("#addoutcheckproductDialog").hide(); 
						 $("#entproductquerydialog").show(); 
						 $("#entcodeentproductquery").val(abnormaltable.row(abnormalRow).data().entCode);
						 $("#contryentproductquery").val(abnormaltable.row(abnormalRow).data().countryCode);
						  
						 
						 
				 });	
//企业产品查询对话框中查询按钮
			     $("#queryentproductqueryBtn").click(function(){
			    	 entproducttable.row().remove().draw();
				 var name=$("#productentproductquery").val();
				
				 /*if(name==''){alert("没有输入产品名称");return;}*/
				 var entcode=$("#entcodeentproductquery").val();
				 var countrycode=$("#contryentproductquery").val();
				 
				 getEntProductByQuery(entcode, '', name, '已审核', '');
				    // entName ='';
					// entProductName ='';
					// status ='待审核';
			  });	
			 
		function    getEntProductByQuery(entCode,entName,entProductName,status,inspOrgCode){
			data={ 
					entCode:entCode,
					entName:entName,
					entProductName:entProductName,
					status :status,
			}; 
			 var jsonstr = JSON.stringify(data);
			$.post("EntProductCheckAction_getEntProductInput?&ts="   
					+ new Date().getTime(), {
						 data : jsonstr,inspOrgCode:inspOrgCode
					}, function(rdata) {
						
						if(rdata.data.length >0){
							 for ( var i = 0; i < rdata.data.length; i++) {   
								 entproducttable.row.add([
									           rdata.data[i].productCode,rdata.data[i].entProductName,rdata.data[i].entProductCode
									          ]);
								}
							 entproducttable.draw();
							
						}else{
							alert("无符合条件数据");
							
						}
						
						
						
						//table.add(());
						
					}, 'json');
		}
//企业产品查询对话框中选中返回按钮
			 $("#checkedqentproductqueryDialogBtn").click(function(){
				 if(entproductRow==null){alert("选中一行企业产品数据");return;}
				 if(entproducttable.rows().data().length >0){
					 var procode= entproducttable.row(entproductRow).data()[0];
					 var entziprocode= entproducttable.row(entproductRow).data()[2];
						 $("#productcodeoutcheckproduct").val(procode);
						 $("#entprozijidecodema").val(entziprocode);
						 $(".overlay").show();
						 $("#addoutcheckproductDialog").show(); 
						 $("#entproductquerydialog").hide(); 
						 entproducttable.row().remove().draw();
						 clearAlertDiv("entproductquerydialog");
				 }else{
					 alert("数据为空，无法返回");
				 }
				
			  });
			 
	 //企业产品查询对话框取消按钮
			 $("#cancelthecpquerbtn").click(function(){
				 $(".overlay").show();
				 $("#addoutcheckproductDialog").show(); 
				 $("#entproductquerydialog").hide(); 
				 entproducttable.row().remove().draw();
				 clearAlertDiv("entproductquerydialog");
			  });
		
			 $("#sbaseconaoutcheckpro-search").click(function(){
				  searchProcessMethod("baseconaoutcheckpro");
			  });			 
			 
			 function searchProcessMethod(input){
				  var basename = $("#"+input).val().trim();
					 $.get("ProcessReceiverAction_GetBaseList?&ts="
								+ new Date().getTime(), 
					    {data : JSON.stringify({baseCode:'',baseName:basename})},
					    function(rdata) {
							var source = new Array();
							$.each(rdata.data, function(index, value){
								source[index] = value.baseCode+" "+value.baseName;
							});	
							cus_autocomplete(source, input, null, null, null);
							$("#"+input).autocomplete( "search", "" );
						}, 'json');
			  }
			 //报检货物
			 $("#bcheckgoodnanum-search").click(function(){
				  searchgoodnuna("bcheckgoodnanum");    
			  });			 
			 
			 function searchgoodnuna(input){
				//  var basename = $("#"+input).val().trim();
				  var decln = abnormaltable.row(abnormalRow).data().declNo;
					 $.get("ProcessReceiverAction_GetDeclGoodsByDeclNo?&ts="
								+ new Date().getTime(), 
					    {data : JSON.stringify({declNo:decln})},
					    function(rdata) {
							var source = new Array();
							$.each(rdata.data, function(index, value){
								source[index] = value.goods_No+" "+value.goods_CName;
							});	
							cus_autocomplete(source, input, null, null, null);
							$("#"+input).autocomplete( "search", "" );
						}, 'json');
			  }
			 
//-------------------------
			//删除出口产品数据
			 $('#deteleforprodusBtn').click(function(){
					
				 if(editdeclRow==null){alert("选中一条");return;}
				 var declprodetailid=editdecltable.row(editdeclRow).data().declProductDetailID;
				
				  delDeclProduct(declprodetailid);
				 
				});
			 function    delDeclProduct(declProductDetailID){
					data={ 
							declProductDetailID:declProductDetailID,
							
					}; 
					 var jsonstr = JSON.stringify(data);
					$.post("ProcessReceiverAction_DelDeclProduct?&ts="   
							+ new Date().getTime(), {
								 data : jsonstr
							}, function(rdata) {
								if(rdata.data=="true"){
									alert("删除成功");
									 editdecltable.draw(false);
									/*var declN=abnormaltable.row(abnormalRow).data().declNo;
									getDeclProduct(declN);*/
									
								}
								
							}, 'json');
				}
//--------------------------------
			 //5重新计算抽检比
			 $("#remathchoujianBtn").click(function(){
				 
				 var declN=abnormaltable.row(abnormalRow).data().declNo;
				
				 CheckDeclProductPerfect(declN);
				// RecalculateDeclProductSampling(declN);
				 
		 });
//-------------------------
			//关闭修改报检数据界面 
			 $("#closeforeditingdecldataDialog").click(function(){
				 $(".overlay").hide();
				 $("#foreditingdecldataDialog").hide(); 
				 abnormaltable.draw(false);
				// editdecltable.row().remove().draw();
		 });
			 
			 
	//出口国家
				$("#sbasecfsdfdfckpro-search").click(function(){
					  searchCountry("baseconaosfdrodef");
				  });
	//出口企业
				$("#productcodsfssdduct-search").click(function(){
					  getent("productcodeoutcheckproductabc");
				  });
				
				
				
				
				
				
				
				
	function GetAbnormalDecl(declNo,inspOrgCode,inspDeptCode){
		data={ 
				declNo:declNo,
				inspOrgCode : inspOrgCode,
				inspDeptCode : inspDeptCode
				
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("UnUsualDeclAction_GetAbnormalDecl?&ts="   
				+ new Date().getTime(), {
					 data : jsonstr
				}, function(rdata) {
					if(rdata.data=="true"){
						alert("调回单证成功");
						 
					}else{
						alert(rdata.data);
					}
					
				}, 'json');
	}
	
		function  ReturnDecl(declNo,inspOrgCode,inspDeptCode,inspOperatorCode) {
			data={ 
					declNo:declNo,
					inspOrgCode : inspOrgCode,
					inspDeptCode : inspDeptCode,
					inspOperatorCode:inspOperatorCode
			}; 
			 var jsonstr = JSON.stringify(data);
			$.post("UnUsualDeclAction_ReturnDecl?&ts="   
					+ new Date().getTime(), {
						 data : jsonstr
					}, function(rdata) {
						if(rdata.data=="true"){
							alert("退档成功");
							getCEMSSeqNum(declNo);
							 
						}else{
							alert(rdata.data);
						}
						
					}, 'json');
		}
		 function    getCEMSSeqNum(declN){
				data={ 
						declNo:declN
						
				}; 
				 var jsonstr = JSON.stringify(data);
				$.post("ProcessReceiverAction_getCEMSSeqNum?&ts="   
						+ new Date().getTime(), {
							 data : jsonstr
						}, function(rdata) {
							
							var key=rdata.data;
							
							updateControlReturnFlgNew(key, '2', '9');
							 
							abnormaltable.draw();
							
						}, 'json');
			} 
		  function    updateControlReturnFlgNew(seqNum,status,returnFlg){
				data={ 
						seqNum:seqNum,
						status :status,
						returnFlg :returnFlg
						
				}; 
				 var jsonstr = JSON.stringify(data);
				$.post("ProcessReceiverAction_UpdateControlReturnFlgNew?&ts="   
						+ new Date().getTime(), {
							 data : jsonstr
						}, function(rdata) {
							
						}, 'json');
			} 
		
		
		  function     getDeclProduct(declNo){
				data={ 
						declNo:declNo
						
				}; 
				 var jsonstr = JSON.stringify(data);
				$.post("ProcessReceiverAction_GetDeclProduct?&ts="   
						+ new Date().getTime(), {
							 data : jsonstr
						}, function(rdata) {     
							
						
							for ( var i = 0; i < rdata.data.length; i++) {
								editdecltable.row.add([
									           rdata.data[i].declProductDetailID,rdata.data[i].productCode,rdata.data[i].productName,
									           rdata.data[i].baseCode,rdata.data[i].baseName,rdata.data[i].lowerSamplingRatio,
				  		      			           rdata.data[i].ifSampling,rdata.data[i].weight,rdata.data[i].values_USD,
									          ]);
								}
							if(setFlg==1){
								editdecltable.draw();
							}else if(setFlg==2){
								editdecltable.draw(false);
							}else{
								editdecltable.draw(false);
							}
							
							$("#closeqoutcheckproductDialogBtn").click();
							
						}, 'json');
			}
		
	function  CheckAbnormalDecl(declNo) {
				data={ 
						declNo:declNo
				}; 
				 var jsonstr = JSON.stringify(data);
				$.post("UnUsualDeclAction_CheckAbnormalDecl?&ts="   
						+ new Date().getTime(), {
							 data : jsonstr
						}, function(rdata) {
							if(rdata.data=="true"){
								
								ReReadDeclInfo(declNo);
							}else{
								alert(rdata.data);
							}
							
						}, 'json');
			}		
		function  ReReadDeclInfo(declNo) {
			data={ 
					declNo:declNo
			}; 
			 var jsonstr = JSON.stringify(data);
			$.post("UnUsualDeclAction_ReReadDeclInfo?&ts="   
					+ new Date().getTime(), {
						 data : jsonstr
					}, function(rdata) {
						
						if(rdata.data=='0'){
							alert("设置重读成功,请过段时间检查数据");
							 
						}else{
							alert("集中审单接口系统找不到对应报检数据");
						}
						
					}, 'json');
		}
		function  GetDeclInfoByDeclNo(declNo) {
			data={ 
					declNo:declNo
				}; 
			 var jsonstr = JSON.stringify(data);
			$.post("UnUsualDeclAction_GetDeclInfoByDeclNo?&ts="   
					+ new Date().getTime(), {
						 data : jsonstr
					}, function(rdata) {
						$("#jianyandateckpro input").val(abnormaltable.row(abnormalRow).data().declDate.substring(0,10));
						if(rdata.data.length>0){
							
						$("#productcodeoutcheckproductabc").val(rdata.data[0].entCode+" "+rdata.data[0].entName);
						$("#baseconaosfdrodef").val(rdata.data[0].countryCode+" "+rdata.data[0].countryName);
						}
						editdecltable.draw();
						
					}, 'json');
		}
		function  SaveDeclInfoInput(declNo,declDate,entCode,countryCode,inspOrgCode,inspDeptCode,inspOperatorCode) {
			data={ 
					declNo:declNo,
					declDate:declDate,
					entCode:entCode,
					countryCode:countryCode,
					inspOrgCode : inspOrgCode,
					inspDeptCode : inspDeptCode,
					inspOperatorCode:inspOperatorCode
					
			}; 
			 var jsonstr = JSON.stringify(data);
			$.post("UnUsualDeclAction_SaveDeclInfoInput?&ts="   
					+ new Date().getTime(), {
						 data : jsonstr
					}, function(rdata) {
						if(rdata.data=="true"){
							alert("保存报检数据成功");    
							//abnormaltable.draw(false);      
							 
						}else{
							alert(rdata.data);
						}
						
					}, 'json');
		}
		function  RecalculateDeclProductSampling(declNo) {
			data={ 
					declNo:declNo,
					
					
			}; 
			 var jsonstr = JSON.stringify(data);
			$.post("UnUsualDeclAction_RecalculateDeclProductSampling?&ts="   
					+ new Date().getTime(), {
						 data : jsonstr
					}, function(rdata) {
						if(rdata.data=="true" ){
							
							if(goFlg ==1){
								abnormaltable.draw();
								alert("产品不全放行成功");
								goFlg==null;
							
							}else{
								editdecltable.draw();
							    alert("重新计算抽检比成功");
							
							}
							
						}else{
							alert(rdata.data);
						}
						
					}, 'json');
		}   
		function  CheckDeclProductPerfect(declNo) {
			data={ 
					declNo:declNo,
					
					
			}; 
			 var jsonstr = JSON.stringify(data);
			$.post("UnUsualDeclAction_CheckDeclProductPerfect?&ts="   
					+ new Date().getTime(), {
						 data : jsonstr
					}, function(rdata) {
						
						if(rdata.data!='0'){
							
							if(confirm("厂检单填写的出口产品不全!继续重新计算抽检并进行放行吗")){
								RecalculateDeclProductSampling(declNo);
							}
						}
						
						
					}, 'json');
		}  
	
		 function getent(input){
			  var entName = $("#"+input).val().trim();
			  data={ 
					    entName:entName,
					    entCode:'',
						mngOrgCode :''
						
				}; 
				 var jsonstr = JSON.stringify(data);
				 $.get("EntAction_getEnt?&ts="
							+ new Date().getTime(), 
				    {data : jsonstr,inspOrgCode:'',roleCode:''},
				    function(rdata) {
						var source = new Array();
						$.each(rdata.data, function(index, value){
							source[index] = value.entCode+" "+value.entName;
						});	
						cus_autocomplete(source, input, null, null, null);
						$("#"+input).autocomplete( "search", "" );
					}, 'json');
		  }
		  function searchCountry(input){
			  var countryName = $("#"+input).val().trim();
				 $.get("SearchSelectAction_getCountryAbnor?&ts="
							+ new Date().getTime(), 
				    {countryName : countryName},
				    function(rdata) {
						var source = new Array();
						$.each(rdata.data, function(index, value){
							source[index] = value.countryCode+" "+value.countryName;
						});	
						cus_autocomplete(source, input, null, null, null);
						$("#"+input).autocomplete( "search", "" );
					}, 'json');
		  }
	//////////////////////////////两个按钮////////	  
		//查看报检数据
		  $("#lookfordecldata").click(function(){
			     if(abnormalRow==null){alert("选中一条报检数据");return;}
				 $(".overlay").show();
				 $("#datafordeclDialog").show(); 
				 var declno=abnormaltable.row(abnormalRow).data().declNo;
				 getDeclInfoFromCIQ(declno);
				  
		 });  
		  function    getDeclInfoFromCIQ(declNo){
				data={ 
						declNo:declNo
						
				}; 
				 var jsonstr = JSON.stringify(data);
				$.post("ProcessReceiverAction_GetDeclInfoFromCIQ?&ts="   
						+ new Date().getTime(), {
							 data : jsonstr
						}, function(rdata) {
							if(rdata.data.length>0){
								
							
							$("#declnodatafordecl").val(rdata.data[0].decl_No);	
							$("#declgetdatafordecl").val(rdata.data[0].decl_Get_No);	
							$("#declregdatafordecl").val(rdata.data[0].decl_Reg_No);	
							$("#declpersondatafordecl").val(rdata.data[0].decl_Person_Code);	
							$("#decldatedatafordecl").val(rdata.data[0].decl_Date);	
							$("#entpropertydatafordecl").val(rdata.data[0].ent_Property);	
							$("#consicodedatafordecl").val(rdata.data[0].consignor_Code);	
							$("#concnamedatafordecl").val(rdata.data[0].consignor_CName);	
							$("#consienamedatafordecl").val(rdata.data[0].consignor_EName);	
							$("#coneecodedatafordecl").val(rdata.data[0].consignee_Code);	
							$("#coneechinanamedatafordecl").val(rdata.data[0].consignee_CName);	
							$("#coneeengnamedatafordecl").val(rdata.data[0].consignee_EName);	
							$("#transwaycodedatafordecl").val(rdata.data[0].trans_Type_Code);	
							$("#transmeancodedatafordecl").val(rdata.data[0].trans_Means_Code);	
							$("#tonnagedatafordecl").val(rdata.data[0].tonnage);	
							$("#trademodecodatafordecl").val(rdata.data[0].trade_Mode_Code);	
							$("#goodplaacodeedatafordecl").val(rdata.data[0].goods_Place_Code);	
							$("#goodpladrdatafordecl").val(rdata.data[0].goods_Place);	
							$("#purposedatafordecl").val(rdata.data[0].purpose_Code);	
							$("#despdatedatafordecl").val(rdata.data[0].desp_Date);	
							$("#arridatedatafordecl").val(rdata.data[0].arri_Date);	
							$("#unloadatedatafordecl").val(rdata.data[0].unload_Date);	
							$("#despportdatafordecl").val(rdata.data[0].desp_Port_Code);	
							$("#arricodepordatafordecl").val(rdata.data[0].arri_Port_Code);	
							$("#entryportdatafordecl").val(rdata.data[0].entry_Port_Code);	
							$("#vialcodpotrdatafordecl").val(rdata.data[0].via_Port_Code);	
							$("#despladatafordecl").val(rdata.data[0].dest_Code);	
							$("#contradecodatafordecl").val(rdata.data[0].trade_Country_Code);	
							$("#sendcontryaredatafordecl").val(rdata.data[0].desp_Country_Code);	
							$("#situacodedatafordecl").val(rdata.data[0].situation_Code);	
							$("#sitionlevledatafordecl").val(rdata.data[0].situation_Level);	
							$("#counterclaimdatafordecl").val(rdata.data[0].counter_Claim);	
							$("#contractdatafordecl").val(rdata.data[0].contract_No);	
							$("#carriernotedatafordecl").val(rdata.data[0].carrier_Note_No);	
							$("#liencsedatafordecl").val(rdata.data[0].license_Code);	
							$("#approvedatafordecl").val(rdata.data[0].approve_Code);	
							$("#prodregdatafordecl").val(rdata.data[0].prod_Reg_No);	
							$("#exchanotcoddatafordecl").val(rdata.data[0].exchange_Note_Codes);	
							$("#exhagenonumdatafordecl").val(rdata.data[0].exchange_Note_Num);	
							$("#packcapcoddatafordecl").val(rdata.data[0].pack_Cap_Resu_Codes);	
							$("#packuseresdatafordecl").val(rdata.data[0].pack_Use_Resu_Codes);	
							$("#sheettypedatafordecl").val(rdata.data[0].sheet_Type_Codes);	
							$("#certtypedatafordecl").val(rdata.data[0].cert_Type_Codes);	
							$("#cretorigialdatafordecl").val(rdata.data[0].cert_Originals);	
							$("#cercopiesdatafordecl").val(rdata.data[0].cert_Copies);	
							$("#specialcheckdatafordecl").val(rdata.data[0].special_Require);	
							$("#marknumdatafordecl").val(rdata.data[0].mark_No);	
							$("#ifforeignvesdatafordecl").val(rdata.data[0].value_Checkup_Flag);	
							$("#inspcodewaydatafordecl").val(rdata.data[0].insp_Mode_Code);	
							$("#insomoedjidatafordecl").val(rdata.data[0].insp_Org_Code);	
							$("#tochckedeptdatafordecl").val(rdata.data[0].insp_Dept_1);	
							$("#tocehckdetptwodatafordecl").val(rdata.data[0].insp_Dept_2);	
							$("#tochedepthreedatafordecl").val(rdata.data[0].insp_Dept_3);	
							$("#tochoekfourdatafordecl").val(rdata.data[0].insp_Dept_4);	
							$("#tochoeljiefivedatafordecl").val(rdata.data[0].insp_Dept_5);	
							$("#typefordecldatafordecl").val(rdata.data[0].decl_Type_Code);	
							$("#statfoprocessdatafordecl").val(rdata.data[0].process_Status);	
							$("#feestoatusdatafordecl").val(rdata.data[0].fee_Status);	
							$("#goostolvaluedatafordecl").val(rdata.data[0].values_USD);	
							$("#totalogoodyuandatafordecl").val(rdata.data[0].values_RMB);	
							$("#operatcodedatafordecl").val(rdata.data[0].operator_Code);	
							$("#operateimerdatafordecl").val(rdata.data[0].operate_Date);	
							$("#sControlDept").val(rdata.data[0].stat_Flag);	
							$("#wasteflagdatafordecl").val(rdata.data[0].waste_Flag);	
							$("#releasestusdatafordecl").val(rdata.data[0].release_Status);	
							$("#checkputypeldatafordecl").val(rdata.data[0].checkup_Type_Code);
							$("#choejlswoerddatafordecl").val(rdata.data[0].checkup_Work_Code);	
							$("#outshipintersinglmesmark").val(rdata.data[0].org_Code);	
							$("#orgtheojwodddatafordecl").val(rdata.data[0].dept_Code);	
							$("#deptingodatafordecl").val(rdata.data[0].dest_Org_Code);	
							$("#mudididatafordecl").val(rdata.data[0].flow_Flag);	
							$("#liuxiangflgdatafordecl").val(rdata.data[0].trans_Flag);
							$("#chuanshudatafordecl").val(rdata.data[0].inputer_Code);
							$("#yulurupeodatafordecl").val(rdata.data[0].input_Date);
							$("#yultiemfodatafordecl").val(rdata.data[0].ent_Decl_No);
							$("#entdecohaodatafordecl").val(rdata.data[0].o_I_Flag);
							$("#kouanneidiflgdatafordecl").val(rdata.data[0].chg_Org_Code);
							$("#xiugaiorgdmdatafordecl").val(rdata.data[0].chg_Person_Code);
							$("#xugaiperodatafordecl").val(rdata.data[0].chg_Date);
							$("#updatetimeojdatafordecl").val(rdata.data[0].check_Flag);
							$("#chayanbzflodatafordecl").val(rdata.data[0].input_Org_Code);
							$("#lurupreivorcodatafordecl").val(rdata.data[0].deCL_FLAG);
							$("#zhitongshiflodatafordecl").val(rdata.data[0].cuSTOM_CODE);
							$("#baoguankouancodatafordecl").val(rdata.data[0].auTO_PASS_FLAG);
							$("#greetongdaozigbzhdatafordecl").val(rdata.data[0].auTO_CHECK_FLAG);
							$("#zidongshendanflgdatafordecl").val(rdata.data[0].baCK_TRANSPORT_FLAG);
							$("#iftuiyongflgdatafordecl").val(rdata.data[0].coNTACTOR);
							$("#contractperosndatafordecl").val(rdata.data[0].teLEPHONE);
							$("#thepohonnumbdatafordecl").val(rdata.data[0].prOCESS_FLAG);
							$("#chlililimesflgdatafordecl").val(rdata.data[0].moNITOR_PASS_FLAG);
							$("#dianzhichuliflgdatafordecl").val(rdata.data[0].coMPUTE_PASS_FLAG);
							$("#jishuanhefangbzhdatafordecl").val(rdata.data[0].trANS_MEANS_NAME);
							$("#yunshutooglsdatafordecl").val(rdata.data[0].moNITOR_FLAG);
							$("#jianguanbaozhidatafordecl").val(rdata.data[0].spOTTEST_FLAG);
							$("#dianeleccheckcoupijjdatafordecl").val(rdata.data[0].coMB_BATCH_NO);
							$("#huogooddgrouphaodatafordecl").val(rdata.data[0].reSEND_NUM);
							$("#aganinsengdflgdatafordecl").val(rdata.data[0].chANGE_INSP_DEPT_FLAG);
							$("#jigouneigsenglgofldatafordecl").val(rdata.data[0].ciQ2000_ASSIGN_FLAG);
							$("#xiqxitfendanbaozohidatafordecl").val(rdata.data[0].monitor_Decl_Flag);
							}else{
								alert("没有相应报检数据");
							}
							
						}, 'json');
			}
		  
		//查看huowu数据
		  $("#lookforgoodsdata").click(function(){
			     if(abnormalRow==null){alert("选中一条报检数据");return;}
				 $(".overlay").show();
				 $("#thedateforgoodsDialog").show(); 
				 var declno=abnormaltable.row(abnormalRow).data().declNo;
				 goodNo=1;
				 getDeclGoodsInfoFromCIQ(declno, goodNo);
				  
		 });   
		  
		//right button		  
		  $("#rightforgoodsBtn").click(function(){
			
			    
					 goodsnum=$("#huoxuhaogooddthedateforgoods").val().trim();
				     clearAlertDiv("thedateforgoodsDialog");
					 var declno=abnormaltable.row(abnormalRow).data().declNo;
					 goodNo=goodNo+1;
					 getDeclGoodsInfoFromCIQ(declno, goodNo);
				
				 
				  
		 }); 		  
//left button		  
		  $("#leftforgoodsBtn").click(function(){
			     clearAlertDiv("thedateforgoodsDialog");
				 var declno=abnormaltable.row(abnormalRow).data().declNo;
				 if(goodNo==1){
					 getDeclGoodsInfoFromCIQ(declno, 1);
				 }else{
					 goodNo=goodNo-1;
					 
					 getDeclGoodsInfoFromCIQ(declno, goodNo); 
				 }
				 
				  
		 }); 		    
		  function    getDeclGoodsInfoFromCIQ(declNo,goodsNo){
				data={ 
						declNo:declNo,
						goodsNo:goodsNo
						
				}; 
				 var jsonstr = JSON.stringify(data);
				$.post("ProcessReceiverAction_GetDeclGoodsInfoFromCIQ?&ts="   
						+ new Date().getTime(), {
							 data : jsonstr
						}, function(rdata) {
						
							if(rdata.data.length>0){    
								
							var gno=rdata.data[0].goods_No;
							
							$("#huoxuhaogooddthedateforgoods").val(gno);
							$("#hsbianmaincvodethedateforgoods").val(rdata.data[0].hs_Code);
							$("#huowuchinasenamethedateforgoods").val(rdata.data[0].goods_CName);
							$("#chendandanwihaothedateforgoods").val(rdata.data[0].prOD_REG_NO);
							$("#nameforeigngoddthedateforgoods").val(rdata.data[0].goods_EName);
							$("#guizgehaothedateforgoods").val(rdata.data[0].Goods_model);
							$("#orignincodethedateforgoods").val(rdata.data[0].origin_Place_Code);
							$("#yuanchaoguodaimathedateforgoods").val(rdata.data[0].origin_Country_Code);
							$("#weightzhonglithedateforgoods").val(rdata.data[0].weight);
							$("#zhonglainunitcodethedateforgoods").val(rdata.data[0].weight_Unit_Code);
							$("#stadwighotthedateforgoods").val(rdata.data[0].stD_Weight);
							$("#biaozhounweunticodthedateforgoods").val(rdata.data[0].stD_Weight_Unit_Code);
							$("#numbersosthedateforgoods").val(rdata.data[0].qtY);
							$("#numbsersjounitcodthedateforgoods").val(rdata.data[0].qtY_Unit_Code);
							$("#stdonumbersoenthedateforgoods").val(rdata.data[0].STD_qtY);
							$("#stanqualityunitcodthedateforgoods").val(rdata.data[0].stD_QTY_Unit_Code);
							$("#packzhuanjiannumthedateforgoods").val(rdata.data[0].pack_Number);
							$("#packtypecoothedateforgoods").val(rdata.data[0].pack_Type_Code);
							$("#whickbizthedateforgoods").val(rdata.data[0].ccY);
							$("#thetranslvthedateforgoods").val(rdata.data[0].rate);
							$("#singlepricethedateforgoods").val(rdata.data[0].price);
							$("#totlehuowuuuthedateforgoods").val(rdata.data[0].goods_Values);
							$("#goodsttoledollorthedateforgoods").val(rdata.data[0].values_USD);
							$("#goodrmbitothedateforgoods").val(rdata.data[0].values_RMB);
							$("#ciqbianmaciqthedateforgoods").val(rdata.data[0].ciQ_CODE);
							$("#wayjianyanthecoodethedateforgoods").val(rdata.data[0].inSP_MODE_CODE);
							$("#yanzhengtaojianflgthedateforgoods").val(rdata.data[0].coNDITION_FLAG);
							$("#feijuoldthingflgthedateforgoods").val(rdata.data[0].waSTE_FLAG);
							$("#jianzhongwaycodthedateforgoods").val(rdata.data[0].chECKUP_TYPE_CODE);
							$("#jianworkwaycothedateforgoods").val(rdata.data[0].chECKUP_WORK_CODE);
							$("#yanqingcodstrthedateforgoods").val(rdata.data[0].siTUATION_CODE);
							$("#yiqlevlstrthedateforgoods").val(rdata.data[0].siTUATION_LEVEL);
							$("#usecodyontuthedateforgoods").val(rdata.data[0].puRPOSE_CODE);
							$("#tongbaoxuhaostrthedateforgoods").val(rdata.data[0].noTIFY_NOS);
							$("#shencpihaothedateforgoods").val(rdata.data[0].prOD_NO);
							$("#googgrouppihzuthedateforgoods").val(rdata.data[0].coMBBATCH_NO);
							
							if(goodsnum==gno){
								alert("已到最后"); goodNo=goodNo-1;return;
							}
							}else{
								alert("没有相应货物数据");
							}
							
						}, 'json');
			}
		  
//查看报检数据关闭
		  $("#closeSearchConvCtrl").click(function(){
			    
				 $(".overlay").hide();
				 $("#datafordeclDialog").hide(); 
				 clearAlertDiv("datafordeclDialog");
				 
				  
		 });  
//查看huowu数据关闭
		  $("#closethedateforgoodsBtn").click(function(){
			    
				 $(".overlay").hide();
				 $("#thedateforgoodsDialog").hide(); 
				 clearAlertDiv("thedateforgoodsDialog");
				 
				  
		 });   
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
								
								 if(abnormalRow == null){
									  alert("请先选择一条要修改的单证！");
									  return;
								  }
								  clearAlertDiv("foreditingdecldataDialog");
								 $(".overlay").show();
								 $("#foreditingdecldataDialog").show(); 
								 var decln = abnormaltable.row(abnormalRow).data().declNo;
								 $("#vfsdfdbaodeclpro").val(decln); 
								 GetDeclInfoByDeclNo(decln);
								 editdecltable.draw();
							}
							
							 
						}else{
							alert(rdata.data);
						}
					}, 'json');
				
			}	  
});