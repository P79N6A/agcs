package org.agcs.system.hibernate.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.agcs.system.core.listener.ApplicationListener;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 数据库类型公共类
* @Title: DBTypeUtil.java 
* @Package org.braveframwork.hibernate.util 
* @Description:  
* @author vivian
* @date 2016-1-16 下午4:23:24 
* @version V1.0
 */
public class DBTypeUtil {
	
	private static Logger log = Logger.getLogger(DBTypeUtil.class);
	
	/**
	 * 根据数据库方言获取数据库类型
	 * @return
	 */
	public static String getDBType(){
		String dbtype = "";
		ApplicationContext ctx = ApplicationListener.getCtx();
		if(ctx == null){
			return dbtype;
		}else{
			LocalSessionFactoryBean sf = (LocalSessionFactoryBean) ctx.getBean("&sessionFactory");
			String dialect = sf.getHibernateProperties().getProperty("hibernate.dialect");
			log.info(dialect);
			if("org.hibernate.dialect.MySQLDialect".equals(dialect)){
				dbtype = "mysql";
			}else if(dialect.contains("Oracle")){
				dbtype = "oracle";
			}else if("org.hibernate.dialect.SQLServerDialect".equals(dialect)){
				dbtype = "sqlserver";
			}
			return dbtype;
		}
	}
}
