package org.agcs.system.hibernate.pbc.impl;

import org.agcs.system.hibernate.qbc.CriteriaQuery;
import org.agcs.system.hibernate.qbc.IDataTypeParse;

public class StringParseImpl implements IDataTypeParse {
	
	//in查询分隔符
	private static final String SUFFIX_SPLIT = ",";
	private static final String SUFFIX_LIKE = "*";
	private static final String SUFFIX_LIKE_REP = "%";
	private static final String SUFFIX_NOT_EQ = "!";
	private static final String SUFFIX_NOT_EQ_NULL = "!NULL";
	

	public void addCriteria(CriteriaQuery cq, String name, Object value) {
		String searchValue = null;
		if(value != null && (searchValue = value.toString().trim()) != ""){
			if(searchValue.indexOf(SUFFIX_SPLIT) >= 0){
				//in 多个条件查询(逗号隔开参数)
				String[] vals = searchValue.split(SUFFIX_SPLIT);
				cq.in(name, vals);
			}else if(searchValue.indexOf(SUFFIX_LIKE) >= 0){
				//模糊查询（带有*的参数）
				cq.like(name, searchValue.replace(SUFFIX_LIKE, SUFFIX_LIKE_REP));
			}else if(searchValue.equals(SUFFIX_NOT_EQ) || searchValue.toUpperCase().equals(SUFFIX_NOT_EQ_NULL)){
				//不为空查询
				cq.isNotNull(name);
			}else if(searchValue.indexOf(SUFFIX_NOT_EQ) >= 0){
				cq.notEq(name, searchValue.replace(SUFFIX_NOT_EQ, ""));
			}else{
				cq.like(name, "%"+searchValue+"%");
			}
		}

	}

	public void addCriteria(CriteriaQuery cq, String name, Object value,
			String beginValue, String endValue) {
		addCriteria(cq,name,value);
	}

}
