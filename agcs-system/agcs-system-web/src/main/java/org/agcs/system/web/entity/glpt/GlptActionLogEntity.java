package org.agcs.system.web.entity.glpt;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;

import org.agcs.system.core.util.ToolsUtil;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**   
 * @Title: Entity
 * @Description: 操作日志
 * @date 2016-02-20
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="glpt_action_log")
@DynamicUpdate(true)
@DynamicInsert(true)
public class GlptActionLogEntity implements java.io.Serializable{
	
	// Fields
	/**日志id*/
	private java.lang.String id;
	/**操作类名*/
	private java.lang.String classname;
	/**表名*/
	private java.lang.String tablename;
	/**输入参数*/
	private java.lang.String inputpagram;
	/**输出结果*/
	private java.lang.String outresult;
	/**操作方法名*/
	private java.lang.String method;
	/**请求地址*/
	private java.lang.String url;
	/**开始时间*/
	private java.util.Date kssj;
	/**结束时间*/
	private java.util.Date jssj;
	/**操作事项*/
	private java.lang.String opname;
	/**状态*/
	private java.lang.String status;
	/**日志级别*/
	private java.lang.String levels;
	/**操作人*/
	private java.lang.String czr;
	/**操作人姓名*/
	private java.lang.String czrxm;
	/**操作人部门*/
	private java.lang.String czrbm;
	/**操作时间*/
	private java.util.Date czsj;
	/**操作内容*/
	private java.lang.String cznr;
	/**操作IP*/
	private java.lang.String czip;
	
	// Constructors
	/** default constructor */
	public GlptActionLogEntity() {
	}
	
	public GlptActionLogEntity(GlptUserEntity user, HttpServletRequest request){
		this.czr = user.getUserCode();
		this.czrxm = user.getUserName();
		this.czip = ToolsUtil.getIpAddr(request);
		this.czsj = new Date();
		this.url = request.getContextPath();
		this.levels = "SYSTEM";
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
	
	@Column(name="CLASSNAME", nullable=true,length=100)
	public java.lang.String getClassname(){
		return this.classname;
	}
	public void setClassname(java.lang.String classname){
		this.classname = classname;
	}
	
	@Column(name="TABLENAME", nullable=true,length=50)
	public java.lang.String getTablename(){
		return this.tablename;
	}
	public void setTablename(java.lang.String tablename){
		this.tablename = tablename;
	}
	
	@Column(name="INPUTPAGRAM", nullable=true,length=65535)
	public java.lang.String getInputpagram(){
		return this.inputpagram;
	}
	public void setInputpagram(java.lang.String inputpagram){
		this.inputpagram = inputpagram;
	}
	
	@Column(name="OUTRESULT", nullable=true,length=65535)
	public java.lang.String getOutresult(){
		return this.outresult;
	}
	public void setOutresult(java.lang.String outresult){
		this.outresult = outresult;
	}
	
	@Column(name="METHOD", nullable=true,length=100)
	public java.lang.String getMethod(){
		return this.method;
	}
	public void setMethod(java.lang.String method){
		this.method = method;
	}
	
	@Column(name="URL", nullable=true,length=500)
	public java.lang.String getUrl(){
		return this.url;
	}
	public void setUrl(java.lang.String url){
		this.url = url;
	}
	
	@Column(name="KSSJ", nullable=true)
	public java.util.Date getKssj(){
		return this.kssj;
	}
	public void setKssj(java.util.Date kssj){
		this.kssj = kssj;
	}
	
	@Column(name="JSSJ", nullable=true)
	public java.util.Date getJssj(){
		return this.jssj;
	}
	public void setJssj(java.util.Date jssj){
		this.jssj = jssj;
	}
	
	@Column(name="OPNAME", nullable=true,length=500)
	public java.lang.String getOpname(){
		return this.opname;
	}
	public void setOpname(java.lang.String opname){
		this.opname = opname;
	}
	
	@Column(name="STATUS", nullable=true,length=10)
	public java.lang.String getStatus(){
		return this.status;
	}
	public void setStatus(java.lang.String status){
		this.status = status;
	}
	
	@Column(name="CZR", nullable=true,length=50)
	public java.lang.String getCzr(){
		return this.czr;
	}
	public void setCzr(java.lang.String czr){
		this.czr = czr;
	}
	
	@Column(name="CZRXM", nullable=true,length=100)
	public java.lang.String getCzrxm(){
		return this.czrxm;
	}
	public void setCzrxm(java.lang.String czrxm){
		this.czrxm = czrxm;
	}
	
	@Column(name="CZRBM", nullable=true,length=50)
	public java.lang.String getCzrbm(){
		return this.czrbm;
	}
	public void setCzrbm(java.lang.String czrbm){
		this.czrbm = czrbm;
	}
	
	@Column(name="CZSJ", nullable=true)
	public java.util.Date getCzsj(){
		return this.czsj;
	}
	public void setCzsj(java.util.Date czsj){
		this.czsj = czsj;
	}
	
	@Column(name="CZNR", nullable=true,length=500)
	public java.lang.String getCznr(){
		return this.cznr;
	}
	public void setCznr(java.lang.String cznr){
		this.cznr = cznr;
	}
	
	@Column(name="CZIP", nullable=true,length=50)
	public java.lang.String getCzip(){
		return this.czip;
	}
	public void setCzip(java.lang.String czip){
		this.czip = czip;
	}

	@Column(name="LEVELS", nullable=true,length=50)
	public java.lang.String getLevels() {
		return levels;
	}
	public void setLevels(java.lang.String levels) {
		this.levels = levels;
	}
	
}