package ar.edu.utn.frba.tacs.mercadowarnessh.colas;

import org.springframework.jms.core.JmsTemplate;

import ar.edu.utn.frba.tacs.warnes.Pedido;

public class ColaFiller {
	private JmsTemplate jmsTemplate = null;
	private PedidosProvider pedidosProvider = null;
	
	public void fill(){
		for(Pedido p:pedidosProvider.getPedidos())
			jmsTemplate.convertAndSend(p);
	}

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public PedidosProvider getPedidosProvider() {
		return pedidosProvider;
	}

	public void setPedidosProvider(PedidosProvider pedidosProvider) {
		this.pedidosProvider = pedidosProvider;
	}
}
