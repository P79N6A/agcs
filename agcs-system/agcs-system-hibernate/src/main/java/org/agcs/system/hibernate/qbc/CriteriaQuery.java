package org.agcs.system.hibernate.qbc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.agcs.system.hibernate.qbc.CriterionList;
import org.agcs.system.hibernate.qbc.DataGrid;
import org.agcs.system.hibernate.qbc.SortDirection;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
* @Title: CriteriaQuery.java 
* @Package org.braveframwork.hibernate.qbc 
* @Description:  对hibernate qbc查询方法的封装
* @author vivian
* @date 2016-1-27 上午11:22:12 
* @version V1.0
 */
@SuppressWarnings("rawtypes")
public class CriteriaQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6150595744783435667L;
	
	private DetachedCriteria detachedCriteria;
	private DataGrid datagrid;
	private CriterionList criterionList = new CriterionList();
	private Map<String, Object> map;
	private Map<String, Object> orderMap;
	private Class entityClass;
	private boolean flag = true;//对同一字段进行第二次重命名查询时值设置FASLE不保存重命名查询条件
	private List<String> alias = new ArrayList<String>();  //创建别名集合
	
	public CriteriaQuery(){
		
	}
	public CriteriaQuery(Class clazz, DataGrid datagrid){
		this.detachedCriteria = DetachedCriteria.forClass(clazz);
		this.entityClass = clazz;
		this.datagrid = datagrid;
		this.map = new HashMap<String, Object>();
		this.orderMap = new HashMap<String, Object>();
	}
	
	public void add(){
		for(int i = 0; i < getCriterionList().size(); i++){
			detachedCriteria.add(getCriterionList().getParas(i));
		}
		getCriterionList().removeAll(getCriterionList());
	}
	
	/**
	 * 多值(in)查询
	 * @param name
	 * @param values
	 */
	public void in(String name, Object[] values){
		if(values != null && values[0] != ""){
			criterionList.addPara(Restrictions.in(name, values));
		}
	}
	
	/**
	 * like模糊查询
	 * @param name
	 * @param value
	 */
	public void like(String name, Object value){
		if(value != null && value != ""){
			criterionList.addPara(Restrictions.like(name, value));
			if(flag){
				this.put(name, value);
			}
			flag = true;
		}
	}
	/**
	 * 设置is not null 查询条件
	 */
	public void isNotNull(String name){
		criterionList.addPara(Restrictions.isNotNull(name));
	}
	/**
	 * 设置noteq(不等)查询条件
	 * @param name
	 * @param value
	 */
	public void notEq(String name, Object value){
		if(value != null && value != ""){
			criterionList.addPara(Restrictions.ne(name, value));
			if(flag){
				this.put(name, value);
			}
			flag = true;
		}
	}
	
	public void eq(String name, Object value){
		if(value != null && value != ""){
			criterionList.addPara(Restrictions.eq(name, value));
			if(flag){
				this.put(name, value);
			}
			flag = true;
		}
	}
	
	/**
	 * 设置ge(>=)查询条件
	 * @param name
	 * @param value
	 */
	public void ge(String name, Object value){
		if(value != null && value != ""){
			criterionList.addPara(Restrictions.ge(name, value));
			if(flag){
				this.put(name, value);
			}
			flag = true;
		}
	}
	
	/**
	 * 设置le(<=)查询条件
	 * @param name
	 * @param value
	 */
	public void le(String name, Object value){
		if(value != null && value != ""){
			criterionList.addPara(Restrictions.le(name, value));
			if(flag){
				this.put(name, value);
			}
			flag = true;
		}
	}
	
	/**
	 * 保存查询条件
	 */
	public void put(String name, Object value){
		if(value != null && value != ""){
			map.put(name, value);
		}
	}
	
	public void createAlias(String name, String value){
		if(!alias.contains(name)){
			detachedCriteria.createAlias(name, value);
			alias.add(name);
		}
	}
	
	/**
	 * 设置排序
	 * @param map
	 */
	public void setOrder(Map<String, Object> map){
		for(Map.Entry<String, Object> entry : map.entrySet()){
			createOrderAlias(entry.getKey());
			if(SortDirection.asc.equals(entry.getValue())){
				detachedCriteria.addOrder(Order.asc(entry.getKey()));
			}else{
				detachedCriteria.addOrder(Order.desc(entry.getKey()));
			}
		}
	}
	
	/**
	 * 创建别名
	 */
	public void createOrderAlias(String entitys){
		String[] alias = entitys.split("\\.");
		for(int i = 0; i < alias.length-1; i++){
			createAlias(alias[i], alias[i]);
		}
	}
	
	public DetachedCriteria getDetachedCriteria() {
		return detachedCriteria;
	}
	public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
		this.detachedCriteria = detachedCriteria;
	}
	public DataGrid getDatagrid() {
		return datagrid;
	}
	public void setDatagrid(DataGrid datagrid) {
		this.datagrid = datagrid;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public Map<String, Object> getOrderMap() {
		return orderMap;
	}
	public void setOrderMap(Map<String, Object> orderMap) {
		this.orderMap = orderMap;
	}
	public Class getEntityClass() {
		return entityClass;
	}
	public void setEntityClass(Class entityClass) {
		this.entityClass = entityClass;
	}
	public CriterionList getCriterionList() {
		return criterionList;
	}
	public void setCriterionList(CriterionList criterionList) {
		this.criterionList = criterionList;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
}
