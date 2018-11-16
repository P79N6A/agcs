package org.agcs.system.hibernate.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.agcs.system.hibernate.pojo.DataGridReturn;
import org.agcs.system.hibernate.qbc.CriteriaQuery;
import org.hibernate.Session;

/**
* @Title: IBaseDao.java 
* @Package org.braveframwork.hibernate 
* @Description:  Dao层泛型基类接口
* @author vivian
* @date 2016-1-16 上午9:26:10 
* @version V1.0
 */
public interface IBaseDao {
	
	public Session getSession();
	
	/**
	 * 保存实体类
	 * @param entity
	 * @return
	 */
	public <T> T save(T entity);
	
	/**
	 * 批量保存
	 * @param entitys
	 */
	public <T> void batchSaveList(List<T> entitys);
	
	/**
	 * 保存或修改
	 * @param entity
	 */
	public <T> T saveOrUpdate(T entity);
	
	/**
	 * 删除单个实体
	 * @param entity
	 */
	public <T> void delete(T entity);
	
	/**
	 * 根据ID删除
	 * @param entityName
	 * @param id
	 */
	public <T> void deleteById(Class entityName, Serializable id);
	
	/**
	 * 批量删除
	 * @param entities
	 */
	public <T> void deleteAllEntity(Collection<T> entities);
	
	/**
	 * 修改实体
	 * @param entity
	 */
	public <T> T update(T entity);
	
	/**
	 * 根据ID修改
	 * @param entityName
	 * @param id
	 * @return
	 */
	public <T> T updateById(Class entityName, Serializable id);
	
	/**
	 * 执行HQL语句操作更新
	 * 
	 * @param hql
	 * @return
	 */
	public Integer executeHql(String hql);
	
	/**
	 * 根据sql更新
	 * @param sql
	 * @return
	 */
	public int updateBySqlString(String sql);
	
	/**
	 * 根据实体名称和主键获取实体
	 * @param entityName
	 * @param id
	 * @return
	 */
	public <T> T get(Class<T> entityName, Serializable id);
	
	/**
	 * 根据实体名字获取唯一记录
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public <T> T findUniqueByProperty(Class<T> entityClass, String propertyName, Object value);
	
	/**
	 * 根据属性查找对象列表
	 * @param entityClass
	 * @param propertyNmae
	 * @param vlaue
	 * @return
	 */
	public <T> List<T> findByProperty(Class<T> entityClass, String propertyName, Object vlaue);
	
	/**
	 * 加载所有实体
	 * @param entityClass
	 * @return
	 */
	public <T> List<T> loadAll(final Class<T> entityClass);
	
	/**
	 * 根据hql语句查询对象集合
	 * @param hql
	 * @return
	 */
	public <T> List<T> findByHQl(String hql, Object... param);
	
	/**
	 * 根据hql语句查询对象集合
	 * @param hql
	 * @return
	 */
	public <T> List<T> findByHQl(String hql, Map<String, Object> param);
	
	/**
	 * 根据hql查询唯一对象
	 * @param hql
	 * @return
	 */
	public <T> T singleResult(String hql);
	
	/**
	 * 根据sql查询
	 * @param sql
	 * @return
	 */
	public <T> List<T> findBySql(String sql, Object... param);
	
	/**
	 * 根据sql查询
	 * @param sql
	 * @return
	 */
	public <T> List<T> findBySql(final Class clasz, String sql);
	
	/**
	 * 根据sql查询
	 * @param sql
	 * @return
	 */
	public <T> List<T> findBySql(final Class clasz, String sql, Object... param);
	
	/**
	 * 通过属性名称获取实体并排序
	 * @return
	 */
	public <T> List<T> findByPropertyisOrder(Class<T> entityClass, String propertyName, Object value, boolean isAsc);
	
	/**
	 * 通过JDBC查找对象集合 使用指定的检索标准检索数据返回数据
	 */
	public List<Map<String, Object>> findForJdbc(String sql, Object... objs);

	/**
	 * 通过JDBC查找对象集合 使用指定的检索标准检索数据返回数据
	 */
	public Map<String, Object> findOneForJdbc(String sql, Object... objs);
	
	/**
	 * 获取存储器列表对象数
	 * @param hql
	 * @return
	 * @throws Exception
	 */
	public Long getRepositoryByHQLCount(String hql, Object... param) throws Exception;
	
	/**
	 * 获取存储器列表对象数
	 * 
	 * @param sqls	SQL语句
	 * @return
	 */
	public Long getCountForJdbc(String sql, Object... param) throws Exception;
	
	/**
	 * 通过JDBC查找对象集合，带分页 使用指定的检索标准检索并分页返回数据
	 * @param sql
	 * @param page
	 * @param rows
	 * @return
	 */
	public <T> List<T> findObjForJdbc(String sql, int page, int rows, Class<T> clazz);
	
	/**
	 * 通过JDBC查找对象集合，带分页 使用指定的检索标准检索并分页返回数据
	 * @param sql
	 * @param page
	 * @param rows
	 * @return
	 */
	public <T> List<T> findObjForJdbc(String sql, int page, int rows, Class<T> clazz, Object... objs);
	
	/**
	 * 通过JDBC查找对象集合,带分页 使用指定的检索标准检索数据并分页返回数据
	 */
	public List<Map<String, Object>> findSqlForJdbc(String sql, int page, int rows, Object... objs);
	
	/**
	 * HQL分页查询
	 * @param hql
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public <T> List<T> findHQLByPage(final String hql, final int offset, final int pageSize);
	/**
	 * HQL分页查询
	 * @param hql
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public <T> List<T> findHQLByPage(final String hql, final int offset, final int pageSize, final Map<String, Object> map);
	/**
	 * HQL分页查询
	 * @param hql
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public <T> List<T> findHQLByPage(final String hql, final int offset, final int pageSize, final Object[] values);
	
	/**
	 * 执行SQL
	 */
	public Integer executeSql(String sql, List<Object> param);

	/**
	 * 执行SQL
	 */
	public Integer executeSql(String sql, Object... param);
	
	/**
	 * 执行SQL 使用:name占位符
	 */
	public Integer executeSql(String sql, Map<String, Object> param);
	
	/**
	 * 执行SQL 使用:name占位符,并返回插入的主键值
	 */
	public Object executeSqlReturnKey(String sql, Map<String, Object> param);
	
	/**
	 * QBC查询集合
	 * @return
	 */
	public DataGridReturn getCriteriaQueryList(final CriteriaQuery cq, boolean isPage);

}
