package org.utn.tacs.tp.group2.daos.implementations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.utn.tacs.tp.group2.persistence.PersistentObject;

public abstract class AbstractDao<T extends PersistentObject> extends HibernateDaoSupport{

    //********************************************
	//** UTIL METHODS
	//********************************************

    protected QueryHandler<T> getQueryHandler(){
    	return new QueryHandler<T>(this.getSession());
    }
    
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}	

	public void remove(T t) {
		this.getHibernateTemplate().delete(t);
	}

	public Boolean isPersisted(T t) {
		return this.getHibernateTemplate().get(getGenericClass(), t.getId()) != null;
	}
	
	@SuppressWarnings("unchecked")
	public T findByID(final Long id) {
		return (T) this.getHibernateTemplate().load(getGenericClass(), id);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		// TODO: TEST!
		return (List<T>) this.getHibernateTemplate().loadAll(getGenericClass());
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
