Date.prototype.format = function(format) {
    var date = {
           "M+": this.getMonth() + 1,
           "d+": this.getDate(),
           "h+": this.getHours(),
           "m+": this.getMinutes(),
           "s+": this.getSeconds(),
           "q+": Math.floor((this.getMonth() + 3) / 3),
           "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
           format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
           if (new RegExp("(" + k + ")").test(format)) {
                  format = format.replace(RegExp.$1, RegExp.$1.length == 1
                         ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
           }
    }
    return format;
}

/**
 * 时间戳格式化时间字符串  年月日时分秒
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
 * 年月日
 * @param longDate
 * @returns {String}
 */
function formatTimeYMD(longDate){
	var date=new Date();
	date.setTime(longDate);
	var year=date.getFullYear();
	var month=date.getMonth()+1;
	if(month<9)
		month="0"+month;
	var today=date.getDate();
	if(today<10)
		today="0"+today;
	
	return year+"-"+month+"-"+today;
}

/**
 * 获取当前时间
 * @returns {String}
 */
function getDate(){
	var now=new Date().getTime();
	return formatTimeYMD(now);
}