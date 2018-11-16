package org.agcs.system.mq.producer.impl;

import org.agcs.system.mq.producer.ITransactionProducerService;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.TransactionCheckListener;
import com.alibaba.rocketmq.client.producer.TransactionMQProducer;
import com.alibaba.rocketmq.common.message.Message;

/**
 * 此类中的发送消息是示例，实际业务中，需写接口发送具体的消息
 * @author Administrator
 *
 */
public class TransactionProducerServiceImpl implements ITransactionProducerService{
	
	private String namesrvAddr;
	private String producerGroup;
	private String instanceName;
	
	private static TransactionMQProducer transactionMQProducer = null;
	
	
	public String pushTransactionMQ(String topic, String tags, String keys, String body) throws Exception{
		String result = null;
		TransactionExecuterImpl tranExecuter = new TransactionExecuterImpl();
		Message msg = new Message(topic, tags, keys, body.getBytes());
		SendResult sendResult = transactionMQProducer.sendMessageInTransaction(msg, tranExecuter, null);
		result = sendResult.toString();
		return result;
	}
	
	public void init(){
		
		if(transactionMQProducer == null){
			TransactionCheckListener transactionCheckListener = new TransactionCheckListenerImpl();
			try {
				transactionMQProducer = new TransactionMQProducer(producerGroup);
				transactionMQProducer.setNamesrvAddr(namesrvAddr);
				transactionMQProducer.setInstanceName(instanceName);
				// 事务回查最小并发数
				transactionMQProducer.setCheckThreadPoolMinSize(2);
		        // 事务回查最大并发数
				transactionMQProducer.setCheckThreadPoolMaxSize(2);
		        // 队列数
				transactionMQProducer.setCheckRequestHoldMax(2000);
				transactionMQProducer.setTransactionCheckListener(transactionCheckListener);
				transactionMQProducer.start();
			} catch (MQClientException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public String getNamesrvAddr() {
		return namesrvAddr;
	}
	public void setNamesrvAddr(String namesrvAddr) {
		this.namesrvAddr = namesrvAddr;
	}
	public String getProducerGroup() {
		return producerGroup;
	}
	public void setProducerGroup(String producerGroup) {
		this.producerGroup = producerGroup;
	}
	public String getInstanceName() {
		return instanceName;
	}
	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}
	
}
