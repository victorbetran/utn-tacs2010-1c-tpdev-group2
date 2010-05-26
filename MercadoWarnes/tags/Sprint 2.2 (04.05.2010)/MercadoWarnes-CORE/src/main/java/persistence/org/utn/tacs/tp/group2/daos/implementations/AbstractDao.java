package org.utn.tacs.tp.group2.daos.implementations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.utn.tacs.tp.group2.persistence.PersistentObject;

public abstract class AbstractDao<T extends PersistentObject>{

    //********************************************
	//** UTIL METHODS
	//********************************************

	private Transaction currentTransaction;
	private Session currentSession;
	public void beginTransaction() {
		this.currentSession = getSessionFactory().openSession();
		this.currentTransaction = this.currentSession.beginTransaction();
	}

	public void commitTransaction(){
		this.currentTransaction.commit();
		this.currentSession.flush();
		this.currentSession.close();
	}
	
	public void rollbackTransaction(){
		this.currentTransaction.rollback();
	}
	
    protected QueryHandler<T> getQueryHandler(){
    	return new QueryHandler<T>(this.currentSession);
    }
    
	public void save(T t) {
		this.currentSession.save(t);
	}	

	public void remove(T t) {
		this.currentSession.delete(t);
	}

	public Boolean isPersisted(T t) {
		return this.currentSession.get(getGenericClass(), t.getId()) != null;
	}
	
	@SuppressWarnings("unchecked")
	public T findByID(final Long id) {
		return (T) this.currentSession.load(getGenericClass(), id);
	}
    
	//********************************************
	//** SPRING INTERFACE
	//********************************************

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	private SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
	
	//********************************************
	//** ABSTRACT METHOD
	//********************************************
	
	protected abstract Class<T> getGenericClass();
	
	//********************************************
	//** PRIVATE IMPLEMENTATION
	//********************************************
	
	static protected class QueryHandler<T>{
		Session session;
	    	
		public QueryHandler(Session s) {
			session = s;
		}
		    	
		Query q;
		public QueryHandler<T> setBody(String query) {
			q = session.createQuery(query);
			return this;
		}
				
		public QueryHandler<T> addParameter(String name,Object value){
			q.setParameter(name, value);
			return this;
		}
				
		@SuppressWarnings("unchecked")
		public List<T> getResults(){
			ArrayList<T> result = new ArrayList<T>();
			result.addAll(q.list());
			return result;
		}
		
		@SuppressWarnings("unchecked")
			public T getResult(){
				return (T)q.uniqueResult();
		}
	}
	
}
