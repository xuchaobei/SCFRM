//@ sourceURL=CIQCtrl.js  
$(document).ready(function(){
	var curTable1Row = null;
	var curTable2Row = null;
	var curTable3Row = null;
	var curLimitTableRow = null;
	
	var table1Operation = 0;   //0:新增  1：修改
	var table2Operation = 0;   //0:新增  1：修改
	var table3Operation = 0;   //0:新增  1：修改
	var limitTableOperation = 0;    //0:新增  1：修改
	
	initInspOrgSelect("rControlOrg","rControlOrg-select","rControlDept","rControlDept-select");
	initStringSelect("SelectDataAction_getControlReason","rControlReason","rControlReason-select");
	initStringSelect("SelectDataAction_getControlReason","sControlReason","sControlReason-select");
	initStringSelect("SelectDataAction_getLogicalDefine?logicFlg=1","sLeftLogic","sLeftLogic-select");
	initStringSelect("SelectDataAction_getLogicalDefine?logicFlg=2","sRightLogic","sRightLogic-select");
	initStringSelect("SelectDataAction_getCIQControlFieldDefine","sDefinedField","sDefinedField-select");
	initStringSelect("SelectDataAction_getLogicalOperator","sCalculationDesc","sCalculationDesc-select");
	initStringSelect("SelectDataAction_getLimitType","sLimitType","sLimitType-select");
	initStringSelect("SelectDataAction_getCIQControlSamplingMode","sSamplingMode","sSamplingMode-select");
	
	searchCountry("ltCountry","ltCountry-search");
	searchDetectionItem("sDetectionItem","sDetectionItem-search");
	
	var table1Columns = [
	                     {"data": "ciqControlID"},
	                     {"data": "controlName"},
	                     {"data": "controlReason"},
	                     {"data": "remarks"},
	                     {"data": "deadline"},
	                     {"data": "ifExec",
                    	   "render": function ( data, type, full, meta ) {
                    	       return data === '1'  ? '生效' : '无效';
                    	    }
	                     },
	                     {"data": "ifCheck",
                      	  "render": function ( data, type, full, meta ) {
                    	      return data === '1'  ? '通过' : '不通过';
                    	    }
	                     },
	                     {"data": "controlInputDatetime"},
	                     {"data": "controlDeptName"},
	                     ];
	
	
	var table2Columns = [
	                     {"data": "ciqControlConditionID"},
	                     {"data": "orderNo"},
	                     {"data": "leftLogic"},
	                     {"data": "definedField"},
	                     {"data": "calculationDesc"},
	                     {"data": "keywords"},
	                     {"data": "rightLogic"},
	                     {"data": "keywordsDesc"},
	                     ];
	
	var table3Columns = [
	                     {"data": "ciqControlItemID"},
	                     {"data": "itemCode"},
	                     {"data": "itemName"},
	                     {"data": "samplingMode"},
	                     {"data": "samplingParamValue"},
	                     {"data": "detectionStd"},
	                     {"data": "limitReq"},
	                     ];
	
	
	
	/* ------------- table1响应事件 ------------------ */
	
	var table1 = $('#table1').DataTable( {
	      "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "ajax":{
			  url : "CIQCtrlAction_getCIQControl",
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
	
	$("#search1").click(function(){
		$(".overlay").show();
	    $("#table1Search").show();
	});
	
	$("#add1").click(function(){
		table1Operation = 0;
		$(".overlay").show();
		$("#table1Save").show();
		$("#table1Save .form-group:gt(1)").hide();
	});
	
	$("#edit1").click(function(){
		table1Operation = 1;
		getTable1ItemDetail();
	});
	
	$("#delete1").click(deleteTable1);
	
	$("#test1").click(testTable1);
	
	$("#validate1").click(validateTable1);
	
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
			  url : "CIQCtrlAction_getCIQControlCondition",
			  type : "GET",
			  data : function(d){
				  if(curTable1Row == null){
					  return {ciqControlID : 0};
				  }else{
					  return {ciqControlID : table1.row(curTable1Row).data().ciqControlID};
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
	
	
	$(".nav-tabs > li").click(setTabDisplay);
	
	$('#table2').on( 'draw.dt', drawTable2CB);
	
	$('#table2 tbody').on("click", "tr", clickTable2Row );
	
    $("#add2").click(addTable2);
    
	$("#edit2").click(getTable2ItemDetail);
	
	$("#delete2").click(deleteTable2);
	
    $("#saveTable2").click(saveTable2);
	
	$("#closeTable2Save").click(function(){
		clearAlertDiv("table2Save");
		$(".overlay").hide();
		$("#table2Save").hide();
	});
	
	$("#sKeywords-search").click(searchKeywords);
	
	/* ------------- table3响应事件 ------------------ */
	
	var table3 = $('#table3').DataTable( {
		  "info" : false,
		  "paging" : false,
		  "lengthChange" : false,
	      "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "ajax":{
			  url : "CIQCtrlAction_getCIQControlItem",
			  type : "GET",
			  data : function(d){
				  if(curTable1Row == null){
					  return {ciqControlID : 0};
				  }else{
					  return {ciqControlID : table1.row(curTable1Row).data().ciqControlID};
				  }
			  }
		  },
	      "columns": table3Columns,
	      "columnDefs": [
	                     {
	                         "targets": [ 0 ],
	                         "visible": false,
	                     }
	                 ]
	  } );
	
	var limitTable = $('#sLimitTable').DataTable( {
		  "info" : false,
		  "paging" : false,
		  "lengthChange" : false,
	      "columns":  [{"data":"countryCode"},
	                   { "data": "countryName" },
	                   { "data": "detectionLimit" },
	                   { "data": "limitUnit" }],
		   "columnDefs": [
		                     {
		                         "targets": [ 0 ],
		                         "visible": false,
		                     }
		                 ]
	  } );
	
	$('#table3').on( 'draw.dt', drawTable3CB);
	
	$('#table3 tbody').on("click", "tr", clickTable3Row );

	$('#sLimitTable tbody').on("click", "tr", clickLimitTableRow );
	
    $("#add3").click(addTable3);
    
	$("#edit3").click(getTable3ItemDetail);
	
	$("#delete3").click(deleteTable3);
	
	$("#saveTable3").click(saveTable3);
		
	$("#closeTable3Save").click(function(){
		clearAlertDiv("table3Save");
		limitTable.clear().draw();
		$(".overlay").hide();
		$("#table3Save").hide();
	});
	
	$("#sDetectionItem-search").click(searchDetectionItem);
	
	$("#addLimitTable").click(addLimitTable);
	
	$("#editLimitTable").click(editLimitTable);
	
	$("#deleteLimitTable").click(deleteLimitTable);
	
	$("#confirmLimitTable").click(saveLimitTable);
	
	$("#closeLimitTable").click(function(){
		 clearAlertDiv("limitTableDiv");
 		 $("#limitTableDiv").hide();
 		 $("#table3Save").show(); 
	});
	
	//应急布控列表获取数据后的回调
	function drawTable1CB() {
		 curTable2Row = null;
		 curTable3Row = null;
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
	
	//点击应急布控列表行的回调
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
	
	  //获取应急布控列表的请求参数
	  function getTable1RequestParam(){
		  var data = {
				  controlName : $("#rControlName").val().trim(),
				  controlReason : $("#rControlReason").val().trim(),
				  ifExec : getIfExecValue(),
				  ifCheck : getIfCheckValue() ,
				  controlOrgCode : getSelectValue("rControlOrg"),
				  controlDeptCode : getSelectValue("rControlDept"),
		  };
		  var jsonstr = JSON.stringify(data);
		  return jsonstr;
	  }

	 //应急布控是否生效
	 function getIfExecValue(){
		 if($("#execTrue").is(':checked') == true && $("#execFalse").is(':checked') == false){
			 return "1";
		 }else if($("#execTrue").is(':checked') == false && $("#execFalse").is(':checked') == true){
			 return "2";
		 }else{
			 return "0";
		 }
	 }
	
	 //应急布控是否测试通过
	 function getIfCheckValue(){
		 if($("#checkTrue").is(':checked') == true && $("#checkFalse").is(':checked') == false){
			 return "1";
		 }else if($("#checkTrue").is(':checked') == false && $("#checkFalse").is(':checked') == true){
			 return "2";
		 }else{
			 return "0";
		 }
	 }
	
	 //查询应急布控
	 function searchTable1(){
		 var data = getTable1RequestParam();
		 if(checkJsonParam(JSON.parse(data))){
			  table1.draw();
			  $("#closeTable1Search").click();
		 }
	 }
	 
	 //应急布控详情
	 function getTable1ItemDetail(){
		  if(curTable1Row == null){
			  alert("请先选择一条记录");
			  return;
		  }
		  $(".overlay").show();
		  $("#table1Save").show();
		  $("#table1Save .form-group:gt(1)").show();
		  
		  var ciqControlID = table1.row(curTable1Row).data().ciqControlID;
		  $.get("CIQCtrlAction_getCIQCtrlDetail?&ts="
					+ new Date().getTime(), {
						ciqControlID : ciqControlID
			}, function(rdata) {
				setTable1ItemDetail(rdata);
			}, 'json');
	 }
	 
	 //设置应急布控详情
	 function setTable1ItemDetail(rdata){
		  $("#sControlName").val(rdata.controlName);
		  $("#sControlReason").val(rdata.controlReason);
		  $("#sDeadline input").val(rdata.deadline);
		  $("#sRemarks").val(rdata.remarks);
		  $("#sControlOrg").val(rdata.orgName);
		  $("#sControlDept").val(rdata.deptName);
		  $("#sControlOperator").val(rdata.operatorName);
		  $("#sControlDatetime").val(rdata.controlInputDatetime);
	  };
	 
	 //保存应急布控
	 function saveTable1(){
		  var data = null;
		  if(table1Operation == 0){
			  data = saveTable1Param(0);			  
		  }else{
			  if(curTable1Row == null){
				  alert("请先选择一条记录");
				  return;
			  }
			  var ciqControlID = table1.row(curTable1Row).data().ciqControlID;
			  data = saveTable1Param(ciqControlID);
		  }
		  if(!data){
			  return;
		  }
		  $.post("CIQCtrlAction_saveCIQControl?&ts="
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
	 
	 //保存应急布控的参数
	 function saveTable1Param(ciqControlID){
		  var data = null;
		  var controlName = $("#sControlName").val().trim();
		  var controlReason = $("#sControlReason").val().trim();
		  if(controlName == null || controlName == ""){
			  alert("请输入布控名称");
			  return false;
		  }
		  if(controlReason == null || controlReason == ""){
			  alert("请输入布控依据");
			  return false;
		  }
		  data = {
				    ciqControlID : ciqControlID,
				    controlName : controlName,
				    controlReason : controlReason,
				    deadline : $("#sDeadline input").val(),
				    remarks : $("#sRemarks").val().trim(),
		  };
		  
		  var jsonstr = JSON.stringify(data);
		  return jsonstr;
	 }
	 
	  //删除应急布控
	  function deleteTable1(){
		  if(curTable1Row == null){
			  alert("请先选择一条要删除的记录！");
			  return;
		  }
		  if(confirm("确认要删除该条记录?")){
			  var ciqControlID = table1.row(curTable1Row).data().ciqControlID;
			  $.post("CIQCtrlAction_deleteCIQControl?&ts="
						+ new Date().getTime(), {
							ciqControlID : ciqControlID
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
	  
	  //测试应急布控条件是否设置正确
	  function testTable1(){
		  if(curTable1Row == null){
			  alert("请先选择一条记录！");
			  return;
		  }
		  var ciqControlID = table1.row(curTable1Row).data().ciqControlID;
		  $.post("CIQCtrlAction_checkCIQControlCondition?&ts="
					+ new Date().getTime(), {
						ciqControlID : ciqControlID
			}, function(rdata) {
				if(rdata.data == "true"){
					alert("测试通过！");
					table1.draw(false);
				}else{
					alert(rdata.data);
				}
			}, 'json');
	  }
	  
	  //设置应急布控条件生效或无效
	  function validateTable1(){
		  if(curTable1Row == null){
			  alert("请先选择一条记录！");
			  return;
		  }
		  var ciqControlID = table1.row(curTable1Row).data().ciqControlID;
		  $.post("CIQCtrlAction_updateCIQControlValid?&ts="
					+ new Date().getTime(), {
						ciqControlID : ciqControlID
			}, function(rdata) {
				if(rdata.data == "true"){
					alert("操作成功！");
					table1.draw(false);
				}else{
					alert(rdata.data);
				}
			}, 'json');
		  
	  }
	 
	//设置tab切换
	function setTabDisplay(){
		if(! $(this).hasClass("active")){
			$(".nav-tabs li.active").removeClass("active");
			$(this).addClass("active");
			if($("#tab1").hasClass("hide")){
				$("#tab1").removeClass("hide");
				$("#tab2").addClass("hide");
			}else{
				$("#tab2").removeClass("hide");
				$("#tab1").addClass("hide");
			}
		}
    }
	
	//布控规则列表获取数据后的回调
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
	
	
	//点击布控列表行的回调
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
	
	
	 //新增布控规则
	 function addTable2(){
		  if(curTable1Row == null){
			  alert("请先选择一条应急布控记录");
			  return;
		  }
		 
		  table2Operation = 0;
		  $(".overlay").show();
		  $("#table2Save").show();
	 }
	
	 //布控规则详情
	 function getTable2ItemDetail(){
		  if(curTable1Row == null){
			  alert("请先选择一条应急布控记录");
			  return;
		  }
		  if(curTable2Row == null){
			  alert("请先选择一条布控规则记录");
			  return;
		  }
		  table2Operation = 1;
		  $(".overlay").show();
		  $("#table2Save").show();
		  
		  var curRowData = table2.row(curTable2Row).data();
		  $("#sOrderNo").val(curRowData.orderNo);
		  $("#sLeftLogic").val(curRowData.leftLogic);
		  $("#sDefinedField").val(curRowData.definedField);
		  $("#sCalculationDesc").val(curRowData.calculationDesc);
		  $("#sKeywords").val(curRowData.keywords+" "+curRowData.keywordsDesc);
		  $("#sRightLogic").val(curRowData.rightLogic);
		  
	 }
	 
	 //保存应急布控规则
	 function saveTable2(){
		  var data = null;
		  if(table2Operation == 0){
			  data = saveTable2Param(0);			  
		  }else{
			  if(curTable2Row == null){
				  alert("请先选择一条记录");
				  return;
			  }
			  var ciqControlConditionID = table2.row(curTable2Row).data().ciqControlConditionID;
			  data = saveTable2Param(ciqControlConditionID);
		  }
		  if(!data){
			  return;
		  }
		  $.post("CIQCtrlAction_saveCIQControlCondition?&ts="
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
	 
	 //保存应急布控的参数
	 function saveTable2Param(ciqControlConditionID){
		  var data = null;
		  var ciqControlID = table1.row(curTable1Row).data().ciqControlID;
		  var orderNo = $("#sOrderNo").val().trim();
		  if(orderNo == null || orderNo == ""){
			  alert("请输入排序序号");
			  return false;
		  }
		  var leftLogic = $("#sLeftLogic").val().trim();
		  if(leftLogic == null || leftLogic == ""){
			  alert("请输入左关系符");
			  return false;
		  }
		  var definedField = $("#sDefinedField").val().trim();
		  if(definedField == null || definedField == ""){
			  alert("请输入字段名称");
			  return false;
		  }
		  var calculationDesc = $("#sCalculationDesc").val().trim();
		  if(calculationDesc == null || calculationDesc == ""){
			  alert("请输入计算符");
			  return false;
		  }
		  var keywordsValue = $("#sKeywords").val().trim();
		  if(keywordsValue == null || keywordsValue == ""  ){
			  alert("请输入关键词");
			  return false;
		  }else if(!/\d.+\s\D.+/.test(keywordsValue)){
			  alert("请选择合法的关键词格式！");
			  return false;
		  }
		  var keywords = keywordsValue.split(" ")[0];
		  var keywordsDesc = keywordsValue.split(" ")[1];
		  var rightLogic = $("#sRightLogic").val().trim();
		  if(rightLogic == null || rightLogic == ""){
			  alert("请输入右关系符");
			  return false;
		  }
		  data = {
				    ciqControlID : ciqControlID,
				    ciqControlConditionID : ciqControlConditionID,
				    orderNo : orderNo,
				    leftLogic : leftLogic,
				    definedField : definedField,
				    calculationDesc : calculationDesc,
				    keywords : keywords,
				    keywordsDesc : keywordsDesc,
				    rightLogic : rightLogic,
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
			  var ciqControlConditionID = table2.row(curTable2Row).data().ciqControlConditionID;
			  $.post("CIQCtrlAction_deleteCIQControlCondition?&ts="
						+ new Date().getTime(), {
							ciqControlConditionID : ciqControlConditionID
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
	  
	  //布控规则关键词查询
	  function searchKeywords(){
		  var ciqControlID = table1.row(curTable1Row).data().ciqControlID;
		  var definedField = $("#sDefinedField").val().trim();
		  var keywords = $("#sKeywords").val().trim();
		  $.get("SearchSelectAction_getCIQControlKeyValue?&ts="
					+ new Date().getTime(), 
		    {
				 ciqControlID : ciqControlID,
				 definedField : definedField,
				 keywords : keywords
			 },
		    function(rdata) {
				var source = new Array();
				$.each(rdata.data, function(index, value){
					source[index] = value.showCode+" "+value.showName;
				});	
				cus_autocomplete(source, "sKeywords", null, null, null);
				$("#sKeywords").autocomplete( "search", "" );
			}, 'json');
	  }
	  
		//布控项目列表获取数据后的回调
		function drawTable3CB() {
			 if(table3.rows().data().length > 0){
				  var dtRow = null;
				  var node = null;
				  if(curTable3Row != null){
					  dtRow = table3.row($(curTable3Row).context._DT_RowIndex);
					  node = dtRow.node();
					  if(node != null){
						  $(node).click();
						  return;
					  }
				  }
				  dtRow = table3.row(0);
				  node = dtRow.node();
				  $(node).click();
			  }else{
				  curTable3Row = null;
			  }
		 } 
	  
		function clickTable3Row(){
			 var row = this;
			  if ( $(row).hasClass('active') ) {
				    curTable3Row = null;
		            $(row).removeClass('active');
		        }
		        else {
		        	curTable3Row = row;
		        	table3.$('tr.active').removeClass('active');
		            $(row).addClass('active');
		        }
		}
		
		 //新增布控项目
		 function addTable3(){
			  if(curTable1Row == null){
				  alert("请先选择一条应急布控记录");
				  return;
			  }
			  table3Operation = 0;
			  $(".overlay").show();
			  $("#table3Save").show();
		 }
		
		 //布控项目详情
		 function getTable3ItemDetail(){
			  if(curTable1Row == null){
				  alert("请先选择一条应急布控记录");
				  return;
			  }
			  if(curTable3Row == null){
				  alert("请先选择一条布控项目记录");
				  return;
			  }
			  table3Operation = 1;
			  $(".overlay").show();
			  $("#table3Save").show();
			  
			  var ciqControlItemID = table3.row(curTable3Row).data().ciqControlItemID;
			  $.get("CIQCtrlAction_getCIQCtrlItemDetail?&ts="
						+ new Date().getTime(), {
							ciqControlItemID : ciqControlItemID
				}, function(rdata) {
					setTable3ItemDetail(rdata);
				}, 'json');
			  
		 }
		 
		 //设置布控项目详情
		  function setTable3ItemDetail(rdata){
			  var itemDetail = rdata.ctrlItem;
			  var itemLimit = rdata.itemLimit;
			  $("#sDetectionItem").val(itemDetail.itemCode+" "+itemDetail.itemName);
			  $("#sDetectionStd").val(itemDetail.detectionStd);
			  $("#sSamplingMode").val(itemDetail.samplingMode);
			  $("#sSamplingParamValue").val(itemDetail.samplingParamValue);
			  $("#sLimitType").val(itemDetail.limitType);
			  $("#sDetectionLimit").val(itemDetail.detectionLimit);
			  $("#sLimitUnit").val(itemDetail.limitUnit);
			  limitTable.rows.add(itemLimit).draw();
		  }
		 
		 //保存布控项目
		 function saveTable3(){
			  var data = null;
			  if(table3Operation == 0){
				  data = saveTable3Param(0);			  
			  }else{
				  if(curTable3Row == null){
					  alert("请先选择一条记录");
					  return;
				  }
				  var ciqControlItemID = table3.row(curTable3Row).data().ciqControlItemID;
				  data = saveTable3Param(ciqControlItemID);
			  }
			  if(!data){
				  return;
			  }
			  $.post("CIQCtrlAction_saveCIQControlItem?&ts="
						+ new Date().getTime(), {
				    data : data
				}, function(rdata) {
					if(rdata.data == "true"){
						alert("保存成功！");
						if(table3Operation == 0){
							curTable3Row = null;     //保证新增后选中第一条新增的记录
							table3.draw();
							$("#closeTable3Save").click();
						}else{
							table3.draw(false);
							$("#closeTable3Save").click();
						}
					}else{
						alert(rdata.data);
					}
				}, 'json');
		 }
		 
		 //保存布控项目的参数
		 function saveTable3Param(ciqControlItemID){
			  var ciqControlID = table1.row(curTable1Row).data().ciqControlID;
			  var detectionItem = $("#sDetectionItem").val().trim();
			  if(detectionItem == null || detectionItem == ""){
				  alert("请选择检测项目");
				  return;
			  }else if(!/\d.+\s\D.+/.test(detectionItem)){
				  alert("请输入合法的数据格式！");
				  return false;
			  }
			  var detectionStd = $("#sDetectionStd").val().trim();
			  var samplingMode = $("#sSamplingMode").val().trim();
			  if(samplingMode == null || samplingMode == ""){
				  alert("请输入抽检模式");
				  return;
			  }
			  var samplingParamValue = $("#sSamplingParamValue").val().trim();
			  if(samplingParamValue == null || samplingParamValue == ""){
				  alert("请输入抽检参数值");
				  return;
			  }
			  if(!(/^\d+$/.test(samplingParamValue))){
				  alert("抽检参数值必须为整数");
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
					    ciqControlID : ciqControlID,
					    ciqControlItemID : ciqControlItemID,
					    itemCode : detectionItem.split(" ")[0],
					    itemName : detectionItem.split(" ")[1],
					    detectionStd : detectionStd ,
					    samplingMode : samplingMode,
					    samplingParamValue : samplingParamValue,
					    limitType : limitType,
					    detectionLimit : detectionLimit,
					    limitUnit :limitUnit,
					    itemLimitList : itemLimitList
			  };
			  var jsonstr = JSON.stringify(data);
			  return jsonstr;
		 }
	  
	  function deleteTable3(){
		  if(curTable3Row == null){
			  alert("请先选择一条要删除的记录！");
			  return;
		  }
		  if(confirm("确认要删除该条记录?")){
			  var ciqControlItemID = table3.row(curTable3Row).data().ciqControlItemID;
			  $.post("CIQCtrlAction_deleteCIQControlItem?&ts="
						+ new Date().getTime(), {
							ciqControlItemID : ciqControlItemID
				}, function(rdata) {
					if(rdata.data == "true"){
						alert("删除成功！");
						table3.row(curTable3Row).remove().draw(false);
					}else{
						alert("删除失败！");
					}
				}, 'json');
		  }

	  }
	
	  
	  function clickLimitTableRow(){
			 var row = this;
			  if ( $(row).hasClass('active') ) {
				    curLimitTableRow = null;
		            $(row).removeClass('active');
		        }
		        else {
		        	curLimitTableRow = row;
		        	limitTable.$('tr.active').removeClass('active');
		            $(row).addClass('active');
		        }
		}
	
	  //新增限量表
	  function addLimitTable(){
		  limitTableOperation = 0;
		  $("#table3Save").hide(); 
		  $("#limitTableDiv").show();
	  }
	  
	  //获取要编辑的限量表记录详情
	  function editLimitTable(){
		  limitTableOperation = 1;
		  if(curLimitTableRow == null){
			  alert("请先选择一条记录");
			  return;
		  }
		  $("#table3Save").hide(); 
		  $("#limitTableDiv").show();
		  var row = limitTable.row(curLimitTableRow).data();
		  $("#ltCountry").val(row.countryCode+" "+row.countryName);
		  $("#ltDetectionLimit").val(row.detectionLimit);
		  $("#ltLimitUnit").val(row.limitUnit);
		  
	  }
	  
	  //新增或修改限量表
	  function saveLimitTable(){
		  var country = $("#ltCountry").val().trim();
		  if(country == ""){
			  alert("请先选择国家或地区");
			  return;
		  }else if(!/\d.+\s\D.+/.test(country)){
			  alert("请输入合法的数据格式！");
			  return;
		  }
		  var detectionLimit = $("#ltDetectionLimit").val().trim();
		  if(detectionLimit == ""){
			  alert("请输入限量值");
			  return;
		  }
		  var limitUnit = $("#ltLimitUnit").val().trim();
		  if(limitUnit == ""){
			  alert("请输入单位");
			  return;
		  }
		  var limitItem = {
				  countryCode : country.split(" ")[0],
		          countryName : country.split(" ")[1],
		          detectionLimit : detectionLimit,
		          limitUnit : limitUnit
		  };
		  if(limitTableOperation == 0){
			  limitTable.row.add(limitItem).draw();
		  }else{
			  limitTable.row(curLimitTableRow).data(limitItem).draw();
		  }
		  $("#closeLimitTable").click();
    	  $("#table3Save").show(); 
	  }
	  
	  //删除限量表记录
	  function deleteLimitTable(){
		  if(curLimitTableRow == null){
			  alert("请先选择一条要删除的记录");
			  return;
		  }else{
			  if(confirm("确认删除该条记录？")){
				  limitTable.row(curLimitTableRow).remove().draw();
			  }
		  }
	  }
});