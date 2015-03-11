//@ sourceURL=ItemStatistics.js 
$(document).ready(function(){
	var reqData = null;
	
	searchCountry("country","country-search");
	searchEnt("ent","ent-search");
	searchDetectionItem("item","item-search");
	
	var table1Columns = [
	                     {"data": "itemCode"},
	                     {"data": "itemName"},
	                     {"data": "className"},
	                     {"data": "productCode"},
	                     {"data": "productName"},
	                     {"data": "countryName"},
	                     {"data": "totalGoodsTimes"},
	                     {"data": "totalProductSamplingTimes"},
	                     {"data": "productSamplingRatio"},
	                     {"data": "totalProductUnqualifyTimes"},
	                     {"data": "productUnqualifyRatio"},
	                     ];
	
	
	var table1 = $('#table1').DataTable( {
		  "deferLoading":0,
	      "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "ajax":{
			  url : "ItemStatisticsAction_getStaticResultForItem",
			  type : "GET",
			  data : function(d){
				  d.data = getTable1RequestParam;
			  }
		  },
	      "columns": table1Columns,
	     
	  } );
	
	
	$("#search").click(function(){
		 $.post(
			      'ItemStatisticsAction_checkPermission',
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
			      'ItemStatisticsAction_checkPermission',
			      {},
			      function (data) 
			      {
			    	  if(data.data){
			    		  alert(data.data);
			    	  }else{
			    		  if(reqData==null){
			    			  getTable1RequestParam();
			    		  }
			    		  if(!checkJsonParam(reqData)){
			    			  return;
			    		  }
			    		  var jsonstr = JSON.stringify(reqData);
			    		  window.location.href="ItemStatisticsExportAction?data="+jsonstr;
			    	  }
			      },"json");
		 
	});
	
	
	 function getTable1RequestParam(){
		 var startDate = $("#startDate").val().trim();
		 if(startDate == ""){
			 alert("请选择开始时间");
			 return false;
		 }
		 var endDate = $("#endDate").val().trim();
		 if(endDate == ""){
			 alert("请选择结束时间");
			 return false;
		 }
		 reqData = {
				  startYear : startDate.split("-")[0],
				  startMonth : startDate.split("-")[1],
				  endYear : endDate.split("-")[0],
				  endMonth : endDate.split("-")[1],
				  countryCode : getSelectValue("country"),
			      entCode : getSelectValue("ent"),
			      productCode : $("#productCode").val().trim(),
			      productName : $("#productName").val().trim(),
			      itemCode : getSelectValue("item")
		  };
		  if(!checkJsonParam(reqData)){
			  return false;
		  }
		  var jsonstr = JSON.stringify(reqData);
		  return jsonstr;
	  }
	
	  
});