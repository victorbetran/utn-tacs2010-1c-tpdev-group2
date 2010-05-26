package org.utn.tacs.tp.group2.persistence.utils;

import org.junit.After;
import org.junit.Before;
import org.utn.tacs.tp.group2.daos.implementations.AbstractDao;
import org.utn.tacs.tp.group2.persistence.PersistentObject;

public abstract class DataBaseHandler<T extends PersistentObject> {

	@Before
	public void setUp() {
		getDao().beginTransaction();
	}

	@After
	public void setDown() {
		getDao().rollbackTransaction();
	}

	protected abstract AbstractDao<T> getDao();
	
}
