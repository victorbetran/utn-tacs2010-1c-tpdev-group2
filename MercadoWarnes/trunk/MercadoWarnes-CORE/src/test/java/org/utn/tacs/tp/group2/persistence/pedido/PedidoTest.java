package org.utn.tacs.tp.group2.persistence.pedido;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.utn.tacs.tp.group2.daos.implementations.AbstractDao;
import org.utn.tacs.tp.group2.daos.interfaces.PedidoDAO;
import org.utn.tacs.tp.group2.pedido.Pedido;
import org.utn.tacs.tp.group2.persistence.utils.DataBaseHandler;

public abstract class PedidoTest extends DataBaseHandler<Pedido>{

	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	protected PedidoDAO dao = (PedidoDAO) applicationContext.getBean("pedidoDAO");
	
	@Override
	public void setUp() {
		super.setUp();
	}
	
	@Override
	protected AbstractDao<Pedido> getDao() {
		return PedidoDAO.getInstance();
	}
	
}
