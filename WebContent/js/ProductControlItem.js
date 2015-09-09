//@ sourceURL=ProductControlItem.js 
$(document).ready(function(){
	var curTable1Row = null;
	
	var table1Columns = [
	                     {"data": "productControlItemID"},
	                     {"data": "productCode"},
	                     {"data": "productName"},
	                     {"data": "countryName"},
	                     {"data": "itemName"},
	                     {"data": "detectionStd"},
	                     {"data": "monitoringReason"},
	                     {"data": "limitReq"},
	                     {"data": "riskPossibility",
	                    	 "render": function ( data, type, full, meta ) {
		                		  if(data=='1'){      
		                			  return '极少发生';
		                		  }else if(data=='2'){
		                			  return '较少发生';
		                		  }else if(data=='3'){
		                			  return '偶尔发生';
		                		  }else if(data=='4'){
		                			  return '经常发生';
		                		  }else if(data=='5'){
		                			  return '频繁发生';
		                		  }
	                    	    } },
	                     {"data": "seriousnessLevel",
	                    	    	"render": function ( data, type, full, meta )
	                    	    	{
			                		  if(data=='1'){      
			                			  return '无危害';
			                		  }else if(data=='2'){
			                			  return '轻度';
			                		  }else if(data=='3'){
			                			  return '中等';
			                		  }else if(data=='4'){
			                			  return '严重';
			                		  }else if(data=='5'){
			                			  return '灾难性';
			                		  }
                    	    } },
	                     {"data": "riskEvlDesc"}
	                     ];
	
	var table1 = $('#table1').DataTable( {
	      "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "ajax":{
			  url : "ProductCtrlItemAction_getProductControlItem",
			  type : "GET",
			  data : function(d){
				  d.data = getTable1RequestParam;
			  }
		  },
	      "columns": table1Columns,
	      "columnDefs": [
	                     {
	                         "targets": [ 0,5,6 ],
	                         "visible": false,
	                     }
	                 ]
	  } );
	
	var alertColumns = [
	                     {"data": "className"},
	                     {"data": "subclassName"},
	                     {"data": "materialName"},
	                     {"data": "sourceName"},
	                     {"data": "ifMainMaterial",
                    	  "render": function ( data, type, full, meta ) {
                    		  if(data == "1"){
                    			  return "是";
                    		  }else{
                    			  return "否";
                    		  }
                    	    }
	                     },
	                     ];
	
	
	var alertTable = $('#alertTable').DataTable( {
		  /*"scrollY": "100px", 
		  "scrollCollapse": true,*/
		  "info" : false,
		  "paging" : false,
		  "lengthChange" : false,
	      "deferRender": true,
		  "processing": true,
	      "columns": alertColumns,
	  } );
	
	$('#table1').on( 'draw.dt', drawTable1CB);
	 
	$('#table1 tbody').on("click", "tr", clickTable1Row );
	
	$("#search1").click(searchTable1Btn);
	
	$("#productBtn").click(getProductDetail);
	
	$("#itemBtn").click(getItemDetail);
	
	$("#searchTable1").click(searchTable1);
	
	$("#closeTable1Search").click(function(){
		clearAlertDiv("table1Search");
		$(".overlay").hide();
		$("#table1Search").hide();
	});
	
	$("#closeTableProduct").click(function(){
		clearAlertDiv("tableProduct");
		$(".overlay").hide();
		$("#tableProduct").hide();
		 alertTable.row().remove().draw();
	});

	$("#closeTableItem").click(function(){
		clearAlertDiv("tableItem");
		$(".overlay").hide();
		$("#tableItem").hide();
	});
	
	 function getTable1RequestParam(){
		  var data = {
				 productCode : $("#rProductCode").val().trim(),
				 productName : $("#rProductName").val().trim(),
				 countryName : $("#rCountryName").val().trim(),
				 itemName : $("#rItemName").val().trim(),
		  };
		  var jsonstr = JSON.stringify(data);
		  return jsonstr;
	  }
	
	function drawTable1CB() {
		 if(table1.rows().data().length > 0){
			  var dtRow = null;
			  var node = null;
			  if(curTable1Row != null){
				  dtRow = table1.row($(curTable1Row).context._DT_RowIndex);
				  node = dtRow.node();
				  if(node != null){
					  $(node).click();
					  return;
				  }
			  }
			  dtRow = table1.row(0);
			  node = dtRow.node();
			  $(node).click();
		  }else{
			  curTable1Row = null;
		  }
	 } 
	
	function clickTable1Row(){
		 var row = this;
		  if ( $(row).hasClass('active') ) {
			    curTable1Row = null;
	            $(row).removeClass('active');
	        }
	        else {
	        	curTable1Row = row;
	        	table1.$('tr.active').removeClass('active');
	            $(row).addClass('active');
	        }
	}
	
	 function searchTable1Btn(){
	 	 $(".overlay").show();
		 $("#table1Search").show();
	 } 
	 
     function searchTable1(){
		 table1.draw();
		 //$("#closeTable1Search").click();
		    $(".overlay").hide();         
			$("#table1Search").hide();
	 }
	  
	 function getProductDetail(){
		 if(curTable1Row == null){
			  alert("请先选择一条记录");
			  return;
		  }
		  $(".overlay").show();
	   	  $("#tableProduct").show();
	   	  var productCode = table1.row(curTable1Row).data().productCode;
		  $.get("ProductCtrlItemAction_getProductByCode?&ts="
					+ new Date().getTime(), {
						data : productCode
			}, function(rdata) {
				setProductDetail(rdata);
			}, 'json');
	 }
	 
	 function getItemDetail(){
		 if(curTable1Row == null){
			  alert("请先选择一条记录");
			  return;
		  }
		 $(".overlay").show();
	   	 $("#tableItem").show();
	   	 var productControlItemID = table1.row(curTable1Row).data().productControlItemID;
	   	 $.get("ProductCtrlItemAction_getProductControlItem?&ts="
					+ new Date().getTime(), {
						productControlItemID : productControlItemID
			}, function(rdata) {
				setItemDetail(rdata);
			}, 'json');
	 }
	 
	 
	 function setProductDetail(rdata){
		  var productDetail = rdata.productDetail;
		  var materialDetailList = rdata.materialDetail;
		  $("#productCode").val(productDetail.productCode);
		  $("#productName").val(productDetail.productName);
		  $("#className").val(productDetail.className);
		  $("#subclassName").val(productDetail.subclassName);
		  $("#methodName").val(productDetail.methodName);
		  $("#typeName").val(productDetail.typeName);
		  $("#useName").val(productDetail.useName);
		  alertTable.rows.add(materialDetailList).draw();
	  };
	 
	 
	 function setItemDetail(rdata){
		  $("#productCode2").val(rdata.productCode);
		  $("#productName2").val(rdata.productName);
		  $("#countryCode").val(rdata.countryCode);
		  $("#countryName").val(rdata.countryName);
		  $("#itemCode").val(rdata.itemCode);
		  $("#itemName").val(rdata.itemName);
		  $("#detectionStd").val(rdata.detectionStd);
		  $("#monitoringReason").val(rdata.monitoringReason);
		  var data=rdata.riskPossibility;
		  var possible=null;
		  if(data=='1'){      
			  possible ='极少发生';
		  }else if(data=='2'){
			  possible ='较少发生';
		  }else if(data=='3'){
			  possible ='偶尔发生';
		  }else if(data=='4'){
			  possible= '经常发生';
		  }else if(data=='5'){
			  possible ='频繁发生';
		  }
		  var sdata=rdata.seriousnessLevel;
		  var serious=null;
		  if(sdata=='1'){      
			  serious= '无危害';
		  }else if(sdata=='2'){
			  serious ='轻度';
		  }else if(sdata=='3'){
			  serious ='中等';
		  }else if(sdata=='4'){
			  serious ='严重';
		  }else if(sdata=='5'){
			  serious ='灾难性';
		  }
		  $("#riskPossibility").val(possible);
		  $("#seriousnessLevel").val(serious);
		  $("#riskEvlDesc").val(rdata.riskEvlDesc);
		  $("#limitType").val(rdata.limitType);
		  $("#detectionLimit").val(rdata.detectionLimit);
		  $("#limitUnit").val(rdata.limitUnit);
		  $("#controlDatetime").val(rdata.controlDatetime);
		  $("#convCtrlItemID").val(rdata.convCtrlItemID);
	  };
	 
	  
});