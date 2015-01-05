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
				$("#right-content").empty().load("./html/AccessoryCtrl.html?ts="+new Date().getTime());
			});
			break;
		case 3 :
			$(this).click(function(){
				$("li.current").removeClass("current");
				$(this).addClass("current");
				$("#right-content").empty().load("./html/AdditiveCtrl.html?ts="+new Date().getTime());
			});
			break;
		}
	});
	
	setMinContentDivHeight();
});