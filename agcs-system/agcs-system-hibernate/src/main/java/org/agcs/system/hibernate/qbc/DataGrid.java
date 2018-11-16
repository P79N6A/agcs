package org.agcs.system.hibernate.qbc;

import java.util.List;

import org.agcs.system.hibernate.qbc.SortDirection;

/**
* @Title: DataGrid.java 
* @Package org.brave.web.common 
* @Description:  easyui的datagrid向后台传递数据使用
* @author vivian
* @date 2016-1-27 下午1:48:29 
* @version V1.0
 */
public class DataGrid {
	
	private int page;  //当前页
	private int rows;  //记录数
	private String sort;//排序字段名
	private SortDirection order = SortDirection.asc;//排序
	private String field;
	private List resultList;
	private int total;
	
	public DataGrid(){
		
	}
	public DataGrid(int page, int rows, String sort, SortDirection order, String field,
			List resultList, int total) {
		this.page = page;
		this.rows = rows;
		this.sort = sort;
		this.order = order;
		this.field = field;
		this.resultList = resultList;
		this.total = total;
	}


	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public SortDirection getOrder() {
		return order;
	}
	public void setOrder(SortDirection order) {
		this.order = order;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public List getResultList() {
		return resultList;
	}
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	

}
