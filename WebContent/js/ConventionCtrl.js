//@ sourceURL=ConvertionalCtrl.js  
$(document).ready(function(){
	
	var curConvCtrlRow = null; 
	var curItemCtrlRow = null;
	var curLimitTableRow = null;
	var convCtrlOperation = 0;   //0新增， 1修改， 2复制
	var itemCtrlOperation = 0;  //0新增，1 修改
	var limitTableOperation = 0;   //0 新增， 1 修改， 2删除
	var materialClassCode = 0;
	initInspOrgSelect();
	initProductClassSelect();
	initMaterialClassSelect();
	initHazardLevelSelect();
	initCountryReactLevelSelect();
	initLimitTypeSelect();
	
	var convCtrlColumns = [
	                  { "data": "convCtrlID" },
	      	          { "data": "countryName" },
	      	          { "data": "productClassName" },
	      	          { "data": "productSubclassName" },
	      	          { "data": "processingMethodName" },
	      	          { "data": "materialClassName" },
	      	          { "data": "materialSubclassName" },
	      	          { "data": "materialName" },
	      	          { "data": "materialSourceName" },
	      	          { "data": "packageTypeName" },
	      	          { "data": "intentedUseName" },
	      	          { "data": "productCode" },
	      	          { "data": "differenceCode" },
	      	          { "data": "controlDeptName" },
	      	          { "data": "controlDatetime" },
	     ];
	
	var itemCtrlColumns = [
	                  { "data": "convCtrlItemID" },
	      	          { "data": "itemName" },
	      	          { "data": "detectionStd" },
	      	          { "data": "monitoringReason" },
	      	          { "data": "unqualifyRatio" },
	      	          { "data": "hazardLevel" },
	      	          { "data": "countryReactLevel" },
	      	          { "data": "limitReq" },
	     ];
	
	var convCtrlTable = $('#convCtrlTb').DataTable( {
	      "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "ajax":{
			  url : "ConventionCtrlAction_getConvCtrl",
			  type : "GET",
			  data : function(d){
				  d.data = getConvCtrlRequestParam();
			  }
		  },
	      "columns": convCtrlColumns,
	      "columnDefs": [
	                     {
	                         "targets": [ 0 ],
	                         "visible": false,
	                     }
	                 ]
	  } );
	

	var itemCtrlTable = $('#itemCtrlTb').DataTable( {
		  "info" : false,
		  "paging" : false,
		  "lengthChange" : false,
	      "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "ajax":{
			  url : "ConventionCtrlAction_getConvCtrlItem",
			  type : "GET",
			  data : function(d){
				  if(curConvCtrlRow == null){
					  return {convCtrlID : 0};
				  }else{
					  return {convCtrlID : convCtrlTable.row(curConvCtrlRow).data().convCtrlID};
				  }
				  
			  }
		  },
	      "columns": itemCtrlColumns,
	      "columnDefs": [
	                     {
	                         "targets": [ 0 ],
	                         "visible": false,
	                     }
	                 ]
	  } );
	
	
	
	var limitTable = $('#aLimitTable').DataTable( {
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
	
	  $('#convCtrlTb').on( 'draw.dt', function () {
		  if(convCtrlTable.rows().data().length > 0){
			  var dtRow = convCtrlTable.row(0);
			  $(dtRow.node()).click();
		  }
		  curItemCtrlRow = null;
	  } );

	  $('#convCtrlTb tbody').on("click", "tr", clickConvCtrlRow );
	  
	  $('#aLimitTable tbody').on("click", "tr", clickLimitTableRow );
	  
	  $("#search").click(function(){
			 $(".overlay").show();
			 $("#convCtrlSearch").show(); 
		  });
	  
	  $("#add").click(function(){
		  convCtrlOperation = 0;
		  $(".overlay").show();
		  $("#convCtrlAdd").show(); 
	  });
	  
	  $("#edit").click( function(){
		  convCtrlOperation = 1;
		  getConvCtrlDetail();
	  });
	  
	  $("#copy").click(function(){
		 convCtrlOperation = 2; 
		 getConvCtrlDetail();
	  });
	  
	  $('#delete').click( delConvCtrlRule );
	  
	  $('#itemCtrlTb tbody').on("click", "tr", clickItemCtrlRow );
	  
	  $("#searchConvCtrl").click(function(){
		  convCtrlTable.draw();
		  $("#closeSearchConvCtrl").click();
	  });
	  
	  $("#addConvCtrl").click(function(){
          saveConvCtrl();		  
	  });
	  
	  $("#editConvCtrl").click(function(){
          saveConvCtrl();		  
	  });
	  
	  $("#closeAddConvCtrl").click(function(){
		  clearAlertDiv("convCtrlAdd");
		  $(".overlay").hide();
	      $("#convCtrlAdd").hide(); 
	  });
	  
	  $("#closeSearchConvCtrl").click(function(){
		  clearAlertDiv("convCtrlSearch");
		  $(".overlay").hide();
	      $("#convCtrlSearch").hide(); 
	  });
	  
	  $("#closeEditConvCtrl").click(function(){
		  clearAlertDiv("convCtrlEdit");
		  $(".overlay").hide();
	      $("#convCtrlEdit").hide(); 
	  });
	  
	  $("#addItem").click(function(){
		  itemCtrlOperation = 0;
		  $(".overlay").show();
		  $("#ctrlItemAdd").show();
	  });
	  
	  $("#editItem").click(function(){
		  itemCtrlOperation = 1;
		  getCtrlItemDetail();
	  });
	  
	  $("#deleteItem").click(delConvCtrlItem);
	  
	  $("#addCtrlItem").click(function(){
		  saveCtrlItem();
	  });
	  
	  $("#closeAddCtrlItem").click(function(){
		  clearAlertDiv("ctrlItemAdd");
		  limitTable.clear().draw();
		  $(".overlay").hide();
	      $("#ctrlItemAdd").hide(); 
	  });
	  
	  $("#addLimitTable").click(function(){
		  limitTableOperation = 0;
		  $("#ctrlItemAdd").hide(); 
		  $("#limitTable").show();
		 
	  });
	  
	  $("#editLimitTable").click(function(){
		  limitTableOperation = 1;
		  getLimitTable();
	  });
	  
	  $("#deleteLimitTable").click(function(){
		  limitTableOperation = 2;
		  $("#ctrlItemAdd").hide(); 
		  $("#limitTable").show();
	  });
	  
      $("#confirmLimitTable").click(function(){
    	  switch(limitTableOperation){
    	      case 0 :
    	    	  saveLimitTable(0);   //新增
    	    	  break;
    	      case 1: 
    	    	  saveLimitTable(1);   //修改
    	    	  break;
    	      case 2:
    	    	  deleteLimitTable();
    	    	  break;
    	  }
	  });
      
  	  $("#closeLimitTable").click(function(){
  		  clearAlertDiv("limitTable");
  		  $("#limitTable").hide();
  		  $("#ctrlItemAdd").show(); 
      });
  
	  
	  $("#aProcessMethod-search").click(function(){
		  searchProcessMethod("aProcessMethod");
	  });
	  
	  $("#eProcessMethod-search").click(function(){
		  searchProcessMethod("eProcessMethod");
	  });
	  
	  $("#sProcessMethod-search").click(function(){
		  searchProcessMethod("sProcessMethod");
	  });
	  
	  $("#aMaterialSource-search").click(function(){
		  searchMaterialSource("aMaterialSource");
	  });
	  
	  $("#eMaterialSource-search").click(function(){
		  searchMaterialSource("eMaterialSource");
	  });
	  
	  $("#sMaterialSource-search").click(function(){
		  searchMaterialSource("sMaterialSource");
	  });
	  
	  $("#aIntendedUse-search").click(function(){
		  searchIntendedUse("aIntendedUse");
	  });
	  
	  $("#eIntendedUse-search").click(function(){
		  searchIntendedUse("eIntendedUse");
	  });
	  
	  $("#sIntendedUse-search").click(function(){
		  searchIntendedUse("sIntendedUse");
	  });
	  
	  $("#aCountry-search").click(function(){
		  searchCountry("aCountry");
	  });
	  
	  $("#eCountry-search").click(function(){
		  searchCountry("eCountry");
	  });
	  
	  $("#cCountry-search").click(function(){
		  searchCountry("cCountry");
	  });
	  
	  $("#ltCountry-search").click(function(){
		  searchCountry("ltCountry");
	  });
	  
	  $("#aPackageType-search").click(function(){
		  searchPackageType("aPackageType");
	  });
	  
	  $("#ePackageType-search").click(function(){
		  searchPackageType("ePackageType");
	  });
	  
	  $("#sPackageType-search").click(function(){
		  searchPackageType("sPackageType");
	  });
	  
	  $("#aDetectionItem-search").click(function(){
		 searchDetectionItem("aDetectionItem"); 
	  });
	  
	
	  
	  //获取常规布控列表的请求参数
	  function getConvCtrlRequestParam(){
		  var data = {
				    productClassCode : getSelectValue("sProductClass"),
				    productSubclassCode : getSelectValue("sProductSubclas"),
				    materialClassCode : getSelectValue("sMaterialClass"),
				    materialSubclassCode : getSelectValue("sMaterialSubclass") ,
				    materialCode : getSelectValue("sMaterialSubsubclass"),
				    materialSourceCode : getSelectValue("sMaterialSource"),
				    processMethodCode : getSelectValue("sProcessMethod"),
				    packageTypeCode : getSelectValue("sPackageType"),
				    intendedUseCode : getSelectValue("sIntendedUse"),
				    countryCode : getSelectValue("sCountry"),
				    productCode : $("#sProductCode").val().trim(),
				    controlOrgCode : getSelectValue("sControlOrg"),
				    controlDeptCode : getSelectValue("sControlDept"),
		  };
		  var jsonstr = JSON.stringify(data);
		  return jsonstr;
	  }
	  
	  //常规布控保存的请求参数
	  function saveConvCtrlRequestParam(convCtrlID){
		  var data = null;
		  if(convCtrlOperation == 0){
			  if(getSelectValue("aCountry") == ""){
				  alert("国家或地区为必填项！");
				  return false;
			  }
			  data = {
					    convCtrlID : convCtrlID,
					    productClassCode : getSelectValue("aProductClass"),
					    productSubclassCode : getSelectValue("aProductSubclass"),
					    materialClassCode : getSelectValue("aMaterialClass"),
					    materialSubclassCode : getSelectValue("aMaterialSubclass") ,
					    materialCode : getSelectValue("aMaterialCode"),
					    materialSourceCode : getSelectValue("aMaterialSource"),
					    processMethodCode : getSelectValue("aProcessMethod"),
					    packageTypeCode : getSelectValue("aPackageType"),
					    intendedUseCode : getSelectValue("aIntendedUse"),
					    countryCode : getSelectValue("aCountry"),
					    productCode : $("#aProductCode").val().trim(),
					    differenceCode : $("#aDifferenceCode").val().trim(),
			  };
		  }else if(convCtrlOperation == 1){
			  if(getSelectValue("eCountry") == ""){
				  alert("国家或地区为必填项！");
				  return false;
			  }
			  data = {
					    convCtrlID : convCtrlID,
					    productClassCode : getSelectValue("eProductClass"),
					    productSubclassCode : getSelectValue("eProductSubclass"),
					    materialClassCode : getSelectValue("eMaterialClass"),
					    materialSubclassCode : getSelectValue("eMaterialSubclass") ,
					    materialCode : getSelectValue("eMaterialCode"),
					    materialSourceCode : getSelectValue("eMaterialSource"),
					    processMethodCode : getSelectValue("eProcessMethod"),
					    packageTypeCode : getSelectValue("ePackageType"),
					    intendedUseCode : getSelectValue("eIntendedUse"),
					    countryCode : getSelectValue("eCountry"),
					    productCode : $("#eProductCode").val().trim(),
					    differenceCode : $("#eDifferenceCode").val().trim(),
			  };
		  }else{
			  if(getSelectValue("eCountry") == ""){
				  alert("国家或地区为必填项！");
				  return false;
			  }
			  data = {
					    convCtrlID : 0,
					    productClassCode : getSelectValue("eProductClass"),
					    productSubclassCode : getSelectValue("eProductSubclass"),
					    materialClassCode : getSelectValue("eMaterialClass"),
					    materialSubclassCode : getSelectValue("eMaterialSubclass") ,
					    materialCode : getSelectValue("eMaterialCode"),
					    materialSourceCode : getSelectValue("eMaterialSource"),
					    processMethodCode : getSelectValue("eProcessMethod"),
					    packageTypeCode : getSelectValue("ePackageType"),
					    intendedUseCode : getSelectValue("eIntendedUse"),
					    countryCode : getSelectValue("eCountry"),
					    productCode : $("#eProductCode").val().trim(),
					    differenceCode : $("#eDifferenceCode").val().trim(),
			  };
		  }
		  var jsonstr = JSON.stringify(data);
		  return jsonstr;
	  }
	  
	  //保存常规布控
	  function saveConvCtrl(){
		  var data = null;
		  if(convCtrlOperation == 0){
			  data = saveConvCtrlRequestParam(0);
		  }else{
			  if(curConvCtrlRow == null){
				  alert("请先选择一条布控规则");
				  return;
			  }
			  var convCtrlID = convCtrlTable.row(curConvCtrlRow).data().convCtrlID;
			  data = saveConvCtrlRequestParam(convCtrlID);
		  }
		  if(!data){
			  return;
		  }
		  $.post("ConventionCtrlAction_saveConvCtrl?&ts="
					+ new Date().getTime(), {
			    data : data
			}, function(rdata) {
				if(rdata.data == "true"){
					alert("保存成功！");
					convCtrlTable.draw();
					if(convCtrlOperation == 0){
						$("#closeAddConvCtrl").click();
					}else{
						$("#closeEditConvCtrl").click();
					}
				}else{
					alert(rdata.data);
				}
			}, 'json');
	  }	
	  
	  //点击常规布控table回调
	  function clickConvCtrlRow(){
		  var row = this;
		  if ( $(row).hasClass('active') ) {
			    curConvCtrlRow = null;
	            $(row).removeClass('active');
	        }
	        else {
	        	curConvCtrlRow = row;
	        	convCtrlTable.$('tr.active').removeClass('active');
	            $(row).addClass('active');
	        }
		    itemCtrlTable.draw();
	  }
	  
	  
	  //点击布控项目table回调
	  function clickItemCtrlRow(){
		  var row = this;
		  if ( $(row).hasClass('active') ) {
			    curItemCtrlRow = null;
	            $(row).removeClass('active');
	        }
	        else {
	        	curItemCtrlRow = row;
	        	itemCtrlTable.$('tr.active').removeClass('active');
	            $(row).addClass('active');
	        }
	  }
	  
	  //点击限量表table回调
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
	  
	  //获取常规布控详情
	  function getConvCtrlDetail(){
		  if(curConvCtrlRow == null){
			  alert("请先选择一条布控规则");
			  return;
		  }
		  $(".overlay").show();
		  $("#convCtrlEdit").show();
		  if(convCtrlOperation == 1){
			  $("#convCtrlEdit .form-group:gt(3)").show();
		  }else if(convCtrlOperation == 2){
			  $("#convCtrlEdit .form-group:gt(3)").hide();
		  }
		  var convCtrlID = convCtrlTable.row(curConvCtrlRow).data().convCtrlID;
		  $.get("ConventionCtrlAction_getConvCtrlDetail?&ts="
					+ new Date().getTime(), {
						convCtrlID : convCtrlID
			}, function(rdata) {
				setConvCtrlDetail(rdata);
			}, 'json');
	  }
	  
	  //设置常规布控详情
	  function setConvCtrlDetail(rdata){
		  $("#eProductClass").val(rdata.productClassCode+" "+rdata.productClassName);
		  initProductSubclassSelect(rdata.productClassCode, $("#eProductSubclass").get(0));
		  $("#eProductSubclass").val(rdata.productSubclassCode+" "+rdata.productSubclassName);
		  $("#eProcessMethod").val(rdata.processingMethodCode+" "+rdata.processingMethodName);
		  $("#eMaterialClass").val(rdata.materialClassCode+" "+rdata.materialClassName);
		  materialClassCode = rdata.materialClassCode;
		  initMaterialSubclassSelect(materialClassCode, $("#eMaterialSubclass").get(0));
		  $("#eMaterialSubclass").val(rdata.materialSubclassCode+" "+rdata.materialSubclassName);
		  initMaterialSubsubclassSelect(rdata.materialSubclassCode, $("#eMaterialCode").get(0));
		  $("#eMaterialCode").val(rdata.materialCode+" "+rdata.materialName);
		  $("#eMaterialSource").val(rdata.materialSourceCode+" "+rdata.materialSourceName);
		  $("#ePackageType").val(rdata.packageTypeCode+" "+rdata.packageTypeName);
		  $("#eIntendedUse").val(rdata.intentedUseCode+" "+rdata.intentedUseName);
		  $("#eCountry").val(rdata.countryCode+" "+rdata.countryName);
		  $("#eProductCode").val(rdata.productCode);
		  $("#eDifferenceCode").val(rdata.differenceCode);
		  $("#eOrgName").val(rdata.orgName);
		  $("#eDeptName").val(rdata.deptName);
		  $("#eOperatorName").val(rdata.operatorName);
		  $("#eControlDatetime").val(rdata.controlDatetime);
	  };
	  
	  //删除常规布控
	  function delConvCtrlRule(){
		  if(curConvCtrlRow == null){
			  alert("请先选择一条要删除的记录！");
			  return;
		  }
		  if(confirm("确认要删除该条记录?")){
			  var convCtrlID = convCtrlTable.row(curConvCtrlRow).data().convCtrlID;
			  $.post("ConventionCtrlAction_delConvCtrl?&ts="
						+ new Date().getTime(), {
				    convCtrlID : convCtrlID
				}, function(rdata) {
					if(rdata.data == "true"){
						alert("删除成功！");
						convCtrlTable.row(curConvCtrlRow).remove().draw(false);
					}else{
						alert("删除失败！");
					}
				}, 'json');
		  }

	  }
	  
	  //保存布控项目
	  function saveCtrlItem(){
		  var data = null;
		  switch(itemCtrlOperation){
		  case 0 :
			  data = saveCtrlItemRequestParam(0);
			  break;
		  case 1 :
			  var convCtrlItemID = itemCtrlTable.row(curItemCtrlRow).data().convCtrlItemID;
			  data = saveCtrlItemRequestParam(convCtrlItemID);
			  break;
		  
		  }
		  if(!data){
			  return;
		  }
		  $.post("ConventionCtrlAction_saveConvCtrlItem?&ts="
					+ new Date().getTime(), {
			    data : data
			}, function(rdata) {
				if(rdata.data == "true"){
					alert("保存成功！");
					itemCtrlTable.draw();
					$("#closeAddCtrlItem").click();
				}else{
					alert(rdata.data);
				}
			}, 'json');
	  }
	  
	  //保存布控项目时的参数
	  function saveCtrlItemRequestParam(convCtrlItemID){
		  if(curConvCtrlRow == null){
			  alert("请先选择一条布控规则");
			  return;
		  }
		  var convCtrlID = convCtrlTable.row(curConvCtrlRow).data().convCtrlID;
		  var detectionItem = $("#aDetectionItem").val().trim();
		  if(detectionItem == null || detectionItem == ""){
			  alert("请选择检测项目");
			  return;
		  }
		  var monitoringReason = $("#aMonitoringReason").val().trim();
		  if(monitoringReason == null || monitoringReason == ""){
			  alert("请输入监控依据");
			  return;
		  }
		  var unqualifyRatio = $("#aUnqualifyRatio").val().trim();
		  if(unqualifyRatio == null || unqualifyRatio == ""){
			  alert("请输入项目超标率");
			  return;
		  }
		  var hazardLevel = $("#aHazardLevel").val().trim();
		  if(hazardLevel == null || hazardLevel == ""){
			  alert("请输入风险危害程度");
			  return;
		  }
		  var countryReactLevel = $("#aCountryReactLevel").val().trim();
		  if(countryReactLevel == null || countryReactLevel == ""){
			  alert("请输入输入国反应程度");
			  return;
		  }
		  var limitType = $("#aLimitType").val().trim();
		  if(limitType == null || limitType == ""){
			  alert("请输入限量类型");
			  return;
		  }
		  var detectionLimit = $("#aDetectionLimit").val().trim();
		  if(detectionLimit == null || detectionLimit == ""){
			  alert("请输入默认限量值");
			  return;
		  }
		  var limitUnit = $("#aLimitUnit").val().trim();
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
				    convCtrlID : convCtrlID,
				    convCtrlItemID : convCtrlItemID,
				    itemCode : detectionItem.split(" ")[0],
				    itemName : detectionItem.split(" ")[1],
				    detectionStd : $("#aDetectionStd").val().trim() ,
				    monitoringReason : monitoringReason,
				    unqualifyRatio : unqualifyRatio,
				    hazardLevel : hazardLevel,
				    countryReactLevel : countryReactLevel,
				    limitType : limitType,
				    detectionLimit : detectionLimit,
				    limitUnit :limitUnit,
				    itemLimitList : itemLimitList
		  };
		  var jsonstr = JSON.stringify(data);
		  return jsonstr;
	  }
	  
	  
	  //获取布控项目详情
	  function getCtrlItemDetail(){
		  if(curItemCtrlRow == null){
			  alert("请先选择一条要编辑的布控项目");
			  return;
		  }
		  $(".overlay").show();
		  $("#ctrlItemAdd").show();
		  var convCtrlItemID = itemCtrlTable.row(curItemCtrlRow).data().convCtrlItemID;
		  $.get("ConventionCtrlAction_getConvCtrlItemDetail?&ts="
					+ new Date().getTime(), {
				convCtrlItemID : convCtrlItemID
			}, function(rdata) {
				setCtrlItemDetail(rdata);
			}, 'json');
	  }
	  
	  //设置布控项目详情
	  function setCtrlItemDetail(rdata){
		  var itemDetail = rdata.ctrlItem;
		  var itemLimit = rdata.itemLimit;
		  $("#aDetectionItem").val(itemDetail.itemCode+" "+itemDetail.itemName);
		  $("#aDetectionStd").val(itemDetail.detectionStd);
		  $("#aMonitoringReason").val(itemDetail.monitoringReason);
		  $("#aUnqualifyRatio").val(itemDetail.unqualifyRatio);
		  $("#aHazardLevel").val(itemDetail.hazardLevel);
		  $("#aCountryReactLevel").val(itemDetail.countryReactLevel);
		  $("#aLimitType").val(itemDetail.limitType);
		  $("#aDetectionLimit").val(itemDetail.detectionLimit);
		  $("#aLimitUnit").val(itemDetail.limitUnit);
		  limitTable.rows.add(itemLimit).draw();
	  }
	  
	  //删除布控项目
	  function delConvCtrlItem(){
		  if(curItemCtrlRow == null){
			  alert("请先选择一条要删除的记录！");
			  return;
		  }
		  if(confirm("确认要删除该条记录?")){
			  var convCtrlItemID = itemCtrlTable.row(curItemCtrlRow).data().convCtrlItemID;
			  $.post("ConventionCtrlAction_delConvCtrlItem?&ts="
						+ new Date().getTime(), {
						convCtrlItemID : convCtrlItemID
				}, function(rdata) {
					if(rdata.data == "true"){
						alert("删除成功！");
						itemCtrlTable.row(curItemCtrlRow).remove().draw(false);
					}else{
						alert("删除失败！");
					}
				}, 'json');
		  }

	  }
	  
	  function initProductClassSelect(){
		  $.get("SelectDataAction_getProductClass?&ts="
					+ new Date().getTime(), 
		    function(rdata) {
				var source = new Array();
				$.each(rdata.data, function(index, value){
					source[index] = value.classCode+" "+value.className;
				});	
				cus_autocomplete(source, "sProductClass", "sProductClass-select", null, selectProductClassCB);
				cus_autocomplete(source, "aProductClass", "aProductClass-select", null, selectProductClassCB);
				cus_autocomplete(source, "eProductClass", "eProductClass-select", null, selectProductClassCB);
			}, 'json');
	  }
	  
	  function selectProductClassCB(event, ui){
		  var productClassCode = ui.item.value.split(" ")[0];
		  var input = $(event.target).parent().parent().next().next().children().children().filter("input").get(0);
		  initProductSubclassSelect(productClassCode, input);
	  }
	  
	  function initProductSubclassSelect(productClassCode, input){
		  $.get("SelectDataAction_getProductSubclass?&ts="
					+ new Date().getTime(), 
			{productClassCode : productClassCode},
		    function(rdata) {
				var source = new Array();
				$.each(rdata.data, function(index, value){
					source[index] = value.subclassCode+" "+value.subclassName;
				});	
				var inputSelect = $(input).next().get(0).id;
				cus_autocomplete(source, input.id, inputSelect, null, null);
			}, 'json');
		  
	  }
	  
	  function initMaterialClassSelect(){
		  $.get("SelectDataAction_getMaterialClass?&ts="
					+ new Date().getTime(), 
		    function(rdata) {
				var source = new Array();
				$.each(rdata.data, function(index, value){
					source[index] = value.classCode+" "+value.className;
				});	
				cus_autocomplete(source, "sMaterialClass", "sMaterialClass-select", null, selectMaterialClassCB);
				cus_autocomplete(source, "aMaterialClass", "aMaterialClass-select", null, selectMaterialClassCB);
				cus_autocomplete(source, "eMaterialClass", "eMaterialClass-select", null, selectMaterialClassCB);
			}, 'json');
	  }
	  
	  function selectMaterialClassCB(event, ui){
		  materialClassCode = ui.item.value.split(" ")[0];
		  var input = $(this).parent().parent().next().next().children().children().filter("input").get(0);
		  initMaterialSubclassSelect(materialClassCode, input);
	  }
	  
	  function initMaterialSubclassSelect(materialClassCode, input){
		  $.get("SelectDataAction_getMaterialSubclass?&ts="
					+ new Date().getTime(), 
			{materialClassCode : materialClassCode},
		    function(rdata) {
				var source = new Array();
				$.each(rdata.data, function(index, value){
					source[index] = value.subclassCode+" "+value.subclassName;
				});	
				var inputSelect = $(input).next().get(0).id;
				cus_autocomplete(source, input.id, inputSelect, null, selectMaterialSubclassCB);
			}, 'json');
		  
	  }
	  
	  function selectMaterialSubclassCB(event, ui){
		  var materialSubclassCode = ui.item.value.split(" ")[0];
		  var input = $(this).parent().parent().next().next().children().children().filter("input").get(0);
		  initMaterialSubsubclassSelect(materialSubclassCode, input);
	  }
	  
	  function initMaterialSubsubclassSelect(materialSubclassCode, input){
		  $.get("SelectDataAction_getMaterialSubsubclass?&ts="
					+ new Date().getTime(), 
			{materialClassCode : materialClassCode, materialSubclassCode : materialSubclassCode, showFlag : 0},
		    function(rdata) {
				var source = new Array();
				$.each(rdata.data, function(index, value){
					source[index] = value.materialCode+" "+value.materialName;
				});	
				var inputSelect = $(input).next().get(0).id;
				cus_autocomplete(source, input.id, inputSelect, null, null);
			}, 'json');
	  }
	  
	  
	  function initInspOrgSelect(){
		  $.get("SelectDataAction_getInspOrg?&ts="
					+ new Date().getTime(), 
		    function(rdata) {
				var source = new Array();
				$.each(rdata.data, function(index, value){
					source[index] = value.orgCode+" "+value.orgName;
				});	
				cus_autocomplete(source, "sControlOrg", "sControlOrg-select", null, selectInspOrgCB);		
			}, 'json');
	  }
	  
	  function selectInspOrgCB(event, ui){
		  var orgCode = ui.item.value.split(" ")[0];
		  initInspDeptSelect(orgCode);
	  }
	  
	  function initInspDeptSelect(orgCode){
		  $.get("SelectDataAction_getInspDept?&ts="
					+ new Date().getTime(), 
			{orgCode : orgCode},
		    function(rdata) {
				var source = new Array();
				$.each(rdata.data, function(index, value){
					source[index] = value.deptCode+" "+value.deptName;
				});	
				cus_autocomplete(source, "sControlDept", "sControlDept-select", null, null);		
			}, 'json');
	  }
	  
	  function initHazardLevelSelect(){
		  $.get("SelectDataAction_getEvlLevel?&ts="
					+ new Date().getTime(), 
			{levelType : "风险危害程度"},
		    function(rdata) {
				var source = new Array();
				$.each(rdata.data, function(index, value){
					source[index] = value.levelDesc;
				});	
				cus_autocomplete(source, "aHazardLevel", "aHazardLevel-select", null, null);		
			}, 'json');
	  }
	  
	  function initCountryReactLevelSelect(){
		  $.get("SelectDataAction_getEvlLevel?&ts="
					+ new Date().getTime(), 
			{levelType : "输入国反应程度"},
		    function(rdata) {
				var source = new Array();
				$.each(rdata.data, function(index, value){
					source[index] = value.levelDesc;
				});	
				cus_autocomplete(source, "aCountryReactLevel", "aCountryReactLevel-select", null, null);		
			}, 'json');
	  }
	  
	  function initLimitTypeSelect(){
		  $.get("SelectDataAction_getLimitType?&ts="
					+ new Date().getTime(), 
		    function(rdata) {
				var source = new Array();
				$.each(rdata.data, function(index, value){
					source[index] = value.limitType;
				});	
				cus_autocomplete(source, "aLimitType", "aLimitType-select", null, null);		
			}, 'json');
	  }
	  
	  function searchProcessMethod(input){
		  var methodName = $("#"+input).val().trim();
			 $.get("SearchSelectAction_getProcessingMethod?&ts="
						+ new Date().getTime(), 
			    {methodName : methodName},
			    function(rdata) {
					var source = new Array();
					$.each(rdata.data, function(index, value){
						source[index] = value.methodCode+" "+value.methodName;
					});	
					cus_autocomplete(source, input, null, null, null);
					$("#"+input).autocomplete( "search", "" );
				}, 'json');
	  }
	  
	  function searchMaterialSource(input){
		  var sourceName = $("#"+input).val().trim();
			 $.get("SearchSelectAction_getMaterialSource?&ts="
						+ new Date().getTime(), 
			    {sourceName : sourceName},
			    function(rdata) {
					var source = new Array();
					$.each(rdata.data, function(index, value){
						source[index] = value.sourceCode+" "+value.sourceName;
					});	
					cus_autocomplete(source, input, null, null, null);
					$("#"+input).autocomplete( "search", "" );
				}, 'json');
	  }
	  
	  function searchPackageType(input){
		  var typeName = $("#"+input).val().trim();
			 $.get("SearchSelectAction_getPackageType?&ts="
						+ new Date().getTime(), 
			    {typeName : typeName},
			    function(rdata) {
					var source = new Array();
					$.each(rdata.data, function(index, value){
						source[index] = value.typeCode+" "+value.typeName;
					});	
					cus_autocomplete(source, input, null, null, null);
					$("#"+input).autocomplete( "search", "" );
				}, 'json');
	  }
	  
	  function searchIntendedUse(input){
		  var useName = $("#"+input).val().trim();
			 $.get("SearchSelectAction_getIntendedUse?&ts="
						+ new Date().getTime(), 
			    {useName : useName},
			    function(rdata) {
					var source = new Array();
					$.each(rdata.data, function(index, value){
						source[index] = value.useCode+" "+value.useName;
					});	
					cus_autocomplete(source, input, null, null, null);
					$("#"+input).autocomplete( "search", "" );
				}, 'json');
	  }
	  
	  
	  function searchCountry(input){
		  var countryName = $("#"+input).val().trim();
			 $.get("SearchSelectAction_getCountry?&ts="
						+ new Date().getTime(), 
			    {countryName : countryName},
			    function(rdata) {
					var source = new Array();
					$.each(rdata.data, function(index, value){
						source[index] = value.countryCode+" "+value.countryName;
					});	
					cus_autocomplete(source, input, null, null, null);
					$("#"+input).autocomplete( "search", "" );
				}, 'json');
	  }
	  
	  function searchDetectionItem(input){
		  var detectionItem = $("#"+input).val().trim();
			 $.get("SearchSelectAction_getItem?&ts="
						+ new Date().getTime(), 
			    {itemName : detectionItem},
			    function(rdata) {
					var source = new Array();
					$.each(rdata.data, function(index, value){
						source[index] = value.itemCode+" "+value.itemName;
					});	
					cus_autocomplete(source, input, null, null, null);
					$("#"+input).autocomplete( "search", "" );
				}, 'json');
	  }
	  
	  //新增或修改限量表
	  function saveLimitTable(flag){
		  var country = $("#ltCountry").val().trim();
		  if(country == ""){
			  alert("请先选择国家或地区");
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
		  if(flag == 0){
			  limitTable.row.add(limitItem).draw();
		  }else{
			  limitTable.row(curLimitTableRow).data(limitItem).draw();
		  }
		  $("#closeLimitTable").click();
    	  $("#ctrlItemAdd").show(); 
	  }
	  
	  //获取要编辑的限量表记录详情
	  function getLimitTable(){
		  if(curLimitTableRow == null){
			  alert("请先选择一条记录");
			  return;
		  }
		  $("#ctrlItemAdd").hide(); 
		  $("#limitTable").show();
		  var row = limitTable.row(curLimitTableRow).data();
		  $("#ltCountry").val(row.countryCode+" "+row.countryName);
		  $("#ltDetectionLimit").val(row.detectionLimit);
		  $("#ltLimitUnit").val(row.limitUnit);
		  
	  }
	  
	  //删除限量表记录
	  function deleteLimitTable(){
		  if(curLimitTableRow == null){
			  alert("请先选择一条要删除的记录");
			  return;
		  }
		  limiTable.row(curLimitTableRow).remove().draw();
		  $("#closeLimitTable").click();
    	  $("#ctrlItemAdd").show(); 
	  }
	  
	  
});