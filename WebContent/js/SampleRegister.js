//@ sourceURL=SampleRegister.js  
$(document).ready(function(){
	var curTable1Row = null;
	var curTable2Row = null;
	var curTable4Row = null;
	var curTable5Row = null;
	
	
	var sampleID = 0;
	
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

	
	var table1_2_1Columns = [
		                     {"data": "testItemID"},
		                     {"data": "itemName"},
		                     {"data": "standardName"},
		                     {"data": "deptSimpleName"},
		                     {"data": "productCategoryName"},
		                     {"data": "testPeriod"},
		                     {"data": "testFee"},
		                     ];

	var table1_2_2Columns = [
		                     {"data": "itemCodeSub"},
		                     {"data": "itemNameSub"},
		                     {"data": "itemMatched"},
		                     ];
	
	var table1_2_3Columns = [
		                     {"data": "labItemMatchID"},
		                     {"data": "itemCode"},
		                     {"data": "itemName"},
		                     {"data": "lrpItemID"},
		                     {"data": "lrpItemName"},
		                     {"data": "lrpItemTestStd"},
		                     {"data": "labDeptName"},
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
	
	$("#register1").click(sampleRegAuto);

	$("#register1").click(sampleRegAuto);
	

	$("#searchTable1").click(searchTable1);
	
	$("#addTable1").click(function(){
		clearAlertDiv("table1Add");
		addTable1Btn();
		
	});
	
	$("#saveTable1").click(saveTable1);
	
	$("#closeTable1Search").click(function(){
		clearAlertDiv("table1Search");
		$(".overlay").hide();
		$("#table1Search").hide();
	});
	
	
	$("#closeTable1Add").click(function(){
		clearAlertDiv("table1Add");
		curTable1_1Row = null;
		curTable1_2Row = null;
		$(".overlay").hide();
		table4.draw();
		table5.draw();
	});
	
	$("#deleteTable1-1").click(deleteLabItemMatched);
	
	$("#itemMatch").click(matchItem);
	
	$("#newItemMatch").click(matchNewItemBtn);
	
	$("#setItem").click(setItemDetailBtn);
	
	function sampleRegAuto(){
		if(curTable1Row == null){
			alert("请选择要自动登记的报检号");
			return;
		}
		var declNo = table1.row(curTable1Row).data().declNo;
		$.post("SampleRegisterAction_checkIfProcessOperate?&ts="
				+ new Date().getTime(), {
					data : declNo
		}, function(rdata) {
			if(rdata.data !=""){
				alert(rdata.data);
			}else{
				$.post("SampleRegisterAction_sampleRegAuto?&ts="
						+ new Date().getTime(), {
							data : declNo
				}, function(rdata) {
					if(rdata.data !=""){
						alert(rdata.data);
					}else{
						alert("样品自动登记成功！");
						table4.draw();
						table5.draw();
					}
				}, 'json');
			}
		}, 'json');
	}
	
	function matchNewItemBtn(){
		curTable1_2_1Row = null;
		if(curTable1_2Row == null){
			alert("请选择要匹配的项目记录");
			return;
		}
		$("#sItemName").val(table1_2.row(curTable1_2Row).data().itemName);
    	$("#table1-2Match").show();

	}
	
	function setItemDetailBtn(){
		curTable1_2_2Row = null;
		curTable1_2_3Row = null;
		curTable1_2_4Row = null;
		if(curTable1_2Row == null){
			alert("请选择要项目细化的项目记录");
			return;
		}
		if(table1_2.row(curTable1_2Row).data().ifSet != "1"){
			alert("不是集合项目，不能细化操作");
			return;
		}
		$("#table1-2Set").show();
		getItemSub();
		getItemSubMatched();
	}
	
	function getItemSub(){
		var data = {
				itemCode : table1_2.row(curTable1_2Row).data().itemCode,
				productCode : table2.row(curTable2Row).data().productCode
		};
		data = JSON.stringify(data);
		$.get("SampleRegisterAction_getItemSubByDeclItem?&ts="
				+ new Date().getTime(), {
					data : data
				}, function(rdata) {
					table1_2_2.clear();
					table1_2_2.rows.add(data.data).draw();
				}, 'json');
		
	}
	
	
	function getItemSubMatched(){
		var data = {
				itemCode : table1_2.row(curTable1_2Row).data().itemCode,
				declProductItemID : table1_2.row(curTable2Row).data().DeclProductItemID
		};
		data = JSON.stringify(data);
		$.get("SampleRegisterAction_getItemSubMatchedForDeclItem?&ts="
				+ new Date().getTime(), {
					data : data
				}, function(rdata) {
					table1_2_3.clear();
					table1_2_3.rows.add(data.data).draw();
				}, 'json');
		
	}
	
	function matchNewItem(){
		if(curTable1_2_1Row == null){
			alert("请选择要匹配的LMIS项目");
			return;
		}
		var data = {
				declProductDetailID : table2.row(curTable2Row).data().declProductDetailID,
					itemCode: table1_2.row(curTable1_2Row).data().itemCode,
					lrpItemID:table1_2_1.row(curTable1_2_1Row).data().lrpItemID,
					lrpItemName:table1_2_1.row(curTable1_2_1Row).data().lrpItemName,
					lrpItemTestStd:	table1_2_1.row(curTable1_2_1Row).data().lrpItemTestStd,
					labDeptName:table1_2_1.row(curTable1_2_1Row).data().labDeptName,
					declProductItemID:table1_2.row(curTable1_2Row).data().declProductItemID
		}
		data = JSON.stringify(data);
		$.post("SampleRegisterAction_saveLabItemMatched?&ts="
				+ new Date().getTime(), {
					data : data
		}, function(rdata) {
			if(rdata.data !="true"){
				alert("匹配失败");
			}else{
				alert("匹配成功");
				$("#closeTable1-2-1").click();
		    	getLabItemMatched();
			}
		}, 'json');
	}

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
						  showSamplingItemFlg : 1,
						  showNotLabFlg : 0
				  }
				  data = JSON.stringify(data);
				  return data;
			  }
		  },
	      "columns": table2Columns,
	  } );
	
	
	$('#table2').on( 'draw.dt', drawTable2CB);
	
	$('#table2 tbody').on("click", "tr", clickTable2Row );
	
	function drawTable2CB() {
		 curTable2Row = null;
		 if(table2.rows().data().length > 0){
			  var dtRow = null;
			  var node = null;
			  dtRow = table2.row(0);
			  node = dtRow.node();
			  $(node).click();
		  }
	 } 
	
	function clickTable2Row() {
		var row = this;
		if ($(row).hasClass('active')) {
			curTable2Row = null;
			$(row).removeClass('active');
		} else {
			curTable2Row = row;
			table2.$('tr.active').removeClass('active');
			$(row).addClass('active');
		}
	}

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
					  return {data : 0};
				  }else{
					  return {data : table2.row(curTable2Row).data().declProductDetailID};
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
					  return {data : 0};
				  }else{
					  return {data : table2.row(curTable2Row).data().declProductDetailID};
				  }
			  }
		  },
	      "columns": table4Columns,
	  } );
	
	$('#table4').on( 'draw.dt', drawTable4CB);
	 
	$('#table4 tbody').on("click", "tr", clickTable4Row );
	
	$("#divide4").click(function(){
		if(curTable4Row == null){
			alert("请选择要分样的样品记录");
			return;
		}
		 var declNo = table1.row(curTable1Row).data().declNo;			
    	  $.post("SampleRegisterAction_checkIfProcessOperate?&ts="
					+ new Date().getTime(), {
						data : declNo
			}, function(rdata) {
				if(rdata.data !=""){
					alert(rdata.data);
				}else{
					$(".overlay").show();
					$("#table4Divide").show();
				}
			}, 'json');
	});
	
	$("#deleteSample4").click(function(){
		if(curTable4Row == null){
			alert("请选择要删除的样品");
			return;
		}
		var labSampleID = table4.row(curTable4Row).data().labSampleID;
		$.post("SampleRegisterAction_delLabSample?&ts="
				+ new Date().getTime(), {
					data : labSampleID
		}, function(rdata) {
			if(rdata.data !="true"){
				alert(rdata.data);
			}else{
				alert("删除成功");
				table4.draw();
				table5.draw();
			}
		}, 'json');
	});
	
	$("#deleteItem4").click(function(){
		if(curTable5Row == null){
			alert("请选择要删除的样品项目");
			return;
		}
		var labSampleItemID = table4.row(curTable4Row).data().labSampleItemID;
		$.post("SampleRegisterAction_DelLabSampleItem?&ts="
				+ new Date().getTime(), {
					data : labSampleItemID
		}, function(rdata) {
			if(rdata.data !="true"){
				alert(rdata.data);
			}else{
				alert("删除成功");
				table5.draw();
			}
		}, 'json');
	});
	
	$("#saveTable4Divide").click(saveTable4Divide);
	
	$("#closeTable4Divide").click(function(){
		clearAlertDiv("table4Divide");
		$(".overlay").hide();
    	$("#table4Divide").hide();
	});
	
	function saveTable4Divide(){
		var dividedSampleNum = $("#dividedSampleNum").val().trim();
		var reg =  /^\d+$/;
		if(!reg.test(dividedSampleNum)){
			alert("分样数量必须为整数");
			return;
		}
		var labSampleID = table4.row(curTable4Row).data().labSampleID;
		var data = {
				labSampleID : labSampleID,
				dividedSampleNum : dividedSampleNum
		}; 
		data = JSON.stringify(data);
		$.post("SampleRegisterAction_divideLabSample?&ts="
					+ new Date().getTime(), {
						data : data
			}, function(rdata) {
				if(rdata.data !=""){
					alert(rdata.data);
				}else{
					$("#closeTable1Divide").click();
					table4.draw();
					table5.draw();
				}
			}, 'json');
	}
	
	function drawTable4CB() {
		 curTable4Row = null;
		 if(table4.rows().data().length > 0){
			  var dtRow = null;
			  var node = null;
			  dtRow = table4.row(0);
			  node = dtRow.node();
			  $(node).click();
		  }
	 } 
	
	function clickTable4Row() {
		var row = this;
		if ($(row).hasClass('active')) {
			curTable4Row = null;
			$(row).removeClass('active');
		} else {
			curTable4Row = row;
			table4.$('tr.active').removeClass('active');
			$(row).addClass('active');
		}
	}
	
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
					  return {data : 0};
				  }else{
					  return {data : table2.row(curTable2Row).data().declProductDetailID};
				  }
			  }
		  },
	      "columns": table5Columns,
	  } );
	
	
	$('#table5').on( 'draw.dt', drawTable5CB);
	 
	$('#table5 tbody').on("click", "tr", clickTable5Row );
	
	function drawTable5CB() {
		 curTable5Row = null;
		 if(table5.rows().data().length > 0){
			  var dtRow = null;
			  var node = null;
			  dtRow = table5.row(0);
			  node = dtRow.node();
			  $(node).click();
		  }
	 } 
	
	function clickTable5Row() {
		var row = this;
		if ($(row).hasClass('active')) {
			curTable5Row = null;
			$(row).removeClass('active');
		} else {
			curTable5Row = row;
			table5.$('tr.active').removeClass('active');
			$(row).addClass('active');
		}
	}
	
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
    	 sampleID = 0;
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
	 
		/* ------------- table1_1响应事件 ------------------ */
 	 
	 	var curTable1_1Row = null;
	 
	 	var table1_1 = $('#table1-1').DataTable( {
			  "info" : false,
			  "paging" : false,
			  "lengthChange" : false,
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
		        	table1_1.$('tr.active').removeClass('active');
		            $(row).addClass('active');
		        }
		}
	 	
	 
		/* ------------- table1_2响应事件 ------------------ */
	 	 
	 	 var curTable1_2Row = null;
	 	 
	 	var table1_2 = $('#table1-2').DataTable( {
			  "info" : false,
			  "paging" : false,
			  "lengthChange" : false,
		      "columns": table1_2Columns,
		  } );
	 	 
	 	$('#table1-2').on( 'draw.dt', drawTable1_2CB);
		 
		$('#table1-2 tbody').on("click", "tr", clickTable1_2Row );
		
		function drawTable1_2CB() {
	 		curTable1_2Row = null;
			 if(table1_2.rows().data().length > 0){
				  var dtRow = null;
				  var node = null;
				  if(curTable1_2Row != null){
					  dtRow = table1_2.row($(curTable1Row).context._DT_RowIndex);
					  node = dtRow.node();
					  if(node != null){
						  $(node).click();
						  return;
					  }
				  }
				  dtRow = table1_2.row(0);
				  node = dtRow.node();
				  $(node).click();
			  }else{
				  curTable1_2Row = null;
			  }
		 } 
		
		function clickTable1_2Row(){
			 var row = this;
			  if ( $(row).hasClass('active') ) {
				    curTable1_2Row = null;
		            $(row).removeClass('active');
		        }
		        else {
		        	curTable1_2Row = row;
		        	table1_2.$('tr.active').removeClass('active');
		            $(row).addClass('active');
		        }
		}
		
	 /* ------------- table1_2_1响应事件 ------------------ */
		
		var curTable1_2_1Row = null;
		
		var table1_2_1 = $('#table1-2-1').DataTable( {
			  "info" : false,
			  "paging" : false,
			  "lengthChange" : false,
		      "deferRender": true,
			  "processing": true,
			  "serverSide": true,
			  "ajax":{
				  url : "SampleRegisterAction_getLabItemByQuery",
				  type : "GET",
				  data : function(d){
					  var itemName = $("#sItemName").val().trim();
					  if(itemName == null || itemName == ""){
						  alert("没有输入LMIS项目名称");
						  itemName = "";
					  }
					  var data = {
							  itemName : itemName,
							  labDeptName : $("#sLabDeptName").val().trim()
					  };
					  data = JSON.stringify(data);
					  return data;
				  }
			  },
		      "columns": table1_2_1Columns,
		  } );
		
		$('#table1-2-1').on( 'draw.dt', drawTable1_2_1CB);
		 
		$('#table1-2-1 tbody').on("click", "tr", clickTable1_2_1Row );
		
		$("#searchTable1-2-1").click(function(){
			table1_2_1.draw();
		});
		
		$("#confirmTable1-2-1").click(matchNewItem);
		
		$("#closeTable1-2-1").click(function(){
			clearAlertDiv("table1-2Match");
			$("#table1-2Match").hide();
		});
		
		
		function drawTable1_2_1CB() {
			curTable1_2_1Row = null;
			 if(table1_2_1.rows().data().length > 0){
				  var dtRow = null;
				  var node = null;
				  dtRow = table1_2_1.row(0);
				  node = dtRow.node();
				  $(node).click();
			  }
		 } 
		
		function clickTable1_2_1Row() {
			var row = this;
			if ($(row).hasClass('active')) {
				curTable1_2_1Row = null;
				$(row).removeClass('active');
			} else {
				curTable1_2_1Row = row;
				table1.$('tr.active').removeClass('active');
				$(row).addClass('active');
			}
		}
		
		
		/* ------------- table1_2_2响应事件 ------------------ */
		
		curTable1_2_2Row = null;
		
		var table1_2_2 = $('#table1-2-2').DataTable( {
			  "info" : false,
			  "paging" : false,
			  "lengthChange" : false,
			  "serverSide": false,
		      "columns": table1_2_2Columns,
		  } );
		
		$('#table1-2-2').on( 'draw.dt', drawTable1_2_4CB);
		 
		$('#table1-2-2 tbody').on("click", "tr", clickTable1_2_2Row );
		
		function drawTable1_2_2CB() {
			curTable1_2_2Row = null;
			 if(table1_2_2.rows().data().length > 0){
				  var dtRow = null;
				  var node = null;
				  dtRow = table1_2_2.row(0);
				  node = dtRow.node();
				  $(node).click();
			  }
		 } 
		
		function clickTable1_2_2Row() {
			var row = this;
			if ($(row).hasClass('active')) {
				curTable1_2_2Row = null;
				$(row).removeClass('active');
			} else {
				curTable1_2_2Row = row;
				table1.$('tr.active').removeClass('active');
				$(row).addClass('active');
			}
		}
		
		$("#confirmTable1-2-2").click(confirmMatchedItemSet);

		$("#deleteTable1-2-2").click(deleteMatchedItemSet);

		$("#closeTable1-2-2").click(closeMatchedItemSet);
		
		function confirmMatchedItemSet(){
			if(curTable1_2_2Row == null){
				alert("请先选择要匹配的子项目");
				return;
			}
			var ifMatched = table1_2_2.row(curTable1_2_2Row).data().itemMatched;
			var itemCode = table1_2_2.row(curTable1_2_2Row).data().itemCode;
			var productCode = table2.row(curTable2Row).data().productCode;
			var lrpItemNo = "0";
			var declProductDetailID = table2.row(curTable2Row).data().declProductDetailID;
			var declProductItemID = table1_2.row(curTable1_2Row).data().declProductItemID;
			var data;
			if(ifMatched == "匹配" && $("#reMatch").prop("checked") == false){
				
			}else{
				if(curTable1_2_4Row == null){
					alert("请选择所匹配的LMIS项目");
					return;
				}
				lrpItemNo = table1_2_4.row(curTable1_2_4Row).data().testItemID;
			}
			data = {
					itemCode : itemCode,
					productCode : productCode,
					lrpItemNo : lrpItemNo,
					declProductDetailID : declProductDetailID,
					declProductItemID : declProductItemID
			}
			saveLabItemMatchedForSubitem(data);
		}
		
		function deleteMatchedItemSet(){
			if(curTable1_2_3Row == null){
				alert("请先选择要删除的匹配项目");
				return;
			}
			if(confirm("确定要删除所匹配的项目吗？")){
				var labItemMatchID = table1_2_3.row(curTable1_2_3Row).data().labItemMatchID;
				$.post("SampleRegisterAction_delItemSubMatchedForDeclItem?&ts="
						+ new Date().getTime(), {
							data : labItemMatchID
				}, function(rdata) {
					if(rdata.data !="true"){
						alert("删除失败");
					}else{
						alert("删除成功");
						getItemSubMatched();
					}
				}, 'json');
			}
		}
		
		function closeMatchedItemSet(){
			clearAlertDiv("table1-2Set");
			$("#table1-2Set").hide();
			table1_1.draw();
		}
		
		function saveLabItemMatchedForSubitem(data){
			$.post("SampleRegisterAction_SaveLabItemMatchedForSubitem?&ts="
					+ new Date().getTime(), {
						data : data
			}, function(rdata) {
				if(rdata.data !="true"){
					alert(rdata.data);
				}else{
					alert("操作成功");
					getItemSubMatched();
				}
			}, 'json');
		}
		
		/* ------------- table1_2_3响应事件 ------------------ */
		
		var table1_2_3 = $('#table1-2-3').DataTable( {
			  "info" : false,
			  "paging" : false,
			  "lengthChange" : false,
			  "serverSide": false,
		      "columns": table1_2_3Columns,
		      "columnDefs": [
		                     {
		                    	 "targets": [ 0 ],
		                    	 "visible": false,
		                     }
		                     ]
		      
		  } );
		
		$('#table1-2-4').on( 'draw.dt', drawTable1_2_4CB);
		 
		$('#table1-2-4 tbody').on("click", "tr", clickTable1_2_4Row );
		
		$("#searchTable1-2-4").click(function(){
			table1_2_4.draw();
		});
		
		function drawTable1_2_4CB() {
			curTable1_2_4Row = null;
			 if(table1_2_4.rows().data().length > 0){
				  var dtRow = null;
				  var node = null;
				  dtRow = table1_2_4.row(0);
				  node = dtRow.node();
				  $(node).click();
			  }
		 } 
		
		function clickTable1_2_4Row() {
			var row = this;
			if ($(row).hasClass('active')) {
				curTable1_2_4Row = null;
				$(row).removeClass('active');
			} else {
				curTable1_2_4Row = row;
				table1.$('tr.active').removeClass('active');
				$(row).addClass('active');
			}
		}
		
		/* ------------- table1_2_4响应事件 ------------------ */
		
		var curTable1_2_4Row = null;
		
		var table1_2_4 = $('#table1-2-4').DataTable( {
			 "info" : false,
			  "paging" : false,
			  "lengthChange" : false,
		      "deferRender": true,
			  "processing": true,
			  "serverSide": true,
			  "ajax":{
				  url : "SampleRegisterAction_getLabItemByQuery",
				  type : "GET",
				  data : function(d){
					  var itemName = $("#sItemName2").val().trim();
					  if(itemName == null || itemName == ""){
						  alert("没有输入LMIS项目名称");
						  itemName = "";
					  }
					  var data = {
							  itemName : itemName,
							  labDeptName : $("#sLabDeptName2").val().trim()
					  };
					  data = JSON.stringify(data);
					  return data;
				  }
			  },
		      "columns": table1_2_1Columns,
		  } );
		
		$('#table1-2-4').on( 'draw.dt', drawTable1_2_4CB);
		 
		$('#table1-2-4 tbody').on("click", "tr", clickTable1_2_4Row );
		
		$("#searchTable1-2-4").click(function(){
			table1_2_4.draw();
		});
		
		function drawTable1_2_4CB() {
			curTable1_2_4Row = null;
			 if(table1_2_4.rows().data().length > 0){
				  var dtRow = null;
				  var node = null;
				  dtRow = table1_2_4.row(0);
				  node = dtRow.node();
				  $(node).click();
			  }
		 } 
		
		function clickTable1_2_4Row() {
			var row = this;
			if ($(row).hasClass('active')) {
				curTable1_2_4Row = null;
				$(row).removeClass('active');
			} else {
				curTable1_2_4Row = row;
				table1.$('tr.active').removeClass('active');
				$(row).addClass('active');
			}
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
  			 $.get("SampleRegisterAction_getLabItemMatched?&ts="
  						+ new Date().getTime(), {
  							data : data
  				}, function(rdata) {
  					setLabItemMatched(rdata);
  				}, 'json');
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
	
	var inputData = [
	     			{name:'applyKind',id:'aApplyKind',desc:'业务类型',rules:['str']},
	     			{name:'sampleKind',id:'aSampleKind',desc:'样品类别',rules:['str']},
	     			{name:'applyDept',id:'aApplyDept',desc:'送检部门',rules:['str']},
	     			{name:'appliant',id:'aAppliant',desc:'送检人',rules:['str']},
	     			{name:'sampleState',id:'aSampleState',desc:'样品物理状态',rules:['str']},
	     			{name:'sampleDisposal',id:'aSampleDisposal',desc:'样品处理方式',rules:['str']},
	     			{name:'samplePreservation',id:'aSamplePreservation',desc:'样品保存方式',rules:['str']},
	     			{name:'remarks',id:'aRemarks',desc:'备注',rules:[]},
	     			{name:'sampleName',id:'aSampleName',desc:'样品名称',rules:['str']},
	     			{name:'copyCount',id:'aCopyCount',desc:'样品份数',rules:['str','int']},
	     			{name:'sampleCount',id:'aSampleCount',desc:'样品重量',rules:['str','float']},
	     			{name:'countUnit',id:'aCountUnit',desc:'单位',rules:['str']},
	     			{name:'sampleRemarks',id:'aSampleRemarks',desc:'样品说明',rules:[]},
	     	];
	     	
	     	function saveTable1(){
	     		
	     		if(sampleID != 0){
	     			alert("本界面不支持修改样品功能!");
	     			return;
	     		}
	     		var data = {};
	     		for(var i = 0; i < inputData.length; i++ ){
	     			var val = $("#"+inputData[i]['id']).val();
	     			var rules = inputData[i]['rules'];
	     			rules.each(function(n, rule){
	     				if(rule == 'str'){
	     					if(val == null || val == ""){
	     						var msg = "没有填写"+inputData[i]['desc']+"，保存样品数据失";
	     						alert(msg);
	     						return;
	     					}
	     				}else if(rule == 'int'){
	     					var reg = /^\d+$/;						
	     					if(!val.test(reg)){
	     						var msg = inputData[i]['desc']+"必须为大于0的整数";
	     						alert(msg);
	     						return;
	     					}
	     				}else if(rule == 'float'){
	     					var reg = /^\d+(\.\d+)?$/ ;						
	     					if(!val.test(reg)){
	     						var msg = inputData[i]['desc']+"必须为大于0的实数";
	     						alert(msg);
	     						return;
	     					}
	     				}else{
	     					
	     				}
	     			});
	     			data[inputData[i]['name']] = val;
	     		}
	     		
	     		if(table1_1.rows().data().length <= 0){
	     			alert("没有样品拟送检项目，保存样品数据失败");
	     			return;
	     		}
	     		saveLabApplyInfo(data);
	     		saveLabDefaultData(data);
	     	}
	     	
	     	function saveLabApplyInfo(data){ 
	     		var params = {
	     				declNo : table1.row(curTable1Row).data().declNo,
	     				applyKind : data.applyKind,
	     				sampleKind : data.sampleKind,
	     				applyDept : data.applyDept,
	     				appliant : data.appliant,
	     				sampleState : data.sampleState,
	     				sampleDisposal : data.sampleDisposal,
	     				samplePreservation : data.samplePreservation,
	     				remarks : data.remarks
	     		}
	     		params = JSON.stringify(params);
	     		 $.post("SampleRegisterAction_saveLabApplyInfo?&ts="
	     					+ new Date().getTime(), {
	     						data : params
	     			}, function(rdata) {
	     				if(rdata.data =="false"){
	     					alert("保存样品失败");
	     				}else{
	     					var labApplyID = rdata.data;
	     					saveLabSample(labApplyID, data);
	     				}
	     			}, 'json');

	     	}
	     	
	     	function saveLabDefaultData(data){
	     		var params = {
	     				declProductDetailID : table2.row(curTable2Row).data().declProductDetailID,
	     				applyKind : data.applyKind,
	     				sampleKind : data.sampleKind,
	     				applyDept : data.applyDept,
	     				appliant : data.appliant,
	     				sampleState : data.sampleState,
	     				sampleDisposal : data.disposal,
	     				samplePreservation : data.samplePreservation,
	     		}
	     		params = JSON.stringify(params);
	     		 $.post("SampleRegisterAction_saveLabDefaultData?&ts="
	     					+ new Date().getTime(), {
	     						data : params
	     			}, function(rdata) {
	     				if(rdata.data =="false"){
	     					alert("保存样品失败");
	     				}
	     			}, 'json');

	     	}
	     	
	     	function saveLabSample(labApplyID, data){
	     		var params = {
	     				sampleID : 0,
	     				labApplyID : labApplyID,
	     				declProductDetailID : table2.row(curTable2Row).data().declProductDetailID,
	     				sampleName : data.sampleName,
	     				sampleCount : data.sampleCount,
	     				countUnit : data.countUnit,
	     				sampleRemarks : data.sampleRemarks,
	     				copyCount : data.copyCount,
	     		}
	     		params = JSON.stringify(params);
	     		 $.post("SampleRegisterAction_saveLabSample", {
	     						data : params
	     			}, function(rdata) {
	     				if(rdata.data =="false"){
	     					alert("保存样品失败");
	     				}else{
	     					var sampleID = rdata.data;
	     					saveLabSampleItem(sampleID, data);
	     				}
	     			}, 'json');

	     	}
	     	
	     	function saveLabSampleItem(sampleID, data){
	     		var params = {
	     				declProductDetailID : table2.row(curTable2Row).data().declProductDetailID,
	     				sampleItemID : 0,
	     				sampleID : sampleID,
	     				labFlg : 1
	     		}
	     		var length = table1_1.rows().data().length;
	     		for(var i = 0; i < length; i++){
	     			params.itemCode = table1_1.row(i).data().itemCode; 
	     			params.lrpItemNo = table1_1.row(i).data().lrpItemNo; 
	     			params.lrpItemName = table1_1.row(i).data().lrpItemName; 
	     			params.lrpTestStd = table1_1.row(i).data().lrpTestStd; 
	     			params = JSON.stringify(params);
	     			 $.post("SampleRegisterAction_saveLabSampleItem", {
	     							data : params
	     				}, function(rdata) {
	     					if(rdata.data =="false"){
	     						alert("保存样品失败");
	     						return;
	     					}else if(i == length-1){
	     						alert("保存样品成功")
	     					}
	     				}, 'json');
	     		}
	     	}
	     	
	     	function matchItem(){
	     		if(curTable1_2 == null){
	     			alert("请选择匹配的项目记录");
	     			return;
	     		}
	     		var data = {
	     				itemCode : table1_2.row(curTable1_2Row).data().itemCode,
	     				declProductDetailID : table2.row(curTable2Row).data().declProductDetailID,
	     				declProductItemID : table1_2.row(curTable1_2Row).data().declProductItemID,
	     		}
	     		 data = JSON.stringify(data);
	     		 $.post("SampleRegisterAction_saveLabItemMatchedManual?&ts="
	     					+ new Date().getTime(), {
	     						data : data
	     			}, function(rdata) {
	     				if(rdata.data =="true"){
	     					alert("操作成功");
	     					getLabItemMatched();
	     				}else{
	     					alert(rdata.data);
	     				}
	     			}, 'json');
	     	}
	     	
 	 function getDeclProductItem(){
 		 	var data = {
 				declProductDetailID :table2.row(curTable2Row).data().declProductDetailID,
 				showSamplingItemFlg : 1,
 				showNotLabFlg :  2   	
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
 		table1_2.clear();
 		table1_2.rows.add(data.data).draw();
 		
 		
 	}

	
	

	  
	  
	  
	function initProcessStatusSelect(){
		var source = ["待登记","已登记"];
		cus_autocomplete(source, "sProcessStatus", "sProcessStatus-select", null, null);
		$("#sProcessStatus").val("待登记");
	}
});