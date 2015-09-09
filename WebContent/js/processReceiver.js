$(document).ready(function (){
	var goodsnum=0;
	
	var declNo='';
	var inspOrgCode='0040';//系统登录时检验机构代码
	var inspDeptCode ='';
	var inspOperatorCode='' ;
	var processName='检验员接单' ;
	var finishFlg='1';//1 待检验2已完成    
	var decldataRow=null;
	var outproductRow=null;
	var checkitemRow=null;
	var entproductRow=null;
	var itemRow=null;
	var showSamplingItemFlg=0;
	var showNotLabFlg=1;
	var setFlg=null;
	var goodNo=null;
    var operatingOrgCode=null ;
	var operatingDeptCode=null;
	var operatingOperatorCode=null;
	
	$("#theoneisnotuse").hide();
	var columnClass = [
		                  { "data": "declNo"   },
		                  { "data": "declDate" ,
		                	  "render": function (data, type, full, meta){
		                		  return data=data.substring(0,10);
		                	  }},
		                  { "data": "entCode" },
		      	          { "data": "entName" },
		      	          { "data": "countryCode" },
		      	          { "data": "countryName" },
		                  { "data": "processStatus" ,
		      	        	  /*"render":function(data, type, full, meta){
		      	        		  data=getProcessStatusForDecl(declNo);
		      	        	  }*/},
		      	          { "data": "productData" },
		                  { "data": "certType" },
		      	          { "data": "ifSampling",
		                	  "render": function ( data, type, full, meta ) {
		                		  if(data=='1'){      
		                			  return '是';
		                		  }else if(data=='0'){
		                			  return '否';
		                		  }else{
		                			  return '';
		                		  }
	                    	    } 
		      	         }
		      	         ];
	var decldatatable = $('#decldataTb').DataTable( {
			  "deferRender": true,
			  "processing": true,
			  "serverSide": true,
			  "ajax":{
				  url : "ProcessReceiverAction_GetDeclInfoForProcess",
				  type : "GET",
				  data : function(d){
					//  d.data = getClassReqParam();
					  
					  d.data=inintparm(declNo,inspOrgCode,inspDeptCode,inspOperatorCode,processName,finishFlg);
					  
				 }
			  },
		      "columns": columnClass,
		      "columnDefs": [
		                     /*{
		                         "targets": [10],
		                         "data": "ifSampling" ,
		                         "render" :function(data,type,row){
		                        	 return "<input type='checkbox' name='raidoflg'  id='"+row[0]+"'>合格"+"<input type='checkbox' name='radioflg' id='"+row[0].split("").reverse().join("")+"'>不合格";
		                         }
		                     },*/
		                     {
		                         // The `data` parameter refers to the data for the cell (defined by the
		                         // `data` option, which defaults to the column being worked with, in
		                         // this case `data: 0`.
		                    	 
		                       /*  "render": function ( data, type, row ) {
		                        	 alert(row[3]);
		                             return data +' ('+ row[3]+')';
		                         },
		                         "targets": 0*/
		                     },
		                    // { "visible": false,  "targets": [ 3 ] }
		                    /* {
		                         "targets": [10], // 目标列位置，下标从0开始  
		                         "data": "ifSampling", // 数据列名
		                         "render": function(data, type, full) { // 返回自定义内容
		                        	 if(data==1){
		                        		 return "<input type='checkbox' name='raidoflg' checked id='abcdefg'>合格"+"<input type='checkbox' name='radioflg'>不合格"; 
		                        	 }else if(data==0){
		                        		 return "<input type='checkbox' name='raidoflg'  >合格"+"<input type='checkbox' name='radioflg' checked>不合格"; 
		                        	 }
		                            
		                        	 //return "<a href='/delete?name=" + data + "'>Delete</a>&nbsp;<a href='/update?name=" + data + "'>Update</a>";
		                         }
		                     }*/
		                 ]
		  } );
	
//出口产品数据表数据的获取      
	var outproductcolum = [
		                  { "data": "declProductDetailID" },
		                  { "data": "productCode" },
		      	          { "data": "productName" },
		      	          { "data": "goodnn"},
		      	          { "data": "baseName" },
		      	          { "data": "samplingRatio"/*,
		      	        	  "render": function ( data, type, full, meta ){
		      	        		  return data=data+"%-";
		      	        	  }*/},
		                  { "data": "ifSampling" ,
		                	  "render": function ( data, type, full, meta ) {
	                    	       return data === '1'  ? '是' : '否';
	                    	    }},
		      	          { "data": "samplingReason" },
		      	          { "data": "weight" },
		      	          { "data": "values_USD" },
		      	        { "data": "entProductCode" }
		                 ];
	var outproducttable = $('#outproductdataTb').DataTable( {
			  "deferRender": true,
			  "processing": true,
			  "serverSide": true,
			  "paging" : false,
			  "info":false,
			  "ajax":{
				  url : "ProcessReceiverAction_GetDeclProduct",
				  type : "GET",
				  data : function(d){
					 
					  if(decldataRow==null){
						  d.data=JSON.stringify({declNo :'0'}); 
					  }else{
						  d.data=JSON.stringify({declNo : decldatatable.row(decldataRow).data().declNo});
					  }
					 
					
				  }
			  },
		      "columns": outproductcolum,  
		      "columnDefs": [
		                     {
		                         "targets": [ 0 ],
		                         "visible": false,
		                     },
		                     {
		                         "targets": [ 10 ],
		                         "visible": false,
		                     }
		                 ]
		  } );	
	
	
//监测项目表数据的获取
	var checkitemcolum = [
		                  { "data": "itemCode" },
		                  { "data": "itemName" },
		                  { "data": "limitReq" },
		      	          { "data": "samplingRatio" },
		      	          { "data": "ifSampling",
		      	        	"render": function ( data, type, full, meta ) {
	                    	       return data === '1'  ? '是' : '否';
	                    	    }},
		      	          { "data": "detectionStd" },
		                 
		                  { "data": "releaseMode" },
		                  { "data": "fromWhere" },
		                  { "data": "sendLabFlg" ,
			      	        	"render": function ( data, type, full, meta ) {
		                    	       return data === '1'  ? '是' : '否';
		                    	    }},
		                  { "data": "labDataUnit" },
		                  { "data": "testLabName" },
		      	          { "data": "itemType" },
		      	          { "data": "ifSet" ,
		      	        	"render": function ( data, type, full, meta ) {
	                    	       return data === '1'  ? '是' : '否';
	                    	    }}
		                 ];
	var checkitemtable = $('#controlitemTb').DataTable( {
			  "deferRender": true,
			  "processing": true,
			  "serverSide": true,
			  "ajax":{
				  url : "ProcessReceiverAction_GetDeclProductItem",
				  type : "GET",
				  data : function(d){
					  if(outproductRow==null){
						  d.data=JSON.stringify({declProductDetailID :'0',
			 					showSamplingItemFlg :showSamplingItemFlg,showNotLabFlg:showNotLabFlg
						  });
					  }else{
						 
						  d.data=JSON.stringify({declProductDetailID : outproducttable.row(outproductRow).data().declProductDetailID,
			 					showSamplingItemFlg :showSamplingItemFlg,showNotLabFlg:showNotLabFlg
						  });
					  }
					 
					 
				  }
			  },
		      "columns": checkitemcolum,  
		    "columnDefs": [
		                     {
		                         "targets": [ 5,11 ],
		                         "visible": false,
		                     }
		                 ]
		  } );	
	
	var entproducttable=$('#entproductqueryTb').DataTable({
		"paging" : false,
		"info":false
	});
	var itemtable=$('#foritemnamequeryTb').DataTable({
		"paging" : false,
		"info":false
	});
	var setitemtable=$('#jiheitemlookTb').DataTable({
		"paging" : false,
		"info":false
	});
	
	
	//---------------------------------------------------------------三个表初始化----------------
	
/*//初始化报检数据第一行默认选中
	$('#decldataTb').on( 'draw.dt', function () {
		  if(decldatatable.rows().data().length > 0){
			  var dtRow = decldatatable.row(0);
			  $(dtRow.node()).click();
		  }else{
			  decldataRow=null;
			  outproducttable.draw();
		  }
		 
		  
	  } );	*/
	
//初始化报检数据第一行默认选中
	$('#decldataTb').on( 'draw.dt', function () {
		  if(decldatatable.rows().data().length > 0){
			  var dtRow = null;
			  var node = null;
			  if(decldataRow != null){
			  dtRow = decldatatable.row($(decldataRow).context._DT_RowIndex);
			  node = dtRow.node();
			  if(node != null){
			  $(node).click();
			  return;
			  }
			  }
			  dtRow = decldatatable.row(0);
			  node = dtRow.node();
			  $(node).click();
         }else{
        	 decldataRow=null;
        	 outproducttable.draw();
         }
		  outproductRow=null;
		  
	  } );	

/*//初始化出口产品数据第一行默认选中
	$('#outproductdataTb').on( 'draw.dt', function () {
		  if(outproducttable.rows().data().length > 0){
			  var dtRow = outproducttable.row(0);
			  $(dtRow.node()).click();
		  }else{
			 
			  outproductRow=null;
			  checkitemtable.draw();  
			 
			}
		 // checkitemRow=null;
		  
	  } );	*/
	
//初始化出口产品数据第一行默认选中
	$('#outproductdataTb').on( 'draw.dt', function () {
		  if(outproducttable.rows().data().length > 0){
			  var dtRow = null;
			  var node = null;
			  if(outproductRow != null){
			  dtRow = outproducttable.row($(outproductRow).context._DT_RowIndex);
			  node = dtRow.node();
			  if(node != null){
			  $(node).click();
			  return;
			  }
			  }
			  dtRow = outproducttable.row(0);
			  node = dtRow.node();
			  $(node).click();
         }else{
        	 outproductRow=null;
        	 checkitemtable.draw();  
         }
		 
		  
	  } );	
	
//初始化监测项目第一行默认选中
	$('#controlitemTb').on( 'draw.dt', function () {
		  if(checkitemtable.rows().data().length > 0){
			  var dtRow = null;
			  var node = null;
			  if(checkitemRow != null){
			  dtRow = checkitemtable.row($(checkitemRow).context._DT_RowIndex);
			  node = dtRow.node();
			  if(node != null){
			  $(node).click();
			  return;
			  }
			  }
			  dtRow = checkitemtable.row(0);
			  node = dtRow.node();
			  $(node).click();
         }else{
        	 checkitemRow=null;
        	 
         }
		 
		  
	  } );		
	
//----------------------------------------------------------------------------默认选中第一行----------	
	
// 报检数据出口产品数据监测项目表中某一行的点击功能
    $('#decldataTb tbody').on("click", "tr", clickclassRow );
	$('#outproductdataTb tbody').on("click", "tr", clicksubclassRow );
	$('#controlitemTb tbody').on("click", "tr", clicksubsubclassRow );
	$('#entproductqueryTb tbody').on("click", "tr", clickentproductqueryRow );
	$('#foritemnamequeryTb tbody').on("click", "tr", clickforitemRow );
//选中报检数据表中的一行
	function clickclassRow(){  
		  var row = this;
		  if ( $(row).hasClass('active') ) {  
			  decldataRow = null;
	            $(row).removeClass('active');
	        }
	        else {
	        	decldataRow = row;
	        	decldatatable.$('tr.active').removeClass('active');
	            $(row).addClass('active');
	            
	          
	        }
		  outproducttable.draw();  
		 // outproductRow = null;  
	  }
//选中出口产品数表中的一行
	function clicksubclassRow(){ 
		 var row = this;
		  if ( $(row).hasClass('active') ) {  
			  outproductRow = null;
	            $(row).removeClass('active');
	        }
	        else {
	        	outproductRow = row;
	        	outproducttable.$('tr.active').removeClass('active');
	            $(row).addClass('active');
	            
	             
	        }
		  
		  checkitemtable.draw(); 
		 // checkitemRow = null;  
	  }
//选中监测项目表中的一行
	function clicksubsubclassRow(){  
		  var row = this;
		  if ( $(row).hasClass('active') ) {  
			    checkitemRow = null;
	            $(row).removeClass('active');
	        }
	        else {
	        	checkitemRow = row;
	        	checkitemtable.$('tr.active').removeClass('active');
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
//项目查询表中的一行
	function clickforitemRow(){  
		  var row = this;
		  if ( $(row).hasClass('active') ) {  
			   itemRow = null;
	            $(row).removeClass('active');
	        }
	        else {
	        	itemRow = row;
	        	itemtable.$('tr.active').removeClass('active');
	            $(row).addClass('active');
	        }
   }
//----------------------------------------------------------------------------点击选中-------------	
	
	 function inintparm(declNo,inspOrgCode,inspDeptCode,inspOperatorCode,processName,finishFlg){
		 data={ 
					declNo:declNo,
					inspOrgCode:inspOrgCode,
					inspDeptCode : inspDeptCode,
					inspOperatorCode : inspOperatorCode,
					processName : processName,
					finishFlg : finishFlg
			}; 
			 var jsonstr = JSON.stringify(data);
			return jsonstr;
			
		}
	// initentstatusSelect();
	 function initentstatusSelect(){
		  $.get("ProcessReceiverAction_GetProcessStatusForDecl?&ts="
					+ new Date().getTime(), {
						data:JSON.stringify({declNo:declNo})
					},
		    function(rdata) {
					
				/*var source = new Array();
				$.each(rdata.data, function(index, value){
					source[index] = value;
				});	
				cus_autocomplete(source, "statusdeclsingleprive", "sentstatusdeclsingleprive-select", null, null);		
			*/}, 'json');
	  }
	 initInspOrgSelect();
	  function initInspOrgSelect(){
		  $.get("SelectDataAction_getInspOrg?&ts="
					+ new Date().getTime(), 
		    function(rdata) {
				var source = new Array();
				$.each(rdata.data, function(index, value){
					source[index] = value.orgCode+" "+value.orgName;
				});	
				cus_autocomplete(source, "orgcodenamegivethesingleprive", "sentorgcodenamegivete-select", null, selectInspOrgCB);		
			}, 'json');
	  }
	  function selectInspOrgCB(event, ui){
		  var orgCode = ui.item.value.split(" ")[0];
		  initInspDeptSelect(orgCode);
	  }
	  
	  function initInspDeptSelect(orgCode){
		  $.get("SelectDataAction_getInspDept?&ts="
					+ new Date().getTime(), 
			{orgCode : orgCode},
		    function(rdata) {
				var source = new Array();
				$.each(rdata.data, function(index, value){
					source[index] = value.deptCode+" "+value.deptName;
				});	
				cus_autocomplete(source, "deptcodenamegivethesingleprive", "sentdeptcodenamegivethesing-select", null, null);		
			}, 'json');
	  }
	  function initLimitTypeSelect(){
		  $.get("SelectDataAction_getLimitType?&ts="
					+ new Date().getTime(), 
		    function(rdata) {
				var source = new Array();
				$.each(rdata.data, function(index, value){
					source[index] = value.limitType;
				});	
				cus_autocomplete(source, "aLimitType", "aLimitType-select", null, null);		
			}, 'json');
	  }
	  initLimitTypeSelect();
	  function initLimitTypeSelect(){
		  
		  $.get("SelectDataAction_getLimitType?&ts="
					+ new Date().getTime(), 
		    function(rdata) {
				var source = new Array();
				$.each(rdata.data, function(index, value){
					source[index] = value.limitType;
				});	
				cus_autocomplete(rdata.data, "limittypeadditemchecked", "sentlimitypechecked-select", null, null);		
			}, 'json');
	  }
	  inintgetRiskClass();
	  function inintgetRiskClass(){
		  $.get("ProcessReceiverAction_GetRiskClass?&ts="
					+ new Date().getTime(), 
		    function(rdata) {
				var source = new Array();
				$.each(rdata.data, function(index, value){
					source[index] = value.classCode+" "+value.className;
				});	
				cus_autocomplete(source, "riskclassforitemnamequery", "sentriskclassitemquery-select", null, null);		
			}, 'json');
	  }
//-------------------------------------------------------------------------------下拉框初始化----------------------------	  
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
			 $("#secledted").val('待接收');
			   
		  });	
	
//查询功能
			$('#querydeclsinglepriveBtn').click(function(){
				           declNo=$("#declnodeclsingleprive").val().trim();
				           var status=$("#secledted").val().trim();
				           if(status=='待接收'){finishFlg='1';}
				           else if(status=='已接收'){finishFlg='2';}  
				        
				           decldatatable.draw();
				           $("#closedeclsinglepriveDialogBtn").click();
						 
			});	
//主界面转单按钮	
		$("#givedecldata").click(function(){
			     if(decldataRow==null){alert("选中一条报检数据");return;}
				 $(".overlay").show();
				 $("#declnogivethesingleprive").val(decldatatable.row(decldataRow).data().declNo);
				 $("#givethesinglepriveDialog").show(); 
			/*var declN=decldatatable.row(decldataRow).data().declNo;
			var fz=declN.split("").reverse().join("");
			alert(fz);
			if($("#"+declN).prop("checked")){
				alert("check");
			}else{
				alert("not check");
			}
			if($("#"+fz).prop("checked")){
				alert("fz check");
			}else{
				alert(" fz not check");
			}
			alert("yzj");
			var declN=decldatatable.row(decldataRow).data()[10];
			 alert(declN+" ten is ");*/
		 });
//转单对话框中取消按钮
		 $("#closegivethesinglepriveDialogBtn").click(function(){
			 $(".overlay").hide();
			 $("#givethesinglepriveDialog").hide(); 
			 clearAlertDiv("givethesinglepriveDialog");
			    // entName ='';
				// entProductName ='';
				// status ='待审核';
		  });	
//转单对话框中确认功能	
		 $('#suregivethesinglepriveBtn').click(function(){
		     var declN=decldatatable.row(decldataRow).data().declNo;
		     var inspOrgCod=getSelectValue("orgcodenamegivethesingleprive");
			 var inspDeptCod=getSelectValue("deptcodenamegivethesingleprive");
			    if(inspOrgCod=='' || inspDeptCod==''){alert("信息填写完整");return;}
			    if(confirm("确定要转单")){
			    	 transDecl(declN, inspOrgCod, inspDeptCod);
			    }
			
				
			
				 
		 	});	
		 
			function     transDecl(declNo,inspOrgCode,inspDeptCode){
				data={ 
						declNo:declNo,
						inspOrgCode:inspOrgCode,
						inspDeptCode : inspDeptCode,
						operatingOrgCode : operatingOrgCode,
						operatingDeptCode : operatingDeptCode,
						operatingOperatorCode:operatingOperatorCode
				}; 
				 var jsonstr = JSON.stringify(data);
				$.post("ProcessReceiverAction_TransDecl?&ts="   
						+ new Date().getTime(), {
							 data : jsonstr
						}, function(rdata) {
							if(rdata.data=="true"){
								/* inspDeptCode ='';
								 inspOperatorCode='' ;
								 processName='检验员接单' ;
								 finishFlg=1;*/
								 decldatatable.draw();
								 $("#closegivethesinglepriveDialogBtn").click();
							}else{
								alert(rdata.data);
								 $("#closegivethesinglepriveDialogBtn").click();
							}
							
						}, 'json');
			} 
		 
//---------------------------------------------------------------------------转单功能----------		 
//主界面调回按钮	
		 $("#rebackdecldata").click(function(){
				     if(decldataRow==null){alert("选中一条报检数据");return;}
					  var declN=decldatatable.row(decldataRow).data().declNo;
					  withdrawDecl(declN);
					  
			 });
		function     withdrawDecl(declNo){
				data={ 
						declNo:declNo,
						inspOrgCode : operatingOrgCode,
						inspDeptCode : operatingDeptCode,
						inspOperatorCode:operatingOperatorCode
				}; 
				 var jsonstr = JSON.stringify(data);
				$.post("ProcessReceiverAction_WithdrawDecl?&ts="   
						+ new Date().getTime(), {
							 data : jsonstr
						}, function(rdata) {
							if(rdata.data=="true"){
								alert("调回单证成功");
								 /*inspDeptCode ='';
								 inspOperatorCode='' ;
								 processName='检验员接单' ;
								 finishFlg=1;*/
								 decldatatable.draw();
							}else{
								alert(rdata.data);
							}
							
						}, 'json');
			}	
//----------------------------------------------------------------------调回完毕---------------		 
//主界面接单确认按钮	
		 $("#receivesuredecldata").click(function(){
			 
			 getoperatelimit('检验员接单',2);
				    
					
					  
			 });
		
		 function    acceptDecl(declN){
				data={ 
						declNo:declN,
						inspOrgCode : operatingOrgCode,
						inspDeptCode : operatingDeptCode,
						inspOperatorCode:operatingOperatorCode
				}; 
				 var jsonstr = JSON.stringify(data);
				$.post("ProcessReceiverAction_AcceptDecl?&ts="   
						+ new Date().getTime(), {
							 data : jsonstr
						}, function(rdata) {
							if(rdata.data[0].one=="true"){
							
								var key =rdata.data[0].two;
								
								if(key=='1'){
								
									saveReturnFlg(declN,'d');
									/*inspDeptCode ='';
									 inspOperatorCode='' ;
									 processName='检验员接单' ;
									 finishFlg=1;*/
									 decldatatable.draw();   
									//decldatatable.row(decldataRow).remove().draw(false);
								}else if(key=='2'){
								
									saveReturnFlg(declN,'c');
									/*inspDeptCode ='';
									 inspOperatorCode='' ;
									 processName='检验员接单' ;
									 finishFlg=1;*/
									 decldatatable.draw();
									//decldatatable.row(decldataRow).remove().draw(false);
								}
								
							}else{
								alert(rdata.data[0].one);
							}
							
						}, 'json');
			}
		
		//在集中审单系统调用存储过程Pro_SaveReturnFlg集中审单系统的数据库是Oracle
			function     saveReturnFlg(decl_no,returnFlg){
				data={ 
						declNo:decl_no,
						returnFlg :returnFlg
				}; 
				 var jsonstr = JSON.stringify(data);
				$.post("ProcessReceiverAction_SaveReturnFlg?&ts="   
						+ new Date().getTime(), {
							 data : jsonstr
						}, function(rdata) {
						
						}, 'json');
			}
		 
//------------------------------------------------------------------------接单确认功能-----------------		 
		 

//主界面直接放行按钮	
			 $("#directgodecldata").click(function(){
					     if(decldataRow==null){alert("选中一条报检数据");return;}
						  var declN=decldatatable.row(decldataRow).data().declNo;
						 directRelease(declN);
						  
				 });			 
		  function    directRelease(declN){
					data={ 
							declNo:declN,
							inspOrgCode : operatingOrgCode,
							inspDeptCode : operatingDeptCode,
							inspOperatorCode:operatingOperatorCode
					}; 
					 var jsonstr = JSON.stringify(data);
					$.post("ProcessReceiverAction_DirectRelease?&ts="   
							+ new Date().getTime(), {
								 data : jsonstr
							}, function(rdata) {
								if(rdata.data=="true"){
								
									getCEMSSeqNum(declN);
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
							saveReturnFlg(declN, 'e');
							CheckPrintCert(declN, key);
							//updateControlReturnFlgNew(key, '2', '0');
							 /*inspDeptCode ='';
							 inspOperatorCode='' ;
							 processName='检验员接单';
							 finishFlg=1;*/
							//decldatatable.draw();
							
						}, 'json');
			} 
		  
			function CheckPrintCert(decl,key){
				data={ 
						declNo:decl
				}; 
				 var jsonstr = JSON.stringify(data);
				$.post("PassJundgeAction_CheckIfPrintCert?&ts="   
						+ new Date().getTime(), {
							 data : jsonstr
						}, function(rdata) {
							
							
							if(rdata.data=='0'){
								
								updateControlReturnFlgNew(key, '2', '1');
							}else if(rdata.data=='1'){
								
								updateControlReturnFlgNew(key, '2', '2');
							}
							decldatatable.draw();      
						
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
		  
//查看报检数据
		  $("#lookfordecldata").click(function(){
			     if(decldataRow==null){alert("选中一条报检数据");return;}
				 $(".overlay").show();
				 $("#datafordeclDialog").show(); 
				 var declno=decldatatable.row(decldataRow).data().declNo;
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
			     if(decldataRow==null){alert("选中一条报检数据");return;}
				 $(".overlay").show();
				 $("#thedateforgoodsDialog").show(); 
				 var declno=decldatatable.row(decldataRow).data().declNo;
				 goodNo=1;
				 getDeclGoodsInfoFromCIQ(declno, goodNo);
				  
		 }); 
//right button		  
		  $("#rightforgoodsBtn").click(function(){
			
			    
					 goodsnum=$("#huoxuhaogooddthedateforgoods").val().trim();
				     clearAlertDiv("thedateforgoodsDialog");
					 var declno=decldatatable.row(decldataRow).data().declNo;
					 goodNo=goodNo+1;
					 getDeclGoodsInfoFromCIQ(declno, goodNo);
				
				 
				  
		 }); 		  
//left button		  
		  $("#leftforgoodsBtn").click(function(){
			     clearAlertDiv("thedateforgoodsDialog");
				 var declno=decldatatable.row(decldataRow).data().declNo;
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
		  
//----------------------------------------------------------------------------直接放行完毕------------------			 
//主界面出口数据更改抽检按钮	
			$("#updateoutproductdata").click(function(){
				
				 getoperatelimit('更改抽检',4);
			});	
		  
			function     changeProductSampling(declProductDetailID){
				data={ 
						declProductDetailID:declProductDetailID
						
				}; 
				 var jsonstr = JSON.stringify(data);
				$.post("ProcessReceiverAction_ChangeProductSampling?&ts="   
						+ new Date().getTime(), {
							 data : jsonstr
						}, function(rdata) {
							if(rdata.data=="true"){
								alert("更改成功");
								outproducttable.draw();       
								
							}else{
								alert(rdata.data);
							}
							
						}, 'json');
			}
		  
//-------------------------------------------------------------------------更改抽检功能---------------		  
		  
		  
 //主界面新增产品按钮	
			$("#addoutproductdata").click(function(){
				     if(decldataRow==null){alert("选中一条报检数据");return;}
					  setFlg=1;
					  var declno=decldatatable.row(decldataRow).data().declNo;
					  CheckQualifySaveBefore(declno);
			 });
//主界面修改产品按钮	
			$("#editoutproductdata").click(function(){
				  if(decldataRow==null){alert("选中一条报检数据");return;}
				  if(outproductRow==null){alert("选中一条出口产品数据");return;}
					setFlg=2;
					var declno=decldatatable.row(decldataRow).data().declNo;
					 CheckQualifySaveBefore(declno);
					
			 });			
//新增产品对话框中取消按钮
			 $("#closeqoutcheckproductDialogBtn").click(function(){
				 $(".overlay").hide();
				 $("#addoutcheckproductDialog").hide(); 
				 clearAlertDiv("addoutcheckproductDialog");
				 $("#samplingflgoutcheckpro").prop("checked",false);
				    // entName ='';
					// entProductName ='';
					// status ='待审核';
			  });	
			 
//新增产品对话框中保存功能	
			 $('#saveoutcheckproductBtn').click(function(){
				  var entzicode=$("#entprozijidecodema").val().trim();
				   var procode=$("#productcodeoutcheckproduct").val().trim();
				  
				 /* var weight=$("#weightoutcheckpro").val().trim();
				  var money=$("#valuesusdoutcheckpro").val().trim();*/
				  if(procode==''){alert("填写产品编号");return;}
				  if(entzicode==''){alert("没有企业自编码");return;}
				 /* if(basecode==''){alert("填写原料基地");return;}*/
				  //if(weight==''){alert("填写数重量");return;}
				  //if(money==''){alert("填写金额");return;}
				  //if(isNaN(weight)){alert("数重量必须为实数");return;}
				 // if(isNaN(money)){alert("金额必须为实数");return;}
				  var goodnn=$("#bcheckgoodnanum").val().trim();
					 
				  var goodsNo= getSelectValue("bcheckgoodnanum");
				  var basecode= getSelectValue("baseconaoutcheckpro");
				 
				  var k=[];
				  k= goodnn.split(' ');     
				
				  var goodsCName=k[1];
				  var checkflg=null;
				  if($("#samplingflgoutcheckpro").prop("checked")){
					  checkflg=1;
				  }else{
					  checkflg=2;
				  }
				  var declN=decldatatable.row(decldataRow).data().declNo;
				  if(setFlg==1){
					  saveDeclProductInput(0, declN, procode,entzicode, basecode, goodsNo, goodsCName, checkflg);
					  
					 
				  }else if(setFlg==2){
					  var declprodetailid=outproducttable.row(outproductRow).data().declProductDetailID;
				
					  saveDeclProductInput(declprodetailid, declN, procode,entzicode, basecode, goodsNo, goodsCName, checkflg);
					  ;
				  }
					 
			 	});	
			 
			 function    saveDeclProductInput(declProductDetailID,declNo,productCode,entProductCode,baseCode,goodsNo,goodsCName,samplingFlg){
					data={ 
							declProductDetailID:declProductDetailID,
							declNo:declNo,
							productCode : productCode,
							entProductCode :entProductCode,
							baseCode :getSelectValue("baseconaoutcheckpro"),
							goodsNo :getSelectValue("bcheckgoodnanum"),
							goodsCName :goodsCName,
							ifSampling :samplingFlg
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
									if(setFlg==1){
										outproductRow=null;
										outproducttable.draw();
									}else if(setFlg==2){
										outproducttable.draw(false);
									}
								
									 $("#closeqoutcheckproductDialogBtn").click();
								}else{
									alert(rdata.data);
									$("#closeqoutcheckproductDialogBtn").click();
								}
								
							}, 'json');
				}
			 
//删除出口产品数据
			 $('#deleteoutproductdata').click(function(){
					
				  if(decldataRow==null){alert("选中一条报检数据");return;}
			  	  if(outproductRow==null){alert("选中一条出口产品数据");return;}	
				  setFlg=4;
				  var declno=decldatatable.row(decldataRow).data().declNo;
				  CheckQualifySaveBefore(declno);
				 
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
									outproducttable.row(outproductRow).remove().draw(false);
								}
								
							}, 'json');
				}	
			 //货物号
			 $("#bcheckgoodnanum-search").click(function(){
				  searchgoodnuna("bcheckgoodnanum");    
			  });			 
			 
			 function searchgoodnuna(input){    
				//  var basename = $("#"+input).val().trim();
				  var decln = decldatatable.row(decldataRow).data().declNo;
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
			 
//新增产品对话框中产品编号按钮--企业产品查询	
				$("#sproductoutcheckproduct").click(function(){
						 $(".overlay").show();
						 $("#addoutcheckproductDialog").hide(); 
						 $("#entcodeentproductquery").val(decldatatable.row(decldataRow).data().entCode);
						 $("#contryentproductquery").val(decldatatable.row(decldataRow).data().countryCode);
						 $("#entproductquerydialog").show();  
						 
						 
				 });
//企业产品查询对话框中查询按钮
				     $("#queryentproductqueryBtn").click(function(){
				    	 entproducttable.row().remove().draw();
					 var name=$("#productentproductquery").val();
					
					 if(name==''){alert("没有输入产品名称");return;}
					 var entcode=$("#entcodeentproductquery").val();
					 var countrycode=$("#contryentproductquery").val();
					 getEntProductByQuery(entcode, '', name, '已审核', '');
					// getEntProductByQuery(entcode, '', countrycode, name);
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
							 data : jsonstr
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
//企业产品查询对话框中取消按钮				 
				 $("#cancelentproductqueryBtn").click(function(){
					 $(".overlay").show();
					 $("#addoutcheckproductDialog").show(); 
					 $("#entproductquerydialog").hide(); 
					 entproducttable.row().remove().draw();
					 clearAlertDiv("entproductquerydialog");
				  });	 
//原料基地查询	 
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
				 
//-----------------------------------------------------------------出口产品数据功能完毕-----------				 
				 
//主界面新增项目按钮
				  $("#addcontrolitem").click(function(){
					 
					  if(decldataRow==null){alert("选中一条报检数据");return;}
				  	  if(outproductRow==null){alert("选中一条出口产品数据");return;}	
					  setFlg=5;
					  var declno=decldatatable.row(decldataRow).data().declNo;
					  CheckQualifySaveBefore(declno);
				  });
//新增项目对话框中取消按钮
				 $("#closeadditemcheckedDialogBtn").click(function(){
					 $(".overlay").hide();
					 $("#additemcheckedDialog").hide(); 
					 clearAlertDiv("additemcheckedDialog");
					    // entName ='';
						// entProductName ='';
						// status ='待审核';
				  });	
				
//新增项目对话框中确认功能
				$('#sureadditemcheckedBtn').click(function(){
					     
					 var declprodetailid=outproducttable.row(outproductRow).data().declProductDetailID;
					
					  var itemcode=getSelectValue("itemconaadditemchecked");
					  var stander=$("#detectionstdadditemchecked").val().trim();
					  var limitype=$("#limittypeadditemchecked").val().trim();
					  var deteionlimit=$("#detectionlimitadditemchecked").val().trim();
				   	  var limitunit=$("#limitunitadditemchecked").val().trim();
				   	  if(itemcode=='' ||  limitype=='' || deteionlimit==''){alert("项目 限量类型 限量值为必填项");return;}
				   	  if(limitype !='逻辑型' ){
				   		  if(isNaN(deteionlimit)){
				   			  alert("非逻辑型的限量值必须为数字");return;
				   		  }
				   	  }
				   	  saveDeclProductItemInput(declprodetailid, itemcode, stander, limitype, deteionlimit, limitunit);
					
				});				 
				function    saveDeclProductItemInput(declProductDetailID,itemCode,detectionStd,limitReq,detectionLimit,limitUnit){
					data={ 
							declProductDetailID:declProductDetailID,
							itemCode:itemCode,
							detectionStd : detectionStd,
							limitReq : limitReq,
							detectionLimit : detectionLimit,    
							limitUnit : limitUnit
					}; 
					
					if(!checkJsonParam(data)){
						return ;
						}
					 var jsonstr = JSON.stringify(data);
					$.post("ProcessReceiverAction_SaveDeclProductItemInput?&ts="   
							+ new Date().getTime(), {
								 data : jsonstr
							}, function(rdata) {
								if(rdata.data=="true"){
									
									checkitemtable.draw();
									$("#closeadditemcheckedDialogBtn").click();
									
								}else{
									alert(rdata.data);
									$("#closeadditemcheckedDialogBtn").click();
								}
								
							}, 'json');
				} 
				 
//新增项目对话框中项目后面按钮			 
			  /* $("#sitemnacoadditemchecked").click(function(){
					 $(".overlay").show();
					 $("#foritemnamequeryDialog").show();   
					 $("#additemcheckedDialog").hide(); 
				 });*/	 
				 
				 
//新增项目对话框中项目后面中查询按钮
				 $("#queryforitemnamequeryBtn").click(function(){
				
					 itemtable.row().remove().draw();
					var itemname= $("#itemnameforitemnamequery").val().trim();
					var riskclass=getSelectValue("riskclassforitemnamequery");
					
					if(itemname=='' && riskclass==''){alert("至少输入一个");return;}
					 getItem(itemname, riskclass);
					    // entName ='';
						// entProductName ='';
						// status ='待审核';
				  });
				 
				 $("#sitemnacoadditemchecked-search").click(function(){
					 var itemname= $("#itemconaadditemchecked").val().trim();
					 if(itemname==''){alert("输入检索项目名称关键字段");return;}
					 searchitemziduan("itemconaadditemchecked");
				  });
				 
				 function searchitemziduan(input){
					  var basename = $("#"+input).val().trim();
						 $.get("ProcessReceiverAction_GetItem?&ts="
									+ new Date().getTime(), 
						    {data : JSON.stringify({itemName:basename,riskClassCode:''})},
						    function(rdata) {
								var source = new Array();
								$.each(rdata.data, function(index, value){
									source[index] = value.itemCode+" "+value.itemName;
								});	
								cus_autocomplete(source, input, null, null, null);
								$("#"+input).autocomplete( "search", "" );
							}, 'json');
				  }	
				 
				 function    getItem(itemName,riskClassCode){
						data={ 
								itemName:itemName,
								riskClassCode:riskClassCode
								
								
						}; 
						 var jsonstr = JSON.stringify(data);
						$.post("ProcessReceiverAction_GetItem?&ts="   
								+ new Date().getTime(), {
									 data : jsonstr
								}, function(rdata) {
									
									 for ( var i = 0; i < rdata.data.length; i++) {
										 itemtable.row.add([
											           rdata.data[i].itemCode,rdata.data[i].itemName
											          ]);
										}
									 itemtable.draw();
									
								}, 'json');
					}
				 
				 
//新增项目对话框中项目后面选中返回按钮
				 $("#checkedforitemnamequery").click(function(){
					 if(itemRow==null){alert("选中一条");return;}
					
					 /*$(".overlay").show();
					 $("#additemcheckedDialog").show(); 
					 $("#foritemnamequeryDialog").hide(); */
					 if(itemtable.rows().data().length >0){
						 $("#itemconaadditemchecked").val(itemtable.row(itemRow).data()[0]+" "+itemtable.row(itemRow).data()[1]);
						 $("#closeqforitemnamequeryDialogBtn").click(); 
					 }else{
						 alert("数据为空，无法返回");
					 }
					 
					 
					 // entName ='';
						// entProductName ='';
						// status ='待审核';
				  });	
//项目名称查询中的取消
				 $("#closeqforitemnamequeryDialogBtn").click(function(){
					 $(".overlay").show();
					 $("#additemcheckedDialog").show(); 
					 $("#foritemnamequeryDialog").hide(); 
					 clearAlertDiv("foritemnamequeryDialog");
					 itemtable.row().remove().draw();
					 
				 });
				 
//集合查看按钮
				 $("#watchthejiheBtn").click(function(){
					 
					 if(checkitemRow==null){alert("选中一条监测项目数据");return;}
					 var ifsets=checkitemtable.row(checkitemRow).data().ifSet;
					
					 if(ifsets=='0'){alert("不是集合");return;}
					 $(".overlay").show();
					 $("#lookjiheitemDialog").show(); 
					  var itemcode=checkitemtable.row(checkitemRow).data().itemCode;
					  getItemSub(itemcode);
					  
					 
				 });
					function    getItemSub(itemCode){
						data={ 
								itemCode:itemCode
								
								
						}; 
						 var jsonstr = JSON.stringify(data);
						$.post("ProcessReceiverAction_GetItemSub?&ts="   
								+ new Date().getTime(), {
									 data : jsonstr
								}, function(rdata) {
									 for ( var i = 0; i < rdata.data.length; i++) {
										 setitemtable.row.add([
											           rdata.data[i].itemCodeSub,rdata.data[i].itemName
											          ]);
										}
									 setitemtable.draw();
									
								}, 'json');
					}
	 //集合项目关闭
				 $("#closejiheitemdialogBtn").click(function(){
					 
					 
					 $(".overlay").hide();
					 $("#lookjiheitemDialog").hide();   
					 
				 });
				 
//更改项目抽检
				 $("#updatecontrolitem").click(function(){
					 
					 getoperatelimit('更改抽检', 5);
				 });
					function    changeItemSampling(declProductDetailID,itemCode){
						data={ 
								declProductDetailID:declProductDetailID,
								itemCode:itemCode,
								
						}; 
						 var jsonstr = JSON.stringify(data);
						$.post("ProcessReceiverAction_ChangeItemSampling?&ts="   
								+ new Date().getTime(), {
									 data : jsonstr
								}, function(rdata) {
									if(rdata.data=="true"){
										
										checkitemtable.draw(false);
									}else{
										alert(rdata.data);
									}
									
								}, 'json');
					}	 
				 
				 
	function  getDeclInfoForProcess (declNo,inspOrgCode,inspDeptCode,inspOperatorCode,processName,finishFlg){
		data={ 
				declNo:declNo,
				inspOrgCode:inspOrgCode,
				inspDeptCode : inspDeptCode,
				inspOperatorCode : inspOperatorCode,
				processName : processName,
				finishFlg : finishFlg
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("ProcessReceiverAction_GetDeclInfoForProcess?&ts="   
				+ new Date().getTime(), {
					 data : jsonstr
				}, function(rdata) {
					
				}, 'json');
		
	}
	function    getProcessStatusForDecl(declNo){
		data={ 
				declNo:declNo
				
		}; 
		 var jsonstr = JSON.stringify(data);
		 var valuess=null;
		$.post("ProcessReceiverAction_GetProcessStatusForDecl?&ts="   
				+ new Date().getTime(), {
					 data : jsonstr
				}, function(rdata) {
					valuess=rdata.data;
				}, 'json');
		return valuess;
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
					
				}, 'json');
	}
	function     getDeclProductItem(){
		data={ 
				declProductDetailID:declProductDetailID,
				showSamplingItemFlg:showSamplingItemFlg,
				showNotLabFlg : showNotLabFlg
			
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("ProcessReceiverAction_GetDeclProductItem?&ts="   
				+ new Date().getTime(), {
					 data : jsonstr
				}, function(rdata) {
					
				}, 'json');
	}

	
	
	
	
	
	
	

	
	

	
	
	function    getRiskClass(declNo,inspOrgCode,inspDeptCode,inspOperatorCode,processName,finishFlg){
		data={ 
				declNo:declNo,
				inspOrgCode:inspOrgCode,
				inspDeptCode : inspDeptCode,
				inspOperatorCode : inspOperatorCode,
				processName : processName,
				finishFlg : finishFlg
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("ProcessReceiverAction_GetRiskClass?&ts="   
				+ new Date().getTime(), {
					 data : jsonstr
				}, function(rdata) {
					
				}, 'json');
	}
	
//采信放行按钮
	  $("#creditRelease").click(function(){
		 
		 getoperatelimit('检验员接单',1);
	  });
//取消放行按钮
	  $("#cancleRelease").click(function(){
		 
		 getoperatelimit('检验员接单',3);
	  });
//采信放行取消
	  $("#closeCreditRelease").click(function(){
		  $(".overlay").hide();
			 $("#creditReleaseDialog").hide(); 
			 clearAlertDiv("creditReleaseDialog");
		 
	  });
//确认放行按钮
	  $("#sureCreditRelease").click(function(){
		 
		var itemCode=getSelectValue("creditem");
		var proid= outproducttable.row(outproductRow).data().declProductDetailID;
		// proid=parseInt(proid, 10);
		var testname=$("#credithrdorg").val().trim();
		var labData=$("#credresult").val().trim();
		var dataUnit=$("#creiunit").val().trim();
		if(testname==''){alert("请输入第三方检测机构");return;}  
		if(labData==''){alert("请输入检测结果");return;}
		
		SaveCreditRelease(testname, itemCode, proid, labData, dataUnit);
		
		
	  });	  
	  function SaveCreditRelease(testLabName,itemCode,declProductDetailID,labData,dataUnit){
		
		  data={ 
				   testLabName : testLabName,
				   itemCode:itemCode,
				   declProductDetailID:declProductDetailID,
				  labData : labData,
				  dataUnit : dataUnit
				
			}; 
			 var jsonstr = JSON.stringify(data);
			$.post("ProcessReceiverAction_SaveCreditRelease?&ts="   
					+ new Date().getTime(), {
						 data : jsonstr
					}, function(rdata) {
						
						if(rdata.data=="true"){
							alert("采信放行成功");
							checkitemtable.draw();
							 $("#closeCreditRelease").click();
						}else{
							alert(rdata.data);
						}
						
					}, 'json');
		
	  }
	  function CancelReleaseMode(declProductDetailID,itemCode){
			
		  data={ 
				   declProductDetailID:declProductDetailID,
				   itemCode:itemCode
				  
				  
				
			}; 
			 var jsonstr = JSON.stringify(data);
			$.post("ProcessReceiverAction_CancelReleaseMode?&ts="   
					+ new Date().getTime(), {
						 data : jsonstr
					}, function(rdata) {
						
						if(rdata.data=="true"){
							alert("取消放行成功");
							checkitemtable.draw();
							
						}else{
							alert(rdata.data);
						}
						
					}, 'json');
		
	  }

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

								
								 if(decldataRow==null){alert("选中一条报检数据");return;}
							  	  if(outproductRow==null){alert("选中一条出口产品数据");return;}	
							  	 if(checkitemRow==null){alert("选中一条监测项目数据");return;}
								  setFlg=7;
								  var declno=decldatatable.row(decldataRow).data().declNo;
								  CheckQualifySaveBefore(declno);
								
							}else if(useflg==2){
								 if(decldataRow==null){alert("选中一条报检数据");return;}
								  var declN=decldatatable.row(decldataRow).data().declNo;
								  if(confirm("确定要接单吗?")){
									  acceptDecl(declN);
								  }
							}else if(useflg==3){
								 if(decldataRow==null){alert("选中一条报检数据");return;}
							  	  if(outproductRow==null){alert("选中一条出口产品数据");return;}	
							  	 if(checkitemRow==null){alert("选中一条监测项目数据");return;}
								  setFlg=8;
								  var declno=decldatatable.row(decldataRow).data().declNo;
								  CheckQualifySaveBefore(declno);
							}else if(useflg==4){

								 if(decldataRow==null){alert("选中一条报检数据");return;}
								 if(outproductRow==null){alert("选中一条出口产品数据");return;}	
								  setFlg=3;
								  var declno=decldatatable.row(decldataRow).data().declNo;
								  CheckQualifySaveBefore(declno);
								
							 
							}else if(useflg==5){
								if(decldataRow==null){alert("选中一条报检数据");return;}
							  	  if(outproductRow==null){alert("选中一条出口产品数据");return;}	
							  	 if(checkitemRow==null){alert("选中一条监测项目数据");return;}
								  setFlg=6;
								  var declno=decldatatable.row(decldataRow).data().declNo;
								  CheckQualifySaveBefore(declno);
							}
							
							 
						}else{
							alert(rdata.data);
						}
					}, 'json');
				
			}	
	
		  $("#credithrdorg-select").click(GetOtherTestLab);	 
		 
			function GetOtherTestLab(){
				data={ 
						testLabName: $("#credithrdorg").val().trim()
						
				}; 
				 var jsonstr = JSON.stringify(data);
				$.post("ThridTestLabAction_GetOtherTestLab?&ts="   
						+ new Date().getTime(), {
							 data : jsonstr
						}, function(rdata) {
							var source = new Array();
							$.each(rdata.data, function(index, value){
								source[index] = value.testLabName;
							});	
							if(source.length == 0){
								alert("查询结果为空！");
								return;
							}
							cus_autocomplete(source, "credithrdorg", null, null, null);
							$("#credithrdorg").autocomplete( "search", "" );
						}, 'json');
			}
			
			
			  function CheckQualifySaveBefore(declno){
					data={ 
							processName :processName,
							declNo :declno
							
					}; 
					 var jsonstr = JSON.stringify(data);
					$.post("PassJundgeAction_CheckQualifySaveBefore?&ts="   
							+ new Date().getTime(), {
								 data : jsonstr
							}, function(rdata) {
								if(rdata.data=="true"){
									if(setFlg==1){
									 $(".overlay").show();
									 $("#addoutcheckproductDialog").show(); 
									 $("#chongzhongtheprodiv").show();
									 
									}else if(setFlg==2){
										 $(".overlay").show();
										 $("#addoutcheckproductDialog").show();   
										 $("#chongzhongtheprodiv").hide(); 
										 $("#productcodeoutcheckproduct").val(outproducttable.row(outproductRow).data().productCode);
										 $("#baseconaoutcheckpro").val(outproducttable.row(outproductRow).data().baseCode+" "+outproducttable.row(outproductRow).data().baseName);
										 //$("#weightoutcheckpro").val(outproducttable.row(outproductRow).data().weight);
										 $("#bcheckgoodnanum").val(outproducttable.row(outproductRow).data().goodnn);
										 $("#entprozijidecodema").val(outproducttable.row(outproductRow).data().entProductCode);
										 
										
									}else if(setFlg==3){
									 var declProductDetailID= outproducttable.row(outproductRow).data().declProductDetailID;
									 changeProductSampling(declProductDetailID);
									}else if(setFlg==4){
										 var declprodetailid=outproducttable.row(outproductRow).data().declProductDetailID;
											
										  if(confirm("确定删除吗?")){
											  delDeclProduct(declprodetailid); 
										  }
									}else if(setFlg==5){
										 $(".overlay").show();
										 $("#additemcheckedDialog").show();  
									}else if(setFlg==6){
										var declprodetailid=outproducttable.row(outproductRow).data().declProductDetailID;
										 
										  var itemcode=checkitemtable.row(checkitemRow).data().itemCode;
										 
										  changeItemSampling(declprodetailid, itemcode);
									}else if(setFlg==7){
										   var ifSampling= checkitemtable.row(checkitemRow).data().ifSampling;
											if(ifSampling=='0'){
												alert("不抽检的不需要进行采信放行");
											}else {
												 $(".overlay").show();
												 $("#creditReleaseDialog").show(); 
												 var itemCode=checkitemtable.row(checkitemRow).data().itemCode;
												 var itemName=checkitemtable.row(checkitemRow).data().itemName;
												 $("#creditem").val(itemCode+" "+itemName);
											}
									}else if(setFlg==8){
										var declprodetailid=outproducttable.row(outproductRow).data().declProductDetailID;
										var itemCode=checkitemtable.row(checkitemRow).data().itemCode;
									    if(confirm("确定要取消吗?")){
									    	CancelReleaseMode(declprodetailid, itemCode);
									    }
										
										
									}
									 
								}else{
									alert(rdata.data);
								}
								
							}, 'json');
				}			
			
			
});
