$(document).ready(function(){
	
	
	
	var curRow=null;
	var seFlg=null;
	var useflg=null;
	var showFlg=0;
    var menunam='国家地区设置';
	var countryNa='';
//页面刷新进入
	var columnproClass = [
	                      { "data": "countryID"},
		                  { "data": "countryCode" },
		                  { "data": "countryName" },
		      	          { "data": "ifSet" ,
		      	        	"render": function ( data, type, full, meta ) {
		      	        		return data === '1'  ? '是' : '否';
	                    	    }},   
		                 ];
	var table = $('#countryAreaTb').DataTable( {
		 // "scrollX": true,
	      "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "ajax":{
			  url : "CountryAreaAction_getCountry",
			  type : "GET",
			  data : function(d){
			         d.data= JSON.stringify({countryName  : countryNa});  
			         
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
	$('#countryAreaTb').on( 'draw.dt', function () {   
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
	
	 $('#countryAreaTb tbody').on("click", "tr", clickRow );
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
	  $("#querycountryArea").click(function() {
		  $(".overlay").show();
	      $("#queryaddSetDialog").show();
			
			});
	  //查询功能
	  $("#queryscessorySetBtn").click(function() {
		    countryNa=$("#queressorySetName").val().trim();
		    if(countryNa==''){alert("请输入国家地区名称");return;}
	        table.draw();
	        $("#querddaccessorySetDialogBtn").click();
			
			});
	  //查询取消
	  $("#querddaccessorySetDialogBtn").click(function(){
		    $(".overlay").hide();
	        $("#queryaddSetDialog").hide(); 
	      
			$("#queressorySetName").val(""); 
	  });
	  
//点击新增，置空，并填充表的左边(获取非集合数据)
		 $("#addcountryArea").click(function() {
			 
			 useflg=1;
			 getoperatelimit(menunam, useflg);
			});  
		 
//复制按钮
			$("#copycountryArea").bind('click', function(){
				
				

				 if(curRow==null){
						alert("请选择一条国家地区数据");
					}else{
						seFlg=3;
						 $(".overlay").show();    
						 $("#addcountryAreaDialog").show(); 
						
						var countryCode=table.row(curRow).data().countryCode;
						var countryName=table.row(curRow).data().countryName;
						var ifSet=table.row(curRow).data().ifSet;
						if(ifSet=='1'){
							 $("#ifsetsets").prop("checked",true);     
							/* SendListRquest(''); */
							 getMethodSub(countryCode);
							 $("#countryAreaCode").val(countryCode);
							 $("#countryAreaName").val(countryName);
						}else{
							$("#ifsetsets").prop("checked",false);
							$("#countryAreaCode").val(countryCode);
							$("#countryAreaName").val(countryName);
							$("#setsleftlistall tbody").empty();
						    $("#setsrightlistall tbody").empty();
						} 
					}
				 
			
		});	  		 
	  
//修改按钮
			$("#editcountryArea").bind('click', function(){
				
				

				 if(curRow==null){
						alert("请选择要修改的国家地区");
					}else{
						seFlg=2;
						 $(".overlay").show();    
						 $("#addcountryAreaDialog").show(); 
						
						var countryCode=table.row(curRow).data().countryCode;
						var countryName=table.row(curRow).data().countryName;
						var ifSet=table.row(curRow).data().ifSet;
						if(ifSet=='1'){
							 $("#ifsetsets").prop("checked",true);     
							/* SendListRquest(''); */
							 getMethodSub(countryCode);
							 $("#countryAreaCode").val(countryCode);
							 $("#countryAreaName").val(countryName);
						}else{
							$("#ifsetsets").prop("checked",false);
							$("#countryAreaCode").val(countryCode);
							$("#countryAreaName").val(countryName);
							$("#setsleftlistall tbody").empty();
						    $("#setsrightlistall tbody").empty();
						} 
					}
				 
			
		});	  
	  
//删除选中行
			  $('#deletecountryArea').click(function(){
				  
				     useflg=3;
					 getoperatelimit(menunam, useflg);
			  }); 	  
//点击取消，隐藏对话框
				 $("#closeaddcountryAreaDialogBtn").click(function(){
					  $(".overlay").hide();
				      $("#addcountryAreaDialog").hide();
				      $("#setsleftlistall tbody").empty(); 
				      $("#setsrightlistall tbody").empty(); 
				      $("#countryAreaCode").val("");
			 		  $("#countryAreaName").val("");    
			 		  $("#querycountryNsettexxt").val("");
			 		  $("#ifsetsets").prop("checked",false);
				      
				  });  
		//集合中查询		 
			$('#querycountryNsetBtn').click(function(){
				var name=$("#querycountryNsettexxt").val().trim();
				/*if(name==''){alert("输入查询字段");return;}*/
				$("#setsleftlistall tbody").empty(); 
				SendListRquest(name);      
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
				    });	*/			 
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
					if($("#countryAreaCode").val()==''||$("#countryAreaName").val()==''){validate=false;}
					return validate;
				}
//保存功能
				$("#savecountryAreaBtn").bind('click', function(){
					
					 useflg=2;
					 getoperatelimit(menunam, useflg);
					
				});	  
	  
				
				
				//保存事件函数
				function savemethod(proMid){
					 
			        if($($("input[name='ifsetsets']")).prop("checked")){
			     	  
			     	 var countryCode=  $("#countryAreaCode").val().trim();
			         var countryName=  $("#countryAreaName").val().trim();
			       	 var ifsets="1";
			       	 var setsdata={
			       			countryID : proMid,
							countryCode:countryCode,
							countryName : countryName,
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
			   				"CountryAreaAction_SaveCountrySub",
			      				{data:setsnamedata,setscontent:setscontent.toString()},
			      				function(rdata) {
			      					if(rdata.data=="true"){
			      						if(seFlg==1){
			      							curRow=null;
			      							table.draw();
			      						}else if(seFlg==2){
			      							table.draw(false);
			      						}
			      						 $("#closeaddcountryAreaDialogBtn").click();
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
					
					 var countryCode=  $("#countryAreaCode").val().trim();
			         var countryName=  $("#countryAreaName").val().trim();
			       	 
					 var setsdata={
							    countryID : id,
								countryCode:countryCode,
								countryName : countryName,
								ifSet :0
					 };
					 var setsnamedata = JSON.stringify(setsdata);
						 $.post( 
								 "CountryAreaAction_SaveCountry?&ts="+new Date().getTime(),
							{data:setsnamedata},
							function(rdata) {
							if(rdata.data=="true"){
								if(seFlg==1){
									curRow=null;
									table.draw();
								}else if(seFlg==2){
									table.draw(false);
								}
								 $("#closeaddcountryAreaDialogBtn").click();
								 
						        }
							else{
									alert(rdata.data);
								}
							}, 
							'json');
				}			
				
	  
//获取非集合数据
			function SendListRquest(countryname){
				$.post("CountryAreaAction_getCountrySet", 
						 {data:JSON.stringify({countryName:countryname})}, 
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
			    var ctd=$('<td width="25%">'+rdataobj.data[i].countryCode+'</td>').appendTo($(ctr));
			    var ctd=$('<td width="55%">'+rdataobj.data[i].countryName+'</td>').appendTo($(ctr));
				$(ctr).mouseover(function(){
					$(this).toggleClass('oddrow');
				});
				$(ctr).mouseout(function(){
					$(this).toggleClass("oddrow");     
				});
			}
		}	
		//填充集合右边
		function getMethodSub(countryCode){

			$.post("CountryAreaAction_getCountrySub", 
					  {data:JSON.stringify({countryCode:countryCode})}, 
			    function(rdataobj) { 
						    $showlist=$($("#setsrightlistall")); 
							var ctbody=$("<tbody></tbody>").appendTo($showlist);
							for(var i=0;i<rdataobj.data.length;i++){
							    var ctr=$("<tr></tr>").appendTo($(ctbody));
							    var ctd=$('<td width="20%"><input type="checkbox" name="checkbox_rightsets"></input></td>').appendTo($(ctr));
							    var ctd=$('<td width="25%">'+rdataobj.data[i].subcountryCode+'</td>').appendTo($(ctr));
							    var ctd=$('<td width="25%">'+rdataobj.data[i].countryName+'</td>').appendTo($(ctr));
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
				  var countryCode = table.row(curRow).data().countryCode;
				  $.post("CountryAreaAction_DelCountry?&ts="
							+ new Date().getTime(), {
								data:JSON.stringify({countryCode:countryCode})  
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
					 $("#addcountryAreaDialog").show(); 
						

					 }else if(useflg==2){
						 
						 
							
						  if(ifvalidate()==true){
							  var waycode=$("#countryAreaCode").val().trim();
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
								var countryID=table.row(curRow).data().countryID;
								  savemethod(countryID);
							  }if(seFlg==3){
								  savemethod('0');
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
		
		
	
	
	
	
	
		
	
	
	
	
	
	
	
	
	      
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
function SaveCountry(countryID,countryCode,countryName,ifSet){
	data={ 
			countryID:countryID,
			countryCode : countryCode,
			countryName : countryName,
			ifSet :ifSet
			
	}; 
	 var jsonstr = JSON.stringify(data);
	$.post("CountryAreaAction_SaveCountry?&ts="   
			+ new Date().getTime(), {
				 data : jsonstr
			}, function(rdata) {
				if(rdata.data=="true"){
					alert("退档成功");
					
					 
				}else{
					alert(rdata.data);
				}
				
			}, 'json');
}
function SaveCountrySub(countrySubID,countryCode,subcountryCode){
	data={ 
			countrySubID:countrySubID,
			countryCode : countryCode,
			subcountryCode : subcountryCode
			
	}; 
	 var jsonstr = JSON.stringify(data);
	$.post("CountryAreaAction_SaveCountrySub?&ts="   
			+ new Date().getTime(), {
				 data : jsonstr
			}, function(rdata) {
				if(rdata.data=="true"){
					alert("退档成功");
					
					 
				}else{
					alert(rdata.data);
				}
				
			}, 'json');
}
function DelCountry(countryCode){
	data={ 
			countryCode:countryCode
			
	}; 
	 var jsonstr = JSON.stringify(data);
	$.post("CountryAreaAction_DelCountry?&ts="   
			+ new Date().getTime(), {
				 data : jsonstr
			}, function(rdata) {
				if(rdata.data=="true"){
					alert("退档成功");
					
					 
				}else{
					alert(rdata.data);
				}
				
			}, 'json');
}
function getCountry(countryName){
	data={ 
			countryName:countryName
			
			
	}; 
	 var jsonstr = JSON.stringify(data);
	$.post("CountryAreaAction_getCountry?&ts="   
			+ new Date().getTime(), {
				 data : jsonstr
			}, function(rdata) {
				if(rdata.data=="true"){
					alert("退档成功");
					
					 
				}else{
					alert(rdata.data);
				}
				
			}, 'json');
}
function getCountrySub(countryCode){
	data={ 
			countryCode:countryCode
			
			
	}; 
	 var jsonstr = JSON.stringify(data);
	$.post("CountryAreaAction_getCountrySub?&ts="   
			+ new Date().getTime(), {
				 data : jsonstr
			}, function(rdata) {
				if(rdata.data=="true"){
					alert("退档成功");
					
					 
				}else{
					alert(rdata.data);
				}
				
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
	 window.location.href="CountryAreaExportAction";
}	
	
});