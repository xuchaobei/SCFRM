//@ sourceURL=AccessoryCtrl.js  
$(document).ready(function(){
	var curTable1Row = null;
	var curTable2Row = null;
	var curLimitTableRow = null;
	
	var table1Operation = 0;   //0:新增  1：修改
	var table2Operation = 0;   //0:新增  1：修改
	var limitTableOperation = 0;    //0:新增  1：修改
	
	initProductClassSelect("sProductClass","sProductClass-select","sProductSubclass","sProductSubclass-select");
	initMaterialClassSelect("sMaterialClass","sMaterialClass-select","sMaterialSubclass","sMaterialSubclass-select","sMaterial","sMaterial-select");
	initStringSelect("SelectDataAction_getLimitType","sLimitType","sLimitType-select");
	
	searchAccessory("sAccessory","sAccessory-search");
	searchCountry("sCountry","sCountry-search");
	searchCountry("ltCountry","ltCountry-search");
	searchDetectionItem("sDetectionItem","sDetectionItem-search");
	
	var table1Columns = [
	                     {"data": "accessoryControlID"},
	                     {"data": "accessoryName"},
	                     {"data": "countryName"},
	                     {"data": "productClassName"},
	                     {"data": "productSubclassName"},
	                     {"data": "materialClassName"},
	                     {"data": "materialSubclassName"},
	                     {"data": "materialName"},
	                     {"data": "controlDeptName"},
	                     {"data": "controlDatetime"},
	                     ];
	
	
	var table2Columns = [
	                     {"data": "accessoryControlItemID"},
	                     {"data": "itemCode"},
	                     {"data": "itemName"},
	                     {"data": "detectionStd"},
	                     {"data": "samplingRatio"},
	                     {"data": "limitReq"},
	                     ];
	
	
	/* ------------- table1响应事件 ------------------ */
	
	var table1 = $('#table1').DataTable( {
	      "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "ajax":{
			  url : "AccessoryCtrlAction_getAccessoryControl",
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
			  url : "AccessoryCtrlAction_getAccessoryControlItem",
			  type : "GET",
			  data : function(d){
				  if(curTable1Row == null){
					  return {accessoryControlID : 0};
				  }else{
					  return {accessoryControlID : table1.row(curTable1Row).data().accessoryControlID};
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
	
	$('#table2').on( 'draw.dt', drawTable2CB);
	
	$('#table2 tbody').on("click", "tr", clickTable2Row );

	$('#sLimitTable tbody').on("click", "tr", clickLimitTableRow );
	
    $("#add2").click(addTable2);
    
	$("#edit2").click(getTable2ItemDetail);
	
	$("#delete2").click(deleteTable2);
	
	$("#saveTable2").click(saveTable2);
		
	$("#closeTable2Save").click(function(){
		clearAlertDiv("table2Save");
		limitTable.clear().draw();
		$(".overlay").hide();
		$("#table2Save").hide();
	});
	
	$("#sDetectionItem-search").click(searchDetectionItem);
	
	$("#addLimitTable").click(addLimitTable);
	
	$("#editLimitTable").click(editLimitTable);
	
	$("#deleteLimitTable").click(deleteLimitTable);
	
	$("#confirmLimitTable").click(saveLimitTable);
	
	$("#closeLimitTable").click(function(){
		 clearAlertDiv("limitTableDiv");
 		 $("#limitTableDiv").hide();
 		 $("#table2Save").show(); 
	});
	
	//应急布控列表获取数据后的回调
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
	}
	
	  //获取应急布控列表的请求参数
	  function getTable1RequestParam(){
		  var accessoryName = $("#rAccessoryName").val().trim();
		  return accessoryName;
	  }

	 //查询辅料布控
	 function searchTable1Btn(){
	 	 $(".overlay").show();
		 $("#table1Search").show();
	 } 
	  
	 //新增辅料布控
     function addTable1Btn(){
    	  table1Operation = 0;
    	  $(".overlay").show();
    	  $("#table1Save").show();
     }
     
     //编辑辅料布控
     function editTable1Btn(){
 		  table1Operation = 1;
 		  getTable1ItemDetail();
 	}
	  
	 //查询应急布控
	 function searchTable1(){
		 table1.draw();
		 $("#closeTable1Search").click();
	 }
	 
	 //应急布控详情
	 function getTable1ItemDetail(){
		  if(curTable1Row == null){
			  alert("请先选择一条记录");
			  return;
		  }
		  $(".overlay").show();
		  $("#table1Save").show();
		  
		  var accessoryControlID = table1.row(curTable1Row).data().accessoryControlID;
		  $.get("AccessoryCtrlAction_getAccessoryControlDetailByID?&ts="
					+ new Date().getTime(), {
						accessoryControlID : accessoryControlID
			}, function(rdata) {
				setTable1ItemDetail(rdata);
			}, 'json');
	 }
	 
	 function setTable1ItemDetail(rdata){
		  $("#sAccessory").val(rdata.accessoryName);
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
			  var accessoryControlID = table1.row(curTable1Row).data().accessoryControlID;
			  data = saveTable1Param(accessoryControlID);
		  }
		  if(!data){
			  return;
		  }
		  $.post("AccessoryCtrlAction_saveAccessoryControl?&ts="
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
	 
	 function saveTable1Param(accessoryControlID){
		  var data = null;
		  var accessoryName = $("#sAccessory").val().trim();
		  if(accessoryName == "" || accessoryName == null){
			  alert("请输入辅料！");
			  return false;
		  }
		  var countryCode = getSelectValue("sCountry");
		  if(countryCode == ""){
			  alert("请输入国家或地区！");
			  return false;
		  }
		  data = {
				    accessoryControlID : accessoryControlID,
				    accessoryName : accessoryName,
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
			  var accessoryControlID  = table1.row(curTable1Row).data().accessoryControlID ;
			  $.post("AccessoryCtrlAction_delAccessoryControl?&ts="
						+ new Date().getTime(), {
							accessoryControlID : accessoryControlID
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
	  
	
	//布控项目列表获取数据后的回调
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
		  
		  var accessoryControlItemID = table2.row(curTable2Row).data().accessoryControlItemID;
		  $.get("AccessoryCtrlAction_getAccessoryControlItemDetailByID?&ts="
					+ new Date().getTime(), {
						accessoryControlItemID : accessoryControlItemID
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
			  var accessoryControlItemID = table2.row(curTable2Row).data().accessoryControlItemID;
			  data = saveTable2Param(accessoryControlItemID);
		  }
		  if(!data){
			  return;
		  }
		  $.post("AccessoryCtrlAction_saveAccessoryControlItem?&ts="
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
		 function saveTable2Param(accessoryControlItemID){
			  var accessoryControlID = table1.row(curTable1Row).data().accessoryControlID;
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
					    accessoryControlID : accessoryControlID,
					    accessoryControlItemID : accessoryControlItemID,
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
			  var accessoryControlItemID = table2.row(curTable2Row).data().accessoryControlItemID;
			  $.post("AccessoryCtrlAction_delAccessoryControlItem?&ts="
						+ new Date().getTime(), {
							accessoryControlItemID : accessoryControlItemID
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
		  $("#table2Save").hide(); 
		  $("#limitTableDiv").show();
	  }
	  
	  //获取要编辑的限量表记录详情
	  function editLimitTable(){
		  limitTableOperation = 1;
		  if(curLimitTableRow == null){
			  alert("请先选择一条记录");
			  return;
		  }
		  $("#table2Save").hide(); 
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
    	  $("#table2Save").show(); 
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
	  
	  /**
	   * 查询检测项目
	   * @param inputID
	   * @param btnID
	   */
	  function searchAccessory(inputID, btnID){
	  	$("#"+btnID).click(function(){
	  		 var accessoryName = getSearchParam(inputID);
	  		 if(accessoryName == null || accessoryName == ""){
	  			 alert("请输入查询条件");
	  			 return;
	  		 }
	  		 $.get("SearchSelectAction_getAccessory?&ts="
	  					+ new Date().getTime(), 
	  		    {data : accessoryName},
	  		    function(rdata) {
	  				var source = new Array();
	  				$.each(rdata.data, function(index, value){
	  					source[index] = value;
	  				});	
	  				if(source.length == 0){
	  					alert("查询结果为空！");
	  					return;
	  				}
	  				cus_autocomplete(source, inputID, null, null, null);
	  				$("#"+inputID).autocomplete( "search", "" );
	  			}, 'json');		
	  	});
	  }
});