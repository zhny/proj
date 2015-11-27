package cn.zhny.base.utils;

import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;






public class SecurityUtil {
	
	private static Logger log = Logger.getLogger(SecurityUtil.class);
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
	/**
	 * 将给定字符串经MD5加密
	 * @param s
	 * @return
	 */
	public static String md5(String s,Charset charSet) {
		StringBuffer sb = new StringBuffer("");
		try {
			byte[] btInput = null;
			if(null == charSet){
				btInput = s.getBytes(Charset.forName("utf-8"));
			}else{
				btInput = s.getBytes(charSet);
			}
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			sb = new StringBuffer();
			for (int i = 0; i < md.length; i++) {
				int val = ((int) md[i]) & 0xff;
				if (val < 16)
					sb.append("0");
				sb.append(Integer.toHexString(val));
			}
		} catch (Exception e) {
			log.error(e);
		}
		return sb.toString();
	}
	
	/**
	 * 生成ipop 平台消息签名
	 * @param appkey ipop分配
	 * @param createtime 时间yyyyMMddHHmmss
	 * @param salt 6位数字或字母
	 * @return 签名字符串
	 */
	public static String ipop_signature(String appkey,String createtime,String salt){
		byte[] res = new byte[0];
		String[] strs = {appkey,createtime,salt};
		Arrays.sort(strs);
		StringBuilder buf = new StringBuilder();
		for(String str : strs){
			buf.append(str);
		}
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(buf.toString().getBytes());
			res = md.digest();
		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage(), e);
		}

		return new String(Base64.base64Encode(res));
	}
	/**
	 * 配合ipop平台的AES加密
	 * @param content 原文
	 * @param appkey 32位的密钥
	 * @return 密文
	 */
	public static String ipop_aesEncoder(String content,String appkey){
		byte[] res = null;
		res = aes(content.getBytes(),appkey.substring(0, 16).getBytes(),appkey.substring(16).getBytes(),Cipher.ENCRYPT_MODE);
		return new String(Base64.base64Encode(res));
	}
	/**
	 * 
	 * @param content
	 * @param appkey
	 * @return
	 */
	public static String ipop_aesDecoder(String content,String appkey){
		byte[] res = null;
		res = Base64.base64Decode(content.getBytes());
		res = aes(res,appkey.substring(0, 16).getBytes(),appkey.substring(16).getBytes(),Cipher.DECRYPT_MODE);
		return new String(res);
	}
	/**
	 * AES加密，模式和方案:CBC/PKCS5Padding
	 * @param src_data 原文
	 * @param key 密钥
	 * @param iv_data 向量
	 * @param type 类型（加密|解密）
	 * @return
	 */
	private static byte[] aes(byte[] src_data,byte[] key,byte[] iv_data,int type){
		byte[] data = new byte[0];
		SecretKeySpec sks = new SecretKeySpec(key, "AES");
		IvParameterSpec iv = new IvParameterSpec(iv_data);
			//AES/CBC/PKCS5Padding
		try {
				Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
				cipher.init(type, sks, iv);
				data = cipher.doFinal(src_data);				
		} catch (NoSuchAlgorithmException e) {
			log.error("",e);
		} catch (NoSuchPaddingException e) {
			log.error("",e);
		} catch (InvalidKeyException e) {
			log.error("",e);
		} catch (IllegalBlockSizeException e) {
			log.error("",e);
		} catch (BadPaddingException e) {
			log.error("",e);
		} catch (InvalidAlgorithmParameterException e) {
			log.error("",e);
		}
		return data;
	}
	/**
	 * 按ipop要求对id进行加密
	 * @param id
	 * @param key
	 * @return
	 */
	public static String createOpenId(String id,String key){
		try {
			SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cip = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cip.init(Cipher.ENCRYPT_MODE, skey);
			byte[] data = cip.doFinal(id.getBytes());
			return new String(Base64.base64Encode(data));
		} catch (NoSuchAlgorithmException e) {
			log.error("", e);
		} catch (NoSuchPaddingException e) {
			log.error("", e);
		} catch (InvalidKeyException e) {
			log.error("", e);
		} catch (IllegalBlockSizeException e) {
			log.error("", e);
		} catch (BadPaddingException e) {
			log.error("", e);
		}
		return null;
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
	/**
	 * 生成指定位数的由数字组成的随机数
	 * @param len 位数，最长不超过20位
	 * @return
	 */
	public static String getRandomDigitStr(int len){
		StringBuilder buf = new StringBuilder();
		Random ran = new Random();
		if(len<=0 || len >= 20){
			len=6;
		}
		for(int i=0;i<len;i++){
			buf.append(seed[ran.nextInt(10)]);
		}
		return buf.toString();
	}
	
//	public static void main(String[] args) {
//		String res = SecurityUtil.createOpenId("4205", "cOm2013Zf13e135a");
//		System.out.println(res);
//	}
}
