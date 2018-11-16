package org.agcs.system.hibernate.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.agcs.system.core.util.MyBeanUtil;
import org.agcs.system.hibernate.dao.IBaseDao;
import org.agcs.system.hibernate.pojo.DataGridReturn;
import org.agcs.system.hibernate.qbc.CriteriaQuery;
import org.agcs.system.hibernate.qbc.PageUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.Assert;

@SuppressWarnings("hiding")
public abstract class BaseDaoImpl<T, PK extends Serializable> implements IBaseDao {

	private static final Logger log = Logger.getLogger(BaseDaoImpl.class);
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier("namedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * 保存实体类
	 * @param entity
	 * @return
	 */
	public <T> T save(T entity){
		try {
			getSession().save(entity);
			getSession().flush();
			if(log.isDebugEnabled()){
				log.debug("保存实体成功," + entity.getClass().getName());
			}
			return entity;
		} catch (RuntimeException e) {
			log.error("保存实体异常", e);
			throw e;
		}
	}
	
	/**
	 * 批量保存
	 * @param entitys
	 */
	public <T> void batchSaveList(List<T> entitys){
		if(entitys != null){
			for(int i = 0; i < entitys.size(); i++){
				getSession().save(entitys.get(i));
				if(i % 20 == 0){
					//每20条提交一次
					getSession().flush();
					getSession().clear();
				}
			}
			getSession().flush();
			getSession().clear();
		}
	}

	/**
	 * 保存或修改
	 * @param entity
	 */
	public <T> T saveOrUpdate(T entity) {
		try {
			getSession().saveOrUpdate(entity);
			getSession().flush();
			if(log.isDebugEnabled()){
				log.debug("添加或更新成功,"+entity.getClass().getName());
			}
			return entity;
		} catch (RuntimeException e) {
			log.error("添加或更新异常", e);
			throw e;
		}
	}

	/**
	 * 删除单个实体
	 * @param entity
	 */
	public <T> void delete(T entity) {
		try {
			getSession().delete(entity);
			getSession().flush();
			if(log.isDebugEnabled()){
				log.debug("删除成功,"+entity.getClass().getName());
			}
		} catch (RuntimeException e) {
			log.error("删除异常", e);
			throw e;
		}
		
	}

	/**
	 * 根据ID删除
	 * @param entityName
	 * @param id
	 */
	public <T> void deleteById(Class entityName, Serializable id) {
		delete(get(entityName, id));
		getSession().flush();
	}

	/**
	 * 批量删除
	 * @param entities
	 */
	public <T> void deleteAllEntity(Collection<T> entities) {
		for(Object entity : entities){
			getSession().delete(entity);
			getSession().flush();
		}
		
	}

	/**
	 * 修改实体
	 * @param entity
	 */
	public <T> T update(T entity) {
		try {
			getSession().update(entity);
			getSession().flush();
			if(log.isDebugEnabled()){
				log.debug("修改实体成功," + entity.getClass().getName());
			}
			return entity;
		} catch (RuntimeException e) {
			log.error("修改实体异常", e);
			throw e;
		}
		
	}

	/**
	 * 根据ID修改
	 * @param entityName
	 * @param id
	 * @return
	 */
	public <T> T updateById(Class entityName, Serializable id) {
		return (T) update(get(entityName, id));
	}

	/**
	 * 执行HQL语句操作更新
	 * 
	 * @param hql
	 * @return
	 */
	public Integer executeHql(String hql) {
		Query query = getSession().createQuery(hql);
		return query.executeUpdate();
	}

	/**
	 * 根据sql更新
	 * @param sql
	 * @return
	 */
	public int updateBySqlString(String sql) {
		Query query = getSession().createSQLQuery(sql);
		return query.executeUpdate();
	}
	
	/**
	 * 根据实体名称和主键获取实体
	 * @param entityName
	 * @param id
	 * @return
	 */
	public <T> T get(Class<T> entityName, Serializable id) {
		return (T) getSession().get(entityName, id);
	}

	/**
	 * 根据属性名和属性值 查询 且要求对象唯一.
	 * 
	 * @return 符合条件的唯一对象.
	 */
	public <T> T findUniqueByProperty(Class<T> entityClass,
			String propertyName, Object value) {
		Assert.hasText(propertyName);
		return (T) createCriteria(entityClass, Restrictions.eq(propertyName, value)).uniqueResult();
	}

	/**
	 * 按属性查找对象列表.
	 */
	public <T> List<T> findByProperty(Class<T> entityClass,
			String propertyName, Object vlaue) {
		Assert.hasText(propertyName);
		return (List<T>)createCriteria(entityClass, Restrictions.eq(propertyName, vlaue)).list();
	}
	
	/**
	 * 根据属性名和属性值 查询对象集合 并排序
	 */
	public <T> List<T> findByPropertyisOrder(Class<T> entityClass,
			String propertyName, Object value, boolean isAsc) {
		Assert.hasText(propertyName);
		return createCriteria(entityClass, isAsc, 
				Restrictions.eq(propertyName, value)).list();
	}

	/**
	 * 加载所有实体
	 */
	public <T> List<T> loadAll(Class<T> entityClass) {
		Criteria criteria = createCriteria(entityClass);
		return criteria.list();
	}

	/**
	 * 根据hql查询
	 */
	public <T> List<T> findByHQl(String hql, Object... param) {
		Query query = getSession().createQuery(hql);
		if(param != null && param.length > 0){
			for(int i = 0; i < param.length; i++){
				query.setParameter(i, param[i]);
			}
		}
		List<T> list = query.list();
		if(list.size() > 0){
			getSession().flush();
		}
		return list;
	}
	
	/**
	 * 根据hql语句查询对象集合
	 * @param hql
	 * @return
	 */
	public <T> List<T> findByHQl(String hql, Map<String, Object> param){
		Query query = getSession().createQuery(hql);
		for (String key : param.keySet())
			query.setParameter(key, param.get(key));
		List<T> list = query.list();
		if(list.size() > 0){
			getSession().flush();
		}
		return list;
	}

	/**
	 * 通过hql查询唯一对象
	 */
	public <T> T singleResult(String hql) {
		T t = null;
		Query query = getSession().createQuery(hql);
		List<T> list = query.list();
		if(list != null){
			if(list.size() > 1){
				getSession().flush();
				t = list.get(0);
			}else{
				throw new RuntimeException("查询结果数:"+list.size() + "大于1");
			}
		}else{
			throw new RuntimeException("查询没有符合条件的对象");
		}
		return t;
	}

	/**
	 * 根据sql查询对象集合 带条件
	 */
	public <T> List<T> findBySql(String sql, Object... param) {
		Query query = getSession().createSQLQuery(sql);
		if(param != null && param.length > 0){
			for(int i = 0; i < param.length; i++){
				query.setParameter(i, param[i]);
			}
		}
		List <T> list = query.list();
		if(list.size() > 0){
			getSession().flush();
		}
		return list;
	}

	/**
	 * 根据sql查询对象集合
	 */
	public <T> List<T> findBySql(Class clasz, String sql) {
		Query query = getSession().createSQLQuery(sql).addEntity(clasz);
		List<T> list = query.list();
		if(list.size() > 0){
			getSession().flush();
		}
		return list;
	}
	
	/**
	 * 根据sql查询
	 * @param sql
	 * @return
	 */
	public <T> List<T> findBySql(final Class clasz, String sql, Object... param){
		Query query = getSession().createSQLQuery(sql).addEntity(clasz);
		if(param != null && param.length > 0){
			for(int i = 0; i < param.length; i++){
				query.setParameter(i, param[i]);
			}
		}
		List <T> list = query.list();
		if(list.size() > 0){
			getSession().flush();
		}
		return list;
	}

	/**
	 * 使用指定的检索标准检索数据
	 */
	public List<Map<String, Object>> findForJdbc(String sql, Object... objs) {
		return this.jdbcTemplate.queryForList(sql, objs);
	}

	public Map<String, Object> findOneForJdbc(String sql, Object... objs) {
		return this.jdbcTemplate.queryForMap(sql, objs);
	}

	public Long getRepositoryByHQLCount(String hql, Object... param) throws Exception {
		Query q = this.getSession().createQuery(hql);
		if(param != null && param.length > 0){
			for(int i = 0; i < param.length; i++){
				q.setParameter(i, param[i]);
			}
		}
		Long count = Long.parseLong(q.uniqueResult().toString());
		this.getSession().flush();
		return count;
	}

	public Long getCountForJdbc(String sql, Object... param) throws Exception {
		Query q = this.getSession().createSQLQuery(sql);
		if(param != null && param.length > 0){
			for(int i = 0; i < param.length; i++){
				q.setParameter(i, param[i]);
			}
		}
		Long count = Long.parseLong(q.uniqueResult().toString());
		this.getSession().flush();
		return count;
	}

	public <T> List<T> findObjForJdbc(String sql, int page, int rows,
			Class<T> clazz) {
		List<T> rsList = new ArrayList<T>();
		sql = JdbcDao.createPageSql(sql, page, rows);
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql);
		
		T po = null;
		for(Map<String, Object> map : list){
			try {
				po = clazz.newInstance();
				MyBeanUtil.copyMap2Bean(po, map);
				rsList.add(po);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return rsList;
	}
	
	public <T> List<T> findObjForJdbc(String sql, int page, int rows, Class<T> clazz, Object... objs){
		List<T> rsList = new ArrayList<T>();
		sql = JdbcDao.createPageSql(sql, page, rows);
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql, objs);
		T po = null;
		for(Map<String, Object> map : list){
			try {
				po = clazz.newInstance();
				MyBeanUtil.copyMap2Bean(po, map);
				rsList.add(po);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return rsList;
	}

	public List<Map<String, Object>> findSqlForJdbc(String sql, int page, int rows, Object... objs) {
		sql = JdbcDao.createPageSql(sql, page, rows);
		return this.jdbcTemplate.queryForList(sql, objs);
	}
	
	/**
	 * HQL分页查询
	 * @param hql
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public <T> List<T> findHQLByPage(final String hql, final int offset, final int pageSize){
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
		this.getSession().flush();
		return query.list();
	}
	/**
	 * HQL分页查询
	 * @param hql
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public <T> List<T> findHQLByPage(final String hql, final int offset, final int pageSize, final Map<String, Object> map){
		Query query = this.getSession().createQuery(hql);
		for (String key : map.keySet())
			query.setParameter(key, map.get(key));
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
		this.getSession().flush();
		return query.list();
	}
	/**
	 * HQL分页查询
	 * @param hql
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public <T> List<T> findHQLByPage(final String hql, final int offset, final int pageSize, final Object[] values){
		Query query = this.getSession().createQuery(hql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
		this.getSession().flush();
		return query.list();
	}

	public Integer executeSql(String sql, List<Object> param) {
		return this.jdbcTemplate.update(sql, param);
	}

	public Integer executeSql(String sql, Object... param) {
		return this.jdbcTemplate.update(sql, param);
	}

	public Integer executeSql(String sql, Map<String, Object> param) {
		return this.namedParameterJdbcTemplate.update(sql, param);
	}

	public Object executeSqlReturnKey(String sql, Map<String, Object> param) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public DataGridReturn getCriteriaQueryList(final CriteriaQuery cq, boolean isPage){
		Criteria criteria = cq.getDetachedCriteria().getExecutableCriteria(getSession());
		CriteriaImpl impl = (CriteriaImpl)criteria;
		Projection projection = impl.getProjection();
		final int counts = ((Long)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		criteria.setProjection(projection);
		if(projection == null){
			criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);//设置查询结果为实体对象
		}
		if(StringUtils.isNotBlank(cq.getDatagrid().getSort())){
			cq.getOrderMap().put(cq.getDatagrid().getSort(), cq.getDatagrid().getOrder());
		}
		//设置排序
		if(!cq.getOrderMap().isEmpty()){
			cq.setOrder(cq.getOrderMap());
		}
		int pageSize = cq.getDatagrid().getRows();
		int curPage = PageUtil.getCurPageNo(counts, cq.getDatagrid().getPage(), cq.getDatagrid().getRows());
		int offset = PageUtil.getOffest(counts, curPage, pageSize);
		if(isPage){
			criteria.setFirstResult(offset);
			criteria.setMaxResults(cq.getDatagrid().getRows());
		}else{
			pageSize = counts;
		}
		List list = criteria.list();
		cq.getDatagrid().setResultList(list);
		cq.getDatagrid().setTotal(counts);
		return new DataGridReturn(counts, list);
	}
	
	/**
	 * 创建单一Criteria对象
	 * @param <T>
	 * @param entityClass
	 * @param criterions
	 * @return
	 */
	private <T> Criteria createCriteria(Class<T> entityClass) {
		Criteria criteria = getSession().createCriteria(entityClass);
		return criteria;
	}
	
	/**
	 * 创建Criteria对象带属性比较
	 * @param entityClass
	 * @param criterions
	 * @return
	 */
	private <T> Criteria createCriteria(Class<T> entityClass, Criterion...criterions){
		Criteria criteria = getSession().createCriteria(entityClass);
		for(Criterion c : criterions){
			criteria.add(c);
		}
		return criteria;
	}
	
	/**
	 * 创建Criteria对象，有排序功能。
	 * @param entiClass
	 * @param isAsc
	 * @param criterions
	 * @return
	 */
	private <T> Criteria createCriteria(Class<T> entityClass, boolean isAsc, Criterion...criterions){
		Criteria criteria = createCriteria(entityClass, criterions);
		if(isAsc){
			criteria.addOrder(Order.asc("asc"));
		}else{
			criteria.addOrder(Order.asc("desc"));
		}
		return criteria;
	}
		
}
