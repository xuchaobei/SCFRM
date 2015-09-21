//@ sourceURL=qualifyevalue.js  
$(document).ready(function (){

	var declNo='';
	var inspOrgCode='0040';//系统登录时检验机构代码
	var inspDeptCode ='';
	var inspOperatorCode='' ;
	var processName='合格性评判' ;   
	var finishFlg=1;//1 待检验2已完成
	var decldataRow=null;
	var outproductRow=null;
	var checkitemRow=null;
	var colIdx=null;
	var ypcolIdx=null;
	var declProductDetailID=null;
	var setflg=null;
	var allSaveFlg=null;
	var finflg=0;
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
	                    	       return data === '1'  ? '是' : '否';
	                    	    } 
		      	         }
		      	         ];
	var decldatatable = $('#decldataTb').DataTable( {
			  "deferRender": true,
			  "processing": true,
			  "serverSide": true,
			  "ajax":{
				  url : "ProcessReceiverAction_GetDeclInfoForProcessHg",
				  type : "GET",
				  data : function(d){
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
								  { "data": "baseCode"},
								  { "data": "baseName" },
								  { "data": "lowerSamplingRatio"},
							  	  { "data": "ifSampling" ,
									  "render": function ( data, type, full, meta ) {
									       return data === '1'  ? '是' : '否';
									    }},
								  //{ "data": "samplingReason" },
								 // { "data": "weight" },
									  
							      	     
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
		                         "targets": [ 5 ],
		                         "visible": false,
		                     },
		                     {
		                         "targets": [7], // 目标列位置，下标从0开始  
		                         "data": "totelstr", // 数据列名
		                         "render": function(data, type, full) { // 返回自定义内容
		                        	  var str=[];
		                        	  str= data.split("/");    
		                              if(str[1]=='1'){   
		                        		 return "<input type='checkbox'  checked id='1"+str[0]+"'>合格"+"<input type='checkbox'  id='2"+str[0]+"'>不合格"; 
		                        	 }else if(str[1]=='0' && str[3]=='0'){
		                        		 return "<input type='checkbox'  checked id='1"+str[0]+"'>合格"+"<input type='checkbox'  id='2"+str[0]+"'>不合格"; 
		                        	 }else if(str[3]=='1'){
		                        		 return "<input type='checkbox'  id='1"+str[0]+"' >合格"+"<input type='checkbox'  checked id='2"+str[0]+"'>不合格"; 
		                        	 }else{
		                        		 return "<input type='checkbox'  checked id='1"+str[0]+"'>合格"+"<input type='checkbox'  id='2"+str[0]+"'>不合格";
		                        	 }
		                            
		                        	 //return "<a href='/delete?name=" + data + "'>Delete</a>&nbsp;<a href='/update?name=" + data + "'>Update</a>";
		                         }
		                     },
		                     {                     
		                         "targets": [8], // 目标列位置，下标从0开始  
		                         "data": "totelstr", // 数据列名
		                         "render": function(data, type, full) { // 返回自定义内容
		                        	 
		                        	 var str=[];
		                        	 str= data.split("/");
		                        	
		                        	 if(str[2]=='0' || str[2]==''){
		                        		 return "<input type='checkbox'  id='3"+str[0]+"'>合格"+"<input type='checkbox'  id='4"+str[0]+"'>不合格"+"<input type='checkbox' name='radioflg' id='0"+str[0]+"'>不适用"; 
		                        	 }if(str[2]=='1' ){
		                        		 return "<input type='checkbox'   checked id='3"+str[0]+"'>合格"+"<input type='checkbox' name='radioflg' id='4"+str[0]+"'>不合格"+"<input type='checkbox'  id='0"+str[0]+"'>不适用"; 
		                        	 }else if(str[2]=='2') {
		                        		 return "<input type='checkbox'  id='3"+str[0]+"' >合格"+"<input type='checkbox' name='radioflg' checked id='4"+str[0]+"'>不合格"+"<input type='checkbox'  id='0"+str[0]+"'>不适用"; 
		                        	 }else if(str[2]=='3'){
		                        		 return "<input type='checkbox'   id='3"+str[0]+"'>合格"+"<input type='checkbox' name='radioflg' id='4"+str[0]+"'>不合格"+"<input type='checkbox' checked  id='0"+str[0]+"'>不适用";
		                        	 }
		                            
		                        	 //return "<a href='/delete?name=" + data + "'>Delete</a>&nbsp;<a href='/update?name=" + data + "'>Update</a>";
		                         }
		                     },
		                     {
		                         "targets": [9], // 目标列位置，下标从0开始  
		                         "data": "totelstr", // 数据列名
		                         "render": function(data, type, full) { // 返回自定义内容
		                        	  var str=[];
		                        	  str= data.split("/");
		                              if(str[1]=='0' && str[3]=='0'){
		                        		 return "N" ;
		                        	 }else{
		                        		 return "Y";
		                        	 }
		                            
		                        	 //return "<a href='/delete?name=" + data + "'>Delete</a>&nbsp;<a href='/update?name=" + data + "'>Update</a>";
		                         }
		                     },
		                     {
		                         "targets": [10], // 目标列位置，下标从0开始  
		                         "render": function(data, type, full) { // 返回自定义内容
		                        	
		                        	  return "<a href='#?name=" + full.declProductDetailID + "'>保存</a>";
		                             // return '<button class="btn btn-primary btn-sm" value="保存"  onclick="a()" >';
		                         }
		                     }
		                 ]
		  } );	
	
	//报验数据的获取
	var baoyandatacolum = [
		                  { "data": "applyNo" },
		                  { "data": "applyOrg" },
		      	          { "data": "applyDept" },
		      	          { "data": "appliant"},
		      	          { "data": "applyDate" },
		      	         { "data": "sampleName"},
		      	       /* { "data": "ifSampling"},
		      	          { "data": "samplingReason" },
		      	          { "data": "weight"},*/
		      	         
		                 ];
	var baoyandatatable = $('#baoyandataTb').DataTable( {
			  "deferRender": true,
			  "processing": true,
			  "serverSide": true,
			  "paging" : false,
				"info":false,
			  "ajax":{
				  url : "PassJundgeAction_GetLabApplyByDeclNo",
				  type : "GET",
				  data : function(d){
					 
					  if(decldataRow==null){
						  d.data=JSON.stringify({declNo :'9999999999999999'}); 
					  }else{
						  d.data=JSON.stringify({declNo : decldatatable.row(decldataRow).data().declNo});
					  }
					 
					
				  }
			  },
		      "columns": baoyandatacolum,  
		  /* "columnDefs": [
		                     {
		                         "targets": [ 0 ],
		                         "visible": false,
		                     }
		                 ]*/
		  } );	
	
	var baoyandataRow = null; 
	
	$('#baoyandataTb').on( 'draw.dt', drawBaoyandataTbCB);
	
	$('#baoyandataTb tbody').on("click", "tr", clickBaoyandataTbRow );
	
	function drawBaoyandataTbCB() {
		 if(baoyandatatable.rows().data().length > 0){
			  var dtRow = null;
			  var node = null;
			  if(baoyandataRow != null){
				  dtRow = baoyandatatable.row($(baoyandataRow).context._DT_RowIndex);
				  node = dtRow.node();
				  if(node != null){
					  $(node).click();
					  return;
				  }
			  }
			  dtRow = baoyandatatable.row(0);
			  node = dtRow.node();
			  $(node).click();
		  }else{
			  baoyandataRow = null;
		  }
	 } 
	
	function clickBaoyandataTbRow(){
		 var row = this;
		  if ( $(row).hasClass('active') ) {
			    baoyandataRow = null;
	            $(row).removeClass('active');
	        }
	        else {
	        	baoyandataRow = row;
	        	baoyandatatable.$('tr.active').removeClass('active');
	            $(row).addClass('active');
	        }
	}
	
	//样品项目数据的获取
	var checkitemcolum = [
	                      { "data": "labSampleItemID" },
		                  { "data": "applyNo" },
		                  { "data": "sampleNo" },
		      	          { "data": "labSampleID" },
		      	          { "data": "sampleName"},
		      	          { "data": "itemCode" },
		                  { "data": "itemName" },
		      	          { "data": "limitReq" },
		      	          { "data": "sendLabFlg",
		      	        	  "render": function ( data, type, full, meta ) {
		      	        		  return data === '1'  ? '是' : '否';
                  	             }},
                  	      { "data": "labInterface",
                  	            	 "render": function ( data, type, full, meta ) {
                  	            		 return data === '1'  ? '是' : '否';
           	             				}},       
                  	      { "data": "releaseMode"},
                  	      { "data": "testLabName"},
		      	         /* { "data": "labData"},
		      	          { "data": "labDataUnit" },*/
	                      /*{ "data": "ifQualified_Yes" ,
		      	        	"render": function ( data, type, full, meta ) {
	                    	       return data === '1'  ? '是' : '否';
	                    	    }},
		      	          { "data": "ifQualified_No" ,
		      	        	"render": function ( data, type, full, meta ) {
	                    	       return data === '1'  ? '是' : '否';
	                    	    }},	 
                	      { "data": "ifCancel" ,
		      	        	"render": function ( data, type, full, meta ) {
	                    	       return data === '1'  ? '是' : '否';
	                    	    }}	*/    
		                 ];
	var checkitemtable = $('#yangpingitemTb').DataTable( {
			  "deferRender": true,
			  "processing": true,
			  "serverSide": true,
			  "paging" : false,
				"info":false,
			  "ajax":{
				  url : "PassJundgeAction_GetLabSampleItemByProduct",
				  type : "GET",
				  data : function(d){
					  if(outproductRow==null){
						  d.data=JSON.stringify({declProductDetailID :'0'
						  });
					  }else{
						 
						  d.data=JSON.stringify({declProductDetailID : outproducttable.row(outproductRow).data().declProductDetailID
						  });
					  }
					 
					 
				  }
			  },
		      "columns": checkitemcolum,  
		      "columnDefs": [
							{
							    "targets": [0,5 ],
							    "visible": false,
							},
							{
		                         "targets": [12], // 目标列位置，下标从0开始  
		                         "data": "totelstr", // 数据列名
		                         "render": function(data, type, full) { // 返回自定义内容
		                        	  var str=[];
		                        	  str= data.split("@");
		                              if(str[4]=='1' && str[7]=='0'){
		                        		 //return "<input type='text' style='width:90%'  id='8"+str[0]+"'>"; 
		                            	  return "<input type='text' style='width:90%'    id='8"+str[0]+"'"+ "value='"+str[5]+ "'>";
		                        	 }else {
		                        		// return "<input type='text' style='width:60%'  disabled='disabled'  id='8"+str[0]+"'"+ "value='"+str[5]+ "'>"; 
		                        		 return str[5];
		                        		
		                        	 }
		                            
		                        	 //return "<a href='/delete?name=" + data + "'>Delete</a>&nbsp;<a href='/update?name=" + data + "'>Update</a>";
		                         }
		                     },	
		                     {
		                         "targets": [13], // 目标列位置，下标从0开始  
		                         "data": "totelstr", // 数据列名
		                         "render": function(data, type, full) { // 返回自定义内容
		                        	  var str=[];
		                        	  str= data.split("@");
		                              if(str[4]=='1' && str[7]=='0'){
		                            	  //return "<input type='text' style='width:90%'  id='9"+str[0]+"'>"; 
		                            	  return "<input type='text' style='width:90%'  id='9"+str[0]+"'"+ "value='"+str[6]+ "'>";
		                            	  
		                        	 }else {            
		                        		 //return "<input type='text' disabled='disabled'  id='9"+str[0]+"'"+ "value='"+str[5]+ "'>"; 
		                        		 return str[6];
		                        		
		                        	 }
		                            
		                        	 //return "<a href='/delete?name=" + data + "'>Delete</a>&nbsp;<a href='/update?name=" + data + "'>Update</a>";
		                         }
		                     },
		                  {
		                         "targets": [14], // 目标列位置，下标从0开始  
		                         "data": "totelstr", // 数据列名
		                         "render": function(data, type, full) { // 返回自定义内容
		                        	  var str=[];
		                        	  str= data.split("@");
		                              if(str[1]=='1'){
		                        		 return "<input type='checkbox' name='raidoflg' checked id='5"+str[0]+"'>合格"+"<input type='checkbox' name='radioflg' id='6"+str[0]+"'>不合格"; 
		                        	 }else if(str[1]=='0' && str[3]=='0'){
		                        		 return "<input type='checkbox' name='raidoflg' checked id='5"+str[0]+"'>合格"+"<input type='checkbox' name='radioflg' id='6"+str[0]+"'>不合格"; 
		                        	 }else if(str[3]=='1'){
		                        		 return "<input type='checkbox' name='raidoflg' id='5"+str[0]+"' >合格"+"<input type='checkbox' name='radioflg' checked id='6"+str[0]+"'>不合格"; 
		                        	 }else {
		                        		 return "<input type='checkbox' name='raidoflg' checked id='5"+str[0]+"'>合格"+"<input type='checkbox' name='radioflg' id='6"+str[0]+"'>不合格";
		                        	 }
		                            
		                        	 //return "<a href='/delete?name=" + data + "'>Delete</a>&nbsp;<a href='/update?name=" + data + "'>Update</a>";
		                         }
		                     },
		                     {
		                         "targets": [15], // 目标列位置，下标从0开始  
		                         "data": "totelstr", // 数据列名
		                         "render": function(data, type, full) { // 返回自定义内容
		                        	  var str=[];
		                        	  str= data.split("@");
		                              if(str[2]=='1'){
		                        		 return "<input type='checkbox' name='raidoflg' checked id='7"+str[0]+"'>作废"; 
		                        	 }else {
		                        		 return "<input type='checkbox' name='raidoflg' id='7"+str[0]+"' >作废"; 
		                        	 }
		                            
		                        	 //return "<a href='/delete?name=" + data + "'>Delete</a>&nbsp;<a href='/update?name=" + data + "'>Update</a>";
		                         }
		                     },
		                     {
		                         "targets": [16], // 目标列位置，下标从0开始  
		                         "data": "totelstr", // 数据列名
		                         "render": function(data, type, full) { // 返回自定义内容
		                        	  var str=[];
		                        	  str= data.split("@");
		                              if(str[1]=='0' && str[3]=='0'){
		                        		 return "N" ;
		                        	 }else {
		                        		 return "Y";
		                        	 }
		                            
		                        	 //return "<a href='/delete?name=" + data + "'>Delete</a>&nbsp;<a href='/update?name=" + data + "'>Update</a>";
		                         }
		                     }
		                 ]
		  } );	
	
	


	
	
