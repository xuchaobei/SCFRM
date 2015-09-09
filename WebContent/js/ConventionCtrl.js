$(document).ready(function(){
var curConvCtrlRow = null;
var curItemCtrlRow = null;
var countryTbRow=null;
var curLimitTableRow = null;
var convCtrlOperation = 0; //0新增， 1修改， 2复制
var itemCtrlOperation = 0; //0新增，1 修改
var limitTableOperation = 0; //0 新增， 1 修改， 2删除
var materialClassCode = 0;
initInspOrgSelect();
initProductClassSelect();
initMaterialClassSelect();
initHazardLevelSelect();
initCountryReactLevelSelect();
initLimitTypeSelect();
var convCtrlColumns = [
{ "data": "convCtrlID" },
{ "data": "convctrlTitle" },

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
var countryColumns = [
{ "data": "convCtrlCountryID" },
{ "data": "countryCode" },
{ "data": "countryName" }
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
},
},
"columns": convCtrlColumns,
"columnDefs": [
{
"targets": [ 0 ,8,9,11,12,13,14],
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
},
},
"columns": itemCtrlColumns,
"columnDefs": [
{
"targets": [ 0 ],
"visible": false,
}
]
} );

var countryTable = $('#CountryTb').DataTable( {
	"info" : false,
	"paging" : false,
	"lengthChange" : false,
	"deferRender": true,
	"processing": true,
	"serverSide": true,
	"ajax":{
	url : "ConventionCtrlAction_getConvCtrlCounty",
	type : "GET",
	data : function(d){
	if(curConvCtrlRow == null){
	return {convCtrlID : 0};
	}else{
	return {convCtrlID : convCtrlTable.row(curConvCtrlRow).data().convCtrlID};
	}
	},
	},
	"columns": countryColumns,
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
"columns": [{"data":"countryCode"},
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
curItemCtrlRow = null;
if(convCtrlTable.rows().data().length > 0){
var dtRow = null;
var node = null;
if(curConvCtrlRow != null){
dtRow = convCtrlTable.row($(curConvCtrlRow).context._DT_RowIndex);
node = dtRow.node();
if(node != null){
$(node).click();
return;
}
}
dtRow = convCtrlTable.row(0);
node = dtRow.node();
$(node).click();
}else{
curConvCtrlRow = null;
}
});
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
$('#CountryTb tbody').on("click", "tr", clickCountryTbRow );
$("#searchConvCtrl").click(function(){
	var data = getConvCtrlRequestParam();
	if(checkJsonParam(JSON.parse(data))){
	convCtrlTable.draw();
	//$("#closeSearchConvCtrl").click();
	$(".overlay").hide();
	$("#convCtrlSearch").hide();
	}
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
$("#addItem").click(addConvCtrlItem);
$("#addCountry").click(addCountry);
$("#editItem").click(getCtrlItemDetail);
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
deleteLimitTable();
});
$("#confirmLimitTable").click(function(){
switch(limitTableOperation){
case 0 :
saveLimitTable(0); //新增
break;
case 1:
saveLimitTable(1); //修改
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
$("#sCountry-search").click(function(){
searchCountry("sCountry");
});
$("#aCountry-search").click(function(){
searchCountry("aCountry");
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
convctrlTitle: $("#sconvCtrlTitle").val().trim(),
productClassCode : getSelectValue("sProductClass"),
productSubclassCode : getSelectValue("sProductSubclass"),
materialClassCode : getSelectValue("sMaterialClass"),
materialSubclassCode : getSelectValue("sMaterialSubclass") ,
materialCode : getSelectValue("sMaterialCode"),
materialSourceCode : getSelectValue("sMaterialSource"),
processingMethodCode : getSelectValue("sProcessMethod"),
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
			
		
		if(getSelectValue("aProductClass")==''){
			alert("产品大类为必填项");
			return false;
		}
		if(getSelectValue("aProductSubclass")==''){
			alert("产品小类为必填项");
			return false;
		}
		var convctrlTitle=$("#aconvctrltit").val().trim();
		if( convctrlTitle== ""){
			alert("布控标题为必填项！");
			return false;
			}
		var differenceCode = $("#aDifferenceCode").val().trim();
		if(differenceCode != null && differenceCode.length > 1){
		alert("识别码必须为1位字符！");
		return false;
		}
		/*if(getSelectValue("aProductClass")=="" && getSelectValue("aProductSubclass")==""  && getSelectValue("aMaterialClass")==""  && getSelectValue("aMaterialSubclass")=="" && getSelectValue("aMaterialCode")=="" && getSelectValue("aMaterialSource")==""  && getSelectValue("aProcessMethod")=="" && getSelectValue("aPackageType")=="" &&  getSelectValue("aIntendedUse")==""  && $("#aProductCode").val().trim()=="" &&  $("#aDifferenceCode").val().trim()=="" ){
			alert("国家地区必须填写，其他可选填，但必须需要填写1个以上.");
			return false;
		}*/
		data = {
		convCtrlID : convCtrlID,
		convctrlTitle :convctrlTitle,
		productClassCode : getSelectValue("aProductClass"),
		productSubclassCode : getSelectValue("aProductSubclass"),
		materialClassCode : getSelectValue("aMaterialClass"),
		materialSubclassCode : getSelectValue("aMaterialSubclass") ,
		materialCode : getSelectValue("aMaterialCode"),
		materialSourceCode : getSelectValue("aMaterialSource"),
		processingMethodCode : getSelectValue("aProcessMethod"),
		packageTypeCode : getSelectValue("aPackageType"),
		intendedUseCode : getSelectValue("aIntendedUse"),
		productCode : $("#aProductCode").val().trim(),
		differenceCode : $("#aDifferenceCode").val().trim(),
		};
		}else if(convCtrlOperation == 1){
			var convctrlTitle=$("#econctrltitle").val().trim();
			if( convctrlTitle== ""){
				alert("布控标题为必填项！");
				return false;
				}	
		/*if(getSelectValue("eCountry") == ""){
		alert("国家或地区为必填项！");
		return false;
		}*/
		if(getSelectValue("eProductClass") == ""){
			alert("产品大类为必填项");
			return false;
			}
		if(getSelectValue("eProductSubclass") == ""){
			alert("产品小类为必填项！");
			return false;
			}
		var differenceCode = $("#aDifferenceCode").val().trim();
		if(differenceCode != null && differenceCode.length > 1){
		alert("识别码必须为1位字符！");
		return false;
		}
		data = {
		convCtrlID : convCtrlID,
		convctrlTitle :convctrlTitle,
		productClassCode : getSelectValue("eProductClass"),
		productSubclassCode : getSelectValue("eProductSubclass"),
		materialClassCode : getSelectValue("eMaterialClass"),
		materialSubclassCode : getSelectValue("eMaterialSubclass") ,
		materialCode : getSelectValue("eMaterialCode"),
		materialSourceCode : getSelectValue("eMaterialSource"),
		processingMethodCode : getSelectValue("eProcessMethod"),
		packageTypeCode : getSelectValue("ePackageType"),
		intendedUseCode : getSelectValue("eIntendedUse"),
		productCode : $("#eProductCode").val().trim(),
		differenceCode : $("#eDifferenceCode").val().trim(),
		};
		}else{
			var convctrlTitle=$("#econctrltitle").val().trim();
			if( convctrlTitle== ""){
				alert("布控标题为必填项！");
				return false;
				}
		/*if(getSelectValue("eCountry") == ""){
		alert("国家或地区为必填项！");
		return false;
		}*/
		if(getSelectValue("eProductClass") == ""){
			alert("产品大类为必填项");
			return false;
			}
		if(getSelectValue("eProductSubclass") == ""){
			alert("产品小类为必填项！");
			return false;
			}
		var differenceCode = $("#aDifferenceCode").val().trim();
		if(differenceCode != null && differenceCode.length > 1){
		alert("识别码必须为1位字符！");
		return false;
		}
		data = {
		convCtrlID : 0,
		convctrlTitle :convctrlTitle,
		productClassCode : getSelectValue("eProductClass"),
		productSubclassCode : getSelectValue("eProductSubclass"),
		materialClassCode : getSelectValue("eMaterialClass"),
		materialSubclassCode : getSelectValue("eMaterialSubclass") ,
		materialCode : getSelectValue("eMaterialCode"),
		materialSourceCode : getSelectValue("eMaterialSource"),
		processingMethodCode : getSelectValue("eProcessMethod"),
		packageTypeCode : getSelectValue("ePackageType"),
		intendedUseCode : getSelectValue("eIntendedUse"),
		productCode : $("#eProductCode").val().trim(),
		differenceCode : $("#eDifferenceCode").val().trim(),
		};
		}
		if(!checkJsonParam(data)){
		return false;
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
		if(convCtrlOperation == 0){
		curConvCtrlRow = null; //保证新增后选中第一条新增的记录
		convCtrlTable.draw();
		$("#closeAddConvCtrl").click();
		}else{
		convCtrlTable.draw(false);
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
countryTable.draw();
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
//点击国家项目table回调
function clickCountryTbRow(){
var row = this;
if ( $(row).hasClass('active') ) {
countryTbRow = null;
$(row).removeClass('active');
}
else {
	countryTbRow = row;
countryTable.$('tr.active').removeClass('active');
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
		$("#convCtrlEdit .form-group:gt(4)").show();
		}else if(convCtrlOperation == 2){
		$("#convCtrlEdit .form-group:gt(4)").hide();
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
/*$("#eCountry").val(rdata.countryCode+" "+rdata.countryName);*/
$("#eProductCode").val(rdata.productCode);
$("#eDifferenceCode").val(rdata.differenceCode);
$("#eOrgName").val(rdata.orgName);
$("#eDeptName").val(rdata.deptName);
$("#eOperatorName").val(rdata.operatorName);
$("#eControlDatetime").val(rdata.controlDatetime);
$("#econctrltitle").val(rdata.convctrlTitle);  

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
alert(rdata.data);
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
			curItemCtrlRow = null;
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
			/*if(monitoringReason == null || monitoringReason == ""){
			alert("请输入监控依据");
			return;
			}*/
			var unqualifyRatio = $("#aUnqualifyRatio").val().trim();
			if(unqualifyRatio == null || unqualifyRatio == ""){
			alert("请输入项目超标率");
			return;
			}
			if(isNaN(unqualifyRatio)){
				alert("项目超标率必须为数字");
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
			if(limitType !="逻辑型"){
				if(isNaN(detectionLimit)){
					alert("限量默认值必须为数字");
					return;
				}
				
			}
			var limitUnit = $("#aLimitUnit").val().trim();
			/*if(limitUnit == null || limitUnit == ""){
			alert("请输入限量单位");
			return;
			}*/
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
			itemCode : getSelectValue("aDetectionItem"),
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
			if(!checkJsonParam(data)){
			return false;
			}
			var jsonstr = JSON.stringify(data);
			return jsonstr;
}
//新增布控项目
function addConvCtrlItem(){
if(curConvCtrlRow == null){
alert("请先选择一条布控规则");
return;
}
itemCtrlOperation = 0;
$(".overlay").show();
$("#ctrlItemAdd").show();
}
//新增国家布控
function addCountry(){
	if(curConvCtrlRow == null){
	alert("请先选择一条布控规则");
	return;
	}
	
	$(".overlay").show();
	$("#addCounDia").show();
	}
//获取布控项目详情
	function getCtrlItemDetail(){
		if(curConvCtrlRow == null){
		alert("请先选择一条布控规则");
		return;
		}
		if(curItemCtrlRow == null){
		alert("请先选择一条布控项目");
		return;
		}
		itemCtrlOperation = 1;
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
alert(rdata.data);
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
$(input).val("");
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
$(input).val("");
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
$(input).val("");
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
$("#sControlDept").val("");
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
			
		cus_autocomplete(rdata.data, "aLimitType", "aLimitType-select", null, null);
		}, 'json');
}
function searchProcessMethod(input){
var methodName =getSearchParam(input);
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
cus_autocomplete(source, input, null, null, null);
$("#"+input).autocomplete( "search", "" );
}, 'json');
}
function searchMaterialSource(input){
			var sourceName = getSearchParam(input);
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
			cus_autocomplete(source, input, null, null, null);
			$("#"+input).autocomplete( "search", "" );
			}, 'json');
}
function searchPackageType(input){
var typeName = getSearchParam(input);
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
cus_autocomplete(source, input, null, null, null);
$("#"+input).autocomplete( "search", "" );
}, 'json');
}
function searchIntendedUse(input){
var useName = getSearchParam(input);
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
cus_autocomplete(source, input, null, null, null);
$("#"+input).autocomplete( "search", "" );
}, 'json');
}
function searchCountry(input){
var countryName = getSearchParam(input);
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
cus_autocomplete(source, input, null, null, null);
$("#"+input).autocomplete( "search", "" );
}, 'json');
}
function searchDetectionItem(input){
var detectionItem =getSearchParam(input);
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
		var limitType = $("#aLimitType").val().trim();
		if(limitType == null || limitType == ""){
		alert("请先选中限量类型");
		return;
		}
		var detectionLimit = $("#ltDetectionLimit").val().trim();
		if(detectionLimit == ""){
		alert("请输入限量值");
		return;
		}
		if(limitType !="逻辑型"){
			if(isNaN(detectionLimit)){
				alert("限量默认值必须为数字");
				return;
			}
			
		}
		var limitUnit = $("#ltLimitUnit").val().trim();
		/*if(limitUnit == ""){
		alert("请输入单位");
		return;
		}*/
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
}else{
if(confirm("确认删除该条记录？")){
limitTable.row(curLimitTableRow).remove().draw();
}
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
      	$(row).addClass('active');
      	if(queryflg==1){
      		$("#aMaterialClass").val(matericlasalltable.row(matrclaRow).data()[0]+" "+matericlasalltable.row(matrclaRow).data()[1]);
          	$("#aMaterialSubclass").val(matericlasalltable.row(matrclaRow).data()[2]+" "+matericlasalltable.row(matrclaRow).data()[3]);
          	$("#aMaterialCode").val(matericlasalltable.row(matrclaRow).data()[4]+" "+matericlasalltable.row(matrclaRow).data()[5]);
          	
      	}else if(queryflg==2){
      		$("#eMaterialClass").val(matericlasalltable.row(matrclaRow).data()[0]+" "+matericlasalltable.row(matrclaRow).data()[1]);
          	$("#eMaterialSubclass").val(matericlasalltable.row(matrclaRow).data()[2]+" "+matericlasalltable.row(matrclaRow).data()[3]);
          	$("#eMaterialCode").val(matericlasalltable.row(matrclaRow).data()[4]+" "+matericlasalltable.row(matrclaRow).data()[5]);
          	
      	}else if(queryflg==3) {
      		$("#sMaterialClass").val(matericlasalltable.row(matrclaRow).data()[0]+" "+matericlasalltable.row(matrclaRow).data()[1]);
          	$("#sMaterialSubclass").val(matericlasalltable.row(matrclaRow).data()[2]+" "+matericlasalltable.row(matrclaRow).data()[3]);
          	$("#sMaterialCode").val(matericlasalltable.row(matrclaRow).data()[4]+" "+matericlasalltable.row(matrclaRow).data()[5]);
          	
      	}         
      	
      }
}
/*//查询1按钮
$("#smasubsubclasselect").click(function(){
	$(".overlay").show();
	$("#convCtrlAdd").hide();
	 matericlasalltable.row().remove().draw();
	$("#subsubclaqueydialog").show();
	matrclaRow=null;
	queryflg=1;    
	  });*/
