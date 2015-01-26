//@ sourceURL=CtrlPlan.js 
$(document).ready(function(){
	var curTable1Row = null;
	var curTable2Row = null;
	
	var table1Operation = 0;   //0:新增  1：修改
	
	searchEnt("sEnt","sEnt-search");
	searchProduct("sProduct","sProduct-search");
	searchDetectionItem("sDetectionItem","sDetectionItem-search");

	var table1Columns = [
	                     {"data": "ctrlPlanImpID"},
	                     {"data": "ctrlPlanName"},
	                     {"data": "entName"},
	                     {"data": "productCode"},
	                     {"data": "productName"},
	                     {"data": "impDate"},
	                     {"data":"validDays"}
	                     ];
	
	
	var table2Columns = [
	                     {"data": "ctrlPlanItemID"},
	                     {"data": "itemCode"},
	                     {"data": "itemName"},
	                     {"data": "labData"},
	                     {"data": "dataUnit"},
	                     ];
	
	
/* ------------- table1响应事件 ------------------ */
	
	var table1 = $('#table1').DataTable( {
	      "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "ajax":{
			  url : "CtrlPlanAction_getCtrlPlanImp",
			  type : "GET",
			  data : function(d){
				  d.data = getTable1RequestParam;
			  }
		  },
	      "columns": table1Columns,
	      "columnDefs": [
	                     {
	                         "targets": [ 0 ],
	                         "visible": false,
	                     }
	                 ]
	  } );
	
	$('#table1').on( 'draw.dt', drawTable1CB);
	 
	$('#table1 tbody').on("click", "tr", clickTable1Row );
	
	$("#search1").click(searchTable1Btn);
	
	$("#add1").click(addTable1Btn);
	
	$("#edit1").click(editTable1Btn);
	
	$("#delete1").click(deleteTable1);
	
	$("#searchTable1").click(searchTable1);
	
    $("#saveTable1").click(saveTable1);
	
	$("#closeTable1Search").click(function(){
		clearAlertDiv("table1Search");
		$(".overlay").hide();
		$("#table1Search").hide();
	});
	
	$("#closeTable1Save").click(function(){
		clearAlertDiv("table1Save");
		$(".overlay").hide();
		$("#table1Save").hide();
	});
	
	
	/* ------------- table2响应事件 ------------------ */
	
	var table2 = $('#table2').DataTable( {
		  "info" : false,
		  "paging" : false,
		  "lengthChange" : false,
	      "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "ajax":{
			  url : "CtrlPlanAction_getCtrlPlanItem",
			  type : "GET",
			  data : function(d){
				  if(curTable1Row == null){
					  return {ctrlPlanImpID : 0};
				  }else{
					  return {ctrlPlanImpID : table1.row(curTable1Row).data().ctrlPlanImpID};
				  }
			  }
		  },
	      "columns": table2Columns,
	      "columnDefs": [
	                     {
	                         "targets": [ 0 ],
	                         "visible": false,
	                     }
	                 ]
	  } );
	
	
	$('#table2').on( 'draw.dt', drawTable2CB);
	
	$('#table2 tbody').on("click", "tr", clickTable2Row );

    $("#add2").click(addTable2Btn);
    
	$("#delete2").click(deleteTable2);
	
	$("#saveTable2").click(saveTable2);
		
	$("#closeTable2Save").click(function(){
		clearAlertDiv("table2Save");
		$(".overlay").hide();
		$("#table2Save").hide();
	});
	
	
	 function getTable1RequestParam(){
		 var data={
				 ctrlPlanName : $("#rCtrlPlanName").val().trim(),
				 entName : $("#rEntName").val().trim(),
				 productName : $("#rProductName").val().trim()
		 }
		 var jsonstr = JSON.stringify(data);
		 return jsonstr;
	  }
	
	 function drawTable1CB() {
		 curTable2Row = null;
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
		    table2.draw();
	}
	
	 function searchTable1Btn(){
	 	 $(".overlay").show();
		 $("#table1Search").show();
	 } 
	  
     function addTable1Btn(){
    	  table1Operation = 0;
    	  $(".overlay").show();
    	  $("#table1Save").show();
    	  $("#table1Save .form-group:gt(4)").hide();
     }
     
     function editTable1Btn(){
 		  table1Operation = 1;
 		  getTable1ItemDetail();
 	}
     
     function deleteTable1(){
		  if(curTable1Row == null){
			  alert("请先选择一条要删除的记录！");
			  return;
		  }
		  if(confirm("确认要删除该条记录?")){
			  var ctrlPlanImpID  = table1.row(curTable1Row).data().ctrlPlanImpID ;
			  $.post("CtrlPlanAction_delCtrlPlanImp?&ts="
						+ new Date().getTime(), {
							ctrlPlanImpID : ctrlPlanImpID
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
     
     function searchTable1(){
		 table1.draw();
		 $("#closeTable1Search").click();
	 }
	 
	 function getTable1ItemDetail(){
		  if(curTable1Row == null){
			  alert("请先选择一条记录");
			  return;
		  }
		  $(".overlay").show();
		  $("#table1Save").show();
		  $("#table1Save .form-group:gt(4)").show();
		  var ctrlPlanImpID = table1.row(curTable1Row).data().ctrlPlanImpID;
		  $.get("CtrlPlanAction_getCtrlPlanImpByID?&ts="
					+ new Date().getTime(), {
						ctrlPlanImpID : ctrlPlanImpID
			}, function(rdata) {
				setTable1ItemDetail(rdata);
			}, 'json');
	 }
	 
	 function setTable1ItemDetail(rdata){
		  $("#sCtrlPlanName").val(rdata.ctrlPlanName);
		  $("#sEnt").val(rdata.entCode+" "+rdata.entName);
		  $("#sProduct").val(rdata.productCode+" "+rdata.productName);
		  $("#sImpDate").val(rdata.impDate);
		  $("#sValidDays").val(rdata.validDays);
		  $("#sRegDeptName").val(rdata.regDeptName);
		  $("#sRegDate").val(rdata.regDate);
	  };
	 
	 function saveTable1(){
		  var data = null;
		  if(table1Operation == 0){
			  data = saveTable1Param(0);			  
		  }else{
			  if(curTable1Row == null){
				  alert("请先选择一条记录");
				  return;
			  }
			  var ctrlPlanImpID = table1.row(curTable1Row).data().ctrlPlanImpID;
			  data = saveTable1Param(ctrlPlanImpID);
		  }
		  if(!data){
			  return;
		  }
		  $.post("CtrlPlanAction_saveCtrlPlanImp?&ts="
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
	 
	 function saveTable1Param(ctrlPlanImpID){
		  var data = null;
		  var ctrlPlanName = $("#sCtrlPlanName").val().trim();
		  if(ctrlPlanName == ""){
			  alert("请输入监控计划名称！");
			  return false;
		  }
		  var entCode = getSelectValue("sEnt");
		  if(entCode == ""){
			  alert("请选择监控企业！");
			  return false;
		  }
		  var productCode = getSelectValue("sProduct");
		  if(productCode == ""){
			  alert("请选择产品编号！");
			  return false;
		  }
		  var impDate = $("#sImpDate").val().trim();
		  if(impDate == ""){
			  alert("请选中监控日期！");
			  return false;
		  }
		  var validDays = $("#sValidDays").val().trim();
		  if(validDays == ""){
			  alert("请输入监控有效期！");
			  return false;
		  }
		  data = {
				  ctrlPlanImpID : ctrlPlanImpID,
				  ctrlPlanName : ctrlPlanName,
				  entCode : getSelectValue("sEnt"),
				  productCode : getSelectValue("sProduct"),
				  impDate : impDate,
				  validDays : validDays
				  
		  };
		  if(!checkJsonParam(data)){
			  return false;
		  }
		  var jsonstr = JSON.stringify(data);
		  return jsonstr;
	 }
	  
	 function drawTable2CB() {
		 if(table2.rows().data().length > 0){
			  var dtRow = null;
			  var node = null;
			  if(curTable2Row != null){
				  dtRow = table2.row($(curTable2Row).context._DT_RowIndex);
				  node = dtRow.node();
				  if(node != null){
					  $(node).click();
					  return;
				  }
			  }
			  dtRow = table2.row(0);
			  node = dtRow.node();
			  $(node).click();
		  }else{
			  curTable2Row = null;
		  }
	 } 
	
	
	function clickTable2Row(){
		 var row = this;
		  if ( $(row).hasClass('active') ) {
			    curTable2Row = null;
	            $(row).removeClass('active');
	        }
	        else {
	        	curTable2Row = row;
	        	table2.$('tr.active').removeClass('active');
	            $(row).addClass('active');
	        }
	}
	
	
	 function addTable2Btn(){
		  if(curTable1Row == null){
			  alert("请先选择一条记录");
			  return;
		  }
		  $(".overlay").show();
		  $("#table2Save").show();
	 }
	 
	 function saveTable2(){
		  var data = null;
		  data = saveTable2Param(0);
		  if(!data){
			  return;
		  }
		  $.post("CtrlPlanAction_saveCtrlPlanItem?&ts="
					+ new Date().getTime(), {
			    data : data
			}, function(rdata) {
				if(rdata.data == "true"){
					alert("保存成功！");
					curTable2Row = null;     //保证新增后选中第一条新增的记录
					table2.draw();
					$("#closeTable2Save").click();
				}else{
					alert(rdata.data);
				}
			}, 'json');
	 }
	 
	 function saveTable2Param(ctrlPlanItemID){
		  var data = null;
		  var itemCode = getSelectValue("sDetectionItem");
		  if(itemCode == ""){
			  alert("请输入通报项目！");
			  return false;
		  }
		  var labData = $("#sLabData").val().trim();
		  if(labData == ""){
			  alert("请输入检测结果！");
			  return false;
		  }
		  var dataUnit = $("#sDataUnit").val().trim();
		  if(dataUnit == ""){
			  alert("请输入结果单位！");
			  return false;
		  }
		  var ctrlPlanImpID  = table1.row(curTable1Row).data().ctrlPlanImpID;
		  data = {
				  ctrlPlanItemID : ctrlPlanItemID,
				  ctrlPlanImpID : ctrlPlanImpID,
				  itemCode : itemCode,
				  labData : labData,
				  dataUnit : dataUnit
		  };
		  var jsonstr = JSON.stringify(data);
		  return jsonstr;
	 }
	 
	 function deleteTable2(){
		  if(curTable2Row == null){
			  alert("请先选择一条要删除的记录！");
			  return;
		  }
		  if(confirm("确认要删除该条记录?")){
			  var ctrlPlanItemID = table2.row(curTable2Row).data().ctrlPlanItemID;
			  $.post("CtrlPlanAction_delCtrlPlanItem?&ts="
						+ new Date().getTime(), {
							ctrlPlanItemID : ctrlPlanItemID
				}, function(rdata) {
					if(rdata.data == "true"){
						alert("删除成功！");
						table2.row(curTable2Row).remove().draw(false);
					}else{
						alert("删除失败！");
					}
				}, 'json');
		  }
	  }
});