//主界面查询按钮
	  $("#querydecldata").click(function(){
		 $(".overlay").show();
		 $("#querydeclsinglepriveDialog").show();  
		 $("#secledted").val('待评定');
	  });
//查询对话框中取消按钮
		 $("#closedeclsinglepriveDialogBtn").click(function(){
			 $(".overlay").hide();
			 $("#querydeclsinglepriveDialog").hide(); 
			 clearAlertDiv("querydeclsinglepriveDialog");
			 $("#secledted").val('待评定');
			   
		  });	
	
//查询功能
			$('#querydeclsinglepriveBtn').click(function(){
				           declNo=$("#declnodeclsingleprive").val().trim();
				           var status=$("#secledted").val().trim();
				           if(status=='待评定'){finishFlg=1;}
				           else if(status=='已评定'){finishFlg=2;};
				           decldatatable.draw();
				           $("#closedeclsinglepriveDialogBtn").click();
						 
			});		
//--------------------------------------------------------------------查询功能完毕--------------------	
//评定确定
			$('#givedecldata').click(function(){
				 getoperatelimit('合格性评定',1);
			});		
//评定撤销
			$('#rebackdecldata').click(function(){
				 if(decldataRow==null){alert("选中一条报检数据");return;}	
				if(confirm("确定要撤销此评定吗")){
					var decn=decldatatable.row(decldataRow).data().declNo;
					CancelCurrentProcess(decn,processName, inspOrgCode, inspDeptCode, inspOperatorCode);
				}      
			});				
			
