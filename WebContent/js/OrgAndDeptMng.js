//@ sourceURL=OrgAndDeptMng.js 
$(document).ready(function(){
	var curTable1Row = null;
	var curTable2Row = null;
	
	var table1Operation = 0;   //0:新增  1：修改
	var table2Operation = 0;   //0:新增  1：修改
	
	var table1Columns = [
	                     {"data": "inspOrgID"},
	                     {"data": "orgCode"},
	                     {"data": "orgName"},
	                     ];
	
	var table2Columns = [
	                     {"data": "inspDeptID"},
	                     {"data": "deptCode"},
	                     {"data": "deptName"},
	                     ];
	
	/* ------------- table1响应事件 ------------------ */
	var table1 = $('#table1').DataTable( {
	      "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "ajax":{
			  url : "OrgAndDeptMngAction_getInspOrg",
			  type : "GET",
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
	
	$("#add1").click(addTable1Btn);
	
	$("#edit1").click(editTable1Btn);
	
	$("#delete1").click(deleteTable1Btn);
	
    $("#saveTable1").click(saveTable1);
	
	$("#closeTable1Save").click(function(){
		clearAlertDiv("table1Save");
		$(".overlay").hide();
		$("#table1Save").hide();
	});
	
	
	/* ------------- table2响应事件 ------------------ */
	
	var table2 = $('#table2').DataTable( {
	      "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "ajax":{
			  url : "OrgAndDeptMngAction_getInspDeptPaging",
			  type : "GET",
			  data : function(d){
				  if(curTable1Row == null){
					  return {data : 0};
				  }else{
					  return {data : table1.row(curTable1Row).data().orgCode};
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
	
	
	$('#table2').on( 'draw.dt', drawTable2CB);
	
	$('#table2 tbody').on("click", "tr", clickTable2Row );

    $("#add2").click(addTable2Btn);
  
	$("#edit2").click(editTable2Btn);
	
	$("#delete2").click(deleteTable2Btn);
    
	$("#saveTable2").click(saveTable2);
		
	$("#closeTable2Save").click(function(){
		clearAlertDiv("table2Save");
		$(".overlay").hide();
		$("#table2Save").hide();
	});
	
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
	
    function addTable1Btn(){
    	 $.post(
			      'OrgAndDeptMngAction_checkPermission',
			      {},
			      function (data) 
			      {
			    	  if(data.data){
			    		  alert(data.data);
			    	  }else{
			    		  table1Operation = 0;
			    	   	  $(".overlay").show();
			    	   	  $("#table1Save").show();
			    	  }
			      },"json");
   	  
    }
    
    function editTable1Btn(){
    	 $.post(
			      'OrgAndDeptMngAction_checkPermission',
			      {},
			      function (data) 
			      {
			    	  if(data.data){
			    		  alert(data.data);
			    	  }else{
			    		  table1Operation = 1;
			    		  $(".overlay").show();
			    	   	  $("#table1Save").show();
			    		  setTable1ItemDetail();
			    	  }
			      },"json");
		
	}
    
    function deleteTable1Btn(){
    	$.post(
			      'OrgAndDeptMngAction_checkPermission',
			      {},
			      function (data) 
			      {
			    	  if(data.data){
			    		  alert(data.data);
			    	  }else{
			    		  deleteTable1();
			    	  }
			      },"json");
    }
    
    function deleteTable1(){
		  if(curTable1Row == null){
			  alert("请先选择一条要删除的记录！");
			  return;
		  }
		  if(confirm("确认要删除该条记录?")){
			  var inspOrgID  = table1.row(curTable1Row).data().inspOrgID ;
			  $.post("OrgAndDeptMngAction_delInspOrg?&ts="
						+ new Date().getTime(), {
							inspOrgID : inspOrgID
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
    
	 function setTable1ItemDetail(){
		  var rdata = table1.row(curTable1Row).data();
		  $("#sOrgCode").val(rdata.orgCode);
		  $("#sOrgName").val(rdata.orgName);
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
			  var inspOrgID = table1.row(curTable1Row).data().inspOrgID;
			  data = saveTable1Param(inspOrgID);
		  }
		  if(!data){
			  return;
		  }
		  $.post("OrgAndDeptMngAction_saveInspOrg?&ts="
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
	 
	 function saveTable1Param(inspOrgID){
		  var data = null;
		  var orgCode = $("#sOrgCode").val().trim();
		  if(orgCode == ""){
			  alert("请输入检验机构代码！");
			  return false;
		  }
		  var orgName = $("#sOrgName").val().trim();
		  if(orgName == ""){
			  alert("请输入检验机构名称！");
			  return false;
		  }
		  data = {
				  inspOrgID : inspOrgID,
				  orgCode : orgCode,
				  orgName : orgName,
		  };
		  if(!checkJsonParam(data)){
			  return false;
		  }
		  var jsonstr = JSON.stringify(data);
		  return jsonstr;
	 }
	  
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
	
	
	 function addTable2Btn(){
		$.post(
			      'OrgAndDeptMngAction_checkPermission',
			      {},
			      function (data) 
			      {
			    	  if(data.data){
			    		  alert(data.data);
			    	  }else{
			    		  if(curTable1Row == null){
			    			  alert("请先选择一条记录");
			    			  return;
			    		  }
			    		  table2Operation = 0;
			    		  $(".overlay").show();
			    		  $("#table2Save").show();
			    	  }
			      },"json");
		
	 }
	 
	 function editTable2Btn(){
    	 $.post(
			      'OrgAndDeptMngAction_checkPermission',
			      {},
			      function (data) 
			      {
			    	  if(data.data){
			    		  alert(data.data);
			    	  }else{
			    		  if(curTable1Row == null){
			    			  alert("请先选择一条记录");
			    			  return;
			    		  }
			    		  table2Operation = 1;
			    		  $(".overlay").show();
			    		  $("#table2Save").show();
			    		  setTable2ItemDetail();
			    	  }
			      },"json");
		
	}
	 
	 function setTable2ItemDetail(){
		  var rdata = table2.row(curTable2Row).data();
		  $("#sDeptCode").val(rdata.deptCode);
		  $("#sDeptName").val(rdata.deptName);
	  };
	  
	 function saveTable2(){
		  var data = null;
		  if(table2Operation == 0){
			  data = saveTable2Param(0);			  
		  }else{
			  if(curTable2Row == null){
				  alert("请先选择一条记录");
				  return;
			  }
			  var inspDeptID = table2.row(curTable2Row).data().inspDeptID;
			  data = saveTable2Param(inspDeptID);
		  }
		  if(!data){
			  return;
		  }
		  $.post("OrgAndDeptMngAction_saveInspDept?&ts="
					+ new Date().getTime(), {
			    data : data
			}, function(rdata) {
				if(rdata.data == "true"){
					alert("保存成功！");
					curTable2Row = null;     //保证新增后选中第一条新增的记录
					table2.draw();
					$("#closeTable2Save").click();
				}else{
					alert(rdata.data);
				}
			}, 'json');
	 }
	 
	 function saveTable2Param(inspDeptID){
		  var data = null;
		  var deptCode = $("#sDeptCode").val().trim();
		  if(deptCode == ""){
			  alert("请输入检验部门代码！");
			  return false;
		  }
		  var deptName = $("#sDeptName").val().trim();
		  if(deptName == ""){
			  alert("请输入检验部门名称！");
			  return false;
		  }
		  var orgCode  = table1.row(curTable1Row).data().orgCode;
		  data = {
				  inspDeptID : inspDeptID,
				  orgCode : orgCode,
				  deptCode : deptCode,
				  deptName : deptName,
		  };
		  if(!checkJsonParam(data)){
			  return false;
		  }
		  var jsonstr = JSON.stringify(data);
		  return jsonstr;
	 }
	 
	 function deleteTable2Btn(){
			$.post(
				      'OrgAndDeptMngAction_checkPermission',
				      {},
				      function (data) 
				      {
				    	  if(data.data){
				    		  alert(data.data);
				    	  }else{
				    		  deleteTable2();
				    	  }
				      },"json");
	 }
	 
	 function deleteTable2(){
		  if(curTable2Row == null){
			  alert("请先选择一条要删除的记录！");
			  return;
		  }
		  if(confirm("确认要删除该条记录?")){
			  var inspDeptID = table2.row(curTable2Row).data().inspDeptID;
			  $.post("OrgAndDeptMngAction_delInspDept?&ts="
						+ new Date().getTime(), {
							inspDeptID : inspDeptID
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
	
});