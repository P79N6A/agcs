package org.agcs.system.core.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ApplicationListener implements ServletContextListener,HttpSessionListener{
	
	private static ApplicationContext ctx = null;

	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
	}

	/**
	 * 服务器初始化
	 */
	
	public void contextInitialized(ServletContextEvent evt) {
		ctx = WebApplicationContextUtils.getWebApplicationContext(evt.getServletContext());
	}

	public static ApplicationContext getCtx() {
		return ctx;
	}
	
	public void contextDestroyed(ServletContextEvent paramServletContextEvent) {
		
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		
	}

}
