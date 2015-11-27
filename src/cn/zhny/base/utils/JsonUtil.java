package cn.zhny.base.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {

	public static final Gson gson = new GsonBuilder()
			.enableComplexMapKeySerialization().create();

	public static String toJson(Object o) {
		return gson.toJson(o);
	}

	public static <T> T toObj(String json, Class<T> classOfT) {
		return gson.fromJson(json, classOfT);
	}

	public static void toJson(Object o, HttpServletResponse response) {
		//response.reset();
		if(response==null)return;
		if(!("text/plain;charset=utf-8").equals(response.getContentType())){
			response.setContentType("application/json;charset=utf-8");
		}
		try {
			PrintWriter writer = response.getWriter();
			String s=toJson(o);
			writer.write(s);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void anotherJson(Object o, HttpServletResponse response){
		try {
			PrintWriter writer = response.getWriter();
			String s=toJson(o);
			writer.write(s);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
