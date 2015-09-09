//@ sourceURL=DeclQuery.js 
$(document).ready(function(){
	var curTable1Row = null;
	
	initIfQualify();
	initReleaseMode();
	
	var table1Columns = [
	                     {"data": "declNo"},
	                     {"data": "declDate"},
	                     {"data": "entName"},
	                     {"data": "countryName"},
	                     {"data": "productData"},
	                     {"data": "processOperateDatetime"},
	                     {"data": "ifSampling","render": function ( data, type, full, meta ) {
	                    	 return data === '1'  ? '是' : '否';
	                     	}},
	                     {"data": "ifQualified_Yes"},
	                     {"data": "releaseMode"},
	                     {"data": "processOperatorCode"},
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
		 var startDate=$("#startDate").val().trim();
		 var endDate=$("#endDate").val().trim();
		 
		 if(startDate=='' || startDate==endDate){
			  var date= new Date();
			  date.setDate(date.getDate()-10);
			  startDate=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+(date.getDate());
			  //endDate=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+(date.getDate());
			 
			  $("#startDate").val(startDate);
			 
		 }
		 
		  var data = {
				 declNo : $("#declNo").val().trim(),
				 entName : $("#entName").val().trim(),
				 countryName : $("#countryName").val().trim(),
				 startDate : startDate,
				 endDate : endDate,
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

		$.get("DeclQueryAction_GetReleaseMode?&ts="
		+ new Date().getTime(),
		 function(rdata) {
			
			cus_autocomplete(rdata.data, "releaseMode", "releaseMode-select", null, null);
		  }, 'json');

		
				
	}
});