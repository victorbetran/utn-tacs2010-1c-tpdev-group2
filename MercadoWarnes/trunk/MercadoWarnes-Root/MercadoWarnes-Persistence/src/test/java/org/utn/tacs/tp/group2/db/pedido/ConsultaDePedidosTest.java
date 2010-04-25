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
		
		this.pedidoPersistido = new Pedido();
		this.dao.save(this.pedidoPersistido);
		
		this.pedidoPersistidoA = new Pedido();
		this.dao.save(this.pedidoPersistidoA);
	}

	
	//********************************************
	//** TEST METHODS
	//********************************************
	/**
	 * Consulta un pedido existente en la BD por su ID
	 */
	@Test
	public void consultarPedidoPorIDTest(){
		Pedido pedidoObtenidoConDao = dao.findByID(pedidoPersistido.getId());
		Assert.assertEquals("El Pedido persistido no coincide con el accedido.",pedidoPersistido, pedidoObtenidoConDao);
	}
	
	/**
	 * 
	 */
	public void consultarPedidoPorEstadoTest(){
		//TODO: Implementar.
	}
	
	/**
	 * Consulta una pieza existente en la BD por su Categoria
	 */
	@Test 
	public void consultarPiezasPorCategoria(){
		//TODO: Implementar.
	}

	
}
