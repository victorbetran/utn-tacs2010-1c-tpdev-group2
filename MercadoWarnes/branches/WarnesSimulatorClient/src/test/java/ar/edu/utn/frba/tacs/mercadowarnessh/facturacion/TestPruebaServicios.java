package ar.edu.utn.frba.tacs.mercadowarnessh.facturacion;

import org.junit.Test;


public class TestPruebaServicios {
	
	@Test
	public void efectivizarPedido(){
		PedidosServiceImplService service = new PedidosServiceImplService();  
		PedidosService port = service.getPort(PedidosService.class);  
		
		Pieza pieza = new Pieza();
		pieza.setDescripcion("Mi pieza SOAP");
		pieza.setPrecio(Float.valueOf(120));
		pieza.setId(Long.valueOf(666));
		
		Pedido pedido = new Pedido();
		pedido.setId(Long.valueOf(666));
		pedido.getPiezas().add(pieza);
		
		port.notificarPedidoEfectivo(pedido);
	}
}
