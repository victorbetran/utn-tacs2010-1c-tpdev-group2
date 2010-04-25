package org.utn.tacs.tp.group2.daos.implementations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

public class AbstractDao {

    public static void doExecute(final Command closure) {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;

        try {
        	sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            closure.execute(session);

            transaction.commit();
        }
        
        
        catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        finally {
            if (session != null) {
                session.close();
                sessionFactory.close();
            }
        }
    }
	
}
