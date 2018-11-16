package org.agcs.system.mq.producer;

public interface IProducerService {
	
	/**
	 * 发送常规消息
	 * @return
	 */
	public String pushNormalMQ(String topic, String tags, String keys, String body) throws Exception;
	
	/**
	 * 发送顺序消息
	 * @param topic
	 * @param tags
	 * @param keys
	 * @param order
	 * @param body
	 * @return
	 * @throws Exception
	 */
	public String pushOrderMQ(String topic, String tags, String keys, Object order, String body) throws Exception;
	

}
