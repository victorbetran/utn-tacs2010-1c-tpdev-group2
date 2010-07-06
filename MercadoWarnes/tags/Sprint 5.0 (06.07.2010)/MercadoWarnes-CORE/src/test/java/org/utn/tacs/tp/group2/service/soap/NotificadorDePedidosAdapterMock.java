package org.utn.tacs.tp.group2.service.soap;

import java.util.ArrayList;
import java.util.List;

import org.utn.tacs.tp.group2.pedido.EfectivizacionPedidosObserver;
import org.utn.tacs.tp.group2.pedido.Pedido;

public class NotificadorDePedidosAdapterMock  implements EfectivizacionPedidosObserver{

	private List<Pedido> pedidosNotificados = new ArrayList<Pedido>();
	
	public void pedidoEfectivizado(Pedido pedido) {
		this.pedidosNotificados.add(pedido);
	}
	
	public boolean isNotificated(Pedido pedido){
		return this.pedidosNotificados.contains(pedido);
	}

}
