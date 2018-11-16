package org.agcs.system.hibernate.dao.impl;

import org.agcs.system.core.util.PropertiesHelper;
import org.agcs.system.hibernate.dao.ICommonDao;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Repository
public class CommonDaoImpl extends BaseDaoImpl implements ICommonDao {
	
	private static final Logger log = Logger.getLogger(CommonDaoImpl.class);
	private static String islog = PropertiesHelper.getInstance().getProperty("islog");
	
	public <T> void addLog(T entity){
		try {
			if("true".equals(islog)){
				getSession().save(entity);
				getSession().flush();
				if(log.isDebugEnabled()){
					log.debug("保存日志成功," + entity.getClass().getName());
				}
			}
		} catch (RuntimeException e) {
			log.error("保存日志异常", e);
			throw e;
		}
	}
	
}