//转单确认中的检验员			
$("#multoperate-select").click(function(){
				initOperName("multoperate");
				});
			function initOperName(inputID){  
				
				 var org=getSelectValue("multinsporg");
				 var dept=getSelectValue("multdept");
				
				data={ 
						
						
						inspOrgCode :org,
						inspDeptCode :dept
						
						
				}; 
				 var jsonstr = JSON.stringify(data);
				$.get("PassJundgeAction_GetInspOperatorByOrgDept?&ts="
				+ new Date().getTime(),{
					data:jsonstr
				},
				function(rdata) {
					var source = new Array();
					$.each(rdata.data, function(index, value){
						source[index] = value.operatorCode+" "+value.operatorName;
					});	
					if(source.length == 0){             
						alert("查询结果为空！");
						return;
					}
					cus_autocomplete(source, inputID, null, null, null);      
					$("#"+inputID).autocomplete( "search", "" );         
				}, 'json');
		   }			
			
//转单确认
			$('#suretrans').click(function(){
				 var decl=$("#multdecl").val().trim();
				 var org=getSelectValue("multinsporg");
				 var dept=getSelectValue("multdept");
				 var operate=getSelectValue("multoperate");
				 if(decl=='' ||org=='' || dept=='' || operate==''){alert("信息填写完整");return;}
				if(confirm("确定要转单吗")){
					var decn=decldatatable.row(decldataRow).data().declNo;
					TransDeclForMultiDept(decn, org, dept, operate);
				}      
				
						 
			});			
		
			  function TransDeclForMultiDept(declno,inspOrgCode,inspDeptCode,operatorCode){
					data={ 
							
							declNo :declno,
							inspOrgCode :inspOrgCode,
							inspDeptCode :inspDeptCode,
							operatorCode :operatorCode
							
					}; 
					 var jsonstr = JSON.stringify(data);
					$.post("PassJundgeAction_TransDeclForMultiDept?&ts="   
							+ new Date().getTime(), {
								 data : jsonstr
							}, function(rdata) {
								if(rdata.data=="true"){
									alert("转单成功");
									decldatatable.draw();
									$('#closemultqDialog').click();
								}else{
									alert(rdata.data);}
								
							}, 'json');
				}
