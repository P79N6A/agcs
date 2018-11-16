package org.agcs.system.mq;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;

public class ApplicationMQListener implements ServletContextListener,HttpSessionListener{
	
	private static final Logger log = Logger.getLogger(ApplicationMQListener.class);
	private static DefaultMQProducer producer = null;

	public void sessionCreated(HttpSessionEvent arg0) {
		
	}

	/**
	 * MQ初始化
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		if(producer == null){
			try {
				producer = new DefaultMQProducer("ribao_consumer_group");
				producer.setNamesrvAddr("192.168.0.13:9876;192.168.0.80:9876");
		        producer.setInstanceName("Producer");
				producer.start();
			} catch (MQClientException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static DefaultMQProducer getProducer(){
		return producer;
	}
	
	public void sessionDestroyed(HttpSessionEvent arg0) {
		
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		//关闭tomcat时调用shutdown来清理资源，关闭网络连接，从MetaQ服务器上注销自己
		if(producer != null){
			producer.shutdown();
		}
	}

}
