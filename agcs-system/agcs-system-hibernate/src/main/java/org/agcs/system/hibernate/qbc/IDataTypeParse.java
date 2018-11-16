package org.agcs.system.hibernate.qbc;

import org.agcs.system.hibernate.qbc.CriteriaQuery;

/**
* @Title: IDataTypeParse.java 
* @Package org.braveframwork.hibernate.qbc 
* @Description:  条件拼装接口
* @author vivian
* @date 2016-1-27 下午5:41:15 
* @version V1.0
 */
public interface IDataTypeParse {
	
	/**
	 * 单值拼装
	 * @param cq
	 * @param name
	 * @param value
	 */
	public void addCriteria(CriteriaQuery cq, String name, Object value);
	
	/**
	 * 范围值拼装
	 * @param cq
	 * @param name
	 * @param value
	 * @param beginValue
	 * @param endValue
	 */
	public void addCriteria(CriteriaQuery cq, String name, Object value, String beginValue, String endValue);

}
