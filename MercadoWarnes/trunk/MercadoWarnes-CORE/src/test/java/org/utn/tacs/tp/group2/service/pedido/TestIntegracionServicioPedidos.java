package org.utn.tacs.tp.group2.service.pedido;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.restlet.ext.spring.SpringRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.utn.tacs.tp.group2.daos.interfaces.PiezaDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class TestIntegracionServicioPedidos {

	@Autowired
	private SpringRouter router;
	
	@Autowired
	private PiezaDAO piezaDao;
	
	@Autowired
	private PiezaDAO pedidoDao;
	
	@Test
	public void asiMvnTestNoJode(){
		// TODO TODO!!!!
	}
	
	//********************************************
	//** GETTER & SETTERS
	//********************************************
	
	public SpringRouter getRouter() {
		return router;
	}
	
	public void setRouter(SpringRouter router) {
		this.router = router;
	}
	
	public PiezaDAO getPiezaDao() {
		return piezaDao;
	}
	
	public void setPiezaDao(PiezaDAO piezaDao) {
		this.piezaDao = piezaDao;
	}
	
	public PiezaDAO getPedidoDao() {
		return pedidoDao;
	}
	
	public void setPedidoDao(PiezaDAO pedidoDao) {
		this.pedidoDao = pedidoDao;
	}
	
	
}