/*//查询2按钮
$("#smasubsubclasselecttwo").click(function(){
	$(".overlay").show();
	$("#convCtrlEdit").hide();
	 matericlasalltable.row().remove().draw();
	$("#subsubclaqueydialog").show();
	matrclaRow=null;
	queryflg=2;
	  });*/
/*//查询3按钮
$("#smasubsubclasselectthree").click(function(){
	$(".overlay").show();
	$("#convCtrlSearch").hide();
	 matericlasalltable.row().remove().draw();
	$("#subsubclaqueydialog").show();
	matrclaRow=null;
	queryflg=3;
	  });*/

//选中返回
$("#confrimback").click(function(){
	if(matrclaRow==null){alert("请选中一行");return;}
	$("#closesubsub").click();
	  });
//取消
$("#closesubsub").click(function(){
	$("#subsubclaqueydialog").hide();
	//$("#subsubclassname").val('');
	if(queryflg==1){
		$("#convCtrlAdd").show();
	}else if(queryflg==2){
		$("#convCtrlEdit").show();
	}else if(queryflg==3){
		$("#convCtrlSearch").show();
	}
	matrclaRow=null;
	  });
//原料细类查询1
$("#smasubsubclasselect").click(function(){
	   matericlasalltable.row().remove().draw();
		var xileiname= $("#aMaterialCode").val().trim();
		if(xileiname==''|| xileiname==null){alert("请输入原料细类名称关键字,不要代码");return;}  
		$(".overlay").show();
		$("#convCtrlAdd").hide();
		$("#subsubclaqueydialog").show();
		matrclaRow=null;
		queryflg=1;
		getmaterialclassAll(xileiname);
	  });
