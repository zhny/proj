package cn.zhny.base.utils;

import java.util.Date;

import com.ibm.icu.text.SimpleDateFormat;

public class DateUtil2 {
	public static final SimpleDateFormat yyyy_MM_dd=new SimpleDateFormat("yyyy-MM-dd");
	
	public static final SimpleDateFormat HH_mm_ss=new SimpleDateFormat("HH:mm:ss");
	
	public static final SimpleDateFormat yyyy_MM_dd_HH_mm_ss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static String format_yyyy_MM_dd(Date date){
		return yyyy_MM_dd.format(date);
	}
	
	public static String format_HH_mm_ss(Date date){
		return HH_mm_ss.format(date);
	}
	
	public static String format_yyyy_MM_dd_HH_mm_ss(Date date){
		return yyyy_MM_dd_HH_mm_ss.format(date);
	}
}
