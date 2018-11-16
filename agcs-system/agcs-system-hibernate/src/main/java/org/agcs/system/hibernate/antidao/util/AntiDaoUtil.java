package org.agcs.system.hibernate.antidao.util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class AntiDaoUtil {
	
	public static String getFirstSmall(String name){
		name = name.trim();
		if(name.length() >= 2){
			return name.substring(0, 1).toLowerCase()+name.substring(1);
		}
		return name.toLowerCase();
	}
	
	public static boolean isAbstract(Method method){
		int mod = method.getModifiers();
		return Modifier.isAbstract(mod);
	}
	
	public static boolean isWrapClass(Class clz){
	    try{
	      return ((Class)clz.getField("TYPE").get(null)).isPrimitive(); 
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return false;
	}

}
