package com.weiwu.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {
	
	static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月");  
	
	public static String getCurYearMonth(){
		Calendar cal= Calendar.getInstance();
		return simpleDateFormat.format(cal.getTime());
	}
}
