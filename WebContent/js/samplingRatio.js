$(document).ready(
		function(){
			 var curRow = null; //局部变量
			 var subcurRow = null;
			 var useflg=null;
			 var menunam='产品抽检率设置';
			  
	//页面进入加载产品大类
			var columnproClass = [
			                  { "data": "levelDesc" },
			                  { "data": "samplingLevel" },
			      	          { "data": "lowerSamplingRatio" },
			      	          { "data": "upperSamplingRatio" },
			                 ];
			var table = $('#prosampraditoTb').DataTable( {
				  "deferRender": true,
				  "processing": true,
				  "serverSide": true,
				  "search":true,
				  "paging" : false,
				  "info":false,
				  "ajax":{
					  url : "SamplingRatioAction_GetSamplingRatioDefine",
					  type : "GET",
					  data : function(d){
						  d.data = getRequestParam();
						 
					  }
				  },
			      "columns": columnproClass,
			   "columnDefs": [
			                     {
			                         "targets": [ 1 ],
			                         "visible": false,
			                     }
			                 ]
			  } );
			//初始化第一行默认选中
			$('#prosampraditoTb').on( 'draw.dt', function () {
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
			
		//项目表	
			var columnproSubClass = [
				                  { "data": "levelDesc" },
				                  { "data": "samplingLevel" },
				      	          { "data": "riskEvlDesc" },
				      	          { "data": "riskEvlLevel" },
				      	          { "data": "lowerSamplingRatio" },
				      	          { "data": "upperSamplingRatio" },
				                 ];
				var subtables = $('#itemsamplraidtoTb').DataTable( {
					  "deferRender": true,
					  "processing": true,
					  "serverSide": true,
					  "search":true,
					  "paging" : false,
					  "info":false,
					  "ajax":{
						  url : "SamplingRatioAction_GetSamplingRatioDefineItem",
						  type : "GET",
						  data : function(d){
							  d.data = getRequestParam();
							 
						  }
					  },
				      "columns": columnproSubClass,
				    "columnDefs": [
				                     {
				                         "targets": [ 1,3 ],
				                         "visible": false,
				                     }
				                 ]
				  } );
			
			
			//项目表第一行默认选中
			$('#itemsamplraidtoTb').on( 'draw.dt', function () {
				  if(subtables.rows().data().length > 0){
					  var dtRow = null;
					  var node = null;
					  if(subcurRow != null){
					  dtRow = subtables.row($(subcurRow).context._DT_RowIndex);
					  node = dtRow.node();
					  if(node != null){
					  $(node).click();
					  return;
					  }
					  }
					  dtRow = subtables.row(0);
					  node = dtRow.node();
					  $(node).click();  
				 }else{
                	 subcurRow=null;
                 }
				 
			  } );
			
			
			//点击选中某一行
			$('#prosampraditoTb tbody').on("click", "tr", clickRow );
			$('#itemsamplraidtoTb tbody').on("click", "tr", clickRowSub );
			
			//选中产品某一行
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
			//选中xm某一行
			 function clickRowSub(){  
				  var row = this;
				  if ( $(row).hasClass('active') ) {  
					    subcurRow = null;
			            $(row).removeClass('active');
			        }
			        else {
			        	subcurRow = row;
			            subtables.$('tr.active').removeClass('active');
			            $(row).addClass('active');
			        }
			 }
			  
			
							
				 
			
			
			//获取请求参数
			  function getRequestParam(){
				  var data = {
						    levelDesc : "",
						   
						     };
				  var jsonstr = JSON.stringify(data);   
				  
				  return jsonstr;
			  }
			
		
			
			//产品修改按钮
			 $("#editsamplingrad").click(function(){
				  
				 useflg=1;
				 getoperatelimit(menunam, useflg);
			  });
			 //产品修改功能
			 $("#confirmprosmbtn").click(function(){
				 
				 var samplingLevel=table.row(curRow).data().samplingLevel;
				 var lowerSamplingRatio=$("#lowerSamplingRatio").val().trim();
				 var upperSamplingRatio=$("#upSamplingRatio").val().trim();
				 if(lowerSamplingRatio=='' || upperSamplingRatio=='' ){alert("上限下限值不能为空");return;}
				 if(isNaN(lowerSamplingRatio)){alert("下限值必须为数字");return;}
				 if(isNaN(upperSamplingRatio)){alert("上限值必须为数字");return;}
				 if(parseInt(lowerSamplingRatio, 10) > parseInt(upperSamplingRatio, 10)){alert("下限值不能大于上限值");return;}
				 saveProClass(samplingLevel,lowerSamplingRatio,upperSamplingRatio);   		  
			    	   
			 });
			 //产品取消
			 $("#closeprosmdialog").click(function(){
				  $(".overlay").hide();
			      $("#editprosmdialog").hide(); 
			      clearAlertDiv("editprosmdialog");
			  });
			
			//项目修改按钮
			 $("#edititemcouraidot").click(function(){
				 
				 useflg=2;
				 getoperatelimit(menunam, useflg);
				  
			  });
			     
			 //项目修改功能
			 $("#confismitemb").click(function(){          
				 
				 var samplingLevel=subtables.row(subcurRow).data().samplingLevel;
				 var riskEvlLevel=subtables.row(subcurRow).data().riskEvlLevel;
				 var lowerSamplingRatio=$("#procoujianlower").val().trim();
				 var upperSamplingRatio=$("#prochojupdg").val().trim();
				 if(lowerSamplingRatio=='' || upperSamplingRatio=='' ){alert("上限下限值不能为空");return;}
				 if(isNaN(lowerSamplingRatio)){alert("下限值必须为数字");return;}
				 if(isNaN(upperSamplingRatio)){alert("上限值必须为数字");return;}
				 if(parseInt(lowerSamplingRatio, 10) > parseInt(upperSamplingRatio, 10)){alert("下限值不能大于上限值");return;}
				 saveItemClass(samplingLevel, riskEvlLevel, lowerSamplingRatio, upperSamplingRatio);
			parseInt(lowerSamplingRatio, 10)
			 });
			 //项目取消
			 $("#closeedititemdialgo").click(function(){    
				  $(".overlay").hide();
			      $("#edititemsmdialgo").hide(); 
			      clearAlertDiv("edititemsmdialgo");   
			  });
			
			
			
			
			 
			 //保存产品修改
			  function saveProClass(samplingLevel,lowerSamplingRatio,upperSamplingRatio){
				
			
					  data={    samplingLevel:samplingLevel,
							  lowerSamplingRatio:lowerSamplingRatio,
							  upperSamplingRatio:upperSamplingRatio
				    	};   
					  var jsonstr = JSON.stringify(data);
				  $.post("SamplingRatioAction_SaveProductSamplingRatioDefine?&ts="   
							+ new Date().getTime(), {
								 data : jsonstr
							}, function(rdata) {
						if(rdata.data == "true"){ 
							table.draw();
							$("#closeprosmdialog").click();
							
					    }else{
							alert(rdata.data);
							$("#closeprosmdialog").click();
						}
					}, 'json');
			 
			  }
			
			  //保存项目修改
			  function saveItemClass(samplingLevel,riskEvlLevel,lowerSamplingRatio,upperSamplingRatio){
				
			
					  data={    samplingLevel:samplingLevel,
							    riskEvlLevel:riskEvlLevel,
							    lowerSamplingRatio:lowerSamplingRatio,
							    upperSamplingRatio:upperSamplingRatio
				    	};   
					  var jsonstr = JSON.stringify(data);
				  $.post("SamplingRatioAction_SaveItemSamplingRatioDefine?&ts="   
							+ new Date().getTime(), {
								 data : jsonstr
							}, function(rdata) {
						if(rdata.data == "true"){ 
							subtables.draw();
						
							$("#closeedititemdialgo").click();
							
					    }else{
							alert(rdata.data);
							$("#closeedititemdialgo").click();
						}
					}, 'json');
			 
			  }
			 
			 
			  
		//权限	 
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
								 if(curRow==null){alert("选中一行");return;}
								 $(".overlay").show();
							    $("#editprosmdialog").show();
							    $("#levelDesc").val(table.row(curRow).data().levelDesc);
								$("#lowerSamplingRatio").val(table.row(curRow).data().lowerSamplingRatio);
								$("#upSamplingRatio").val(table.row(curRow).data().upperSamplingRatio);
								 
				   				 
							 }else if(useflg==2){
							if(subcurRow==null){alert("请选中一行");return;}
							 $(".overlay").show();
							 $("#edititemsmdialgo").show(); 
							 $("#chyangolevle").val(subtables.row(subcurRow).data().levelDesc);
							 $("#itemrisklevle").val(subtables.row(subcurRow).data().riskEvlDesc);
							 $("#procoujianlower").val(subtables.row(subcurRow).data().lowerSamplingRatio);
							 $("#prochojupdg").val(subtables.row(subcurRow).data().upperSamplingRatio);
							 }
							 
							}else{    
								alert(rdata.data);
							}
						}, 'json');
					
				}	 
			 
			 

	

	
	
});