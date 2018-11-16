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
 * @Description: 菜单管理
 * @date 2016-02-18
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="glpt_node")
@DynamicUpdate(true)
@DynamicInsert(true)
public class GlptNodeEntity implements java.io.Serializable{
	
	// Fields
	/**id,自增序列*/
	private java.lang.String id;
	/**节点代号*/
	private java.lang.String nodeCode;
	/**节点名称*/
	private java.lang.String node_name;
	/**节点url*/
	private java.lang.String nodeUrl;
	/**节点图标*/
	private java.lang.String nodeIcon;
	/**上级节点*/
	private java.lang.String supNodeCode;
	/**状态*/
	private java.lang.String status;
	/**节点类型*/
	private java.lang.String type;
	/**所属系统*/
	private java.lang.String system;
	/**排序*/
	private java.lang.Integer sort;
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
	public GlptNodeEntity() {
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
	
	@Column(name="NODE_CODE", nullable=false,length=30)
	public java.lang.String getNodeCode(){
		return this.nodeCode;
	}
	public void setNodeCode(java.lang.String nodeCode){
		this.nodeCode = nodeCode;
	}
	
	@Column(name="NODE_NAME", nullable=true,length=100)
	public java.lang.String getNode_name(){
		return this.node_name;
	}
	public void setNode_name(java.lang.String node_name){
		this.node_name = node_name;
	}
	
	@Column(name="NODE_URL", nullable=true,length=200)
	public java.lang.String getNodeUrl(){
		return this.nodeUrl;
	}
	public void setNodeUrl(java.lang.String nodeUrl){
		this.nodeUrl = nodeUrl;
	}
	
	@Column(name="NODE_ICON", nullable=true,length=50)
	public java.lang.String getNodeIcon(){
		return this.nodeIcon;
	}
	public void setNodeIcon(java.lang.String nodeIcon){
		this.nodeIcon = nodeIcon;
	}
	
	@Column(name="SUP_NODE_CODE", nullable=true,length=30)
	public java.lang.String getSupNodeCode(){
		return this.supNodeCode;
	}
	public void setSupNodeCode(java.lang.String supNodeCode){
		this.supNodeCode = supNodeCode;
	}
	
	@Column(name="STATUS", nullable=true,length=10)
	public java.lang.String getStatus(){
		return this.status;
	}
	public void setStatus(java.lang.String status){
		this.status = status;
	}
	
	@Column(name="TYPE", nullable=true,length=10)
	public java.lang.String getType(){
		return this.type;
	}
	public void setType(java.lang.String type){
		this.type = type;
	}
	
	@Column(name="SYSTEM", nullable=true,length=10)
	public java.lang.String getSystem(){
		return this.system;
	}
	public void setSystem(java.lang.String system){
		this.system = system;
	}
	
	@Column(name="SORT", nullable=true,precision=10,scale=0)
	public java.lang.Integer getSort(){
		return this.sort;
	}
	public void setSort(java.lang.Integer sort){
		this.sort = sort;
	}
	
	@Column(name="REMARK", nullable=true,length=500)
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