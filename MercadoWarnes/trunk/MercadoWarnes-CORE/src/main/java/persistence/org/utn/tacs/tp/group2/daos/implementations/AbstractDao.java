package org.utn.tacs.tp.group2.daos.implementations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.utn.tacs.tp.group2.persistence.PersistentObject;

public abstract class AbstractDao<T extends PersistentObject> extends HibernateDaoSupport{

    //********************************************
	//** UTIL METHODS
	//********************************************
    
//    protected Session this.currentSession{
//    	return SessionProvider.getInstance().this.currentSession;
//    }

	private Transaction currentTransaction;
	private Session currentSession;
	public void beginTransaction() {
//		this.currentTransaction = getSessionFactory().openSession().beginTransaction();
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
//		getHibernateTemplate().save(t);
		this.currentSession.save(t);
	}	

	public void remove(T t) {
//		getHibernateTemplate().delete(t);
		this.currentSession.delete(t);
	}

	public Boolean isPersisted(T t) {
//		return getHibernateTemplate().get(getGenericClass(), t.getId()) != null;
		return this.currentSession.get(getGenericClass(), t.getId()) != null;
	}
	
	@SuppressWarnings("unchecked")
	public T findByID(final Long id) {
		return (T) this.currentSession.load(getGenericClass(), id);
//		return (T) getHibernateTemplate().get(getGenericClass(), id);
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
