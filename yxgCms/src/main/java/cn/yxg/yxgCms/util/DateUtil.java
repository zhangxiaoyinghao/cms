package cn.yxg.yxgCms.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Date getCurrentDateZero(){
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zero = calendar.getTime();
        return zero;
	}

	public static Date getOneMuniteLater() {
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR, 1);
		return calendar.getTime();
	}
	
	public static Date getSomeDaysLater(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
        calendar.add(Calendar.DATE,days);
		return calendar.getTime();
	}
	
}
