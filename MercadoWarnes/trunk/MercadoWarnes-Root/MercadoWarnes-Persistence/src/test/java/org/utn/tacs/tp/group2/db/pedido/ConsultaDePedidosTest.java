package org.utn.tacs.tp.group2.db.pedido;

import org.junit.Assert;
import org.junit.Test;
import org.utn.tacs.tp.group2.daos.interfaces.PedidoDAO;
import org.utn.tacs.tp.group2.db.DataBaseHandlerTest;
import org.utn.tacs.tp.group2.pedido.Pedido;


public class ConsultaDePedidosTest extends DataBaseHandlerTest{

	PedidoDAO dao;
	Pedido pedidoPersistido;
	Pedido pedidoPersistidoA;
	
	@Override
	public void setUp() {
		super.setUp();
		
		dao = PedidoDAO.getInstance();
		
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
