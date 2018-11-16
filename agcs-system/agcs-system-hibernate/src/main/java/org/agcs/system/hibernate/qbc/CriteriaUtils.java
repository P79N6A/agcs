package org.agcs.system.hibernate.qbc;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.agcs.system.hibernate.qbc.CriteriaQuery;
import org.agcs.system.hibernate.qbc.IDataTypeParse;
import org.agcs.system.hibernate.pbc.impl.BigDecimalParseImpl;
import org.agcs.system.hibernate.pbc.impl.DoubleParseImpl;
import org.agcs.system.hibernate.pbc.impl.FloatParseImpl;
import org.agcs.system.hibernate.pbc.impl.IntegerParseImpl;
import org.agcs.system.hibernate.pbc.impl.LongParseImpl;
import org.agcs.system.hibernate.pbc.impl.ShortParseImpl;
import org.agcs.system.hibernate.pbc.impl.StringParseImpl;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

/**
* @Title: CriteriaUtils.java 
* @Package org.braveframwork.hibernate.qbc 
* @Description:  qbc查询构造器
* @author vivian
* @date 2016-1-27 下午2:18:13 
* @version V1.0
 */
public class CriteriaUtils {
	
	/** 时间查询符号 */
	private static final String END = "_end";
	private static final String BEGIN = "_begin";
	
	private static Map<String, IDataTypeParse> map = new HashMap<String, IDataTypeParse>();
	private static final SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private static final SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
	
	static{
		map.put("class java.lang.String", new StringParseImpl());
		map.put("class java.math.BigDecimal", new BigDecimalParseImpl());
		map.put("class java.lang.Integer", new IntegerParseImpl());
		map.put("class java.lang.Short", new ShortParseImpl());
		map.put("class java.lang.Long", new LongParseImpl());
		map.put("class java.lang.Double", new DoubleParseImpl());
		map.put("class java.lang.Float", new FloatParseImpl());
	}
	
	public static void installHql(CriteriaQuery cq, Object searchObj, Map<String, String[]> parameterMap){
		//构建对象
		installHqlJoinAlias(cq, searchObj, parameterMap, "");
		cq.add();
	}
	
	/**
	 * 添加别名、条件查询
	 * @param cq
	 * @param searchObj
	 * @param parameterMap
	 * @param alias
	 */
	public static void installHqlJoinAlias(CriteriaQuery cq, Object searchObj, Map<String, String[]> parameterMap, String alias){
		//获取对象所有属性
		PropertyDescriptor propertys[] = PropertyUtils.getPropertyDescriptors(searchObj);
		String aliasName, name, type;
		for(int i = 0; i < propertys.length; i++){
			aliasName = (StringUtils.isEmpty(alias) ? "":alias+".")+propertys[i].getName();
			name = propertys[i].getName();
			type = propertys[i].getPropertyType().toString();
			try {
				if(isSpecialField(name) || !PropertyUtils.isReadable(searchObj, name)){
					continue;
				}
				//判断是否有区间值
				String beginValue = null;
				String endValue = null;
				if(parameterMap != null && parameterMap.containsKey(name+BEGIN)){
					beginValue = parameterMap.get(name+BEGIN)[0].trim();
				}
				if(parameterMap != null && parameterMap.containsKey(name+END)){
					endValue = parameterMap.get(name+END)[0].trim();
				}
				Object value = PropertyUtils.getSimpleProperty(searchObj, name);
				//根据类型分类处理
				if(type.contains("class java.lang") || type.contains("class java.math")){
					if(parameterMap == null){
						map.get(type).addCriteria(cq, aliasName, value);
					}else{
						map.get(type).addCriteria(cq, aliasName, value, beginValue, endValue);
					}
				}else if(type.contains("class java.util.Date")){
					if(StringUtils.isNotBlank(beginValue)){
						if(beginValue.length() > 10){
							cq.ge(aliasName, time.parse(beginValue));
						}else{
							cq.ge(aliasName, day.parse(beginValue));
						}
					}
					if(StringUtils.isNotBlank(endValue)){
						if(beginValue.length() > 10){
							cq.le(aliasName, time.parse(endValue));
						}else{
							cq.le(aliasName, day.parse(endValue));
						}
					}
					if(isNotEmpty(value)){
						cq.eq(aliasName, value);
					}
					
				}else if(!isJavaClass(propertys[i].getPropertyType())){
					if(isNotEmpty(value) && isNotAllProperty(value)){
						//创建实体类别名,继续创建查询条件
						cq.createAlias(aliasName, aliasName);
						installHqlJoinAlias(cq, value, parameterMap, aliasName);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 判断是否是基本字段
	 * @param name
	 * @return
	 */
	private static boolean isSpecialField(String name) {
		return "class".equals(name) || "ids".equals(name) || "id".equals(name) ||
				"page".equals(name) || "rows".equals(name) || "sort".equals(name) ||
				"order".equals(name);
	}
	
	/**
	 * 判断是不是空
	 */
	public static boolean isNotEmpty(Object value) {
		boolean flag = true;
		if(value != null && !"".equals(value)){
			if(value.toString().length() > 0){
				flag = true;
			}
		}else{
			flag = false;
		}
		
		return flag;
	}
	
	/**
	 * 判断是否java基础类
	 * @param clazz
	 * @return
	 */
	private static boolean isJavaClass(Class<?> clazz){
		boolean isJavaClass = false;
		if(clazz.isArray()){
			isJavaClass = false;
		}else if(clazz.isPrimitive() || clazz.getPackage() == null ||
				clazz.getPackage().getName().equals("java.lang") ||
				clazz.getPackage().getName().equals("java.math") ||
				clazz.getPackage().getName().equals("java.util")){
			isJavaClass = true;
		}
		return isJavaClass;
	}
	
	private static boolean isNotAllProperty(Object value){
		boolean isAll = false;
		try {
			PropertyDescriptor propertys[] = PropertyUtils.getPropertyDescriptors(value);
			String name;
			for(int i = 0; i < propertys.length; i++){
				name = propertys[i].getName();
				if("class".equals(name) || !PropertyUtils.isReadable(value, name)){
					continue;
				}
				if(Map.class.isAssignableFrom(propertys[i].getPropertyType())){
					Map<?,?> map = (Map<?, ?>) PropertyUtils.getSimpleProperty(value, name);
					if(map != null && map.size() > 0){
						isAll = true;
						break;
					}
				}else if(Collection.class.isAssignableFrom(propertys[i].getPropertyType())){
					Collection<?> c = (Collection<?>) PropertyUtils.getSimpleProperty(value, name);
					if(c != null && c.size() > 0){
						isAll = true;
						break;
					}
				}else if(isNotEmpty(PropertyUtils.getSimpleProperty(value, name))){
					isAll = true;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return isAll;
	}

}
