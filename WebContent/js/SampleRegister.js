//@ sourceURL=SampleRegister.js  
$(document).ready(function(){
	var curTable1Row = null;
	var curTable2Row = null;
	var curLimitTableRow = null;
	
	var table1Operation = 0;   //0:新增  1：修改
	var table2Operation = 0;   //0:新增  1：修改
	var limitTableOperation = 0;    //0:新增  1：修改
	
	initProcessStatusSelect();
	initStringSelect("SelectDataAction_getLabApplyKind","aApplyKind","aApplyKind-select");
	initStringSelect("SelectDataAction_getLabSamplePhysicalState","aSampleState","aSampleState-select");
	initStringSelect("SelectDataAction_getLabSampleDisposal","aSampleDisposal","aSampleDisposal-select");
	initStringSelect("SelectDataAction_getLabSamplePreservation","aSamplePreservation","aSamplePreservation-select");
	
	searchItem("SearchSelectAction_getLabSampleKind", "aSampleKind","aSampleKind-search");
	searchItem("SearchSelectAction_getLabApplyDept", "aApplyDept","aApplyDept-search");
	searchItem("SearchSelectAction_getLabAppliant", "aAppliant","aAppliant-search");
		
	
	var table1Columns = [
	                     {"data": "declNo"},
	                     {"data": "declDate"},
	                     {"data": "countryName"},
	                     {"data": "entName"},
	                     {"data": "productData"},
	                     {"data": "certType"},
	                     {"data": "ifSampling"},
	                     {"data": "processStatus"},
	                     ];
	
	var table2Columns = [
	                     {"data": "declProductDetailID"},
	                     {"data": "productCode"},
	                     {"data": "productName"},
	                     {"data": "baseName"},
	                     {"data": "ifSampling"},
	                     {"data": "weight"},
	                     {"data": "values_USD"},
	                     ];
	
	var table3Columns = [
	                     {"data": "declProductDetailID"},
	                     {"data": "itemCode"},
	                     {"data": "itemName"},
	                     {"data": "limitReq"},
	                     {"data": "sendLabFlg"},
	                     {"data": "labFlg"},
	                     {"data": "labDataUnit"},
	                     {"data": "testLabName"},
	                     {"data": "ifSet"},
	                     ];

	var table4Columns = [
	                     {"data": "labSampleID"},
	                     {"data": "declProductDetailID"},
	                     {"data": "sampleName"},
	                     {"data": "copyCount"},
	                     {"data": "sampleCount"},
	                     {"data": "countUnit"},
	                     {"data": "sampleRegMode"},
	                     {"data": "applyNo"},
	                     {"data": "sampleNo"},
	                     ];

	var table5Columns = [
	                     {"data": "labSampleItemID"},
	                     {"data": "labSampleID"},
	                     {"data": "sampleName"},
	                     {"data": "itemCode"},
	                     {"data": "itemName"},
	                     {"data": "lrpItemNo"},
	                     {"data": "lrpItemName"},
	                     {"data": "lrpTestStd"},
	                     {"data": "labDeptName"},
	                     ];
	
	
	var table1_1Columns = [
	                     {"data": "labItemMatchID"},
	                     {"data": "itemCode"},
	                     {"data": "itemName"},
	                     {"data": "lrpItemID"},
	                     {"data": "lrpItemName"},
	                     {"data": "lrpTestStd"},
	                     {"data": "labDeptName"},
	                     ];

	
	var table1_2Columns = [
	                     {"data": "declProductItemID"},
	                     {"data": "itemCode"},
	                     {"data": "itemName"},
	                     {"data": "fromWhere"},
	                     {"data": "itemType"},
	                     {"data": "limitReq"},
	                     {"data": "ifSet",
	                    	 "render": function ( data, type, full, meta ) {
	                    	       return data === '1'  ? '是' : '否';
	                    	    }
	                     },
	                     {"data": "itemifMatched",
	                    	 "render": function ( data, type, full, meta ) {
	                    	       return data === '1'  ? '是' : '否';
	                    	    }},
	                     ];
	
	
	/* ------------- table1响应事件 ------------------ */
	
	var table1 = $('#table1').DataTable( {
	      "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "ajax":{
			  url : "SampleRegisterAction_getDeclInfoForProcess",
			  type : "GET",
			  data : function(d){
				  d.data = getTable1RequestParam();
			  }
		  },
	      "columns": table1Columns,
	  } );
	
	$('#table1').on( 'draw.dt', drawTable1CB);
	 
	$('#table1 tbody').on("click", "tr", clickTable1Row );
	
	$("#search1").click(searchTable1Btn);
	
	$("#cancel1").click(cancelTable1Btn);
	
	$("#add1").click(addTable1Btn);

	$("#searchTable1").click(searchTable1);
	
	$("#addTable1").click(addTable1);

	/*$("#edit1").click(editTable1Btn);
	
	$("#delete1").click(deleteTable1);
	
	
	$("#saveTable1").click(saveTable1);*/
	
	$("#closeTable1Search").click(function(){
		clearAlertDiv("table1Search");
		$(".overlay").hide();
		$("#table1Search").hide();
	});
	
	$("#addTable1").click(function(){
		clearAlertDiv("table1Add");
	});
	
	$("#closeTable1Add").click(function(){
		clearAlertDiv("table1Add");
		$(".overlay").hide();
		$("#table1Save").hide();
	});
	
	$("#deleteTable1-1").click(deleteLabItemMatched);

	/* ------------- table2响应事件 ------------------ */
	
	var table2 = $('#table2').DataTable( {
		  "info" : false,
		  "paging" : false,
		  "lengthChange" : false,
	      "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "ajax":{
			  url : "SampleRegisterAction_getDeclProduct",
			  type : "GET",
			  data : function(d){
				  var declNo = 0;
				  if(curTable1Row != null){
					  declNo = table1.row(curTable1Row).data().declNo;
				  }
				  var data = {
						  declNo : declNo,
						  showSamplingItemFlg : true,
						  showNotLabFlg : false
				  }
				  data = JSON.stringify(data);
				  return data;
			  }
		  },
	      "columns": table2Columns,
	  } );
	
	
	$('#table2').on( 'draw.dt', drawTable2CB);
	
	$('#table2 tbody').on("click", "tr", clickTable2Row );

