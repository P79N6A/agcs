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
 * @Description: 角色管理
 * @date 2016-02-18
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="glpt_role")
@DynamicUpdate(true)
@DynamicInsert(true)
public class GlptRoleEntity implements java.io.Serializable{
	
	// Fields
	/**ID*/
	private java.lang.String id;
	/**角色代号*/
	private java.lang.String roleCode;
	/**角色名称*/
	private java.lang.String roleName;
	/**状态(有效1、无效0)*/
	private java.lang.String state;
	/**描述*/
	private java.lang.String remaek;
	/**操作人*/
	private java.lang.String lrr;
	/**操作人姓名*/
	private java.lang.String lrrxm;
	/**操作时间*/
	private java.util.Date lrsj;
	/**操作人部门*/
	private java.lang.String lrrbm;
	/**操作IP*/
	private java.lang.String lrip;
	/**操作MAC*/
	private java.lang.String lrmac;
	/**修改时间*/
	private java.util.Date lastUpdate;
	
	// Constructors
	/** default constructor */
	public GlptRoleEntity() {
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
	
	@Column(name="ROLE_CODE", nullable=false,length=30)
	public java.lang.String getRoleCode(){
		return this.roleCode;
	}
	public void setRoleCode(java.lang.String roleCode){
		this.roleCode = roleCode;
	}
	
	@Column(name="ROLE_NAME", nullable=true,length=50)
	public java.lang.String getRoleName(){
		return this.roleName;
	}
	public void setRoleName(java.lang.String roleName){
		this.roleName = roleName;
	}
	
	@Column(name="STATE", nullable=true,length=5)
	public java.lang.String getState(){
		return this.state;
	}
	public void setState(java.lang.String state){
		this.state = state;
	}
	
	@Column(name="REMAEK", nullable=true,length=300)
	public java.lang.String getRemaek(){
		return this.remaek;
	}
	public void setRemaek(java.lang.String remaek){
		this.remaek = remaek;
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
	
	@Column(name="LRSJ", nullable=true)
	public java.util.Date getLrsj(){
		return this.lrsj;
	}
	public void setLrsj(java.util.Date lrsj){
		this.lrsj = lrsj;
	}
	
	@Column(name="LRRBM", nullable=true,length=30)
	public java.lang.String getLrrbm(){
		return this.lrrbm;
	}
	public void setLrrbm(java.lang.String lrrbm){
		this.lrrbm = lrrbm;
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