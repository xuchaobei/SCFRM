$(document).ready(function(){
	
	$("#risk-nav ul li").each(function(i){
		switch(i){
		case 0 :
			$(this).click(function(){
				$("#right-content").empty().load("./html/ConventionCtrl.html?ts="+new Date().getTime());
			});
			break;
		case 1 :
			$(this).click(function(){
				$("#right-content").empty().load("./html/ConventionCtrl.html");
			});
			break;
		}
	});
	
	setMinContentDivHeight();
});