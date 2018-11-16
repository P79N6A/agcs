package org.agcs.system.hibernate.pbc.impl;

import org.agcs.system.hibernate.qbc.CriteriaQuery;
import org.agcs.system.hibernate.qbc.CriteriaUtils;
import org.agcs.system.hibernate.qbc.IDataTypeParse;

public class ShortParseImpl implements IDataTypeParse {

	public void addCriteria(CriteriaQuery cq, String name, Object value) {
		if(CriteriaUtils.isNotEmpty(value)){
			cq.eq(name, value);
		}
	}

	public void addCriteria(CriteriaQuery cq, String name, Object value,
			String beginValue, String endValue) {
		if(CriteriaUtils.isNotEmpty(beginValue)){
			cq.ge(name, Short.parseShort(beginValue));
		}
		if(CriteriaUtils.isNotEmpty(endValue)){
			cq.le(name, Short.parseShort(endValue));
		}
		if(CriteriaUtils.isNotEmpty(value)){
			cq.eq(name, value);
		}
	}

}
