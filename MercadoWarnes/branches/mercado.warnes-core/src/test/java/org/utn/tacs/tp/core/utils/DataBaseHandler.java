package org.utn.tacs.tp.core.utils;

import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.junit.After;
import org.junit.Before;

public class DataBaseHandler {

	Transaction transaction;
	private Session session;
	@Before
	public void setUp() {
		session = SessionProvider.getInstance().getSession();
		transaction = session.beginTransaction();
	}

	@After
	public void setDown() {
		transaction.rollback();
		SessionProvider.getInstance().killSession();
	}

}
