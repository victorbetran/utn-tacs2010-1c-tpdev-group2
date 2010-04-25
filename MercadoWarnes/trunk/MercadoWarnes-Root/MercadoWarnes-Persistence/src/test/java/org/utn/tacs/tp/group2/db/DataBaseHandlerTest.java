package org.utn.tacs.tp.group2.db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.After;
import org.junit.Before;

public class DataBaseHandlerTest {

	private SessionFactory sessionFactory;

	@Before
	public void setUp() {
		sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		sessionFactory.close();
	}

	@After
	public void setDown() {
		//TODO Ver bien si no hay que hacer alguna cosa para bajar/limpiar la DB.
	}

}
