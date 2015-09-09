$(document).ready(function(){
	var curRow=null;
	var seFlg=null;
	var useflg=null;
	var showFlg=0;
    var menunam='加工方式设置';
//页面刷新进入
	var columnproClass = [
	                      { "data": "processingMethodID"},
		                  { "data": "methodCode" },
		                  { "data": "methodName" },
		      	          { "data": "ifSet" },
		                 ];
	var table = $('#processingWayTb').DataTable( {
		 // "scrollX": true,
	      "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "ajax":{
			  url : "ProcessingWayAction_getProcessingMethodAll",
			  type : "GET",
			  data : function(d){
			         d.data= JSON.stringify({methodName : ""});  
			         d.showFlg=showFlg;
				  }  
		  },
	      "columns": columnproClass,
	      "columnDefs": [
							{
							"targets": [ 0 ],
							"visible": false,
							}
						]
	  } );
	
	//初始化第一行默认选中小类
	$('#processingWayTb').on( 'draw.dt', function () {
		  if(table.rows().data().length > 0){
			  var dtRow = null;
			  var node = null;
			  if(curRow != null){
			  dtRow = table.row($(curRow).context._DT_RowIndex);
			  node = dtRow.node();
			  if(node != null){
			  $(node).click();
			  return;
			  }
			  }
			  dtRow = table.row(0);
			  node = dtRow.node();
			  $(node).click();
		 }else{
			 curRow=null;
         }
		 
	  } );
	//点击取消，隐藏对话框
	 $("#closeaddprocessingWayDialogBtn").click(function(){
		  $(".overlay").hide();
	      $("#addprocessingWayDialog").hide();
	      $("#setsleftlistall tbody").empty(); 
	      $("#setsrightlistall tbody").empty(); 
	      $("#processingWayName").val("");
 		  $("#processingWayCode").val("");
 		  $("#ifsetsets").prop("checked",false);
	      
	  });  
	 
	 
	//点击新增，置空，并填充表的左边(获取加工方式非集合数据)
	 $("#addprocessingWay").click(function() {
		 
		 useflg=1;
		 getoperatelimit(menunam, useflg);
		});
	 //点击定义集合复选框功能
	 $("#ifsetsets").bind('click', function(){
		  if($($("input[name='ifsetsets']")).prop("checked")){ 
		       SendListRquest();
		    }
		    else{
			    $("#setsleftlistall tbody").empty();
			    $("#setsrightlistall tbody").empty();
		    }
	    });
	//获取集合数据
		function SendListRquest(){
			$.post("ProcessingWayAction_getProcessingMethod", 
					  {"methodName" : ""}, 
			    function(rdataobj) {  
				showsetslist(rdataobj);
		}, 'json');
		    }
		
	//集合左边
	function showsetslist(rdataobj){
		
		$showlist=$($("#setsleftlistall")); 
		var ctbody=$("<tbody></tbody>").appendTo($showlist);
		for(i=0;i<rdataobj.data.length;i++){
		    var ctr=$("<tr></tr>").appendTo($(ctbody));
		    var ctd=$('<td width="20%"><input type="checkbox" name="checkbox_leftsets"></input></td>').appendTo($(ctr));
		    var ctd=$('<td width="25%">'+rdataobj.data[i].methodCode+'</td>').appendTo($(ctr));
		    var ctd=$('<td width="55%">'+rdataobj.data[i].methodName+'</td>').appendTo($(ctr));
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
		for(i=0;i<setsarray.length;i++){
			var tempstring=setsarray[i].split(" ");
			var ifExist=false;
			for(j=0;j<setscontent.length;j++){
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
		for(i=0;i<setsarray.length;i++){
			var tempstring=setsarray[i].split(" ");
		    var ctr=$("<tr></tr>").appendTo($(ctbody));
		    var ctd=$('<td width="20%"><input type="checkbox" name="checkbox_rightsets"></input></td>').appendTo($(ctr));
		    var ctd=$('<td width="25%">'+tempstring[0]+'</td>').appendTo($(ctr));
		    var ctd=$('<td width="25%">'+tempstring[1]+'</td>').appendTo($(ctr));
		}
    });
	//保存时输入信息验证函数
	function ifvalidate(){
		var validate=true;
		if($("#processingWayCode").val()==''||$("#processingWayName").val()==''){validate=false;}
		return validate;
	}
	//保存加工方式功能
	$("#saveprocessingWayBtn").bind('click', function(){
		
		 useflg=2;
		 getoperatelimit(menunam, useflg);
		
	});
	//修改加工方式功能
	$("#editprocessingWay").bind('click', function(){
		
		 if(curRow==null){
				alert("请选择要修改的加工方式");
			}else{
				seFlg=2;
				 $(".overlay").show();    
				 $("#addprocessingWayDialog").show(); 
				
				var methodCode=table.row(curRow).data().methodCode;
				var methodName=table.row(curRow).data().methodName;
				var ifSet=table.row(curRow).data().ifSet;
				if(ifSet=="是"){
					 $("#ifsetsets").prop("checked",true);
					 SendListRquest(); 
					 getMethodSub(methodCode);
					 $("#processingWayCode").val(methodCode);
					 $("#processingWayName").val(methodName);
				}else{
					$("#ifsetsets").prop("checked",false);
					$("#processingWayCode").val(methodCode);
					$("#processingWayName").val(methodName);
					$("#setsleftlistall tbody").empty();
				    $("#setsrightlistall tbody").empty();
				} 
			}
		 
		
	
});
	function getMethodSub(methodCode){

		$.post("ProcessingWayAction_getProcessingMethodSub", 
				  {data:JSON.stringify({methodCode:methodCode})}, 
		    function(rdataobj) { 
					    $showlist=$($("#setsrightlistall")); 
						var ctbody=$("<tbody></tbody>").appendTo($showlist);
						for(var i=0;i<rdataobj.data.length;i++){
						    var ctr=$("<tr></tr>").appendTo($(ctbody));
						    var ctd=$('<td width="20%"><input type="checkbox" name="checkbox_rightsets"></input></td>').appendTo($(ctr));
						    var ctd=$('<td width="25%">'+rdataobj.data[i].methodCodeSub+'</td>').appendTo($(ctr));
						    var ctd=$('<td width="25%">'+rdataobj.data[i].methodName+'</td>').appendTo($(ctr));
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
	
	//保存事件函数
	function savemethod(proMid){
		 
        if($($("input[name='ifsetsets']")).prop("checked")){
     	  
     	 var methodCode=  $("#processingWayCode").val().trim();
       	 var ifsets="1";
       	 var setsdata={
       			processingMethodID:proMid,  
       			methodName: $("#processingWayName").val().trim(),
       			methodCode: methodCode,   
       			ifSet:ifsets
       	 };
       	 var setsnamedata =JSON.stringify(setsdata);// $.toJSON(setsdata);
    		 var count=0;  
    		 setscontent = new Array();
    		 $("#setsrightlistall tbody tr").each(function(index){
   				var temp=$(this).children().eq(1).html();
   				setscontent[count]=temp;
   				count++;
   		  });
    		 
   		 $.post( 
   				"ProcessingWayAction_SaveProcessingMethodSub",
      				{processingMethodID:0,processingWayCode:methodCode,data:setsnamedata,setscontent:setscontent.toString()},
      				function(rdata) {
      					if(rdata.data=="true"){
      						if(seFlg==1){
      							curRow=null;
      							table.draw();
      						}else if(seFlg==2){
      							table.draw(false);
      						}
      						 $("#closeaddprocessingWayDialogBtn").click();
      					}
      					else{
      						alert(rdata.data);
      					}
      				}, 
      				'json');
       	 }
        else{//保存非集合加工方式  
       	
       	 savesetsbasicdata(proMid);
       	
       	// $("#fuzzy").toggle();
       	 }           
		  
	}
	//保存非集合数据函数
	function savesetsbasicdata(id){
		 var setsdata={
				processingMethodID:id,
       			methodName:$("#processingWayName").val(),
       			methodCode:$("#processingWayCode").val(),
       			ifSet:0
		 };
		 var setsnamedata = JSON.stringify(setsdata);
			 $.post( 
					 "ProcessingWayAction_SaveProcessingMethod?&ts="+new Date().getTime(),
				{processingMethodID:0,data:setsnamedata},
				function(rdata) {
				if(rdata.data=="true"){
					if(seFlg==1){
						curRow=null;
						table.draw();
					}else if(seFlg==2){
						table.draw(false);
					}
					 $("#closeaddprocessingWayDialogBtn").click();
					 
			        }
				else{
						alert(rdata.data);
					}
				}, 
				'json');
	}
	$('#processingWayTb tbody').on("click", "tr", clickRow );
	  //选中行变色
	  function clickRow(){
		  var row = this;
		  if ( $(row).hasClass('active') ) {  
			    curRow = null;
	            $(row).removeClass('active');
	        }
	        else {
	        	curRow = row;
	            table.$('tr.active').removeClass('active');
	            $(row).addClass('active');
	        }
	  }
	
	 //删除选中行
	  $('#deleteprocessingWay').click(function(){
		  
		   useflg=3;
			 getoperatelimit(menunam, useflg);
	  });  
	  function delConvCtrlRule(){
		  if(curRow == null){
			  alert("请先选择一条要删除的记录！");
			  return;
		  }
		  if(confirm("确定要删除吗?")){  
			  var methodCode = table.row(curRow).data().methodCode;
			  $.post("ProcessingWayAction_DelProcessingMethod?&ts="
						+ new Date().getTime(), {
							data:JSON.stringify({methodCode : methodCode})  
				}, function(rdata) {
					if(rdata.data == "true"){
						alert("删除成功！");
						table.row(curRow).remove().draw(false);
					}
				}, 'json');
		  }

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
					 $("#addprocessingWayDialog").show(); 
						/*$.post("ProcessingWayAction_getProcessingMethod", 
								  {"methodName" : ""}, 
						    function(rdataobj) {  
									  
							//var rdataobj = eval("("+rdataobj+")");   
							showsetslist(rdataobj);
					}, 'json');*/

					 }else if(useflg==2){
						 
							
						  if(ifvalidate()==true){
							  var waycode=$("#processingWayCode").val().trim();
							  if($($("input[name='ifsetsets']")).prop("checked")){ 
								  if(waycode.length >=4 || waycode.length <2){
									alert("加工代码字符串长度要大于等于2，小于4");return;  
								  }
								    var count=0;
									
									$("input[name='checkbox_rightsets']").each(function(index){
										
											count++;
										
									});
								  if(count <=1){
									  alert("集合定义右侧数据栏中至少有2个加工方式");return;
								  }
							    }else{
							    	if(waycode.length !=2){
										alert("加工代码字符串长度必须为2");return;  
									  }
							    }
							  if(seFlg==1){
								  savemethod('0');
							  }
							  if(seFlg==2){
								var processingMethodID=table.row(curRow).data().processingMethodID;
								  savemethod(processingMethodID);
							  }
						  }
						  else{alert("名称和代码不能为空！");}  
				  
						 
						 
					 }else if(useflg==3){
						 delConvCtrlRule();
						 
					 }
						
					}else{
						alert(rdata.data);
					}
				}, 'json');
			
		}	 
	 
	  $("#export").click(exportData);
	  function exportData(){
	  	 window.location.href="ProcessingWayExportAction";
	  }	
	
});