//多部门转单取消
				$('#closemultqDialog').click(function(){
					
					$(".overlay").hide(); 
					   $("#multdeptstransDialog").hide();
					   clearAlertDiv("multdeptstransDialog");
				});			  
//出口产品数据保存按钮
				/*$('#outproductdataTb tbody').on('click','tr',function(){
					
					var name = $('td', this).eq(9);
					alert("helegf"+name);
				});*/
			  $("#addoutproductdata").click(function(){
				 
				  if(outproductRow==null){alert("选中一条出口产品数据");return;}	
					var declProductDetailID= outproducttable.row(outproductRow).data().declProductDetailID;
				
					var iffiq="1"+declProductDetailID;//现场检验合格
					var iffiqnot="2"+declProductDetailID;//现场检验不合格
					var ifq="3"+declProductDetailID;//标签合格
					var ifqnot="4"+declProductDetailID;//标签不合格
                    var labeling="0"+declProductDetailID;//标签合格不使用
					var filinq=null;
					var quali=null;
					if($("#"+iffiq).prop("checked") && $("#"+iffiqnot).prop("checked")){
						alert("现场查验合格和不合格只能勾选1个");
						return;
						
					}   
					if((!$("#"+iffiq).prop("checked")) && (!$("#"+iffiqnot).prop("checked"))){
						alert("现场查验合格和不合格必须勾选1个");
						return;
						
					}
					if($("#"+ifq).prop("checked") && $("#"+ifqnot).prop("checked") && $("#"+labeling).prop("checked")){
						alert("标签合格 、不合格、不使用只能勾选1个");
						return;
						
					}       
					if((!$("#"+ifq).prop("checked")) && (!$("#"+ifqnot).prop("checked")) && (!$("#"+labeling).prop("checked"))){
						alert("标签合格 、不合格、不使用必须勾选1个");
						return;   
						                 
					}
					if($("#"+ifq).prop("checked") && $("#"+ifqnot).prop("checked")  ){
						alert("标签合格 、不合格、不使用只能勾选1个");
						return;
						
					}
					if($("#"+ifq).prop("checked") && $("#"+labeling).prop("checked")  ){
						alert("标签合格 、不合格、不使用只能勾选1个");
						return;
						
					}
					if($("#"+labeling).prop("checked") && $("#"+ifqnot).prop("checked")  ){
						alert("标签合格 、不合格、不使用只能勾选1个");
						return;
						
					}
					if($("#"+iffiq).prop("checked")){
					
						filinq=1;
					}else{
						
						filinq=0;
					}
					if($("#"+ifq).prop("checked")){
					
						quali=1;
					}else if($("#"+ifqnot).prop("checked")){
						
						quali=2;
					}else if($("#"+labeling).prop("checked")){
						quali=3;
					}
					setflg=1;
					var decn=decldatatable.row(decldataRow).data().declNo;
					//alert(outproducttable.row(outproductRow).data());
					
					CheckInspDeptForDeclProduct(decn, declProductDetailID, filinq, quali,'');
					
			  });
			  function CheckInspDeptForDeclProduct(decn,declProductDetailID,filinq,quali,str){
				  var id=outproducttable.row(outproductRow).data().declProductDetailID;
					data={ 
							
							declProductDetailID :id
							
					}; 
					 var jsonstr = JSON.stringify(data);
					$.post("PassJundgeAction_CheckInspDeptForDeclProduct?&ts="   
							+ new Date().getTime(), {
								 data : jsonstr
							}, function(rdata) {
								if(rdata.data=="true"){
									if(setflg==1){
										CheckQualifySaveBefore(decn, declProductDetailID, filinq, quali,'');
									}else if(setflg==2){
										CheckQualifySaveBefore(decn, '', '', '','');
									}else if(setflg==3){
										CheckQualifySaveBefore(decn, '', '', '',str);
									}
									
								}else{
									alert(rdata.data);
								}
								
							}, 'json');
				}
			  function CheckQualifySaveBefore(declno,declProductDetailID,filinq,quali,str){
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
									if(setflg==1){
										SaveDeclProductQualify(declProductDetailID, filinq, quali);
									}else if(setflg==2){
										var labSampleItemID= checkitemtable.row(checkitemRow).data().labSampleItemID;
										var labSampleID= checkitemtable.row(checkitemRow).data().labSampleID;
										var itemCode= checkitemtable.row(checkitemRow).data().itemCode;
										var sendLabFlg= checkitemtable.row(checkitemRow).data().sendLabFlg;
										var labInterface= checkitemtable.row(checkitemRow).data().labInterface;
										var labData='';
										var labDataUnit='';
										var dataid=null;
										var dataunitid=null;
										if(sendLabFlg=='1' && labInterface=='0' ){
											 dataid="8"+labSampleItemID;
											 dataunitid="9"+labSampleItemID;
											labData=$("#"+dataid).val().trim();
											labDataUnit=$("#"+dataunitid).val().trim();}
										else{
											
											labData= checkitemtable.row(checkitemRow).data().labData;
											 labDataUnit= checkitemtable.row(checkitemRow).data().labDataUnit;
										
										}
										
										
										
										var ifq="5"+labSampleItemID;
										var ifc="7"+labSampleItemID;
										var ifqnot="6"+labSampleItemID;
										var quality=null;
										var qualitynot=null;
										var cancel=null;
										
									//alert("labData "+labData+" labDataUnit"+labDataUnit);    
										if($("#"+ifq).prop("checked")){
											
											quality=1;
										}else{
										
											quality=0;
										}
										if($("#"+ifqnot).prop("checked")){
										
											qualitynot=1;
										}else{
										
											qualitynot=0;
										}
										if($("#"+ifc).prop("checked")){
											
											cancel=1;
											SaveLabSampleItemQualify(labSampleID, itemCode, 0, 1, 1, labData, labDataUnit);
										}else{
											
											cancel=0;
											if(labData=='' && sendLabFlg=='1' && labInterface=='0' ){alert("没有填写实验室结果");return;}
											if(labData==''  ){alert("实验室检测结果还没有返回，不能判定合格性");return;}
											if(quality==1 && qualitynot==1){
												alert("合格和不合格只能勾选1个");  
												return;
												
											}
										
											if(quality==0 && qualitynot==0){
												alert("没有进行合格性判定");
												return;
												
											}
											if(sendLabFlg=='1' && labInterface=='0' )
											    {
												  SaveLabSampleItemQualify(labSampleID, itemCode, quality, 0, 1, labData, labDataUnit);
													 
											        }else{
											    	
											      SaveLabSampleItemQualify(labSampleID, itemCode, quality, 0, 0, labData, labDataUnit);
												 }
										
										}
	       									
										
									}else if(setflg==3){
										if(confirm("确定要全保存吗?")){
											//alert("yes"+str);
											AllSaveLabSampleItemQualify(str);
											
										}
									}
									 
								}else{
									alert(rdata.data);
								}
								
							}, 'json');
				}
			  function CheckQualifyJudgeBefore(declno){
					data={ 
							
							declNo :declno
							
					}; 
					 var jsonstr = JSON.stringify(data);
					$.post("PassJundgeAction_CheckQualifyJudgeBefore?&ts="   
							+ new Date().getTime(), {
								 data : jsonstr
							}, function(rdata) {           
								
								var data=rdata.data;                    
								 
								  
								var dt=[];
								   dt=data.split("@");
								  
								   var strA=dt[0];
								   var inspOrg=dt[1];
								   var inspDept=dt[2];        
								  
								   if(strA==''){
									  
									   CheckifUnqualify(declno);
								   }else if(strA !='' && inspOrg ==''){
									   alert(strA);
								   }else if(strA !='' && inspOrg !=''){
									   
									   if(confirm(strA)){           
										   $(".overlay").show(); 
										   $("#multdeptstransDialog").show();
										   var id=decldatatable.row(decldataRow).data().declNo;
										   $("#multdecl").val(id);
										   $("#multinsporg").val(inspOrg);
										   $("#multdept").val(inspDept);
									   }
									   
								   }
								/*if(rdata.data=="true"){
									CheckifUnqualify(declno);
								}else{
									alert(rdata.data);
								}*/
								
							}, 'json');
				}
			  function CheckifUnqualify(declno){
					data={ 
							
							declNo :declno
							
					}; 
					 var jsonstr = JSON.stringify(data);
					$.post("PassJundgeAction_CheckifUnqualify?&ts="   
							+ new Date().getTime(), {
								 data : jsonstr
							}, function(rdata) {
								//alert("if hege"+rdata.data);
								if(rdata.data=="true"){
									var decn=decldatatable.row(decldataRow).data().declNo;
									QualifyJudge(decn, inspOrgCode, inspDeptCode, inspOperatorCode);
									
									
								}else{
									var fordata=rdata.data;
									if(confirm(fordata)){
										var decn=decldatatable.row(decldataRow).data().declNo;
										QualifyJudge(decn, inspOrgCode, inspDeptCode, inspOperatorCode);
									}
									//alert(rdata.data);
								}
								
							}, 'json');
				}
