package org.agcs.system.web.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class IdEntity {
	
	private String id;

	@Id
	@GenericGenerator(name="paymentableGenerator", strategy="uuid")
	@GeneratedValue(generator="paymentableGenerator")
	@Column(name ="ID", nullable=false, length=35)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

}
