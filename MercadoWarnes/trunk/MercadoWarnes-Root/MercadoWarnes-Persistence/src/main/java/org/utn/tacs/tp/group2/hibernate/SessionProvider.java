package org.utn.tacs.tp.group2.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;

public class SessionProvider {

	private static SessionProvider instance = null;
	private Session session = null;
	
	private SessionProvider() {
	}
	
	public static SessionProvider getInstance(){
		if(instance == null){
			instance = new SessionProvider();
		}
		return instance;
	}


	public Session getSession() {
		if(session == null){
			try{
	        	SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
	            session = sessionFactory.openSession();
			}
	        catch (Exception e) {
	        	// TODO: Apesta esto!, mal!
	            e.printStackTrace();
	        }
		}
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public void killSession() {
		if(this.session != null){
			this.session.close();
			this.session = null;
		}
	}
	
}
