//@ sourceURL=TestResultSearch.js 
$(document).ready(function(){
	var reqData = null;

	var table1Columns = [
	                     {"data": "declNo"},
	                     {"data": "decldate"},
	                     {"data": "countryName"},
	                     {"data": "entName"},
	                     {"data": "productCode"},
	                     {"data": "productName"},
	                     {"data": "itemName"},
	                     {"data": "labDataUnit"},
	                     {"data": "limitReq"},
	                     {"data": "ifQualified_Yes",
                    	 "render": function ( data, type, full, meta ) {
                    		 if(data == 1){
                    			 return "合格";
                    		 }else{
                    			 return "不合格";
                    		 }
                    	    }
	                     },
	                     ];
	
	
	var table1 = $('#table1').DataTable( {
		  "deferLoading":0,
	      "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "ajax":{
			  url : "TestResultSearchAction_getTestResultByQuery",
			  type : "GET",
			  data : function(d){
				  d.data = getTable1RequestParam;
			  }
		  },
	      "columns": table1Columns,
	     
	  } );
	
	
	$("#search").click(function(){
		 $.post(
			      'TestResultSearchAction_checkPermission',
			      {},
			      function (data) 
			      {
			    	  if(data.data){
			    		  alert(data.data);
			    	  }else{
			    		  table1.draw();
			    	  }
			      },"json");
		
	});
	
	$("#export").click(function(){
		  $.post(
			      'TestResultSearchAction_checkPermission',
			      {},
			      function (data) 
			      {
			    	  if(data.data){
			    		  alert(data.data);
			    	  }else{
			    		  if(reqData==null){
			    			  if(!getTable1RequestParam()){
			    				  return;
			    			  }
			    		  }
			    		  var jsonstr = JSON.stringify(reqData);
			    		  window.location.href="TestResultSearchExportAction?data="+jsonstr;
			    	  }
			      },"json");
		 
	});
	
	
	 function getTable1RequestParam(){
		 var ifQualify = 1;
		 var qualify_yes = $("#qualify_yes").prop("checked");
		 var qualify_no = $("#qualify_no").prop("checked");
		 if(qualify_yes && qualify_no){
			 alert("合格、不合格选项不可以同时选择！");
			 return false;
		 }else if(qualify_yes){
			 ifQualify = 2;
		 }else if(qualify_no){
			 ifQualify = 3;
		 }else{
			 ifQualify = 1;
		 }
		 
		 var declNo = $("#declNo").val().trim();
		 var entName = $("#entName").val().trim();
		 var countryName = $("#countryName ").val().trim();
		 var productName = $("#productName").val().trim();
		 var productCode = $("#productCode").val().trim();
		 var itemName = $("#itemName").val().trim();
		 
		 var startDate = $("#startDate").val().trim();
		 var endDate = $("#endDate").val().trim();
		 var declDateFlg = 0;
		 if(startDate == "" && endDate == ""){
			 declDateFlg = 0;
		 }else if(startDate != "" && endDate != ""){
			 declDateFlg = 1;
		 }else if(startDate != "" ){
			 declDateFlg = 2;
		 }else{
			 declDateFlg = 3;
		 }
		 reqData = {
				 declNo : declNo,
				 entName : entName,
				 countryName : countryName,
				 productCode : productCode,
				 productName : productName,
				 itemName : itemName,
				 ifQualify : ifQualify,
				 declDateFlg : declDateFlg,
				 declDateStart : startDate,
				 declDateEnd : endDate,
		  };
		  var jsonstr = JSON.stringify(reqData);
		  return jsonstr;
	  }
	
	  
});