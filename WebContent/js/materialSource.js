$(document).ready(function(){
	
	var curRow=null;
	var seFlg=null;
	var useflg=null;
	var showFlg=0;
    var menunam='原料来源设置';
	
//页面刷新进入
	var columnproClass = [
	                      { "data": "materialSourceID"},
		                  { "data": "sourceCode" },
		                  { "data": "sourceName" },
		      	          { "data": "ifSet" ,
		      	        	"render": function ( data, type, full, meta ) {
	                    	      return data === '1'  ? '是' : '否';
	                    	    }},
		                 ];
	var table = $('#materialSourceTb').DataTable( {
		 // "scrollX": true,
	      "deferRender": true,
		  "processing": true,
		  "serverSide": true,
		  "ajax":{
			  url : "MaterialSourceAction_getMaterialSource",
			  type : "GET",
			  data : function(d){
			         d.data= JSON.stringify({sourceName : ""});  
			         
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
	$('#materialSourceTb').on( 'draw.dt', function () {   
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
	
	 $('#materialSourceTb tbody').on("click", "tr", clickRow );
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
	  
	  
//点击新增，置空，并填充表的左边(获取非集合数据)
		 $("#addmaterialSource").click(function() {
			 
			 useflg=1;
			 getoperatelimit(menunam, useflg);
			});  
	  
//修改按钮
			$("#editmaterialSource").bind('click', function(){
				
				
				//alert("待开发");

				 if(curRow==null){
						alert("请选择要修改的原料来源");
					}else{
						seFlg=2;
						 $(".overlay").show();    
						 $("#addmaterialSourceDialog").show(); 
						
						var sourceCode=table.row(curRow).data().sourceCode;
						var sourceName=table.row(curRow).data().sourceName;
						var ifSet=table.row(curRow).data().ifSet;
						if(ifSet=='1'){
							 $("#ifsetsets").prop("checked",true);
							 SendListRquest(); 
							 getMethodSub(sourceCode);
							 $("#materialSourceCode").val(sourceCode);
							 $("#materialSourceName").val(sourceName);
						}else{
							$("#ifsetsets").prop("checked",false);
							$("#materialSourceCode").val(sourceCode);
							$("#materialSourceName").val(sourceName);
							$("#setsleftlistall tbody").empty();
						    $("#setsrightlistall tbody").empty();
						} 
					}
				 
				
			
		});	  
	  
//删除选中行
			  $('#deletematerialSource').click(function(){
				  
				     useflg=3;
					 getoperatelimit(menunam, useflg);
			  }); 	  
//点击取消，隐藏对话框
				 $("#closeaddmaterialSourceDialogBtn").click(function(){
					  $(".overlay").hide();
				      $("#addmaterialSourceDialog").hide();
				      $("#setsleftlistall tbody").empty(); 
				      $("#setsrightlistall tbody").empty(); 
				      $("#materialSourceCode").val("");
			 		  $("#materialSourceName").val("");
			 		  $("#ifsetsets").prop("checked",false);
				      
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
					if($("#materialSourceCode").val()==''||$("#materialSourceName").val()==''){validate=false;}
					return validate;
				}
//保存功能
				$("#savematerialSourceBtn").bind('click', function(){
					
					 useflg=2;
					 getoperatelimit(menunam, useflg);
				});	  
	  
				
				
				//保存事件函数
				function savemethod(proMid){
					 
			        if($($("input[name='ifsetsets']")).prop("checked")){
			     	  
			     	 var sourceCode=  $("#materialSourceCode").val().trim();
			         var sourceName=  $("#materialSourceName").val().trim();
			       	 var ifsets="1";
			       	 var setsdata={
			       			materialSourceID:proMid,
							sourceCode : sourceCode,
							sourceName : sourceName,
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
			   				"MaterialSourceAction_SaveMaterialSourceSub",
			      				{materialSourceSubID:0,
			   					sourceCode : sourceCode,data:setsnamedata,setscontent:setscontent.toString()},
			      				function(rdata) {
			      					if(rdata.data=="true"){
			      						if(seFlg==1){
			      							curRow=null;
			      							table.draw();
			      						}else if(seFlg==2){
			      							table.draw(false);
			      						}
			      						 $("#closeaddmaterialSourceDialogBtn").click();
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
					 var sourceCode=  $("#materialSourceCode").val().trim();
			         var sourceName=  $("#materialSourceName").val().trim();
			       	 
					 var setsdata={
							 materialSourceID:id,
								sourceCode : sourceCode,
								sourceName : sourceName,
								ifSet:0
					 };
					 var setsnamedata = JSON.stringify(setsdata);
						 $.post( 
								 "MaterialSourceAction_SaveMaterialSource?&ts="+new Date().getTime(),
							{data:setsnamedata},
							function(rdata) {
							if(rdata.data=="true"){
								if(seFlg==1){
									curRow=null;
									table.draw();
								}else if(seFlg==2){
									table.draw(false);
								}
								 $("#closeaddmaterialSourceDialogBtn").click();
								 
						        }
							else{
									alert(rdata.data);
								}
							}, 
							'json');
				}			
				
	  
//获取非集合数据
			function SendListRquest(){
				$.post("MaterialSourceAction_getMaterialSourceSet", 
						 {data:JSON.stringify({sourceName:''})}, 
				    function(rdataobj) {  
					showsetslist(rdataobj);
			}, 'json');
			    }
			
		//填充集合左边
		function showsetslist(rdataobj){
			
			$showlist=$($("#setsleftlistall")); 
			var ctbody=$("<tbody></tbody>").appendTo($showlist);
			for(i=0;i<rdataobj.data.length;i++){
			    var ctr=$("<tr></tr>").appendTo($(ctbody));
			    var ctd=$('<td width="20%"><input type="checkbox" name="checkbox_leftsets"></input></td>').appendTo($(ctr));
			    var ctd=$('<td width="25%">'+rdataobj.data[i].sourceCode+'</td>').appendTo($(ctr));
			    var ctd=$('<td width="55%">'+rdataobj.data[i].sourceName+'</td>').appendTo($(ctr));
				$(ctr).mouseover(function(){
					$(this).toggleClass('oddrow');
				});
				$(ctr).mouseout(function(){
					$(this).toggleClass("oddrow");     
				});
			}
		}	
		//填充集合右边
		function getMethodSub(sourceCode){

			$.post("MaterialSourceAction_getMaterialSourceSub", 
					  {data:JSON.stringify({sourceCode:sourceCode})}, 
			    function(rdataobj) { 
						    $showlist=$($("#setsrightlistall")); 
							var ctbody=$("<tbody></tbody>").appendTo($showlist);
							for(var i=0;i<rdataobj.data.length;i++){
							    var ctr=$("<tr></tr>").appendTo($(ctbody));
							    var ctd=$('<td width="20%"><input type="checkbox" name="checkbox_rightsets"></input></td>').appendTo($(ctr));
							    var ctd=$('<td width="25%">'+rdataobj.data[i].sourceCodeSub+'</td>').appendTo($(ctr));
							    var ctd=$('<td width="25%">'+rdataobj.data[i].sourceName+'</td>').appendTo($(ctr));
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
					 $("#addmaterialSourceDialog").show(); 
						

					 }else if(useflg==2){
						 
							
						  if(ifvalidate()==true){
							  var waycode=$("#materialSourceCode").val().trim();
							  if($($("input[name='ifsetsets']")).prop("checked")){ 
								  if(waycode.length >=3 ){
									alert("加工代码字符串长度要小于3");return;  
								  }
								    var count=0;
									
									$("input[name='checkbox_rightsets']").each(function(index){
										
											count++;
										
									});
								  if(count <=1){
									  alert("集合定义右侧数据栏中至少有2个加工方式");return;
								  }
							    }else{
							    	if(waycode.length !=1){
										alert("加工代码字符串长度必须为1");return;  
									  }
							    }
							  if(seFlg==1){
								  savemethod('0');
							  }
							  if(seFlg==2){
								var materialSourceID=table.row(curRow).data().materialSourceID;
								  savemethod(materialSourceID);
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
		
		
		
		
		
		
		
		
	  
	  
	
	function  SaveMaterialSource(materialSourceID,sourceCode,sourceName,ifSet){
		data={ 
				materialSourceID:materialSourceID,
				sourceCode : sourceCode,
				sourceName : sourceName,
				IfSet:IfSet
		}; 
		 var jsonstr = JSON.stringify(data);
		$.post("MaterialSourceAction_SaveMaterialSource?&ts="   
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
function  SaveMaterialSourceSub(materialSourceSubID,sourceCode,sourceCodeSub){
	data={ 
			materialSourceSubID:materialSourceSubID,
			sourceCode : sourceCode,
			sourceCodeSub : sourceCodeSub
			
	}; 
	 var jsonstr = JSON.stringify(data);
	$.post("MaterialSourceAction_SaveMaterialSourceSub?&ts="   
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

function delConvCtrlRule(){
	  if(curRow == null){
		  alert("请先选择一条要删除的记录！");
		  return;
	  }
	  if(confirm("确定要删除吗?")){  
		  var sourceCode = table.row(curRow).data().sourceCode;
		  $.post("MaterialSourceAction_DelMaterialSource?&ts="
					+ new Date().getTime(), {
						data:JSON.stringify({sourceCode:sourceCode})  
			}, function(rdata) {
				if(rdata.data == "true"){
					alert("删除成功！");
					table.row(curRow).remove().draw(false);
				}
			}, 'json');
	  }

}




function  DelMaterialSource(sourceCode){
	data={ 
			sourceCode:sourceCode
			
	}; 
	 var jsonstr = JSON.stringify(data);
	$.post("MaterialSourceAction_DelMaterialSource?&ts="   
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
function  GetMaterialSource(sourceName,showflg){
	data={ 
			sourceName:sourceName,
			showflg : showflg
			
	}; 
	 var jsonstr = JSON.stringify(data);
	$.post("MaterialSourceAction_GetMaterialSource?&ts="   
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
function  GetMaterialSourceSub(sourceCode){
	data={ 
			sourceCode:sourceCode
			
	}; 
	 var jsonstr = JSON.stringify(data);
	$.post("MaterialSourceAction_GetMaterialSourceSub?&ts="   
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
	
$("#export").click(exportData);
function exportData(){
	 window.location.href="MaterialSourceExportAction";
}
	
});