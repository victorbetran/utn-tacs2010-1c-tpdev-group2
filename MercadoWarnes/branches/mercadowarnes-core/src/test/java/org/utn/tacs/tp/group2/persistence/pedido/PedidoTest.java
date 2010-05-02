package org.utn.tacs.tp.group2.persistence.pedido;

import org.utn.tacs.tp.group2.persistence.daos.interfaces.PedidoDAO;
import org.utn.tacs.tp.group2.persistence.utils.DataBaseHandler;

public abstract class PedidoTest extends DataBaseHandler{

	protected PedidoDAO dao;
	
	@Override
	public void setUp() {
		super.setUp();
		dao = PedidoDAO.getInstance();
	}
	
}
