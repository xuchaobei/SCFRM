$(document).ready(function(){
	
	
	
	
	
	
	
	var curRow=null;
	var seFlg=null;
	var useflg=null;
	var showFlg=0;
    var menunam='检测项目设置';
	var itemname='';
//页面刷新进入
	var columnproClass = [
	                      { "data": "itemID"},
		                  { "data": "itemCode" },
		                  { "data": "itemName" },
		                  { "data": "className" },
		                  { "data": "riskClassCode" },        
		                  { "data": "itemType" },
		                  { "data": "labFlg" ,
			      	        	"render": function ( data, type, full, meta ) {
			      	        		return data === '1'  ? '是' : '否';
		                    	    }},
		      	          { "data": "ifSet" ,  
		      	        	"render": function ( data, type, full, meta ) {
		      	        		return data === '1'  ? '是' : '否';
	                    	    }},   
		                 ];
	var table = $('#examineItemTb').DataTable( {
		 // "scrollX": true,
	      "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "ajax":{
			  url : "ItemAction_GetItem",
			  type : "GET",
			  data : function(d){
			         d.data= JSON.stringify({itemName  : itemname});  
			         
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
	
//初始化第一行
	$('#examineItemTb').on( 'draw.dt', function () {   
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
	
	 $('#examineItemTb tbody').on("click", "tr", clickRow );
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
	  //查询按钮
	  $("#queryexamineItem").click(function() {
		  $(".overlay").show();
	      $("#queryDialog").show();
			
			});
	  //查询功能
	  $("#queryssorySetBtn").click(function() {
		    itemname=$("#quereitemName").val().trim();
		    if(itemname==''){alert("请输入国家地区名称");return;}
	        table.draw();
	        $("#querddrySDialogBtn").click();
			
			});
	  //查询取消
	  $("#querddrySDialogBtn").click(function(){
		    $(".overlay").hide();
	        $("#queryDialog").hide(); 
	      
			$("#quereitemName").val(""); 
	  });
	  
//点击新增，置空，并填充表的左边(获取非集合数据)
		 $("#addexamineItem").click(function() {
			 
			 useflg=1;
			 getoperatelimit(menunam, useflg);
			});  
	  
//修改按钮
			$("#editexamineItem").bind('click', function(){
				
				
				//alert("待开发");

				 if(curRow==null){
						alert("请选择要修改的项目");
					}else{
						 seFlg=2;
						 $(".overlay").show();    
						 $("#addexamineItemDialog").show(); 
						
						var itemCode=table.row(curRow).data().itemCode;
						var itemName=table.row(curRow).data().itemName;
						var ifSet=table.row(curRow).data().ifSet;
						var riskclass=table.row(curRow).data().riskClassCode+" "+table.row(curRow).data().className;
						var itemType=table.row(curRow).data().itemType;
						var labFlg=table.row(curRow).data().labFlg;
						if(labFlg=='1'){
							 $("#iftolibry").prop("checked",true);     
						}else{
							 $("#iftolibry").prop("checked",false);     
						}
						if(ifSet=='1'){
							 $("#ifsetsets").prop("checked",true);     
							/* SendListRquest(''); */
							 getMethodSub(itemCode);
							 $("#examineItemCode").val(itemCode);
							 $("#examineItemName").val(itemName);
							 $("#sriskclass").val(riskclass);
							 $("#sitemclass").val(itemType);
						}else{
							$("#ifsetsets").prop("checked",false);
							$("#examineItemCode").val(itemCode);
							$("#examineItemName").val(itemName);
							 $("#sriskclass").val(riskclass);
							 $("#sitemclass").val(itemType);
							$("#setsleftlistall tbody").empty();
						    $("#setsrightlistall tbody").empty();
						} 
					}
				 
				
			
		});	  
	  
//删除选中行
			  $('#deleteexamineItem').click(function(){
				  
				     useflg=3;
					 getoperatelimit(menunam, useflg);
			  }); 	  
//点击取消，隐藏对话框
				 $("#closeaddexamineItemDialogBtn").click(function(){
					  $(".overlay").hide();
				      $("#addexamineItemDialog").hide();
				      $("#setsleftlistall tbody").empty(); 
				      $("#setsrightlistall tbody").empty(); 
				      $("#examineItemCode").val("");
			 		  $("#examineItemName").val("");
			 		  $("#ifsetsets").prop("checked",false);
			 		  $("#iftolibry").prop("checked",false);
					  $("#sriskclass").val("");
					  $("#sitemclass").val("");
					  $("#queryitemNsettexxt").val("");
				  });  
		//集合中查询		 
			$('#setquerybtneeeaf').click(function(){
			
				var kename=$("#queryitemNsettexxt").val().trim();
				//if(kename==''){alert("请输入项目名称关键字段");return;}
				$("#setsleftlistall tbody").empty();
						SendListRquest(kename);
				}); 			 
	  
/* //点击定义集合复选框功能
				 $("#ifsetsets").bind('click', function(){
					  if($($("input[name='ifsetsets']")).prop("checked")){ 
					       SendListRquest('');
					    }
					    else{
						    $("#setsleftlistall tbody").empty();
						    $("#setsrightlistall tbody").empty();
					    }
				    });*/				 
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
				 
//保存时输入信息验证函数
				function ifvalidate(){
					var validate=true;
					if($("#examineItemCode").val()==''||$("#examineItemName").val()==''|| $("#sriskclass").val()==''||$("#sitemclass").val()==''){validate=false;}
					return validate;
				}
//保存功能
				$("#saveexamineItemBtn").bind('click', function(){
					
					 useflg=2;
					 getoperatelimit(menunam, useflg);
					
					
				});	  
	  
				
				
				//保存事件函数
				function savemethod(proMid){
					 var riskClassCode= getSelectValue("sriskclass");
			         var itemType=  $("#sitemclass").val().trim();
			         var labFlg=null;
				     if($($("input[name='iftolibry']")).prop("checked")){
				       		labFlg='1';
				     }else{
				       		labFlg='0';
				       	 }
			        if($($("input[name='ifsetsets']")).prop("checked")){
			     	  
			     	 var itemCode=  $("#examineItemCode").val().trim();
			         var itemName=  $("#examineItemName").val().trim();
			         
			       	 var ifsets="1";
			       	
			       	 var setsdata={
			       			itemID : proMid,
							itemCode:itemCode,
							itemName : itemName,
							riskClassCode :riskClassCode,
							itemType :itemType,
							labFlg :labFlg,
							ifSet :ifsets
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
			   				"ItemAction_SaveItemSub",
			      				{data:setsnamedata,setscontent:setscontent.toString()},
			      				function(rdata) {
			      					if(rdata.data=="true"){
			      						if(seFlg==1){
			      							curRow=null;
			      							table.draw();
			      						}else if(seFlg==2){
			      							table.draw(false);
			      						}
			      						 $("#closeaddexamineItemDialogBtn").click();
			      					}
			      					else{
			      						alert(rdata.data);
			      					}
			      				}, 
			      				'json');
			       	 }
			        else{//保存非集合加工方式  
			       	
			       	 savesetsbasicdata(proMid,riskClassCode,itemType,labFlg);
			       	
			       	// $("#fuzzy").toggle();
			       	 }           
					  
				}
				//保存非集合数据函数
				function savesetsbasicdata(id,riskClassCode,itemType,labFlg){
					var count=0;
					
					$("input[name='checkbox_rightsets']").each(function(index){
						
							count++;   
						
					});
				  if(count >0){    
					 var boole= $($("input[name='ifsetsets']")).prop("checked");
					  if(!boole){
						  alert("若要保存为集合,请勾选定义为集合。若要保存为非集合,请移除右侧表中的数据");return; 
					  }
					 
				  }
					
					var itemCode=  $("#examineItemCode").val().trim();
			         var itemName=  $("#examineItemName").val().trim();
			       	 
					 var setsdata={
							    itemID : id,
								itemCode:itemCode,
								itemName : itemName,
								riskClassCode :riskClassCode,
								itemType :itemType,
								labFlg :labFlg,
								ifSet :0
					 };
					 var setsnamedata = JSON.stringify(setsdata);
						 $.post( 
								 "ItemAction_SaveItem?&ts="+new Date().getTime(),
							{data:setsnamedata},
							function(rdata) {
							if(rdata.data=="true"){
								if(seFlg==1){
									curRow=null;
									table.draw();
								}else if(seFlg==2){
									table.draw(false);
								}
								 $("#closeaddexamineItemDialogBtn").click();
								 
						        }
							else{
									alert(rdata.data);
								}
							}, 
							'json');
				}			
				
	  
//获取非集合数据
			function SendListRquest(name){
				$.post("ItemAction_GetItemSet", 
						 {data:JSON.stringify({itemName:name})}, 
				    function(rdataobj) {  
					showsetslist(rdataobj);
			}, 'json');
			    }
			
		//填充集合左边
		function showsetslist(rdataobj){
			
			$showlist=$($("#setsleftlistall")); 
			var ctbody=$("<tbody></tbody>").appendTo($showlist);
			for(var i=0;i<rdataobj.data.length;i++){
			    var ctr=$("<tr></tr>").appendTo($(ctbody));
			    var ctd=$('<td width="20%"><input type="checkbox" name="checkbox_leftsets"></input></td>').appendTo($(ctr));
			    var ctd=$('<td width="25%">'+rdataobj.data[i].itemCode+'</td>').appendTo($(ctr));
			    var ctd=$('<td width="55%">'+rdataobj.data[i].itemName+'</td>').appendTo($(ctr));
				$(ctr).mouseover(function(){
					$(this).toggleClass('oddrow');
				});
				$(ctr).mouseout(function(){
					$(this).toggleClass("oddrow");     
				});
			}
		}	
		//填充集合右边
		function getMethodSub(itemCode){

			$.post("ItemAction_GetItemSub", 
					  {data:JSON.stringify({itemCode:itemCode})}, 
			    function(rdataobj) { 
						    $showlist=$($("#setsrightlistall")); 
							var ctbody=$("<tbody></tbody>").appendTo($showlist);
							for(var i=0;i<rdataobj.data.length;i++){
							    var ctr=$("<tr></tr>").appendTo($(ctbody));
							    var ctd=$('<td width="20%"><input type="checkbox" name="checkbox_rightsets"></input></td>').appendTo($(ctr));
							    var ctd=$('<td width="25%">'+rdataobj.data[i].itemCodeSub+'</td>').appendTo($(ctr));
							    var ctd=$('<td width="25%">'+rdataobj.data[i].itemName+'</td>').appendTo($(ctr));
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
			
	  
		function delConvCtrlRule(){
			  if(curRow == null){
				  alert("请先选择一条要删除的记录！");
				  return;
			  }
			  if(confirm("确定要删除吗?")){  
				  var itemCode = table.row(curRow).data().itemCode;
				  $.post("ItemAction_DelItem?&ts="
							+ new Date().getTime(), {
								data:JSON.stringify({itemCode:itemCode})  
					}, function(rdata) {
						if(rdata.data == "true"){
							alert("删除成功！");
							table.row(curRow).remove().draw(false);
						}else{
							
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
					 $("#addexamineItemDialog").show(); 
						

					 }else if(useflg==2){ 
							
						  if(ifvalidate()==true){
							 /* var waycode=$("#examineItemCode").val().trim();*/
							  if($($("input[name='ifsetsets']")).prop("checked")){ 
								 /* if(waycode.length >=3 ){
									alert("预期用途字符串长度要小于3");return;  
								  }*/
								    var count=0;
									
									$("input[name='checkbox_rightsets']").each(function(index){
										
											count++;
										
									});
								  if(count <=1){
									  alert("集合定义右侧数据栏中至少有2个国家地区");return;
								  }
							    }/*else{
							    	if(waycode.length !=1){
										alert("预期用途字符串长度必须为1");return;  
									  }
							    }*/
							  if(seFlg==1){
								  savemethod('0');
							  }
							  if(seFlg==2){
								var itemID=table.row(curRow).data().itemID;
								  savemethod(itemID);
							  }
						  }
						  else{alert("名称 代码 风险类别 项目类别 不能为空！");}  
				  }else if(useflg==3){
						 delConvCtrlRule();
						 
					 }
						
					}else{
						alert(rdata.data);        
					}
				}, 'json');
			
		}			
		
initRiskclass();	
function initRiskclass(){
	$.get("ItemAction_GetRiskClass?&ts="
	+ new Date().getTime(),
	function(rdata) {
	var source = new Array();
	$.each(rdata.data, function(index, value){
	source[index] = value.classCode+" "+value.className;
	});
	cus_autocomplete(source, "sriskclass", "sriskclass-select", null, null);
	}, 'json');
	}
	
	  
	
	
$("#selectall").on("click",function(){
	$("#setsleftlistall :checkbox").each(function(index){
			if(!$(this).prop("checked")){   //jquery1.6改进了$(this).attr("checked")
			$(this).prop("checked",true);
			  //$("input[name='name"+i+"']").prop("checked",false);
			}
		});	
});		
$("#notselectall").on("click",function(){
	$("#setsleftlistall :checkbox").each(function(index){
			if($(this).prop("checked")){   //jquery1.6改进了$(this).attr("checked")
			$(this).prop("checked",false);
			  //$("input[name='name"+i+"']").prop("checked",false);
			}
		});	
});	
$("#selectother").on("click",function(){
	$("#setsleftlistall :checkbox").each(function(index){
			if(!$(this).prop("checked")){   //jquery1.6改进了$(this).attr("checked")
			$(this).prop("checked",true);
			  //$("input[name='name"+i+"']").prop("checked",false);
			}else{
				$(this).prop("checked",false);
			}
		});	
});	
$("#export").click(exportData);
function exportData(){
	 window.location.href="ExamineItemExportAction";
}	
	
	
	
	
	
	
	      
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	
	
	
	
	
	
	
	
	
	
	
	
	
function SaveItem(){
	
}
function SaveItemSub(){
	
}
function DelItem(){
	
}
function GetItem(){
	
}
function GetItemSub(){
	
}
function GetRiskClass(){
	
}
	
});