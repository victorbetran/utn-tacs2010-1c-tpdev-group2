package org.utn.tacs.tp.group2.persistence.utils;

import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.utn.tacs.tp.group2.daos.implementations.AbstractDao;

public abstract class DataBaseHandler<T> {

	Transaction transaction;
//	private Session session;
	@Before
	public void setUp() {
//		((HibernateDaoSupport)getDao()).
//		session = SessionProvider.getInstance().getSession();
//		transaction = session.beginTransaction();
//		transaction = getDao().getSessionFactory().getCurrentSession().beginTransaction();
	}

	@After
	public void setDown() {
//		transaction.rollback();
//		SessionProvider.getInstance().killSession();
//		transaction.rollback();
	}

	protected abstract AbstractDao<T> getDao();
	
}