/* ------------- table3响应事件 ------------------ */

	var table3 = $('#table3').DataTable( {
		  "info" : false,
		  "paging" : false,
		  "lengthChange" : false,
	      "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "ajax":{
			  url : "SampleRegisterAction_getDeclProductItem",
			  type : "GET",
			  data : function(d){
				  if(curTable2Row == null){
					  return {declNo : 0};
				  }else{
					  return {declNo : table2.row(curTable2Row).data().declProductDetailID};
				  }
			  }
		  },
	      "columns": table3Columns,
	  } );
	
/* ------------- table4响应事件 ------------------ */
	
	var table4 = $('#table4').DataTable( {
		  "info" : false,
		  "paging" : false,
		  "lengthChange" : false,
	      "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "ajax":{
			  url : "SampleRegisterAction_getLabSampleByProduct",
			  type : "GET",
			  data : function(d){
				  if(curTable2Row == null){
					  return {declNo : 0};
				  }else{
					  return {declNo : table2.row(curTable2Row).data().declProductDetailID};
				  }
			  }
		  },
	      "columns": table4Columns,
	  } );
	
/* ------------- table5响应事件 ------------------ */
	
	var table5 = $('#table5').DataTable( {
		  "info" : false,
		  "paging" : false,
		  "lengthChange" : false,
	      "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "ajax":{
			  url : "SampleRegisterAction_getLabSampleItemByProduct",
			  type : "GET",
			  data : function(d){
				  if(curTable2Row == null){
					  return {declNo : 0};
				  }else{
					  return {declNo : table2.row(curTable2Row).data().declProductDetailID};
				  }
			  }
		  },
	      "columns": table5Columns,
	  } );
	
	
	 function getTable1RequestParam(){
		 var declNo =$("#sDeclNo").val().trim();
		 var finishFlg = 1;
		 if(declNo == null || declNo == undefined){
			 declNo = "";
		 }
		 var processStatus = $("#sProcessStatus").val();
		 if(processStatus == null || processStatus == undefined ||
				 processStatus ==""|| processStatus == "待登记"){
			 finishFlg = 1;
		 }else if(processStatus == "已登记"){
			 finishFlg = 2;
		 }
		  var data = {
				 declNo : declNo,
				 finishFlg : finishFlg
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
	
	function clickTable1Row() {
		var row = this;
		if ($(row).hasClass('active')) {
			curTable1Row = null;
			$(row).removeClass('active');
		} else {
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
	  
	 function cancelTable1Btn(){
		 if(curTable1Row == null){
			 alert("请先选择一条报检记录");
			 return;
		 }
		 var declNo = table1.row(curTable1Row).data().declNo;			 
		  $.get("SampleRegisterAction_cancelCurrentProcess?&ts="
					+ new Date().getTime(), {
						data : declNo
			}, function(rdata) {
				if(rdata.data ==""){
					alert("操作成功");
					table1.draw();
				}else{
					alert(rdata.data);
				}
			}, 'json');
	 }
	 
     function addTable1Btn(){
    	 if(curTable2Row == null){
    		 alert("请选择需要新增样品的出口产品！");
    		 return;
    	 }
    	 var declNo = table1.row(curTable1Row).data().declNo;			
    	 var declProductDetailID = table2.row(curTable2Row).data().declProductDetailID;			
    	  $.post("SampleRegisterAction_checkIfProcessOperate?&ts="
					+ new Date().getTime(), {
						data : declNo
			}, function(rdata) {
				if(rdata.data !=""){
					alert(rdata.data);
				}else{
					$.post("SampleRegisterAction_checkItemIfSampling?&ts="
							+ new Date().getTime(), {
								data : declProductDetailID
					}, function(rdata) {
						if(rdata.data !=""){
							alert(rdata.data);
						}else{
							$(".overlay").show();
					    	$("#table1Add").show();
					    	getLabApply();
					    	getSampleInfo();
					    	getLabItemMatched();
					    	getDeclProductItem();
						}
					}, 'json');
				}
			}, 'json');
     }
     
     function editTable1Btn(){
 		  table1Operation = 1;
 		  getTable1ItemDetail();
 	 }
	  
	 function searchTable1(){
		 table1.draw();
		 $("#closeTable1Search").click();
	 }
	 
	 
	 function getLabApply(){
		 var data = {
			declNo : table1.row(curTable1Row).data().declNo,
			declProductDetailID :  table2.row(curTable2Row).data().declProductDetailID		
		 };
		 data = JSON.stringify(data);
		 $.get("SampleRegisterAction_getLabApply?&ts="
					+ new Date().getTime(), {
						data : data
			}, function(rdata) {
				if(rdata.data.length >  0){
					setLabApply(rdata.data);
				}else{
					alert("获取报验信息数据失败");
				}
			}, 'json');
	}
	 
	 function setLabApply(data){
		 $("#aApplyKind").val(data.applyKind);
		 $("#aSampleKind").val(data.sampleKind);
		 $("#aApplyDept").val(data.applyDept);
		 $("#aAppliant").val(data.appliant);
		 $("#aSampleState").val(data.sampleState);
		 $("#aSampleDisposal").val(data.sampleDisposal);
		 $("#aSamplePreservation").val(data.samplePreservation);
		 $("#aRemarks").val(data.remarks);
	 }
	 
 	 function getSampleInfo(){
 		 var data = {
 				sampleID : 0,
 				product : table2.row(curTable2Row).data().productCode,
 				declProductDetailID :  table2.row(curTable2Row).data().declProductDetailID		
 			 };
 			 data = JSON.stringify(data);
 			 $.get("SampleRegisterAction_getLabSampleInfoBySampleID?&ts="
 						+ new Date().getTime(), {
 							data : data
 				}, function(rdata) {
 					if(rdata.data.length >  0){
 						setSampleInfo(rdata.data);
 					}else{
 						alert("获取样品信息数据失败");
 					}
 				}, 'json');
 	 }
 	 
 	 function setSampleInfo(){
 		 $("#aSampleName").val(sampleName);
 		 $("#aCopyCount").val(copyCount);
 		 $("#aSampleRemarks").val(sampleRemarks);
 		 $("#aSampleCount").val(sampleCount);
 		 $("#aCountUnit").val(countUnit);
 	 }
 	 
 	 function getLabItemMatched(){
 		 	var data = {
  				sampleID : 0,
  				product : table2.row(curTable2Row).data().productCode,
  				declProductDetailID :  table2.row(curTable2Row).data().declProductDetailID		
  			 };
  			 data = JSON.stringify(data);
  			 $.get("SampleRegisterAction_getLabSampleInfoBySampleID?&ts="
  						+ new Date().getTime(), {
  							data : data
  				}, function(rdata) {
  					setLabItemMatched(rdata);
  				}, 'json');
 	 }
 	 
 	var table1_1 = $('#table1-1').DataTable( {
		  "info" : false,
		  "paging" : false,
		  "lengthChange" : false,
		  "data": data.data, 		      
	      "columns": table1_1Columns,
	      "columnDefs": [
	                     {
	                         "targets": [ 0 ],
	                         "visible": false,
	                     }
	                 ]
		  } );
	
 	$('#table1-1').on( 'draw.dt', drawTable1_1CB);
	 
	$('#table1-1 tbody').on("click", "tr", clickTable1_1Row );
 	 
 	var curTable1_1Row = null;
 	
 	function drawTable1_1CB() {
 		curTable1_1Row = null;
		 if(table1_1.rows().data().length > 0){
			  var dtRow = null;
			  var node = null;
			  if(curTable1_1Row != null){
				  dtRow = table1_1.row($(curTable1Row).context._DT_RowIndex);
				  node = dtRow.node();
				  if(node != null){
					  $(node).click();
					  return;
				  }
			  }
			  dtRow = table1_1.row(0);
			  node = dtRow.node();
			  $(node).click();
		  }else{
			  curTable1_1Row = null;
		  }
	 } 
	
	function clickTable1_1Row(){
		 var row = this;
		  if ( $(row).hasClass('active') ) {
			    curTable1_1Row = null;
	            $(row).removeClass('active');
	        }
	        else {
	        	curTable1_1Row = row;
	        	table1.$('tr.active').removeClass('active');
	            $(row).addClass('active');
	        }
	}
 	
 	function setLabItemMatched(data){
 		table1_1.clear();
 		table1_1.rows.add(data.data).draw();
 	}
 	
	
	function deleteLabItemMatched(){
		if(curTable1_1Row == null){
			alert("请选择要删除的拟送检项目!");
			return;
		}
		var data = table1_1.row(curTable1_1Row).data().labItemMatchID;
		$.post("SampleRegisterAction_delLabItemMatchedForNewSample", {
			data : data
		}, function(rdata) {
			if(rdata.data == "1"){
				alert("删除成功！");
				table1_1.row(curTable1_1Row).remove().draw();
			}else{
				alert(rdata.data);
			}
		}, 'json')
	}
	
 	
 	 function getDeclProductItem(){
 		 	var data = {
 				declProductDetailID :table2.row(curTable2Row).data().declProductDetailID,
 				showSamplingItemFlg : true,
 				showNotLabFlg :  false   /////////??????	
   			 };
   			 data = JSON.stringify(data);
			$.get("SampleRegisterAction_getDeclProductItem"
						+ new Date().getTime(), {
							data : data
				}, function(rdata) {
					setDeclProductItem(rdata);
				}, 'json');
 	 }
 	 
 	function setDeclProductItem(data){
 		if(table1_2 != null){
 			table1_2.clear();
 		}
 		table1_2 = $('#table1-2').DataTable( {
 			  "info" : false,
 			  "paging" : false,
 			  "lengthChange" : false,
 			  "data": data.data, 		      
 		      "columns": table1_2Columns,
 		  } );
 		
 	}
	 
	 function getTable1ItemDetail(){
		  if(curTable1Row == null){
			  alert("请先选择一条记录");
			  return;
		  }
		  $(".overlay").show();
		  $("#table1Save").show();
		  
		  var additiveControlID = table1.row(curTable1Row).data().additiveControlID;
		  $.get("AdditiveCtrlAction_getAdditiveControlDetailByID?&ts="
					+ new Date().getTime(), {
						additiveControlID : additiveControlID
			}, function(rdata) {
				setTable1ItemDetail(rdata);
			}, 'json');
	 }
	 
	 function setTable1ItemDetail(rdata){
		  $("#sAdditive").val(rdata.additiveName);
		  $("#sProductClass").val(rdata.productClassCode+" "+rdata.productClassName);
		  $("#sProductSubclass").val(rdata.productSubclassCode+" "+rdata.productSubclassName);
		  $("#sMaterialClass").val(rdata.materialClassCode+" "+rdata.materialClassName);
		  $("#sMaterialSubclass").val(rdata.materialSubclassCode+" "+rdata.materialSubclassName);
		  $("#sMaterial").val(rdata.materialCode+" "+rdata.materialName);
		  $("#sCountry").val(rdata.countryCode+" "+rdata.countryName);
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
			  var additiveControlID = table1.row(curTable1Row).data().additiveControlID;
			  data = saveTable1Param(additiveControlID);
		  }
		  if(!data){
			  return;
		  }
		  $.post("AdditiveCtrlAction_saveAdditiveControl?&ts="
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
	 
	 function saveTable1Param(additiveControlID){
		  var data = null;
		  var additiveName = $("#sAdditive").val().trim();
		  if(additiveName == "" || additiveName == null){
			  alert("请输入辅料！");
			  return false;
		  }
		  var countryCode = getSelectValue("sCountry");
		  if(countryCode == ""){
			  alert("请输入国家或地区！");
			  return false;
		  }
		  data = {
				    additiveControlID : additiveControlID,
				    additiveName : additiveName,
				    productClassCode : getSelectValue("sProductClass"),
				    productSubclassCode : getSelectValue("sProductSubclass"),
				    materialClassCode : getSelectValue("sMaterialClass"),
				    materialSubclassCode : getSelectValue("sMaterialSubclass"),
				    materialCode : getSelectValue("sMaterial"),
				    countryCode : countryCode,
		  };
		  if(!checkJsonParam(data)){
			  return false;
		  }
		  var jsonstr = JSON.stringify(data);
		  return jsonstr;
	 }
	 
	  function deleteTable1(){
		  if(curTable1Row == null){
			  alert("请先选择一条要删除的记录！");
			  return;
		  }
		  if(confirm("确认要删除该条记录?")){
			  var additiveControlID  = table1.row(curTable1Row).data().additiveControlID ;
			  $.post("AdditiveCtrlAction_delAdditiveControl?&ts="
						+ new Date().getTime(), {
							additiveControlID : additiveControlID
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
	
	
	//点击布控项目行的回调
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
		table3.draw();
		table4.draw();
		table5.draw();
	}
	
	
	 //新增布控项目
	 function addTable2(){
		  if(curTable1Row == null){
			  alert("请先选择一条记录");
			  return;
		  }
		 
		  table2Operation = 0;
		  $(".overlay").show();
		  $("#table2Save").show();
	 }
	
	 //布控项目详情
	 function getTable2ItemDetail(){
		  if(curTable1Row == null){
			  alert("请先选择一条布控规则");
			  return;
		  }
		  if(curTable2Row == null){
			  alert("请先选择一条布控项目");
			  return;
		  }
		  table2Operation = 1;
		  $(".overlay").show();
		  $("#table2Save").show();
		  
		  var additiveControlItemID = table2.row(curTable2Row).data().additiveControlItemID;
		  $.get("AdditiveCtrlAction_getAdditiveControlItemDetailByID?&ts="
					+ new Date().getTime(), {
						additiveControlItemID : additiveControlItemID
			}, function(rdata) {
				setTable2ItemDetail(rdata);
			}, 'json');
		  
	 }
	 
	 //设置布控项目详情
	  function setTable2ItemDetail(rdata){
		  var itemDetail = rdata.ctrlItem;
		  var itemLimit = rdata.itemLimit;
		  $("#sDetectionItem").val(itemDetail.itemCode+" "+itemDetail.itemName);
		  $("#sDetectionStd").val(itemDetail.detectionStd);
		  $("#sSamplingRatio").val(itemDetail.samplingRatio);
		  $("#sLimitType").val(itemDetail.limitType);
		  $("#sDetectionLimit").val(itemDetail.detectionLimit);
		  $("#sLimitUnit").val(itemDetail.limitUnit);
		  limitTable.rows.add(itemLimit).draw();
	  }
	 
	 
	 function saveTable2(){
		  var data = null;
		  if(table2Operation == 0){
			  data = saveTable2Param(0);			  
		  }else{
			  var additiveControlItemID = table2.row(curTable2Row).data().additiveControlItemID;
			  data = saveTable2Param(additiveControlItemID);
		  }
		  if(!data){
			  return;
		  }
		  $.post("AdditiveCtrlAction_saveAdditiveControlItem?&ts="
					+ new Date().getTime(), {
			    data : data
			}, function(rdata) {
				if(rdata.data == "true"){
					alert("保存成功！");
					if(table2Operation == 0){
						curTable2Row = null;     //保证新增后选中第一条新增的记录
						table2.draw();
						$("#closeTable2Save").click();
					}else{
						table2.draw(false);
						$("#closeTable2Save").click();
					}
				}else{
					alert(rdata.data);
				}
			}, 'json');
	 }
		 
		 //保存布控项目的参数
		 function saveTable2Param(additiveControlItemID){
			  var additiveControlID = table1.row(curTable1Row).data().additiveControlID;
			  var detectionItem = $("#sDetectionItem").val().trim();
			  if(detectionItem == null || detectionItem == ""){
				  alert("请选择检测项目");
				  return;
			  }else if(!/\d.+\s\D.+/.test(detectionItem)){
				  alert("请输入合法的数据格式！");
				  return false;
			  }
			  var detectionStd = $("#sDetectionStd").val().trim();
			  var samplingRatio = $("#sSamplingRatio").val().trim();
			  if(samplingRatio == null || samplingRatio == ""){
				  alert("请输入抽检模式");
				  return;
			  }
			  var limitType = $("#sLimitType").val().trim();
			  if(limitType == null || limitType == ""){
				  alert("请输入限量类型");
				  return;
			  }
			  var detectionLimit = $("#sDetectionLimit").val().trim();
			  if(detectionLimit == null || detectionLimit == ""){
				  alert("请输入默认限量值");
				  return;
			  }
			  var limitUnit = $("#sLimitUnit").val().trim();
			  if(limitUnit == null || limitUnit == ""){
				  alert("请输入限量单位");
				  return;
			  }
			  var itemLimitList = new Array();
			  for(var i = 0 ; i < limitTable.data().length; i++){
				  var row = limitTable.data()[i];
				  var itemLimit = {countryCode : row.countryCode,
						           countryName : row.countryName,
						          detectionLimit : row.detectionLimit,
						          limitUnit : row.limitUnit
				  
				  };
				  itemLimitList[i] = itemLimit;
			  }
			  var data = {
					    additiveControlID : additiveControlID,
					    additiveControlItemID : additiveControlItemID,
					    itemCode : detectionItem.split(" ")[0],
					    itemName : detectionItem.split(" ")[1],
					    detectionStd : detectionStd ,
					    samplingRatio : samplingRatio,
					    limitType : limitType,
					    detectionLimit : detectionLimit,
					    limitUnit :limitUnit,
					    itemLimitList : itemLimitList
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
			  var additiveControlItemID = table2.row(curTable2Row).data().additiveControlItemID;
			  $.post("AdditiveCtrlAction_delAdditiveControlItem?&ts="
						+ new Date().getTime(), {
							additiveControlItemID : additiveControlItemID
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
	
	

	  
	  
	  
	function initProcessStatusSelect(){
		var source = ["待登记","已登记"];
		cus_autocomplete(source, "sProcessStatus", "sProcessStatus-select", null, null);
		$("#sProcessStatus").val("待登记");
	}
});