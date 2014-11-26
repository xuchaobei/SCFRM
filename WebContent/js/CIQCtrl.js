//@ sourceURL=CIQCtrl.js  
$(document).ready(function(){
	var curTable1Row = null;
	getIfExecValue();
	var table1Columns = [
	                     {"data": "ciqControlID"},
	                     {"data": "controlName"},
	                     {"data": "controlReason"},
	                     {"data": "remarks"},
	                     {"data": "deadline"},
	                     {"data": "ifExec",
                    	   "render": function ( data, type, full, meta ) {
                    	       return data === '1'  ? '生效' : '无效';
                    	    }
	                     },
	                     {"data": "ifCheck",
                      	  "render": function ( data, type, full, meta ) {
                    	      return data === '1'  ? '通过' : '不通过';
                    	    }
	                     },
	                     {"data": "controlInputDatetime"},
	                     {"data": "controlDeptName"},
	                     ];
	
	
	var table2Columns = [
	                     {"data": "ciqControlConditionID"},
	                     {"data": "orderNo"},
	                     {"data": "leftLogic"},
	                     {"data": "definedField"},
	                     {"data": "calculationDesc"},
	                     {"data": "keywords"},
	                     {"data": "rightLogic"},
	                     {"data": "keywordsDesc"},
	                     ];
	
	var table3Columns = [
	                     {"data": "ciqControlItemID"},
	                     {"data": "itemCode"},
	                     {"data": "itemName"},
	                     {"data": "samplingMode"},
	                     {"data": "samplingParamValue"},
	                     {"data": "detectionStd"},
	                     {"data": "limitReq"},
	                     ];
	
	
	
	/* ------------- table1响应事件 ------------------ */
	
	var table1 = $('#table1').DataTable( {
	      "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "ajax":{
			  url : "CIQCtrlAction_getCIQControl",
			  type : "GET",
			  data : function(d){
				  d.data = null;
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
	
	$("#search1").click(function(){
		$(".overlay").show();
	    $("#table1Search").show();
	});
	
	$("#add1").click(function(){
		$(".overlay").show();
		$("#table1Save").show();
	});
	
	$("#edit1").click(function(){
		$(".overlay").show();
		$("#table1Save").show();
	});
	
	$("#delete1").click(function(){
		
	});
	
	$("#test1").click(function(){
		
	});
	
	$("#validate1").click(function(){
		
	});
	
	$("#searchTable1").click();
	
	$("#closeTable1Search").click(function(){
		$(".overlay").hide();
		$("#table1Search").hide();
	});
	
	$("#closeTable1Save").click(function(){
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
			  url : "CIQCtrlAction_getCIQControlCondition",
			  type : "GET",
			  data : function(d){
				  if(curTable1Row == null){
					  return {ciqControlID : 0};
				  }else{
					  return {ciqControlID : table1.row(curTable1Row).data().ciqControlID};
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
	
	
	$(".nav-tabs > li").click(setTabDisplay);
	
    $("#add2").click(function(){
		
	});
	$("#edit2").click(function(){
		
	});
	$("#delete2").click(function(){
		
	});
	
	
	/* ------------- table3响应事件 ------------------ */
	
	var table3 = $('#table3').DataTable( {
		  "info" : false,
		  "paging" : false,
		  "lengthChange" : false,
	      "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "ajax":{
			  url : "CIQCtrlAction_getCIQControlItem",
			  type : "GET",
			  data : function(d){
				  if(curTable1Row == null){
					  return {ciqControlID : 0};
				  }else{
					  return {ciqControlID : table1.row(curTable1Row).data().ciqControlID};
				  }
			  }
		  },
	      "columns": table3Columns,
	      "columnDefs": [
	                     {
	                         "targets": [ 0 ],
	                         "visible": false,
	                     }
	                 ]
	  } );
	
    $("#add3").click(function(){
		
	});
	$("#edit3").click(function(){
		
	});
	$("#delete3").click(function(){
		
	});
	
	//应急布控列表获取数据后的回调
	function drawTable1CB() {
		  if(table1.rows().data().length > 0){
			  var dtRow = table1.row(0);
			  $(dtRow.node()).click();
		  }
		  curTable1Row = null;
	 } 
	
	//点击应急布控列表行的回调
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
		    table3.draw();
	}
	
	  //获取应急布控列表的请求参数
	  function getTable1RequestParam(){
		  var data = {
				  controlName : getSelectValue("rControlName"),
				  controlReason : getSelectValue("rControlReason"),
				  ifExec : getIfExecValue(),
				  ifCheck : getIfCheckValue() ,
				  controlOrgCode : getSelectValue("rControlOrg"),
				  controlDeptCode : getSelectValue("rControlDept"),
		  };
		  var jsonstr = JSON.stringify(data);
		  return jsonstr;
	  }
	
	 //应急布控是否生效
	 function getIfExecValue(){
		 if($("#execTrue").attr("checked") == true && $("#execFalse").attr("checked") == false){
			 return "1";
		 }else if($("#execTrue").attr("checked") == false && $("#execFalse").attr("checked") == true){
			 return "2";
		 }else{
			 return "3";
		 }
	 }
	
	 //应急布控是否测试通过
	 function getIfCheckValue(){
		 if($("#checkTrue").attr("checked") == true && $("#checkFalse").attr("checked") == false){
			 return "1";
		 }else if($("#checkTrue").attr("checked") == false && $("#checkFalse").attr("checked") == true){
			 return "2";
		 }else{
			 return "3";
		 }
	 }
	 
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
	
});