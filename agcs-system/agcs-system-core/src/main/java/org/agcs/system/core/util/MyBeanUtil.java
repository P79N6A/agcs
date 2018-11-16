package org.agcs.system.core.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;

/**
 * @Title: MyBeanUtil.java
 * @Package org.braveframwork.core.util
 * @Description: bean工具类
 * @author vivian
 * @date 2016-1-18 下午3:22:59
 * @version V1.0
 */
public class MyBeanUtil extends BeanUtils {

	@SuppressWarnings("rawtypes")
	private static void convert(Object dest, Object orig)
			throws IllegalAccessException, InvocationTargetException {

		// Validate existence of the specified beans
		if (dest == null) {
			throw new IllegalArgumentException("No destination bean specified");
		}
		if (orig == null) {
			throw new IllegalArgumentException("No origin bean specified");
		}

		// Copy the properties, converting as necessary
		if (orig instanceof DynaBean) {
			DynaProperty origDescriptors[] = ((DynaBean) orig).getDynaClass()
					.getDynaProperties();
			for (int i = 0; i < origDescriptors.length; i++) {
				String name = origDescriptors[i].getName();
				if (PropertyUtils.isWriteable(dest, name)) {
					Object value = ((DynaBean) orig).get(name);
					try {
						copyProperty(dest, name, value);
					} catch (Exception e) {
						; // Should not happen
					}

				}
			}
		} else if (orig instanceof Map) {
			Iterator names = ((Map) orig).keySet().iterator();
			while (names.hasNext()) {
				String name = (String) names.next();
				if (PropertyUtils.isWriteable(dest, name)) {
					Object value = ((Map) orig).get(name);
					try {
						copyProperty(dest, name, value);
					} catch (Exception e) {
						; // Should not happen
					}

				}
			}
		} else
		/* if (orig is a standard JavaBean) */
		{
			PropertyDescriptor origDescriptors[] = PropertyUtils
					.getPropertyDescriptors(orig);
			for (int i = 0; i < origDescriptors.length; i++) {
				String name = origDescriptors[i].getName();
				// String type =
				// origDescriptors[i].getPropertyType().toString();
				if ("class".equals(name)) {
					continue; // No point in trying to set an object's class
				}
				if (PropertyUtils.isReadable(orig, name)
						&& PropertyUtils.isWriteable(dest, name)) {
					try {
						Object value = PropertyUtils.getSimpleProperty(orig,
								name);
						copyProperty(dest, name, value);
					} catch (java.lang.IllegalArgumentException ie) {
						; // Should not happen
					} catch (Exception e) {
						; // Should not happen
					}

				}
			}
		}

	}

	/**
	 * 对象拷贝 数据对象空值不拷贝到目标对象
	 * 
	 * @param dataObject
	 * @param toObject
	 * @throws NoSuchMethodException
	 *             copy
	 */
	public static void copyBeanNotNull2Bean(Object databean, Object tobean)
			throws Exception {
		PropertyDescriptor origDescriptors[] = PropertyUtils
				.getPropertyDescriptors(databean);
		for (int i = 0; i < origDescriptors.length; i++) {
			String name = origDescriptors[i].getName();
			// String type = origDescriptors[i].getPropertyType().toString();
			if ("class".equals(name)) {
				continue; // No point in trying to set an object's class
			}
			if (PropertyUtils.isReadable(databean, name)
					&& PropertyUtils.isWriteable(tobean, name)) {
				try {
					Object value = PropertyUtils.getSimpleProperty(databean,
							name);
					if (value != null) {
						copyProperty(tobean, name, value);
					}
				} catch (java.lang.IllegalArgumentException ie) {
					; // Should not happen
				} catch (Exception e) {
					; // Should not happen
				}

			}
		}
	}

	/**
	 * 把orig和dest相同属性的value复制到dest中
	 * 
	 * @param dest
	 * @param orig
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void copyBean2Bean(Object dest, Object orig) throws Exception {
		convert(dest, orig);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void copyBean2Map(Map map, Object bean) {
		PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(bean);
		for (int i = 0; i < pds.length; i++) {
			PropertyDescriptor pd = pds[i];
			String propname = pd.getName();
			try {
				Object propvalue = PropertyUtils.getSimpleProperty(bean,
						propname);
				map.put(propname, propvalue);
			} catch (IllegalAccessException e) {
				// e.printStackTrace();
			} catch (InvocationTargetException e) {
				// e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// e.printStackTrace();
			}
		}
	}

	/**
	 * map对象复制到bean中
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("rawtypes")
	public static void copyMap2Bean(Object bean, Map map)
			throws IllegalAccessException, InvocationTargetException {
		if (bean == null || map == null) {
			return;
		}
		Iterator iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String name = (String) iterator.next();
			if (name == null) {
				continue;
			}
			Object value = map.get(name);
			try {
				Class clazz = PropertyUtils.getPropertyType(bean, name);
				if (null == clazz) {
					continue;
				}
				String className = clazz.getName();
				if ("java.sql.Timestamp".equalsIgnoreCase(className)) {
					if (value == null || value.equals("")) {
						continue;
					}
				}
				setProperty(bean, name, value);
			} catch (NoSuchMethodException e) {
				continue;
			}
		}
	}

}
