package org.utn.tacs.tp.group2.db.pedido;

import org.utn.tacs.tp.group2.daos.interfaces.PedidoDAO;
import org.utn.tacs.tp.group2.db.DataBaseHandlerTest;

public abstract class PedidoTest extends DataBaseHandlerTest{

	protected PedidoDAO dao;
	
	@Override
	public void setUp() {
		super.setUp();
		dao = PedidoDAO.getInstance();
	}
	
}
