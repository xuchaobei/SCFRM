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
	                         "targets": [ 0 ],
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
		  "scrollY": "70px", 
		  "info" : false,
		  "paging" : false,
		  "lengthChange" : false,
	      "deferRender": true,
		  "processing": true,
	      "columns": tab1Columns,
	  } );
	
	var tab2Table= $('#tab2Table').DataTable( {
		  "scrollY": "70px", 
		  "info" : false,
		  "paging" : false,
		  "lengthChange" : false,
	      "deferRender": true,
		  "processing": true,
	      "columns": tab2Columns,
	  } );
	
	var tab3Table= $('#tab3Table').DataTable( {
		  "scrollY": "70px", 
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
		 $("#closeTable1Search").click();
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
		  $.get("EntProductAction_getEntProductDetailByID?&ts="
					+ new Date().getTime(), {
						entCode : entCode,
						entProductCode : entProductCode
			}, function(rdata) {
				setTable1ItemDetail(rdata);
			}, 'json');
	 }
	 

	 function setTable1ItemDetail(rdata) {

		var productBase = rdata.data[0].entProductBase;
		var productMaterialList = rdata.data[0].entProductMaterialList;
		var productAccessoryList = rdata.data[0].entProductAccessoryDtoList;
		var productAdditiveList = rdata.data[0].entProductAdditiveDtoList;
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
		  var data = null;
		  if(table1.row(curTable1Row).data().rapidRelease == "1" && !$("#sRapidRelease").is(':checked') ){
			  setEntProductRapidRelease();
		  }else if(table1.row(curTable1Row).data().rapidRelease == "0" && $("#sRapidRelease").is(':checked')){
			  setEntProductRapidRelease();
		  }
		
		  if(table1.row(curTable1Row).data().greenChanel == "1" && !$("#sGreenChanel").is(':checked')){
			  setEntProductGreenChanel();
		  }else if(table1.row(curTable1Row).data().greenChanel == "0" && $("#sGreenChanel").is(':checked')){
			  setEntProductGreenChanel();
		  }
		  
		  $("#closeTable1Save").click();
	 }
	 
	 function setEntProductRapidRelease(){
		 var entCode = table1.row(curTable1Row).data().entCode;
		 var productCode = table1.row(curTable1Row).data().productCode;
		 var countryCode = table1.row(curTable1Row).data().countryCode;
		 var entProductCode = table1.row(curTable1Row).data().entProductCode;
		 $.post("EntProductAction_setEntProductRapidRelease?&ts="
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
	  
});