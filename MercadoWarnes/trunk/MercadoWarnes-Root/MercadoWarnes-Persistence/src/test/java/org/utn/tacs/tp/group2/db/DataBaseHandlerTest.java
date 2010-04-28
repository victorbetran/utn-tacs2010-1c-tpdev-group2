package org.utn.tacs.tp.group2.db;

import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.junit.After;
import org.junit.Before;
import org.utn.tacs.tp.group2.hibernate.SessionProvider;

public class DataBaseHandlerTest {

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
