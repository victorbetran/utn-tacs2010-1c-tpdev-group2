package org.utn.tacs.tp.group2.service.soap;


import org.utn.tacs.tp.group2.pedido.EfectivizacionPedidosObserver;
import org.utn.tacs.tp.group2.pedido.Pedido;

import ar.edu.utn.frba.tacs.mercadowarnessh.facturacion.PedidosService;
import ar.edu.utn.frba.tacs.mercadowarnessh.facturacion.PedidosServiceImplService;

/**
 * Clase utilizada para realizar notificaciones al servicio SOAP sobre
 * el cambio de estado en los Pedidos.
 */
public class NotificadorDePedidosAdapter implements EfectivizacionPedidosObserver{

	//***********************************
	//* ATRIBUTTES
	//***********************************
//	private static final NotificadorDePedidosAdapter INSTANCE = new NotificadorDePedidosAdapter();
	private PedidosService pedidoServiceSOAP;
	
	
	//***********************************
	//* CONSTRUCTORS
	//***********************************
	public NotificadorDePedidosAdapter() {
		PedidosServiceImplService service = new PedidosServiceImplService();  
		this.pedidoServiceSOAP = service.getPort(PedidosService.class);  
	}
	
//	public static NotificadorDePedidosAdapter getInstance(){
//		return INSTANCE;
//	}
	
	
	//***********************************
	//* PUBLIC METHODS
	//***********************************
	/**
	 * Notifica de la efectivización de un pedido al WS de SOAP.
	 */
	private void notifyPedidoEfectivization(Pedido pedido){
		this.pedidoServiceSOAP.notificarPedidoEfectivo(this.myPedidoToYourSOAPPedido(pedido));
	}
	
	//********************************************
	//** EFECTIVIZACION PEDIDO OBSERVER
	//********************************************
	
	public void pedidoEfectivizado(Pedido pedido) {
		this.notifyPedidoEfectivization(pedido);
	}
	
	//***********************************
	//*PRIVATE METHODS
	//***********************************
	/**
	 * Convierte un medido de nuestro dominio a un pedido perteneciente a la aplicación SOAP-.
	 */
	private ar.edu.utn.frba.tacs.mercadowarnessh.facturacion.Pedido myPedidoToYourSOAPPedido(org.utn.tacs.tp.group2.pedido.Pedido pedido){
		ar.edu.utn.frba.tacs.mercadowarnessh.facturacion.Pedido pedidoSOAP = new ar.edu.utn.frba.tacs.mercadowarnessh.facturacion.Pedido();
		pedidoSOAP.setId(pedido.getId());
		for(org.utn.tacs.tp.group2.pieza.Pieza pieza : pedido.getPiezas()){
			ar.edu.utn.frba.tacs.mercadowarnessh.facturacion.Pieza piezaSOAP = new ar.edu.utn.frba.tacs.mercadowarnessh.facturacion.Pieza();
			piezaSOAP.setId(pieza.getId());
			piezaSOAP.setDescripcion(pieza.getDescripcion());
			piezaSOAP.setPrecio(Float.valueOf(1234)); //TODO: cambiar a pieza.getPrecio() cuando se termine el tema de las cotizaciones.
			pedidoSOAP.getPiezas().add(piezaSOAP);
		}
		return pedidoSOAP;
	}
}
