package org.agcs.system.hibernate.pojo;

import java.io.Serializable;

public class ModelView implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1275167537352069854L;
	private Object data;
	private int status;
	private String message;
	private String trncode;

	public ModelView(){
		
	}
	
	public ModelView(Object data, int status, String message) {
		this.data = data;
		this.status = status;
		this.message = message;
		this.trncode = System.currentTimeMillis()+"";
	}
	
	public ModelView(Object data, int status, String message, String trncode) {
		this.data = data;
		this.status = status;
		this.message = message;
		this.trncode = trncode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTrncode() {
		return trncode;
	}

	public void setTrncode(String trncode) {
		this.trncode = trncode;
	}

}
