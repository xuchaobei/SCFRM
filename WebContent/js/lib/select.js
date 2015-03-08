//--------------------下拉列表数据获取------------------------------//
/**
 * 只显示name，不需要显示code的select
 * @param url
 * @param inputID
 * @param btnID
 */
function initStringSelect(url, inputID, btnID){
	  $.get(url, 
	    function(rdata) {
			cus_autocomplete(rdata.data, inputID, btnID, null, null);		
		}, 'json');
}


/**
 * 检验机构
 * @param inputID
 * @param btnID
 */
function initInspOrgSelect(orgInputID, orgBtnID, deptInputID, deptBtnID){
	  $.get("SelectDataAction_getInspOrg?&ts="
				+ new Date().getTime(), 
	    function(rdata) {
			var source = new Array();
			$.each(rdata.data, function(index, value){
				source[index] = value.orgCode+" "+value.orgName;
			});	
			cus_autocomplete(source, orgInputID, orgBtnID, null, 
				function(event, ui){
				    var orgCode = ui.item.value.split(" ")[0];
				    $("#"+deptInputID).val("");
				    return initInspDeptSelect(orgCode, deptInputID, deptBtnID);
			});		
		}, 'json');
}


/**
 * 检验部门
 * @param orgCode
 * @param inputID
 * @param btnID
 */
function initInspDeptSelect(orgCode, inputID, btnID){
	  $.get("SelectDataAction_getInspDept?&ts="
				+ new Date().getTime(), 
		{orgCode : orgCode},
	    function(rdata) {
			var source = new Array();
			$.each(rdata.data, function(index, value){
				source[index] = value.deptCode+" "+value.deptName;
			});	
			cus_autocomplete(source, inputID, btnID, null, null);		
		}, 'json');
}

/**
 * 产品大类
 * @param classInputID
 * @param classBtnID
 * @param subclassInputID
 * @param subclassBtnID
 */
function initProductClassSelect(classInputID, classBtnID, subclassInputID, subclassBtnID){
	  $.get("SelectDataAction_getProductClass?&ts="
				+ new Date().getTime(), 
	    function(rdata) {
			var source = new Array();
			$.each(rdata.data, function(index, value){
				source[index] = value.classCode+" "+value.className;
			});	
			cus_autocomplete(source, classInputID, classBtnID, null, 
                             function(event, ui){
				var productClassCode = ui.item.value.split(" ")[0];
				$("#"+subclassInputID).val("");
			    return initProductSubclassSelect(productClassCode, subclassInputID, subclassBtnID);
			});
		}, 'json');
}


/**
 * 产品小类
 * @param productClassCode
 * @param inputID
 * @param btnID
 */
function initProductSubclassSelect(productClassCode, inputID, btnID){
	  $.get("SelectDataAction_getProductSubclass?&ts="
				+ new Date().getTime(), 
		{productClassCode : productClassCode},
	    function(rdata) {
			var source = new Array();
			$.each(rdata.data, function(index, value){
				source[index] = value.subclassCode+" "+value.subclassName;
			});	
			cus_autocomplete(source, inputID, btnID, null, null);
		}, 'json');
	  
}

/**
 * 原料大类
 * @param classInputID
 * @param classBtnID
 * @param subclassInputID
 * @param subclassBtnID
 * @param inputID
 * @param btnID
 */
function initMaterialClassSelect(classInputID, classBtnID, subclassInputID, subclassBtnID, inputID, btnID){
	  $.get("SelectDataAction_getMaterialClass?&ts="
				+ new Date().getTime(), 
	    function(rdata) {
			var source = new Array();
			$.each(rdata.data, function(index, value){
				source[index] = value.classCode+" "+value.className;
			});	
			cus_autocomplete(source, classInputID, classBtnID, null, function(event, ui){
				  var materialClassCode = ui.item.value.split(" ")[0];
				  $("#"+subclassInputID).val("");
				  $("#"+inputID).val("");
				  initMaterialSubclassSelect(materialClassCode, subclassInputID, subclassBtnID, inputID, btnID);
			});
		}, 'json');
}


/**
 * 原料小类
 * @param materialClassCode
 * @param subclassInputID
 * @param subclassBtnID
 * @param inputID
 * @param btnID
 */
function initMaterialSubclassSelect(materialClassCode, subclassInputID, subclassBtnID, inputID, btnID){
	  $.get("SelectDataAction_getMaterialSubclass?&ts="
				+ new Date().getTime(), 
		{materialClassCode : materialClassCode},
	    function(rdata) {
			var source = new Array();
			$.each(rdata.data, function(index, value){
				source[index] = value.subclassCode+" "+value.subclassName;
			});	
			cus_autocomplete(source, subclassInputID, subclassBtnID, null, function(event, ui){
				 var materialSubclassCode = ui.item.value.split(" ")[0];
				 $("#"+inputID).val("");
				 initMaterialSubsubclassSelect(materialClassCode, materialSubclassCode, inputID, btnID);
			});
		}, 'json');
	  
}

/**
 * 原料细类
 * @param materialClassCode
 * @param materialSubclassCode
 * @param inputID
 * @param btnID
 */
