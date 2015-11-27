package cn.zhny.base.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.zhny.base.vo.Jo;

/**
 * 手机api数据渲染工具
 * @author zhny
 *
 * @param <T>
 */
public class ApiRender {

	private static final Logger log = LoggerFactory.getLogger(ApiRender.class);
	
	public static Jo renderList(Object data,Map<String,Object> otherData,String returnName, String... mapping) {
		List<?> list=(List<?>) data;
		if (mapping.length % 2 != 0) {
			return new Jo(Jo.CODE_FAIL, "参数映射不正确");
		}
		try {
			if(list==null||list.isEmpty()){
				return new Jo();
			}
			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
				Object object = iterator.next();
				Map<String, Object> row = new HashMap<String, Object>();
				for (int i = 0; i < mapping.length; i += 2) {
					Method method=object.getClass().getMethod(getGetMethod(mapping[i + 1]));
					if(method==null)continue;
					Object fieldValue=method.invoke(object);
					if(fieldValue instanceof List){
						Jo subJo=renderSubList(fieldValue,mapping);
						if(subJo.getCode()==Jo.CODE_FAIL){
							return subJo;
						}else{
							row.put(mapping[i],subJo.getData());
						}
					}else{
						row.put(mapping[i],fieldValue);
					}
				}
				resultList.add(row);
			}
			Map<String, Object> result = new HashMap<String, Object>();
			result.putAll(otherData);
			result.put(returnName, resultList);
			return new Jo(result);
		} catch (Exception e) {
			log.error("渲染异常", e);
			return new Jo(Jo.CODE_FAIL,"数据加载异常");
		} 
	}
	
	/**
	 * 渲染List中的数据对象到指定格式的返回数据，主要用于给外部提供接口时字段名称的映射
	 * @param data			List  类型的数据
	 * @param returnName	返回的Data数据中List数据的名称
	 * @param mapping		原字段到返回字段的名称映射，需一一对应
	 * @return
	 */
	public static Jo renderList(Object data, String returnName, String... mapping) {
		List<?> list=(List<?>) data;
		if (mapping.length % 2 != 0) {
			return new Jo(Jo.CODE_FAIL, "参数映射不正确");
		}
		try {
			if(list==null||list.isEmpty()){
				return new Jo();
			}
			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
				Object object = iterator.next();
				Map<String, Object> row = new HashMap<String, Object>();
				for (int i = 0; i < mapping.length; i += 2) {
					Method method=object.getClass().getMethod(getGetMethod(mapping[i + 1]));
					if(method==null)continue;
					Object fieldValue=method.invoke(object);
					if(fieldValue instanceof List){
						Jo subJo=renderSubList(fieldValue,mapping);
						if(subJo.getCode()==Jo.CODE_FAIL){
							return subJo;
						}else{
							row.put(mapping[i],subJo.getData());
						}
					}else{
						row.put(mapping[i],fieldValue);
					}
				}
				resultList.add(row);
			}
			Map<String, Object> result = new HashMap<String, Object>();
			result.put(returnName, resultList);
			return new Jo(result);
		} catch (Exception e) {
			log.error("渲染异常", e);
			return new Jo(Jo.CODE_FAIL,"数据加载异常");
		} 
	}
	
	public static Jo renderSubList(Object data, String... mapping) {
		List<?> list=(List<?>) data;
		if (mapping.length % 2 != 0) {
			return new Jo(Jo.CODE_FAIL, "参数映射不正确");
		}
		try {
			if(list==null||list.isEmpty()){
				return new Jo();
			}
			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
				Object object = iterator.next();
				Map<String, Object> row = new HashMap<String, Object>();
				for (int i = 0; i < mapping.length; i += 2) {
					Method method=object.getClass().getMethod(getGetMethod(mapping[i + 1]));
					if(method==null)continue;
					Object fieldValue=method.invoke(object);
					row.put(mapping[i],fieldValue);
				}
				resultList.add(row);
			}
			return new Jo(resultList);
		} catch (Exception e) {
			log.error("渲染异常", e);
			return new Jo(Jo.CODE_FAIL,"数据加载异常");
		} 
	}
	

	public static String getGetMethod(String name) {
		return "get"+name.toUpperCase().substring(0, 1) + name.substring(1);
	}
	/**
	 * 将实体的属性值映射到map
	 * @param map  存放值的map
	 * @param data 实体对象
	 * @param exclude 要排除的属性
	 */
	public static void renderMap(Map<String,Object> map,Object data,List<String> exclude,boolean excludeNull){
		
//		Annotation[] ants = data.getClass().getAnnotations();
//		boolean isEntity = false;
//		for(Annotation ant : ants){
//			if(ant.annotationType()==Entity.class){
//				isEntity = true;
//			}
//		}
//		if(!isEntity){
//			log.info("not entity ,return");
//			return;
//		}
		if(data == null || map == null){
			return;
		}
		Method[] methods = data.getClass().getMethods();
		Map<String,Method> getMethods = new HashMap<String,Method>();
		if(exclude == null){
			exclude = new ArrayList<String>();
		}
		//exclude.add("class");//特别排除
		for(Method m:methods){
		
			String name = m.getName();
			if(name.startsWith("get") && name.length()>3){
				String field = name.substring(3);
				field = field.substring(0, 1).toLowerCase()+field.substring(1);
				if(!exclude.contains(field)){				
					getMethods.put(field, m);
					
				}
			}
		}
		Iterator<Map.Entry<String, Method>> itr = getMethods.entrySet().iterator();
		while(itr.hasNext()){
			Map.Entry<String, Method> ent = itr.next();
			try {
				Object val = ent.getValue().invoke(data);
				if(excludeNull){
					if(val != null){
						map.put(ent.getKey(), val);
					}
				}else{
					map.put(ent.getKey(), val);
				}
			} catch (Exception e) {
				log.error(e.getMessage(),e);
			}
		}
		
	}
}
