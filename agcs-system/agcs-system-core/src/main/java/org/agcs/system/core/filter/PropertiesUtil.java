package org.agcs.system.core.filter;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.agcs.system.core.util.StringUtil;

public class PropertiesUtil {
	
	public static Map readProperties(String path){
		Map map = new HashMap();
		Properties props = new Properties();
		try {
			File file = new File(path);
			InputStream in = new FileInputStream(file);
			props.load(in);
			Enumeration en = props.propertyNames();
			while(en.hasMoreElements()){
				String key = (String)en.nextElement();
				String value = props.getProperty(key);
				map.put(key, value);
			}
			//map.put("=", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public static String replaceCheck(Map map, String name){
		Set<String> keys = map.keySet();
		Iterator<String> iter = keys.iterator();
		if(!StringUtil.isEmpty(name)){
			while(iter.hasNext()){
				String key = iter.next();
				String value = (String)map.get(key);
				if(!StringUtil.isEmpty(key) && name.contains(key)){
					name=name.replace(key, value);//对于符合map中的key值实现替换功能
				}
			}
		}
		return name;
	}

}
