package org.utn.tacs.tp.group2.db.pedido;

import org.utn.tacs.tp.group2.daos.interfaces.PedidoDAO;
import org.utn.tacs.tp.group2.db.DataBaseHandlerTest;

public class AbmDePedidosTest extends DataBaseHandlerTest{

	private PedidoDAO dao;
	
	@Override
	public void setUp() {
		super.setUp();
		dao = PedidoDAO.getInstance();
	}
	
}
