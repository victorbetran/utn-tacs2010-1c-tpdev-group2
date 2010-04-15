package org.utn.tacs.tp.group2.mocks;

import org.utn.tacs.tp.group2.dao.PedidoDAO;
import org.utn.tacs.tp.group2.dao.PiezaDAO;

//TODO: esto luego deberia transformarse en una Interface con varias implementaciones,
//las cuales devuelvan los DAOs correspondientes. La factory deber�a ser un Singleton.
public abstract class MockDAOFactory {

	public static PiezaDAO getPiezaDAO() {
		return new PiezaDAOMock();
	}

	public static PedidoDAO getPedidoDAO() {
		return new PedidoDAOMock();
	}
}
