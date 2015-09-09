$(document).ready(function(){
	
	
	$("#risk-nav ul li").each(function(i){
		switch(i){
		case 0 :
		$(this).click(function(){
		$("li.current").removeClass("current");
		$(this).addClass("current");
		$("#right-content").empty().load("./html/ConventionCtrl.html?ts="+new Date().getTime());
		});
		break;
		case 1 :
		$(this).click(function(){
		$("li.current").removeClass("current");
		$(this).addClass("current");
		$("#right-content").empty().load("./html/CIQCtrl.html?ts="+new Date().getTime());
		});
		break;
		case 2 :
			$(this).click(function(){
				$("li.current").removeClass("current");
				$(this).addClass("current");
				$("#right-content").empty().load("./html/ProductControlItem.html?ts="+new Date().getTime());
			});
			break;
		case 3 :
			$(this).click(function(){
				$("li.current").removeClass("current");
				$(this).addClass("current");
				$("#right-content").empty().load("./html/AccessoryCtrl.html?ts="+new Date().getTime());
			});
			break;
		case 4 :
			$(this).click(function(){
				$("li.current").removeClass("current");
				$(this).addClass("current");
				$("#right-content").empty().load("./html/AdditiveCtrl.html?ts="+new Date().getTime());
			});
			break;
		   case 5 :
			$(this).click(function(){
				$("li.current").removeClass("current");
				$(this).addClass("current");
				$("#right-content").empty().load("./html/ForeignReport.html?ts="+new Date().getTime());
			});
			break;     
		
		}

		});
	$("#enterprise-nav ul li").each(function(i){
			switch(i){
			case 0 :
			$(this).click(function(){
			$("li.current").removeClass("current");
			$(this).addClass("current");
			$("#right-content").empty().load("./html/entBasicData.html?ts="+new Date().getTime());
			});
			break;
			case 1 :
			$(this).click(function(){
			$("li.current").removeClass("current");
			$(this).addClass("current");
			$("#right-content").empty().load("./html/entProductCheck.html?ts="+new Date().getTime());
			});
			break;
			case 2 :
				$(this).click(function(){
					$("li.current").removeClass("current");
					$(this).addClass("current");
					$("#right-content").empty().load("./html/EntProduct.html?ts="+new Date().getTime());
				});
				break;
			case 3 :
				$(this).click(function(){
					$("li.current").removeClass("current");
					$(this).addClass("current");
					$("#right-content").empty().load("./html/BaseMng.html?ts="+new Date().getTime());
				});
				break;
			case 4 :
				$(this).click(function(){
					$("li.current").removeClass("current");
					$(this).addClass("current");
					$("#right-content").empty().load("./html/BaseMaterial.html?ts="+new Date().getTime());
				});
				break;
			
			case 5 :
				$(this).click(function(){
					$("li.current").removeClass("current");
					$(this).addClass("current");
					$("#right-content").empty().load("./html/materialBatch.html?ts="+new Date().getTime());
				});
				break;
			case 6 :
				$(this).click(function(){
					$("li.current").removeClass("current");
					$(this).addClass("current");
					$("#right-content").empty().load("./html/CtrlPlan.html?ts="+new Date().getTime());
				});
				break;	
			
			
			}
			});
	  $("#business-nav ul li").each(function(i){
		switch(i){
		case 0 :
		$(this).click(function(){
		$("li.current").removeClass("current");
		$(this).addClass("current");
		$("#right-content").empty().load("./html/processReceiver.html?ts="+new Date().getTime());
		});
		break;
		case 1 :
			$(this).click(function(){
				$("li.current").removeClass("current");
				$(this).addClass("current");
				$("#right-content").empty().load("./html/SampleRegister.html?ts="+new Date().getTime());
			});
			break;
		case 2 :
		$(this).click(function(){
		$("li.current").removeClass("current");
		$(this).addClass("current");
		$("#right-content").empty().load("./html/qualifyevalue.html?ts="+new Date().getTime());
		});
		break;
		case 3 :
			$(this).click(function(){
			$("li.current").removeClass("current");
			$(this).addClass("current");
			$("#right-content").empty().load("./html/abnormaldecl.html?ts="+new Date().getTime());
			});
			break;
		case 4 :
			$(this).click(function(){
				$("li.current").removeClass("current");
				$(this).addClass("current");
				$("#right-content").empty().load("./html/DeclProcess.html?ts="+new Date().getTime());
			});
			break;
		case 5 :
			$(this).click(function(){
				$("li.current").removeClass("current");
				$(this).addClass("current");
				$("#right-content").empty().load("./html/DeclQuery.html?ts="+new Date().getTime());
			});
			break;
	
			
		}
		});
	  $("#management-nav ul li").each(function(i){
			switch(i){
			case 0 :
			$(this).click(function(){
			$("li.current").removeClass("current");
			$(this).addClass("current");
			$("#right-content").empty().load("./html/inspOperatorManage.html?ts="+new Date().getTime());
			});
			break;
			case 1 :
			$(this).click(function(){
			$("li.current").removeClass("current");
			$(this).addClass("current");
			$("#right-content").empty().load("./html/changeOperatorPassword.html?ts="+new Date().getTime());
			});
			break;
			case 2 :
				$(this).click(function(){
				$("li.current").removeClass("current");
				$(this).addClass("current");
				$("#right-content").empty().load("./html/roleManage.html?ts="+new Date().getTime());
				});
				break;
			case 3 :
				$(this).click(function(){
				$("li.current").removeClass("current");         
				$(this).addClass("current");
				$("#right-content").empty().load("./html/thridTestLab.html?ts="+new Date().getTime());
				});
				break;
			case 4 :
				$(this).click(function(){
					$("li.current").removeClass("current");
					$(this).addClass("current");
					$("#right-content").empty().load("./html/OrgAndDeptMng.html?ts="+new Date().getTime());
				});
				break;	
			case 5 :
				$(this).click(function(){
					$("li.current").removeClass("current");
					$(this).addClass("current");
					$("#right-content").empty().load("./html/productStander.html?ts="+new Date().getTime());
				});
				break;		
			}
			});
    $("#basic-setting-nav ul li").each(function(i){
		switch(i){
		case 0 :
			$(this).click(function(){
			$("li.current").removeClass("current");
			$(this).addClass("current");
		    $("#right-content").empty().load("./html/productClass.html?ts="+new Date().getTime());
		    });
		break;

		case 1 :
			$(this).click(function(){
			$("li.current").removeClass("current");
			$(this).addClass("current");
		    $("#right-content").empty().load("./html/processingWay.html");     
		    });
		break;
		case 2 :
			$(this).click(function(){
				$("li.current").removeClass("current");
				$(this).addClass("current");
			$("#right-content").empty().load("./html/materialClass.html");     
			});
		break;
		case 3 :
			$(this).click(function(){
				$("li.current").removeClass("current");
				$(this).addClass("current");
			$("#right-content").empty().load("./html/materialSource.html");     
			});
		break;
		case 4 :
			$(this).click(function(){
				$("li.current").removeClass("current");
				$(this).addClass("current");
			$("#right-content").empty().load("./html/packageType.html");     
			});
		break;
		case 5 :
			$(this).click(function(){
				$("li.current").removeClass("current");
				$(this).addClass("current");
			$("#right-content").empty().load("./html/intendedUse.html");     
			});
		break;
		case 6 :
			$(this).click(function(){
				$("li.current").removeClass("current");
				$(this).addClass("current");
			$("#right-content").empty().load("./html/processingDegree.html");     
			});
		break;
		case 7 :
			$(this).click(function(){
				$("li.current").removeClass("current");
				$(this).addClass("current");
			$("#right-content").empty().load("./html/storageCondition.html");     
			});
		break;
		case 8 :
			$(this).click(function(){
				$("li.current").removeClass("current");
				$(this).addClass("current");
			$("#right-content").empty().load("./html/countryArea.html");     
			});
		break;
		case 9 :
			$(this).click(function(){
				$("li.current").removeClass("current");
				$(this).addClass("current");
			$("#right-content").empty().load("./html/examineItem.html");     
			});
		break;
		case 10 :
			$(this).click(function(){
				$("li.current").removeClass("current");
				$(this).addClass("current");
			$("#right-content").empty().load("./html/accessorySet.html");     
			});
		break;
		case 11 :
			$(this).click(function(){
				$("li.current").removeClass("current");
				$(this).addClass("current");
			$("#right-content").empty().load("./html/additiveSet.html");     
			});
		break;
		case 12 :
			$(this).click(function(){
				$("li.current").removeClass("current");
				$(this).addClass("current");
			$("#right-content").empty().load("./html/additivePurpose.html");     
			});
		break;         
		case 13 :
			$(this).click(function(){
				$("li.current").removeClass("current");
				$(this).addClass("current");
			$("#right-content").empty().load("./html/samplingRatio.html");     
			});
		break; 
		/*case 2 :
			$(this).click(function(){
				$("li.current").removeClass("current");
				$(this).addClass("current");
			$("#right-content").empty().load("./html/materialClass.html");     
			});
		break;*/
		

		}
    });
    
    $("#statistics-nav ul li").each(function(i){
		switch(i){
		case 0 :
			$(this).click(function(){
				$("li.current").removeClass("current");
				$(this).addClass("current");
				$("#right-content").empty().load("./html/DeclStatistics.html?ts="+new Date().getTime());
			});
			break;
		case 1 :
			$(this).click(function(){
				$("li.current").removeClass("current");
				$(this).addClass("current");
				$("#right-content").empty().load("./html/GoodsStatistics.html?ts="+new Date().getTime());
			});
			break;
		case 2 :
			$(this).click(function(){
				$("li.current").removeClass("current");
				$(this).addClass("current");
				$("#right-content").empty().load("./html/ProductStatistics.html?ts="+new Date().getTime());
			});
			break;
		case 3 :
			$(this).click(function(){
				$("li.current").removeClass("current");
				$(this).addClass("current");
				$("#right-content").empty().load("./html/ItemStatistics.html?ts="+new Date().getTime());
			});
			break;
		case 4 :
			$(this).click(function(){
				$("li.current").removeClass("current");
				$(this).addClass("current");
				$("#right-content").empty().load("./html/SamplingStatistics.html?ts="+new Date().getTime());
			});
			break;
		case 5 :
			$(this).click(function(){
				$("li.current").removeClass("current");
				$(this).addClass("current");
				$("#right-content").empty().load("./html/BaseStatistics.html?ts="+new Date().getTime());
			});
			break;
	    case 6 :
			$(this).click(function(){
				$("li.current").removeClass("current");
				$(this).addClass("current");
				$("#right-content").empty().load("./html/ReleaseModeStatistics.html?ts="+new Date().getTime());
			});
			break;
	    case 7 :
			$(this).click(function(){
				$("li.current").removeClass("current");
				$(this).addClass("current");
				$("#right-content").empty().load("./html/DeclQueryStatistics.html?ts="+new Date().getTime());
			});
			break;
	    case 8 :
			$(this).click(function(){
				$("li.current").removeClass("current");
				$(this).addClass("current");
				$("#right-content").empty().load("./html/AccessorySearch.html?ts="+new Date().getTime());
			});
			break;
	    case 9 :
			$(this).click(function(){
				$("li.current").removeClass("current");
				$(this).addClass("current");
				$("#right-content").empty().load("./html/AdditiveSearch.html?ts="+new Date().getTime());
			});
			break;
	    case 10 :
			$(this).click(function(){
				$("li.current").removeClass("current");
				$(this).addClass("current");
				$("#right-content").empty().load("./html/TestResultSearch.html?ts="+new Date().getTime());
			});
			break;
	    case 11 :
			$(this).click(function(){
				$("li.current").removeClass("current");
				$(this).addClass("current");
				$("#right-content").empty().load("./html/CalculateStaticData.html?ts="+new Date().getTime());
			});
			break;
		}
	});
    
    
    
    
    
    setMinContentDivHeight();
    
    $("#business-nav ul li:first").click();
});


