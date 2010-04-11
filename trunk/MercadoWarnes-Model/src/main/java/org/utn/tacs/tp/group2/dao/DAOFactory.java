package org.utn.tacs.tp.group2.dao;

import org.utn.tacs.tp.group2.mocks.PedidoDAOMock;
import org.utn.tacs.tp.group2.mocks.PiezaDAOMock;

//TODO: esto luego deberia transformarse en una Interface con varias implementaciones,
//las cuales devuelvan los DAOs correspondientes. La factory debería ser un Singleton.
public abstract class DAOFactory {

	public static PiezaDAO getPiezaDAO() {
		return new PiezaDAOMock();
	}

	public static PedidoDAO getPedidoDAO() {
		return new PedidoDAOMock();
	}
}
