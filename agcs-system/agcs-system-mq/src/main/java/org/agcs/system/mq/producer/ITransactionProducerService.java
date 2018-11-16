package org.agcs.system.mq.producer;

public interface ITransactionProducerService {
	
	/**
	 * 发送事务消息
	 * @param topic
	 * @param tags
	 * @param keys
	 * @param body
	 * @return
	 * @throws Exception
	 */
	public String pushTransactionMQ(String topic, String tags, String keys, String body) throws Exception;

}
