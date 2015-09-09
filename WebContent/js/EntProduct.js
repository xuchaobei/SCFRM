//@ sourceURL=EntProduct.js 
$(document).ready(function(){
	var curTable1Row = null;
	
	var table1Columns = [
	                     {"data": "entProductID"},
	                     {"data": "entCode"},
	                     {"data": "entName"},
	                     {"data": "countryCode"},
	                     {"data": "countryName"},
	                     {"data": "entProductName"},
	                     {"data": "entProductCode"},
	                     {"data": "productCode"},
	                     {"data": "rapidRelease",
                      	  "render": function ( data, type, full, meta ) {
                    	      return data === '1'  ? '是' : '否';
                    	    }
		                 },
		                 {"data": "greenChanel",
                      	  "render": function ( data, type, full, meta ) {
                    	      return data === '1'  ? '是' : '否';
                    	    }
		                 },
		                 {"data": "materialRelease",
	                      	  "render": function ( data, type, full, meta ) {
	                    	      return data === '1'  ? '是' : '否';
	                    	    }
			               },
			             {"data": "ratioRelease",
	                      	  "render": function ( data, type, full, meta ) {
	                    	      return data === '1'  ? '是' : '否';
	                    	    }
			               },
		                 {"data": "samplingLevel",
	                      	  "render": function ( data, type, full, meta ) {
	                      		  if(data=='1'){
	                      			return '低'; 
	                      		  }
	                      		  if(data=='2'){
	                      			return '中'; 
	                      		  }
	                      		  if(data=='3'){
	                      			return '高'; 
	                      		  }
	                    	     // return data === '1'  ? '是' : '否';
	                    	    }
			                 },
	                     {"data": "riskLevelDesc"},
	                     {"data": "lowerSamplingRatio",
                      	  "render": function ( data, type, full, meta ) {
                    	      return data   + '-'+ full.upperSamplingRatio;
                    	    }
			             },
	                     ];
	
	var table1 = $('#table1').DataTable( {
	      "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "ajax":{
			  url : "EntProductAction_getEntProductByQuery",
			  type : "GET",
			  data : function(d){
				  d.data = getTable1RequestParam;
			  }
		  },
	      "columns": table1Columns,
	      "columnDefs": [
	                     {
	                         "targets": [ 0 ,3],
	                         "visible": false,
	                     }
	                 ]
	  } );
	
	var tab1Columns = [
						{"data": "className"},
						{"data": "subclassName"},
						{"data": "materialName"},
						{"data": "sourceName"},
						{"data": "ifMainMaterial",
                     	 "render": function ( data, type, full, meta ) {
                   	      return data === '1'  ? '是' : '否';
                   	   }
			            },
	                   ];
	
	var tab2Columns = [
						{"data": "accessoryName"},
						{"data": "accessoryUse"},
						{"data": "usedProcess"},
	                   ];
	
	var tab3Columns = [
						{"data": "additiveName"},
						{"data": "purpose"},
						{"data": "useProcess"},
						{"data": "useQuantity"},
	                   ];
	
	var tab1Table= $('#tab1Table').DataTable( {
		 /* "scrollY": "70px", */
		  "info" : false,
		  "paging" : false,
		  "lengthChange" : false,
	      "deferRender": true,
		  "processing": true,
	      "columns": tab1Columns,
	  } );
	
	var tab2Table= $('#tab2Table').DataTable( {
		/*  "scrollY": "70px", */
		  "info" : false,
		  "paging" : false,
		  "lengthChange" : false,
	      "deferRender": true,
		  "processing": true,
	      "columns": tab2Columns,
	  } );
	
	var tab3Table= $('#tab3Table').DataTable( {
		/*  "scrollY": "70px", */
		  "info" : false,
		  "paging" : false,
		  "lengthChange" : false,
	      "deferRender": true,
		  "processing": true,
	      "columns": tab3Columns,
	  } );
	
	$('#table1').on( 'draw.dt', drawTable1CB);
	 
	$('#table1 tbody').on("click", "tr", clickTable1Row );
	
	$("#search1").click(searchTable1Btn);
	
	$("#edit1").click(editTable1Btn);
	
	$("#list1Export").click(exportRapidRealease);

	$("#list2Export").click(exportGreenChanel);
	
	$("#list3Export").click(exportMaterialBatch);
	
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
		$(".nav-tabs li.active").removeClass("active");
		$(".nav-tabs li:eq(0)").addClass("active");
	});
	
	
	$("ul.nav-tabs > li").click(setTabDisplay);

	//设置tab切换
	function setTabDisplay(){
		if(! $(this).hasClass("active")){
			$(".nav-tabs li.active").removeClass("active");
			$(this).addClass("active");
			
			var that = this;
            $(".nav-tabs li").each(function(n, value){
            	if(that == value){
            		$("#tab"+n).removeClass("hide");
            	}else{
            		$("#tab"+n).addClass("hide");
            	}
            });			
		}
    }
	
	 function getTable1RequestParam(){
		  var data = {
				 entCode : $("#rEntCode").val().trim(),
				 entName : $("#rEntName").val().trim(),
				 entProductName : $("#rEntProductName").val().trim()
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
	  
    
    function editTable1Btn(){  
    	
		getTable1ItemDetail();
	}
    
    function searchTable1(){
		 table1.draw();
		 //$("#closeTable1Search").click();
		    $(".overlay").hide();           
			$("#table1Search").hide();
	 }
	 
	 function getTable1ItemDetail(){
		  if(curTable1Row == null){
			  alert("请先选择一条记录");
			  return;
		  }
		  $(".overlay").show();
		  $("#table1Save").show();

		  var entCode = table1.row(curTable1Row).data().entCode;
		  var entProductCode = table1.row(curTable1Row).data().entProductCode;
		  var productCode = table1.row(curTable1Row).data().productCode;
		  var countryCode = table1.row(curTable1Row).data().countryCode;
		 
		  $.get("EntProductAction_getEntProductDetailForMng?&ts="
					+ new Date().getTime(), {
						entCode : entCode,
						entProductCode : entProductCode,
						productCode : productCode,
						countryCode : countryCode
			}, function(rdata) {
				setTable1ItemDetail(rdata);
			}, 'json');
	 }
	 

	 function setTable1ItemDetail(rdata) {

		var productBase = rdata.data[0].entProductBase;
		var productMaterialList = rdata.data[0].entProductMaterialList;
		var productAccessoryList = rdata.data[0].entProductAccessoryDtoList;
		var productAdditiveList = rdata.data[0].entProductAdditiveDtoList;
		var entProductReleaseModeList = rdata.data[0].entProductReleaseModeDto;
		$("#sEntProductInputID").val(productBase.entProductInputID);
		$("#sEntCode").val(productBase.entCode);
		$("#sEntName").val(productBase.entName);
		$("#sEntProductCode").val(productBase.entProductCode);
		$("#sEntProductName").val(productBase.entProductName);
		$("#sProductCode").val(productBase.productCode);
		$("#sClassName").val(productBase.className);
		$("#sSubclassName").val(productBase.subclassName);
		$("#sMethodName").val(productBase.methodName);
		$("#sTypeName").val(productBase.typeName);
		$("#sUseName").val(productBase.useName);
		$("#sDegreeName").val(productBase.degreeName);
		$("#sConditionName").val(productBase.conditionName);
		$("#sCountryCode").val(table1.row(curTable1Row).data().countryCode);
		$("#sCountryName").val(table1.row(curTable1Row).data().countryName);
		if (table1.row(curTable1Row).data().rapidRelease == "1") {
			$("#sRapidRelease").prop("checked", true);
		} else {
			$("#sRapidRelease").prop("checked", false);
		}
		if (table1.row(curTable1Row).data().greenChanel == "1") {
			$("#sGreenChanel").prop("checked", true);
		} else {
			$("#sGreenChanel").prop("checked", false);
		}
		if (entProductReleaseModeList[0].materialRelease == "1") {
			$("#sMaterialRelease").prop("checked", true);
		} else {
			$("#sMaterialRelease").prop("checked", false);
		}
		if (entProductReleaseModeList[0].ratioRelease == "1") {
			$("#sRatioRelease").prop("checked", true);
		} else {
			$("#sRatioRelease").prop("checked", false);
		}
		$("#tab0,#tab1,#tab2").removeClass("hide");
		tab1Table.clear().rows.add(productMaterialList).draw();
		tab2Table.clear().rows.add(productAccessoryList).draw();
		tab3Table.clear().rows.add(productAdditiveList).draw();
		$("#tab1,#tab2").addClass("hide");
	 };
	 
	 function setTable1ItemDetail1(rdata){
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
		 
		  setEntProductRapidRelease();
		  /* 下面的不要了
		   * var data = null;
		  if(table1.row(curTable1Row).data().rapidRelease == "1" && !$("#sRapidRelease").is(':checked') ){
			  setEntProductRapidRelease();
		  }else if(table1.row(curTable1Row).data().rapidRelease == "0" && $("#sRapidRelease").is(':checked')){
			  setEntProductRapidRelease();
		  }
		
		  if(table1.row(curTable1Row).data().greenChanel == "1" && !$("#sGreenChanel").is(':checked')){
			  setEntProductGreenChanel();
		  }else if(table1.row(curTable1Row).data().greenChanel == "0" && $("#sGreenChanel").is(':checked')){
			  setEntProductGreenChanel();
		  }*/
		  
		  
	 }
	 
	 function setEntProductRapidRelease(){
		 var entCode = table1.row(curTable1Row).data().entCode;
		 var productCode = table1.row(curTable1Row).data().productCode;
		 var countryCode = table1.row(curTable1Row).data().countryCode;
		 var entProductCode = table1.row(curTable1Row).data().entProductCode;
		var rapidRelease=null;
		var greenChanel=null;
		var materialRelease=null;
		var ratioRelease=null;
		 if($("#sRapidRelease").prop("checked")){
			 rapidRelease=1;
		 }else{
			 rapidRelease=0;
		 }
		 if($("#sGreenChanel").prop("checked")){
			 greenChanel=1;
		 }else{
			 greenChanel=0;
		 }
		 if($("#sMaterialRelease").prop("checked")){
			 materialRelease=1;
		 }else{
			 materialRelease=0;
		 }
		 if($("#sRatioRelease").prop("checked")){
			 ratioRelease=1;
		 }else{
			 ratioRelease=0;
		 }
		 if((rapidRelease==1 && greenChanel==0 && materialRelease==0 && ratioRelease==0)
			|| (rapidRelease==0 && greenChanel==1 && materialRelease==0 && ratioRelease==0)
			|| (rapidRelease==0 && greenChanel==0 && materialRelease==1 && ratioRelease==0)
			|| (rapidRelease==0 && greenChanel==0 && materialRelease==0 && ratioRelease==1))
		 {
			 $.post("EntProductAction_setEntProductReleaseMode?&ts="
						+ new Date().getTime(), {
							entCode : entCode,
							productCode : productCode,
							countryCode : countryCode,
							entProductCode : entProductCode,
							rapidRelease :rapidRelease,
							greenChanel :greenChanel,
							materialRelease :materialRelease,
							ratioRelease :ratioRelease
				}, function(rdata) {
					if(rdata.data == "true"){
						alert("操作成功");
						table1.draw(false);
						$("#closeTable1Save").click();
					}else{
						alert(rdata.data);
						$("#closeTable1Save").click();
					}
				}, 'json');
		 }else{
			 alert("四中验放模式只能选择一种");
			 
		 }
		
	 }
	 
	 function setEntProductGreenChanel(){
		 var entCode = table1.row(curTable1Row).data().entCode;
		 var productCode = table1.row(curTable1Row).data().productCode;
		 var countryCode = table1.row(curTable1Row).data().countryCode;
		 var entProductCode = table1.row(curTable1Row).data().entProductCode;
		 $.post("EntProductAction_setEntProductGreenChanel?&ts="
					+ new Date().getTime(), {
						entCode : entCode,
						productCode : productCode,
						countryCode : countryCode,
						entProductCode : entProductCode
			}, function(rdata) {
				if(rdata.data == "true"){
					table1.draw(false);
				}else{
					alert(rdata.data);
				}
			}, 'json');
	 }
	 
	 function exportRapidRealease(){
		 window.location.href="ExportExcelAction?serviceName=entProduct.EntProductService&filename=RapidRelease";
	 }
	 
	 function exportGreenChanel(){
		 window.location.href="ExportExcelAction?serviceName=entProduct.EntProductService&filename=GreenChanel";
	 }
	 function exportMaterialBatch(){
		 window.location.href="ExportExcelAction?serviceName=entProduct.EntProductService&filename=MaterialBatch";
	 }
});