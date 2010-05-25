package org.utn.tacs.tp.group2.persistence.utils;

import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.utn.tacs.tp.group2.daos.implementations.AbstractDao;
import org.utn.tacs.tp.group2.persistence.PersistentObject;

public abstract class DataBaseHandler<T extends PersistentObject> {

	Transaction transaction;
//	private Session session;
	@Before
	public void setUp() {
//		((HibernateDaoSupport)getDao()).
//		session = SessionProvider.getInstance().getSession();
//		transaction = session.beginTransaction();
//		getDao().getSessionFactory().openSession();
//		transaction = getDao().getSessionFactory().getCurrentSession().getTransaction();
//		getDao().getSessionFactory().getCurrentSession().beginTransaction();
		getDao().beginTransaction();
	}

	@After
	public void setDown() {
//		transaction.rollback();
//		SessionProvider.getInstance().killSession();
//		transaction.rollback();
//		getDao().getSessionFactory().getCurrentSession().getTransaction().rollback();
		getDao().rollbackTransaction();
	}

	protected abstract AbstractDao<T> getDao();
	
}
