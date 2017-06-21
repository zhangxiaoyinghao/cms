/**
 * created by ms
 * last modified 2014/05/09
 * email: meishen@videoworks.cn
 * qq: 68593129
 */
var cn = {};
cn.videofact = {};
(function () {
	$(function () {
		
		initCommonComponents();
		addCommonEventListener();
	});
	/* Init common components.
	 ==========================================*/
	function initCommonComponents() {
		//initAuthority();
	}
	/* add init components.
	 ==========================================*/
	function initAuthority() {
		$("body").find("[data_auth]").each(function(){
			var auth = $(this).attr("data_auth");
			var disable = true;
			$.each(auths,function(index,value){
				if(auth==value){
					disable = false;
					return false;
				}
			});
			if(disable){
				if($(this).is('button')){
					$(this).addClass("disabled");
				}else if($(this).is('a')){
					$(this).css("color","#5F6369");
					$(this).attr("href","#");
				}else if($(this).is('div')){
					alert(1);
					if($(this).attr("name")=="switchDiv"){
						alert(2);
						//$(this).children().eq(0).addClass("bootstrap-switch-disabled");
					}
				}
			}
		});
	}

	/* Add common event listener.
	 ==========================================*/
	function addCommonEventListener() {
		/*batch-check*/
		//alert("===111");
		$(".batch-check .all").click(batchCheckAllClicked);
		$(".batch-check .inverse").click(batchCheckInverseClicked);
		$(".batch-check .cancel").click(batchCheckCancelClicked);

		/*pagination*/
		$(".pagination a").click(paginationAClicked);
		$("form.pagination-form [type=submit]").click(paginationFormSubmit);
		
		$("#serch").click(serchClicked);
	}

	/*Common event listener functions
	 ===========================================*/
	function serchClicked(){
		$(".advancedsearch").toggle();
	}
	
	

	
	function batchCheckAllClicked(event) {
		var target = event.target;
		var dataTarget = $(target).closest(".batch-check").data("target");
		$(dataTarget).attr("checked", true);
	}

	function batchCheckInverseClicked(event) {
		var target = event.target;
		var dataTarget = $(target).closest(".batch-check").data("target");
		$(dataTarget).each(function () {
			if ($(this).attr("checked")) {
				$(this).attr("checked", false);
			} else {
				$(this).attr("checked", true);
			}
		});
	}

	function batchCheckCancelClicked(event) {
		var target = event.target;
		var dataTarget = $(target).closest(".batch-check").data("target");
		$(dataTarget).attr("checked", false);
	}

	function paginationAClicked(event) {
		var target = event.target;
		var $Pagination = $(target).closest(".pagination");
		var pageIndex = 0;
		if ($(target).hasClass("prev")) {
			pageIndex = parseInt($Pagination.find("li.active>a").text()) - 1;
		} else if ($(target).hasClass("next")) {
			pageIndex = parseInt($Pagination.find("li.active>a").text()) + 1;
		} else {
			pageIndex = parseInt($(target).text());
		}
		if (isNaN(pageIndex)) {
			throw new Error("the index of page is not a number!");
			return;
		}
		$($Pagination.data("target")).find(".page-index").val(pageIndex);
		$($Pagination.data("target")).find("[type=submit]").trigger("click");
	}

	function paginationFormSubmit(event) {
		//alert("dd");
		event.preventDefault();
		var target = event.target;
		var $form = $(target).closest("form.pagination-form");
		var totalPage = parseInt($form.data("total-page"));
		
		if (isNaN(totalPage)) {
			return;
		}
		var pageIndex = parseInt($form.find(".page-index").val());
		if (isNaN(pageIndex) || (pageIndex > totalPage) || pageIndex < 1) {
			
			$form.find(".page-index").val("");
			return;
		} else {
				$form.submit();
		}
	}

	/*Commons*/
	function Commons() {
	}

	/* define properties
	 =========================================*/
	Commons.name = "se-common-js";
	Commons.version = "1.0";
	/* define functions
	 ==========================================*/
	/**
	 * clone object.
	 * @param input
	 * @returns {*}
	 */
	Commons.clone = function (input) {
		if (input === null) {
			return null;
		}

		// Final value.
		if (typeof input !== "object") {
			return input;
		}

		// Array.
		if (input instanceof Array) {

			var arr = [];
			for (var i = 0; i < input.length; i++) {

				if (typeof input[i] === "object") {
					arr[i] = clone(input[i]);
				} else {
					arr[i] = input[i];
				}
			}

			return arr;
		}

		var output = {};
		for (var attr in input) {
			output[ attr ] = clone(input[ attr ]);
		}
		return output;
	}

	/*Class
	 ==============================================*/
	/**
	 * a function triggered after @param tm time.
	 * @param handler
	 * @param tm
	 * @constructor
	 */
	Commons.LazyFunction = function (handler, tm) {
		this.cnt = 0;
		this.tm = tm || 1000;
		this.handler = handler || function () {
		};
	}
	Commons.LazyFunction.prototype = {
		execute: function () {
			clearTimeout(this.cnt);
			this.cnt = setTimeout(this.handler, this.tm);
		}
	}

	cn.videofact.Commons = Commons;
})();


/**
 * 初始化时间控件
 */
function initCalendar(mark)
{
    $(mark).datetimepicker(
    {
    	 language : 'zh-CN',
    	 format: "yyyy-mm-dd hh:ii:ss",
    	 autoclose: true,
    	 todayBtn: true,
    	 pickerPosition: "bottom-left"
    	
    }
    );
}

/**
 * 时间戳格式化时间字符串  
 * @param longDate
 * @returns {String}
 */
function formatTime(longDate){
	var date=new Date();
	date.setTime(longDate);
	var year=date.getFullYear();
	var month=date.getMonth()+1;
	if(month<9)
		month="0"+month;
	var today=date.getDate();
	if(today<10)
		today="0"+today;
	var hour=date.getHours();
	if(hour<10)
		hour="0"+hour;
	var minute=date.getMinutes();
	if(minute<10)
		minute="0"+minute;
	var second=date.getSeconds();
	if(second<10)
		second="0"+second;
	return year+"-"+month+"-"+today+" "+hour+":"+minute+":"+second;
}

/**
 * 字符串转换时间戳
 * @param strDate
 * @returns
 */
function parseTime(strDate){
	strDate = strDate.replace("-","/").replace("-","/");
	var longDate=new Date(strDate).getTime();
	return longDate;
}

/* add init components.
==========================================*/
function initAuthority() {
	$("body").find("[data_auth]").each(function(){
		var auth = $(this).attr("data_auth");
		var disable = true;
		$.each(auths,function(index,value){
			if(auth==value){
				disable = false;
				return false;
			}
		});
		if(disable){
			$(this).addClass("disabled");
			if($(this).is('button')){
			}else if($(this).is('a')){
				$(this).css("color","#5F6369");
				$(this).attr("href","#");
			}else if($(this).is('input')){
				if($(this).attr("name")=="switch"){
//					alert($(this).parent().parent().html());
					$(this).bootstrapSwitch('readonly', true);
					
					//$(this).children().eq(0).addClass("bootstrap-switch-disabled");
				}
			}
		}
	});
}
//function initMenuBar() {
//	if(showMenuBar=="yes"){
//		$(".showMenuBar").show();
//	}
//}
