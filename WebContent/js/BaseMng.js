//@ sourceURL=BaseMng.js 
$(document).ready(function(){
	var curTable1Row = null;
	
	var table1Operation = 0;   //0:新增  1：修改
	
	initEvlLevelSelect("基地备案等级", "sBaseLevel", "sBaseLevel-select");
	initInspOrgSelect("sOrg", "sOrg-select", null, null);
	
	var table1Columns = [
	                     {"data": "baseID"},
	                     {"data": "baseCode"},
	                     {"data": "baseName"},
	                     {"data": "address"},
	                     {"data": "baseLevel"},
	                     {"data": "baseEvl"},
	                     {"data": "area"},
	                     {"data": "plantKind"},
	                     {"data": "orgName"},
	                     ];
	
	var table1 = $('#table1').DataTable( {
	      "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "ajax":{
			  url : "BaseMngAction_getBaseList",
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
	
	 function getTable1RequestParam(){
		  var data = {
				 baseName : $("#rBaseName").val().trim(),
				 baseCode : $("#rBaseCode").val().trim(),
		  };
		  var jsonstr = JSON.stringify(data);
		  return jsonstr;
	  }
	
	function drawTable1CB() {
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
	}
	
	 function searchTable1Btn(){
	 	 $(".overlay").show();
		 $("#table1Search").show();
	 } 
	  
    function addTable1Btn(){
   	    table1Operation = 0;
   	    $(".overlay").show();
   	    $("#table1Save").show();
    }
    
    function editTable1Btn(){
		table1Operation = 1;
		getTable1ItemDetail();
	}
    
    function deleteTable1(){
		  if(curTable1Row == null){
			  alert("请先选择一条要删除的记录！");
			  return;
		  }
		  if(confirm("确认要删除该条记录?")){
			  var baseCode  = table1.row(curTable1Row).data().baseCode ;
			  $.post("BaseMngAction_delBase?&ts="
						+ new Date().getTime(), {
							baseCode : baseCode
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
    
    function searchTable1(){
		 table1.draw();
		 $("#closeTable1Search").click();
	 }
	 
	 function getTable1ItemDetail(){
		  if(curTable1Row == null){
			  alert("请先选择一条记录");
			  return;
		  }
		  $(".overlay").show();
		  $("#table1Save").show();
		  
		  var baseCode = table1.row(curTable1Row).data().baseCode;
		  $.get("BaseMngAction_getBaseDetailByCode?&ts="
					+ new Date().getTime(), {
						baseCode : baseCode
			}, function(rdata) {
				setTable1ItemDetail(rdata);
			}, 'json');
	 }
	 
	 function setTable1ItemDetail(rdata){
		  $("#sBaseCode").val(rdata.baseCode);
		  $("#sBaseName").val(rdata.baseName);
		  $("#sArea").val(rdata.area);
		  $("#sAddress").val(rdata.address);
		  $("#sPlantKind").val(rdata.plantKind);
		  $("#sBaseLevel").val(rdata.baseLevel);
		  $("#sBaseEvl").val(rdata.baseEvl);
		  $("#sEntName").val(rdata.entName);
		  $("#sRegDate").val(rdata.regDate);
		  $("#sOrg").val(rdata.orgCode+" "+rdata.orgName);
		  $("#sRemarks").val(rdata.remarks);
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
			  var baseID = table1.row(curTable1Row).data().baseID;
			  data = saveTable1Param(baseID);
		  }
		  if(!data){
			  return;
		  }
		  $.post("BaseMngAction_saveBase?&ts="
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
	 
	 function saveTable1Param(baseID){
		  var data = null;
		  var baseCode = $("#sBaseCode").val().trim();
		  if(baseCode == "" || baseCode == null){
			  alert("请输入基地备案号！");
			  return false;
		  }
		  var baseName = $("#sBaseName").val().trim();
		  if(baseName == "" || baseName == null){
			  alert("请输入基地名称！");
			  return false;
		  }
		  var area = $("#sArea").val().trim();
		  if(area == "" || area == null){
			  alert("请输入基地面积！");
			  return false;
		  }
		  var address = $("#sAddress").val().trim();
		  if(address == "" || address == null){
			  alert("请输入基地地址！");
			  return false;
		  }
		  var plantKind = $("#sPlantKind").val().trim();
		  if(plantKind == "" || plantKind == null){
			  alert("请输入种养殖品种！");
			  return false;
		  }
		  var baseLevel = $("#sBaseLevel").val().trim();
		  if(baseLevel == "" || baseLevel == null){
			  alert("请输入分类等级！");
			  return false;
		  }
		  var baseEvl = $("#sBaseEvl").val().trim();
		  if(baseEvl == "" || baseEvl == null){
			  alert("请输入评价分值！");
			  return false;
		  }
		  var regDate = $("#sRegDate").val().trim();
		  if(regDate == "" || regDate == null){
			  alert("请选择备案时间！");
			  return false;
		  }
		  var orgCode = getSelectValue("sOrg");
		  if(orgCode == "" ){
			  alert("请选择所属机构 	！");
			  return false;
		  }
		  
		  data = {
				  baseID : baseID,
				  baseCode : baseCode,
				  baseName : baseName,
				  area : area,
				  address : address,
				  plantKind : plantKind,
				  baseLevel : baseLevel,
				  baseEvl : baseEvl,
				  entName : $("#sEntName").val().trim(),
				  regDate : regDate,
				  orgCode : orgCode,
				  remarks : $("#sRemarks").val().trim()
		  };
		  if(!checkJsonParam(data)){
			  return false;
		  }
		  var jsonstr = JSON.stringify(data);
		  return jsonstr;
	 }
	  
});