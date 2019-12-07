package com.spidersmart.register.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class RegisterUtilities {
	public static String convertDateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		return sdf.format(date);
	}
	
	public static Date convertStringToDate(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		return sdf.parse(date);
	}	
	
	public static String getTodayString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(new Date());
	}
}
