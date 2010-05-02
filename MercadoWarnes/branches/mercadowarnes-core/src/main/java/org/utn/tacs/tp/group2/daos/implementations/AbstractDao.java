package org.utn.tacs.tp.group2.daos.implementations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.utn.tacs.tp.group2.utils.SessionProvider;

public abstract class AbstractDao<T> {

    //********************************************
	//** UTIL METHODS
	//********************************************
    
    protected Session getSession(){
    	return SessionProvider.getInstance().getSession();
    }

    protected QueryHandler<T> getQueryHandler(){
    	return new QueryHandler<T>(getSession());
    }
    
	public void save(T t) {
		getSession().save(t);
	}	

	public void remove(T t) {
		getSession().delete(t);
	}

	public Boolean isPersisted(T t) {
		return getSession().contains(t);
	}
	
	@SuppressWarnings("unchecked")
	public T findByID(final Long id) {
		return (T) getSession().load(getGenericClass(), id);
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
