package org.agcs.system.hibernate.dao;

public interface ICommonDao extends IBaseDao {
	
	public <T> void addLog(T entity);
	
}
