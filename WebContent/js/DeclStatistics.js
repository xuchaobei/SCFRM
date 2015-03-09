//@ sourceURL=DeclStatistics.js 
$(document).ready(function(){
	var reqData = null;
	
	initInspOrgSelect("org","org-select","dept","dept-select");
	searchCountry("country","country-search");
	searchEnt("ent","ent-search");
	
	
	var table1Columns = [
	                     {"data": "orgName"},
	                     {"data": "deptName"},
	                     {"data": "entName"},
	                     {"data": "countryName"},
	                     {"data": "totalDeclExportTimes"},
	                     {"data": "totalValues_USD"},
	                     {"data": "totalWeight"},
	                     {"data": "totalUnqualifyDeclExportTimes"},
	                     {"data": "totalUnqualifyValues_USD"},
	                     {"data": "totalUnqualifyWeight"},
	                     {"data": "unqualifyRatio"},
	                     {"data": "totalSamplingTimes"},
	                     {"data": "samplingRatio"},
	                     ];
	
	var table2Columns = [
	                     {"data": "totalDeclExportTimes"},
	                     {"data": "totalValues_USD"},
	                     {"data": "totalWeight"},
	                     {"data": "totalUnqualifyDeclExportTimes"},
	                     {"data": "totalUnqualifyValues_USD"},
	                     {"data": "totalUnqualifyWeight"},
	                     {"data": "unqualifyRatio"},
	                     {"data": "totalSamplingTimes"},
	                     {"data": "samplingRatio"},
	                     ];
	
	var table1 = $('#table1').DataTable( {
		  "deferLoading":0,
	      "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "ajax":{
			  url : "DeclStatisticsAction_getStaticResultForDecl",
			  type : "GET",
			  data : function(d){
				  d.data = getTable1RequestParam;
			  }
		  },
	      "columns": table1Columns,
	     
	  } );
	
	var table2 = $('#table2').DataTable( {
		  "info" : false,
		  "paging" : false,
		  "lengthChange" : false,
	      "columns": table2Columns,
	  } );
	
	
	$("#search").click(function(){
		 $.post(
			      'DeclStatisticsAction_checkPermission',
			      {},
			      function (data) 
			      {
			    	  if(data.data){
			    		  alert(data.data);
			    	  }else{
			    		  table1.draw();
			    		  reqData.group_Org ? table1.column(0).visible(true) : table1.column(0).visible(false);
			    		  reqData.group_Dept ? table1.column(1).visible(true) : table1.column(1).visible(false);
			    		  reqData.group_Ent ? table1.column(2).visible(true) : table1.column(2).visible(false);
			    		  reqData.group_Country ? table1.column(3).visible(true) : table1.column(3).visible(false);
			    		  getSummaryStatistics(getTable1RequestParam());
			    	  }
			      },"json");
		
	});
	
	$("#export").click(function(){
		  $.post(
			      'DeclStatisticsAction_checkPermission',
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
			    		  window.location.href="DeclStatisticsExportAction?data="+jsonstr;
			    	  }
			      },"json");
		 
	});
	
	
	function getSummaryStatistics(jsonstr){
		 $.get(
			      'DeclStatisticsAction_getSummaryStaticResultForDecl',
			      {data : jsonstr},
			      function (data) 
			      {
			    	  if(table2.row(1).data()){
			    		  table2.clear().row(1).data(data).draw();
			    	  }else{
			    		  table2.clear().row.add(data).draw();
			    	  }
			      },"json");
	}
	
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
		 var group_Org = false;
		 if($("#orgBox").prop("checked")  == true){
			 group_Org = true;
		 }
		 var group_Dept = false;
		 if($("#deptBox").prop("checked")  == true){
			 group_Dept = true;
		 }
		 var group_Country = false;
		 if($("#countryBox").prop("checked") == true){
			 group_Country = true;
		 }
		 var group_Ent = false;
		 if($("#entBox").prop("checked") == true){
			 group_Ent = true;
		 }
		 if(!(group_Org || group_Dept || group_Country || group_Ent)){
			alert("需勾选至少一个统计合并项"); 
			return false;
		 }
		 reqData = {
				  startYear : startDate.split("-")[0],
				  startMonth : startDate.split("-")[1],
				  endYear : endDate.split("-")[0],
				  endMonth : endDate.split("-")[1],
				  orgCode : getSelectValue("org"),
				  deptCode : getSelectValue("dept"),
				  countryCode : getSelectValue("country"),
			      entCode : getSelectValue("ent"),
			      group_Org : group_Org,
			      group_Dept : group_Dept,
			      group_Country : group_Country,
			      group_Ent : group_Ent
		  };
		  if(!checkJsonParam(reqData)){
			  return false;
		  }
		  var jsonstr = JSON.stringify(reqData);
		  return jsonstr;
	  }
	
	  
});