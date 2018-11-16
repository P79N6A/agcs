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
 * @Description: 用户信息列表
 * @date 2016-02-01
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="glpt_user")
@DynamicUpdate(true)
@DynamicInsert(true)
public class GlptUserEntity implements java.io.Serializable{
	
	// Fields
	/**ID*/
	private java.lang.String id;
	/**用户代号*/
	private java.lang.String userCode;
	/**用户名*/
	private java.lang.String userName;
	/**用户密码*/
	private java.lang.String password;
	/**真实姓名*/
	private java.lang.String realName;
	/**性别*/
	private java.lang.String sex;
	/**年龄*/
	private java.lang.Integer age;
	/**生日*/
	private java.util.Date birthday;
	/**手机号*/
	private java.lang.String mobilePhone;
	/**电话*/
	private java.lang.String telphone;
	/**身份证明号码（身份证、军官证、护照等等）*/
	private java.lang.String caedId;
	/**地址*/
	private java.lang.String address;
	/**状态*/
	private java.lang.String status;
	/**在线状态（在线，隐身、忙碌、离开、离线）*/
	private java.lang.String inline;
	/**学历*/
	private java.lang.String education;
	/**邮箱*/
	private java.lang.String email;
	/**QQ号码*/
	private java.lang.String qq;
	/**描述*/
	private java.lang.String remaek;
	/**签名*/
	private java.lang.String signature;
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
	public GlptUserEntity() {
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
	
	@Column(name="USER_CODE", nullable=false,length=30)
	public java.lang.String getUserCode(){
		return this.userCode;
	}
	public void setUserCode(java.lang.String userCode){
		this.userCode = userCode;
	}
	
	@Column(name="USER_NAME", nullable=true,length=30)
	public java.lang.String getUserName(){
		return this.userName;
	}
	public void setUserName(java.lang.String userName){
		this.userName = userName;
	}
	
	@Column(name="PASSWORD", nullable=true,length=50)
	public java.lang.String getPassword(){
		return this.password;
	}
	public void setPassword(java.lang.String password){
		this.password = password;
	}
	
	@Column(name="REAL_NAME", nullable=true,length=100)
	public java.lang.String getRealName(){
		return this.realName;
	}
	public void setRealName(java.lang.String realName){
		this.realName = realName;
	}
	
	@Column(name="SEX", nullable=true,length=2)
	public java.lang.String getSex(){
		return this.sex;
	}
	public void setSex(java.lang.String sex){
		this.sex = sex;
	}
	
	@Column(name="AGE", nullable=true,precision=10,scale=0)
	public java.lang.Integer getAge(){
		return this.age;
	}
	public void setAge(java.lang.Integer age){
		this.age = age;
	}
	
	@Column(name="BIRTHDAY", nullable=true)
	public java.util.Date getBirthday(){
		return this.birthday;
	}
	public void setBirthday(java.util.Date birthday){
		this.birthday = birthday;
	}
	
	@Column(name="MOBILE_PHONE", nullable=true,length=30)
	public java.lang.String getMobilePhone(){
		return this.mobilePhone;
	}
	public void setMobilePhone(java.lang.String mobilePhone){
		this.mobilePhone = mobilePhone;
	}
	
	@Column(name="TELPHONE", nullable=true,length=30)
	public java.lang.String getTelphone(){
		return this.telphone;
	}
	public void setTelphone(java.lang.String telphone){
		this.telphone = telphone;
	}
	
	@Column(name="CAED_ID", nullable=true,length=30)
	public java.lang.String getCaedId(){
		return this.caedId;
	}
	public void setCaedId(java.lang.String caedId){
		this.caedId = caedId;
	}
	
	@Column(name="ADDRESS", nullable=true,length=300)
	public java.lang.String getAddress(){
		return this.address;
	}
	public void setAddress(java.lang.String address){
		this.address = address;
	}
	
	@Column(name="STATUS", nullable=true,length=5)
	public java.lang.String getStatus(){
		return this.status;
	}
	public void setStatus(java.lang.String status){
		this.status = status;
	}
	
	@Column(name="INLINE", nullable=true,length=5)
	public java.lang.String getInline(){
		return this.inline;
	}
	public void setInline(java.lang.String inline){
		this.inline = inline;
	}
	
	@Column(name="EDUCATION", nullable=true,length=50)
	public java.lang.String getEducation(){
		return this.education;
	}
	public void setEducation(java.lang.String education){
		this.education = education;
	}
	
	@Column(name="EMAIL", nullable=true,length=30)
	public java.lang.String getEmail(){
		return this.email;
	}
	public void setEmail(java.lang.String email){
		this.email = email;
	}
	
	@Column(name="QQ", nullable=true,length=30)
	public java.lang.String getQq(){
		return this.qq;
	}
	public void setQq(java.lang.String qq){
		this.qq = qq;
	}
	
	@Column(name="REMAEK", nullable=true,length=500)
	public java.lang.String getRemaek(){
		return this.remaek;
	}
	public void setRemaek(java.lang.String remaek){
		this.remaek = remaek;
	}
	
	@Column(name="SIGNATURE", nullable=true,length=100)
	public java.lang.String getSignature(){
		return this.signature;
	}
	public void setSignature(java.lang.String signature){
		this.signature = signature;
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