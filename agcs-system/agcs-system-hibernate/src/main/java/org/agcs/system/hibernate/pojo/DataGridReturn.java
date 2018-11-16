package org.agcs.system.hibernate.pojo;

import java.util.List;

/**
* @Title: DataGridReturn.java 
* @Package org.braveframwork.hibernate.pojo 
* @Description:  用于向前台返回json easyui datagrid
* @author vivian
* @date 2016-1-28 上午10:36:56 
* @version V1.0
 */
public class DataGridReturn {
	
	private Integer total;
	private List rows;
	
	public DataGridReturn() {
		
	}
	
	public DataGridReturn(Integer total, List rows) {
		this.total = total;
		this.rows = rows;
	}

	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	
	
	

}
