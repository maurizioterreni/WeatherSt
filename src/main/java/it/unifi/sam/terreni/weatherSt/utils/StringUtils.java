package it.unifi.sam.terreni.weatherSt.utils;

public class StringUtils {
	public static boolean isEmpty(String str) {
		if(str == null || str.isEmpty())
			return true;
		return false;
	}
}
