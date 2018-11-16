package org.agcs.system.mq.example.ordermessage;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.agcs.system.mq.producer.Tag;
import org.agcs.system.mq.producer.Topic;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerOrderly;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;

public class Consumer {
	static int count = 0;
	
	public static void main(String[] args) throws MQClientException {
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ribao_consumer_group2");
		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
		consumer.setNamesrvAddr("192.168.0.13:9876;192.168.0.80:9876");
		consumer.setInstanceName("Consumber");
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
//				System.out.println(this.consumeTimes.get());
//				if((this.consumeTimes.get() % 2) == 0){
//					System.out.println("%2");
//					return ConsumeOrderlyStatus.SUCCESS;
//				}else if((this.consumeTimes.get() % 3) == 0){
//					System.out.println("%3");
//					return ConsumeOrderlyStatus.ROLLBACK;
//				}else if((this.consumeTimes.get() % 4) == 0){
//					System.out.println("%4");
//					return ConsumeOrderlyStatus.COMMIT;
//				}else if((this.consumeTimes.get() % 5) == 0){
//					System.out.println("%5");
//					context.setSuspendCurrentQueueTimeMillis(3000);
//					return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
//				}
				
				return ConsumeOrderlyStatus.SUCCESS;
			}
		});
		consumer.start();
		System.out.println("Consumer Started.");
		
	}
	

}
