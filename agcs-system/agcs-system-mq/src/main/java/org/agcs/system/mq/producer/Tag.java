package org.agcs.system.mq.producer;

public enum Tag {
	
	ORDER_QG("order_qg"), ORDER_WL("order_wl"), ORDER_CK("order_ck");
	
	private String name;
	
	private Tag(String name){
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
