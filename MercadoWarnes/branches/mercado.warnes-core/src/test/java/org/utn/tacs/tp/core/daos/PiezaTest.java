package org.utn.tacs.tp.core.daos;

import org.utn.tacs.tp.core.utils.DataBaseHandler;

public abstract class PiezaTest extends DataBaseHandler{

	protected PiezaDAO dao; 
	@Override
	public void setUp() {
		super.setUp();
		dao = PiezaDAO.getInstance();
	}
	
}

