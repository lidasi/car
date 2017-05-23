package com.comm.util;

import java.util.HashMap;
import java.util.Iterator;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.api.jms.HornetQJMSClient;
import org.hornetq.api.jms.JMSFactoryType;
import org.hornetq.core.remoting.impl.netty.TransportConstants;

import com.comm.web.common.util.PropUtil;


public class HornetQUtils {

	/**
	 * LOG输出
	 */
	private static Logger logger = Logger.getLogger(HornetQUtils.class);

	private static final String CF_CLASSNAME = PropUtil.getProperty("jms.cf.classname"); //NettyConnectorFactory.class.getName()
	private static final String PROVIDER_HOST = PropUtil.getProperty("jms.server.host");;//PropUtil.getProperty("jms.server.host");
	private static final String PROVIDER_PORT = PropUtil.getProperty("jms.server.port");

	public static final String QNAME_SENDSMS = "sendSMS";
	public static final String QNAME_SENDMAIL = "sendMail";
	public static final String QNAME_ADDUSER = "addUser";

	private static HashMap<String, MessageProducer> _prodMap = new HashMap<String, MessageProducer>();

	private static ConnectionFactory _cf = null;
	private static Connection _connection = null;
	private static Session _session = null;

	/**
	 * 创建JMS连接、会话
	 */
	private static void init() {
		// 设置上下文的JNDI查找
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(TransportConstants.HOST_PROP_NAME, PROVIDER_HOST);
		map.put(TransportConstants.PORT_PROP_NAME, PROVIDER_PORT);
		try {
			// 创建JMS连接、会话
			TransportConfiguration server = new TransportConfiguration(CF_CLASSNAME, map);
			_cf = (ConnectionFactory) HornetQJMSClient.createConnectionFactoryWithoutHA(JMSFactoryType.CF, server);
			_connection = _cf.createConnection();
			_session = _connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		} catch (Exception e) {
			logger.error("创建JMS连接、会话对象时出错", e);
		}
	}

	/**
	 * 重置消息队列配置
	 */
	public static void reset() {
		close();
		init();
	}
	
	/**
	 * 获取指定队列对象
	 *
	 * @param queueName 消息队列名
	 *
	 * @return Destination 消息队列对象
	 */
	private static MessageProducer getMessageProducer(String queueName) {
		logger.debug("开始创建JMS MessageProducer");
		if (_session == null) {
			init();
		}
		MessageProducer producer = _prodMap.get(queueName);
		if (producer == null) {
			try {
				// 设置上下文的JNDI查找
				producer = _session.createProducer(HornetQJMSClient.createQueue(queueName));
			} catch (Exception e) {
				logger.error("创建Destination对象时出错", e);
			}
		}
		return producer;
	}

	/**
	 * 发送消息到指定队列
	 *
	 * @param queueName 消息队列名<br>
	 *       这里的队列名应该是jboss配置文件standalone-full.xml中的"hornetq-server"->"jms-destinations"->"jms-queue"的[name]属性值，<br>
	 *       而不是其子节点"entry"的[name]属性值
	 * @param msgValue  消息内容
	 */
	public static String sendQueueMessage(String queueName, String msgValue) {
		// 创建JMS生产者
		MessageProducer producer = getMessageProducer(queueName);
		if (producer == null) {
			logger.error("错误:无JMS生产者对象 " + queueName);
			return Const.SMU_SEND_FAIL;
		}
		try {
			TextMessage message = _session.createTextMessage(msgValue);
			producer.send(message);
		} catch (Exception e) {
			logger.error("发送消息时出错: " + queueName + "##" + msgValue, e);
			return Const.SMU_SEND_FAIL;
		}
		return Const.SMU_SEND_SUCC;
	}

	/**
	 * 关闭连接、会话等JMS资源
	 */
	public static void close() {
		// 关闭连接会话,生产商和消费者
		try {
			for (Iterator<MessageProducer> iter = _prodMap.values().iterator(); iter.hasNext(); ) {
				iter.next().close();
			}
		} catch (Exception e) {
			logger.error("关闭JMS MessageProducer资源时出错", e);
		}
		try {
			if (_session != null) {
				_session.close();
			}
		} catch (Exception e) {
			logger.error("关闭JMS Session资源时出错", e);
		}
		try {
			if (_connection != null) {
				_connection.close();
			}
		} catch (Exception e) {
			logger.error("关闭JMS Connection资源时出错", e);
		}
	}
}
