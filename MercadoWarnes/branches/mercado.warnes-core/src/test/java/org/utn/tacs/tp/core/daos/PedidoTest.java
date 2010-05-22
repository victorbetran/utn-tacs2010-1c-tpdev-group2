package org.utn.tacs.tp.core.daos;

import org.utn.tacs.tp.core.utils.DataBaseHandler;

public abstract class PedidoTest extends DataBaseHandler{

	protected PedidoDAO dao;
	
	@Override
	public void setUp() {
		super.setUp();
		dao = PedidoDAO.getInstance();
	}
	
}
