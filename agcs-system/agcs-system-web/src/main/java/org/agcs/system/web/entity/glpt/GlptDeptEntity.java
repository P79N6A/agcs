package org.agcs.system.web.entity.glpt;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**   
 * @Title: Entity
 * @Description: 部门信息
 * @date 2016-02-22
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="glpt_dept")
@DynamicUpdate(true)
@DynamicInsert(true)
public class GlptDeptEntity implements java.io.Serializable{
	
	// Fields
	/**序列，自动增长*/
	private java.lang.String id;
	/**部门代号*/
	private java.lang.String deptCode;
	/**部门名称*/
	private java.lang.String deptName;
	/**上级部门*/
	private java.lang.String supDeptCode;
	/**状态*/
	private java.lang.String status;
	/**描述*/
	private java.lang.String remark;
	/**操作人*/
	private java.lang.String lrr;
	/**操作人姓名*/
	private java.lang.String lrrxm;
	/**操作人部门*/
	private java.lang.String lrrbm;
	/**操作时间*/
	private java.util.Date lrsj;
	/**操作IP*/
	private java.lang.String lrip;
	/**操作MAC*/
	private java.lang.String lrmac;
	/**最后修改时间*/
	private java.util.Date lastUpdate;
	
	// Constructors
	/** default constructor */
	public GlptDeptEntity() {
	}
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name="ID", nullable=false,length=35)
	public java.lang.String getId(){
		return this.id;
	}
	public void setId(java.lang.String id){
		this.id = id;
	}
	
	@Column(name="DEPT_CODE", nullable=false,length=50)
	public java.lang.String getDeptCode(){
		return this.deptCode;
	}
	public void setDeptCode(java.lang.String deptCode){
		this.deptCode = deptCode;
	}
	
	@Column(name="DEPT_NAME", nullable=true,length=100)
	public java.lang.String getDeptName(){
		return this.deptName;
	}
	public void setDeptName(java.lang.String deptName){
		this.deptName = deptName;
	}
	
	@Column(name="SUP_DEPT_CODE", nullable=true,length=50)
	public java.lang.String getSupDeptCode(){
		return this.supDeptCode;
	}
	public void setSupDeptCode(java.lang.String supDeptCode){
		this.supDeptCode = supDeptCode;
	}
	
	@Column(name="STATUS", nullable=true,length=5)
	public java.lang.String getStatus(){
		return this.status;
	}
	public void setStatus(java.lang.String status){
		this.status = status;
	}
	
	@Column(name="REMARK", nullable=true,length=300)
	public java.lang.String getRemark(){
		return this.remark;
	}
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}
	
	@Column(name="LRR", nullable=true,length=30)
	public java.lang.String getLrr(){
		return this.lrr;
	}
	public void setLrr(java.lang.String lrr){
		this.lrr = lrr;
	}
	
	@Column(name="LRRXM", nullable=true,length=50)
	public java.lang.String getLrrxm(){
		return this.lrrxm;
	}
	public void setLrrxm(java.lang.String lrrxm){
		this.lrrxm = lrrxm;
	}
	
	@Column(name="LRRBM", nullable=true,length=30)
	public java.lang.String getLrrbm(){
		return this.lrrbm;
	}
	public void setLrrbm(java.lang.String lrrbm){
		this.lrrbm = lrrbm;
	}
	
	@Column(name="LRSJ", nullable=true)
	public java.util.Date getLrsj(){
		return this.lrsj;
	}
	public void setLrsj(java.util.Date lrsj){
		this.lrsj = lrsj;
	}
	
	@Column(name="LRIP", nullable=true,length=30)
	public java.lang.String getLrip(){
		return this.lrip;
	}
	public void setLrip(java.lang.String lrip){
		this.lrip = lrip;
	}
	
	@Column(name="LRMAC", nullable=true,length=30)
	public java.lang.String getLrmac(){
		return this.lrmac;
	}
	public void setLrmac(java.lang.String lrmac){
		this.lrmac = lrmac;
	}
	
	@Column(name="LAST_UPDATE", nullable=true)
	public java.util.Date getLastUpdate(){
		return this.lastUpdate;
	}
	public void setLastUpdate(java.util.Date lastUpdate){
		this.lastUpdate = lastUpdate;
	}
	
}