//样品项目数据
			  $("#addxiangmuyanpdata").click(function(){
				 
				  if(checkitemRow==null){alert("选中一条样品项目数据");return;}	
				  setflg=2;
				 var decn=decldatatable.row(decldataRow).data().declNo;
					//alert(outproducttable.row(outproductRow).data());
					//CheckQualifySaveBefore(decn, '', '', '');
				 CheckInspDeptForDeclProduct(decn, '', '', '','');
			  });		
			//allsaveone
			/*  $("#allsaveone").click(function(){            
				 
				 
				 var k= outproducttable.rows().data().length;
				
				  if(k>0){
					  outproducttable.$('tr.active').removeClass('active');
					 
					  for ( var int = 0; int < k; int++) {
							 $(outproducttable.row(int).node()).click();
							 $("#addoutproductdata").click(); 
							 
						}
				  }else{
					  alert("无数据");
				  }
				
				
				
			  });*/
			
			
//allsavetwo
			  $("#allsaveotwo").click(function(){
				  
				  checkitemtable.$('tr.active').removeClass('active');
				  checkitemRow=null;
				  var len=checkitemtable.rows().data().length;
				 // alert("len "+len);
				  var str=[];
				  for ( var i = 0; i < len; i++) {
					  dtRow = checkitemtable.row(i);
					  node = dtRow.node();
					  $(node).click(); 
					   var labSampleItemID= checkitemtable.row(checkitemRow).data().labSampleItemID;
						var labSampleID= checkitemtable.row(checkitemRow).data().labSampleID;
						var itemCode= checkitemtable.row(checkitemRow).data().itemCode;
						var sendLabFlg= checkitemtable.row(checkitemRow).data().sendLabFlg;
						var labInterface= checkitemtable.row(checkitemRow).data().labInterface;
						
						var labData='';
						var labDataUnit='';
						var dataid=null;
						var dataunitid=null;
						if(sendLabFlg=='1' && labInterface=='0'){
							 dataid="8"+labSampleItemID;
							 dataunitid="9"+labSampleItemID;
							labData=$("#"+dataid).val().trim();
							labDataUnit=$("#"+dataunitid).val().trim();
						}else{
							labData= checkitemtable.row(checkitemRow).data().labData;
							labDataUnit= checkitemtable.row(checkitemRow).data().labDataUnit;
					
							
						}
						var ifq="5"+labSampleItemID;
						var ifc="7"+labSampleItemID;
						var ifqnot="6"+labSampleItemID;
						var quality=null;
						var qualitynot=null;
						var cancel=null;
						
					
						if($("#"+ifq).prop("checked")){
							
							quality=1;
						}else{
						
							quality=0;
						}
						if($("#"+ifqnot).prop("checked")){
						
							qualitynot=1;
						}else{
						
							qualitynot=0;
						}
						if($("#"+ifc).prop("checked")){
							
							cancel=1;
							//SaveLabSampleItemQualify(labSampleID, itemCode, 0, 1, 1, labData, labDataUnit);
						str=labSampleID+","+itemCode+","+0+","+1+","+1+","+labData+","+labDataUnit+"@"+str;
						}else{
							
							cancel=0;
							if(labData=='' && sendLabFlg=='1' && labInterface=='0' ){alert("没有填写实验室结果");return;}
							if(labData==''  ){alert("实验室检测结果还没有返回，不能判定合格性");return;}
							if(quality==1 && qualitynot==1){
								alert("合格和不合格只能勾选1个");  
								return;
								
							}
						
							if(quality==0 && qualitynot==0){
								alert("没有进行合格性判定");
								return;
								
							}
							if(sendLabFlg=='1' && labInterface=='0')
							    {
								str=labSampleID+","+itemCode+","+quality+","+0+","+1+","+labData+","+labDataUnit+"@"+str;
								    //SaveLabSampleItemQualify(labSampleID, itemCode, quality, 0, 0, labData, labDataUnit);
							    }else{
							    	str=labSampleID+","+itemCode+","+quality+","+0+","+0+","+labData+","+labDataUnit+"@"+str;
									   
							    
								   //SaveLabSampleItemQualify(labSampleID, itemCode, quality, 0, 1, labData, labDataUnit);
								  }
						
						}
						
						//alert("labdata "+labData);
					
				}
				 // alert("输入合格"+str);
				  setflg=3;
				  var decn=decldatatable.row(decldataRow).data().declNo;
			
				  CheckInspDeptForDeclProduct(decn, '', '', '',str);
					
				
			  });                          
			
			
			
			
			

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
					  baoyandatatable.draw();
		         }
				  //subsubclassRow=null;
				  
			  } );	

