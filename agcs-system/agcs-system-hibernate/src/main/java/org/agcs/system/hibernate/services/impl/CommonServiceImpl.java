package org.agcs.system.hibernate.services.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.agcs.system.hibernate.dao.ICommonDao;
import org.agcs.system.hibernate.pojo.DataGridReturn;
import org.agcs.system.hibernate.qbc.CriteriaQuery;
import org.agcs.system.hibernate.services.CommonServiceI;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("commonService")
@Transactional
public class CommonServiceImpl<T, PK extends Serializable> implements CommonServiceI{
	
	private ICommonDao commonDao;
	
	public ICommonDao getCommonDao() {
		return commonDao;
	}
	@Resource
	public void setCommonDao(ICommonDao commonDao) {
		this.commonDao = commonDao;
	}
	
	public Session getSession(){
		return commonDao.getSession();
	}

	public <T> void save(HttpServletRequest request, T entity) {
		this.commonDao.save(entity);
	}
	
	public <T> void addLog(T entity){
		this.commonDao.addLog(entity);
	}
	
	public <T> void batchSaveList(List<T> entitys){
		this.commonDao.batchSaveList(entitys);
	}

	public <T> void saveOrUpdate(HttpServletRequest request, T entity) {
		this.commonDao.saveOrUpdate(entity);
	}
	
	public <T> void delete(T entity){
		this.commonDao.delete(entity);
	}
	
	public void delete(HttpServletRequest request, Class<?> c, String ids) {
		String[] idarr = ids.split(",");
		for(String id : idarr){
			this.commonDao.deleteById(c, id);
		}
	}
	
	public <T> void deleteAllEntity(Collection<T> entities){
		this.commonDao.deleteAllEntity(entities);
	}
	
	public <T> T update(T entity){
		return this.commonDao.update(entity);
	}
	
	public <T> T updateById(Class entityName, Serializable id){
		return this.commonDao.updateById(entityName, id);
	}
	
	public Integer executeHql(String hql){
		return this.commonDao.executeHql(hql);
	}
	
	public int updateBySqlString(String sql){
		return this.commonDao.updateBySqlString(sql);
	}
	
	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> T getEntity(Class<?> c, Serializable id) {
		return (T) this.commonDao.get(c, id);
	}
	
	public <T> T findUniqueByProperty(Class<T> entityClass, String propertyName, Object value){
		return this.commonDao.findUniqueByProperty(entityClass, propertyName, value);
	}
	
	public <T> List<T> findByProperty(Class<T> entityClass, String propertyName, Object vlaue){
		return this.commonDao.findByProperty(entityClass, propertyName, vlaue);
	}
	
	public <T> List<T> loadAll(final Class<T> entityClass){
		return this.commonDao.loadAll(entityClass);
	}
	
	public <T> List<T> findByHQl(String hql, Object... param){
		return this.commonDao.findByHQl(hql, param);
	}
	
	public <T> List<T> findByHQl(String hql, Map<String, Object> param){
		return this.commonDao.findByHQl(hql, param);
	}
	
	public <T> T singleResult(String hql){
		return this.commonDao.singleResult(hql);
	}
	
	public <T> List<T> findBySql(String sql, Object... param){
		return this.commonDao.findBySql(sql, param);
	}
	
	public <T> List<T> findBySql(final Class clasz, String sql){
		return this.commonDao.findBySql(clasz, sql);
	}
	
	public <T> List<T> findBySql(final Class clasz, String sql, Object... param){
		return this.commonDao.findBySql(clasz, sql, param);
	}
	
	public <T> List<T> findByPropertyisOrder(Class<T> entityClass, String propertyName, Object value, boolean isAsc){
		return this.commonDao.findByPropertyisOrder(entityClass, propertyName, value, isAsc);
	}
	
	public List<Map<String, Object>> findForJdbc(String sql, Object... objs){
		return this.commonDao.findForJdbc(sql, objs);
	}
	
	public Map<String, Object> findOneForJdbc(String sql, Object... objs){
		return this.commonDao.findOneForJdbc(sql, objs);
	}
	
	public Long getRepositoryByHQLCount(String hql, Object... param) throws Exception{
		return this.commonDao.getRepositoryByHQLCount(hql, param);
	}
	
	public Long getCountForJdbc(String sql, Object... param) throws Exception{
		return this.commonDao.getCountForJdbc(sql, param);
	}
	
	public <T> List<T> findObjForJdbc(String sql, int page, int rows, Class<T> clazz){
		return this.commonDao.findObjForJdbc(sql, page, rows, clazz);
	}
	
	public <T> List<T> findObjForJdbc(String sql, int page, int rows, Class<T> clazz, Object... objs){
		return this.commonDao.findObjForJdbc(sql, page, rows, clazz, objs);
	}
	
	public List<Map<String, Object>> findSqlForJdbc(String sql, int page, int rows, Object... objs){
		return this.commonDao.findSqlForJdbc(sql, page, rows, objs);
	}
	
	public <T> List<T> findHQLByPage(final String hql, final int offset, final int pageSize){
		return this.commonDao.findHQLByPage(hql, offset, pageSize);
	}
	
	public <T> List<T> findHQLByPage(final String hql, final int offset, final int pageSize, final Map<String, Object> map){
		return this.commonDao.findHQLByPage(hql, offset, pageSize, map);
	}
	
	public <T> List<T> findHQLByPage(final String hql, final int offset, final int pageSize, final Object[] values){
		return this.commonDao.findHQLByPage(hql, offset, pageSize, values);
	}
	
	public Integer executeSql(String sql, List<Object> param){
		return this.commonDao.executeSql(sql, param);
	}
	
	public Integer executeSql(String sql, Object... param){
		return this.commonDao.executeSql(sql, param);
	}
	
	public Integer executeSql(String sql, Map<String, Object> param){
		return this.commonDao.executeSql(sql, param);
	}
	
	public Object executeSqlReturnKey(String sql, Map<String, Object> param){
		return this.commonDao.executeSqlReturnKey(sql, param);
	}
	
	public DataGridReturn datagridPage(CriteriaQuery cq, boolean isPage) {
		return this.commonDao.getCriteriaQueryList(cq, isPage);
	}
	
}
