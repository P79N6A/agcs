package org.agcs.system.hibernate.antidao.dao.impl;

import java.io.Serializable;
import java.util.List;
import org.agcs.system.hibernate.antidao.dao.IAntiBaseDao;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Component
@Transactional
public class AntiBaseDaoImpl<T, PK extends Serializable> implements IAntiBaseDao {
	private static final Logger log = Logger.getLogger(AntiBaseDaoImpl.class);
	private SessionFactory sessionFactory;
	
	public Session getSession(){
	    try{
	    	return this.sessionFactory.getCurrentSession(); 
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return this.sessionFactory.openSession();
	}
	
	public <T> void save(T entity){
	    try{
	      getSession().save(entity);
	      getSession().flush();
	      if (log.isDebugEnabled()){
	    	  log.debug("保存实体成功," + entity.getClass().getName());
	      }
	    }catch (RuntimeException e) {
	    	log.error("保存实体异常", e);
	    	throw e;
	    }
	}
	
	public <T> void saveOrUpdate(T entity){
	    try{
	      getSession().saveOrUpdate(entity);
	      getSession().flush();
	      if (log.isDebugEnabled()){
	    	  log.debug("添加或更新成功," + entity.getClass().getName());
	      }
	    }catch (RuntimeException e) {
	    	log.error("添加或更新异常", e);
	    	throw e;
	    }
	}

	public <T> void delete(T entity){
	    try{
	      getSession().delete(entity);
	      getSession().flush();
	      if (log.isDebugEnabled()){
	    	  log.debug("删除成功," + entity.getClass().getName());
	      }
	    }catch (RuntimeException e) {
	    	log.error("删除异常", e);
	      throw e;
	    }
	}

	public <T> T findUniqueByProperty(Class<T> entityClass, String propertyName, Object value){
	    Assert.hasText(propertyName);
	    return (T) createCriteria(entityClass, new Criterion[] { Restrictions.eq(propertyName, value) }).uniqueResult();
	}

	private <T> Criteria createCriteria(Class<T> entityClass, boolean isAsc, Criterion[] criterions) {
	    Criteria criteria = createCriteria(entityClass, criterions);
	    if (isAsc)
	      criteria.addOrder(Order.asc("asc"));
	    else {
	      criteria.addOrder(Order.desc("desc"));
	    }
	    return criteria;
	}

	private <T> Criteria createCriteria(Class<T> entityClass, Criterion[] criterions){
	    Criteria criteria = getSession().createCriteria(entityClass);
	    for (Criterion c : criterions) {
	      criteria.add(c);
	    }
	    return criteria;
	}

	public <T> List<T> findByProperty(Class<T> entityClass, String propertyName, Object value){
	    Assert.hasText(propertyName);
	    return createCriteria(entityClass, new Criterion[] { Restrictions.eq(propertyName, value) }).list();
	}

	public <T> T get(Class<T> entityClass, Serializable id){
	    return (T) getSession().get(entityClass, id);
	}

	public <T> T get(T entitie){
	    Criteria executableCriteria = getSession().createCriteria(entitie.getClass());
	    executableCriteria.add(Example.create(entitie));
	    if (executableCriteria.list().size() == 0) {
	      return null;
	    }
	    return (T) executableCriteria.list().get(0);
	}

	public <T> List<T> loadAll(T entitie){
	    Criteria executableCriteria = getSession().createCriteria(entitie.getClass());
	    executableCriteria.add(Example.create(entitie));
	    return executableCriteria.list();
	}

	public <T> void deleteEntityById(Class entityName, Serializable id){
	    delete(get(entityName, id));
	    getSession().flush();
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
}
