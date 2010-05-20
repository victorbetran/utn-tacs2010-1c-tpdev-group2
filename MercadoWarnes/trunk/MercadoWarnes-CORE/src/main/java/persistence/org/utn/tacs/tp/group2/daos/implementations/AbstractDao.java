package org.utn.tacs.tp.group2.daos.implementations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class AbstractDao<T> extends HibernateDaoSupport{

    //********************************************
	//** UTIL METHODS
	//********************************************
    
//    protected Session getSession(){
//    	return SessionProvider.getInstance().getSession();
//    }

    protected QueryHandler<T> getQueryHandler(){
    	return new QueryHandler<T>(getSession());
    }
    
	public void save(T t) {
		//getSession().save(t);
		getHibernateTemplate().save(t);
	}	

	public void remove(T t) {
//		getHibernateTemplate().delete(t);
		getSession().delete(t);
	}

	public Boolean isPersisted(T t) {
		return getSession().contains(t);
//		return isPersisted(t);
	}
	
	@SuppressWarnings("unchecked")
	public T findByID(final Long id) {
		return (T) getSession().load(getGenericClass(), id);
//		return (T) getHibernateTemplate().load(getGenericClass(), id);
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
