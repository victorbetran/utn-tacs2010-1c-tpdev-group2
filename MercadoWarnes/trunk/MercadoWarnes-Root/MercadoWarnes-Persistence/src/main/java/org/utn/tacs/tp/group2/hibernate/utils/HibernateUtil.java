package org.utn.tacs.tp.group2.hibernate.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {
	
    public static void doExecute(final SessionClosure closure) {
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