package it.unifi.sam.terreni.weatherSt.utils;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringUtils {
	public static boolean isEmpty(String str) {
		if(str == null || str.isEmpty())
			return true;
		return false;
	}
	
	
	public static String doubleToString(Double f) {
		DecimalFormat df = new DecimalFormat("#.00");
		
		return df.format(f);
	}
	
	public static String floatToString(Float f) {
		return floatToString(f,"#.00");
	}
	
	public static String floatToString(Float f, String pattern) {
		DecimalFormat df = new DecimalFormat(pattern);
		
		return df.format(f);
	}
	
	
	
	public static String locatDateTimeToString(LocalDateTime time, String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return time.format(formatter);
	}
	public static String locatDateTimeToString(LocalDateTime time) {
		return locatDateTimeToString(time, "yyyy-MM-dd HH:mm:ss");
	}
}
