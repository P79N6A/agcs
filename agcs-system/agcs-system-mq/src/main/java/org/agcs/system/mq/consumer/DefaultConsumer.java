package org.agcs.system.mq.consumer;

import java.util.List;
import java.util.Properties;

import org.agcs.system.hibernate.services.CommonServiceI;
import org.agcs.system.mq.producer.Tag;
import org.agcs.system.mq.producer.Topic;
import org.apache.log4j.Logger;
import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerOrderly;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;


public class DefaultConsumer {
	
	private static final Logger log = Logger.getLogger(DefaultConsumer.class);
	DefaultMQPushConsumer consumer = null;
	private Properties rocketProperties;
	
	private CommonServiceI commonService;
	
	public DefaultConsumer(){
		
	}
	
	public void init(){
		log.info("DefaultConsumer init method......");
		if(consumer == null){
			log.info("constructor_rocketmq.consumerGroup:"+rocketProperties.getProperty("rocketmq.consumerGroup"));
			consumer = new DefaultMQPushConsumer(rocketProperties.getProperty("rocketmq.consumerGroup"));
		}
		try {
			consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
			consumer.setNamesrvAddr(rocketProperties.getProperty("rocketmq.namesrv.addr"));
			consumer.setInstanceName(rocketProperties.getProperty("rocketmq.instance.name"));
			consumer.subscribe(Topic.ORDER_QG.toString(), Tag.ORDER_QG.toString());
			consumer.registerMessageListener(new MessageListenerOrderly() {
				public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs,
						ConsumeOrderlyContext context) {
					context.setAutoCommit(true);
					MessageExt msg = msgs.get(0);
					System.out.println(Thread.currentThread().getName()+"Recevie New Message:"+new String(msg.getBody()));
					try {
	                    //模拟业务逻辑处理中...
	                    //Thread.sleep(500);
						exec();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
					
					return ConsumeOrderlyStatus.SUCCESS;
				}
			});
			consumer.start();
			System.out.println("Consumer Started.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void exec(){
		this.commonService.findBySql("select 1");
	}
	
	public void shutdown(){
		log.info("DefaultConsumer shutdown method......");
		if(consumer != null){
			consumer = null;
		}
	}

	public Properties getRocketProperties() {
		if(rocketProperties == null){
			rocketProperties = new Properties();
		}
		return rocketProperties;
	}

	public void setRocketProperties(Properties rocketProperties) {
		this.rocketProperties = rocketProperties;
	}

	public CommonServiceI getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonServiceI commonService) {
		this.commonService = commonService;
	}
	
}
