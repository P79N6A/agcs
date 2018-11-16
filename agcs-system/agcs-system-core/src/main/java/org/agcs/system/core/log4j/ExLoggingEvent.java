package org.agcs.system.core.log4j;

import org.apache.log4j.Category;
import org.apache.log4j.Priority;
import org.apache.log4j.spi.LoggingEvent;

public class ExLoggingEvent extends LoggingEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3056522788880373480L;

	public ExLoggingEvent(String fqnOfCategoryClass, Category logger,
			Priority level, Object message, Throwable throwable) {
		super(fqnOfCategoryClass, logger, level, message, throwable);  
	}

	@Override
	public String getRenderedMessage() {
		String rederMessage = super.getRenderedMessage();
		if(rederMessage.indexOf("'") != -1){
			rederMessage = rederMessage.replaceAll("'", "''");
		}
		return rederMessage;
	}

	@Override
	public String getThreadName() {
		String thrdName = super.getThreadName();
		if(thrdName.indexOf("'") != -1){
			thrdName = thrdName.replaceAll("'", "''");
		}
		return thrdName;
	}
	
	

}
