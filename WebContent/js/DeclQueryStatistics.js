//@ sourceURL=DeclQueryStatistics.js 
$(document).ready(function(){
	var curTable1Row = null;
	var table1Operation = 0;
	
	initStringSelect("SelectDataAction_getDeclQueryLogic?data=1","sLeftLogic","sLeftLogic-select");
	initStringSelect("SelectDataAction_getDeclQueryLogic?data=2","sRightLogic","sRightLogic-select");
	initStringSelect("SelectDataAction_getDeclQueryDefinedField","sDefinedField","sDefinedField-select");
	initStringSelect("SelectDataAction_getDeclQueryOperateSignal","sOperateSign","sOperateSign-select");
	deleteTable1All();
	
	var table1Columns = [
	                     {"data":"declQueryConditionID"},
	                     {"data":"sortNo"},
	                     {"data":"leftLogic"},
	                     {"data":"definedField"},
	                     {"data":"operateSign"},
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
		 "info" : false,
		  "paging" : false,
		  "lengthChange" : false,
		  "deferLoading" : true,
		  "processing": true,
		  "serverSide": true,
		  "ajax":{
			  url : "DeclQueryStatisticsAction_getDeclQueryCondition",
			  type : "GET",
			  cache:false,
		  },
	      "columns": table1Columns,
	      "columnDefs": [
	                      {
	                         "targets": [ 0 ],
	                         "visible": false,
	                     }
	                 ]
	  } );
	
	var table2 = $('#table2').DataTable( {
		
		 "deferLoading" : true,
		  "processing": true,
		  "serverSide": true,
		  "ajax":{
			  url : "DeclQueryStatisticsAction_getDeclQueryResult",
			  type : "GET",
			  cache : false,
			  dataSrc : function ( json ) {
				  if(json.error && json.error!=""){
					  alert(json.error);
				  }
				  return json.data;
			  },
		  },
	      "columns": table2Columns,
	  } );
	
	$('#table1 tbody').on("click", "tr", clickTable1Row );
	
	$("#search").click(searchTable2);
	
	$("#add").click(addTable1Btn);
	
	$("#edit").click(editTable1Btn);

	$("#generate").click(generateSQL);
	
	$("#delete").click(deleteTable1);
	
	$("#saveTable1").click(saveTable1);
	
	$("#closeTable1Save").click(function(){
		clearAlertDiv("table1Save");
		$(".overlay").hide();
		$("#table1Save").hide();
	});
	
	
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
	  
    function addTable1Btn(){
  	  table1Operation = 0;
  	  $(".overlay").show();
  	  $("#table1Save").show();
    }
   
   function editTable1Btn(){
		  table1Operation = 1;
		  getTable1ItemDetail();
	}
   
   function getTable1ItemDetail(){
		  if(curTable1Row == null){
			  alert("请先选择一条记录");
			  return;
		  }
		  $(".overlay").show();
		  $("#table1Save").show();
		  var rowData = table1.row(curTable1Row).data();
		  $("#sSortNo").val(rowData.sortNo);
		  $("#sLeftLogic").val(rowData.leftLogic);
		  $("#sDefinedField").val(rowData.definedField);
		  $("#sOperateValue").val(rowData.operateValue);
		  $("#sOperateSign").val(rowData.operateSign);
		  $("#sRightLogic").val(rowData.rightLogic);
	 }
   
	 function saveTable1(){
		  var data = null;
		  if(table1Operation == 0){
			  data = saveTable1Param(0);			  
		  }else{
			  if(curTable1Row == null){
				  alert("请先选择一条记录");
				  return;
			  }
			  var declQueryConditionID = table1.row(curTable1Row).data().declQueryConditionID;
			  data = saveTable1Param(declQueryConditionID);
		  }
		  if(!data){
			  return;
		  }
		  $.post("DeclQueryStatisticsAction_saveDeclQueryCondition?&ts="
					+ new Date().getTime(), {
			    data : data
			}, function(rdata) {
				if(rdata.data == "true"){
					alert("保存成功！");
					if(table1Operation == 0){
						curTable1Row = null;     //保证新增后选中第一条新增的记录
						table1.draw();
						$("#closeTable1Save").click();
					}else{
						table1.draw(false);
						$("#closeTable1Save").click();
					}
				}else{
					alert(rdata.data);
				}
			}, 'json');
	 }

	 function saveTable1Param(declQueryConditionID){
		  var data = null;
		  var sortNo = $("#sSortNo").val().trim();
		  if(sortNo == "" || sortNo == null){
			  alert("请输入排序序号！");
			  return false;
		  }
		  if(isNaN(sortNo) || sortNo <= 0){
			  alert("排序序号必须为大于0的整数！");
			  return false;
		  }
		  var leftLogic = $("#sLeftLogic").val().trim();
		  if( leftLogic == null){
			  leftLogic = "";
		  }
		  var rightLogic = $("#sRightLogic").val().trim();
		  if( rightLogic == null){
			  rightLogic = "";
		  }
		  var definedField = $("#sDefinedField").val().trim();
		  if(definedField == ""|| definedField == null){
			  alert("请输入字段名！");
			  return false;
		  }
		  var operateSign = $("#sOperateSign").val().trim();
		  if(operateSign == ""|| operateSign == null){
			  alert("请输入操作符！");
			  return false;
		  }
		  var operateValue = $("#sOperateValue").val().trim();
		  if(operateValue == ""|| operateValue == null){
			  alert("请输入取值！");
			  return false;
		  }
		  data = {
				    declQueryConditionID : declQueryConditionID,
				    sortNo : sortNo,
				    leftLogic : leftLogic,
				    definedField : definedField,
				    operateSign : operateSign,
				    operateValue : operateValue,
				    rightLogic : rightLogic,
		  };
		  var jsonstr = JSON.stringify(data);
		  return jsonstr;
	 }
	 
	  function deleteTable1(){
		  if(curTable1Row == null){
			  alert("请先选择一条要删除的记录！");
			  return;
		  }
		  if(confirm("确认要删除该条记录?")){
			  var declQueryConditionID  = table1.row(curTable1Row).data().declQueryConditionID ;
			  $.post("DeclQueryStatisticsAction_delDeclQuerySingleCondition?&ts="
						+ new Date().getTime(), {
							data : declQueryConditionID
				}, function(rdata) {
					if(rdata.data == "true"){
						alert("删除成功！");
						table1.row(curTable1Row).remove().draw(false);
					}else{
						alert("删除失败！");
					}
				}, 'json');
		  }
	  }
	  
	  function deleteTable1All(){
			  $.post("DeclQueryStatisticsAction_delDeclQueryAllCondition?&ts="
						+ new Date().getTime(), {
				}, function(rdata) {
				}, 'json');
	  }
	  
	  function searchTable2(){
		  $.post(
			      'DeclQueryStatisticsAction_checkPermission',
			      {},
			      function (data) 
			      {
			    	  if(data.data){
			    		  alert(data.data);
			    	  }else{
			    		  table2.draw();
			    	  }
			      },"json");
	  }
	  
	  function generateSQL(){
		  $.post(
			      'DeclQueryStatisticsAction_generateDeclQuerySQLStr',
			      {},
			      function (data) 
			      {
			    	  $("#sqlContent").text(data.data);
			      },"json");
	  }
		
		$("#export").click(function(){
			
			 $.post(
				      'DeclQueryStatisticsAction_checkPermission',
				      {},
				      function (data) 
				      {
				    	  if(data.data){
				    		  alert(data.data);
				    	  }else{
				    		  window.location.href="DeclQueryStatisticsExportAction";
				    	  }
				      },"json");
		});
	  
});