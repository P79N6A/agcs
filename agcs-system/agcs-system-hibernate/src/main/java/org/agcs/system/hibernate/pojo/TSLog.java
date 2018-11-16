package org.agcs.system.hibernate.pojo;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TLog entity.
 *  @author  brave
 */
@Entity
@Table(name = "t_s_log")
public class TSLog implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5110614598529991600L;
	private String userid;
	private Short loglevel;
	private Timestamp operatetime;
	private Short operatetype;
	private String logcontent;
	private String broswer;//用户浏览器类型
	private String note;

	@Column(name = "userid", length = 35)
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(name = "loglevel")
	public Short getLoglevel() {
		return this.loglevel;
	}

	public void setLoglevel(Short loglevel) {
		this.loglevel = loglevel;
	}

	@Column(name = "operatetime", nullable = false, length = 35)
	public Timestamp getOperatetime() {
		return this.operatetime;
	}

	public void setOperatetime(Timestamp operatetime) {
		this.operatetime = operatetime;
	}

	@Column(name = "operatetype")
	public Short getOperatetype() {
		return this.operatetype;
	}

	public void setOperatetype(Short operatetype) {
		this.operatetype = operatetype;
	}

	@Column(name = "logcontent", nullable = false, length = 2000)
	public String getLogcontent() {
		return this.logcontent;
	}

	public void setLogcontent(String logcontent) {
		this.logcontent = logcontent;
	}

	@Column(name = "note", length = 300)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	@Column(name = "broswer", length = 100)
	public String getBroswer() {
		return broswer;
	}

	public void setBroswer(String broswer) {
		this.broswer = broswer;
	}

}