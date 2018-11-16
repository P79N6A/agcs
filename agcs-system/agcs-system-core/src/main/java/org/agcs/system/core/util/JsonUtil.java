package org.agcs.system.core.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONStringer;

import net.sf.json.JSONObject;

public class JsonUtil {

	/**
	 * @param obj 任意对象
	 * @return String
	 */
	public static String object2json(Object obj) throws Exception {
		if (obj == null) {
			return null;
		}
		if (obj instanceof String || obj instanceof Byte){
				return "\""+string2json(obj.toString())+"\"";
		} 
		if(obj instanceof Integer
				|| obj instanceof Float || obj instanceof Boolean
				|| obj instanceof Short || obj instanceof Double
				|| obj instanceof Long || obj instanceof BigDecimal
				|| obj instanceof BigInteger || obj instanceof Date){
			return string2json(obj.toString());
		}
		String jsonString = JSONHelper.toJSON(obj);
		return jsonString;
	}

	/**
	 * @param bean  bean对象
	 * @return String
	 */
	public static String bean2json(Object bean) throws Exception {
		StringBuilder json = new StringBuilder();
		json.append("{");
		PropertyDescriptor[] props = null;
		try {
			props = Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors();
			if (props != null) {
				for (int i = 0; i < props.length; i++) {
					try {
						String name = object2json(props[i].getName());
						String value = object2json(props[i].getReadMethod().invoke(bean));
						json.append(name);
						json.append(":");
						json.append(value);
						json.append(",");
					} catch (Exception e) {
					}
				}
				json.setCharAt(json.length() - 1, '}');
			} else {
				json.append("}");
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
			throw e;
		}
		
		return json.toString();
	}

	/**
	 * @param list
	 *            list对象
	 * @return String
	 */
	public static String list2json(List<?> list) throws Exception {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (list != null && list.size() > 0) {
			for (Object obj : list) {
				json.append(object2json(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}

	/**
	 * @param array  对象数组
	 * @return String
	 */
	public static String array2json(Object[] array) throws Exception {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (array != null && array.length > 0) {
			for (Object obj : array) {
				json.append(object2json(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}

	/**
	 * @param map  map对象
	 * @return String
	 */
	public static String map2json(Map<?, ?> map) throws Exception {
		StringBuilder json = new StringBuilder();
		json.append("{");
		if (map != null && map.size() > 0) {
			for (Object key : map.keySet()) {
				json.append(object2json(key));
				json.append(":");
				json.append(object2json(map.get(key)));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, '}');
		} else {
			json.append("}");
		}
		return json.toString();
	}

	/**
	 * @param set  集合对象
	 * @return String
	 */
	public static String set2json(Set<?> set) throws Exception {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (set != null && set.size() > 0) {
			for (Object obj : set) {
				json.append(object2json(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}

	/**
	 * @param s 参数
	 * @return String
	 */
	public static String string2json(String s) throws Exception {
		if (s == null)
			return "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			switch (ch) {
			case '"':
				sb.append("\\\"");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\t':
				sb.append("\\t");
				break;
			case '/':
				sb.append("\\/");
				break;
			default:
				if (ch >= '\u0000' && ch <= '\u001F') {
					String ss = Integer.toHexString(ch);
					sb.append("\\u");
					for (int k = 0; k < 4 - ss.length(); k++) {
						sb.append('0');
					}
					sb.append(ss.toUpperCase());
				} else {
					sb.append(ch);
				}
			}
		}
		return sb.toString();
	}
	
	@SuppressWarnings("rawtypes")
	public static String listToJson(List list, int total) throws Exception{
		JSONObject jobj = new JSONObject();//new一个JSON  
		jobj.accumulate("total",total );//total代表一共有多少数据  
        jobj.accumulate("rows", list);//row是代表显示的页的数据  
        return jobj.toString();
	}
	
	
	public static <T> T parseJson2Obj(String jsonStr, Class<T> c) {
		if (jsonStr == null) {
			return null;
		}
		T t = null;
		try {
			t = JSONHelper.parseObject(jsonStr, c);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(object2json(1.5));
		System.out.println(object2json("sss"));
	}
	
}
