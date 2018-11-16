package org.agcs.system.mq.example.ordermessage;

import java.util.List;

import org.agcs.system.mq.producer.Topic;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.MQProducer;
import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageQueue;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

public class Producer {
	
	public static void main(String[] args){
		try {
			DefaultMQProducer producer = new DefaultMQProducer("ribao_consumer_group");
			producer.setNamesrvAddr("192.168.0.13:9876;192.168.0.80:9876");
	        producer.setInstanceName(Long.toString(System.currentTimeMillis()));
			producer.start();
			String[] tags = new String[]{"TagA", "TagB", "TagC", "TagD", "TagE"};
			int index = 0;
			for(int i = 0; i < 1; i++){
				System.out.println(index);
				// 订单ID相同的消息要有序
			    int orderId = index % 10;
			    Message msg = new Message(Topic.ORDER_QG.toString(), "order_qg", "KEY"+index, ("Hello RocketMQ"+index).getBytes());
			    SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
					
					public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
						Integer id = (Integer) arg;
                        int index = id % mqs.size();
                        return mqs.get(2);
					}
				}, 0);
			    System.out.println(sendResult);
			    index ++;
			}
			producer.shutdown();
		} catch (MQClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemotingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MQBrokerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(System.getProperty("rocketmq.namesrv.domain",
        "jmenv.tbsite.net"));
		
		System.out.println(System.getProperty("rocketmq.namesrv.domain.subgroup",
        "nsaddr"));
		
	}

}