/*	//初始化出口产品数据第一行默认选中
			$('#outproductdataTb').on( 'draw.dt', function () {
				  if(outproducttable.rows().data().length > 0){
					  var dtRow = outproducttable.row(0);
					  $(dtRow.node()).click();
				  }else{
					 
					  outproductRow=null;
					  checkitemtable.draw();  
					 
					};
				
				  
			  } );	*/
			
//初始化出口产品数据第一行默认选中
			$('#outproductdataTb').on( 'draw.dt', function () {
				  if(outproducttable.rows().data().length > 0){
					  var dtRow = null;
					  var node = null;
					/*  for ( var int = 0; int < outproducttable.rows().data().length; int++) {
						  dtRow = outproducttable.row(int);
						  node = dtRow.node();
						  $(node).click();    
						  
					}*/
					  if(outproductRow != null){
						  var k=$(outproductRow).context._DT_RowIndex;
						  
							  dtRow = outproducttable.row(k);
							  node = dtRow.node();
						
							  if(node != null){
							  $(node).click();
							 /* $(node).click();
							  $(node).click();*/
							  return;
							  } 
						  
					  
					  }
					  else{
						  
							     dtRow = outproducttable.row(0);
								  node = dtRow.node();
								  $(node).click(); 
							
					  }
					
					    
		         }else{
		        	 outproductRow=null;
		        	 checkitemtable.draw();  
		         }
				  //subsubclassRow=null;   
				  
			  } );	
			
			//$( outproducttable.column( 5 ).nodes() ).addClass( 'active' );      
