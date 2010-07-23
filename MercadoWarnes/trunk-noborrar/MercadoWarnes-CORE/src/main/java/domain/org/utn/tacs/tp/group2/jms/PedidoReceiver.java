package org.utn.tacs.tp.group2.jms;


import org.springframework.beans.factory.annotation.Autowired;
import org.utn.tacs.tp.group2.daos.interfaces.PedidoDAO;
import org.utn.tacs.tp.group2.pedido.EfectivizacionPedidosObserver;
import org.utn.tacs.tp.group2.pedido.Pedido;
public class PedidoReceiver  {
	
	
	@Autowired
	private PedidoDAO pedidoDAO;
	

	public void procesarPedido(Pedido pedido){	
		pedido.efectivizar();
		pedido.acceptEfectivizacionObserver(this.getNotificador());
		this.pedidoDAO.save(pedido);
	}

	@Autowired
	private EfectivizacionPedidosObserver notificadorDePedidosAdapter;
	private EfectivizacionPedidosObserver getNotificador() {
		return this.notificadorDePedidosAdapter;
	}
	
	public void setNotificador(EfectivizacionPedidosObserver notificador) {
		this.notificadorDePedidosAdapter = notificador;
	}
	
}

