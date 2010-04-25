package org.utn.tacs.tp.group2.hibernate.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Test;

public class DataBaseCreator{

	@Test
	public void startDB(){
        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        sessionFactory.close();
	}
}