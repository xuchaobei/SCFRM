$(document).ready(function() {
	$(".submenu > a").click(function(e) {
		e.preventDefault();
		var $li = $(this).parent("li");
		var $ul = $(this).next("ul");

		if ($li.hasClass("open")) {
			$ul.slideUp(350);
			$li.removeClass("open");
		} else {
			$(".nav > li > ul").slideUp(350);
			$(".nav > li").removeClass("open");
			$ul.slideDown(350);
			$li.addClass("open");
		}
	});
	
});

$.extend( $.fn.dataTable.defaults, {
    "searching": false,
    "ordering": false,
    "pagingType": "full_numbers",
    "dom" : 'rt<"bottom"lip><"clear">',
    "language": {
        "url": "vendors/datatables/chinese.lang.json"
    }
} );

var reLogin = false;

$( document ).ajaxComplete(function(event,xhr,options) {
	if(reLogin){
		return;
	}
	if(xhr.status == 600){
		if(!reLogin){
			reLogin = true;
			alert("登录超时，请重新登录！");
			document.location.href="./login.html";
		}
	}
});

/*自定义autocomplete
 * collection ： 绑定的数据集合
 * input_id : input id
 * btn_id : 下拉按钮 id
 * create_cb : 创建时的回调，可选
 * select_cb : 选中条目后的回调 
 * */
function cus_autocomplete(collection, input_id, btn_id, create_cb, select_cb){
	
	$("#"+input_id).autocomplete({
		source : collection,
		minLength : 0,		
		create : create_cb,
		open : function(event, ui){
			var menuWidth = $(this).outerWidth() +  $("#"+btn_id).outerWidth();
	        $(this).autocomplete( "widget" ).width(menuWidth);
		},
		select : select_cb,		
	});
	if(btn_id != null){
		$("#"+btn_id).off("click");
		$("#"+btn_id).click(
			function() {	
				$("#"+input_id).autocomplete("search", "");	
				$("#"+input_id).focus();
		});		
	}else{
		$("#"+input_id).focus();
	}
 }

/**
 * 获取select中的编码，select中数据格式为：code+" "+name.
 * @param selectId
 * @returns
 */
function getSelectValue(selectId){
	var val = $("#"+selectId).val().trim();
	if(val == null || val == ""){
		return "";
	}else if(!/\w+\s[\w\W]+/.test(val)){
		alert("请输入合法的数据格式！");
		return null;
	}else{
		return val.split(" ")[0];
	}
}


/**
 * 获取通过input进行查询的参数值，如果input的value为code+" "+name,截取name作为请求参数。
 * @param inputID
 * @returns
 */
function getSearchParam(inputID){
	var val = $("#"+inputID).val().trim();
	if(/\w+\s[\w\W]+/.test(val)){
		return val.split(" ")[1];
	}else{
		return val;
	}
}


/**
 * 检测json格式的请求对象是否合法，配合getSelectValue方法使用，确保code+" "+name格式输入参数的合法性。
 * @param data json格式的请求对象
 * @returns {Boolean}
 */
function checkJsonParam(data){
	for(var item in data){
		if(data[item] == null){
			return false;
		}
	}
	return true;
}

/**
 * 设置内容区域的最小高度
 */
function setMinContentDivHeight() {
	var windowHeight = $(window).height();
	var headerHeight = $(".header").outerHeight();
	var contentHeight = $("#content").outerHeight();
	var footerHeight = $("footer").outerHeight();
	var minContHeight = windowHeight - headerHeight - footerHeight - 30;
	if (minContHeight > contentHeight) {
		$("#content").css("min-height", minContHeight);
	}
}

/**
 * 清空弹出div中input标签的输入内容
 */
function clearAlertDiv(divID){
	$("#"+divID + " input[type='text']").each(function(){
		$(this).val("");
	});
	$("#"+divID + " input[type='checkbox']").each(function(){
		$(this).attr("checked", false);
	});
}

	