package org.utn.tacs.tp.group2.jms;


import org.springframework.beans.factory.annotation.Autowired;
import org.utn.tacs.tp.group2.daos.interfaces.PedidoDAO;
import org.utn.tacs.tp.group2.pedido.Pedido;
public class PedidoReceiver  {
	
	
	@Autowired
	private PedidoDAO pedidoDAO;
	

	public void procesarPedido(Pedido pedido)
	{	
		pedido.efectivizar();
		this.pedidoDAO.save(pedido);
	}

}
