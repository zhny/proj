package cn.zhny.base.utils;

import java.util.Random;

import org.apache.log4j.Logger;

public class StringTools {
	private static String[] seed;
	static{
		seed = new String[62];
		int index = 0;
		for(int i=0;i<10;i++){
			seed[index++] = String.valueOf(i); 
		}
		for(int i=65;i<91;i++){
			seed[index++] = String.valueOf((char)i);
		}
		for(int i=97;i<123;i++){
			seed[index++] = String.valueOf((char)i);
		}
		
	}

	private static Logger log = Logger.getLogger(StringTools.class);
	public static int strToInt(String str,int def){
		if(str == null){
			return def;
		}
		int res = def;
		try{
			res = Integer.parseInt(str);
		}catch(NumberFormatException e){
			log.error(e.getMessage(), e);
		}
		return res;
	}
	public static double steToDouble(String str,double def){
		if(str == null){
			return def;
		}
		double res = def;
		try{
			res = Double.parseDouble(str);
		}catch(NumberFormatException e){
			log.error(e.getMessage(), e);
		}
		return res;
	}
	public static String makeSqlSafe(String unsafeString) {
        return unsafeString.replaceAll("'","''");
    }
	
	public static boolean isEmpty(String str){
		return (str == null || str.length() == 0);
	}

	/**
	 * 生成指定位数的随机数字符串，包含数字和大小写字母
	 * @param len 位数，最长不超过20位
	 * @return
	 */
	public static String getRandomStr(int len){
		StringBuilder buf = new StringBuilder();
		Random ran = new Random();
		if(len<=0 || len >= 20){
			len=6;
		}
		for(int i=0;i<len;i++){
			buf.append(seed[ran.nextInt(62)]);
		}
		return buf.toString();
	}
}
