package org.utn.tacs.tp.group2.db.consultas;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.utn.tacs.tp.group2.daos.implementations.PedidoDAOImpl;
import org.utn.tacs.tp.group2.daos.interfaces.PedidoDAO;
import org.utn.tacs.tp.group2.exceptions.pedido.PedidoInexistenteException;
import org.utn.tacs.tp.group2.pedido.Pedido;


public class ConsultarPedidosTest {

	//********************************************
	//** ATRIBUTTES
	//********************************************

	
	
	//********************************************
	//** SET UP
	//********************************************
	private SessionFactory sessionFactory;
	@Before
	public void setUp() {
		sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		sessionFactory.close();
	}
	
	@After
	public void setDown(){
	}

	@Test
	public void savePiezaEnDisco(){
		PedidoDAO pedidoDao = new PedidoDAOImpl();
		Pedido pedidoPersistible = new Pedido();
		
		pedidoDao.save(pedidoPersistible);
		
		Pedido pedidoPersistido = pedidoDao.findByID(pedidoPersistible.getId());
		
		Assert.assertEquals("Las piezas no se corresponden, no representan lo mismo.",pedidoPersistible, pedidoPersistido);
		
	}

	//********************************************
	//** TEST METHODS
	//********************************************
//	/**
//	 * Consulta un pedido existente en la BD por su ID
//	 */
//	@Test 
//	public void consultarUnPedidoPorID(){
//
//	}
	
//	/**
//	 * Consulta un pedido que no existe en la BD por su ID
//	 */
//	@Test(expected=PedidoInexistenteException.class) 
//	public void consultarUnPedidoInexistentePorID(){
//
//	}
	
//	/**
//	 * Consulta las piezas reservadas.
//	 */
//	@Test 
//	public void consultarPedidosPorEstado(){
//
//	}

	
}
