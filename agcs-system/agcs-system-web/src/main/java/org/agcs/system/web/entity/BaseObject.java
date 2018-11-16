package org.agcs.system.web.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public class BaseObject implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int currentPage;
	private int pageSize;
	private int startNumber;
	private String lrr;
	private String lrrxm;
	private Date lrsj;
	private String lrrbm;
	private String lrip;
	private String lrmac;
	private Date last_update;
	@Transient
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	@Transient
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	@Transient
	public int getStartNumber() {
		return this.startNumber = (this.currentPage-1)*pageSize;
	}
	public void setStartNumber(int startNumber) {
		this.startNumber = startNumber;
	}
	@Column(name="LRR", nullable=true, length=35)
	public String getLrr() {
		return lrr;
	}
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	@Column(name="LRRXM", nullable=true, length=50)
	public String getLrrxm() {
		return lrrxm;
	}
	public void setLrrxm(String lrrxm) {
		this.lrrxm = lrrxm;
	}
	@Column(name="LRSJ", nullable=true)
	public Date getLrsj() {
		return lrsj;
	}
	public void setLrsj(Date lrsj) {
		this.lrsj = lrsj;
	}
	@Column(name="LRRBM", nullable=true,length=35)
	public String getLrrbm() {
		return lrrbm;
	}
	public void setLrrbm(String lrrbm) {
		this.lrrbm = lrrbm;
	}
	@Column(name="LRIP", nullable=true,length=35)
	public String getLrip() {
		return lrip;
	}
	public void setLrip(String lrip) {
		this.lrip = lrip;
	}
	@Column(name="LRMAC", nullable=true,length=35)
	public String getLrmac() {
		return lrmac;
	}
	public void setLrmac(String lrmac) {
		this.lrmac = lrmac;
	}
	@Column(name="LAST_UPDATE", nullable=true)
	public Date getLast_update() {
		return last_update;
	}
	public void setLast_update(Date last_update) {
		this.last_update = last_update;
	}
	

}
