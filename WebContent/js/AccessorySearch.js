//@ sourceURL=AccessorySearch.js 
$(document).ready(function(){
	var reqData = null;
	
	var table1Columns = [
	                     {"data": "accessoryName"},
	                     {"data": "entName"},
	                     {"data": "productCode"},
	                     {"data": "entProductName"},
	                     {"data": "accessoryUse"},
	                     {"data": "countryName"},
	                     ];
	
	
	var table1 = $('#table1').DataTable( {
		  "deferLoading":0,
	      "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "ajax":{
			  url : "AccessorySearchAction_getAccessoryUseByEntProduct",
			  type : "GET",
			  data : function(d){
				  d.data = getTable1RequestParam;
			  }
		  },
	      "columns": table1Columns,
	     
	  } );
	
	
	$("#search").click(function(){
		 $.post(
			      'AccessorySearchAction_checkPermission',
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
			      'AccessorySearchAction_checkPermission',
			      {},
			      function (data) 
			      {
			    	  if(data.data){
			    		  alert(data.data);
			    	  }else{
			    		  if(reqData==null){
			    			  getTable1RequestParam();
			    		  }
			    		/*  if(!checkJsonParam(reqData)){
			    			  return;
			    		  }*/
			    		  var jsonstr = JSON.stringify(reqData);
			    		  window.location.href="AccessorySearchExportAction?data="+jsonstr;
			    	  }
			      },"json");
		 
	});
	
	
	 function getTable1RequestParam(){
		 reqData = {
				  accessoryName : $("#accessory").val().trim(),
				  entName : $("#ent").val().trim(),
			      productName : $("#product").val().trim(),
			      countryName : $("#country").val().trim()
		  };
		  var jsonstr = JSON.stringify(reqData);
		  return jsonstr;
	  }
	
	  
});