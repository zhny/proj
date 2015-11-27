package cn.zhny.base.utils;

import java.util.Iterator;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * 错误处理工具
 * @author zhny
 *
 */
public class ErrorUtil {
	
	
	/**
	 * 捕获校验错误文字信息
	 * @param bindResult 
	 * @return
	 */
	public static String pickErrorMsg(BindingResult bindResult){
		List<ObjectError> errors=bindResult.getAllErrors();
		StringBuilder result=new StringBuilder();
		for (Iterator<ObjectError> iterator = errors.iterator(); iterator.hasNext();) {
			ObjectError objectError = iterator.next();
			result.append(objectError.getDefaultMessage()).append("\n");
		}
		return result.toString();
	}
}
