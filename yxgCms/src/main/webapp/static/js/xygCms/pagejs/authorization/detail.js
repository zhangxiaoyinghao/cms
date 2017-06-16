$(function() {
	//初始化控件
	initComponents();
	//监听所有event事件
	addMyEventListener();
});
/*
 * Init components. ==========================================
 */
function initComponents() {
//			initCalendar(".form_datetime");
	initFormValidate();
	initTagName();
}
/*
 * Add event listener. ==========================================
 */
function addMyEventListener(){
	$('#saveBtn').click(save);
	$('#toListBtn').click(toList);
	$('#maxDateBtn').click(maxDate);
	$('.form_datetime').click(initDatePicker);
}
function initTagName() {
	if($("#id").val()){
		$(".box-title").html("权限修改");
		$("#saveBtn").html("修改");
	}
}
function initDatePicker(){
	WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});
}
function save(){
	if($('#form').valid()){
//				initFormatToLong("#effectiveDateStr","#effectiveDate");
//				initFormatToLong("#expireDateStr","#expireDate");
		$('#effectiveDate').val('946656000000');
		$('#expireDateStr').val('32503651199000');
		var mesg="";
		if(id==""){
			form.action="save";
			mesg="新增权限";
		}else{
			form.action="update";
			mesg="修改权限";
		}
		if($("#systemAudit").val()=="yes"){
			$.Zebra_Dialog(mesg+"正在审核请稍后.", {
				'type':     'information',
				'title':    '提示',
				'buttons':  ["确定"],
				'onClose':  function(caption){
					form.submit();
				}
			});
		}else{
			form.submit();
		}
	}
}
function toList(){
	window.location.href="list";
	window.event.returnValue = false;
}
function maxDate(){
	$('#effectiveDate').val('2000-01-01 00:00:00');
	$('#expireDateStr').val('2999-12-31 23:59:59');
}
/**
 * 时间转毫秒数
 */
function initFormatToLong(mark,tomark){
	//把页面时间戳转成时间
	$(mark).each(function(){
		if($.trim($(this).val())!="" && $.trim($(this).val())!="NaN"){
			var value = parseTime($.trim($(this).val()));
			$(tomark).val(value);
		}else{
			$(tomark).val("0");
		}
	});
}

/**
 * 初始化表单效验
 */
function initFormValidate(){
	$('#form').validate({
		rules:{
			name: {
				required: true,
				rangelength:[1,64]
			},
			content: {
				required: true
			},
			categoryId:{
				required: true
			},
			description:{
				rangelength:[1,1024]
			}
		},
		messages:{
			name: {
				required: "请输入权限名称",
				rangelength:"您输入的请输入权限名称过长，请重新输入"
			},
			content: {
				required: "请输入权限内容"
			},
			categoryId:{
				required: "请选择一个系统"
			},
			description:{
				rangelength:"您输入的请输入权限描述过长，请重新输入"
			}
		},
		errorPlacement:function(error, element) { 
			error.appendTo(element.parent().find("span")); 
		} 
	});			
}