//原料细类查询2
$("#smasubsubclasselecttwo").click(function(){
	   matericlasalltable.row().remove().draw();
		var xileiname= $("#eMaterialCode").val().trim();
		if(xileiname==''|| xileiname==null){alert("请输入原料细类名称关键字,不要代码");return;}  
		$(".overlay").show();
		$("#convCtrlEdit").hide();
		$("#subsubclaqueydialog").show();
		matrclaRow=null;
		queryflg=2;
		getmaterialclassAll(xileiname);
	  });
//原料细类查询3
$("#smasubsubclasselectthree").click(function(){
	   matericlasalltable.row().remove().draw();
		var xileiname= $("#sMaterialCode").val().trim();
		if(xileiname==''|| xileiname==null){alert("请输入原料细类名称关键字,不要代码");return;}  
		$(".overlay").show();
		$("#convCtrlSearch").hide();
		$("#subsubclaqueydialog").show();   
		matrclaRow=null;
		queryflg=3;
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

$(".nav-tabs > li").click(setTabDisplay);
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
//保存国家布控
$("#saveCounCtrl").click(saveCounCtrl);
function saveCounCtrl(){
	  var data = null;
	  data = saveTable2Param();
	  if(!data){
		  return;
	  }
	  $.post("ConventionCtrlAction_saveConvCtrlCounty?&ts="
				+ new Date().getTime(), {
		    data : data
		}, function(rdata) {
			if(rdata.data == "true"){
				alert("保存成功！");
				countryTbRow = null;     //保证新增后选中第一条新增的记录
				countryTable.draw();
				$("#closeCounDia").click();
			}else{
				alert(rdata.data);
			}
		}, 'json');
}
function saveTable2Param(){
	  var data = null;
	  var countryCode = getSelectValue("aCountry");
	  if(countryCode == ""){
		  alert("请输入国家名称！");
		  return false;
	  }
	  var convCtrlID = convCtrlTable.row(curConvCtrlRow).data().convCtrlID;
	  data = {
			  
			  convCtrlID : convCtrlID,
			  countryCode : countryCode,
	  };
	  if(!checkJsonParam(data)){
		  return false;
	  }
	  var jsonstr = JSON.stringify(data);
	  return jsonstr;
}
$("#closeCounDia").click(function(){
	clearAlertDiv("addCounDia");
	$(".overlay").hide();
	$("#addCounDia").hide();
});
$("#deleteCountry").click(deleteTable2);
function deleteTable2(){
	  if(countryTbRow == null){
		  alert("请先选择一条要删除的记录！");
		  return;
	  }
	  if(confirm("确认要删除该条记录?")){
		  var convCtrlCountryID = countryTable.row(countryTbRow).data().convCtrlCountryID;
		  $.post("ConventionCtrlAction_delConvCtrlCounty?&ts="
					+ new Date().getTime(), {
						convCtrlCountryID : convCtrlCountryID
			}, function(rdata) {
				if(rdata.data == "true"){
					alert("删除成功！");
					countryTable.row(countryTbRow).remove().draw(false);
				}else{
					alert(rdata.data);
				}
			}, 'json');
	  }

}
});