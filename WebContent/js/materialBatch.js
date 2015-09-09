//@ sourceURL=BaseMaterial.js 
$(document).ready(function(){
	var curTable1Row = null;
	var curTable2Row = null;
	
	var table1Operation = 0;   //0:新增  1：修改
	
	initMaterialClassSelect("sMaterialClass","sMaterialClass-select","sMaterialSubclass","sMaterialSubclass-select","sMaterial","sMaterial-select");

	//searchBase("sBase","sBase-search");
	searchDetectionItem("sDetectionItem","sDetectionItem-search");

	var table1Columns = [
	                     {"data": "materialReleaseID"},
	                     {"data": "materialBatchNo"},
	                     {"data": "materialClassCode",
                    	   "render": function ( data, type, full, meta ) {
                    	       return data + " " +full.materialClassName;
                    	    }
	                     },
	                     {"data": "materialSubclassCode",
                    	   "render": function ( data, type, full, meta ) {
                    		   return data + " " +full.materialSubclassName;
                    	    }
	                     },
	                     {"data": "materialCode",
                    	   "render": function ( data, type, full, meta ) {
                    		   return data + " " +full.materialName;
                    	    }
	                     },
	                     {"data": "inspDate",
	                    	   "render": function ( data, type, full, meta ) {
	                    		   return data.substring(0,10);
	                    	    }},
	                     {"data": "validDays"}
	                     ];
	
	
	var table2Columns = [
	                     {"data": "materialReleaseItemID"},
	                     {"data": "itemCode"},
	                     {"data": "itemName"},
	                     {"data": "labData"},
	                     {"data": "dataUnit"},
	                     ];
	
	
/* ------------- table1响应事件 ------------------ */
	
	var table1 = $('#table1').DataTable( {
	      "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "ajax":{
			  url : "BaseMaterialAction_getMaterialReleaseList",
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
			  url : "BaseMaterialAction_getMaterialReleaseItem",
			  type : "GET",
			  data : function(d){
				  if(curTable1Row == null){
					  return {materialReleaseID : 0};
				  }else{
					  return {materialReleaseID : table1.row(curTable1Row).data().materialReleaseID};
				  }
			  }
		  },
	      "columns": table2Columns,
	      "columnDefs": [
	                     {
	                         "targets": [ 0,1 ],
	                         "visible": false,
	                     }
	                 ]
	  } );
	
	
	$('#table2').on( 'draw.dt', drawTable2CB);
	
	$('#table2 tbody').on("click", "tr", clickTable2Row );

    $("#add2").click(addTable2Btn);
    
	$("#delete2").click(deleteTable2);
	
	$("#saveTable2").click(saveTable2);
		
	$("#closeTable2Save").click(function(){
		clearAlertDiv("table2Save");
		$(".overlay").hide();
		$("#table2Save").hide();
	});
	
	
	 function getTable1RequestParam(){
		 return  $("#MaterialBatchNo").val().trim();
	  }
	
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
			  alert("请先选择一条要删除的记录");
			  return;
		  }
		  if(confirm("确认要删除该条记录?")){
			  var materialReleaseID  = table1.row(curTable1Row).data().materialReleaseID ;
			  $.post("BaseMaterialAction_delMaterialRelease?&ts="
						+ new Date().getTime(), {
							materialReleaseID : materialReleaseID
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
		  
		  var materialReleaseID = table1.row(curTable1Row).data().materialReleaseID;
		  $.get("BaseMaterialAction_getMaterialReleaseByID?&ts="
					+ new Date().getTime(), {
						materialReleaseID : materialReleaseID
			}, function(rdata) {
				setTable1ItemDetail(rdata);
			}, 'json');
	 }
	 
	 function setTable1ItemDetail(rdata){
		  $("#sMaterBea").val(rdata.materialBatchNo);
		  $("#sMaterialClass").val(rdata.materialClassCode+" "+rdata.materialClassName);
		  initMaterialSubclassSelect(rdata.materialClassCode,"sMaterialSubclass","sMaterialSubclass-select","sMaterial","sMaterial-select");
		  $("#sMaterialSubclass").val(rdata.materialSubclassCode+" "+rdata.materialSubclassName);
		  initMaterialSubsubclassSelect(rdata.materialClassCode, rdata.materialSubclassCode, "sMaterial","sMaterial-select");
		  $("#sMaterial").val(rdata.materialCode+" "+rdata.materialName);
		  $("#sImpDate").val(rdata.inspDate);
		  $("#sValidDays").val(rdata.validDays);
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
			  var materialReleaseID = table1.row(curTable1Row).data().materialReleaseID;
			  data = saveTable1Param(materialReleaseID);
		  }
		  if(!data){
			  return;
		  }
		  $.post("BaseMaterialAction_saveMaterialRelease?&ts="
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
	 
	 function saveTable1Param(materialReleaseID){
		  var data = null;
		  var materialBatchNo = $("#sMaterBea").val().trim();
		  if(materialBatchNo == ""){
			  alert("原料批号不能为空！");
			  return false;
		  }
		  var materialClassCode = getSelectValue("sMaterialClass");
		  if(materialClassCode == ""){
			  alert("请选择原料大类！");
			  return false;
		  }
		  var impDate = $("#sImpDate").val().trim();
		  if(impDate == ""){
			  alert("请选中监控日期！");
			  return false;
		  }
		  var validDays = $("#sValidDays").val().trim();
		  if(validDays == ""){
			  alert("请输入监控有效期！");
			  return false;
		  }
		  data = {
				  materialReleaseID : materialReleaseID,
				  materialBatchNo : materialBatchNo,
				  materialClassCode : materialClassCode,
				  materialSubclassCode : getSelectValue("sMaterialSubclass"),
				  materialCode : getSelectValue("sMaterial"),
				  inspDate :impDate,
				  validDays :validDays
				  
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
		  if(curTable1Row == null){
			  alert("请先选择一条记录");
			  return;
		  }
		  $(".overlay").show();
		  $("#table2Save").show();
	 }
	 
	 function saveTable2(){
		  var data = null;
		  data = saveTable2Param(0);
		  if(!data){
			  return;
		  }
		  $.post("BaseMaterialAction_saveMaterialReleaseItem?&ts="
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
	 
	 function saveTable2Param(materialReleaseItemID){
		  var data = null;
		  var itemCode = getSelectValue("sDetectionItem");
		  if(itemCode == ""){
			  alert("请输入通报项目！");
			  return false;
		  }
		  var labData = $("#sLabData").val().trim();
		  if(labData == ""){
			  alert("请输入检测结果！");
			  return false;
		  }
		  var dataUnit = $("#sDataUnit").val().trim();
		  if(dataUnit == ""){
			  alert("请输入结果单位！");
			  return false;
		  }
		
		  var materialReleaseID  = table1.row(curTable1Row).data().materialReleaseID;
		  data = {
				  materialReleaseItemID : materialReleaseItemID,
				  materialReleaseID : materialReleaseID,
				  itemCode : itemCode,
				  labData : labData,
				  dataUnit : dataUnit
				 
		  };
		  if(!checkJsonParam(data)){
			  return false;
		  }
		  var jsonstr = JSON.stringify(data);
		  return jsonstr;
	 }
	 
	 function deleteTable2(){
		  if(curTable2Row == null){
			  alert("请先选择一条要删除的记录！");
			  return;
		  }
		  if(confirm("确认要删除该条记录?")){
			  var materialReleaseID  = table1.row(curTable1Row).data().materialReleaseID;
			  var materialReleaseItemID = table2.row(curTable2Row).data().materialReleaseItemID;
			  $.post("BaseMaterialAction_delMaterialReleaseItem?&ts="
						+ new Date().getTime(), {
							materialReleaseItemID : materialReleaseItemID,
							 materialReleaseID : materialReleaseID,
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
	        	$("#sMaterialClass").val(matericlasalltable.row(matrclaRow).data()[0]+" "+matericlasalltable.row(matrclaRow).data()[1]);
	        	$("#sMaterialSubclass").val(matericlasalltable.row(matrclaRow).data()[2]+" "+matericlasalltable.row(matrclaRow).data()[3]);
	        	$("#sMaterial").val(matericlasalltable.row(matrclaRow).data()[4]+" "+matericlasalltable.row(matrclaRow).data()[5]);
	        	$(row).addClass('active');
	        }
	  }
	  /*//查询按钮
	  $("#smasubsubclasselect").click(function(){
	  	$(".overlay").show();
	  	$("#table1Save").hide();
	  	 matericlasalltable.row().remove().draw();
	  	$("#subsubclaqueydialog").show();
	  	matrclaRow=null;
	  	  });*/
	  //选中返回
	  $("#confrimback").click(function(){
	  	if(matrclaRow==null){alert("请选中一行");return;}    
	  	$("#closesubsub").click();
	  	  });
	  //取消
	  $("#closesubsub").click(function(){
	  	$("#subsubclaqueydialog").hide();
	  	$("#table1Save").show();
	  //	$("#subsubclassname").val('');
	  	matrclaRow=null;
	  	  });
	  //原料细类查询
	  $("#smasubsubclasselect").click(function(){
	  	   matericlasalltable.row().remove().draw();
	  		var xileiname= $("#sMaterial").val().trim();
	  		if(xileiname==''|| xileiname==null){alert("请输入原料细类名称关键字,不要代码");return;}  
	  		$(".overlay").show();
		  	$("#table1Save").hide();
		  	$("#subsubclaqueydialog").show();
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
	  
	  	 
	 
});