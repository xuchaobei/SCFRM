//@ sourceURL=DeclQueryStatistics.js 
$(document).ready(function(){
	var curTable1Row = null;
	
	initIfQualify();
	initReleaseMode();
	
	
	var table1Columns = [
	                     {"data":"declQueryConditionID"},
	                     {"data":"sortNo"},
	                     {"data":"leftLogic"},
	                     {"data":"definedField"},
	                     {"data":"operateName"},
	                     {"data":"operateValue"},
	                     {"data":"rightLogic"},
	                     ];
	
	var table2Columns = [
	                     {"data": "declNo"},
	                     {"data": "declDate"},
	                     {"data": "countryName"},
	                     {"data": "entName"},
	                     {"data": "productData"},
	                     {"data": "processOperateDatetime"},
	                     {"data": "values_USD"},
	                     {"data": "weight"},
	                     {"data": "ifSampling"},
	                     {"data": "ifQualified_Yes"},
	                     {"data": "whyUnqualify"},
	                     {"data": "releaseMode"},
	                     ];
	var table1 = $('#table1').DataTable( {
	      "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "ajax":{
			  url : "DeclQueryAction_getDeclQueryResultSimple",
			  type : "GET",
			  data : function(d){
				  d.data = getTable1RequestParam;
			  }
		  },
	      "columns": table1Columns,
	      "columnDefs": [
	                     {
	                    	 "render": function ( data, type, row ) {
	                    		 if(data == 1){
	                    			 return "合格";
	                    		 }else{
	                    			 return "不合格";
	                    		 }
	                         },
	                         "targets": [ 7 ],
	                     },
	                 ]
	  } );
	

	
	$('#table1').on( 'draw.dt', drawTable1CB);
	 
	$('#table1 tbody').on("click", "tr", clickTable1Row );
	
	$("#search1").click(function(){
		table1.draw();
	});
	
	 function getTable1RequestParam(){
		 var qualifyJudge = $("#ifQualify").val().trim();
		  var data = {
				 declNo : $("#declNo").val().trim(),
				 entName : $("#entName").val().trim(),
				 countryName : $("#countryName").val().trim(),
				 startDate : $("#startDate").val().trim(),
				 endDate : $("#endDate").val().trim(),
				 qualifyJudge : qualifyJudge,
				 releaseStatus : $("#releaseMode").val().trim()
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
	  
	function initIfQualify(){
		var source = [{
							value:"合格",
							label:"合格"
						},
		              {
							value:"不合格",
							label:"不合格"
		              }];
		
		cus_autocomplete(source, "ifQualify", "ifQualify-select", null, null);		
	}
	
	function initReleaseMode(){
		var source = ["直接放行","监控放行","信用放行","评判放行"];
		cus_autocomplete(source, "releaseMode", "releaseMode-select", null, null);		
	}
});