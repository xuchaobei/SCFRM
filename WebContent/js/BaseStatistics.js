//@ sourceURL=BaseStatistics.js 
$(document).ready(function(){
	var reqData = null;
	
	searchBase("base","base-search");
	
	var table1Columns = [
	                     {"data": "baseCode"},
	                     {"data": "baseName"},
	                     {"data": "productCode"},
	                     {"data": "productName"},
	                     {"data": "countryName"},
	                     {"data": "totalWeight"},
	                     {"data": "className"},
	                     {"data": "subclassName"},
	                     {"data": "materialName"},
	                     {"data": "totalExportTimes"},
	                     {"data": "totalUnqualifyExportTimes"},
	                     {"data": "unqualifyRatio"},
	                     ];
	
	
	var table1 = $('#table1').DataTable( {
		  "deferLoading":0,
	      "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "ajax":{
			  url : "BaseStatisticsAction_getStaticResultForBase",
			  type : "GET",
			  data : function(d){
				  d.data = getTable1RequestParam;
			  }
		  },
	      "columns": table1Columns,
	     
	  } );
	
	
	$("#search").click(function(){
		 $.post(
			      'BaseStatisticsAction_checkPermission',
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
			      'BaseStatisticsAction_checkPermission',
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
			    		  window.location.href="BaseStatisticsExportAction?data="+jsonstr;
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
				  baseCode : getSelectValue("base"),
		  };
		  if(!checkJsonParam(reqData)){
			  return false;
		  }
		  var jsonstr = JSON.stringify(reqData);
		  return jsonstr;
	  }
	
	  
});