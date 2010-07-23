package org.utn.tacs.tp.group2.daos.implementations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.utn.tacs.tp.group2.daos.exceptions.PedidoInexistenteException;
import org.utn.tacs.tp.group2.daos.interfaces.PedidoDAO;
import org.utn.tacs.tp.group2.pedido.EstadoPedido;
import org.utn.tacs.tp.group2.pedido.Pedido;

public class PedidoDAOImpl extends HibernateDaoSupport implements PedidoDAO {

    public QueryHandler<Pedido> getQueryHandler(){
    	return new QueryHandler<Pedido>(this.getSession());
    }
    
	public void save(Pedido t) {
		this.getHibernateTemplate().save(t);
	}	

	public void remove(Pedido t) {
		this.getHibernateTemplate().delete(t);
	}

	public Boolean isPersisted(Pedido t) {
		return this.existsId(t.getId());
	}
	
	public Boolean existsId(Long id){
		return this.getHibernateTemplate().get(Pedido.class, id) != null;
	}
	
	public Pedido findByID(final Long id) {
		if( this.getHibernateTemplate().get(Pedido.class, id) == null ){
			throw getNotFoundObjectException(id);
		}
		return (Pedido) this.getHibernateTemplate().load(Pedido.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Pedido> findAll() {
		// TODO: TEST!
		return (List<Pedido>) this.getHibernateTemplate().loadAll(Pedido.class);
	}
	
	
	//********************************************
	//** PRIVATE IMPLEMENTATION
	//********************************************
	
	@SuppressWarnings("hiding")
	public
	static class QueryHandler<Pedido>{
		Session session;
	    	
		public QueryHandler(Session s) {
			session = s;
		}
		    	
		Query q;
		public QueryHandler<Pedido> setBody(String query) {
			q = session.createQuery(query);
			return this;
		}
				
		public QueryHandler<Pedido> addParameter(String name,Object value){
			q.setParameter(name, value);
			return this;
		}
				
		@SuppressWarnings("unchecked")
		public List<Pedido> getResults(){
			ArrayList<Pedido> result = new ArrayList<Pedido>();
			result.addAll(q.list());
			return result;
		}
		
		@SuppressWarnings("unchecked")
			public Pedido getResult(){
				return (Pedido)q.uniqueResult();
		}
	}
	
	public List<Pedido> findByEstado(EstadoPedido estado) {
		return getQueryHandler().setBody("from Pedido as pedido WHERE pedido.estado = :est")
								.addParameter("est", estado)
								.getResults();
	}

	public RuntimeException getNotFoundObjectException(Long id) {
		return new PedidoInexistenteException("No existe una pedido con el ID: '" + id + "'");
	}
}
