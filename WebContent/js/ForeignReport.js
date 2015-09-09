//@ sourceURL=ForeignReport.js 
$(document).ready(function(){
	var curTable1Row = null;
	var curTable2Row = null;
	
	var table1Operation = 0;   //0:新增  1：修改
	
	initProductClassSelect("sProductClass","sProductClass-select","sProductSubclass","sProductSubclass-select");
	initMaterialClassSelect("sMaterialClass","sMaterialClass-select","sMaterialSubclass","sMaterialSubclass-select","sMaterial","sMaterial-select");

	searchEnt("sEnt","sEnt-search");
	searchCountryForDeport("sCountry","sCountry-search");
	searchMaterialSource("sMaterialSource","sMaterialSource-search");
	searchProcessingMethod("sProcessingMethod","sProcessingMethod-search");
	searchPackageType("sPackageType","sPackageType-search");
	searchIntendedUse("sIntentedUse","sIntentedUse-search");
	searchDetectionItem("sDetectionItem","sDetectionItem-search");

	var table1Columns = [
	                     {"data": "foreignReportingID"},
	                     {"data": "reportingTitle"},
	                     {"data": "countryName"},
	                     {"data": "entCode"},
	                     {"data": "entName"},
	                     {"data": "reportingReason"},
	                     {"data": "reportingDate"},
	                     ];
	
	
	var table2Columns = [
	                     {"data": "reportingItemID"},
	                     {"data": "itemCode"},
	                     {"data": "itemName"},
	                     ];
	
	/* ------------- table1响应事件 ------------------ */
	
	var table1 = $('#table1').DataTable( {
	      "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "ajax":{
			  url : "ForeignReportAction_getForeignReportList",
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
			  url : "ForeignReportAction_getForeignReportingItem",
			  type : "GET",
			  data : function(d){
				  if(curTable1Row == null){
					  return {foreignReportingID : 0};
				  }else{
					  return {foreignReportingID : table1.row(curTable1Row).data().foreignReportingID};
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
		 var data = {
				 reportingTitle : $("#rReportingTitle").val().trim(),
				 countryName : $("#rCountryName").val().trim(),
				 entName : $("#rEntName").val().trim(),
		  };
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
    	  $("#noteffectflg").prop("checked",false);
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
			  var foreignReportingID  = table1.row(curTable1Row).data().foreignReportingID ;
			  $.post("ForeignReportAction_delForeignReporting?&ts="
						+ new Date().getTime(), {
							foreignReportingID : foreignReportingID
				}, function(rdata) {
					if(rdata.data == "true"){
						alert("删除成功！");
						table1.row(curTable1Row).remove().draw(false);
					}else{
						alert(rdata.data);
					}
				}, 'json');
		  }

	  }
     
     function searchTable1(){
		 table1.draw();
		 //$("#closeTable1Search").click();
		    $(".overlay").hide();
			$("#table1Search").hide();
	 }
	 
	 function getTable1ItemDetail(){
		  if(curTable1Row == null){
			  alert("请先选择一条记录");
			  return;
		  }
		  $(".overlay").show();
		  $("#table1Save").show();
		  $("#table1Save .form-group:gt(4)").show();
		  
		  var foreignReportingID = table1.row(curTable1Row).data().foreignReportingID;
		  $.get("ForeignReportAction_getForeignReportingDetailByID?&ts="
					+ new Date().getTime(), {
						foreignReportingID : foreignReportingID
			}, function(rdata) {
				setTable1ItemDetail(rdata);
			}, 'json');
	 }
	 
	 function setTable1ItemDetail(rdata){
		  $("#sReportingTitle").val(rdata.reportingTitle);
		  $("#sEnt").val(rdata.entCode+" "+rdata.entName);
		  $("#sReportingReason").val(rdata.reportingReason);
		  $("#sCountry").val(rdata.countryCode+" "+rdata.countryName);
		  $("#sProductClass").val(rdata.productClassCode+" "+rdata.productClassName);
		  initProductSubclassSelect(rdata.productClassCode, "sProductSubclass", "sProductSubclass-select");
		  $("#sProductSubclass").val(rdata.productSubclassCode+" "+rdata.productSubclassName);
		  $("#sMaterialClass").val(rdata.materialClassCode+" "+rdata.materialClassName);
		  initMaterialSubclassSelect(rdata.materialClassCode,"sMaterialSubclass","sMaterialSubclass-select","sMaterial","sMaterial-select");
		  $("#sMaterialSubclass").val(rdata.materialSubclassCode+" "+rdata.materialSubclassName);
		  initMaterialSubsubclassSelect(rdata.materialClassCode, rdata.materialSubclassCode, "sMaterial","sMaterial-select");
		  $("#sMaterial").val(rdata.materialCode+" "+rdata.materialName);
		  $("#sMaterialSource").val(rdata.materialSourceCode+" "+rdata.materialSourceName);
		  $("#sProcessingMethod").val(rdata.processingMethodCode+" "+rdata.processingMethodName);
		  $("#sPackageType").val(rdata.packageTypeCode+" "+rdata.packageTypeName);
		  $("#sIntentedUse").val(rdata.intentedUseCode+" "+rdata.intentedUseName);
		  $("#sReportingDate").val(rdata.reportingDate);
		  $("#sRemarks").val(rdata.remarks);
		  $("#sRegDeptName").val(rdata.regDeptName);
		  $("#sRegDatetime").val(rdata.regDatetime);
		 
		  if(rdata.notEffectFlg=='1'){
			  $("#noteffectflg").prop("checked",true);
		  }else{
			  $("#noteffectflg").prop("checked",false);       
		  }
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
			  var foreignReportingID = table1.row(curTable1Row).data().foreignReportingID;
			  data = saveTable1Param(foreignReportingID);
		  }
		  if(!data){
			  return;
		  }
		  $.post("ForeignReportAction_saveForeignReporting?&ts="
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
	 
	 function saveTable1Param(foreignReportingID){
		  var data = null;
		  var reportingTitle = $("#sReportingTitle").val().trim();
		  if(reportingTitle == "" || reportingTitle == null){
			  alert("请输入通报标题！");
			  return false;
		  }
		  var reportingDate = $("#sReportingDate").val().trim();
		  if(reportingDate == "" || reportingDate == null){
			  alert("请输入通报日期！");
			  return false;
		  }
		  var countryCode = getSelectValue("sCountry");
		  if(countryCode == ""){
			  alert("请输入国家或地区！");
			  return false;
		  }
		  var productClassCode = getSelectValue("sProductClass");
		 /* if(productClassCode == ""){
			  alert("请输入产品大类！");
			  return false;
		  }*/
		  var productSubclassCode = getSelectValue("sProductSubclass");
		 /* if(productSubclassCode == ""){
			  alert("请输入产品小类！");
			  return false;
		  }*/
		  var processingMethodCode = getSelectValue("sProcessingMethod");
		  if(processingMethodCode == ""){
			  alert("请输入加工方式！");
			  return false;
		  }
		  var materialClassCode = getSelectValue("sMaterialClass");
		  if(materialClassCode == ""){
			  alert("请输入原料大类！");
			  return false;
		  }
		  var materialSubclassCode = getSelectValue("sMaterialSubclass");
		  if(materialSubclassCode == ""){
			  alert("请输入原料小类！");
			  return false;
		  }
		  var materialCode = getSelectValue("sMaterial");
		  if(materialCode == ""){
			  alert("请输入原料细类！");
			  return false;
		  }
		  var notEffectFlg=null;
		  if($("#noteffectflg").prop("checked")){
			  notEffectFlg='1';
		  }else{
			  notEffectFlg='0';     
		  }
		
		  data = {
				  foreignReportingID : foreignReportingID,
				  reportingTitle : reportingTitle,
				  reportingDate : reportingDate,
				  reportingReason : $("#sReportingReason").val().trim(),
				  entCode : getSelectValue("sEnt"),
				  productClassCode : productClassCode,
				  productSubclassCode : productSubclassCode,
				  materialClassCode : materialClassCode,
				  materialSubclassCode : getSelectValue("sMaterialSubclass"),
				  materialCode : getSelectValue("sMaterial"),
				  materialSourceCode : getSelectValue("sMaterialSource"),
				  processingMethodCode : getSelectValue("sProcessingMethod"),
				  packageTypeCode : getSelectValue("sPackageType"),
				  intentedUseCode : getSelectValue("sIntentedUse"),
				  countryCode : countryCode,
				  notEffectFlg :notEffectFlg
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
		  $.post("ForeignReportAction_saveForeignReportingItem?&ts="
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
	 
	 function saveTable2Param(reportingItemID){
		  var data = null;
		  var itemCode = getSelectValue("sDetectionItem");
		  if(itemCode == ""){
			  alert("请输入通报项目！");
			  return false;
		  }
		  var foreignReportingID  = table1.row(curTable1Row).data().foreignReportingID;
		  data = {
				  reportingItemID : reportingItemID,
				  foreignReportingID : foreignReportingID,
				  itemCode : itemCode,
		  };
		  if(!checkJsonParam(data)){
			  return false;
		  }
		  var jsonstr = JSON.stringify(data);
		  return jsonstr;
	 }
	 
	 function deleteTable2(){
		  if(curTable2Row == null){
			  alert("请先选择一条要删除的记录！");
			  return;
		  }
		  if(confirm("确认要删除该条记录?")){
			  var reportingItemID = table2.row(curTable2Row).data().reportingItemID;
			  $.post("ForeignReportAction_delForeignReportingItem?&ts="
						+ new Date().getTime(), {
							reportingItemID : reportingItemID
				}, function(rdata) {
					if(rdata.data == "true"){
						alert("删除成功！");
						table2.row(curTable2Row).remove().draw(false);
					}else{
						alert(rdata.data);
					}
				}, 'json');
		  }

	  }
	 
	 var matrclaRow=null;
	 var matericlasalltable = $('#materialclsallTb').DataTable({"paging" : false,
	 	"info":false});
	 $('#materialclsallTb tbody').on("click", "tr", clickmateriallRow );
	 function clickmateriallRow(){
	 	  var row = this;       
	 	  if ( $(row).hasClass('active') ) {
	 		    matrclaRow = null;
	           $(row).removeClass('active');
	       }
	       else {           
	       	matrclaRow = row;
	       	matericlasalltable.$('tr.active').removeClass('active');
	       	$("#sMaterialClass").val(matericlasalltable.row(matrclaRow).data()[0]+" "+matericlasalltable.row(matrclaRow).data()[1]);
	       	$("#sMaterialSubclass").val(matericlasalltable.row(matrclaRow).data()[2]+" "+matericlasalltable.row(matrclaRow).data()[3]);
	       	$("#sMaterial").val(matericlasalltable.row(matrclaRow).data()[4]+" "+matericlasalltable.row(matrclaRow).data()[5]);
	       	$(row).addClass('active');
	       }
	 }
	/* //查询按钮
	 $("#smasubsubclasselect").click(function(){
	 	$(".overlay").show();
	 	$("#table1Save").hide();
	 	 matericlasalltable.row().remove().draw();
	 	$("#subsubclaqueydialog").show();
	 	matrclaRow=null;
	 	  });*/
	 //选中返回
	 $("#confrimback").click(function(){
	 	if(matrclaRow==null){alert("请选中一行");return;}
	 	$("#closesubsub").click();
	 	  });
	 //取消
	 $("#closesubsub").click(function(){
	 	$("#subsubclaqueydialog").hide();
	 	$("#table1Save").show();
	 	$("#subsubclassname").val('');
	 	matrclaRow=null;
	 	  });
	 //原料细类查询    
	 $("#smasubsubclasselect").click(function(){
	 	   matericlasalltable.row().remove().draw();
	 		var xileiname= $("#sMaterial").val().trim();
	 		if(xileiname==''|| xileiname==null){alert("请输入原料细类名称关键字,不要代码");return;}  
	 		$(".overlay").show();
		 	$("#table1Save").hide();
		 	$("#subsubclaqueydialog").show();
	 		getmaterialclassAll(xileiname);
	 	  });
	 function getmaterialclassAll(name){    
	     data={
	     		materialName:name
	 	     };
	 	    var jsonstr = JSON.stringify(data);
	 		$.post("EntAction_getmatriclall?&ts="   
	 				+ new Date().getTime(), {
	 					 data : jsonstr 
	 				}, function(rdata) {
	 					
	 					if(rdata.data.length>0){
	 						
	 					
	 					for ( var i = 0; i <rdata.data.length; i++) {
	 						matericlasalltable.row.add([
	 						                       rdata.data[i].classCode,rdata.data[i].className,
	 						                       rdata.data[i].subclassCode,rdata.data[i].subclassName,
	 						                       rdata.data[i].materialCode,rdata.data[i].materialName
	 						                        ]);
	 					}
	 					matericlasalltable.draw();
	 					}
	 				}, 'json');
	 }	 
	 
	 $("#cleardate").click(function(){
			
			$("#sReportingDate").val('');   
			
		}); 
});