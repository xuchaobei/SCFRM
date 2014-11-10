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
	}
 }

/**
 * 获取select中的编码，select中数据格式为：code+" "+name.
 * @param selectId
 * @returns
 */
function getSelectValue(selectId){
	var val = $("#"+selectId).val();
	var code =  val == null ? "" : val.split(" ")[0];
	return code;
}

function initContentDivHeight() {
	var windowHeight = $(window).height();
	var headerHeight = $(".header").outerHeight();
	var contentHeight = $(".page-content").outerHeight();
	var footerHeight = $("footer").outerHeight();
	var minContHeight = windowHeight - headerHeight - footerHeight - 30;
	if (minContHeight > contentHeight) {
		$(".page-content").height(minContHeight);
	}
}

	