package org.utn.tacs.tp.group2.daos.implementations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.utn.tacs.tp.group2.daos.exceptions.PiezaInexistenteException;
import org.utn.tacs.tp.group2.daos.interfaces.PiezaDAO;
import org.utn.tacs.tp.group2.pieza.Auto;
import org.utn.tacs.tp.group2.pieza.EstadoPieza;
import org.utn.tacs.tp.group2.pieza.Pieza;

public class PiezaDAOImpl extends HibernateDaoSupport implements PiezaDAO{
	
    public QueryHandler<Pieza> getQueryHandler(){
    	return new QueryHandler<Pieza>(this.getSession());
    }
    
	public void save(Pieza pieza) {
		this.getHibernateTemplate().save(pieza);
	}	

	public void remove(Pieza pieza) {
		this.getHibernateTemplate().delete(pieza);
	}

	public Boolean isPersisted(Pieza pieza) {
		return this.existsId(pieza.getId());
	}
	
	public Boolean existsId(Long id){
		return this.getHibernateTemplate().get(Pieza.class, id) != null;
	}
	
	public Pieza findByID(final Long id) {
		if( this.getHibernateTemplate().get(Pieza.class, id) == null ){
			throw getNotFoundObjectException(id);
		}
		return (Pieza) this.getHibernateTemplate().load(Pieza.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Pieza> findAll() {
		// TODO: TEST!
		return (List<Pieza>) this.getHibernateTemplate().loadAll(Pieza.class);
	}
	
	
	public List<Pieza> findByAuto(final Auto auto) {
		return getQueryHandler().setBody("from Pieza as pieza inner join fetch pieza.autoOrigen WHERE pieza.autoOrigen = :aut")
								.addParameter("aut", auto)
								.getResults();
	}

	public List<Pieza> findByAutoId(Long autoId) {
		return getQueryHandler().setBody("from Pieza as pieza inner join fetch pieza.autoOrigen WHERE pieza.autoOrigen.id = :autId")
								.addParameter("autId", autoId)
								.getResults();
	}
	
	public List<Pieza> findByCategoria(final String categoria) {
		return getQueryHandler().setBody("FROM Pieza WHERE categoria = :cat")
								.addParameter("cat", categoria)
								.getResults();
	}
	
	public Pieza findByCodigo(String codigo) {			
		return getQueryHandler().setBody("FROM Pieza WHERE codigo = :cod")
								.addParameter("cod", codigo)
								.getResult();
	}

	public List<Pieza> findByEstado(EstadoPieza estado) {
		return getQueryHandler().setBody("from Pieza as pieza WHERE pieza.estado = :est")
								.addParameter("est", estado)
								.getResults();
	}
	
	public List<Pieza> findByEstadoAndAutoId(EstadoPieza estado, Long autoId) {
		// TODO: TEST en la parte de DAOS!
		return getQueryHandler().setBody("from Pieza as pieza inner join fetch pieza.autoOrigen WHERE pieza.autoOrigen.id = :autId AND pieza.estado = :est")
				.addParameter("est", estado)
				.addParameter("autId", autoId)
				.getResults();
	}
	
	//********************************************
	//** PRIVATE IMPLEMENTATION
	//********************************************
	
	@SuppressWarnings("hiding")
	public
	static class QueryHandler<Pieza>{
		Session session;
	    	
		public QueryHandler(Session s) {
			session = s;
		}
		    	
		Query q;
		public QueryHandler<Pieza> setBody(String query) {
			q = session.createQuery(query);
			return this;
		}
				
		public QueryHandler<Pieza> addParameter(String name,Object value){
			q.setParameter(name, value);
			return this;
		}
				
		@SuppressWarnings("unchecked")
		public List<Pieza> getResults(){
			ArrayList<Pieza> result = new ArrayList<Pieza>();
			result.addAll(q.list());
			return result;
		}
		
		@SuppressWarnings("unchecked")
			public Pieza getResult(){
				return (Pieza) q.uniqueResult();
		}
	}
	
	public RuntimeException getNotFoundObjectException(Long id) {
		return new PiezaInexistenteException("No existe una pieza con el ID: '" + id + "'");
	}
	
}
