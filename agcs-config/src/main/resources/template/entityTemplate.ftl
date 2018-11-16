package ${bussiPackage}.entity.${entityPackage};

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
 * @Description: ${funDescription}
 * @date ${create_time}
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="${tableName}")
@DynamicUpdate(true)
@DynamicInsert(true)
public class ${entityName}Entity implements java.io.Serializable{
	
	// Fields
	<#list originalColumns as po>
	/**${po.filedComment}*/
	private ${po.filedType} ${po.filedName};
	</#list>
	
	// Constructors
	/** default constructor */
	public ${entityName}Entity() {
	}
	
	<#list originalColumns as po>
	<#if po.filedName == generate_table_id>
	<#if primary_key_policy == 'uuid'>
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	</#if>
	<#if primary_key_policy == 'identity'>
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	</#if>
	<#if primary_key_policy == 'sequence'>
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="sequence")
	@SequenceGenerator(name="sequence",sequenceName="${sequence_code}",allocationSize=1)
	</#if>
	</#if>
	@Column(name="${po.filedDbName}", nullable=<#if po.nullable == 'Y'>true<#else>false</#if><#if po.precision != ''>,precision=${po.precision}</#if><#if po.scale != ''>,scale=${po.scale}</#if><#if po.charmaxLength != ''>,length=${po.charmaxLength}</#if>)
	public ${po.filedType} get${po.filedName?cap_first}(){
		return this.${po.filedName};
	}
	public void set${po.filedName?cap_first}(${po.filedType} ${po.filedName}){
		this.${po.filedName} = ${po.filedName};
	}
	
	</#list>
}