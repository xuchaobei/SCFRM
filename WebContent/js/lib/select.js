//--------------------下拉列表数据获取------------------------------//
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