//@ sourceURL=DeclProcess.js 
$(document).ready(function(){
	var curTable1Row = null;
	
	initIfQualify();
	
	var table1Columns = [
	                     {"data": "declNo"},
	                     {"data": "declDate"},
	                     {"data": "countryName"},
	                     {"data": "entName"},
	                     {"data": "productData"},
	                     {"data": "certType"},
	                     {"data": "ifSampling","render": function ( data, type, full, meta ) {
	                    	 return data === '1'  ? '是' : '否';
	                     	}},
	                     {"data": "ifQualified_Yes"},
	                     {"data": "ifQualified_No"},
	                     ];
	
	var table2Columns = [
	                     {"data": "declNo"},
	                     {"data": "processName"},
	                     {"data": "processOperateDatetime"},
	                     {"data": "orgName"},
	                     {"data": "deptName"},
	                     {"data": "operatorName"},
	                     ];
	
	var table3Columns = [
	                     {"data": "operateDate"},
	                     {"data": "operateDesc"},
	                     {"data": "processName"},
	                     {"data": "orgName"},
	                     {"data": "deptName"},
	                     {"data": "operatorName"},
	                     ];
	
	var table1 = $('#table1').DataTable( {
	      "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "ajax":{
			  url : "DeclProcessAction_getDeclProcessByQuery",
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
	                    		 }else if(row.ifQualified_No == 1){
	                    			 return "不合格";
	                    		 }else{
	                    			 return "";
	                    		 }
	                         },
	                         "targets": [ 7 ],
	                     },
	                     { "visible": false,  "targets": [ 8 ] }
	                 ]
	  } );
	
	var table2 = $('#table2').DataTable( {
		  "info" : false,
		  "paging" : false,
		  "lengthChange" : false,
	      "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "ajax":{
			  url : "DeclProcessAction_getDeclProcessByDeclNo",
			  type : "GET",
			  data : function(d){
				  if(curTable1Row != null){
					  d.data = table1.row(curTable1Row).data().declNo;
				  }
			  }
		  },
	      "columns": table2Columns,
	  } );
	
	var table3 = $('#table3').DataTable( {
		  "info" : false,
		  "paging" : false,
		  "lengthChange" : false,
	      "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "ajax":{
			  url : "DeclProcessAction_getDeclProcessOperateLog",
			  type : "GET",
			  data : function(d){
				  if(curTable1Row != null){
					  d.data = table1.row(curTable1Row).data().declNo;
				  }
			  }
		  },
	      "columns": table3Columns,
	  } );
	
	$('#table1').on( 'draw.dt', drawTable1CB);
	 
	$('#table1 tbody').on("click", "tr", clickTable1Row );
	
	$("#search1").click(function(){
		table1.draw();
	});
	
	 function getTable1RequestParam(){
		 var ifQualify = $("#ifQualify").val().trim();
		 if(ifQualify == "合格"){
			 ifQualify = 2;
		 }else if(ifQualify == "不合格"){
			 ifQualify = 3;
		 }else{
			 ifQualify = 1; 
		 }
		 var startDate=$("#declDateStart").val().trim();
		 var endDate=$("#declDateEnd").val().trim();
	
		 if(startDate=='' || startDate==endDate){
			  var date= new Date();
			  date.setDate(date.getDate()-10);
			  startDate=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+(date.getDate());
			 // endDate=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
			$("#declDateStart").val(startDate); 
		 }
		
		  var data = {
				 declNo : $("#declNo").val().trim(),
				 entName : $("#entName").val().trim(),
				 countryName : $("#countryName").val().trim(),
				 declDateStart : startDate,
				 declDateEnd : endDate,
				 ifQualify : ifQualify
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
			  table2.clear();
			  table3.clear();
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
		  table2.draw();
		  table3.draw();
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
	
});