$(document).ready(function(){
	var classRow=null;
	var subclassRow=null;
	var subsubclassRow=null;
	var seFlg=null;
	var useflg=null;
	var menunam='原料类别设置';
//原料大类表数据的获取
	var columnClass = [
		                  { "data": "materialClassID" },
		                  { "data": "classCode" },
		      	          { "data": "className" },
		                 ];
	var classtable = $('#MaterialClassTb').DataTable( {
			  "deferRender": true,
			  "processing": true,
			  "serverSide": true,
			  "ajax":{
				  url : "MaterialClassAction_getMaterialClass",
				  type : "GET",
				  data : function(d){
					//  d.data = getClassReqParam();
					 
				  }
			  },
		      "columns": columnClass,
		    "columnDefs": [
		                     {
		                         "targets": [ 0 ],
		                         "visible": false,
		                     }
		                 ]
		  } );	
	
//原料小类表数据的获取
	var columnSubClass = [
		                  { "data": "materialSubclassID" },
		                  { "data": "classCode" },
		      	          { "data": "subclassCode" },
		      	          { "data": "subclassName" },
		                 ];
	var subclasstable = $('#MaterialSubclassTb').DataTable( {
			  "deferRender": true,
			  "processing": true,
			  "serverSide": true,
			  "ajax":{
				  url : "MaterialClassAction_getMaterialSubClass",
				  type : "GET",
				  data : function(d){
					  if(classRow == null){
						 // alert("code"+classtable.row(classRow).data().classCode);
						  //return {classCode : 0};
						  d.data=JSON.stringify({classCode : '0'});
					  }else{
						  //获得convCtrlID
						  //alert("classcode"+classtable.row(classRow).data().classCode);
						  //return {classCode : classtable.row(classRow).data().classCode};
						  d.data=JSON.stringify({classCode : classtable.row(classRow).data().classCode});
					  }
					
				  }
			  },
		      "columns": columnSubClass,  
		    "columnDefs": [
		                     {
		                         "targets": [ 0 ],
		                         "visible": false,
		                     }
		                 ]
		  } );	
//原料细类表数据的获取
	var columnSubsubClass = [
		                  { "data": "materialID" },
		                  { "data": "classCode" },
		      	          { "data": "subclassCode" },
		      	          { "data": "materialCode" },
		      	          { "data": "materialName" },
		      	          { "data": "ifSet" },
		                 ];
	var subsubclasstable = $('#MaterialSubsubClasssTb').DataTable( {
			  "deferRender": true,
			  "processing": true,
			  "serverSide": true,
			  "ajax":{
				  url : "MaterialClassAction_getMaterialSubsubClass",
				  type : "GET",
				  data : function(d){
					  if(subclassRow==null){  
						  d.data=JSON.stringify({classCode : 0,subclassCode :0});
					 
					  }else{
						  d.data=JSON.stringify({classCode : classtable.row(classRow).data().classCode,
						      subclassCode :subclasstable.row(subclassRow).data().subclassCode});
					  }
					 
				  }
			  },
		      "columns": columnSubsubClass,
		    "columnDefs": [
		                     {
		                         "targets": [ 0 ,1 ,2 ,5],
		                         "visible": false,
		                     }
		                 ]
		  } );
/*//初始化大类第一行默认选中
	$('#MaterialClassTb').on( 'draw.dt', function () {
		  if(classtable.rows().data().length > 0){
			  var dtRow = classtable.row(0);
			  $(dtRow.node()).click();
		  }
		  subclassRow=null;
		  
	  } );	*/
//初始化大类第一行默认选中
	$('#MaterialClassTb').on( 'draw.dt', function () {
		  if(classtable.rows().data().length > 0){
			  var dtRow = null;
			  var node = null;
			  if(classRow != null){
			  dtRow = classtable.row($(classRow).context._DT_RowIndex);
			  node = dtRow.node();
			  if(node != null){
			  $(node).click();
			  return;
			  }
			  }
			  dtRow = classtable.row(0);
			  node = dtRow.node();
			  $(node).click();
         }else{
        	 classRow=null;
         }
		  subclassRow=null;
		  
	  } );

/*//初始化小类第一行默认选中
	$('#MaterialSubclassTb').on( 'draw.dt', function () {
		  if(subclasstable.rows().data().length > 0){
			  var dtRow = subclasstable.row(0);
			  $(dtRow.node()).click();
		  }else{
			  subsubclasstable.draw();  
			  alert("xiao null clear xilei"); 
			  $("#MaterialSubsubClasssTb tbody").empty();
			  $showlist=$("#MaterialSubsubClasssTb");
				var ctbody=$("<tbody></tbody>").appendTo($showlist);
				var ctr=$("<tr class='odd'></tr>").appendTo($(ctbody));
				$('<td class="dataTables_empty" valign="top" colspan="5">数据为空</td>').appendTo($(ctr));
		 }
		  subsubclassRow=null;
		  
	  } );*/
//初始化小类第一行默认选中
	$('#MaterialSubclassTb').on( 'draw.dt', function () {
		  if(subclasstable.rows().data().length > 0){
			  var dtRow = null;
			  var node = null;
			  if(subclassRow != null){
			  dtRow = subclasstable.row($(subclassRow).context._DT_RowIndex);
			  node = dtRow.node();
			  if(node != null){
			  $(node).click();
			  return;
			  }
			  }
			  dtRow = subclasstable.row(0);
			  node = dtRow.node();
			  $(node).click();
         }else{
        	 subclassRow=null;
        	 subsubclasstable.draw(); 
         }
		  subsubclassRow=null;
		  
	  } );	
	
//初始化细类第默认选中小类
	$('#MaterialSubsubClasssTb').on( 'draw.dt', function (){
		  if(subsubclasstable.rows().data().length > 0){
			  var dtRow = null;
			  var node = null;
			  if(subsubclassRow != null){
			  dtRow = subsubclasstable.row($(subsubclassRow).context._DT_RowIndex);
			  node = dtRow.node();
			  if(node != null){
			  $(node).click();
			  return;
			  }
			  }
			  dtRow = subsubclasstable.row(0);
			  node = dtRow.node();
			  $(node).click();
		 }else{
			 subsubclassRow=null;
         }
		 
	  } );
	
//大类 小类 细类 表中某一行的点击功能
    $('#MaterialClassTb tbody').on("click", "tr", clickclassRow );
	$('#MaterialSubclassTb tbody').on("click", "tr", clicksubclassRow );
	$('#MaterialSubsubClasssTb tbody').on("click", "tr", clicksubsubclassRow );
//选中原料大类表中的一行
	function clickclassRow(){  
		  var row = this;
		  if ( $(row).hasClass('active') ) {  
			    classRow = null;
	            $(row).removeClass('active');
	        }
	        else {
	        	classRow = row;
	            classtable.$('tr.active').removeClass('active');
	            $(row).addClass('active');
	            
	          
	        }
		  subclasstable.draw();       
		  subclassRow = null;  
	  }
//选中原料小类表中的一行
	function clicksubclassRow(){ 
		 var row = this;
		  if ( $(row).hasClass('active') ) {  
			    subclassRow = null;
	            $(row).removeClass('active');
	        }
	        else {
	        	subclassRow = row;
	            subclasstable.$('tr.active').removeClass('active');
	            $(row).addClass('active');
	            
	             
	        }
		  
		  subsubclasstable.draw(); 
		  subsubclassRow = null;  
	  }
//选中原料细类表中的一行
	function clicksubsubclassRow(){  
		  var row = this;
		  if ( $(row).hasClass('active') ) {  
			    subsubclassRow = null;
	            $(row).removeClass('active');    
	        }
	        else {
	        	subsubclassRow = row;
	        	subsubclasstable.$('tr.active').removeClass('active');
	            $(row).addClass('active');
	        }
   }
	
//原料大类新增/修改按钮
$("#addMaterialClass").click(function() {
	  useflg=1;
	 getoperatelimit(menunam, useflg);
	    
});
$("#editMaterialClass").click(function() { 
	useflg=2;
	 getoperatelimit(menunam, useflg);
	
});
//原料大类取消按钮
$("#closeaddMaterialClassDialogBtn").click(function(){
	  $(".overlay").hide();
    $("#addMaterialClassDialog").hide();
    $("#classCode").val("");
	$("#className").val(""); 
    /*$("#setsleftlistall tbody").empty(); 
    $("#setsrightlistall tbody").empty(); 
    $("#processingWayName").val("");
	  $("#processingWayCode").val("");
	  $("#ifsetsets").prop("checked",false);*/
    
});

//原料大类保存功能
$("#saveMaterialClassBtn").bind('click', function(){ 
	
		if(seFlg==1){
		
			addEditMaterialClass('0');
		  }
		  if(seFlg==2){
			
			var materialClassID=classtable.row(classRow).data().materialClassID;
			addEditMaterialClass(materialClassID);
		  }
	 
});
//原料大类新增/修改函数---------------------------------------------------------------------------------------
function addEditMaterialClass(clCode){
	      
		 var classCode = $("#classCode").val().trim(); 
		  var className =$("#className").val().trim(); 
		  if(classCode.length !=2){alert("原料大类编号长度为2");return;}
		  if(classCode==null || classCode=="" ||className==null || className=="" )
		   {alert("信息填写完整");}
		  else{
			  data={  materialClassID:clCode,     
					  classCode:classCode,
					  className:className
		    	};   
			  var jsonstr = JSON.stringify(data);
		  $.post("MaterialClassAction_SaveMaterialClass?&ts="   
					+ new Date().getTime(), {
						 data : jsonstr
					}, function(rdata) {
				if(rdata.data == "true"){
					if(seFlg==1){
						classRow=null;
						classtable.draw();
					}else if(seFlg==2){
						classtable.draw(false);
					}
					
					$("#closeaddMaterialClassDialogBtn").click();
				}else{
					alert(rdata.data);
					//$("#closeaddMaterialClassDialogBtn").click();
				}
			}, 'json');
	  }	
	
		
	}
//原料大类删除函数
$('#deleteMaterialClass').click(function(){
	 useflg=3;
	 getoperatelimit(menunam, useflg);
});
function delMaterialClass(){
	  if(classRow == null){
		  alert("请先选择一条要删除的记录！");
		  return;
	  }
	  if(confirm("确定要删除吗?")){  
		  var classCode = classtable.row(classRow).data().classCode;
		  $.post("MaterialClassAction_DelMaterialClass?&ts="
					+ new Date().getTime(), {
						data:JSON.stringify({classCode : classCode})  
			}, function(rdata) {
				if(rdata.data == "true"){
					alert("删除成功！");
					classtable.row(classRow).remove().draw(false);
				}
			}, 'json');
	  }

  
		
	}
//原料小类新增/修改按钮
$("#addMaterialSubclass").click(function() {  

useflg=4;
getoperatelimit(menunam, useflg);
	
});
$("#editMaterialSubclass").click(function() {
useflg=5;
getoperatelimit(menunam, useflg);
	
});
//原料小类取消按钮
$("#closeupdateMaterialSubclassDialogBtn").click(function(){
	  $(".overlay").hide();
  $("#updateMaterialSubclassDialog").hide();
  $("#MaterialSubclasssubclassCode").val("");
	 $("#MaterialSubclasssubclassName").val("");
	 $("#MaterialSubclassclassCode").val("");
  /*$("#setsleftlistall tbody").empty(); 
  $("#setsrightlistall tbody").empty(); 
  $("#processingWayName").val("");
	  $("#processingWayCode").val("");
	  $("#ifsetsets").prop("checked",false);*/
  
});
//原料小类保存功能
$("#updateMaterialSubclassBtn").bind('click', function(){ 
	
	if(seFlg==3){
		
		addEditSubclass('0');
	  }
	  if(seFlg==4){
		
		var materialSubclassID=subclasstable.row(subclassRow).data().materialSubclassID;
		addEditSubclass(materialSubclassID);
	  }
 
});
//原料小类保存函数
function addEditSubclass(clCode){ 
	 var classCode = classtable.row(classRow).data().classCode;
	 var subclassCode = $("#MaterialSubclasssubclassCode").val().trim();
	  var subclassName = $("#MaterialSubclasssubclassName").val().trim();
	  if(subclassCode.length !=2){alert("原料小类编号长度为2");return;}
	  if(subclassCode==null || subclassCode=="" ||subclassName==null || subclassName=="" )
	   {alert("信息填写完整");}
	  else{
		  data={    materialSubclassID:clCode,
				  classCode:classCode,
				  subclassCode:subclassCode,
				  subclassName:subclassName
	    	};   
		  var jsonstr = JSON.stringify(data);
	  $.post("MaterialClassAction_SaveMaterialSubclass?&ts="   
				+ new Date().getTime(), {
					 data : jsonstr
				}, function(rdata) {
			if(rdata.data == "true"){
				if(seFlg==3){
					subclassRow=null;
					subclasstable.draw();
				}else if(seFlg==4){
					subclasstable.draw(false);
				}
				
				$("#closeupdateMaterialSubclassDialogBtn").click();
			}else{
				alert(rdata.data);
				//$("#closeupdateMaterialSubclassDialogBtn").click();
			}
		}, 'json');
 }		
}
//原料小类删除函数
$('#deleteMaterialSubclass').click(function(){
	
	 useflg=6;
	 getoperatelimit(menunam, useflg);
});
function delsubClass(){
	  if(subclassRow == null){
		  alert("请先选择一条要删除的记录！");
		  return;
	  }
	  if(confirm("确定要删除吗?")){  
		  var classCode = classtable.row(classRow).data().classCode;
		  var subclassCode=subclasstable.row(subclassRow).data().subclassCode;
		  $.post("MaterialClassAction_DelMaterialSubclass?&ts="
					+ new Date().getTime(), {
						data:JSON.stringify({classCode : classCode,subclassCode:subclassCode})  
			}, function(rdata) {
				if(rdata.data == "true"){
					alert("删除成功！");
					subclasstable.row(subclassRow).remove().draw(false);//只有一行有个错误
				}
			}, 'json');
	  }
	}
//原料细类新增/修改按钮
$("#addMaterialSubsubClasss").click(function() {  
	
	 useflg=7;
	 getoperatelimit(menunam, useflg);
	
});
$("#editMaterialSubsubClasss").click(function() { 
	useflg=8;
	 getoperatelimit(menunam, useflg);
	
});
$("#deleteMaterialSubsubClasss").click(function() { 
	 useflg=9;
	 getoperatelimit(menunam, useflg);
	
});
//原料细类取消按钮
$("#closeupdateMaterialSubsubClasssDialogBtn").click(function(){
	  $(".overlay").hide();
  $("#updateMaterialSubsubClasssDialog").hide();   
  $("#setsleftlistall tbody").empty(); 
  $("#setsrightlistall tbody").empty(); 
      $("#MaterialSubsubClasssclassCode").val("");
	  $("#MaterialSubsubClassssubclassCode").val("");
	  $("#MaterialSubsubClasssmaterialCode").val("");
	  $("#MaterialSubsubClasssmaterialName").val("");
	  $("#ifsetsets").prop("checked",false);
  
});
//原料细类保存功能
$("#saveMaterialSubsubClasssBtn").bind('click', function(){ 
	
	
		  if(seFlg==5){
			
			  savexilei('0');
		  }
		  if(seFlg==6){
			var materialID=subsubclasstable.row(subsubclassRow).data().materialID;
			savexilei(materialID);
		  }
	   
});
//原料细类保存函数
function savexilei(mtid){
	 var methodCode= $("#MaterialSubsubClasssmaterialCode").val().trim();
	 var materialName= $("#MaterialSubsubClasssmaterialName").val().trim();
	 if(methodCode==null || methodCode=="" ||materialName==null || materialName=="" )
	   {alert("信息填写完整"); return;}
    if($($("input[name='ifsetsets']")).prop("checked")){
 	  
 	
    	
   	var setsdata={
			 materialID:mtid,
			 classCode:$("#MaterialSubsubClasssclassCode").val().trim(),
			 subclassCode:$("#MaterialSubsubClassssubclassCode").val().trim(),
			 materialCode:methodCode,
			 materialName:materialName,
  			 ifSet:1
	 };
	 var setsnamedata = JSON.stringify(setsdata);
		 var count=0;  
		 setscontent = new Array();
		 $("#setsrightlistall tbody tr").each(function(index){
				var temp=$(this).children().eq(1).html();
				setscontent[count]=temp;
				count++;
		  });
		 if(methodCode.length >=4 || methodCode.length <2){alert("原料细类编号长度必须大于等于2,小于4");return;}
		if(count <2){alert("集合定义右侧数据栏至少有2个原料细类数据");return;}
		 $.post( 
				"MaterialClassAction_SaveMaterialSubsubclassSub",
  				{data:setsnamedata,setscontent:setscontent.toString()},
  				function(rdata) {
  					if(rdata.data=="true"){
  						if(seFlg==5){
  							   
  							subsubclassRow=null;
  							subsubclasstable.draw();
  						}else if(seFlg==6){
  							subsubclasstable.draw(false);
  						}
  						
  						 $("#closeupdateMaterialSubsubClasssDialogBtn").click();
  					}
  					else{
  						alert(rdata.data);
  					}
  				}, 
  				'json');
   	 }
    else{//保存非集合加工方式  
    	savexiNS(mtid);
   	
   	// $("#fuzzy").toggle();
   	 }           
	  
}
//保存非集合数据函数
function savexiNS(id){           
	
	 var methodCode= $("#MaterialSubsubClasssmaterialCode").val().trim();
   	 if(methodCode.length !=2){alert("原料细类编号长度为2");return;}
	 var setsdata={
			 materialID:id,
			 classCode:$("#MaterialSubsubClasssclassCode").val().trim(),
			 subclassCode:$("#MaterialSubsubClassssubclassCode").val().trim(),
			 materialCode:methodCode,
			 materialName:$("#MaterialSubsubClasssmaterialName").val().trim(),
   			 ifSet:0
	 };
	 var setsnamedata = JSON.stringify(setsdata);
		 $.post( 
				 "MaterialClassAction_SaveMaterialSubsubclass?&ts="+new Date().getTime(),
			{data:setsnamedata},
			function(rdata) {
			if(rdata.data=="true"){	
				if(seFlg==5){
					    subsubclassRow=null;
						subsubclasstable.draw();
					}else if(seFlg==6){
						subsubclasstable.draw(false);
					}
				 $("#closeupdateMaterialSubsubClasssDialogBtn").click();
		        }
			else{
					alert(rdata.data);
				}
			}, 
			'json');
}

function delMaterialSubsubclass(){

	  if(subsubclassRow == null){
		  alert("请先选择一条要删除的记录！");
		  return;
	  }
	  if(confirm("确定要删除吗?")){  
		  var classCode = subsubclasstable.row(subsubclassRow).data().classCode;
		  var subclassCode = subsubclasstable.row(subsubclassRow).data().subclassCode;
		  var materialCode = subsubclasstable.row(subsubclassRow).data().materialCode;
		  $.post("MaterialClassAction_DelMaterialSubsubclass?&ts="
					+ new Date().getTime(), {
						data:JSON.stringify({classCode : classCode,subclassCode:subclassCode,materialCode:materialCode})  
			}, function(rdata) {
				if(rdata.data == "true"){
					alert("删除成功！");
					subsubclasstable.row(subsubclassRow).remove().draw(false);
				}
			}, 'json');
	  }


		
	
	
	
}


/*function ifvalitedclass(){
	 var classCode = $("#classCode").val().trim();
	  var className = $("#className").val().trim();
	 var len= classCode.length;
	 if(len != '2'){alert("bianhao ge shi error");}
}*/



//获取原料大类请求参数 
function getClassReqParam(){  
	  var data = {
			  classCode : $("#classCode").val().trim(),
			  className : $("#className").val().trim()
			     };
	  var jsonstr = JSON.stringify(data);
	  
	  return jsonstr;
}

//点击定义集合复选框功能
$("#ifsetsets").bind('click', function(){
	  if($($("input[name='ifsetsets']")).prop("checked")){ 
		    var classCode=$("#MaterialSubsubClasssclassCode").val().trim();
			 var subclassCode=$("#MaterialSubsubClassssubclassCode").val().trim();
		    getsubsubsub(classCode, subclassCode);
	    }
	    else{
		    $("#setsleftlistall tbody").empty();
		    $("#setsrightlistall tbody").empty();
	    }
   });
//获取集合数据
	function getsubsubsub(clcode,clsubcode){
		$.post("MaterialClassAction_getMaterialSubsubClassNP", 
				  {data : JSON.stringify({classCode : clcode, subclassCode :clsubcode})}, 
		    function(rdataobj) {  
			 showsetsleft(rdataobj);
	}, 'json');
	    }
	
//集合左边
function showsetsleft(rdataobj){
	
	$showlist=$($("#setsleftlistall")); 
	var ctbody=$("<tbody></tbody>").appendTo($showlist);
	for(i=0;i<rdataobj.data.length;i++){
	    var ctr=$("<tr></tr>").appendTo($(ctbody));
	    var ctd=$('<td width="20%"><input type="checkbox" name="checkbox_leftsets"></input></td>').appendTo($(ctr));
	    var ctd=$('<td width="25%">'+rdataobj.data[i].materialCode+'</td>').appendTo($(ctr));
	    var ctd=$('<td width="55%">'+rdataobj.data[i].materialName+'</td>').appendTo($(ctr));
		$(ctr).mouseover(function(){
			$(this).toggleClass('oddrow');
		});
		$(ctr).mouseout(function(){
			$(this).toggleClass("oddrow");     
		});
	}
}
//添加>>  集合左边到右边
$("#addset").bind('click', function(){
	var count=0;
	setsarray = new Array();//左边代码和内容集合
	$("input[name='checkbox_leftsets']").each(function(index){
		    
	if($(this).prop("checked")){   //jquery1.6改进了$(this).attr("checked")
		    var temp=$(this).parents().next().html();
			temp=temp+" "+$(this).parents().next().next().html();
			setsarray[count]=temp;
			count++;
		}
	});
	 count=0;
	 setscontent = new Array();//右边代码集合
	 $("#setsrightlistall tbody tr").each(function(index){
			var temp=$(this).children().eq(1).html();
			setscontent[count]=temp;
			count++;
	  });
	 
	$showlist=$("#setsrightlistall");
	var ctbody=$("<tbody></tbody>").appendTo($showlist);
	for(var i=0;i<setsarray.length;i++){
		var tempstring=setsarray[i].split(" ");
		var ifExist=false;
		for(var j=0;j<setscontent.length;j++){
			if(tempstring[0]==setscontent[j]){ifExist=true;}
		}
		if(ifExist==false){
	    var ctr=$("<tr></tr>").appendTo($(ctbody));
	    var ctd=$('<td width="20%"><input type="checkbox" name="checkbox_rightsets"></input></td>').appendTo($(ctr));
	    var ctd=$('<td width="25%">'+tempstring[0]+'</td>').appendTo($(ctr));
	    var ctd=$('<td width="25%">'+tempstring[1]+'</td>').appendTo($(ctr));
		$(ctr).mouseover(function(){
			$(this).toggleClass("oddrow");
		});
		$(ctr).mouseout(function(){
			$(this).toggleClass("oddrow");
		});
		}				
	}
});
//移除<<  右边选择删除
$("#delset").bind('click', function(){
	var count=0;
	setsarray = new Array();
	$("input[name='checkbox_rightsets']").each(function(index){
		if($(this).prop("checked")==false){
			var temp=$(this).parents().next().html();
			temp=temp+" "+$(this).parents().next().next().html();
			setsarray[count]=temp;
			count++;
		}
	});
	$("#setsrightlistall tbody").empty();   
	$showlist=$("#setsrightlistall");
	var ctbody=$("<tbody></tbody>").appendTo($showlist);
	for(var i=0;i<setsarray.length;i++){
		var tempstring=setsarray[i].split(" ");
	    var ctr=$("<tr></tr>").appendTo($(ctbody));
	    var ctd=$('<td width="20%"><input type="checkbox" name="checkbox_rightsets"></input></td>').appendTo($(ctr));
	    var ctd=$('<td width="25%">'+tempstring[0]+'</td>').appendTo($(ctr));
	    var ctd=$('<td width="25%">'+tempstring[1]+'</td>').appendTo($(ctr));
	}
});
//填充集合右侧数据
function getxileiSub(classCode,subclassCode,materialCode){

	$.post("MaterialClassAction_getMaterialSubsubclassSub", 
			  {data:JSON.stringify({classCode:classCode,subclassCode:subclassCode,materialCode:materialCode})}, 
	    function(rdataobj) { 
				    $showlist=$($("#setsrightlistall")); 
					var ctbody=$("<tbody></tbody>").appendTo($showlist);
					for(var i=0;i<rdataobj.data.length;i++){
					    var ctr=$("<tr></tr>").appendTo($(ctbody));
					    var ctd=$('<td width="20%"><input type="checkbox" name="checkbox_rightsets"></input></td>').appendTo($(ctr));
					    var ctd=$('<td width="25%">'+rdataobj.data[i].materialCodeSub+'</td>').appendTo($(ctr));
					    var ctd=$('<td width="25%">'+rdataobj.data[i].materialName+'</td>').appendTo($(ctr));
						$(ctr).mouseover(function(){
							$(this).toggleClass('oddrow');
						});
						$(ctr).mouseout(function(){
							$(this).toggleClass("oddrow");     
						});
					}
				
		//showsetslist(rdataobj);
}, 'json');
    
	
}


function getoperatelimit(menuname,useflg){
	
	data={
			
			menuName : menuname
	};
	
	var jsonstr=JSON.stringify(data);
	 $.post("EntAction_getOperateLimit?&ts="
				+ new Date().getTime(), {
					data:jsonstr
		}, function(rdata) {
			if(rdata.data == "true"){
				
			 if(useflg==1){
			 seFlg=1;
			 $(".overlay").show();
			 $("#addMaterialClassDialog").show(); 
			 }else if(useflg==2){
			 if(classRow==null){
					alert("请选择要修改的原料大类");
				}else{
					seFlg=2;
					 $(".overlay").show();
				     $("#addMaterialClassDialog").show(); 
				     var classCode=classtable.row(classRow).data().classCode;
						var className=classtable.row(classRow).data().className;
						$("#classCode").val(classCode);
						$("#className").val(className);
				}
			 }else if(useflg==3){
				 delMaterialClass();
			  }else if(useflg==4){
				   if(classRow==null){alert("请先选中原料大类数据");return;}
				    seFlg=3;
					var classCode = classtable.row(classRow).data().classCode;
					$("#MaterialSubclassclassCode").val(classCode);
					 $(".overlay").show();
					 $("#updateMaterialSubclassDialog").show(); 
			 }else if(useflg==5){
				     if(subclassRow==null){alert("请选择要修改的原料小类");}
					 else{
						seFlg=4;      
					 $(".overlay").show();
					 $("#updateMaterialSubclassDialog").show(); 
					 var classCode = classtable.row(classRow).data().classCode;
					 var subclassCode=subclasstable.row(subclassRow).data().subclassCode;
					 var subclassName=subclasstable.row(subclassRow).data().subclassName;
					 $("#MaterialSubclassclassCode").val(classCode);
					 $("#MaterialSubclasssubclassCode").val(subclassCode);
					 $("#MaterialSubclasssubclassName").val(subclassName);
					}
				 
			 }else if(useflg==6){
				 delsubClass();
			 }else if(useflg==7){
				 if(subclassRow==null){alert("请先选中原料小类数据");return;}
				 seFlg=5;
				 $(".overlay").show();
				 $("#updateMaterialSubsubClasssDialog").show(); 
				 var classCode = classtable.row(classRow).data().classCode;
				 var subclassCode=subclasstable.row(subclassRow).data().subclassCode;
				 $("#MaterialSubsubClasssclassCode").val(classCode);
				 $("#MaterialSubsubClassssubclassCode").val(subclassCode);
				
			 }else if(useflg==8){
				   if(subsubclassRow==null){alert("请选中要修改的原料细类");}
					else{
						seFlg=6;
						 var classCode = classtable.row(classRow).data().classCode;
						 var subclassCode=subclasstable.row(subclassRow).data().subclassCode;
						 var materialCode=subsubclasstable.row(subsubclassRow).data().materialCode;
						 var materialName=subsubclasstable.row(subsubclassRow).data().materialName;
						 var ifSet=subsubclasstable.row(subsubclassRow).data().ifSet;
						 $("#MaterialSubsubClasssclassCode").val(classCode);
						 $("#MaterialSubsubClassssubclassCode").val(subclassCode);
						 $("#MaterialSubsubClasssmaterialCode").val(materialCode);
						 $("#MaterialSubsubClasssmaterialName").val(materialName);
						 if(ifSet=="是"){
							 $("#ifsetsets").prop("checked",true);
							 getsubsubsub(classCode, subclassCode);//left
							 getxileiSub(classCode, subclassCode, materialCode);//right
							 
						}else{
							$("#ifsetsets").prop("checked",false);      
							$("#setsleftlistall tbody").empty();
						    $("#setsrightlistall tbody").empty();
						}
						
					 $(".overlay").show();
					 $("#updateMaterialSubsubClasssDialog").show(); 
					}
			 }else if(useflg==9){
				 //删除细类
				
				 delMaterialSubsubclass();
			 }
				
			}else{
				alert(rdata.data);
			}
		}, 'json');
	
}	
$("#export").click(exportData);
function exportData(){
	 window.location.href="MaterialClassExportAction";
}
});


