package org.agcs.system.mq.producer;

public enum Topic {
	
	ORDER_QG("qgpt_order_mq");
	
	private String name;
	
	private Topic(String name){
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
	
}
