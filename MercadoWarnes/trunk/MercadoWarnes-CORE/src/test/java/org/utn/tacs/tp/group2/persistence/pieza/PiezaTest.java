package org.utn.tacs.tp.group2.persistence.pieza;

import org.utn.tacs.tp.group2.daos.implementations.AbstractDao;
import org.utn.tacs.tp.group2.daos.interfaces.PiezaDAO;
import org.utn.tacs.tp.group2.persistence.utils.DataBaseHandler;
import org.utn.tacs.tp.group2.pieza.Pieza;

public abstract class PiezaTest extends DataBaseHandler<Pieza>{

	protected PiezaDAO dao; 
	@Override
	public void setUp() {
		super.setUp();
		dao = PiezaDAO.getInstance();
	}
	
	@Override
	protected AbstractDao<Pieza> getDao() {
		return PiezaDAO.getInstance();
	}
	
}
