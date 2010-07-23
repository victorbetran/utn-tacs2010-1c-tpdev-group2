package ar.edu.utn.frba.tacs.warnes;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsTemplate;

import junit.framework.TestCase;

public class ColaTest extends TestCase {
	public void testCola() throws Exception {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(new ActiveMQConnectionFactory("tcp://localhost:61636"));
		jmsTemplate.setDefaultDestination(new ActiveMQQueue("pedidos"));
		
		System.out.println(jmsTemplate.receiveAndConvert());;
	}
}