/*	//初始化样品数据第一行默认选中
			$('#yangpingitemTb').on( 'draw.dt', function () {
				  if(checkitemtable.rows().data().length > 0){
					  var dtRow = checkitemtable.row(0);
					  $(dtRow.node()).click();
				  }else{
					 
					  checkitemRow=null;
					};
				
				  
			  } );	*/
			
	//初始化样品数据第一行默认选
			$('#yangpingitemTb').on( 'draw.dt', function () {
				  if(checkitemtable.rows().data().length > 0){
					  var dtRow = null;
					  var node = null;
					 if(checkitemRow != null){
						
						  var k=$(checkitemRow).context._DT_RowIndex;
						  
							  dtRow = checkitemtable.row(k);     
							  
							  node = dtRow.node();   
							
							  if(node != null){
							  $(node).click();     
							  return;
							  }  
						  
					      
					  }else{    
						  
						  	  dtRow = checkitemtable.row(0);
							  node = dtRow.node();
							  $(node).click();
						   
					  }
					
					  
					  
		         }else{
		        	 checkitemRow=null;
		        	
		         }
				  //subsubclassRow=null;
				       
			  } );			
			 $('#decldataTb tbody').on("click", "tr", clickclassRow );
			$("#outproductdataTb tbody").on("click","td", function(){
				
				colIdx = outproducttable.cell(this).index().column;  
				 } );
			$("#yangpingitemTb tbody").on("click","td", function(){
				
				ypcolIdx = checkitemtable.cell(this).index().column; 
				 } );
			 $('#outproductdataTb tbody').on("click", "tr", clicksubclassRow );	
			 $('#yangpingitemTb tbody').on("click", "tr", clicksubsubclassRow );
			 
				
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
					  baoyandatatable.draw();
					 // outproductRow = null;  
				  } 
				//选中出口产品数表中的一行
				function clicksubclassRow(){ 
					 var row = this;
					
					  if(colIdx !=7 && colIdx !=8 && colIdx!=9 && colIdx !=10){
						  
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
						  
					  }else{
						  
						    outproductRow = row;
				        	outproducttable.$('tr.active').removeClass('active');
				            $(row).addClass('active');
				            checkitemtable.draw(); 
				            if(colIdx==10){
				            	saveckprodata();
				            	colIdx=null;
				            	
				            }
				       }
				 } 
				
					
					
				 
			 
				function clicksubsubclassRow(){  
					  var row = this;
					  if(ypcolIdx !=12 && ypcolIdx!=15 && ypcolIdx !=13 && ypcolIdx!=14){
						  
						   if ( $(row).hasClass('active') ) {  
							    checkitemRow = null;
					            $(row).removeClass('active');
					        }
					        else {
					        	checkitemRow = row;
					        	checkitemtable.$('tr.active').removeClass('active');
					            $(row).addClass('active');
					        }  
			   			  
					  }else{
						    checkitemRow = row;
				        	checkitemtable.$('tr.active').removeClass('active');
				            $(row).addClass('active');
				           
					  }
			 }
			 
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
			
			
			
			function  GetLabApplyByDeclNo(declNo){
				data={ 
						declNo:declNo
						
				}; 
				 var jsonstr = JSON.stringify(data);
				$.post("PassJundgeAction_GetLabApplyByDeclNo?&ts="   
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
	function  GetLabSampleItemByProduct(declNo){
		data={ 
				declNo:declNo
				
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("PassJundgeAction_GetLabSampleItemByProduct?&ts="   
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
	function  SaveDeclProductQualify(declProductDetailID,ifFieldInspQualified,ifQualified){
		data={ 
				declProductDetailID:declProductDetailID,
				ifFieldInspQualified : ifFieldInspQualified,   
				labellingQualify : ifQualified   
				
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("PassJundgeAction_SaveDeclProductQualify?&ts="   
				+ new Date().getTime(), {
					 data : jsonstr
				}, function(rdata) {
					if(rdata.data=="true"){
						colIdx=null;
						outproducttable.draw();
						 
					}else{
						alert(rdata.data);
					}
					
				}, 'json');
	}
	function  SaveLabSampleItemQualify(labSampleID,itemCode,ifQualified,ifCancel,operateFlg,labData,labDataUnit){
		data={ 
				labSampleID:labSampleID,
				itemCode : itemCode,
				ifQualified : ifQualified,
				ifCancel:ifCancel,
				operateFlg :operateFlg,
				labData :labData,
				labDataUnit :labDataUnit
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("PassJundgeAction_SaveLabSampleItemQualify?&ts="   
				+ new Date().getTime(), {
					 data : jsonstr
				}, function(rdata) {
					if(rdata.data=="true"){            
						
						checkitemtable.draw();          
						
					}else{
						alert(rdata.data);
					}
					
				}, 'json');
	}
	function  AllSaveLabSampleItemQualify(str){
		data={ 
				str:str
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("PassJundgeAction_AllSaveLabSampleItemQualify?&ts="   
				+ new Date().getTime(), {          
					 data : jsonstr
				}, function(rdata) {
					if(rdata.data=="true"){                    
						//alert("保存成功");
						checkitemtable.draw();          
						
					}else{
						alert(rdata.data);
					}
					
				}, 'json');
	}
	function  QualifyJudge(declNo,processOrgCode,processDeptCode,processOperatorCode){
		data={ 
				declNo:declNo,
				processOrgCode	 : processOrgCode,
				processDeptCode : processDeptCode,
				processOperatorCode:processOperatorCode
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("PassJundgeAction_QualifyJudge?&ts="   
				+ new Date().getTime(), {
					 data : jsonstr
				}, function(rdata) {
					if(rdata.data[0].one=="true"){
						alert("操作成功");
						var flg=rdata.data[0].two;
						
						getCEMSSeqNum(declNo, flg);
						 
					}else{
						alert(rdata.data[0].one);
					}
					
				}, 'json');
	}
	
	function  CancelCurrentProcess(declNo,processName,processOrgCode,processDeptCode,processOperatorCode){
		data={ 
				declNo:declNo,
				processName :processName,
				processOrgCode : processOrgCode,
				processDeptCode : processDeptCode,
				processOperatorCode:processOperatorCode
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("PassJundgeAction_CancelCurrentProcess?&ts="   
				+ new Date().getTime(), {
					 data : jsonstr
				}, function(rdata) {
					if(rdata.data=="true"){
						alert("评定撤销操作成功");
						
						 decldatatable.row(decldataRow).remove().draw(false);
					}else{
						alert(rdata.data);
					}
					
				}, 'json');
	}
	
	  function    getCEMSSeqNum(declN,qualiyflg){
			data={ 
					declNo:declN
					
			}; 
			 var jsonstr = JSON.stringify(data);
			$.post("ProcessReceiverAction_getCEMSSeqNum?&ts="   
					+ new Date().getTime(), {
						 data : jsonstr
					}, function(rdata) {
					
						var key=rdata.data;   
						
						if(qualiyflg=='1'){
						saveReturnFlg(declN, "f");
						updateControlReturnFlgNew(key, '2', '3');
						decldatatable.draw();
						}else if(qualiyflg=='2'){
							CheckPrintCert(declN,key);
						}
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
		function CheckPrintCert(decl,key){
			data={ 
					declNo:decl
			}; 
			 var jsonstr = JSON.stringify(data);
			$.post("PassJundgeAction_CheckIfPrintCert?&ts="   
					+ new Date().getTime(), {
						 data : jsonstr
					}, function(rdata) {
						
						saveReturnFlg(decl, "e");
						if(rdata.data=='0'){
							
							updateControlReturnFlgNew(key, '2', '1');
						}else if(rdata.data=='1'){
							
							updateControlReturnFlgNew(key, '2', '2');
						}
						decldatatable.draw();      
					
					}, 'json');
		}
		
		//主界面接单撤销按钮	   
		 $("#receiverconcledecldata").click(function(){   
			 
			 getoperatelimit('检验员接单',2);
				     
				   
					  
			 });		 
		 function     cancelCurrentProcess(declNo,processName){
				data={ 
						declNo:declNo,
						processName :processName       
						
				}; 
				 var jsonstr = JSON.stringify(data);
				$.post("ProcessReceiverAction_CancelCurrentProcess?&ts="   
						+ new Date().getTime(), {
							 data : jsonstr
						}, function(rdata) {
							if(rdata.data=="true"){
								alert("操作成功");
								 /*inspDeptCode ='';
								 inspOperatorCode='' ;
								 processName='检验员接单';
								 finishFlg=1;*/
								decldatatable.draw();
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
									
									if(confirm("确定要进行评定吗?")){
										var decn=decldatatable.row(decldataRow).data().declNo;
										CheckQualifyJudgeBefore(decn);        
									}
								
							}else if(useflg==2){
								if(decldataRow==null){alert("选中一条报检数据");return;}
								  var declN=decldatatable.row(decldataRow).data().declNo;
								  if(confirm("确定要撤销吗?")){
									  cancelCurrentProcess(declN, '检验员接单');
								  }
								
							}
							
							 
						}else{
							alert(rdata.data);
						}
					}, 'json');
				
			}
	
			function saveckprodata(){
			

				 
				  if(outproductRow==null){alert("选中一条出口产品数据");return;}	
					var declProductDetailID= outproducttable.row(outproductRow).data().declProductDetailID;
				
					var iffiq="1"+declProductDetailID;//现场检验合格
					var iffiqnot="2"+declProductDetailID;//现场检验不合格
					var ifq="3"+declProductDetailID;//标签合格
					var ifqnot="4"+declProductDetailID;//标签不合格
		          var labeling="0"+declProductDetailID;//标签合格不使用
					var filinq=null;
					var quali=null;
					if($("#"+iffiq).prop("checked") && $("#"+iffiqnot).prop("checked")){
						alert("现场查验合格和不合格只能勾选1个");
						return;
						
					}   
					if((!$("#"+iffiq).prop("checked")) && (!$("#"+iffiqnot).prop("checked"))){
						alert("现场查验合格和不合格必须勾选1个");
						return;
						
					}
					if($("#"+ifq).prop("checked") && $("#"+ifqnot).prop("checked") && $("#"+labeling).prop("checked")){
						alert("标签合格 、不合格、不使用只能勾选1个");
						return;
						
					}       
					if((!$("#"+ifq).prop("checked")) && (!$("#"+ifqnot).prop("checked")) && (!$("#"+labeling).prop("checked"))){
						alert("标签合格 、不合格、不使用必须勾选1个");
						return;   
						                 
					}
					if($("#"+ifq).prop("checked") && $("#"+ifqnot).prop("checked")  ){
						alert("标签合格 、不合格、不使用只能勾选1个");
						return;
						
					}
					if($("#"+ifq).prop("checked") && $("#"+labeling).prop("checked")  ){
						alert("标签合格 、不合格、不使用只能勾选1个");
						return;
						
					}
					if($("#"+labeling).prop("checked") && $("#"+ifqnot).prop("checked")  ){
						alert("标签合格 、不合格、不使用只能勾选1个");
						return;
						
					}
					if($("#"+iffiq).prop("checked")){
					
						filinq=1;
					}else{
						
						filinq=0;
					}
					if($("#"+ifq).prop("checked")){
					
						quali=1;
					}else if($("#"+ifqnot).prop("checked")){
						
						quali=2;
					}else if($("#"+labeling).prop("checked")){
						quali=3;
					}
					setflg=1;
					var decn=decldatatable.row(decldataRow).data().declNo;
					//alert(outproducttable.row(outproductRow).data());
					
					CheckInspDeptForDeclProduct(decn, declProductDetailID, filinq, quali,'');
					
			  
			}
	
	//打印标签
	$("#receivesuredecldata").click(function(){
		if(baoyandataRow==null){
			alert("请选择要打印的LMIS报验号");
			return;
		}
		var applyNo = baoyandatatable.row(baoyandataRow).data().applyNo;
		var answer = confirm("确定要打印标签吗？");
		if(answer){
		$.post( 
					"ProcessReceiverAction_generateLabellingForPrn?&ts="+new Date().getTime(),
					{data : applyNo},
					function(rdata) {
						var data = rdata.data;
						for(i=0;i<data.length;i++){
							var txtValue = { 'Title': '', 'Name': '', 'PrintFull': [] };	       
			                txtValue.Title = "3+3G监管系统";
			                txtValue.Name = data[i].label;
				            var objs = { 'Code': '', 'PrintPar': [] };
				            objs.Code = data[i].code;
				            var obj = { 'Parm': '', 'Value': '' };
				            var source=data[i].labelData;
				            for(var e in source){			            	
				            	obj.Parm = e;
				            	obj.Value = source[e];
				            	objs.PrintPar.push(obj);
				            }		            
				            txtValue.PrintFull.push(objs);
				            var tgame = document.getElementById("Pr");
				            //var printstr = "{\"Title\":\"杭州黄金珠宝检测中心\",\"Name\":\"样品编号：\",\"PrintFull\":[{\"Code\":\"GGC11100700130\",\"PrintPar\":[{\"Parm\":\"样品类型名称\",\"Value\":\"镶嵌宝石\"}]}]}";
				            //alert($.toJSON(txtValue));
				            tgame.PrinS = $.toJSON(txtValue);
				            tgame.ShowMessage();
						}
					}, 
					'json');
		}
	})
	
	//读取检测报告
	$("#readTestResult").bind('click', function(){	
		if(decldataRow==null){
			alert("请选择要打印检测报告的报检记录");
			return;
		}
		var answer = confirm("确定要读取报告吗？");
		if(answer){
			for(var i = 0; i < baoyandatatable.rows().data().length; i++){
				var applyNo = baoyandatatable.row(i).data().applyNo;
				//需要替换成正确的ip地址
				$("#testReport a").attr("href","http://10.38.0.95:85/shared/file.gsp?sid=missing&fileid="+applyNo);
				$("#testReport a")[0].click();	
			}
		}
	})
		
		
});