function initMaterialSubsubclassSelect(materialClassCode, materialSubclassCode, inputID, btnID){
	  $.get("SelectDataAction_getMaterialSubsubclass?&ts="
				+ new Date().getTime(), 
		{materialClassCode : materialClassCode, materialSubclassCode : materialSubclassCode, showFlag : 0},
	    function(rdata) {
			var source = new Array();
			$.each(rdata.data, function(index, value){
				source[index] = value.materialCode+" "+value.materialName;
			});	
			cus_autocomplete(source, inputID, btnID, null, null);
		}, 'json');
}

/**
 * 等级配置数据
 */
function initEvlLevelSelect(levelType, inputID, btnID ){
	  $.get("SelectDataAction_getEvlLevel?&ts="
				+ new Date().getTime(), 
		{levelType : levelType},
	    function(rdata) {
			var source = new Array();
			$.each(rdata.data, function(index, value){
				source[index] = value.levelDesc;
			});	
			cus_autocomplete(source, inputID, btnID, null, null);		
		}, 'json');
}

/**
 * 查询国家
 * @param input
 */
function searchCountry(inputID, btnID){
	  $("#"+btnID).click(function(){
		     var countryName = getSearchParam(inputID);
			 $.get("SearchSelectAction_getCountry?&ts="
						+ new Date().getTime(), 
			    {countryName : countryName},
			    function(rdata) {
					var source = new Array();
					$.each(rdata.data, function(index, value){
						source[index] = value.countryCode+" "+value.countryName;
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

/**
 * 查询检测项目
 * @param inputID
 * @param btnID
 */
function searchDetectionItem(inputID, btnID){
	$("#"+btnID).click(function(){
		 var detectionItem = getSearchParam(inputID);
		 $.get("SearchSelectAction_getItem?&ts="
					+ new Date().getTime(), 
		    {itemName : detectionItem},
		    function(rdata) {
				var source = new Array();
				$.each(rdata.data, function(index, value){
					source[index] = value.itemCode+" "+value.itemName;
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


/**
 * 原料来源查询
 */
function searchMaterialSource(inputID, btnID){
	$("#"+btnID).click(function(){
		 var sourceName = getSearchParam(inputID);
		 $.get("SearchSelectAction_getMaterialSource?&ts="
					+ new Date().getTime(), 
		    {sourceName : sourceName},
		    function(rdata) {
		    	var source = new Array();
				$.each(rdata.data, function(index, value){
					source[index] = value.sourceCode+" "+value.sourceName;
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

/**
 * 查询加工方式
 */
function searchProcessingMethod(inputID, btnID){
	$("#"+btnID).click(function(){
		 var methodName =getSearchParam(inputID);
		 $.get("SearchSelectAction_getProcessingMethod?&ts="
					+ new Date().getTime(), 
		    {methodName : methodName},
		    function(rdata) {
				var source = new Array();
				$.each(rdata.data, function(index, value){
					source[index] = value.methodCode+" "+value.methodName;
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

/**
 * 查询包装类型
 */
function searchPackageType(inputID, btnID){
	$("#"+btnID).click(function(){
		 var typeName = getSearchParam(inputID);
		 $.get("SearchSelectAction_getPackageType?&ts="
					+ new Date().getTime(), 
		    {typeName : typeName},
		    function(rdata) {
				var source = new Array();
				$.each(rdata.data, function(index, value){
					source[index] = value.typeCode+" "+value.typeName;
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

/**
 * 查询预期用途
 */
function searchIntendedUse(inputID, btnID){
	$("#"+btnID).click(function(){
		 var useName = getSearchParam(inputID);
		 $.get("SearchSelectAction_getIntendedUse?&ts="
					+ new Date().getTime(), 
		    {useName : useName},
		    function(rdata) {
				var source = new Array();
				$.each(rdata.data, function(index, value){
					source[index] = value.useCode+" "+value.useName;
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

/**
 * 查询企业
 */
function searchEnt(inputID, btnID){
	$("#"+btnID).click(function(){
		 var entName = getSearchParam(inputID);
		 $.get("SearchSelectAction_getEnt?&ts="
					+ new Date().getTime(), 
		    {data : entName},
		    function(rdata) {
				var source = new Array();
				$.each(rdata.data, function(index, value){
					source[index] = value.entCode+" "+value.entName;
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

/**
 * 查询基地
 */
function searchBase(inputID, btnID){
	$("#"+btnID).click(function(){
		 var baseName = getSearchParam(inputID);
		 $.get("SearchSelectAction_getBase?&ts="
					+ new Date().getTime(), 
		    {data : baseName},
		    function(rdata) {
				var source = new Array();
				$.each(rdata.data, function(index, value){
					source[index] = value.baseCode+" "+value.baseName;
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

/**
 * 查询产品
 */
function searchProduct(inputID, btnID){
	$("#"+btnID).click(function(){
		 var productName = getSearchParam(inputID);
		 $.get("SearchSelectAction_getProductByQuery?&ts="
					+ new Date().getTime(), 
		    {data : productName},
		    function(rdata) {
				var source = new Array();
				$.each(rdata.data, function(index, value){
					source[index] = value.productCode+" "+value.productName;
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
