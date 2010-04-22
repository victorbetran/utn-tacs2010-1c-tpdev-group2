package org.utn.tacs.tp.group2.daos.mocks;

import org.utn.tacs.tp.group2.daos.interfaces.DAOFactory;
import org.utn.tacs.tp.group2.daos.interfaces.PedidoDAO;
import org.utn.tacs.tp.group2.daos.interfaces.PiezaDAO;

public class MockDAOFactory implements DAOFactory{

	private static DAOFactory INSTANCE;
	
	private MockDAOFactory() {}
	
	public static DAOFactory getInstance(){
		if(INSTANCE==null)
			INSTANCE = new MockDAOFactory();
		return INSTANCE;
	}
	
	public PiezaDAO getPiezaDAO() {
		return new PiezaDAOMock();
	}

	public PedidoDAO getPedidoDAO() {
		return new PedidoDAOMock();
	}
}
