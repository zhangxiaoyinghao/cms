/*
 * @author aqr
 * @qq 995349498
 * @date 1/16/2014
 * @email mgwaqir@gmail.com
 * */
(function ($) {
	if ($.fn.datepicker === undefined || typeof $.fn.datepicker !== "function") {
		throw new Error("there is no jQuery function named datepicker");
		return;
	}
	$(function () {
		initComponents();
		addEventListener();
	});
	/* InitComponents
	 =====================================================*/
	function initComponents() {
		$(".datepicker[data-send=ms]").datepicker({
			dateFormat: "yy-mm-dd",
			onSelect: function () {
				datePickerChanged($(this));
			}
		});
		$(".datepicker[data-send=ms]").each(function () {
			if (isNaN(parseInt($(this).data("ms")))) {
				return;
			}
			var date = new Date(parseInt($(this).data("ms")));
			$(this).datepicker("setDate", date);
			datePickerChanged($(this));
		});
	}

	/* Add event listener
	 ======================================================*/
	function addEventListener() {
		$(".datepicker[data-send=ms]").keyup(datePickerKeyUp);
	}

	/* Function of addEventListener
	 ======================================================*/
	function datePickerKeyUp(event) {
		var target = event.target;
		if ($.trim($(target).val()) === "") {
			$(target).next(".datepicker-ms").val("");
		}
	}

	/* Normal functions
	 ======================================================*/
	function datePickerChanged($obj) {
		var $input = null;
		if ($(".datepicker-ms[name='" + $obj.data("name") + "']").size() === 0) {
			$input = $("<input type='hidden' class='datepicker-ms' name='" + $obj.attr("name") + "'/>");
			$obj.attr("data-name",$obj.attr("name"));
			$obj.removeAttr("name");
		} else {
			$input = $(".datepicker-ms[name='" + $obj.data("name") + "']");
		}
		console.log($obj.datepicker("getDate").getTime());
		$input.val($obj.datepicker("getDate").getTime());
		$input.insertAfter($obj);
	}
})(jQuery);
