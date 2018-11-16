package org.agcs.system.hibernate.qbc;

import java.util.ArrayList;

import org.hibernate.criterion.Criterion;

/**
* @Title: CriterionList.java 
* @Package org.braveframwork.hibernate.qbc 
* @Description:  qbc查询条件集合
* @author vivian
* @date 2016-1-27 下午2:41:35 
* @version V1.0
 */
public class CriterionList extends ArrayList<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5277183213057501490L;
	
	public final Criterion getParas(final int index){
		return (Criterion) super.get(index);
	}
	
	public final void addPara(final int index, final Criterion c){
		super.add(index, c);
	}
	
	public final void addPara(final Criterion c){
		super.add(c);
	}
	
	public final int indexOfPara(final Criterion c){
		return super.indexOf(c);
	}
	
	public final void removePara(final int index) {
		super.remove(index);
	}
}
