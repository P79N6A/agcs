package org.agcs.system.mq.producer.impl;

import java.util.List;
import org.agcs.system.mq.producer.IProducerService;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.TransactionCheckListener;
import com.alibaba.rocketmq.client.producer.TransactionMQProducer;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageQueue;

/**
 * 此类中的发送消息是示例，实际业务中，需写接口发送具体的消息
 * @author Administrator
 *
 */
public class ProducerServiceImpl implements IProducerService{
	
	private String namesrvAddr;
	private String producerGroup;
	private String instanceName;
	
	private static DefaultMQProducer producer = null;
	
	/**
	 * 发送消息
	 * 此方法发送的消息，消费端修改DefaultMQPushConsumer后就重新消费一次,所有消费端都能消费每一条消息
	 * @return
	 */
	public String pushNormalMQ(String topic, String tags, String keys, String body) throws Exception{
		String result;
		Message msg = new Message(topic, tags, keys, body.getBytes());
	    SendResult sendResult = producer.send(msg);
	    result = sendResult.toString();
		return result;
	}
	
	/**
	 * 发送顺序消息
	 * @param topic
	 * @param tags
	 * @param keys
	 * @param order  order相同，在同一个队列的消息按照顺序消费
	 * @param body
	 * @return
	 * @throws Exception
	 */
	public String pushOrderMQ(String topic, String tags, String keys, Object order, String body) throws Exception{
		String result = null;
		Message msg = new Message(topic, tags, keys, body.getBytes());
		SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
            public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
            	//根据业务实际情况，选择消息队列，默认每个broker4个队列，可以通过setDefaultTopicQueueNums(defaultTopicQueueNums);
                return mqs.get(0);
            }
        }, order);
		result = sendResult.toString();
		return result;
	}
	
	public void init(){
		if(producer == null){
			try {
				producer = new DefaultMQProducer(producerGroup);
				producer.setNamesrvAddr(namesrvAddr);
		        producer.setInstanceName(instanceName);
				producer.start();
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
