package org.utn.tacs.tp.group2.jms;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.utn.tacs.tp.group2.daos.interfaces.PedidoDAO;
import org.utn.tacs.tp.group2.pedido.Pedido;
import org.utn.tacs.tp.group2.pieza.Moneda;
import org.utn.tacs.tp.group2.pieza.Pieza;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class PedidoReceiverTest {

	private Pedido pedido;
	private Pieza pieza;
	
	@Autowired
	private PedidoReceiver pedidoReceiver;
	
	@Autowired
	private PedidoDAO pedidoDAO;
	
	@Before
	public void setUp() {
		this.pedido=Pedido.create();
		this.pieza=new Pieza("3", 3, Moneda.PESO);
		this.pedido.addPieza(pieza);	
			
	}
	
	@Test
	@Transactional
	public void ProcesarUnPedido()
	{		
		this.pedidoReceiver.procesarPedido(this.pedido);
		Assert.assertTrue(this.pedido.isEfectivo());	
		Assert.assertTrue(this.pedidoDAO.isPersisted(this.pedido));
	}
}
