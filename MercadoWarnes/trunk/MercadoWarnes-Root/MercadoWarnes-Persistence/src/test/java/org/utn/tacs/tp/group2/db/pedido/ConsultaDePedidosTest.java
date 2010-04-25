package org.utn.tacs.tp.group2.db.pedido;

import org.junit.Assert;
import org.junit.Test;
import org.utn.tacs.tp.group2.pedido.Pedido;


public class ConsultaDePedidosTest extends PedidoTest{

	Pedido pedidoPersistido;
	Pedido pedidoPersistidoA;
	
	@Override
	public void setUp() {
		super.setUp();
		
		pedidoPersistido = new Pedido();
		dao.save(pedidoPersistido);
		
		pedidoPersistidoA = new Pedido();
		dao.save(pedidoPersistidoA);
		
	}
	
	@Test
	public void consultarPedidoPorIDTest(){
		Pedido pedidoObtenidoConDao = dao.findByID(pedidoPersistido.getId());
		Assert.assertEquals("El Pedido persistido no coincide con el accedido.",pedidoPersistido, pedidoObtenidoConDao);
	}
	
	
	public void consultarPedidoPorEstadoTest(){
		// TODO: Implementar.
	}
	
}
