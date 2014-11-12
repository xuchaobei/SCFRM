//@ sourceURL=ConvertionalCtrl.js  
$(document).ready(function(){
	
	initContentDivHeight();
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
		  var dtRow = convCtrlTable.row(0);
		  curConvCtrlID = dtRow.data().convCtrlID;
		  $(dtRow.node()).click();
		  itemCtrlTable.draw();
	  } );

	  $('#convCtrlTb tbody').on("click", "tr", clickConvCtrlRow );
	  
	  $('#delete').click( delConvCtrlRule );
	  
	  $("#search").click(function(){
		 $(".overlay").show();
		 $("#convCtrlSearch").show(); 
	  });
	  
	  
	  $('#itemCtrlTb tbody').on("click", "tr", clickItemCtrlRow );
	  
	  
	  $("#searchConvCtrl").click(function(){
		  convCtrlTable.draw();
		  $("#closeConvCtrl").click();
	  });
	  
	  $("#closeConvCtrl").click(function(){
		  $(".overlay").hide();
	      $("#convCtrlSearch").hide(); 
	  });
	  
	  $("#processMethod-search").click(function(){
		  searchProcessMethod();
	  });
	  
	  $("#materialSource-search").click(function(){
		  searchMaterialSource();
	  });
	  
	  $("#intendedUse-search").click(function(){
		  searchIntendedUse();
	  });
	  
	  $("#country-search").click(function(){
		  searchCountry();
	  });
	  
	  $("#packageType-search").click(function(){
		  searchPackageType();
	  });
	  
	  
	  function getConvCtrlRequestParam(){
		  var data = {
				    productClassCode : getSelectValue("productClass"),
				    productSubclassCode : getSelectValue("productSubclas"),
				    materialClassCode : getSelectValue("materialClass"),
				    materialSubclassCode : getSelectValue("materialSubclass") ,
				    materialCode : getSelectValue("materialSubsubclass"),
				    materialSourceCode : getSelectValue("materialSource"),
				    processMethodCode : getSelectValue("processMethod"),
				    packageTypeCode : getSelectValue("packageType"),
				    intendedUseCode : getSelectValue("intendedUse"),
				    countryCode : getSelectValue("country"),
				    productCode : $("#productCode").val(),
				    controlOrgCode : getSelectValue("controlOrg"),
				    controlDeptCode : getSelectValue("controlDept"),
		  };
		  var jsonstr = JSON.stringify(data);
		  return jsonstr;
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
		  if(curRow == null){
			  alert("请先选择一条要删除的记录！");
			  return;
		  }
		  if(confirm("确认要删除该条记录?")){
			  var convCtrlId = table.row(curRow).data().convCtrlID;
			  $.post("ConventionCtrlAction_delConvCtrl?&ts="
						+ new Date().getTime(), {
				    convCtrlId : convCtrlId
				}, function(rdata) {
					if(rdata.data == "true"){
						alert("删除成功！");
						convCtrlTable.row(curRow).remove().draw(false);
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
				cus_autocomplete(source, "productClass", "productClass-select", null, selectProductClassCB);
			}, 'json');
	  }
	  
	  function selectProductClassCB(event, ui){
		  var productClassCode = ui.item.value.split(" ")[0];
		  initProductSubclassSelect(productClassCode);
	  }
	  
	  function initProductSubclassSelect(productClassCode){
		  $.get("SelectDataAction_getProductSubclass?&ts="
					+ new Date().getTime(), 
			{productClassCode : productClassCode},
		    function(rdata) {
				var source = new Array();
				$.each(rdata.data, function(index, value){
					source[index] = value.subclassCode+" "+value.subclassName;
				});	
				cus_autocomplete(source, "productSubclass", "productSubclass-select", null, null);
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
				cus_autocomplete(source, "materialClass", "materialClass-select", null, selectMaterialClassCB);
			}, 'json');
	  }
	  
	  function selectMaterialClassCB(event, ui){
		  var materialClassCode = ui.item.value.split(" ")[0];
		  initMaterialSubclassSelect(materialClassCode);
	  }
	  
	  function initMaterialSubclassSelect(materialClassCode){
		  $.get("SelectDataAction_getMaterialSubclass?&ts="
					+ new Date().getTime(), 
			{materialClassCode : materialClassCode},
		    function(rdata) {
				var source = new Array();
				$.each(rdata.data, function(index, value){
					source[index] = value.subclassCode+" "+value.subclassName;
				});	
				cus_autocomplete(source, "materialSubclass", "materialSubclass-select", null, selectMaterialSubclassCB);
			}, 'json');
		  
	  }
	  
	  function selectMaterialSubclassCB(event, ui){
		  var materialSubsubclassCode = ui.item.value.split(" ")[0];
		  initMaterialSubsubclassSelect(materialSubsubclassCode);
	  }
	  
	  function initMaterialSubsubclassSelect(materialSubsubclassCode){
		  $.get("SelectDataAction_getMaterialSubsubclass?&ts="
					+ new Date().getTime(), 
			{materialClassCode : materialClassCode, materialSubclassCode : materialSubclassCode, showFlag : 0},
		    function(rdata) {
				var source = new Array();
				$.each(rdata.data, function(index, value){
					source[index] = value.subsubclassCode+" "+value.subsubclassName;
				});	
				cus_autocomplete(source, "materialSubsubclass", "materialSubsubclass-select", null, null);
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
				cus_autocomplete(source, "controlOrg", "controlOrg-select", null, selectInspOrgCB);		
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
				cus_autocomplete(source, "controlDept", "controlDept-select", null, null);		
			}, 'json');
	  }
	  
	  function searchProcessMethod(){
		  var methodName = $("#processMethod").val();
			 $.get("SearchSelectAction_getProcessingMethod?&ts="
						+ new Date().getTime(), 
			    {methodName : methodName},
			    function(rdata) {
					var source = new Array();
					$.each(rdata.data, function(index, value){
						source[index] = value.methodCode+" "+value.methodName;
					});	
					cus_autocomplete(source, "processMethod", null, null, null);
					$("#processMethod").autocomplete( "search", "" );
				}, 'json');
	  }
	  
	  function searchMaterialSource(){
		  var sourceName = $("#materialSource").val();
			 $.get("SearchSelectAction_getMaterialSource?&ts="
						+ new Date().getTime(), 
			    {sourceName : sourceName},
			    function(rdata) {
					var source = new Array();
					$.each(rdata.data, function(index, value){
						source[index] = value.sourceCode+" "+value.sourceName;
					});	
					cus_autocomplete(source, "materialSource", null, null, null);
					$("#materialSource").autocomplete( "search", "" );
				}, 'json');
	  }
	  
	  function searchPackageType(){
		  var typeName = $("#packageType").val();
			 $.get("SearchSelectAction_getPackageType?&ts="
						+ new Date().getTime(), 
			    {typeName : typeName},
			    function(rdata) {
					var source = new Array();
					$.each(rdata.data, function(index, value){
						source[index] = value.methodCode+" "+value.methodName;
					});	
					cus_autocomplete(source, "packageType", null, null, null);
					$("#packageType").autocomplete( "search", "" );
				}, 'json');
	  }
	  
	  function searchIntendedUse(){
		  var useName = $("#intendedUse").val();
			 $.get("SearchSelectAction_getIntendedUse?&ts="
						+ new Date().getTime(), 
			    {useName : useName},
			    function(rdata) {
					var source = new Array();
					$.each(rdata.data, function(index, value){
						source[index] = value.useCode+" "+value.useName;
					});	
					cus_autocomplete(source, "intendedUse", null, null, null);
					$("#intendedUse").autocomplete( "search", "" );
				}, 'json');
	  }
	  
	  
	  function searchCountry(){
		  var countryName = $("#country").val();
			 $.get("SearchSelectAction_getCountry?&ts="
						+ new Date().getTime(), 
			    {countryName : countryName},
			    function(rdata) {
					var source = new Array();
					$.each(rdata.data, function(index, value){
						source[index] = value.countryCode+" "+value.countryName;
					});	
					cus_autocomplete(source, "country", null, null, null);
					$("#country").autocomplete( "search", "" );
				}, 'json');
	  }
	  
});