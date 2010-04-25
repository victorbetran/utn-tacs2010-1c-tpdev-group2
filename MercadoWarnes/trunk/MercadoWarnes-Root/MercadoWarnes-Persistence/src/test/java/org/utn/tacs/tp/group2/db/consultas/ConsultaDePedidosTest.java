package org.utn.tacs.tp.group2.db.consultas;

import org.junit.Assert;
import org.junit.Test;
import org.utn.tacs.tp.group2.daos.implementations.PedidoDAOImpl;
import org.utn.tacs.tp.group2.daos.interfaces.PedidoDAO;
import org.utn.tacs.tp.group2.db.DataBaseHandlerTest;
import org.utn.tacs.tp.group2.pedido.Pedido;


public class ConsultaDePedidosTest extends DataBaseHandlerTest{

	@Override
	public void setUp() {
		super.setUp();
		
	}
	
	@Test
	public void savePiezaEnDisco(){
		PedidoDAO pedidoDao = new PedidoDAOImpl();
		Pedido pedidoPersistible = new Pedido();
		
		pedidoDao.save(pedidoPersistible);
		
		Pedido pedidoPersistido = pedidoDao.findByID(pedidoPersistible.getId());
		
		Assert.assertEquals("Las piezas no se corresponden, no representan lo mismo.",pedidoPersistible, pedidoPersistido);
	}


	
}
