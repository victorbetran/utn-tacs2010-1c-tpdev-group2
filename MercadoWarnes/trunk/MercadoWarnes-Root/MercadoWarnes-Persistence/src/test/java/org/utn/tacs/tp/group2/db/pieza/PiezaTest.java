package org.utn.tacs.tp.group2.db.pieza;

import org.utn.tacs.tp.group2.daos.interfaces.PiezaDAO;
import org.utn.tacs.tp.group2.db.DataBaseHandlerTest;

public abstract class PiezaTest extends DataBaseHandlerTest{

	protected PiezaDAO dao; 
	@Override
	public void setUp() {
		super.setUp();
		dao = PiezaDAO.getInstance();
	}
	
}
