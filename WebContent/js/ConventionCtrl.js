//@ sourceURL=ConvertionalCtrl.js  
$(document).ready(function(){
	
	//initContentDivHeight();
	//产品大类编号 
	var productClassCode = 0;
	var productSubclassCode = 0;
	var materialSubsubclassCode = 0;
	var materialClassCode = 0;
	var materialSubclassCode = 0;	
	var curConvCtrlID = 0;
	var curConvCtrlRow = null; 
	var curItemCtrlRow = null;
	initInspOrgSelect();
	initProductClassSelect();
	initMaterialClassSelect();
	
	var convCtrlColumns = [
	                  { "data": "convCtrlID" },
	      	          { "data": "countryName" },
	      	          { "data": "productClassName" },
	      	          { "data": "productSubclassName" },
	      	          { "data": "processMethodName" },
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
			  url : "ConventionCtrlAction_getItemCtrl",
			  type : "GET",
			  data : function(d){
				  return {data : curConvCtrlID};
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
	
	  $('#convCtrlTb').on( 'draw.dt', function () {
		  if(convCtrlTable.rows().data().length > 0){
			  var dtRow = convCtrlTable.row(0);
			  curConvCtrlID = dtRow.data().convCtrlID;
			  $(dtRow.node()).click();
			  itemCtrlTable.draw();
		  }
	  } );

	  $('#convCtrlTb tbody').on("click", "tr", clickConvCtrlRow );
	  
	  $("#add").click(function(){
			 $(".overlay").show();
			 $("#convCtrlAdd").show(); 
	  });
	  
	  $('#delete').click( delConvCtrlRule );
	  
	  $("#search").click(function(){
		 $(".overlay").show();
		 $("#convCtrlSearch").show(); 
	  });
	  
	  
	  $('#itemCtrlTb tbody').on("click", "tr", clickItemCtrlRow );
	  
	  $("#addConvCtrl").click(function(){
          saveConvCtrl();		  
		  $("#closeAddConvCtrl").click();
	  });
	  
	  $("#searchConvCtrl").click(function(){
		  convCtrlTable.draw();
		  $("#closeSearchConvCtrl").click();
	  });
	  
	  $("#closeAddConvCtrl").click(function(){
		  $(".overlay").hide();
	      $("#convCtrlAdd").hide(); 
	  });
	  
	  $("#closeSearchConvCtrl").click(function(){
		  $(".overlay").hide();
	      $("#convCtrlSearch").hide(); 
	  });
	  
	  $("#aProcessMethod-search").click(function(){
		  searchProcessMethod("aProcessMethod");
	  });
	  
	  $("#sProcessMethod-search").click(function(){
		  searchProcessMethod("sProcessMethod");
	  });
	  
	  $("#aMaterialSource-search").click(function(){
		  searchMaterialSource("aMaterialSource");
	  });
	  
	  $("#sMaterialSource-search").click(function(){
		  searchMaterialSource("sMaterialSource");
	  });
	  
	  $("#aIntendedUse-search").click(function(){
		  searchIntendedUse("aIntendedUse");
	  });
	  
	  $("#sIntendedUse-search").click(function(){
		  searchIntendedUse("sIntendedUse");
	  });
	  
	  $("#aCountry-search").click(function(){
		  searchCountry("aCountry");
	  });
	  
	  $("#cCountry-search").click(function(){
		  searchCountry("cCountry");
	  });
	  
	  $("#aPackageType-search").click(function(){
		  searchPackageType("aPackageType");
	  });
	  
	  $("#sPackageType-search").click(function(){
		  searchPackageType("sPackageType");
	  });
	  
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
				    productCode : $("#sProductCode").val(),
				    controlOrgCode : getSelectValue("sControlOrg"),
				    controlDeptCode : getSelectValue("sControlDept"),
		  };
		  var jsonstr = JSON.stringify(data);
		  return jsonstr;
	  }
	  
	  function addConvCtrlRequestParam(){
		  if(getSelectValue("aCountry") == ""){
			  alert("国家或地区为必填项！");
			  return false;
		  }
		  var data = {
				    convCtrlID : 0,
				    productClassCode : getSelectValue("aProductClass"),
				    productSubclassCode : getSelectValue("aProductSubclas"),
				    materialClassCode : getSelectValue("aMaterialClass"),
				    materialSubclassCode : getSelectValue("aMaterialSubclass") ,
				    materialCode : getSelectValue("aMaterialSubsubclass"),
				    materialSourceCode : getSelectValue("aMaterialSource"),
				    processMethodCode : getSelectValue("aProcessMethod"),
				    packageTypeCode : getSelectValue("aPackageType"),
				    intendedUseCode : getSelectValue("aIntendedUse"),
				    countryCode : getSelectValue("aCountry"),
				    productCode : $("#aProductCode").val(),
				    differenceCode : $("#aDifferenceCode").val(),
		  };
		  var jsonstr = JSON.stringify(data);
		  return jsonstr;
	  }
	  
	  function saveConvCtrl(){
		  var data = addConvCtrlRequestParam();
		  if(!data){
			  return;
		  }
		  $.post("ConventionCtrlAction_saveConvCtrl?&ts="
					+ new Date().getTime(), {
			    data : data
			}, function(rdata) {
				if(rdata.data == "true"){
					alert("新增成功！");
					convCtrlTable.draw();
				}else{
					alert(rdata.data);
				}
			}, 'json');
	  }	
	  
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
	  }
	  
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
	  
	  function delConvCtrlRule(){
		  if(curConvCtrlRow == null){
			  alert("请先选择一条要删除的记录！");
			  return;
		  }
		  if(confirm("确认要删除该条记录?")){
			  var convCtrlId = convCtrlTable.row(curConvCtrlRow).data().convCtrlID;
			  $.post("ConventionCtrlAction_delConvCtrl?&ts="
						+ new Date().getTime(), {
				    convCtrlId : convCtrlId
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
				//cus_autocomplete(source, "productClassCodeInput", "productClassCodeSelect", null, null);
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
			}, 'json');
	  }
	  
	  function selectMaterialClassCB(event, ui){
		  var materialClassCode = ui.item.value.split(" ")[0];
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
		  var materialSubsubclassCode = ui.item.value.split(" ")[0];
		  var input = $(this).parent().parent().next().next().children().children().filter("input").get(0);
		  initMaterialSubsubclassSelect(materialSubsubclassCode, input);
	  }
	  
	  function initMaterialSubsubclassSelect(materialSubsubclassCode, input){
		  $.get("SelectDataAction_getMaterialSubsubclass?&ts="
					+ new Date().getTime(), 
			{materialClassCode : materialClassCode, materialSubclassCode : materialSubclassCode, showFlag : 0},
		    function(rdata) {
				var source = new Array();
				$.each(rdata.data, function(index, value){
					source[index] = value.subsubclassCode+" "+value.subsubclassName;
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
	  
	  function searchProcessMethod(input){
		  var methodName = $("#"+input).val();
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
		  var sourceName = $("#"+input).val();
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
		  var typeName = $("#"+input).val();
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
		  var useName = $("#"+input).val();
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
		  var countryName = $("#"+input).val();
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
	  
});