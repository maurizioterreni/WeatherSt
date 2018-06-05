package it.unifi.sam.terreni.weatherSt.utils;

public class GroupByClass {
	public static Long MAX_YEAR = 631139040000L;//20 anni 31556952000 * 20
	public static Long MAX_MONTH = 63113904000L;//24 mesi 2629746000 * 24
	public static Long MAX_WEEK = 7257600000L;//12 settimane 604800000 * 12
	public static Long MAX_DAY = 864000000L;//10 giorni 86400000 * 10
	
	
	public static String DAY_GROUPBY = " day(m.localDateTime), month(m.localDateTime), year(m.localDateTime) ";
	public static String WEEK_GROUPBY = " week(m.localDateTime), month(m.localDateTime), year(m.localDateTime) ";
	public static String MONTH_GROUPBY = " year(m.localDateTime), month(m.localDateTime) ";
	public static String YEAR_GROUPBY = " year(m.localDateTime) ";
	public static String HOUR_GROUPBY = " hour(m.localDateTime), day(m.localDateTime), month(m.localDateTime)  ";
	
}
